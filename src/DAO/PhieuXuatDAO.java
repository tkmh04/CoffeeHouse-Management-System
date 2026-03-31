package DAO;

import DTO.ChiTietHoaDonDTO;
import DTO.HoaDonDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

public class PhieuXuatDAO {
    private Connection conn;
    private String lastError = "";

    public PhieuXuatDAO() {
        conn = ConnectDataBaseDB.getConnection();
        if (conn == null) {
            try {
                new ConnectDataBaseDB();
                conn = ConnectDataBaseDB.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void ensureConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            new ConnectDataBaseDB();
            conn = ConnectDataBaseDB.getConnection();
        }
    }

    public String getLastError() {
        return lastError;
    }

    public int themHoaDon(Integer idNv, Integer idKhMember, double total) {
        lastError = "";
        String sql = "INSERT INTO hoadon(idNv, idKhMember, timeHd, total) VALUES (?, ?, NOW(), ?)";
        try {
            ensureConnection();
        } catch (SQLException e) {
            lastError = e.getMessage();
            e.printStackTrace();
            return -1;
        }

        try (PreparedStatement pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            if (idNv == null) {
                pre.setNull(1, Types.INTEGER);
            } else {
                pre.setInt(1, idNv);
            }

            if (idKhMember == null) {
                pre.setNull(2, Types.INTEGER);
            } else {
                pre.setInt(2, idKhMember);
            }

            pre.setDouble(3, total);
            int affected = pre.executeUpdate();
            if (affected == 0) {
                return -1;
            }

            try (ResultSet rs = pre.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            lastError = ex.getMessage();
            if (isMissingAutoIncrementError(ex)) {
                return insertHoaDonManualId(idNv, idKhMember, total);
            }
            ex.printStackTrace();
        }
        return -1;
    }

    private int insertHoaDonManualId(Integer idNv, Integer idKhMember, double total) {
        String sqlMax = "SELECT IFNULL(MAX(idHd), 0) + 1 FROM hoadon";
        String sqlInsert = "INSERT INTO hoadon(idHd, idNv, idKhMember, timeHd, total) VALUES (?, ?, ?, NOW(), ?)";
        try {
            ensureConnection();
        } catch (SQLException e) {
            lastError = e.getMessage();
            e.printStackTrace();
            return -1;
        }

        try (PreparedStatement preMax = conn.prepareStatement(sqlMax);
             ResultSet rs = preMax.executeQuery()) {
            int nextId = 1;
            if (rs.next()) {
                nextId = rs.getInt(1);
            }

            try (PreparedStatement pre = conn.prepareStatement(sqlInsert)) {
                pre.setInt(1, nextId);
                if (idNv == null) {
                    pre.setNull(2, Types.INTEGER);
                } else {
                    pre.setInt(2, idNv);
                }

                if (idKhMember == null) {
                    pre.setNull(3, Types.INTEGER);
                } else {
                    pre.setInt(3, idKhMember);
                }

                pre.setDouble(4, total);
                int affected = pre.executeUpdate();
                if (affected > 0) {
                    return nextId;
                }
            }
        } catch (SQLException ex) {
            lastError = ex.getMessage();
            ex.printStackTrace();
        }
        return -1;
    }

    private boolean isMissingAutoIncrementError(SQLException ex) {
        String msg = ex.getMessage();
        return msg != null && (msg.contains("Field 'idHd' doesn't have a default value") || msg.contains("doesn't have a default value"));
    }

    public ArrayList<HoaDonDTO> layDanhSachHoaDon() {
        ArrayList<HoaDonDTO> result = new ArrayList<>();
        String sql = "SELECT idHd, idNv, idKhMember, timeHd, total FROM hoadon ORDER BY idHd DESC";
        try {
            ensureConnection();
        } catch (SQLException e) {
            lastError = e.getMessage();
            e.printStackTrace();
            return result;
        }
        try (PreparedStatement pre = conn.prepareStatement(sql);
             ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                HoaDonDTO dto = new HoaDonDTO();
                dto.setIdHd(rs.getInt("idHd"));
                int idNv = rs.getInt("idNv");
                dto.setIdNv(rs.wasNull() ? null : idNv);
                int idKh = rs.getInt("idKhMember");
                dto.setIdKhMember(rs.wasNull() ? null : idKh);
                dto.setTimeHd(rs.getTimestamp("timeHd"));
                dto.setTotal(rs.getDouble("total"));
                result.add(dto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public boolean xoaHoaDon(int idHd) {
        xoaChiTietTheoHoaDon(idHd);
        String sql = "DELETE FROM hoadon WHERE idHd = ?";
        try {
            ensureConnection();
        } catch (SQLException e) {
            lastError = e.getMessage();
            e.printStackTrace();
            return false;
        }
        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, idHd);
            return pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean themChiTietHoaDon(int idHd, int idSp, Integer idTopping, double quantity, double price) {
        ensureChiTietHoaDonColumns();
        String sql = "INSERT INTO chitiethoadon(idHd, idSp, idTopping, quantity, price) VALUES (?, ?, ?, ?, ?)";
        try {
            ensureConnection();
        } catch (SQLException e) {
            lastError = e.getMessage();
            e.printStackTrace();
            return false;
        }

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, idHd);
            pre.setInt(2, idSp);
            if (idTopping == null) {
                pre.setNull(3, Types.INTEGER);
            } else {
                pre.setInt(3, idTopping);
            }
            pre.setDouble(4, quantity);
            pre.setDouble(5, price);
            return pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            lastError = ex.getMessage();
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<ChiTietHoaDonDTO> layChiTietHoaDon(int idHd) {
        ensureChiTietHoaDonColumns();
        ArrayList<ChiTietHoaDonDTO> result = new ArrayList<>();
        String sql = "SELECT ct.idHd, ct.idSp, ct.idTopping, "
            + "COALESCE(ct.productName, sp.nameSp) AS nameSp, "
            + "COALESCE(ct.toppingsText, tp.nameTopping) AS nameTopping, "
            + "ct.quantity, COALESCE(ct.lineTotal, ct.price) AS price "
                + "FROM chitiethoadon ct "
                + "LEFT JOIN sanpham sp ON ct.idSp = sp.idSp "
                + "LEFT JOIN topping tp ON ct.idTopping = tp.idTopping "
                + "WHERE ct.idHd = ?";
        try {
            ensureConnection();
        } catch (SQLException e) {
            lastError = e.getMessage();
            e.printStackTrace();
            return result;
        }

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, idHd);
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    ChiTietHoaDonDTO dto = new ChiTietHoaDonDTO();
                    dto.setIdHd(rs.getInt("idHd"));
                    dto.setIdSp(rs.getInt("idSp"));
                    int idTp = rs.getInt("idTopping");
                    dto.setIdTopping(rs.wasNull() ? null : idTp);
                    dto.setTenSp(rs.getString("nameSp"));
                    dto.setTenTopping(rs.getString("nameTopping"));
                    dto.setQuantity(rs.getDouble("quantity"));
                    dto.setPrice(rs.getDouble("price"));
                    result.add(dto);
                }
            }
        } catch (SQLException ex) {
            lastError = ex.getMessage();
            ex.printStackTrace();
        }
        return result;
    }

    public void xoaChiTietTheoHoaDon(int idHd) {
        String sql = "DELETE FROM chitiethoadon WHERE idHd = ?";
        try {
            ensureConnection();
        } catch (SQLException e) {
            lastError = e.getMessage();
            e.printStackTrace();
            return;
        }

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, idHd);
            pre.executeUpdate();
        } catch (SQLException ex) {
            lastError = ex.getMessage();
            ex.printStackTrace();
        }
    }

    public boolean themChiTietHoaDonDayDu(int idHd, int idSp, String productName, String sizeSp, String toppingsText, double quantity, double lineTotal) {
        ensureChiTietHoaDonColumns();
        String sql = "INSERT INTO chitiethoadon(idHd, idSp, idTopping, quantity, price, productName, sizeSp, toppingsText, lineTotal) VALUES (?, ?, NULL, ?, ?, ?, ?, ?, ?)";
        try {
            ensureConnection();
        } catch (SQLException e) {
            lastError = e.getMessage();
            e.printStackTrace();
            return false;
        }

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, idHd);
            pre.setInt(2, idSp);
            pre.setDouble(3, quantity);
            pre.setDouble(4, lineTotal);
            pre.setString(5, productName);
            pre.setString(6, sizeSp);
            pre.setString(7, toppingsText == null ? "" : toppingsText.trim());
            pre.setDouble(8, lineTotal);
            return pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            lastError = ex.getMessage();
            ex.printStackTrace();
            return false;
        }
    }

    private void ensureChiTietHoaDonColumns() {
        try {
            ensureConnection();
            try (Statement st = conn.createStatement()) {
                st.executeUpdate("ALTER TABLE chitiethoadon ADD COLUMN productName VARCHAR(255) NULL");
            } catch (SQLException ignored) {
            }

            try (Statement st = conn.createStatement()) {
                st.executeUpdate("ALTER TABLE chitiethoadon ADD COLUMN sizeSp VARCHAR(10) NULL");
            } catch (SQLException ignored) {
            }

            try (Statement st = conn.createStatement()) {
                st.executeUpdate("ALTER TABLE chitiethoadon ADD COLUMN toppingsText TEXT NULL");
            } catch (SQLException ignored) {
            }

            try (Statement st = conn.createStatement()) {
                st.executeUpdate("ALTER TABLE chitiethoadon ADD COLUMN lineTotal DOUBLE NULL");
            } catch (SQLException ignored) {
            }
        } catch (SQLException e) {
            lastError = e.getMessage();
            e.printStackTrace();
        }
    }
}

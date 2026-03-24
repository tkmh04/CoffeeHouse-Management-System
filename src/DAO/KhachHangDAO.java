package DAO;

import DTO.KhachHangDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KhachHangDAO {
    Connection conn;

    public KhachHangDAO() {
        conn = ConnectDataBaseDB.getConnection();
    }

    // Phương thức để thêm khách hàng vào cơ sở dữ liệu
    public boolean themKhachHang(KhachHangDTO kh) {
        try {
            String sql = "INSERT INTO khach_hang (`TENKH`, `SDT`, `DIACHI`, `LOAITHANHVIEN`) VALUES (?, ?, ?, ?)";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, kh.getTenKhachHang());
            pre.setString(2, kh.getsDT());
            pre.setString(3, kh.getDiaChi());
            pre.setString(4, kh.getLoaiThanhVien());
            int result = pre.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Phương thức để xoá khách hàng từ cơ sở dữ liệu
    public boolean xoaKhachHang(int maKhachHang) {
        boolean rs = false;
        try {
            String sql = "DELETE FROM khach_hang WHERE `MAKH` = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, maKhachHang);
            rs = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return rs;
    }

    // Phương thức để cập nhật thông tin khách hàng trong cơ sở dữ liệu
    public boolean suaKhachHang(KhachHangDTO kh) {
        boolean result = false;
        try {
            String sql = "UPDATE khach_hang SET `TENKH` = ?, `SDT` = ?, `DIACHI` = ?, `LOAITHANHVIEN` = ? WHERE `MAKH` = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, kh.getTenKhachHang());
            pre.setString(2, kh.getsDT());
            pre.setString(3, kh.getDiaChi());
            pre.setString(4, kh.getLoaiThanhVien());
            pre.setInt(5, kh.getMaKhachHang());
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return result;
    }

    // Phương thức để lấy danh sách khách hàng từ cơ sở dữ liệu
    public ArrayList<KhachHangDTO> layDanhSachKhachHang() {
        try {
            String sql = "SELECT * FROM khach_hang";
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<KhachHangDTO> dsKh = new ArrayList<>();
            while (rs.next()) {
                KhachHangDTO khachHang = new KhachHangDTO();
                khachHang.setMaKhachHang(rs.getInt("MAKH"));
                khachHang.setTenKhachHang(rs.getString("TENKH"));
                khachHang.setsDT(rs.getString("SDT"));
                khachHang.setDiaChi(rs.getString("DIACHI"));
                khachHang.setLoaiThanhVien(rs.getString("LOAITHANHVIEN"));
                dsKh.add(khachHang);
            }
            return dsKh;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức để lấy khách hàng theo ID
    public KhachHangDTO getKhachHangByID(int maKhachHang) {
        try {
            String sql = "SELECT * FROM khach_hang WHERE `MAKH` = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, maKhachHang);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                KhachHangDTO khachHang = new KhachHangDTO();
                khachHang.setMaKhachHang(rs.getInt("MAKH"));
                khachHang.setTenKhachHang(rs.getString("TENKH"));
                khachHang.setsDT(rs.getString("SDT"));
                khachHang.setDiaChi(rs.getString("DIACHI"));
                khachHang.setLoaiThanhVien(rs.getString("LOAITHANHVIEN"));
                return khachHang;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức để tìm kiếm khách hàng theo tên
    public ArrayList<KhachHangDTO> timKhachHang(String tenKhachHang) {
        try {
            String sql = "SELECT * FROM khach_hang WHERE `TENKH` LIKE ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + tenKhachHang + "%");
            ResultSet rs = pre.executeQuery();
            ArrayList<KhachHangDTO> dsKh = new ArrayList<>();
            while (rs.next()) {
                KhachHangDTO khachHang = new KhachHangDTO();
                khachHang.setMaKhachHang(rs.getInt("MAKH"));
                khachHang.setTenKhachHang(rs.getString("TENKH"));
                khachHang.setsDT(rs.getString("SDT"));
                khachHang.setDiaChi(rs.getString("DIACHI"));
                khachHang.setLoaiThanhVien(rs.getString("LOAITHANHVIEN"));
                dsKh.add(khachHang);
            }
            return dsKh;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức để tìm kiếm khách hàng theo số điện thoại
    public KhachHangDTO getKhachHangBySDT(String sdt) {
        try {
            String sql = "SELECT * FROM khach_hang WHERE `SDT` = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, sdt);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                KhachHangDTO khachHang = new KhachHangDTO();
                khachHang.setMaKhachHang(rs.getInt("MAKH"));
                khachHang.setTenKhachHang(rs.getString("TENKH"));
                khachHang.setsDT(rs.getString("SDT"));
                khachHang.setDiaChi(rs.getString("DIACHI"));
                khachHang.setLoaiThanhVien(rs.getString("LOAITHANHVIEN"));
                return khachHang;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức check xem số điện thoại có tồn tại không
    public boolean checkSDT(String sdt) {
        String sql = "SELECT SDT FROM khach_hang WHERE `SDT` = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, sdt);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Phương thức check xem tên khách hàng có tồn tại không
    public boolean checkTenKhachHang(String tenKhachHang) {
        String sql = "SELECT TENKH FROM khach_hang WHERE `TENKH` = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, tenKhachHang);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
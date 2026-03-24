package DAO;

import DTO.NhanVienDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhanVienDAO {
    Connection conn;

    public NhanVienDAO() {
        conn = ConnectDataBaseDB.getConnection();
    }

    // Phương thức để thêm nhân viên vào cơ sở dữ liệu
    public boolean themNhanVien(NhanVienDTO nv) {
        try {
            String sql = "INSERT INTO nhanvien (`nameNv`, `dateOfBirthNv`, `sexualNv`, `phoneNumbNv`, `addressNv`) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, nv.getNameNhanVien());
            pre.setString(2, nv.getDateOfBirth());
            pre.setString(3, nv.getSexual());
            pre.setString(4, nv.getPhoneNumber());
            pre.setString(5, nv.getAddress());
            int result = pre.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Phương thức để xoá nhân viên từ cơ sở dữ liệu
    public boolean xoaNhanVien(int idNhanVien) {
        boolean rs = false;
        try {
            String sql = "DELETE FROM nhanvien WHERE `idNv` = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, idNhanVien);
            rs = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return rs;
    }

    // Phương thức để cập nhật thông tin nhân viên trong cơ sở dữ liệu
    public boolean suaNhanVien(NhanVienDTO nv) {
        boolean result = false;
        try {
            String sql = "UPDATE nhanvien SET `nameNv` = ?, `dateOfBirthNv` = ?, `sexualNv` = ?, `phoneNumbNv` = ?, `addressNv` = ? WHERE `idNv` = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, nv.getNameNhanVien());
            pre.setString(2, nv.getDateOfBirth());
            pre.setString(3, nv.getSexual());
            pre.setString(4, nv.getPhoneNumber());
            pre.setString(5, nv.getAddress());
            pre.setInt(6, nv.getIdNhanVien());
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return result;
    }

    // Phương thức để lấy danh sách nhân viên từ cơ sở dữ liệu
    public ArrayList<NhanVienDTO> layDanhSachNhanVien() {
        try {
            String sql = "SELECT * FROM nhanvien";
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<NhanVienDTO> dsNv = new ArrayList<>();
            while (rs.next()) {
                NhanVienDTO nhanVien = new NhanVienDTO();
                nhanVien.setIdNhanVien(rs.getInt("idNv"));
                nhanVien.setNameNhanVien(rs.getString("nameNv"));
                nhanVien.setDateOfBirth(rs.getString("dateOfBirthNv"));
                nhanVien.setSexual(rs.getString("sexualNv"));
                nhanVien.setPhoneNumber(rs.getString("phoneNumbNv"));
                nhanVien.setAddress(rs.getString("addressNv"));
                dsNv.add(nhanVien);
            }
            return dsNv;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức để lấy nhân viên theo ID
    public NhanVienDTO getNhanVienByID(int idNhanVien) {
        try {
            String sql = "SELECT * FROM nhanvien WHERE `idNv` = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, idNhanVien);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                NhanVienDTO nhanVien = new NhanVienDTO();
                nhanVien.setIdNhanVien(rs.getInt("idNv"));
                nhanVien.setNameNhanVien(rs.getString("nameNv"));
                nhanVien.setDateOfBirth(rs.getString("dateOfBirthNv"));
                nhanVien.setSexual(rs.getString("sexualNv"));
                nhanVien.setPhoneNumber(rs.getString("phoneNumbNv"));
                nhanVien.setAddress(rs.getString("addressNv"));
                return nhanVien;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức để tìm kiếm nhân viên theo tên
    public ArrayList<NhanVienDTO> timNhanVien(String nameNhanVien) {
        try {
            String sql = "SELECT * FROM nhanvien WHERE `nameNv` LIKE ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + nameNhanVien + "%");
            ResultSet rs = pre.executeQuery();
            ArrayList<NhanVienDTO> dsNv = new ArrayList<>();
            while (rs.next()) {
                NhanVienDTO nhanVien = new NhanVienDTO();
                nhanVien.setIdNhanVien(rs.getInt("idNv"));
                nhanVien.setNameNhanVien(rs.getString("nameNv"));
                nhanVien.setDateOfBirth(rs.getString("dateOfBirthNv"));
                nhanVien.setSexual(rs.getString("sexualNv"));
                nhanVien.setPhoneNumber(rs.getString("phoneNumbNv"));
                nhanVien.setAddress(rs.getString("addressNv"));
                dsNv.add(nhanVien);
            }
            return dsNv;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức để tìm kiếm nhân viên theo số điện thoại
    public NhanVienDTO getNhanVienByPhoneNumber(String phoneNumber) {
        try {
            String sql = "SELECT * FROM nhanvien WHERE `phoneNumbNv` = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, phoneNumber);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                NhanVienDTO nhanVien = new NhanVienDTO();
                nhanVien.setIdNhanVien(rs.getInt("idNv"));
                nhanVien.setNameNhanVien(rs.getString("nameNv"));
                nhanVien.setDateOfBirth(rs.getString("dateOfBirthNv"));
                nhanVien.setSexual(rs.getString("sexualNv"));
                nhanVien.setPhoneNumber(rs.getString("phoneNumbNv"));
                nhanVien.setAddress(rs.getString("addressNv"));
                return nhanVien;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức check xem số điện thoại có tồn tại không
    public boolean checkPhoneNumber(String phoneNumber) {
        String sql = "SELECT phoneNumbNv FROM nhanvien WHERE `phoneNumbNv` = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, phoneNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Phương thức check xem tên nhân viên có tồn tại không
    public boolean checkNameNhanVien(String nameNhanVien) {
        String sql = "SELECT nameNv FROM nhanvien WHERE `nameNv` = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, nameNhanVien);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}

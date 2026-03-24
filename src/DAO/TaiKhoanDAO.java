/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietQuyenDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import DTO.TaiKhoanDTO;


public class TaiKhoanDAO {
    Connection conn;

    public TaiKhoanDAO() {
        conn = ConnectDataBaseDB.getConnection();
    }

    // Phương thức để thêm tài khoản vào cơ sở dữ liệu
    public boolean themTaiKhoan(TaiKhoanDTO tk) {
        try {
            String sql = "INSERT INTO taikhoan(`idAccount`, `idNv`, `userName`, `passWord`, `idQuyen`, `status`) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, tk.getIdNhanVien());
            pre.setInt(2, tk.getIdTaiKhoan());
            pre.setString(3, tk.getTen());
            pre.setString(4, tk.getPassword());
            pre.setInt(5, tk.getIdQuyen());
            pre.setInt(6, tk.getStatus());
            int result = pre.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Phương thức để xoá tài khoản từ cơ sở dữ liệu
    public boolean xoaTaiKhoan(int idTaiKhoan) {
        boolean rs = false;
        try {
            String sql = "DELETE FROM taikhoan WHERE `idAccount` = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, idTaiKhoan);
            rs = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return rs;
    }

    // Phương thức để cập nhật thông tin tài khoản trong cơ sở dữ liệu
    public boolean capNhatTaiKhoan(TaiKhoanDTO tk) {
    boolean result = false;
    try {
        String sql = "UPDATE taikhoan SET `idAccount`= ?, `idNv` = ?, `userName` = ?, `passWord` = ?, `idQuyen` = ?, `status` = ? WHERE `idAccount` = ?";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setInt(1, tk.getIdTaiKhoan());
        pre.setInt(2, tk.getIdNhanVien());
        pre.setString(3, tk.getTen());
        pre.setString(4, tk.getPassword());
        pre.setInt(5, tk.getIdQuyen());
        pre.setInt(6, tk.getStatus());
        // Set the last parameter to the idAccount for the WHERE clause
        pre.setInt(7, tk.getIdTaiKhoan());
        result = pre.executeUpdate() > 0;
    } catch (SQLException ex) {
        ex.printStackTrace(); // Print stack trace for debugging
        return false;
    }
    return result;
}

    // Phương thức để lấy danh sách tài khoản từ cơ sở dữ liệu
    public ArrayList<TaiKhoanDTO> layDanhSachTaiKhoan() {
        try {
            String sql = "SELECT * FROM taikhoan";
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<TaiKhoanDTO> dstk = new ArrayList<>();
            while (rs.next()) {
                TaiKhoanDTO taiKhoan = new TaiKhoanDTO();
                taiKhoan.setIdTaiKhoan(rs.getInt(1));
                taiKhoan.setIdNhanVien(rs.getInt(2));
                taiKhoan.setTen(rs.getString(3));
                taiKhoan.setPassword(rs.getString(4));
                taiKhoan.setIdQuyen(rs.getInt(5));
                taiKhoan.setStatus(rs.getInt(6));
                dstk.add(taiKhoan);
            }
            return dstk;
        } catch (SQLException e) {
        }

        return null;
    }

    public ArrayList<String> laydanhsachQuyen() throws SQLException{
        ArrayList <String> danhsachQuyen = new ArrayList<>();
        String sql = "SELECT idQuyen FROM phanquyen";
        PreparedStatement pre = conn.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        while (rs.next()){
            danhsachQuyen.add(rs.getString("idQuyen"));
        }
        return danhsachQuyen;
    }
    
    public boolean checkLogin(String username, String password) {
    String sql = "SELECT idQuyen FROM taikhoan WHERE userName = ? AND passWord = ?";
    try {
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();        
        return resultSet.next();
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;
    }
}

    
    public boolean checkUser(String username) {
        String sql = "SELECT idQuyen FROM taikhoan WHERE userName = ?";
    try {
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();        
        return resultSet.next();
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;
    }
    }
    public boolean checkPassword(String password) {
        String sql = "SELECT idQuyen FROM taikhoan WHERE passWord = ?";
    try {
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, password);
        ResultSet resultSet = preparedStatement.executeQuery();        
        return resultSet.next();
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;
    }
    }
    
    public TaiKhoanDTO getTaiKhoan(String username, String password) throws SQLException {
    String sql = "SELECT idQuyen FROM taikhoan WHERE userName = ? AND password = ?";
    try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                TaiKhoanDTO tk = new TaiKhoanDTO();
                tk.setIdQuyen(resultSet.getInt("idQuyen"));
                return tk;
            } else {
                // Nếu không tìm thấy tài khoản, trả về null
                return null;
            }
        }
    }
}
    
    
    public ArrayList<String> danhSachChucNang(int idQuyen) {
    ArrayList<String> danhSachChucNang = new ArrayList<>();
    try {
        String query = "SELECT DISTINCT chucnang.nameChucNang FROM taikhoan, chitietquyen, chucnang, phanquyen WHERE taikhoan.idQuyen = chitietquyen.idQuyen AND chucnang.maCn = chitietquyen.maCn AND phanquyen.idQuyen = chitietquyen.idQuyen AND chitietquyen.idQuyen = ? AND chitietquyen.hanhdong = \"Xem\"";
        PreparedStatement pre = conn.prepareStatement(query);
        pre.setInt(1, idQuyen);
        ResultSet rs = pre.executeQuery();
        
        while(rs.next()){
            String maCn = rs.getString("nameChucNang");
            danhSachChucNang.add(maCn);
        }
    } catch (SQLException ex){
        // Xử lý ngoại lệ nếu có
        ex.printStackTrace();
    }
    return danhSachChucNang;
}

    
    
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import DTO.PhanQuyenDTO;
import DTO.ChiTietQuyenDTO;
import DTO.ChucNangDTO;




public class PhanQuyenDAO {
    private Connection conn;

    public PhanQuyenDAO() {
        conn = JDBCUtil.getConnection();
    }

    // Phương thức để thêm tài khoản vào cơ sở dữ liệu
        public boolean themPhanQuyen(PhanQuyenDTO pq) {
        try {
            String sql = "INSERT INTO phanquyen(`idQuyen`,`tenQuyen`) VALUES (?, ?)";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, pq.getIdQuyen());
            pre.setString(2, pq.getTen());
            int result = pre.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Phương thức để xoá tài khoản từ cơ sở dữ liệu
    public boolean xoaPhanQuyen(int idQuyen) {
        boolean rs = false;
        try {
            String sql = "DELETE FROM phanquyen WHERE `idQuyen` = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, idQuyen);   
            rs = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return rs;
    }

    // Phương thức để cập nhật thông tin tài khoản trong cơ sở dữ liệu
    public boolean capNhatPhanQuyen(PhanQuyenDTO pq) {
    boolean result = false;
    try {
        String sql = "UPDATE phanquyen SET `idQuyen`= ?, `tenQuyen` = ? WHERE `idQuyen` = ?";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setInt(1, pq.getIdQuyen());
        pre.setString(2, pq.getTen());
        pre.setInt(3, pq.getIdQuyen());
        result = pre.executeUpdate() > 0;
    } catch (SQLException ex) {
        ex.printStackTrace(); // Print stack trace for debugging
        return false;
    }
    return result;
}

    // Phương thức để lấy danh sách tài khoản từ cơ sở dữ liệu
    public ArrayList<PhanQuyenDTO> layDanhSachQuyen() {
        try {
            String sql = "SELECT * FROM phanquyen";
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<PhanQuyenDTO> dspq = new ArrayList<>();
            while (rs.next()) {
                PhanQuyenDTO phanQuyen = new PhanQuyenDTO();
                phanQuyen.setIdQuyen(rs.getInt(1));
                phanQuyen.settenQuyen(rs.getString(2));
                dspq.add(phanQuyen);
            }
            return dspq;
        } catch (SQLException e) {
        }

        return null;
    }
    
    public ArrayList<Integer> layIdQuyen() {
    ArrayList<Integer> danhSachIdQuyen = new ArrayList<>();
    try {
        String sql = "SELECT idQuyen FROM phanquyen";
        PreparedStatement pre = conn.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();

        while (rs.next()) {
            int idQuyen = rs.getInt("idQuyen");
            danhSachIdQuyen.add(idQuyen);
        }
    } catch (SQLException e) {
        e.printStackTrace(); // In ra lỗi nếu có
    }
    return danhSachIdQuyen;
}

    public PhanQuyenDTO getIdvsTen(int idQuyen) throws SQLException {
    String sql = "SELECT phanquyen.tenQuyen, taikhoan.idQuyen FROM phanquyen, taikhoan WHERE phanquyen.idQuyen = taikhoan.idQuyen AND taikhoan.idQuyen = ?";
    try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
        preparedStatement.setInt(1, idQuyen);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                PhanQuyenDTO pq = new PhanQuyenDTO();
                pq.settenQuyen(resultSet.getString("tenQuyen"));
                pq.setIdQuyen(resultSet.getInt("idQuyen"));
                return pq;
            } else {
                // Nếu không tìm thấy tài khoản, trả về null
                return null;
            }
        }
    }
}
}
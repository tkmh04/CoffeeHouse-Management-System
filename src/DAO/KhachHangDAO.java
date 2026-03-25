/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.KhachHangDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class KhachHangDAO implements  interfaceDAO<KhachHangDTO> {
    
   
 

    public void updateKhachHang(KhachHangDTO khachHang) {
       

        try  {
            Connection connectDB = JDBCUtil.getConnection();
            String query = "UPDATE khach_hang SET TENKH = ?, SDT = ?, DIACHI = ?, LOAITHANHVIEN = ? WHERE MAKH = ?";
            PreparedStatement preparedStatement = (PreparedStatement) connectDB.prepareStatement(query);
            preparedStatement.setString(1, khachHang.getTenKhachHang());
            preparedStatement.setString(2, khachHang.getsDT());
            preparedStatement.setString(3, khachHang.getDiaChi());
            preparedStatement.setString(4, khachHang.getLoaiThanhVien());
            preparedStatement.setInt(5, khachHang.getMaKhachHang());

            preparedStatement.executeUpdate();
            connectDB.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     public void deleteKhachHang(int maKH) {
        

        try {
            Connection connectDB = JDBCUtil.getConnection();
            String query = "DELETE FROM khach_hang WHERE MAKH = ?";
            PreparedStatement preparedStatement = (PreparedStatement) connectDB.prepareStatement(query);
            preparedStatement.setInt(1, maKH);
            preparedStatement.executeUpdate();
            connectDB.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
      public KhachHangDTO getKhachHangById(String maKH) {
        
        KhachHangDTO khachHang = null;

        try  {
            Connection connectDB = JDBCUtil.getConnection();
            String query = "SELECT * FROM khach_hang WHERE MAKH = ?";
            PreparedStatement preparedStatement = (PreparedStatement) connectDB.prepareStatement(query);
            preparedStatement.setString(1, maKH);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    
                    khachHang = new KhachHangDTO();
                    khachHang.setMaKhachHang(resultSet.getInt("MAKH"));
                    khachHang.setTenKhachHang(resultSet.getString("TENKH"));
                    khachHang.setsDT(resultSet.getString("SDT"));
                    khachHang.setDiaChi(resultSet.getString("DIACHI"));
                    khachHang.setLoaiThanhVien(resultSet.getString("LOAITHANHVIEN"));

                }
                connectDB.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return khachHang;
    }

    @Override
    public int ADD(KhachHangDTO khachHang) {
         int result=0;
        
        try {
            Connection connectDB = JDBCUtil.getConnection();
            String query = "INSERT INTO khach_hang (MAKH,TENKH,SDT,DIACHI,LOAITHANHVIEN) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = (PreparedStatement) connectDB.prepareStatement(query);
            preparedStatement.setInt(1, khachHang.getMaKhachHang());
            preparedStatement.setString(2, khachHang.getTenKhachHang());
            preparedStatement.setString(3, khachHang.getsDT());
            preparedStatement.setString(4, khachHang.getDiaChi());
            preparedStatement.setString(5, khachHang.getLoaiThanhVien());
            
            result=preparedStatement.executeUpdate();
            
            connectDB.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int UPDATE(KhachHangDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int DELETE(KhachHangDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public KhachHangDTO selectById(KhachHangDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<KhachHangDTO> selectAll() {
         ArrayList<KhachHangDTO> danhSachKhachHang = new ArrayList<KhachHangDTO>();
   
    try {
           Connection connectDB = JDBCUtil.getConnection();
             String query = "SELECT * FROM khach_hang";
           PreparedStatement pst = (PreparedStatement) connectDB.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
        while (rs.next()){
            KhachHangDTO khachHang = new KhachHangDTO();
            khachHang.setMaKhachHang(rs.getInt("MAKH"));
            khachHang.setTenKhachHang(rs.getString("TENKH"));
            khachHang.setsDT(rs.getString("SDT"));
            khachHang.setDiaChi(rs.getString("DIACHI"));
            khachHang.setLoaiThanhVien(rs.getString("LOAITHANHVIEN"));
            
            // Thêm khách hàng vào danh sách
            danhSachKhachHang.add(khachHang);
        }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachKhachHang;
    }
//    public int addKhachHang(KhachHangDTO khachHang){
//       
    

    @Override
    public ArrayList<KhachHangDTO> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

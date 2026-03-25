/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author PC
 */

import DTO.NhanVienDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {

    private JDBCUtil connectDB;

    public NhanVienDAO() {
        try {
            connectDB = new JDBCUtil();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<NhanVienDTO> getAllNhanVien() {
        List<NhanVienDTO> danhSachNhanVien = new ArrayList<>();
        

        try {
            Connection connectDB = JDBCUtil.getConnection();
            String query = "SELECT * FROM nhanvien";
            PreparedStatement pst = (PreparedStatement) connectDB.prepareStatement(query);
            ResultSet resultSet = (ResultSet) pst.executeQuery();
            while (resultSet.next()) {
                NhanVienDTO nhanVien = new NhanVienDTO();
                nhanVien.setManhanvien(resultSet.getInt("idNv"));
                nhanVien.setTennhanvien(resultSet.getString("nameNv"));
                nhanVien.setNgaysinh(resultSet.getString("dateOfBirthNv"));
                nhanVien.setGioitinh(resultSet.getString("sexualNv"));
                nhanVien.setSdt("phoneNumbNv");
                nhanVien.setDiachi("addressNv");
                danhSachNhanVien.add(nhanVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachNhanVien;
    }

    public void addNhanVien(NhanVienDTO nhanVien) {
        String query = "INSERT INTO nhanvien (idNv, nameNv, dateOfBirthNv, sexualNv, phoneNumbNv,addressNv) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connectDB.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, nhanVien.getManhanvien());
            preparedStatement.setString(2, nhanVien.getTennhanvien());
            preparedStatement.setString(3, nhanVien.getNgaysinh());
             preparedStatement.setString(4, nhanVien.getGioitinh());
            preparedStatement.setString(5, nhanVien.getSdt());
             preparedStatement.setString(6, nhanVien.getDiachi());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateNhanVien(NhanVienDTO nhanVien) {
        String query = "UPDATE nhanvien SET nameNv = ?, dateOfBirthNv = ?, sexualNv = ?, phoneNumbNv = ?, addressNv = ?  WHERE idNv = ?";

        try (PreparedStatement preparedStatement = connectDB.getConnection().prepareStatement(query)) {
            
            preparedStatement.setString(1, nhanVien.getTennhanvien());
            preparedStatement.setString(2, nhanVien.getNgaysinh());
             preparedStatement.setString(3, nhanVien.getGioitinh());
            preparedStatement.setString(4, nhanVien.getSdt());
             preparedStatement.setString(5, nhanVien.getDiachi());
             preparedStatement.setInt(6, nhanVien.getManhanvien());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteNhanVien(int maNhanVien) {
        String query = "DELETE FROM nhanvien WHERE idNv = ?";

        try (PreparedStatement preparedStatement = connectDB.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, maNhanVien);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public NhanVienDTO getNhanVienById(int maNhanVien) {
        String query = "SELECT * FROM nhanvien WHERE idNv = ?";
        NhanVienDTO nhanVien = null;

        try (PreparedStatement preparedStatement = connectDB.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, maNhanVien);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    nhanVien = new NhanVienDTO();
                    nhanVien.setManhanvien(resultSet.getInt("idNv"));
                    nhanVien.setTennhanvien(resultSet.getString("nameNv"));
                    nhanVien.setNgaysinh(resultSet.getString("dateOfBirthNv"));
                    nhanVien.setGioitinh(resultSet.getString("sexualNv"));
                    nhanVien.setSdt("phoneNumbNv");
                    nhanVien.setDiachi("addressNv");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nhanVien;
    }
}

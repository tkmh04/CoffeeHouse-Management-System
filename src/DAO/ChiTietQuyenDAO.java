/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietQuyenDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class ChiTietQuyenDAO {
    private Connection conn;

    public ChiTietQuyenDAO() {
        conn = ConnectDataBaseDB.getConnection();
    }

    public boolean themChiTietQuyen(ChiTietQuyenDTO ctq) {
    try {
        String sql = "INSERT INTO chitietquyen (idQuyen, maCn, HanhDong) VALUES (?, ?, ?)";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setInt(1, ctq.getidQuyen());
        pre.setInt(2, ctq.getmaCn());
        pre.setString(3, ctq.gethangDong());

        int result = pre.executeUpdate();
        return result > 0;
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;
    }
}

    public boolean xoaChiTietQuyen(int idQuyen) {
        boolean rs = false;
        try {
            String sql = "DELETE FROM chitietquyen WHERE `idQuyen` = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, idQuyen);   
            rs = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return rs;
    }

    public ArrayList<ChiTietQuyenDTO> layChiTietQuyen(int idQuyen) {
    ArrayList<ChiTietQuyenDTO> chiTietQuyenList = new ArrayList<>();
    try {
        String sql = "SELECT idQuyen, maCn, HanhDong FROM chitietquyen WHERE idQuyen = ?";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setInt(1, idQuyen);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            ChiTietQuyenDTO chiTietQuyen = new ChiTietQuyenDTO();
            chiTietQuyen.setidQuyen(rs.getInt("idQuyen"));
            chiTietQuyen.setmaCn(rs.getInt("maCn"));
            chiTietQuyen.sethangDong(rs.getString("HanhDong"));
            chiTietQuyenList.add(chiTietQuyen);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return chiTietQuyenList;
}
    public ArrayList<String> layDanhSachHanhDong(int maCn, int idQuyen) {
        ArrayList<String> danhSachHanhDong = new ArrayList<>();
        String sql = "SELECT HanhDong FROM chitietquyen WHERE maCn = ? AND idQuyen = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, maCn);
            statement.setInt(2, idQuyen);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String hanhDong = resultSet.getString("HanhDong");
                danhSachHanhDong.add(hanhDong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachHanhDong;
    }

}

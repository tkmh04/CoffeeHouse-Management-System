/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import DAO.JDBCUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import DTO.NhaCungCapDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class NhaCungCapDAO implements interfaceDAO<NhaCungCapDTO>{
    public static NhaCungCapDAO getInstance(){
        return new NhaCungCapDAO();
    }

    @Override
    public int ADD(NhaCungCapDTO t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO `nhacungcap` (`idNcc`, `nameNcc`, `diachi`, `phoneNumbNcc`) VALUES (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getIdNcc());
            pst.setString(2, t.getTenNcc());
            pst.setString(3, t.getDiaChi());
            pst.setString(4, t.getSdtNcc());
            result = pst.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Không thêm được nhà cung cấp " + t.getIdNcc(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public int UPDATE(NhaCungCapDTO t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE `nhacungcap` SET `nameNcc`=?,`diachi`=?,`phoneNumbNcc`=? WHERE `idNcc`= ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTenNcc());
            pst.setString(2, t.getDiaChi());
            pst.setString(3, t.getSdtNcc());
            pst.setInt(4, t.getIdNcc());
            result = pst.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public int DELETE(NhaCungCapDTO t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM `nhacungcap` WHERE `idNcc`=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getIdNcc());
            result = pst.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public NhaCungCapDTO selectById(String t) {
        NhaCungCapDTO result = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM `nhacungcap` WHERE `idNcc`=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(t));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int idNcc = rs.getInt("idNcc");
                String tenNcc = rs.getString("nameNcc");
                String diachi = rs.getString("diachi");
                String sdtNcc = rs.getString("phoneNumbNcc");

                result = new NhaCungCapDTO(idNcc, tenNcc, diachi, sdtNcc);
            }

        } catch (SQLException ex) {
        }
        return result;
    }

    @Override
    public ArrayList<NhaCungCapDTO> selectAll() {
        ArrayList<NhaCungCapDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM nhacungcap ";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int idNcc = rs.getInt("idNcc");
                String tenNcc = rs.getString("nameNcc");
                String diaChi = rs.getString("diachi");
                String sdtNcc = rs.getString("phoneNumbNcc");
                NhaCungCapDTO ncc = new NhaCungCapDTO(idNcc, tenNcc, diaChi, sdtNcc);
                result.add(ncc);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<NhaCungCapDTO> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanliquantrasua' AND   TABLE_NAME   = 'nhacungcap'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (!rs2.isBeforeFirst() ) {
                System.out.println("No data");
            } else {
                while ( rs2.next() ) {
                    result = rs2.getInt("AUTO_INCREMENT");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public NhaCungCapDTO selectById(NhaCungCapDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author asus
 */
import DTO.NguyenlieuDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
public class NguyenlieuDAO implements interfaceDAO<NguyenlieuDTO> {

    @Override
    public int ADD(NguyenlieuDTO t) {
        int result = 0;
        try {
            Connection con = ConnectDataBaseDB.getConnection();
            String sql = "INSERT INTO nguyenlieu (nameNl, unit, quant) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTenNL());
            pst.setString(2, t.getDonvi());
            pst.setDouble(3, t.getSoluong());
            result = pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int UPDATE(NguyenlieuDTO t) {
         int result = 0;
        try {
            Connection con = ConnectDataBaseDB.getConnection();
            String sql = "INSERT INTO nguyenlieu (nameNl, unit, quant) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTenNL());
            pst.setString(2, t.getDonvi());
            pst.setDouble(3, t.getSoluong());
            result = pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int DELETE(NguyenlieuDTO t) {
        int result = 0;
        try {
            Connection con = ConnectDataBaseDB.getConnection();
            String sql = "DELETE FROM nguyenlieu WHERE idNl = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getIdNl());
            result = pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public void deleteById(int id) {
    try {
        Connection con = ConnectDataBaseDB.getConnection();
        String sql = "DELETE FROM nguyenlieu WHERE idNl = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, id);
        pst.executeUpdate();
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    public NguyenlieuDTO selectById(int id) {
         NguyenlieuDTO result = null;
        try 
        {
            Connection con = (Connection) ConnectDataBaseDB.getConnection();
            String sql = "SELECT * FROM topping WHERE idTopping=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next())
            {
                int MaNl =rs.getInt("idNL");
                String TenNl=rs.getString("nameNL");
                String unitNl=rs.getString("unit");
                double quantNl=rs.getDouble("quant");
                 result = new NguyenlieuDTO(MaNl,TenNl,unitNl,quantNl);
              
            }

            
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<NguyenlieuDTO> selectAll() {
        ArrayList<NguyenlieuDTO> result =new ArrayList<NguyenlieuDTO>();
        try 
        {
            Connection con = (Connection) ConnectDataBaseDB.getConnection();
            String sql = "SELECT * FROM nguyenlieu";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next())
            {
                int MaNl =rs.getInt("idNl");
                String TenNl=rs.getString("nameNl");
                String Donvi=rs.getString("unit");
                double Soluong=rs.getDouble("quant");
                NguyenlieuDTO nl = new NguyenlieuDTO(MaNl,TenNl,Donvi,Soluong);
                result.add(nl);
            }

            
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
   public void updateQuantity(int idSp, String size, int sl) {
    try {
        Connection con = (Connection) ConnectDataBaseDB.getConnection();
        String sql1 = "SELECT idNL, quant FROM congthuc WHERE IdSp = ? AND Size = ?";
        PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql1);
        pstmt.setInt(1, idSp);
        pstmt.setString(2, size);
        
        ResultSet rs = (ResultSet) pstmt.executeQuery();
        while (rs.next()) {
            int idNl = rs.getInt("idNL");
            double quantTity = rs.getDouble("quant");
            double adjustedQuantity = quantTity * sl; // Số lượng được điều chỉnh
            String sql2 = "UPDATE nguyenlieu SET quant = quant - ? WHERE idNl = ?";
            PreparedStatement pstmt2 = (PreparedStatement) con.prepareStatement(sql2);
            pstmt2.setDouble(1, adjustedQuantity);
            pstmt2.setInt(2, idNl);
            pstmt2.executeUpdate();
        }
        
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
 public void updateById(NguyenlieuDTO dto) {
    try {
        Connection con = ConnectDataBaseDB.getConnection();
        String sql = "UPDATE nguyenlieu SET nameNl = ?, unit = ?, quant = ? WHERE idNl = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, dto.getTenNL());
        pst.setString(2, dto.getDonvi());
        pst.setDouble(3, dto.getSoluong());
        pst.setInt(4, dto.getIdNl());
        pst.executeUpdate();
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
public boolean isDuplicateName(String nameNL) {
        try {
            Connection con = ConnectDataBaseDB.getConnection();
            String sql = "SELECT * FROM nguyenlieu WHERE nameNl = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, nameNL);
            ResultSet rs = pst.executeQuery();
            boolean isDuplicate = rs.next();
            con.close();
            return isDuplicate;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public ArrayList<NguyenlieuDTO> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public NguyenlieuDTO selectById(NguyenlieuDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}

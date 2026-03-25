/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NguyenlieuDTO;
import DTO.SanPhamDTO;
import DTO.ToppingDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class ToppingDAO implements interfaceDAO<ToppingDTO>{

    @Override
    public int ADD(ToppingDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int UPDATE(ToppingDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int DELETE(ToppingDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



    @Override
    public ArrayList<ToppingDTO> selectAll() {
       ArrayList<ToppingDTO> result =new ArrayList<ToppingDTO>();
        try 
        {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM topping";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next())
            {
                int MaTP =rs.getInt("idTopping");
                String TenTP=rs.getString("nameTopping");
                Double GiaTP =rs.getDouble("priceTopping");
                int Soluong = rs.getInt("quantTopping");
                
                
                ToppingDTO tp = new ToppingDTO(MaTP,TenTP,GiaTP,Soluong);
                result.add(tp);
            }

            
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return result;
    }

    @Override
    public ArrayList<ToppingDTO> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
//    public static void main(String[] args) {
//        ToppingDAO A = new ToppingDAO();
//        System.out.println(A.selectAll());
//    }
   
    public ToppingDTO selectById(String t) {
        ToppingDTO result = null;
        try 
        {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM topping WHERE idTopping=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next())
            {
                int MaTp =rs.getInt("idTopping");
                String TenTp=rs.getString("nameTopping");
                Double PriceTp=rs.getDouble("priceTopping");
                int QuantTp=rs.getInt("quantTopping");
                 result = new ToppingDTO(MaTp,TenTp,PriceTp,QuantTp);
              
            }

            
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ToppingDTO selectById(ToppingDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
    
}

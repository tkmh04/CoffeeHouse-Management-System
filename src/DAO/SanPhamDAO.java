/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.util.ArrayList;
import DTO.SanPhamDTO;
import DAO.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author asus
 */
public class SanPhamDAO implements interfaceDAO<SanPhamDTO> {
    public static SanPhamDAO getInstance()
    {
        return new SanPhamDAO();
    }
    @Override
    public int ADD(SanPhamDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int UPDATE(SanPhamDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int DELETE(SanPhamDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public SanPhamDTO selectById(String t) {
        SanPhamDTO result = null;
        try 
        {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM sanpham WHERE idSp=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next())
            {
                int MaSp =rs.getInt("idSp");
                String TenSp=rs.getString("nameSp");
                int LoaiSp=rs.getInt("idtypeSp");
                String HinhSp=rs.getString("imageSp");
                 result = new SanPhamDTO(MaSp,TenSp,LoaiSp,HinhSp);
              
            }

            
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<SanPhamDTO> selectAll() {
        ArrayList<SanPhamDTO> result =new ArrayList<SanPhamDTO>();
        try 
        {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM sanpham";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next())
            {
                int MaSp =rs.getInt("idSp");
                String TenSp=rs.getString("nameSp");
                int LoaiSp=rs.getInt("idtypeSp");
                String HinhSp=rs.getString("imageSp");
                SanPhamDTO sp = new SanPhamDTO(MaSp,TenSp,LoaiSp,HinhSp);
                result.add(sp);
            }

            
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    @Override
    public ArrayList<SanPhamDTO> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public static void main(String[] args)
{
   SanPhamDAO sp= new SanPhamDAO();
    System.out.println(sp.selectAll());
}

    @Override
    public SanPhamDTO selectById(SanPhamDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public double dbPriceSp(String idSp, String size) {
    double result = 0;
    try (Connection con = JDBCUtil.getConnection();
         PreparedStatement pst = con.prepareStatement("SELECT priceSp FROM giasanpham WHERE idSp = ? AND size = ?")) {
        pst.setString(1, idSp);
        pst.setString(2, size);
        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                result = rs.getDouble("priceSp");
            }
        }
    } catch (Exception e) {
        // Xử lý ngoại lệ một cách chính xác tại đây, ví dụ: thông báo lỗi cho người dùng hoặc ghi log
        e.printStackTrace();
    }
    return result;
}
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class KhachHangBUS {
    
    private KhachHangDAO khachHangDAO;
    
    public KhachHangBUS(){
        this.khachHangDAO= new KhachHangDAO();
    }
    public KhachHangBUS(KhachHangDAO khachHangDAO){
        this.khachHangDAO = khachHangDAO;
    }
    public ArrayList<KhachHangDTO> getAllKhachHang() {
        return khachHangDAO.selectAll();
    }
    public void addKhachHang (KhachHangDTO khachHang){
        khachHangDAO.ADD(khachHang);
    }
    public void upadateKhachHang(KhachHangDTO khachHang){
        khachHangDAO.updateKhachHang(khachHang);
    }
    public void deleteKhachHang(int maKhachHang ) {
        khachHangDAO.deleteKhachHang(maKhachHang);
    }
    public KhachHangDTO getKhachHangById(String maKhachHang){
        return khachHangDAO.getKhachHangById(maKhachHang);
    }
    
    
    
    
}

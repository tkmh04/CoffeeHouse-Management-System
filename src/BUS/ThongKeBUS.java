/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ThongKeDAO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ThongKeBUS {
    ThongKeDAO tkeDAO = new ThongKeDAO();
    public int tinhDoanhThu(int nam, int quy) {
        return tkeDAO.tinhDoanhThu(nam, quy);
    }
    public int tinhVon(int nam, int quy) {
        return tkeDAO.tinhVon(nam, quy);
    }
    public int tinhDoanhThuTuNgayDenNgay(int Ngay, int Thang, int Nam) {
        return tkeDAO.tinhDoanhThuTuNgayDenNgay(Ngay, Thang, Nam);
    }
    public int tinhVonTuNgayDenNgay(int Ngay, int Thang, int Nam) {
        return tkeDAO.tinhVonTuNgayDenNgay(Ngay, Thang, Nam);
    }
    public int tinhDoanhThuTuThangDenThang(int Thang, int Nam) {
        return tkeDAO.tinhDoanhThuTuThangDenThang(Thang, Nam);
    }
    public int tinhVonTuThangDenThang(int Thang, int Nam) {
        return tkeDAO.tinhVonTuThangDenThang(Thang, Nam);
    }
    public int tinhDoanhThuTuNamDenNam(int Nam) {
        return tkeDAO.tinhDoanhThuTuNamDenNam(Nam);
    }
    public int tinhVonTuNamDenNam(int Nam) {
        return tkeDAO.tinhVonTuNamDenNam(Nam);
    }
    public int demNhanVien(){
        return tkeDAO.demNhanVien();
    }
    public int demKhachHang(){
        return tkeDAO.demKhachHang();
    }
    public int demSP(){
        return tkeDAO.demSP();
    }
    public double demDT(){
        return tkeDAO.demDT();
    }
    public ArrayList<ArrayList<Object>> topSanPhamBanChay(int limit){
        return tkeDAO.topSanPhamBanChay(limit);
    }
    public ArrayList<String[]> tongSP(){
        return tkeDAO.tongSP();
    }
    public int demPN(){
        return tkeDAO.demPN();
    }
    public int demPX(){
        return tkeDAO.demPX();
    }
}

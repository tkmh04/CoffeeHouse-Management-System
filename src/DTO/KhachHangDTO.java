/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author PC
 */
public class KhachHangDTO {
    private int maKhachHang;
    private String tenKhachHang;
    private String sDT;
    private String diaChi;
    private String loaiThanhVien;
    public KhachHangDTO(int maKhachHang, String tenKhachHang, String sDT, String diaChi, String loaiThanhVien) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.sDT = sDT;
        this.diaChi = diaChi;
        this.loaiThanhVien = loaiThanhVien;
    }
   
    
    public KhachHangDTO(){
        
    }
    
    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getsDT() {
        return sDT;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getLoaiThanhVien() {
        return loaiThanhVien;
    }

    public void setLoaiThanhVien(String loaiThanhVien) {
        this.loaiThanhVien = loaiThanhVien;
    }
    
}
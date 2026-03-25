/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;

/**
 *
 * @author PC
 */public class NhanVienDTO {
    private int manhanvien;
    private String tennhanvien;
    private String ngaysinh;
    private String gioitinh;
    private String sdt;
    private String diachi;

    public NhanVienDTO(int manhanvien, String tennhanvien, String ngaysinh, String gioitinh, String sdt, String diachi) {
        this.manhanvien = manhanvien;
        this.tennhanvien = tennhanvien;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.sdt = sdt;
        this.diachi = diachi;
    }
    public NhanVienDTO(){
        
    }

    public int getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(int manhanvien) {
        this.manhanvien = manhanvien;
    }

    public String getTennhanvien() {
        return tennhanvien;
    }

    public void setTennhanvien(String tennhanvien) {
        this.tennhanvien = tennhanvien;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
    @Override
    public String toString() {
        return "NhanVienDTO{" + "manhanvien=" + manhanvien + ", tennhanvien=" + tennhanvien + ", ngaysinh=" + ngaysinh + ", gioitinh=" + gioitinh + ", sdt=" + sdt + ", diachi=" + diachi + '}';
    }

      
    
    
}

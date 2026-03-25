/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

public class TaiKhoanDTO {
    private int idTaiKhoan;
    private int idNhanVien;
    private String ten;
    private String password;
    private int idQuyen;
    private int status;
    public TaiKhoanDTO(){
        
    }
    public TaiKhoanDTO(int idTaiKhoan, int idNhanVien, String ten, String password, int idQuyen, int status){
        this.idTaiKhoan = idTaiKhoan;
        this.idNhanVien = idNhanVien;
        this.ten = ten;
        this.password = password;
        this.idQuyen = idQuyen;
        this.status = status;
    }

    public int getIdTaiKhoan() {
        return idTaiKhoan;
    }

    public void setIdTaiKhoan(int idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }

    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdQuyen() {
        return idQuyen;
    }

    public void setIdQuyen(int idQuyen) {
        this.idQuyen = idQuyen;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

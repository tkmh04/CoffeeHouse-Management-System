/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Admin
 */
public class NhanVienDTO {
    private int idNhanVien;
    private String nameNhanVien;
    private String dateOfBirth;
    private String sexual;
    private String phoneNumber;
    private String address;
    
    public NhanVienDTO(int idNhanVien, String nameNhanVien, String dateOfBirth, String sexual, String phoneNumber, String address) {
        this.idNhanVien = idNhanVien;
        this.nameNhanVien = nameNhanVien;
        this.dateOfBirth = dateOfBirth;
        this.sexual = sexual;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    
    public NhanVienDTO() {
    }
    
    // Getters and Setters
    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getNameNhanVien() {
        return nameNhanVien;
    }

    public void setNameNhanVien(String nameNhanVien) {
        this.nameNhanVien = nameNhanVien;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSexual() {
        return sexual;
    }

    public void setSexual(String sexual) {
        this.sexual = sexual;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;

/**
 *
 * @author asus
 */
public class NguyenlieuDTO {
    private int idNl;
    private String tenNL;
    private String donvi;
    private double soluong;
    public NguyenlieuDTO()
    {
        
    }
    public NguyenlieuDTO(int idNl ,String tenNL, String donvi, double soluong) {
        this.idNl = idNl;
        this.tenNL = tenNL;
        this.donvi = donvi;
        this.soluong = soluong;
    }

    public int getIdNl() {
        return idNl;
    }

    public void setIdNl(int idNl) {
        this.idNl = idNl;
    }

    public String getTenNL() {
        return tenNL;
    }

    public void setTenNL(String tenNL) {
        this.tenNL = tenNL;
    }

    public String getDonvi() {
        return donvi;
    }

    public void setDonvi(String donvi) {
        this.donvi = donvi;
    }

    public double getSoluong() {
        return soluong;
    }

    public void setSoluong(double soluong) {
        this.soluong = soluong;
    }

    @Override
    public String toString() {
        return "NguyenlieuDTO{" + "tenNL=" + tenNL + ", donvi=" + donvi + ", soluong=" + soluong + '}';
    }

    
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NguyenlieuDTO other = (NguyenlieuDTO) obj;
        return true;
    }

   
    
}

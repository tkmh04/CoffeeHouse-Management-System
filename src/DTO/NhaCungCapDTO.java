/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;

/**
 *
 * @author Admin
 */
public class NhaCungCapDTO {
    private int idNcc;
    private String tenNcc,sdtNcc,diaChi;
    public NhaCungCapDTO(int idNcc ,String tenNcc,String diaChi,String sdtNcc) {
        this.idNcc = idNcc;
        this.tenNcc = tenNcc;
        this.diaChi = diaChi;
        this.sdtNcc = sdtNcc;
    }

    public NhaCungCapDTO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getIdNcc() {
        return idNcc;
    }

    public void setIdNcc(int idNcc) {
        this.idNcc = idNcc;
    }

    public String getTenNcc() {
        return tenNcc;
    }

    public void setTenNcc(String tenNcc) {
        this.tenNcc = tenNcc;
    }
    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi=diaChi;
    }
    public String getSdtNcc() {
        return sdtNcc;
    }

    public void setSdtNcc(String sdtNcc) {
        this.sdtNcc = sdtNcc;
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.idNcc);
        hash = 29 * hash + Objects.hashCode(this.tenNcc);
        hash = 29 * hash + Objects.hashCode(this.diaChi);
        hash = 29 * hash + Objects.hashCode(this.sdtNcc);
        return hash;
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
        final NhaCungCapDTO other = (NhaCungCapDTO) obj;
        if (!Objects.equals(this.idNcc, other.idNcc)) {
            return false;
        }
        if (!Objects.equals(this.tenNcc, other.tenNcc)) {
            return false;
        }
        if (!Objects.equals(this.sdtNcc, other.sdtNcc)) {
            return false;
        }
        return Objects.equals(this.diaChi, other.diaChi);
    }
    @Override
    public String toString() {
        return "NhaCungCapDTO{" + "idNcc=" + idNcc + ",tenNcc=" + tenNcc +",Diachi=" + diaChi + ", sdtNcc=" + sdtNcc + '}';
    }
}

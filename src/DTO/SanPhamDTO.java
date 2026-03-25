/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import DAO.SanPhamDAO;
import javax.swing.ImageIcon;

/**
 *
 * @author asus
 */
public class SanPhamDTO {
    private int idSp;
    private String tenSp;
    private String sizeSp;
    private int idtypeSp;
    private String imgSp;
    private float priceSp;
    private final SanPhamDAO spDAO = new SanPhamDAO();

    public SanPhamDTO(int idSp, String tenSp,int idtypeSp,String imgSp ) {
        this.idSp = idSp;
        this.tenSp = tenSp;
        this.idtypeSp=idtypeSp;
        this.imgSp = imgSp;
      
    }
    public SanPhamDTO()
    {
        
    }

    public int getIdSp() {
        return idSp;
    }

    public void setIdSp(int idSp) {
        this.idSp = idSp;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public String getSizeSp() {
        return sizeSp;
    }

    public void setSizeSp(String sizeSp) {
        this.sizeSp = sizeSp;
    }

    public String getImgSp() {
        return imgSp;
    }

    public void setImgSp(String imgSp) {
        this.imgSp = imgSp;
    }

    public float getPriceSp() {
        return priceSp;
    }

    public void setPriceSp(float priceSp) {
        this.priceSp = priceSp;
    }
    
}

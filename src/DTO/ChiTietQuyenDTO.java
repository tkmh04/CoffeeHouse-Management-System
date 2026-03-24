/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Admin
 */
public class ChiTietQuyenDTO {
    private int idQuyen;
    private int maCn;
    private String HanhDong;
    
    public ChiTietQuyenDTO(){
        
    }
    
    public ChiTietQuyenDTO(int idQuyen, int maCn, String HangDong){
        this.idQuyen = idQuyen;
        this.maCn = maCn;
        this.HanhDong = HangDong;
    }
    public int getidQuyen(){
        return idQuyen;
}
    public void setidQuyen(int idQuyen){
        this.idQuyen = idQuyen;
    }
    public int getmaCn(){
        return maCn;
}
    public void setmaCn(int maCn){
        this.maCn = maCn;
    }
    public String gethangDong(){
        return HanhDong;
}
    public void sethangDong(String HangDong){
        this.HanhDong = HangDong;
    }
}

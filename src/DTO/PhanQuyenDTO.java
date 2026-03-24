/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

public class PhanQuyenDTO {
    private int idQuyen;
    private String tenQuyen;

    public PhanQuyenDTO(){
        
    }
    public PhanQuyenDTO(int idQuyen, String tenQuyen){
        this.idQuyen = idQuyen;
        this.tenQuyen = tenQuyen;
    }

    public int getIdQuyen() {
        return idQuyen;
    }

    public void setIdQuyen(int idQuyen) {
        this.idQuyen = idQuyen;
    }

    public String getTen() {
        return tenQuyen;
    }

    public void settenQuyen(String tenQuyen) {
        this.tenQuyen = tenQuyen;
    }

}
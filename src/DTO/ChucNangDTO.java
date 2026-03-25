/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Admin
 */
public class ChucNangDTO {
    private int maCn;
    private String tenCn;
    public ChucNangDTO(int maCn, String tenCn){
        this.maCn = maCn;
        this.tenCn = tenCn;
    }
    public void setmaCn(int maCn) {
        this.maCn = maCn;
    }

    public int getmaCn() {
        return maCn;
    }
    public void settenCn(String tenCn) {
        this.tenCn = tenCn;
    }

    public String gettenCn() {
        return tenCn;
    }
}

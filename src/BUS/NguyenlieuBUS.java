/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NguyenlieuDAO;
import DTO.NguyenlieuDTO;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class NguyenlieuBUS {
     public final NguyenlieuDAO nlDAO = new NguyenlieuDAO();
    public ArrayList<NguyenlieuDTO> listNL = new ArrayList<>();

    public NguyenlieuBUS() {
        listNL=nlDAO.selectAll();
    }
    public ArrayList<NguyenlieuDTO> getAll() {
               

        return this.listNL;
    }
    public int getIdNl(int index) {
        return this.listNL.get(index).getIdNl();
    }
    public String getName(int index)
    {
        return this.listNL.get(index).getTenNL();
    }
    public String getDonvi(int index)
    {
        return this.listNL.get(index).getDonvi();
    }
    public double getSoluong(int index)
    {
        return this.listNL.get(index).getSoluong();
    }
    public void updateQuantity(int idSp, String size, int sl) {
        nlDAO.updateQuantity(idSp, size, sl);
    }
    public void updateById(NguyenlieuDTO dto)
    {
        nlDAO.updateById(dto);
    }
    
}

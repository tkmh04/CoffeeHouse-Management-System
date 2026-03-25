/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ToppingDAO;
import DTO.ToppingDTO;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class ToppingBUS {
    public final ToppingDAO tpDAO = new ToppingDAO();
    public ArrayList<ToppingDTO> listTP = new ArrayList<>();
    public ToppingBUS()
    {
        listTP = tpDAO.selectAll();
    }

    public ArrayList<ToppingDTO> getAll() {
        return this.listTP;
    }

    public int getIdTopping(int index)
    {
        return this.listTP.get(index).getIdTopping();
    }
    public String getNameTopping(int index)
    {
        return this.listTP.get(index).getNameTopping();
    }
    public double getPriceTopping(int index)
    {
        return this.listTP.get(index).getPriceTopping();
    }
     public ToppingDTO selectedById(String id) {
        try {
           
            return tpDAO.selectById(id);
        } catch (Exception ex) {
       
            ex.printStackTrace();
            return null;
        }
    }
    
}

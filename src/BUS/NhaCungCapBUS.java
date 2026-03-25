/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class NhaCungCapBUS {
    private final NhaCungCapDAO NccDAO = new NhaCungCapDAO();
    private ArrayList<NhaCungCapDTO> listNcc = new ArrayList<>();
    public static NhaCungCapBUS getInstance(){
        return new NhaCungCapBUS();
    }
    public NhaCungCapBUS() {
        this.listNcc = NccDAO.selectAll();
    }
    public ArrayList<NhaCungCapDTO> getAll() {
        return this.listNcc;
    }
    public NhaCungCapDTO getByIndex(int index) {
        return this.listNcc.get(index);
    }
    public int getIndexByMaNCC(int idncc) {
        int i = 0;
        int vitri = -1;
        while (i < this.listNcc.size() && vitri == -1) {
            if (listNcc.get(i).getIdNcc() == idncc) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }
    public String getTenNcc(int idncc) {
        return this.listNcc.get(getIndexByMaNCC(idncc)).getTenNcc();
    }
    public int getIndexByIdNCC(int idNcc) {
        int i = 0;
        int vitri = -1;
        while (i < this.listNcc.size() && vitri == -1) {
            if (listNcc.get(i).getIdNcc() == idNcc) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }
    public boolean add(NhaCungCapDTO ncc) {
        boolean check = NccDAO.ADD(ncc) != 0;
        if (check) {
            this.listNcc.add(ncc);
        }
        return check;
    }
    public boolean update(NhaCungCapDTO ncc) {
        boolean check = NccDAO.UPDATE(ncc) != 0;
        if (check) {
            this.listNcc.set(getIndexByIdNCC(ncc.getIdNcc()), ncc);
        }
        return check;
    }

    public ArrayList<NhaCungCapDTO> searchTatCa(String text){
        ArrayList<NhaCungCapDTO> result = new ArrayList<>();
        ArrayList<NhaCungCapDTO> armt = NhaCungCapDAO.getInstance().selectAll();
        for (var ncc : armt) {
            if (Integer.toString(ncc.getIdNcc()).toLowerCase().contains(text.toLowerCase())
                    || ncc.getTenNcc().toLowerCase().contains(text.toLowerCase())
                    || ncc.getSdtNcc().toLowerCase().contains(text.toLowerCase())
                    || ncc.getDiaChi().toLowerCase().contains(text.toLowerCase())) {
                result.add(ncc);
            }
        }
        return result;
    }
    public ArrayList<NhaCungCapDTO> searchTenNCC(String text) {
        ArrayList<NhaCungCapDTO> result = new ArrayList<>();
        ArrayList<NhaCungCapDTO> armt = NhaCungCapDAO.getInstance().selectAll();
        for (var ncc : armt) {
            if (ncc.getTenNcc().toLowerCase().contains(text.toLowerCase())) {
                result.add(ncc);
            }
        }
        return result;
    }
    public ArrayList<NhaCungCapDTO> searchIdNCC(String text) {
        ArrayList<NhaCungCapDTO> result = new ArrayList<>();
        ArrayList<NhaCungCapDTO> armt = NhaCungCapDAO.getInstance().selectAll();
        for (var ncc : armt) {
            if (Integer.toString(ncc.getIdNcc()).toLowerCase().contains(text.toLowerCase())) {
                result.add(ncc);
            }
        }
        return result;
    }
    public ArrayList<NhaCungCapDTO> searchDiaChi(String text) {
        ArrayList<NhaCungCapDTO> result = new ArrayList<>();
        ArrayList<NhaCungCapDTO> armt = NhaCungCapDAO.getInstance().selectAll();
        for (var ncc : armt) {
            if (ncc.getDiaChi().toLowerCase().contains(text.toLowerCase())) {
                result.add(ncc);
            }
        }
        return result;
    }

    public ArrayList<NhaCungCapDTO> searchSdt(String text) {
        ArrayList<NhaCungCapDTO> result = new ArrayList<>();
        ArrayList<NhaCungCapDTO> armt = NhaCungCapDAO.getInstance().selectAll();
        for (var ncc : armt) {
            if (ncc.getSdtNcc().toLowerCase().contains(text.toLowerCase())) {
                result.add(ncc);
            }
        }
        return result;
    } 
    public String[] getArrTenNhaCungCap() {
        int size = listNcc.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = listNcc.get(i).getTenNcc();
        }
        return result;
    }
}

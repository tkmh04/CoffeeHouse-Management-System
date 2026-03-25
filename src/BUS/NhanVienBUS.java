/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

/**
 *
 * @author PC
 */

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import java.util.List;

public class NhanVienBUS {

    private NhanVienDAO nhanVienDAO;
   
    public NhanVienBUS() {
        this.nhanVienDAO = new NhanVienDAO();
    }
    public NhanVienBUS(NhanVienDAO nhanVienDAO) {
        this.nhanVienDAO = nhanVienDAO;
    }

    public List<NhanVienDTO> getAllNhanVien() {
        return nhanVienDAO.getAllNhanVien();
    }

    public void addNhanVien(NhanVienDTO nhanVien) {
        nhanVienDAO.addNhanVien(nhanVien);
    }

    public void updateNhanVien(NhanVienDTO nhanVien) {
        nhanVienDAO.updateNhanVien(nhanVien);
    }

    public void deleteNhanVien(int maNhanVien) {
        nhanVienDAO.deleteNhanVien(maNhanVien);
    }

    public NhanVienDTO getNhanVienById(int maNhanVien) {
        return nhanVienDAO.getNhanVienById(maNhanVien);
    }
}

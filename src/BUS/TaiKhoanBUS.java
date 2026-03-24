/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TaiKhoanBUS {
    TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
    private ArrayList<TaiKhoanDTO> listNhanVien = null;
    public TaiKhoanBUS() {
        docDanhSach();
    }
    public void docDanhSach() {
        this.listNhanVien = taiKhoanDAO.layDanhSachTaiKhoan();
    }
    public boolean themTaiKhoan(int idtk, int idnv, String ten, String password, int idquyen, int status) {
    if (idtk == 0 || idnv == 0 || ten.isEmpty() || password.isEmpty() || idquyen == 0 || status == 0) {
        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        return false;
    }

    TaiKhoanDTO tk = new TaiKhoanDTO();
    tk.setIdTaiKhoan(idtk);
    tk.setIdNhanVien(idnv);
    tk.setTen(ten);
    tk.setPassword(password);
    tk.setIdQuyen(idquyen);
    tk.setStatus(status);

    // Call DAO method to add the account
    boolean added = taiKhoanDAO.themTaiKhoan(tk);
    if (added) {
        // Reload data after adding
        docDanhSach();
    }

    return added;  // Return the result of adding
}

    

    public boolean xoaTaiKhoan(int idTaiKhoan) {
        return taiKhoanDAO.xoaTaiKhoan(idTaiKhoan);
    }

    public boolean capNhatTaiKhoan(int idtk, int idnv, String ten, String password, int idquyen, int status) {
    if (idtk == 0 || idnv == 0 || ten.isEmpty() || password.isEmpty() || idquyen == 0 || status == 0) {
        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        return false;
    }

    TaiKhoanDTO tk = new TaiKhoanDTO();
    tk.setIdTaiKhoan(idtk);
    tk.setIdNhanVien(idnv);
    tk.setTen(ten);
    tk.setPassword(password);
    tk.setIdQuyen(idquyen);
    tk.setStatus(status);

    // Call DAO method to update the account
    boolean updated = taiKhoanDAO.capNhatTaiKhoan(tk);
    if (updated) {
        // Reload data after updating
        docDanhSach();
        JOptionPane.showMessageDialog(null, "Cập nhật tài khoản thành công");
    } else {
        JOptionPane.showMessageDialog(null, "Cập nhật tài khoản thất bại");
    }

    return updated;  // Return the result of the update operation
}

    public ArrayList<TaiKhoanDTO> layDanhSachTaiKhoan() {
        return taiKhoanDAO.layDanhSachTaiKhoan();
    }
    
    public ArrayList<TaiKhoanDTO> timKiemTaiKhoan(String columnName, String value) {
    ArrayList<TaiKhoanDTO> ketQua = new ArrayList<>();
    ArrayList<TaiKhoanDTO> dsTaiKhoan = layDanhSachTaiKhoan();

    for (TaiKhoanDTO tk : dsTaiKhoan) {
    switch (columnName) {
        case "Tất cả":
            if (String.valueOf(tk.getIdTaiKhoan()).toLowerCase().contains(value.toLowerCase())
                || String.valueOf(tk.getIdNhanVien()).toLowerCase().contains(value.toLowerCase())
                || tk.getTen().toLowerCase().contains(value.toLowerCase())
                || tk.getPassword().toLowerCase().contains(value.toLowerCase())
                || String.valueOf(tk.getIdQuyen()).toLowerCase().contains(value.toLowerCase())
                || String.valueOf(tk.getStatus()).toLowerCase().contains(value.toLowerCase())) {
            ketQua.add(tk);
        }
        break;
        case "idTaiKhoan":
                if (String.valueOf(tk.getIdTaiKhoan()).toLowerCase().contains(value.toLowerCase())) {
                    ketQua.add(tk);
                }
                break;
            case "idNhanVien":
                if (String.valueOf(tk.getIdNhanVien()).toLowerCase().contains(value.toLowerCase())) {
                    ketQua.add(tk);
                }
                break;
            case "idQuyen":
                if (String.valueOf(tk.getIdQuyen()).toLowerCase().contains(value.toLowerCase())) {
                    ketQua.add(tk);
                }
                
                break;
            case "Tên tài khoản":
                if (tk.getTen().toLowerCase().contains(value.toLowerCase())) {
                    ketQua.add(tk);
                }
                break;
    }
}

    return ketQua;
}

    public boolean checkLogin(String username, String password) {
        return taiKhoanDAO.checkLogin(username, password);
    }
    public boolean checkUser(String username) {
        return taiKhoanDAO.checkUser(username);
    }
    public boolean checkPassword(String password) {
            return taiKhoanDAO.checkPassword(password);
    }
    public TaiKhoanDTO getTaiKhoan(String username, String password) {
        try {
            return taiKhoanDAO.getTaiKhoan(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Xử lý ngoại lệ ở đây
        }
    }
    public ArrayList<String> danhSachChucNang(int idQuyen) {
        return taiKhoanDAO.danhSachChucNang(idQuyen);
    }
    
}

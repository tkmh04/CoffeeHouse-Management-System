package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class NhanVienBUS {
    NhanVienDAO nhanVienDAO = new NhanVienDAO();
    private ArrayList<NhanVienDTO> listNhanVien = null;

    public NhanVienBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listNhanVien = nhanVienDAO.layDanhSachNhanVien();
    }

    public boolean themNhanVien(String nameNhanVien, String dateOfBirth, String sexual, String phoneNumber, String address) {
        if (nameNhanVien.isEmpty() || dateOfBirth.isEmpty() || phoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
            return false;
        }

        // Check xem số điện thoại đã tồn tại chưa
        if (nhanVienDAO.checkPhoneNumber(phoneNumber)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại đã tồn tại!");
            return false;
        }

        NhanVienDTO nv = new NhanVienDTO();
        nv.setNameNhanVien(nameNhanVien);
        nv.setDateOfBirth(dateOfBirth);
        nv.setSexual(sexual);
        nv.setPhoneNumber(phoneNumber);
        nv.setAddress(address);

        // Call DAO method to add the employee
        boolean added = nhanVienDAO.themNhanVien(nv);
        if (added) {
            // Reload data after adding
            docDanhSach();
            JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại");
        }

        return added;
    }

    public boolean xoaNhanVien(int idNhanVien) {
        boolean deleted = nhanVienDAO.xoaNhanVien(idNhanVien);
        if (deleted) {
            docDanhSach();
            JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Xóa nhân viên thất bại");
        }
        return deleted;
    }

    public boolean suaNhanVien(int idNhanVien, String nameNhanVien, String dateOfBirth, String sexual, String phoneNumber, String address) {
        if (nameNhanVien.isEmpty() || dateOfBirth.isEmpty() || phoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
            return false;
        }

        NhanVienDTO nv = new NhanVienDTO();
        nv.setIdNhanVien(idNhanVien);
        nv.setNameNhanVien(nameNhanVien);
        nv.setDateOfBirth(dateOfBirth);
        nv.setSexual(sexual);
        nv.setPhoneNumber(phoneNumber);
        nv.setAddress(address);

        // Call DAO method to update the employee
        boolean updated = nhanVienDAO.suaNhanVien(nv);
        if (updated) {
            // Reload data after updating
            docDanhSach();
            JOptionPane.showMessageDialog(null, "Cập nhật nhân viên thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật nhân viên thất bại");
        }

        return updated;
    }

    public ArrayList<NhanVienDTO> layDanhSachNhanVien() {
        return nhanVienDAO.layDanhSachNhanVien();
    }

    public NhanVienDTO getNhanVienByID(int idNhanVien) {
        return nhanVienDAO.getNhanVienByID(idNhanVien);
    }

    public ArrayList<NhanVienDTO> timKiemNhanVien(String columnName, String value) {
        ArrayList<NhanVienDTO> ketQua = new ArrayList<>();
        ArrayList<NhanVienDTO> dsNhanVien = layDanhSachNhanVien();

        if (dsNhanVien == null) {
            return ketQua;
        }

        for (NhanVienDTO nv : dsNhanVien) {
            switch (columnName) {
                case "Tất cả":
                    if (String.valueOf(nv.getIdNhanVien()).toLowerCase().contains(value.toLowerCase())
                            || nv.getNameNhanVien().toLowerCase().contains(value.toLowerCase())
                            || nv.getDateOfBirth().toLowerCase().contains(value.toLowerCase())
                            || nv.getSexual().toLowerCase().contains(value.toLowerCase())
                            || nv.getPhoneNumber().toLowerCase().contains(value.toLowerCase())
                            || nv.getAddress().toLowerCase().contains(value.toLowerCase())) {
                        ketQua.add(nv);
                    }
                    break;
                case "Mã NV":
                    if (String.valueOf(nv.getIdNhanVien()).toLowerCase().contains(value.toLowerCase())) {
                        ketQua.add(nv);
                    }
                    break;
                case "Tên NV":
                    if (nv.getNameNhanVien().toLowerCase().contains(value.toLowerCase())) {
                        ketQua.add(nv);
                    }
                    break;
                case "Ngày sinh":
                    if (nv.getDateOfBirth().toLowerCase().contains(value.toLowerCase())) {
                        ketQua.add(nv);
                    }
                    break;
                case "Giới tính":
                    if (nv.getSexual().toLowerCase().contains(value.toLowerCase())) {
                        ketQua.add(nv);
                    }
                    break;
                case "Số ĐT":
                    if (nv.getPhoneNumber().toLowerCase().contains(value.toLowerCase())) {
                        ketQua.add(nv);
                    }
                    break;
                case "Địa chỉ":
                    if (nv.getAddress().toLowerCase().contains(value.toLowerCase())) {
                        ketQua.add(nv);
                    }
                    break;
            }
        }

        return ketQua;
    }

    public boolean checkPhoneNumber(String phoneNumber) {
        return nhanVienDAO.checkPhoneNumber(phoneNumber);
    }

    public boolean checkNameNhanVien(String nameNhanVien) {
        return nhanVienDAO.checkNameNhanVien(nameNhanVien);
    }

    public NhanVienDTO getNhanVienByPhoneNumber(String phoneNumber) {
        return nhanVienDAO.getNhanVienByPhoneNumber(phoneNumber);
    }
}

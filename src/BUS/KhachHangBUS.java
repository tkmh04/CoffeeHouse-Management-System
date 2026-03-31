package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class KhachHangBUS {
    KhachHangDAO khachHangDAO = new KhachHangDAO();
    private ArrayList<KhachHangDTO> listKhachHang = null;

    public KhachHangBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listKhachHang = khachHangDAO.layDanhSachKhachHang();
    }

    public boolean themKhachHang(String tenKhachHang, String sdt, String diaChi, String loaiThanhVien) {
        if (tenKhachHang.isEmpty() || sdt.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
            return false;
        }

        // Check xem số điện thoại đã tồn tại chưa
        if (khachHangDAO.checkSDT(sdt)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại đã tồn tại!");
            return false;
        }

        KhachHangDTO kh = new KhachHangDTO();
        kh.setTenKhachHang(tenKhachHang);
        kh.setsDT(sdt);
        kh.setDiaChi(diaChi);
        kh.setLoaiThanhVien(loaiThanhVien);

        // Call DAO method to add the customer
        boolean added = khachHangDAO.themKhachHang(kh);
        if (added) {
            // Reload data after adding
            docDanhSach();
            JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại");
        }

        return added;
    }

    public boolean xoaKhachHang(int maKhachHang) {
        boolean deleted = khachHangDAO.xoaKhachHang(maKhachHang);
        if (deleted) {
            docDanhSach();
            JOptionPane.showMessageDialog(null, "Xóa khách hàng thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Xóa khách hàng thất bại");
        }
        return deleted;
    }

    public boolean suaKhachHang(int maKhachHang, String tenKhachHang, String sdt, String diaChi, String loaiThanhVien) {
        if (tenKhachHang.isEmpty() || sdt.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
            return false;
        }

        KhachHangDTO kh = new KhachHangDTO();
        kh.setMaKhachHang(maKhachHang);
        kh.setTenKhachHang(tenKhachHang);
        kh.setsDT(sdt);
        kh.setDiaChi(diaChi);
        kh.setLoaiThanhVien(loaiThanhVien);

        // Call DAO method to update the customer
        boolean updated = khachHangDAO.suaKhachHang(kh);
        if (updated) {
            // Reload data after updating
            docDanhSach();
            JOptionPane.showMessageDialog(null, "Cập nhật khách hàng thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật khách hàng thất bại");
        }

        return updated;
    }

    public ArrayList<KhachHangDTO> layDanhSachKhachHang() {
        return khachHangDAO.layDanhSachKhachHang();
    }

    public KhachHangDTO getKhachHangByID(int maKhachHang) {
        return khachHangDAO.getKhachHangByID(maKhachHang);
    }

    public ArrayList<KhachHangDTO> timKiemKhachHang(String columnName, String value) {
        ArrayList<KhachHangDTO> ketQua = new ArrayList<>();
        ArrayList<KhachHangDTO> dsKhachHang = layDanhSachKhachHang();

        if (dsKhachHang == null) {
            return ketQua;
        }

        for (KhachHangDTO kh : dsKhachHang) {
            switch (columnName) {
                case "Tất cả":
                    if (String.valueOf(kh.getMaKhachHang()).toLowerCase().contains(value.toLowerCase())
                            || kh.getTenKhachHang().toLowerCase().contains(value.toLowerCase())
                            || kh.getsDT().toLowerCase().contains(value.toLowerCase())
                            || kh.getDiaChi().toLowerCase().contains(value.toLowerCase())
                            || kh.getLoaiThanhVien().toLowerCase().contains(value.toLowerCase())) {
                        ketQua.add(kh);
                    }
                    break;
                case "Mã KH":
                    if (String.valueOf(kh.getMaKhachHang()).toLowerCase().contains(value.toLowerCase())) {
                        ketQua.add(kh);
                    }
                    break;
                case "Tên KH":
                    if (kh.getTenKhachHang().toLowerCase().contains(value.toLowerCase())) {
                        ketQua.add(kh);
                    }
                    break;
                case "Số ĐT":
                    if (kh.getsDT().toLowerCase().contains(value.toLowerCase())) {
                        ketQua.add(kh);
                    }
                    break;
                case "Địa chỉ":
                    if (kh.getDiaChi().toLowerCase().contains(value.toLowerCase())) {
                        ketQua.add(kh);
                    }
                    break;
                case "Loại thành viên":
                    if (kh.getLoaiThanhVien().toLowerCase().contains(value.toLowerCase())) {
                        ketQua.add(kh);
                    }
                    break;
            }
        }

        return ketQua;
    }

    public boolean checkSDT(String sdt) {
        return khachHangDAO.checkSDT(sdt);
    }

    public boolean checkTenKhachHang(String tenKhachHang) {
        return khachHangDAO.checkTenKhachHang(tenKhachHang);
    }

    public KhachHangDTO getKhachHangBySDT(String sdt) {
        return khachHangDAO.getKhachHangBySDT(sdt);
    }

    public KhachHangDTO getKhachHangBySDTFlexible(String sdt) {
        String normalizedInput = normalizePhone(sdt);
        if (normalizedInput.isEmpty()) {
            return null;
        }

        ArrayList<KhachHangDTO> dsKhachHang = layDanhSachKhachHang();
        if (dsKhachHang == null) {
            return null;
        }

        String alt = normalizedInput;
        if (normalizedInput.startsWith("0") && normalizedInput.length() > 1) {
            alt = "84" + normalizedInput.substring(1);
        } else if (normalizedInput.startsWith("84") && normalizedInput.length() > 2) {
            alt = "0" + normalizedInput.substring(2);
        }

        for (KhachHangDTO kh : dsKhachHang) {
            String sdtDb = normalizePhone(kh.getsDT());
            if (sdtDb.equals(normalizedInput) || sdtDb.equals(alt)) {
                return kh;
            }
        }
        return null;
    }

    public KhachHangDTO timKhachHangTheoSDTNhapVao(String sdt) {
        return khachHangDAO.timKhachHangTheoSDTNhapVao(sdt);
    }

    private String normalizePhone(String phone) {
        if (phone == null) {
            return "";
        }
        StringBuilder digits = new StringBuilder();
        for (char c : phone.trim().toCharArray()) {
            if (Character.isDigit(c)) {
                digits.append(c);
            }
        }
        String normalized = digits.toString();
        if (normalized.startsWith("84") && normalized.length() > 9) {
            normalized = "0" + normalized.substring(2);
        }
        return normalized;
    }
}
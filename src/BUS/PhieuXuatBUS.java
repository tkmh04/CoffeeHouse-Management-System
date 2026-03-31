package BUS;

import DTO.ChiTietHoaDonDTO;
import DAO.PhieuXuatDAO;
import DTO.HoaDonDTO;
import java.util.ArrayList;

public class PhieuXuatBUS {
    private final PhieuXuatDAO phieuXuatDAO = new PhieuXuatDAO();

    public int taoHoaDon(Integer idNv, Integer idKhMember, double total) {
        return phieuXuatDAO.themHoaDon(idNv, idKhMember, total);
    }

    public ArrayList<HoaDonDTO> layDanhSachHoaDon() {
        return phieuXuatDAO.layDanhSachHoaDon();
    }

    public boolean xoaHoaDon(int idHd) {
        return phieuXuatDAO.xoaHoaDon(idHd);
    }

    public String getLastError() {
        return phieuXuatDAO.getLastError();
    }

    public boolean themChiTietHoaDon(int idHd, int idSp, Integer idTopping, double quantity, double price) {
        return phieuXuatDAO.themChiTietHoaDon(idHd, idSp, idTopping, quantity, price);
    }

    public ArrayList<ChiTietHoaDonDTO> layChiTietHoaDon(int idHd) {
        return phieuXuatDAO.layChiTietHoaDon(idHd);
    }

    public boolean themChiTietHoaDonDayDu(int idHd, int idSp, String productName, String sizeSp, String toppingsText, double quantity, double lineTotal) {
        return phieuXuatDAO.themChiTietHoaDonDayDu(idHd, idSp, productName, sizeSp, toppingsText, quantity, lineTotal);
    }
}

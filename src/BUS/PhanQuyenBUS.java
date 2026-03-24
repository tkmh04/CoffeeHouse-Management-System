/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;



import DAO.ChiTietQuyenDAO;
import DAO.PhanQuyenDAO;
import DTO.PhanQuyenDTO;
import DTO.ChiTietQuyenDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PhanQuyenBUS {
    PhanQuyenDAO phanQuyenDAO = new PhanQuyenDAO();
    ChiTietQuyenDAO chitietquyenDAO = new ChiTietQuyenDAO();
    private ArrayList<PhanQuyenDTO> listQuyen = null;
    private ArrayList<ChiTietQuyenDTO> listctquyen = null;
    public PhanQuyenBUS() {
        docDanhSach();
    }
    public void docDanhSach() {
        this.listQuyen = phanQuyenDAO.layDanhSachQuyen();
        
    }
    public boolean themPhanQuyen(int idQuyen, String tenQuyen) {
    if (idQuyen == 0 || tenQuyen.isEmpty() ) {
        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        return false;
    }
    

    PhanQuyenDTO pq = new PhanQuyenDTO();
    pq.setIdQuyen(idQuyen);
    pq.settenQuyen(tenQuyen);

    // Call DAO method to add the account
    boolean added = phanQuyenDAO.themPhanQuyen(pq);
    if (added) {
        // Reload data after adding
        docDanhSach();
    }

    return added;  // Return the result of adding
}
    public boolean themChiTietQuyen(int idQuyen, int maCn, String HanhDong) {
    // Kiểm tra tính hợp lệ của dữ liệu đầu vào
    ChiTietQuyenDTO ctq = new ChiTietQuyenDTO();
    ctq.setidQuyen(idQuyen);
    ctq.setmaCn(maCn);
    ctq.sethangDong(HanhDong);

    // Gọi phương thức của DAO để thêm dữ liệu vào cơ sở dữ liệu
    boolean added = chitietquyenDAO.themChiTietQuyen(ctq);

    if (added) {
        // Nạp lại danh sách sau khi thêm
        docDanhSach();
    }

    return added; // Trả về kết quả của phương thức thêm
}

    public boolean xoaChiTietPhanQuyen(int idPhanQuyen) {
    boolean xoaChiTietQuyenThanhCong = chitietquyenDAO.xoaChiTietQuyen(idPhanQuyen);
    
    return xoaChiTietQuyenThanhCong;
}

    public boolean xoaPhanQuyen(int idPhanQuyen) {
    boolean xoaPhanQuyenThanhCong = phanQuyenDAO.xoaPhanQuyen(idPhanQuyen);
    boolean xoaChiTietQuyenThanhCong = chitietquyenDAO.xoaChiTietQuyen(idPhanQuyen);
    
    return xoaPhanQuyenThanhCong && xoaChiTietQuyenThanhCong;
}


    public boolean capNhatPhanQuyen(int idQuyen, String tenQuyen) {
    if (idQuyen == 0 || tenQuyen.isEmpty() ) {
        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        return false;
    }

    PhanQuyenDTO pq = new PhanQuyenDTO();
    pq.setIdQuyen(idQuyen);
    pq.settenQuyen(tenQuyen);


    // Call DAO method to update the account
    boolean updated = phanQuyenDAO.capNhatPhanQuyen(pq);
    if (updated) {
        // Reload data after updating
        docDanhSach();
        JOptionPane.showMessageDialog(null, "Cập nhật mã Quyền và tên Quyền thành công");
    } else {
        JOptionPane.showMessageDialog(null, "Cập nhật mã Quyền và tên Quyền thất bại");
    }

    return updated;  // Return the result of the update operation
}
    

    public ArrayList<PhanQuyenDTO> layDanhSachQuyen() {
        return phanQuyenDAO.layDanhSachQuyen();
    }
    

    public ArrayList<PhanQuyenDTO> timKiemTatCa(String keyword) {
    ArrayList<PhanQuyenDTO> ketQua = new ArrayList<>();
    ArrayList<PhanQuyenDTO> dsPhanQuyen = layDanhSachQuyen();

    for (PhanQuyenDTO phanQuyen : dsPhanQuyen) {
        // Thực hiện tìm kiếm trong tất cả các trường dữ liệu
        if (String.valueOf(phanQuyen.getIdQuyen()).toLowerCase().contains(keyword.toLowerCase())
                || String.valueOf(phanQuyen.getTen()).toLowerCase().contains(keyword.toLowerCase())
            )   
            ketQua.add(phanQuyen);
        }
   

    return ketQua;
}

   public ArrayList<ChiTietQuyenDTO> laydanhsachchitietquyen(int idQuyen) {
    return chitietquyenDAO.layChiTietQuyen(idQuyen);
}
   
   public boolean kiemTraHanhDongTonTai(String hanhDong, int maCn, int idQuyen) {
    // Lấy danh sách hành động từ cơ sở dữ liệu hoặc từ đâu đó
    ArrayList<String> danhSachHanhDong = chitietquyenDAO.layDanhSachHanhDong(maCn, idQuyen);
    // Kiểm tra xem hành động có tồn tại trong danh sách hay không
    if (!danhSachHanhDong.contains(hanhDong)) {
        return false;
    }
    else 
        return true;   
   }
   
   public ArrayList<Integer> layIdQuyen() {
    // Khởi tạo danh sách để lưu trữ idQuyen
    ArrayList<Integer> danhSachIdQuyen = new ArrayList<>();
    // Gọi phương thức từ DAO để lấy danh sách idQuyen
    danhSachIdQuyen = phanQuyenDAO.layIdQuyen();
    // Trả về danh sách idQuyen
    return danhSachIdQuyen;
}

   public PhanQuyenDTO getTenTKPQ(int idQuyen) {
        try {
            return phanQuyenDAO.getTenTK(idQuyen);
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Xử lý ngoại lệ ở đây
        }
    }
}



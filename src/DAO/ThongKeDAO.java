/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ThongKeDAO {
    private static Connection conn;

    public ThongKeDAO() {
        conn = ConnectDataBaseDB.getConnection();
    }

    public int tinhDoanhThu(int nam, int quy) {
        String sql = "SELECT SUM(total) FROM hoadon WHERE YEAR(timeHd) = ? AND QUARTER(timeHd) = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, nam);
            statement.setInt(2, quy);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Phương thức tính vốn cho mỗi quý
    public int tinhVon(int nam, int quy) {
        String sql = "SELECT SUM(Total) FROM phieunhap WHERE YEAR(timeNhap) = ? AND QUARTER(timeNhap) = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, nam);
            statement.setInt(2, quy);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    // Phương thức tính doanh thu từ ngày đến ngày bất kỳ
// Phương thức tính doanh thu từ ngày tháng năm đến ngày tháng năm bất kỳ
    public int tinhDoanhThuTuNgayDenNgay(int Ngay, int Thang, int Nam) {
    String sql = "SELECT SUM(total) FROM hoadon WHERE MONTH(timeHd) = ? AND YEAR(timeHd) = ? AND DAYOFMONTH(timeHd) = ?";
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
        statement.setInt(1, Thang);
        statement.setInt(2, Nam);
        statement.setInt(3, Ngay);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1); // Trả về tổng doanh thu
            }
        }
    } catch (SQLException e) {
            e.printStackTrace();
        }
    return 0; // Trả về 0 nếu không tìm thấy kết quả
}

// Phương thức tính vốn từ ngày tháng năm đến ngày tháng năm bất kỳ
    public int tinhVonTuNgayDenNgay(int Ngay, int Thang, int Nam) {
    String sql = "SELECT SUM(Total) FROM phieunhap WHERE MONTH(timeNhap) = ? AND YEAR(timeNhap) = ? AND DAYOFMONTH(timeNhap) = ?";
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
        statement.setInt(1, Thang);
        statement.setInt(2, Nam);
        statement.setInt(3, Ngay);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1); // Trả về tổng doanh thu
            }
        }
    } catch (SQLException e) {
            e.printStackTrace();
        }
    return 0; // Trả về 0 nếu không tìm thấy kết quả
}

// Phương thức tính doanh thu từ tháng đến tháng bất kỳ
    public int tinhDoanhThuTuThangDenThang(int Thang, int Nam) {
    String sql = "SELECT SUM(total) FROM hoadon WHERE YEAR(timeHd) = ? AND MONTH(timeHd) = ?";
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
        statement.setInt(1, Nam);
        statement.setInt(2, Thang);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
    } catch (SQLException e) {
            e.printStackTrace();
        }
    return 0;
}

// Phương thức tính vốn từ tháng đến tháng bất kỳ
    public int tinhVonTuThangDenThang(int Thang, int Nam) {
    String sql = "SELECT SUM(Total) FROM phieunhap WHERE YEAR(timeNhap) = ? AND MONTH(timeNhap) = ?";
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
        statement.setInt(1, Nam);
        statement.setInt(2, Thang);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
    } catch (SQLException e) {
            e.printStackTrace();
        }
    return 0;
}

// Phương thức tính doanh thu từ năm đến năm bất kỳ
public int tinhDoanhThuTuNamDenNam(int Nam) {
    String sql = "SELECT SUM(total) FROM hoadon WHERE YEAR(timeHd) = ?";
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
        statement.setInt(1, Nam);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
    } catch (SQLException e) {
            e.printStackTrace();
        }
    return 0;
}

// Phương thức tính vốn từ năm đến năm bất kỳ
public int tinhVonTuNamDenNam(int Nam) {
    String sql = "SELECT SUM(Total) FROM phieunhap WHERE YEAR(timeNhap) = ?";
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
        statement.setInt(1, Nam);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
    } catch (SQLException e) {
            e.printStackTrace();
        }
    return 0; //ko lỗi
}
    public int demNhanVien() {
        int slnv = 0;
        String sql = "SELECT COUNT(idNv) FROM nhanvien";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slnv;
}
    
    public int demKhachHang(){
        int slkh = 0;
        String sql = "SELECT COUNT(idKhMember) FROM khachhangmember";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slkh;
    }
    public int demSP(){
        int sp = 0;
        String sql = "SELECT COUNT(idSp) FROM sanpham";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sp;
    }
    public double demDT(){
        double dt = 0;
        String sql = "SELECT SUM(total) FROM hoadon";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dt;
    }
    public ArrayList<ArrayList<Object>> topSanPhamBanChay(int limit) {
    ArrayList<ArrayList<Object>> topProducts = new ArrayList<>();
    String sql = "SELECT sanpham.nameSp, SUM(chitiethoadon.quantity) AS totalQuantity FROM chitiethoadon JOIN sanpham ON sanpham.idSp = chitiethoadon.idSp GROUP BY chitiethoadon.idSp ORDER BY totalQuantity DESC LIMIT ?";
    
    try (PreparedStatement statement = conn.prepareStatement(sql)){
        statement.setInt(1, limit);
        try (ResultSet rs = statement.executeQuery()){
            while (rs.next()) {
                ArrayList<Object> productInfo = new ArrayList<>();
                String productName = rs.getString("nameSp");
                int totalQuantity = rs.getInt("totalQuantity");
                productInfo.add(productName);
                productInfo.add(totalQuantity);
                topProducts.add(productInfo);
            }
        }
    } catch (SQLException e){
        e.printStackTrace();
    }
    return topProducts;
}
    public ArrayList<String[]> tongSP() {
    ArrayList<String[]> Products = new ArrayList<>();
    String sql = "SELECT sanpham.nameSp, IFNULL(SUM(chitiethoadon.quantity), 0) AS totalQuantity FROM sanpham LEFT JOIN chitiethoadon ON sanpham.idSp = chitiethoadon.idSp GROUP BY sanpham.idSp";
    try (PreparedStatement statement = conn.prepareStatement(sql)){
        try (ResultSet rs = statement.executeQuery()){
            while (rs.next()) {
            String[] productInfo = new String[2]; // Array to hold product name and total quantity
            String productName = rs.getString("nameSp");
            int totalQuantity = rs.getInt("totalQuantity");

            productInfo[0] = productName;
            productInfo[1] = String.valueOf(totalQuantity); // Convert int to String

            Products.add(productInfo);
        }
        }
    } catch (SQLException e){
        e.printStackTrace();
    }
    return Products;
}
    public int demPX(){
        int px = 0;
        String sql = "SELECT COUNT(idHd) FROM hoadon";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return px;
    }
    public int demPN(){
        int pn = 0;
        String sql = "SELECT COUNT(idPN) FROM phieunhap";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pn;
    }
}

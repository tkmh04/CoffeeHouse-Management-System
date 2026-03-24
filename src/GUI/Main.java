/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author Admin
 */

import GUI.Login;
import BUS.TaiKhoanBUS;
import DTO.TaiKhoanDTO;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.sql.SQLException;
import GUI.ThongKe.ThongKe;



public class Main extends JFrame{
    JPanel Taskbar, Main;
    JPanel pnCard, pnPhieuNhap, pnPhieuXuat, pnNhanVien, pnKhachHang, pnThongKe,pnTaiKhoan,pnSanPham,pnPhanQuyen, pnMenu, pnNguyenLieu;
    ArrayList<JLabel> ListTaskbar;
    CardLayout cardMenu = new CardLayout();
//    Container cn = getContentPane();
    NhanVien nhanVien;
    KhachHang khachHang;
    PhieuNhap phieuNhap;
    PhieuXuat phieuXuat;
    TaiKhoan taiKhoan;
    PhanQuyen phanQuyen;
    ThongKe thongKe;
    Menu menu;
    NguyenLieu nL;

    String[] iconlb = {
    "img/milktea.png",
    "img/pn.png",
    "img/px.png",
    "img/staff.png",
    "img/customer.png",
    "img/tk.png",
    "img/account.png",
    "img/nl.png",
    "img/pq.png",
    "img/nl.png",
    "img/nl.png",
};

    Color MainColor = new Color(250,250,250);
    final Color PanelDefault = new Color(250, 250, 250);//trắng
    final Color PanelHover = new Color(250, 50, 100);//hồng đậm
    final Color PanelClick = new Color(250, 10, 100);//hồng


    TaiKhoanBUS tkBUS = new TaiKhoanBUS();
    
    String tenTaiKhoan;
    int idQuyen;
    ArrayList<String> danhSachChucNang;
    
    public Main(String tenTaiKhoan, int idQuyen){
        this.tenTaiKhoan = tenTaiKhoan;
        this.idQuyen = idQuyen;
        initComonent(); // hoặc xử lý ngoại lệ theo cách thích hợp
}

    
    void initComonent(){
        
        
        this.setSize(new Dimension(1280, 900));
        this.setForeground(MainColor);

        this.setTitle("Cửa hàng Trà Sữa Không Tên");
        Image icon = Toolkit.getDefaultToolkit().getImage("img/TS.png");
        this.setIconImage(icon);
        
        Main = new JPanel();
        Main.setLayout(new BorderLayout());
        
        Taskbar = new JPanel();
        Taskbar.setPreferredSize(new Dimension(220, getHeight()));
        Taskbar.setBackground(Color.pink);
        
        JLabel lbIconUser = new JLabel();
        lbIconUser.setPreferredSize(new Dimension(Taskbar.getPreferredSize().width, 100));
        ImageIcon iconUser = new ImageIcon("img/user.png");
        lbIconUser.setIcon(iconUser);
        lbIconUser.setHorizontalAlignment(JLabel.CENTER);
        Taskbar.add(lbIconUser);
        
        JPanel line = new JPanel();
        line.setOpaque(true);
        line.setBackground(new Color(250, 250, 250));
	line.setPreferredSize(new Dimension(150, 2));
	Taskbar.add(line);
        
        
        JLabel lbTextUser = new JLabel(tenTaiKhoan);
        lbTextUser.setPreferredSize(new Dimension(Taskbar.getPreferredSize().width, 30));
        lbTextUser.setHorizontalAlignment(JLabel.CENTER);
        lbTextUser.setForeground(Color.WHITE);
        Taskbar.add(lbTextUser);
        
        danhSachChucNang = tkBUS.danhSachChucNang(idQuyen);
        ListTaskbar = new ArrayList<>();
        
//        for (String cn : danhSachChucNang){
//            JLabel lb = new JLabel(cn);
//            ListTaskbar.add(lb);
//            lb.setPreferredSize(new Dimension(Taskbar.getPreferredSize().width,50));
//            lb.setOpaque(true);
//            lb.setForeground(Color.PINK);
//            lb.setBackground(MainColor);
//            Font font = new Font("Segoe UI",Font.BOLD, 20);
//            lb.setFont(font);
//            lb.setHorizontalAlignment(JLabel.CENTER);
//            lb.setVisible(true);
//            Taskbar.add(lb,BorderLayout.CENTER);
//        }
        
        for (int i = 0; i < danhSachChucNang.size(); i++) {
            ImageIcon pic = new ImageIcon(iconlb[i]); // Lấy đường dẫn đến biểu tượng từ mảng icon
            JLabel lb = new JLabel(danhSachChucNang.get(i), pic, JLabel.LEFT); // Đặt biểu tượng bên trái
            lb.setVerticalAlignment(JLabel.CENTER);
            lb.setHorizontalAlignment(JLabel.CENTER);
            lb.setIconTextGap(10);
            lb.setPreferredSize(new Dimension(Taskbar.getPreferredSize().width, 50));
            lb.setOpaque(true);
            lb.setForeground(Color.PINK);
            lb.setBackground(MainColor);
            Font font = new Font("Segoe UI", Font.BOLD, 20);
            lb.setFont(font);
            lb.setVisible(true);
            Taskbar.add(lb, BorderLayout.CENTER);
            ListTaskbar.add(lb);
            }


    
        
        pnCard = new JPanel(cardMenu);
        pnNhanVien = new JPanel();
        pnKhachHang = new JPanel();
        pnPhieuNhap = new JPanel();
        pnPhieuXuat = new JPanel();
        pnTaiKhoan = new JPanel();
        pnPhanQuyen = new JPanel();
        pnThongKe = new JPanel();
        pnSanPham = new JPanel();
        pnMenu = new JPanel();
        pnNguyenLieu = new JPanel();
        JPanel pnNhaCC = new JPanel();
        JPanel pnTaoPhieuNhap = new JPanel();
        
        pnCard.add(pnMenu,"1");
        pnCard.add(pnPhieuNhap,"2");
        pnCard.add(pnPhieuXuat,"3");
        pnCard.add(pnNhanVien,"4");
        pnCard.add(pnKhachHang,"5");
        pnCard.add(pnThongKe,"6");
        pnCard.add(pnTaiKhoan,"7");
        pnCard.add(pnNguyenLieu,"8");
        pnCard.add(pnPhanQuyen,"9");
        pnCard.add(pnNhaCC,"10");
        pnCard.add(pnTaoPhieuNhap,"11");

        nhanVien = new NhanVien();
        pnNhanVien.setLayout(new BorderLayout());
        pnNhanVien.add(nhanVien);
        
        khachHang = new KhachHang();
        pnKhachHang.setLayout(new BorderLayout());
        pnKhachHang.add(khachHang);
        
        phieuNhap = new PhieuNhap();
        pnPhieuNhap.setLayout(new BorderLayout());
        pnPhieuNhap.add(phieuNhap);
        
        phieuXuat = new PhieuXuat();
        pnPhieuXuat.setLayout(new BorderLayout());
        pnPhieuXuat.add(phieuXuat);

        taiKhoan = new TaiKhoan();
        pnTaiKhoan.setLayout(new BorderLayout());
        pnTaiKhoan.add(taiKhoan);
        
        phanQuyen = new PhanQuyen(idQuyen);
        pnPhanQuyen.setLayout(new BorderLayout());
        pnPhanQuyen.add(phanQuyen);
                
        thongKe = new ThongKe();
        pnThongKe.setLayout(new BorderLayout());
        pnThongKe.add(thongKe);
        
        menu = new Menu();
        pnMenu.setLayout(new BorderLayout());
        pnMenu.add(menu);
        
        nL = new NguyenLieu();
        pnNguyenLieu.setLayout(new BorderLayout());
        pnNguyenLieu.add(nL);
        
        Main.add(pnCard);////trung tâm, phía bắc, phía nam, phía đông và phía tây        
        add(Main);
        
        JButton Logout = new JButton("Đăng xuất");
        Logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try { /////try-catch
                dispose();
                Login login = new Login();
                login.setVisible(true);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi đăng xuất");
                }
            }
        });    
        Taskbar.add(Logout);
               
        JScrollPane scrollMenu = new JScrollPane(Taskbar, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    Main.add(scrollMenu, BorderLayout.WEST);
        
        Mouse();
    }
    private void Mouse(){
        for (JLabel lb : ListTaskbar){
            lb.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (JLabel lbDisable : ListTaskbar) {
                        lbDisable.setBackground(PanelDefault);
                        lb.setForeground(Color.pink);
                    }
                    lb.setBackground(PanelClick);
                    
                    String cardName = "";
                    if (lb.getText().equals("Menu")) {
                    cardName = "1";
                    } else if (lb.getText().equals("Phiếu nhập")) {
                        cardName = "2";
                    } else if (lb.getText().equals("Phiếu xuất")) {
                        cardName = "3";
                    } else if (lb.getText().equals("Nhân viên")) {
                        cardName = "4";
                    } 
                    else if (lb.getText().equals("Khách hàng")) {
                        cardName = "5";
                    } 
                    else if (lb.getText().equals("Thống kê")) {
                        cardName = "6";
                    } else if (lb.getText().equals("Tài khoản")) {
                        cardName = "7";
                    } else if (lb.getText().equals("Nguyên liệu")) {
                        cardName = "8";
                    }
                    else if (lb.getText().equals("Phân quyền")) {
                        cardName = "9";
                    }
                    else if (lb.getText().equals("Nhà Cung Cấp")) {
                        cardName = "10";
                    }
                    else if (lb.getText().equals("Tạo Phiếu Nhập")) {
                        cardName = "11";
                    }
                    cardMenu.show(pnCard, cardName);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (lb.getBackground().equals(PanelDefault)) {
                        lb.setBackground(PanelHover);
                        lb.setForeground(MainColor);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (lb.getBackground().equals(PanelHover)) {
                        lb.setBackground(PanelDefault);
                        lb.setForeground(Color.PINK);
                    }
                }
            });
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.TaiKhoanBUS;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


/**
 *
 * @author asus
 */
public class MainFrame extends JFrame{
    public JLabel lbMenu, lbPhieuNhap, lbPhieuXuat, lbNhanVien, lbKhachHang, lbThongKe,lbTaiKhoan,lbNguyenLieu;    
    JPanel Taskbar, Main;
    private TaiKhoanBUS tkBUS = new TaiKhoanBUS();
    JPanel pnCard, pnMenu, pnPhieuNhap, pnPhieuXuat, pnNhanVien, pnKhachHang, pnThongKe,pnTaiKhoan,pnNguyenLieu,pnPhanQuyen;
    ArrayList<JLabel> ListTaskbar;
    CardLayout cardMenu = new CardLayout();
    Container cn = getContentPane();
    ArrayList<String> danhSachChucNang;
    private String tenTaiKhoan;
    private int idQuyen;
    PhanQuyenGUI PhanQuyen;
//    PhieuNhap phieuNhap;
//    PhieuXuat phieuXuat;
    KhachHangGUI khachHang;
    TaiKhoanGUI taiKhoan;
//    ThongKe thongKe;
    NguyenlieuGUI NguyenLieu;
    SanPhamGUI menu;
    Color MainColor = new Color(250,250,250);
    final Color PanelDefault = new Color(250, 250, 250);//trắng
    final Color PanelHover = new Color(250, 50, 100);//hồng đậm
    final Color PanelClick = new Color(250, 10, 100);//hồng  
    String[] iconlb = {
    "src/img/milktea.png",
    "src/img/pn.png",
    "src/img/px.png",
    "src/img/staff.png",
    "src/img/customer.png",
    "src/img/tk.png",
    "src/img/account.png",
    "src/img/nl.png",
    "src/img/pq.png"
};
    public MainFrame(String tenTaiKhoan,int idQuyen){
        this.tenTaiKhoan = tenTaiKhoan;
        this.idQuyen = idQuyen;
        initComonent();
    }
    void initComonent(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        this.setSize(new Dimension(1280, 700));
        this.setForeground(MainColor);
        this.setLayout(new BorderLayout(0,0));
        this.setTitle("Cửa hàng Trà Sữa Không Tên");
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/TS.png"));
        this.setIconImage(icon);
        this.setVisible(true);
        Main = new JPanel();
        Main.setLayout(new BorderLayout());
        
        Taskbar = new JPanel();
        Taskbar.setPreferredSize(new Dimension(225, 200));
        Taskbar.setBackground(Color.pink);
        
        JLabel lbIconUser = new JLabel();   
        lbIconUser.setPreferredSize(new Dimension(Taskbar.getPreferredSize().width, 100));
        ImageIcon iconUser = new ImageIcon(getClass().getResource("/img/user.png"));
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
        
//        lbMenu = new JLabel("Menu");
//        lbPhieuNhap = new JLabel("Phiếu Nhập");
//        lbPhieuXuat = new JLabel("Phiếu Xuất");
//        lbNhanVien = new JLabel("Nhân Viên");
//        lbKhachHang = new JLabel("Khách Hàng");
//        lbThongKe = new JLabel("Thống Kê");
//        lbTaiKhoan = new JLabel("Tài Khoản");
//        lbNguyenLieu = new JLabel("Kho Nguyên Liệu");
        
        danhSachChucNang = tkBUS.danhSachChucNang(idQuyen);
        ListTaskbar = new ArrayList<>();
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
//        ListTaskbar.add(lbMenu);
//        ListTaskbar.add(lbPhieuNhap);
//        ListTaskbar.add(lbPhieuXuat);
//        ListTaskbar.add(lbNhanVien);
//        ListTaskbar.add(lbKhachHang);
//        ListTaskbar.add(lbTaiKhoan);
//        ListTaskbar.add(lbThongKe);
//        ListTaskbar.add(lbNguyenLieu);
        Main.add(Taskbar, BorderLayout.WEST);
        JButton dangXuat = new JButton();

        for (JLabel lb : ListTaskbar){
            lb.setPreferredSize(new Dimension(Taskbar.getPreferredSize().width,50));
            lb.setOpaque(true);
            lb.setForeground(Color.PINK);
            lb.setBackground(MainColor);
            Font font = new Font("Segoe UI",Font.BOLD, 20);
            lb.setFont(font);
            lb.setHorizontalAlignment(JLabel.CENTER);

            Taskbar.add(lb,BorderLayout.CENTER);
        }
//        lbMenu.setBackground(PanelClick);
//        
//        lbMenu.setVisible(true);
        Main.add(Taskbar, BorderLayout.WEST);
        
        pnCard = new JPanel(cardMenu);
        pnMenu = new JPanel();
        pnPhieuNhap = new JPanel();
        pnPhieuXuat = new JPanel();
        pnNhanVien = new JPanel();
        pnKhachHang = new JPanel();
        pnThongKe = new JPanel();
        pnTaiKhoan = new JPanel();
        pnNguyenLieu = new JPanel();
        pnPhanQuyen = new JPanel();
        
        pnMenu.setVisible(true);
        pnCard.add(pnMenu,"1");
        pnCard.add(pnPhieuNhap,"2");
        pnCard.add(pnPhieuXuat,"3");
        pnCard.add(pnNhanVien,"4");
        pnCard.add(pnKhachHang,"5");
        pnCard.add(pnThongKe,"6");
        pnCard.add(pnTaiKhoan,"7");
        pnCard.add(pnNguyenLieu,"8");
        pnCard.add(pnPhanQuyen,"9");
        
         
          menu = new SanPhamGUI();
          
          JScrollPane  scrollPane = new JScrollPane(menu);
          scrollPane.getVerticalScrollBar().setUnitIncrement(16);
          pnMenu.setLayout(new BorderLayout());
          pnMenu.add(scrollPane,BorderLayout.CENTER);
          


//        phieuNhap = new PhieuNhap();
//        pnPhieuNhap.setLayout(new BorderLayout());
//        pnPhieuNhap.add(phieuNhap);
//        lbPhieuNhap.setVisible(true);
//   
//        phieuXuat = new PhieuXuat();
//        pnPhieuXuat.setLayout(new BorderLayout());
//        pnPhieuXuat.add(phieuXuat);
//        lbPhieuXuat.setVisible(true);

        
        khachHang = new KhachHangGUI();
        pnKhachHang.setLayout(new BorderLayout());
        pnKhachHang.add(khachHang);
        
        taiKhoan = new TaiKhoanGUI();
        pnTaiKhoan.setLayout(new BorderLayout());
        pnTaiKhoan.add(taiKhoan);
        
//        
//        
//        thongKe = new ThongKe();
//        pnThongKe.setLayout(new BorderLayout());
//        pnThongKe.add(thongKe);
//        lbThongKe.setVisible(true);
//
        NguyenLieu = new NguyenlieuGUI();
          JScrollPane  scrollPaneNL = new JScrollPane(NguyenLieu);
          scrollPaneNL.getVerticalScrollBar().setUnitIncrement(16);
//        pnNguyenLieu.setLayout(new BorderLayout());
        pnNguyenLieu.add(NguyenLieu);
//        lbNguyenLieu.setVisible(true);
     
        
        PhanQuyen = new PhanQuyenGUI(idQuyen);
        pnPhanQuyen.setLayout(new BorderLayout());
        pnPhanQuyen.add(PhanQuyen);
        
        Main.add(pnCard);
        cn.add(Main);
        Mouse();
    }
    private void Mouse(){
        for (JLabel lb : ListTaskbar){
            lb.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (JLabel lbDisable : ListTaskbar) {
                        lbDisable.setBackground(PanelDefault);
                        lb.setForeground(Color.PINK);
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
                    cardMenu.show(pnCard, cardName);
//                    System.out.println(cardName);
                    
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
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
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

    NguyenlieuGUI NguyenLieu;
    SanPhamGUI menu;

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

    // Professional color scheme - Pink gradient
    Color MainColor = new Color(248, 248, 252);
    final Color TaskbarBg = new Color(255, 182, 193);  // Light pink
    final Color TextPrimary = new Color(90, 52, 72);
    final Color TextSecondary = new Color(120, 78, 100);
    final Color PanelDefault = new Color(255, 244, 248);
    final Color PanelHover = new Color(245, 170, 200);  // Pink on hover
    final Color PanelClick = new Color(220, 92, 146);   // Active pink

    // Taskbar specific settings
    final int TASKBAR_WIDTH = 280;
    final int MENU_ITEM_HEIGHT = 58;
    final int MENU_ITEM_WIDTH = 240;

    TaiKhoanBUS tkBUS = new TaiKhoanBUS();
    
    String tenTaiKhoan;
    int idQuyen;
    ArrayList<String> danhSachChucNang;
    Map<String, String> mapChucNangCard = new HashMap<>(); // Map function name -> card name
    
    public Main(String tenTaiKhoan, int idQuyen){
        this.tenTaiKhoan = tenTaiKhoan;
        this.idQuyen = idQuyen;
        initComonent(); // hoặc xử lý ngoại lệ theo cách thích hợp
}

    
    void initComonent(){
        this.setSize(new Dimension(1280, 900));
        this.setForeground(MainColor);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("Cửa hàng Trà Sữa Không Tên");
        Image icon = Toolkit.getDefaultToolkit().getImage("img/TS.png");
        this.setIconImage(icon);
        
        // Main panel with gradient background
        Main = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // More balanced gradient
                GradientPaint gradient = new GradientPaint(0, 0, new Color(252, 248, 250),
                                                          getWidth(), getHeight(), new Color(248, 240, 245));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        Main.setLayout(new BorderLayout());
        
        // Taskbar with improved styling
        Taskbar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Gradient background for taskbar
                GradientPaint gradient = new GradientPaint(0, 0, new Color(255, 190, 200),
                                                          getWidth(), getHeight(), new Color(255, 160, 185));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                
                // Right border shadow
                g2d.setColor(new Color(0, 0, 0, 20));
                g2d.fillRect(getWidth() - 2, 0, 2, getHeight());
            }
        };
        Taskbar.setPreferredSize(new Dimension(TASKBAR_WIDTH, getHeight()));
        Taskbar.setLayout(new BoxLayout(Taskbar, BoxLayout.Y_AXIS));
        Taskbar.setBorder(BorderFactory.createEmptyBorder());
        
        // User section with enhanced styling
        JPanel userSection = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Subtle white background
                g2d.setColor(new Color(255, 255, 255, 30));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        userSection.setLayout(new BoxLayout(userSection, BoxLayout.Y_AXIS));
        userSection.setOpaque(false);
        userSection.setPreferredSize(new Dimension(250, 140));
        userSection.setMaximumSize(new Dimension(250, 140));
        userSection.setBorder(BorderFactory.createEmptyBorder(20, 0, 15, 0));
        
        // User icon with better styling
        JLabel lbIconUser = new JLabel();
        lbIconUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbIconUser.setPreferredSize(new Dimension(80, 80));
        lbIconUser.setMaximumSize(new Dimension(80, 80));
        try {
            ImageIcon iconUser = new ImageIcon("img/user.png");
            Image scaledUserIcon = iconUser.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            lbIconUser.setIcon(new ImageIcon(scaledUserIcon));
        } catch (Exception e) {
            lbIconUser.setText("👤");
            lbIconUser.setFont(new Font("Segoe UI", Font.PLAIN, 40));
        }
        lbIconUser.setHorizontalAlignment(JLabel.CENTER);
        userSection.add(lbIconUser);
        
        // Username with better styling
        JLabel lbTextUser = new JLabel(tenTaiKhoan);
        lbTextUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbTextUser.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTextUser.setForeground(new Color(100, 50, 80));
        userSection.add(Box.createVerticalStrut(8));
        userSection.add(lbTextUser);
        
        Taskbar.add(userSection);
        
        // Enhanced separator
        JPanel separator = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Gradient separator
                GradientPaint gradient = new GradientPaint(0, 0, new Color(255, 140, 170, 0),
                                                          getWidth(), 0, new Color(220, 100, 150));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        separator.setOpaque(false);
        separator.setPreferredSize(new Dimension(MENU_ITEM_WIDTH, 2));
        separator.setMaximumSize(new Dimension(MENU_ITEM_WIDTH, 2));
        separator.setAlignmentX(Component.CENTER_ALIGNMENT);
        Taskbar.add(separator);
        
        danhSachChucNang = tkBUS.danhSachChucNang(idQuyen);
        ListTaskbar = new ArrayList<>();
        
        // Add spacing before menu items
        Taskbar.add(Box.createVerticalStrut(16));

        JLabel lbMenuTitle = new JLabel("DANH MUC CHUC NANG");
        lbMenuTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbMenuTitle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lbMenuTitle.setForeground(new Color(126, 73, 101));
        lbMenuTitle.setBorder(BorderFactory.createEmptyBorder(0, 10, 12, 10));
        Taskbar.add(lbMenuTitle);
        
        // Create mapping between function names and card names
        mapChucNangCard.put("menu", "1");
        mapChucNangCard.put("phiếu nhập", "2");
        mapChucNangCard.put("phiếu xuất", "3");
        mapChucNangCard.put("nhân viên", "4");
        mapChucNangCard.put("khách hàng", "5");
        mapChucNangCard.put("thống kê", "6");
        mapChucNangCard.put("tài khoản", "7");
        mapChucNangCard.put("nguyên liệu", "8");
        mapChucNangCard.put("phân quyền", "9");
        mapChucNangCard.put("nhà cung cấp", "10");
        mapChucNangCard.put("tạo phiếu nhập", "11");
        
        for (int i = 0; i < danhSachChucNang.size(); i++) {
            try {
                ImageIcon pic = new ImageIcon(iconlb[i]);
                Image scaledImg = pic.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImg);
                
                JLabel lb = createMenuItemLabel(danhSachChucNang.get(i), scaledIcon);
                Taskbar.add(lb);
                ListTaskbar.add(lb);
            } catch (Exception e) {
                JLabel lb = createMenuItemLabel(danhSachChucNang.get(i), null);
                Taskbar.add(lb);
                ListTaskbar.add(lb);
            }
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

        if (!ListTaskbar.isEmpty()) {
            JLabel defaultLabel = ListTaskbar.get(0);
            setMenuItemActive(defaultLabel);
            String cardName = mapChucNangCard.getOrDefault(normalizeMenuText(defaultLabel.getText()), "1");
            cardMenu.show(pnCard, cardName);
        }

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
        
        menu = new SanPhamGUI();
        pnMenu.setLayout(new BorderLayout());
        pnMenu.add(menu);
        
        NguyenLieu = new NguyenlieuGUI();
        pnNguyenLieu.setLayout(new BorderLayout());
        pnNguyenLieu.add(NguyenLieu);
        
        Main.add(pnCard);
        add(Main);
        
        // Add spacing before logout button
        Taskbar.add(Box.createVerticalGlue());
        
        // Enhanced logout button with better styling
        JButton Logout = new JButton("Đăng xuất");
        Logout.setPreferredSize(new Dimension(MENU_ITEM_WIDTH, MENU_ITEM_HEIGHT));
        Logout.setMaximumSize(new Dimension(MENU_ITEM_WIDTH, MENU_ITEM_HEIGHT));
        Logout.setAlignmentX(Component.CENTER_ALIGNMENT);
        Logout.setBackground(new Color(220, 100, 150));
        Logout.setForeground(Color.WHITE);
        Logout.setFont(new Font("Segoe UI", Font.BOLD, 12));
        Logout.setOpaque(true);
        Logout.setFocusPainted(false);
        Logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Logout.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        
        // Enhanced hover effect
        Logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Logout.setBackground(new Color(255, 140, 175));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                Logout.setBackground(new Color(220, 100, 150));
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                Logout.setBackground(new Color(200, 80, 130));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                Logout.setBackground(new Color(255, 140, 175));
            }
        });
        
        Logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dispose();
                    Login login = new Login();
                    login.setVisible(true);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi đăng xuất");
                }
            }
        });
        
        Taskbar.add(Box.createVerticalStrut(15));
        JPanel logoutPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Subtle background
                g2d.setColor(new Color(255, 255, 255, 15));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        logoutPanel.setOpaque(false);
        logoutPanel.setMaximumSize(new Dimension(TASKBAR_WIDTH, MENU_ITEM_HEIGHT + 20));
        logoutPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));
        logoutPanel.setLayout(new BoxLayout(logoutPanel, BoxLayout.X_AXIS));
        logoutPanel.add(Box.createHorizontalGlue());
        logoutPanel.add(Logout);
        logoutPanel.add(Box.createHorizontalGlue());
        Taskbar.add(logoutPanel);
               
        JScrollPane scrollMenu = new JScrollPane(Taskbar, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollMenu.setBorder(null);
        scrollMenu.setOpaque(false);
        scrollMenu.getViewport().setOpaque(false);
        scrollMenu.getVerticalScrollBar().setUnitIncrement(10);
        Main.add(scrollMenu, BorderLayout.WEST);
        
        Mouse();
    }

    private JLabel createMenuItemLabel(String text, Icon icon) {
        JLabel lb = new JLabel(text, icon, JLabel.LEFT);
        lb.setVerticalAlignment(JLabel.CENTER);
        lb.setHorizontalAlignment(JLabel.LEFT);
        lb.setHorizontalTextPosition(JLabel.RIGHT);
        lb.setVerticalTextPosition(JLabel.CENTER);
        lb.setIconTextGap(14);
        lb.setPreferredSize(new Dimension(MENU_ITEM_WIDTH, MENU_ITEM_HEIGHT));
        lb.setMaximumSize(new Dimension(MENU_ITEM_WIDTH, MENU_ITEM_HEIGHT));
        lb.setAlignmentX(Component.CENTER_ALIGNMENT);
        lb.setOpaque(true);
        lb.setForeground(TextSecondary);
        lb.setBackground(PanelDefault);
        lb.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 4, 0, 0, PanelDefault),
                BorderFactory.createEmptyBorder(0, 14, 0, 12)));
        lb.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return lb;
    }

    private void setMenuItemDefault(JLabel lb) {
        lb.setBackground(PanelDefault);
        lb.setForeground(TextSecondary);
        lb.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 4, 0, 0, PanelDefault),
                BorderFactory.createEmptyBorder(0, 14, 0, 12)));
    }

    private void setMenuItemHover(JLabel lb) {
        lb.setBackground(PanelHover);
        lb.setForeground(Color.WHITE);
        lb.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 4, 0, 0, new Color(255, 236, 245)),
                BorderFactory.createEmptyBorder(0, 14, 0, 12)));
    }

    private void setMenuItemActive(JLabel lb) {
        lb.setBackground(PanelClick);
        lb.setForeground(Color.WHITE);
        lb.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 4, 0, 0, Color.WHITE),
                BorderFactory.createEmptyBorder(0, 14, 0, 12)));
    }

    private String normalizeMenuText(String text) {
        return text == null ? "" : text.toLowerCase().trim();
    }

    private void Mouse(){
        for (JLabel lb : ListTaskbar){
            lb.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Reset all to default
                    for (JLabel lbDisable : ListTaskbar) {
                        setMenuItemDefault(lbDisable);
                    }
                    setMenuItemActive(lb);
                    
                    String labelText = normalizeMenuText(lb.getText());
                    String cardName = mapChucNangCard.getOrDefault(labelText, "1");
                    cardMenu.show(pnCard, cardName);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (lb.getBackground().equals(PanelClick)) {
                        lb.setBackground(new Color(195, 72, 126));
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (lb.getBackground().equals(new Color(195, 72, 126))) {
                        setMenuItemActive(lb);
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (lb.getBackground().equals(PanelDefault)) {
                        setMenuItemHover(lb);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (lb.getBackground().equals(PanelHover)) {
                        setMenuItemDefault(lb);
                    }
                }
            });
        }
    }
}

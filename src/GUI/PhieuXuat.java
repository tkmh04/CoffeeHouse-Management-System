/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.PhieuNhapBUS;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class PhieuXuat extends JPanel {
    
    private JPanel top, bot, center;
    private JButton them, xoa, sua, xemChiTiet, nhap, xuat;
    private JLabel search;
    private JTextField txt;
    private String valueToSearch = "";
    private String selectedBox = "";
    private JComboBox comboBox;
    private JTextField txtIDHoaDon, txtIDNhanVien, txtIDKhachHang, txtThoiGian, txtTongTien;
    private JTable tb;
    private DefaultTableModel model;
    private String[] column = {"ID Hóa Đơn", "ID Nhân Viên", "ID Khách Hàng", "Thời Gian", "Tổng Tiền"};

    public PhieuXuat() {
        initComponent();
    }

    private void hienthi() {
        // TODO: Gọi BUS layer để lấy dữ liệu từ database
        model = new DefaultTableModel(column, 0);
        // Dữ liệu demo tạm thời
        Object[] row1 = {1, 1, 1, "2024-05-19 10:30", 50000};
        Object[] row2 = {2, 2, 3, "2024-05-19 11:15", 75000};
        model.addRow(row1);
        model.addRow(row2);
        tb.setModel(model);
    }

    private void them() {
        JFrame dialog = new JFrame("Thêm hóa đơn");
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setSize(450, 300);
        dialog.setLocationRelativeTo(null);
        
        JPanel p = new JPanel(new GridLayout(5, 2, 10, 10));
        p.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        p.add(new JLabel("ID Nhân Viên:"));
        txtIDNhanVien = new JTextField();
        p.add(txtIDNhanVien);
        
        p.add(new JLabel("ID Khách Hàng:"));
        txtIDKhachHang = new JTextField();
        p.add(txtIDKhachHang);
        
        p.add(new JLabel("Thời Gian:"));
        txtThoiGian = new JTextField();
        txtThoiGian.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        p.add(txtThoiGian);
        
        p.add(new JLabel("Tổng Tiền:"));
        txtTongTien = new JTextField();
        p.add(txtTongTien);
        
        p.add(new JLabel("Ghi Chú:"));
        JTextArea noteArea = new JTextArea();
        noteArea.setLineWrap(true);
        noteArea.setWrapStyleWord(true);
        p.add(new JScrollPane(noteArea));
        
        JPanel pBtn = new JPanel();
        JButton btnThemOK = new JButton("Thêm");
        btnThemOK.addActionListener(e1 -> {
            if (validateInput()) {
                // TODO: Gọi BUS để thêm hóa đơn
                JOptionPane.showMessageDialog(null, "Thêm hóa đơn thành công");
                hienthi();
                dialog.dispose();
            }
        });
        pBtn.add(btnThemOK);
        
        JButton btnCancel = new JButton("Hủy");
        btnCancel.addActionListener(e1 -> dialog.dispose());
        pBtn.add(btnCancel);
        
        dialog.setLayout(new BorderLayout());
        dialog.add(p, BorderLayout.CENTER);
        dialog.add(pBtn, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private void xoa(int rowIndex) {
        String idStr = model.getValueAt(rowIndex, 0).toString();
        // TODO: Gọi BUS để xóa hóa đơn
        JOptionPane.showMessageDialog(null, "Xóa hóa đơn thành công");
        hienthi();
    }

    private void sua() {
        int selectedRow = tb.getSelectedRow();
        if (selectedRow != -1) {
            String idHoaDon = model.getValueAt(selectedRow, 0).toString();
            String idNhanVien = model.getValueAt(selectedRow, 1).toString();
            String idKhachHang = model.getValueAt(selectedRow, 2).toString();
            String thoiGian = model.getValueAt(selectedRow, 3).toString();
            String tongTien = model.getValueAt(selectedRow, 4).toString();

            JFrame dialog = new JFrame("Sửa hóa đơn");
            dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dialog.setSize(450, 320);
            dialog.setLocationRelativeTo(null);
            
            JPanel p = new JPanel(new GridLayout(5, 2, 10, 10));
            p.setBorder(new EmptyBorder(20, 20, 20, 20));
            
            p.add(new JLabel("ID Hóa Đơn:"));
            txtIDHoaDon = new JTextField(idHoaDon);
            txtIDHoaDon.setEditable(false);
            p.add(txtIDHoaDon);
            
            p.add(new JLabel("ID Nhân Viên:"));
            txtIDNhanVien = new JTextField(idNhanVien);
            p.add(txtIDNhanVien);
            
            p.add(new JLabel("ID Khách Hàng:"));
            txtIDKhachHang = new JTextField(idKhachHang);
            p.add(txtIDKhachHang);
            
            p.add(new JLabel("Thời Gian:"));
            txtThoiGian = new JTextField(thoiGian);
            p.add(txtThoiGian);
            
            p.add(new JLabel("Tổng Tiền:"));
            txtTongTien = new JTextField(tongTien);
            p.add(txtTongTien);
            
            JPanel pBtn = new JPanel();
            JButton btnSaveOK = new JButton("Lưu");
            btnSaveOK.addActionListener(e1 -> {
                if (validateInput()) {
                    // TODO: Gọi BUS để cập nhật hóa đơn
                    JOptionPane.showMessageDialog(null, "Cập nhật hóa đơn thành công");
                    hienthi();
                    dialog.dispose();
                }
            });
            pBtn.add(btnSaveOK);
            
            JButton btnCancel = new JButton("Hủy");
            btnCancel.addActionListener(e1 -> dialog.dispose());
            pBtn.add(btnCancel);
            
            dialog.setLayout(new BorderLayout());
            dialog.add(p, BorderLayout.CENTER);
            dialog.add(pBtn, BorderLayout.SOUTH);
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn cần sửa");
        }
    }

    private void xemChiTiet() {
        int selectedRow = tb.getSelectedRow();
        if (selectedRow != -1) {
            String idHoaDon = model.getValueAt(selectedRow, 0).toString();
            String idNhanVien = model.getValueAt(selectedRow, 1).toString();
            String idKhachHang = model.getValueAt(selectedRow, 2).toString();
            String thoiGian = model.getValueAt(selectedRow, 3).toString();
            String tongTien = model.getValueAt(selectedRow, 4).toString();

            JFrame dialog = new JFrame("Chi Tiết Hóa Đơn");
            dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dialog.setSize(600, 450);
            dialog.setLocationRelativeTo(null);
            
            // Thông tin chính
            JPanel pInfo = new JPanel(new GridLayout(4, 2, 10, 10));
            pInfo.setBorder(new EmptyBorder(20, 20, 10, 20));
            pInfo.add(new JLabel("ID Hóa Đơn:"));
            pInfo.add(new JLabel(idHoaDon));
            pInfo.add(new JLabel("ID Nhân Viên:"));
            pInfo.add(new JLabel(idNhanVien));
            pInfo.add(new JLabel("ID Khách Hàng:"));
            pInfo.add(new JLabel(idKhachHang));
            pInfo.add(new JLabel("Thời Gian:"));
            pInfo.add(new JLabel(thoiGian));
            
            // Chi tiết sản phẩm
            JPanel pDetail = new JPanel(new BorderLayout());
            pDetail.setBorder(new EmptyBorder(10, 20, 20, 20));
            
            String[] columnDetail = {"ID Sản Phẩm", "ID Topping", "Số Lượng", "Giá"};
            DefaultTableModel modelDetail = new DefaultTableModel(columnDetail, 0);
            
            // Dữ liệu demo cho chi tiết
            Object[] row1 = {1, 1, 2, 50000};
            Object[] row2 = {2, 2, 1, 25000};
            modelDetail.addRow(row1);
            modelDetail.addRow(row2);
            
            JTable tableDetail = new JTable(modelDetail);
            tableDetail.setFocusable(false);
            tableDetail.setRowHeight(25);
            Font fontTable = new Font("Segoe UI", Font.BOLD, 12);
            tableDetail.getTableHeader().setFont(fontTable);
            tableDetail.getTableHeader().setBackground(new Color(250, 50, 100));
            tableDetail.getTableHeader().setForeground(Color.WHITE);
            
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < tableDetail.getColumnCount(); i++) {
                tableDetail.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            
            JScrollPane spDetail = new JScrollPane(tableDetail);
            pDetail.add(new JLabel("Chi Tiết Sản Phẩm:"), BorderLayout.NORTH);
            pDetail.add(spDetail, BorderLayout.CENTER);
            
            // Tổng tiền
            JPanel pTotal = new JPanel(new BorderLayout());
            pTotal.setBorder(new EmptyBorder(10, 20, 10, 20));
            JLabel lblTotal = new JLabel("Tổng Tiền: " + tongTien + " VND");
            lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
            lblTotal.setForeground(new Color(250, 50, 100));
            pTotal.add(lblTotal, BorderLayout.CENTER);
            
            // Nút đóng
            JPanel pBtn = new JPanel();
            JButton btnClose = new JButton("Đóng");
            btnClose.addActionListener(e1 -> dialog.dispose());
            pBtn.add(btnClose);
            
            dialog.setLayout(new BorderLayout());
            dialog.add(pInfo, BorderLayout.NORTH);
            dialog.add(pDetail, BorderLayout.CENTER);
            dialog.add(pTotal, BorderLayout.SOUTH);
            
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn cần xem chi tiết");
        }
    }

    private void timkiem(String keyword, String value) {
        // TODO: Gọi BUS layer để tìm kiếm
        if (value.isEmpty()) {
            hienthi();
        } else {
            model.setRowCount(0);
            // Dữ liệu demo cho tìm kiếm
            if (selectedBox.equals("Tất cả") || selectedBox.isEmpty()) {
                hienthi();
            }
        }
    }

    private boolean validateInput() {
        if (txtIDNhanVien.getText().trim().isEmpty() || 
            txtIDKhachHang.getText().trim().isEmpty() ||
            txtTongTien.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
            return false;
        }
        try {
            Integer.parseInt(txtIDNhanVien.getText());
            Integer.parseInt(txtIDKhachHang.getText());
            Double.parseDouble(txtTongTien.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập dữ liệu hợp lệ");
            return false;
        }
        return true;
    }

    private void initComponent() {
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());

        // Top panel - search
        top = new JPanel();
        top.setBackground(Color.WHITE);
        top.setPreferredSize(new Dimension(1138, 70));
        top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
        top.setBorder(new EmptyBorder(10, 20, 10, 20));

        search = new JLabel("Tìm Kiếm:");
        String[] list = {"Tất cả", "ID Hóa Đơn", "ID Nhân Viên", "ID Khách Hàng", "Thời Gian", "Tổng Tiền"};
        comboBox = new JComboBox(list);
        comboBox.setPreferredSize(new Dimension(150, 40));
        comboBox.addActionListener(e -> {
            selectedBox = comboBox.getSelectedItem().toString();
            timkiem(selectedBox, valueToSearch);
        });

        txt = new JTextField();
        txt.setPreferredSize(new Dimension(250, 40));
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                valueToSearch = txt.getText().trim();
                timkiem(selectedBox, valueToSearch);
            }
        });

        top.add(search);
        top.add(Box.createHorizontalStrut(10));
        top.add(comboBox);
        top.add(Box.createHorizontalStrut(10));
        top.add(txt);
        top.add(Box.createHorizontalGlue());

        // Bottom panel - buttons
        bot = new JPanel();
        bot.setBackground(Color.WHITE);
        bot.setPreferredSize(new Dimension(1138, 70));
        bot.setBorder(new EmptyBorder(10, 20, 10, 20));

        them = new JButton("Thêm");
        them.setPreferredSize(new Dimension(120, 50));
        them.setBackground(new Color(250, 100, 150));
        them.setForeground(Color.WHITE);
        them.setFont(new Font("Arial", Font.BOLD, 14));
        them.addActionListener(e -> them());

        xoa = new JButton("Xóa");
        xoa.setPreferredSize(new Dimension(120, 50));
        xoa.setBackground(new Color(255, 100, 100));
        xoa.setForeground(Color.WHITE);
        xoa.setFont(new Font("Arial", Font.BOLD, 14));
        xoa.addActionListener(e -> {
            int selectedRow = tb.getSelectedRow();
            if (selectedRow != -1) {
                int confirm = JOptionPane.showConfirmDialog(null, "Xóa hóa đơn này?");
                if (confirm == JOptionPane.YES_OPTION) {
                    xoa(selectedRow);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn hàng cần xóa");
            }
        });

        sua = new JButton("Sửa");
        sua.setPreferredSize(new Dimension(120, 50));
        sua.setBackground(new Color(100, 150, 255));
        sua.setForeground(Color.WHITE);
        sua.setFont(new Font("Arial", Font.BOLD, 14));
        sua.addActionListener(e -> sua());

        xemChiTiet = new JButton("Xem Chi Tiết");
        xemChiTiet.setPreferredSize(new Dimension(140, 50));
        xemChiTiet.setBackground(new Color(100, 200, 100));
        xemChiTiet.setForeground(Color.WHITE);
        xemChiTiet.setFont(new Font("Arial", Font.BOLD, 14));
        xemChiTiet.addActionListener(e -> xemChiTiet());

        nhap = new JButton("Nhập");
        nhap.setPreferredSize(new Dimension(120, 50));
        nhap.setEnabled(false);

        xuat = new JButton("Xuất");
        xuat.setPreferredSize(new Dimension(120, 50));
        xuat.setEnabled(false);

        bot.add(them);
        bot.add(xoa);
        bot.add(sua);
        bot.add(xemChiTiet);
        bot.add(nhap);
        bot.add(xuat);

        // Center panel - table
        center = new JPanel();
        center.setLayout(new BorderLayout());

        tb = new JTable();
        hienthi();

        JScrollPane sp = new JScrollPane(tb);
        center.add(sp, BorderLayout.CENTER);

        Font font1 = new Font("Segoe UI", Font.BOLD, 13);
        tb.setFocusable(false);
        tb.setIntercellSpacing(new Dimension(0, 0));
        tb.getTableHeader().setFont(font1);
        tb.setRowHeight(30);
        tb.setShowVerticalLines(false);
        tb.getTableHeader().setOpaque(false);
        tb.setFillsViewportHeight(true);
        tb.getTableHeader().setBackground(new Color(250, 50, 100));
        tb.getTableHeader().setForeground(Color.WHITE);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tb.getColumnCount(); i++) {
            tb.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Add panels
        this.add(top, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        this.add(bot, BorderLayout.SOUTH);
    }
}

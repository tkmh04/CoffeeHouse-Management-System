/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.NguyenLieuBUS;
import DTO.NguyenLieuDTO;
import BUS.NguyenLieuBUS;
import DTO.NguyenLieuDTO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author Admin
    private NguyenLieuBUS nlBUS = new NguyenLieuBUS();
 */
public class NguyenLieu extends JPanel {
    private NguyenLieuBUS nlBUS = new NguyenLieuBUS();
    private JPanel top, bot, center;
    private JButton them, xoa, sua, nhap, xuat;
    private JLabel search;
    private JTextField txt;
    private String valueToSearch = "";
    private String selectedBox = "";
    private JComboBox comboBox;
    private JTextField txtMaNL, txtTenNL, txtDonViTinh, txtSoLuong, txtGiaNhap;
    private JTable tb;
    private DefaultTableModel model;
    private String[] column = {"Mã NL", "Tên NL", "Đơn Vị Tính", "Số Lượng", "Giá Nhập"};

    public NguyenLieu() {
        initComponent();
    }

    private void hienthi() {
        // TODO: Gọi BUS layer để lấy dữ liệu từ database
        model = new DefaultTableModel(column, 0);
        // Dữ liệu demo tạm thời
        Object[] row1 = {1, "Trà Đen", "kg", 50, 120000};
        Object[] row2 = {2, "Sữa Tươi", "lít", 30, 25000};
        Object[] row3 = {3, "Đường", "kg", 100, 15000};
        model.addRow(row1);
        model.addRow(row2);
        model.addRow(row3);
        tb.setModel(model);
    }

    private void them() {
        JFrame dialog = new JFrame("Thêm nguyên liệu");
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setSize(450, 300);
        dialog.setLocationRelativeTo(null);
        
        JPanel p = new JPanel(new GridLayout(5, 2, 10, 10));
        p.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        p.add(new JLabel("Tên Nguyên Liệu:"));
        txtTenNL = new JTextField();
        p.add(txtTenNL);
        
        p.add(new JLabel("Đơn Vị Tính:"));
        txtDonViTinh = new JTextField();
        p.add(txtDonViTinh);
        
        p.add(new JLabel("Số Lượng:"));
        txtSoLuong = new JTextField();
        p.add(txtSoLuong);
        
        p.add(new JLabel("Giá Nhập:"));
        txtGiaNhap = new JTextField();
        p.add(txtGiaNhap);
        
        p.add(new JLabel("Ghi Chú:"));
        JTextArea noteArea = new JTextArea();
        noteArea.setLineWrap(true);
        noteArea.setWrapStyleWord(true);
        p.add(new JScrollPane(noteArea));
        
        JPanel pBtn = new JPanel();
        JButton btnThemOK = new JButton("Thêm");
        btnThemOK.addActionListener(e1 -> {
            if (validateInput()) {
                // TODO: Gọi BUS để thêm nguyên liệu
                JOptionPane.showMessageDialog(null, "Thêm nguyên liệu thành công");
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
        // TODO: Gọi BUS để xóa nguyên liệu
        JOptionPane.showMessageDialog(null, "Xóa nguyên liệu thành công");
        hienthi();
    }

    private void sua() {
        int selectedRow = tb.getSelectedRow();
        if (selectedRow != -1) {
            String maNL = model.getValueAt(selectedRow, 0).toString();
            String tenNL = model.getValueAt(selectedRow, 1).toString();
            String donViTinh = model.getValueAt(selectedRow, 2).toString();
            String soLuong = model.getValueAt(selectedRow, 3).toString();
            String giaNhap = model.getValueAt(selectedRow, 4).toString();

            JFrame dialog = new JFrame("Sửa nguyên liệu");
            dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dialog.setSize(450, 320);
            dialog.setLocationRelativeTo(null);
            
            JPanel p = new JPanel(new GridLayout(5, 2, 10, 10));
            p.setBorder(new EmptyBorder(20, 20, 20, 20));
            
            p.add(new JLabel("Mã NL:"));
            txtMaNL = new JTextField(maNL);
            txtMaNL.setEditable(false);
            p.add(txtMaNL);
            
            p.add(new JLabel("Tên NL:"));
            txtTenNL = new JTextField(tenNL);
            p.add(txtTenNL);
            
            p.add(new JLabel("Đơn Vị Tính:"));
            txtDonViTinh = new JTextField(donViTinh);
            p.add(txtDonViTinh);
            
            p.add(new JLabel("Số Lượng:"));
            txtSoLuong = new JTextField(soLuong);
            p.add(txtSoLuong);
            
            p.add(new JLabel("Giá Nhập:"));
            txtGiaNhap = new JTextField(giaNhap);
            p.add(txtGiaNhap);
            
            JPanel pBtn = new JPanel();
            JButton btnSaveOK = new JButton("Lưu");
            btnSaveOK.addActionListener(e1 -> {
                if (validateInput()) {
                    // TODO: Gọi BUS để cập nhật nguyên liệu
                    JOptionPane.showMessageDialog(null, "Cập nhật nguyên liệu thành công");
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
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nguyên liệu cần sửa");
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
            } else {
                // Tìm kiếm theo từ khóa
                for (int i = 0; i < model.getRowCount(); i++) {
                    String cellValue = model.getValueAt(i, getColumnIndex(keyword)).toString();
                    if (cellValue.toLowerCase().contains(value.toLowerCase())) {
                        Object[] row = {
                            model.getValueAt(i, 0),
                            model.getValueAt(i, 1),
                            model.getValueAt(i, 2),
                            model.getValueAt(i, 3),
                            model.getValueAt(i, 4)
                        };
                        model.addRow(row);
                    }
                }
            }
        }
    }

    private int getColumnIndex(String keyword) {
        switch (keyword) {
            case "Mã NL": return 0;
            case "Tên NL": return 1;
            case "Đơn Vị Tính": return 2;
            case "Số Lượng": return 3;
            case "Giá Nhập": return 4;
            default: return -1;
        }
    }

    private boolean validateInput() {
        if (txtTenNL.getText().trim().isEmpty() || 
            txtDonViTinh.getText().trim().isEmpty() ||
            txtSoLuong.getText().trim().isEmpty() ||
            txtGiaNhap.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
            return false;
        }
        try {
            Integer.parseInt(txtSoLuong.getText());
            Double.parseDouble(txtGiaNhap.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Số lượng và giá nhập phải là số");
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
        String[] list = {"Tất cả", "Mã NL", "Tên NL", "Đơn Vị Tính", "Số Lượng", "Giá Nhập"};
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
                int confirm = JOptionPane.showConfirmDialog(null, "Xóa nguyên liệu này?");
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

        nhap = new JButton("Nhập");
        nhap.setPreferredSize(new Dimension(120, 50));
        nhap.setEnabled(false);

        xuat = new JButton("Xuất");
        xuat.setPreferredSize(new Dimension(120, 50));
        xuat.setEnabled(false);

        bot.add(them);
        bot.add(xoa);
        bot.add(sua);
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
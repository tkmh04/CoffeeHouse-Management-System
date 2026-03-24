/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
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
 */
public class NhanVien extends JPanel {
    private NhanVienBUS nvBUS = new NhanVienBUS();
    private JPanel top, bot, center;
    private JButton them, xoa, sua, nhap, xuat;
    private JLabel search;
    private JTextField txt;
    private String valueToSearch = "";
    private String selectedBox = "";
    private JComboBox comboBox;
    private JTextField txtMaNv, txtTenNv, txtDateOfBirth, txtSexual, txtSdt, txtDiaChi;
    private JTable tb;
    private DefaultTableModel model;
    private String[] column = {"Mã NV", "Tên NV", "Ngày sinh", "Giới tính", "Số ĐT", "Địa chỉ"};

    public NhanVien() {
        initComponent();
    }

    private void hienthi() {
        ArrayList<NhanVienDTO> dsNhanVien = nvBUS.layDanhSachNhanVien();
        model = new DefaultTableModel(column, 0);
        if (dsNhanVien != null) {
            for (NhanVienDTO nv : dsNhanVien) {
                Object[] row = {nv.getIdNhanVien(), nv.getNameNhanVien(), nv.getDateOfBirth(), nv.getSexual(), nv.getPhoneNumber(), nv.getAddress()};
                model.addRow(row);
            }
        }
        tb.setModel(model);
    }

    private void them() {
        JFrame dialog = new JFrame("Thêm nhân viên");
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setSize(450, 300);
        dialog.setLocationRelativeTo(null);
        
        JPanel p = new JPanel(new GridLayout(6, 2, 10, 10));
        p.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        p.add(new JLabel("Tên NV:"));
        txtTenNv = new JTextField();
        p.add(txtTenNv);
        
        p.add(new JLabel("Ngày sinh:"));
        txtDateOfBirth = new JTextField();
        p.add(txtDateOfBirth);
        
        p.add(new JLabel("Giới tính:"));
        txtSexual = new JTextField();
        p.add(txtSexual);
        
        p.add(new JLabel("Số ĐT:"));
        txtSdt = new JTextField();
        p.add(txtSdt);
        
        p.add(new JLabel("Địa chỉ:"));
        txtDiaChi = new JTextField();
        p.add(txtDiaChi);
        
        p.add(new JLabel("Ghi chú:"));
        JTextArea noteArea = new JTextArea();
        noteArea.setLineWrap(true);
        noteArea.setWrapStyleWord(true);
        p.add(new JScrollPane(noteArea));
        
        JPanel pBtn = new JPanel();
        JButton btnThemOK = new JButton("Thêm");
        btnThemOK.addActionListener(e1 -> {
            String ten = txtTenNv.getText().trim();
            String dateOfBirth = txtDateOfBirth.getText().trim();
            String sexual = txtSexual.getText().trim();
            String sdt = txtSdt.getText().trim();
            String diachi = txtDiaChi.getText().trim();

            if (ten.isEmpty() || dateOfBirth.isEmpty() || sdt.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
                return;
            }

            if (nvBUS.themNhanVien(ten, dateOfBirth, sexual, sdt, diachi)) {
                JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công");
                hienthi();
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại");
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
        int id = Integer.parseInt(idStr);
        if (nvBUS.xoaNhanVien(id)) {
            JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công");
            hienthi();
        } else {
            JOptionPane.showMessageDialog(null, "Xóa nhân viên thất bại");
        }
    }

    private void sua() {
        int selectedRow = tb.getSelectedRow();
        if (selectedRow != -1) {
            String maNv = model.getValueAt(selectedRow, 0).toString();
            String ten = model.getValueAt(selectedRow, 1).toString();
            String dateOfBirth = model.getValueAt(selectedRow, 2).toString();
            String sexual = model.getValueAt(selectedRow, 3).toString();
            String sdt = model.getValueAt(selectedRow, 4).toString();
            String diaChi = model.getValueAt(selectedRow, 5).toString();

            JFrame dialog = new JFrame("Sửa nhân viên");
            dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dialog.setSize(450, 320);
            dialog.setLocationRelativeTo(null);
            
            JPanel p = new JPanel(new GridLayout(6, 2, 10, 10));
            p.setBorder(new EmptyBorder(20, 20, 20, 20));
            
            p.add(new JLabel("Mã NV:"));
            JTextField txtMaEdit = new JTextField(maNv);
            txtMaEdit.setEditable(false);
            p.add(txtMaEdit);
            
            p.add(new JLabel("Tên NV:"));
            JTextField txtTenEdit = new JTextField(ten);
            p.add(txtTenEdit);
            
            p.add(new JLabel("Ngày sinh:"));
            JTextField txtDateEdit = new JTextField(dateOfBirth);
            p.add(txtDateEdit);
            
            p.add(new JLabel("Giới tính:"));
            JTextField txtSexualEdit = new JTextField(sexual);
            p.add(txtSexualEdit);
            
            p.add(new JLabel("Số ĐT:"));
            JTextField txtSdtEdit = new JTextField(sdt);
            p.add(txtSdtEdit);
            
            p.add(new JLabel("Địa chỉ:"));
            JTextField txtDiaChiEdit = new JTextField(diaChi);
            p.add(txtDiaChiEdit);
            
            JPanel pBtn = new JPanel();
            JButton btnSaveOK = new JButton("Lưu");
            btnSaveOK.addActionListener(e1 -> {
                String newTen = txtTenEdit.getText().trim();
                String newDateOfBirth = txtDateEdit.getText().trim();
                String newSexual = txtSexualEdit.getText().trim();
                String newSdt = txtSdtEdit.getText().trim();
                String newDiaChi = txtDiaChiEdit.getText().trim();

                if (newTen.isEmpty() || newDateOfBirth.isEmpty() || newSdt.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
                    return;
                }

                try {
                    int id = Integer.parseInt(maNv);
                    if (nvBUS.suaNhanVien(id, newTen, newDateOfBirth, newSexual, newSdt, newDiaChi)) {
                        JOptionPane.showMessageDialog(null, "Cập nhật nhân viên thành công");
                        hienthi();
                        dialog.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật nhân viên thất bại");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ");
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
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần sửa");
        }
    }

    private void timkiem(String keyword, String value) {
        ArrayList<NhanVienDTO> ketQua = nvBUS.timKiemNhanVien(keyword, value);
        model.setRowCount(0);
        if (ketQua != null) {
            for (NhanVienDTO nv : ketQua) {
                Object[] row = {nv.getIdNhanVien(), nv.getNameNhanVien(), nv.getDateOfBirth(), nv.getSexual(), nv.getPhoneNumber(), nv.getAddress()};
                model.addRow(row);
            }
        }
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
        String[] list = {"Tất cả", "Mã NV", "Tên NV", "Ngày sinh", "Giới tính", "Số ĐT", "Địa chỉ"};
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
                int confirm = JOptionPane.showConfirmDialog(null, "Xóa nhân viên này?");
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

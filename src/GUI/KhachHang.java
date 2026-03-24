package GUI;

import BUS.KhachHangBUS;
import DTO.KhachHangDTO;
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

public class KhachHang extends JPanel {
    private KhachHangBUS khBUS = new KhachHangBUS();
    private JPanel top, bot, center;
    private JButton them, xoa, sua, nhap, xuat;
    private JLabel search;
    private JTextField txt;
    private String valueToSearch = "";
    private String selectedBox = "";
    private JComboBox comboBox;
    private JTextField txtMaKh, txtTenKh, txtSDT, txtDiaChi, txtLoaiThanhVien;
    private JTable tb;
    private DefaultTableModel model;
    private String[] column = {"Mã KH", "Tên KH", "Số ĐT", "Địa chỉ", "Loại thành viên"};

    public KhachHang() {
        initComponent();
    }

    private void hienthi() {
        ArrayList<KhachHangDTO> dsKhachHang = khBUS.layDanhSachKhachHang();
        model = new DefaultTableModel(column, 0);
        if (dsKhachHang != null) {
            for (KhachHangDTO kh : dsKhachHang) {
                Object[] row = {
                    kh.getMaKhachHang(),
                    kh.getTenKhachHang(),
                    kh.getsDT(),
                    kh.getDiaChi(),
                    kh.getLoaiThanhVien()
                };
                model.addRow(row);
            }
        }
        tb.setModel(model);
    }

    private void them() {
        String ten = txtTenKh.getText().trim();
        String sdt = txtSDT.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        String loai = txtLoaiThanhVien.getText().trim();

        if (khBUS.themKhachHang(ten, sdt, diaChi, loai)) {
            hienthi();
            txtTenKh.setText("");
            txtSDT.setText("");
            txtDiaChi.setText("");
            txtLoaiThanhVien.setText("");
        }
    }

    private void xoa(int rowIndex) {
        String idStr = model.getValueAt(rowIndex, 0).toString();
        int id = Integer.parseInt(idStr);
        if (khBUS.xoaKhachHang(id)) {
            hienthi();
        }
    }

    private void sua() {
        int selectedRow = tb.getSelectedRow();
        if (selectedRow != -1) {
            String makh = txtMaKh.getText().trim();
            String ten = txtTenKh.getText().trim();
            String sdt = txtSDT.getText().trim();
            String diaChi = txtDiaChi.getText().trim();
            String loai = txtLoaiThanhVien.getText().trim();

            if (makh.isEmpty() || ten.isEmpty() || sdt.isEmpty() || diaChi.isEmpty() || loai.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
                return;
            }

            int id = Integer.parseInt(makh);
            if (khBUS.suaKhachHang(id, ten, sdt, diaChi, loai)) {
                hienthi();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần sửa");
        }
    }

    private void timkiem(String keyword, String value) {
        ArrayList<KhachHangDTO> ketQua = khBUS.timKiemKhachHang(keyword, value);
        model.setRowCount(0);
        if (ketQua != null) {
            for (KhachHangDTO kh : ketQua) {
                Object[] row = {
                    kh.getMaKhachHang(),
                    kh.getTenKhachHang(),
                    kh.getsDT(),
                    kh.getDiaChi(),
                    kh.getLoaiThanhVien()
                };
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
        String[] list = {"Tất cả", "Mã KH", "Tên KH", "Số ĐT", "Địa chỉ", "Loại thành viên"};
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
        them.addActionListener(e -> {
            JFrame dialog = new JFrame("Thêm khách hàng");
            dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dialog.setSize(400, 250);
            dialog.setLocationRelativeTo(null);
            
            JPanel p = new JPanel(new GridLayout(4, 2, 10, 10));
            p.setBorder(new EmptyBorder(20, 20, 20, 20));
            
            p.add(new JLabel("Tên KH:"));
            txtTenKh = new JTextField();
            p.add(txtTenKh);
            
            p.add(new JLabel("Số ĐT:"));
            txtSDT = new JTextField();
            p.add(txtSDT);
            
            p.add(new JLabel("Địa chỉ:"));
            txtDiaChi = new JTextField();
            p.add(txtDiaChi);
            
            p.add(new JLabel("Loại thành viên:"));
            txtLoaiThanhVien = new JTextField();
            p.add(txtLoaiThanhVien);
            
            JPanel pBtn = new JPanel();
            JButton btnThemOK = new JButton("Thêm");
            btnThemOK.addActionListener(e1 -> {
                them();
                dialog.dispose();
            });
            pBtn.add(btnThemOK);
            
            JButton btnCancel = new JButton("Hủy");
            btnCancel.addActionListener(e1 -> dialog.dispose());
            pBtn.add(btnCancel);
            
            dialog.setLayout(new BorderLayout());
            dialog.add(p, BorderLayout.CENTER);
            dialog.add(pBtn, BorderLayout.SOUTH);
            dialog.setVisible(true);
        });

        xoa = new JButton("Xóa");
        xoa.setPreferredSize(new Dimension(120, 50));
        xoa.setBackground(new Color(255, 100, 100));
        xoa.setForeground(Color.WHITE);
        xoa.setFont(new Font("Arial", Font.BOLD, 14));
        xoa.addActionListener(e -> {
            int selectedRow = tb.getSelectedRow();
            if (selectedRow != -1) {
                int confirm = JOptionPane.showConfirmDialog(null, "Xóa khách hàng này?");
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
        sua.addActionListener(e -> {
            int selectedRow = tb.getSelectedRow();
            if (selectedRow != -1) {
                String makh = model.getValueAt(selectedRow, 0).toString();
                String ten = model.getValueAt(selectedRow, 1).toString();
                String sdt = model.getValueAt(selectedRow, 2).toString();
                String diaChi = model.getValueAt(selectedRow, 3).toString();
                String loai = model.getValueAt(selectedRow, 4).toString();

                JFrame dialog = new JFrame("Sửa khách hàng");
                dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dialog.setSize(400, 280);
                dialog.setLocationRelativeTo(null);
                
                JPanel p = new JPanel(new GridLayout(5, 2, 10, 10));
                p.setBorder(new EmptyBorder(20, 20, 20, 20));
                
                p.add(new JLabel("Mã KH:"));
                txtMaKh = new JTextField(makh);
                txtMaKh.setEditable(false);
                p.add(txtMaKh);
                
                p.add(new JLabel("Tên KH:"));
                txtTenKh = new JTextField(ten);
                p.add(txtTenKh);
                
                p.add(new JLabel("Số ĐT:"));
                txtSDT = new JTextField(sdt);
                p.add(txtSDT);
                
                p.add(new JLabel("Địa chỉ:"));
                txtDiaChi = new JTextField(diaChi);
                p.add(txtDiaChi);
                
                p.add(new JLabel("Loại thành viên:"));
                txtLoaiThanhVien = new JTextField(loai);
                p.add(txtLoaiThanhVien);
                
                JPanel pBtn = new JPanel();
                JButton btnSaveOK = new JButton("Lưu");
                btnSaveOK.addActionListener(e1 -> {
                    sua();
                    dialog.dispose();
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
                JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần sửa");
            }
        });

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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.PhanQuyenBUS;
import DAO.ChiTietQuyenDAO;
import DTO.ChiTietQuyenDTO;
import DTO.PhanQuyenDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Admin
 */


public class PhanQuyenGUI extends JPanel{
    JPanel top, bot, center;
    private PhanQuyenBUS pqBUS = new PhanQuyenBUS();
    private ChiTietQuyenDAO chitietquyenDAO = new ChiTietQuyenDAO();
    JButton them, xoa, sua, nhap, xuat;
    JLabel search;
    JTextField txt;
    JLabel idmq, idtq, ten, pw, quyen, tt;
    JTextField txtmq, txttq, txtten, txtpw, txtquyen, txttt;
    public static final int width = 1138, height = 699;
    final Color Click = new Color(250, 50, 100);
    
    private JTable tb;
    ////////

    List<JCheckBox> allCheckboxesThem = new ArrayList<>();
    List<JCheckBox> allCheckboxesSua = new ArrayList<>();
//    static int idcount = 1;
    
//    static int currentMaQuyen = 1;
    String[] tenCacHang = {"Nhân viên", "Khách hàng", "Phiếu nhập", "Phiếu xuất", "Tài khoản", "Phân quyền", "Thống kê", "Sản phẩm"};
    ////////
    String[] cb = {"Xem", "Thêm", "Xoá", "Sửa" ,"Tìm kiếm"};
    String column[] = {"IdQuyen", "Tên Quyền"};
    ArrayList<Object[]> data = new ArrayList<>();
    
    DefaultTableModel model;

    private int idQuyen;
    
    public PhanQuyenGUI(int idQuyen){
        this.idQuyen = idQuyen;
        initComponent();
    }
    
    public void hienthi() {
    ArrayList<PhanQuyenDTO> dsPhanQuyen = pqBUS.layDanhSachQuyen();
        model = new DefaultTableModel(column, 0);
        for (PhanQuyenDTO taiKhoan : dsPhanQuyen) {
            Object[] row = {
                    taiKhoan.getIdQuyen(),
                    taiKhoan.getTen(),
            };
            model.addRow(row);
        }
        tb.setModel(model);
}
    
public void them() {
    String smq = txtmq.getText();
    String sten = txttq.getText();
    
    // Kiểm tra xem id quyền và tên quyền có được nhập đầy đủ không
    if (smq.isEmpty() || sten.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
        return;
    }
    
    // Lặp qua tất cả các checkbox
    for (int i = 0; i < allCheckboxesThem.size(); i++) {
        JCheckBox checkbox = allCheckboxesThem.get(i);
        if (checkbox.isSelected()) {
            // Tính toán mã chức năng và mã hàng tương ứng với checkbox
            int hang = i / 5;
            int maChucNang = hang + 1;
            String chucNang = checkbox.getText();
            
            // Thực hiện thêm phân quyền và chi tiết quyền vào cơ sở dữ liệu
            if (!pqBUS.themChiTietQuyen(Integer.parseInt(smq), maChucNang, chucNang)) {
                JOptionPane.showMessageDialog(null, "Thêm chi tiết quyền thất bại");
                return; // Thoát khỏi phương thức nếu thêm không thành công
            }
        }
    }
    
    // Thêm quyền vào cơ sở dữ liệu
    if (pqBUS.themPhanQuyen(Integer.parseInt(smq), sten)) {
        Object[] newRow = {smq, sten};
        model.addRow(newRow);
        JOptionPane.showMessageDialog(null, "Thêm quyền thành công");
    } else {
        JOptionPane.showMessageDialog(null, "Thêm quyền thất bại");
    }
}


    public void xoa(int rowIndex) {
        String idStr = model.getValueAt(rowIndex, 0).toString();
        int id = Integer.parseInt(idStr);
        boolean flag = (pqBUS.xoaPhanQuyen(id));
        if (flag) {
            JOptionPane.showMessageDialog(null, "Xoá thành công");
            model.removeRow(rowIndex);
        } else {
            JOptionPane.showMessageDialog(null, "Xoá thất bại");
        }
}
    
public void sua(int selectedRow) {
    String smq = txtmq.getText();
    String sten = txttq.getText();
    
    // Kiểm tra xem id quyền và tên quyền có được nhập đầy đủ không
    if (smq.isEmpty() || sten.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
        return;
    }
    
    // Cập nhật quyền vào cơ sở dữ liệu
    if (pqBUS.capNhatPhanQuyen(Integer.parseInt(smq), sten)) {
        model.setValueAt(sten, selectedRow, 1); // Cập nhật tên quyền

        // Xóa tất cả các chi tiết quyền của quyền này trong cơ sở dữ liệu trước khi thêm lại
        if (pqBUS.xoaChiTietPhanQuyen(Integer.parseInt(smq))) {
            // Lặp qua tất cả các checkbox để kiểm tra xem các chức năng nào đã được chọn
            for (int i = 0; i < allCheckboxesSua.size(); i++) {
                JCheckBox checkbox = allCheckboxesSua.get(i);
                if (checkbox.isSelected()) {
                    // Tính toán mã chức năng và mã hàng tương ứng với checkbox
                    int hang = i / 5;
                    int maChucNang = hang + 1;
                    String chucNang = checkbox.getText();

                    // Thực hiện thêm chi tiết quyền vào cơ sở dữ liệu
                    if (!pqBUS.themChiTietQuyen(Integer.parseInt(smq), maChucNang, chucNang)) {
                        JOptionPane.showMessageDialog(null, "Thêm chi tiết quyền thất bại");
                        return; // Thoát khỏi phương thức nếu thêm không thành công
                    }
                }
            }
            
            // Xóa tất cả các phần tử trong danh sách allCheckboxesSua
            allCheckboxesSua.clear();
            
            // Hiển thị cửa sổ thông báo khi cập nhật thành công
            JOptionPane.showMessageDialog(null, "Cập nhật chi tiết quyền thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Xóa chi tiết quyền thất bại");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Cập nhật chi tiết quyền thất bại");
    }
}


    public void timkiem(String keyword) {
        // Gọi phương thức tìm kiếm từ BUS
        ArrayList<PhanQuyenDTO> ketQua = pqBUS.timKiemTatCa(keyword);
        model.setRowCount(0); // Xóa dữ liệu cũ
        for (PhanQuyenDTO phanQuyen : ketQua) {
            Object[] row = {
                    phanQuyen.getIdQuyen(),
                    phanQuyen.getTen(),
            };
            model.addRow(row);
        }
    }

    
    void initComponent()  {
        
        
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        
        top = new JPanel();
        top.setBackground(Color.WHITE);
        top.setPreferredSize(new Dimension(width, 70));
        BoxLayout boxlayout = new BoxLayout(top, BoxLayout.X_AXIS);
        top.setLayout(boxlayout);
        top.setBorder(new EmptyBorder(new Insets(20, 150, 20, 150)));

        bot = new JPanel();
        bot.setPreferredSize(new Dimension(width,60));
        bot.setBackground(Color.WHITE);
        
        center = new JPanel();
        center.setLayout(new BorderLayout());  
        center.setPreferredSize(new Dimension(width,600));
        center.setLayout(new BorderLayout());
        
        add(top, BorderLayout.NORTH);
        add(bot, BorderLayout.SOUTH);
        add(center, BorderLayout.CENTER);
        
        ImageIcon icona = new ImageIcon("img/add.png");
        them = new JButton("Thêm", icona);
        them.setBackground(Color.WHITE);
        them.setForeground(Color.PINK);
        them.setBorder(null);
        them.setFont(new Font("Arial", Font.PLAIN, 20));
        them.setFocusable(false);
        them.setPreferredSize(new Dimension(100, 50));
        them.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame csthem = new JFrame();

                csthem.setLayout(new BorderLayout());
                JPanel topframe = new JPanel();
                
                csthem.setTitle("Thêm");
                csthem.setSize(400, 300);
                
                idmq = new JLabel("Id quyền");
                idtq = new JLabel("Tên quyền");

                txtmq = new JTextField(10);
                txttq = new JTextField(10);

                topframe.add(idmq);
                topframe.add(txtmq);
                topframe.add(idtq);
                topframe.add(txttq);

                csthem.add(topframe, BorderLayout.NORTH);
                
                JPanel centerframe = new JPanel();
                centerframe.setLayout(new GridLayout(8,1));
                                
            for (int i = 0; i < 8; i++) {
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
                TitledBorder border = BorderFactory.createTitledBorder(tenCacHang[i]);
                panel.setBorder(border);

                for (int j = 0; j < 5; j++) {
                    JCheckBox checkbox = new JCheckBox(cb[j]);
                    allCheckboxesThem.add(checkbox); 
                    panel.add(checkbox);
                }

            centerframe.add(panel);
        }
                
                csthem.add(centerframe, BorderLayout.CENTER);
                csthem.pack();
                csthem.setLocationRelativeTo(null);

                csthem.setResizable(false);
       
                
                JPanel panel1 = new JPanel();
                JButton button = new JButton("Thêm");
                button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                // Gọi phương thức thêm sinh viên
                    them();
        }
    });
                panel1.add(button);
                csthem.add(panel1, BorderLayout.SOUTH);
            csthem.setVisible(true);
            }
        });


        ImageIcon iconm = new ImageIcon("img/minus.png");
        xoa = new JButton("Xoá",iconm);
        xoa.setBackground(Color.WHITE);
        xoa.setForeground(Color.pink);
        xoa.setBorder(null);
        xoa.setFont(new Font("Arial", Font.PLAIN, 20));
        xoa.setFocusable(false);
        xoa.setPreferredSize(new Dimension(100, 50));
        xoa.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {

                int selectedRow = tb.getSelectedRow();
                if (selectedRow != -1) {
                    int sc = JOptionPane.showConfirmDialog(null, "Xoá tài khoản?");
                    if (sc == JOptionPane.YES_OPTION) {
                        xoa(selectedRow);
                    } else if (sc == JOptionPane.NO_OPTION) {
                        JOptionPane.showMessageDialog(null, "Đã Huỷ Xoá Tài Khoản");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn hàng cần xoá");
                
            }
    }
});


        ImageIcon icone = new ImageIcon("img/edit.png");
        sua = new JButton("Sửa",icone);
        sua.setBackground(Color.WHITE);
        sua.setForeground(Color.pink);
        sua.setBorder(null);
        sua.setFont(new Font("Arial", Font.PLAIN, 20));
        sua.setFocusable(false);
        sua.setPreferredSize(new Dimension(100, 50));
        sua.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = tb.getSelectedRow();
        if (selectedRow != -1) {
            String smq = (model.getValueAt(selectedRow, 0) != null) ? model.getValueAt(selectedRow, 0).toString() : "";
            String stq = (model.getValueAt(selectedRow, 1) != null) ? model.getValueAt(selectedRow, 1).toString() : "";

            JFrame csthem = new JFrame();
            csthem.setTitle("Sửa");
            csthem.setSize(400, 300);
            csthem.setLayout(new BorderLayout());
            
            JPanel topframe = new JPanel();
            idmq = new JLabel("Id quyền");
            idtq = new JLabel("Tên quyền");
            txtmq = new JTextField(smq, 10);
            txttq = new JTextField(stq, 10);
            topframe.add(idmq);
            topframe.add(txtmq);
            topframe.add(idtq);
            topframe.add(txttq);
            csthem.add(topframe, BorderLayout.NORTH);

            JPanel centerframe = new JPanel();
            centerframe.setLayout(new GridLayout(8, 1));

            // Lấy danh sách các chi tiết quyền của quyền được chọn
            ArrayList<ChiTietQuyenDTO> chiTietQuyen = pqBUS.laydanhsachchitietquyen(Integer.parseInt(smq));
            // Load dữ liệu từ chiTietQuyen và đánh dấu các checkbox tương ứng đã được chọn

            
            for (int i = 0; i < 8; i++) {
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
                TitledBorder border = BorderFactory.createTitledBorder(tenCacHang[i]);
                panel.setBorder(border);

                for (int j = 0; j < 5; j++) {
                    int maChucNang = i + 1; // Mã chức năng được tính từ chỉ số của panel
                    String chucNang = cb[j];
                    
                    boolean isSelected = false;
                    
                    // Kiểm tra xem chức năng này đã được lưu trong cơ sở dữ liệu hay không
                    for (ChiTietQuyenDTO ctq : chiTietQuyen) {
                        if (ctq.getmaCn() == maChucNang && ctq.gethangDong().equals(chucNang)) {
                            isSelected = true;
                            break; // Nếu đã tìm thấy, không cần kiểm tra các checkbox khác
                        }
                    }
                    
                    JCheckBox checkbox = new JCheckBox(chucNang);
                    allCheckboxesSua.add(checkbox);

                    checkbox.setSelected(isSelected);
                    panel.add(checkbox);
                }

                centerframe.add(panel);
            }

            csthem.add(centerframe, BorderLayout.CENTER);
            csthem.pack();
            csthem.setResizable(false);
            csthem.setLocationRelativeTo(null);
            

            JPanel panel1 = new JPanel();
            JButton button = new JButton("Lưu");
            button.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        
        sua(selectedRow);
        
    }
});
            panel1.add(button);
            csthem.add(panel1, BorderLayout.SOUTH);
            csthem.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn quyền cần sửa");
        }
    }
});

        ImageIcon iconi = new ImageIcon("img/excel.png");
        nhap = new JButton("Nhập",iconi);
        nhap.setBackground(Color.WHITE);
        nhap.setForeground(Color.pink);
        nhap.setBorder(null);
        nhap.setFont(new Font("Arial", Font.PLAIN, 20));
        nhap.setFocusable(false);
        nhap.setPreferredSize(new Dimension(100, 50));
        nhap.setEnabled(false);
        
        ImageIcon iconex = new ImageIcon("img/export.png");
        xuat = new JButton("Xuất",iconex);
        xuat.setBackground(Color.WHITE);
        xuat.setForeground(Color.pink);
        xuat.setBorder(null);
        xuat.setFont(new Font("Arial", Font.PLAIN, 20));
        xuat.setFocusable(false);
        xuat.setPreferredSize(new Dimension(100, 50));
        
        search = new JLabel("Tìm Kiếm");
        search.setPreferredSize(new Dimension(80, 20));
        txt = new JTextField();
        txt.addKeyListener(new KeyAdapter() {
    @Override
    public void keyReleased(KeyEvent e) {
        String keyword = txt.getText().trim();
        timkiem(keyword);
    }
});


// Thiết lập enable
        them.setEnabled(pqBUS.kiemTraHanhDongTonTai("Thêm",6,idQuyen));
        xoa.setEnabled(pqBUS.kiemTraHanhDongTonTai("Xoá",6,idQuyen));
        sua.setEnabled(pqBUS.kiemTraHanhDongTonTai("Sửa",6,idQuyen));
        txt.setEnabled(pqBUS.kiemTraHanhDongTonTai("Tìm kiếm",6,idQuyen));
        
        bot.add(them);
        bot.add(xoa);
        bot.add(sua);
        bot.add(nhap);
        bot.add(xuat);
        
        top.add(search);
        top.add(txt);
        
        tb = new JTable();
        hienthi(); 

        JScrollPane sp = new JScrollPane(tb);
        center.add(sp,BorderLayout.CENTER);
        
        Font font1 = new Font("Segoe UI",Font.BOLD,13);
        tb.setFocusable(false);
        tb.setIntercellSpacing(new Dimension(0,0));     
        tb.getTableHeader().setFont(font1);
        tb.setRowHeight(30);
        tb.setShowVerticalLines(false);              
        tb.getTableHeader().setOpaque(false);
        tb.setFillsViewportHeight(true);
        tb.getTableHeader().setBackground(Click);
        tb.getTableHeader().setForeground(Color.WHITE);



        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tb.getModel());
        tb.setRowSorter(sorter);
        
        // Đặt màu nền cho các hàng được chọn
        tb.setSelectionBackground(Color.PINK);
        
        // Đặt màu chữ cho các hàng được chọn
        tb.setSelectionForeground(Color.WHITE);
        
        // Đặt căn giữa cho văn bản trong các ô
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tb.getColumnCount(); i++) {
            tb.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
    }
}
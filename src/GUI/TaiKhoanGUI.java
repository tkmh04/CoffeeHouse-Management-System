package GUI;

import BUS.PhanQuyenBUS;
import BUS.TaiKhoanBUS;
import DTO.TaiKhoanDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class TaiKhoanGUI extends JPanel {
    private TaiKhoanBUS tkBUS = new TaiKhoanBUS();
    private PhanQuyenBUS pqBUS = new PhanQuyenBUS();

    JPanel top, bot, center;
    PhanQuyenGUI phanQuyen;
    JButton them, xoa, sua, nhap, xuat;
    JLabel search;
    JTextField txt;
    private String valueToSearch = "";
    private String selectedBox = "";
    JComboBox comboBox;
    
    JLabel idtk, idnv, ten, pw, quyen, tt;
    public static final int width = 1138, height = 699;
    JTextField txttk, txtnv, txtten, txtpw, txtquyen, txttt;
    JComboBox<String> cboQuyen;
    JTable tb;

    String column[] = {"IdTaiKhoan", "Id Nhân Viên", "Tên", "Password", "Id Quyền", "Trạng Thái"};
    ArrayList<Object[]> data = new ArrayList<>();
    DefaultTableModel model;

    public TaiKhoanGUI() {
        initComponent();
    }

    public void hienthi() {
        ArrayList<TaiKhoanDTO> dsTaiKhoan = tkBUS.layDanhSachTaiKhoan();
        model = new DefaultTableModel(column, 0);
        for (TaiKhoanDTO taiKhoan : dsTaiKhoan) {
            Object[] row = {
                    taiKhoan.getIdTaiKhoan(),
                    taiKhoan.getIdNhanVien(),
                    taiKhoan.getTen(),
                    taiKhoan.getPassword(),
                    taiKhoan.getIdQuyen(),
                    taiKhoan.getStatus(),
            };
            model.addRow(row);
        }
        tb.setModel(model);
    }

    public void them() {
        String stk = txttk.getText();
        int gtk = Integer.parseInt(stk);
        String snv = txtnv.getText();
        int gnv = Integer.parseInt(snv);
        String sten = txtten.getText();
        String spassword = txtpw.getText();
        String squyen = txtquyen.getText();
        int gquyen = Integer.parseInt(squyen);
        String stt = txttt.getText();
        int gtt = Integer.parseInt(stt);

        if (tkBUS.themTaiKhoan(gtk, gnv, sten, spassword, gquyen, gtt)) {
            JOptionPane.showMessageDialog(null, "Thêm tài khoản thành công");
            Object[] newRow = {stk, snv, sten, spassword, squyen, stt};
            model.addRow(newRow);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm tài khoản thất bại");
        }
    }

    public void xoa(int rowIndex) {
        String idStr = model.getValueAt(rowIndex, 0).toString();
        int id = Integer.parseInt(idStr);
        boolean flag = tkBUS.xoaTaiKhoan(id);
        if (flag) {
            JOptionPane.showMessageDialog(null, "Xoá thành công");
            model.removeRow(rowIndex);
        } else {
            JOptionPane.showMessageDialog(null, "Xoá tài khoản thất bại");
        }
    }

    public void sua() {
        int selectedRow = tb.getSelectedRow();
        if (selectedRow != -1) {
            String newtk = txttk.getText();
            String newnv = txtnv.getText();
            String newten = txtten.getText();
            String newpw = txtpw.getText();
            String newquyen = txtquyen.getText();
            String newtt = txttt.getText();

            if (newtk.isEmpty() || newnv.isEmpty() || newten.isEmpty() || newpw.isEmpty() || newquyen.isEmpty() || newtt.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
                return;
            }

            int gtk = Integer.parseInt(newtk);
            int gnv = Integer.parseInt(newnv);
            int gquyen = Integer.parseInt(newquyen);
            int gtt = Integer.parseInt(newtt);

            if (tkBUS.capNhatTaiKhoan(gtk, gnv, newten, newpw, gquyen, gtt)) {
                JOptionPane.showMessageDialog(null, "Sửa tài khoản thành công");
                model.setValueAt(newtk, selectedRow, 0);
                model.setValueAt(newnv, selectedRow, 1);
                model.setValueAt(newten, selectedRow, 2);
                model.setValueAt(newpw, selectedRow, 3);
                model.setValueAt(newquyen, selectedRow, 4);
                model.setValueAt(newtt, selectedRow, 5);
            } else {
                JOptionPane.showMessageDialog(null, "Sửa tài khoản thất bại");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản cần sửa");
        }
    }

    public void timkiem(String keyword, String value) {
        // Gọi phương thức tìm kiếm từ BUS
        ArrayList<TaiKhoanDTO> ketQua = tkBUS.timKiemTaiKhoan(keyword, value);
        model.setRowCount(0); // Xóa dữ liệu cũ
        for (TaiKhoanDTO taiKhoan : ketQua) {
            Object[] row = {
                    taiKhoan.getIdTaiKhoan(),
                    taiKhoan.getIdNhanVien(),
                    taiKhoan.getTen(),
                    taiKhoan.getPassword(),
                    taiKhoan.getIdQuyen(),
                    taiKhoan.getStatus(),
            };
            model.addRow(row);
        }
    }

    public void initComponent() {
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());

        top = new JPanel();
        top.setBackground(Color.WHITE);
        top.setPreferredSize(new Dimension(width, 70));
        BoxLayout boxlayout = new BoxLayout(top, BoxLayout.X_AXIS);
        top.setLayout(boxlayout);
        top.setBorder(new EmptyBorder(new Insets(20, 150, 20, 150)));

        bot = new JPanel();
        bot.setPreferredSize(new Dimension(1138, 60));
        bot.setBackground(Color.WHITE);

        center = new JPanel();
        center.setPreferredSize(new Dimension(1138, 600));
        center.setLayout(new BorderLayout());

        this.add(top, BorderLayout.NORTH);
        this.add(bot, BorderLayout.SOUTH);
        this.add(center, BorderLayout.CENTER);

        ImageIcon icona = new ImageIcon("img/add.png");
        them = new JButton("Thêm", icona);
        them.setBackground(Color.WHITE);
        them.setForeground(Color.PINK);
        them.setBorder(null);
        them.setFont(new Font("Arial", Font.PLAIN, 20));
        them.setFocusable(false);
        them.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame csthem = new JFrame();
                csthem.setTitle("Thêm");
                csthem.setResizable(false);
//                csthem.pack();
                csthem.setSize(400, 300);
                csthem.setLocationRelativeTo(null);
                csthem.setLayout(new BoxLayout(csthem.getContentPane(), BoxLayout.Y_AXIS));
                JPanel panel = new JPanel(new GridLayout(6, 2));

                idtk = new JLabel("Id Tài Khoản");
                idnv = new JLabel("Id Nhân Viên");
                ten = new JLabel("Tên");
                pw = new JLabel("Password");
                quyen = new JLabel("Quyền");
                tt = new JLabel("Trạng Thái");

                txttk = new JTextField(30);  // Khởi tạo và gán giá trị
                txtnv = new JTextField(30);
                txtten = new JTextField(30);
                txtpw = new JTextField(30);
                txtquyen = new JTextField(30);
                txttt = new JTextField(30);

                panel.add(idtk);
                panel.add(txttk);
                panel.add(idnv);
                panel.add(txtnv);
                panel.add(ten);
                panel.add(txtten);
                panel.add(pw);
                panel.add(txtpw);
                panel.add(quyen);
                
                cboQuyen = new JComboBox<>();
                ArrayList<Integer> danhSachIdQuyen = pqBUS.layIdQuyen();
                for (Integer idQuyen : danhSachIdQuyen) {
                cboQuyen.addItem(String.valueOf(idQuyen));
                }
                panel.add(cboQuyen);

                panel.add(tt);
                panel.add(txttt);


                csthem.add(panel);

                JPanel panel1 = new JPanel();
                JButton button = new JButton("Thêm");
                button.setPreferredSize(new Dimension(100, 50));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        them();
                        csthem.dispose(); // Đóng frame sau khi thêm
                    }
                });
                panel1.add(button);

                csthem.add(panel1);
                csthem.setVisible(true);
            }
        });

        ImageIcon iconm = new ImageIcon("img/minus.png");
        xoa = new JButton("Xoá", iconm);
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
        sua = new JButton("Sửa", icone);
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
                    String stk = model.getValueAt(selectedRow, 0).toString();
                    String snv = model.getValueAt(selectedRow, 1).toString();
                    String sten = model.getValueAt(selectedRow, 2).toString();
                    String spassword = model.getValueAt(selectedRow, 3).toString();
                    String squyen = model.getValueAt(selectedRow, 4).toString();
                    String stt = model.getValueAt(selectedRow, 5).toString();

                    JFrame editFrame = new JFrame("Chỉnh sửa thông tin tài khoản");
                    editFrame.setLayout(new BoxLayout(editFrame.getContentPane(), BoxLayout.Y_AXIS));
                    JPanel panel1 = new JPanel();
                    panel1.setLayout(new GridLayout(6, 2));

                    JLabel lbtk = new JLabel("Id Tài Khoản");
                    txttk = new JTextField(stk);
                    txttk.setEditable(false);

                    JLabel lbnv = new JLabel("Id Nhân Viên");
                    txtnv = new JTextField(snv);

                    JLabel lbten = new JLabel("Tên");
                    txtten = new JTextField(sten);

                    JLabel lbpw = new JLabel("Password");
                    txtpw = new JTextField(spassword);

                    JLabel lbquyen = new JLabel("Id Quyền");
                    txtquyen = new JTextField(squyen);

                    JLabel lbtt = new JLabel("Trạng Thái");
                    txttt = new JTextField(stt);

                    JButton btnSave = new JButton("Lưu");
                    btnSave.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            sua();
                            editFrame.dispose(); // Đóng frame sau khi sửa
                        }
                    });

                    panel1.add(lbtk);
                    panel1.add(txttk);
                    panel1.add(lbnv);
                    panel1.add(txtnv);
                    panel1.add(lbten);
                    panel1.add(txtten);
                    panel1.add(lbpw);
                    panel1.add(txtpw);
                    panel1.add(lbquyen);
                    panel1.add(txtquyen);
                    panel1.add(lbtt);
                    panel1.add(txttt);

                    JPanel panel3 = new JPanel();
                    panel3.add(btnSave);

                    editFrame.add(panel1);
                    editFrame.add(panel3);
//                    editFrame.pack();
                    editFrame.setSize(400, 300);
                    editFrame.setLocationRelativeTo(null);
                    editFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản cần sửa");
                }
            }
        });

        ImageIcon iconi = new ImageIcon("img/excel.png");
        nhap = new JButton("Nhập", iconi);
        nhap.setBackground(Color.WHITE);
        nhap.setForeground(Color.pink);
        nhap.setBorder(null);
        nhap.setFont(new Font("Arial", Font.PLAIN, 20));
        nhap.setFocusable(false);
        nhap.setPreferredSize(new Dimension(100, 50));
        nhap.setEnabled(false);

        ImageIcon iconex = new ImageIcon("img/export.png");
        xuat = new JButton("Xuất", iconex);
        xuat.setBackground(Color.WHITE);
        xuat.setForeground(Color.pink);
        xuat.setBorder(null);
        xuat.setFont(new Font("Arial", Font.PLAIN, 20));
        xuat.setFocusable(false);
        xuat.setPreferredSize(new Dimension(100, 50));

        search = new JLabel("Tìm Kiếm");
        search.setPreferredSize(new Dimension(80, 20));
        

txt = new JTextField();
String[] list = {"Tất cả", "idTaiKhoan", "idNhanVien", "Tên tài khoản", "idQuyen"};
comboBox = new JComboBox(list);

txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                // Cập nhật giá trị của valueToSearch mỗi khi có sự kiện phím được thả ra
                valueToSearch = txt.getText().trim();
                
                // Gọi phương thức tìm kiếm
                timkiem(selectedBox, valueToSearch);
            }
        });

        // Sự kiện khi người dùng thay đổi lựa chọn trong JComboBox
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cập nhật giá trị của selectedBox mỗi khi có sự kiện thay đổi trong JComboBox
                selectedBox = comboBox.getSelectedItem().toString();
                
                // Gọi phương thức tìm kiếm
                timkiem(selectedBox, valueToSearch);
            }
        });

        
        bot.add(them);
        bot.add(xoa);
        bot.add(sua);
        bot.add(nhap);
        bot.add(xuat);

        top.add(comboBox);
        top.add(search);
        top.add(txt);
        

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

    }
}

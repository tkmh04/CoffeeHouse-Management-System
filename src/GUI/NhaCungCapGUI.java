/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.NhaCungCapBUS;
import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import GUI.Custom.PanelBorderRadius;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.awt.GridLayout;

public final class NhaCungCapGUI extends JPanel implements ActionListener{
    private JToolBar chucnang;
    private JComboBox<String> cbxLuaChon;
    private PanelBorderRadius pntren,pnduoi;
    private JTable tableNcc;
    private JScrollPane scrollTableNcc;
    private JTextField txtSearchForm;
    private DefaultTableModel tblModel;
    private JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter,timkiem,pnbtn;
    private JButton btnSua,btnXoa,btnThem,btnReset,exportExcel,importExcel;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    public NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    public ArrayList<NhaCungCapDTO> listncc =nccBUS.getAll();
    Color BackgroundColor = new Color(255, 224, 230);

    private void initNcc() {
        //table            
        tableNcc = new JTable();
        scrollTableNcc = new JScrollPane(); 
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã NCC", "Tên nhà cung cấp", "Địa chỉ", "Số điện thoại"};
        tblModel.setColumnIdentifiers(header);
        tableNcc.setModel(tblModel);
        tableNcc.setFocusable(false);
        scrollTableNcc.setViewportView(tableNcc);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = tableNcc.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(1);
        columnModel.getColumn(1).setPreferredWidth(300);
        columnModel.getColumn(2).setPreferredWidth(350);
        columnModel.getColumn(3).setPreferredWidth(2);

        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(10, 10));
        this.add(contentCenter, BorderLayout.CENTER);

        pntren = new PanelBorderRadius();
        pntren.setLayout(new GridLayout(1,2,0,0)); // Sử dụng FlowLayout và thiết lập căn chỉnh sang trái
        pntren.setBorder(new EmptyBorder(10,10,10,10));       

        pnlBorder1 = new JPanel();
        pnlBorder1.setPreferredSize(new Dimension(0, 10));
        pnlBorder1.setBackground(BackgroundColor);
        this.add(pnlBorder1, BorderLayout.NORTH);

        pnlBorder2 = new JPanel();
        pnlBorder2.setPreferredSize(new Dimension(0, 10));
        pnlBorder2.setBackground(BackgroundColor);
        this.add(pnlBorder2, BorderLayout.SOUTH);

        pnlBorder3 = new JPanel();
        pnlBorder3.setPreferredSize(new Dimension(10, 0));
        pnlBorder3.setBackground(BackgroundColor);
        this.add(pnlBorder3, BorderLayout.EAST);

        pnlBorder4 = new JPanel();
        pnlBorder4.setPreferredSize(new Dimension(10, 0));
        pnlBorder4.setBackground(BackgroundColor);
        this.add(pnlBorder4, BorderLayout.WEST);
        //Cac Button
        chucnang = new JToolBar();
        chucnang.setBackground(new Color(255, 255, 255));
        chucnang.setLayout(new GridLayout(1, 6,0, 0));
        chucnang.setBorder(BorderFactory.createTitledBorder("Chức năng"));
        chucnang.setRollover(true);
        chucnang.setPreferredSize(new Dimension(390, 90));

        ImageIcon addimg = new ImageIcon(getClass().getResource("/img/add.png"));
        btnThem= new JButton("Thêm",addimg);
        btnThem.setCursor(new java.awt.Cursor(Cursor.DEFAULT_CURSOR));
        btnThem.setFocusable(false);
        btnThem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThem.addActionListener(this);

        ImageIcon editimg = new ImageIcon(getClass().getResource("/img/edit.png"));
        btnSua = new JButton("Sửa",editimg);
        btnSua.setFocusable(false);
        btnSua.setHorizontalTextPosition(SwingConstants.CENTER);
        btnSua.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnSua.setActionCommand("sua");
        btnSua.addActionListener(this);

        ImageIcon deleteimg = new ImageIcon(getClass().getResource("/img/delete.png"));
        btnXoa = new JButton("Xóa",deleteimg);
        btnXoa.setFocusable(false);
        btnXoa.setCursor(new Cursor(Cursor.HAND_CURSOR));btnXoa.setFocusable(false);
        btnXoa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnXoa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);  
        btnXoa.addActionListener(this); 

        chucnang.add(btnThem);
        chucnang.add(btnSua);                
        chucnang.add(btnXoa);
        
        pntren.add(chucnang);

        exportExcel =new JButton();
        exportExcel.setFont(new Font("SF Pro Display", 0, 15));
        exportExcel.setIcon(new ImageIcon(getClass().getResource("/img/xuatexcel.png"))); // NOI18N
        exportExcel.setText("Xuất Excel");
        exportExcel.setHorizontalTextPosition(SwingConstants.CENTER);
        exportExcel.setVerticalTextPosition(SwingConstants.BOTTOM);
//        exportExcel.addActionListener(new ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                exportExcelActionPerformed(evt);
//            }
//        });
        chucnang.add(exportExcel);

        importExcel=new JButton();
        importExcel.setFont(new java.awt.Font("SF Pro Display", 0, 15)); // NOI18N
        importExcel.setIcon(new ImageIcon(getClass().getResource("/img/nhapexcel.png"))); // NOI18N
        importExcel.setText("Nhập Excel");
        importExcel.setFocusable(false);
        importExcel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        importExcel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
//        importExcel.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                importExcelActionPerformed(evt);
//            }
//        });
        chucnang.add(importExcel);
        //Tìm kiếm
        timkiem = new JPanel();
        timkiem.setBackground(new Color(255, 255, 255));
        timkiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        timkiem.setLayout(new FlowLayout(FlowLayout.CENTER));
        timkiem.setPreferredSize(new Dimension(760, 90));
        
        cbxLuaChon = new JComboBox<>();
        cbxLuaChon.setFont(new Font("SF Pro Display", 0, 15)); // NOI18N
        cbxLuaChon.setModel(new DefaultComboBoxModel<>(new String[] { "Tất cả", "Mã nhà cung cấp", "Tên nhà cung cấp", "Số điện thoại", "Địa chỉ" }));
        txtSearchForm = new JTextField();
        txtSearchForm.setFont(new Font("SF Pro Display", 0, 15));
        txtSearchForm.setPreferredSize(new Dimension(200,35));
        txtSearchForm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                txtSearchFormActionPerformed(evt);
            }
        });
        txtSearchForm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchFormKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchFormKeyReleased(evt);
            }
        });
        btnReset = new javax.swing.JButton();
        btnReset.setFont(new java.awt.Font("SF Pro Display", 0, 15)); // NOI18N
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reset.png"))); // NOI18N
        btnReset.setText("Làm mới");
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnResetActionPerformed(evt);
            }   
        });
        timkiem.setVisible(true);
        timkiem.add(cbxLuaChon);
        timkiem.add(txtSearchForm);
        timkiem.add(btnReset);
        pntren.add(chucnang,BorderLayout.WEST);
        pntren.add(timkiem,BorderLayout.CENTER);
        pnduoi = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(pnduoi, BoxLayout.Y_AXIS);
        pnduoi.setLayout(boxly);
        contentCenter.add(pntren, BorderLayout.NORTH);
        contentCenter.add(pnduoi, BorderLayout.CENTER);
        pnduoi.add(scrollTableNcc);

    }
    public NhaCungCapGUI() {
        initNcc();
        tableNcc.setDefaultEditor(Object.class, null);
        loadDataToTable(listncc);
    }
    public void loadDataToTable(ArrayList<NhaCungCapDTO> result) {
        tblModel.setRowCount(0);
        for (NhaCungCapDTO ncc : result) {
            tblModel.addRow(new Object[]{
                ncc.getIdNcc(), ncc.getTenNcc(), ncc.getDiaChi(), ncc.getSdtNcc()
            });
        }
    }
    public NhaCungCapDTO getNhaCungCapSelect() {
        int i_row = tableNcc.getSelectedRow();
        NhaCungCapDTO ncc = NhaCungCapDAO.getInstance().selectAll().get(i_row);
        return ncc;
    }

//    private void exportExcelActionPerformed(java.awt.event.ActionEvent evt) {                                            
//        // TODO add your handling code here:
//        try {
//            JFileChooser jFileChooser = new JFileChooser();
//            jFileChooser.showSaveDialog(this);
//            File saveFile = jFileChooser.getSelectedFile();
//            if (saveFile != null) {
//                saveFile = new File(saveFile.toString() + ".xlsx");
//                Workbook wb = new XSSFWorkbook();
//                Sheet sheet = wb.createSheet("NhaCungCap");
//
//                Row rowCol = sheet.createRow(0);
//                for (int i = 0; i < tableNcc.getColumnCount(); i++) {
//                    Cell cell = rowCol.createCell(i);
//                    cell.setCellValue(tableNcc.getColumnName(i));
//                }
//
//                for (int j = 0; j < tableNcc.getRowCount(); j++) {
//                    Row row = sheet.createRow(j + 1);
//                    for (int k = 0; k < tableNcc.getColumnCount(); k++) {
//                        Cell cell = row.createCell(k);
//                        if (tableNcc.getValueAt(j, k) != null) {
//                            cell.setCellValue(tableNcc.getValueAt(j, k).toString());
//                        }
//
//                    }
//                }
//                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
//                wb.write(out);
//                wb.close();
//                out.close();
//                openFile(saveFile.toString());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }  
//    private void importExcelActionPerformed(ActionEvent evt) {                                            
//        // TODO add your handling code here:
//        //Import excel
//        File excelFile;
//        FileInputStream excelFIS = null;
//        BufferedInputStream excelBIS = null;
//        XSSFWorkbook excelJTableImport = null;
//        ArrayList<NhaCungCapDTO> listAccExcel = new ArrayList<NhaCungCapDTO>();
//
//        // Tạo và cấu hình JFileChooser
//        JFileChooser jf = new JFileChooser();
//        jf.setDialogTitle("Chọn file Excel");
//        int result = jf.showOpenDialog(null);
//
//        if (result == JFileChooser.APPROVE_OPTION) {
//            try {
//                // Lấy tệp được chọn
//                excelFile = jf.getSelectedFile();
//                excelFIS = new FileInputStream(excelFile);
//                excelBIS = new BufferedInputStream(excelFIS);
//                excelJTableImport = new XSSFWorkbook(excelBIS);
//                XSSFSheet excelSheet = excelJTableImport.getSheetAt(0);
//
//                // Đọc dữ liệu từ file Excel và thêm vào danh sách
//                for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
//                    XSSFRow excelRow = excelSheet.getRow(row);
//                    int idNcc = (int) excelRow.getCell(0).getNumericCellValue();
//                    String tenNcc = excelRow.getCell(1).getStringCellValue();
//                    String sdtNcc = excelRow.getCell(2).getStringCellValue();
//                    String diaChi = excelRow.getCell(3).getStringCellValue();
//                    NhaCungCapDTO acc = new NhaCungCapDTO(idNcc, tenNcc, sdtNcc, diaChi);
//                    listAccExcel.add(acc);
//                }
//
//                // Hiển thị dữ liệu trong bảng
//                DefaultTableModel table_acc = (DefaultTableModel) tableNcc.getModel();
//                table_acc.setRowCount(0);
//                loadDataToTable(listAccExcel);
//
//                // Thêm dữ liệu vào cơ sở dữ liệu
//                int k = 0;
//                for (NhaCungCapDTO nhaCungCap : listAccExcel) {
//                    k = NhaCungCappDAO.getInstance().ADD(nhaCungCap);
//                }
//                if (k != 0) {
//                    JOptionPane.showMessageDialog(this, "Import thành công !");
//                }
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(NhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IOException ex) {
//                Logger.getLogger(NhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
//            } finally {
//                try {
//                    if (excelBIS != null) {
//                        excelBIS.close();
//                    }
//                    if (excelFIS != null) {
//                        excelFIS.close();
//                    }
//                } catch (IOException e) {
//                    Logger.getLogger(NhaCungCap.class.getName()).log(Level.SEVERE, null, e);
//                }
//            }
//        }
//    } 
    private void btnResetActionPerformed(ActionEvent evt) {
        txtSearchForm.setText("");
        cbxLuaChon.setSelectedIndex(0);
        loadDataToTable(NhaCungCapDAO.getInstance().selectAll());
    } 
    private void txtSearchFormKeyPressed(java.awt.event.KeyEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void txtSearchFormActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }   
    private void txtSearchFormKeyReleased(java.awt.event.KeyEvent evt) { 
        String luachon = (String) cbxLuaChon.getSelectedItem();
        String searchContent = txtSearchForm.getText();
        ArrayList<NhaCungCapDTO> result = new ArrayList<>();
        switch (luachon) {
            case "Tất cả" -> result = NhaCungCapBUS.getInstance().searchTatCa(searchContent);
            case "Mã nhà cung cấp" -> result = NhaCungCapBUS.getInstance().searchIdNCC(searchContent);
            case "Tên nhà cung cấp" -> result = NhaCungCapBUS.getInstance().searchTenNCC(searchContent);
            case "Địa chỉ" -> result = NhaCungCapBUS.getInstance().searchDiaChi(searchContent);
            case "Số điện thoại" -> result = NhaCungCapBUS.getInstance().searchSdt(searchContent);
        }
        loadDataToTable(result);
    }
    private void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public int getRowSelected() {
        int index = tableNcc.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp");
        }
        return index;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnThem)) {
            themNhaCungCap themNcc = new themNhaCungCap(this, owner, true);
            themNcc.setVisible(true);
        }
        if (e.getSource().equals(btnSua)) {
            int index = getRowSelected();
            suaNhaCungCap suaNcc = new suaNhaCungCap(this, owner,true, listncc.get(index));
            suaNcc.setVisible(true);
        }
        if (e.getSource().equals(btnXoa)) {
            if (tableNcc.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp muốn xoá");
            } else {
                int output = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá nhà cung cấp", "Xác nhận xoá nhà cung cấp", JOptionPane.YES_NO_OPTION);
                    if (output == JOptionPane.YES_OPTION) {
                        NhaCungCapDTO selectedNcc = getNhaCungCapSelect();
                        NhaCungCapDAO.getInstance().DELETE(selectedNcc);
                        JOptionPane.showMessageDialog(this, "Xóa thành công !");
                        loadDataToTable(NhaCungCapDAO.getInstance().selectAll());
                }
            }
        }        
    }
}
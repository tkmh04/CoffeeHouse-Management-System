/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DTO.NhaCungCapDTO;
import GUI.NhaCungCapGUI;
import GUI.Custom.InputForm;
import helper.Validation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JDialog;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Admin
 */
public class suaNhaCungCap extends JDialog implements ActionListener {
    private JButton btLuu, bthuy;
    private JLabel themNcc;
    private InputForm tenNcc;
    private InputForm diachi;
    private InputForm sodienthoai;
    private JPanel pnTuade, pnGiua,pnDuoi;
    private NhaCungCapGUI jpNcc;
    private NhaCungCapDTO nccDTO;

    public suaNhaCungCap(NhaCungCapGUI jpNcc, JFrame owner, boolean modal) {
        super(owner ,modal);
        this.jpNcc = jpNcc;
        initComponents();    
        setLocationRelativeTo(owner); 
    }
    public suaNhaCungCap(NhaCungCapGUI jpNcc, JFrame owner, boolean modal, NhaCungCapDTO nccdto) {
        super(owner, modal);
        this.jpNcc = jpNcc;
        this.nccDTO = nccdto;
        initComponents();
        initInfo();
    }
    public void initInfo() {
    if (nccDTO != null) {
        tenNcc.setText(nccDTO.getTenNcc());
        diachi.setText(nccDTO.getDiaChi());
        sodienthoai.setText(nccDTO.getSdtNcc());
        }
    }
    private void initComponents() {
        this.setSize(new Dimension(390, 468));
        this.setLayout(new BorderLayout(0, 0));
        pnTuade =new JPanel();
        themNcc = new JLabel();
        pnTuade.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnTuade.setBackground(new Color(250, 10, 100));
        themNcc.setText("Thêm nhà cung cấp");
        themNcc.setFont(new Font("SF Pro Display", 1, 24));
        pnTuade.add(themNcc);
        add(pnTuade, BorderLayout.NORTH);
        
        pnGiua =new JPanel();
        pnGiua.setLayout(new GridLayout(3, 1, 10, 5));
        pnGiua.setBackground(Color.WHITE);
        tenNcc = new InputForm("Tên nhà cung cấp");
        diachi = new InputForm("Địa chỉ");
        sodienthoai = new InputForm("Số điện thoại");
        PlainDocument phonex = (PlainDocument)sodienthoai.getTxtForm().getDocument();
        
        pnGiua.add(tenNcc);
        pnGiua.add(diachi);
        pnGiua.add(sodienthoai);
//        txtTenNhaCungCap = new JTextField(20);
//        txtTenNhaCungCap.setFont(new Font("SF Pro Display", 0, 14));
//        txtTenNhaCungCap.setSize(68,31);
//        lbTenNcc = new JLabel();
//        lbTenNcc.setText("Tên nhà cung cấp");
//        lbTenNcc.setFont(new Font("SF Pro Display", 0, 16));
//        pnGiua.add(lbTenNcc);
//        pnGiua.add(txtTenNhaCungCap);
//        
//        lbDiaChi = new JLabel();
//        lbDiaChi.setText("Địa chỉ");
//        lbDiaChi.setFont(new Font("SF Pro Display", 0, 16));
//        txtDiaChi = new JTextField(20);
//        txtDiaChi.setFont(new Font("SF Pro Display", 0, 14));
//        txtDiaChi.setSize(68,31);
//        pnGiua.add(lbDiaChi);
//        pnGiua.add(txtDiaChi);
//        
//        lbSdt = new JLabel();
//        lbSdt.setText("Số điện thoại");
//        lbSdt.setFont(new Font("SF Pro Display", 0, 16));
//        txtphone = new JTextField(20);
//        txtphone.setFont(new Font("SF Pro Display", 0, 14));
//        txtphone.setSize(68,31);
//        pnGiua.add(lbSdt);
//        pnGiua.add(txtphone);
        
        pnGiua.setVisible(true);
        this.add(pnGiua, BorderLayout.CENTER);

        // Panel 3
        pnDuoi = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnDuoi.setBackground(Color.WHITE);
        
        btLuu = new JButton();
        btLuu.setBackground(new Color(250, 10, 100));
        btLuu.setFont(new Font("SF Pro Display", 0, 16));
        btLuu.setForeground(new Color(255, 255, 255));
        btLuu.setText("Lưu");
        btLuu.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btLuu.addActionListener(this);
        
        bthuy = new JButton();
        bthuy.setText("Hủy");
        bthuy.setBackground(new Color(250, 10, 100));
        bthuy.setFont(new java.awt.Font("SF Pro Display", 0, 16)); 
        bthuy.setForeground(new Color(255, 255, 255));
        bthuy.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        bthuy.addActionListener(this);
        

        pnDuoi.add(btLuu);
        pnDuoi.add(bthuy);
        this.add(pnDuoi, BorderLayout.SOUTH);
    }
    public boolean Validation(){
         if (Validation.isEmpty(tenNcc.getText())) {
            JOptionPane.showMessageDialog(this, "Tên nhà cung cấp không được rỗng", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
         }
         else  if (Validation.isEmpty(diachi.getText())) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được rỗng", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
         }
         
         else if (Validation.isEmpty(sodienthoai.getText()) || !Validation.isNumber(sodienthoai.getText()) && sodienthoai.getText().length()!=10) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được rỗng và phải là 10 ký tự số", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
         }
          return true;
    }
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btLuu && Validation()) {
            jpNcc.nccBUS.update(new NhaCungCapDTO(nccDTO.getIdNcc(), tenNcc.getText(), diachi.getText(), sodienthoai.getText()));
            jpNcc.loadDataToTable(jpNcc.listncc);
            dispose();       
        } else if (evt.getSource() == bthuy) {
            dispose();
        }
    }
}

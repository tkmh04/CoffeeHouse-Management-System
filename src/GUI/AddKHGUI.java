/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.KhachHangBUS;
import BUS.NguyenlieuBUS;
import DAO.KhachHangDAO;
import DAO.NguyenlieuDAO;
import DTO.KhachHangDTO;
import DTO.NguyenlieuDTO;
import GUI.Component.ConfirmDialog;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author asus
 */
public class AddKHGUI extends JFrame {
    private JPanel pnName, pnUnit, pnQuant, Wrapper, pnButton,pnRank;
    private JLabel lblName, lblUnit, lblQuant, lblRank;
    private JTextField tfName, tfUnit, tfQuant, tfRank;
    private JButton btnConfirm, btnExit;
    private KhachHangDAO khDAO=new KhachHangDAO();
    private KhachHangBUS khBUS = new KhachHangBUS();
    public AddKHGUI() {
        this.setSize(new Dimension(400, 300));
        this.setTitle("Thêm khách hàng thành viên");
        Wrapper = new JPanel(new GridLayout(5, 1));

        pnName = new JPanel(new GridBagLayout());
        tfName = new JTextField();
        tfName.setPreferredSize(new Dimension(70,35));
        lblName = new JLabel("Tên khách hàng: ");
        lblName.setPreferredSize(new Dimension( 100,30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        pnName.add(lblName, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pnName.add(tfName, gbc);

        pnUnit = new JPanel(new GridBagLayout());
        tfUnit = new JTextField(20);
        tfUnit.setPreferredSize(new Dimension(70,35));
        lblUnit = new JLabel("Số điện thoại: ");
        lblUnit.setPreferredSize(new Dimension( 100,30));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        pnUnit.add(lblUnit, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pnUnit.add(tfUnit, gbc);

        pnQuant = new JPanel(new GridBagLayout());
        tfQuant = new JTextField(20);
        tfQuant.setPreferredSize(new Dimension(70,35));
        lblQuant = new JLabel("Địa chỉ: ");
        lblQuant.setPreferredSize(new Dimension( 100,30));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        pnQuant.add(lblQuant, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pnQuant.add(tfQuant, gbc);
        
        pnRank = new JPanel(new GridBagLayout());
        tfRank = new JTextField();
        tfRank.setPreferredSize(new Dimension(70,35));
        lblRank = new JLabel("Rank: ");
        lblRank.setPreferredSize(new Dimension( 100,30));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        pnRank.add(lblRank, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pnRank.add(tfRank, gbc);
        
        pnButton = new JPanel();
        btnConfirm = new JButton("Xác nhận");
        btnExit= new JButton("Hủy");
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();
            }
            
        });
        pnButton.add(btnConfirm);
        pnButton.add(btnExit);
        
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=tfName.getText();
                String sdt=tfUnit.getText();
                String diachi =tfQuant.getText();
                String rank = tfRank.getText();
                 
                KhachHangDTO updateNl = new KhachHangDTO();
                updateNl.setTenKhachHang(name);
                updateNl.setsDT(sdt);
                updateNl.setDiaChi(diachi);
                updateNl.setLoaiThanhVien(rank);
                
                
                
                
                ConfirmDialog confirmDialog = new ConfirmDialog(AddKHGUI.this, "Xác nhận thay đổi", "Bạn có chắc muốn thêm?");
                confirmDialog.setVisible(true);
                if (confirmDialog.isConfirmed())
                {
                    khDAO.ADD(updateNl);
                    JOptionPane.showMessageDialog(null, "Đã thêm thành công!");
                    dispose();
                    
                }
            }
        });
                {
                    
                }
        
        Wrapper.add(pnName);
        Wrapper.add(pnUnit);
        Wrapper.add(pnQuant);
        Wrapper.add(pnRank);
        Wrapper.add(pnButton);
        this.add(Wrapper);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

           
}
}
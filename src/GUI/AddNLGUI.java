/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.NguyenlieuBUS;
import DAO.NguyenlieuDAO;
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
public class AddNLGUI extends JFrame {
    private JPanel pnName, pnUnit, pnQuant, Wrapper, pnButton;
    private JLabel lblName, lblUnit, lblQuant;
    private JTextField tfName, tfUnit, tfQuant;
    private JButton btnConfirm, btnExit;
    private NguyenlieuDAO nlDAO=new NguyenlieuDAO();
    private NguyenlieuBUS nlBUS = new NguyenlieuBUS();
    public AddNLGUI() {
        this.setSize(new Dimension(400, 300));
        this.setTitle("Thêm nguyên liệu");
        Wrapper = new JPanel(new GridLayout(4, 1));

        pnName = new JPanel(new GridBagLayout());
        tfName = new JTextField();
        tfName.setPreferredSize(new Dimension(70,35));
        lblName = new JLabel("Tên nguyên liệu: ");
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
        lblUnit = new JLabel("Đơn vị: ");
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
        lblQuant = new JLabel("Số lượng: ");
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
                String unit=tfUnit.getText();
                double quant = Double.parseDouble(tfQuant.getText());
                 if (nlDAO.isDuplicateName(name))
                {
                  JOptionPane.showMessageDialog(null, "Tên nguyên liệu đã tồn tại");
                        return;
                }
                NguyenlieuDTO updateNl = new NguyenlieuDTO();
                updateNl.setTenNL(name);
                updateNl.setDonvi(unit);
                updateNl.setSoluong(quant);
                
                
                
                
                ConfirmDialog confirmDialog = new ConfirmDialog(AddNLGUI.this, "Xác nhận thay đổi", "Bạn có chắc muốn thêm?");
                confirmDialog.setVisible(true);
                if (confirmDialog.isConfirmed())
                {
                    nlDAO.ADD(updateNl);
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
        Wrapper.add(pnButton);
        this.add(Wrapper);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

           
}
}
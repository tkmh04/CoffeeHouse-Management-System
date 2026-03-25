/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.NguyenlieuBUS;
import DAO.NguyenlieuDAO;
import DTO.NguyenlieuDTO;
import GUI.Component.ConfirmDialog;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EditNLGUI extends JFrame {
    private JPanel pnName, pnUnit, pnQuant, Wrapper, pnButton;
    private JLabel lblName, lblUnit, lblQuant;
    private JTextField tfName, tfUnit, tfQuant;
    private JButton btnConfirm, btnExit;
    private NguyenlieuDAO nlDAO=new NguyenlieuDAO();
    private NguyenlieuBUS nlBUS = new NguyenlieuBUS();
    public EditNLGUI(int id) {
        this.setSize(new Dimension(400, 300));
        this.setTitle("Sửa thông tin nguyên liệu");
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
                NguyenlieuDTO updateNl = new NguyenlieuDTO(id,name,unit,quant);
                
                ConfirmDialog confirmDialog = new ConfirmDialog(EditNLGUI.this, "Xác nhận thay đổi", "Bạn có chắc muốn thay đổi?");
                confirmDialog.setVisible(true);
                if (confirmDialog.isConfirmed())
                {
                    nlBUS.updateById(updateNl);
                    JOptionPane.showMessageDialog(null, "Đã thay đổi thành công!");
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

    public static void main(String[] args) {
        new EditNLGUI(3);
    }
}
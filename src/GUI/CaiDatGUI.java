/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author PC
 */
import java.awt.*;
import javax.swing.*;
import java.awt.*;

public class CaiDatGUI extends JPanel {
    private JFrame f;
    private JPanel p1,p2;
    private JButton bthistory,btchangepass,btschedule;
    private JComboBox<String> comboBox;
    
    public CaiDatGUI(){
        init();
        
    }
    void init(){
       
        
        p1 = new JPanel();
        p1.setBackground(Color.ORANGE);
        p1.setPreferredSize(new Dimension(1280,70));
        
        bthistory= new JButton("Lịch sử tìm kiếm");
        ImageIcon icon1 = new ImageIcon("img/history1.jpg");
        Image newImage1 = icon1.getImage().getScaledInstance(40, 30, Image.SCALE_DEFAULT);
        bthistory.setIcon(new ImageIcon(newImage1));
        bthistory.setVerticalTextPosition(SwingConstants.BOTTOM);
        bthistory.setHorizontalTextPosition(SwingConstants.CENTER);
        bthistory.setBackground(Color.white);
        bthistory.setBorderPainted(false);
        
//        bthistory.setBounds(5,5,60,50);
//        bthistory.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                comboBox.setVisible(true); // Hiển thị JComboBox khi nút được nhấp
//            }
//        });
        
        btchangepass = new JButton("Thay đổi mật khẩu");
//        bthistory.setBounds(5,5,60,50);
        ImageIcon icon2 = new ImageIcon("img/changepass1.png");
        Image newImage2 = icon2.getImage().getScaledInstance(40, 30, Image.SCALE_DEFAULT);
        btchangepass.setIcon(new ImageIcon(newImage2));
        btchangepass.setVerticalTextPosition(SwingConstants.BOTTOM);
        btchangepass.setHorizontalTextPosition(SwingConstants.CENTER);
        btchangepass.setBackground(Color.white);
        btchangepass.setBorderPainted(false);
        
        btschedule = new JButton("Lịch làm việc ");
//        bthistory.setBounds(5,5,60,50);
        ImageIcon icon3 = new ImageIcon("img/schedule2.png");
        Image newImage3 = icon3.getImage().getScaledInstance(40, 30, Image.SCALE_DEFAULT);
        btschedule.setIcon(new ImageIcon(newImage3));
        btschedule.setVerticalTextPosition(SwingConstants.BOTTOM);
        btschedule.setHorizontalTextPosition(SwingConstants.CENTER);
        btschedule.setBackground(Color.white);
        btschedule.setBorderPainted(false);
        
        comboBox = new JComboBox<>(new String[]{"Option 1", "Option 2", "Option 3"});
        comboBox.setVisible(false); // Ẩn JComboBox ban đầu
        
        p1.add(bthistory);
        p1.add(btchangepass);
        p1.add(btschedule);
        
        p2 = new JPanel();
        p2.setBackground(Color.white);
        p2.setPreferredSize(new Dimension(1100,900));
    
        
        
        p1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        p2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        p1.setBackground(Color.white);
        p2.setBackground(new Color(250, 10, 100));
        
        add(p1,BorderLayout.NORTH);
        add(p2,BorderLayout.SOUTH);
       
        
        setVisible(true);
    }
    public static void main(String[] args) {
        new CaiDatGUI();
    }
}
    


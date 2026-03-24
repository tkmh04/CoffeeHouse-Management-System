package GUI.ThongKe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import BUS.ThongKeBUS;



public class ThongKeSP extends JPanel {
    String backPanelName;
    CardLayout cardLayout;
    JPanel cardPanel;
    JPanel top, center, center1, center2;
    JTextField texttopsp;
    JLabel title, lbtopsp;
    JButton bntop, resetButton;

    ArrayList<ArrayList<Object>> sanPhamBanChay;
    ArrayList<String[]> tongSP;
    ThongKeBUS tkeBUS = new ThongKeBUS();
    int stt;

    public ThongKeSP(String backPanelName, CardLayout cardLayout, JPanel cardPanel) {
    this.backPanelName = backPanelName;
    this.cardLayout = cardLayout;
    this.cardPanel = cardPanel;
    
    initComponent();
}


    void initComponent() {
        setLayout(new BorderLayout());
        top = new JPanel();
        center = new JPanel();
        center.setLayout(new BorderLayout());
        center1 = new JPanel();
        center1.setBackground(Color.WHITE);
        center2 = new JPanel(new BorderLayout());
        center2.setBackground(Color.WHITE);
        top.setLayout(new BorderLayout());
        top.setPreferredSize(new Dimension(getWidth(), 50));
        top.setBackground(Color.YELLOW);
        String[] columns = {"STT","Sản Phẩm", "Số Lượng Đã Bán"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        lbtopsp = new JLabel("Mời nhập Top: ");
        bntop = new JButton("Thống kê");
        texttopsp = new JTextField(10);
        resetButton = new JButton("Reset"); // Khởi tạo nút resetButton
        stt = 1; 

        tongSP = tkeBUS.tongSP(); // Assuming tkeBUS provides the data

        for (String[] sanPham : tongSP) {
            Object[] rowData = new Object[]{stt++, sanPham[0], sanPham[1]};
            model.addRow(rowData);
        }

        // Xử lý sự kiện cho nút Reset
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tongSP = tkeBUS.tongSP();
                model.setRowCount(0); // Xóa hết dữ liệu cũ trong bảng
                stt = 1;
                for (String[] sanPham : tongSP) {
                    Object[] rowData = new Object[]{stt++, sanPham[0], sanPham[1]};
                    model.addRow(rowData);
                }
                texttopsp.setText(""); // Xóa nội dung trong ô nhập liệu
            }
        });

        bntop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stt = 1;
                int limit;
                try {
                    limit = Integer.parseInt(texttopsp.getText());
                    sanPhamBanChay = tkeBUS.topSanPhamBanChay(limit);
                    
                    model.setRowCount(0);

                    for (ArrayList<Object> sanPham : sanPhamBanChay) {
                        Object[] rowData = {stt++, sanPham.get(0), sanPham.get(1)}; 
                        model.addRow(rowData);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số nguyên vào ô văn bản!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JTable table = new JTable(model);
        table.getTableHeader().setBackground(Color.yellow);
        table.setFillsViewportHeight(true);
        center1.add(bntop);
        center1.add(lbtopsp);
        center1.add(texttopsp);
        center1.add(resetButton);
        center2.add(new JScrollPane(table), BorderLayout.CENTER);
        center.add(center1, BorderLayout.NORTH);
        center.add(center2, BorderLayout.CENTER);
        
        title = new JLabel("THỐNG KÊ SẢN PHẨM");
        title.setHorizontalAlignment(JLabel.CENTER); //chiều ngang
        JButton backButton = new JButton("Quay lại");
        backButton.setBounds(0, 0, 80, 50);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, backPanelName);
            }
        });
        
        top.add(backButton);
        top.add(title, BorderLayout.CENTER);

        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
    }
}


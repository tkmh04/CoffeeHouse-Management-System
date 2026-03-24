package GUI.ThongKe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import BUS.ThongKeBUS;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;


public class ThongKe extends JPanel{
    ThongKeBUS tkeBUS = new ThongKeBUS();
    public static final int width = 1138, height = 699;
    private JPanel cardPanel;
    CardLayout cardLayout;
    JPanel top,center, cenTitle, cenMain, cenMainT, cenMainC;
    JComboBox<Integer> nam;
    JLabel title;
    JLabel lbNam;
    ThongKeDT tkdt;
    ThongKeSP tksp;
    JFreeChart chart;
    JLabel label1, label2, label3, label4;
    JButton bnupdate;
    int slkh, slsp, slnv;
    double sldt;
    public ThongKe(){
        initComponent();
    }
    
    private void updateData(int selectedYear) {
        cenMainC.removeAll();
        String[] columns = {"Quý", "Doanh thu", "Vốn", "Lợi nhuận"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        ArrayList<ArrayList<Object>> data = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            int doanhThu = tkeBUS.tinhDoanhThu(selectedYear, i);
            int von = tkeBUS.tinhVon(selectedYear, i);
            int loiNhuan = doanhThu - von;
            ArrayList<Object> row = new ArrayList<>();
            row.add("Quý " + i);
            row.add(doanhThu);
            row.add(von);
            row.add(loiNhuan);
            data.add(row);
        }
        for (ArrayList<Object> row : data) {
            model.addRow(row.toArray());
        }

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa dữ liệu
        table.setDefaultRenderer(Object.class, centerRenderer);
        table.getTableHeader().setBackground(Color.ORANGE);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < model.getRowCount(); i++) {
            String quy = (String) model.getValueAt(i, 0);
            int doanhThu = (int) model.getValueAt(i, 1);
            int von = (int) model.getValueAt(i, 2);
            int loiNhuan = (int) model.getValueAt(i, 3);
            dataset.addValue(doanhThu, "Doanh thu", quy);
            dataset.addValue(von, "Vốn", quy);
            dataset.addValue(loiNhuan, "Lợi nhuận", quy);
        }
        chart = ChartFactory.createBarChart(
                "Biểu đồ Doanh thu, Vốn, Lợi nhuận theo Quý",
                "Quý",
                "Số tiền",
                dataset
        );
        ChartPanel chartPanel = new ChartPanel(chart);
        
        // Add components to cenMainT and cenMainC

        cenMainC.add(new JScrollPane(table)); // Add updated table
        cenMainC.add(chartPanel); // Add updated chart        

        cenMain.add(cenMainC, BorderLayout.CENTER);
        cenMain.revalidate(); // Revalidate the panel to reflect changes
    }
    
    private void updateStatistics() {
        slkh = tkeBUS.demKhachHang();
        slsp = tkeBUS.demSP();
        slnv = tkeBUS.demNhanVien();
        sldt = tkeBUS.demDT();

        label1.setText(Integer.toString(slkh));
        label2.setText(Integer.toString(slsp));
        label3.setText(Integer.toString(slnv));
        label4.setText(Double.toString(sldt));
    }
    
    void initComponent(){
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        top = new JPanel();
        center = new JPanel();
        top.setPreferredSize(new Dimension(width,120));
        top.setLayout(new GridLayout(1,5));
        top.setBackground(Color.WHITE);
        center.setPreferredSize(new Dimension(width,579));
        center.setLayout(new BorderLayout());
        cenTitle = new JPanel();
        cenTitle.setBackground(Color.ORANGE);
        cenMain = new JPanel(new BorderLayout());

        cenMainT = new JPanel();
        cenMainT.setPreferredSize(new Dimension(getWidth(),80));
        cenMainT.setBackground(Color.WHITE);
        cenMainC = new JPanel();
        cenMainC.setBackground(Color.WHITE);
        
        title = new JLabel("Thống kê theo quý");
        bnupdate = new JButton("Update");
        bnupdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStatistics();
            }
        }); 
        
        center.add(cenTitle, BorderLayout.NORTH);
        center.add(cenMain, BorderLayout.CENTER);
        cenTitle.add(title);

        nam = new JComboBox<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear; i >= currentYear - 10; i--)
            nam.addItem(i);
        lbNam = new JLabel("Mời chọn năm: ");

nam.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        int selectedYear = (int) nam.getSelectedItem();
        updateData(selectedYear);
    }
});

        int selectedYear = (int) nam.getSelectedItem();
        updateData(selectedYear);
        cenMainT.add(lbNam);
        cenMainT.add(nam);
        cenMainT.add(bnupdate);
        cenMain.add(cenMainT, BorderLayout.NORTH);
        // Tạo panel 0
        JPanel tk = new JPanel();
//      tk.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 20));
//      tk.setLayout(new GridLayout(1, 4, 20, 20));
        tk.setPreferredSize(new Dimension(width,200));
        tk.setLayout(new BorderLayout());
        tk.add(top, BorderLayout.NORTH);
        tk.add(center, BorderLayout.CENTER);
        
        Font font = new Font("Segoe UI",Font.BOLD, 20);  
        
        label1 = new JLabel();
        label1.setIcon(new ImageIcon("D:/NETBEANS/JAVA/QLTiemTraSua/img/tkkh.png"));
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setVerticalAlignment(JLabel.CENTER);
        label1.setHorizontalTextPosition(SwingConstants.CENTER);
        label1.setFont(font);
          
        label2 = new JLabel();
        label2.setHorizontalTextPosition(SwingConstants.CENTER);
        label2.setIcon(new ImageIcon("D:/NETBEANS/JAVA/QLTiemTraSua/img/tksp.png"));
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setVerticalAlignment(JLabel.CENTER);
        label2.setFont(font);
        label2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "panel2");
            }
        });
        top.add(label2);
        top.add(label1);

        label3 = new JLabel();
        label3.setHorizontalAlignment(JLabel.CENTER);
        label3.setVerticalAlignment(JLabel.CENTER);
        label3.setHorizontalTextPosition(SwingConstants.CENTER);
        label3.setIcon(new ImageIcon("D:/NETBEANS/JAVA/QLTiemTraSua/img/tknv.png"));
        label3.setFont(font);
        top.add(label3);

        label4 = new JLabel();
        label4.setHorizontalAlignment(JLabel.CENTER);
        label4.setVerticalAlignment(JLabel.CENTER);
        label4.setHorizontalTextPosition(JLabel.CENTER);
        label4.setIcon(new ImageIcon("D:/NETBEANS/JAVA/QLTiemTraSua/img/tkdt.png"));
        label4.setFont(font);
//        label4.setOpaque(true);
        label4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "panel4");
            }
        });
        top.add(label4);
        JLabel label5 = new JLabel();
        label5.setHorizontalAlignment(JLabel.CENTER);
        label5.setVerticalAlignment(JLabel.CENTER);
        label5.setIcon(new ImageIcon("D:/NETBEANS/JAVA/QLTiemTraSua/img/tknx.png"));
        label5.setFont(font);
        label5.setFont(font);
        label5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame frame = new JFrame();
                frame.setLayout(new FlowLayout(FlowLayout.CENTER,200,10));
                int slpn = tkeBUS.demPN();
                JLabel pn = new JLabel("Tổng phiếu nhập: " + slpn);
                int slpx = tkeBUS.demPX();
                JLabel px = new JLabel("Tổng phiếu xuất: " + slpx);
                frame.add(pn);
                frame.add(px);
                frame.setSize(600,500);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });
        top.add(label5);
        updateStatistics();
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        
        // Tạo panel 2
        tksp = new ThongKeSP("panel0", cardLayout, cardPanel);
        // Tạo panel 4
        tkdt = new ThongKeDT("panel0", cardLayout, cardPanel);
        

        // Tạo container chứa các panel
        cardPanel.add(tk, "panel0");
        cardPanel.add(tksp, "panel2");
        cardPanel.add(tkdt, "panel4");
        
        add(cardPanel);


    }

}
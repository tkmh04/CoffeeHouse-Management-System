package GUI.ThongKe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import BUS.ThongKeBUS;
import java.util.Calendar;
import javax.swing.table.DefaultTableCellRenderer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.category.DefaultCategoryDataset;
import BUS.ThongKeBUS;
import org.jfree.chart.JFreeChart;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;

public class ThongKeDT extends JPanel {

    String backPanelName;
    CardLayout cardLayout;
    JPanel cardPanel;
    JPanel top, center, toptitle, center1, center2, center3, centertop, centertb,centerbot, tb1, tb2, tb3 , pnchart1, pnchart2, pnchart3;
    JDateChooser fromDC, toDC;
    JLabel title, lb, lbFrom, lbTo, maxmonth, maxyear;
    JButton btday, btmonth , btyear, chart1, chart2, chart3;
    int fromDay, toDay, fromMonth, toMonth, fromYear, toYear;
    int maxMonth = 0;
    int maxMonthofYear = 0;
    int maxYear = 0;
    int maxRevenueM = Integer.MIN_VALUE;
    int maxRevenueY = Integer.MIN_VALUE;
    
    JFreeChart chart;
    ThongKeBUS tkeBUS = new ThongKeBUS();

    public ThongKeDT(String backPanelName, CardLayout cardLayout, JPanel cardPanel) {
    this.backPanelName = backPanelName;
    this.cardLayout = cardLayout;
    this.cardPanel = cardPanel;
    initComponent();
}

    private void nutBieuDo1() {
    
        JFrame frame = new JFrame();
    
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = fromDay; i <= toDay; i++) {
            int doanhThu = tkeBUS.tinhDoanhThuTuNgayDenNgay(i, fromMonth, fromYear);
            int von = tkeBUS.tinhVonTuNgayDenNgay(i, fromMonth, fromYear);
            int loiNhuan = doanhThu - von;
            dataset.addValue(doanhThu, "Doanh thu", "Ngày " + i);
            dataset.addValue(von, "Vốn", "Ngày " + i);
            dataset.addValue(loiNhuan, "Lợi nhuận", "Ngày " + i);
        }

        chart = ChartFactory.createBarChart(
            "Biểu đồ Doanh thu, Vốn, Lợi nhuận theo Ngày",
            "Ngày",
            "Số tiền",
            dataset
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        frame.add(chartPanel);   
        frame.pack();
        frame.setLocationRelativeTo(null); 
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true); 
}
    private void nutBieuDo2() {
    
        JFrame frame = new JFrame();
    
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = fromMonth; i <= toMonth; i++) {
            int doanhThu = tkeBUS.tinhDoanhThuTuThangDenThang(i, fromYear);
            int von = tkeBUS.tinhVonTuThangDenThang(i, fromYear);
            int loiNhuan = doanhThu - von;
            dataset.addValue(doanhThu, "Doanh thu", "Tháng " + i);
            dataset.addValue(von, "Vốn", "Tháng " + i);
            dataset.addValue(loiNhuan, "Lợi nhuận", "Tháng " + i);
        }

        chart = ChartFactory.createBarChart(
            "Biểu đồ Doanh thu, Vốn, Lợi nhuận theo Tháng",
            "Tháng",
            "Số tiền",
            dataset
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        frame.add(chartPanel);   
        frame.pack();
        frame.setLocationRelativeTo(null); 
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true); 
}
    private void nutBieuDo3() {
    
        JFrame frame = new JFrame();
    
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = fromYear; i <= toYear; i++) {
            int doanhThu = tkeBUS.tinhDoanhThuTuNamDenNam(i);
            int von = tkeBUS.tinhVonTuNamDenNam(i);
            int loiNhuan = doanhThu - von;
            dataset.addValue(doanhThu, "Doanh thu", "Năm " + i);
            dataset.addValue(von, "Vốn", "Năm " + i);
            dataset.addValue(loiNhuan, "Lợi nhuận", "Năm " + i);
        }

        chart = ChartFactory.createBarChart(
            "Biểu đồ Doanh thu, Vốn, Lợi nhuận theo Năm",
            "Năm",
            "Số tiền",
            dataset
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        frame.add(chartPanel);   
        frame.pack();
        frame.setLocationRelativeTo(null); 
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true); 
}
    private void updateDay() {
        tb1.removeAll();
        String[] columns = {"Ngày", "Doanh thu", "Vốn", "Lợi nhuận"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        ArrayList<ArrayList<Object>> data = new ArrayList<>();
        for (int i = fromDay; i <= toDay; i++) {
            int doanhThu = tkeBUS.tinhDoanhThuTuNgayDenNgay(i, fromMonth, fromYear);
            int von = tkeBUS.tinhVonTuNgayDenNgay(i, fromMonth, fromYear);
            int loiNhuan = doanhThu - von;
            ArrayList<Object> row = new ArrayList<>();
            row.add("Ngày " + i);
            row.add(doanhThu);
            row.add(von);
            row.add(loiNhuan);
            data.add(row);
        }
        for (ArrayList<Object> row : data) {
            model.addRow(row.toArray());
        }

        JTable table1 = new JTable(model);
        table1.setFillsViewportHeight(true);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa dữ liệu
        table1.setDefaultRenderer(Object.class, centerRenderer);
        table1.getTableHeader().setBackground(Color.BLUE);
        table1.getTableHeader().setForeground(Color.WHITE);

        tb1.add(new JScrollPane(table1), BorderLayout.CENTER); // Add updated table  
        tb1.revalidate(); // Revalidate the panel to reflect changes
    }
    private void updateMonth() {
        tb2.removeAll();
        String[] columns = {"Tháng", "Doanh thu", "Vốn", "Lợi nhuận"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        ArrayList<ArrayList<Object>> data = new ArrayList<>();
        for (int i = fromMonth; i <= toMonth; i++) {
            int doanhThu = tkeBUS.tinhDoanhThuTuThangDenThang(i, fromYear);
            int von = tkeBUS.tinhVonTuThangDenThang(i, fromYear);
            int loiNhuan = doanhThu - von;
            ArrayList<Object> row = new ArrayList<>();
            row.add("Tháng " + i);
            row.add(doanhThu);
            row.add(von);
            row.add(loiNhuan);
            data.add(row);
        }
        for (ArrayList<Object> row : data) {
            model.addRow(row.toArray());
        }

        JTable table2 = new JTable(model);
        table2.setFillsViewportHeight(true);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa dữ liệu
        table2.setDefaultRenderer(Object.class, centerRenderer);
        table2.getTableHeader().setBackground(Color.BLUE);
        table2.getTableHeader().setForeground(Color.WHITE);
        tb2.add(new JScrollPane(table2), BorderLayout.CENTER); // Add updated table  
        tb2.revalidate(); // Revalidate the panel to reflect changes
    }
    private void updateYear() {
        tb3.removeAll();
        String[] columns = {"Năm", "Doanh thu", "Vốn", "Lợi nhuận"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        ArrayList<ArrayList<Object>> data = new ArrayList<>();
        for (int i = fromYear; i <= toYear; i++) {
            int doanhThu = tkeBUS.tinhDoanhThuTuNamDenNam(i);
            int von = tkeBUS.tinhVonTuNamDenNam(i);
            int loiNhuan = doanhThu - von;
            ArrayList<Object> row = new ArrayList<>();
            row.add("Năm " + i);
            row.add(doanhThu);
            row.add(von);
            row.add(loiNhuan);
            data.add(row);
        }
        for (ArrayList<Object> row : data) {
            model.addRow(row.toArray());
        }

        JTable table3 = new JTable(model);
        table3.setFillsViewportHeight(true);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa dữ liệu
        table3.setDefaultRenderer(Object.class, centerRenderer);
        table3.getTableHeader().setBackground(Color.BLUE);
        table3.getTableHeader().setForeground(Color.WHITE);
        tb3.add(new JScrollPane(table3), BorderLayout.CENTER); // Add updated table  
        tb3.revalidate(); // Revalidate the panel to reflect changes
    }
    private void thongKeCaoNhat(){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear; i >= currentYear - 10; i--){
            for (int month = 1; month <= 12; month++) {
                int revenue = tkeBUS.tinhDoanhThuTuThangDenThang(month, currentYear);
                if (revenue > maxRevenueM) {
                    maxRevenueM = revenue;
                    maxMonth = month;
                    maxMonthofYear = currentYear;
                }
            }
        }
        for (int year = currentYear; year >= currentYear - 10; year--) {
            int revenue = tkeBUS.tinhDoanhThuTuNamDenNam(year);
            if (revenue > maxRevenueY) {
                maxRevenueY = revenue;
                maxYear = year;
            }
        }
        maxyear.setText("Năm có doanh thu cao nhất là năm " + Integer.toString(maxYear) + " : " + Integer.toString(maxRevenueY));
        maxmonth.setText("Tháng có doanh thu cao nhất là tháng " + Integer.toString(maxMonth) + " năm " + Integer.toString(maxMonthofYear) + " : " + Integer.toString(maxRevenueM));
    }
       
    void initComponent() {
        ThongKeBUS tkeBUS = new ThongKeBUS();
        setLayout(new BorderLayout());
        top = new JPanel();
        center = new JPanel(new BorderLayout());
        centertop = new JPanel();
        centertop.setBackground(Color.WHITE);
        centertb = new JPanel();
        centertb.setLayout(new GridLayout(1,3));
        centerbot = new JPanel();
        centerbot.setBackground(Color.WHITE);
        centerbot.setPreferredSize(new Dimension(getWidth(),85));
        centerbot.setLayout(new FlowLayout(FlowLayout.CENTER, 70,30));
        top.setLayout(new BorderLayout());
        top.setPreferredSize(new Dimension(getWidth(), 50));
        toptitle = new JPanel(new FlowLayout(FlowLayout.CENTER,0,15));
        toptitle.setBackground(Color.BLUE);
        
        title = new JLabel("THỐNG KÊ DOANH THU");
        JButton backButton = new JButton("Quay lại");
        backButton.setBounds(0, 0, 80, 50);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, backPanelName);
            }
        });
        
        center1 = new JPanel(new BorderLayout());
        center1.setBackground(Color.WHITE);
        center2 = new JPanel(new BorderLayout());
        center2.setBackground(Color.BLUE);
        center3 = new JPanel(new BorderLayout());
        center3.setBackground(Color.WHITE);


        btday = new JButton("Thống Kê Ngày");
        btmonth = new JButton("Thống Kê Theo Tháng");
        btyear = new JButton("Thống Kê Theo Năm");
        chart1 = new JButton("Biểu Đồ");
        chart2 = new JButton("Biểu Đồ");
        chart3 = new JButton("Biểu Đồ");
        pnchart1 = new JPanel(new GridLayout(1,2));
        pnchart2 = new JPanel(new GridLayout(1,2));
        pnchart3 = new JPanel(new GridLayout(1,2));
        
        lb = new JLabel("Thống kê");
        centertop.add(lb);
        lbFrom = new JLabel("từ ngày:");
        centertop.add(lbFrom);

        fromDC = new JDateChooser();
        fromDC.setDateFormatString("dd/MM/yyyy");
        fromDC.setPreferredSize(new Dimension(110, 22)); //getHeight()componentlayout
        centertop.add(fromDC);

        lbTo = new JLabel("đến ngày:");
        centertop.add(lbTo);

        toDC = new JDateChooser();
        toDC.setDateFormatString("dd/MM/yyyy");
        toDC.setPreferredSize(new Dimension(110, 22));
        centertop.add(toDC);

        Calendar fromCalendar = Calendar.getInstance();
        Calendar toCalendar = Calendar.getInstance();
        btday.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Date fromDate = fromDC.getDate();
            Date toDate = toDC.getDate();        
            fromCalendar.setTime(fromDate);
            toCalendar.setTime(toDate);
            
            fromDay = fromCalendar.get(Calendar.DAY_OF_MONTH);
            fromMonth = fromCalendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0
            fromYear = fromCalendar.get(Calendar.YEAR);

            toDay = toCalendar.get(Calendar.DAY_OF_MONTH);
            updateDay();       
    }
});
        chart1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date fromDate = fromDC.getDate();
                Date toDate = toDC.getDate();
        
                fromCalendar.setTime(fromDate);
                fromDay = fromCalendar.get(Calendar.DAY_OF_MONTH);
                fromMonth = fromCalendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0
                fromYear = fromCalendar.get(Calendar.YEAR);

                toCalendar.setTime(toDate);
                toDay = toCalendar.get(Calendar.DAY_OF_MONTH);
                nutBieuDo1();
            }
        });
        pnchart1.add(chart1);
        pnchart1.add(btday);
        center1.add(pnchart1, BorderLayout.NORTH);
        
        btmonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date fromDate = fromDC.getDate();
                Date toDate = toDC.getDate();
        
                fromCalendar.setTime(fromDate);
                fromMonth = fromCalendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0
                fromYear = fromCalendar.get(Calendar.YEAR);

                toCalendar.setTime(toDate);
                toMonth = toCalendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0
                updateMonth();
            }
        });
        chart2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date fromDate = fromDC.getDate();
                Date toDate = toDC.getDate();
        
                fromCalendar.setTime(fromDate);

                fromMonth = fromCalendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0
                fromYear = fromCalendar.get(Calendar.YEAR);

                toCalendar.setTime(toDate);
                toMonth = toCalendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0
                nutBieuDo2();
            }
        });
        pnchart2.add(chart2);
        pnchart2.add(btmonth);
        center2.add(pnchart2, BorderLayout.NORTH);
        
        btyear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date fromDate = fromDC.getDate();
                Date toDate = toDC.getDate();
        
                fromCalendar.setTime(fromDate);
                fromYear = fromCalendar.get(Calendar.YEAR);

                toCalendar.setTime(toDate);
                toYear = toCalendar.get(Calendar.YEAR);
                updateYear();
            }
        });
        chart3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date fromDate = fromDC.getDate();
                Date toDate = toDC.getDate();
        
                fromCalendar.setTime(fromDate);

                fromYear = fromCalendar.get(Calendar.YEAR);

                toCalendar.setTime(toDate);
                
                toYear = toCalendar.get(Calendar.YEAR);
                nutBieuDo3();
            }
        });
        pnchart3.add(chart3);
        pnchart3.add(btyear);
        center3.add(pnchart3, BorderLayout.NORTH);
               
        maxyear = new JLabel();
        maxmonth = new JLabel();
        thongKeCaoNhat();
        JButton tkmax = new JButton("Thống kê cao nhất");
        tkmax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thongKeCaoNhat();
            }
        }); 
        
        centerbot.add(tkmax);
        centerbot.add(maxmonth);
        centerbot.add(maxyear);
        
        tb1 = new JPanel(new BorderLayout());

        tb2 = new JPanel(new BorderLayout());

        tb3 = new JPanel(new BorderLayout());

        center1.add(tb1, BorderLayout.CENTER);
        center2.add(tb2, BorderLayout.CENTER);
        center3.add(tb3, BorderLayout.CENTER);
        centertb.add(center1);
        centertb.add(center2);
        centertb.add(center3);
        
        center.add(centertop, BorderLayout.NORTH);
        center.add(centertb, BorderLayout.CENTER);
        center.add(centerbot, BorderLayout.SOUTH);
        
        toptitle.add(title);
        top.add(backButton);
        top.add(toptitle, BorderLayout.CENTER);
        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        
    }
}


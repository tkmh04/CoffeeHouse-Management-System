package GUI;

import BUS.NguyenlieuBUS;
import DAO.NguyenlieuDAO;
import DTO.NguyenlieuDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author asus
 */
public class NguyenlieuGUI extends JPanel {
    DefaultTableModel ModelTable;
    JTable tableNL;
    JScrollPane scrollTableNguyenlieu;
    NguyenlieuBUS nlBUS = new NguyenlieuBUS();
     
    public ArrayList<DTO.NguyenlieuDTO> listNL ;
    public ArrayList<DTO.NguyenlieuDTO> newListNL;
    JPanel buttonPanel;
    JTextField searchField;
    public NguyenlieuGUI() {
        initNguyenlieuGUI();
    }

    void initNguyenlieuGUI() {
        this.setLayout(new BorderLayout());
        JPanel buttonPanelWrapper = new JPanel(new FlowLayout());
      
        // Khởi tạo panel chứa các nút
        buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Thêm");
        JButton deleteButton = new JButton("Xóa");
        JButton editButton = new JButton("Sửa");
        JButton reloadButton = new JButton("Reload");
        searchField = new JTextField();
        

        // Đặt kích thước cho trường nhập liệu và nút tìm kiếm
        searchField.setPreferredSize(new Dimension(100, 40));

        // Thêm sự kiện cho nút tìm kiếm
      
        // Đặt kích thước cho nút
        Dimension buttonSize = new Dimension(100, 40);
        addButton.setPreferredSize(buttonSize);
        deleteButton.setPreferredSize(buttonSize);
        editButton.setPreferredSize(buttonSize);
        reloadButton.setPreferredSize(buttonSize);
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.add(new JLabel("Tìm kiếm:"));
        searchPanel.add(searchField);
   
        searchField.getDocument().addDocumentListener(new DocumentListener() {
    @Override
    public void insertUpdate(DocumentEvent e) {
        performSearch();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        performSearch();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        performSearch();
    }

    private void performSearch() {
        String keyword = searchField.getText();
        search(keyword);
    }
});
        // Thêm panel tìm kiếm vào phía trên của giao diện

        // Thêm các nút vào panel
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);
        buttonPanel.add(reloadButton);
        buttonPanel.add(searchPanel);
        buttonPanel.setPreferredSize(new Dimension(700,80));
        buttonPanelWrapper.add(buttonPanel);
        buttonPanelWrapper.setPreferredSize(new Dimension(700, 80));
        buttonPanelWrapper.setBorder(BorderFactory.createLineBorder(new Color(255,230,230)));
        buttonPanel.setOpaque(true);
        // Xử lý sự kiện khi nhấn các nút
        addButton.addActionListener(e -> {
            new AddNLGUI();
        });

        deleteButton.addActionListener(e -> {
            
           int selectedRow = tableNL.getSelectedRow();
            if(selectedRow!=-1)
            {
                int id = (int) tableNL.getValueAt(selectedRow,0);
                int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                   NguyenlieuDAO dao = new NguyenlieuDAO();
                    dao.deleteById(id);
                    JOptionPane.showMessageDialog(null, "Đã xóa thành công!");
                    
            
                }
                
                 
            }
            else
            {
             JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                
            }
        });

        editButton.addActionListener(e -> {
            int selectedRow = tableNL.getSelectedRow();
            if(selectedRow!=-1)
            {
                int id = (int) tableNL.getValueAt(selectedRow,0);
                
                 new EditNLGUI(id);
            }
            else
            {
             JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để sửa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                
            }
           
        });

        reloadButton.addActionListener(e -> {
            reloadTableData();
        });
        listNL= nlBUS.getAll();
        tableNL = new JTable();
        ModelTable = new DefaultTableModel();
        ModelTable.setRowCount(0);
        String[] Header = {"ID", "Tên nguyên liệu", "Đơn vị", "Số lượng"};
        ModelTable.setColumnIdentifiers(Header);

        for (NguyenlieuDTO dto : listNL) {
            ModelTable.addRow(new Object[]{dto.getIdNl(), dto.getTenNL(), dto.getDonvi(), dto.getSoluong()});
        }

        tableNL.setModel(ModelTable);
        tableNL.setRowHeight(80);

        setJTableColumnsWidth(tableNL, 700, 10, 40, 10, 40);
        scrollTableNguyenlieu = new JScrollPane(tableNL);
        scrollTableNguyenlieu.setPreferredSize(new Dimension(1035, 550));
        Font font = new Font("Arial", Font.BOLD, 18);

        tableNL.setFont(font);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        TableColumnModel columnModel = tableNL.getColumnModel();

        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setCellRenderer(renderer);
        }

        // Thêm panel nút vào phía trên của giao diện
        this.add(buttonPanelWrapper, BorderLayout.NORTH);
        this.add(scrollTableNguyenlieu, BorderLayout.CENTER);
    }
    public void search(String keyword)
    {
        try{
            int id= Integer.parseInt(keyword);
            searchById(id);
        }
        catch(NumberFormatException e   ){
            searchByName(keyword);
        }
    }
    public void searchByName(String keyword) {
    // Tạo một danh sách mới để lưu trữ kết quả tìm kiếm
    ArrayList<NguyenlieuDTO> searchResult = new ArrayList<>();
    
    // Lặp qua danh sách nguyên liệu hiện tại để tìm kiếm
    for (NguyenlieuDTO dto : listNL) {
        // Kiểm tra xem từ khóa có xuất hiện trong tên nguyên liệu không
        if (dto.getTenNL().toLowerCase().contains(keyword.toLowerCase())) {
            // Nếu có, thêm vào danh sách kết quả tìm kiếm
            searchResult.add(dto);
        }
    }
    
    
    // Xóa hết các dòng trong bảng hiện tại
    ModelTable.setRowCount(0);
    
    // Thêm các dòng tìm được vào bảng hiển thị
    for (NguyenlieuDTO dto : searchResult) {
        ModelTable.addRow(new Object[]{dto.getIdNl(), dto.getTenNL(), dto.getDonvi(), dto.getSoluong()});
    }
}
        public void searchById(int id) {
    // Tạo một danh sách mới để lưu trữ kết quả tìm kiếm
    ArrayList<NguyenlieuDTO> searchResult = new ArrayList<>();
    
    // Lặp qua danh sách nguyên liệu hiện tại để tìm kiếm
    for (NguyenlieuDTO dto : listNL) {
        // Kiểm tra xem từ khóa có xuất hiện trong tên nguyên liệu không
        if (String.valueOf(dto.getIdNl()).contains(String.valueOf(id))) {
            // Nếu có, thêm vào danh sách kết quả tìm kiếm
            searchResult.add(dto);
        }
    }
    
    
    // Xóa hết các dòng trong bảng hiện tại
    ModelTable.setRowCount(0);
    
    // Thêm các dòng tìm được vào bảng hiển thị
    for (NguyenlieuDTO dto : searchResult) {
        ModelTable.addRow(new Object[]{dto.getIdNl(), dto.getTenNL(), dto.getDonvi(), dto.getSoluong()});
    }
}
    public void setJTableColumnsWidth(JTable table, int tablePreferredWidth, double... percentages) {
        double total = 0;
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            total += percentages[i];
        }

        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth((int) (tablePreferredWidth * (percentages[i] / total)));
        }
    }

    public void reloadTableData() {
        NguyenlieuBUS nlBUS1=new NguyenlieuBUS();
        
        listNL = nlBUS1.getAll();
      
        ModelTable.setRowCount(0);
        for (NguyenlieuDTO dto : listNL) {
            ModelTable.addRow(new Object[]{dto.getIdNl(), dto.getTenNL(), dto.getDonvi(), dto.getSoluong()});
        }
    }
}

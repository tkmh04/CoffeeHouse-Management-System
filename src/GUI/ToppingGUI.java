/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.ToppingBUS;
import DTO.NguyenlieuDTO;
import DTO.ToppingDTO;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author asus
 */
public class ToppingGUI extends JFrame {
    JTable tableTP;
    DefaultTableModel ModelTable;
    ToppingBUS tpBUS = new ToppingBUS();
    ArrayList<DTO.ToppingDTO> listTP = tpBUS.getAll();
    JScrollPane scrollTableTopping;

    public ToppingGUI()
    {
        initDialog();
    }
    void initDialog()
    {
        this.setSize(400, 350);
         tableTP = new JTable();
        ModelTable = new DefaultTableModel();
        ModelTable.setRowCount(0);
        String[] Header={"ID","TOPPING","Giá","Số lượng"};
        ModelTable.setColumnIdentifiers(Header);
        for (ToppingDTO dto : listTP) 
        {
                ModelTable.addRow(new Object[]
                        {
                            dto.getIdTopping(),dto.getNameTopping(),dto.getPriceTopping(),dto.getQuantTopping()
                            
                        }); 
                
        }            
        
        tableTP.setModel(ModelTable);
        tableTP.setRowHeight(40);
       
        setJTableColumnsWidth(tableTP,300,20,30,20,20);
        scrollTableTopping = new JScrollPane(tableTP);
        scrollTableTopping.setPreferredSize(new Dimension(300,360));
        Font font = new Font("Arial", Font.BOLD, 18);

        tableTP.setFont(font);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        TableColumnModel columnModel = tableTP.getColumnModel();

        for (int i = 0; i < columnModel.getColumnCount(); i++) {
          TableColumn column = columnModel.getColumn(i);
          column.setCellRenderer(renderer);
        }

         this.add(scrollTableTopping);
         this.setVisible(true);
       
    }
    public  void setJTableColumnsWidth(JTable table, int tablePreferredWidth,
        double... percentages) {
    double total = 0;
    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
        total += percentages[i];
    }
 
    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
        TableColumn column = table.getColumnModel().getColumn(i);
        column.setPreferredWidth((int)
                (tablePreferredWidth * (percentages[i] / total)));
    }
}

    }
   

    
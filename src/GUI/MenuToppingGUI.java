/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.ToppingBUS;
import DTO.SelectedTopping;
import DTO.ToppingDTO;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

public class MenuToppingGUI extends JFrame {

    static void resetStaticSelectedToppings() {
        staticSelectedToppings.clear() ;
    }
    private JPanel panelHeader, mainPanel, buttonPanel;
    private JLabel label1, label2, label3;
    private JButton confirmButton;
    private ArrayList<ToppingDTO> listTP;
    public static ArrayList<SelectedTopping> staticSelectedToppings = new ArrayList<>();

    public MenuToppingGUI() {
        initialize();
    }

    private void initialize() {
        listTP = new ToppingBUS().getAll();
        staticSelectedToppings = new ArrayList<>();
        this.setSize(500,700);
        setTitle("Toppings Menu");

        panelHeader = new JPanel(new GridLayout(1, 3));
        label1 = new JLabel("Topping");
        label2 = new JLabel("Price");
        label3 = new JLabel("Quantity");
        panelHeader.add(label1);
        panelHeader.add(label2);
        panelHeader.add(label3);

        mainPanel = new JPanel(new GridLayout(0, 3, 10, 5)); // 0 rows, 3 columns, horizontal gap: 10, vertical gap: 5

        for (ToppingDTO toppingDTO : listTP) {
            JLabel nameLabel = new JLabel(toppingDTO.getNameTopping());
            JLabel priceLabel = new JLabel(String.valueOf(toppingDTO.getPriceTopping()));
            SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 5, 1);
            JSpinner spinner = new JSpinner(spinnerModel);

            mainPanel.add(nameLabel);
            mainPanel.add(priceLabel);
            mainPanel.add(spinner);
        }

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateSelectedToppings();
               MenuToppingGUI.this.dispose();
         
            }
        });
        buttonPanel.add(confirmButton);

        add(panelHeader, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    private void updateSelectedToppings() {
        staticSelectedToppings.clear();
        for (int i = 0; i < listTP.size(); i++) {
            ToppingDTO topping = listTP.get(i);
            int quantity = (int) ((JSpinner) mainPanel.getComponent(i * 3 + 2)).getValue();
            String ten=topping.getNameTopping();
            if (quantity > 0) {
                staticSelectedToppings.add(new SelectedTopping(topping.getIdTopping(),ten, quantity));
            }
        }
       
    }

    public static ArrayList<SelectedTopping> getStaticSelectedToppings() {
        
        return staticSelectedToppings;
    }
    
    
    

  
}

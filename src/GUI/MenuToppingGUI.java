/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.ToppingBUS;
import DTO.SelectedTopping;
import DTO.ToppingDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerModel;
import javax.swing.SwingConstants;

public class MenuToppingGUI extends JFrame {

    static void resetStaticSelectedToppings() {
        staticSelectedToppings.clear();
    }

    private JPanel panelHeader, mainPanel, buttonPanel;
    private JButton confirmButton, cancelButton;
    private ArrayList<ToppingDTO> listTP;
    private ArrayList<JSpinner> toppingSpinners;
    public static ArrayList<SelectedTopping> staticSelectedToppings = new ArrayList<>();
    private final Color appBg = new Color(252, 246, 250);
    private final Color cardBg = Color.WHITE;
    private final Color borderColor = new Color(233, 211, 223);
    private final Color titleColor = new Color(94, 58, 80);
    private final Color accent = new Color(219, 88, 147);
    private final Color accentHover = new Color(198, 72, 129);

    public MenuToppingGUI() {
        initialize();
    }

    private void initialize() {
        listTP = new ToppingBUS().getAll();
        staticSelectedToppings = new ArrayList<>();
        toppingSpinners = new ArrayList<>();

        this.setSize(560, 680);
        this.setTitle("Chọn topping");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(appBg);
        this.setLayout(new BorderLayout(12, 10));

        JLabel titleLabel = new JLabel("Tuy chon topping", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(titleColor);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(16, 0, 6, 0));
        add(titleLabel, BorderLayout.NORTH);

        panelHeader = new JPanel(new GridLayout(1, 3, 10, 0));
        panelHeader.setBackground(new Color(246, 229, 238));
        panelHeader.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));

        JLabel label1 = createHeaderLabel("Topping");
        JLabel label2 = createHeaderLabel("Gia");
        JLabel label3 = createHeaderLabel("So luong");
        panelHeader.add(label1);
        panelHeader.add(label2);
        panelHeader.add(label3);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(cardBg);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        for (ToppingDTO toppingDTO : listTP) {
            JPanel row = new JPanel(new GridLayout(1, 3, 10, 0));
            row.setBackground(cardBg);
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
            row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(242, 232, 238)));

            JLabel nameLabel = new JLabel(toppingDTO.getNameTopping());
            nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            nameLabel.setForeground(titleColor);

            JLabel priceLabel = new JLabel(formatVnd(toppingDTO.getPriceTopping()));
            priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
            priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
            priceLabel.setForeground(new Color(114, 66, 93));

            SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 5, 1);
            JSpinner spinner = new JSpinner(spinnerModel);
            spinner.setFont(new Font("Segoe UI", Font.BOLD, 13));
            spinner.setPreferredSize(new Dimension(66, 30));
            toppingSpinners.add(spinner);

            JPanel spinnerWrap = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            spinnerWrap.setOpaque(false);
            spinnerWrap.add(spinner);

            row.add(nameLabel);
            row.add(priceLabel);
            row.add(spinnerWrap);
            mainPanel.add(row);
            mainPanel.add(Box.createVerticalStrut(4));
        }

        JPanel centerPanel = new JPanel(new BorderLayout(0, 8));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 14, 0, 14));
        centerPanel.add(panelHeader, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(BorderFactory.createLineBorder(borderColor));
        scrollPane.getViewport().setBackground(cardBg);
        scrollPane.getVerticalScrollBar().setUnitIncrement(12);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(appBg);

        confirmButton = new JButton("Xac nhan");
        confirmButton.setFocusPainted(false);
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setBackground(accent);
        confirmButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        confirmButton.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSelectedToppings();
                MenuToppingGUI.this.dispose();
            }
        });

        confirmButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confirmButton.setBackground(accentHover);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                confirmButton.setBackground(accent);
            }
        });

        cancelButton = new JButton("Dong");
        cancelButton.setFocusPainted(false);
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        cancelButton.setBackground(cardBg);
        cancelButton.setForeground(titleColor);
        cancelButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor),
                BorderFactory.createEmptyBorder(10, 18, 10, 18)));
        cancelButton.addActionListener(e -> MenuToppingGUI.this.dispose());

        buttonPanel.add(cancelButton);
        buttonPanel.add(confirmButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JLabel createHeaderLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setForeground(titleColor);
        return label;
    }

    private String formatVnd(double value) {
        return String.format(Locale.US, "%,.0f VNĐ", value).replace(',', '.');
    }

    private void updateSelectedToppings() {
        staticSelectedToppings.clear();
        for (int i = 0; i < listTP.size(); i++) {
            ToppingDTO topping = listTP.get(i);
            int quantity = (int) toppingSpinners.get(i).getValue();
            String ten = topping.getNameTopping();
            if (quantity > 0) {
                staticSelectedToppings.add(new SelectedTopping(topping.getIdTopping(), ten, quantity));
            }
        }
    }

    public static ArrayList<SelectedTopping> getStaticSelectedToppings() {
        return staticSelectedToppings;
    }
}

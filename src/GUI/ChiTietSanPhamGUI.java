package GUI;

import BUS.NguyenlieuBUS;
import BUS.SanPhamBUS;
import BUS.ToppingBUS;
import DTO.SelectedTopping;
import DTO.cartProduct;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import GUI.Component.ConfirmDialog;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

public class ChiTietSanPhamGUI extends JFrame {
    private SanPhamBUS idBUS = new SanPhamBUS();
    private DTO.SanPhamDTO idd = new DTO.SanPhamDTO();
    private NguyenlieuBUS nlBUS = new NguyenlieuBUS();
    private ToppingBUS tpBUS = new ToppingBUS();
    private JTextField phoneNumberField;
    private JLabel totalBillLabel;
    private JLabel totalPriceLabel;
    private JPanel pnImage, pnAdd, pnBill, pnPay, pnRow1, pnRow2, pnRow3,pnCartProduct,pnCartProductHeader,totalPricePanel;
    private static ArrayList<cartProduct> cartProducts = new ArrayList<>();
    ArrayList<SelectedTopping> nametp;
    private JTextField quantityField;
    private JButton plusBtn, minusBtn;
    private ButtonGroup bg = new ButtonGroup();
    private boolean flag;
    private String selectedValue = "";
    private double totalPrice=0;
    private int selectedIndex = -1;
    private final Color appBg = new Color(251, 244, 248);
    private final Color cardBg = Color.WHITE;
    private final Color softBorder = new Color(233, 212, 223);
    private final Color accent = new Color(219, 88, 147);
    private final Color accentHover = new Color(197, 72, 128);
    private final Color textPrimary = new Color(90, 58, 76);
    private final Color tableHeaderBg = new Color(244, 227, 236);
    
    public ChiTietSanPhamGUI(int id) {
        flag = cartProducts != null && !cartProducts.isEmpty();
        initChiTietSanPhamGUI(id);
    }

    void initChiTietSanPhamGUI(int id) {
        
        this.setSize(680, 760);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String imageLink = idBUS.selectedById(String.valueOf(id)).getImgSp();
        ImageIcon img = new ImageIcon(getClass().getResource(imageLink));
        Image image = img.getImage();
        Image scaledImg = image.getScaledInstance(145, 170, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
         this.setTitle("Chi tiết chọn món");     
        JLabel lbImage = new JLabel(scaledIcon);
       
        
        idd = idBUS.selectedById(String.valueOf(id));
        String name = idd.getTenSp();
        final int selectedProductId = idd.getIdSp();
        JLabel lbAddDetails = new JLabel(name,JLabel.CENTER);
        JLabel lbBill = new JLabel("Thông tin giỏ hàng",JLabel.CENTER);
        lbAddDetails.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lbAddDetails.setForeground(textPrimary);
        lbBill.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbBill.setForeground(textPrimary);

        lbBill.setPreferredSize(new Dimension(600,50));

        pnRow1 = new JPanel();
        pnImage = new JPanel();
        pnAdd = new JPanel();
        pnAdd.setLayout(new GridLayout(4,1,5,5));
        pnAdd.add(lbAddDetails);
        JPanel pnSize = new JPanel();
        pnSize.setLayout(new FlowLayout());
        JPanel ctn1 = new JPanel();
        ctn1.setLayout(new GridLayout(1,2));
        ctn1.add(pnSize);
        JRadioButton sizeM = new JRadioButton("M");
        sizeM.setSelected(true);
        sizeM.setFocusPainted(false);
        sizeM.setFont(new Font("Segoe UI", Font.BOLD, 14));

        sizeM.setVerticalTextPosition(JRadioButton.TOP);
        sizeM.setHorizontalTextPosition(JRadioButton.CENTER);
        JRadioButton sizeL = new JRadioButton("L");
        sizeL.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        sizeL.setVerticalTextPosition(JRadioButton.TOP);
        sizeL.setFocusPainted(false);
        
        JPanel ctn3 = new JPanel();
        
        JLabel quantityLbl= new JLabel("Số lượng :");
        quantityLbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        quantityLbl.setForeground(textPrimary);
        quantityField =  new JTextField("1",5);
        quantityField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        plusBtn = new JButton("+");
        plusBtn.setFocusPainted(false);
        plusBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        plusBtn.setBackground(cardBg);
        plusBtn.setBorder(BorderFactory.createLineBorder(softBorder));
         plusBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quantity = Integer.parseInt(quantityField.getText());
                quantity++;
                quantityField.setText(Integer.toString(quantity));
            }
        });
         
        minusBtn = new JButton("-");
        minusBtn.setFocusPainted(false);
        minusBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        minusBtn.setBackground(cardBg);
        minusBtn.setBorder(BorderFactory.createLineBorder(softBorder));
        minusBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quantity = Integer.parseInt(quantityField.getText());
                if (quantity > 1) { 
                    quantity--;
                    quantityField.setText(Integer.toString(quantity));
                }
            }
        });
        ctn3.setBackground(cardBg);
        ctn3.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 0));
        ctn3.add(quantityLbl,JPanel.CENTER_ALIGNMENT);
        ctn3.add(quantityField,JPanel.CENTER_ALIGNMENT);
        ctn3.add(plusBtn,JPanel.CENTER_ALIGNMENT);
        ctn3.add(minusBtn,JPanel.CENTER_ALIGNMENT);
        
        sizeL.setHorizontalTextPosition(JRadioButton.CENTER);
        sizeM.setOpaque(false);
        sizeL.setOpaque(false);
        bg.add(sizeM);
        bg.add(sizeL);
        pnSize.add(sizeM);
        pnSize.add(sizeL);
        
        pnSize.setBackground(cardBg);
        JLabel toppingBtn = new JLabel("TOPPING",JLabel.CENTER);
        JLabel addBtn = new JLabel("THÊM",JLabel.CENTER);  
        addBtn.setBackground(accent);
        addBtn.setForeground(new Color(255,255,255));
        addBtn.setOpaque(true);
        addBtn.setPreferredSize(new Dimension(92,36));
        addBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        addBtn.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));
        JPanel ctnAddBtn = new JPanel();
        ctnAddBtn.setBackground(cardBg);
        ctnAddBtn.add(addBtn);
        JPanel ctnTopping = new JPanel();
        toppingBtn.setBackground(new Color(248, 231, 239));
        toppingBtn.setForeground(textPrimary);
        toppingBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        toppingBtn.setOpaque(true);
        toppingBtn.setPreferredSize(new Dimension(92,35));
        toppingBtn.setBorder(BorderFactory.createLineBorder(softBorder));
        
        ctnTopping.add(toppingBtn,JPanel.CENTER_ALIGNMENT);
        ctnTopping.setBackground(cardBg);
        ctn1.add(pnSize);
        ctn1.add(ctnTopping);
        ctn1.setBackground(cardBg);
        ctn1.setOpaque(true);
        
        
        pnAdd.add(ctn1);
        pnAdd.add(ctn3);
        pnAdd.add(ctnAddBtn);
        
        pnImage.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        pnRow1.setLayout(new GridLayout(1, 2)); 
        pnRow1.setPreferredSize(new Dimension(600,250));
        pnImage.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(softBorder),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        pnAdd.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(softBorder),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        pnAdd.setOpaque(true);
        pnRow1.add(pnImage);
        pnRow1.add(pnAdd);
        
        pnImage.setVisible(true);
        pnImage.setOpaque(true);
        pnAdd.setVisible(true);
        pnImage.add(lbImage, gbc);
        pnRow1.setVisible(true);
        pnRow1.setOpaque(true);

        pnRow2 = new JPanel();
        pnRow2.setPreferredSize(new Dimension(640,350));
        pnBill = new JPanel(new BorderLayout());        
        pnCartProductHeader = new JPanel(new GridLayout(1,4));
        pnCartProduct = new JPanel(new GridLayout(0,4));
        JScrollPane scrollPane = new JScrollPane(pnCartProduct);
        scrollPane.setPreferredSize(new Dimension(640, 250));
        scrollPane.setBorder(BorderFactory.createLineBorder(softBorder));
        scrollPane.getViewport().setBackground(cardBg);
        pnCartProduct.setBackground(cardBg);
        
        pnCartProductHeader.setPreferredSize(new Dimension(600,40));
        JLabel header1= new JLabel("Sản phẩm",JLabel.CENTER);
        JLabel header2= new JLabel("Topping",JLabel.CENTER);
        JLabel header3= new JLabel("Giá",JLabel.CENTER);
        JLabel header4= new JLabel("Số lượng",JLabel.CENTER);
        styleHeaderLabel(header1);
        styleHeaderLabel(header2);
        styleHeaderLabel(header3);
        styleHeaderLabel(header4);
        
        
        pnCartProductHeader.add(header1);
        pnCartProductHeader.add(header2);
        pnCartProductHeader.add(header3);
        pnCartProductHeader.add(header4);
        
        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            
            public void mouseClicked(MouseEvent e) {
                  Enumeration<AbstractButton> buttons = bg.getElements();
                  
                   int quantityy = Integer.parseInt(quantityField.getText()); // Số lượng
         while (buttons.hasMoreElements()) {
             AbstractButton button = buttons.nextElement();
             if (button.isSelected()) {
                 selectedValue = button.getText();
                 break;
    }

           
}                   
                double price=0;
                nametp = MenuToppingGUI.getStaticSelectedToppings();
               String namepr = name+"("+selectedValue+")"; // Tên sản phẩm
               String toppings="";                
            int idTpp;
            double priceSpp=0;
            double priceTpp=0;
            int quantTpp=0;   
            String SIZE="";
        if(!nametp.isEmpty())
        {
             for (SelectedTopping topping : nametp) {
                 
           
            toppings+=topping.getName()+"("+"x"+topping.getQuantity()+")"+"\n";
            priceSpp=idBUS.getPriceSp(String.valueOf(id),selectedValue);
            idTpp=topping.getId();
            quantTpp=topping.getQuantity();
            priceTpp+=tpBUS.selectedById(String.valueOf(idTpp)).getPriceTopping()*quantTpp;
            SIZE=selectedValue;
            
                         
        }
             price=priceCal(priceSpp,priceTpp,quantityy);
        }
        else if(nametp.isEmpty())
        {
            
             priceSpp=idBUS.getPriceSp(String.valueOf(id),selectedValue);  
             price=priceCal(priceSpp,0,quantityy);
             SIZE=selectedValue;
        }
             
                               
        cartProduct product = new cartProduct(selectedProductId, namepr, toppings, price, quantityy, SIZE);
       
                
                addCartProduct(product);
                totalPriceLabel.setText(String.valueOf(totalPrice) + "VNĐ");
                cartProducts.add(product);
                quantityField.setText("1");
            }
            public void mouseEntered(MouseEvent e){
               
            }
            public void mouseExited(MouseEvent e){
               
            }
            
            
        });
        pnBill.add(lbBill,BorderLayout.NORTH); 
        pnBill.add(pnCartProductHeader,BorderLayout.CENTER);
        pnBill.add(scrollPane,BorderLayout.SOUTH);
        pnBill.revalidate();
        pnBill.repaint();       
        pnBill.setVisible(true);
        pnBill.setOpaque(true);
        pnRow2.add(pnBill);
        pnRow2.setVisible(true);
        pnRow2.setOpaque(true);

        pnRow3 = new JPanel();
        pnPay = new JPanel();

        pnPay.setVisible(true);
        pnPay.setOpaque(true);
        pnRow3.add(pnPay);
        pnRow3.setBorder(BorderFactory.createLineBorder(softBorder));
        pnRow3.setPreferredSize(new Dimension(600,100));
        pnRow3.setVisible(true);
        pnRow3.setOpaque(true);

        this.setResizable(false);
        this.setLayout(new FlowLayout());

        this.add(pnRow1);
        this.add(pnRow2);
        this.add(pnRow3);
        this.setVisible(true);
        this.getContentPane().setBackground(appBg);

        pnImage.setBackground(cardBg);
        pnAdd.setBackground(cardBg);
        pnRow1.setBackground(appBg);
        pnRow2.setBackground(appBg);
        pnRow3.setBackground(appBg);
        pnBill.setBackground(appBg);
        pnPay.setBackground(appBg);
        
        ctnTopping.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
         
        
         ctnTopping.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
             
              MenuToppingGUI tp = new MenuToppingGUI();
                
               
            }
            public void mouseEntered(MouseEvent e){
               
            }
            public void mouseExited(MouseEvent e){
               
            }
           
        });
         pnRow3.setLayout(new BorderLayout());
        
        pnPay.setLayout(new FlowLayout()); 
        pnPay.setPreferredSize(new Dimension(300,150));
        JPanel phoneNumberPanel = new JPanel();
        phoneNumberPanel.setPreferredSize(new Dimension(300,40));
        JLabel lbPhone = new JLabel("Số điện thoại:",JLabel.CENTER);
        lbPhone.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbPhone.setForeground(textPrimary);
        phoneNumberPanel.add(lbPhone);
        phoneNumberField = new JTextField(10); 
        phoneNumberField.setBackground(cardBg);
        phoneNumberField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        phoneNumberField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(softBorder),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)));
        phoneNumberPanel.add(phoneNumberField,JPanel.CENTER_ALIGNMENT);
        phoneNumberPanel.setBackground(appBg);
        pnPay.add(phoneNumberPanel,JPanel.CENTER_ALIGNMENT); // Thêm phoneNumberPanel vào pnPay

        totalPricePanel = new JPanel(new FlowLayout());
        totalPriceLabel = new JLabel("0 VNĐ");
        totalPriceLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        totalPriceLabel.setForeground(textPrimary);
        totalBillLabel = new JLabel("Tổng hóa đơn: ",JLabel.CENTER);
        totalBillLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        totalBillLabel.setForeground(textPrimary);
        totalPricePanel.add(totalBillLabel);
        totalPricePanel.add(totalPriceLabel);
        totalPricePanel.setPreferredSize(new Dimension(300,70));
        totalPricePanel.setOpaque(false);
        pnPay.add(totalPricePanel);
         

        JPanel rightPanel = new JPanel(); 
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS)); 
        JButton paymentButton = new JButton("Thanh toán");
        paymentButton.setFocusPainted(false);
        paymentButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        paymentButton.setBackground(accent);
        paymentButton.setForeground(Color.WHITE);
        paymentButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        paymentButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(300, 15));
        emptyPanel.setBackground(appBg);
        rightPanel.add(emptyPanel);
        JPanel paymentBtnPn = new JPanel();
        paymentBtnPn.setBackground(appBg);
       
        paymentBtnPn.add(paymentButton);
        rightPanel.add(paymentBtnPn,JPanel.CENTER_ALIGNMENT);
        rightPanel.setBackground(appBg);

        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                addBtn.setBackground(accentHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addBtn.setBackground(accent);
            }
        });

        paymentButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                paymentButton.setBackground(accentHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                paymentButton.setBackground(accent);
            }
        });
        
        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cartProducts == null || cartProducts.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Giỏ hàng đang trống!");
                    return;
                }

                ConfirmDialog confirmDialog = new ConfirmDialog(ChiTietSanPhamGUI.this, "Xác nhận thanh toán", "Bạn có chắc muốn thanh toán?");
                confirmDialog.setVisible(true);
            
                if (confirmDialog.isConfirmed()) {
                    for (cartProduct product : cartProducts) {
                        int productId = product.getId();
                        String sizez = product.getSize();
                        int quantity = product.getQuant();
                        nlBUS.updateQuantity(productId, sizez, quantity);
                    }
                    JOptionPane.showMessageDialog(null, "Đã thanh toán thành công!");
                    resetOrderState();
                    dispose();
                }
            }
        });
       

        pnRow3.add(pnPay, BorderLayout.WEST);
        pnRow3.add(rightPanel, BorderLayout.CENTER); 
        if(flag==true)
            displayCartProducts();
    
        this.setLocationRelativeTo(null);
    }

    private void resetOrderState() {
        if (cartProducts != null) {
            cartProducts.clear();
        }
        totalPrice = 0;
        MenuToppingGUI.resetStaticSelectedToppings();
    }

    private void styleHeaderLabel(JLabel header) {
        header.setOpaque(true);
        header.setBackground(tableHeaderBg);
        header.setForeground(textPrimary);
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBorder(BorderFactory.createLineBorder(softBorder));
    }
   void addCartProduct(cartProduct product) {   
    JLabel particular1 = new JLabel(product.getName(), JLabel.CENTER);
    JPanel toppingPanel = new JPanel(new GridLayout(0, 1));
    String toppings = product.getToppings();
    String[] toppingArray = toppings.split("\n"); 
    for (String topping : toppingArray) {
        JLabel toppingLabel = new JLabel(topping, JLabel.CENTER);
        toppingLabel.setBackground(cardBg);
        toppingLabel.setForeground(textPrimary);
        toppingLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        toppingLabel.setOpaque(true);
        toppingPanel.add(toppingLabel); 
         
    }
    
    JLabel particular3 = new JLabel(String.valueOf(product.getPrice()), JLabel.CENTER);
    JLabel particular4 = new JLabel(String.valueOf(product.getQuant()), JLabel.CENTER);
    
    particular1.setBackground(cardBg);
    particular1.setForeground(textPrimary);
    particular1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    particular1.setOpaque(true);
    particular1.addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) {
            showPopupMenu(e.getComponent(), e.getX(), e.getY());
           
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
            showPopupMenu(e.getComponent(), e.getX(), e.getY());
             selectedIndex = pnCartProduct.getComponentZOrder(particular1) / 4; 
          
        }
    }

    private void showPopupMenu(Component component, int x, int y) {
        JPopupMenu popupMenu = new JPopupMenu();
        
        
        
        JMenuItem deleteItem = new JMenuItem("Xóa");
        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 if (selectedIndex != -1) {
            // Xóa dòng ở vị trí selectedIndex
            pnCartProduct.remove(selectedIndex * 4);
            pnCartProduct.remove(selectedIndex * 4); // Xóa các thành phần của dòng
            pnCartProduct.remove(selectedIndex * 4);
            pnCartProduct.remove(selectedIndex * 4);
            pnCartProduct.revalidate();
            pnCartProduct.repaint();
            
            // Cập nhật lại tổng giá trị
            totalPrice -= cartProducts.get(selectedIndex).getPrice();
            totalPriceLabel.setText(String.valueOf(totalPrice) + "VNĐ");
            
            // Xóa dữ liệu tương ứng trong danh sách sản phẩm
            cartProducts.remove(selectedIndex);
            
            // Đặt lại selectedIndex về -1 sau khi xóa
            selectedIndex = -1;
        }
            }
        });
        
        popupMenu.add(deleteItem);
        
        popupMenu.show(component, x, y);
    }
});

    particular3.setBackground(cardBg);
    particular3.setForeground(textPrimary);
    particular3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    particular3.setOpaque(true);
    particular4.setBackground(cardBg);
    particular4.setForeground(textPrimary);
    particular4.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    particular4.setOpaque(true);
    toppingPanel.setBackground(cardBg);
   
    pnCartProduct.add(particular1);
    pnCartProduct.add(toppingPanel); 
    pnCartProduct.add(particular3);
    pnCartProduct.add(particular4);
    
    pnCartProduct.revalidate();
    
    MenuToppingGUI.resetStaticSelectedToppings();
}
 double priceCal(double price1,double price2,int quantity)
 {
     double price=(price1+price2)*quantity;
     totalPrice+=price;
     return price;
 }
 void displayCartProducts() {

        if(!cartProducts.isEmpty())
        {
            for (cartProduct product : cartProducts) {
                addCartProduct(product);
        }
           
        }
        
    }

   
}

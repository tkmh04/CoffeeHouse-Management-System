package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import BUS.SanPhamBUS;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class SanPhamGUI extends JPanel {
    public SanPhamBUS spBUS =new SanPhamBUS();
    private ArrayList<JPanel> ListProductPanel = new ArrayList<>();
    public JScrollPane scrollPane;
    public ArrayList<DTO.SanPhamDTO> listSP = spBUS.getAll();
    public String img;
    public String nameSp;
    
    public SanPhamGUI() {
               JPanel mainPanel = new JPanel();
               
                mainPanel.setLayout(new GridLayout(0, 5));
        
        for (int i = 0; i <= listSP.size()-1; i++) {
            img=spBUS.imgLink(i);
            nameSp=spBUS.getName(i);
            int idSelectedSP = spBUS.getId(i);
            JPanel productPanel = createProductPanel( idSelectedSP);
            ListProductPanel.add(productPanel);
            mainPanel.add(productPanel);
           
        }

        
        this.add(mainPanel);
        scrollPane = new JScrollPane(this);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
       
    }
   

    private JPanel createProductPanel(int idSelectedSP) {
        JPanel productPanel = new JPanel();
        productPanel.setPreferredSize(new Dimension(180, 220));
        productPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        productPanel.setBackground(new Color(253, 172, 203));
        // Thêm nội dung cho sản phẩm
       
        try {
            InputStream inputStream = getClass().getResourceAsStream(img);
            BufferedImage image = ImageIO.read(inputStream);
            int newWidth = 120;
            int newHeight = 142;
            Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon imgProduct = new ImageIcon(scaledImage);
            JLabel imgLabel = new JLabel(imgProduct);
            productPanel.add(imgLabel);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
//        ImageIcon IconInfo = new ImageIcon(getClass().getResource("/img/infoIcon.png"));
         ImageIcon IconCart = new ImageIcon(getClass().getResource("/img/cart.png"));
//        JLabel labelInfoProduct = new JLabel(IconInfo,JLabel.CENTER);
         JLabel labelCartProduct = new JLabel(IconCart,JLabel.CENTER);
       
          JPanel Infobg = new JPanel();
          JPanel Cartbg = new JPanel();
           Infobg.setBorder(BorderFactory.createEmptyBorder());
        Cartbg.setBorder(BorderFactory.createEmptyBorder());
         Infobg.setPreferredSize(new Dimension(80,45));
       
          Cartbg.setPreferredSize(new Dimension(175,43 ));
          Infobg.setBackground(new Color(242, 93, 255));
//          Infobg.add(labelInfoProduct);
          Cartbg.setBackground(new Color(255, 0, 229));
          Cartbg.add(labelCartProduct);
        JLabel labelProduct = new JLabel("", JLabel.CENTER); // Căn giữa nhãn
        labelProduct.setText(nameSp);
        Font font = new Font("Arial",Font.PLAIN,20);
        labelProduct.setFont(font);
        labelProduct.setPreferredSize(new Dimension(150,20));
        productPanel.add(labelProduct);
//         productPanel.add(Infobg);
          productPanel.add(Cartbg);
          productPanel.setVisible(true);
          
          Cartbg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
             
              ChiTietSanPhamGUI menuToppingGUI = new ChiTietSanPhamGUI(idSelectedSP);
                
               
            }
            public void mouseEntered(MouseEvent e){
                labelCartProduct.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Cartbg.setBackground(new Color(255, 0, 190));
            }
            public void mouseExited(MouseEvent e){
                labelCartProduct.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                Cartbg.setBackground(new Color(255, 0, 229));
            }
        });
        return productPanel;
    }

   
//public static void main(String[] args)
//{
//  JFrame frame = new JFrame();
//        frame.setSize(800, 900);
//        SanPhamGUI sanPhamGUI = new SanPhamGUI();
//        frame.add(sanPhamGUI); // Add SanPhamGUI panel to the frame
//        scrollPane = new JScrollPane(sanPhamGUI); // Create JScrollPane with SanPhamGUI panel
//        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
//        frame.add(scrollPane); // Add JScrollPane to the frame (optional, only if the panel overflows)
//        frame.setVisible(true);
//
//        
//       
//}
}

package GUI;

import BUS.PhanQuyenBUS;
import BUS.TaiKhoanBUS;
import java.awt.*;
import javax.swing.*;

import DAO.JDBCUtil;
import DAO.JDBCUtil;
import DTO.PhanQuyenDTO;
import DTO.TaiKhoanDTO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author asus
 */
public class Login extends JFrame{
    Font TitleFont=new Font("Times New Roman",Font.BOLD,30);
    Font FieldFont=new Font("Times New Roman",Font.PLAIN,16);
    private JComponent[] components;
    private TaiKhoanBUS tkBUS;
    private PhanQuyenBUS pqBUS;
    JDBCUtil conn;
    void init()
    {
        pqBUS = new PhanQuyenBUS();
        tkBUS = new TaiKhoanBUS();
    components=new JComponent[3];
    JPanel container =new JPanel(new GridLayout(3,1));
    this.setLayout(new FlowLayout());
        ImageIcon backgroundImg = new ImageIcon("D:\\NETBEANS\\JAVA\\QLTiemTraSua\\img\\loginBackground.jpg");
        JLabel backgroundImageLabel = new JLabel(backgroundImg);
    
     
    JPanel backgroundImagePanel =new JPanel(){
        @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                Image image = backgroundImg.getImage();
                g.drawImage(image, 0, 0, 600, 350, this);
            }
        };
    backgroundImagePanel.setLayout(null);
     
    components[0] = new JLabel("ĐĂNG NHẬP",JLabel.CENTER);
    components[0].setForeground(Color.black);
    components[0].setFont(TitleFont);
    
    components[0].setOpaque(false);
    this.setTitle("LOGIN");

     
    components[1]=new JPanel(new FlowLayout(FlowLayout.CENTER,100,10));
     
    JTextField UsernameTextField=new JTextField("admin");
    JPasswordField passField=new JPasswordField("admin");

    UsernameTextField.setPreferredSize(new Dimension(250,35));
    passField.setPreferredSize(new Dimension(250,35));
    UsernameTextField.setFont(FieldFont);
    passField.setFont(FieldFont);
    components[1].add(UsernameTextField);
    components[1].add(passField);
    components[1].setOpaque(false);
     
    components[2] = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel LabelButtonLogin = new JLabel("XÁC NHẬN",JLabel.CENTER);
    LabelButtonLogin.setForeground(Color.black);
     
    LabelButtonLogin.setPreferredSize(new Dimension(90 ,40));
    
// Sự kiện đăng nhập
    LabelButtonLogin.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        String username = UsernameTextField.getText();
        char[] passwordChars = passField.getPassword();
        String password = new String(passwordChars);

        if (tkBUS.checkLogin(username, password)) {
            TaiKhoanDTO tkDTO = tkBUS.getTaiKhoan(username, password);
            // Nếu tài khoản tồn tại, lấy idTaiKhoan từ tài khoản và tiếp tục xử lý
            int idQuyen = tkDTO.getIdQuyen();
            PhanQuyenDTO pqDTO = pqBUS.getIdvsTen(idQuyen);
            String tenTK = pqDTO.getTen();
            int idQ = pqDTO.getIdQuyen();
            dispose();
            GUI.MainFrame menu = new GUI.MainFrame(tenTK, idQ);
            menu.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Tên đăng nhập hoặc mật khẩu không đúng");
        }

    }
});



     LabelButtonLogin.setBackground(Color.white);
     LabelButtonLogin.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
     components[2].add(LabelButtonLogin);
     components[2].setOpaque(false);
     for(JComponent component :  components)
     {
       container.add(component);
     
     }
     
     
     
     container.setBounds(0,0,600,350);
     container.setOpaque(false);
     backgroundImagePanel.add(container);
      this.setSize(600,350);
      this.setContentPane(backgroundImagePanel);
    
     
     
     
     
     
     
     
     
     this.setLocationRelativeTo(null);
     this.setResizable(false);
     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     this.setVisible(true);
     
     
 }

 public Login() throws SQLException
 {
     conn = new JDBCUtil();
     init();
 }
    public static void main(String[] args) throws Exception {
        try {
            // Gọi hàm khởi tạo của lớp Login
            Login login = new Login();
        } catch (SQLException e) {
            // Xử lý ngoại lệ SQL ở đây
            e.printStackTrace();
        }
    }
}


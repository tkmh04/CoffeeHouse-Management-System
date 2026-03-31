package GUI;

import BUS.PhanQuyenBUS;
import BUS.TaiKhoanBUS;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

import DAO.ConnectDataBaseDB;
import DTO.PhanQuyenDTO;
import DTO.TaiKhoanDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;


/**
 *
 * @author asus
 */
public class Login extends JFrame{
    private TaiKhoanBUS tkBUS;
    private PhanQuyenBUS pqBUS;
    ConnectDataBaseDB conn;
    
    // Color scheme - Pink gradient theme
    final Color PrimaryColor = new Color(255, 105, 180);      // Hot pink
    final Color SecondaryColor = new Color(255, 182, 193);    // Light pink
    final Color TextPrimary = new Color(70, 70, 70);
    final Color TextSecondary = new Color(120, 120, 120);
    final Color ButtonHover = new Color(255, 160, 190);
    final Color Background = new Color(250, 250, 252);
    
    void init() {
        pqBUS = new PhanQuyenBUS();
        tkBUS = new TaiKhoanBUS();
        
        this.setSize(new Dimension(700, 500));
        this.setTitle("Cửa hàng Trà Sữa - Đăng Nhập");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set frame icon if available
        try {
            Image icon = Toolkit.getDefaultToolkit().getImage("img/TS.png");
            this.setIconImage(icon);
        } catch (Exception e) {
            // Icon not critical
        }
        
        // Main background panel with gradient
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Gradient background
                GradientPaint gradient = new GradientPaint(0, 0, new Color(250, 240, 245),
                                                          getWidth(), getHeight(), new Color(240, 220, 235));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());
        
        // Main login panel
        JPanel loginPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // White card with shadow effect
                g2d.setColor(new Color(0, 0, 0, 15));
                g2d.fillRoundRect(3, 3, getWidth() - 6, getHeight() - 6, 20, 20);
                
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth() - 3, getHeight() - 3, 20, 20);
                
                // Border
                g2d.setColor(new Color(220, 150, 180));
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRoundRect(0, 0, getWidth() - 3, getHeight() - 3, 20, 20);
            }
        };
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setPreferredSize(new Dimension(380, 420));
        loginPanel.setOpaque(false);
        loginPanel.setBorder(BorderFactory.createEmptyBorder(40, 35, 40, 35));
        
        // Title
        JLabel titleLabel = new JLabel("ĐĂNG NHẬP");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(PrimaryColor);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(titleLabel);
        
        // Subtitle
        JLabel subtitleLabel = new JLabel("Cửa hàng Trà Sữa Không Tên");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitleLabel.setForeground(TextSecondary);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(Box.createVerticalStrut(5));
        loginPanel.add(subtitleLabel);
        
        loginPanel.add(Box.createVerticalStrut(25));
        
        // Username label
        JLabel userLabel = new JLabel("Tên đăng nhập");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        userLabel.setForeground(TextPrimary);
        userLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginPanel.add(userLabel);
        loginPanel.add(Box.createVerticalStrut(5));
        
        // Username text field
        JTextField usernameField = new JTextField("admin");
        usernameField.setPreferredSize(new Dimension(300, 40));
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        usernameField.setForeground(TextPrimary);
        loginPanel.add(usernameField);
        
        loginPanel.add(Box.createVerticalStrut(15));
        
        // Password label
        JLabel passLabel = new JLabel("Mật khẩu");

        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        passLabel.setForeground(TextPrimary);
        passLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginPanel.add(passLabel);
        loginPanel.add(Box.createVerticalStrut(5));
        
        // Password field
        JPasswordField passwordField = new JPasswordField("123");
        passwordField.setPreferredSize(new Dimension(300, 40));
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        loginPanel.add(passwordField);
        
        loginPanel.add(Box.createVerticalStrut(10));
        
        // Show password checkbox
        JCheckBox showPasswordCheckBox = new JCheckBox("Hiển thị mật khẩu");
        showPasswordCheckBox.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        showPasswordCheckBox.setForeground(TextSecondary);
        showPasswordCheckBox.setOpaque(false);
        showPasswordCheckBox.setFocusPainted(false);
        showPasswordCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordField.setEchoChar(showPasswordCheckBox.isSelected() ? (char) 0 : '*');
            }
        });
        loginPanel.add(showPasswordCheckBox);
        
        loginPanel.add(Box.createVerticalStrut(20));
        
        // Login button
        JButton loginButton = new JButton("Đăng nhập");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setPreferredSize(new Dimension(150, 45));
        loginButton.setMaximumSize(new Dimension(200, 45));
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        loginButton.setBackground(PrimaryColor);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        loginButton.setOpaque(true);
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Button hover effect
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(ButtonHover);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setBackground(PrimaryColor);
            }
        });
        
        // Login action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);
                
                // Clear password for security
                for (int i = 0; i < passwordChars.length; i++) {
                    passwordChars[i] = 0;
                }
                
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập tên đăng nhập và mật khẩu", 
                        "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if (tkBUS.checkLogin(username, password)) {
                    TaiKhoanDTO tkDTO = tkBUS.getTaiKhoan(username, password);
                    int idQuyen = tkDTO.getIdQuyen();
                    PhanQuyenDTO pqDTO = pqBUS.getTenTKPQ(idQuyen);
                    String tenTK = pqDTO.getTen();
                    dispose();
                    GUI.Main menu = new GUI.Main(tenTK, idQuyen);
                    menu.setVisible(true);
                } else if (!tkBUS.checkUser(username) && tkBUS.checkPassword(password)) {
                    JOptionPane.showMessageDialog(null, "Tên đăng nhập không đúng", 
                        "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
                } else if (tkBUS.checkUser(username) && !tkBUS.checkPassword(password)) {
                    JOptionPane.showMessageDialog(null, "Mật khẩu không đúng", 
                        "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Tên đăng nhập hoặc mật khẩu không đúng", 
                        "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        loginPanel.add(loginButton);
        
        // Add login panel to background with GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        backgroundPanel.add(loginPanel, gbc);
        
        this.setContentPane(backgroundPanel);
        this.setVisible(true);
     
     
 }

    public Login() throws SQLException {
        conn = new ConnectDataBaseDB();
        init();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Login login = new Login();
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi kết nối cơ sở dữ liệu", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}


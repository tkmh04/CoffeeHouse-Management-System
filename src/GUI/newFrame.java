package GUI;
import GUI.SanPhamGUI;
import java.awt.ScrollPane;
import javax.swing.*;

public class newFrame extends JFrame {
    public newFrame() {
        SanPhamGUI sanPhamGUI = new SanPhamGUI();
        ScrollPane a =new ScrollPane();
        a.add(sanPhamGUI);
        add(a);
        setSize(720, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Create an instance of MainFrame
        SwingUtilities.invokeLater(() -> new newFrame());
    }
}

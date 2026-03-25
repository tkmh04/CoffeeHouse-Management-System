/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
 

/**
 *
 * @author asus
 */
public class CustomTextField extends JTextField  {
    private boolean mouseOver;
//    private final Animator animator;
    public CustomTextField()
    {
        setBorder(new EmptyBorder(3,3,3,3));
        addMouseListener(new MouseAdapter()
                {
            @Override
            public void mouseEntered(MouseEvent e) {
              mouseOver=true;
              repaint();
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseOver=false;
                repaint();
            }
            
                }
        );
//        animator= new Animator(300,target);
        
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2=(Graphics2D) g;
     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
     g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
     int width=getWidth();
     int height=getHeight();
     if(mouseOver)
     {
        g2.setColor(new Color(77,121,255));
     }
     else
     {
         g2.setColor(new Color(150,150,150));
     }
     g2.fillRect(2,height-1,width-4,1);
     g2.dispose();
    }
    
}

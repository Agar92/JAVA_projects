package GUI;
import Main.Frame;

import Resourses.Resourses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Андрей on 17.03.2017.
 */
public class TopPanel1 extends JPanel{

    private JButton button1;
    private JButton button2;
    private JPanel toppanel;
    private JPanel rootpanel;

    public TopPanel1() {
        toppanel.setBackground(Color.gray);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int state = Frame.ICONIFIED;
                Frame.frame.setExtendedState(state);
            }
        });
    }

    public JPanel getToppanel() {return toppanel;}

    private void createUIComponents() {
        // TODO: place custom component creation code here
        button1 = new JButton(){
            @Override
            protected  void paintComponent(Graphics g){
                super.paintComponent(g);
                try {
                    g.drawImage(Resourses.getClose(),0,0,getWidth(),getHeight(),this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };

        button2 = new JButton(){
            @Override
            protected  void paintComponent(Graphics g){
                super.paintComponent(g);
                try {
                    g.drawImage(Resourses.getHide(),0,0,getWidth(),getHeight(),this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
    }
}

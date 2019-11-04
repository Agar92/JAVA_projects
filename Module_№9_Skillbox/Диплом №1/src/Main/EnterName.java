package Main;

import Resourses.Resourses;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Андрей on 19.03.2017.
 */
public class EnterName extends JPanel {

    private JPanel rootpanel;

    private JPanel toppanel;
    private JPanel mainpanel;
    private JButton button1;
    private JButton button2;
    private JLabel label1;
    private JPanel namepanel;
    private JPanel surnamepanel;
    private JTextField namefield;
    private JTextField surnamefield;

    EnterName(){
        //toppanel.add(new TopPanel());
        Frame.setTopPanel(toppanel);
        namepanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
        surnamepanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
    }

    public  String getInitials(){
        return namefield.getName()+surnamefield.getName();
    }
    public JPanel getRootpanel() {return rootpanel;}
    public JPanel getToppanel() {return toppanel;}
    public JButton getButton(){
        return button1;
    }
    public void setName(String name){namefield.setText(name);}
    public void setSurname(String surname){surnamefield.setText(surname);}
    public String getName(){return namefield.getText();}
    public String getLastName(){return surnamefield.getText();}

    public void setFocus()
    {
        namefield.requestFocusInWindow();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        mainpanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                try {
                    g.drawImage(Resourses.getBackground(),0,0,getWidth(),getHeight(),this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
        label1 = new JLabel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                try {
                    g.drawImage(Resourses.getLogo(),0,0,this.getWidth(),this.getHeight(),this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
    }
}

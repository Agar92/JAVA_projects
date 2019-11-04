package Main;

import Resourses.Resourses;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

/**
 * Created by Андрей on 18.03.2017.
 */
public class EnterPhone {

    private JPanel rootpanel;
    private JButton button1;
    private JButton button2;
    private JLabel label1;
    private JButton InputButton;

    private JPanel toppanel;

    private JFormattedTextField PhoneField;


    public EnterPhone(){

        toppanel.setBackground(Color.gray);
        Frame.setTopPanel(toppanel);
        MaskFormatter phoneMask=null;
        try {
            phoneMask=new MaskFormatter("+7(###)###-##-##");
            DefaultFormatterFactory dff=new DefaultFormatterFactory(phoneMask);
            PhoneField.setFormatterFactory(dff);
        }
        catch (ParseException pe)
        {
            pe.printStackTrace();
        }
    }

    public JPanel getRootpanel() {
        return rootpanel;
    }
    public JButton getButton1() {
        return button1;
    }
    public JButton getButton2() {
        return button2;
    }
    public JButton getInputBtn() {return InputButton;}
    public JFormattedTextField getPhoneField() {return PhoneField;}
    public JPanel getToppanel() {return toppanel;}

    public  void clearTxtPhoneNo(){
        PhoneField.setText("");

    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        label1 = new JLabel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                try {
                    g.drawImage(Resourses.getLogo(),0,0,getWidth(),getHeight(),this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
        rootpanel = new JPanel(){
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
    }
}


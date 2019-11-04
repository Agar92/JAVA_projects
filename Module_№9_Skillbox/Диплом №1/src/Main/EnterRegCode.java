package Main;

import General.TextFieldLen;
import Resourses.Resourses;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;


public class EnterRegCode {
    private JPanel rootpanel;
    private JLabel label1;
    private JLabel label2;

    private JPasswordField passwordField;

    private JPanel passpanel;

    private JButton InputButton;

    private JPanel toppanel;

    private JPanel mainpanel;

    private JLabel phonelabel;

    EnterRegCode(){
        getToppanel().setBackground(Color.gray);
        getPasspanel().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
        Frame.setTopPanel(toppanel);

        AbstractDocument txtField=(AbstractDocument) passwordField.getDocument();
        txtField.setDocumentFilter(new TextFieldLen(5));
    }

    public void setFocus()
    {
        passwordField.requestFocusInWindow();
    }

    public JPanel getRootpanel() {return rootpanel;}
    public JPasswordField getPasswordField() {return passwordField;}
    public JPanel getPasspanel() {return passpanel;}
    public JButton getInputButton() {return InputButton;}
    public JPanel getToppanel() {return toppanel;}
    public JPanel getMainpanel() {return mainpanel;}
    public JLabel getPhonelabel() {return phonelabel;}

    public  void setPhoneLabel(String phone){
        Frame.enterregcode.getPhonelabel().setText(Frame.enterphone.getPhoneField().getText());
    }

    public String getPassWord(){
        String password = "";
        for(int i=0;i<passwordField.getPassword().length;i++){
            password=password+passwordField.getPassword()[i];
        }
        return password;
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here

        label1 = new JLabel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                try {
                    g.drawImage(Resourses.getLogoMini(),0,0,getWidth(),getHeight(),this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
        label2 = new JLabel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                try {
                    g.drawImage(Resourses.getIconPhone(),0,0,22,36,this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
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
    }
}

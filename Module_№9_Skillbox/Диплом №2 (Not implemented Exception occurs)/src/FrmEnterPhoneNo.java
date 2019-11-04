import Resourses.Resourses;
import GUI.SetIcon;
import General.CommonProcs;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.*;



public class FrmEnterPhoneNo extends JPanel{
    private JPanel panEnterPhoneNo;
    private JButton btnCont;
    private JTextPane lblEnterPhone;
    private JFormattedTextField txtPhoneNo;
    private JPanel panPhoneNo;
    private JPanel panPhoneIcon;
    private JPanel panLogo;
    private JPanel rootpanel;

    public JButton getBtnCont() {
        return btnCont;
    }

    public JPanel getPanEnterPhoneNo() {
        return panEnterPhoneNo;
    }

    public JPanel getRootpanel() {return rootpanel;}

    public JFormattedTextField getTxtPhoneNo() {
        return txtPhoneNo;
    }

    public FrmEnterPhoneNo()
    {
        lblEnterPhone.setFont(Resourses.getLightPlain20());
        lblEnterPhone.setForeground(Color.WHITE);
        CommonProcs.allignTxtPane(lblEnterPhone, CommonProcs.CENTER);

        panPhoneNo.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.WHITE));
        txtPhoneNo.setCaretColor(Color.WHITE);
        txtPhoneNo.setForeground(Color.WHITE);
        txtPhoneNo.setBorder(BorderFactory.createEmptyBorder());

        MaskFormatter phoneMask=null;
        try {
            phoneMask=new MaskFormatter("+7(###)###-##-##");
            DefaultFormatterFactory dff=new DefaultFormatterFactory(phoneMask);
            txtPhoneNo.setFormatterFactory(dff);
        }
        catch (ParseException pe)
        {
            pe.printStackTrace();
        }
        txtPhoneNo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar()==KeyEvent.VK_ENTER)
                    btnCont.doClick();
            }
        });
    }

    public String getPhoneNo(){
        try {
            txtPhoneNo.commitEdit();
            return txtPhoneNo.getValue().toString();
        } catch (ParseException e) {
            return null;
        }
    }

    private void createUIComponents() throws IOException {
        // TODO: place custom component creation code here
        panEnterPhoneNo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Resourses.getBackground(),0,0,getWidth(),getHeight(),this);
            }
        };
        panLogo=new SetIcon(Resourses.getLogo());
        panPhoneIcon=new SetIcon(Resourses.getIconPhone());
    }


    public void clearTxtPhoneNo() {
        this.txtPhoneNo.setText("");
        txtPhoneNo.requestFocusInWindow();
    }
}

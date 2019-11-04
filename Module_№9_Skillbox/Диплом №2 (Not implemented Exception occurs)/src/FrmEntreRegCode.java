import General.TextFieldLen;
import GUI.SetIcon;
import Resourses.Resourses;
import General.CommonProcs;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class FrmEntreRegCode extends JPanel{
    private JPanel panEnterRegCode;
    private JButton btnCont;
    private JLabel lblPhoneNo;
    private JPasswordField pswSmsCode;
    private JTextPane lblEnterRegCode;
    private JPanel panMiniLogo;
    private JPanel panRegCode;
    private JPanel panLock;
    private JPanel rootpanel;

    public JPasswordField getPswSmsCode() {
        return pswSmsCode;
    }

    public JButton getBtnCont() {
        return btnCont;
    }

    public JPanel getRootpanel() {return rootpanel;}

    public void setLblPhoneNo(String lblPhoneNo) {
        this.lblPhoneNo.setText(lblPhoneNo);
    }

    public JPanel getPanEnterRegCode() {
        return panEnterRegCode;
    }

    public FrmEntreRegCode() {
        lblPhoneNo.setForeground(Color.white);

        lblEnterRegCode.setFont(Resourses.getLightPlain15());
        lblEnterRegCode.setForeground(Color.WHITE);
        CommonProcs.allignTxtPane(lblEnterRegCode,CommonProcs.CENTER);

        panEnterRegCode.setBorder(BorderFactory.createMatteBorder(0,0,2,0, Color.WHITE));

        pswSmsCode.setForeground(Color.WHITE);
        pswSmsCode.setCaretColor(Color.white);
        pswSmsCode.setBorder(BorderFactory.createEmptyBorder());
        AbstractDocument txtField=(AbstractDocument) pswSmsCode.getDocument();
        txtField.setDocumentFilter(new TextFieldLen(5));
        pswSmsCode.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            if(e.getKeyChar()==KeyEvent.VK_ENTER)
                btnCont.doClick();
        }
    });
}

    public String getValSmsCode()
    {
        return String.valueOf(pswSmsCode.getPassword()).replaceAll("[^\\p{Digit}]","");
    }

    public void setFocus()
    {
        pswSmsCode.requestFocusInWindow();
    }

    private void createUIComponents() throws IOException {
        // TODO: place custom component creation code here
        panEnterRegCode=new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Resourses.getBackground(),0,0,getWidth(),getHeight(),this);
            }
        };

        panMiniLogo=new SetIcon(Resourses.getLogoMini());
        panLock = new SetIcon(Resourses.getIconLock());
    }
}

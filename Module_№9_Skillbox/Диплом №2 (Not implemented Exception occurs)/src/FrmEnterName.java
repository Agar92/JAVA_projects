import Resourses.Resourses;
import GUI.SetIcon;
import General.CommonProcs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class FrmEnterName extends JPanel{
    private JPanel panEnterName;
    private JButton btnEndReg;
    private JTextField txtName;
    private JTextField txtLastName;
    private JTextPane lblEnterName;
    private JPanel panMiniLogo;
    private JPanel panPhoto;
    private JPanel panFullName;
    private JPanel panName;
    private JPanel rootpanel;

    public FrmEnterName() throws IOException {
        Font nameFont= Resourses.getRegPlain20();

        lblEnterName.setForeground(Color.WHITE);
        lblEnterName.setFont(Resourses.getLightPlain15());
        CommonProcs.allignTxtPane(lblEnterName,CommonProcs.CENTER);

        txtName.setCaretColor(Color.WHITE);
        txtName.setForeground(Color.WHITE);
        txtName.setFont(nameFont);
        txtName.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.WHITE));
        txtName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar()==KeyEvent.VK_ENTER)
                    txtName.transferFocus();
            }
        });

        txtLastName.setCaretColor(Color.WHITE);
        txtLastName.setForeground(Color.WHITE);
        txtLastName.setFont(nameFont);
        txtLastName.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.WHITE));
        txtLastName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar()==KeyEvent.VK_ENTER)
                    btnEndReg.doClick();
            }
        });
        txtName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txtName.selectAll();
            }
        });
        txtLastName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txtLastName.selectAll();
            }
        });
    }

    public JButton getBtnEndReg() {
        return btnEndReg;
    }

    public JTextField getTxtName() {
        return txtName;
    }

    public JTextField getTxtLastName() {
        return txtLastName;
    }


    public void setFocus()
    {
        txtName.requestFocusInWindow();
    }

    private void createUIComponents() throws IOException {
        // TODO: place custom component creation code here
        panEnterName=new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Resourses.getBackground(),0,0,getWidth(),getHeight(),this);
            }
        };

        panMiniLogo=new SetIcon(Resourses.getLogoMini());
    }

    public JPanel getRootpanel() {return rootpanel;}
}

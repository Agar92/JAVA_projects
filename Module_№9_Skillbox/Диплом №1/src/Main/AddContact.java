package Main;

import GUI.FuncButton;
import GUI.SetIcon;
import General.CommonProcs;
import Resourses.Resourses;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.ParseException;

public class AddContact extends JPanel{
    private JPanel panAddContact;
    private JPanel panAddCenter;
    private JPanel panPhoneNo;
    private JPanel panPhoneIcon;
    private JFormattedTextField txtPhoneNo;
    private JTextPane lblEnterPhoneNo;
    private JPanel panContactFound;
    private JPanel panAvatar;
    private JButton btnAdd;
    private JLabel lblAddContact;
    private JLabel lblContactFound;
    private JLabel lblContactName;
    private JPanel panContactSouth;
    private JButton btnBack;
    private JPanel panBottomLeft;


    public JButton getBtnBack() {
        return btnBack;
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }



    public JPanel getPanAddContact() {
        return panAddContact;
    }

    public AddContact() {
        panAddContact.setBackground(Color.BLACK);
        lblEnterPhoneNo.setFont(Resourses.getLightBold15());
        lblEnterPhoneNo.setForeground(Color.WHITE);
        CommonProcs.allignTxtPane(lblEnterPhoneNo, CommonProcs.CENTER);

        lblAddContact.setFont(Resourses.getRegBold35());
        lblAddContact.setForeground(CommonProcs.BLUE_COLOR);

        lblContactName.setFont(Resourses.getLightReg22());

        panPhoneNo.setBorder(BorderFactory.createMatteBorder(0,0,2,0, Color.WHITE));
        txtPhoneNo.setBorder(BorderFactory.createEmptyBorder());
        txtPhoneNo.setFont(Resourses.getRegBold33());
        txtPhoneNo.setForeground(Color.WHITE);
        txtPhoneNo.setCaretColor(Color.WHITE);

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
                    btnAdd.doClick();
            }
        });
    }

    public void setFocus()
    {
        txtPhoneNo.requestFocusInWindow();
    }

    private void createUIComponents() throws IOException {
        // TODO: place custom component creation code here
        panAddContact=this;
        panPhoneIcon=new SetIcon(Resourses.getIconPhone());
        panAvatar=new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(Resourses.getIconUser(),0,0,getWidth(),getHeight(),this);
            }
        };
        btnBack=new FuncButton(Resourses.getIconBack());
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        //super.paintComponent(graphics);
        Color color = Color.black;
        graphics.setColor(CommonProcs.makeTransparent(color, 0.7f));
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}

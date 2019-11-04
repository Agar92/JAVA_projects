package Main;

import GUI.FuncButton;
import GUI.SetIcon;
import General.CommonProcs;
import Resourses.Resourses;
import org.javagram.dao.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;


public class EditContact extends JPanel{
    private JPanel panEditContact;
    private JTextPane lblEditContact;
    private JPanel panCenter;
    private JPanel panBottom;
    private JPanel panContact;
    private JPanel panAvatar;
    private JLabel lblPhoneNo;
    private JPanel panDelete;
    private JButton btnDelete;
    private JButton btnSave;
    private JButton btnBack;
    private JTextField txtContactName;
    private JPanel panDelIcon;
    private JPanel panBottomLeft;

    public EditContact() {
        lblEditContact.setFont(Resourses.getLightPlain35());
        lblEditContact.setForeground(CommonProcs.BLUE_COLOR);
        CommonProcs.allignTxtPane(lblEditContact, CommonProcs.CENTER);

        txtContactName.setFont(Resourses.getRegPlain28());
        txtContactName.setCaretColor(Color.WHITE);
        txtContactName.setForeground(Color.WHITE);
        txtContactName.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.WHITE));

        panDelete.setBorder(BorderFactory.createLineBorder(CommonProcs.RED_COLOR,2));

        btnDelete.setBorder(BorderFactory.createEmptyBorder());

        txtContactName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txtContactName.selectAll();
            }
        });
    }

    public JButton getBtnBack() {
        return btnBack;
    }

    public JButton getBtnSave() {
        return btnSave;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public JPanel getPanEditContact() {
        return panEditContact;
    }



    private void createUIComponents() throws IOException {
        // TODO: place custom component creation code here
        panEditContact=this;
        btnBack=new FuncButton(Resourses.getIconBack());
        panDelIcon=new SetIcon(Resourses.getIconTrash());
        panAvatar=new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(Resourses.getIconUser(),0,0,getWidth(),getHeight(),this);
            }
        };
    }

    public void setFocus()
    {
        txtContactName.requestFocusInWindow();
    }

    public void setLblPhoneNo(String lblPhoneNo) {
        this.lblPhoneNo.setText(lblPhoneNo);
    }


    @Override
    protected void paintComponent(Graphics graphics) {
        //super.paintComponent(graphics);
        Color color = Color.black;
        graphics.setColor(CommonProcs.makeTransparent(color, 0.7f));
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    public void setContactName(Person person)
    {
        txtContactName.setText(person.getFirstName()+" "+person.getLastName());

    }
}

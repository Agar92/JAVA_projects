package Main;

import GUI.FuncButton;
import General.CommonProcs;
import Resourses.Resourses;
import com.sun.org.apache.regexp.internal.RE;
import org.javagram.dao.Me;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;

public class UserProfile extends JPanel{
    private JPanel panUserProf;
    private JPanel panCenter;
    private JTextPane txtProfSetup;
    private JPanel panUserName;
    private JPanel panAvatar;
    private JTextField txtName;
    private JTextField txtLastName;
    private JButton btnSave;
    private JPanel panUserSouth;
    private JLabel lblPhoneNo;
    private JPanel panBottomLeft;
    private JButton btnBack;
    private JButton btnEdit;
    private JButton btnDelete;
    private JPanel panBottomRight;
    private JButton btnExit;
    private Me im;

    public UserProfile()
    {
        panUserProf.setBackground(Color.BLACK);
        txtProfSetup.setFont(Resourses.getLightBold30());
        txtProfSetup.setForeground(CommonProcs.BLUE_COLOR);
        CommonProcs.allignTxtPane(txtProfSetup, CommonProcs.CENTER);

        txtName.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.WHITE));
        txtName.setFont(Resourses.getLightPlain25());
        txtName.setCaretColor(Color.WHITE);
        txtName.setForeground(Color.WHITE);
        txtName.setBorder(BorderFactory.createEmptyBorder());

        txtLastName.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.WHITE));
        txtLastName.setFont(Resourses.getLightPlain25());
        txtLastName.setCaretColor(Color.WHITE);
        txtLastName.setForeground(Color.WHITE);
        txtLastName.setBorder(BorderFactory.createEmptyBorder());

        btnExit.setForeground(CommonProcs.BLUE_COLOR);
        btnExit.setBorder(BorderFactory.createMatteBorder(0,0,2,0,CommonProcs.BLUE_COLOR));

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

    private void createUIComponents() throws IOException {
        // TODO: place custom component creation code here
        panUserProf=this;
        btnBack=new FuncButton(Resourses.getIconBack());
        panAvatar=new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(Resourses.getIconUser(),0,0,getWidth(),getHeight(),this);
            }
        };
        btnEdit=new FuncButton(Resourses.getIconEdit());
        btnDelete=new FuncButton(Resourses.getIconTrash());

    }

    public void setLblPhoneNo(String lblPhoneNo) {
        this.lblPhoneNo.setText(lblPhoneNo);
    }

    public JPanel getPanUserProf() {
        return panUserProf;
    }

    public JButton getBtnSave() {
        return btnSave;
    }

    public JButton getBtnBack() {
        return btnBack;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public JButton getBtnExit() {
        return btnExit;
    }

    public void setFocus()
    {
        txtName.requestFocusInWindow();
    }

    public void updateMe(Me im)
    {
        txtName.setText(im.getFirstName());
        txtLastName.setText(im.getLastName());
        lblPhoneNo.setText(im.getPhoneNumber());
        setFocus();
    }


    @Override
    protected void paintComponent(Graphics graphics) {
        //super.paintComponent(graphics);
        Color color = Color.black;
        graphics.setColor(CommonProcs.makeTransparent(color, 0.7f));
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}

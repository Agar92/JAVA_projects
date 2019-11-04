package Main;

import GUI.SetIcon;
import General.CommonProcs;
import Resourses.Resourses;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Message extends JPanel{
    private JPanel panRoot;
    private JPanel panIncomingIcon;
    private JPanel panOutgoingIcon;
    private JTextArea txtMessage;
    private JLabel lblDateTime;
    private JPanel panMsg;
    private JPanel panMessageTop;
    private JPanel panMessageBottom;
    private BufferedImage messageOutlineTop, messageOutlineBottom;

    {
        panMsg.setPreferredSize(null);
        txtMessage.setLineWrap(true);
        txtMessage.setWrapStyleWord(true);
        txtMessage.setSize(307, Short.MAX_VALUE);
        txtMessage.setFont(Resourses.getLightBold15());
        txtMessage.setEditable(false);
    }


    public void setTxtMessage(String txtMessage) {
        this.txtMessage.setText(txtMessage);
    }

    public void setLblDateTime(String lblDateTime) {
        this.lblDateTime.setText(lblDateTime);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        panRoot=this;
        panIncomingIcon=new SetIcon(Resourses.getIconIncoming());
        panOutgoingIcon=new SetIcon(Resourses.getIconOutgoing());
        panMessageBottom=new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(messageOutlineBottom,0,0,getWidth(),9,this);
            }
        };
        panMessageTop=new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {

                g.drawImage(messageOutlineTop,0,0,getWidth(),10,this);
            }
        };
    }

    public void setPanelColor(boolean isIncoming)
    {
        if(isIncoming){
            panIncomingIcon.setVisible(true);
            panOutgoingIcon.setVisible(false);
            panMsg.setBackground(CommonProcs.BLUE_COLOR);
            txtMessage.setBackground(CommonProcs.BLUE_COLOR);
            messageOutlineTop= Resourses.getInMsgTop();
            messageOutlineBottom= Resourses.getInMsgBottom();
            panMsg.repaint();
        } else {
            panIncomingIcon.setVisible(false);
            panOutgoingIcon.setVisible(true);
            panMsg.setBackground(CommonProcs.VIOLET_COLOR);
            txtMessage.setBackground(CommonProcs.VIOLET_COLOR);
            txtMessage.setForeground(Color.WHITE);
            messageOutlineTop= Resourses.getOutMsgTop();
            messageOutlineBottom= Resourses.getOutMsgBottpm();
            panMsg.repaint();
        }
    }
}

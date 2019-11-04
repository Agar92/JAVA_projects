import GUI.*;
import General.CommonProcs;
import Resourses.Resourses;
import org.javagram.dao.Me;
import org.javagram.dao.Message;
import org.javagram.dao.Person;
import org.javagram.dao.proxy.TelegramProxy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;


public class FrmContactList extends JPanel{

    private JPanel panContactList;
    private JPanel panMainNorth;
    private JPanel panTopLeft;
    private JPanel panMicrologo;
    private JPanel panTopRight;
    private JPanel panUserAvatar;
    private JLabel lblUserName;
    private JButton btnSettings;
    private JPanel panMainWest;
    private JPanel panFind;
    private JTextField txtFind;
    private JButton btnSearch;
    private JButton btnSend;
    private JTextArea txtToSend;
    private JScrollPane scrlMessage;
    private JPanel panMessages;
    private JPanel panContactAvatar;
    private JLabel lblContactName;
    private JButton btnEdit;
    private JScrollPane scrlToSend;
    private JPanel panMainCenter;
    private JPanel panTopChat;
    private JPanel panTopChatLeft;
    private JPanel panTopChatRight;
    private JPanel panSendSMS;
    private JPanel panChat;
    private JPanel panChatList;
    private JPanel rootpanel;
    private TelegramProxy telegramProxy;
    private final int width=150;
    private final int maxMessageCount=100;
    private final DateFormat dateFormat = new SimpleDateFormat("HH:mm dd MMMM yyyy");
    private int personId;
    private int i=0;

    public FrmContactList()
    {
        Elements.decorateScrollPane(scrlToSend);
        Elements.decorateScrollPane(scrlMessage);
        panContactList.setBorder(BorderFactory.createLineBorder(CommonProcs.VIOLET_COLOR, 5));
        txtFind.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txtFind.selectAll();
            }
        });
    }

    private void createUIComponents() throws IOException{
        // TODO: place custom component creation code here
        panContactList=this;
        panMicrologo=new SetIcon(Resourses.getLogoMicro());
        panUserAvatar=new SetIcon(Resourses.getMaskBlueMini());
        btnSettings=new FuncButton(Resourses.getIconSetting());
        btnSearch=new FuncButton(Resourses.getIconSearch());
        btnSend=new FuncButton(Resourses.getButtonSend());
        panContactAvatar=new SetIcon(Resourses.getMaskWhiteMini());
        btnEdit=new FuncButton(Resourses.getIconEdit());
    }

    {
        btnEdit.setEnabled(false);
        scrlToSend.setBorder(BorderFactory.createEmptyBorder());
        scrlMessage.setBorder(BorderFactory.createEmptyBorder());
        txtFind.setBorder(BorderFactory.createEmptyBorder());
    }

    public void setUpChat(Person person)
    {

        panMessages.removeAll();
        panMessages.setLayout(new BoxLayout(panMessages, BoxLayout.Y_AXIS));
        panMessages.add(Box.createGlue());

        if(person == null)
            return;
        personId=person.getId();
        List<Message> messages = telegramProxy.getMessages(person, maxMessageCount);
        for(int i = messages.size() - 1; i >= 0 ; i--)
        {
            Message message = messages.get(i);
            FrmMessage panel=new FrmMessage();
            if(message.getReceiver() instanceof Me)
            {
                panel.setPanelColor(true);
                panel.setAlignmentX(RIGHT_ALIGNMENT);
            } else if(message.getSender() instanceof Me) {
                panel.setPanelColor(false);
                panel.setAlignmentX(0.00000005f);
            }
            panel.setPreferredSize(null);
            panel.setTxtMessage(message.getText());
            panel.setLblDateTime(dateFormat.format(message.getDate()));
            panMessages.add(panel);
            panMessages.revalidate();
            panMessages.repaint();
        }
        scrlMessage.getVerticalScrollBar().setValue(scrlMessage.getVerticalScrollBar().getMaximum());
    }



    public int getPersonId()
    {
        return this.personId;
    }


    public void setTxtToSend(String txtToSend) {
        this.txtToSend.setText(txtToSend);
    }

    public void setFocus()
    {
        txtFind.transferFocus();
        txtFind.requestFocus();
    }

    public JTextArea getTxtToSend() {
        return txtToSend;
    }

    public void setLblUserName(String lblUserName) {
        this.lblUserName.setText(lblUserName);
    }

    public JButton getBtnSend() {
        return btnSend;
    }

    public JButton getBtnSearch() {
        return btnSearch;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public JButton getBtnSettings() {
        return btnSettings;
    }

    public void setTxtFind(String txtFind) {
        this.txtFind.setText(txtFind);
    }

    public void setLblContactName(String lblContactName) {
        this.lblContactName.setText(lblContactName);
    }

    public void setTelegramProxy(TelegramProxy telegramProxy) {
        this.telegramProxy = telegramProxy;
    }

    public JPanel getPanContactList() {
        return panContactList;
    }

    public JTextField getTxtFind() {
        return txtFind;
    }

    public void setContactsPanel(Component contactsPanel) {
        this.panChatList.removeAll();
        this.panChatList.add(contactsPanel);
    }

    public JPanel getRootpanel() {return rootpanel;}
}

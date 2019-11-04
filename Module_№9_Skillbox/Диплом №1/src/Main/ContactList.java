package Main;

import GUI.Elements;
import Resourses.Resourses;
import org.javagram.dao.Me;
import org.javagram.dao.Message;
import org.javagram.dao.Person;
import org.javagram.dao.proxy.TelegramProxy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Андрей on 26.03.2017.
 */
public class ContactList extends JPanel{
    private JPanel rootpanel;
    private JPanel toppanel;
    private JLabel label1;
    private JButton btnSettings;
    private JTextArea lblContactName;
    private JLabel masklabel;
    private JPanel leftpanel;
    private JPanel rightpanel;
    private JTextField txtFind;
    private JLabel iconlabel;
    private JScrollPane scroll1;
    private JButton buttonsend;
    private JLabel labelsearch;
    private JPanel panMessages;
    private JTextArea textToSend;
    private JPanel bluepanel;
    private JPanel abovepanel;
    private JPanel panel1;
    private JPanel chatpanel;
    private JPanel infolabel;
    private JScrollPane scrlMessage;
    private JButton editbutton;
    private JLabel lbluser;
    private JScrollPane scrlToSend;
    private JButton buttonSearch;

    private TelegramProxy telegramProxy;
    private int personId;
    private Me im;
    private final int maxMessageCount=100;
    private final DateFormat dateFormat = new SimpleDateFormat("HH:mm dd MMMM yyyy");

    ContactList(){
        Frame.setTopPanel(toppanel);
        lblContactName.setText(Frame.entername.getInitials());
        buttonsend.setBorder(BorderFactory.createLineBorder(Color.blue, 1, true));
        Frame.frame.getRootPane().setDefaultButton(buttonsend);
        txtFind.setBorder(null);
        panel1.setBorder(BorderFactory.createMatteBorder(0,0, 1, 1, Color.gray));
        chatpanel.setBorder(BorderFactory.createMatteBorder(0,0, 0, 1, Color.gray));
        infolabel.setBorder(BorderFactory.createMatteBorder(0,0, 1, 0, Color.gray));
        textToSend.setBorder(BorderFactory.createLineBorder(new Color(0xE0E0E0), 1, true));
        txtFind.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txtFind.selectAll();
            }
        });
        Elements.decorateScrollPane(scrlToSend);
        Elements.decorateScrollPane(scrlMessage);
    }

    public JPanel getRootpanel(){
        return rootpanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        textToSend = new JTextArea("Ваше сообщение");
        label1 = new JLabel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                try {
                    g.drawImage(Resourses.getLogoMicro(),0,0,getWidth(),getHeight(),this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        btnSettings = new JButton(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                try {
                    g.drawImage(Resourses.getIconSettings(),0,0,getWidth(),getHeight(),this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        masklabel = new JLabel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                try {
                    g.drawImage(Resourses.getMaskBlueMini(),0,0,getWidth(),getHeight(),this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        buttonSearch = new JButton(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                try {
                    g.drawImage(Resourses.getSearchIcon(),0,0,getWidth(),getHeight(),this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        editbutton = new JButton(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                try {
                    g.drawImage(Resourses.getIconEdit(),0,0,getWidth(),getHeight(),this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        iconlabel = new JLabel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                try {
                    g.drawImage(Resourses.getMaskWhiteMini(),0,0,getWidth(),getHeight(),this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        buttonsend = new JButton(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                try {
                    g.drawImage(Resourses.getIconSend(),0,0,getWidth(),getHeight(),this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void setTelegramProxy(TelegramProxy telegramProxy) {
        this.telegramProxy = telegramProxy;
    }


    public void setUpChat(Person person)
    {

        panMessages.removeAll();
        panMessages.setLayout(new BoxLayout(panMessages, BoxLayout.Y_AXIS));
        panMessages.add(Box.createGlue());

        if(person == null)
            return;
        personId=person.getId();
        java.util.List<Message> messages = telegramProxy.getMessages(person, maxMessageCount);
        for(int i = messages.size() - 1; i >= 0 ; i--)
        {
            Message message = messages.get(i);
            Main.Message panel=new Main.Message();
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

    public JTextField getTxtFind() {
        return txtFind;
    }

    public int getPersonId()
    {
        return this.personId;
    }

    public JTextArea getLblContactName() {
        return lblContactName;
    }

    public void setLblContactName(String lblContactName) {
        this.lblContactName.setText(lblContactName);
    }

    public JButton getBtnEdit(){return editbutton;}

    public JPanel getToppanel(){return toppanel;}


    public void setLblUser() {
        this.lbluser.setText(im.getFirstName()+" "+im.getLastName());
    }


    public void setFocus()
    {
        txtFind.transferFocus();
        txtFind.requestFocus();
    }

    public void setContactsPanel(Component contactsPanel) {
        this.chatpanel.removeAll();
        this.chatpanel.add(contactsPanel);
    }
    public JTextArea getTxtToSend() {
        return textToSend;
    }
    public void setTxtToSend(String txtToSend) {
        this.textToSend.setText(txtToSend);
    }
    public JButton getBtnSettings() {return btnSettings;}
    public JButton getBtnSearch() {return buttonSearch;}
    public JButton getBtnSend() {return buttonsend;}
}


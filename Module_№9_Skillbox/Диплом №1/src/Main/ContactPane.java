package Main;

import General.CommonProcs;
import Resourses.Resourses;
import org.javagram.dao.Person;
import org.javagram.dao.proxy.TelegramProxy;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;



public class ContactPane extends JPanel implements ListCellRenderer{
    private JPanel panContact;
    private JLabel lblName;
    private JLabel lblTime;
    private JLabel lblMsg;
    private JPanel panAvatar;
    private Color bg;
    private TelegramProxy telegramProxy;
    private Person person;
    private org.javagram.dao.Dialog dlg;
    private final DateFormat fullDateFormat = new SimpleDateFormat("HH:mm dd MMM yyyy");
    private final DateFormat timeFormat=new SimpleDateFormat("HH:mm");
    private final DateFormat dayFormat=new SimpleDateFormat("dd-MM");
    private final DateFormat monthFormat=new SimpleDateFormat("MM-YY");
    private BufferedImage imageIcon;

    public JLabel getlblName() {
        return lblName;
    }
    public ContactPane(TelegramProxy telegramProxy)
    {
        this.telegramProxy=telegramProxy;
        //Рекомендуемый размер является минимальным
        //Меньше JScrollPane ужимать не будет
        setPreferredSize(null);
        //А вот setMinimumSize(), вопреки здравому смыслу, вызывать не стоит
        bg=getBackground();
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object cont, int index, boolean isSelected, boolean cellHasFocus) {
        person=(Person) cont;
        if(isSelected)
            selected();
        else
            unselected();
        lblName.setText(person.getFirstName()+" "+person.getLastName());
        dlg=telegramProxy.getDialog(person);
        if(dlg!=null) {
            lblMsg.setText(dlg.getLastMessage().getText());
            lblTime.setText(timeFormat.format(dlg.getLastMessage().getDate()));
        } else {
            lblMsg.setText("");
            lblTime.setText("");
        }
        return this;
    }

    private void createUIComponents(){
        // TODO: place custom component creation code here
        panContact=this;
        panAvatar=new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(imageIcon,0,0,null);
            }
        };
    }

    private void selected() {
        //setBackground(Color.WHITE);
        setOpaque(true);
        setBorder(BorderFactory.createMatteBorder(1,5,1,0, CommonProcs.BLUE_COLOR));
        if(telegramProxy.isOnline(person))
            imageIcon= Resourses.getMaskWhiteOnline();
        else
            imageIcon= Resourses.getMaskWhite();
        repaint();
    }

    private void unselected(){
        //setBackground(bg);
        setOpaque(false);
        setBorder(BorderFactory.createMatteBorder(0,0,0,1, CommonProcs.BLUE_COLOR));
        if(telegramProxy.isOnline(person))
            imageIcon= Resourses.getMaskGrayOnline();
        else
            imageIcon= Resourses.getMaskGray();
        repaint();
    }
}

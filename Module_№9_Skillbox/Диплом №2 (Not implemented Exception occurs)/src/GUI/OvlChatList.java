package GUI;

import GUI.Elements;
import org.javagram.dao.Person;

import javax.swing.*;


public class OvlChatList extends JPanel{
    private JScrollPane panScrList;
    private JList lstChatList;
    private JPanel panChat;

    {
        Elements.decorateScrollPane(panScrList);
        panScrList.setBorder(BorderFactory.createEmptyBorder());
    }

    public void setSelectedValue(Person person) {
        if(person != null) {
            ListModel<Person> model = getLstChatList().getModel();
            for (int i = 0; i < model.getSize(); i++) {
                if (model.getElementAt(i).equals(person)) {
                    lstChatList.setSelectedIndex(i);
                }
            }
        }
        else
            lstChatList.clearSelection();
    }

    public JList getLstChatList() {
        return lstChatList;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        panChat=this;
    }
}

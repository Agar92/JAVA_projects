import GUI.FuncButton;
import Resourses.Resourses;

import javax.swing.*;
import java.awt.*;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class TopPanel extends JPanel{

    private JButton button1;
    private JButton button2;
    private JPanel toppanel;
    private JPanel rootpanel;

    public TopPanel() {
        toppanel.setBackground(Color.gray);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int state = Frame.ICONIFIED;
                Loader.frame.setExtendedState(state);
            }
        });
    }

    public JPanel getToppanel() {return toppanel;}

    private void createUIComponents() throws IOException {
        // TODO: place custom component creation code here
        button1 = new FuncButton(Resourses.getIconClose());
        button2 = new FuncButton(Resourses.getIconHide());
    }
}

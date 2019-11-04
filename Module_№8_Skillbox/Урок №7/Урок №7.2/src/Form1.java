import javax.swing.*;

import java.awt.*;

import static java.awt.Label.CENTER;

/**
 * Created by Анд on 02.12.2016.
 */
public class Form1 extends JFrame {
    private JPanel panel1;
    private JTextField textField1;
    private JButton button1;
    private JLabel lbl1;
    private JPanel panelle;

    Form1(){
        setContentPane(getrootpanel());
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        lbl1.setHorizontalAlignment(JLabel.CENTER);
        panelle.setForeground(Color.GREEN);

    }

    public JPanel getrootpanel() {
        return panel1;
    }

    public JTextField gettextfield() {
        return textField1;
    }

    public JButton getbutton() {
        return button1;
    }
}

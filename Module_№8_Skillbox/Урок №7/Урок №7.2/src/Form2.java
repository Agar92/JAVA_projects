import javax.swing.*;

/**
 * Created by Анд on 02.12.2016.
 */
public class Form2 extends JFrame{

    private JPanel panel2;
    private JTextField textField1;
    private JButton button2;

    Form2(){
        setContentPane(getrootpanel());
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        setSize(250,200);
    }

    public JPanel getrootpanel() {
        return panel2;
    }
    public JTextField gettextfield() {
        return textField1;
    }

    public JButton getbutton() {
        return button2;
    }
}

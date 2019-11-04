import javax.swing.*;

/**
 * Created by Technic on 20.11.2016.
 */
public class FirstForm extends JFrame {
    private JPanel RootPanel;
    private JTextField Surname;
    private JLabel surname;
    private JLabel forname;
    private JTextField ForName;
    private JLabel patronimic;
    private JTextField Patronimic;
    private JButton Button;

    public FirstForm(){
        setContentPane(getRootPanel());
        setTitle("First ID Form");
        setSize(250,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public JPanel getRootPanel() {
        return RootPanel;
    }

    public JButton getButton() {
        return Button;
    }

    public JTextField getSurname() {
        return Surname;
    }

    public JTextField getForName() {
        return ForName;
    }

    public JTextField getPatronimic() {
        return Patronimic;
    }

}

import javax.swing.*;

/**
 * Created by Technic on 20.11.2016.
 */
public class FirstForm extends JFrame {
    private JPanel RootPanel;
    private JPanel panel1;
    private JTextField Surname;
    private JLabel lbl1;
    private JPanel panel2;
    private JLabel lbl2;
    private JTextField Forname;
    private JLabel lbl3;
    private JTextField Patronimic;
    private JButton Button;
    private JPanel panel3;
    private JLabel lbl4;
    private JLabel lbl5;
    private JPanel panel4;

    public FirstForm(){
        RootPanel.setLayout(new BoxLayout(RootPanel, BoxLayout.Y_AXIS));
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
        return Forname;
    }

    public JTextField getPatronimic() {
        return Patronimic;
    }

}

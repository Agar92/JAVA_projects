import javax.swing.*;

/**
 * Created by Technic on 20.11.2016.
 */
public class SeccondForm extends JFrame {
    private JPanel panel1;
    private JTextField IdTextField;
    private JButton Button;

    public SeccondForm(){
        setContentPane(getPanel1());
        setTitle("Seccond ID Form");
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public JButton getButton() {
        return Button;
    }

    public JTextField getIdTextField() {
        return IdTextField;
    }

    public JPanel getPanel1() {
        return panel1;
    }
}

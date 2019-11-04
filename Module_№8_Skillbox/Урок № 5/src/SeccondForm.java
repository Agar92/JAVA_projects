import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Technic on 20.11.2016.
 */
public class SeccondForm extends JFrame {
    private JPanel rootpanel;
    private JTextField IdTextField;
    private JButton Button;
    private JPanel panelle1;
    private JButton Buttonelle;
    private JPanel panelle2;
    private JPanel panelle3;

    public SeccondForm(){
        rootpanel.setLayout(new BoxLayout(rootpanel, BoxLayout.Y_AXIS));

        setContentPane(getrootpanel());
        setTitle("Seccond ID Form");
        setSize(250,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
    public JButton getButton() {
        return Buttonelle;
    }

    public JTextField getIdTextField() {
        return IdTextField;
    }

    public JPanel getrootpanel() {
        return rootpanel;
    }

}

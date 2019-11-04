import javax.swing.*;

/**
 * Created by Анд on 02.12.2016.
 */
public class Form3 extends JFrame{

    private JPanel panel3;
    private JTextArea textArea;

    Form3(){
        setContentPane(getrootpanel());
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        setSize(250,200);
    }

    public JPanel getrootpanel() {
        return panel3;
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}

import javax.swing.*;

/**
 * Created by Анд on 02.12.2016.
 */
public class Form1 {
    private JTextField TextField1;
    private JPanel rootPanel1;
    private JButton button1;
    public int TransitionAllowed;

    public Form1(){
        this.TransitionAllowed=0;
    }

    public JPanel getrootPanel1() {
        return rootPanel1;
    }

    public JButton getbutton1() {
        return button1;
    }

    public JTextField getTextField1() {
        return TextField1;
    }
}

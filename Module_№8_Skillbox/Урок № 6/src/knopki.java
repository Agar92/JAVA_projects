import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class knopki {
    public static void  main(String[] args) {
        JFrame frame = new JFrame();
        final JOptionPane optionPane = new JOptionPane(
                "Don't forget to read manuals ;)",
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.YES_NO_OPTION);
        frame.setContentPane(optionPane);
        frame.setTitle("Окошко");
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel lbl = new JLabel("LBL");
        frame.add(lbl);
        JTextField txt  = new JTextField("Key");
        frame.add(txt);
        /*for(int i = 0;i++){
            if(txt.keyPressed()==true)

        }**/
        lbl.addKeyListener(new KeyListener(){
            public void keyPressed(KeyEvent e) {
                lbl.setText(e.getKeyText(e.getKeyCode()));
                System.out.println(e.getKeyCode());
                System.out.println( e.isAltDown());
                System.out.println(e.getKeyText(e.getKeyCode()));
            }
            public void keyReleased(KeyEvent e) {

            }

            public void keyTyped(KeyEvent e) {

            }
        });
        int ar[] = {2};
        txt.addKeyListener(new KeyListener(){
            public void keyPressed(KeyEvent e) {
                txt.setText(e.getKeyText(e.getKeyCode()));
                System.out.println(e.getKeyCode());
                System.out.println(e.getKeyText(e.getKeyCode()));
                /*if(ar[0]==10)&&(e.isControlDown()==){

                }**/
            }
            public void keyReleased(KeyEvent e) {

            }

            public void keyTyped(KeyEvent e) {

            }
        });
        frame.setVisible(true);
    }
}

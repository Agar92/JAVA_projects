
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Loader {
    public static void  main(String[] args) {
        final JFrame frame = new JFrame();
        final Form form = new Form();
        frame.setContentPane(form.getRootPanel());
        frame.setTitle("Окошко");
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        final JFrame frame1 = new JFrame();
        final Form1 form1 = new Form1();
        frame1.setContentPane(form1.getRootPanel1());
        frame1.setTitle("Окошко");
        frame1.setSize(400, 130);
        frame1.setLocationRelativeTo(null);
        frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JButton btn1 = form.getButton();
        JButton btn2 = form1.getButton1();
        final JTextField f1 = form.getfield1();
        final JTextField f2 = form.getfield2();
        final JTextField f3 = form.getfield3();
        String s1 = f1.getText().trim();
        //Ничего не выводит - здесь ошибка
        System.out.println(s1);
        String s2 = f2.getText().trim();
        String s3 = f3.getText().trim();
        final String s = s1+s2+s3;
        final JTextField f = form1.getField();
        String S = f.getText();
        btn1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                f.setText(s1);
                frame1.setVisible(true);
            }
        });
        btn2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.dispose();
                String ar[] = f.getText().split(" ");
                int a = ar.length;
                if (a == 3) {
                    f1.setText(ar[0]);
                    f2.setText(ar[1]);
                    f3.setText(ar[2]);
                    frame.setVisible(true);
                }
                else frame.setVisible(true);
            }
        });
        JPanel p1 = form.getRootPanel();
        p1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyText(e.getKeyCode()).equals(KeyEvent. VK_ENTER)){
                    if (frame.isActive()){
                        frame.dispose();
                        frame1.setVisible(true);
                    }
                    else{
                        frame1.dispose();
                        frame.setVisible(true);
                    }
                    System.out.println("Enter");
                }
            }
        });
    }
}

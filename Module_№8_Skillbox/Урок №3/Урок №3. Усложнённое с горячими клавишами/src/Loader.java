import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class Loader {
    public static void  main(String[] args) {
        final JFrame frame = new JFrame();
        final Form form = new Form();
        frame.setContentPane(form.getrootPanel());
        frame.setTitle("Окошко");
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        final JFrame frame1 = new JFrame();
        final Form1 form1 = new Form1();
        frame1.setContentPane(form1.getrootPanel1());
        frame1.setTitle("Окошко");
        frame1.setSize(400, 130);
        frame1.setLocationRelativeTo(null);
        frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame1.setVisible(true);
        JButton btn1 = form.getButton();
        JButton btn2 = form1.getbutton1();
        final JTextField field1 = form.getTextField();
        final JTextField field2 = form.getTextField2();
        final JTextField field3 = form.getTextField3();
        final JTextField field4 = form1.getTextField1();
        btn1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(form.TransitionAllowed == 0){
                frame.dispose();
                frame1.setVisible(true);
                field4.setText(field1.getText().trim()+" "+field2.getText().trim()+" "+field3.getText().trim());
                    form1.TransitionAllowed = 0;
            }
            else{
                    frame.setVisible(true);
                    frame1.dispose();
                }
            }
        });
        btn2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                    if(form1.TransitionAllowed == 0){
                    frame1.dispose();
                    String [] arry = field4.getText().trim().split(" ");
                    int a =  arry.length;
                    if(a==3) {
                        field1.setText(arry[a - 1 - 2]);
                        System.out.println(arry[a - 1 - 2]);
                        field2.setText(arry[a - 1 - 1]);
                        System.out.println(arry[a - 1 - 1]);
                        field3.setText(arry[a - 1]);
                        System.out.println(arry[a - 1]);
                        frame.setVisible(true);
                    }
                    else{frame.setVisible(true);}
            }}
        });
        //
        String cancelName = "cancel";
        InputMap inputMap = form.getrootPanel().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, KeyEvent.CTRL_MASK, true), cancelName);
        ActionMap actionMap = form.getrootPanel().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                frame1.setVisible(true);
            }
        });
        InputMap inputMap1 = form1.getrootPanel1().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap1.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, KeyEvent.CTRL_MASK, true), cancelName);
        ActionMap actionMap1 = form1.getrootPanel1().getActionMap();
        actionMap1.put(cancelName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.dispose();
                frame.setVisible(true);
            }
        });


    }
}

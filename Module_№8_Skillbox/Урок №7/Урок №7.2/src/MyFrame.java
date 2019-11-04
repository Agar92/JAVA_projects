import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    private Form1 form1 = new Form1();
    private Form2 form2 = new Form2();
    private Form3 form3 = new Form3();

    public MyFrame(){
            setContentPane(form1.getrootpanel());
            getContentPane().revalidate();
            getContentPane().repaint();
            setTitle("First Pnone Number Form");
            setSize(250,200);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setVisible(true);
            form1.getbutton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(form1.gettextfield().getText().length()==11){
                        switchToSeccondForm();
                    }
                    else{
                        JOptionPane.showMessageDialog(form1, "The number is incorrect");
                    }
                }
            });
        form2.getbutton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(form2.gettextfield().getText().length()!=0){
                    switchToThirdForm();
                }
                else{
                    JOptionPane.showMessageDialog(form1, "You did not enter anything");
                }
            }
        });
    }
    public void switchToSeccondForm(){
        form1.setVisible(false);
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        form2.setVisible(true);
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    public void switchToThirdForm(){
        form2.setVisible(false);
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        form3.setVisible(true);
        getContentPane().revalidate();
        getContentPane().repaint();
    }

}

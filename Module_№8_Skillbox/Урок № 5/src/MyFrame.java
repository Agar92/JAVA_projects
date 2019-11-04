import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Admin on 16.11.2016.
 */
public class MyFrame extends JFrame {
    private FirstForm firstForm = new FirstForm();
    private SeccondForm seccondForm = new SeccondForm();

    public MyFrame(){
        /*setContentPane(firstForm.getRootPanel());
        setTitle("First ID Form");
        setSize(250,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);**/
        firstForm.setVisible(true);
        firstForm.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(firstForm.getSurname().getText().length()!=0 && firstForm.getForName().getText().length()!=0 && firstForm.getPatronimic().getText().length()!=0) {
                    switchToSeccondForm();
                }else if (firstForm.getSurname().getText().length()==0 || firstForm.getForName().getText().length()==0){
                    JOptionPane.showMessageDialog(
                            firstForm.getRootPanel(),
                            "Вы не заполнили обязательное поле Фамилия или Имя");
                }else if(firstForm.getPatronimic().getText().length()==0)
                {
                    int response = JOptionPane.showConfirmDialog
                            (
                                    firstForm.getRootPanel(),
                                    "ВЫ уверены что хотите оставить поле Отчество пустым",
                                    "Внимание",
                                    JOptionPane.YES_NO_OPTION
                            );
                    if(response == JOptionPane.NO_OPTION)
                    {
                        firstForm.getPatronimic().requestFocus();
                    }else
                    {
                        switchToSeccondForm();
                    }
                }
            }
        });
        seccondForm.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToFirstForm();
            }
        });
        firstForm.getPatronimic().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_ENTER){
                    switchToSeccondForm();
                }
            }
        });
        seccondForm.getIdTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_ENTER){
                    switchToFirstForm();
                }
            }
        });
    }
    public void switchToSeccondForm(){
        String idString = firstForm.getSurname().getText() + " " + firstForm.getForName().getText() + " " + firstForm.getPatronimic().getText();
        seccondForm.getIdTextField().setText(idString);
        firstForm.setVisible(false);
        seccondForm.setVisible(true);
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    public void switchToFirstForm(){
        String[] idString = seccondForm.getIdTextField().getText().split("\\s");
        firstForm.getSurname().setText(idString[0]);
        firstForm.getForName().setText(idString[1]);
        firstForm.getPatronimic().setText(idString[2]);
        seccondForm.setVisible(false);
        firstForm.setVisible(true);
        //getContentPane().revalidate();
        //getContentPane().repaint();
    }
}


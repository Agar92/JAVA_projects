import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Андрей on 27.10.2016.
 */
public class Form {
    public int TransitionAllowed=0;
    private JPanel rootPanel;
    private JTextField textField;
    private JButton Button;
    private JTextField textField2;
    private JTextField textField3;
    public Form(){
        Button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(textField.getText().length() == 0){
                    TransitionAllowed = 1;
                    JOptionPane.showMessageDialog(rootPanel, "1.Пожалуйста, введите имя");
                    textField.grabFocus();
                }
                else if(textField3.getText().length() == 0){
                    TransitionAllowed = 2;
                    JOptionPane.showMessageDialog(rootPanel, "2.Пожалуйста, введите фамилию");
                    textField3.grabFocus();
                }
                else if(textField2.getText().length() == 0){
                    TransitionAllowed = 3;
                    int option = JOptionPane.showConfirmDialog(rootPanel,
                            "3.Вы уверены, что не хотите вводить отчество?",
                            "Warn!", JOptionPane.YES_NO_OPTION
                            );
                    if (option == JOptionPane.YES_OPTION) {
                        textField2.grabFocus();
                    }
                    if (option == JOptionPane.NO_OPTION) {
                        textField2 .setText("NO");
                        textField2.grabFocus();
                    }
                }
                }



        });

    }

    public JPanel getrootPanel() {
        return rootPanel;
    }

    public JButton getButton() {
        return Button;
    }

    public JTextField getTextField() {
        return textField;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JTextField getTextField3() {
        return textField3;
    }
}

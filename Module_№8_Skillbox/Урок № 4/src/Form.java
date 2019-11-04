import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Андрей on 27.10.2016.
 */
public class Form {
    private JTextField textField;
    private JPanel rootPanel;
    private JButton button1;

    public Form() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(
                        rootPanel,
                        "Вы уверены, что хотите отформатировать все ваши диски?"

                );
                if (option == JOptionPane.YES_OPTION) {
                    textField.setText("YES");
                    textField.grabFocus();
                }
                if (option == JOptionPane.NO_OPTION) {
                    textField.setText("NO");
                    textField.grabFocus();
                }
                if (option == JOptionPane.CANCEL_OPTION) {
                    textField.setText("CANCEL");
                    textField.grabFocus();
                }
            }
        });
    }
        public JPanel getrootPanel() {
            return rootPanel;
        }
    }

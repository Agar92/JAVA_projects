
import javax.swing.*;
import java.awt.*;

public class Loader {
    public static void  main(String[] args) {
        JFrame frame = new JFrame();
        FormStep form = new FormStep();
        frame.setContentPane(form.getRootPanel());
        frame.setTitle("Пожалуйста, введите ваши данные");
        frame.setSize(700, 350);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
import javax.swing.*;
import java.io.IOException;

public class Loader {
    public static Frame frame;
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException {
        final int w=930;
        final int h=618;
        frame=new Frame();
        frame.setSize(w,h);
        frame.setTitle("Telegram experiments");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

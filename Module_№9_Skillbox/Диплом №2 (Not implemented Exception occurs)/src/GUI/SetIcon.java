package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SetIcon extends JPanel{
    private BufferedImage icon;
    private JComponent ancestor;

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(icon, 0, 0,null);
    }

    public SetIcon(BufferedImage icon){
        this.icon=icon;
    }
}

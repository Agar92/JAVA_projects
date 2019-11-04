package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class FuncButton extends JButton{
    private BufferedImage icon;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(icon,0,0,null);
    }

    public FuncButton(BufferedImage iconFile) throws IOException {
        setBorder(BorderFactory.createEmptyBorder());
        icon= iconFile;
    }
}

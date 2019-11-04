package test;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Board extends JPanel {

    private Image city;

    public Board() {

        initBoard();
    }

    private void initBoard() {

        loadImage();

        int w = city.getWidth(this);
        int h =  city.getHeight(this);
        setPreferredSize(new Dimension(w, h));

        System.out.println("w="+w+" h="+h);
    }

    private void loadImage() {

        ImageIcon ii = new ImageIcon("src/resources/newyork.jpg");
        city = ii.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(city, 0, 0, null);
    }
}

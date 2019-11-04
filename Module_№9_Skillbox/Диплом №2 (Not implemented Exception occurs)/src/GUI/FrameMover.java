package GUI;

import GUI.ComponentResizerExtended;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class FrameMover implements MouseListener, MouseMotionListener {

    private JFrame frame;
    private Point lastLocation;
    private ComponentResizerExtended cr;
    private boolean isResizeble;

    public FrameMover(JFrame frame, Dimension minSize, Dimension maxSize) {
        this.frame = frame;
        cr=new ComponentResizerExtended(ComponentResizerExtended.KEEP_RATIO,frame);
        Dimension defaultDimension=new Dimension(930, 618);
            try {
                cr.setMaximumSize(maxSize);
                cr.setMinimumSize(minSize);
            } catch (NullPointerException e) {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                cr.setMaximumSize(screenSize);
                cr.setMinimumSize(defaultDimension);
            }
        cr.setSnapSize(new Dimension(1,1));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastLocation=e.getLocationOnScreen();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point currentLocation=e.getLocationOnScreen();
        int deltaX=currentLocation.x-lastLocation.x;
        int deltaY=currentLocation.y-lastLocation.y;
        if(deltaX!=0||deltaY!=0)
        {
            frame.setLocation(frame.getLocation().x+deltaX, frame.getLocation().y+deltaY);
            lastLocation=currentLocation;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public void setIsResizeble(boolean isResizeble) {
        this.isResizeble = isResizeble;
    }
}

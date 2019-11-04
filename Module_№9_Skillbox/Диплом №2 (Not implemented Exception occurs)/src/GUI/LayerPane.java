package GUI;

import javax.swing.*;
import java.awt.*;


public class LayerPane extends JLayeredPane{
    @Override
    public void doLayout() {
        super.doLayout();
        super.doLayout();
        for(Component component : getComponents()) {
            component.setBounds(0, 0, this.getWidth(), this.getHeight());
        }
    }
}

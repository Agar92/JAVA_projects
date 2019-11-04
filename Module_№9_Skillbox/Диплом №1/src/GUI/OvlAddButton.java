package GUI;

import javax.swing.*;
import java.io.IOException;
import Resourses.Resourses;


public class OvlAddButton extends JPanel{
    private JPanel panAdd;
    private JButton btnAdd;
    private JPanel panButton;

    private void createUIComponents() throws IOException {
        // TODO: place custom component creation code here
        panButton=this;
        btnAdd=new FuncButton(Resourses.getIconPlus());
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }
}
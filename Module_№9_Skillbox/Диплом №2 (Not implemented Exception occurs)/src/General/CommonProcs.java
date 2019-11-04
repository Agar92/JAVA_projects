package General;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

/**
 * В классе собраны статические методы, которые могут пригодиться в проекте
 */
public class CommonProcs {
    public static final int CENTER=StyleConstants.ALIGN_CENTER;
    public static final int LEFT=StyleConstants.ALIGN_LEFT;
    public static final int JUSTIFIED=StyleConstants.ALIGN_JUSTIFIED;
    public static final String GUI_FOLDER = "guicomp/";
    public static final String FONT_SANS_REG = "fonts/OpenSansRegular.ttf";
    public static final String FONT_SANS_LIGHT = "fonts/OpenSansLight.ttf";
    public static final String FONTSANS_SEMI_BOLD = "fonts/OpenSansSemiBold.ttf";
    public static final Color BLUE_COLOR = new Color(2, 169, 219);
    public static final Color VIOLET_COLOR=new Color(75, 64, 170);
    public static final Color RED_COLOR =new Color(255,86,86);


    public static class HostData
    {
        public static final String hostAddr="149.154.167.50:443";
        public static final int appId=68588;
        public static final String appHash="11b1339bc7acda1bed464ab2313617ae";
    }

    public static void allignTxtPane(JTextPane txtPane, int align)
    {
        StyledDocument styledDocument=txtPane.getStyledDocument();
        SimpleAttributeSet center=new SimpleAttributeSet();
        StyleConstants.setAlignment(center,align);
        styledDocument.setParagraphAttributes(0,styledDocument.getLength(),center,false);
    }


    public static Color makeTransparent(Color color,float transparency) {
        if(transparency < 0.0f || transparency > 1.0f)
            throw new IllegalArgumentException();
        return new Color(color.getRed(),color.getGreen(), color.getBlue(), (int)Math.round(color.getAlpha() * transparency));
    }
}

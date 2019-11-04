package Resourses;

import General.CommonProcs;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Resourses {

    private static BufferedImage background,
            logo,
            close,
            hide,
            phone,
            logomini,
            logomicro,
            settings,
            maskBlueMini,
            maskWhiteMini,
            search,
            edit,
            send,
            plus,
            back,
            user,
            trash,
            maskGray,
            maskWhite,
            maskGrayOnline,
            maskWhiteOnline,
            iconIncoming,
            iconOutgoing,
            inMsgTop,
            inMsgBottom,
            outMsgTop,
            outMsgBottom;

    private static Font
            regBold35,
            lightPlain15,
            lightBold15,
            lightPlain20,
            lightPlain25,
            lightPlain35,
            lightBold30,
            regPlain22,
            regPlain28,
            regPlain20,
            regBold33;

    static {
        logo=loadIcon("logo.png");
        background= loadIcon("background.png");
        close = loadIcon("icon-close.png");
        hide = loadIcon("icon-hide.png");
        phone = loadIcon("icon-phone.png");
        logomini = loadIcon("logo-mini.png");
        logomicro = loadIcon("logo-micro.png");
        settings = loadIcon("icon-settings.png");
        maskBlueMini = loadIcon("mask-blue-mini.png");
        search = loadIcon("icon-search.png");
        maskWhiteMini = loadIcon("mask-white-mini.png");
        edit = loadIcon("icon-edit.png");
        send = loadIcon("button-send.png");
        plus = loadIcon("icon-plus.png");
        back = loadIcon("icon-back.png");
        user = loadIcon("User-icon.png");
        trash = loadIcon("icon-trash.png");

        maskGray=loadIcon("mask-gray.png");
        maskWhite=loadIcon("mask-white.png");
        maskGrayOnline=loadIcon("mask-gray-online.png");
        maskWhiteOnline=loadIcon("mask-white-online.png");

        iconIncoming =loadIcon("message-in-left.png");
        iconOutgoing=loadIcon("message-out-right.png");

        inMsgTop=loadIcon("message-in-top.png");
        inMsgBottom=loadIcon("message-in-bottom.png");
        outMsgTop=loadIcon("message-out-top.png");
        outMsgBottom=loadIcon("message-out-bottom.png");

        regBold33=font(CommonProcs.FONT_SANS_REG, Font.BOLD,33);
        regBold35=font(CommonProcs.FONT_SANS_REG, Font.BOLD,33);
        lightPlain15=font(CommonProcs.FONT_SANS_LIGHT,Font.PLAIN,15);
        regPlain22=font(CommonProcs.FONT_SANS_REG, Font.BOLD,22);
        regPlain28=font(CommonProcs.FONT_SANS_REG, Font.BOLD,28);
        regPlain20=font(CommonProcs.FONT_SANS_REG, Font.BOLD,20);
        lightPlain35=font(CommonProcs.FONT_SANS_LIGHT,Font.PLAIN,35);
        lightPlain20=font(CommonProcs.FONT_SANS_LIGHT,Font.PLAIN,20);
        lightPlain25=font(CommonProcs.FONT_SANS_LIGHT,Font.PLAIN,25);
        lightBold30=font(CommonProcs.FONT_SANS_LIGHT,Font.BOLD,30);
        lightBold15=font(CommonProcs.FONT_SANS_LIGHT, Font.BOLD,15);
    }
    private static BufferedImage loadIcon(String fileName) {
        try {
            return ImageIO.read(new File("Img/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
    }

    private static Font font(String fontPath, int fontType, int fontSize){
        Font font = null;
        try (InputStream inputStream = Resourses.class.getResourceAsStream(fontPath)) {
            font = Font.createFont(Font.PLAIN, inputStream).deriveFont(fontType, fontSize);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        return font;
    }


    public static BufferedImage getBackground() {
        return background;
    }

    public static BufferedImage getLogo() {
        return logo;
    }

    public static BufferedImage getClose() {return close;}

    public static BufferedImage getHide() {return hide;}

    public static BufferedImage getIconPhone() {return phone;}

    public static BufferedImage getLogoMini() {return logomini;}

    public static BufferedImage getLogoMicro() {return logomicro;}

    public static BufferedImage getIconSettings() {return settings;}

    public static BufferedImage getMaskBlueMini() {return maskBlueMini;}

    public static BufferedImage getSearchIcon() {return search;}

    public static BufferedImage getMaskWhiteMini(){return maskWhiteMini;}

    public  static BufferedImage getIconEdit(){return edit;}

    public  static BufferedImage getIconSend(){return send;}

    public  static BufferedImage getIconPlus(){return plus;}

    public static BufferedImage getIconBack(){return back;}

    public static BufferedImage getIconUser(){return user;}

    public  static BufferedImage getIconTrash(){return trash;}




    public static Font getRegBold35() {
        return regBold35;
    }

    public static Font getLightPlain15() {
        return lightPlain15;
    }

    public static Font getLightReg22() {
        return regPlain22;
    }

    public static Font getRegBold33() {
        return regBold33;
    }

    public static Font getLightPlain35() {
        return lightPlain35;
    }

    public static void setLightPlain35(Font lightPlain35) {
        Resourses.lightPlain35 = lightPlain35;
    }

    public static Font getRegPlain28() {
        return regPlain28;
    }

    public static Font getRegPlain20() {
        return regPlain20;
    }

    public static Font getLightPlain20() {
        return lightPlain20;
    }

    public static Font getLightPlain25() {
        return lightPlain25;
    }

    public static Font getLightBold30() {
        return lightBold30;
    }

    public static Font getLightBold15() {
        return lightBold15;
    }


    public static BufferedImage getMaskGray() {
        return maskGray;
    }

    public static BufferedImage getMaskWhite() {
        return maskWhite;
    }

    public static BufferedImage getMaskGrayOnline() {
        return maskGrayOnline;
    }

    public static BufferedImage getMaskWhiteOnline() {
        return maskWhiteOnline;
    }


    public static BufferedImage getIconIncoming() {
        return iconIncoming;
    }

    public static BufferedImage getIconOutgoing() {
        return iconOutgoing;
    }


    public static BufferedImage getInMsgTop() {
        return inMsgTop;
    }

    public static BufferedImage getInMsgBottom() {
        return inMsgBottom;
    }

    public static BufferedImage getOutMsgTop() {
        return outMsgTop;
    }

    public static BufferedImage getOutMsgBottpm() {
        return outMsgBottom;
    }



}

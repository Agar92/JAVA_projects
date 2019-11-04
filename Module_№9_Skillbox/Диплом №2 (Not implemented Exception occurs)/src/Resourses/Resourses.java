package Resourses;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import General.CommonProcs;

public class Resourses {
    private Resourses(){
    }

    private static BufferedImage background,
            logo,
            logoMini,
            maskGray,
            maskWhite,
            iconPhone,
            maskDarkGrayBig,
            iconBack,
            logoMicro,
            maskBlueMini,
            maskWhiteMini,
            iconPlus,
            iconSearch,
            buttonSend,
            iconEdit,
            iconSetting,
            iconLock,
            iconTrash,
            iconHide,
            iconClose,
            maskGrayOnline,
            maskWhiteOnline,
            iconIncoming,
            iconOutgoing,
            inMsgTop,
            inMsgBottom,
            outMsgTop,
            outMsgBottpm,
            userIcon,
            userIcon1;

    private static Font regBold35,
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
        logoMini=loadIcon("logo-mini.png");
        background= loadIcon("background.png");
        maskGray=loadIcon("mask-gray.png");
        maskWhite=loadIcon("mask-white.png");
        iconPhone=loadIcon("icon-phone.png");
        maskDarkGrayBig=loadIcon("mask-dark-gray-big.png");
        iconBack=loadIcon("icon-back.png");
        logoMicro=loadIcon("logo-micro.png");
        maskBlueMini=loadIcon("mask-blue-mini.png");
        maskWhiteMini=loadIcon("mask-white-mini.png");
        iconPlus=loadIcon("icon-plus.png");
        iconSearch=loadIcon("icon-search.png");
        buttonSend=loadIcon("button-send.png");
        iconEdit=loadIcon("icon-edit.png");
        iconSetting=loadIcon("icon-settings.png");
        iconLock=loadIcon("icon-lock.png");
        iconTrash=loadIcon("icon-trash.png");
        iconHide=loadIcon("icon-hide.png");
        iconClose=loadIcon("icon-close.png");
        iconIncoming =loadIcon("message-in-left.png");
        iconOutgoing=loadIcon("message-out-right.png");
        maskGrayOnline=loadIcon("mask-gray-online.png");
        maskWhiteOnline=loadIcon("mask-white-online.png");
        inMsgTop=loadIcon("message-in-top.png");
        inMsgBottom=loadIcon("message-in-bottom.png");
        outMsgTop=loadIcon("message-out-top.png");
        outMsgBottpm=loadIcon("message-out-bottom.png");
        userIcon =loadIcon("User-icon.png");
        userIcon1 =loadIcon("User-icon1.png");

        //************** fonts *******************
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
            return ImageIO.read(Resourses.class.getResource(CommonProcs.GUI_FOLDER + fileName));
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

    public static BufferedImage getLogoMini() {
        return logoMini;
    }

    public static BufferedImage getMaskGray() {
        return maskGray;
    }

    public static BufferedImage getMaskWhite() {
        return maskWhite;
    }

    public static BufferedImage getIconPhone() {
        return iconPhone;
    }

    public static BufferedImage getMaskDarkGrayBig() {
        return maskDarkGrayBig;
    }

    public static BufferedImage getIconBack() {
        return iconBack;
    }

    public static BufferedImage getLogoMicro() {
        return logoMicro;
    }

    public static BufferedImage getMaskBlueMini() {
        return maskBlueMini;
    }

    public static BufferedImage getMaskWhiteMini() {
        return maskWhiteMini;
    }

    public static BufferedImage getIconPlus() {
        return iconPlus;
    }

    public static BufferedImage getIconSearch() {
        return iconSearch;
    }

    public static BufferedImage getButtonSend() {
        return buttonSend;
    }

    public static BufferedImage getIconEdit() {
        return iconEdit;
    }

    public static BufferedImage getIconSetting() {
        return iconSetting;
    }

    public static BufferedImage getIconLock() {
        return iconLock;
    }

    public static BufferedImage getIconHide() {
        return iconHide;
    }

    public static BufferedImage getIconClose() {
        return iconClose;
    }

    public static BufferedImage getIconIncoming() {
        return iconIncoming;
    }

    public static BufferedImage getIconOutgoing() {
        return iconOutgoing;
    }

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

    public static BufferedImage getMaskGrayOnline() {
        return maskGrayOnline;
    }

    public static BufferedImage getMaskWhiteOnline() {
        return maskWhiteOnline;
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
        return outMsgBottpm;
    }

    public static BufferedImage getUserIcon() {
        return userIcon;
    }

    public static BufferedImage getUserIcon1() {
        return userIcon1;
    }

    public static BufferedImage getIconTrash() {
        return iconTrash;
    }
}

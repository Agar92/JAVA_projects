package Main;

import GUI.*;
import General.CommonProcs;
import org.javagram.TelegramApiBridge;
import org.javagram.dao.*;
import org.javagram.dao.Dialog;
import org.javagram.dao.proxy.TelegramProxy;
import org.javagram.dao.proxy.changes.UpdateChanges;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * Created by Андрей on 18.03.2017.
 */
public class Frame extends JFrame  {

    private TelegramDAO telegramDAO;
    private TelegramProxy telegramProxy;

    public static JFrame frame;
    static EnterPhone enterphone;
    static EnterRegCode enterregcode;
    static EnterName entername;
    static ContactList contactlist;
    private OverlayPanel overlayForm;
    private OvlAddButton ovlAddButton;
    private OvlChatList ovlChatList;
    private LayerPane layerpane;
    private UserProfile userprofile;
    private AddContact addcontact;
    private EditContact editcontact;

    private static final int MAIN_WINDOW = -1, PROFILE_FORM = 0, ADD_CONTACT=1, EDIT_CONTACT=2, CONTACT_LIST=3;
    private boolean isSelectEvent;

    private GUI.ComponentResizer componentResizer;
    private GUI.FrameMover frameMover;
    private GUI.ComponentMover componentMover;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    //private TelegramApiBridge tel;
    private String smsCode;
    private Me im;
    private Person person;


        {
            frame = new JFrame();
        enterphone = new EnterPhone();
        enterregcode = new EnterRegCode();
        entername = new EnterName();
        contactlist = new ContactList();
        ovlAddButton = new OvlAddButton();
        ovlChatList = new OvlChatList();
        layerpane = new LayerPane();
        userprofile = new UserProfile();
        addcontact = new AddContact();
        editcontact = new EditContact();
        addListeners();
        isSelectEvent = true;
        }

    public Frame() throws IOException {
        telegramDAO = new ApiBridgeTelegramDAO(CommonProcs.HostData.hostAddr,
                CommonProcs.HostData.appId,
                CommonProcs.HostData.appHash);
        //telegramDAO=new DebugTelegramDAO();
        Timer timer = new Timer(2000, e-> {
            if(telegramDAO.isLoggedIn())
                updateChat();
        });
        timer.start();
        frame.setContentPane(enterphone.getRootpanel());
        frame.getRootPane().setDefaultButton(enterphone.getInputBtn());
        frameMover = new FrameMover(frame, new Dimension(1500, 700), new Dimension(600, 400));
        componentResizer = new ComponentResizer(frame);
        componentMover = new ComponentMover(frame);
        ComponentMover cm = new ComponentMover(frame, enterphone.getToppanel());
        System.out.println(screenSize.getWidth() + " " + screenSize.getHeight());
        frame.setSize(1500, 700);
        frame.setLocation((int) (screenSize.getWidth() - frame.getWidth()) / 2, (int) (screenSize.getHeight() - frame.getHeight()) / 2);
        //frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.setMinimumSize(new Dimension(600, 400));
        //frame.setMaximumSize(new Dimension(900, 600));
        frame.setUndecorated(true);
        frame.pack();
        frame.setVisible(true);
        //tel=new TelegramApiBridge("149.154.167.50:443",68588,"11b1339bc7acda1bed464ab2313617ae");
    }


    private void addListeners()
    {

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                if ((JOptionPane.showConfirmDialog(getContentPane(),
                        "Вы действительно хотите выйти?",
                        "Закрыть?",
                        JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION) {
                    try {
                        telegramDAO.close();
                        System.exit(0);
                    }
                    catch (Exception e1) {
                        errorMsg(e1.getMessage());
                        System.exit(-1);
                    }
                }
            }
        });
    enterphone.getInputBtn().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        getAuthCode();
                        updateFrame(enterregcode.getRootpanel());
                        frame.getRootPane().setDefaultButton(enterregcode.getInputButton());
                        ComponentMover cm = new ComponentMover(frame, enterregcode.getToppanel());

                    }
                });
        enterregcode.getInputButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pushSMSCode();
                } catch (ApiException e1) {
                    e1.printStackTrace();
                }
                //frame.setVisible(false);
                /*updateFrame(entername.getRootpanel());
                frame.getRootPane().setDefaultButton(entername.getButton());
                ComponentMover cm = new ComponentMover(frame, entername.getToppanel());
                frame.revalidate();
                frame.repaint();*/
            }
        });
        entername.getButton().addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pushNewUserName();
                } catch (ApiException e1) {
                    e1.printStackTrace();
                }
                updateFrame(contactlist.getRootpanel());
                ComponentMover cm = new ComponentMover(frame, contactlist.getToppanel());
            }
        });
        contactlist.getBtnSettings().addActionListener(e-> {
            userprofile.updateMe(im);
            overlayForm.setIndex(PROFILE_FORM);
            userprofile.setFocus();
        });

        contactlist.getBtnSearch().addActionListener(e-> searchFor());

        contactlist.getBtnEdit().addActionListener(e-> {
            editcontact.setContactName(person);
            overlayForm.setIndex(EDIT_CONTACT);
            editcontact.setFocus();
        });

        contactlist.getTxtFind().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar()==KeyEvent.VK_ENTER)
                    searchFor();
            }
        });
        contactlist.getBtnSend().addActionListener(e->{sendSms();
        });

        ovlAddButton.getBtnAdd().addActionListener(e-> {
            overlayForm.setIndex(ADD_CONTACT);
            addcontact.setFocus();
        });

        userprofile.getBtnEdit().addActionListener(e-> JOptionPane.showInternalConfirmDialog(overlayForm,
                "Редактирование аватара",
                "Нажата кнопка",
                JOptionPane.DEFAULT_OPTION));
        userprofile.getBtnDelete().addActionListener(e-> JOptionPane.showInternalConfirmDialog(overlayForm,
                "Удаление аватара",
                "Нажата кнопка",
                JOptionPane.DEFAULT_OPTION));
        userprofile.getBtnSave().addActionListener(e-> JOptionPane.showInternalConfirmDialog(overlayForm,
                "Сохранение данных профиля",
                "Нажата кнопка",
                JOptionPane.DEFAULT_OPTION));
        userprofile.getBtnBack().addActionListener(e-> {
            overlayForm.setIndex(MAIN_WINDOW);
            contactlist.setFocus();
        });
        userprofile.getBtnExit().addActionListener(e-> JOptionPane.showInternalConfirmDialog(overlayForm,
                "Выход из программы или в предыдущее окно?",
                "Нажата кнопка",
                JOptionPane.DEFAULT_OPTION));

        addcontact.getBtnBack().addActionListener(e-> {
            overlayForm.setIndex(MAIN_WINDOW);
            contactlist.setFocus();
        });
        addcontact.getBtnAdd().addActionListener(e-> JOptionPane.showInternalConfirmDialog(overlayForm,
                "Добавление контакта",
                "Нажата кнопка",
                JOptionPane.DEFAULT_OPTION));

        editcontact.getBtnSave().addActionListener(e->JOptionPane.showInternalConfirmDialog(overlayForm,
                "Сохранение контакта",
                "Нажата кнопка",
                JOptionPane.DEFAULT_OPTION));

        editcontact.getBtnDelete().addActionListener(e-> JOptionPane.showInternalConfirmDialog(overlayForm,
                "Удаление контакта",
                "Нажата кнопка",
                JOptionPane.DEFAULT_OPTION));

        editcontact.getBtnBack().addActionListener(e-> {
            overlayForm.setIndex(MAIN_WINDOW);
            contactlist.setFocus();
        });

        ovlChatList.getLstChatList().addListSelectionListener(e -> selectPerson());
    }
    public  static void setTopPanel(JPanel panel){
        TopPanel1 pane = new TopPanel1();
        pane.getToppanel().setSize(panel.getWidth(), 30);
        panel.add(pane.getToppanel());
    }

    private void getAuthCode(){
        String fullPhoneNo=enterphone.getPhoneField().getText();;
        try {
            try {
                telegramDAO.acceptNumber(fullPhoneNo.replaceAll("[^\\p{Digit}]",""));
            } catch (ApiException e) {
                e.printStackTrace();
            }
        } catch (IOException|NullPointerException e) {
            errorMsg("Неправильно введен номер");
            enterphone.clearTxtPhoneNo();
            return;
        }
        /*try {
            tel.authCheckPhone(fullPhoneNo.replaceAll("[^\\p{Digit}]",""));
        } catch (IOException|NullPointerException e) {
            System.out.println(("Неправильно введен номер"));
            enterphone.clearTxtPhoneNo();
            return;
        }*/
        /*try{
            tel.authSendCode(fullPhoneNo);
            enterregcode.setPhoneLabel(fullPhoneNo);
            //userProfile.setLblPhoneNo(fullPhoneNo);
            //updateFrame(enterRegCode.getPanEnterRegCode());
            //enterRegCode.setFocus();
        } catch (Exception e){
            //errorMsg(e.getMessage());
            Frame.enterphone.clearTxtPhoneNo();
        }*/
        try{
            telegramDAO.sendCode();
            enterregcode.setPhoneLabel(fullPhoneNo);
            userprofile.setLblPhoneNo(fullPhoneNo);
            updateFrame(enterregcode.getRootpanel());
            enterregcode.setFocus();
        } catch (Exception e){
            errorMsg(e.getMessage());
            enterphone.clearTxtPhoneNo();
        }
    }
    private void pushSMSCode() throws ApiException {
        smsCode = enterregcode.getPassWord();
        try {
            if (telegramDAO.canSignIn()) {
                System.out.println("ARE\n\n\n\n\n\n");
                im = telegramDAO.signIn(smsCode);
                fillInContactList();
            } else {
                updateFrame(entername.getRootpanel());
                entername.setFocus();
            }
        } catch (IOException evt) {
            errorMsg(evt.getMessage());
        }
    }

    private void pushNewUserName() throws ApiException {
        try {
            if(telegramDAO.canSignUp()) {
                im = telegramDAO.signUp(smsCode,
                        entername.getName(),
                        entername.getLastName());
                fillInContactList();
            }else{
                goToStart();
            }
        } catch (IOException e1) {
            errorMsg("Неверно введено имя");
        }
    }

    private void goToStart() throws ApiException, IOException{
        try {
            telegramDAO.logOut();
            telegramProxy = null;
        } catch (Exception e) {
            errorMsg("Продолжение работы невозможно");
            telegramDAO.close();
            System.exit(-1);
        }
        updateFrame(enterphone.getRootpanel());
    }

    private void sendSms()
    {
        String txt = contactlist.getTxtToSend().getText();
        if(telegramProxy != null && person != null && !txt.isEmpty()) {
            try {
                telegramProxy.sendMessage(person, txt);
                contactlist.setTxtToSend("");
                updateChat();
                contactlist.getTxtToSend().requestFocusInWindow();
            } catch (Exception ex) {
                errorMsg(ex.getMessage());
            }
        }
    }



        /*try {
            if (telegramDAO.canSignIn()) {
                System.out.println(telegramDAO.canSignIn());
                im=telegramDAO.signIn(smsCode);
                //fillInContactList();

                frame.setVisible(false);
                frame.setContentPane(entername.getRootpanel());
                //entername.setName(im.getFirstName());
                //entername.setName(telegramProxy.getMe().getFirstName());
                //entername.setSurname(telegramProxy.getMe().getLastName());
                frame.setLocationRelativeTo(null);
                frame.setSize(600, 400);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setMinimumSize(new Dimension(600, 400));
                frame.setMaximumSize(new Dimension(900, 600));
                //frame.setUndecorated(true);
                frame.pack();
                frame.setVisible(true);
                ComponentMover cm = new ComponentMover(frame, entername.getToppanel());
            } else {
                frame.setVisible(false);
                frame.setContentPane(entername.getRootpanel());
                frame.setLocationRelativeTo(null);
                frame.setSize(600, 400);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setMinimumSize(new Dimension(600, 400));
                frame.setMaximumSize(new Dimension(900, 600));
                //frame.setUndecorated(true);
                frame.pack();
                frame.setVisible(true);
                ComponentMover cm = new ComponentMover(frame, entername.getToppanel());
            }
        } catch (IOException evt) {
            System.out.println(evt.getMessage());
        }*/

    private void errorMsg(String messsage)
    {
        JOptionPane.showInternalConfirmDialog(getContentPane(),
                messsage,
                "Ошибка",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE);
    }

    private  void updateFrame(JPanel panel){
        /*frame.setVisible(false);
        frame.setContentPane(panel);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600, 400));
        frame.setMaximumSize(new Dimension(900, 600));
        //frame.setUndecorated(true);
        frame.pack();
        frame.setVisible(true);*/
        frame.setContentPane(panel);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }
    private void fillInContactList() throws ApiException, IOException{
        createTelegramProxy();
        overlayForm=new OverlayPanel(contactlist.getRootpanel(),
                userprofile,
                addcontact,
                editcontact);
        contactlist.setTelegramProxy(telegramProxy);
        contactlist.setContactsPanel(layerpane);
        layerpane.add(ovlChatList, new Integer(0));
        layerpane.add(ovlAddButton, new Integer(1));
        updateFrame(overlayForm);
        contactlist.setFocus();
    }
    private void createTelegramProxy() throws ApiException {
        telegramProxy = new TelegramProxy(telegramDAO);
        updateTelegramProxy();
    }
    private void updateTelegramProxy() {
        updateContacts();
        contactlist.setLblContactName(im.getFirstName()+" "+im.getLastName());
        contactlist.revalidate();
        contactlist.repaint();
    }

    private void updateContacts()
    {
        java.util.List<Person> persons=telegramProxy.getPersons();
        cellRender(persons);
    }
    private void cellRender(java.util.List<Person> persons)
    {
        isSelectEvent=false;
        ovlChatList.getLstChatList().setPreferredSize(null);
        ovlChatList.getLstChatList().setCellRenderer(new ContactPane(telegramProxy));
        ovlChatList.getLstChatList().setListData(persons.toArray(new Person[persons.size()]));
        ovlChatList.setSelectedValue(person);
        isSelectEvent=true;
    }
    private void updateChat()
    {
        if (telegramProxy != null) {
            UpdateChanges updateChanges = telegramProxy.update();
            if (updateChanges.getListChanged()) {
                updateContacts();
            } else if (updateChanges.getLargePhotosChanged().size() +
                    updateChanges.getSmallPhotosChanged().size() +
                    updateChanges.getStatusesChanged().size() != 0) {
                ovlChatList.getLstChatList().repaint();
            }

            Dialog currentDialog = person!=null?telegramProxy.getDialog(person):null;


            if (((person==null?0:person.getId())!=contactlist.getPersonId())||
                    updateChanges.getDialogsToReset().contains(currentDialog) ||
                    updateChanges.getDialogsChanged().getChanged().containsKey(currentDialog) ||
                    updateChanges.getDialogsChanged().getDeleted().contains(currentDialog)) {
                contactlist.setUpChat(person);
            }

        }

    }

    private void searchFor() {
        String text=contactlist.getTxtFind().getText().trim();
        if(text.isEmpty()) {
            return;
        }
        String[] words = text.toLowerCase().split("\\s+");
        java.util.List<Person> persons = telegramProxy.getPersons();
        Person personToFind = searchFor(text.toLowerCase(), words, persons);
        if(personToFind == null) {
            errorMsg("Ничего не найдено");
        } else {
            ovlChatList.setSelectedValue(personToFind);
            contactlist.setFocus();
        }
    }

    private Person searchFor(String text, String[] words, java.util.List<? extends Person> persons) {
        int currentIndex = persons.indexOf(person);
        for(int i = 1; i <= persons.size(); i++) {
            int index = (currentIndex + i) % persons.size();
            Person ps = persons.get(index);
            if(contains(ps.getFirstName().toLowerCase(), words)
                    || contains(ps.getLastName().toLowerCase(), words)) {
                return ps;
            }
        }
        return null;
    }

    private void selectPerson()
    {
        if(isSelectEvent) {
            person = (Person) ovlChatList.getLstChatList().getSelectedValue();
            if (person != null) {
                String fullPersonName = person.getFirstName() + " " + person.getLastName();
                contactlist.setLblContactName(fullPersonName);
                contactlist.getBtnEdit().setEnabled(true);
                contactlist.setUpChat(person);
                this.revalidate();
                this.repaint();
            }
            else
                contactlist.getBtnEdit().setEnabled(false);
        }
    }

    private static boolean contains(String text, String... words) {
        for(String word : words) {
            if(text.contains(word))
                return true;
        }
        return false;
    }
}


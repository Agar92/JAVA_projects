import GUI.*;
import Resourses.Resourses;
import General.CommonProcs;
import org.javagram.dao.*;
import org.javagram.dao.Dialog;
import org.javagram.dao.proxy.TelegramProxy;
import org.javagram.dao.proxy.changes.UpdateChanges;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowEvent;
import java.io.IOException;


    public class Frame extends JFrame{
    private TelegramDAO telegramDAO;
    private TelegramProxy telegramProxy;
    private FrmEntreRegCode enterRegCode;
    private FrmEnterPhoneNo enterPhoneNo;
    private FrmEnterName enterName;
    private FrmUserProfile userProfile;
    private FrmAddContact addContact;
    private FrmEditContact editContact;
    private FrameMover frameMover;
    private String smsCode;
    private Me im;
    private Dimension minimumSize, maximumSize;
    private OverlayPanel overlayForm;
    private static final int MAIN_WINDOW = -1, PROFILE_FORM = 0, ADD_CONTACT=1, EDIT_CONTACT=2, CONTACT_LIST=3;
    private Timer timer;
    private boolean isSelectEvent;
    private Person person;
    private LayerPane layerPane;
    private OvlChatList ovlChatList;
    private OvlAddButton ovlAddButton;

    private FrmContactList contactList;
    {
        enterRegCode=new FrmEntreRegCode();
        enterPhoneNo=new FrmEnterPhoneNo();
        enterName=new FrmEnterName();
        userProfile=new FrmUserProfile();
        addContact=new FrmAddContact();
        editContact=new FrmEditContact();
        contactList=new FrmContactList();
        ovlChatList=new OvlChatList();
        ovlAddButton=new OvlAddButton();
        layerPane=new LayerPane();
        frameMover=new FrameMover(this,minimumSize,maximumSize);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        setContentPane(enterPhoneNo.getRootpanel());
        createTopBar(enterPhoneNo);
        addListeners();
        isSelectEvent=true;
    }

    public Frame() throws IOException {
        /*telegramDAO=new ApiBridgeTelegramDAO(CommonProcs.HostData.hostAddr,
                CommonProcs.HostData.appId,
                CommonProcs.HostData.appHash);*/
        telegramDAO=new DebugTelegramDAO();

        timer = new Timer(2000, e-> {
            if(telegramDAO.isLoggedIn())
                updateChat();
        });
        timer.start();
    }

    private void updateFrame(JPanel panel)
    {
        setContentPane(panel);
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    private void getAuthCode(){
        String fullPhoneNo=enterPhoneNo.getPhoneNo();;
        try {
            try {
                telegramDAO.acceptNumber(fullPhoneNo.replaceAll("[^\\p{Digit}]",""));
            } catch (ApiException e) {
                e.printStackTrace();
            }
        } catch (IOException|NullPointerException e) {
            errorMsg("Неправильно введен номер");
            enterPhoneNo.clearTxtPhoneNo();
            return;
        }
        try{
            telegramDAO.sendCode();
            enterRegCode.setLblPhoneNo(fullPhoneNo);
            userProfile.setLblPhoneNo(fullPhoneNo);
            updateFrame(enterRegCode.getRootpanel());
            createTopBar(enterRegCode);
            enterRegCode.setFocus();
        } catch (Exception e){
            errorMsg(e.getMessage());
            enterPhoneNo.clearTxtPhoneNo();
            }
    }

    private void fillInContactList() throws IOException {
        createTelegramProxy();
        //createTopBar(overlayForm);
        JPanel panel = new JPanel();
        BorderLayout border = new BorderLayout();
        panel.setLayout(border);
        panel.add(new TopPanel().getToppanel(), BorderLayout.NORTH);
        panel.add(contactList.getRootpanel(), BorderLayout.CENTER);
        overlayForm=new OverlayPanel(panel,
                userProfile,
                addContact,
                editContact);
        contactList.setTelegramProxy(telegramProxy);
        contactList.setContactsPanel(layerPane);
        layerPane.add(ovlChatList, new Integer(0));
        layerPane.add(ovlAddButton, new Integer(1));
        updateFrame(overlayForm);
        contactList.setFocus();
    }

    private void errorMsg(String messsage)
    {
        JOptionPane.showInternalConfirmDialog(getContentPane(),
                messsage,
                "Ошибка",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE);
    }

    private void closeWindow()
    {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    private void createTopBar(JPanel panel)
    {
        TopPanel toppanel = new TopPanel();
        getContentPane().add(toppanel.getToppanel(), BorderLayout.NORTH);
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

        enterPhoneNo.getBtnCont().addActionListener(e-> getAuthCode());

        enterRegCode.getBtnCont().addActionListener(e-> pushSMSCode());

        enterName.getBtnEndReg().addActionListener(e-> pushNewUserName());

        contactList.getBtnSettings().addActionListener(e-> {
            userProfile.updateMe(im);
            overlayForm.setIndex(PROFILE_FORM);
            userProfile.setFocus();
            });
        contactList.getBtnSearch().addActionListener(e-> searchFor());

        contactList.getTxtFind().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar()==KeyEvent.VK_ENTER)
                    searchFor();
            }
        });

        contactList.getBtnEdit().addActionListener(e-> {
            editContact.setContactName(person);
            overlayForm.setIndex(EDIT_CONTACT);
            editContact.setFocus();
        });

        contactList.getBtnSend().addActionListener(e->{sendSms();
        });
        ovlAddButton.getBtnAdd().addActionListener(e-> {
            overlayForm.setIndex(ADD_CONTACT);
            addContact.setFocus();
        });

        userProfile.getBtnEdit().addActionListener(e-> JOptionPane.showInternalConfirmDialog(overlayForm,
                "Редактирование аватара",
                "Нажата кнопка",
                JOptionPane.DEFAULT_OPTION));
        userProfile.getBtnDelete().addActionListener(e-> JOptionPane.showInternalConfirmDialog(overlayForm,
                "Удаление аватара",
                "Нажата кнопка",
                JOptionPane.DEFAULT_OPTION));
        userProfile.getBtnSave().addActionListener(e-> JOptionPane.showInternalConfirmDialog(overlayForm,
                "Сохранение данных профиля",
                "Нажата кнопка",
                JOptionPane.DEFAULT_OPTION));
        userProfile.getBtnBack().addActionListener(e-> {
            overlayForm.setIndex(MAIN_WINDOW);
            contactList.setFocus();
        });
        userProfile.getBtnExit().addActionListener(e-> JOptionPane.showInternalConfirmDialog(overlayForm,
                            "Выход из программы или в предыдущее окно?",
                            "Нажата кнопка",
                            JOptionPane.DEFAULT_OPTION));

        addContact.getBtnBack().addActionListener(e-> {
            overlayForm.setIndex(MAIN_WINDOW);
            contactList.setFocus();
        });
        addContact.getBtnAdd().addActionListener(e-> JOptionPane.showInternalConfirmDialog(overlayForm,
                "Добавление контакта",
                "Нажата кнопка",
                JOptionPane.DEFAULT_OPTION));

        editContact.getBtnSave().addActionListener(e->JOptionPane.showInternalConfirmDialog(overlayForm,
                            "Сохранение контакта",
                            "Нажата кнопка",
                            JOptionPane.DEFAULT_OPTION));

        editContact.getBtnDelete().addActionListener(e-> JOptionPane.showInternalConfirmDialog(overlayForm,
                "Удаление контакта",
                "Нажата кнопка",
                JOptionPane.DEFAULT_OPTION));

        editContact.getBtnBack().addActionListener(e-> {
            overlayForm.setIndex(MAIN_WINDOW);
            contactList.setFocus();
        });

        ovlChatList.getLstChatList().addListSelectionListener(e -> selectPerson());
    }

    private void selectPerson()
    {
        if(isSelectEvent) {
            person = (Person) ovlChatList.getLstChatList().getSelectedValue();
            if (person != null) {
                String fullPersonName = person.getFirstName() + " " + person.getLastName();
                contactList.setLblContactName(fullPersonName);
                contactList.getBtnEdit().setEnabled(true);
                contactList.setUpChat(person);
                this.revalidate();
                this.repaint();
            }
            else
                contactList.getBtnEdit().setEnabled(false);
        }
    }

    private void pushSMSCode() {
        smsCode = enterRegCode.getValSmsCode();
        try {
            if (telegramDAO.canSignIn()) {
                try {
                    im=telegramDAO.signIn(smsCode);
                } catch (ApiException e) {
                    e.printStackTrace();
                }
                fillInContactList();
            } else {
                updateFrame(enterName.getRootpanel());
                //createTopBar(enterName);
                enterName.setFocus();
            }
        } catch (IOException evt) {
            errorMsg(evt.getMessage());
        }

    }

    private void pushNewUserName() {
        try {
            if(telegramDAO.canSignUp()) {
                try {
                    im = telegramDAO.signUp(smsCode,
                            enterName.getTxtName().getText(),
                            enterName.getTxtLastName().getText());
                } catch (ApiException e) {
                    e.printStackTrace();
                }
                fillInContactList();
            }else{
                goToStart();
            }
        } catch (IOException e1) {
            errorMsg("Неверно введено имя");
        }
    }

    private void createTelegramProxy() {
        try {
            telegramProxy = new TelegramProxy(telegramDAO);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        updateTelegramProxy();
    }

    private void updateTelegramProxy() {
        updateContacts();
        contactList.setLblUserName(im.getFirstName()+" "+im.getLastName());
        contactList.revalidate();
        contactList.repaint();
    }
    private void goToStart() {
        try {
            telegramDAO.logOut();
            telegramProxy = null;
        } catch (Exception e) {
            errorMsg("Продолжение работы невозможно");
            try {
                telegramDAO.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ApiException e1) {
                e1.printStackTrace();
            }
            System.exit(-1);
        }
        updateFrame(enterPhoneNo.getRootpanel());
    }

    private void sendSms()
    {
        String txt = contactList.getTxtToSend().getText();
        if(telegramProxy != null && person != null && !txt.isEmpty()) {
            try {
                telegramProxy.sendMessage(person, txt);
                contactList.setTxtToSend("");
                updateChat();
                contactList.getTxtToSend().requestFocusInWindow();
            } catch (Exception ex) {
                errorMsg(ex.getMessage());
            }
        }
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


            if (((person==null?0:person.getId())!=contactList.getPersonId())||
                    updateChanges.getDialogsToReset().contains(currentDialog) ||
                    updateChanges.getDialogsChanged().getChanged().containsKey(currentDialog) ||
                    updateChanges.getDialogsChanged().getDeleted().contains(currentDialog)) {
                contactList.setUpChat(person);
            }

        }

    }

    private void searchFor() {
        String text=contactList.getTxtFind().getText().trim();
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
            contactList.setFocus();
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


    private static boolean contains(String text, String... words) {
        for(String word : words) {
            if(text.contains(word))
                return true;
        }
        return false;
    }
}

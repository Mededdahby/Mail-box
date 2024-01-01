package ClientSide;

import ServerSide.ClientImplementation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client extends JFrame {
    JMenuBar menubar;
    JMenu fileMenu, editMenu, helpMenu,authMenu;
    JMenuItem openItem, saveItem, cutItem, copyItem, pasteItem, aboutItem ,loginItem,registerItem ;
    JButton addButton, readButton, deleteButton, loginButton, registerButton;
    JLabel nomLabel, prenomLabel, passwordLabel;
    JTextField nomField, prenomField, passwordField;
    JTextArea messages;
    JList<String> emailList;
    DefaultListModel<String> emailListModel;
    JPanel mainPanel, buttonPanel, messagePanel, menuPanel;
    Personne personne  ;
    private static Personne  loggedInPerson;
    ObjectInputStream testing;



    public Client() {
        menuPanel = new JPanel();
        menubar = new JMenuBar();
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        helpMenu = new JMenu("Help");
        authMenu = new JMenu("Authentication");

        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        cutItem = new JMenuItem("Cut");
        copyItem = new JMenuItem("Copy");
        pasteItem = new JMenuItem("Paste");
        aboutItem = new JMenuItem("About");
        loginItem = new JMenuItem("Login");
        registerItem = new JMenuItem("Register");
        registerButton = new JButton("Register");
        loginButton = new JButton("login");

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        helpMenu.add(aboutItem);
        authMenu.add(loginItem);
        authMenu.add(registerItem);

        menubar.add(fileMenu);
        menubar.add(editMenu);
        menubar.add(helpMenu);
        menubar.add(authMenu);

        setJMenuBar(menubar);

        addButton = new JButton("Add");
        readButton = new JButton("Read");

        nomLabel = new JLabel("Nom : ");
        prenomLabel = new JLabel("Prenom : ");
        passwordLabel = new JLabel("Password : ");
        nomField = new JTextField();
        prenomField = new JTextField();
        passwordField = new JTextField();

        mainPanel = new JPanel(new BorderLayout());
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addButton);
        buttonPanel.add(readButton);


        messages = new JTextArea();
        mainPanel.add(new JScrollPane(messages), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addMessageDialog());
        readButton.addActionListener(e -> messagesList());

        loginItem.addActionListener(e -> showLoginForm());
        registerItem.addActionListener(e -> showRegisterForm());
        add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 450);
        setLocationRelativeTo(null);
        setVisible(true);
        add(menuPanel);
        addButton.addActionListener(e->addMessageDialog());
        readButton.addActionListener(e->messagesList());
        registerButton.addActionListener(e->showRegisterForm());
        loginButton.addActionListener(e ->showLoginForm());
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                }
            }
        });


        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                }
            }
        });
   aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aboutMessage = "Welcome to Your Application!\n\n"
                        + "To use this application, follow these steps:\n\n"
                        + "1. If you don't have an account, click on 'Register' to create one.\n"
                        + "2. Log in using your registered username and password.\n"
                        + "3. Click 'Read' to display messages.\n"
                        + "4. Click 'Add' to add new messages.\n\n"
                        + "Enjoy using Your Application!";

                JOptionPane.showMessageDialog(null, aboutMessage, "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
    private void messagesList() {
        PrintWriter pn;
        ObjectInputStream testing;
        Socket socket;
        try {
            socket = new Socket("localhost", 9999);
            System.out.println("Connected ... ");
            pn = new PrintWriter(socket.getOutputStream(), true);
            pn.println("1");
            if(loggedInPerson != null){
            testing = new ObjectInputStream(socket.getInputStream());
            List<Message> message = (List<Message>) testing.readObject();
            System.out.println(message.size());
            StringBuilder messageText = new StringBuilder();
            for (Message m : message) {
                messageText.append(m.toString()).append("\n");
            }
                messages.setText(messageText.toString());
        }else {
                StringBuilder messageText = new StringBuilder();
                messages.setText(messageText.toString());
            }

    } catch (IOException | ClassNotFoundException ex) {
        throw new RuntimeException(ex);
    }
}


    private void addMessage(String titre ,String messageText ) {
        try {
            Socket socket = new Socket("localhost", 9999);
            System.out.println("Connected ... ");
            PrintWriter pn = new PrintWriter(socket.getOutputStream(), true);
            pn.println("4");
            BufferedReader status = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String satatu = status.readLine();
            ObjectOutputStream obj = new ObjectOutputStream(socket.getOutputStream());
            obj.writeObject(new Message(titre, messageText,loggedInPerson.getId()));

            BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String result = bf.readLine();
            if (result.equals("true")) {
                JOptionPane.showMessageDialog(null, "Message added successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add message.");
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    private void addMessageDialog() {
        JDialog addMessageDialog = new JDialog(this, "Add Message", true);
        addMessageDialog.setSize(300, 300);
        addMessageDialog.setLayout(new GridLayout(3, 2));

        JLabel titleLabel = new JLabel("Title: ");
        JTextField titleField = new JTextField();
        JLabel subjectLabel = new JLabel("Subject: ");
        JTextArea subjectArea = new JTextArea();
        JButton addButton = new JButton("Add");

        addMessageDialog.add(titleLabel);
        addMessageDialog.add(titleField);
        addMessageDialog.add(subjectLabel);
        addMessageDialog.add(new JScrollPane(subjectArea));
        addMessageDialog.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String subject = subjectArea.getText();
                if (!title.isEmpty() && !subject.isEmpty()) {
                    addMessage(title , subject);
                    messages.append("Title: " + title + "\nSubject: " + subject + "\n\n");
                    addMessageDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(addMessageDialog, "Please enter a title and subject.");
                }
            }
        });
        addMessageDialog.setLocationRelativeTo(this);
        addMessageDialog.setVisible(true);
    }
    private void showLoginForm() {
        if (loggedInPerson == null) {
            JDialog loginDialog = new JDialog(this, "Login", true);
            loginDialog.setSize(300, 150);
            loginDialog.setLayout(new GridLayout(3, 2));
            JButton submitButton = new JButton("Login");
            loginDialog.add(nomLabel);
            loginDialog.add(nomField);
            loginDialog.add(passwordLabel);
            loginDialog.add(passwordField);
            loginDialog.add(submitButton);

            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Personne personne1 = new Personne(nomField.getText(), "", passwordField.getText());
                        Socket socket = new Socket("localhost", 9999);
                        PrintWriter pn = new PrintWriter(socket.getOutputStream(), true);
                        pn.println("3");
                        BufferedReader status = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String statu = status.readLine();
                        System.out.println(statu);
                        ObjectOutputStream pn2 = new ObjectOutputStream(socket.getOutputStream());
                        pn2.writeObject(personne1);
                        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                        Personne pp = (Personne) inputStream.readObject();
                        if (pp != null) {
                            loggedInPerson = pp;
                            System.out.println(loggedInPerson.toString());
                            JOptionPane.showMessageDialog(null, "You logged in successfully: " + nomField.getText());
                        } else {
                            JOptionPane.showMessageDialog(null, "not exist !");
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            loginDialog.setLocationRelativeTo(this);
            loginDialog.setVisible(true);
        }
    }
    private void showRegisterForm() {
        JDialog registerDialog = new JDialog(this, "Register", true);
        registerDialog.setSize(300, 150);
        registerDialog.setLayout(new GridLayout(4, 2));
        JButton submit = new JButton("Register");
        registerDialog.add(nomLabel);
        registerDialog.add(nomField);
        registerDialog.add(prenomLabel);
        registerDialog.add(prenomField);
        registerDialog.add(passwordLabel);
        registerDialog.add(passwordField);

        registerDialog.add(submit);
submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Personne personne1 = new Personne(nomField.getText(), prenomField.getText(), passwordField.getText());

                    Socket socket = new Socket("localhost", 9999);
                    System.out.println("Connected");
                    PrintWriter pn = new PrintWriter(socket.getOutputStream(), true);
                    pn.println("2");
                    pn.flush();
                    BufferedReader status  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String statu = status.readLine();
                    ObjectOutputStream obj = new ObjectOutputStream(socket.getOutputStream());
                    obj.writeObject(personne1);
                    PrintWriter pn2 = new PrintWriter(socket.getOutputStream(), true);
                    pn2.println(nomField.getText());
                    BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String result = bf.readLine();
                    if (result.equals("true")) {
                        JOptionPane.showMessageDialog(null, "You Registered  successfully, welcome : " + nomField.getText() );
                    } else {
                        JOptionPane.showMessageDialog(null, "not exist");
                    }
                    System.out.println(result);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        registerDialog.setLocationRelativeTo(this);
        registerDialog.setVisible(true);
    }


    public static void main(String[] args) {
        new Client();
    }
}

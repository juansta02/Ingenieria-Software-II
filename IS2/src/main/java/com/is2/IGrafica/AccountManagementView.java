package com.is2.IGrafica;

import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.is2.*;

public class AccountManagementView extends JFrame {
    private AppController controller;
    private boolean isAdmin;
    private Jugador jugador;
    private Admin admin;

    public AccountManagementView(AppController controller, User user) {
        this.controller = controller;
        this.isAdmin = user.isAdmin();
        if (isAdmin) {
            this.admin = (Admin) user;
        } else {
            this.jugador = (Jugador) user;
        }

        setTitle("Gestión de Cuenta");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel fieldsPanel = new JPanel();
        GroupLayout layout = new GroupLayout(fieldsPanel);
        fieldsPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Definimos los componentes
        JLabel nameLabel = new JLabel("Nombre:");
        JTextField nameField = new JTextField();
        nameField.setEditable(!isAdmin);

        JLabel passwordLabel = new JLabel("Contraseña:");
        JPasswordField passwordField = new JPasswordField();
        passwordField.setEditable(!isAdmin);

        JLabel usernameLabel = new JLabel("Usuario:");
        JTextField usernameField = new JTextField();
        JLabel surnameLabel = new JLabel("Apellidos:");
        JTextField surnameField = new JTextField();
        JLabel phoneLabel = new JLabel("Teléfono:");
        JTextField phoneField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        // Mostrar solo los campos que aplica
        if (isAdmin) {
            nameField.setText(admin.getUsername());
            passwordField.setText(admin.getPassword());
            layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(nameLabel)
                    .addComponent(passwordLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(nameField)
                    .addComponent(passwordField)));
            layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField)));
        } else {
            usernameField.setText(jugador.getUsername());
            passwordField.setText(jugador.getPassword());
            nameField.setText(jugador.getNombre());
            surnameField.setText(jugador.getApellidos());
            phoneField.setText(jugador.getTelefono());
            emailField.setText(jugador.getEmail());

            layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(usernameLabel)
                    .addComponent(passwordLabel)
                    .addComponent(nameLabel)
                    .addComponent(surnameLabel)
                    .addComponent(phoneLabel)
                    .addComponent(emailLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(usernameField)
                    .addComponent(passwordField)
                    .addComponent(nameField)
                    .addComponent(surnameField)
                    .addComponent(phoneField)
                    .addComponent(emailField)));
            layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabel)
                    .addComponent(usernameField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(surnameLabel)
                    .addComponent(surnameField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneLabel)
                    .addComponent(phoneField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel)
                    .addComponent(emailField)));
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton backButton = new JButton("Volver");
        backButton.addActionListener(e -> controller.showMainScreen());
        buttonPanel.add(backButton);

        if (!isAdmin) {
            JButton saveButton = new JButton("Guardar Cambios");
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean hecho = jugador.setUsername(usernameField.getText());
                    if (hecho) {
                        jugador.setPassword(new String(passwordField.getPassword()));
                        jugador.setNombre(nameField.getText());
                        jugador.setApellidos(surnameField.getText());
                        jugador.setTelefono(phoneField.getText());
                        jugador.setEmail(emailField.getText());
                        JOptionPane.showMessageDialog(null, "Información actualizada correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Nombre de usuario ya existente.");
                    }
                }
            });
            buttonPanel.add(saveButton);
        }

        mainPanel.add(fieldsPanel);
        mainPanel.add(buttonPanel);

        add(mainPanel);
    }
}

package com.is2.IGrafica;

import javax.swing.*;
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

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JButton backButton = new JButton("Volver");
        backButton.setBounds(200, 270, 120, 25);
        panel.add(backButton);

        if (isAdmin) {
            /* Mostrar solo nombre y contraseña como no editables */
            JLabel nameLabel = new JLabel("Nombre:");
            nameLabel.setBounds(50, 20, 100, 25);
            panel.add(nameLabel);

            JTextField nameField = new JTextField(admin.getUsername());
            nameField.setBounds(200, 20, 200, 25);
            nameField.setEditable(false);
            panel.add(nameField);

            JLabel passwordLabel = new JLabel("Contraseña:");
            passwordLabel.setBounds(50, 60, 100, 25);
            panel.add(passwordLabel);

            /* Simulación, manejar contraseñas de forma segura */
            JPasswordField passwordField = new JPasswordField(admin.getPassword());
            passwordField.setBounds(200, 60, 200, 25);
            passwordField.setEditable(false);
            panel.add(passwordField);
        } else {
            /* Mostrar y permitir la edición de todos los campos para el jugador */
            JLabel usernameLabel = new JLabel("Usuario:");
            usernameLabel.setBounds(50, 20, 100, 25);
            panel.add(usernameLabel);

            JTextField usernameField = new JTextField(jugador.getUsername());
            usernameField.setBounds(200, 20, 200, 25);
            panel.add(usernameField);

            JLabel passwordLabel = new JLabel("Contraseña:");
            passwordLabel.setBounds(50, 60, 100, 25);
            panel.add(passwordLabel);

            JPasswordField passwordField = new JPasswordField(jugador.getPassword());
            passwordField.setBounds(200, 60, 200, 25);
            panel.add(passwordField);

            JLabel nameLabel = new JLabel("Nombre:");
            nameLabel.setBounds(50, 100, 100, 25);
            panel.add(nameLabel);

            JTextField nameField = new JTextField(jugador.getNombre());
            nameField.setBounds(200, 100, 200, 25);
            panel.add(nameField);

            JLabel surnameLabel = new JLabel("Apellidos:");
            surnameLabel.setBounds(50, 140, 100, 25);
            panel.add(surnameLabel);

            JTextField surnameField = new JTextField(jugador.getApellidos());
            surnameField.setBounds(200, 140, 200, 25);
            panel.add(surnameField);

            JLabel phoneLabel = new JLabel("Teléfono:");
            phoneLabel.setBounds(50, 180, 100, 25);
            panel.add(phoneLabel);

            JTextField phoneField = new JTextField(String.valueOf(jugador.getTelefono()));
            phoneField.setBounds(200, 180, 200, 25);
            panel.add(phoneField);

            JLabel emailLabel = new JLabel("Email:");
            emailLabel.setBounds(50, 220, 100, 25);
            panel.add(emailLabel);

            JTextField emailField = new JTextField(jugador.getEmail());
            emailField.setBounds(200, 220, 200, 25);
            panel.add(emailField);

            // Botón para guardar cambios
            JButton saveButton = new JButton("Guardar Cambios");
            saveButton.setBounds(200, 270, 150, 30);
            panel.add(saveButton);

            // Acción del botón de guardar
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean hecho = jugador.setUsername(usernameField.getText());
                    if (hecho) {
                        jugador.setPassword(new String(passwordField.getPassword())); // Guardar de forma segura
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
        }
        backButton.addActionListener(e -> controller.showLoginScreen());

        add(panel);
    }
}

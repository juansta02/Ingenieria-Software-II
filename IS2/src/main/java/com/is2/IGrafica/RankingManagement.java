package com.is2.IGrafica;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.is2.*;

public class RankingManagement extends JFrame {
    private AppController controller;
    private boolean isAdmin;
    private Jugador jugador;
    private Admin admin;

    public RankingManagement(AppController controller, User user) {
        this.controller = controller;
        this.isAdmin = user.isAdmin();
        if (isAdmin) {
            this.admin = (Admin) user;
        } else {
            this.jugador = (Jugador) user;
        }

        setTitle("Ranking");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JButton backButton = new JButton("Volver");
        backButton.setBounds(200, 270, 120, 25);
        panel.add(backButton);

        if (isAdmin) {
            /* Mostrar ranking y boton para editarlo */
            JButton saveButton = new JButton("Guardar Cambios");
            saveButton.setBounds(200, 270, 150, 30);
            panel.add(saveButton);

            // Acción del botón de guardar
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    

                }
            });
        } else {
            /* Mostrar y permitir la edición de todos los campos para el jugador */
            
            // Botón para guardar cambios
            
        }
        backButton.addActionListener(e -> controller.showLoginScreen());

        add(panel);
    }
}

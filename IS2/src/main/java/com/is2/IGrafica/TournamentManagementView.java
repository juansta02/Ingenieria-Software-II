package com.is2.IGrafica;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import com.is2.*;

public class TournamentManagementView extends JFrame {
    private final static int FALL = 0;
    private final static int WINTER = 1;
    private final static int SPRING = 2;
    private final static int SUMMER = 3;

    private Torneo[] torneos = new Torneo[4];
    private AppController controller;
    private Jugador jugador;
    private Admin admin;
    private boolean isAdmin;

    public TournamentManagementView(AppController controller, User user) {
        this.controller = controller;
        this.isAdmin = user.isAdmin();

        if (isAdmin) {
            this.admin = (Admin) user;
        } else {
            this.jugador = (Jugador) user;
        }

        try {
            torneos[FALL] = new Torneo(0);
            torneos[WINTER] = new Torneo(1);
            torneos[SPRING] = new Torneo(2);
            torneos[SUMMER] = new Torneo(3);
        } catch (java.text.ParseException e) {
            JOptionPane.showMessageDialog(null, "Ha habido un error al cargar los torneos disponibles");
            controller.showMainScreen();
        }

        setTitle("Gestión de Torneos");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Contenedor principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Márgenes generales

        JLabel label = new JLabel("Pantalla de Gestión de Torneos");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        for (int i = 0; i < torneos.length; i++) {
            JPanel torneoPanel = new JPanel();
            torneoPanel.setLayout(new GridLayout(0, 2, 10, 10)); // Espaciado interno entre componentes
            torneoPanel.setBorder(BorderFactory.createTitledBorder("Torneo " + i)); // Caja con título

            JLabel fechaInicioInscLabel = new JLabel("Inicio Inscripción: " + torneos[i].getfInicioInsc());
            JLabel fechaInicioLabel = new JLabel("Inicio Torneo: " + torneos[i].getfInicioTorneo());

            torneoPanel.add(fechaInicioInscLabel);
            torneoPanel.add(fechaInicioLabel);

            if (!isAdmin) {
                JButton inscribirseButton = new JButton("Inscribirse");
                int torneoId = i;
                inscribirseButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            torneos[torneoId].inscripcion(jugador.getUsername());
                        } catch (IOException e1) {
                            JOptionPane.showMessageDialog(null, "No se pudo inscribir al torneo " + torneoId);
                        }
                    }
                });
                torneoPanel.add(inscribirseButton);
            } else {
                JButton modificarButton = new JButton("Modificar");
                int torneoId = i;
                modificarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nuevaFechaInsc = JOptionPane.showInputDialog(
                                "Nueva fecha de inicio inscripción (dd-MM-yyyy):");
                        String nuevaFechaInicio = JOptionPane.showInputDialog(
                                "Nueva fecha de inicio torneo (dd-MM-yyyy):");

                        try {
                            torneos[torneoId].setfInicioInsc(nuevaFechaInsc);
                            torneos[torneoId].setfInicioTorneo(nuevaFechaInicio);
                        } catch (ParseException e1) {
                            JOptionPane.showMessageDialog(null, "Formato incorrecto en las fechas");
                        } catch (IOException e1) {
                            JOptionPane.showMessageDialog(null, "Error al abrir el archivo");
                        }

                        JOptionPane.showMessageDialog(null, "Información del torneo actualizada.");
                        controller.showTournamentManagementScreen(); // Refrescar la vista
                    }
                });
                torneoPanel.add(modificarButton);
            }

            // Añadir cada panel de torneo al panel principal
            torneoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(Box.createVerticalStrut(10)); // Espaciado entre torneos
            panel.add(torneoPanel);
        }

        // Botón de volver
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("Volver");
        backButton.addActionListener(e -> controller.showMainScreen());
        backPanel.add(backButton);

        panel.add(Box.createVerticalStrut(20)); // Espaciado antes del botón de volver
        panel.add(backPanel);

        add(panel);
    }
}

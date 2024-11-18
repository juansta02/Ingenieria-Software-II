package com.is2.IGrafica;

import javax.swing.*;

import java.awt.GridLayout;
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
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));

        JLabel label = new JLabel("Pantalla de Gestión de Torneos");
        panel.add(label);

        for (int i = 0; i < torneos.length; i++) {
            JPanel torneoPanel = new JPanel();
            torneoPanel.setLayout(new GridLayout(0, 2));

            JLabel torneoLabel = new JLabel("Torneo " + i);
            JLabel fechaInicioInscLabel = new JLabel(
                    "Fecha de inicio de inscripción: " + torneos[i].getfInicioTorneo());
            JLabel fechaInicioLabel = new JLabel("Fecha de inicio del torneo: " + torneos[i].getfInicioTorneo());

            torneoPanel.add(torneoLabel);
            torneoPanel.add(fechaInicioInscLabel);
            torneoPanel.add(fechaInicioLabel);

            if (!isAdmin) {
                JButton inscribirseButton = new JButton("Inscribirse");
                int torneoId = i; // Variable para usar en la lambda
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
                int torneoId = i; // Variable para usar en la lambda
                modificarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Aquí puedes abrir una ventana o panel adicional para modificar el torneo
                        String nuevaFechaInsc = JOptionPane
                                .showInputDialog("Ingrese nueva fecha de inicio de inscripción (formato dd-MM-yyyy):");
                        String nuevaFechaInicio = JOptionPane
                                .showInputDialog("Ingrese nueva fecha de inicio del torneo (formato dd-MM-yyyy):");

                        try {
                            torneos[torneoId].setfInicioInsc(nuevaFechaInsc);
                            torneos[torneoId].setfInicioTorneo(nuevaFechaInicio);
                        } catch (ParseException e1) {
                            JOptionPane.showMessageDialog(null, "Formato incorrecto en una de las fechas");
                        } catch (IOException e1) {
                            JOptionPane.showMessageDialog(null, "Ha habido un error al abrir el archivo");

                        }

                        JOptionPane.showMessageDialog(null, "Información del torneo " + torneoId + " actualizada.");
                        controller.showTournamentManagementScreen(); // Refrescar la vista
                    }
                });
                torneoPanel.add(modificarButton);
            }

            panel.add(torneoPanel);
        }

        JButton backButton = new JButton("Volver");
        backButton.addActionListener(e -> controller.showMainScreen());
        panel.add(backButton);

        add(panel);
    }
}

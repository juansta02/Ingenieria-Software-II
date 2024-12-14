package com.is2.IGrafica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;
import com.is2.*;

public class RankingManagementView extends JFrame {
    private AppController controller;
    private boolean isAdmin;
    private Jugador jugador;
    private Ranking ranking;
    private JTable rankingTable;

    public RankingManagementView(AppController controller, User user) {
        this.controller = controller;
        this.isAdmin = user.isAdmin();

        if (!isAdmin) {
            this.jugador = (Jugador) user;
        }

        setTitle("Ranking");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        ranking = new Ranking(); // Cargar el ranking

        // Crear tabla para mostrar el ranking
        String[] columnNames = { "Usuario", "Puntos" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que las celdas no sean editables
            }
        };
        rankingTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(rankingTable);
        scrollPane.setBounds(50, 30, 500, 200);
        panel.add(scrollPane);

        // Rellenar la tabla con los datos del ranking
        loadRankingData(tableModel);

        JButton backButton = new JButton("Volver");
        backButton.setBounds(240, 300, 120, 25);
        panel.add(backButton);

        backButton.addActionListener(e -> controller.showLoginScreen());

        add(panel);
    }

    private void loadRankingData(DefaultTableModel tableModel) {
        List<Map.Entry<String, Integer>> rankingList = ranking.getRanking();
        for (Map.Entry<String, Integer> entry : rankingList) {
            tableModel.addRow(new Object[] { entry.getKey(), entry.getValue() });
        }
    }
}

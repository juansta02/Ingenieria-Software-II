import javax.swing.*;

public class TournamentManagementView extends JFrame {
    private AppController controller;

    public TournamentManagementView(AppController controller) {
        this.controller = controller;

        setTitle("Gestión de Torneos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Pantalla de Gestión de Torneos");
        panel.add(label);

        JButton backButton = new JButton("Volver");
        backButton.addActionListener(e -> controller.showMainScreen(true));
        panel.add(backButton);

        add(panel);
    }
}

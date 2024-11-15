import javax.swing.*;

public class MainView extends JFrame {
    private AppController controller;
    private boolean isAdmin;

    public MainView(AppController controller, boolean isAdmin) {
        this.controller = controller;
        this.isAdmin = isAdmin;

        setTitle("Menú Principal");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        // Menú "Torneos"
        JMenu menuTorneos = new JMenu("Torneos");
        JMenuItem verTorneos = new JMenuItem("Ver Torneos");
        menuTorneos.add(verTorneos);
        menuBar.add(menuTorneos);

        if (isAdmin) {
            JMenuItem gestionarTorneos = new JMenuItem("Gestionar Torneos");
            gestionarTorneos.addActionListener(e -> controller.showTournamentManagementScreen());
            menuTorneos.add(gestionarTorneos);
        }

        // Menú "Cuenta"
        JMenu menuCuenta = new JMenu("Cuenta");
        JMenuItem cerrarSesion = new JMenuItem("Cerrar Sesión");
        cerrarSesion.addActionListener(e -> controller.logout());
        menuCuenta.add(cerrarSesion);
        menuBar.add(menuCuenta);

        setJMenuBar(menuBar);

        // Panel de bienvenida
        JPanel panel = new JPanel();
        JLabel welcomeLabel = new JLabel(isAdmin ? "Bienvenido, Administrador" : "Bienvenido, Jugador");
        panel.add(welcomeLabel);

        add(panel);
    }
}

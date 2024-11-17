import javax.swing.*;


public class MainView extends JFrame {

    private AppController controller;
    private boolean isAdmin;
    private User user;

    public MainView(AppController controller, User user) {
        this.controller = controller;
        this.isAdmin = user.isAdmin();
        this.user = user;

        setTitle("Menú Principal");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        
        /* Boton para torneos */
        JButton torneosButton = new JButton("Torneos");
        torneosButton.setBounds(50, 50, 120, 40);
        panel.add(torneosButton);

        /* Boton para ranking */
        JButton rankingButton = new JButton("Ranking");
        rankingButton.setBounds(50, 50, 120, 40);
        panel.add(torneosButton);


        JMenuBar menuBar = new JMenuBar();

        // Menú "Cuenta"
        JMenu menuCuenta = new JMenu("Cuenta");
        JMenuItem modificarCuenta = new JMenuItem("Modificar datos");
        modificarCuenta.addActionListener(e -> controller.showTournamentManagementScreen());
        JMenuItem cerrarSesion = new JMenuItem("Cerrar Sesión");
        cerrarSesion.addActionListener(e -> controller.logout());
        menuCuenta.add(cerrarSesion);
        menuBar.add(menuCuenta);

        setJMenuBar(menuBar);

        // Panel de bienvenida
        JLabel welcomeLabel = new JLabel(isAdmin ? "Bienvenido, Administrador" : "Bienvenido, Jugador");
        panel.add(welcomeLabel);

        torneosButton.addActionListener(e -> controller.showTournamentManagementScreen());
        add(panel);
        
    }
}

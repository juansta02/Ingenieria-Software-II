import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginView extends JFrame {
    private AppController controller;
    private User user;

    public LoginView(AppController controller) {
        this.controller = controller;

        setTitle("Inicio de Sesión");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setBounds(50, 50, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(150, 50, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(50, 100, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(150, 100, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setBounds(50, 150, 120, 25);
        panel.add(loginButton);

        JButton registerButton = new JButton("Registrar");
        registerButton.setBounds(200, 150, 120, 25);
        panel.add(registerButton);

        add(panel);

        // Acción para iniciar sesión
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulación de inicio de sesión con rol
                String usuario = userText.getText();
                String password = new String(passwordText.getPassword());
                user = new User();

                if (user.login(usuario, password)) {
                    controller.setUser(user);
                    controller.showMainScreen();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario no registrado");
                }

            }
        });

        // Acción para registro
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.showRegisterScreen();
            }
        });
    }

}

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterView extends JFrame {
    private AppController controller;

    public RegisterView(AppController controller) {
        this.controller = controller;

        setTitle("Registro de Nuevo Jugador");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Campos del formulario
        JLabel nameLabel = new JLabel("Nombre:");
        nameLabel.setBounds(50, 20, 80, 25);
        panel.add(nameLabel);

        JTextField nameField = new JTextField(20);
        nameField.setBounds(150, 20, 165, 25);
        panel.add(nameField);

        JLabel surnameLabel = new JLabel("Apellidos:");
        surnameLabel.setBounds(50, 60, 80, 25);
        panel.add(surnameLabel);

        JTextField surnameField = new JTextField(20);
        surnameField.setBounds(150, 60, 165, 25);
        panel.add(surnameField);

        JLabel phoneLabel = new JLabel("Teléfono:");
        phoneLabel.setBounds(50, 100, 80, 25);
        panel.add(phoneLabel);

        JTextField phoneField = new JTextField(20);
        phoneField.setBounds(150, 100, 165, 25);
        panel.add(phoneField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 140, 80, 25);
        panel.add(emailLabel);

        JTextField emailField = new JTextField(20);
        emailField.setBounds(150, 140, 165, 25);
        panel.add(emailField);

        JLabel usernameLabel = new JLabel("Usuario:");
        usernameLabel.setBounds(50, 180, 80, 25);
        panel.add(usernameLabel);

        JTextField usernameField = new JTextField(20);
        usernameField.setBounds(150, 180, 165, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(50, 220, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBounds(150, 220, 165, 25);
        panel.add(passwordField);

        // Botones
        JButton registerButton = new JButton("Registrar");
        registerButton.setBounds(50, 270, 120, 25);
        panel.add(registerButton);

        JButton backButton = new JButton("Volver");
        backButton.setBounds(200, 270, 120, 25);
        panel.add(backButton);

        add(panel);

        // Acción del botón "Registrar"
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validación básica de los campos
                if (nameField.getText().isEmpty() || surnameField.getText().isEmpty() || phoneField.getText().isEmpty()
                        || emailField.getText().isEmpty() || usernameField.getText().isEmpty()
                        || String.valueOf(passwordField.getPassword()).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
                } else {
                    boolean registro = User.singin(nameField.getText(), surnameField.getText(), Integer.parseInt(phoneField.getText()), emailField.getText(), usernameField.getText(), tring.valueOf(passwordField.getPassword()));
                    if (registro) {
                        JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente.");
                        controller.showLoginScreen(); // Volver a la pantalla de inicio de sesión
                    } else {
                        JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe");
                    }
                }
            }
        });

        // Acción del botón "Volver"
        backButton.addActionListener(e -> controller.showLoginScreen());
    }
}

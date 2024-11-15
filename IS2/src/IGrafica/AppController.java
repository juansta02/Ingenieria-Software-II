import javax.swing.*;

public class AppController {
    private JFrame currentFrame; // Ventana actual

    public AppController() {
        // Inicia con la pantalla de inicio de sesión
        showLoginScreen();
    }

    public void showLoginScreen() {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        LoginView loginView = new LoginView(this);
        currentFrame = loginView;
        currentFrame.setVisible(true);
    }

    public void showMainScreen(boolean isAdmin) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        MainView mainView = new MainView(this, isAdmin);
        currentFrame = mainView;
        currentFrame.setVisible(true);
    }

    public void showTournamentManagementScreen() {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        TournamentManagementView tmView = new TournamentManagementView(this);
        currentFrame = tmView;
        currentFrame.setVisible(true);
    }

    public void logout() {
        showLoginScreen();
    }
    public void showRegisterScreen() {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        RegisterView registerView = new RegisterView(this);
        currentFrame = registerView;
        currentFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AppController::new);
    }
}

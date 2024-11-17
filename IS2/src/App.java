import java.util.Date;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(AppController::new);
    }
}
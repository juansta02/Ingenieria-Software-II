import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.is2.*;

public class LoginTest {
    private Jugador jugador;

    @BeforeEach
    public void setUp() {
        jugador = new Jugador();
    }

    @Test
    public void testLoginSuccess() {
        // Simulamos el registro de un usuario
        jugador.signin("John", "Doe", "123456789", "john@example.com", "john_doe", "password123");
        
        // Probamos un inicio de sesión correcto
        assertTrue(jugador.login("john_doe", "password123"));
    }

    @Test
    public void testLoginFailure() {
        // Intentamos un inicio de sesión con credenciales incorrectas
        assertFalse(jugador.login("john_doe", "wrongpassword"));
    }

    @Test
    public void testEmptyUsername() {
        // Intentamos un inicio de sesión con un nombre de usuario vacío
        assertFalse(jugador.login("", "password123"));
    }

    @Test
    public void testEmptyPassword() {
        // Intentamos un inicio de sesión con una contraseña vacía
        assertFalse(jugador.login("john_doe", ""));
    }

    @Test
    public void testLoginWithNullUsername() {
        // Intentamos un inicio de sesión con un nombre de usuario nulo
        assertThrows(NullPointerException.class, () -> jugador.login(null, "password123"));
    }

    @Test
    public void testLoginWithNullPassword() {
        // Intentamos un inicio de sesión con una contraseña nula
        assertThrows(NullPointerException.class, () -> jugador.login("john_doe", null));
    }
}

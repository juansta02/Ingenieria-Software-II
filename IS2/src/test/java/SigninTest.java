import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.is2.*;

public class SigninTest {
    private Jugador jugador;

    @BeforeEach
    public void setUp() {
        jugador = new Jugador();  // Inicializa un jugador nuevo antes de cada prueba.
    }

    // Test de éxito de registro con datos válidos
    @Test
    public void testSigninSuccess() {
        boolean result = jugador.signin("John", "Doe", "123456789", "john@example.com", "john_doe", "password123");
        assertTrue(result, "El registro debería ser exitoso con datos válidos.");
    }

    // Test de fallo de registro cuando faltan datos importantes
    @Test
    public void testSigninFailureMissingUsername() {
        boolean result = jugador.signin("John", "Doe", "123456789", "john@example.com", "", "password123");
        assertFalse(result, "El registro debería fallar si el nombre de usuario está vacío.");
    }

    @Test
    public void testSigninFailureMissingPassword() {
        boolean result = jugador.signin("John", "Doe", "123456789", "john@example.com", "john_doe", "");
        assertFalse(result, "El registro debería fallar si la contraseña está vacía.");
    }

    // Test de fallo de registro cuando el nombre de usuario ya existe
    @Test
    public void testSigninFailureUsernameAlreadyExists() {
        // Simulamos que el jugador "john_doe" ya está registrado
        jugador.signin("John", "Doe", "123456789", "john@example.com", "john_doe", "password123");

        // Intentamos registrarlo nuevamente con el mismo nombre de usuario
        boolean result = jugador.signin("Jane", "Doe", "987654321", "jane@example.com", "john_doe", "password456");
        assertFalse(result, "El registro debería fallar si el nombre de usuario ya existe.");
    }
}

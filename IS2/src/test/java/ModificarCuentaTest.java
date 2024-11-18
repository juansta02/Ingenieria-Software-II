import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.is2.*;

public class ModificarCuentaTest {
    private Jugador jugador;

    @BeforeEach
    public void setUp() {
        jugador = new Jugador();
        // Registrar al jugador con datos iniciales
        jugador.signin("John", "Doe", "123456789", "john@example.com", "john_doe", "password123");
    }

    // Test para cambiar el nombre
    @Test
    public void testUpdateName() {
        // Cambiar el nombre
        jugador.setNombre("Jonathan");

        // Verificar que el nombre se haya actualizado correctamente
        assertEquals("Jonathan", jugador.getNombre(), "El nombre del jugador debería haberse actualizado correctamente.");
    }

    // Test para cambiar el apellido
    @Test
    public void testUpdateLastName() {
        // Cambiar el apellido
        jugador.setApellidos("Smith");

        // Verificar que el apellido se haya actualizado correctamente
        assertEquals("Smith", jugador.getApellidos(), "El apellido del jugador debería haberse actualizado correctamente.");
    }

    // Test para cambiar el email
    @Test
    public void testUpdateEmail() {
        // Cambiar el email
        jugador.setEmail("jonathan.doe@example.com");

        // Verificar que el email se haya actualizado correctamente
        assertEquals("jonathan.doe@example.com", jugador.getEmail(), "El email del jugador debería haberse actualizado correctamente.");
    }

    // Test para cambiar el número de teléfono
    @Test
    public void testUpdatePhoneNumber() {
        // Cambiar el número de teléfono
        jugador.setTelefono("987654321");

        // Verificar que el número de teléfono se haya actualizado correctamente
        assertEquals("987654321", jugador.getTelefono(), "El número de teléfono del jugador debería haberse actualizado correctamente.");
    }

    // Test para cambiar la contraseña
    @Test
    public void testUpdatePassword() {
        // Cambiar la contraseña
        jugador.setPassword("newPassword456");

        // Verificar que la contraseña se haya actualizado correctamente
        assertEquals("newPassword456", jugador.getPassword(), "La contraseña del jugador debería haberse actualizado correctamente.");
    }
}

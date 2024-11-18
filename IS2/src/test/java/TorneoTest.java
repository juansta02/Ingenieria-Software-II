import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.text.ParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.is2.*;

public class TorneoTest {
    private Torneo torneo;

    @BeforeEach
    public void setUp() {
        try {
            torneo = new Torneo(0);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testInscribirse(){
        boolean result = false;
        //simulamos inscripcion de un usuario a un torneo
        try {
            result = torneo.inscripcion("john_doe");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}

package grupo1;

import org.junit.jupiter.api.Test;
import org.nsq.grupo1.Servicio1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Servicio1Test {

    @Test
    void testDentroDelLimite() {
        assertTrue(Servicio1.verificarLimite(4500.0));
    }

    @Test
    void testFueraDelLimite() {
        assertFalse(Servicio1.verificarLimite(6000.0));
    }

    @Test
    void testValorExacto() {
        assertTrue(Servicio1.verificarLimite(5000.0));
    }

    @Test
    void testValorNegativo() {
        assertTrue(Servicio1.verificarLimite(-10.0));
    }

    @Test
    void testValorMinimo() {
        assertTrue(Servicio1.verificarLimite(0));
    }

}

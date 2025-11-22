package grupo1;

import org.junit.jupiter.api.Test;
import org.nsq.base.Pedido;
import org.nsq.grupo1.Servicio1;
import org.nsq.modelo.Producto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IntegracionTest {

    @Test
    void testFlujoExitoso() {
        List<Producto> productos = List.of(
                new Producto("Laptop", 2500, 1),
                new Producto("Mouse", 100, 2)
        );

        double total = Pedido.calcularTotalPedido(productos, 10);
        boolean valido = Servicio1.verificarLimite(total);

        assertTrue(valido);
    }

    @Test
    void testErrorBase() {
        List<Producto> productos = List.of();

        assertThrows(IllegalArgumentException.class,
                () -> Pedido.calcularTotalPedido(productos, 10));
    }

    @Test
    void testErrorSecundaria() {
        List<Producto> productos = List.of(new Producto("TV", 6000, 1));

        double total = Pedido.calcularTotalPedido(productos, 0);

        assertFalse(Servicio1.verificarLimite(total));
    }

    @Test
    void testValorLimite() {
        List<Producto> productos = List.of(new Producto("Computadora", 5000, 1));

        double total = Pedido.calcularTotalPedido(productos, 0);

        assertTrue(Servicio1.verificarLimite(total));
    }

    @Test
    void testCombinado() {
        List<Producto> productos = List.of(
                new Producto("SuperGalletota", 2000, 1),
                new Producto("SuperGalletita", 1500, 1)
        );

        double total = Pedido.calcularTotalPedido(productos, 20);
        boolean dentroLimite = Servicio1.verificarLimite(total);

        assertTrue(dentroLimite);
        assertEquals(2800.0, total);
    }
}

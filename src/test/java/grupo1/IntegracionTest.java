package grupo1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nsq.base.Pedido;
import org.nsq.grupo1.Servicio1;
import org.nsq.modelo.Producto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IntegracionTest {
    private Pedido pedido;
    private Producto p1, p2;

    @BeforeEach
    void init() {
        pedido = new Pedido();
        p1 = new Producto("Laptop", 1500, 10, "SKU123", "Electrónica", true, true);
        p2 = new Producto("Mouse", 50, 20, "SKU456", "Accesorios", true, false);
    }

    @Test
    void testFlujoExitoso() {
        assertTrue(pedido.agregarProducto(p1, 5));
        assertTrue(pedido.validarStock());
        assertTrue(Servicio1.validarDescuentoAplicable(p1, 10));
    }

    @Test
    void testErrorPorDuplicado() {
        assertTrue(pedido.agregarProducto(p1, 5));
        assertFalse(pedido.agregarProducto(p1, 3));
        assertTrue(pedido.validarStock());
        assertFalse(Servicio1.validarDescuentoAplicable(p2, 60));
    }

    @Test
    void testStockInvalidoYServicio() {
        Producto sinStock = new Producto("Monitor", 300, 0, "SKU999", "Electrónica", true, true);
        pedido.agregarProducto(sinStock, 0);
        assertFalse(pedido.validarStock());
        assertTrue(Servicio1.validarDescuentoAplicable(sinStock, 15));
    }
}

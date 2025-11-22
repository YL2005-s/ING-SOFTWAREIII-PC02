package grupo1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nsq.base.Pedido;
import org.nsq.modelo.Producto;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoDosTest {
    private Pedido pedido;
    private Producto p1, p2;

    @BeforeEach
    void setUp() {
        pedido = new Pedido();
        p1 = new Producto("Laptop", 1500, 10, "SKU123", "Electrónica", true, true);
        p2 = new Producto("Mouse", 50, 20, "SKU456", "Accesorios", true, false);
    }

    @Test
    void testCantidadInvalida() {
        assertFalse(pedido.agregarProducto(p1, 0));
    }

    @Test
    void testProductoDuplicadoPorSku() {
        assertTrue(pedido.agregarProducto(p1, 5));
        assertFalse(pedido.agregarProducto(p1, 3));
    }

    @Test
    void testAgregadoCorrecto() {
        assertTrue(pedido.agregarProducto(p1, 5));
        assertEquals(1, pedido.getDetallesPedido().size());
    }

    @Test
    void testPreservaAtributos() {
        pedido.agregarProducto(p1, 5);
        Producto agregado = pedido.getDetallesPedido().getFirst();

        assertEquals("Laptop", agregado.getNombre());
        assertEquals(1500, agregado.getPrecio());
        assertEquals("SKU123", agregado.getSku());
        assertEquals("Electrónica", agregado.getCategoria());
        assertTrue(agregado.isActivo());
        assertTrue(agregado.isDescuentoAplicable());
        assertEquals(5, agregado.getCantidad());
    }



}


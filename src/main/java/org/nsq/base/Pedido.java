package org.nsq.base;

import org.nsq.modelo.Producto;

import java.util.List;

public class Pedido {

    public static double calcularTotalPedido(List<Producto> productos, double descuento) {
        if (productos == null || productos.isEmpty()) {
            throw new IllegalArgumentException("Error: no hay productos en el pedido");
        }

        double subtotal = productos.stream()
                .mapToDouble(p -> p.getPrecio() * p.getCantidad())
                .sum();
        if (subtotal <= 0) {
            throw new IllegalArgumentException("Error: monto inválido");
        }
        return subtotal - (subtotal * (descuento / 100));
    }
}
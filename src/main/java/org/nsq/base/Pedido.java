package org.nsq.base;

import org.nsq.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private final List<Producto> detallesPedido;

    public Pedido(List<Producto> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }

    public boolean agregarProducto(Producto producto, int cantidad) {
        if (cantidad <= 0) {
            System.err.println("Error: cantidad inválida");
            return false;
        }

        if (producto == null || !producto.isActivo()) {
            return false;
        }

        boolean existe = detallesPedido.stream()
                .anyMatch(p -> p.getSku().equals(producto.getSku()));

        if (existe) return false;
        Producto copia = new Producto(
                producto.getNombre(),
                producto.getPrecio(),
                cantidad,
                producto.getSku(),
                producto.getCategoria(),
                producto.isActivo(),
                producto.isDescuentoAplicable()
        );

        detallesPedido.add(copia);
        return true;
    }

    public boolean validarStock() {
        if (detallesPedido.isEmpty()) return true;

        for (Producto p : detallesPedido) {
            if (p.getCantidad() <= 0) {
                return false;
            }
        }
        return true;
    }

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
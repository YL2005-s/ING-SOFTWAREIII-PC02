package org.nsq.modelo;

import java.util.Objects;

public class Producto {
    private String nombre;
    private double precio;
    private int cantidad;
    private String sku;
    private String categoria;
    private boolean esActivo;
    private boolean descuentoAplicable;

    public Producto(String nombre, double precio, int cantidad, String sku, String categoria, boolean esActivo, boolean descuentoAplicable) {
        if (precio < 0) throw new IllegalArgumentException("El precio no puede ser negativo");
        if (cantidad < 0) throw new IllegalArgumentException("La cantidad no puede ser negativa");
        if (sku == null || sku.trim().isEmpty()) throw new IllegalArgumentException("El SKU no puede ser nulo o vacío");

        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.sku = sku;
        this.categoria = categoria;
        this.esActivo = esActivo;
        this.descuentoAplicable = descuentoAplicable;
    }

    public Producto(String nombre, double precio, int cantidad) {
        this(nombre, precio, cantidad, "GEN-000", "General", true, false);
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio < 0) throw new IllegalArgumentException("El precio no puede ser negativo");
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad < 0) throw new IllegalArgumentException("La cantidad no puede ser negativa");
        this.cantidad = cantidad;
    }

    public String getSku() {
        return sku;
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean isActivo() {
        return esActivo;
    }

    public boolean isDescuentoAplicable() {
        return descuentoAplicable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto producto = (Producto) o;
        return Objects.equals(sku, producto.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }

    @Override
    public String toString() {
        return String.format("Producto[%s, %.2f, cant=%d, sku=%s, activo=%b, desc=%b]",
                nombre, precio, cantidad, sku, esActivo, descuentoAplicable);
    }
}

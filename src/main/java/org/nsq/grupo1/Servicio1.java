package org.nsq.grupo1;

import org.nsq.modelo.Producto;

public class Servicio1 {

    public static boolean verificarLimite(double total) {
        return total <= 5000;
    }

    public static boolean validarDescuentoAplicable(Producto p, double porcentaje) {
        return p != null && p.isDescuentoAplicable() && porcentaje >= 0 && porcentaje <= 50;
    }

}

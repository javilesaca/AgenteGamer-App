package com.miapp.agentegamer.ui.model;

import com.miapp.agentegamer.domain.model.SistemaFinanciero;

/**
 * Modelo UI para representar el estado financiero en la interfaz.
 * Contiene el estado, mensaje, color y porcentaje gastado.
 */
public class EstadoFinancieroUI {

    private final SistemaFinanciero.EstadoFinanciero estado;
    private final String mensaje;
    private final int colorRes;
    private final double porcentajeGastado;

    public EstadoFinancieroUI(
            SistemaFinanciero.EstadoFinanciero estado,
            String mensaje,
            int colorRes,
            double porcentajeGastado
    ) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.colorRes = colorRes;
        this.porcentajeGastado = porcentajeGastado;
    }

    public SistemaFinanciero.EstadoFinanciero getEstado() {
        return estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public int getColorRes() {
        return colorRes;
    }

    public double getPorcentajeGastado() {
        return porcentajeGastado;
    }
}

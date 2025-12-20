package com.miapp.agentegamer.agent;

import com.miapp.agentegamer.data.model.GastoEntity;
import com.miapp.agentegamer.data.remote.model.GameDto;

import java.util.List;

public class AgenteFinanciero {

    private final double presupuestoMensual;

    public AgenteFinanciero(double presupuestoMensual){
        this.presupuestoMensual = presupuestoMensual;
    }

    public double calcularTotalGastos(List<GastoEntity> gastos){
        double total = 0;
        for (GastoEntity gasto : gastos){
            total += gasto.getPrecio();
        }
        return total;
    }

    public double calcularPresupuestoRestante(List<GastoEntity> gastos){
        return presupuestoMensual - calcularTotalGastos(gastos);
    }

    public double calcularPorcentajeGastado(List<GastoEntity> gastos){
        return (calcularTotalGastos(gastos) / presupuestoMensual * 100);
    }

    public String generarRecomendacion(List<GastoEntity> gastos){
        double porcentaje = calcularPorcentajeGastado(gastos);

        if (porcentaje < 50) {
            return "Tenemos un buen control de gastos. Podemos permitirnos nuevas compras.";
        } else if (porcentaje < 80) {
            return "Atención : estamos consumiendo gran parte del presupuesto.";
        } else {
            return "Alerta: hemos superado el 80% del presupuesto de este mes.";
        }
    }

    public String evaluarEstado(double total){
        double restante = presupuestoMensual - total;

        if (restante <= 0) {
            return "Has agotado tu presupuesto mensual.";
        } else if (restante < 20) {
            return "Cuidado: te queda poco presupuesto.";
        } else {
            return "Te quedan " + restante + " €. Puedes permitirte un juego.";
        }
    }

    public boolean puedeComprar(double gastoActual, double precioJuego) {
        return (gastoActual + precioJuego) <= presupuestoMensual;
    }

    public enum EstadoFinanciero {
        VERDE, AMARILLO, ROJO
    }

    public EstadoFinanciero obtenerEstado(double totalGastado) {
        if (totalGastado < presupuestoMensual * 0.5) {
            return EstadoFinanciero.VERDE;
        } else if (totalGastado < presupuestoMensual * 0.8) {
            return EstadoFinanciero.AMARILLO;
        } else {
            return EstadoFinanciero.ROJO;
        }
    }

    public double estimarPrecio(GameDto juego) {

        //Si no tiene rating asumimos precio medio
        if (juego.getRating() <= 0) {
            return 49.99;
        }

        if (juego.getRating() >= 4.5) {
            return 69.99;
        } else if (juego.getRating() >= 4.0) {
            return 59.99;
        } else if (juego.getRating() >= 3.5) {
            return 49.99;
        } else {
            return 39.99;
        }
    }

}

package com.miapp.agentegamer.agent;

import com.miapp.agentegamer.data.model.GastoEntity;
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
}

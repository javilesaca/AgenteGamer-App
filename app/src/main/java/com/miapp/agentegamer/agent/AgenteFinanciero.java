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
        if (total == 0) {
            return "Sin gastos registrados.";
        } else if (total < presupuestoMensual * 0.5) {
            return "Tenemos un buen control de gastos.";
        } else if (total < presupuestoMensual) {
            return "Nos estamos acercando al límite del presupuesto.";
        } else {
            return "Hemos superado el presupuesto mensual.";
        }
    }
}

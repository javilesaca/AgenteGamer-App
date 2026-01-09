package com.miapp.agentegamer.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class FechaUtils {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public static long diasHasta(String fechaLanzamiento){
        try{
            Date fechajuego = FORMAT.parse(fechaLanzamiento);
            Date hoy = new Date();

            long diff = fechajuego.getTime() - hoy.getTime();
            long dias = TimeUnit.MICROSECONDS.toDays(diff);

            return Math.max(dias, 0); //el return evita numeros negativos
        } catch (ParseException e){
            return Long.MAX_VALUE;
        }
    }

    public static Date parseFecha(String fecha) {
        try{
            return FORMAT.parse(fecha);
        } catch (Exception e) {
            return null;
        }
    }

    public static long ahoraTimestamp() {
        return System.currentTimeMillis();
    }
}

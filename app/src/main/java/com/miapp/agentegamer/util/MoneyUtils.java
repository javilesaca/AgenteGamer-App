package com.miapp.agentegamer.util;

import java.util.Locale;
import java.text.NumberFormat;

/**
 * Utilidad para formatear dinero en euros.
 * Formatea valores numéricos al formato monetario español.
 */
public class MoneyUtils {

    private static final Locale LOCALE_ES = new Locale("es", "ES");

    /**
     * Formatea una cantidad de dinero en euros
     * Ejemplo: 250 -> 250,00 €
     */
    public static String format(double cantidad) {
        NumberFormat formato = NumberFormat.getCurrencyInstance(LOCALE_ES);
        return formato.format(cantidad);
    }
}

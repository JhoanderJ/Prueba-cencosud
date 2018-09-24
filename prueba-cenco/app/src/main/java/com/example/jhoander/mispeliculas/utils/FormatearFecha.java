package com.example.jhoander.mispeliculas.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class FormatearFecha {

    public static String formatear(String fecha){
        String miNuevaFecha = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("dd MMMM, yyyy", Locale.ENGLISH);
        try {
            miNuevaFecha = format2.format(format.parse(fecha));
        } catch (ParseException e) {


        }
        return miNuevaFecha;
    }
}
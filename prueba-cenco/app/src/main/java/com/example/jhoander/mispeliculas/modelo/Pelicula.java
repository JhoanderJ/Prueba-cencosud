package com.example.jhoander.mispeliculas.modelo;

import com.google.gson.annotations.SerializedName;

public class Pelicula {

    @SerializedName("title")
    public String titulo;
    @SerializedName("overview")
    public String resumen;
    @SerializedName("release_date")
    public String fecha;
    @SerializedName("poster_path")
    public String imagen;
    @SerializedName("original_language")
    public String lenguaje;
    @SerializedName("adult")
    public boolean adulto;

}
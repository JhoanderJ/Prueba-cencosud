package com.example.jhoander.mispeliculas.modelo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PeliculaList {

    @SerializedName("page")
    public Integer pagina;
    @SerializedName("results")
    public List<Pelicula> peliculas;

}
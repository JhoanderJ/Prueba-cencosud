package com.example.jhoander.mispeliculas.contratos;

import com.example.jhoander.mispeliculas.modelo.Pelicula;

import java.util.List;

public interface PeliculaContrato {

    interface Vista{
        void mostrarPelicula(List<Pelicula> peliculas);
        void mostrarError();
    }

    interface Presentador{
        void obtenerPeliculasPopular();
        void obtenerPeliculasTopRated();
    }
}

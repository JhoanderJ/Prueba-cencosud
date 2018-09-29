package com.example.jhoander.mispeliculas.contratos;


import com.example.jhoander.mispeliculas.modelo.Pelicula;
import com.example.jhoander.mispeliculas.retrofit.ApiServicios;
import java.util.List;

public class PeliculaPresentador implements PeliculaContrato.Presentador, ApiServicios.Services {

    PeliculaContrato.Vista vista;
    ApiServicios apiServicios;

    public PeliculaPresentador (PeliculaContrato.Vista vista){
        this.vista=vista;
        apiServicios = new ApiServicios(this);
    }

    @Override
    public void obtenerPeliculasPopular() {
        apiServicios.obtenerPeliculasPopular();
    }
    public void obtenerPeliculasTopRated(){
        apiServicios.obtenerPeliculasTopRated();
    }

    @Override
    public void onResponse(List<Pelicula> peliculas) {
        vista.mostrarPelicula(peliculas);
    }

    @Override
    public void onError(Throwable t) {
        vista.mostrarError();
    }
}


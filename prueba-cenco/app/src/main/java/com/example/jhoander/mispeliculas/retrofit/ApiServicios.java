package com.example.jhoander.mispeliculas.retrofit;

import com.example.jhoander.mispeliculas.BuildConfig;
import com.example.jhoander.mispeliculas.modelo.Pelicula;
import com.example.jhoander.mispeliculas.modelo.PeliculaList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiServicios {

    ApiInterface apiInterface;
    Services services;

    public ApiServicios(Services services) {
        this.services = services;
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public void obtenerPeliculasPopular(){

        Call<PeliculaList> call = apiInterface.doGetMoviePopular(BuildConfig.ApiKey);
        call.enqueue(new Callback<PeliculaList>() {
            @Override
            public void onResponse(Call<PeliculaList> call, Response<PeliculaList> response) {
                PeliculaList peliculaList = response.body();
                services.onResponse(peliculaList.peliculas);
            }

            @Override
            public void onFailure(Call<PeliculaList> call, Throwable t) {
                call.cancel();
                services.onError(t);

            }
        });
    }

    public void obtenerPeliculasTopRated(){

        Call<PeliculaList> call = apiInterface.doGetMovieTopRated(BuildConfig.ApiKey);
        call.enqueue(new Callback<PeliculaList>() {
            @Override
            public void onResponse(Call<PeliculaList> call, Response<PeliculaList> response) {
                PeliculaList peliculaList = response.body();
                services.onResponse(peliculaList.peliculas);
            }

            @Override
            public void onFailure(Call<PeliculaList> call, Throwable t) {
                call.cancel();
                services.onError(t);

            }
        });

    }

    public interface Services{
        void onResponse(List<Pelicula> peliculas);
        void onError(Throwable t);
    }
}
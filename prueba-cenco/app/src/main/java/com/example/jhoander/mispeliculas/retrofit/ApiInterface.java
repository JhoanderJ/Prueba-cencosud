package com.example.jhoander.mispeliculas.retrofit;

import com.example.jhoander.mispeliculas.modelo.PeliculaList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/3/movie/popular?")
    Call<PeliculaList> doGetMoviePopular(@Query("api_key") String apiKey);

    @GET("/3/movie/top_rated?")
    Call<PeliculaList> doGetMovieTopRated(@Query("api_key") String apiKey);
}
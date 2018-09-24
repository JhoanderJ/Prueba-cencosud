package com.example.jhoander.mispeliculas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.jhoander.mispeliculas.adaptador.PeliculaAdaptador;
import com.example.jhoander.mispeliculas.modelo.Pelicula;
import com.example.jhoander.mispeliculas.retrofit.ApiServicios;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ApiServicios.Services,PeliculaAdaptador.OnItemListener {

    private RecyclerView listaPeliculas;
    ApiServicios apiServicios;
    PeliculaAdaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarAdaptador();
        onRadioButtonCheckend();
        apiServicios = new ApiServicios(this);
        apiServicios.obtenerPeliculasPopular();
    }

    public void inicializarAdaptador(){
        listaPeliculas = (RecyclerView) findViewById(R.id.myRecyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaPeliculas.setLayoutManager(llm);
        adaptador = new PeliculaAdaptador();
        adaptador.setOnItemListener(this);
        listaPeliculas.setAdapter(adaptador);
    }

    @Override
    public void onResponse(List<Pelicula> peliculas) {
        adaptador.setPeliculas(peliculas);
    }

    @Override
    public void onError(Throwable t) {
        Toast.makeText(this,"hubo un error inesperado",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(Pelicula pelicula) {
        Gson gson = new Gson();
        Intent intent = new Intent(this,DetallePelicula.class);
        intent.putExtra("pelicula", gson.toJson(pelicula));
        startActivity(intent);
    }

    public void onRadioButtonCheckend(){

        ((RadioGroup) findViewById(R.id.rg_peliculas)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.rb_popular){
                    apiServicios.obtenerPeliculasPopular();
                }else {
                    apiServicios.obtenerPeliculasTopRated();

                }
            }
        });


    }
}

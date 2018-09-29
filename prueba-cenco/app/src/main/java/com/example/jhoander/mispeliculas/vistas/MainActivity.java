package com.example.jhoander.mispeliculas.vistas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.jhoander.mispeliculas.R;
import com.example.jhoander.mispeliculas.adaptador.PeliculaAdaptador;
import com.example.jhoander.mispeliculas.contratos.PeliculaContrato;
import com.example.jhoander.mispeliculas.contratos.PeliculaPresentador;
import com.example.jhoander.mispeliculas.modelo.Pelicula;
import com.google.gson.Gson;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PeliculaContrato.Vista,PeliculaAdaptador.OnItemListener {

    private RecyclerView listaPeliculas;
    PeliculaAdaptador adaptador;

    PeliculaContrato.Presentador presentador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarAdaptador();
        onRadioButtonCheckend();

        presentador = new PeliculaPresentador(this);
        presentador.obtenerPeliculasPopular();
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
                    presentador.obtenerPeliculasPopular();
                }else {
                    presentador.obtenerPeliculasTopRated();
                }
            }
        });

    }

    @Override
    public void mostrarPelicula(List<Pelicula> peliculas)  {
        adaptador.setPeliculas(peliculas);
    }

    @Override
    public void mostrarError() {
        Toast.makeText(this,"hubo un error inesperado",Toast.LENGTH_LONG).show();
    }
}

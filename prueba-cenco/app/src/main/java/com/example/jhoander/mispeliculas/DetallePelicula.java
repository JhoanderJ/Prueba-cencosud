package com.example.jhoander.mispeliculas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jhoander.mispeliculas.modelo.Pelicula;
import com.example.jhoander.mispeliculas.utils.FormatearFecha;
import com.google.gson.Gson;

public class DetallePelicula extends AppCompatActivity {

    Pelicula pelicula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pelicula);
        Gson gson = new Gson();
        pelicula = gson.fromJson(getIntent().getStringExtra("pelicula"),Pelicula.class);
        setData();
        setBack();
    }

    public void setBack(){
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    public void setData(){
        ((TextView) findViewById(R.id.tv_tituloRL)).setText(pelicula.titulo);
        ((TextView) findViewById(R.id.tv_resumenRL)).setText(pelicula.resumen);
        ((TextView) findViewById(R.id.tv_adultoRL)).setText(pelicula.adulto?"SI":"NO");
        Glide.with(this)
                .load( BuildConfig.BaseUrlImage+pelicula.imagen)
                .into(((ImageView) findViewById(R.id.iv_peliculaRL)));
        ((TextView) findViewById(R.id.tv_fechaRL)).setText(FormatearFecha.formatear(pelicula.fecha));
        ((TextView) findViewById(R.id.tv_lenguajeRL)).setText(pelicula.lenguaje);

    }
}

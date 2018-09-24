package com.example.jhoander.mispeliculas.adaptador;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jhoander.mispeliculas.BuildConfig;
import com.example.jhoander.mispeliculas.R;
import com.example.jhoander.mispeliculas.modelo.Pelicula;
import com.example.jhoander.mispeliculas.utils.FormatearFecha;

import java.util.ArrayList;
import java.util.List;

public class PeliculaAdaptador extends RecyclerView.Adapter<PeliculaAdaptador.PeliculaViewHolder> {

    List<Pelicula> peliculas;
    Context context;
    OnItemListener onItemListener;


    public PeliculaAdaptador(){
        peliculas = new ArrayList<>();
    }

    public void setPeliculas(List<Pelicula> peliculas){
        this.peliculas = peliculas;
        notifyDataSetChanged();
    }

    @Override
    public PeliculaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pelicula, parent, false);
         this.context = v.getContext();
        return new PeliculaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PeliculaViewHolder peliculaViewHolder, int position) {
        peliculaViewHolder.setData(peliculas.get(position));
    }

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    @Override
    public int getItemCount() {
       return peliculas.size();
    }

    public class PeliculaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgPeliculaCV;
        private TextView tvTituloCV;
        private TextView tvFechaCV;

        public Pelicula pelicula;

        public PeliculaViewHolder(View itemView) {
            super(itemView);
            imgPeliculaCV = (ImageView) itemView.findViewById(R.id.img_pelicula);
            tvTituloCV    = (TextView) itemView.findViewById(R.id.tv_titulo);
            tvFechaCV     = (TextView) itemView.findViewById(R.id.tv_fecha);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemListener.onItemClick(pelicula);
                }
            });
        }

        public void setData(Pelicula pelicula){
            this.pelicula = pelicula;
            Glide.with(context)
                    .load(BuildConfig.BaseUrlImage+pelicula.imagen)
                    .into(imgPeliculaCV);
            tvTituloCV.setText(pelicula.titulo);
            tvFechaCV.setText(FormatearFecha.formatear(pelicula.fecha));
        }
    }

    public interface OnItemListener{
        void onItemClick(Pelicula pelicula);
    }
}
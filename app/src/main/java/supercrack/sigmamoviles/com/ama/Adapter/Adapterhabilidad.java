package supercrack.sigmamoviles.com.ama.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Activity.CN_ListaVideoActivity;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.CN_habilidad;
import supercrack.sigmamoviles.com.ama.R;

/**
 * Created by eglp on 02/01/2017.
 */

public class Adapterhabilidad extends RecyclerView.Adapter<Adapterhabilidad.habilidadHolder>{

    private ArrayList<CN_habilidad> lista;
    private Activity activity;

    public Adapterhabilidad(ArrayList<CN_habilidad> lista, Activity activity) {
        this.lista = lista;
        this.activity = activity;
    }

    @Override
    public habilidadHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_habilidad,parent,false);
        return new habilidadHolder(view);
    }

    @Override
    public void onBindViewHolder(habilidadHolder holder, final int position) {

        Picasso.with(activity).load(lista.get(position).getImagen()).into(holder.imag);
        holder.titulo.setText(lista.get(position).getDescripcion());

        holder.imag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(activity , CN_ListaVideoActivity.class);

                intent.putExtra("titulo" , lista.get(position).getDescripcion());
                intent.putExtra("codigo" , lista.get(position).getUuid());

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                activity.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class habilidadHolder extends RecyclerView.ViewHolder {

        private ImageView imag;
        private TextView titulo;

        public habilidadHolder(View itemView) {
            super(itemView);

            imag = (ImageView) itemView.findViewById(R.id.lista_habilidad_imagen);
            titulo = (TextView) itemView.findViewById(R.id.lista_habilidad_titulo);

        }
    }

}

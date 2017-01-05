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

import supercrack.sigmamoviles.com.ama.Activity.HabilidadesActivity;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.CN_Edad;
import supercrack.sigmamoviles.com.ama.R;

/**
 * Created by eglp on 02/01/2017.
 */

public class AdapterEdad extends RecyclerView.Adapter<AdapterEdad.EdadHolder>{

    private ArrayList<CN_Edad> lista;
    private Activity activity;

    public AdapterEdad(ArrayList<CN_Edad> lista, Activity activity) {
        this.lista = lista;
        this.activity = activity;
    }

    @Override
    public EdadHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_edad,parent,false);
        return new EdadHolder(view);
    }

    @Override
    public void onBindViewHolder(EdadHolder holder, final int position) {

        Picasso.with(activity).load(lista.get(position).getImagen()).into(holder.img);
        holder.titulo.setText(lista.get(position).getDescripcion());

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(activity , HabilidadesActivity.class);

                intent.putExtra("Codigo" , lista.get(position).getUuid());

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

    public class EdadHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView titulo;

        public EdadHolder(View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.lista_edad_imagen);
            titulo = (TextView) itemView.findViewById(R.id.lista_edad_titulo);
        }
    }
}

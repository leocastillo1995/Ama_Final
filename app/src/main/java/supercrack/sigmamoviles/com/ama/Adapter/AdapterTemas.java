package supercrack.sigmamoviles.com.ama.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Activity.CN_TemaForoDetalleActivity;
import supercrack.sigmamoviles.com.ama.Modelo.Tema;
import supercrack.sigmamoviles.com.ama.R;

/**
 * Created by eglp on 26/12/2016.
 */

public class AdapterTemas extends RecyclerView.Adapter<AdapterTemas.TemasHolder>{

    private ArrayList<Tema> lista;
    private Activity activity;

    public AdapterTemas(ArrayList<Tema> lista, Activity activity) {
        this.lista = lista;
        this.activity = activity;
    }

    @Override
    public TemasHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_temas,parent,false);
        return new TemasHolder(view);
    }

    @Override
    public void onBindViewHolder(TemasHolder holder, final int position) {

        holder.titulo.setText(lista.get(position).getTitulo());
        holder.descrip.setText(lista.get(position).getDescripcion());

        holder.flecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(activity , CN_TemaForoDetalleActivity.class);

                intent.putExtra("id" , lista.get(position).getUuid());

                activity.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class TemasHolder extends RecyclerView.ViewHolder {

        private TextView titulo;
        private TextView descrip;
        private ImageView flecha;

        public TemasHolder(View itemView) {
            super(itemView);

            titulo = (TextView) itemView.findViewById(R.id.txt_listatitulo_titulo);
            descrip = (TextView) itemView.findViewById(R.id.txt_listatitulo_descrip);
            flecha = (ImageView) itemView.findViewById(R.id.img_listatitulo_flecha);
        }
    }
}

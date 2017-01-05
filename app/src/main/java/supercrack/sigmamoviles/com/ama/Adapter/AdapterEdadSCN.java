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

import supercrack.sigmamoviles.com.ama.Activity.SCN_HabilidadActivity;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.SCN_Edad;
import supercrack.sigmamoviles.com.ama.R;

/**
 * Created by eglp on 03/01/2017.
 */

public class AdapterEdadSCN extends RecyclerView.Adapter<AdapterEdadSCN.EdadesHolder>{

    private ArrayList<SCN_Edad> lista;
    private Activity activity;

    public AdapterEdadSCN(ArrayList<SCN_Edad> lista, Activity activity) {
        this.lista = lista;
        this.activity = activity;
    }

    @Override
    public EdadesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_edad,parent,false);
        return new EdadesHolder(view);
    }

    @Override
    public void onBindViewHolder(EdadesHolder holder, final int position) {

        holder.img.setImageResource(Integer.parseInt(lista.get(position).getUrl()));
        holder.titulo.setText(lista.get(position).getNombre());

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity , SCN_HabilidadActivity.class);
                intent.putExtra("codedad" , lista.get(position).getEdadid());
                activity.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class EdadesHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView titulo;

        public EdadesHolder(View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.lista_edad_imagen);
            titulo = (TextView) itemView.findViewById(R.id.lista_edad_titulo);
        }
    }
}

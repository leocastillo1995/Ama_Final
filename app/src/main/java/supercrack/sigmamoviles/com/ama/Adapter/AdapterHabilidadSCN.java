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

import supercrack.sigmamoviles.com.ama.Activity.SCN_ListaVideoActivity;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.SCN_Habilidad;
import supercrack.sigmamoviles.com.ama.R;

/**
 * Created by eglp on 03/01/2017.
 */

public class AdapterHabilidadSCN extends RecyclerView.Adapter<AdapterHabilidadSCN.HAbilidadSCN>{

    private ArrayList<SCN_Habilidad> lista;
    private Activity activity;

    public AdapterHabilidadSCN(ArrayList<SCN_Habilidad> lista, Activity activity) {
        this.lista = lista;
        this.activity = activity;
    }

    @Override
    public HAbilidadSCN onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_habilidad,parent,false);
        return new HAbilidadSCN(view);
    }

    @Override
    public void onBindViewHolder(HAbilidadSCN holder, final int position) {

        holder.imag.setImageResource(Integer.parseInt(lista.get(position).getUrl()));
        holder.titulo.setText(lista.get(position).getNombre());

        holder.imag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity , SCN_ListaVideoActivity.class);
                intent.putExtra("codhabilidad" , lista.get(position).getHabilidaid());
                intent.putExtra("codid" , activity.getIntent().getExtras().getString("codedad"));
                intent.putExtra("nombre" , lista.get(position).getNombre());
                activity.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class HAbilidadSCN extends RecyclerView.ViewHolder {

        private ImageView imag;
        private TextView titulo;

        public HAbilidadSCN(View itemView) {
            super(itemView);

            imag = (ImageView) itemView.findViewById(R.id.lista_habilidad_imagen);
            titulo = (TextView) itemView.findViewById(R.id.lista_habilidad_titulo);
        }
    }

}

package supercrack.sigmamoviles.com.ama.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.PreguntaOpcion;
import supercrack.sigmamoviles.com.ama.R;

/**
 * Created by eglp on 30/12/2016.
 */

public class AdapterPreguntas extends RecyclerView.Adapter<AdapterPreguntas.PreguntasHolder>{

    private ArrayList<PreguntaOpcion> array;
    private Activity activity;
    private static int mSelectedItem = 0;

    public AdapterPreguntas(ArrayList<PreguntaOpcion> array, Activity activity) {
        this.array = array;
        this.activity = activity;
    }

    @Override
    public PreguntasHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_preguntas,parent,false);
        return new PreguntasHolder(view);
    }

    public PreguntaOpcion onSelected(){
        if (mSelectedItem!=-1){
            return  array.get(mSelectedItem);
        }else {
            return  null;
        }
    }

    @Override
    public void onBindViewHolder(PreguntasHolder holder, int position) {

        holder.opcion.setText(array.get(position).getDescripcion());
        holder.opcion.setChecked(position == mSelectedItem);

    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class PreguntasHolder extends RecyclerView.ViewHolder {

        private RadioButton opcion;

        public PreguntasHolder(final View inflate) {
            super(inflate);

            opcion = (RadioButton) inflate.findViewById(R.id.list_cnpreguntas_rbutton);
            itemView.setOnClickListener(clickListener);
            opcion.setOnClickListener(clickListener);
        }

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelectedItem = getAdapterPosition();
                notifyItemRangeChanged(0, array.size());
            }
        };
    }
}

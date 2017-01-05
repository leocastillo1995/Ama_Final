package supercrack.sigmamoviles.com.ama.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.PreguntaOpcion;
import supercrack.sigmamoviles.com.ama.R;

/**
 * Created by eglp on 02/01/2017.
 */

public class AdapterPregunta1_scn extends RecyclerView.Adapter<AdapterPregunta1_scn.Pregunta1scnHolder>{

    private ArrayList<PreguntaOpcion> array;
    private Activity activity;
    private static int mSelectedItem = 0;

    public AdapterPregunta1_scn(ArrayList<PreguntaOpcion> array, Activity activity) {
        this.array = array;
        this.activity = activity;
    }

    @Override
    public Pregunta1scnHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pregunta1,parent,false);
        return new Pregunta1scnHolder(view);
    }

    public PreguntaOpcion onSelected(){
        if (mSelectedItem!=-1){
            return  array.get(mSelectedItem);
        }else {
            return  null;
        }
    }

    @Override
    public void onBindViewHolder(Pregunta1scnHolder holder, int position) {

        holder.opcion.setText(array.get(position).getDescripcion());
        holder.opcion.setChecked(position==mSelectedItem);

        if(array.get(position).getDescripcion().equalsIgnoreCase("femenino"))
        {
            holder.image.setImageResource(R.drawable.femenino);
        }
        else
        {
            holder.image.setImageResource(R.drawable.masculino);
        }

    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class Pregunta1scnHolder extends RecyclerView.ViewHolder {

        private RadioButton opcion;
        private ImageView image;

        public Pregunta1scnHolder(final View inflate) {
            super(inflate);

            opcion = (RadioButton) inflate.findViewById(R.id.list_pregunta1_rbuton);
            image = (ImageView) inflate.findViewById(R.id.list_pregunta1_img);
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

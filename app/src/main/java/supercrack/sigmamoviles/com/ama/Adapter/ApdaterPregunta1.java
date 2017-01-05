package supercrack.sigmamoviles.com.ama.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.PreguntaOpcion;
import supercrack.sigmamoviles.com.ama.R;

/**
 * Created by eglp on 30/12/2016.
 */

public class ApdaterPregunta1 extends RecyclerView.Adapter<ApdaterPregunta1.Pregunta1Holder>{

    private ArrayList<PreguntaOpcion> array;
    private Activity activity;
    private static int mSelectedItem = 0;

    public ApdaterPregunta1(ArrayList<PreguntaOpcion> array, Activity activity) {
        this.array = array;
        this.activity = activity;
    }

    @Override
    public Pregunta1Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pregunta1,parent,false);
        return new Pregunta1Holder(view);
    }

    public PreguntaOpcion onSelected(){
        if (mSelectedItem!=-1){
         return  array.get(mSelectedItem);
        }else {
            return  null;
        }
    }

    @Override
    public void onBindViewHolder(Pregunta1Holder holder, int position) {

        holder.opcion.setText(array.get(position).getDescripcion());
        Picasso.with(activity).load(array.get(position).getImagen()).into(holder.image);
        holder.opcion.setChecked(position==mSelectedItem);

    }


    @Override
    public int getItemCount() {
        return array.size();
    }

    public class Pregunta1Holder extends RecyclerView.ViewHolder {

        private RadioButton opcion;
        private ImageView image;

        public Pregunta1Holder(final View inflate) {
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

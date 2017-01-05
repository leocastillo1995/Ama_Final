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

import supercrack.sigmamoviles.com.ama.Activity.CN_VideoCompletoActivity;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.CN_video;
import supercrack.sigmamoviles.com.ama.R;

/**
 * Created by eglp on 02/01/2017.
 */

public class AdapterVideo extends RecyclerView.Adapter<AdapterVideo.VideoHolder>{

    private ArrayList<CN_video> lista;
    private Activity activity;

    public AdapterVideo(ArrayList<CN_video> lista, Activity activity) {
        this.lista = lista;
        this.activity = activity;
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_video,parent,false);
        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoHolder holder, final int position) {

        Picasso.with(activity).load(lista.get(position).getImagen()).into(holder.imag);
        holder.descrip.setText(lista.get(position).getDescripcion());

        holder.imag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(activity , CN_VideoCompletoActivity.class);

                intent.putExtra("codigovideo" , lista.get(position).getUuid());

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

    public class VideoHolder extends RecyclerView.ViewHolder {

        private ImageView imag;
        private TextView descrip;

        public VideoHolder(View itemView) {
            super(itemView);

            imag = (ImageView) itemView.findViewById(R.id.lista_videos_imagen);
            descrip = (TextView) itemView.findViewById(R.id.lista_videos_descrip);
        }
    }
}

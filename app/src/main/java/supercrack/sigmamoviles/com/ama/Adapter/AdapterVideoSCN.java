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

import supercrack.sigmamoviles.com.ama.Activity.SCN_VideoCompletoActivity;
import supercrack.sigmamoviles.com.ama.Array.Conexion.Sin_Conexion.ArrayVideoSCN;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.SCN_Video;
import supercrack.sigmamoviles.com.ama.R;

/**
 * Created by eglp on 03/01/2017.
 */

public class AdapterVideoSCN extends RecyclerView.Adapter<AdapterVideoSCN.VideoSCNHolder>{

    private ArrayList<SCN_Video> lista = new ArrayList<SCN_Video>();
    private Activity activity;

    public AdapterVideoSCN(Activity activity , String codedad , String codhabilidad)
    {
        this.activity = activity;

        ArrayVideoSCN videos = new ArrayVideoSCN(activity);

        for (SCN_Video x: videos.listavideo)
        {
            if(x.getEdadid().equals(codedad) && x.getHabilidaid().equals(codhabilidad))
            {
                SCN_Video a = new SCN_Video(x.getVideoid() , x.getEdadid() , x.getHabilidaid() , x.getNombre() , x.getImagen() , x.getRuta() , x.getSecuencia() , x.getActivo());
                lista.add(a);
            }
        }


    }

    @Override
    public VideoSCNHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_video,parent,false);
        return new VideoSCNHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoSCNHolder holder, final int position) {

        holder.descrip.setText(lista.get(position).getNombre());
        holder.imag.setImageResource(Integer.parseInt(lista.get(position).getImagen()));

        holder.imag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity , SCN_VideoCompletoActivity.class);
                intent.putExtra("codvid" , lista.get(position).getVideoid());
                activity.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class VideoSCNHolder extends RecyclerView.ViewHolder {

        private ImageView imag;
        private TextView descrip;

        public VideoSCNHolder(View itemView) {
            super(itemView);

            imag = (ImageView) itemView.findViewById(R.id.lista_videos_imagen);
            descrip = (TextView) itemView.findViewById(R.id.lista_videos_descrip);
        }
    }
}

package supercrack.sigmamoviles.com.ama.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Array.Conexion.ArrayUsuario;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.ComentarioForo;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Usuario;
import supercrack.sigmamoviles.com.ama.R;

/**
 * Created by eglp on 26/12/2016.
 */

public class AdapterComentarios extends RecyclerView.Adapter<AdapterComentarios.ComentariosHolder>{

    private ArrayUsuario arrayUsu = new ArrayUsuario();
    private ArrayList<ComentarioForo> listacometarion;
    private String titulo;

    public AdapterComentarios(ArrayList<ComentarioForo> listacometarion, ArrayList<Usuario> arrayUsuario, String titulo) {
        this.listacometarion = listacometarion;
        this.titulo = titulo;

        arrayUsu.listUsuario = arrayUsuario;
    }

    @Override
    public ComentariosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_comentario,parent,false);
        return new AdapterComentarios.ComentariosHolder(view);
    }

    @Override
    public void onBindViewHolder(ComentariosHolder holder, int position) {

        Usuario x = arrayUsu.ObtenerUsuario(listacometarion.get(position).getUsername());

        if(x.getGenero() == 0)
        {
            holder.perfil.setImageResource(R.drawable.masculino);
        }
        else
        {
            holder.perfil.setImageResource(R.drawable.femenino);
        }

        holder.usuario.setText(x.getFirst_name());
        holder.titulo.setText(titulo);
        holder.descripcion.setText(listacometarion.get(position).getDescripcion());

    }

    @Override
    public int getItemCount() {
        return listacometarion.size();
    }

    public class ComentariosHolder extends RecyclerView.ViewHolder {

        private ImageView perfil;
        private TextView usuario;
        private TextView titulo;
        private TextView descripcion;

        public ComentariosHolder(View itemView) {
            super(itemView);

            perfil = (ImageView) itemView.findViewById(R.id.img_listacomentarios_perfil);
            usuario = (TextView) itemView.findViewById(R.id.txt_listacomentarios_perfil);
            titulo = (TextView) itemView.findViewById(R.id.txt_listacomentarios_titulo);
            descripcion = (TextView) itemView.findViewById(R.id.txt_listacomentarios_comentario);

        }
    }
}

package supercrack.sigmamoviles.com.ama.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.ButterKnife;
import butterknife.InjectView;
import supercrack.sigmamoviles.com.ama.Adapter.AdapterVideo;
import supercrack.sigmamoviles.com.ama.Array.Conexion.ArrayVideos;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.CN_video;
import supercrack.sigmamoviles.com.ama.R;

public class CN_ListaVideoActivity extends AppCompatActivity {

    private ArrayVideos videos;
    private AdapterVideo adapterVideo;
    private RecyclerView.LayoutManager recyclerViewLayoutManage;

    @InjectView(R.id.lista_cnactivitylistavideos_videos)
    RecyclerView listavideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cn_lista_video);

        videos = new ArrayVideos(this);
        ButterKnife.inject(this);

        String titulo = getIntent().getExtras().getString("titulo");
        String codigo = getIntent().getExtras().getString("codigo");

        barra(titulo);

        ArrayList<CN_video> lista = videos.listatodo(codigo);

        Collections.sort(lista, new Comparator<CN_video>() {
            @Override
            public int compare(CN_video cn_video, CN_video t1) {
                return new Integer(cn_video.getSecuencia()).compareTo(t1.getSecuencia());
            }
        });

        recyclerViewLayoutManage = new GridLayoutManager(CN_ListaVideoActivity.this , 2);
        listavideo.setLayoutManager(recyclerViewLayoutManage);

        adapterVideo = new AdapterVideo(lista , this);
        listavideo.setAdapter(adapterVideo);
    }

    private void barra(String titulo)
    {
        Toolbar toll = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toll);
        getSupportActionBar().setTitle(titulo);
    }
}

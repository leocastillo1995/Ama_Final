package supercrack.sigmamoviles.com.ama.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import supercrack.sigmamoviles.com.ama.Adapter.AdapterVideoSCN;
import supercrack.sigmamoviles.com.ama.R;

public class SCN_ListaVideoActivity extends AppCompatActivity {

    private AdapterVideoSCN videoSCN;
    private RecyclerView.LayoutManager recyclerViewLayoutManage;

    @InjectView(R.id.lista_scnactivitlistavideos_lista)
    RecyclerView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scn_lista_video);

        String cod1= getIntent().getExtras().getString("codid");
        String cod2= getIntent().getExtras().getString("codhabilidad");
        String nombre = getIntent().getExtras().getString("nombre");

        ButterKnife.inject(this);
        barra(nombre);

        recyclerViewLayoutManage = new GridLayoutManager(SCN_ListaVideoActivity.this , 2);
        lista.setLayoutManager(recyclerViewLayoutManage);

        videoSCN = new AdapterVideoSCN(this , cod1 , cod2);
        lista.setAdapter(videoSCN);
    }

    public void barra(String titulo)
    {
        Toolbar toll = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toll);
        getSupportActionBar().setTitle(titulo);
    }
}

package supercrack.sigmamoviles.com.ama.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import supercrack.sigmamoviles.com.ama.Adapter.AdapterEdadSCN;
import supercrack.sigmamoviles.com.ama.Array.Conexion.Sin_Conexion.ArrayEdadSCN;
import supercrack.sigmamoviles.com.ama.R;

public class SCN_EdadActivity extends AppCompatActivity {

    private ArrayEdadSCN edadSCN;
    private RecyclerView.LayoutManager recyclerViewLayoutManage;
    private AdapterEdadSCN adapterEdad;

    @InjectView(R.id.lista_scnedad_listaedad)
    RecyclerView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scn_edad);

        edadSCN = new ArrayEdadSCN(this);
        barra("Edad");
        ButterKnife.inject(this);

        recyclerViewLayoutManage = new GridLayoutManager(SCN_EdadActivity.this , 2);
        lista.setLayoutManager(recyclerViewLayoutManage);

        adapterEdad = new AdapterEdadSCN(edadSCN.listaedad , SCN_EdadActivity.this);
        lista.setAdapter(adapterEdad);

    }

    private void barra(String titulo)
    {
        Toolbar toll = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toll);
        getSupportActionBar().setTitle(titulo);
    }
}

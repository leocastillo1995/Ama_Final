package supercrack.sigmamoviles.com.ama.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import supercrack.sigmamoviles.com.ama.Adapter.AdapterHabilidadSCN;
import supercrack.sigmamoviles.com.ama.Array.Conexion.Sin_Conexion.ArrayHabilidadSCN;
import supercrack.sigmamoviles.com.ama.R;

public class SCN_HabilidadActivity extends AppCompatActivity {

    private ArrayHabilidadSCN arrayHabilidadSCN;
    private AdapterHabilidadSCN habilidadSCN;
    private RecyclerView.LayoutManager recyclerViewLayoutManage;

    @InjectView(R.id.lista_scnhabilidad_listahabilidad)
    RecyclerView listahabilidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scn_habilidad);

        arrayHabilidadSCN = new ArrayHabilidadSCN(this);

        barra("Habilidad");
        ButterKnife.inject(this);

        recyclerViewLayoutManage = new GridLayoutManager(SCN_HabilidadActivity.this , 2);
        listahabilidad.setLayoutManager(recyclerViewLayoutManage);

        habilidadSCN = new AdapterHabilidadSCN(arrayHabilidadSCN.listahabilidad , SCN_HabilidadActivity.this);
        listahabilidad.setAdapter(habilidadSCN);
    }

    private void barra(String titulo)
    {
        Toolbar toll = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toll);
        getSupportActionBar().setTitle(titulo);
    }
}

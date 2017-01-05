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
import supercrack.sigmamoviles.com.ama.Adapter.Adapterhabilidad;
import supercrack.sigmamoviles.com.ama.Array.Conexion.ArrayHabilidad;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.CN_habilidad;
import supercrack.sigmamoviles.com.ama.R;

public class HabilidadesActivity extends AppCompatActivity {

    private Adapterhabilidad adapterhabilidad;
    private ArrayHabilidad habilidad;
    private RecyclerView.LayoutManager recyclerViewLayoutManage;

    @InjectView(R.id.lista_cnactivity_listahabilidad)
    RecyclerView listahabilidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habilidades);

        habilidad = new ArrayHabilidad(this);
        ButterKnife.inject(this);

        barra("Habilidades");

        String codigo = getIntent().getExtras().getString("Codigo");

        recyclerViewLayoutManage = new GridLayoutManager(HabilidadesActivity.this , 2);
        listahabilidad.setLayoutManager(recyclerViewLayoutManage);

        ArrayList<CN_habilidad> lista = habilidad.listatodo(codigo);

        Collections.sort(lista, new Comparator<CN_habilidad>() {
            @Override
            public int compare(CN_habilidad cn_habilidad, CN_habilidad t1) {
                return new Integer(cn_habilidad.getSecuencia()).compareTo(t1.getSecuencia());
            }
        });

        adapterhabilidad = new Adapterhabilidad(lista , this);
        listahabilidad.setAdapter(adapterhabilidad);

    }

    private void barra(String titulo)
    {
        Toolbar toll = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toll);
        getSupportActionBar().setTitle(titulo);
    }
}

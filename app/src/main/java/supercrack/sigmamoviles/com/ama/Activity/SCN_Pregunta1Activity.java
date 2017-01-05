package supercrack.sigmamoviles.com.ama.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import supercrack.sigmamoviles.com.ama.Adapter.AdapterPregunta1_scn;
import supercrack.sigmamoviles.com.ama.Array.Conexion.ArrayPregunta;
import supercrack.sigmamoviles.com.ama.Array.Conexion.Sin_Conexion.ArrayPreguntaOpcion;
import supercrack.sigmamoviles.com.ama.Array.Conexion.Sin_Conexion.ArrayPreguntaTitulo;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Preguntas;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.PreguntaOpcion;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.PreguntasTitulo;
import supercrack.sigmamoviles.com.ama.R;

public class SCN_Pregunta1Activity extends AppCompatActivity {

    private ArrayPreguntaTitulo titulo;
    private ArrayPreguntaOpcion opcion;
    private AdapterPregunta1_scn pregunta1;
    private ArrayPregunta arrayPregunta = new ArrayPregunta();

    @InjectView(R.id.txt_scnactivitypregunta1_titulo)
    TextView txt_titulo;

    @InjectView(R.id.lista_scnactivitypregunta1_lista)
    RecyclerView lista;

    @InjectView(R.id.btn_scnactivitypregunta1_siguiente)
    View btn_siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scn_pregunta1);

        titulo = new ArrayPreguntaTitulo(this);
        opcion = new ArrayPreguntaOpcion(this);

        ButterKnife.inject(this);
        barra("Preguntas");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SCN_Pregunta1Activity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lista.setLayoutManager(linearLayoutManager);

        PreguntasTitulo x = titulo.Obtenertodo(3);

        txt_titulo.setText(x.getTitulo());

        pregunta1 = new AdapterPregunta1_scn(opcion.ObtenerTodo(x.getIdpregunta()), SCN_Pregunta1Activity.this);
        lista.setAdapter(pregunta1);

    }

    private void barra(String titulo)
    {
        Toolbar toll = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toll);
        getSupportActionBar().setTitle(titulo);
    }

    private void proceso()
    {
        PreguntaOpcion element = pregunta1.onSelected();

        Intent intent = new Intent(this , SCN_PreguntasActivity.class);

        Preguntas x = new Preguntas(null , element.getUuid());
        arrayPregunta.Agregar(x);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }

    @OnClick(R.id.btn_scnactivitypregunta1_siguiente)
    public void siguiente()
    {
        proceso();
    }
}

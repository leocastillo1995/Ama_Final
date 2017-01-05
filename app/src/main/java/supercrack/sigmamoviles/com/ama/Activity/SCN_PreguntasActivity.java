package supercrack.sigmamoviles.com.ama.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import supercrack.sigmamoviles.com.ama.Adapter.AdapterPreguntas;
import supercrack.sigmamoviles.com.ama.Array.Conexion.ArrayPregunta;
import supercrack.sigmamoviles.com.ama.Array.Conexion.Sin_Conexion.ArrayPreguntaOpcion;
import supercrack.sigmamoviles.com.ama.Array.Conexion.Sin_Conexion.ArrayPreguntaTitulo;
import supercrack.sigmamoviles.com.ama.Array.Conexion.Sin_Conexion.ArrayPreguntaopcionesSCN;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.PreguntaOpciones;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Preguntas;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.PreguntaOpcion;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.PreguntasTitulo;
import supercrack.sigmamoviles.com.ama.R;
import supercrack.sigmamoviles.com.ama.ServicioInterno.RegistrosProcesoPreguntas;

public class SCN_PreguntasActivity extends AppCompatActivity {

    private ArrayPregunta arrayPregunta = new ArrayPregunta();
    private ArrayPreguntaTitulo titulo;
    private AdapterPreguntas adapterPreguntas;
    private RecyclerView.LayoutManager recyclerViewLayoutManage;
    private ArrayPreguntaOpcion opcion;
    private ArrayPreguntaopcionesSCN SCNpreguntas;
    private int contado = 1;
    private int preguntastotal;
    private int pregun;
    private RegistrosProcesoPreguntas regpreguntas;

    @InjectView(R.id.txt_scnactivitypreguntas_titulo)
    TextView txt_titulo;

    @InjectView(R.id.lista_scnactivitypreguntas_lista)
    RecyclerView lista;

    @InjectView(R.id.btn_scnactivitypreguntas_volver)
    View btn_volver;

    @InjectView(R.id.btn_scnactivitypreguntas_siguiente)
    View btn_siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scn__preguntas);

        titulo = new ArrayPreguntaTitulo(this);
        opcion = new ArrayPreguntaOpcion(this);
        regpreguntas = new RegistrosProcesoPreguntas(this);
        SCNpreguntas = new ArrayPreguntaopcionesSCN(this);

        ButterKnife.inject(this);
        barra("Preguntas");

        borrar();

        preguntastotal = titulo.lista.size();

        recyclerViewLayoutManage = new GridLayoutManager(SCN_PreguntasActivity.this , 2);
        lista.setLayoutManager(recyclerViewLayoutManage);

        PreguntasTitulo x = titulo.Obtenertodo(contado);

        txt_titulo.setText(x.getTitulo());

        ArrayList<PreguntaOpcion> list = opcion.ObtenerTodo(x.getIdpregunta());

        Collections.sort(list, new Comparator<PreguntaOpcion>() {
            @Override
            public int compare(PreguntaOpcion preguntaOpcion, PreguntaOpcion t1) {
                return new Integer(preguntaOpcion.getSecuencia()).compareTo(t1.getSecuencia());
            }
        });

        adapterPreguntas = new AdapterPreguntas(list, SCN_PreguntasActivity.this);
        lista.setAdapter(adapterPreguntas);
    }

    private void barra(String titulo)
    {
        Toolbar toll = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toll);
        getSupportActionBar().setTitle(titulo);
    }

    private void proceso()
    {
        contado++;

        PreguntasTitulo q = titulo.Obtenertodo(contado);

        PreguntaOpcion a = adapterPreguntas.onSelected();



        if(contado > preguntastotal)
        {

                Preguntas preguntas = new Preguntas(null , a.getUuid());
                arrayPregunta.Agregar(preguntas);


                for (int i = 0 ; i < arrayPregunta.listapreguntas.size() ;i++)
                {
                    pregun = i;
                    Preguntas x = arrayPregunta.listapreguntas.get(i);

                    PreguntaOpciones n = new PreguntaOpciones(x.getUuid());

                    regpreguntas.Registrar(n);

                    if(pregun == arrayPregunta.listapreguntas.size() - 1)
                    {
                        mensaje("Sus respuestas ya fueron registradas !Gracias¡");
                    }
            }
        }
        else
        {
            if(contado == 3)
            {
                int cond = contado+=1;

                PreguntasTitulo x = titulo.Obtenertodo(cond);

                txt_titulo.setText(x.getTitulo());

                ArrayList<PreguntaOpcion> list = opcion.ObtenerTodo(x.getIdpregunta());

                Collections.sort(list, new Comparator<PreguntaOpcion>() {
                    @Override
                    public int compare(PreguntaOpcion preguntaOpcion, PreguntaOpcion t1) {
                        return new Integer(preguntaOpcion.getSecuencia()).compareTo(t1.getSecuencia());
                    }
                });

                adapterPreguntas = new AdapterPreguntas(list, SCN_PreguntasActivity.this);
                lista.setAdapter(adapterPreguntas);

                Preguntas preguntas = new Preguntas(null , a.getUuid());
                arrayPregunta.Agregar(preguntas);

            }
            else
            {
                PreguntasTitulo x = titulo.Obtenertodo(contado);

                txt_titulo.setText(q.getTitulo());

                ArrayList<PreguntaOpcion> list = opcion.ObtenerTodo(q.getIdpregunta());

                Collections.sort(list, new Comparator<PreguntaOpcion>() {
                    @Override
                    public int compare(PreguntaOpcion preguntaOpcion, PreguntaOpcion t1) {
                        return new Integer(preguntaOpcion.getSecuencia()).compareTo(t1.getSecuencia());
                    }
                });

                adapterPreguntas = new AdapterPreguntas(list, SCN_PreguntasActivity.this);
                lista.setAdapter(adapterPreguntas);

                Preguntas preguntas = new Preguntas(null , a.getUuid());
                arrayPregunta.Agregar(preguntas);

            }
        }
    }

    private void retroceder()
    {
        contado--;

        PreguntasTitulo q = titulo.Obtenertodo(contado);

        if(contado == 0)
        {
            Intent intent = new Intent(SCN_PreguntasActivity.this , CN_Pregunta1Activity.class);

            arrayPregunta.limipar();

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        }
        else
        {
            if(contado == 3)
            {
                int cond = contado-=1;

                PreguntasTitulo x = titulo.Obtenertodo(cond);

                txt_titulo.setText(x.getTitulo());

                ArrayList<PreguntaOpcion> list = opcion.ObtenerTodo(x.getIdpregunta());

                Collections.sort(list, new Comparator<PreguntaOpcion>() {
                    @Override
                    public int compare(PreguntaOpcion preguntaOpcion, PreguntaOpcion t1) {
                        return new Integer(preguntaOpcion.getSecuencia()).compareTo(t1.getSecuencia());
                    }
                });

                adapterPreguntas = new AdapterPreguntas(list, SCN_PreguntasActivity.this);
                lista.setAdapter(adapterPreguntas);

                arrayPregunta.remover();

            }
            else
            {
                PreguntasTitulo x = titulo.Obtenertodo(contado);

                txt_titulo.setText(q.getTitulo());

                ArrayList<PreguntaOpcion> list = opcion.ObtenerTodo(q.getIdpregunta());

                Collections.sort(list, new Comparator<PreguntaOpcion>() {
                    @Override
                    public int compare(PreguntaOpcion preguntaOpcion, PreguntaOpcion t1) {
                        return new Integer(preguntaOpcion.getSecuencia()).compareTo(t1.getSecuencia());
                    }
                });

                adapterPreguntas = new AdapterPreguntas(list, SCN_PreguntasActivity.this);
                lista.setAdapter(adapterPreguntas);

                arrayPregunta.remover();
            }
        }
    }

    private void borrar()
    {
        for (PreguntaOpciones h : SCNpreguntas.lista)
        {
            regpreguntas.Eliminar(h.getId_opcion());
        }
    }

    private void mensaje(String mensaje)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ama");
        builder.setMessage(mensaje);
        builder.setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                arrayPregunta.limipar();

                Intent intent = new Intent(SCN_PreguntasActivity.this , SCN_EdadActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @OnClick(R.id.btn_scnactivitypreguntas_volver)
    public void volver()
    {
        retroceder();
    }

    @OnClick(R.id.btn_scnactivitypreguntas_siguiente)
    public void siguiente()
    {
        proceso();
    }

}

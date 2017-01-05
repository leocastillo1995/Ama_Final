package supercrack.sigmamoviles.com.ama.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import supercrack.sigmamoviles.com.ama.Adapter.AdapterPreguntas;
import supercrack.sigmamoviles.com.ama.Array.Conexion.ArrayPregunta;
import supercrack.sigmamoviles.com.ama.Array.Conexion.Sin_Conexion.ArrayPreguntaOpcion;
import supercrack.sigmamoviles.com.ama.Array.Conexion.Sin_Conexion.ArrayPreguntaTitulo;
import supercrack.sigmamoviles.com.ama.Conexion.ServicioAma;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.PreguntaOpciones;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Preguntas;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.PreguntaOpcion;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.PreguntasTitulo;
import supercrack.sigmamoviles.com.ama.R;

public class CN_PreguntasActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    private ArrayPregunta arrayPregunta = new ArrayPregunta();
    private ArrayPreguntaTitulo titulo;
    private AdapterPreguntas adapterPreguntas;
    private RecyclerView.LayoutManager recyclerViewLayoutManage;
    private ArrayPreguntaOpcion opcion;
    private int contado = 1;
    private int preguntastotal;
    private int pregun;

    @InjectView(R.id.lista_cnactivity_listapregunta)
    RecyclerView lista;

    @InjectView(R.id.txt_cnactivitypreguntas_titulo)
    TextView txt_titulo;

    @InjectView(R.id.btn_cnactivitypreguntas_siguiente)
    View btn_siguiente;

    @InjectView(R.id.btn_cnactivitypreguntas_volver)
    View btn_retroceso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cn_preguntas);

        titulo = new ArrayPreguntaTitulo(this);
        opcion = new ArrayPreguntaOpcion(this);

        ButterKnife.inject(this);
        barra("Preguntas");

        preguntastotal = titulo.lista.size();

        recyclerViewLayoutManage = new GridLayoutManager(CN_PreguntasActivity.this , 2);
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

        adapterPreguntas = new AdapterPreguntas(list, CN_PreguntasActivity.this);
        lista.setAdapter(adapterPreguntas);
    }

    private String Token()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Token" , Context.MODE_PRIVATE);
        return "bearer " +sharedPreferences.getString("token" , "");
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
                if(a.getUuid() == null)
                {
                    mensaje2("Tiene que seleccionar una opción");
                }
                else
                {
                    Preguntas preguntas = new Preguntas(null , a.getUuid());
                    arrayPregunta.Agregar(preguntas);

                    showProgress();
                    Retrofit retrofit = new Retrofit.Builder().baseUrl(ServicioAma.URL)
                            .addConverterFactory(GsonConverterFactory.create()).build();

                    ServicioAma ama = retrofit.create(ServicioAma.class);

                    for (int i = 0 ; i < arrayPregunta.listapreguntas.size() ;i++)
                    {
                        pregun = i;
                        Preguntas x = arrayPregunta.listapreguntas.get(i);

                        PreguntaOpciones n = new PreguntaOpciones(x.getUuid());

                        ama.registrarpregunta(Token() , n).enqueue(new Callback<PreguntaOpciones>() {
                            @Override
                            public void onResponse(Call<PreguntaOpciones> call, Response<PreguntaOpciones> response) {

                                dialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<PreguntaOpciones> call, Throwable t) {

                                mensaje_SCN("Ama a detectado que no está conectado se activara el modo cuestionario para que lo pueda utilizar. Cuando se vuelva conectar se sincronizara los datos registrados ¡Gracias por su atención!");
                                dialog.dismiss();
                            }
                        });

                        if(pregun == arrayPregunta.listapreguntas.size() - 1)
                        {
                            mensaje("Sus respuestas ya fueron registradas !Gracias¡");
                        }

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

                    adapterPreguntas = new AdapterPreguntas(list, CN_PreguntasActivity.this);
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

                    adapterPreguntas = new AdapterPreguntas(list, CN_PreguntasActivity.this);
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
            Intent intent = new Intent(CN_PreguntasActivity.this , CN_Pregunta1Activity.class);

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

                adapterPreguntas = new AdapterPreguntas(list, CN_PreguntasActivity.this);
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

                adapterPreguntas = new AdapterPreguntas(list, CN_PreguntasActivity.this);
                lista.setAdapter(adapterPreguntas);

                arrayPregunta.remover();
            }
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

                Intent intent = new Intent(CN_PreguntasActivity.this , CN_EdadActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void mensaje2(String mensaje)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ama");
        builder.setMessage(mensaje);
        builder.setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showProgress() {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle("Enviando sus respuestas ");
        dialog.show();
    }

    private void SCN_Inicio()
    {
        Intent intent = new Intent(this , SCN_InicioActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }

    private void mensaje_SCN(String mensaje)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ama");
        builder.setMessage(mensaje);
        builder.setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                SCN_Inicio();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @OnClick(R.id.btn_cnactivitypreguntas_volver)
    public void retroceso()
    {
        retroceder();
    }

    @OnClick(R.id.btn_cnactivitypreguntas_siguiente)
    public void siguiente()
    {
        proceso();
    }
}

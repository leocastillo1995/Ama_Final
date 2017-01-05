package supercrack.sigmamoviles.com.ama.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.gson.JsonObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import supercrack.sigmamoviles.com.ama.Adapter.ApdaterPregunta1;
import supercrack.sigmamoviles.com.ama.Array.Conexion.ArrayPregunta;
import supercrack.sigmamoviles.com.ama.Array.Conexion.Sin_Conexion.ArrayPreguntaOpcion;
import supercrack.sigmamoviles.com.ama.Array.Conexion.Sin_Conexion.ArrayPreguntaTitulo;
import supercrack.sigmamoviles.com.ama.Conexion.ServicioAma;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Preguntas;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.PreguntaOpcion;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.PreguntasTitulo;
import supercrack.sigmamoviles.com.ama.R;

public class CN_Pregunta1Activity extends AppCompatActivity {

    private ArrayPreguntaTitulo titulo;
    private ApdaterPregunta1 pregunta1;
    private ArrayPreguntaOpcion opcion;
    private ProgressDialog dialog;
    private ArrayPregunta arrayPregunta = new ArrayPregunta();

    @InjectView(R.id.lits_cnactivity_pregunta1)
    RecyclerView listapregunta;

    @InjectView(R.id.btn_cnactivity_pregunta1_siguiente)
    View btn_siguiente;

    @InjectView(R.id.txt_cnactivityinicio_pregunta1)
    TextView txt_titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cn_pregunta1);

        titulo = new ArrayPreguntaTitulo(this);
        opcion = new ArrayPreguntaOpcion(this);

        ButterKnife.inject(this);

        barra("Preguntas");

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ServicioAma.URL)
                                .addConverterFactory(GsonConverterFactory.create()).build();

        ServicioAma ama = retrofit.create(ServicioAma.class);

        showProgress();
        ama.getlistaprregunta(Token()).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CN_Pregunta1Activity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                listapregunta.setLayoutManager(linearLayoutManager);

                PreguntasTitulo x = titulo.Obtenertodo(3);

                txt_titulo.setText(x.getTitulo());

                pregunta1 = new ApdaterPregunta1(opcion.ObtenerTodo(x.getIdpregunta()), CN_Pregunta1Activity.this);
                listapregunta.setAdapter(pregunta1);
                dialog.dismiss();

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                mensaje_SCN("Ama a detectado que no está conectado se activara el modo cuestionario para que lo pueda utilizar. Cuando se vuelva conectar se sincronizara los datos registrados ¡Gracias por su atención!"  );
                dialog.dismiss();
            }
        });
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void showProgress() {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle("Cargando la pregunta");
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

    private void proceso()
    {
        PreguntaOpcion element = pregunta1.onSelected();

        Intent intent = new Intent(this , CN_PreguntasActivity.class);

        Preguntas x = new Preguntas(null , element.getUuid());
        arrayPregunta.Agregar(x);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }

    @OnClick(R.id.btn_cnactivity_pregunta1_siguiente)
    public void siguiente()
    {
        proceso();
    }

}

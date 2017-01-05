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

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import supercrack.sigmamoviles.com.ama.Adapter.AdapterComentarios;
import supercrack.sigmamoviles.com.ama.Array.Conexion.ArrayComentario;
import supercrack.sigmamoviles.com.ama.Array.Conexion.ArrayTema;
import supercrack.sigmamoviles.com.ama.Conexion.ServicioAma;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.ComentarioForo;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Tema;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Usuario;
import supercrack.sigmamoviles.com.ama.R;

public class CN_TemaForoDetalleActivity extends AppCompatActivity {

    private ArrayTema arrayTema = new ArrayTema();
    private ArrayComentario arrayComentario = new ArrayComentario();
    private ProgressDialog dialog;
    private String id_codigo , uuid , titulo;
    private AdapterComentarios adapterComentarios;

    @InjectView(R.id.txt_activitytemaforodetalle_usuario)
    TextView txt_nombre;

    @InjectView(R.id.txt_activitytemaforodetalle_titulo)
    TextView txt_titulo;

    @InjectView(R.id.txt_activitytemaforodetalle_descrip)
    TextView txt_descrip;

    @InjectView(R.id.btn_activitytemaforodetalle_responder)
    View btn_responer;

    @InjectView(R.id.lista_activitytemaforodetalle_listacomentarios)
    RecyclerView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cn_tema_foro_detalle);


        Retrofit retrofit = new Retrofit.Builder().baseUrl(ServicioAma.URL)
                            .addConverterFactory(GsonConverterFactory.create()).build();

        final ServicioAma ama = retrofit.create(ServicioAma.class);

        showProgress();
        ama.getlistaTema(Token()).enqueue(new Callback<ArrayList<Tema>>() {
            @Override
            public void onResponse(Call<ArrayList<Tema>> call, Response<ArrayList<Tema>> response) {

                arrayTema.listTema = response.body();

                String id = getIntent().getExtras().getString("id");

                Tema x = arrayTema.ObtenerTema(id);

                if(x!= null)
                {
                    barra(x.getTitulo());
                    txt_titulo.setText(x.getTitulo());
                    txt_descrip.setText(x.getDescripcion());
                    id_codigo = x.getUuid();
                    txt_nombre.setText("Ama");
                    uuid = x.getUuid();
                    titulo = x.getTitulo();
                }


                ama.getlistacomentario(uuid , Token()).enqueue(new Callback<ArrayList<ComentarioForo>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ComentarioForo>> call, Response<ArrayList<ComentarioForo>> response) {

                        arrayComentario.listcomentario = response.body();

                        ama.getlista(Token()).enqueue(new Callback<ArrayList<Usuario>>() {
                            @Override
                            public void onResponse(Call<ArrayList<Usuario>> call, Response<ArrayList<Usuario>> response) {

                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CN_TemaForoDetalleActivity.this);
                                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                lista.setLayoutManager(linearLayoutManager);

                                adapterComentarios = new AdapterComentarios(arrayComentario.listcomentario , response.body() , titulo);
                                lista.setAdapter(adapterComentarios);
                                dialog.dismiss();

                            }

                            @Override
                            public void onFailure(Call<ArrayList<Usuario>> call, Throwable t) {

                                mensaje_SCN("Ama a detectado que no está conectado se activara el modo cuestionario para que lo pueda utilizar. Cuando se vuelva conectar se sincronizara los datos registrados ¡Gracias por su atención!");
                                dialog.dismiss();

                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ComentarioForo>> call, Throwable t) {

                        mensaje_SCN("Ama a detectado que no está conectado se activara el modo cuestionario para que lo pueda utilizar. Cuando se vuelva conectar se sincronizara los datos registrados ¡Gracias por su atención!");
                        dialog.dismiss();
                    }
                });
            }

            @Override
            public void onFailure(Call<ArrayList<Tema>> call, Throwable t) {

                mensaje_SCN("Ama a detectado que no está conectado se activara el modo cuestionario para que lo pueda utilizar. Cuando se vuelva conectar se sincronizara los datos registrados ¡Gracias por su atención!");
                dialog.dismiss();
            }
        });

        ButterKnife.inject(this);
        barra("titulo");
    }

    private void barra(String titulo)
    {
        Toolbar toll = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toll);
        getSupportActionBar().setTitle(titulo);
    }

    private String Token()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Token" , Context.MODE_PRIVATE);
        return "bearer " +sharedPreferences.getString("token" , "");
    }

    private void showProgress() {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle("Cargando detalles del foro y comentarios");
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
        Intent intent = new Intent(this , CN_ComentarioForoActivity.class);
        intent.putExtra("id_coment" , id_codigo);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }

    @OnClick(R.id.btn_activitytemaforodetalle_responder)
    public void responder()
    {
        proceso();
    }
}

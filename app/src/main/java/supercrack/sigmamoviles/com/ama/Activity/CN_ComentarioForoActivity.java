package supercrack.sigmamoviles.com.ama.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import supercrack.sigmamoviles.com.ama.Conexion.ServicioAma;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.ComentarioForo;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.RegistroComentario;
import supercrack.sigmamoviles.com.ama.R;

public class CN_ComentarioForoActivity extends AppCompatActivity {

    private String id_codigo;
    private ProgressDialog dialog;

    @InjectView(R.id.txt_activitycomentarioforo_comentar)
    TextView txt_comentario;

    @InjectView(R.id.btn_activitycomentarioforo_comentar)
    View btn_comentar;

    String comentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cn_comentario_foro);

        id_codigo = getIntent().getExtras().getString("id_coment");

        barra("Registre comentario");
        ButterKnife.inject(this);
    }

    private void barra(String titulo)
    {
        Toolbar toll = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toll);
        getSupportActionBar().setTitle(titulo);
    }

    private void mensaje(String mensaje)
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

    private void mensaje_CN(String mensaje)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ama");
        builder.setMessage(mensaje);
        builder.setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(CN_ComentarioForoActivity.this , CN_MenuActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showProgress() {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle("Registrando su comentario");
        dialog.show();
    }

    private String Token()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Token" , Context.MODE_PRIVATE);
        return "bearer " +sharedPreferences.getString("token" , "");
    }

    private void dato()
    {
        comentario = txt_comentario.getText().toString();
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
        if(comentario.isEmpty())
        {
            mensaje("Escriba su comentario por favor");
        }
        else
        {
            RegistroComentario x = new RegistroComentario(id_codigo , comentario);

            showProgress();
            Retrofit retrofit = new Retrofit.Builder().baseUrl(ServicioAma.URL)
                                    .addConverterFactory(GsonConverterFactory.create()).build();

            ServicioAma ama = retrofit.create(ServicioAma.class);

            ama.registroComentario(Token() , x).enqueue(new Callback<ComentarioForo>() {
                @Override
                public void onResponse(Call<ComentarioForo> call, Response<ComentarioForo> response) {

                    mensaje_CN("Su comentario se registró ¡gracias!");
                    dialog.dismiss();
                }

                @Override
                public void onFailure(Call<ComentarioForo> call, Throwable t) {

                    mensaje_SCN("Ama a detectado que no está conectado se activara el modo cuestionario para que lo pueda utilizar. Cuando se vuelva conectar se sincronizara los datos registrados ¡Gracias por su atención!");
                    dialog.dismiss();
                }
            });

        }
    }

    @OnClick(R.id.btn_activitycomentarioforo_comentar)
    public void comentar()
    {
        dato();
        proceso();
    }
}

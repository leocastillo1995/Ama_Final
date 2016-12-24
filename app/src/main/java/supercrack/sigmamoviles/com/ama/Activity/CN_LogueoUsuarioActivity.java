package supercrack.sigmamoviles.com.ama.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import supercrack.sigmamoviles.com.ama.Conexion.ServicioAma;
import supercrack.sigmamoviles.com.ama.Modelo.Token;
import supercrack.sigmamoviles.com.ama.R;
import supercrack.sigmamoviles.com.ama.Utils.ElementosWebservis;

public class CN_LogueoUsuarioActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    @InjectView(R.id.txt_cnactivityinicio_usuario)
    TextView txt_usuario;

    @InjectView(R.id.txt_cnactivityinicio_contrasenia)
    TextView txt_contrasenia;

    @InjectView(R.id.btn_cnactivityinicio_ingresar)
    View btn_ingresar;

    String usuario , contrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cn__logueo_usuario);

        barra("Login");
        ButterKnife.inject(this);
    }

    private void barra(String titulo)
    {
        Toolbar toll = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toll);
        getSupportActionBar().setTitle(titulo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    private void dato()
    {
        usuario = txt_usuario.getText().toString();
        contrasenia = txt_contrasenia.getText().toString();
    }

    private void proceso()
    {
        showProgress();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ServicioAma.URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        ServicioAma ama = retrofit.create(ServicioAma.class);

        ama.Idendificador(ElementosWebservis.client_id , ElementosWebservis.client_secret ,
                usuario , contrasenia , ElementosWebservis.grant_type).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {

                if(usuario.isEmpty() || contrasenia.isEmpty())
                {
                    mensaje("Ingrese su usuario o contraseña");
                }
                else
                {
                    if(response.isSuccessful())
                    {
                        Toast.makeText(CN_LogueoUsuarioActivity.this , "ingreso" , Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        mensaje("El usuario o la contraseña son incorrectas");
                    }
                }

                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

                    mensaje_SCN("Ama a detectado que no está conectado se activara el modo cuestionario para que lo pueda utilizar. Cuando se vuelva conectar se sincronizara los datos registrados ¡Gracias por su atención!");
                    dialog.dismiss();
            }
        });
    }

    private void showProgress() {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle("Verificando su usuario");
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

    @OnClick(R.id.btn_cnactivityinicio_ingresar)
    public void loguear()
    {
        dato();
        proceso();
    }

}

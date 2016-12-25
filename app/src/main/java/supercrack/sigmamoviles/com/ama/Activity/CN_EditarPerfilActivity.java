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
import android.widget.ImageView;
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
import supercrack.sigmamoviles.com.ama.Modelo.Usuario;
import supercrack.sigmamoviles.com.ama.R;

public class CN_EditarPerfilActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    @InjectView(R.id.img_activityeditarperfil_imagenperfil)
    ImageView img_perfil;

    @InjectView(R.id.txt_activityeditarperfil_nombre)
    TextView txt_nombre;

    @InjectView(R.id.txt_activityeditarperfil_apellido)
    TextView txt_apellido;

    @InjectView(R.id.txt_activityeditarperfil_correo)
    TextView txt_correo;

    @InjectView(R.id.txt_activityeditarperfil_dni)
    TextView txt_dni;

    @InjectView(R.id.btn_activityeditarperfil_guardar)
    View btn_guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cn_editar_perfil);

        ButterKnife.inject(this);
        barra("Editar Perfil");

        showProgress();
        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(ServicioAma.URL).addConverterFactory(GsonConverterFactory.create()).build();

        ServicioAma ama = retrofit.create(ServicioAma.class);

        ama.getobtener(Token()).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                if(response.body().getGenero() == 0)
                {
                    img_perfil.setImageResource(R.drawable.masculino);
                }
                else
                {
                    img_perfil.setImageResource(R.drawable.femenino);
                }

                txt_nombre.setText(response.body().getFirst_name());
                txt_apellido.setText(response.body().getFirst_name());
                txt_correo.setText(response.body().getEmail());
                txt_dni.setText(response.body().getNro_identificacion());
                dialog.dismiss();

            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

                mensaje("Ama a detectado que no está conectado se activara el modo cuestionario para que lo pueda utilizar. Cuando se vuelva conectar se sincronizara los datos registrados ¡Gracias por su atención!");
                dialog.dismiss();
            }
        });

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
        dialog.setTitle("Cargando sus datos");
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

    private void mensaje(String mensaje)
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

    @OnClick(R.id.btn_activityeditarperfil_guardar)
    public void guardar()
    {

    }
}

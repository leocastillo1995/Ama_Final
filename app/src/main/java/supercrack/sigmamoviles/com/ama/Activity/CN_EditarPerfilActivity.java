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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    private int id , genero;
    String usuario;

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

    String nombre , apellido , correo , dni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cn_editar_perfil);

        ButterKnife.inject(this);
        barra("Editar Perfil");

        showProgress("Cargando sus datos");
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
                txt_apellido.setText(response.body().getLast_name());
                txt_correo.setText(response.body().getEmail());
                txt_dni.setText(response.body().getNro_identificacion());
                id = response.body().getId();
                usuario = response.body().getUsername();
                genero = response.body().getGenero();
                dialog.dismiss();

            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

                mensaje_SCN("Ama a detectado que no está conectado se activara el modo cuestionario para que lo pueda utilizar. Cuando se vuelva conectar se sincronizara los datos registrados ¡Gracias por su atención!");
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

    private void showProgress(String titulo) {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle(titulo);
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

    private void proceso()
    {

        if(nombre.isEmpty() || apellido.isEmpty() ||
                correo.isEmpty() || dni.isEmpty())
        {
            mensaje("Falta completar datos");
        }
        else
        {
            if(dni.length() != 8)
            {
                mensaje("El DNI que ingreso no es valido");
            }
            else
            {
                Pattern pattern = Pattern
                        .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

                Matcher mather = pattern.matcher(correo);

                if(mather.find() == false)
                {
                    mensaje("Ingrese bien su correo");
                }
                else
                {
                    Usuario usua = new Usuario();
                    usua.setId(id);
                    usua.setUsername(usuario);
                    usua.setEmail(correo);
                    usua.setFirst_name(nombre);
                    usua.setLast_name(apellido);
                    usua.setNro_identificacion(dni);
                    usua.setGenero(genero);

                    showProgress("Modificando sus datos personales");
                    Retrofit retrofit = new Retrofit.Builder().baseUrl(ServicioAma.URL)
                                            .addConverterFactory(GsonConverterFactory.create()).build();

                    ServicioAma ama = retrofit.create(ServicioAma.class);

                    ama.getactualizar(Token() , usua).enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(CN_EditarPerfilActivity.this);
                            builder.setTitle("Ama");
                            builder.setMessage("Sus datos se modificaron correctamente");
                            builder.setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    Intent intent = new Intent(CN_EditarPerfilActivity.this , CN_MenuActivity.class);

                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                    startActivity(intent);

                                }
                            });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();

                            dialog.dismiss();

                        }

                        @Override
                        public void onFailure(Call<Usuario> call, Throwable t) {

                            mensaje_SCN("Ama a detectado que no está conectado se activara el modo cuestionario para que lo pueda utilizar. Cuando se vuelva conectar se sincronizara los datos registrados ¡Gracias por su atención!");

                            dialog.dismiss();

                        }
                    });
                }
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
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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

    private void dato()
    {
        nombre = txt_nombre.getText().toString();
        apellido = txt_apellido.getText().toString();
        correo = txt_correo.getText().toString();
        dni = txt_dni.getText().toString();
    }


    @OnClick(R.id.btn_activityeditarperfil_guardar)
    public void guardar()
    {
        dato();
        proceso();
    }
}

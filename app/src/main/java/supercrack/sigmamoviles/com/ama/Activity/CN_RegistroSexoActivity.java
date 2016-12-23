package supercrack.sigmamoviles.com.ama.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
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
import supercrack.sigmamoviles.com.ama.Estadicos.EstadicoUsuario;
import supercrack.sigmamoviles.com.ama.Modelo.Token;
import supercrack.sigmamoviles.com.ama.Modelo.Usuario;
import supercrack.sigmamoviles.com.ama.Preferencia.Preferen;
import supercrack.sigmamoviles.com.ama.R;
import supercrack.sigmamoviles.com.ama.Utils.ElementosWebservis;

public class CN_RegistroSexoActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    private Preferen preferen;
    private String token;

    @InjectView(R.id.radio_cnactivityregistrosexo_femenino)
    RadioButton femenino;

    @InjectView(R.id.radio_cnactivityregistrosexo_masculino)
    RadioButton masculino;

    @InjectView(R.id.btn_cnactivityregistrosexo_registrar)
    View btn_registrar;

    int opcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cn__registro_sexo);

        preferen = new Preferen(this);
        barra("Indique se sexo");
        ButterKnife.inject(this);
    }

    private void barra(String titulo)
    {
        Toolbar toll = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toll);
        getSupportActionBar().setTitle(titulo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void dato()
    {
        if(femenino.isChecked() == true)
        {
            opcion = 1;
        }

        if(masculino.isChecked() == true)
        {
            opcion = 0;
        }
    }

    private void procesos()
    {
        final EstadicoUsuario estusuario = new EstadicoUsuario();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ServicioAma.URL)
                                .addConverterFactory(GsonConverterFactory.create()).build();

        final ServicioAma ama = retrofit.create(ServicioAma.class);

        Usuario x = new Usuario(estusuario.getUsuario() , estusuario.getContrasenia() ,
                                estusuario.getCorreo() , estusuario.getNombre() ,
                                estusuario.getApellido() , estusuario.getDni() , opcion);

        showProgress();
        ama.registrarusuario(x).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                ama.Idendificador(ElementosWebservis.client_id , ElementosWebservis.client_secret ,
                                  estusuario.getUsuario() , estusuario.getContrasenia() ,
                                  ElementosWebservis.grant_type).enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(Call<Token> call, Response<Token> response) {

                        token = response.body().getAccess_token();

                        preferen.modificartoken(CN_RegistroSexoActivity.this , token);
                        preferen.modificarUsuario(CN_RegistroSexoActivity.this , estusuario.getUsuario());

                        mensaje("¡Gracias por registrarse en ama!");

                        //Toast.makeText(CN_RegistroSexoActivity.this , "Registro" , Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailure(Call<Token> call, Throwable t) {

                        Toast.makeText(CN_RegistroSexoActivity.this , "Error2" , Toast.LENGTH_LONG).show();
                    }
                });
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

                Toast.makeText(CN_RegistroSexoActivity.this , "Error1" , Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
    }

    private void showProgress() {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle("Registrando su usuario");
        dialog.show();
    }

    private void mensaje(String mensaje)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ama");
        builder.setMessage(mensaje);
        builder.setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent intent = new Intent(CN_RegistroSexoActivity.this , CN_MenuActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                EstadicoUsuario estusuario = new EstadicoUsuario();

                estusuario.setUsuario(null);
                estusuario.setContrasenia(null);
                estusuario.setCorreo(null);
                estusuario.setNombre(null);
                estusuario.setApellido(null);
                estusuario.setDni(null);

                startActivity(intent);

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @OnClick(R.id.btn_cnactivityregistrosexo_registrar)
    public void registrar()
    {
        dato();
        procesos();
    }
}

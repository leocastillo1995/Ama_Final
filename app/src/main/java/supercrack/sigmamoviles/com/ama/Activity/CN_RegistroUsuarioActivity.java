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

import java.util.ArrayList;
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
import supercrack.sigmamoviles.com.ama.Array.ArrayUsuario;
import supercrack.sigmamoviles.com.ama.Conexion.ServicioAma;
import supercrack.sigmamoviles.com.ama.Estadicos.EstadicoUsuario;
import supercrack.sigmamoviles.com.ama.Modelo.Usuario;
import supercrack.sigmamoviles.com.ama.R;

public class CN_RegistroUsuarioActivity extends AppCompatActivity {

    private ArrayUsuario arrayUsuario = new ArrayUsuario();
    private ProgressDialog dialog;

    @InjectView(R.id.txt_cnactivityregistro_nombre)
    TextView txt_nombre;

    @InjectView(R.id.txt_cnactivityregistro_apellido)
    TextView txt_apellido;

    @InjectView(R.id.txt_cnactivityregistro_correo)
    TextView txt_correo;

    @InjectView(R.id.txt_cnactivityregistro_dni)
    TextView txt_dni;

    @InjectView(R.id.txt_cnactivityregistro_usuario)
    TextView txt_usuario;

    @InjectView(R.id.txt_cnactivityregistro_contrasenia)
    TextView txt_contrasenia;

    @InjectView(R.id.btn_cnactivityregistro_siguiente)
    View btn_siguiente;

    String nombre , apellido , correo , dni , usuario , contrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cn__registro_usuario);


        barra("Registro");
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
        nombre = txt_nombre.getText().toString();
        apellido = txt_apellido.getText().toString();
        correo = txt_correo.getText().toString();
        dni = txt_dni.getText().toString();
        usuario = txt_usuario.getText().toString();
        contrasenia = txt_contrasenia.getText().toString();
    }

    private void proceso()
    {
        showProgress();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ServicioAma.URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        ServicioAma ama = retrofit.create(ServicioAma.class);

        ama.getlista(Token()).enqueue(new Callback<ArrayList<Usuario>>() {
            @Override
            public void onResponse(Call<ArrayList<Usuario>> call, Response<ArrayList<Usuario>> response) {

                arrayUsuario.listUsuario = response.body();

                if(nombre.isEmpty() || apellido.isEmpty() ||
                   correo.isEmpty() || dni.isEmpty() ||
                   usuario.isEmpty() || contrasenia.isEmpty())
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
                        if(usuario.length() <=3 || contrasenia.length() <= 3)
                        {
                            mensaje("el usuario o la contraseña tiene que tener más de 3 palabras");
                        }
                        else
                        {
                            String user = arrayUsuario.ObtenerUsuarioUsername(usuario);

                            if(user != null)
                            {
                                mensaje("El usuario que ingreso ya existe");
                            }
                            else
                            {
                                String email = arrayUsuario.ObtenerUsuarioCorreo(correo);

                                if(email != null)
                                {
                                    mensaje("El correo que ingreso ya existe");
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
                                        EstadicoUsuario estusuario = new EstadicoUsuario();
                                        estusuario.setNombre(nombre);
                                        estusuario.setApellido(apellido);
                                        estusuario.setCorreo(correo);
                                        estusuario.setDni(dni);
                                        estusuario.setUsuario(usuario);
                                        estusuario.setContrasenia(contrasenia);

                                        Intent intent = new Intent(CN_RegistroUsuarioActivity.this , CN_RegistroSexoActivity.class);
                                        startActivity(intent);
                                    }

                                }
                            }
                        }
                    }
                }
                dialog.dismiss();

            }

            @Override
            public void onFailure(Call<ArrayList<Usuario>> call, Throwable t) {

                mensaje_SCN("Ama a detectado que no está conectado se activara el modo cuestionario para que lo pueda utilizar. Cuando se vuelva conectar se sincronizara los datos registrados ¡Gracias por su atención!");
                dialog.dismiss();
            }
        });
    }

    private String Token()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Token" , Context.MODE_PRIVATE);
        return "bearer " +sharedPreferences.getString("token" , "");
    }

    private void showProgress() {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle("Verificando Datos");
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

                dialogInterface.cancel();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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

    @OnClick(R.id.btn_cnactivityregistro_siguiente)
    public void siguiente()
    {
        dato();
        proceso();
    }
}

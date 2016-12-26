package supercrack.sigmamoviles.com.ama;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import supercrack.sigmamoviles.com.ama.Activity.CN_InicioActivity;
import supercrack.sigmamoviles.com.ama.Activity.CN_MenuActivity;
import supercrack.sigmamoviles.com.ama.Activity.SCN_InicioActivity;
import supercrack.sigmamoviles.com.ama.Conexion.ServicioAma;
import supercrack.sigmamoviles.com.ama.Modelo.Token;
import supercrack.sigmamoviles.com.ama.Preferencia.Preferen;
import supercrack.sigmamoviles.com.ama.Utils.ElementosWebservis;

public class SplashActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    private String token;

    private Preferen preferen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        preferen = new Preferen(this);

        showProgress();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ServicioAma.URL)
                            .addConverterFactory(GsonConverterFactory.create()).build();


        ServicioAma ama = retrofit.create(ServicioAma.class);

        ama.Idendificador(ElementosWebservis.client_id , ElementosWebservis.client_secret ,
                          ElementosWebservis.username , ElementosWebservis.password ,
                          ElementosWebservis.grant_type).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {

                CN_Incio(response.body().getAccess_token());
                dialog.dismiss();

            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

                mensaje("Ama a detectado que no está conectado se activara el modo cuestionario para que lo pueda utilizar. Cuando se vuelva conectar se sincronizara los datos registrados ¡Gracias por su atención!");
                dialog.dismiss();
            }
        });
    }

    private void showProgress() {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
    }

    private void CN_Incio(String tok)
    {
        if(USUARIO().isEmpty())
        {
            Intent intent = new Intent(this , CN_InicioActivity.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            token = tok;

            preferen.modificartoken(SplashActivity.this , token);

            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this , CN_MenuActivity.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        }
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

    private String USUARIO()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Usuario" , Context.MODE_PRIVATE);
        return sharedPreferences.getString("usuario" , "");
    }

}

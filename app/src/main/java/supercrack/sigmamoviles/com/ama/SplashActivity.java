package supercrack.sigmamoviles.com.ama;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import supercrack.sigmamoviles.com.ama.Activity.CN_InicioActivity;
import supercrack.sigmamoviles.com.ama.Activity.SCN_InicioActivity;
import supercrack.sigmamoviles.com.ama.Conexion.ServicioAma;
import supercrack.sigmamoviles.com.ama.Modelo.Token;
import supercrack.sigmamoviles.com.ama.Utils.ElementosWebservis;

public class SplashActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        showProgress();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ServicioAma.URL)
                            .addConverterFactory(GsonConverterFactory.create()).build();


        ServicioAma ama = retrofit.create(ServicioAma.class);

        ama.Idendificador(ElementosWebservis.client_id , ElementosWebservis.client_secret ,
                          ElementosWebservis.username , ElementosWebservis.password ,
                          ElementosWebservis.grant_type).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {

                token = response.body().getAccess_token();

                CN_Incio();
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

    private void CN_Incio()
    {
        Intent intent = new Intent(this , CN_InicioActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
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

}

package supercrack.sigmamoviles.com.ama;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
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

                Toast.makeText(SplashActivity.this , "Token : " + token , Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

                Toast.makeText(SplashActivity.this , "ERROR" , Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    private void showProgress() {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle("ertgr");
        dialog.show();
    }

    private void CN_Incio()
    {

    }

}

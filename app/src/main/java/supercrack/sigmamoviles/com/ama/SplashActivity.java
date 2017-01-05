package supercrack.sigmamoviles.com.ama;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import supercrack.sigmamoviles.com.ama.Activity.CN_InicioActivity;
import supercrack.sigmamoviles.com.ama.Activity.CN_MenuActivity;
import supercrack.sigmamoviles.com.ama.Activity.SCN_InicioActivity;
import supercrack.sigmamoviles.com.ama.Array.Conexion.Sin_Conexion.ArrayPreguntaOpcion;
import supercrack.sigmamoviles.com.ama.Array.Conexion.Sin_Conexion.ArrayPreguntaTitulo;
import supercrack.sigmamoviles.com.ama.Array.Conexion.Sin_Conexion.ArrayPreguntaopcionesSCN;
import supercrack.sigmamoviles.com.ama.Conexion.ServicioAma;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.PreguntaOpciones;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.PreguntaOpcion;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.PreguntasTitulo;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Token;
import supercrack.sigmamoviles.com.ama.Preferencia.Preferen;
import supercrack.sigmamoviles.com.ama.ServicioInterno.RegistroProcesoPreguntasOpciones;
import supercrack.sigmamoviles.com.ama.ServicioInterno.RegistroProcesoPreguntasTitulo;
import supercrack.sigmamoviles.com.ama.ServicioInterno.RegistrosProcesoPreguntas;
import supercrack.sigmamoviles.com.ama.Utils.ElementosWebservis;

public class SplashActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    private String token;
    private ArrayPreguntaTitulo titulo;
    private RegistroProcesoPreguntasTitulo sv;
    private ArrayPreguntaOpcion opcion;
    private RegistroProcesoPreguntasOpciones sv2;
    private int i =1;
    private ArrayPreguntaopcionesSCN scn;
    private RegistrosProcesoPreguntas regpreguntas;

    private Preferen preferen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        preferen = new Preferen(this);
        titulo = new ArrayPreguntaTitulo(this);
        sv = new RegistroProcesoPreguntasTitulo(this);
        opcion = new ArrayPreguntaOpcion(this);
        sv2 = new RegistroProcesoPreguntasOpciones(this);
        scn = new ArrayPreguntaopcionesSCN(this);
        regpreguntas = new RegistrosProcesoPreguntas(this);

        showProgress();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ServicioAma.URL)
                            .addConverterFactory(GsonConverterFactory.create()).build();


        ServicioAma ama = retrofit.create(ServicioAma.class);

        ama.Idendificador(ElementosWebservis.client_id , ElementosWebservis.client_secret ,
                          ElementosWebservis.username , ElementosWebservis.password ,
                          ElementosWebservis.grant_type).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {

                actualizar("bearer " +response.body().getAccess_token());
                preguntas("bearer " +response.body().getAccess_token());
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

    private void borrartablas()
    {
        for(PreguntasTitulo x : titulo.lista)
        {
            sv.Eliminar(x.getIdpregunta());
        }

        for(PreguntaOpcion x : opcion.listaPreguntaOpcions)
        {
            sv2.eliminar(x.getUuid());
        }
    }

    private void preguntas(String token)
    {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ServicioAma.URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        ServicioAma ama = retrofit.create(ServicioAma.class);

        ama.getlistaprregunta(token).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                borrartablas();

                JsonArray array = response.body().getAsJsonArray("preguntas");

                for(JsonElement x : array)
                {
                    JsonArray array1 = x.getAsJsonObject().get("pregunta__opcion").getAsJsonArray();

                    PreguntasTitulo b = new PreguntasTitulo(x.getAsJsonObject().get("uuid").getAsString(),
                                                            x.getAsJsonObject().get("texto_pregunta").getAsString() ,
                                                            i++);

                    sv.RegistrarPRegunta(b);

                    for(JsonElement a : array1)
                    {
                        Log.d("Mensaje" , x.getAsJsonObject().get("texto_pregunta").getAsString() + " " +
                                a.getAsJsonObject().get("descripcion").getAsString());

                        try{
                            PreguntaOpcion c = new PreguntaOpcion(x.getAsJsonObject().get("uuid").getAsString() , a.getAsJsonObject().get("uuid").getAsString() ,
                                    a.getAsJsonObject().get("secuencia").getAsInt() , a.getAsJsonObject().get("descripcion").getAsString() ,
                                    a.getAsJsonObject().get("imagen").getAsString());

                            sv2.registra(c);
                        }
                        catch (Exception ex)
                        {
                            PreguntaOpcion c = new PreguntaOpcion(x.getAsJsonObject().get("uuid").getAsString() , a.getAsJsonObject().get("uuid").getAsString() ,
                                    a.getAsJsonObject().get("secuencia").getAsInt() , a.getAsJsonObject().get("descripcion").getAsString() ,
                                    null);

                            sv2.registra(c);
                        }

                    }
                }

                dialog.dismiss();

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                mensaje("Ama a detectado que no está conectado se activara el modo cuestionario para que lo pueda utilizar. Cuando se vuelva conectar se sincronizara los datos registrados ¡Gracias por su atención!");
                dialog.dismiss();
            }
        });
    }

    public void actualizar(String token)
    {
        if(scn.lista.size() >=0)
        {
            for (PreguntaOpciones x : scn.lista)
            {
                Retrofit retrofit = new Retrofit.Builder().baseUrl(ServicioAma.URL)
                        .addConverterFactory(GsonConverterFactory.create()).build();

                ServicioAma ama = retrofit.create(ServicioAma.class);

                PreguntaOpciones a = new PreguntaOpciones(x.getId_opcion());

                ama.registrarpregunta(token , a).enqueue(new Callback<PreguntaOpciones>() {
                    @Override
                    public void onResponse(Call<PreguntaOpciones> call, Response<PreguntaOpciones> response) {
                        dialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<PreguntaOpciones> call, Throwable t) {
                        dialog.dismiss();
                    }
                });
            }

            for (PreguntaOpciones h : scn.lista)
            {
                regpreguntas.Eliminar(h.getId_opcion());
            }
        }
    }

    private String USUARIO()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Usuario" , Context.MODE_PRIVATE);
        return sharedPreferences.getString("usuario" , "");
    }

}

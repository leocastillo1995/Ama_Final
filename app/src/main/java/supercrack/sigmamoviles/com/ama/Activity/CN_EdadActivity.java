package supercrack.sigmamoviles.com.ama.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import supercrack.sigmamoviles.com.ama.Adapter.AdapterEdad;
import supercrack.sigmamoviles.com.ama.Array.Conexion.ArrayEdad;
import supercrack.sigmamoviles.com.ama.Array.Conexion.ArrayHabilidad;
import supercrack.sigmamoviles.com.ama.Array.Conexion.ArrayVideos;
import supercrack.sigmamoviles.com.ama.Conexion.ServicioAma;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.CN_Edad;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.CN_habilidad;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.CN_video;
import supercrack.sigmamoviles.com.ama.R;
import supercrack.sigmamoviles.com.ama.ServicioInterno.RegistroProcesoEdad;
import supercrack.sigmamoviles.com.ama.ServicioInterno.RegistroProcesoHabilidad;
import supercrack.sigmamoviles.com.ama.ServicioInterno.RegistroProcesoVideo;

public class CN_EdadActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager recyclerViewLayoutManage;
    private AdapterEdad adapterEdad;
    private ProgressDialog dialog;
    private RegistroProcesoEdad edad;
    private RegistroProcesoHabilidad habilidad;
    private RegistroProcesoVideo video;
    private ArrayVideos arrayVideos;
    private ArrayHabilidad arrayHabilidad;
    private ArrayEdad arrayEdad;

    @InjectView(R.id.lista_cnactivity_listaedades)
    RecyclerView listaeades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cn_edad);

        edad = new RegistroProcesoEdad(this);
        habilidad = new RegistroProcesoHabilidad(this);
        video = new RegistroProcesoVideo(this);

        arrayVideos = new ArrayVideos(this);
        arrayHabilidad = new ArrayHabilidad(this);
        arrayEdad = new ArrayEdad(this);

        ButterKnife.inject(this);
        barra("Edad");

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ServicioAma.URL)
                                .addConverterFactory(GsonConverterFactory.create()).build();

        ServicioAma ama = retrofit.create(ServicioAma.class);


        showProgress();
        ama.getlistaedades(Token()).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

                borrar();

                JsonArray array = response.body();

                ArrayList<CN_Edad> lista = new ArrayList<CN_Edad>();

                for(JsonElement x : array)
                {
                    lista.add(new CN_Edad(x.getAsJsonObject().get("uuid").getAsString() , x.getAsJsonObject().get("descripcion").getAsString()
                                        , x.getAsJsonObject().get("imagen").getAsString() , x.getAsJsonObject().get("secuencia").getAsInt()));

                    edad.registrar(new CN_Edad(x.getAsJsonObject().get("uuid").getAsString() , x.getAsJsonObject().get("descripcion").getAsString()
                            , x.getAsJsonObject().get("imagen").getAsString() , x.getAsJsonObject().get("secuencia").getAsInt()));

                    JsonArray array1 = x.getAsJsonObject().get("edad__habilidad").getAsJsonArray();

                    for(JsonElement a : array1)
                    {
                        habilidad.registar(new CN_habilidad(x.getAsJsonObject().get("uuid").getAsString() , a.getAsJsonObject().get("uuid").getAsString()
                                                            , a.getAsJsonObject().get("descripcion").getAsString() , a.getAsJsonObject().get("imagen").getAsString() , a.getAsJsonObject().get("secuencia").getAsInt()));

                        JsonArray array2 = a.getAsJsonObject().get("habilidad__video").getAsJsonArray();

                        for(JsonElement v : array2)
                        {
                            CN_video p = new CN_video(a.getAsJsonObject().get("uuid").getAsString() , v.getAsJsonObject().get("uuid").getAsString() , v.getAsJsonObject().get("descripcion").getAsString()
                                    , v.getAsJsonObject().get("imagen").getAsString() , v.getAsJsonObject().get("video").getAsString() ,  v.getAsJsonObject().get("secuencia").getAsInt());

                            video.registar(p);
                        }

                    }
                }

                Collections.sort(lista, new Comparator<CN_Edad>() {
                    @Override
                    public int compare(CN_Edad cn_edad, CN_Edad t1) {
                        return new Integer(cn_edad.getSecuencia()).compareTo(t1.getSecuencia());
                    }
                });

                recyclerViewLayoutManage = new GridLayoutManager(CN_EdadActivity.this , 2);
                listaeades.setLayoutManager(recyclerViewLayoutManage);

                adapterEdad = new AdapterEdad(lista , CN_EdadActivity.this);
                listaeades.setAdapter(adapterEdad);

                dialog.dismiss();

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                mensaje_SCN("Ama a detectado que no está conectado se activara el modo cuestionario para que lo pueda utilizar. Cuando se vuelva conectar se sincronizara los datos registrados ¡Gracias por su atención!");
                dialog.dismiss();
            }
        });

    }

    private void showProgress() {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle("Cargando videos");
        dialog.show();
    }

    private String Token()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Token" , Context.MODE_PRIVATE);
        return "bearer " +sharedPreferences.getString("token" , "");
    }

    private void barra(String titulo)
    {
        Toolbar toll = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toll);
        getSupportActionBar().setTitle(titulo);
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

    private void borrar()
    {
        for (CN_video x : arrayVideos.listavideo)
        {
            video.eliminar(x.getUuid());
        }

        for (CN_habilidad x : arrayHabilidad.listahabilidad)
        {
            habilidad.eliminar(x.getUuid());
        }

        for(CN_Edad x : arrayEdad.listaedad)
        {
            edad.eliminar(x.getUuid());
        }
    }
}

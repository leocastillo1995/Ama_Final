package supercrack.sigmamoviles.com.ama.Fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import supercrack.sigmamoviles.com.ama.Activity.SCN_InicioActivity;
import supercrack.sigmamoviles.com.ama.Adapter.AdapterTemas;
import supercrack.sigmamoviles.com.ama.Array.ArrayTema;
import supercrack.sigmamoviles.com.ama.Conexion.ServicioAma;
import supercrack.sigmamoviles.com.ama.Modelo.Tema;
import supercrack.sigmamoviles.com.ama.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CN_ForoTemaFragment extends Fragment {

    private ProgressDialog dialog;
    private AdapterTemas adapterTemas;
    private ArrayTema arrayTema = new ArrayTema();

    @InjectView(R.id.lista_cnfragmentforotema_list)
    RecyclerView listatema;


    public CN_ForoTemaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_cn_foro_tema, container, false);

        ButterKnife.inject(this , view);

        showProgress();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ServicioAma.URL)
                            .addConverterFactory(GsonConverterFactory.create()).build();

        final ServicioAma ama = retrofit.create(ServicioAma.class);

        ama.getlistaTema(Token()).enqueue(new Callback<ArrayList<Tema>>() {
            @Override
            public void onResponse(Call<ArrayList<Tema>> call, Response<ArrayList<Tema>> response) {

                arrayTema.listTema = response.body();

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                listatema.setLayoutManager(linearLayoutManager);

                adapterTemas = new AdapterTemas( arrayTema.listTema ,getActivity());
                listatema.setAdapter(adapterTemas);
                dialog.dismiss();

            }

            @Override
            public void onFailure(Call<ArrayList<Tema>> call, Throwable t) {

                mensaje_SCN("Ama a detectado que no está conectado se activara el modo cuestionario para que lo pueda utilizar. Cuando se vuelva conectar se sincronizara los datos registrados ¡Gracias por su atención!");
                dialog.dismiss();
            }
        });

        return view;
    }

    private String Token()
    {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Token" , Context.MODE_PRIVATE);
        return "bearer " +sharedPreferences.getString("token" , "");
    }

    private void showProgress() {
        dialog = new ProgressDialog(getContext());
        dialog.setCancelable(false);
        dialog.setTitle("Cargando temas del foro");
        dialog.show();
    }

    private void SCN_Inicio()
    {
        Intent intent = new Intent(getContext() , SCN_InicioActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }

    private void mensaje_SCN(String mensaje)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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

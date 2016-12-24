package supercrack.sigmamoviles.com.ama.Fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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
import supercrack.sigmamoviles.com.ama.Modelo.Usuario;
import supercrack.sigmamoviles.com.ama.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CN_PerfilUsuarioFragment extends Fragment {

    private ProgressDialog dialog;

    @InjectView(R.id.img_fragmentperfilusuario_imagensexo)
    ImageView img_sexo;

    @InjectView(R.id.txt_fragmentperfilusuario_nombre)
    TextView txt_nombre;

    @InjectView(R.id.txt_fragmentperfilusuario_apellido)
    TextView txt_apellido;

    @InjectView(R.id.txt_fragmentperfilusuario_correo)
    TextView txt_correo;

    @InjectView(R.id.txt_fragmentperfilusuario_dni)
    TextView txt_dni;

    @InjectView(R.id.btn_fragmentperfilusuario_editar)
    View btn_editar;


    public CN_PerfilUsuarioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_cn_perfil_usuario, container, false);

        ButterKnife.inject(this , view);

        showProgress();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ServicioAma.URL)
                                .addConverterFactory(GsonConverterFactory.create()).build();

        ServicioAma ama = retrofit.create(ServicioAma.class);

        ama.getobtener(Token()).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                if(response.body().getGenero() == 0)
                {
                    img_sexo.setImageResource(R.drawable.masculino);
                }
                else
                {
                    img_sexo.setImageResource(R.drawable.femenino);
                }

                txt_nombre.setText(response.body().getFirst_name());
                txt_apellido.setText(response.body().getLast_name());
                txt_correo.setText(response.body().getEmail());
                txt_dni.setText(response.body().getNro_identificacion());
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

                Toast.makeText(getContext() , "ERROR" , Toast.LENGTH_LONG).show();
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
        dialog.setTitle("Cargando sus datos");
        dialog.show();
    }

    @OnClick(R.id.btn_fragmentperfilusuario_editar)
    public void editar()
    {
        Toast.makeText(getContext() , "HOLA" , Toast.LENGTH_LONG).show();
    }

}

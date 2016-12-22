package supercrack.sigmamoviles.com.ama.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import supercrack.sigmamoviles.com.ama.R;

public class CN_RegistroUsuarioActivity extends AppCompatActivity {

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

    }

    @OnClick(R.id.btn_cnactivityregistro_siguiente)
    public void siguiente()
    {

    }
}

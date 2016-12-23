package supercrack.sigmamoviles.com.ama.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import supercrack.sigmamoviles.com.ama.R;

public class CN_LogueoUsuarioActivity extends AppCompatActivity {

    @InjectView(R.id.txt_cnactivityinicio_usuario)
    TextView txt_usuario;

    @InjectView(R.id.txt_cnactivityinicio_contrasenia)
    TextView txt_contrasenia;

    @InjectView(R.id.btn_cnactivityinicio_ingresar)
    View btn_ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cn__logueo_usuario);

        barra("Login");
        ButterKnife.inject(this);
    }

    private void barra(String titulo)
    {
        Toolbar toll = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toll);
        getSupportActionBar().setTitle(titulo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}

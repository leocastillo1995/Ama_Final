package supercrack.sigmamoviles.com.ama.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import supercrack.sigmamoviles.com.ama.R;

public class CN_InicioActivity extends AppCompatActivity {

    @InjectView(R.id.btn_cnactivity_registro)
    View btn_registro;

    @InjectView(R.id.btn_cnactivity_login)
    View btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cn__inicio);

        barra("Bienvenido");
        ButterKnife.inject(this);
    }

    private void barra(String titulo)
    {
        Toolbar toll = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toll);
        getSupportActionBar().setTitle(titulo);
    }

    @OnClick(R.id.btn_cnactivity_registro)
    public void registro()
    {
        Intent intent = new Intent(this , CN_RegistroUsuarioActivity.class);
        startActivity(intent);
    }
}

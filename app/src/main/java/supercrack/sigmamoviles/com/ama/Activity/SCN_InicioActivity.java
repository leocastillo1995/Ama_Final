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

public class SCN_InicioActivity extends AppCompatActivity {

    @InjectView(R.id.btn_scnactivityinicio_inicio)
    View btn_inicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scn__inicio);

        ButterKnife.inject(this);
        barra("Bienvenido");
    }

    private void barra(String titulo)
    {
        Toolbar toll = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toll);
        getSupportActionBar().setTitle(titulo);
    }

    private void proceso()
    {
        Intent intent = new Intent(this , SCN_Pregunta1Activity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }

    @OnClick(R.id.btn_scnactivityinicio_inicio)
    public void inicio()
    {
        proceso();
    }
}

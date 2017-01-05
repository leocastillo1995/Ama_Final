package supercrack.sigmamoviles.com.ama.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import supercrack.sigmamoviles.com.ama.Preferencia.Preferen;
import supercrack.sigmamoviles.com.ama.R;

public class CN_FinalizarActivity extends AppCompatActivity {

    private Preferen preferen;

    @InjectView(R.id.btn_cnactivityfinalizar_inicio)
    View btn_inicio;

    @InjectView(R.id.btn_cnactivityfinalizar_ayuda)
    View btn_ayuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cn_finalizar);

        preferen = new Preferen(this);

        ButterKnife.inject(this);
        barra("Hasta pronto ");
    }

    private void barra(String titulo)
    {
        Toolbar toll = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toll);
        getSupportActionBar().setTitle(titulo);
    }

    @OnClick(R.id.btn_cnactivityfinalizar_inicio)
    public void inicio()
    {
        Intent intent = new Intent(this , CN_MenuActivity.class);

        preferen.modificarIndidificador(this , 0);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }

    @OnClick(R.id.btn_cnactivityfinalizar_ayuda)
    public void ayuda()
    {
        Intent intent = new Intent(this , CN_MenuActivity.class);

        preferen.modificarIndidificador(this , 1);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }
}
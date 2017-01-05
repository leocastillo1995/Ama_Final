package supercrack.sigmamoviles.com.ama.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import supercrack.sigmamoviles.com.ama.Array.Conexion.ArrayVideos;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.CN_video;
import supercrack.sigmamoviles.com.ama.R;

public class CN_VideoCompletoActivity extends AppCompatActivity {

    private ArrayVideos arrayVideos;
    private ProgressDialog dialog;

    @InjectView(R.id.video_cnactivityvideocompleto_videc)
    VideoView video;

    @InjectView(R.id.btn_cnactivityvideocompleto_salir)
    View btn_salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cn_video_completo);

        showProgress();
        arrayVideos = new ArrayVideos(this);

        ButterKnife.inject(this);

        String codigo = getIntent().getExtras().getString("codigovideo");

        CN_video x = arrayVideos.Obtenertodo(codigo);

        Uri uri = Uri.parse(x.getVideo());

        video.setVideoURI(uri);
        video.start();

        MediaController media = new MediaController(this);

        media.setAnchorView(video);
        video.setMediaController(media);

        video.requestFocus();

        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                Intent intent = new Intent(CN_VideoCompletoActivity.this ,CN_MenuActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
            }
        });

        dialog.dismiss();
    }

    @OnClick(R.id.btn_cnactivityvideocompleto_salir)
    public void salir()
    {
        Intent intent = new Intent(CN_VideoCompletoActivity.this ,CN_FinalizarActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }

    private void showProgress() {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle("Verificando su usuario");
        dialog.show();
    }
}

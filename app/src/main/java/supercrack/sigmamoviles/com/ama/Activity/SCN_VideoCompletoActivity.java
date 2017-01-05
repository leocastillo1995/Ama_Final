package supercrack.sigmamoviles.com.ama.Activity;

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
import supercrack.sigmamoviles.com.ama.Array.Conexion.Sin_Conexion.ArrayVideoSCN;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.SCN_Video;
import supercrack.sigmamoviles.com.ama.R;

public class SCN_VideoCompletoActivity extends AppCompatActivity {

    private ArrayVideoSCN videos;
    private MediaController controller;

    @InjectView(R.id.video_scnactvitivivideocompelto_video)
    VideoView video;

    @InjectView(R.id.btn_scnactvitivivideocompelto_salir)
    View btn_salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scn_video_completo);

        videos = new ArrayVideoSCN(this);

        ButterKnife.inject(this);

        String cod = getIntent().getExtras().getString("codvid");

        SCN_Video x = videos.ObtenerVideo(cod);

        String ruta = "android.resource://" + getPackageName() + "/" + x.getRuta();

        Uri uri = Uri.parse(ruta);

        video.setVideoURI(uri);
        video.start();

        controller = new MediaController(this);

        controller.setAnchorView(controller);

        video.setMediaController(controller);

        video.requestFocus();

        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                Intent intent = new Intent(SCN_VideoCompletoActivity.this , SCN_HastaProntoActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
            }
        });
    }

    private void proceso()
    {
        Intent intent = new Intent(SCN_VideoCompletoActivity.this , SCN_HastaProntoActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
    }

    @OnClick(R.id.btn_scnactvitivivideocompelto_salir)
    public void salir()
    {
        proceso();
    }
}

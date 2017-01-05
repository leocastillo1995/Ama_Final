package supercrack.sigmamoviles.com.ama.Array.Conexion;

import android.content.Context;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.CN_video;
import supercrack.sigmamoviles.com.ama.ServicioInterno.RegistroProcesoVideo;

/**
 * Created by eglp on 02/01/2017.
 */

public class ArrayVideos {

    public ArrayList<CN_video> listavideo;
    private RegistroProcesoVideo video;

    public ArrayVideos(Context context)
    {
        video = new RegistroProcesoVideo(context);
        listavideo = video.lista();
    }

    public ArrayList<CN_video> listatodo(String codigo)
    {
        ArrayList<CN_video> lista = new ArrayList<CN_video>();

        for (CN_video x: listavideo)
        {
            if(x.getCodigoproceso().equals(codigo))
            {
                CN_video a = new CN_video(x.getCodigoproceso() , x.getUuid() , x.getDescripcion()
                        , x.getImagen() , x.getVideo() , x.getSecuencia());
                lista.add(a);
            }
        }

        return lista;
    }

    public CN_video Obtenertodo(String codigo)
    {
        for (CN_video x: listavideo)
        {
            if(x.getUuid().equals(codigo))
            {
                return x;
            }
        }

        return null;
    }

}

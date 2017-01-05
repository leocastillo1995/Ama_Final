package supercrack.sigmamoviles.com.ama.Array.Conexion.Sin_Conexion;

import android.content.Context;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.SCN_Video;
import supercrack.sigmamoviles.com.ama.ServicioInterno.RegistroProsesoVideoSCN;

/**
 * Created by eglp on 03/01/2017.
 */

public class ArrayVideoSCN {

    public ArrayList<SCN_Video> listavideo;
    private RegistroProsesoVideoSCN sv;

    public ArrayVideoSCN(Context context)
    {
        sv = new RegistroProsesoVideoSCN(context);
        listavideo = sv.lista();
    }


    public SCN_Video ObtenerVideo(String codigo)
    {
        for(SCN_Video x : listavideo)
        {
            if(x.getVideoid().equals(codigo))
            {
                return x;
            }
        }

        return null;
    }

}

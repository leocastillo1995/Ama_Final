package supercrack.sigmamoviles.com.ama.Array.Conexion;

import android.content.Context;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Modelo.Conexion.CN_Edad;
import supercrack.sigmamoviles.com.ama.ServicioInterno.RegistroProcesoEdad;

/**
 * Created by eglp on 02/01/2017.
 */

public class ArrayEdad {

    public ArrayList<CN_Edad> listaedad;
    private RegistroProcesoEdad edad;

    public ArrayEdad(Context context)
    {
        edad = new RegistroProcesoEdad(context);
        listaedad = edad.lista();
    }

    public CN_Edad Obtenertodo(int secuencia)
    {
        for (CN_Edad x: listaedad)
        {
            if(x.getSecuencia() == secuencia)
            {
                return x;
            }
        }

        return null;
    }

}

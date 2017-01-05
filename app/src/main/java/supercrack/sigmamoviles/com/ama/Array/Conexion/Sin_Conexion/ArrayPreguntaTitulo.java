package supercrack.sigmamoviles.com.ama.Array.Conexion.Sin_Conexion;

import android.content.Context;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.PreguntasTitulo;
import supercrack.sigmamoviles.com.ama.ServicioInterno.RegistroProcesoPreguntasTitulo;

/**
 * Created by eglp on 31/12/2016.
 */

public class ArrayPreguntaTitulo {

    public ArrayList<PreguntasTitulo> lista;
    private RegistroProcesoPreguntasTitulo sv;

    public ArrayPreguntaTitulo(Context context)
    {
        sv = new RegistroProcesoPreguntasTitulo(context);
        lista = sv.lista();
    }

    public PreguntasTitulo Obtenertodo(int secuencia)
    {
        for (PreguntasTitulo x : lista)
        {
            if(x.getSecuencia() == secuencia)
            {
                return x;
            }
        }

        return null;
    }

}

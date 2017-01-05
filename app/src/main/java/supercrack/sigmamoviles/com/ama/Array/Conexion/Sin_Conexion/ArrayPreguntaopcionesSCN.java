package supercrack.sigmamoviles.com.ama.Array.Conexion.Sin_Conexion;

import android.content.Context;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Modelo.Conexion.PreguntaOpciones;
import supercrack.sigmamoviles.com.ama.ServicioInterno.RegistrosProcesoPreguntas;

/**
 * Created by eglp on 03/01/2017.
 */

public class ArrayPreguntaopcionesSCN {

    public ArrayList<PreguntaOpciones> lista;
    private RegistrosProcesoPreguntas sv;

    public ArrayPreguntaopcionesSCN(Context context)
    {
        sv = new RegistrosProcesoPreguntas(context);
        lista = sv.lista();
    }
}

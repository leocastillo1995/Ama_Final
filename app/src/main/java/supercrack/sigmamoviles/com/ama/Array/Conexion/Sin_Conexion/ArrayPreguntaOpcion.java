package supercrack.sigmamoviles.com.ama.Array.Conexion.Sin_Conexion;

import android.content.Context;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.PreguntaOpcion;
import supercrack.sigmamoviles.com.ama.ServicioInterno.RegistroProcesoPreguntasOpciones;

/**
 * Created by eglp on 30/12/2016.
 */

public class ArrayPreguntaOpcion {

    public ArrayList<PreguntaOpcion> listaPreguntaOpcions;
    private RegistroProcesoPreguntasOpciones sv;

    public ArrayPreguntaOpcion(Context context)
    {
        sv = new RegistroProcesoPreguntasOpciones(context);
        listaPreguntaOpcions = sv.lista();

    }

    public ArrayList<PreguntaOpcion> ObtenerTodo(String codigo)
    {

        ArrayList<PreguntaOpcion> lista = new ArrayList<PreguntaOpcion>();

        for(PreguntaOpcion x : listaPreguntaOpcions)
        {
            if(x.getIdpregunta().equals(codigo))
            {
                PreguntaOpcion a = new PreguntaOpcion(x.getIdpregunta() , x.getUuid() , x.getSecuencia()
                                                    , x.getDescripcion() , x.getImagen());
                lista.add(a);
            }
        }

        return lista;
    }
}

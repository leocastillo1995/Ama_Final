package supercrack.sigmamoviles.com.ama.Array.Conexion;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Preguntas;

/**
 * Created by eglp on 30/12/2016.
 */

public class ArrayPregunta {

    public static ArrayList<Preguntas> listapreguntas = new ArrayList<Preguntas>();

    public ArrayPregunta()
    {

    }

    public void Agregar(Preguntas x)
    {
        listapreguntas.add(x);
    }

    public void limipar()
    {
        listapreguntas.clear();
    }

    public void remover()
    {
        Preguntas x = listapreguntas.get(listapreguntas.size() - 1);
        listapreguntas.remove(x);
    }
}

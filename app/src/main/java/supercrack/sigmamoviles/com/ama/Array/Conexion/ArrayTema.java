package supercrack.sigmamoviles.com.ama.Array.Conexion;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Tema;

/**
 * Created by eglp on 26/12/2016.
 */

public class ArrayTema {

    public ArrayList<Tema> listTema = new ArrayList<Tema>();

    public ArrayTema()
    {

    }

    public Tema ObtenerTema(String id)
    {
        for (Tema x: listTema)
        {
            if(x.getUuid().equals(id))
            {
                return x;
            }
        }

        return null;
    }
}

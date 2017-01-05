package supercrack.sigmamoviles.com.ama.Array.Conexion;

import android.content.Context;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Modelo.Conexion.CN_habilidad;
import supercrack.sigmamoviles.com.ama.ServicioInterno.RegistroProcesoHabilidad;

/**
 * Created by eglp on 02/01/2017.
 */

public class ArrayHabilidad {

    public ArrayList<CN_habilidad> listahabilidad;
    private RegistroProcesoHabilidad habilidad;

    public ArrayHabilidad(Context context)
    {
        habilidad = new RegistroProcesoHabilidad(context);
        listahabilidad = habilidad.lista();
    }

    public ArrayList<CN_habilidad> listatodo(String codigo)
    {
        ArrayList<CN_habilidad> lista = new ArrayList<CN_habilidad>();

        for (CN_habilidad x : listahabilidad)
        {
            if(x.getCodigoedad().equals(codigo))
            {
                CN_habilidad a = new CN_habilidad(x.getCodigoedad() , x.getUuid()
                        , x.getDescripcion() , x.getImagen() , x.getSecuencia());
                lista.add(a);
            }
        }

        return lista;
    }
}

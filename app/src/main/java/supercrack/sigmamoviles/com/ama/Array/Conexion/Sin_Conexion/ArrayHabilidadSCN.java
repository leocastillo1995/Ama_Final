package supercrack.sigmamoviles.com.ama.Array.Conexion.Sin_Conexion;

import android.content.Context;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.SCN_Habilidad;
import supercrack.sigmamoviles.com.ama.ServicioInterno.RegistroProgresoHabilidadSCN;

/**
 * Created by eglp on 03/01/2017.
 */

public class ArrayHabilidadSCN {

    public ArrayList<SCN_Habilidad> listahabilidad;
    private RegistroProgresoHabilidadSCN sv;

    public ArrayHabilidadSCN(Context context)
    {
        sv = new RegistroProgresoHabilidadSCN(context);
        listahabilidad = sv.lista();
    }
}

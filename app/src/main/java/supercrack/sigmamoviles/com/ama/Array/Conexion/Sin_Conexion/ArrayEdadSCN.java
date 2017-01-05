package supercrack.sigmamoviles.com.ama.Array.Conexion.Sin_Conexion;

import android.content.Context;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.SCN_Edad;
import supercrack.sigmamoviles.com.ama.ServicioInterno.RegistroProcesoEdadSCN;

/**
 * Created by eglp on 03/01/2017.
 */

public class ArrayEdadSCN {

    private RegistroProcesoEdadSCN edades;
    public ArrayList<SCN_Edad> listaedad;

    public ArrayEdadSCN(Context context)
    {
        edades = new RegistroProcesoEdadSCN(context);
        listaedad = edades.lista();
    }

}

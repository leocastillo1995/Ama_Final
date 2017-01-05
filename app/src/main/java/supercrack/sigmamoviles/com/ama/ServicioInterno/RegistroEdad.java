package supercrack.sigmamoviles.com.ama.ServicioInterno;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Modelo.Conexion.CN_Edad;

/**
 * Created by eglp on 02/01/2017.
 */

public interface RegistroEdad {

    public void registrar(CN_Edad x);
    public ArrayList<CN_Edad> lista();
    public void eliminar(String codigo);


}

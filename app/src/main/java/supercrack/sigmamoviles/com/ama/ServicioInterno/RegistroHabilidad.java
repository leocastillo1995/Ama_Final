package supercrack.sigmamoviles.com.ama.ServicioInterno;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Modelo.Conexion.CN_habilidad;

/**
 * Created by eglp on 02/01/2017.
 */

public interface RegistroHabilidad {

    public void registar(CN_habilidad x);
    public void eliminar(String codigo);
    public ArrayList<CN_habilidad> lista();
}

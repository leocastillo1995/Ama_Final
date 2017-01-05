package supercrack.sigmamoviles.com.ama.ServicioInterno;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.CN_video;

/**
 * Created by eglp on 02/01/2017.
 */

public interface RegistroVideo {

    public void registar(CN_video x);
    public void eliminar(String codigo);
    public ArrayList<CN_video> lista();

}

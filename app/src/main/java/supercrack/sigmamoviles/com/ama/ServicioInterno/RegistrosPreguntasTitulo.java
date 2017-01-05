package supercrack.sigmamoviles.com.ama.ServicioInterno;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.PreguntasTitulo;

/**
 * Created by eglp on 31/12/2016.
 */

public interface RegistrosPreguntasTitulo {

    public void RegistrarPRegunta(PreguntasTitulo x);
    public void Eliminar(String id);
    public ArrayList<PreguntasTitulo> lista();

}

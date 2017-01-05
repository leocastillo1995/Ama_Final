package supercrack.sigmamoviles.com.ama.ServicioInterno;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.PreguntaOpcion;

/**
 * Created by eglp on 31/12/2016.
 */

public interface RegistroPreguntasOpciones {

    public void registra(PreguntaOpcion x);
    public void eliminar(String id);
    public ArrayList<PreguntaOpcion> lista();

}

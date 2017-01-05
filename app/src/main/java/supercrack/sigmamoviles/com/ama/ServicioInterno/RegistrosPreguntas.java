package supercrack.sigmamoviles.com.ama.ServicioInterno;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Modelo.Conexion.PreguntaOpciones;

/**
 * Created by eglp on 03/01/2017.
 */

public interface RegistrosPreguntas {

    public void Registrar(PreguntaOpciones x);

    public void Eliminar(String codigo);

    public ArrayList<PreguntaOpciones> lista();
}

package supercrack.sigmamoviles.com.ama.Modelo.Conexion;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eglp on 01/01/2017.
 */

public class PreguntaOpciones {

    @SerializedName("id_opcion")
    private String id_opcion;

    public PreguntaOpciones(String id_opcion) {
        this.id_opcion = id_opcion;
    }

    public String getId_opcion() {
        return id_opcion;
    }

    public void setId_opcion(String id_opcion) {
        this.id_opcion = id_opcion;
    }
}

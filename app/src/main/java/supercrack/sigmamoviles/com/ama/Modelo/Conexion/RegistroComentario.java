package supercrack.sigmamoviles.com.ama.Modelo.Conexion;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eglp on 26/12/2016.
 */

public class RegistroComentario {

    @SerializedName("id_tema")
    private String id_tema;

    @SerializedName("texto_comentario")
    private String texto_comentario;

    public RegistroComentario(String id_tema, String texto_comentario) {
        this.id_tema = id_tema;
        this.texto_comentario = texto_comentario;
    }

    public String getId_tema() {
        return id_tema;
    }

    public void setId_tema(String id_tema) {
        this.id_tema = id_tema;
    }

    public String getTexto_comentario() {
        return texto_comentario;
    }

    public void setTexto_comentario(String texto_comentario) {
        this.texto_comentario = texto_comentario;
    }
}

package supercrack.sigmamoviles.com.ama.Modelo.Conexion;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eglp on 26/12/2016.
 */

public class Tema {

    @SerializedName("uuid")
    private String uuid;

    @SerializedName("titulo")
    private String titulo;

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("secuencia")
    private int secuencia;

    public Tema()
    {

    }

    public Tema(String uuid, String titulo, String descripcion, int secuencia) {
        this.uuid = uuid;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.secuencia = secuencia;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }
}

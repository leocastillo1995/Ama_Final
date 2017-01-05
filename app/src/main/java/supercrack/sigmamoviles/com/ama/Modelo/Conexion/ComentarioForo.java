package supercrack.sigmamoviles.com.ama.Modelo.Conexion;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eglp on 26/12/2016.
 */

public class ComentarioForo {

    @SerializedName("uuid")
    private String uuid;

    @SerializedName("username")
    private String username;

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("fecha_registro")
    private String fecha_registro;

    public ComentarioForo(String uuid, String username, String descripcion, String fecha_registro) {
        this.uuid = uuid;
        this.username = username;
        this.descripcion = descripcion;
        this.fecha_registro = fecha_registro;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
}

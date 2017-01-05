package supercrack.sigmamoviles.com.ama.Modelo.Conexion;

/**
 * Created by eglp on 02/01/2017.
 */

public class CN_Edad {

    private String uuid;
    private String descripcion;
    private String imagen;
    private int secuencia;

    public CN_Edad(String uuid, String descripcion, String imagen, int secuencia) {
        this.uuid = uuid;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.secuencia = secuencia;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }
}

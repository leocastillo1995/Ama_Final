package supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion;

/**
 * Created by eglp on 30/12/2016.
 */

public class PreguntaOpcion {

    private String idpregunta;
    private String uuid;
    private int secuencia;
    private String descripcion;
    private String imagen;

    public PreguntaOpcion(String idpregunta, String uuid, int secuencia, String descripcion, String imagen) {
        this.idpregunta = idpregunta;
        this.uuid = uuid;
        this.secuencia = secuencia;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public String getIdpregunta() {
        return idpregunta;
    }

    public void setIdpregunta(String idpregunta) {
        this.idpregunta = idpregunta;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
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
}

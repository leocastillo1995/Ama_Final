package supercrack.sigmamoviles.com.ama.Modelo.Conexion;

/**
 * Created by eglp on 02/01/2017.
 */

public class CN_habilidad {

    private String codigoedad;
    private String uuid;
    private String  descripcion;
    private String  imagen;
    private int secuencia;

    public CN_habilidad(String codigoedad, String uuid, String descripcion, String imagen, int secuencia) {
        this.codigoedad = codigoedad;
        this.uuid = uuid;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.secuencia = secuencia;
    }

    public String getCodigoedad() {
        return codigoedad;
    }

    public void setCodigoedad(String codigoedad) {
        this.codigoedad = codigoedad;
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

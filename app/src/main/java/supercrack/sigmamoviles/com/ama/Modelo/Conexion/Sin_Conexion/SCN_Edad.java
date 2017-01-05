package supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion;

/**
 * Created by eglp on 03/01/2017.
 */

public class SCN_Edad {

    private String edadid;
    private String nombre;
    private String url;
    private int secuencia;
    private String activo;

    public SCN_Edad(String edadid, String nombre, String url, int secuencia, String activo) {
        this.edadid = edadid;
        this.nombre = nombre;
        this.url = url;
        this.secuencia = secuencia;
        this.activo = activo;
    }

    public String getEdadid() {
        return edadid;
    }

    public void setEdadid(String edadid) {
        this.edadid = edadid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }
}

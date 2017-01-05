package supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion;

/**
 * Created by eglp on 02/01/2017.
 */

public class CN_video {

    private String codigoproceso;
    private String uuid;
    private String descripcion;
    private String imagen;
    private String video;
    private int secuencia;

    public CN_video(String codigoproceso, String uuid, String descripcion, String imagen, String video, int secuencia) {
        this.codigoproceso = codigoproceso;
        this.uuid = uuid;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.video = video;
        this.secuencia = secuencia;
    }

    public String getCodigoproceso() {
        return codigoproceso;
    }

    public void setCodigoproceso(String codigoproceso) {
        this.codigoproceso = codigoproceso;
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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }
}

package supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion;

/**
 * Created by eglp on 03/01/2017.
 */

public class SCN_Video {

    private String videoid;
    private String edadid;
    private String habilidaid;
    private String nombre;
    private String imagen;
    private String ruta;
    private int secuencia;
    private int activo;

    public SCN_Video(String videoid, String edadid, String habilidaid, String nombre, String imagen, String ruta, int secuencia, int activo) {
        this.videoid = videoid;
        this.edadid = edadid;
        this.habilidaid = habilidaid;
        this.nombre = nombre;
        this.imagen = imagen;
        this.ruta = ruta;
        this.secuencia = secuencia;
        this.activo = activo;
    }

    public String getVideoid() {
        return videoid;
    }

    public void setVideoid(String videoid) {
        this.videoid = videoid;
    }

    public String getEdadid() {
        return edadid;
    }

    public void setEdadid(String edadid) {
        this.edadid = edadid;
    }

    public String getHabilidaid() {
        return habilidaid;
    }

    public void setHabilidaid(String habilidaid) {
        this.habilidaid = habilidaid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
}

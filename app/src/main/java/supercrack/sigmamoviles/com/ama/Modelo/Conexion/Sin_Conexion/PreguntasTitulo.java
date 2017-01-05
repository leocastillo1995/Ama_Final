package supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion;

/**
 * Created by eglp on 31/12/2016.
 */

public class PreguntasTitulo {

    private String idpregunta;
    private String titulo;
    private int secuencia;

    public PreguntasTitulo(String idpregunta, String titulo, int secuencia) {
        this.idpregunta = idpregunta;
        this.titulo = titulo;
        this.secuencia = secuencia;
    }

    public String getIdpregunta() {
        return idpregunta;
    }

    public void setIdpregunta(String idpregunta) {
        this.idpregunta = idpregunta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }
}

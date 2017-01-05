package supercrack.sigmamoviles.com.ama.Modelo.Conexion;

/**
 * Created by eglp on 30/12/2016.
 */

public class Preguntas {

    private String codigo;
    private String uuid;

    public Preguntas(String codigo, String uuid) {
        this.codigo = codigo;
        this.uuid = uuid;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}

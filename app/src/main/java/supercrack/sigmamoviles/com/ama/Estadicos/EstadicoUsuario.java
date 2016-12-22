package supercrack.sigmamoviles.com.ama.Estadicos;

/**
 * Created by eglp on 22/12/2016.
 */

public class EstadicoUsuario {

    private static String nombre;
    private static String apellido;
    private static String correo;
    private static String dni;
    private static String usuario;
    private static String contrasenia;

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        EstadicoUsuario.nombre = nombre;
    }

    public static String getApellido() {
        return apellido;
    }

    public static void setApellido(String apellido) {
        EstadicoUsuario.apellido = apellido;
    }

    public static String getCorreo() {
        return correo;
    }

    public static void setCorreo(String correo) {
        EstadicoUsuario.correo = correo;
    }

    public static String getDni() {
        return dni;
    }

    public static void setDni(String dni) {
        EstadicoUsuario.dni = dni;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static void setUsuario(String usuario) {
        EstadicoUsuario.usuario = usuario;
    }

    public static String getContrasenia() {
        return contrasenia;
    }

    public static void setContrasenia(String contrasenia) {
        EstadicoUsuario.contrasenia = contrasenia;
    }
}

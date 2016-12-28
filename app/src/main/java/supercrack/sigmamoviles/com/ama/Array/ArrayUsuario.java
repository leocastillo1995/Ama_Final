package supercrack.sigmamoviles.com.ama.Array;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Modelo.Usuario;

/**
 * Created by eglp on 22/12/2016.
 */

public class ArrayUsuario {

    public ArrayList<Usuario> listUsuario = new ArrayList<Usuario>();

    public ArrayUsuario()
    {

    }

    public Usuario ObtenerUsuario(String username)
    {
        for (Usuario x: listUsuario)
        {
            if(x.getUsername().equalsIgnoreCase(username))
            {
                return x;
            }
        }

        return null;
    }

    public String ObtenerUsuarioCorreo(String correo)
    {
        for (Usuario x: listUsuario)
        {
            if(x.getEmail().equalsIgnoreCase(correo))
            {
                return x.getEmail();
            }
        }

        return null;
    }

    public String ObtenerUsuarioUsername(String username)
    {
        for (Usuario x: listUsuario)
        {
            if(x.getUsername().equalsIgnoreCase(username))
            {
                return x.getUsername();
            }
        }

        return null;
    }
}

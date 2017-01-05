package supercrack.sigmamoviles.com.ama.Preferencia;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by eglp on 22/12/2016.
 */

public class Preferen {

    public Preferen(Context context)
    {
        CrearToken(context);
        CrearUsuario(context);
        CrearIndidificador(context);
    }

    private void CrearToken(Context context)
    {

        SharedPreferences sharedPreferences = context.getSharedPreferences("Token" , Context.MODE_PRIVATE);
        sharedPreferences.getString("token" , "");
    }

    private void CrearUsuario(Context context)
    {

        SharedPreferences sharedPreferences = context.getSharedPreferences("Usuario" , Context.MODE_PRIVATE);
        sharedPreferences.getString("usuario" , "");
    }

    private void CrearIndidificador(Context context)
    {

        SharedPreferences sharedPreferences = context.getSharedPreferences("Numero" , Context.MODE_PRIVATE);
        sharedPreferences.getInt("numero" , 0);
    }

    public void modificartoken(Context context , String token)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Token" , Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("token" , token);

        editor.commit();
    }

    public void modificarUsuario(Context context , String token)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Usuario" , Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("usuario" , token);

        editor.commit();
    }

    public void modificarIndidificador(Context context , int numero)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Numero" , Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("numero" , numero);

        editor.commit();
    }
}

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
    }

    private void CrearToken(Context context)
    {

        SharedPreferences sharedPreferences = context.getSharedPreferences("Token" , Context.MODE_PRIVATE);
        sharedPreferences.getString("token" , "");
    }

    public void modificartoken(Context context , String token)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Token" , Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("token" , token);

        editor.commit();
    }
}

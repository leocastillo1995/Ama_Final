package supercrack.sigmamoviles.com.ama.ServicioInterno;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Conexion.SqlLite;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.SCN_Habilidad;

/**
 * Created by eglp on 03/01/2017.
 */

public class RegistroProgresoHabilidadSCN implements RegistroHabilidadSCN{

    private SqlLite conexion;

    public RegistroProgresoHabilidadSCN(Context context)
    {
        conexion = new SqlLite(context);
    }

    @Override
    public ArrayList<SCN_Habilidad> lista() {
        SQLiteDatabase sql = conexion.getReadableDatabase();
        Cursor cursor = sql.rawQuery("select * from tb_habilidad" , null);

        ArrayList<SCN_Habilidad> lista = new ArrayList<SCN_Habilidad>();

        if(cursor.moveToFirst())
        {
            do {

                SCN_Habilidad x = new SCN_Habilidad(cursor.getString(0) , cursor.getString(1) ,
                        cursor.getString(2) , cursor.getInt(3) ,
                        cursor.getString(4));

                lista.add(x);

            }while (cursor.moveToNext());
        }

        return lista;

    }
}

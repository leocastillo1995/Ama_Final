package supercrack.sigmamoviles.com.ama.ServicioInterno;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Conexion.SqlLite;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.SCN_Edad;

/**
 * Created by eglp on 03/01/2017.
 */

public class RegistroProcesoEdadSCN implements RegistroEdadSCN{

    private SqlLite conexion;

    public RegistroProcesoEdadSCN(Context context)
    {
        conexion = new SqlLite(context);
    }


    @Override
    public ArrayList<SCN_Edad> lista() {

        SQLiteDatabase sql = conexion.getReadableDatabase();
        Cursor cursor = sql.rawQuery("select * from tb_edad" , null);

        ArrayList<SCN_Edad> lista = new ArrayList<SCN_Edad>();

        if(cursor.moveToFirst())
        {
            do {

                SCN_Edad x = new SCN_Edad(cursor.getString(0) , cursor.getString(1) ,
                        cursor.getString(2) , cursor.getInt(3) ,
                        cursor.getString(4));

                lista.add(x);

            }while (cursor.moveToNext());
        }

        return lista;
    }
}

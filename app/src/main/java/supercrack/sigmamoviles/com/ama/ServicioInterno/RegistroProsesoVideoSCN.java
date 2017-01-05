package supercrack.sigmamoviles.com.ama.ServicioInterno;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Conexion.SqlLite;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.SCN_Video;

/**
 * Created by eglp on 03/01/2017.
 */

public class RegistroProsesoVideoSCN implements RegistroVideoSCN{

    private SqlLite conexion;

    public RegistroProsesoVideoSCN(Context context)
    {
        conexion = new SqlLite(context);
    }

    @Override
    public ArrayList<SCN_Video> lista() {

        SQLiteDatabase sql = conexion.getReadableDatabase();
        Cursor cursor = sql.rawQuery("select * from tb_video" , null);

        ArrayList<SCN_Video> lista = new ArrayList<SCN_Video>();

        if(cursor.moveToFirst())
        {
            do {

                SCN_Video x = new SCN_Video(cursor.getString(0) , cursor.getString(1) , cursor.getString(2) , cursor.getString(3) , cursor.getString(4) ,
                        cursor.getString(5) , cursor.getInt(6) , cursor.getInt(7));
                lista.add(x);

            }while (cursor.moveToNext());
        }

        return lista;


    }
}

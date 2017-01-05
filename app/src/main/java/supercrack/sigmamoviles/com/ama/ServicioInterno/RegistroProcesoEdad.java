package supercrack.sigmamoviles.com.ama.ServicioInterno;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Conexion.SqlLite;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.CN_Edad;

/**
 * Created by eglp on 02/01/2017.
 */

public class RegistroProcesoEdad implements RegistroEdad{

    private SqlLite conexion;

    public RegistroProcesoEdad(Context context)
    {
        conexion = new SqlLite(context);
    }

    @Override
    public void registrar(CN_Edad x) {

        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("uuid" , x.getUuid());
        cv.put("descripcion" , x.getDescripcion());
        cv.put("imagen" , x.getImagen());
        cv.put("secuencia" , x.getSecuencia());

        db.insert("tb_cn_edad" , null , cv);

    }

    @Override
    public ArrayList<CN_Edad> lista() {

        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_cn_edad" , null);

        ArrayList<CN_Edad> list = new ArrayList<CN_Edad>();

        if(cursor.moveToFirst())
        {
            do {

                CN_Edad x = new CN_Edad(cursor.getString(0) , cursor.getString(1) , cursor.getString(2)
                                        , cursor.getInt(3));

                list.add(x);

            }while (cursor.moveToNext());
        }

        return list;
    }

    @Override
    public void eliminar(String codigo) {

        SQLiteDatabase db = conexion.getWritableDatabase();

        db.delete("tb_cn_edad" , "uuid = '" +codigo +"'" , null);

    }
}

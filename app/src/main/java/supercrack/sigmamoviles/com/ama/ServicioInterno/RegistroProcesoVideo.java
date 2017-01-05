package supercrack.sigmamoviles.com.ama.ServicioInterno;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Conexion.SqlLite;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.CN_video;

/**
 * Created by eglp on 02/01/2017.
 */

public class RegistroProcesoVideo implements RegistroVideo{

    private SqlLite conexion;

    public RegistroProcesoVideo(Context context)
    {
        conexion = new SqlLite(context);
    }

    @Override
    public void registar(CN_video x) {

        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("codigohabilidad" ,x.getCodigoproceso());
        cv.put("uuid" ,x.getUuid());
        cv.put("descripcion" ,x.getDescripcion());
        cv.put("imagen" ,x.getImagen());
        cv.put("video" ,x.getVideo());
        cv.put("secuencia" ,x.getSecuencia());

        db.insert("tb_cn_video" , null , cv);
    }

    @Override
    public void eliminar(String codigo) {

        SQLiteDatabase db = conexion.getWritableDatabase();

        db.delete("tb_cn_video" , "uuid = '" +codigo +"'" , null);

    }

    @Override
    public ArrayList<CN_video> lista() {

        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_cn_video" , null);

        ArrayList<CN_video> list = new ArrayList<CN_video>();

        if(cursor.moveToFirst())
        {
            do {

                CN_video x = new CN_video(cursor.getString(0) , cursor.getString(1) , cursor.getString(2) , cursor.getString(3)
                                        , cursor.getString(4) , cursor.getInt(5));

                list.add(x);

            }while (cursor.moveToNext());
        }

        return list;

    }
}

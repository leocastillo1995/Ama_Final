package supercrack.sigmamoviles.com.ama.ServicioInterno;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Conexion.SqlLite;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.CN_habilidad;

/**
 * Created by eglp on 02/01/2017.
 */

public class RegistroProcesoHabilidad implements RegistroHabilidad{

    private SqlLite conexion;

    public RegistroProcesoHabilidad(Context context)
    {
        conexion = new SqlLite(context);

    }

    @Override
    public void registar(CN_habilidad x) {

        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("codigoedad" , x.getCodigoedad());
        cv.put("uuid" , x.getUuid());
        cv.put("descripcion" , x.getDescripcion());
        cv.put("imagen" , x.getImagen());
        cv.put("secuencia" , x.getSecuencia());

        db.insert("tb_cn_habilidades" , null , cv);

    }

    @Override
    public void eliminar(String codigo) {

        SQLiteDatabase db = conexion.getWritableDatabase();

        db.delete("tb_cn_habilidades" , "uuid = '" +codigo +"'" , null);

    }

    @Override
    public ArrayList<CN_habilidad> lista() {

        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_cn_habilidades" , null);

        ArrayList<CN_habilidad> list = new ArrayList<CN_habilidad>();

        if(cursor.moveToFirst())
        {
            do {

                CN_habilidad x = new CN_habilidad(cursor.getString(0) , cursor.getString(1) , cursor.getString(2)
                                                , cursor.getString(3) , cursor.getInt(4));

                list.add(x);

            }while (cursor.moveToNext());
        }

        return list;

    }
}

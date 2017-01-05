package supercrack.sigmamoviles.com.ama.ServicioInterno;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Conexion.SqlLite;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.PreguntaOpcion;

/**
 * Created by eglp on 31/12/2016.
 */

public class RegistroProcesoPreguntasOpciones implements RegistroPreguntasOpciones{

    private SqlLite conexion;

    public RegistroProcesoPreguntasOpciones(Context context)
    {
        conexion = new SqlLite(context);
    }


    @Override
    public void registra(PreguntaOpcion x) {

        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("idpregunta" , x.getIdpregunta());
        cv.put("uuid" , x.getUuid());
        cv.put("secuencia" , x.getSecuencia());
        cv.put("descripcion" , x.getDescripcion());
        cv.put("imagen" , x.getImagen());

        db.insert("tb_PreguntaOpcion" , null , cv);
    }

    @Override
    public void eliminar(String id) {

        SQLiteDatabase db = conexion.getWritableDatabase();

        db.delete("tb_PreguntaOpcion" , "uuid ='" +id +"'" , null);
    }

    @Override
    public ArrayList<PreguntaOpcion> lista() {

        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_PreguntaOpcion" , null);

        ArrayList<PreguntaOpcion> list = new ArrayList<PreguntaOpcion>();

        if(cursor.moveToFirst())
        {
            do {

                PreguntaOpcion x = new PreguntaOpcion(cursor.getString(0) , cursor.getString(1) ,
                                                      cursor.getInt(2) , cursor.getString(3) ,
                                                      cursor.getString(4));

                list.add(x);

            }while (cursor.moveToNext());
        }

        return list;
    }
}

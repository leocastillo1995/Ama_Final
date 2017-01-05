package supercrack.sigmamoviles.com.ama.ServicioInterno;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Conexion.SqlLite;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Sin_Conexion.PreguntasTitulo;

/**
 * Created by eglp on 31/12/2016.
 */

public class RegistroProcesoPreguntasTitulo implements RegistrosPreguntasTitulo {

    private SqlLite conexion;

    public RegistroProcesoPreguntasTitulo(Context context)
    {
        conexion = new SqlLite(context);
    }


    @Override
    public void RegistrarPRegunta(PreguntasTitulo x) {

        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("idpregunta" , x.getIdpregunta());
        cv.put("titulo" , x.getTitulo());
        cv.put("secuencia" , x.getSecuencia());

        db.insert("tb_PreguntasTitulo" , null , cv);

    }

    @Override
    public void Eliminar(String id) {

        SQLiteDatabase db = conexion.getWritableDatabase();

        db.delete("tb_PreguntasTitulo" , "idpregunta = '" +id +"'" , null);

    }

    @Override
    public ArrayList<PreguntasTitulo> lista() {

        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_PreguntasTitulo" , null);

        ArrayList<PreguntasTitulo> list = new ArrayList<PreguntasTitulo>();

        if(cursor.moveToFirst())
        {
            do {

                PreguntasTitulo x = new PreguntasTitulo(cursor.getString(0) , cursor.getString(1)
                                                        , cursor.getInt(2));

                list.add(x);

            }while (cursor.moveToNext());
        }

        return list;
    }
}

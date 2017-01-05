package supercrack.sigmamoviles.com.ama.ServicioInterno;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import supercrack.sigmamoviles.com.ama.Conexion.SqlLite;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.PreguntaOpciones;

/**
 * Created by eglp on 03/01/2017.
 */

public class RegistrosProcesoPreguntas implements RegistrosPreguntas{

    private SqlLite conexion;

    public RegistrosProcesoPreguntas(Context context)
    {
        conexion = new SqlLite(context);
    }

    @Override
    public void Registrar(PreguntaOpciones x) {

        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("codigo" , x.getId_opcion());

        db.insert("tb_scn_repuestas" , null , cv);

    }

    @Override
    public void Eliminar(String codigo) {

        SQLiteDatabase db = conexion.getWritableDatabase();

        db.delete("tb_scn_repuestas" , "codigo ='" +codigo +"'" , null);

    }

    @Override
    public ArrayList<PreguntaOpciones> lista() {

        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_scn_repuestas" , null);

        ArrayList<PreguntaOpciones> list = new ArrayList<PreguntaOpciones>();

        if(cursor.moveToFirst())
        {
            do {

                PreguntaOpciones x = new PreguntaOpciones(cursor.getString(0));

                list.add(x);

            }while (cursor.moveToNext());
        }

        return list;
    }
}

package supercrack.sigmamoviles.com.ama.Conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import supercrack.sigmamoviles.com.ama.R;

/**
 * Created by eglp on 31/12/2016.
 */

public class SqlLite extends SQLiteOpenHelper{

    public SqlLite(Context context) {
        super(context, "bd_ama.bd", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table tb_PreguntasTitulo (" +
                               "idpregunta varchar(100) not null," +
                               "titulo varchar(90) not null," +
                               "secuencia int not null )");

        sqLiteDatabase.execSQL("create table tb_PreguntaOpcion (" +
                               "idpregunta varchar(100) not null," +
                               "uuid varchar(100) not null," +
                               "secuencia int not null," +
                               "descripcion varchar(100) not null," +
                               "imagen varchar(100) null )");

        sqLiteDatabase.execSQL("create table tb_cn_edad (" +
                               "uuid varchar(100) not null, " +
                               "descripcion varchar(100) not null, " +
                               "imagen varchar(100) not null, " +
                               "secuencia int not null )");

        sqLiteDatabase.execSQL("create table tb_cn_habilidades (" +
                               "codigoedad varchar(100) not null, " +
                               "uuid varchar(100) not null, " +
                               "descripcion varchar(100) not null, " +
                               "imagen varchar(100) not null, " +
                               "secuencia int not null )");

        sqLiteDatabase.execSQL("create table tb_cn_video (" +
                               "codigohabilidad varchar(100) not null, " +
                               "uuid varchar(100) not null, " +
                               "descripcion varchar(100) not null, " +
                               "imagen varchar(100) not null, " +
                               "video varchar(100) null, " +
                               "secuencia int not null )");

        sqLiteDatabase.execSQL("create table tb_scn_repuestas (" +
                               "codigo varchar(100) not null )");

        sqLiteDatabase.execSQL("create table tb_edad (" +
                "edadid varchar(255) not null," +
                "nombre varchar(255) not null," +
                "url varchar(255) not null," +
                "secuencia int not null," +
                "activo varchar(20) not null )");

        sqLiteDatabase.execSQL("insert into tb_edad values('ED001' , '0 a 12 \n meses',"+ R.drawable.nino_0_12  +", 1 , 'true')");
        sqLiteDatabase.execSQL("insert into tb_edad values('ED002' , '1 a 2 \n años',"+ R.drawable.nino_1_2  +", 1 , 'true')");
        sqLiteDatabase.execSQL("insert into tb_edad values('ED003' , '2 a 3 \n años',"+ R.drawable.nino_2_3  +", 1 , 'true')");
        sqLiteDatabase.execSQL("insert into tb_edad values('ED004' , '3 a 4 \n años',"+ R.drawable.nino_3_4  +", 1 , 'true')");
        sqLiteDatabase.execSQL("insert into tb_edad values('ED005' , '4 a 6 \n años',"+ R.drawable.nino_4_6  +", 1 , 'true')");

        sqLiteDatabase.execSQL("create table tb_habilidad(" +
                "habilidaid varchar(255) not null," +
                "nombre varchar(255) not null," +
                "url varchar(255) not null," +
                "secuencia int not null," +
                "activo varchar(20) not null )");

        sqLiteDatabase.execSQL("insert into tb_habilidad values('HA001' , 'Personal Social' ,"+ R.drawable.social + ", 1 , 'true')");
        sqLiteDatabase.execSQL("insert into tb_habilidad values('HA002' , 'Motora' ,"+ R.drawable.motora + ", 1 , 'true')");
        sqLiteDatabase.execSQL("insert into tb_habilidad values('HA003' , 'Afectiva' ,"+ R.drawable.afectiva + ", 1 , 'true')");
        sqLiteDatabase.execSQL("insert into tb_habilidad values('HA004' , 'Intelectual' ,"+ R.drawable.intelectual + ", 1 , 'true')");

        sqLiteDatabase.execSQL("create table tb_video(" +
                "videoid varchar(255) not null," +
                "edadid varchar(255) not null," +
                "habilidaid varchar(255) not null," +
                "nombre varchar(255) not null," +
                "imagen varchar(255) not null," +
                "ruta varchar(10000) not null," +
                "secuencia int not null," +
                "activo int not null )");

        sqLiteDatabase.execSQL("insert into tb_video values('V001' , 'ED001' , 'HA001' , 'Me cambian el pañal' ," + R.drawable.me_cambian_el_panial + "," + R.raw.me_cambian_el_panial + " , 1 , 1)");
        sqLiteDatabase.execSQL("insert into tb_video values('V002' , 'ED001' , 'HA003' , 'La leche materna me alimenta' ," + R.drawable.la_leche_materna_me_alimenta + "," + R.raw.la_leche_materna_me_alimenta + " , 1 , 1)");
//        sqLiteDatabase.execSQL("insert into tb_video values('V003' , 'ED002' , 'HA001' , 'Me divierto imitándote' ," + R.drawable.me_divierto_imitandote + "," + R.raw.me_divierto_imitandote + " , 1 , 1)");
        sqLiteDatabase.execSQL("insert into tb_video values('V004' , 'ED002' , 'HA002' , 'Aprendo a pararme' ," + R.drawable.aprendo_a_pararme + "," + R.raw.aprendo_a_pararme + " , 1 , 1)");
        sqLiteDatabase.execSQL("insert into tb_video values('V005' , 'ED003' , 'HA003' , 'Me enseñan a vestirme' ," + R.drawable.me_ensenian_a_vestirme + "," + R.raw.me_ensenian_a_vestirme + " , 1 , 1)");
//      sqLiteDatabase.execSQL("insert into tb_video values('V006' , 'ED003' , 'HA004' , 'Reconociendo los colores' ," + R.drawable.reconociendo_los_colores + "," + R.raw.reconociendo_los_colores + " , 1 , 1)");
        sqLiteDatabase.execSQL("insert into tb_video values('V007' , 'ED004' , 'HA001' , 'Aprendo a lavarme' ," + R.drawable.aprendo_a_lavarme + "," + R.raw.aprendo_a_lavarme + " , 1 , 1)");
        sqLiteDatabase.execSQL("insert into tb_video values('V008' , 'ED004' , 'HA002' , 'Ensarto mis chapitas' ," + R.drawable.ensarto_mis_chapitas + "," + R.raw.ensarto_mis_chapitas + " , 1 , 1)");
//      sqLiteDatabase.execSQL("insert into tb_video values('V009' , 'ED004' , 'HA002' , 'Aprendo a comer solo' ," + R.drawable.aprendo_a_comer_solo + "," + R.raw.aprendo_a_comer_solo + " , 1 , 1)");
        sqLiteDatabase.execSQL("insert into tb_video values('V010' , 'ED004' , 'HA002' , 'Juego con mis palitos' ," + R.drawable.juego_con_mis_palitos + "," + R.raw.juego_con_mis_palitos + " , 1 , 1)");
//      sqLiteDatabase.execSQL("insert into tb_video values('V011' , 'ED004' , 'HA002' , 'Juego con mi amigo' ," + R.drawable.juego_con_mi_amigo + "," + R.raw.juego_con_mi_amigo + " , 1 , 1)");
        sqLiteDatabase.execSQL("insert into tb_video values('V012' , 'ED004' , 'HA004' , 'Aprendo a contar' ," + R.drawable.ensarto_mis_chapitas + "," + R.raw.aprendo_a_contar + " , 1 , 1)");
        sqLiteDatabase.execSQL("insert into tb_video values('V014' , 'ED005' , 'HA001' , 'Jugamos a la ronda' ," + R.drawable.jugamos_a_la_ronda + "," + R.raw.jugamos_a_la_ronda + " , 1 , 1)");
        sqLiteDatabase.execSQL("insert into tb_video values('V015' , 'ED005' , 'HA003' , 'Ordeno y ayudo' ," + R.drawable.ordeno_y_ayudo + "," + R.raw.ordeno_y_ayudo + " , 1 , 1)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        switch (i1)
        {
            case 2: break;
        }

    }
}

package com.access.accesos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME  = "DBprueba";
    private static final int DB_VERSION = 1;
    public DBHelper(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table usuarios(_id integer primary key autoincrement, "
                  +"nombre text not null, login not null, clave text not null, idrol integer not null)");

        db.execSQL("insert into usuarios(nombre, login, clave, idrol) values('Marco Chunga', 'marco', '123',1)");
        db.execSQL("insert into usuarios(nombre, login, clave, idrol) values('Mijares Navarro', 'mija', '123',2)");
        db.execSQL("insert into usuarios(nombre, login, clave, idrol) values('Gerson Malca', 'gerson', '123',3)");

        db.execSQL("create table roles(idrol integer primary key autoincrement," +
                "nombre text not null)");

        db.execSQL("insert into roles(nombre) values('Administrador')");
        db.execSQL("insert into roles(nombre) values('Director')");
        db.execSQL("insert into roles(nombre) values('Alumno')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static class Usuarios{
        public static final String TABLE = "usuarios";
        public static final String _ID = "_id";
        public static final String NOMBRE = "nombre";
        public static final String LOGIN = "login";
        public static final String CLAVE = "clave";
        public static final String IDROL = "idrol";
        public static final String[] COLUMNAS = new String[]{_ID, NOMBRE, LOGIN, CLAVE, IDROL};
    }

    public static class Roles{
        public static final String TABLER = "roles";
        public static final String IDROL = "idrol";
        public static final String NOMBRER = "nombre";
        public static final String[] COLUMNASR = new String[]{IDROL, NOMBRER};
    }
    public ArrayList misdats(String user){
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * FROM usuarios where user='login'";
        Cursor registros = database.rawQuery(q,null);
        if (registros.moveToFirst()){
            do {
                lista.add(registros.getString(4));
            }while (registros.moveToNext());
        }
        return lista;
    }
    public String guardaruser(String nombre, String login, String clave, Integer idrol){
        String mensaje="";
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre",nombre);
        contenedor.put("login",login);
        contenedor.put("clave",clave);
        contenedor.put("idrol",idrol);
        try {
            database.insertOrThrow("usuarios",null, contenedor);
            mensaje = "Ingresado Correctamente";
        } catch (SQLException e){
            mensaje = null;
        }
        database.close();
        return mensaje;
    }
}
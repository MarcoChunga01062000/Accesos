package com.access.accesos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class RolDao {
    private DBHelper helper;
    private SQLiteDatabase database;
    public RolDao(Context context){
        helper = new DBHelper(context);
    }
    private SQLiteDatabase getDatabase(){
        if(database == null){
            database = helper.getWritableDatabase();
        }
        return database;
    }
    private Rol crearRol(Cursor cursor){
        Rol rol = new Rol(
                cursor.getInt(cursor.getColumnIndex(DBHelper.Roles.IDROL)),
                cursor.getString(cursor.getColumnIndex(DBHelper.Roles.NOMBRER))
        );
        return rol;
    }
    public List<Rol> listarRoles(){
        Cursor cursor = getDatabase().query(DBHelper.Roles.TABLER,DBHelper.Roles.COLUMNASR,null, null, null, null,null);
        List<Rol> lista = new ArrayList<Rol>();
        while(cursor.moveToNext()){
            Rol modelo = crearRol(cursor);
            lista.add(modelo);
        }
        return lista;
    }
    public long modificarRol(Rol rol){
        ContentValues values = new ContentValues();
        values.put(DBHelper.Roles.NOMBRER, rol.getNombre());
        if(rol.getIdr() != null){
            return database.update(DBHelper.Roles.TABLER, values,
                    "idr = ?", new String[]{rol.getIdr().toString()});
        }
        return getDatabase().insert(DBHelper.Roles.TABLER,null,values);
    }
    public boolean eliminarRol(int id){
        return getDatabase().delete(DBHelper.Roles.TABLER,"idr = ?", new String[]{Integer.toString(id)}) > 0;
    }
    public Rol buscarRolPorID(int id){
        Cursor cursor = getDatabase().query(DBHelper.Roles.TABLER,
                DBHelper.Roles.COLUMNASR, "idr = ?", new String[]{ Integer.toString(id)}, null, null, null);
        if(cursor.moveToNext()){
            Rol model = crearRol(cursor);
            cursor.close();
            return model;
        }
        return null;
    }
    public void cerrarDB(){
        helper.close();
        database = null;
    }
}

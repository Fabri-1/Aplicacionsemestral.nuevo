package com.example.aplicacionsemestral.esquemas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class OperacionesCRUD extends SQLiteOpenHelper {

    public OperacionesCRUD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(User.Esquema.CREAR_TABLA_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(User.Esquema.BORRAR_TABLA_USUARIO);
        onCreate(db);
    }

    public long insertTabla(ContentValues columnas_valores_insertar, String nombre_tabla){
        long id_reg_insertado = 0;

        try{

            SQLiteDatabase baseDatos = this.getWritableDatabase();
            id_reg_insertado = baseDatos.insert(nombre_tabla,null, columnas_valores_insertar);

        }catch (Exception e){

            System.out.println("Error:"+e.getMessage());
        }
        return id_reg_insertado;
    }

    public List<ContentValues> obtenerDatos(String columnasObtener[], String consultaFiltro, String valoresFiltro[], String nomTabla){
        Cursor registrosRet = null;
        List<ContentValues> listaRegistros = new ArrayList<ContentValues>();

        try{
            SQLiteDatabase db = this.getWritableDatabase();
            registrosRet = db.query(nomTabla, columnasObtener, consultaFiltro, valoresFiltro,null,null,null);
            if(null != registrosRet){
                registrosRet.moveToFirst();

                while(registrosRet.isAfterLast()==false){
                    ContentValues auxiliar = new ContentValues();

                    for(int i=0; i < registrosRet.getColumnCount();i++){
                        auxiliar.put(registrosRet.getColumnName(i), registrosRet.getString(i));
                    }
                    listaRegistros.add(auxiliar);
                    registrosRet.moveToNext();
                }
                registrosRet.close();
            }

        }catch(Exception e){
            System.out.println("Error metodo obtenerDatos: "+e.getMessage());
        }

        return listaRegistros;
    }

    public int borrarRegistro(String nombre_tabla, String condicion, String[] val_condicion){
        int registros_eliminados=0;

        try{
            SQLiteDatabase db = this.getWritableDatabase();
            registros_eliminados = db.delete(nombre_tabla,condicion,val_condicion);
        }catch(Exception e){
            System.out.println("Error metodo borrar"+e.getMessage());
        }
        return registros_eliminados;

    }

    public int actualizarRegistro(ContentValues columna_valores, String condicion, String[] condicion_valores, String nombre_tabla){
        int cantidad_actualizados=0;

        try{
            SQLiteDatabase db = this.getWritableDatabase();
            cantidad_actualizados = db.update(nombre_tabla,columna_valores,condicion,condicion_valores);
        }catch(Exception e){
            System.out.println("Error metodo actualizar registros"+e.getMessage());
        }
        return cantidad_actualizados;
    }
}

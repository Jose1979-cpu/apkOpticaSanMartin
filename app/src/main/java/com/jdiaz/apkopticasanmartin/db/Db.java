package com.jdiaz.apkopticasanmartin.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class Db extends SQLiteOpenHelper {
    SQLiteDatabase db;
    String _SQL;

    public Db(@Nullable Context context) {
        super( context, "optica.db", null, 1 );
        db = getWritableDatabase();
    }

    public void DbCloseOpen() {
        db.close();
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "create table Usuario ( id integer, Nombres text, Apellidos text, Telefono text, Correo text, Passwordd text  )" );
        db.execSQL( "create table Marca ( id integer, Detalle text )" );
        db.execSQL( "create table Categoria ( id integer, Detalle text )" );
        db.execSQL( "create table CategoriaMarca ( idCategoria integer, idMarca integer )" );

        db.execSQL( "create table Producto ( id integer, Modelo text, idCategoria integer," +
                    " idMarca integer,idColorMarco integer,idColorLente integer," +
                    " idFormaMarco integer, idMaterialMontura integer, idMaterialLente integer," +
                    " Genero text, Varilla text, Puente text, Espejado text, Polarizado text, Estado text," +
                    " Precio real, Stock integer, Categoria text, Marca text, ColorMarco text, ColorLente text," +
                    " FormaMarco text, MaterialMontura text, MaterialLente text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) { }

    public void Sentencia( String _SQL ) {
        this._SQL = _SQL;
    }

    public void Ejecutar() {
        db.execSQL( _SQL );
    }

    public Cursor getCursor( ) {
        return db.rawQuery( _SQL, null );
    }

    public String getCampo() {
        String sCampo = null;
        Cursor cursor = getCursor();
        if ( cursor.moveToFirst() )
            sCampo = cursor.getString(0);

        cursor.close();
        return sCampo;
    }

    public String[][] getRegistros() {
        String item;
        String[][] mRegistros = null;
        Cursor cursor = getCursor();
        if ( cursor.moveToFirst() ) {
            int filas = cursor.getCount();
            int columnas = cursor.getColumnCount();
            int fila = 0;
            mRegistros = new String[ filas ][ columnas ];
            for ( cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext(), fila++ )
                for ( int i=0; i < columnas; i++ ) {
                    item = cursor.getString(i);
                    mRegistros[ fila ][ i ] = item == null || item.equals("null") ? null : item.trim();
                }
        }

        cursor.close();
        return mRegistros;
    }

    public int Insert(String sTabla, ContentValues values ) {

        return (int) db.insert( sTabla, null, values );
    }

    public long Update( String sTabla, ContentValues values, String sCondicion) {
        return db.update( sTabla, values, sCondicion, null );
    }

}


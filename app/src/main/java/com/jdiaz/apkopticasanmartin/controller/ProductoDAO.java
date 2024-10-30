package com.jdiaz.apkopticasanmartin.controller;

import android.content.Context;
import android.database.Cursor;

import com.jdiaz.apkopticasanmartin.db.Db;
import com.jdiaz.apkopticasanmartin.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    Db db;

    public ProductoDAO(Context context) {
        db = new Db( context );
    }

    public List<Producto> getProductos(String sFiltro) {
        if ( sFiltro.equals("Novedades") )
            db.Sentencia("select * from Producto where Estado='N'");
        Cursor cursor = db.getCursor();
        if ( cursor.getCount() == 0 ) { cursor.close(); return null; }


        List<Producto> productos = new ArrayList<>();
        while( cursor.moveToNext() )
            productos.add( new Producto( cursor ) );

        cursor.close();
        return productos;
    }

}

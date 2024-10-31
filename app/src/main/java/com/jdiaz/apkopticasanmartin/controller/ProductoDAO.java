package com.jdiaz.apkopticasanmartin.controller;

import android.content.Context;
import android.database.Cursor;

import com.jdiaz.apkopticasanmartin.db.Db;
import com.jdiaz.apkopticasanmartin.model.Marca;
import com.jdiaz.apkopticasanmartin.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    Db db;

    public ProductoDAO(Context context) {
        db = new Db( context );
    }

    public List<Producto> getProductos(String sFiltro, int id) {
        if ( sFiltro.equals("novedades")  ) db.Sentencia("select * from Producto where Estado='N'" );
        if ( sFiltro.equals("categorias") ) db.Sentencia( "select * from Producto where idCategoria = " + id );

        Cursor cursor = db.getCursor();
        if ( cursor.getCount() == 0 ) { cursor.close(); return null; }


        List<Producto> productos = new ArrayList<>();
        while( cursor.moveToNext() )
            productos.add( new Producto( cursor ) );

        cursor.close();
        return productos;
    }

    public List<Marca> getMarcas(int id) {
        db.Sentencia( "select m.* from CategoriaMarca cm, Marca m where cm.idMarca = m.id and cm.idCategoria = " + id );
        Cursor cursor = db.getCursor();
        if ( cursor.getCount() == 0 ) { cursor.close(); return null; }

        List<Marca> marcas = new ArrayList<>();
        while( cursor.moveToNext() )
            marcas.add( new Marca( cursor.getInt(0), cursor.getString(1) ) );

        cursor.close();
        return marcas;
    }

}

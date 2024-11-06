package com.jdiaz.apkopticasanmartin.controller;

import android.content.Context;
import android.database.Cursor;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.QuerySnapshot;
import com.jdiaz.apkopticasanmartin.MainActivity;
import com.jdiaz.apkopticasanmartin.db.Db;
import com.jdiaz.apkopticasanmartin.model.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    Db db;

    public CategoriaDAO(Context context) {
        db = new Db( context );
    }

    public List<Categoria> getCategorias() {
        db.Sentencia("select * from Categoria");
        Cursor cursor = db.getCursor();
        if ( cursor.getCount() == 0 ) { cursor.close(); return null; }


        List<Categoria> categorias = new ArrayList<>();
        while( cursor.moveToNext() )
            categorias.add( new Categoria( cursor.getInt(0), cursor.getString(1) ) );

        cursor.close();
        return categorias;
    }


}

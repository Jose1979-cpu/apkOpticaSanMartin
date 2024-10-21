package com.jdiaz.apkopticasanmartin.controller;

import android.content.ContentValues;
import android.content.Context;

import com.jdiaz.apkopticasanmartin.db.Db;

import org.json.JSONException;
import org.json.JSONObject;

public class SincronizarDAO {
    Db db;

    public SincronizarDAO(Context context) {
        db = new Db(context);
    }

    public void CategoriaInsert(JSONObject jso) throws JSONException {
        ContentValues values = new ContentValues();
        values.put("id", jso.getInt("id") );
        values.put("detalle", jso.getString("Detalle") );

        db.Insert("Categoria", values);
    }

    public void MarcaInsert(JSONObject jso ) throws JSONException {
        ContentValues values = new ContentValues();
        values.put("id", jso.getInt("id") );
        values.put("detalle", jso.getString("Detalle") );

        db.Insert("Marca", values);
    }

    public void ProductoInsert(JSONObject jso) throws JSONException {
        ContentValues values = new ContentValues();
        values.put("id", jso.getInt("id") );
        values.put("Modelo", jso.getString("Modelo") );
        values.put("idCategoria", jso.getInt("idCategoria") );
        values.put("idMarca", jso.getInt("idMarca") );
        values.put("idColorMarco", jso.getInt("idColorMarco") );
        values.put("idColorLente", jso.getInt("idColorLente") );
        values.put("idFormaMarco", jso.getInt("idFormaMarco") );
        values.put("idMaterialMontura", jso.getInt("idMaterialMontura") );
        values.put("idMaterialLente", jso.getInt("idMaterialLente") );
        values.put("Genero", jso.getString("Genero") );
        values.put("Varilla", jso.getString("Varilla").equals("null") ? null : jso.getString("Varilla") );
        values.put("Puente", jso.getString("Puente").equals("null") ? null : jso.getString("Puente") );
        values.put("Espejado", jso.getString("Espejado").equals("null") ? null : jso.getString("Espejado") );
        values.put("Polarizado", jso.getString("Polarizado").equals("null") ? null : jso.getString("Polarizado") );
        values.put("Precio", jso.getDouble("Precio") );
        values.put("Stock", jso.getInt("Stock") );
        values.put("Categoria", jso.getString("Categoria") );
        values.put("Marca", jso.getString("Marca") );
        values.put("ColorMarco", jso.getString("Color Marco") );
        values.put("ColorLente", jso.getString("Color Lente") );
        values.put("FormaMarco", jso.getString("Forma Marco") );
        values.put("MaterialMontura", jso.getString("Material Montura") );
        values.put("MaterialLente", jso.getString("Material Lente") );

        db.Insert("Producto", values);
    }

}
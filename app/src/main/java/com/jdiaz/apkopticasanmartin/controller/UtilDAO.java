package com.jdiaz.apkopticasanmartin.controller;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.jdiaz.apkopticasanmartin.db.Db;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class UtilDAO {
    Context context;
    Db db;

    public UtilDAO(Context context) {
        this.context = context;
        db = new Db( context );
    }

    public String decimalFormat(double value) {
        return new DecimalFormat("#####0.00").format(value);
    }

    public String decimalFormatGPS(double value) {
        return new DecimalFormat("#####0.0000").format(value);
    }

    public int getHoraHoy() {
        return Integer.parseInt(new SimpleDateFormat("HH").format(new Date()));
    }

    public String FechaHoyES() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
    }

    public String FechaHoy() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public String getVarios(String sCodigo) {
        db.Sentencia( String.format( "select detalle from Varios where codigo='%s'", sCodigo ) );
        return db.getCampo();
    }

    public void setVarios(String sCodigo, String sDetalle) {
        ContentValues values = new ContentValues();
        values.put("detalle", sDetalle);
        db.Update( "Varios", values, String.format("codigo ='%s'",sCodigo) );

        //db.Sentencia("update Varios set detalle = " + (sDetalle == null ? "null" : "'" + sDetalle + "'") + " where codigo='" + sCodigo + "'");
        //db.Ejecutar();
    }

    public boolean getCount(String sTabla) {
        db.Sentencia("select count(*) from " + sTabla);
        return !db.getCampo().equals("0");
    }

    public int Insert(String tabla, ContentValues values) {
        return db.Insert(tabla, values);
    }

    public void Delete(String sTabla) {
        db.Sentencia("delete from " + sTabla);
        db.Ejecutar();
    }

    public void SaveLog(String sError, String sMensaje) {
        ContentValues values = new ContentValues();
        values.put("error", sError);
        values.put("detalle", sMensaje);
        values.put("fecha", FechaHoy());
        db.Insert("Log", values);
    }


    public String VolleyError(VolleyError error, String sVolley) {
        String sMensaje = "";
        if ( error.getMessage() == null && error.networkResponse != null ) {
            if ( error.networkResponse.headers.get("Content-Type").equals("application/json"))
                try {
                    sMensaje = new JSONObject(new String(error.networkResponse.data)).getString("message");
                } catch (Exception ex) { Log.i("VolleyError", Objects.requireNonNull( ex.getMessage() ) ); }
            else sMensaje = "Error en el servidor";
        } else if (error instanceof ServerError) sMensaje = "Error en el servidor";
        else if (error instanceof NoConnectionError) sMensaje = "No hay conexión de internet";
        else if (error instanceof NetworkError) sMensaje = "Error de red";
        else if (error instanceof TimeoutError) sMensaje = "Tiempo de conexión excedido";
        else if (error instanceof AuthFailureError) sMensaje = "AuthFailure";
        else if (error instanceof ParseError) sMensaje = "ParseError";
        SaveLog(sVolley, sMensaje + ( error.getMessage() == null ? "" : " --> " + error.getMessage()));
        Log.i("Voley Error", sVolley + ": " + sMensaje + (error.getMessage() == null ? "" : " --> " + error.getMessage()));
        return sMensaje;
    }

}

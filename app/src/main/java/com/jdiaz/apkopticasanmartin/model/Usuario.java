package com.jdiaz.apkopticasanmartin.model;

import android.database.Cursor;

public class Usuario {
    int id;
    String Nombres, Apellidos, Telefono, Correo, Passwordd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getPasswordd() {
        return Passwordd;
    }

    public void setPasswordd(String passwordd) {
        Passwordd = passwordd;
    }

    public Usuario(Cursor cursor) {
        id = cursor.getInt(0);
        Nombres = cursor.getString(1);
        Apellidos = cursor.getString(2);
        Telefono = cursor.getString(3);
        Correo = cursor.getString(4);
        Passwordd = cursor.getString(5);
    }
}

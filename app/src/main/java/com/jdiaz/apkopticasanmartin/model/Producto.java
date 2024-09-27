package com.jdiaz.apkopticasanmartin.model;

import android.database.Cursor;

public class Producto {
    int id, Stock;
    String Modelo, Genero, Varilla, Puente, Espejado, Polarizado, Estado;
    double Precio;

    public Producto(Cursor cursor) {
        id = cursor.getInt(1);
        Modelo = cursor.getString(2);
        Genero = cursor.getString(3);
        Varilla = cursor.getString(4);
        Puente = cursor.getString(5);
        Espejado = cursor.getString(6);
        Polarizado = cursor.getString(7);
        Estado = cursor.getString(8);
        Precio = cursor.getDouble(9);
        Stock = cursor.getInt(10);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public String getVarilla() {
        return Varilla;
    }

    public void setVarilla(String varilla) {
        Varilla = varilla;
    }

    public String getPuente() {
        return Puente;
    }

    public void setPuente(String puente) {
        Puente = puente;
    }

    public String getEspejado() {
        return Espejado;
    }

    public void setEspejado(String espejado) {
        Espejado = espejado;
    }

    public String getPolarizado() {
        return Polarizado;
    }

    public void setPolarizado(String polarizado) {
        Polarizado = polarizado;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }
}

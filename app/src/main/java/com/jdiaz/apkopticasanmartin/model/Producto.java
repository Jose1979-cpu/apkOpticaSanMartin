package com.jdiaz.apkopticasanmartin.model;

import android.database.Cursor;

import java.io.Serializable;

public class Producto implements Serializable {
    int id, idCategoria, idMarca, idColorMarco, idColorLente, idFormaMarco, idMaterialMontura, idMaterialLente, Stock;
    String Modelo, Genero, Varilla, Puente, Espejado, Polarizado, Estado;
    String Categoria, Marca, ColorMarco, ColorLente, FormaMarco, MaterialMontura, MaterialLente;
    double Precio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public int getIdColorMarco() {
        return idColorMarco;
    }

    public void setIdColorMarco(int idColorMarco) {
        this.idColorMarco = idColorMarco;
    }

    public int getIdColorLente() {
        return idColorLente;
    }

    public void setIdColorLente(int idColorLente) {
        this.idColorLente = idColorLente;
    }

    public int getIdFormaMarco() {
        return idFormaMarco;
    }

    public void setIdFormaMarco(int idFormaMarco) {
        this.idFormaMarco = idFormaMarco;
    }

    public int getIdMaterialMontura() {
        return idMaterialMontura;
    }

    public void setIdMaterialMontura(int idMaterialMontura) {
        this.idMaterialMontura = idMaterialMontura;
    }

    public int getIdMaterialLente() {
        return idMaterialLente;
    }

    public void setIdMaterialLente(int idMaterialLente) {
        this.idMaterialLente = idMaterialLente;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
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


    public Producto(Cursor cursor) {
        id = cursor.getInt(0);
        Modelo = cursor.getString(1);
        idCategoria = cursor.getInt(2);
        idMarca = cursor.getInt(3);
        idColorMarco = cursor.getInt(4);
        idColorLente = cursor.getInt(5);
        idFormaMarco = cursor.getInt(6);
        idMaterialMontura = cursor.getInt(7);
        idMaterialLente = cursor.getInt(8);
        Genero = cursor.getString(9);
        Varilla = cursor.getString(10);
        Puente = cursor.getString(11);
        Espejado = cursor.getString(12);
        Polarizado = cursor.getString(13);
        Estado = cursor.getString(14);
        Precio = cursor.getDouble(15);
        Stock = cursor.getInt(16);

        Categoria = cursor.getString(17);
        Marca = cursor.getString(18);
        ColorMarco = cursor.getString(19);
        ColorLente = cursor.getString(20);
        FormaMarco = cursor.getString(8);
        MaterialMontura = cursor.getString(8);
        MaterialLente = cursor.getString(8);
    }


}

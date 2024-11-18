package com.jdiaz.apkopticasanmartin.model;

import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

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

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getColorMarco() {
        return ColorMarco;
    }

    public void setColorMarco(String colorMarco) {
        ColorMarco = colorMarco;
    }

    public String getColorLente() {
        return ColorLente;
    }

    public void setColorLente(String colorLente) {
        ColorLente = colorLente;
    }

    public String getFormaMarco() {
        return FormaMarco;
    }

    public void setFormaMarco(String formaMarco) {
        FormaMarco = formaMarco;
    }

    public String getMaterialMontura() {
        return MaterialMontura;
    }

    public void setMaterialMontura(String materialMontura) {
        MaterialMontura = materialMontura;
    }

    public String getMaterialLente() {
        return MaterialLente;
    }

    public void setMaterialLente(String materialLente) {
        MaterialLente = materialLente;
    }

    public Producto() { }

    public Producto(JSONObject jso) throws JSONException {
        id = jso.getInt("id");
        Modelo = jso.getString("Modelo");
        idCategoria = jso.getInt("idCategoria");
        idMarca = jso.getInt("idMarca");
        idColorMarco = jso.getInt("idColorMarco");
        idColorLente = jso.getInt("idColorLente");
        idFormaMarco = jso.getInt("idFormaMarco");
        idMaterialMontura = jso.getInt("idMaterialMontura");
        idMaterialLente = jso.getInt("idMaterialLente");
        Genero = jso.getString("Genero");
        Varilla = jso.isNull("Varilla") ? null : jso.getString("Varilla");
        Puente = jso.isNull("Puente") ? null : jso.getString("Puente");
        Espejado = jso.isNull("Espejado") ? null : jso.getString("Espejado");
        Polarizado = jso.isNull("Polarizado") ? null : jso.getString("Polarizado");
        Estado = jso.getString("Estado");
        Precio = jso.getDouble("Precio");
        Stock = jso.getInt("Stock");

        Categoria = jso.getString("Categoria");
        Marca = jso.getString("Marca");
        ColorMarco = jso.isNull("ColorMarco") ? null : jso.getString("ColorMarco");
        ColorLente = jso.isNull("ColorLente") ? null : jso.getString("ColorLente");
        FormaMarco = jso.isNull("FormaMarco") ? null : jso.getString("FormaMarco");
        MaterialMontura = jso.isNull("MaterialMontura") ? null : jso.getString("MaterialMointura");
        MaterialLente = jso.isNull("MaterialLente") ? null : jso.getString("MaterialLente");
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
        FormaMarco = cursor.getString(21);
        MaterialMontura = cursor.getString(22);
        MaterialLente = cursor.getString(23);
    }


}

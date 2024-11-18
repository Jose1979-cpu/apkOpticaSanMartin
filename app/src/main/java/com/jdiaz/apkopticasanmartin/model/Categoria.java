package com.jdiaz.apkopticasanmartin.model;

import java.io.Serializable;
import java.util.List;

public class Categoria implements Serializable {
    int id;
    String Detalle;
    List<Marca> marcas = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetalle() {
        return Detalle;
    }

    public void setDetalle(String detalle) {
        Detalle = detalle;
    }

    public List<Marca> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<Marca> marcas) {
        this.marcas = marcas;
    }

    public Categoria() { }

    public Categoria(int id, String detalle, List<Marca> marcas) {
        this.id = id;
        Detalle = detalle;
        this.marcas = marcas;
    }
}

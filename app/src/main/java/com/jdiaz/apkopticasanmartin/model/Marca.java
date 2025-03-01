package com.jdiaz.apkopticasanmartin.model;

import java.io.Serializable;

public class Marca implements Serializable {
    int id;
    String Detalle, Descripcion;

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

    public String getDescripcion() { return Descripcion; }

    public void setDescripcion(String descripcion) { Descripcion = descripcion; }

    public Marca() { }

    public Marca(int id, String detalle, String descripcion ) {
        this.id = id;
        Detalle = detalle;
        Descripcion = descripcion;
    }

    @Override
    public String toString() {
        return Detalle;
    }

}

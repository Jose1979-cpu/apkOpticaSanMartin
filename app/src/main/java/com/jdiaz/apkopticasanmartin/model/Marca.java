package com.jdiaz.apkopticasanmartin.model;

import java.io.Serializable;

public class Marca implements Serializable {
    int id;
    String Detalle;

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

    public Marca(int id, String detalle) {
        this.id = id;
        Detalle = detalle;
    }

    @Override
    public String toString() {
        return Detalle;
    }
}

package com.jdiaz.apkopticasanmartin.bean;

public class Categoria {

    private Integer id;
    private String Detalle;

    public Categoria(Integer id, String detalle) {
        this.id = id;
        Detalle = detalle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDetalle() {
        return Detalle;
    }

    public void setDetalle(String detalle) {
        Detalle = detalle;
    }
}

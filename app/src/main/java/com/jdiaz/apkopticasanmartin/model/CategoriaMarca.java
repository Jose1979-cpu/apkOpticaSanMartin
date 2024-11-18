package com.jdiaz.apkopticasanmartin.model;

import java.io.Serializable;

public class CategoriaMarca implements Serializable {
    int idCategoria, idMarca;

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

    public CategoriaMarca() { }

    public CategoriaMarca(int idCategoria, int idMarca) {
        this.idCategoria = idCategoria;
        this.idMarca = idMarca;
    }

}

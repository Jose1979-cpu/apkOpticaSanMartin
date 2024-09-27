package com.jdiaz.apkopticasanmartin.bean;

public class Producto {
    private Integer id;
    private String Modelo;
    private String Genero;
    private String Varilla;
    private String Puente;
    private String Espejado;
    private String Polarizado;
    private String Estado;
    private Double Precio;
    private Integer Stock;

    public Producto(Integer id, String modelo, String genero, String varilla, String puente, String espejado,
                    String polarizado, String estado, Double precio, Integer stock) {
        
        this.id = id;
        Modelo = modelo;
        Genero = genero;
        Varilla = varilla;
        Puente = puente;
        Espejado = espejado;
        Polarizado = polarizado;
        Estado = estado;
        Precio = precio;
        Stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double precio) {
        Precio = precio;
    }

    public Integer getStock() {
        return Stock;
    }

    public void setStock(Integer stock) {
        Stock = stock;
    }
}

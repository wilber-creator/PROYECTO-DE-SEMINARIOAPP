package com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_4;

public class EsPedido {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String lugar_envio;
    private String precios;
    private String cantidad;
    private String pago_total;
    private String nombre;
    private String estado;
    private String cliente;

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar_envio() {
        return lugar_envio;
    }

    public void setLugar_envio(String lugar_envio) {
        this.lugar_envio = lugar_envio;
    }

    public String getPrecios() {
        return precios;
    }

    public void setPrecios(String precios) {
        this.precios = precios;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPago_total() {
        return pago_total;
    }

    public void setPago_total(String pago_total) {
        this.pago_total = pago_total;
    }
}

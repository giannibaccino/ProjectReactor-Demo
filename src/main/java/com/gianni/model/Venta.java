package com.gianni.model;

import java.time.LocalDateTime;

public class Venta {

    private Integer idVenta;
    private LocalDateTime fecha;

    public Venta(Integer idVenta, LocalDateTime fecha) {
        this.idVenta = idVenta;
        this.fecha = fecha;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "idVenta=" + idVenta +
                ", fecha=" + fecha +
                '}';
    }
}

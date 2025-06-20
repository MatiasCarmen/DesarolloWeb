package main.modelo;

import java.sql.Date;

public class Pedido {
    private int id;
    private String cliente;
    private Date fecha;
    private String estado;

    // Constructor para listar pedidos desde la BD
    public Pedido(int id, String cliente, Date fecha, String estado) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.estado = estado;
    }

    // Constructor para insertar pedidos sin ID
    public Pedido(String cliente, Date fecha, String estado) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.estado = estado;
    }

    // Getters y Setters
    public int getId() { return id; }
    public String getCliente() { return cliente; }
    public Date getFecha() { return fecha; }
    public String getEstado() { return estado; }

    public void setId(int id) { this.id = id; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public void setEstado(String estado) { this.estado = estado; }
}
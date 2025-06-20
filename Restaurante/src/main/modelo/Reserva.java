package main.modelo;

import java.sql.Date;
import java.sql.Time;

public class Reserva {
    private int id;
    private String nombreCliente;
    private Date fecha;
    private Time hora;
    private int numeroMesa;
    private String estado;

    public Reserva(String nombreCliente, Date fecha, Time hora, int numeroMesa, String estado) {
        this.nombreCliente = nombreCliente;
        this.fecha = fecha;
        this.hora = hora;
        this.numeroMesa = numeroMesa;
        this.estado = estado;
    }

    // Getters y Setters
    public int getId() { return id; }
    public String getNombreCliente() { return nombreCliente; }
    public Date getFecha() { return fecha; }
    public Time getHora() { return hora; }
    public int getNumeroMesa() { return numeroMesa; }
    public String getEstado() { return estado; }

    public void setId(int id) { this.id = id; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public void setHora(Time hora) { this.hora = hora; }
    public void setNumeroMesa(int numeroMesa) { this.numeroMesa = numeroMesa; }
    public void setEstado(String estado) { this.estado = estado; }
}
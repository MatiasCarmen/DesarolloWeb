package main.modelo;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "reservas")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "Reserva.insert",
                procedureName = "sp_insert_reserva",
                parameters = {
                        @StoredProcedureParameter(name = "p_nombre_cliente", mode = ParameterMode.IN, type = String.class),
                        @StoredProcedureParameter(name = "p_fecha", mode = ParameterMode.IN, type = Date.class),
                        @StoredProcedureParameter(name = "p_hora", mode = ParameterMode.IN, type = Time.class),
                        @StoredProcedureParameter(name = "p_numero_mesa", mode = ParameterMode.IN, type = Integer.class),
                        @StoredProcedureParameter(name = "p_estado", mode = ParameterMode.IN, type = String.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "Reserva.list",
                procedureName = "sp_list_reservas",
                resultClasses = Reserva.class
        )
})
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombreCliente;
    private Date fecha;
    private Time hora;
    private int numeroMesa;
    private String estado;

    public Reserva() {
    }

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
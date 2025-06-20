package main.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
@NamedStoredProcedureQuery(
        name = "Usuario.login",
        procedureName = "sp_login",
        resultClasses = Usuario.class,
        parameters = {
                @StoredProcedureParameter(name = "p_username", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "p_password", mode = ParameterMode.IN, type = String.class)
        }
)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private String rol;

    public Usuario() {
    }

    public Usuario(int id, String username, String password, String rol) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRol() { return rol; }

    public void setId(int id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setRol(String rol) { this.rol = rol; }
}
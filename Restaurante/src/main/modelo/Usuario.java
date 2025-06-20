package main.modelo;

public class Usuario {
    private int id;
    private String username;
    private String password;
    private String rol;
    // Datos adicionales para validaciones
    private String dni;
    private String telefono;
    private String correo;

    public Usuario(int id, String username, String password, String rol,
                   String dni, String telefono, String correo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
    }

    // Constructor usado para login (sin datos adicionales)
    public Usuario(int id, String username, String rol) {
        this.id = id;
        this.username = username;
        this.rol = rol;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRol() { return rol; }
    public String getDni() { return dni; }
    public String getTelefono() { return telefono; }
    public String getCorreo() { return correo; }

    public void setId(int id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setRol(String rol) { this.rol = rol; }
    public void setDni(String dni) { this.dni = dni; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setCorreo(String correo) { this.correo = correo; }
}
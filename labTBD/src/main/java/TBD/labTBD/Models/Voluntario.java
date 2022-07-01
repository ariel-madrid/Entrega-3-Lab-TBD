package TBD.labTBD.Models;

public class Voluntario 
{
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;
    private String direccion;
    private String rut;
    private String estado;
    private float latitud;
    private float longitud;

    //Getters
    public int getId()
    {
        return this.id;
    }
    public float getLatitud() {
        return latitud;
    }
    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }
    public float getLongitud() {
        return longitud;
    }
    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }
    public String getNombre()
    {
        return this.nombre;
    }

    public String getApellido()
    {
        return this.apellido;
    }

    public String getContraseña()
    {
        return this.contrasena;
    }

    public String getCorreo()
    {
        return this.correo;
    }

    public String getDireccion()
    {
        return this.direccion;
    }

    public String getRut()
    {
        return this.rut;
    }

    public String getEstado()
    {
        return this.estado;
    }

    //Setters
    public void setId(int id)
    {
        this.id = id;
    }
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }
    
    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

    public void setContraseña(String contrasena)
    {
        this.contrasena = contrasena;
    }

    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    public void setRut(String rut)
    {
        this.rut = rut;
    }

    public void setEstado(String estado)
    {
        this.estado = estado;
    }
}











package TBD.labTBD.Models;

import java.sql.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "Emergencia")
public class Emergencia {
    private int id;
    private String nombre;
    private String descripcion;
    private int id_coordinador;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fecha;
    private String ubicacion;
    private float latitud;
    private float longitud;
    
    //Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getId_coordinador() {
        return id_coordinador;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public float getLatitud() {
        return latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setId_coordinador(int id_coordinador) {
        this.id_coordinador = id_coordinador;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

}

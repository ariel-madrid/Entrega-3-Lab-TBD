package TBD.labTBD.Models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "Tarea")
public class Tarea {
    @Id
    private int id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechainicio;
    private String estado, nombre, descripcion;
    private String ubicacion;
    private String listaHabilidades;
    private int emergencia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmergencia()
    {
        return emergencia;
    }

    public void setIdEmergencia(int emergencia)
    {
        this.emergencia = emergencia;
    }

    public Date getFechaInicio() {
        return fechainicio;
    }

    public void setFechaInicio(Date fechainicio) {
        this.fechainicio = fechainicio;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getListaHabilidades() {
        return listaHabilidades;
    }

    public void setListaHabilidades(String listaHabilidades) {
        this.listaHabilidades = listaHabilidades;
    }

}

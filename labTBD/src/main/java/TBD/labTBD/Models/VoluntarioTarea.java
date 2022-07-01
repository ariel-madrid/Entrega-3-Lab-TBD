package TBD.labTBD.Models;

public class VoluntarioTarea 
{
    private int id;
    private int idTarea;
    private int idVoluntario;
    private int idEmergencia;

    public int getId() {
        return id;
    }
    public int getIdEmergencia() {
        return idEmergencia;
    }
    public void setIdEmergencia(int idEmergencia) {
        this.idEmergencia = idEmergencia;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdTarea() {
        return idTarea;
    }
    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }
    public int getIdVoluntario() {
        return idVoluntario;
    }
    public void setIdVoluntario(int idVoluntario) {
        this.idVoluntario = idVoluntario;
    }

    
}
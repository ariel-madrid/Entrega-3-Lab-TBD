package TBD.labTBD.Repositories;
import java.util.List;

import TBD.labTBD.Models.VoluntarioTarea;

//Interfaz que define los metodos del CRUD
public interface RepositorieVoluntarioTarea{
    public List<VoluntarioTarea> getAll(); // read
    public void update(VoluntarioTarea tarea);
    public void delete(int id);
    public VoluntarioTarea createVoluntarioTarea(VoluntarioTarea tarea);
    public VoluntarioTarea getById(int id);
}

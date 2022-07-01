package TBD.labTBD.Services;

import TBD.labTBD.Models.*;
import TBD.labTBD.Repositories.*;
import java.util.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ServiceVoluntarioTarea {
    private final RepositorieVoluntarioTarea repositorieVoluntarioTarea;

    ServiceVoluntarioTarea(RepositorieVoluntarioTarea RepositorieVoluntarioTarea) {
        this.repositorieVoluntarioTarea = RepositorieVoluntarioTarea;
    }

    // Metodo leer
    @RequestMapping(value = "/voluntario-tarea", method = RequestMethod.GET)
    public List<VoluntarioTarea> getAllVoluntarioTarea() {
        return repositorieVoluntarioTarea.getAll();
    }

    // Metodo crear
    @RequestMapping(value = "/new-voluntario-tarea", method = RequestMethod.POST)
    public VoluntarioTarea crearVoluntarioTarea(@RequestBody VoluntarioTarea VoluntarioTarea) {
        return repositorieVoluntarioTarea.createVoluntarioTarea(VoluntarioTarea);
    }

    /*// Metodo actualizar
    @RequestMapping(value = "/voluntario-tarea/actualizar", method = RequestMethod.PUT)
    public void actualizarVoluntarioTarea(@RequestBody VoluntarioTarea VoluntarioTarea) {
        repositorieVoluntarioTarea.update(VoluntarioTarea);
    }*/

    /*// Metodo eliminar
    @DeleteMapping(value = "/voluntario-tarea/eliminar/{id}")
    public void eliminar(@PathVariable(value = "id") int id) {
        repositorieVoluntarioTarea.delete(id);
    }*/

    /*// Metodo eliminar
    @RequestMapping(value = "/voluntario-tarea/{id}", method = RequestMethod.GET)
    public VoluntarioTarea getById(@PathVariable int id) {
        return repositorieVoluntarioTarea.getById(id);
    }*/
}

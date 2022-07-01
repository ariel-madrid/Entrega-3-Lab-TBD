package TBD.labTBD.ServicesMongoDB;

import TBD.labTBD.Models.*;
import java.util.*;
import TBD.labTBD.RepositoriesMongoDB.TareaRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TareaService
 {
    private final TareaRepository repositorieTarea;

    public TareaService(TareaRepository repositorieTarea) {
        this.repositorieTarea = repositorieTarea;
    }

    public TareaRepository getrepositorieTarea() {
        return repositorieTarea;
    }

    // Metodo leer
    @RequestMapping(value = "/tareasMongoDB", method = RequestMethod.GET)
    public List<Tarea> getAllTarea() {
        return repositorieTarea.findAll();
    }

    // Metodo actualizar
    @RequestMapping(value = "/tareas/actualizarMongoDB", method = RequestMethod.PUT)
    public void actualizarTarea(@RequestBody Tarea tarea) {
        repositorieTarea.save(tarea);
    }

    // Metodo crear
    @RequestMapping(value = "/tareaNuevaMongoDB", method = RequestMethod.POST)
    public Tarea crearTarea(@RequestBody Tarea tarea) {
        return repositorieTarea.save(tarea);
    }

    // Metodo eliminar
    @DeleteMapping(value = "/tarea/eliminarMongoDB/{id}")
    public void eliminar(@PathVariable(value = "id") int id) {
        repositorieTarea.deleteById(id);;
    }

}
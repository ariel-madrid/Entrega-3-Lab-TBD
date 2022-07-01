package TBD.labTBD.ServicesMongoDB;

import TBD.labTBD.Models.*;
import java.util.*;
import TBD.labTBD.RepositoriesMongoDB.EmergenciaRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class EmergenciaService
 {
    private final EmergenciaRepository repositorieEmergencia;

    public EmergenciaService(EmergenciaRepository repositorieEmergencia) {
        this.repositorieEmergencia = repositorieEmergencia;
    }

    public EmergenciaRepository getRepositorieEmergencia() {
        return repositorieEmergencia;
    }

    // Metodo leer
    @RequestMapping(value = "/emergenciasMongoDB", method = RequestMethod.GET)
    public List<Emergencia> getAllEmergencia() {
        return repositorieEmergencia.findAll();
    }

    // Metodo actualizar
    @RequestMapping(value = "/emergencias/actualizarMongoDB", method = RequestMethod.PUT)
    public void actualizarEmergencia(@RequestBody Emergencia emergencia) {
        repositorieEmergencia.save(emergencia);
    }

    // Metodo crear
    @RequestMapping(value = "/nuevaEmergenciaMongoDB", method = RequestMethod.POST)
    public Emergencia crearEmergencia(@RequestBody Emergencia emergencia) {
        return repositorieEmergencia.save(emergencia);
    }

    // Metodo eliminar
    @DeleteMapping(value = "/emergencias/eliminarMongoDB/{id}")
    public void eliminar(@PathVariable(value = "id") int id) {
        repositorieEmergencia.deleteById(id);;
    }

    // Metodo eliminar
    /*@RequestMapping(value = "/emergencia/{id}", method = RequestMethod.GET)
    public Emergencia getById(@PathVariable int id) {
        return repositorieEmergencia.findById(id);
    }*/
}
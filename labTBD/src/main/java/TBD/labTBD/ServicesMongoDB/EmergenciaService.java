package TBD.labTBD.ServicesMongoDB;

import TBD.labTBD.Models.*;
import java.util.*;
import TBD.labTBD.RepositoriesMongoDB.EmergenciaRepository;

import org.bson.Document;
import org.bson.json.JsonWriterSettings;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Consumer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.Block;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

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


    //Tareas de una emergencia -> Finalizado Siuuuu
    @RequestMapping(value = "/tareasemergenciasMongoDB", method = RequestMethod.GET)
    public List<Document> tareasEmergencia()
    {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017" );
        MongoDatabase database = mongoClient.getDatabase("Ayni");
        MongoCollection<Document> collection = database.getCollection("Emergencia");

        AggregateIterable<Document> output =  collection.aggregate(Arrays.asList(new Document("$lookup", 
            new Document("from", "Tarea")
                    .append("localField", "_id")
                    .append("foreignField", "emergencia")
                    .append("as", "Tareas"))));
        
        List<Document> events = new ArrayList<Document>();

        for (Document dbObject : output)
        {
            System.out.println(events.add(dbObject));
        }

        return events;
    }
}
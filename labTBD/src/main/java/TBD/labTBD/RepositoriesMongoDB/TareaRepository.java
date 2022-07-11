package TBD.labTBD.RepositoriesMongoDB;

import org.springframework.data.mongodb.repository.MongoRepository;

import TBD.labTBD.Models.Tarea;
public interface TareaRepository extends MongoRepository<Tarea, Integer>{
}


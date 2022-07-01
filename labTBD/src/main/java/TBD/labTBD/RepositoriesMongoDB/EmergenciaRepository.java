package TBD.labTBD.RepositoriesMongoDB;

import org.springframework.data.mongodb.repository.MongoRepository;

import TBD.labTBD.Models.Emergencia;

public interface EmergenciaRepository extends MongoRepository<Emergencia, Integer>{
    
}

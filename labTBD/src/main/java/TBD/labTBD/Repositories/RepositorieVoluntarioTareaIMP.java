package TBD.labTBD.Repositories;

import java.util.List;
import TBD.labTBD.Models.VoluntarioTarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository
public class RepositorieVoluntarioTareaIMP implements RepositorieVoluntarioTarea
{
    @Autowired 
    private Sql2o sql2o;
    
    @Override
    public VoluntarioTarea createVoluntarioTarea(VoluntarioTarea tarea) {
        String sql = "INSERT INTO voluntariotarea (id, idTarea, idVoluntario, idEmergencia) VALUES(:id,:idTarea,:idVoluntario,:idEmergencia)";
        
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql,true)
            .addParameter("id", tarea.getId())
            .addParameter("idTarea", tarea.getIdTarea())
            .addParameter("idVoluntario", tarea.getIdVoluntario())
            .addParameter("idEmergencia", tarea.getIdEmergencia())
            .executeUpdate().getKey();

            tarea.setId(id);    
            return tarea;
        }
        catch (Exception e) {
            System.out.println(e.getCause() + e.getLocalizedMessage() + "\n");
        }
        return null;
    }

    @Override
    public List<VoluntarioTarea> getAll() {
        String sql = "SELECT * FROM voluntariotarea";
        try(Connection conn = sql2o.open()){
            return (List<VoluntarioTarea>) conn.createQuery(sql).executeAndFetch(VoluntarioTarea.class);   
        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getLocalizedMessage()+" Error en la conexion \n");
        }
        return null;
    }

    
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM voluntario WHERE id="+id;
        try(Connection conn = sql2o.open())
        {
            conn.createQuery(sql).executeUpdate();
        }catch(Exception e)
        {
            System.out.println(e.getMessage() + e.getLocalizedMessage()+" Error al actualizar\n");
        }
        
    }
    @Override
    public VoluntarioTarea getById(int id) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public void update(VoluntarioTarea tarea) {
        //Actualizar tabla
    }
}

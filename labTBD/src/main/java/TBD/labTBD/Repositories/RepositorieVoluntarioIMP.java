package TBD.labTBD.Repositories;

import java.util.List;
import TBD.labTBD.Models.Voluntario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository
public class RepositorieVoluntarioIMP implements RepositorieVoluntario {
    @Autowired
    private Sql2o sql2o;

    // OBTENER A TODOS LOS VOLUNTARIOS (Metodo GET)
    @Override
    public List<Voluntario> getAll() {
        String sql = "SELECT id, nombre, apellido, contrasena, correo, latitud, longitud, rut, estado FROM voluntario";
        try (Connection conn = sql2o.open()) {
            return (List<Voluntario>) conn.createQuery(sql).executeAndFetch(Voluntario.class);
        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getLocalizedMessage() + " Error en la conexion \n");
        }
        return null;
    }

    // ACTUALIZAR A UN VOLUNTARIO (Metodo UPDATE)
    @Override
    public void update(Voluntario voluntario) {
        String sql = "UPDATE voluntario SET nombre=" + "'" +
                voluntario.getNombre() + "'" +
                ", apellido=" + "'" +
                voluntario.getApellido() + "'" +
                ", correo=" + "'" +
                voluntario.getCorreo() + "'" +
                ", contrasena=" + "'" +
                voluntario.getContraseña() + "'" +
                ", direccion=" + "'" +
                voluntario.getDireccion() + "'" +
                ", rut=" + "'" +
                voluntario.getRut() + "'" +
                ", estado=" + "'" +
                voluntario.getEstado() + "'" +
                " WHERE id = " +
                voluntario.getId();
        try (Connection conn = sql2o.open()) {

            conn.createQuery(sql).executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getLocalizedMessage() + " Error al actualizar datos del voluntario\n");
        }
    }

    // ELIMINAR A UN VOLUNTARIO (Metodo UPDATE)
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM voluntario WHERE id=" + id;
        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql).executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getLocalizedMessage() + " error al actualizar\n");
        }

    }

    // CREAR VOLUNTARIO (Metodo CREATE)

    // La verificacion de si existe el voluntario se realiza en el front
    @Override
    public Voluntario createVoluntario(Voluntario voluntario) {
        String sql = "INSERT INTO voluntario (id, nombre, apellido, correo,contrasena,direccion,rut,estado,latitud,longitud,location) "
                +
                "VALUES(:id, :nombre, :apellido, :correo,:contrasena,:direccion,:rut,:estado,:latitud,:longitud, ST_GeomFromText(:point, 4326))";

        String point = "POINT(" + voluntario.getLatitud() + " " + voluntario.getLongitud() + ")";

        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .addParameter("id", voluntario.getId())
                    .addParameter("nombre", voluntario.getNombre())
                    .addParameter("apellido", voluntario.getApellido())
                    .addParameter("correo", voluntario.getCorreo())
                    .addParameter("contrasena", voluntario.getContraseña())
                    .addParameter("direccion", voluntario.getDireccion())
                    .addParameter("rut", voluntario.getRut())
                    .addParameter("estado", voluntario.getEstado())
                    .addParameter("latitud", voluntario.getLatitud())
                    .addParameter("longitud", voluntario.getLongitud())
                    .addParameter("point", point)
                    .executeUpdate().getKey();

            voluntario.setId(id);
            return voluntario;

        } catch (Exception e) {
            System.out.println(e.getCause() + e.getLocalizedMessage() + "\n");

        }
        return null;
    }

    // OBTENER A UN VOLUNTARIO POR ID (Metodo GET)
    @Override
    public Voluntario getById(int id) {
        String sql = "SELECT * FROM voluntario WHERE id=" + Integer.toString(id);

        try (Connection conn = sql2o.open()) {
            List<Voluntario> l = conn.createQuery(sql).executeAndFetch(Voluntario.class);
            return l.get(0);

        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getLocalizedMessage() + "Error al realizar la peticion\n");
        }
        return null;
    }

    @Override
    public List<Voluntario> getVoluntariosFromEmergencia(int emergenciaId) {

        final String voluntarioQuery = "SELECT v.id, v.nombre, v.apellido, v.contrasena, v.correo, v.latitud, v.longitud, v.rut, v.estado "+
        "FROM voluntario as v, voluntariotarea as vt "+
        "WHERE vt.idemergencia = "+emergenciaId+" AND v.id=vt.idvoluntario "+
        "GROUP BY v.id, v.nombre, v.apellido, v.contrasena, v.correo, v.latitud, v.longitud, v.rut, v.estado;";
        try (Connection conn = sql2o.open()) {
            return conn.createQuery(voluntarioQuery)
                    .executeAndFetch(Voluntario.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

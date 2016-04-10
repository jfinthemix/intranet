package proyecto_uoct.mantenedorSubsistemas.model;
import java.util.*;
import javax.ejb.*;

public interface MantenedorSubsistemasLocal extends EJBLocalObject {
    public  List buscarSubsistema() throws Exception;

    public int modificarSubsistema(int id_subsistema, String nombre,
                                   int id_sistema) throws Exception;

    public int eliminarSubsistema(int id_subsistema) throws Exception;

    public int ingresarSubsistema(String nombre, int id_sistema) throws Exception;

    public List lista_sistema() throws Exception;

    public int idPerfil(Integer ses_idusu) throws Exception;

    public String sePuedeEliminar(int id_subsistema) throws Exception;

}

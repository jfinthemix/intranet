package proyecto_uoct.mantenedorSistemas.model;
import java.util.*;
import javax.ejb.*;

public interface MantenedorSistemasLocal extends EJBLocalObject {
    public  List buscarSistema() throws Exception;

    public int modificarSistema(int id_sistema, String nombre) throws Exception;

    public int eliminarSistema(int id_sistema) throws Exception;

    public int ingresarSistema(String nombre) throws Exception;

    public String sePuedeEliminar(int id_sistema) throws Exception;

    public int idPerfil(Integer ses_idusu) throws Exception;
    ;

}

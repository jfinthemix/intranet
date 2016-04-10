package proyecto_uoct.mantenedorDispositivos.model;

import javax.ejb.EJBLocalObject;
import java.util.List;

public interface MantenedorDispositivosLocal extends EJBLocalObject {
    public List buscarDispositivo(String subsistema) throws Exception;

    public int modificarDispositivo(int id_dispositivo, String nombre,
                                    String descripcion, int id_subsistema,
                                    int id_empresa_mantenedora,
                                    int id_tipo_dispositivo) throws Exception;

    public int eliminarDispositivo(int id_dispositivo) throws Exception;

    public int ingresarDispositivo(int id_subsistema, String nombre,
                                   String descripcion,
                                   int id_empresa_mantenedora,
                                   int id_tipo_dispositivo) throws Exception;

    public List lista_sistema() throws Exception;

    public List lista_subsistema() throws Exception;

    public List lista_empresa() throws Exception;

    public List lista_tipo_dispositivo() throws Exception;

    public int idPerfil(Integer ses_idusu) throws Exception;

    public String sePuedeEliminar(int id_dispositivo) throws Exception;

    public String nombreSubsistema(int id) throws Exception;;


}

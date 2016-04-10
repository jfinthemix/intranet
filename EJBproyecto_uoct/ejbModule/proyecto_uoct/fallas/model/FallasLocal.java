package proyecto_uoct.fallas.model;
import java.util.*;//para la List
import javax.ejb.*;

public interface FallasLocal extends EJBLocalObject {
    public List buscar_falla(Integer lista_sistema, Integer lista_subsistema,
                             Integer lista_dispositivo, String fecha_ingreso1,
                             String fecha_ingreso2, Integer estado) throws Exception;

    public List ver_detalle_falla(Integer id_falla, String evento) throws Exception;

    public int ingresar_falla(Integer n_id_dispositivo, Integer n_id_usu_inicia,
                              Integer n_id_usu_cierra, String titulo,
                              String problema, String n_fecha_ingreso,
                              String n_fecha_cierre, Integer n_estado,
                              String comentario) throws Exception;

    public void ingresar_detalle_falla(Integer id_falla, Integer n_id_usu_actualiza,  String comentario, String fecha_actualiza) throws Exception;

    public void ingresar_detalle_falla_y_cierre(Integer id_falla, Integer n_id_usu_actualiza, String comentario, String fecha_actualiza) throws Exception;

    public void ingresar_detalle_falla_y_solicitarcierre(Integer id_falla, Integer n_id_usu_actualiza, String comentario,String fecha_actualiza) throws Exception;

    public int ingresar_comentario(Integer n_id_dispositivo,Integer n_id_usu_actualiza,String n_fecha_actualiza, String comentario) throws Exception;

    public void eliminar_falla(Integer id_falla) throws Exception;

    public String fecha_actual() throws Exception;

    public List lista_subsistema(Integer id_sistema) throws Exception;

    public List lista_dispositivo(Integer id_sistema, Integer id_subsistema) throws Exception;

    public List lista_dispositivo2(Integer id_sistema, Integer id_subsistema) throws Exception;

    public String mailDispositivo(Integer id_dispositivo, int id_empresa_mantenedora) throws Exception;

    public int empresaMantenedora(Integer id_dispositivo) throws Exception;

    public int idDispositivo(Integer id_falla) throws Exception;

    public int tipoDispositivo(Integer id_dispositivo) throws Exception;

    public int idPerfil(Integer ses_idusu) throws Exception;

    public String nombreUsuario(Integer ses_idusu) throws Exception;

    public String nomEmpresaMantenedora(Integer id_empresa_mantenedora) throws Exception;

    public List consultarReporte(Integer lista_sistema, Integer lista_subsistema, Integer lista_dispositivo, String fecha_ingreso1, String fecha_ingreso2)throws Exception;

    public List lista_sistema() throws Exception;

    public List SistemaSubsistema() throws Exception;

    public List SistemaSubsistemaDispositivo() throws Exception;

    public String fecha_hora_actual() throws Exception;



}



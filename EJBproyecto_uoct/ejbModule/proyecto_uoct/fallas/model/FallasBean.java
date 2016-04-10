package proyecto_uoct.fallas.model;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.CreateException;
import proyecto_uoct.dao.FallaDAO;
import proyecto_uoct.fallas.VO.FallaVO;
import java.util.LinkedList;
import java.util.List;


public class FallasBean implements SessionBean {
    SessionContext sessionContext;
    public void ejbCreate() throws CreateException {
    }

    public void ejbRemove() {
    }

    public void ejbActivate() {
    }

    public void ejbPassivate() {
    }

    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    public List buscar_falla(Integer lista_sistema,
                             Integer lista_subsistema,
                             Integer lista_dispositivo,
                             String fecha_ingreso1,
                             String fecha_ingreso2,
                             Integer estado) throws Exception{
        try {
            FallaDAO dao = FallaDAO.getInstance();
            return dao.buscar_falla(lista_sistema,
                                    lista_subsistema,
                                    lista_dispositivo,
                                    fecha_ingreso1,
                                    fecha_ingreso2,
                                    estado);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo realizar la búsqueda de fallas");
            throw e;
        }
    }
    public List ver_detalle_falla(Integer id_falla, String evento) throws Exception{
        try {
            FallaDAO dao = FallaDAO.getInstance();
            return dao.ver_detalle_falla(id_falla, evento);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo realizar la búsqueda del detalle de falla");
            throw e;
        }
    }
    public int ingresar_falla(Integer n_id_dispositivo, Integer n_id_usu_inicia,
                              Integer n_id_usu_cierra, String titulo,
                              String problema, String n_fecha_ingreso,
                              String n_fecha_cierre, Integer n_estado,
                              String comentario) throws Exception{
        FallaDAO dao = FallaDAO.getInstance();
        try {
            return dao.ingresar_falla(n_id_dispositivo,
                               n_id_usu_inicia,
                               n_id_usu_cierra,
                               titulo,
                               problema,
                               n_fecha_ingreso,
                               n_fecha_cierre,
                               n_estado,
                               comentario);
        }catch (Exception e){
            e.printStackTrace();
            System.out.print("No se pudo ingresar la falla");
            throw e;
        }
    }
    public void ingresar_detalle_falla(Integer id_falla,
                                       Integer n_id_usu_actualiza,
                                       String comentario,
                                       String fecha_actualiza) throws Exception{
        FallaDAO dao = FallaDAO.getInstance();
        try {
            dao.ingresar_detalle_falla(id_falla,
                                       n_id_usu_actualiza,
                                       comentario,
                                       fecha_actualiza);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo ingresar el detalle de falla");
            throw e;
        }
    }
    public void ingresar_detalle_falla_y_cierre(Integer id_falla,
                                                Integer n_id_usu_actualiza,
                                                String comentario,
                                                String fecha_actualiza) throws Exception{
        FallaDAO dao = FallaDAO.getInstance();
        try {
            dao.ingresar_detalle_falla_y_cierre(id_falla,
                                                n_id_usu_actualiza,
                                                comentario,
                                                fecha_actualiza);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo ingresar el detalle de falla y el cierre");
            throw e;
        }
    }

    public void ingresar_detalle_falla_y_solicitarcierre(
            Integer id_falla,
            Integer n_id_usu_actualiza,
            String comentario,
            String fecha_actualiza) throws Exception{

        FallaDAO dao = FallaDAO.getInstance();
        try{
            dao.ingresar_detalle_falla_y_solicitarcierre(
                    id_falla,
                    n_id_usu_actualiza,
                    comentario,
                    fecha_actualiza);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo ingresar el detalle de falla y el cierre");
            throw e;
        }
    }

    public int ingresar_comentario(Integer n_id_dispositivo,
                                    Integer n_id_usu_actualiza,
                                    String n_fecha_actualiza,
                                    String comentario) throws Exception{
        FallaDAO dao = FallaDAO.getInstance();
        try {
            return dao.ingresar_comentario(n_id_dispositivo,
                                    n_id_usu_actualiza,
                                    n_fecha_actualiza,
                                    comentario);
        }catch (Exception e){
            e.printStackTrace();
            System.out.print("No se pudo ingresar el comentario");
            throw e;
        }
    }

    public void eliminar_falla(Integer id_falla)  throws Exception{
        FallaDAO dao = FallaDAO.getInstance();
        try {
            dao.eliminar_falla(id_falla);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo eliminar la falla");
            throw e;
        }
    }

    public List lista_sistema() throws Exception {
        try {
            FallaDAO dao = FallaDAO.getInstance();
            return dao.lista_sistema();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo realizar la búsqueda de sistemas");
            throw e;
        }
    }

    public List SistemaSubsistema() throws Exception {
        try {
            FallaDAO dao = FallaDAO.getInstance();
            return dao.SistemaSubsistema();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo realizar la búsqueda de sistema-subsistema");
            throw e;
        }
    }

    public List SistemaSubsistemaDispositivo() throws Exception {
        try {
            FallaDAO dao = FallaDAO.getInstance();
            return dao.SistemaSubsistemaDispositivo();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo realizar la búsqueda de sistema-subsistema-dispositivo");
            throw e;
        }
    }
    public List lista_subsistema(Integer id_sistema) throws Exception {
        try {
            FallaDAO dao = FallaDAO.getInstance();
            return dao.lista_subsistema(id_sistema);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo realizar la búsqueda de subsistemas");
            throw e;
        }
    }

    public List lista_dispositivo(Integer id_sistema,
                                  Integer id_subsistema) throws Exception{
        try {
            FallaDAO dao = FallaDAO.getInstance();
            return dao.lista_dispositivo(id_sistema,
                                         id_subsistema);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo realizar la búsqueda de dispositivos");
            throw e;
        }
    }

    public List  lista_dispositivo2(Integer id_sistema,
                                    Integer id_subsistema) throws Exception{
        try {
            FallaDAO dao = FallaDAO.getInstance();
            return dao.lista_dispositivo2(id_sistema,
                                         id_subsistema);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo realizar la búsqueda de dispositivos");
            throw e;
        }
    }




    public String fecha_hora_actual() throws Exception{
        FallaDAO dao = FallaDAO.getInstance();
        try {
            return dao.fecha_hora_actual();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo localizar la hora actual");
            throw e;
        }
    }

    public String fecha_actual() throws Exception{
        FallaDAO dao = FallaDAO.getInstance();
        try {
            return dao.fecha_actual();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo localizar la hora actual");
            throw e;
        }
    }

    public String mailDispositivo(Integer id_dispositivo, int id_empresa_mantenedora) throws Exception{
        FallaDAO dao = FallaDAO.getInstance();
        try {
            return dao.mailDispositivo(id_dispositivo, id_empresa_mantenedora);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo localizar el mail del dispositivo");
            throw e;
        }
    }

    public int empresaMantenedora(Integer id_dispositivo) throws Exception{
        FallaDAO dao = FallaDAO.getInstance();
        try {
            return dao.empresaMantenedora(id_dispositivo);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo localizar la empresa mantenedora");
            throw e;
        }
    }

    public int idDispositivo(Integer id_falla)throws Exception{
        FallaDAO dao = FallaDAO.getInstance();
        try {
            return dao.idDispositivo(id_falla);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo localizar el id_dispositivo [FallasBean]");
            throw e;
        }
    }

    public int tipoDispositivo(Integer id_dispositivo) throws Exception{
        FallaDAO dao = FallaDAO.getInstance();
        try {
            return dao.tipoDispositivo(id_dispositivo);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo localizar el tipoDispositivo [FallasBean]");
            throw e;
        }
    }

    public int idPerfil(Integer ses_idusu) throws Exception{
        FallaDAO dao = FallaDAO.getInstance();
        try {
            return dao.idPerfil(ses_idusu);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo localizar el idPerfil [FallasBean]");
            throw e;
        }
    }

    public String  nombreUsuario(Integer ses_idusu) throws Exception{
        FallaDAO dao = FallaDAO.getInstance();
         try {
             return dao.nombreUsuario(ses_idusu);
         }catch (Exception e) {
             e.printStackTrace();
             System.out.print("No se pudo localizar el nombre de usuario");
             throw e;
         }
    }

    public String  nomEmpresaMantenedora(Integer id_empresa_mantenedora) throws Exception{
        FallaDAO dao = FallaDAO.getInstance();
         try {
             return dao.nomEmpresaMantenedora(id_empresa_mantenedora);
         }catch (Exception e) {
             e.printStackTrace();
             System.out.print("No se pudo localizar el nombre de la empresa mantenedora");
             throw e;
         }
    }

    public List  consultarReporte(Integer lista_sistema,
                                 Integer lista_subsistema,
                                 Integer lista_dispositivo,
                                 String fecha_ingreso1, String fecha_ingreso2)throws Exception {
       try {
           FallaDAO dao = FallaDAO.getInstance();
           return dao.consultarReporte(lista_sistema,
                                   lista_subsistema,
                                   lista_dispositivo,
                                   fecha_ingreso1,
                                   fecha_ingreso2);
       }catch (Exception e) {
           e.printStackTrace();
           System.out.print("No se pudo realizar la consulta por el reporte");
           throw e;
       }

    }


}

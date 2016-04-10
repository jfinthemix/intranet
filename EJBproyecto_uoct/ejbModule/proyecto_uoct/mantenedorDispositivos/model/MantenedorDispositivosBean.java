package proyecto_uoct.mantenedorDispositivos.model;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.CreateException;
import proyecto_uoct.dao.MantDispositivoDAO;
import proyecto_uoct.mantenedorDispositivos.VO.DispositivoVO;
import java.util.LinkedList;
import java.util.List;



public class MantenedorDispositivosBean implements SessionBean {
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

    public List  buscarDispositivo(String subsistema) throws Exception{
        try {
            MantDispositivoDAO dao = MantDispositivoDAO.getInstance();
            return dao.buscarDispositivo(subsistema);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo realizar la búsqueda de dispositivos");
            throw e;
        }
    }

    public int  modificarDispositivo(int id_dispositivo, String nombre,
                                    String descripcion, int id_subsistema,
                                    int id_empresa_mantenedora,
                                    int id_tipo_dispositivo)  throws Exception {
       try {
          MantDispositivoDAO dao = MantDispositivoDAO.getInstance();
          return dao.modificarDispositivo(id_dispositivo, nombre, descripcion, id_subsistema, id_empresa_mantenedora, id_tipo_dispositivo);
      } catch (Exception e) {
          e.printStackTrace();
          System.out.print("No se pudo realizar la modificación del dispositivo");
          throw e;
      }

    }

    public int eliminarDispositivo(int id_dispositivo)  throws Exception{
        try {
          MantDispositivoDAO dao = MantDispositivoDAO.getInstance();
          return dao.eliminarDispositivo(id_dispositivo);
      } catch (Exception e) {
          e.printStackTrace();
          System.out.print("No se pudo realizar la eliminación del dispositivo");
          throw e;
      }

    }

    public int  ingresarDispositivo(int id_subsistema, String nombre,
                                   String descripcion,
                                   int id_empresa_mantenedora,
                                   int id_tipo_dispositivo)  throws Exception{
       try {
            MantDispositivoDAO dao = MantDispositivoDAO.getInstance();
            return dao.ingresarDispositivo(id_subsistema, nombre, descripcion, id_empresa_mantenedora, id_tipo_dispositivo);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo realizar el ingreso del nuevo dispositivo");
            throw e;
        }

    }

    public List  lista_sistema()  throws Exception{
        try {
            MantDispositivoDAO dao = MantDispositivoDAO.getInstance();
            return dao.lista_sistema();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo realizar la búsqueda de sistemas");
            throw e;
        }
    }

    public List  lista_subsistema()  throws Exception{
        try {
            MantDispositivoDAO dao = MantDispositivoDAO.getInstance();
            return dao.lista_subsistema();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo realizar la búsqueda de subsistemas");
            throw e;
        }
    }

    public List lista_empresa()  throws Exception{
        try {
            MantDispositivoDAO dao = MantDispositivoDAO.getInstance();
            return dao.lista_empresa();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo realizar la búsqueda de empresas");
            throw e;
        }
    }

    public List  lista_tipo_dispositivo() throws Exception{
        try {
            MantDispositivoDAO dao = MantDispositivoDAO.getInstance();
            return dao.lista_tipo_dispositivo();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo realizar la búsqueda de tipos de dispositivos");
            throw e;
        }

    }

    public int  idPerfil(Integer ses_idusu) throws Exception {
        MantDispositivoDAO dao = MantDispositivoDAO.getInstance();
        try {
            return dao.idPerfil(ses_idusu);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo localizar el idPerfil [FallasBean]");
            throw e;
        }

    }

    public String  sePuedeEliminar(int id_dispositivo) throws Exception  {
        try {
         MantDispositivoDAO dao = MantDispositivoDAO.getInstance();
         return dao.sePuedeEliminar(id_dispositivo);
     } catch (Exception e) {
         e.printStackTrace();
         System.out.print("No se pudo realizar la consulta para saber si se puede o no eliminar el dispositivo");
         throw e;
     }

    }

    public String  nombreSubsistema(int id)  throws Exception  {
        try {
         MantDispositivoDAO dao = MantDispositivoDAO.getInstance();
         return dao.nombreSubsistema(id);
     } catch (Exception e) {
         e.printStackTrace();
         System.out.print("No se pudo realizar la consulta para saber el nombre del subsistema");
         throw e;
     }
    }
}

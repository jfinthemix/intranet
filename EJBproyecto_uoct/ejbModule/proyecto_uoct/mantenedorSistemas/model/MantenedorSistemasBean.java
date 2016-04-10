package proyecto_uoct.mantenedorSistemas.model;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.CreateException;
import proyecto_uoct.dao.MantSistemaDAO;
import proyecto_uoct.mantenedorSistemas.VO.SistemaVO;
import java.util.LinkedList;
import java.util.List;

public class MantenedorSistemasBean implements SessionBean {
    SessionContext sessionContext;
    public void ejbCreate() throws CreateException {}

    public void ejbRemove() {}

    public void ejbActivate() {}

    public void ejbPassivate() {}

    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    public MantenedorSistemasBean() {
    }

    public List buscarSistema() throws Exception {
        try {
            MantSistemaDAO dao = MantSistemaDAO.getInstance();
            return dao.buscarSistema();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo realizar la búsqueda de sistemas");
            throw e;
        }
    }

    public int modificarSistema(int id_sistema, String nombre) throws Exception {
        try {
            MantSistemaDAO dao = MantSistemaDAO.getInstance();
            return dao.modificarSistema(id_sistema, nombre);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo realizar la modificación del sistema");
            throw e;
        }

    }

    public int eliminarSistema(int id_sistema) throws Exception {
        try {
            MantSistemaDAO dao = MantSistemaDAO.getInstance();
            return dao.eliminarSistema(id_sistema);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo realizar la eliminación del sistema");
            throw e;
        }

    }

    public int ingresarSistema(String nombre) throws Exception {
        try {
            MantSistemaDAO dao = MantSistemaDAO.getInstance();
            return dao.ingresarSistema(nombre);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo realizar el ingreso del nuevo sistema");
            throw e;
        }
    }

    public String sePuedeEliminar(int id_sistema) throws Exception {
        try {
            MantSistemaDAO dao = MantSistemaDAO.getInstance();
            return dao.sePuedeEliminar(id_sistema);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo realizar la consulta para saber si se puede o no eliminar el sistema");
            throw e;
        }
    }

    public int idPerfil(Integer ses_idusu) throws Exception{
      MantSistemaDAO dao = MantSistemaDAO.getInstance();
      try {
          return dao.idPerfil(ses_idusu);
      } catch (Exception e) {
          e.printStackTrace();
          System.out.print("No se pudo localizar el idPerfil [FallasBean]");
          throw e;
      }
  }


}

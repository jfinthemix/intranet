package proyecto_uoct.mantenedorSubsistemas.model;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.CreateException;
import proyecto_uoct.dao.MantSubsistemaDAO;
import proyecto_uoct.mantenedorSubsistemas.VO.SubsistemaVO;
import java.util.LinkedList;
import java.util.List;

public class MantenedorSubsistemasBean implements SessionBean {
    SessionContext sessionContext;
    public void ejbCreate() throws CreateException {}
    public void ejbRemove() {}
    public void ejbActivate() {}
    public void ejbPassivate() {}
    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    public List buscarSubsistema() throws Exception{
        try {
            MantSubsistemaDAO dao = MantSubsistemaDAO.getInstance();
            return dao.buscarSubsistema();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo realizar la búsqueda de subsistemas");
            throw e;
        }
    }

    public int  modificarSubsistema(int id_subsistema, String nombre,
                                   int id_sistema) throws Exception {
        try {
           MantSubsistemaDAO dao = MantSubsistemaDAO.getInstance();
           return dao.modificarSubsistema(id_subsistema, nombre, id_sistema);
       }catch (Exception e) {
           e.printStackTrace();
           System.out.print("No se pudo realizar la modificación del subsistema");
           throw e;
       }

    }

    public int  eliminarSubsistema(int id_subsistema) throws Exception {
        try {
           MantSubsistemaDAO dao = MantSubsistemaDAO.getInstance();
           return dao.eliminarSubsistema(id_subsistema);
       }catch (Exception e) {
           e.printStackTrace();
           System.out.print("No se pudo realizar la eliminación del subsistema");
           throw e;
       }

    }

    public int  ingresarSubsistema(String nombre, int id_sistema)  throws Exception{
        try {
           MantSubsistemaDAO dao = MantSubsistemaDAO.getInstance();
           return dao.ingresarSubsistema(nombre, id_sistema);
       }catch (Exception e) {
           e.printStackTrace();
           System.out.print("No se pudo realizar el ingreso del nuevo subsistema");
           throw e;
       }

    }

    public List lista_sistema() throws Exception {
            try {
                MantSubsistemaDAO dao = MantSubsistemaDAO.getInstance();
                return dao.lista_sistema();
            }catch (Exception e) {
                e.printStackTrace();
                System.out.print("No se pudo realizar la búsqueda de sistemas");
                throw e;
            }
    }

    public int idPerfil(Integer ses_idusu) throws Exception{
      MantSubsistemaDAO dao = MantSubsistemaDAO.getInstance();
      try {
          return dao.idPerfil(ses_idusu);
      } catch (Exception e) {
          e.printStackTrace();
          System.out.print("No se pudo localizar el idPerfil [FallasBean]");
          throw e;
      }
  }

    public String sePuedeEliminar(int id_subsistema) throws Exception{
        try {
            MantSubsistemaDAO dao = MantSubsistemaDAO.getInstance();
            return dao.sePuedeEliminar(id_subsistema);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo realizar la consulta para saber si se puede o no eliminar el subsistema");
            throw e;
        }
    }

}

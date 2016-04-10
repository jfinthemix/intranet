package proyecto_uoct.mensaje.model;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.CreateException;
import proyecto_uoct.dao.MensajeDAO;

public class MensajeBean implements SessionBean {
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

    public String  getMensaje(int id) {
        MensajeDAO dao=MensajeDAO.getInstance();
        String mens=null;
        try{
            mens=dao.getMensaje(id);
        }catch(Exception e){
            System.out.print(e.toString());
        }
        return mens;
    }

    public boolean setMensaje(String mensaje, int id) {
        MensajeDAO dao=MensajeDAO.getInstance();
        try{
            dao.setMensaje(mensaje, id);
            return true;
        }catch (Exception e){
            System.out.print(e.toString());
            return false;
        }
    }
}

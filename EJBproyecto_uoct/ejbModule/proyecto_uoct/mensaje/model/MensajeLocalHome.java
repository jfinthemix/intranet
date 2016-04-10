package proyecto_uoct.mensaje.model;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

public interface MensajeLocalHome extends EJBLocalHome {
    public MensajeLocal create() throws CreateException;
}

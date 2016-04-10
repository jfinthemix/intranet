package proyecto_uoct.documentacion.model;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

public interface ClienteLocalHome extends EJBLocalHome {


    public ClienteLocal create() throws CreateException;
}

package proyecto_uoct.reservas.model;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

public interface RecursosLocalHome extends EJBLocalHome {


    public RecursosLocal create() throws CreateException;
}

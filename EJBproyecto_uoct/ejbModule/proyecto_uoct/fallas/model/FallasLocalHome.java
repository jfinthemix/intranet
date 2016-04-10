package proyecto_uoct.fallas.model;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

public interface FallasLocalHome extends EJBLocalHome {


    public FallasLocal create() throws CreateException;
}

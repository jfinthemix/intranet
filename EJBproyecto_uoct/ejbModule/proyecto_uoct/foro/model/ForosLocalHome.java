package proyecto_uoct.foro.model;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

public interface ForosLocalHome extends EJBLocalHome {


    public ForosLocal create() throws CreateException;
}

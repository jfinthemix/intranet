package proyecto_uoct.ventas.model;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

public interface VentasLocalHome extends EJBLocalHome {


    public VentasLocal create() throws CreateException, CreateException;
}

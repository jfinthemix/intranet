package proyecto_uoct.inventario.model;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

public interface InventarioLocalHome extends EJBLocalHome {


    public InventarioLocal create() throws CreateException;
}

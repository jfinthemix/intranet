package proyecto_uoct.mantenedorDispositivos.model;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

public interface MantenedorDispositivosLocalHome extends EJBLocalHome {
    public MantenedorDispositivosLocal create() throws CreateException;
}

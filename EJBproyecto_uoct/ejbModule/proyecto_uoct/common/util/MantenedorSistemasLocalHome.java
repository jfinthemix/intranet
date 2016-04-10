package proyecto_uoct.common.util;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

public interface MantenedorSistemasLocalHome extends EJBLocalHome {
    public MantenedorSistemasLocal create() throws CreateException;
}

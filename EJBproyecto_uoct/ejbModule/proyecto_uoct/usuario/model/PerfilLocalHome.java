package proyecto_uoct.usuario.model;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

public interface PerfilLocalHome extends EJBLocalHome {


    public PerfilLocal create() throws CreateException;
}

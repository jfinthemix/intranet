package proyecto_uoct.usuario.model;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

public interface UsuarioLocalHome extends EJBLocalHome {


    public UsuarioLocal create() throws CreateException;
}

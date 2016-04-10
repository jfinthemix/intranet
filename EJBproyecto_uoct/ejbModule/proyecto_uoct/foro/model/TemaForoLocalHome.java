package proyecto_uoct.foro.model;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

public interface TemaForoLocalHome extends EJBLocalHome {


    public TemaForoLocal create() throws CreateException;
}

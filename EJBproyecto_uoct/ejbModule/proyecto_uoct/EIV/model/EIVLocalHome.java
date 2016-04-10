package proyecto_uoct.EIV.model;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

public interface EIVLocalHome extends EJBLocalHome {


    public EIVLocal create() throws CreateException;
}

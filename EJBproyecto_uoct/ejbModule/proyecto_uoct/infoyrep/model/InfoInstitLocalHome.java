package proyecto_uoct.infoyrep.model;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

public interface InfoInstitLocalHome extends EJBLocalHome {


    public InfoInstitLocal create() throws CreateException;
}

package proyecto_uoct.documentacion.model;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

public interface DocumentoLocalHome extends EJBLocalHome {


    public DocumentoLocal create() throws CreateException;
}

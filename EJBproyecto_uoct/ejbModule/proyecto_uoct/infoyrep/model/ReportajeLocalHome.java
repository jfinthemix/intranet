package proyecto_uoct.infoyrep.model;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

public interface ReportajeLocalHome extends EJBLocalHome {


    public ReportajeLocal create() throws CreateException;
}

package proyecto_uoct.proyecto.model;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

public interface ProyectoLocalHome extends EJBLocalHome {


    public ProyectoLocal create() throws CreateException;
}

package proyecto_uoct.mantenedorSubsistemas.model;
import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

public interface MantenedorSubsistemasLocalHome extends EJBLocalHome {
    public MantenedorSubsistemasLocal create() throws CreateException;
}

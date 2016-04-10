package proyecto_uoct.mantenedorSistemas.model;
import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

public interface MantenedorSistemasLocalHome extends EJBLocalHome {
    public MantenedorSistemasLocal create() throws CreateException;
}





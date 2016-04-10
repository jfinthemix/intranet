package proyecto_uoct.EmpMant.model;
import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

public interface EmpMantLocalHome extends EJBLocalHome{

    public EmpMantLocal create() throws CreateException;
}

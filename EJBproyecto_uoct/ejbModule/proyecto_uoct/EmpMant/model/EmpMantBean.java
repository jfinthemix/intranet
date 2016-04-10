package proyecto_uoct.EmpMant.model;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.CreateException;
import proyecto_uoct.dao.EmpMantDAO;
import proyecto_uoct.EmpMant.VO.EmpMantVO;
import java.util.LinkedList;
import java.util.List;

public class EmpMantBean implements SessionBean {
    SessionContext sessionContext;
    public EmpMantBean() {
    }

    public void ejbCreate() throws CreateException {
    }

    public void ejbRemove() {
    }

    public void ejbActivate() {
    }

    public void ejbPassivate() {
    }

    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    public int ingresarEmpMant(String n_nombre, String n_telefono,
                               String n_direccion, Integer n_vigente,
                               Integer n_id_tipo_dispositivo,
                               String n_mail_terreno,
                               Integer n_id_tipo_dispositivo2,
                               String n_mail_sala) throws Exception{
        try {
            EmpMantDAO dao = EmpMantDAO.getInstance();
            return dao.ingresarEmpMant(n_nombre, n_telefono, n_direccion, n_vigente, n_id_tipo_dispositivo, n_mail_terreno, n_id_tipo_dispositivo2, n_mail_sala);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo ingresar la nueva empresa mantenedora");
            throw e;
        }
    }

    public int modificarEmpMant(Integer id_empresa,
                                String nombre,
                                String direccion,
                                String telefono,
                                Integer vigente,
                                String mail_1,
                                String mail_2) throws Exception{
        try {
            EmpMantDAO dao = EmpMantDAO.getInstance();
            dao.modificarEmpMant(id_empresa, nombre, direccion, telefono, vigente, mail_1, mail_2);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo modificar los datos de la empresa mantenedora");
            throw e;
        }
        return 0;
    }

    public List buscarEmpMant(Integer vigente) throws Exception{
        try {
            EmpMantDAO dao = EmpMantDAO.getInstance();
            return dao.buscarEmpMant(vigente);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo realizar la búsqueda de la(s) empresa(s) mantenedora(s)");
            throw e;
        }
    }

    public List  verEmpMant(Integer id_emp) throws Exception{
        try {
            EmpMantDAO dao = EmpMantDAO.getInstance();
            return dao.verEmpMant(id_emp);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo cargar los datos de la empresa mantenedora");
            throw e;
        }
    }

    public List listaDispositivosD(Integer id_empresa) throws Exception {
        try {
            EmpMantDAO dao = EmpMantDAO.getInstance();
            return dao.listaDispositivosD(id_empresa);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo cargar la lista de dispositivos disponibles");
            throw e;
        }
    }

    public List  listaDispositivosM(Integer id_empresa) throws Exception  {
        try {
            EmpMantDAO dao = EmpMantDAO.getInstance();
            return dao.listaDispositivosM(id_empresa);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo cargar la lista de dispositivos de la empresa mantenedora");
            throw e;
        }

    }

    public boolean  modificarDispositivo(String[] listaA,
                                         String[] listaB,
                                         Integer id_emp) throws Exception  {
        try {
            EmpMantDAO dao = EmpMantDAO.getInstance();
            return dao.modificarDispositivo(listaA, listaB, id_emp);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.print("No se pudo modificar la lista de dispositivos de la empresa mantenedora");
            throw e;
        }
    }


}

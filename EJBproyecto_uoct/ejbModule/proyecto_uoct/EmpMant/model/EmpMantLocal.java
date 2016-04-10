package proyecto_uoct.EmpMant.model;
import java.util.*;//para la List
import javax.ejb.*;
import proyecto_uoct.EmpMant.VO.EmpMantVO;

public interface EmpMantLocal extends EJBLocalObject {


    public int ingresarEmpMant(String n_nombre, String n_telefono,
                               String n_direccion, Integer n_vigente,
                               Integer n_id_tipo_dispositivo,
                               String n_mail_terreno,
                               Integer n_id_tipo_dispositivo2,
                               String n_mail_sala) throws
            Exception;

    public int modificarEmpMant(Integer id_empresa, String nombre,
                                String direccion, String telefono,
                                Integer vigente, String mail_1, String mail_2) throws
            Exception;

    public List buscarEmpMant(Integer vigente) throws Exception;

    public List verEmpMant(Integer id_emp) throws Exception;

    public List listaDispositivosD(Integer id_empresa) throws Exception;

    public List listaDispositivosM(Integer id_empresa) throws Exception;

    public boolean modificarDispositivo(String[] listaA, String[] listaB,
                                        Integer id_emp) throws Exception;

}

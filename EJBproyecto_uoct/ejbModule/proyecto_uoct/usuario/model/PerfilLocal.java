package proyecto_uoct.usuario.model;

import javax.ejb.EJBLocalObject;
import java.util.List;

public interface PerfilLocal extends EJBLocalObject {


    public List getFunsdePerfil(Integer id_perfil);

    public List getModulos();

    public List getFuncionalidades();

    public boolean ingresarPerfil(String nom_perfil,
                                  String[] id_funcionalidades) throws Exception;

    public List getPerfiles();

    public boolean actualizaPerfil(Integer id_perfil,
                                   String[] id_funcionalidades);

    public boolean eliminarPerfil(Integer id_perfil);
}

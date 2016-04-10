package proyecto_uoct.usuario.model;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.CreateException;
import java.util.List;
import proyecto_uoct.dao.PerfilDAO;

public class PerfilBean implements SessionBean {
    SessionContext sessionContext;
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

    public List getFunsdePerfil(Integer id_perfil) {
        PerfilDAO perfil = new PerfilDAO();

        return perfil.getFunsdePerfil(id_perfil);
    }

    public List getModulos() {
        PerfilDAO perfil = new PerfilDAO();

        return perfil.getModulos();

    }

    public List getFuncionalidades() {
        PerfilDAO perfil = new PerfilDAO();

        return perfil.getFuncionalidades();

    }

    public boolean ingresarPerfil(String nom_perfil,
                                  String[] id_funcionalidades) throws Exception {

        PerfilDAO perfil = new PerfilDAO();
        try {
            Integer idNuevo = perfil.ingresarPerfil(nom_perfil);
            for (int i = 0; i < id_funcionalidades.length; i++) {
                perfil.ingresarPerfil_Func(idNuevo,
                                           Integer.parseInt(id_funcionalidades[
                        i]));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            sessionContext.setRollbackOnly();
            return false;
        }

    }


    public List getPerfiles() {
        PerfilDAO perfil = new PerfilDAO();
        return perfil.getPerfiles();

    }


    public boolean actualizaPerfil(Integer id_perfil,
                                   String[] id_funcionalidades) {

        PerfilDAO perfil = new PerfilDAO();
        try {
            perfil.borraFuncionalidades(id_perfil);
            for (int i = 0; i < id_funcionalidades.length; i++) {
                perfil.ingresarPerfil_Func(id_perfil,
                                           Integer.parseInt(id_funcionalidades[
                        i]));
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            sessionContext.setRollbackOnly();
            return false;
        }

    }



    public boolean eliminarPerfil(Integer id_perfil) {
        PerfilDAO perfil = PerfilDAO.getInstance();
        try{
            if (!perfil.esEliminable(id_perfil)){
                return false;
            }
            if (perfil.borraFuncionalidades(id_perfil) && perfil.eliminarPerfil(id_perfil)){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
            sessionContext.setRollbackOnly();
        }

        return false;
    }
}

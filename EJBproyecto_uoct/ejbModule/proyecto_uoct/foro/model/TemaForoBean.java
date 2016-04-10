package proyecto_uoct.foro.model;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.CreateException;
import java.util.List;

import proyecto_uoct.dao.ForoDAO;
import java.util.Iterator;
import proyecto_uoct.foro.VO.ForodeListaVO;
import proyecto_uoct.foro.VO.PostUsuVO;

public class TemaForoBean implements SessionBean {
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

    public List getTemasForo() {
        List listaTemas = null;
        ForoDAO listaTemasDao = new ForoDAO();
        try {
            listaTemas = listaTemasDao.getListaTemas();
        } catch (Exception ex) {
            System.out.println("no se buscaron los temas");
        }
        return listaTemas;
    }

    public void agregarTema(String nuevoTema) {
        ForoDAO listaTemasDao = new ForoDAO();
        try {
            listaTemasDao.agregaTema(nuevoTema);
        } catch (Exception ex) {
            ex.printStackTrace();
            sessionContext.setRollbackOnly();
        }

    }

    public boolean borrarTema(int id_tema) {
        ForoDAO dao= ForoDAO.getInstance();
        try {
            List foros=dao.listaForosTema(new Integer(id_tema));
            if(foros!=null){
                Iterator ifo=foros.iterator();
                while(ifo.hasNext()){
                    Integer idForo= ((ForodeListaVO)ifo.next()).getIdForo();
                    dao.borrarPost(idForo.intValue());
                    dao.borrarAllDocsForo(idForo);
                    dao.borrarunforo(idForo.intValue());
                }
            }
            dao.borrarTema(id_tema);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            sessionContext.setRollbackOnly();
            return false;
        }

    }

}


package proyecto_uoct.foro.model;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.CreateException;
import java.util.List;
import proyecto_uoct.dao.ForoDAO;
import proyecto_uoct.dao.ForoDAO;
import proyecto_uoct.foro.VO.DetForoVO;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import proyecto_uoct.foro.VO.DocForoVO;

public class ForosBean implements SessionBean {
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

    public List getListaForos(Integer id_tema) {

        List listaforos = null;
        ForoDAO listaForosTemaDao = new ForoDAO();
        try {
            listaforos = listaForosTemaDao.listaForosTema(id_tema);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listaforos;
    }

    public List getForosxfecha(String fecha_s) {
        List listaforos = null;

        ForoDAO forodao = new ForoDAO();
        listaforos = forodao.getForosxFecha(fecha_s);
        return listaforos;
    }

    public List getForosxpal(String pal) {
        List listaforos = null;

        ForoDAO forodao = new ForoDAO();
        listaforos = forodao.getForosxpal(pal);
        return listaforos;
    }

    public DetForoVO getDetForo(Integer id_foro) {
        DetForoVO detForo = null;
        ForoDAO forodao = new ForoDAO();
        detForo = forodao.getDetForo(id_foro);
        return detForo;
    }

    public List getDocsForo(Integer id_foro) {
        List docsforo = null;
        ForoDAO forodao = new ForoDAO();
        docsforo = forodao.getDocsForo(id_foro);
        return docsforo;
    }

    public List getPost(Integer id_foro) {
        List listapost = null;
        ForoDAO forodao = new ForoDAO();
        listapost = forodao.getPost(id_foro);
        return listapost;

    }

    public void ingresarPost(Integer id_foro, Integer id_usu, String comentario) {
        ForoDAO forodao = new ForoDAO();
        forodao.ingresarPost(id_foro, id_usu, comentario);

    }

    public boolean actDescForo(Integer id_foro, String desc) {
        boolean ingresado = false;
        try{
            ForoDAO forodao = new ForoDAO();
            ingresado = (forodao.actDescForo(id_foro, desc));
        }catch(Exception e){
            sessionContext.setRollbackOnly();
            return false;
        }
        return ingresado;

    }

    public boolean borrarDocForo(Integer id_doc) {
        boolean borrado = false;
        try{
            ForoDAO forodao = new ForoDAO();
            borrado = (forodao.borrarDocForo(id_doc));
        }catch(Exception e){
            sessionContext.setRollbackOnly();
        }finally{
            return borrado;
        }
    }

    public int agregarForo(DetForoVO nuevof) {
        int resp1 = 0;
        ForoDAO forodao = ForoDAO.getInstance();
        try {
            resp1 = forodao.agregarForo(nuevof);
        } catch (Exception ex) {
            return resp1;
        }
        return resp1;
    }

    public Integer getAdminForo(Integer id_foro) {
        ForoDAO forodao = new ForoDAO();
        Integer id_admin = forodao.getAdminForo(id_foro);
        return id_admin;
    }

    public List  buscarForos(String palClave, String fecha, Integer idTema) {
        return ForoDAO.getInstance().buscarForos(palClave,fecha,idTema);
    }

    public boolean  registrarDocumentoForo(DocForoVO docForo) {
        try{
         return  ForoDAO.getInstance().registrarDocForo(docForo);
        }catch(Exception e){
         sessionContext.setRollbackOnly();
         return false;
        }
    }

    public DocForoVO  descargaDocForo(Integer idDocForo) {
        return ForoDAO.getInstance().getDocForo(idDocForo);
    }


}

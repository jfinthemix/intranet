package proyecto_uoct.infoyrep.model;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.CreateException;
import proyecto_uoct.infoyrep.VO.ArchivoInfoVO;
import proyecto_uoct.dao.InfoInstitDAO;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.*;
import java.util.List;
import proyecto_uoct.documentacion.VO.ArchivoDocVO;

public class InfoInstitBean implements SessionBean {
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

    public List getListaArchivos() {
        InfoInstitDAO infoins = InfoInstitDAO.getInstance();
        List lista = null;
        lista= infoins.listaArchivos();
        return lista;
    }

    public boolean upArchivo(ArchivoInfoVO arch) {

        InfoInstitDAO infoins = InfoInstitDAO.getInstance();
        try {
            return infoins.upArchivo(arch);
        } catch (Exception ex) {
            ex.printStackTrace();
            sessionContext.setRollbackOnly();
            return false;
        }

    }



    public ArchivoDocVO getArchivo(Integer idArchivo) {
        InfoInstitDAO infoins = InfoInstitDAO.getInstance();
        return infoins.getArchivoDoc(idArchivo);
}

    public boolean  eliminarArchivo(Integer idArchivo) {
        try{
            return InfoInstitDAO.getInstance().eliminarArchivo(idArchivo);
        }catch (Exception ex) {
            sessionContext.setRollbackOnly();
            return false;
        }
    }


}

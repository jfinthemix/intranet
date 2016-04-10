package proyecto_uoct.infoyrep.model;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.CreateException;
import proyecto_uoct.dao.ReportajeDAO;
import proyecto_uoct.infoyrep.VO.ReportajeVO;
import java.io.DataOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.*;
import java.util.List;

public class ReportajeBean implements SessionBean {
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

    public int agregarReportaje(ReportajeVO nuevoRepor) {

        ReportajeDAO rep = ReportajeDAO.getInstance();
        try {
            int idrep = rep.agregarReportaje(nuevoRepor);
            rep.agregarFoto(nuevoRepor.getFotoRep(), new Integer(idrep));
            return idrep;
        } catch (Exception ex) {
            ex.printStackTrace();
            sessionContext.setRollbackOnly();
            return 0;
        }

    }


    public ReportajeVO getReportaje(int id) {
        ReportajeVO reportaje = null;
        ReportajeDAO rep = ReportajeDAO.getInstance();
        reportaje = rep.getReportaje(id);
        return reportaje;

    }

    public byte[] getFoto(int idRep) {
        ReportajeDAO rep = ReportajeDAO.getInstance();
        return rep.getFoto(idRep);
    }

    public boolean actualizarReportaje(ReportajeVO reportaje) {
        ReportajeDAO rep = ReportajeDAO.getInstance();
        try {

            rep.actualizaReportaje(reportaje);

            if (reportaje.getFotoRep().length!=0) {
                rep.agregarFoto(reportaje.getFotoRep(), reportaje.getIdRep());
            }

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            sessionContext.setRollbackOnly();
            return false;
        }
    }

    public List getTailReportajes() {
        ReportajeDAO rep = ReportajeDAO.getInstance();
        return rep.getTailReportajes();
    }

    public ReportajeVO getReportajePorTipo(int tipo) {
        ReportajeDAO rep = ReportajeDAO.getInstance();
        return rep.getReportajePorTipo(tipo);
    }


}

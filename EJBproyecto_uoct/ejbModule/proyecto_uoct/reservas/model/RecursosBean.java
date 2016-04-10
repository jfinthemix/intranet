package proyecto_uoct.reservas.model;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.CreateException;
import java.util.List;
import proyecto_uoct.dao.RecursosDAO;
import proyecto_uoct.reservas.VO.RecursoVO;
import proyecto_uoct.reservas.VO.FiltroRecursos;
import proyecto_uoct.reservas.VO.ReservaVO;

public class RecursosBean implements SessionBean {
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

    public List getRecursos() throws Exception {
        return RecursosDAO.getInstance().getRecursos();
    }

    public RecursoVO getRecurso(Long idRecurso) throws Exception {
        return RecursosDAO.getInstance().getRecurso(idRecurso);
    }

    public List getReservas(FiltroRecursos filtro) throws Exception {
        return RecursosDAO.getInstance().getReservas(filtro);
    }

    public void insertarReserva(ReservaVO reserva) throws Exception, RecursosException, RecursosException {
         RecursosDAO dao = RecursosDAO.getInstance();

         //Validación del rango
         FiltroRecursos filtroValidacion = new FiltroRecursos();
         filtroValidacion.setFecha(reserva.getFechaReserva());
         filtroValidacion.setValidacionHoraInicio(reserva.getHoraDeInicio());
         filtroValidacion.setValidacionHoraFin(reserva.getHoraDeFin());
         filtroValidacion.setIdRecurso(reserva.getIdRecurso());

         List reservasEnConflicto = dao.getReservas(filtroValidacion);
         if(reservasEnConflicto.size() > 0) {
             throw new RecursosException("El rango de horas está en conflicto con otras reservas");
         }

         dao.insertarReserva(reserva);
    }

    public void eliminarReserva(Long idReserva) throws Exception {
        RecursosDAO.getInstance().eliminarReserva(idReserva);
    }

    public List  getAllRecursos() {

        try {
            return RecursosDAO.getInstance().getAllRecursos();
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean  insertaRecurso(String nom, String desc) {
        return RecursosDAO.getInstance().insertaRecurso(nom,desc);
    }

    public boolean  actualizarRecurso(RecursoVO recurso) {
        try{
            return RecursosDAO.getInstance().actualizarRecurso(recurso);
        }catch(Exception e){
            e.printStackTrace();
            sessionContext.setRollbackOnly();
            return false;
        }
    }
}























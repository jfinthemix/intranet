package proyecto_uoct.reservas.model;

import javax.ejb.EJBLocalObject;
import java.util.List;
import proyecto_uoct.reservas.VO.RecursoVO;
import proyecto_uoct.reservas.VO.FiltroRecursos;
import proyecto_uoct.reservas.VO.ReservaVO;

public interface RecursosLocal extends EJBLocalObject {


    public List getRecursos() throws Exception;

    public RecursoVO getRecurso(Long idRecurso) throws Exception;

    public List getReservas(FiltroRecursos filtro) throws Exception;

    public void insertarReserva(ReservaVO reserva) throws Exception,
            RecursosException, RecursosException;

    public void eliminarReserva(Long idReserva) throws Exception;

    public List getAllRecursos();

    public boolean insertaRecurso(String nom, String desc);

    public boolean actualizarRecurso(RecursoVO recurso);
}

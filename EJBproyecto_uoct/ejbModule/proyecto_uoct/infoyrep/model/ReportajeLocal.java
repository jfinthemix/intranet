package proyecto_uoct.infoyrep.model;

import javax.ejb.EJBLocalObject;
import proyecto_uoct.infoyrep.VO.ReportajeVO;
import java.util.List;

public interface ReportajeLocal extends EJBLocalObject {


    public int agregarReportaje(ReportajeVO nuevoRepor);

    public ReportajeVO getReportaje(int id);

    public byte[] getFoto(int idRep);

    public boolean actualizarReportaje(ReportajeVO reportaje);

    public List getTailReportajes();

    public ReportajeVO getReportajePorTipo(int tipo);
}

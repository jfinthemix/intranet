package proyecto_uoct.EIV.model;

import javax.ejb.EJBLocalObject;
import java.util.List;
import java.util.Date;
import proyecto_uoct.EIV.VO.EIVVO;
import proyecto_uoct.EIV.VO.FlujoVO;
import proyecto_uoct.EIV.VO.EventoVO;
import proyecto_uoct.EIV.VO.BuscadorEIVVO;
import proyecto_uoct.EIV.VO.BusFlujosVO;

public interface EIVLocal extends EJBLocalObject {


    public List getEstadosEIV();

    public EIVVO getDetalleEIV(Integer id_eiv);

    public List getEventosEIV(Integer id_eiv);

    public List getFlujosxCalle(String calle);

    public List getFlujosxEIV(Integer id_eiv);

    public List getFlujosxFecha(Date fecha);

    public List getListaComunas();

    public List getTodaslasRedes();

    public Integer insertEIV(EIVVO nuevoeiv);

    public void insertFlujo(FlujoVO flujo);

    public boolean insertEventoEIV(EventoVO evento, Integer id_eiv,
                                   Integer tipoEvento);

    public boolean actuaEstadoEIV(Integer id_estado, Integer id_eiv);

    public List getTiposEIV();

    public boolean insertTipoEIV(String tipo);

    public boolean eliminarTipoEIV(Integer tipo);

    public boolean agregarComuna(String nomComuna);

    public boolean eliminarComuna(Integer idComuna);

    public List buscarEIV(BuscadorEIVVO bus);

    public boolean actualizarEIV(EIVVO eiv);

    public boolean eliminarFlujo(Integer idFlujo);

    public EventoVO getDetalleEvento(Integer idEvento);

    public boolean eliminarEvento(Integer idEvento);

    public List buscarFlujos(BusFlujosVO busflujos);

    public void alertaEmail(String from, String passw, String rcpt,
                            String subject, String body) throws Exception;

    public List buscaEIVxFechaVenc(Date fechaVenc) throws Exception;

    public void actualizarEvento(EventoVO evento) throws Exception;
}

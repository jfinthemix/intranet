package proyecto_uoct.proyecto.model;

import javax.ejb.EJBLocalObject;
import java.util.List;
import proyecto_uoct.proyecto.VO.DetalleProyectoVO;
import proyecto_uoct.proyecto.VO.DetalleOTVO;
import proyecto_uoct.proyecto.VO.NLOVO;
import proyecto_uoct.proyecto.VO.DocumentodeListaProyVO;
import proyecto_uoct.proyecto.VO.BusOTVO;
import proyecto_uoct.proyecto.VO.BusNLOVO;

public interface ProyectoLocal extends EJBLocalObject {


    public List getProyectosxAno(Integer ano, boolean isActivo, Integer idUsu) throws
            Exception;

    public List getProyectosxAno(Integer ano, boolean isActivo) throws
            Exception;

    public List getProyectos(Integer ano, Integer id_usu, String tipoBus,
                             boolean isActivo) throws Exception;

    public DetalleProyectoVO getDetalleIniciativa(Integer idIni) throws
            Exception;

    public DetalleOTVO getDetalleOT(Integer id_ot) throws Exception;

    public void insertProyecto(DetalleProyectoVO nuevoproy) throws Exception;

    public List getAllProys() throws Exception;

    public void regOT(DetalleOTVO nuevaOT) throws Exception;

    public List getOTsProy(Integer id_proy);

    public void regNLO(NLOVO nlo) throws Exception;

    public boolean regDocIni(DocumentodeListaProyVO nuevodoc) throws Exception;

    public void actualizarFechasOT(DetalleOTVO f_ot) throws Exception;

    public List getDocsIniciativa(Integer idIni) throws Exception;

    public boolean eliminaDocIni(Integer idDoc) throws Exception;

    public DocumentodeListaProyVO descargarDocumento(Integer idDoc) throws
            Exception;

    public void actualizarDatosIni(DetalleProyectoVO proy) throws Exception;

    public DetalleProyectoVO getDetalleIniDesdeOT(Integer idOT) throws
            Exception;

    public List buscarOT(BusOTVO bus) throws Exception;

    public List getEstadosOT() throws Exception;

    public List buscarNLO(BusNLOVO bus) throws Exception;

    public void finalizarIni(DetalleProyectoVO detProy) throws Exception;

    public void actualizarOT(DetalleOTVO detot) throws Exception;

    public void borrarOT(Integer idot) throws Exception;

    public void actualizarNLO(NLOVO nlo) throws Exception;

    public void eliminarNLO(Integer idNLO) throws Exception;

    public boolean esDelEquipo(Integer idUsu, Integer idIni) throws Exception;

    public List getEncargadosOT(Integer idOT) throws Exception;
}

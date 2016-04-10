package proyecto_uoct.documentacion.model;

import javax.ejb.EJBLocalObject;
import java.util.List;
import proyecto_uoct.documentacion.VO.DocumentoVO;
import proyecto_uoct.documentacion.VO.BusDocsVO;
import proyecto_uoct.documentacion.VO.DocumentoInVO;
import proyecto_uoct.documentacion.VO.ArchivoDocVO;
import org.apache.struts.upload.FormFile;

public interface DocumentoLocal extends EJBLocalObject {


    public List getTiposDoc(Integer sentido);

    public List getDocsxCodigo(Integer id_tipo_doc, String id_doc,
                               Integer id_sentido);

    public DocumentoVO getDetalleDoc(Integer idDoc);

    public List buscarDocumentos(BusDocsVO busdocs);

    public List getEIVnuevos();

    public List getDocsxtipodoc(Integer id_tipo_doc);

    public boolean insertarDocumento(DocumentoInVO nuevodoc);

    public String getNomTipoDoc(Integer id_tipo);

    public String borrarDocumento(Integer id_doc);

    public ArchivoDocVO descargaArchivoDoc(Integer idArchivo);

    public boolean actualizaDoc(DocumentoInVO doc);

    public List getArchivosDeDoc(Integer idDoc);

    public boolean borrarArchivoDoc(Integer idArchivo);

    public boolean agregarArchivoDoc(ArchivoDocVO archivo, Integer idDoc);

    public void insertaTipoDoc(String codigo, Integer idSentido,
                               Integer isEnIniciativas) throws Exception;

    public boolean cambiarEstadoTipoDoc(Integer idTipoDoc, boolean isActivo);

    public Integer reservarDoc(String tipoDoc, Integer idUsu, Integer idTipoDoc) throws
            Exception;

    public List getTiposDocxPal(String palClave, Integer sentido,
                                Integer isActivo);

    public List buscarReservasDoc(BusDocsVO bus) throws Exception;
}

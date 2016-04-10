package proyecto_uoct.documentacion.model;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.CreateException;
import java.util.List;
import proyecto_uoct.dao.DocumentoDAO;
import proyecto_uoct.documentacion.VO.DocumentoVO;
import proyecto_uoct.documentacion.VO.DocumentodeListaVO;
import proyecto_uoct.documentacion.VO.BusDocsVO;
import java.util.Iterator;
import proyecto_uoct.documentacion.VO.DocumentoInVO;
import proyecto_uoct.documentacion.VO.ArchivoDocVO;
import org.apache.struts.upload.FormFile;

public class DocumentoBean implements SessionBean {
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

    /*
        devuelve una lista con todos los tipos de documentos existentes
       si sentido es 1 entrega la lista de tipos de documentos entrantes
     si sentido es 2 entrega la lista de tipos de documentos salientes
     */
    public List getTiposDoc(Integer sentido) {
        List listatipos = null;
        DocumentoDAO doc = DocumentoDAO.getInstance();
        listatipos = doc.getTiposDoc(sentido);
        return listatipos;
    }

    public List getDocsxCodigo(Integer id_tipo_doc, String id_doc,
                               Integer id_sentido) {
        List listadocs = null;
        DocumentoDAO doc = DocumentoDAO.getInstance();
        // listadocs = doc.listaDocsxCodigo(id_tipo_doc, id_doc, id_sentido);
        return listadocs;
    }

    public DocumentoVO getDetalleDoc(Integer idDoc) {
        DocumentoVO detDoc = null;
        DocumentoDAO doc = DocumentoDAO.getInstance();
        try {
            detDoc = doc.getDetalleDoc(idDoc);
        } catch (Exception ex) {
            return null;
        }
        return detDoc;

    }

    public List buscarDocumentos(BusDocsVO busdocs) {
        List documentos = null;

        DocumentoDAO doc = DocumentoDAO.getInstance();
        documentos = doc.buscarDocumentos(busdocs);
        return documentos;

    }

    public List getEIVnuevos() {
        return null;
    }

    public List getDocsxtipodoc(Integer id_tipo_doc) {

        DocumentoDAO doc = DocumentoDAO.getInstance();
        List documentos = null;
//        documentos = doc.getDocsxtipodoc(id_tipo_doc);
        return documentos;

    }


    public boolean insertarDocumento(DocumentoInVO nuevodoc) {
        DocumentoDAO doc = DocumentoDAO.getInstance();
        try {
            Integer idDoc = doc.insertDoc(nuevodoc);
            for (int i = 0; i < nuevodoc.getResponsable().length; i++) {
                doc.insertUsu_Doc(idDoc, nuevodoc.getResponsable()[i]);
            }
            if (nuevodoc.getFile() != null) {
                doc.insertaDocFile(idDoc, nuevodoc.getFile());
            }
            if (nuevodoc.getFile1() != null) {
                doc.insertaDocFile(idDoc, nuevodoc.getFile1());
            }
            if (nuevodoc.getFile2() != null) {
                doc.insertaDocFile(idDoc, nuevodoc.getFile2());
            }
            if(nuevodoc.getAnt()!=null){
            for (int i = 0; i < nuevodoc.getAnt().length; i++) {
                doc.insertDoc_Doc(idDoc, nuevodoc.getAnt()[i]);
            }}
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            sessionContext.setRollbackOnly();
            return false;
        }

    }

    public String getNomTipoDoc(Integer id_tipo) {
        DocumentoDAO doc = DocumentoDAO.getInstance();

        return doc.getNomTipoDoc(id_tipo);
    }

    public String borrarDocumento(Integer id_doc) {
        try {
            DocumentoDAO doc = DocumentoDAO.getInstance();
            if(doc.estaEnBitacora(id_doc)){
                return "El documento no se puede eliminar debido a que tiene una bitácora de EIV relacionada";
            }
            doc.borrarDoc_Doc(id_doc);
            doc.borrarDoc_Doc_Ant(id_doc);
            //doc.borrarEIV_Doc(id_doc);
            doc.borrarEventoEIV(id_doc);
            doc.borrarNLO_Doc(id_doc);
            doc.borrarUsu_Doc(id_doc);
            doc.borrarArchivoDoc(id_doc);
            doc.borrarDoc(id_doc);
            return "El documento fue eliminado exitosamente";
        } catch (Exception e) {
            this.sessionContext.setRollbackOnly();
            e.printStackTrace();
            return "El documento no pudo ser eliminado";
        }

    }

    public ArchivoDocVO descargaArchivoDoc(Integer idArchivo) {
        DocumentoDAO doc = DocumentoDAO.getInstance();
        return doc.getArchivoDoc(idArchivo);
    }

    public boolean actualizaDoc(DocumentoInVO doc) {
        DocumentoDAO docDAO = DocumentoDAO.getInstance();
        try {
            docDAO.actualizarDoc(doc);
            docDAO.borrarUsu_Doc(doc.getIdDoc());
            for (int i = 0; i < doc.getResponsable().length; i++) {
                docDAO.insertUsu_Doc(doc.getIdDoc(), doc.getResponsable()[i]);
            }
            docDAO.borrarDoc_Doc(doc.getIdDoc());
            if(doc.getAnt()!=null){
            for (int i = 0; i < doc.getAnt().length; i++) {
                docDAO.insertDoc_Doc(doc.getIdDoc(), doc.getAnt()[i]);
            }}
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            sessionContext.setRollbackOnly();
        }
        return false;
    }


    public List getArchivosDeDoc(Integer idDoc) {
        DocumentoDAO doc = DocumentoDAO.getInstance();
        try {
            return doc.getArchivosDeDoc(idDoc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    public boolean borrarArchivoDoc(Integer idArchivo) {
        DocumentoDAO doc = DocumentoDAO.getInstance();
        try {
            return doc.borrarArchivoDocConIdArchivo(idArchivo);

        } catch (Exception e) {
            e.printStackTrace();
            sessionContext.setRollbackOnly();
        }
        return false;
    }

    public boolean agregarArchivoDoc(ArchivoDocVO archivo, Integer idDoc) {
        DocumentoDAO doc = DocumentoDAO.getInstance();
        try {
            return (doc.insertaDocFile(idDoc, archivo.getArchivoFile()));
        } catch (Exception ex) {
            ex.printStackTrace();
            sessionContext.setRollbackOnly();
        }
        return false;
    }

    public void insertaTipoDoc(String codigo, Integer idSentido,
                                  Integer isEnIniciativas) throws Exception {
        DocumentoDAO doc = DocumentoDAO.getInstance();
        try {
            doc.insertaTipoDoc(codigo, idSentido, isEnIniciativas);
            if (idSentido.intValue() == 2) { // si es un documento saliente genera una secuencia para hacer las reservas.
                doc.insertaSecuenciaTipoDoc(codigo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            sessionContext.setRollbackOnly();
            throw e;
        }
    }

    public boolean cambiarEstadoTipoDoc(Integer idTipoDoc, boolean isActivo) {
        DocumentoDAO doc = DocumentoDAO.getInstance();
        try {
            if (isActivo) {
                doc.desactivarTipoDoc(idTipoDoc);
            } else {
                doc.activarTipoDoc(idTipoDoc);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            sessionContext.setRollbackOnly();
            return false;
        }
    }

    public Integer reservarDoc(String tipoDoc,Integer idUsu,Integer idTipoDoc) throws Exception {
        DocumentoDAO doc = DocumentoDAO.getInstance();

        try {
            return doc.reservarDoc(tipoDoc,idUsu,idTipoDoc);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public List  getTiposDocxPal(String palClave, Integer sentido,Integer isActivo) {
        DocumentoDAO doc = DocumentoDAO.getInstance();
        return doc.getTiposDocxPal(palClave,sentido,isActivo);

    }

    public List  buscarReservasDoc(BusDocsVO bus) throws Exception {
        try{

            DocumentoDAO doc = DocumentoDAO.getInstance();
            return doc.buscarReservasDoc(bus);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}

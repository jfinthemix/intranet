package proyecto_uoct.proyecto.model;

import java.util.*;

import javax.ejb.*;

import proyecto_uoct.dao.*;
import proyecto_uoct.proyecto.VO.*;
import proyecto_uoct.documentacion.model.*;
import proyecto_uoct.common.LocalizadorServicios;

public class ProyectoBean implements SessionBean {
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

    public List getProyectosxAno(Integer ano, boolean isActivo, Integer idUsu) throws
            Exception {
        ProyectoDAO proy = ProyectoDAO.getInstance();
        List losproy = proy.getProyectosxAno(ano, isActivo);
        Iterator i = losproy.iterator();
        while (i.hasNext()) {
            ProyectodeListaVO p = (ProyectodeListaVO) i.next();
            p.setEsDelEquipo(proy.esDelEquipo(idUsu, p.getIdProy()));
        }
        return losproy;
    }


    public List getProyectosxAno(Integer ano, boolean isActivo) throws
            Exception {
        ProyectoDAO proy = ProyectoDAO.getInstance();
        List losproy = proy.getProyectosxAno(ano, isActivo);
        Iterator i = losproy.iterator();
        return losproy;
    }

    public List getProyectos(Integer ano, Integer id_usu, String tipoBus,
                             boolean isActivo) throws
            Exception {
        ProyectoDAO proy = ProyectoDAO.getInstance();
        List proyectos = null;

        if (tipoBus.compareTo("aCargo") == 0) {
            proyectos = proy.BuscadorProyaCargo(ano, id_usu, true);
        }
        if (tipoBus.compareTo("enEquipo") == 0) {
            proyectos = proy.BuscadorProyEnEquipo(ano, id_usu, true);
        }
        if (tipoBus.compareTo("otros") == 0) {
            proyectos = proy.BuscadorOtrasIniciativas(ano, id_usu, true);
        }
        return proyectos;
    }


    public DetalleProyectoVO getDetalleIniciativa(Integer idIni) throws
            Exception {

        ProyectoDAO proy = ProyectoDAO.getInstance();

        DetalleProyectoVO detpro = proy.getDetIni(idIni);
        detpro.setEquipo(proy.getEquipoIni(idIni));
        detpro.setEncargado(proy.getEncargadoIni(idIni).getNombreUsu());
        detpro.setIdEncargado(proy.getEncargadoIni(idIni).getIdUsu());
        detpro.setDocumentos(proy.getDocumentosIni(idIni));
        detpro.setOTs(proy.getOTsProy(idIni));
        detpro.setNLOs(proy.getNLOsinOT(idIni));
        return detpro;

    }


    public DetalleOTVO getDetalleOT(Integer id_ot) throws Exception {
        OTDAO otdao = OTDAO.getInstance();
        DetalleOTVO ot = otdao.getDetalleOT(id_ot);
        return ot;

    }

    public void insertProyecto(DetalleProyectoVO nuevoproy) throws Exception {
        ProyectoDAO dao = ProyectoDAO.getInstance();
        try {
            Integer np = dao.insertProyecto(nuevoproy);
            if (np != null) {
                List enc = nuevoproy.getEquipo();
                Iterator i = enc.iterator();
                while (i.hasNext()) {
                    Integer usuenc = (Integer) i.next();
                    dao.insertaEquipo(usuenc, np);

                }
                dao.insertaEncargado(nuevoproy.getIdEncargado(), np);

            }
        } catch (Exception e) {
            e.printStackTrace();
            sessionContext.setRollbackOnly();
            throw e;
        }
    }


    public List getAllProys() throws Exception {
        ProyectoDAO dao = ProyectoDAO.getInstance();
        List proys = dao.getAllProyectos();
        return proys;
    }


    public void regOT(DetalleOTVO nuevaOT) throws Exception {
        try {
            ProyectoDAO proy = ProyectoDAO.getInstance();
            DocumentoDAO doc = DocumentoDAO.getInstance();

//--Registra el documento
            Integer idDoc = doc.insertDoc(nuevaOT.getDocumento());
            for (int i = 0; i < nuevaOT.getDocumento().getResponsable().length;
                         i++) {
                doc.insertUsu_Doc(idDoc,
                                  nuevaOT.getDocumento().getResponsable()[i]);
            }
            if (nuevaOT.getDocumento().getFile() != null) {
                doc.insertaDocFile(idDoc, nuevaOT.getDocumento().getFile());
            }
            if (nuevaOT.getDocumento().getFile1() != null) {
                doc.insertaDocFile(idDoc, nuevaOT.getDocumento().getFile1());
            }
            if (nuevaOT.getDocumento().getFile2() != null) {
                doc.insertaDocFile(idDoc, nuevaOT.getDocumento().getFile2());
            }
            if (nuevaOT.getDocumento().getAnt() != null) {
                for (int i = 0; i < nuevaOT.getDocumento().getAnt().length; i++) {
                    doc.insertDoc_Doc(idDoc, nuevaOT.getDocumento().getAnt()[i]);
                }
            }

            nuevaOT.getDocumento().setIdDoc(idDoc);

//---- Registra la OT

            Integer nOT = (Integer) proy.regOT(nuevaOT);
            List encar = nuevaOT.getEncargados();
            if (nOT != null) {
                Iterator i = encar.iterator();
                while (i.hasNext()) {
                    Integer e = (Integer) i.next();
                    proy.regResponsableOT(nOT, e);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            sessionContext.setRollbackOnly();
            throw e;
        }
    }


    public List getOTsProy(Integer id_proy) {
        ProyectoDAO dao = ProyectoDAO.getInstance();
        return dao.getOTsProy(id_proy);

    }


    public void regNLO(NLOVO nlo) throws Exception {
        try {

            ProyectoDAO proy = ProyectoDAO.getInstance();
            DocumentoDAO doc = DocumentoDAO.getInstance();
            OTDAO otdao = OTDAO.getInstance();

            //--Registra el documento

            Integer idDoc = doc.insertDoc(nlo.getDocumento());
            for (int i = 0; i < nlo.getDocumento().getResponsable().length;
                         i++) {
                doc.insertUsu_Doc(idDoc,
                                  nlo.getDocumento().getResponsable()[i]);
            }
            if (nlo.getDocumento().getFile() != null) {
                doc.insertaDocFile(idDoc, nlo.getDocumento().getFile());
            }
            if (nlo.getDocumento().getFile1() != null) {
                doc.insertaDocFile(idDoc, nlo.getDocumento().getFile1());
            }
            if (nlo.getDocumento().getFile2() != null) {
                doc.insertaDocFile(idDoc, nlo.getDocumento().getFile2());
            }
            if (nlo.getDocumento().getAnt() != null) {
                for (int i = 0; i < nlo.getDocumento().getAnt().length; i++) {
                    doc.insertDoc_Doc(idDoc, nlo.getDocumento().getAnt()[i]);
                }
            }

            nlo.getDocumento().setIdDoc(idDoc);

//---- Registra la OT

            Integer nnlo = otdao.regNLO(nlo);
            if (nlo.getOT() != null) {
                otdao.insertOT_NLO(nlo.getOT().getIdOT(), nnlo);
            }

        } catch (Exception e) {
            sessionContext.setRollbackOnly();
            throw e;
        }
    }


    public boolean regDocIni(DocumentodeListaProyVO nuevodoc) throws Exception {
        ProyectoDAO dao = ProyectoDAO.getInstance();
        boolean isin = false;
        try {
            isin = dao.regDocIni(nuevodoc);
        } catch (Exception e) {
            e.printStackTrace();
            sessionContext.setRollbackOnly();

        }
        return isin;
    }

    public void actualizarFechasOT(DetalleOTVO f_ot) throws Exception {
        try {
            OTDAO otdao = OTDAO.getInstance();
            otdao.actualizarFechasOT(f_ot);
            int idEstado = 1;
            if (f_ot.getFechaOT() != null) {
                idEstado = 1;
            }
            if (f_ot.getSuscrip() != null) {
                idEstado = 2;
            }
            if (f_ot.getFinTareas() != null) {
                idEstado = 3;
            }
            if (f_ot.getAprobacion() != null) {
                idEstado = 4;
            }
            otdao.actualizarEstadoOT(f_ot.getIdOT(), idEstado);
        } catch (Exception e) {
            sessionContext.setRollbackOnly();
            throw e;
        }
    }

    public List getDocsIniciativa(Integer idIni) throws Exception {
        ProyectoDAO dao = ProyectoDAO.getInstance();

        return dao.getDocumentosIni(idIni);
    }

    public boolean eliminaDocIni(Integer idDoc) throws Exception {
        ProyectoDAO dao = ProyectoDAO.getInstance();
        try {
            return dao.eliminaDocIni(idDoc);
        } catch (Exception e) {
            e.printStackTrace();
            sessionContext.setRollbackOnly();
            return false;
        }

    }

    public DocumentodeListaProyVO descargarDocumento(Integer idDoc) throws
            Exception {
        ProyectoDAO dao = ProyectoDAO.getInstance();
        return dao.descargarDocumento(idDoc);
    }


    public void actualizarDatosIni(DetalleProyectoVO proy) throws Exception {
        ProyectoDAO dao = ProyectoDAO.getInstance();
        try {
            dao.actualizarDatosIni(proy);

            dao.eliminaEncargado(proy.getIdProy());
            dao.eliminarEquipo(proy.getIdProy());
            List enc = proy.getEquipo();
            Iterator i = enc.iterator();
            while (i.hasNext()) {
                Integer usuenc = (Integer) i.next();
                dao.insertaEquipo(usuenc, proy.getIdProy());
            }

            dao.insertaEncargado(proy.getIdEncargado(), proy.getIdProy());

        } catch (Exception ex) {
            ex.printStackTrace();
            sessionContext.setRollbackOnly();
            throw ex;
        }
    }

    public DetalleProyectoVO getDetalleIniDesdeOT(Integer idOT) throws
            Exception {
        ProyectoDAO proy = ProyectoDAO.getInstance();

        DetalleProyectoVO detpro = proy.getDetIniDesdeOT(idOT);
        detpro.setEquipo(proy.getEquipoIni(detpro.getIdProy()));
        detpro.setEncargado(proy.getEncargadoIni(detpro.getIdProy()).
                            getNombreUsu());
        detpro.setIdEncargado(proy.getEncargadoIni(detpro.getIdProy()).getIdUsu());
        detpro.setDocumentos(proy.getDocumentosIni(detpro.getIdProy()));
        detpro.setOTs(proy.getOTsProy(detpro.getIdProy()));
        detpro.setNLOs(proy.getNLOsinOT(detpro.getIdProy()));
        return detpro;
    }

    public List buscarOT(BusOTVO bus) throws Exception {
        try {
            OTDAO ot = OTDAO.getInstance();
            return ot.buscarOT(bus);
        } catch (Exception e) {
            throw e;
        }

    }

    public List getEstadosOT() throws Exception {
        try {
            OTDAO dao = OTDAO.getInstance();
            return dao.getEstadosOT();
        } catch (Exception e) {
            throw e;

        }
    }

    public List buscarNLO(BusNLOVO bus) throws Exception {
        try {
            ProyectoDAO p = ProyectoDAO.getInstance();

            return p.buscarNLO(bus);
        } catch (Exception e) {
            throw e;

        }
    }

    public void finalizarIni(DetalleProyectoVO detProy) throws Exception {
        try {
            ProyectoDAO p = ProyectoDAO.getInstance();
            p.regFechaFin(detProy.getIdProy(), detProy.getFechaFin());
            p.cerrarIni(detProy.getIdProy());
        } catch (Exception e) {
            e.printStackTrace();
            sessionContext.setRollbackOnly();
            throw e;
        }
    }

    public void actualizarOT(DetalleOTVO detot) throws Exception {
        try {
            ProyectoDAO p = ProyectoDAO.getInstance();
            OTDAO ot = OTDAO.getInstance();
            ot.actualizarOT(detot);

            ot.borrarEncargadosOT(detot.getIdOT());
            List encar = detot.getEncargados();
            Iterator i = encar.iterator();
            while (i.hasNext()) {
                Integer e = (Integer) i.next();
                p.regResponsableOT(detot.getIdOT(), e);

            }

        } catch (Exception e) {
            e.printStackTrace();
            sessionContext.setRollbackOnly();
            throw e;
        }

    }

    public void borrarOT(Integer idot) throws Exception {
        try {
            OTDAO ot = OTDAO.getInstance();
            ProyectoDAO p = ProyectoDAO.getInstance();

            ot.borrarEncargadosOT(idot);

            List ns = ot.getNLOsxOT(idot);

            Iterator i = ns.iterator();
            while (i.hasNext()) {
                NLOVO nlo = (NLOVO) i.next();
                ot.borrarNLO(nlo.getId());
            }

            ot.borrarOT_NLO(idot);
            ot.borrarOT(idot);
        } catch (Exception e) {
            e.printStackTrace();
            sessionContext.setRollbackOnly();
            throw e;
        }

    }

    public void actualizarNLO(NLOVO nlo) throws Exception {
        try {
            OTDAO o = OTDAO.getInstance();
            o.actualizarNLO(nlo);
        } catch (Exception e) {
            sessionContext.setRollbackOnly();
            e.printStackTrace();
            throw e;
        }
    }

    public void eliminarNLO(Integer idNLO) throws Exception {
        try {
            ProyectoDAO p = ProyectoDAO.getInstance();
            OTDAO o = OTDAO.getInstance();
            NotaalLibrodeObrasDAO nlo = NotaalLibrodeObrasDAO.getInstance();
            NLOVO notaVO= nlo.getDetalleNLO(idNLO);
            nlo.borrarOT_NLO(idNLO);
            o.borrarNLO(idNLO);
            DocumentoLocal docLoc = LocalizadorServicios.getInstance().getDocumentoLocal();
            docLoc.borrarDocumento(notaVO.getDocumento().getIdDoc());
        } catch (Exception e) {
            sessionContext.setRollbackOnly();
            e.printStackTrace();
            throw e;
        }
    }

    public boolean esDelEquipo(Integer idUsu, Integer idIni) throws Exception {
        try {
            ProyectoDAO p = ProyectoDAO.getInstance();
            return p.esDelEquipo(idUsu, idIni);
        } catch (Exception e) {
            sessionContext.setRollbackOnly();
            e.printStackTrace();
            throw e;
        }
    }

    public List getEncargadosOT(Integer idOT) throws Exception {
        try {
            OTDAO ot = OTDAO.getInstance();
            List encargados = ot.getEncargadosOTxOT(idOT);

            return encargados;
        } catch (Exception e) {
            throw e;
        }
    }

}

package proyecto_uoct.documentacion.controller;

import java.io.*;
import java.text.*;
import java.util.*;

import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.upload.*;
import proyecto_uoct.common.*;
import proyecto_uoct.common.VO.*;
import proyecto_uoct.documentacion.VO.*;
import proyecto_uoct.documentacion.model.*;
import proyecto_uoct.usuario.model.*;

public class DocumentoAction extends Action {
    public DocumentoAction() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ActionForward execute(ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest servletRequest,
                                 HttpServletResponse servletResponse) {

        String hacia = "";

        HttpSession httpSession = servletRequest.getSession();
        DatosSesVO datoses;
        try {

            datoses = (DatosSesVO) httpSession.getAttribute("Ses_Usu");
            datoses.getId();
        } catch (Exception e) {
            servletRequest.setAttribute("mensaje", "La sesión ha caducado");
            return actionMapping.findForward("mensaje_fw");
        }

        if (servletRequest.getParameter("hacia") != null) {
            hacia = servletRequest.getParameter("hacia");
        } else {
            servletRequest.setAttribute("mensaje", "Error al cargar la página. Si está subiendo un archivo, verifique que su tamaño no supera los 10 MB ");
            return actionMapping.findForward("mensaje_fw");

        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        /* si se hacia es cargarRegDoc se busca la lista de tipo de documentos,
         y se entrega a la pagina con el formulario de ingreso de documentos
         */

        if (hacia.equals("cargarRegDocEntrante")) {
            return cargarRegDocEntrante(actionMapping, servletRequest,
                                        httpSession);

        }

        if (hacia.equals("cargarRegDocSaliente")) {
            return cargarRegDocSaliente(actionMapping, servletRequest,
                                        httpSession);
        }

        if (hacia.equals("busDoc")) {
            String tipoBus = "";
            tipoBus=servletRequest.getParameter("tipoBus");

            if (!tipoBus.equals("")) {
                if (tipoBus.equals("1")) {
                    return goBusDoccod(actionMapping, servletRequest,
                                       httpSession);
                }
                if (tipoBus.equals("2")) {
                    return goBusDocSen(actionMapping, servletRequest,
                                       httpSession);
                }
            }
            return cagarbusDoc(actionMapping, servletRequest,
                               httpSession);
        }

        if (hacia.equals("creaExcel")) {
            return creaExcel(actionMapping, servletRequest,
                             httpSession);

        }

        if (hacia.equals("abuscarDocs_pop")) {
            return aBuscarDocsPop(actionMapping, servletRequest,
                                  httpSession);
        }

        if (hacia.equals("buscarDocs_pop")) {
            return BuscarDocsPop(actionMapping, servletRequest,
                                 httpSession);
        }

        if (hacia.equals("abuscarDocsEnt_pop")) {
            return aBuscarDocsEntPop(actionMapping, servletRequest,
                                     httpSession);
        }

        if (hacia.equals("buscarDocsEnt_pop")) {
            return BuscarDocsEntPop(actionMapping, servletRequest,
                                    httpSession);
        }
        if (hacia.equals("abuscarDocsSal_pop")) {
            return aBuscarDocsSalPop(actionMapping, servletRequest,
                                     httpSession);
        }

        if (hacia.equals("buscarDocsSal_pop")) {
            return BuscarDocsSalPop(actionMapping, servletRequest,
                                    httpSession);
        }
        if (hacia.equals("ingresarDocumento")) {
            return ingresarDocumento(actionMapping, servletRequest, actionForm,
                                     httpSession);
        }
        if (hacia.equals("detalleDoc")) {
            return detalleDoc(actionMapping, servletRequest,
                              httpSession);
        }
        if (hacia.equals("descargarArchivo")) {
            return descargarArchivoDoc(actionMapping, servletRequest,
                                       httpSession,
                                       servletResponse);
        }

        if (hacia.equals("buscarParaEditarDoc")) {
            if (servletRequest.getParameter("tipoBus").equals("")) {
                return abuscaParaEditarDoc(actionMapping, servletRequest,
                                           httpSession,
                                           servletResponse);

            }
            return buscaParaEditarDoc(actionMapping, servletRequest,
                                      httpSession,
                                      servletResponse);
        }

        if (hacia.equals("aeditarDoc")) {
            return aeditarDoc(actionMapping, servletRequest,
                              httpSession,
                              servletResponse);
        }
        if (hacia.equals("actualizarDoc")) {
            return actualizarDoc(actionMapping, servletRequest,
                                 httpSession,
                                 servletResponse);
        }
        if (hacia.equals("borrarDoc")) {
            return borrarDoc(actionMapping, servletRequest,
                             httpSession,
                             servletResponse);
        }
        if (hacia.equals("adminArchivosDoc")) {
            return aadminArchivosDoc(actionMapping, servletRequest,
                                     httpSession,
                                     servletResponse);
        }
        if (hacia.equals("borrarArchivoDoc")) {
            return borrarArchivosDoc(actionMapping, servletRequest,
                                     httpSession,
                                     servletResponse);
        }
        if (hacia.equals("agregarArchivoDoc")) {
            return agregarArchivoDoc(actionMapping, servletRequest,
                                     httpSession, actionForm,
                                     servletResponse);
        }

        //----------TIPOS DE DOCUMENTOS----------------

        if (hacia.equals("aadminTiposDoc")) {
            return adminTiposDoc(actionMapping, servletRequest,
                                 httpSession,
                                 servletResponse);
        }
        if (hacia.equals("ingresarTipoDoc")) {
            return ingresarTipoDoc(actionMapping, servletRequest,
                                   httpSession,
                                   servletResponse);
        }

        if (hacia.equals("cambiarEstadoTipoDoc")) {
            return cambiarEstadoTipoDoc(actionMapping, servletRequest,
                                        httpSession,
                                        servletResponse);
        }
        if (hacia.equals("aReservarDoc")) {
            return aReservarDoc(actionMapping, servletRequest,
                                httpSession,
                                servletResponse);
        }

        if (hacia.equals("reservarDoc")) {
            return reservarDoc(actionMapping, servletRequest,
                               httpSession,
                               servletResponse);
        }

        if (hacia.equals("buscarReservasDeDocs")) {
            return buscarReservasDeDocs(actionMapping, servletRequest,
                                        httpSession,
                                        servletResponse);
        }

//si "hacia" no corresponde a ninguna de los casos definidos--------------------------------

        servletRequest.setAttribute("mensaje", "URL errónea");

        return actionMapping.findForward("mensaje_fw");

    }


    /**
     * buscarReservasDeDocs
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param servletResponse HttpServletResponse
     * @return ActionForward
     */
    private ActionForward buscarReservasDeDocs(ActionMapping actionMapping,
                                               HttpServletRequest
                                               servletRequest,
                                               HttpSession httpSession,
                                               HttpServletResponse
                                               servletResponse) {

        try {
            DocumentoLocal d = LocalizadorServicios.getInstance().
                               getDocumentoLocal();
            UsuarioLocal u = LocalizadorServicios.getInstance().getUsuarioLocal();

            servletRequest.setAttribute("usus", u.getUsuariosActivos());
            servletRequest.setAttribute("tiposDoc", d.getTiposDoc(new Integer(2)));

            if (servletRequest.getParameter("buscar") != null) {
                BusDocsVO bus = new BusDocsVO();
                if (!servletRequest.getParameter("idTipoDoc").equals("0")) {
                    bus.setIdTipoDoc(Integer.valueOf(servletRequest.
                            getParameter(
                                    "idTipoDoc")));
                }
                if (!servletRequest.getParameter("fecha_ini").equals("") &&
                    !servletRequest.getParameter("fecha_fin").equals("")) {
                    bus.setFechaIni(servletRequest.getParameter("fecha_ini"));
                    bus.setFechaFin(servletRequest.getParameter("fecha_fin"));
                }
                if (!servletRequest.getParameter("idUsu").equals("0")) {
                    bus.setEncargado(Integer.valueOf(servletRequest.
                            getParameter(
                                    "idUsu")));
                }

                servletRequest.setAttribute("reservas", d.buscarReservasDoc(bus));

            }
            return actionMapping.findForward("buscarReservas_fw");

        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo realizar la búsqueda");
            return actionMapping.findForward("mensaje_fw");
        }
    }

    /**
     * aReservarDoc
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param servletResponse HttpServletResponse
     * @return ActionForward
     */
    private ActionForward aReservarDoc(ActionMapping actionMapping,
                                       HttpServletRequest servletRequest,
                                       HttpSession httpSession,
                                       HttpServletResponse servletResponse) {
        try {

            DocumentoLocal documentoLocal = LocalizadorServicios.
                                            getInstance().
                                            getDocumentoLocal();
            UsuarioLocal usuarioLocal = LocalizadorServicios.getInstance().
                                        getUsuarioLocal();
            servletRequest.setAttribute("nomTipoDoc",
                                        documentoLocal.
                                        getNomTipoDoc(Integer.
                    valueOf(servletRequest.
                            getParameter("idTipoDoc"))));
            servletRequest.setAttribute("idTipoDoc",
                                        servletRequest.getParameter("idTipoDoc"));
            return actionMapping.findForward("reservarDoc_fw");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la pag. para reservar documentos");
            return actionMapping.findForward("mensaje_fw");
        }
    }

    /**
     * BuscarDocsSalPop
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward BuscarDocsSalPop(ActionMapping actionMapping,
                                           HttpServletRequest servletRequest,
                                           HttpSession httpSession) {
        try {
            BusDocsVO busdocs = new BusDocsVO();
            String tipoBus = servletRequest.getParameter("tipoBus"); // el tipo 1 es por codigo del doc, y 2 es por sentido
            String materia = servletRequest.getParameter("materia");
            busdocs.setMateria(materia);
            boolean enIni = false;
            if (tipoBus.equals("1")) {
                Integer idTipoDoc = Integer.valueOf(servletRequest.getParameter(
                        "idTipoDoc"));
                String codDoc = servletRequest.getParameter("codigoDoc");
                busdocs.setIdTipoDoc(idTipoDoc);
                busdocs.setCodigoDoc(codDoc);
                busdocs.setEnIniciativas(true);
            }

            if (tipoBus.equals("2")) {
                busdocs.setIdSentido(Integer.valueOf(servletRequest.
                        getParameter("id_sentido")));
                String enI = servletRequest.getParameter("enIni");
                if (enI != null) {
                    enIni = true;
                }
                busdocs.setEnIniciativas(enIni);
            }

            busdocs.setFechaIni(servletRequest.getParameter("fecha_ini"));
            busdocs.setFechaFin(servletRequest.getParameter("fecha_fin"));

            busdocs.setEncargado(Integer.valueOf(servletRequest.
                                                 getParameter("id_usuario")));

            busdocs.setPalClave(servletRequest.getParameter("palClave"));

            DocumentoLocal documentoLocal = LocalizadorServicios.getInstance().
                                            getDocumentoLocal();

            servletRequest.setAttribute("listadocs",
                                        documentoLocal.buscarDocumentos(busdocs));
            return aBuscarDocsSalPop(actionMapping, servletRequest, httpSession);

        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo realizar la búsqueda");
            return actionMapping.findForward("mensaje_fw");
        }
    }

    /**
     * aBuscarDocsSalPop
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward aBuscarDocsSalPop(ActionMapping actionMapping,
                                            HttpServletRequest servletRequest,
                                            HttpSession httpSession) {
        try {

            DocumentoLocal documentoLocal = LocalizadorServicios.
                                            getInstance().
                                            getDocumentoLocal();
            UsuarioLocal usuarioLocal = LocalizadorServicios.getInstance().
                                        getUsuarioLocal();
            servletRequest.setAttribute("listausu",
                                        usuarioLocal.getListaUsuarios());
            servletRequest.setAttribute("tipossalientes",
                                        documentoLocal.
                                        getTiposDoc(new Integer(2)));
            return actionMapping.findForward("buscarDocsSal_pop");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la pag. para búsqueda");
            return actionMapping.findForward("mensaje_fw");
        }
    }

    /**
     * BuscarDocsEntPop
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward BuscarDocsEntPop(ActionMapping actionMapping,
                                           HttpServletRequest servletRequest,
                                           HttpSession httpSession) {
        try {
            BusDocsVO busdocs = new BusDocsVO();
            String tipoBus = servletRequest.getParameter("tipoBus"); // el tipo 1 es por codigo del doc, y 2 es por sentido
            String materia = servletRequest.getParameter("materia");
            busdocs.setMateria(materia);
            boolean enIni = false;
            if (tipoBus.equals("1")) {
                Integer idTipoDoc = Integer.valueOf(servletRequest.getParameter(
                        "idTipoDoc"));
                String codDoc = servletRequest.getParameter("codigoDoc");
                busdocs.setIdTipoDoc(idTipoDoc);
                busdocs.setCodigoDoc(codDoc);
                busdocs.setEnIniciativas(true);
            }

            if (tipoBus.equals("2")) {
                busdocs.setIdSentido(Integer.valueOf(servletRequest.
                        getParameter("id_sentido")));
                String enI = servletRequest.getParameter("enIni");
                if (enI != null) {
                    enIni = true;
                }
                busdocs.setEnIniciativas(enIni);
            }

            busdocs.setFechaIni(servletRequest.getParameter("fecha_ini"));
            busdocs.setFechaFin(servletRequest.getParameter("fecha_fin"));

            busdocs.setEncargado(Integer.valueOf(servletRequest.
                                                 getParameter("id_usuario")));

            busdocs.setPalClave(servletRequest.getParameter("palClave"));

            DocumentoLocal documentoLocal = LocalizadorServicios.getInstance().
                                            getDocumentoLocal();

            servletRequest.setAttribute("listadocs",
                                        documentoLocal.buscarDocumentos(busdocs));
            return aBuscarDocsEntPop(actionMapping, servletRequest, httpSession);

        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo realizar la búsqueda");
            return actionMapping.findForward("mensaje_fw");
        }
    }

    /**
     * aBuscarDocsEntPop
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward aBuscarDocsEntPop(ActionMapping actionMapping,
                                            HttpServletRequest servletRequest,
                                            HttpSession httpSession) {
        try {

            DocumentoLocal documentoLocal = LocalizadorServicios.
                                            getInstance().
                                            getDocumentoLocal();
            UsuarioLocal usuarioLocal = LocalizadorServicios.getInstance().
                                        getUsuarioLocal();
            servletRequest.setAttribute("listausu",
                                        usuarioLocal.getListaUsuarios());
            servletRequest.setAttribute("tiposentrantes",
                                        documentoLocal.
                                        getTiposDoc(new Integer(1)));
            return actionMapping.findForward("buscarDocsEnt_pop");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la pagina para búsqueda");
            return actionMapping.findForward("mensaje_fw");
        }
    }

    /**
     * reservarDoc
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param servletResponse HttpServletResponse
     * @return ActionForward
     */
    private ActionForward reservarDoc(ActionMapping actionMapping,
                                      HttpServletRequest servletRequest,
                                      HttpSession httpSession,
                                      HttpServletResponse servletResponse) {
        try {
            Integer idTipo = Integer.valueOf(servletRequest.getParameter(
                    "idTipoDoc"));
            DocumentoLocal documentoLocal = LocalizadorServicios.getInstance().
                                            getDocumentoLocal();
            String tipoDoc = documentoLocal.getNomTipoDoc(idTipo);

            httpSession = servletRequest.getSession();
            DatosSesVO datosses = (DatosSesVO) httpSession.getAttribute(
                    "Ses_Usu");

            Integer idDoc = documentoLocal.reservarDoc(tipoDoc, datosses.getId(),
                    idTipo);
            String mensaje = tipoDoc + "-" + idDoc.toString();
            servletRequest.setAttribute("mensaje", mensaje);
            return actionMapping.findForward("docReservado_fw");
        } catch (Exception ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo reservar el código");
            return actionMapping.findForward("mensaje_fw");
        }

    }

    /**
     * cambiarEstadoTipoDoc
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param servletResponse HttpServletResponse
     * @return ActionForward
     */
    private ActionForward cambiarEstadoTipoDoc(ActionMapping actionMapping,
                                               HttpServletRequest
                                               servletRequest,
                                               HttpSession httpSession,
                                               HttpServletResponse
                                               servletResponse) {

        try {
            DocumentoLocal documentoLocal = LocalizadorServicios.getInstance().
                                            getDocumentoLocal();
            Integer idTipoDoc = Integer.valueOf(servletRequest.getParameter(
                    "idTipoDoc"));
            String estado = servletRequest.getParameter("estado");
            boolean isActivo = false;
            if (estado.equals("Habilitado")) {
                isActivo = true;
            } else {
                if (estado.equals("Deshabilitado")) {
                    isActivo = false;
                }
            }
            documentoLocal.cambiarEstadoTipoDoc(idTipoDoc, isActivo);
            servletRequest.setAttribute("mensaje",
                                        "El estado del tipo de doc. fue actualizado");

            return adminTiposDoc(actionMapping, servletRequest, httpSession,
                                 servletResponse);
        } catch (Exception ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cambiar el estado del tipo de documento");
            return actionMapping.findForward("mensaje_fw");
        }

    }

    /**
     * ingresarTipoDoc
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param servletResponse HttpServletResponse
     * @return ActionForward
     */
    private ActionForward ingresarTipoDoc(ActionMapping actionMapping,
                                          HttpServletRequest servletRequest,
                                          HttpSession httpSession,
                                          HttpServletResponse servletResponse) {

        try {
            String codigo = servletRequest.getParameter("codigo");
            Integer idSentido = Integer.valueOf(servletRequest.getParameter(
                    "idSentido"));
            Integer enIni = Integer.valueOf(servletRequest.getParameter("enIni"));
            DocumentoLocal documentoLocal = LocalizadorServicios.getInstance().
                                            getDocumentoLocal();
            documentoLocal.insertaTipoDoc(codigo, idSentido, enIni);
            servletRequest.setAttribute("mensaje",
                                        "El tipo de documento fue insertado exitosamente");
            return adminTiposDoc(actionMapping, servletRequest, httpSession,
                                 servletResponse);
        } catch (Exception ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo ingresar el tipo de documento");
            return actionMapping.findForward("mensaje_fw");
        }

    }

    /**
     * adminTiposDoc
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param servletResponse HttpServletResponse
     * @return ActionForward
     */
    private ActionForward adminTiposDoc(ActionMapping actionMapping,
                                        HttpServletRequest servletRequest,
                                        HttpSession httpSession,
                                        HttpServletResponse servletResponse) {

        try {
            DocumentoLocal documentoLocal = LocalizadorServicios.getInstance().
                                            getDocumentoLocal();
            servletRequest.setAttribute("entrantes",
                                        documentoLocal.getTiposDoc(new
                    Integer(1)));
            servletRequest.setAttribute("salientes",
                                        documentoLocal.getTiposDoc(new
                    Integer(2)));
            return actionMapping.findForward("adminTiposDoc_fw");
        } catch (Exception ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la pagina de administración de tipos de documento");
            return actionMapping.findForward("mensaje_fw");
        }

    }

    /**
     * agregarArchivoDoc
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param servletResponse HttpServletResponse
     * @return ActionForward
     */
    private ActionForward agregarArchivoDoc(ActionMapping actionMapping,
                                            HttpServletRequest servletRequest,
                                            HttpSession httpSession,
                                            ActionForm actionForm,
                                            HttpServletResponse servletResponse) {
        try {

            DocumentoLocal documentoLocal = LocalizadorServicios.getInstance().
                                            getDocumentoLocal();
            Integer idDoc = Integer.valueOf(servletRequest.getParameter("idDoc"));
            RegDocFormBean docAF = (RegDocFormBean) actionForm; // Se llama al bean que recibe los archivos
            FormFile eldoc = docAF.getEldoc();
            ArchivoDocVO arch = new ArchivoDocVO();
            arch.setArchivoFile(eldoc);
            documentoLocal.agregarArchivoDoc(arch, idDoc);
            servletRequest.setAttribute("mensaje",
                                        "El archivo fue agregado exitosamente");
            return aadminArchivosDoc(actionMapping, servletRequest, httpSession,
                                     servletResponse);

        } catch (Exception ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "El archivo no pudo agregado");
            return actionMapping.findForward("mensaje_fw");
        }
    }

    /**
     * borrarArchivosDoc
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param servletResponse HttpServletResponse
     * @return ActionForward
     */
    private ActionForward borrarArchivosDoc(ActionMapping actionMapping,
                                            HttpServletRequest servletRequest,
                                            HttpSession httpSession,
                                            HttpServletResponse servletResponse) {
        try {
            Integer idArchivo = Integer.valueOf(servletRequest.getParameter(
                    "idArchivo"));
            DocumentoLocal documentoLocal = LocalizadorServicios.getInstance().
                                            getDocumentoLocal();
            documentoLocal.borrarArchivoDoc(idArchivo);
            servletRequest.setAttribute("mensaje",
                                        "El archivo fue eliminado exitosamente");
            return aadminArchivosDoc(actionMapping, servletRequest, httpSession,
                                     servletResponse);
        } catch (Exception ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "El archivo no pudo ser eliminado");
            return actionMapping.findForward("mensaje_fw");
        }

    }

    /**
     * aadminArchivosDoc
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param servletResponse HttpServletResponse
     * @return ActionForward
     */
    private ActionForward aadminArchivosDoc(ActionMapping actionMapping,
                                            HttpServletRequest servletRequest,
                                            HttpSession httpSession,
                                            HttpServletResponse servletResponse) {
        try {
            Integer idDoc = Integer.valueOf(servletRequest.getParameter(
                    "idDoc"));
            String tipoDoc = servletRequest.getParameter("tipoDoc");
            String codDoc = servletRequest.getParameter("codDoc");
            DocumentoLocal documentoLocal = LocalizadorServicios.getInstance().
                                            getDocumentoLocal();

            servletRequest.setAttribute("archivos",
                                        documentoLocal.getArchivosDeDoc(idDoc));
            servletRequest.setAttribute("idDoc", idDoc);
            servletRequest.setAttribute("tipoDoc", tipoDoc);
            servletRequest.setAttribute("codDoc", codDoc);
            return actionMapping.findForward("adminArchivosDeDoc_fw");
        } catch (Exception ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la pag. de administración de archivos");
            return actionMapping.findForward("mensaje_fw");
        }

    }

    /**
     * borrarDoc
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param servletResponse HttpServletResponse
     * @return ActionForward
     */
    private ActionForward borrarDoc(ActionMapping actionMapping,
                                    HttpServletRequest servletRequest,
                                    HttpSession httpSession,
                                    HttpServletResponse servletResponse) {

        try {
            Integer id_doc = Integer.valueOf(servletRequest.getParameter(
                    "id_doc"));
            DocumentoLocal documentoLocal = LocalizadorServicios.getInstance().
                                            getDocumentoLocal();

            String mensaje = documentoLocal.borrarDocumento(id_doc);
            servletRequest.setAttribute("mensaje",
                                        mensaje);
            return actionMapping.findForward("mensaje_fw");

        } catch (Exception ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "El documento no pudo ser eliminado");
            return actionMapping.findForward("mensaje_fw");
        }

    }


    /**
     * actualizarDoc
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param servletResponse HttpServletResponse
     * @return ActionForward
     */
    private ActionForward actualizarDoc(ActionMapping actionMapping,
                                        HttpServletRequest servletRequest,
                                        HttpSession httpSession,
                                        HttpServletResponse servletResponse) {
        try {
            Integer idDoc = Integer.valueOf(servletRequest.getParameter(
                    "idDoc"));
            Integer idCli = Integer.valueOf(servletRequest.getParameter("idCli"));
            String materia = servletRequest.getParameter("materia");
            String fechaIn = servletRequest.getParameter("fecha_IO");
            String fechaDoc = servletRequest.getParameter("fecha_doc");
            //++
            Integer[] idDocAnt = null;
            if (servletRequest.getParameterValues("listaRel") != null) {
                if (!servletRequest.getParameterValues("listaRel").equals("")) {
                    String[] idAnt = servletRequest.getParameterValues(
                            "listaRel");
                    idDocAnt = new Integer[idAnt.length];
                    for (int i = 0; i < idAnt.length; i++) {
                        idDocAnt[i] = Integer.valueOf(idAnt[i]);
                    }
                }
            }
            String[] respons = servletRequest.getParameterValues("list1");
            Integer[] responsables = new Integer[respons.length];
            for (int i = 0; i < respons.length; i++) {
                responsables[i] = Integer.valueOf(respons[i]);
            }

            //String resumen = servletRequest.getParameter("descrip");

            DocumentoInVO newDoc = new DocumentoInVO();

            newDoc.setIdDoc(idDoc);
            newDoc.setIdRemitente(idCli);
            newDoc.setMat(materia);
            newDoc.setFechaIn(fechaIn);
            newDoc.setFechaDoc(fechaDoc);
            newDoc.setAnt(idDocAnt);
            newDoc.setResponsable(responsables);
            //newDoc.setResumen(resumen);

            DocumentoLocal documentoLocal = LocalizadorServicios.getInstance().
                                            getDocumentoLocal();
            if (documentoLocal.actualizaDoc(newDoc)) {
                servletRequest.setAttribute("mensaje",
                                            "El documento fue actualizado exitosamente");
                return actionMapping.findForward("mensaje_fw");
            }
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo actualizar el documento");
            return actionMapping.findForward("mensaje_fw");
        }
        servletRequest.setAttribute("mensaje",
                                    "No se pudo actualizar el Documento");
        return actionMapping.findForward("mensaje_fw");
    }

    /**
     * aeditarDoc
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param servletResponse HttpServletResponse
     * @return ActionForward
     */
    private ActionForward aeditarDoc(ActionMapping actionMapping,
                                     HttpServletRequest servletRequest,
                                     HttpSession httpSession,
                                     HttpServletResponse servletResponse) {

        try {
            Integer id_doc = Integer.valueOf(servletRequest.getParameter(
                    "id_doc"));

            DocumentoLocal documentoLocal = null;
            UsuarioLocal usuarioLocal = null;

            documentoLocal = LocalizadorServicios.getInstance().
                             getDocumentoLocal();
            usuarioLocal = LocalizadorServicios.getInstance().
                           getUsuarioLocal();

            DocumentoVO doc = documentoLocal.getDetalleDoc(id_doc);

            List listausu = usuarioLocal.getListaNomUsu();
            servletRequest.setAttribute("listausu", listausu);

            servletRequest.setAttribute("detDoc", doc);

            return actionMapping.findForward("editarDoc_fw");
        } catch (Exception ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la pagina para Editar Documentos");
            return actionMapping.findForward("mensaje_fw");

        }

    }

    /**
     * abuscaParaEditarDoc
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param servletResponse HttpServletResponse
     * @return ActionForward
     */
    private ActionForward abuscaParaEditarDoc(ActionMapping actionMapping,
                                              HttpServletRequest servletRequest,
                                              HttpSession httpSession,
                                              HttpServletResponse
                                              servletResponse) {
        try {

            DocumentoLocal documentoLocal = LocalizadorServicios.
                                            getInstance().
                                            getDocumentoLocal();
            UsuarioLocal usuarioLocal = LocalizadorServicios.getInstance().
                                        getUsuarioLocal();
            servletRequest.setAttribute("listausu",
                                        usuarioLocal.getListaUsuarios());
            servletRequest.setAttribute("tiposentrantes",
                                        documentoLocal.
                                        getTiposDoc(new Integer(1)));
            servletRequest.setAttribute("tipossalientes",
                                        documentoLocal.
                                        getTiposDoc(new Integer(2)));
            return actionMapping.findForward("busDocparaEditar_fw");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la pagina para búsqueda");
            return actionMapping.findForward("mensaje_fw");
        }
    }

    /**
     * buscaParaEditarDoc
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param servletResponse HttpServletResponse
     * @return ActionForward
     */
    private ActionForward buscaParaEditarDoc(ActionMapping actionMapping,
                                             HttpServletRequest servletRequest,
                                             HttpSession httpSession,
                                             HttpServletResponse
                                             servletResponse) {

        UsuarioLocal usuarioLocal = null;
        DocumentoLocal documentoLocal = null;
        try {
            usuarioLocal = LocalizadorServicios.getInstance().
                           getUsuarioLocal();

            documentoLocal = LocalizadorServicios.getInstance().
                             getDocumentoLocal();

            List tiposentrantes = documentoLocal.getTiposDoc(new Integer(1));
            List tipossalientes = documentoLocal.getTiposDoc(new Integer(2));
            List listausu = usuarioLocal.getListaNomUsu();

            BusDocsVO busdocs = new BusDocsVO();
            boolean enIni = false;
            if (servletRequest.getParameter("tipoBus").equals("1")) {
                busdocs.setIdTipoDoc(Integer.valueOf(servletRequest.
                        getParameter(
                                "id_tipoDoc")));
                busdocs.setCodigoDoc(servletRequest.getParameter("codDoc"));
                busdocs.setEnIniciativas(true);
            }
            if (servletRequest.getParameter("tipoBus").equals("2")) {
                busdocs.setIdSentido(Integer.valueOf(servletRequest.
                        getParameter("id_sentido")));

                String enI = servletRequest.getParameter("enIni");
                if (enI != null) {
                    enIni = true;
                }
                busdocs.setEnIniciativas(enIni);

            }
            busdocs.setMateria(servletRequest.getParameter("materia"));
            busdocs.setFechaIni(servletRequest.getParameter("fecha_ini"));
            busdocs.setFechaFin(servletRequest.getParameter("fecha_fin"));
            busdocs.setPalClave(servletRequest.getParameter("palClave"));
            busdocs.setEncargado(Integer.valueOf(servletRequest.
                                                 getParameter("id_usuario")));

//OBTIENE LA LISTA DE DOCUMENTOS COINCIDENTES CON LOS PARAMETROS(ACTIVOS E INACTIVOS)
            List listadocs = documentoLocal.buscarDocumentos(busdocs);
            servletRequest.setAttribute("listadocs", listadocs);

            servletRequest.setAttribute("tiposentrantes", tiposentrantes);
            servletRequest.setAttribute("tipossalientes", tipossalientes);
            servletRequest.setAttribute("listausu", listausu);

            return actionMapping.findForward("busDocparaEditar_fw");

        } catch (LocalizadorServiciosException ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo buscar Documentos");
            return actionMapping.findForward("mensaje_fw");
        }

    }


    /**
     * descargarArchivoDoc
     * busca el archivo en la bd y abre la ventana de dialogo para descargarlo
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param response HttpServletResponse
     * @return ActionForward
     */
    private ActionForward descargarArchivoDoc(ActionMapping actionMapping,
                                              HttpServletRequest servletRequest,
                                              HttpSession httpSession,
                                              HttpServletResponse response) {

        try {

            DocumentoLocal documentoLocal = LocalizadorServicios.getInstance().
                                            getDocumentoLocal();
            Integer idArchivo = Integer.valueOf(servletRequest.getParameter(
                    "idArchivo"));
            ArchivoDocVO a = documentoLocal.descargaArchivoDoc(idArchivo);
            byte[] b = a.getArchivo();

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            output.write(b, 0, b.length);

            response.setContentType("application/download");
            response.setHeader("content-disposition",
                               "attachment; fileName=\"" + a.getNomArchivo() +
                               "\"");
            response.setContentLength(output.size());

            //Escribir el archivo en el response
            OutputStream out = response.getOutputStream();
            output.writeTo(out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo Descargar el curriculum");
            return actionMapping.findForward("mensaje_fw");

        }
        return null;
    }


    /**
     * detalleDoc
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward detalleDoc(ActionMapping actionMapping,
                                     HttpServletRequest servletRequest,
                                     HttpSession httpSession) {
        try {
            Integer idDoc = Integer.valueOf(servletRequest.getParameter(
                    "id_doc"));
            DocumentoLocal documentoLocal = null;

            documentoLocal = LocalizadorServicios.getInstance().
                             getDocumentoLocal();

            DocumentoVO doc = documentoLocal.getDetalleDoc(idDoc);

            if (doc == null) {
                servletRequest.setAttribute("mensaje",
                                            "No se pudo cargar el detalle del Documento");
                return actionMapping.findForward("mensaje_fw");
            } else {
                servletRequest.setAttribute("detDoc", doc);

                return actionMapping.findForward("detDoc_fw");
            }

        } catch (LocalizadorServiciosException ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar el detalle del Documento");
            return actionMapping.findForward("mensaje_fw");
        }

    }


    /**
     * ingresarDocumento
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward ingresarDocumento(ActionMapping actionMapping,
                                            HttpServletRequest servletRequest,
                                            ActionForm actionForm,
                                            HttpSession httpSession) {

        try {

            servletRequest.setCharacterEncoding("ISO-8859-1");
            Integer idTipoDoc = Integer.valueOf(servletRequest.getParameter(
                    "idTipoDoc"));
            String codDoc = servletRequest.getParameter("codDoc");
            Integer idCli = Integer.valueOf(servletRequest.getParameter("idCli"));
            String materia = servletRequest.getParameter("materia");
            String fechaIn = servletRequest.getParameter("fecha_IO");
            String fechaDoc = servletRequest.getParameter("fecha_doc");

            //++
            Integer[] idDocAnt = null;
            if (servletRequest.getParameterValues("listaRel") != null) {
                String[] idAnt = servletRequest.getParameterValues("listaRel");
                idDocAnt = new Integer[idAnt.length];
                for (int i = 0; i < idAnt.length; i++) {
                    idDocAnt[i] = Integer.valueOf(idAnt[i]);
                }
            }

            String[] respons = servletRequest.getParameterValues("list1");
            Integer[] responsables = new Integer[respons.length];
            for (int i = 0; i < respons.length; i++) {
                responsables[i] = Integer.valueOf(respons[i]);
            }

            // String resumen = servletRequest.getParameter("descrip");

            RegDocFormBean docAF = (RegDocFormBean) actionForm; // Se llama al bean que recibe los Documentos
            FormFile eldoc = docAF.getEldoc();
            FormFile eldoc1 = docAF.getEldoc1();
            FormFile eldoc2 = docAF.getEldoc2();

            docAF.reset();
            DocumentoInVO newDoc = new DocumentoInVO();

            newDoc.setIdTipoDoc(idTipoDoc);
            newDoc.setCodDoc(codDoc);
            newDoc.setIdRemitente(idCli);
            newDoc.setMat(materia);
            newDoc.setFechaIn(fechaIn);
            newDoc.setFechaDoc(fechaDoc);
            newDoc.setAnt(idDocAnt);
            newDoc.setResponsable(responsables);
            //newDoc.setResumen(resumen);

            newDoc.setFile(eldoc);
            newDoc.setFile1(eldoc1);
            newDoc.setFile2(eldoc2);

            DocumentoLocal documentoLocal = LocalizadorServicios.getInstance().
                                            getDocumentoLocal();
            if (documentoLocal.insertarDocumento(newDoc)) {
                servletRequest.setAttribute("mensaje",
                                            "El documento fue ingresado exitosamente");
                return actionMapping.findForward("mensaje_fw");
            }
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo Ingresar el Documento");
            return actionMapping.findForward("mensaje_fw");
        }
        servletRequest.setAttribute("mensaje",
                                    "No se pudo Ingresar el Documento");
        return actionMapping.findForward("mensaje_fw");
    }

    /**
     * BuscarDocsPop
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward BuscarDocsPop(ActionMapping actionMapping,
                                        HttpServletRequest servletRequest,
                                        HttpSession httpSession) {
        try {
            BusDocsVO busdocs = new BusDocsVO();
            String tipoBus = servletRequest.getParameter("tipoBus"); // el tipo 1 es por codigo del doc, y 2 es por sentido
            String materia = servletRequest.getParameter("materia");
            busdocs.setMateria(materia);
            boolean enIni = false;
            if (tipoBus.equals("1")) {
                Integer idTipoDoc = Integer.valueOf(servletRequest.getParameter(
                        "idTipoDoc"));
                String codDoc = servletRequest.getParameter("codigoDoc");
                busdocs.setIdTipoDoc(idTipoDoc);
                busdocs.setCodigoDoc(codDoc);
                busdocs.setEnIniciativas(true);
            }

            if (tipoBus.equals("2")) {
                busdocs.setIdSentido(Integer.valueOf(servletRequest.
                        getParameter("id_sentido")));
                String enI = servletRequest.getParameter("enIni");
                if (enI != null) {
                    enIni = true;
                }
                busdocs.setEnIniciativas(enIni);
            }

            busdocs.setFechaIni(servletRequest.getParameter("fecha_ini"));
            busdocs.setFechaFin(servletRequest.getParameter("fecha_fin"));

            busdocs.setEncargado(Integer.valueOf(servletRequest.
                                                 getParameter("id_usuario")));

            busdocs.setPalClave(servletRequest.getParameter("palClave"));

            DocumentoLocal documentoLocal = LocalizadorServicios.getInstance().
                                            getDocumentoLocal();

            servletRequest.setAttribute("listadocs",
                                        documentoLocal.buscarDocumentos(busdocs));
            return aBuscarDocsPop(actionMapping, servletRequest, httpSession);

        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo realizar la búsqueda");
            return actionMapping.findForward("mensaje_fw");
        }

    }

    /**
     * aBuscarDocsPop
     *
     * Carga la pagina de busqueda de documentos en Pop-up(con la opción de "seleccionar"
     * para que se pasen los datos del documento escogido a la ventana que abrio el pop-up
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward aBuscarDocsPop(ActionMapping actionMapping,
                                         HttpServletRequest servletRequest,
                                         HttpSession httpSession) {

        try {

            DocumentoLocal documentoLocal = LocalizadorServicios.
                                            getInstance().
                                            getDocumentoLocal();
            UsuarioLocal usuarioLocal = LocalizadorServicios.getInstance().
                                        getUsuarioLocal();
            servletRequest.setAttribute("listausu",
                                        usuarioLocal.getListaUsuarios());
            servletRequest.setAttribute("tiposentrantes",
                                        documentoLocal.
                                        getTiposDoc(new Integer(1)));
            servletRequest.setAttribute("tipossalientes",
                                        documentoLocal.
                                        getTiposDoc(new Integer(2)));
            return actionMapping.findForward("buscarDocs_pop");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la pagina para búsqueda");
            return actionMapping.findForward("mensaje_fw");
        }

    }

    /**
     * creaExcel
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession

     * @return ActionForward
     */
    private ActionForward creaExcel(ActionMapping actionMapping,
                                    HttpServletRequest servletRequest,
                                    HttpSession httpSession) {

        List listadocs = (List) httpSession.getAttribute("ses_listadocs");
        servletRequest.setAttribute("listadocs", listadocs);
        return actionMapping.findForward("crearExcel_fw");
    }

    /**
     * goBusDoccod
     *  busca documentos cuando se escoge buscar por codigo.
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward goBusDoccod(ActionMapping actionMapping,
                                      HttpServletRequest servletRequest,
                                      HttpSession httpSession) {
        UsuarioLocal usuarioLocal = null;
        DocumentoLocal documentoLocal = null;
        try {
            usuarioLocal = LocalizadorServicios.getInstance().
                           getUsuarioLocal();

            documentoLocal = LocalizadorServicios.getInstance().
                             getDocumentoLocal();

            List tiposentrantes = documentoLocal.getTiposDoc(new Integer(1));
            List tipossalientes = documentoLocal.getTiposDoc(new Integer(2));
            List listausu = usuarioLocal.getListaNomUsu();

            BusDocsVO busdocs = new BusDocsVO();

            busdocs.setIdTipoDoc(Integer.valueOf(servletRequest.getParameter(
                    "id_tipoDoc")));
            busdocs.setCodigoDoc(servletRequest.getParameter("codDoc"));
            busdocs.setMateria(servletRequest.getParameter("materia"));
            busdocs.setFechaIni(servletRequest.getParameter("fecha_ini"));
            busdocs.setFechaFin(servletRequest.getParameter("fecha_fin"));
            busdocs.setPalClave(servletRequest.getParameter("palClave"));
            busdocs.setEncargado(Integer.valueOf(servletRequest.
                                                 getParameter("id_usuario")));

            busdocs.setEnIniciativas(true);

            List listadocs = documentoLocal.buscarDocumentos(busdocs);
            servletRequest.setAttribute("listadocs", listadocs);

            httpSession.setAttribute("ses_listadocs", listadocs); //pone la lista en la session para poder ordenarla si se necesita

            servletRequest.setAttribute("tiposentrantes", tiposentrantes);
            servletRequest.setAttribute("tipossalientes", tipossalientes);
            servletRequest.setAttribute("listausu", listausu);

            return actionMapping.findForward("abusDoc_fw");

        } catch (LocalizadorServiciosException ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo buscar Documentos");
            return actionMapping.findForward("mensaje_fw");
        }
    }

    /**
     * goBusDocSen
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward goBusDocSen(ActionMapping actionMapping,
                                      HttpServletRequest servletRequest,
                                      HttpSession httpSession) {
        UsuarioLocal usuarioLocal = null;
        DocumentoLocal documentoLocal = null;
        try {
            usuarioLocal = LocalizadorServicios.getInstance().
                           getUsuarioLocal();

            documentoLocal = LocalizadorServicios.getInstance().
                             getDocumentoLocal();

            List tiposentrantes = documentoLocal.getTiposDoc(new Integer(1));
            List tipossalientes = documentoLocal.getTiposDoc(new Integer(2));
            List listausu = usuarioLocal.getListaNomUsu();

            BusDocsVO busdocs = new BusDocsVO();
            busdocs.setIdSentido(Integer.valueOf(servletRequest.
                                                 getParameter("id_sentido")));
            busdocs.setMateria(servletRequest.getParameter("materia"));
            busdocs.setFechaIni(servletRequest.getParameter("fecha_ini"));
            busdocs.setFechaFin(servletRequest.getParameter("fecha_fin"));
            busdocs.setPalClave(servletRequest.getParameter("palClave"));

            busdocs.setEncargado(Integer.valueOf(servletRequest.
                                                 getParameter("id_usuario")));

            boolean enIni = false;
            String enI = servletRequest.getParameter("enIni");
            if (enI != null) {
                enIni = true;
            }
            busdocs.setEnIniciativas(enIni);

            List listadocs = documentoLocal.buscarDocumentos(busdocs);
            servletRequest.setAttribute("listadocs", listadocs);

            // httpSession.setAttribute("ses_listadocs", listadocs); //pone la lista en la session para poder ordenarla si se necesita

            servletRequest.setAttribute("tiposentrantes", tiposentrantes);
            servletRequest.setAttribute("tipossalientes", tipossalientes);
            servletRequest.setAttribute("listausu", listausu);

            return actionMapping.findForward("abusDoc_fw");

        } catch (LocalizadorServiciosException ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo buscar Documentos");
            return actionMapping.findForward("mensaje_fw");
        }

    }

    /**
     * cargarbusDoc
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward cagarbusDoc(ActionMapping actionMapping,
                                      HttpServletRequest servletRequest,
                                      HttpSession httpSession) {

        try {
            UsuarioLocal usuarioLocal = null;
            DocumentoLocal documentoLocal = null;

            usuarioLocal = LocalizadorServicios.getInstance().
                           getUsuarioLocal();

            documentoLocal = LocalizadorServicios.getInstance().
                             getDocumentoLocal();

            List tiposentrantes = documentoLocal.getTiposDoc(new Integer(1));
            List tipossalientes = documentoLocal.getTiposDoc(new Integer(2));
            List listausu = usuarioLocal.getListaNomUsu();
            servletRequest.setAttribute("tiposentrantes", tiposentrantes);
            servletRequest.setAttribute("tipossalientes", tipossalientes);
            servletRequest.setAttribute("listausu", listausu);
            return actionMapping.findForward("abusDoc_fw");

        } catch (LocalizadorServiciosException ex) {
            ex.printStackTrace();
        }
        servletRequest.setAttribute("mensaje",
                                    "No se pudo cargar la pagina de busqueda de Documentos");
        return actionMapping.findForward("mensaje_fw");
    }

//-----------carga la pagina de Registro de Documentos Entrantes

    private ActionForward cargarRegDocEntrante(ActionMapping actionMapping,
                                               HttpServletRequest
                                               servletRequest,
                                               HttpSession httpsession) {

        DocumentoLocal documentoLocal = null;
        UsuarioLocal usuarioLocal = null;
        ClienteLocal clienteLocal = null;
        try {
            documentoLocal = LocalizadorServicios.getInstance().
                             getDocumentoLocal();

            usuarioLocal = LocalizadorServicios.getInstance().
                           getUsuarioLocal();

            clienteLocal = LocalizadorServicios.getInstance().
                           getClienteLocal();

        } catch (LocalizadorServiciosException ex) {
            System.out.println("no se pudo llamar a EJB");
        }

        List tiposentrantes = documentoLocal.getTiposDoc(new Integer(1));
        List tipossalientes = documentoLocal.getTiposDoc(new Integer(2));

        List listausu = usuarioLocal.getListaNomUsu();

        servletRequest.setAttribute("mensaje",
                                    servletRequest.getAttribute("mensaje"));
        servletRequest.setAttribute("listausu", listausu);
        servletRequest.setAttribute("tiposentrantes", tiposentrantes);
        servletRequest.setAttribute("tipossalientes", tipossalientes);
        return actionMapping.findForward("RegDocEntrante_fw");
    }

//-----------carga la pagina de Registro de Documentos Salientes
    private ActionForward cargarRegDocSaliente(ActionMapping actionMapping,
                                               HttpServletRequest
                                               servletRequest,
                                               HttpSession httpsession) {
        DocumentoLocal documentoLocal = null;
        UsuarioLocal usuarioLocal = null;
        ClienteLocal clienteLocal = null;
        try {
            documentoLocal = LocalizadorServicios.getInstance().
                             getDocumentoLocal();

            usuarioLocal = LocalizadorServicios.getInstance().
                           getUsuarioLocal();

            clienteLocal = LocalizadorServicios.getInstance().
                           getClienteLocal();

        } catch (LocalizadorServiciosException ex) {
            System.out.println("no se pudo llamar a EJB");
        }

        List tiposentrantes = documentoLocal.getTiposDoc(new Integer(1));
        List tipossalientes = documentoLocal.getTiposDoc(new Integer(2));

        List listausu = usuarioLocal.getListaNomUsu();

        servletRequest.setAttribute("mensaje",servletRequest.getAttribute("mensaje"));
        servletRequest.setAttribute("listausu", listausu);
        servletRequest.setAttribute("tiposentrantes", tiposentrantes);
        servletRequest.setAttribute("tipossalientes", tipossalientes);

        return actionMapping.findForward("RegDocSaliente_fw");
    }

//------------------------------------------



    private void jbInit() throws Exception {
    }

}

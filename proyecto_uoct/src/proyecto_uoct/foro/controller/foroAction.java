package proyecto_uoct.foro.controller;

import java.text.*;
import java.util.*;

import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.upload.*;
import proyecto_uoct.common.*;
import proyecto_uoct.common.VO.*;
import proyecto_uoct.foro.VO.*;
import proyecto_uoct.foro.model.*;
import java.io.ByteArrayOutputStream;
import proyecto_uoct.documentacion.model.DocumentoLocal;
import proyecto_uoct.documentacion.VO.ArchivoDocVO;
import java.io.OutputStream;

public class foroAction extends Action {

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public ActionForward execute(ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest servletRequest,
                                 HttpServletResponse servletResponse) {
        HttpSession httpSession;
        Integer ses_idusu;
        try {
            httpSession = servletRequest.getSession();
            DatosSesVO datosses = (DatosSesVO) httpSession.getAttribute(
                    "Ses_Usu");
            ses_idusu = (Integer) datosses.getId();
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "Su sesión ha caducado");
            return actionMapping.findForward("mensaje_fw");

        }

        String hacia = null;
        if (servletRequest.getParameter("hacia") != null) {
            hacia = servletRequest.getParameter("hacia");
        }else{
            servletRequest.setAttribute("mensaje", "Error al cargar la página. Si está subiendo un archivo, verifique que su tamaño no supera los 10 MB ");
            return actionMapping.findForward("mensaje_fw");
        }



        if (hacia.equals("aBuscarForo")) {
            return aBuscarForo(actionMapping, servletRequest,
                               httpSession, ses_idusu);

        }

        if (hacia.equals("aEditarForo")) {
            return aEditarForo(actionMapping, servletRequest,
                               httpSession, ses_idusu);

        }

        if (hacia.equals("buscarForo")) {
            return buscarForo(actionMapping, servletRequest,
                              httpSession, ses_idusu);

        }

        if (hacia.equals("aRegistrarForo")) {
            return aRegistrarForo(actionMapping, servletRequest,
                                  httpSession, ses_idusu);

        }

        if (hacia.equals("agregarForo")) {
            return agregarForo(actionMapping, servletRequest,
                               httpSession, ses_idusu);

        }

        if (hacia.equals("actualizarDescForo")) {
            return actualizarDescForo(actionMapping, servletRequest,
                                      httpSession, ses_idusu);

        }

        if (hacia.equals("detalleForo")) {
            return detalleForo(actionMapping, servletRequest,
                               httpSession, ses_idusu);

        }

        if (hacia.equals("aPostear")) {
            return aPostear(actionMapping, servletRequest,
                            httpSession, ses_idusu);

        }

        if (hacia.equals("ingresarPost")) {
            return ingresarPost(actionMapping, servletRequest,
                                httpSession, ses_idusu);

        }

        //---------- DOCUMENTO DE FORO --------------

        if (hacia.equals("borrarDocForo")) {
            return borrarDocForo(actionMapping, servletRequest,
                                 httpSession, ses_idusu);

        }

        if (hacia.equals("insertDoc")) {
            return insertDoc(actionMapping, servletRequest,
                             httpSession, ses_idusu, actionForm);

        }

        if (hacia.equals("descargarDoc")) {
                   return descargarDoc(actionMapping, servletRequest,
                                    httpSession, ses_idusu, servletResponse);

               }



        //-------------   TEMAS---------------

        if (hacia.equals("agregarTema")) {
            return agregarTema(actionMapping, servletRequest,
                               httpSession, ses_idusu);

        }

        if (hacia.equals("adminTemas")) {
            return adminTemas(actionMapping, servletRequest,
                              httpSession, ses_idusu);

        }

        if (hacia.equals("borrarTema")) {
            return borrarTema(actionMapping, servletRequest,
                              httpSession, ses_idusu);

        }

        return actionMapping.getInputForward();
    }

    /**
     * descargarDoc
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param ses_idusu Integer
     * @param actionForm ActionForm
     * @return ActionForward
     */
    private ActionForward descargarDoc(ActionMapping actionMapping,
                                       HttpServletRequest servletRequest,
                                       HttpSession httpSession,
                                       Integer ses_idusu, HttpServletResponse response) {
        try {

            ForosLocal forosLocal = LocalizadorServicios.getInstance().getForosLocal();

            Integer idArchivo = Integer.valueOf(servletRequest.getParameter(
                    "idDoc"));
            DocForoVO a = forosLocal.descargaDocForo(idArchivo);
            byte[] b = a.getDocumentoByte();

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            output.write(b, 0, b.length);

            response.setContentType("application/download");
            response.setHeader("content-disposition",
                               "attachment; fileName=\"" + a.getTituloDoc() +
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
                                        "No se pudo Descargar el archivo");
            return actionMapping.findForward("mensaje_fw");

        }
        return null;    }

    /**
     * ingresarPost
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param ses_idusu Integer
     * @return ActionForward
     */
    private ActionForward ingresarPost(ActionMapping actionMapping,
                                       HttpServletRequest servletRequest,
                                       HttpSession httpSession,
                                       Integer ses_idusu) {

        try {

            String id_foro_s = servletRequest.getParameter("id_foro");
            Integer id_foro = null;
            id_foro = id_foro.valueOf(id_foro_s);

            String comentario = servletRequest.getParameter("comentario");

            ForosLocal forosLocal = LocalizadorServicios.getInstance().getForosLocal();

            forosLocal.ingresarPost(id_foro, ses_idusu, comentario);

            servletRequest.setAttribute("id_foro", id_foro);
            return detalleForoIdAtributo(actionMapping, servletRequest,
                                         httpSession, ses_idusu);
        } catch (Exception ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo ingresar el Post");
            return actionMapping.findForward("mensaje_fw");
        }

    }

    /**
     * aPostear
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param ses_idusu Integer
     * @return ActionForward
     */
    private ActionForward aPostear(ActionMapping actionMapping,
                                   HttpServletRequest servletRequest,
                                   HttpSession httpSession, Integer ses_idusu) {

        try {
            String id_foro_s = servletRequest.getParameter("id_foro");
            Integer id_foro = null;
            id_foro = id_foro.valueOf(id_foro_s);
            DetForoVO detforo = null;
            ForosLocal forosLocal = null;
            forosLocal = LocalizadorServicios.getInstance().getForosLocal();
            detforo = forosLocal.getDetForo(id_foro);

            servletRequest.setAttribute("id_foro", id_foro);
            servletRequest.setAttribute("detforo", detforo);
            return actionMapping.findForward("postear_fw");
        } catch (Exception ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la pag. para postear en el foro");
            return actionMapping.findForward("mensaje_fw");
        }
    }

    /**
     * aRegistrarForo
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param ses_idusu Integer
     * @return ActionForward
     */
    private ActionForward aRegistrarForo(ActionMapping actionMapping,
                                         HttpServletRequest servletRequest,
                                         HttpSession httpSession,
                                         Integer ses_idusu) {
        try {

            TemaForoLocal temaforo = LocalizadorServicios.getInstance().
                                     getTemaForoLocal();
            servletRequest.setAttribute("listadetemas", temaforo.getTemasForo());
            return actionMapping.findForward("ingresarForo_fw");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la pag. de edición del foro");
            return actionMapping.findForward("mensaje_fw");

        }

    }

    /**
     * aBuscarForo
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param ses_idtipousu Integer
     * @param ses_idusu Integer
     * @return ActionForward
     */
    private ActionForward aBuscarForo(ActionMapping actionMapping,
                                      HttpServletRequest servletRequest,
                                      HttpSession httpSession,
                                      Integer ses_idusu) {
        try {
            TemaForoLocal temaforo = LocalizadorServicios.getInstance().
                                     getTemaForoLocal();
            servletRequest.setAttribute("temas", temaforo.getTemasForo());
            return actionMapping.findForward("buscarForo_fw");
        } catch (Exception ex) {
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la pag. de edición del foro");
            return actionMapping.findForward("mensaje_fw");
        }
    }

    /**
     * insertDoc
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param ses_idtipousu Integer
     * @param ses_idusu Integer
     * @return ActionForward
     */
    private ActionForward insertDoc(ActionMapping actionMapping,
                                    HttpServletRequest servletRequest,
                                    HttpSession httpSession,
                                    Integer ses_idusu, ActionForm actionForm) {

        try {
            Integer id_foro = Integer.valueOf(servletRequest.getParameter(
                    "id_foro"));
            InsertDocForoFB fb = (InsertDocForoFB) actionForm;
            FormFile nuevoDoc = fb.getUndoc();

            DocForoVO docforo = new DocForoVO();
            docforo.setDocumento(nuevoDoc);
            docforo.setIdForo(id_foro);

            ForosLocal forosLocal = LocalizadorServicios.getInstance().
                                    getForosLocal();

            forosLocal.registrarDocumentoForo(docforo);

            servletRequest.setAttribute("mensaje",
                                        "El documento fue registrado exitosamente");
            return detalleForo(actionMapping,servletRequest,httpSession,id_foro);
        } catch (Exception ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo registrar el documento");
            return detalleForo(actionMapping,servletRequest,httpSession,ses_idusu);
        }
    }

    /**
     * aEditarForo
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param ses_idtipousu Integer
     * @param ses_idusu Integer
     * @return ActionForward
     */
    private ActionForward aEditarForo(ActionMapping actionMapping,
                                      HttpServletRequest servletRequest,
                                      HttpSession httpSession,
                                      Integer ses_idusu) {

        try {
            Integer id_foro = Integer.valueOf(servletRequest.getParameter(
                    "id_foro"));
            ForosLocal forosLocal = LocalizadorServicios.getInstance().
                                    getForosLocal();
            DetForoVO detForo = forosLocal.getDetForo(id_foro);
            List docsForo = forosLocal.getDocsForo(id_foro);
            servletRequest.setAttribute("detForo", detForo);
            servletRequest.setAttribute("docsForo", docsForo);
            return actionMapping.findForward("editarForo_fw");
        } catch (Exception ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la pag. de edición del foro");
            return actionMapping.findForward("mensaje_fw");
        }

    }

    /**
     * buscarForo
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param ses_idtipousu Integer
     * @param ses_idusu Integer
     * @return ActionForward
     */
    private ActionForward buscarForo(ActionMapping actionMapping,
                                     HttpServletRequest servletRequest,
                                     HttpSession httpSession,
                                     Integer ses_idusu) {

        try {

            ForosLocal forosLocal = LocalizadorServicios.getInstance().
                                    getForosLocal();

            String pal = servletRequest.getParameter("palabra");
            String fecha = servletRequest.getParameter("fecha");
            Integer idtema = null;
            if (!servletRequest.getParameter("id_tema").equals("")) {
                idtema = Integer.valueOf(servletRequest.getParameter("id_tema"));
            }

            List listaForos = forosLocal.buscarForos(pal, fecha, idtema);
            TemaForoLocal temaforo = LocalizadorServicios.getInstance().
                                     getTemaForoLocal();
            servletRequest.setAttribute("temas", temaforo.getTemasForo());
            servletRequest.setAttribute("listaForos", listaForos);
            return actionMapping.findForward("buscarForo_fw");
        } catch (LocalizadorServiciosException ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la busqueda de Foros");
            return actionMapping.findForward("mensaje_fw");
        }

    }

    /**
     * borrarTema
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param ses_idusu Integer
     * @return ActionForward
     */
    private ActionForward borrarTema(ActionMapping actionMapping,
                                     HttpServletRequest servletRequest,
                                     HttpSession httpSession,
                                     Integer ses_idusu) {
        try {
            Integer tema_del =Integer.valueOf(servletRequest.getParameter("id_tema"));

            TemaForoLocal temaforo = LocalizadorServicios.getInstance().
                                     getTemaForoLocal();
            if (!temaforo.borrarTema(tema_del.intValue())) {
                servletRequest.setAttribute("mensaje",
                                            "No se pudo eliminar el tema");
                return actionMapping.findForward("mensaje_fw");
            } else {
                servletRequest.setAttribute("mensaje",
                                            "El tema y sus foros fueron eliminados");
                return adminTemas(actionMapping, servletRequest,
                                  httpSession, ses_idusu);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo eliminar el tema");
            return actionMapping.findForward("mensaje_fw");
        }
    }

    /**
     * borrarDocForo
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param ses_idtipousu Integer
     * @param ses_idusu Integer
     * @return ActionForward
     */
    private ActionForward borrarDocForo(ActionMapping actionMapping,
                                        HttpServletRequest servletRequest,
                                        HttpSession httpSession,
                                        Integer ses_idusu) {

        String id_foro_s = servletRequest.getParameter("idForo");
        String id_doc_s = servletRequest.getParameter("idDoc");
        Integer id_doc = null;
        Integer id_foro = null;
        id_foro = id_foro.valueOf(id_foro_s);
        id_doc = id_doc.valueOf(id_doc_s);
        ForosLocal forosLocal = null;

        try {
            forosLocal = LocalizadorServicios.getInstance().getForosLocal();
        } catch (LocalizadorServiciosException ex) {
            System.out.println("no se pudo llamar a EJB");
        }

        if (forosLocal.borrarDocForo(id_doc)) {
            servletRequest.setAttribute("id_foro", id_foro);
            return detalleForoIdAtributo(actionMapping,servletRequest,httpSession,ses_idusu);
        } else {
            servletRequest.setAttribute("mensaje",
                                        "no se pudo eliminar el documento");
            return actionMapping.findForward("mensaje_fw");
        }
    }

    /**
     * agregarTema
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param ses_idtipousu Integer
     * @param ses_idusu Integer
     * @return ActionForward
     */
    private ActionForward agregarTema(ActionMapping actionMapping,
                                      HttpServletRequest servletRequest,
                                      HttpSession httpSession,
                                      Integer ses_idusu) {
        try {
            TemaForoLocal temaforo = null;
            String nuevoTema = (String) servletRequest.getParameter("nuevotema");

            temaforo = LocalizadorServicios.getInstance().getTemaForoLocal();
            temaforo.agregarTema(nuevoTema);
        } catch (LocalizadorServiciosException ex) {
            ex.printStackTrace();
        }

        return adminTemas(actionMapping, servletRequest,
                          httpSession, ses_idusu);

    }

    /**
     * agregarForo
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param ses_idtipousu Integer
     * @param ses_idusu Integer
     * @return ActionForward
     */

    private ActionForward agregarForo(ActionMapping actionMapping,
                                      HttpServletRequest servletRequest,
                                      HttpSession httpSession,
                                      Integer ses_idusu) {

        try {
            String id_tema_s = (String) servletRequest.getParameter("id_tema");
            String titforo = (String) servletRequest.getParameter("titForo");
            String descforo = (String) servletRequest.getParameter("descForo");
            String newpost = (String) servletRequest.getParameter("elPost");

            Integer id_tema_i = Integer.valueOf(id_tema_s);
            int id_newforo = 0;

            ForosLocal forosLocal = null;

            forosLocal = LocalizadorServicios.getInstance().getForosLocal();

            java.util.Date hoy1 = new Date();
            String hoy = sdf.format(hoy1);

            DetForoVO nuevof = new DetForoVO();
            nuevof.setIdTema(id_tema_i);
            nuevof.setTitForo(titforo);
            nuevof.setDescForo(descforo);
            nuevof.setIdUsu(ses_idusu);
            nuevof.setPost(newpost);
            nuevof.setFechaIni_s(hoy);

            id_newforo = forosLocal.agregarForo(nuevof);

            if (id_newforo != 0) {
                Integer int_newforo = null;
                int_newforo = new Integer(id_newforo);
                servletRequest.setAttribute("id_foro", int_newforo);
                return detalleForoIdAtributo(actionMapping, servletRequest,
                                             httpSession,
                                             ses_idusu);

            }

        } catch (LocalizadorServiciosException ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "no se pudo ingresar el nuevo foro");
            return actionMapping.findForward("mensaje_fw");

        }

        servletRequest.setAttribute("mensaje",
                                    "no se pudo ingresar el nuevo foro");
        return actionMapping.findForward("mensaje_fw");
    }

    /**
     * detalleForoIdAtributo
     * Carga la pagina del detalle de un foro recibiendo
     *  el id del foro que debe buscar como un "attributo" a diferencia de
     * DetalleForo que recibe el id como "parametro".
     * Esta funcion es usada para cargar el detalle
     * desde un action, detalleForo es usada para cargar el
     * detalle desde una JSP.
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param ses_idtipousu Integer
     * @param ses_idusu Integer
     * @return ActionForward
     */
    private ActionForward detalleForoIdAtributo(ActionMapping actionMapping,
                                                HttpServletRequest
                                                servletRequest,
                                                HttpSession httpSession,
                                                Integer ses_idusu) {

        try {
            Integer id_foro = (Integer) servletRequest.getAttribute("id_foro");
            DetForoVO detForo = null;
            List docsForo = null;
            List postForo = null;
            ForosLocal forosLocal = null;

            forosLocal = LocalizadorServicios.getInstance().getForosLocal();

            detForo = forosLocal.getDetForo(id_foro);
            docsForo = forosLocal.getDocsForo(id_foro);
            postForo = forosLocal.getPost(id_foro);
            Integer id_admin_foro = forosLocal.getAdminForo(id_foro);

            servletRequest.setAttribute("id_admin_foro", id_admin_foro);
            servletRequest.setAttribute("ses_idusu", ses_idusu);
            servletRequest.setAttribute("detForo", detForo);
            servletRequest.setAttribute("docsForo", docsForo);
            servletRequest.setAttribute("postForo", postForo);
        } catch (Exception ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la pag. del detalle del foro");
            return actionMapping.findForward("mensaje_fw");
        }
        return actionMapping.findForward("detForo_fw");
    }

    /**
     * adminTemas
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param ses_idtipousu Integer
     * @param ses_idusu Integer
     * @return ActionForward
     */
    private ActionForward adminTemas(ActionMapping actionMapping,
                                     HttpServletRequest servletRequest,
                                     HttpSession httpSession,
                                     Integer ses_idusu) {
        try {
            String temas = null;
            List listadetemas;
            TemaForoLocal temaforo = null;
            List listaForos = null;

            temaforo = LocalizadorServicios.getInstance().getTemaForoLocal();

            listadetemas = temaforo.getTemasForo();

            servletRequest.setAttribute("listadetemas", listadetemas);
        } catch (LocalizadorServiciosException ex) {
            ex.printStackTrace();
        }

        return actionMapping.findForward("adminTemas_fw");

    }

    /**
     * detalleForo
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param ses_idtipousu Integer
     * @return ActionForward
     */
    private ActionForward detalleForo(ActionMapping actionMapping,
                                      HttpServletRequest servletRequest,
                                      HttpSession httpSession,
                                      Integer ses_idusu) {

        try {
            String id_foro_s = (String) servletRequest.getParameter("id_foro");
            Integer id_foro = Integer.valueOf(id_foro_s);
            ForosLocal forosLocal = LocalizadorServicios.getInstance().
                                    getForosLocal();

            DetForoVO detForo = forosLocal.getDetForo(id_foro);
            List docsForo = forosLocal.getDocsForo(id_foro);
            List postForo = forosLocal.getPost(id_foro);
            Integer id_admin_foro = forosLocal.getAdminForo(id_foro);
            servletRequest.setAttribute("id_admin_foro", id_admin_foro);
            servletRequest.setAttribute("ses_idusu", ses_idusu);
            servletRequest.setAttribute("detForo", detForo);
            servletRequest.setAttribute("docsForo", docsForo);
            servletRequest.setAttribute("postForo", postForo);
            return actionMapping.findForward("detForo_fw");
        } catch (Exception ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la pag. del detalle del foro");
            return actionMapping.findForward("mensaje_fw");
        }

    }


    /**
     * actualizarDescForo actualiza la descripción de un foro
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @param ses_idtipousu Integer
     * @return ActionForward
     */
    private ActionForward actualizarDescForo(ActionMapping actionMapping,
                                             HttpServletRequest servletRequest,
                                             HttpSession httpSession,
                                             Integer ses_idusu) {

        try {
            String id_foro_s = servletRequest.getParameter("id_foro");
            Integer id_foro = null;
            id_foro = id_foro.valueOf(id_foro_s);
            String descForo = servletRequest.getParameter("descforo");

            ForosLocal forosLocal = LocalizadorServicios.getInstance().
                                    getForosLocal();

            if (forosLocal.actDescForo(id_foro, descForo)) {
                servletRequest.setAttribute("id_foro", id_foro);
            }
            return detalleForo(actionMapping, servletRequest,
                               httpSession, ses_idusu);
        } catch (LocalizadorServiciosException ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo actualizar el foro");
            return actionMapping.findForward("mensaje_fw");

        }

    }

}

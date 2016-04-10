package proyecto_uoct.infoyrep.controller;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.upload.*;
import proyecto_uoct.common.*;
import proyecto_uoct.infoyrep.VO.*;
import proyecto_uoct.infoyrep.model.*;
import proyecto_uoct.mensaje.model.MensajeLocal;


public class reportAction extends Action {
    public ActionForward execute(ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest servletRequest,
                                 HttpServletResponse servletResponse) {

        IngReportajeActionForm iRAF = (IngReportajeActionForm) actionForm;

        String hacia = servletRequest.getParameter("hacia");

        if (servletRequest.getParameter("hacia") != null) {
            hacia = servletRequest.getParameter("hacia");
        } else {
            servletRequest.setAttribute("mensaje", "Error al cargar la página. Si está subiendo un archivo, verifique que su tamaño no supera los 10 MB ");
            return actionMapping.findForward("mensaje_fw");
        }

        if (hacia.equals("getFoto")) {
            return getFoto(actionMapping, servletRequest, servletResponse);

        }

        if (hacia.equals("cargarIngeRep")) {
            return actionMapping.findForward("publicarReportaje_fw");

        }

        if (hacia.equals("ingresarReportaje")) {
            return ingresarReportaje(actionMapping, actionForm, servletRequest,
                                     servletResponse);

        }

        if (hacia.equals("actualizarReportaje")) {
            return actualizarReportaje(actionMapping, actionForm,
                                       servletRequest, servletResponse);
        }

        if (hacia.equals("aactualizarReportaje")) {
            return aactualizarReportaje(actionMapping, servletRequest,
                                        actionForm,
                                        servletResponse);
        }

        if (hacia.equals("seleccionarReportaje")) {
            return seleccionarReportaje(actionMapping, servletRequest,
                                        servletResponse);
        }
        if (hacia.equals("reportajesAnteriores")) {
            return this.reportajesAnteriores(actionMapping, servletRequest);
        }

//----------------------------------------
        if (iRAF.getHacia().compareTo("detReportaje") == 0) {
            ReportajeLocal reportajeLocal = null;

            Integer idRep = Integer.valueOf(iRAF.getIdRep());

            /* if (idRep.intValue() != 1 && idRep.intValue() != 2) {
                 servletRequest.setAttribute("mensaje", "URL incorrecta");
                 return actionMapping.findForward("mensaje_fw");

             }
             */
            try {
                reportajeLocal = LocalizadorServicios.getInstance().
                                 getReportajeLocal();
            } catch (LocalizadorServiciosException ex) {
                System.out.println("no se pudo llamar a EJB");
            }

            ReportajeVO reportaje = reportajeLocal.getReportaje(idRep.intValue());
            reportaje.setDescRep(reportaje.getDescRep().replaceAll("\r\n",
                    "<br>"));
            servletRequest.setAttribute("reportaje", reportaje);
            return actionMapping.findForward("detrep_fw");
        }
        servletRequest.setAttribute("mensaje", "URL incorrecta");

        return actionMapping.findForward("mensaje_fw");
    }

    /**
     * reportajesAnteriores
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward reportajesAnteriores(ActionMapping actionMapping,
                                               HttpServletRequest
                                               request) {
        try {

            ReportajeLocal reportajeLocal = LocalizadorServicios.getInstance().
                                            getReportajeLocal();
            MensajeLocal mensajeLocal = LocalizadorServicios.getInstance().
                                        getMensajeLocal();

            request.setAttribute("mensaje", mensajeLocal.getMensaje(1));
            request.setAttribute("lreps", reportajeLocal.getTailReportajes());

            return actionMapping.findForward("reportajesAnteriores");
        } catch (LocalizadorServiciosException ex) {
            request.setAttribute("mensaje",
                                 "No se pudo cargar la lista de Reportajes");
            return actionMapping.findForward("mensaje");
        }

    }


    /**
     * seleccionarReportaje
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param servletResponse HttpServletResponse
     * @return ActionForward
     */
    private ActionForward seleccionarReportaje(ActionMapping actionMapping,
                                               HttpServletRequest
                                               servletRequest,
                                               HttpServletResponse
                                               servletResponse) {
        try {
            ReportajeLocal reportajeLocal = LocalizadorServicios.getInstance().
                                            getReportajeLocal();

            List lreportajes = reportajeLocal.getTailReportajes();

            servletRequest.setAttribute("lista", lreportajes);
            return actionMapping.findForward("seleccionarReportaje_fw");
        } catch (Exception e) {
            System.out.print(e.toString());
            servletRequest.setAttribute("mensaje", "URL no válida");
            return actionMapping.findForward("mensaje_fw");
        }
    }

    /**
     * aactualizarReportaje
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param servletResponse HttpServletResponse
     * @return ActionForward
     */
    private ActionForward aactualizarReportaje(ActionMapping actionMapping,
                                               HttpServletRequest
                                               servletRequest,
                                               ActionForm actionForm,
                                               HttpServletResponse
                                               servletResponse) {
        IngReportajeActionForm iRAF = (IngReportajeActionForm) actionForm;

        ReportajeLocal reportajeLocal = null;

        Integer idRep = Integer.valueOf(iRAF.getIdRep());

        if (idRep == null) {
            servletRequest.setAttribute("mensaje", "URL incorrecta");
            return actionMapping.findForward("mensaje_fw");

        }

        try {
            reportajeLocal = LocalizadorServicios.getInstance().
                             getReportajeLocal();

            ReportajeVO reportaje = reportajeLocal.getReportaje(idRep.intValue());
            reportaje.setDescRep(reportaje.getDescRep().replaceAll("<br>",
                    "\r\n"));
            servletRequest.setAttribute("reportaje", reportaje);
            return actionMapping.findForward("actualizarReportaje_fw");

        } catch (LocalizadorServiciosException ex) {
            System.out.println("no se pudo llamar a EJB");
            servletRequest.setAttribute("mensaje", "Error en el módulo EJB");
            return actionMapping.findForward("mensaje_fw");
        }

    }

    /**
     * actualizarReportaje
     *
     * @param actionMapping ActionMapping
     * @param actionForm ActionForm
     * @param servletRequest HttpServletRequest
     * @param servletResponse HttpServletResponse
     * @return ActionForward
     */
    private ActionForward actualizarReportaje(ActionMapping actionMapping,
                                              ActionForm actionForm,
                                              HttpServletRequest servletRequest,
                                              HttpServletResponse
                                              servletResponse) {
        try {

            IngReportajeActionForm iRAF = (IngReportajeActionForm) actionForm;

            ReportajeLocal reportajeLocal = null;
            // si id_rep= 0 impplica que viene desde PublicarReportajePortada.jsp
            // y por lo tanto se debe recibir datos para ingresar reportaje.

            Integer idRep = Integer.valueOf(iRAF.getIdRep());
            String tit_rep = (String) iRAF.getTit_rep();
            String desc_rep = (String) iRAF.getDesc_rep();
            FormFile lafoto = iRAF.getFoto();
            ReportajeVO reportaje = new ReportajeVO();

            byte[] fileData = lafoto.getFileData();

            reportaje.setDescRep(desc_rep);
            reportaje.setIdRep(idRep);
            reportaje.setTitRep(tit_rep);
            reportaje.setFotoRep(fileData);

            reportajeLocal = LocalizadorServicios.getInstance().
                             getReportajeLocal();

            reportajeLocal.actualizarReportaje(reportaje);

            ReportajeVO reportaje2 = reportajeLocal.getReportaje(idRep.intValue());
            servletRequest.setAttribute("reportaje", reportaje2);
            return actionMapping.findForward("detrep_fw");

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo actualizar el reportaje");
            return actionMapping.findForward("mensaje_fw");

        } catch (IOException ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo actualizar el reportaje");
            return actionMapping.findForward("mensaje_fw");

        } catch (LocalizadorServiciosException ex1) {
            ex1.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo actualizar el reportaje");
            return actionMapping.findForward("mensaje_fw");

        }

    }

    /**
     * ingresarReportaje
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param servletResponse HttpServletResponse
     * @return ActionForward
     */
    private ActionForward ingresarReportaje(ActionMapping actionMapping,
                                            ActionForm actionForm,
                                            HttpServletRequest servletRequest,
                                            HttpServletResponse servletResponse) {

        try {

            IngReportajeActionForm iRAF = (IngReportajeActionForm) actionForm;

            ReportajeLocal reportajeLocal = null;
            // si id_rep= 0 impplica que viene desde PublicarReportajePortada.jsp
            // y por lo tanto se debe recibir datos para ingresar reportaje.

            String tipo_rep_s = iRAF.getTipo_rep();

            Integer tipo_rep = Integer.valueOf(tipo_rep_s);

            String tit_rep = (String) iRAF.getTit_rep();
            String desc_rep = (String) iRAF.getDesc_rep();
            FormFile lafoto = iRAF.getFoto();
            ReportajeVO nuevoRepor = new ReportajeVO();

            byte[] fileData = lafoto.getFileData();

            nuevoRepor.setDescRep(desc_rep);
            nuevoRepor.setTipo(tipo_rep.intValue());
            nuevoRepor.setTitRep(tit_rep);
            nuevoRepor.setFotoRep(fileData);

            reportajeLocal = LocalizadorServicios.getInstance().
                             getReportajeLocal();

            int idRep = reportajeLocal.agregarReportaje(nuevoRepor);

            ReportajeVO reportaje = reportajeLocal.getReportaje(idRep);
            servletRequest.setAttribute("reportaje", reportaje);
            return actionMapping.findForward("detrep_fw");

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo actualizar el reportaje");
            return actionMapping.findForward("mensaje_fw");

        } catch (IOException ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo actualizar el reportaje");
            return actionMapping.findForward("mensaje_fw");

        } catch (LocalizadorServiciosException ex1) {
            ex1.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo actualizar el reportaje");
            return actionMapping.findForward("mensaje_fw");

        }

    }

    /**
     * getFoto
     *Consulta la foto para mostrar en la portada, correspondiente a uno de los dos reportajes,
     * lo cual se indica como parámetro en el request.
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param servletResponse HttpServletResponse
     * @return ActionForward
     */
    private ActionForward getFoto(ActionMapping actionMapping,
                                  HttpServletRequest servletRequest,
                                  HttpServletResponse response) {

        try {
            ReportajeLocal reportajeLocal = LocalizadorServicios.getInstance().getReportajeLocal();
            int idRep = Integer.valueOf(servletRequest.getParameter("idRep")).
                        intValue();

            byte[] fotoB = reportajeLocal.getFoto(idRep);

            ByteArrayOutputStream output = new ByteArrayOutputStream();

            output.write(fotoB, 0, fotoB.length);

            response.setContentType("image/jpeg ");
            response.setHeader("content-disposition",
                               "inline; fileName=\"foto.jpg\"");
            response.setContentLength(output.size());

//Escribir el archivo en el response
            OutputStream out = response.getOutputStream();
            output.writeTo(out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la imagen");
            return actionMapping.findForward("mensaje_fw");
        }

        return null;
    }
}

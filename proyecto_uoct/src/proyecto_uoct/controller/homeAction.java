package proyecto_uoct.controller;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.Action;

import java.lang.String;
import java.lang.Byte;
import proyecto_uoct.common.LocalizadorServicios;
import proyecto_uoct.common.LocalizadorServiciosException;
import proyecto_uoct.infoyrep.model.ReportajeLocal;
import proyecto_uoct.infoyrep.VO.ReportajeVO;
import proyecto_uoct.documentacion.model.DocumentoLocal;
import proyecto_uoct.mensaje.model.MensajeLocal;


public class homeAction extends Action {
    public ActionForward execute(ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest servletRequest,
                                 HttpServletResponse servletResponse) {

        try {
            ReportajeVO reportaje_izq = null;
            ReportajeVO reportaje_der = null;

             // AQUI SE AGREGA UN ALTO NIVEL DE ACOPLAMIENTO, YA QUE EN LA BASE DE DATOS, EL CAMPO REPORTAJE.TIPO PUEDE SER 1  O 2
            int izq = 1;
            int der = 2;

            ReportajeLocal reportajeLocal = LocalizadorServicios.getInstance().getReportajeLocal();
            DocumentoLocal documentoLocal=LocalizadorServicios.getInstance().getDocumentoLocal();
            MensajeLocal mensajeLocal=LocalizadorServicios.getInstance().getMensajeLocal();

            reportaje_izq = reportajeLocal.getReportajePorTipo(izq);
            reportaje_der = reportajeLocal.getReportajePorTipo(der);
            servletRequest.setAttribute("mensaje",mensajeLocal.getMensaje(1));
            servletRequest.setAttribute("tiposSalientes",documentoLocal.getTiposDoc(new Integer(2)));

            servletRequest.setAttribute("reportaje_izq", reportaje_izq);
            servletRequest.setAttribute("reportaje_der", reportaje_der);
            servletRequest.setAttribute("listaReps", reportajeLocal.getTailReportajes());

            return actionMapping.findForward("home_fw");
        } catch (LocalizadorServiciosException ex) {
            servletRequest.setAttribute("mensaje","No se pudo cargar la pagina inicial");
            return actionMapping.findForward("mensaje_fw");
        }

    }
}

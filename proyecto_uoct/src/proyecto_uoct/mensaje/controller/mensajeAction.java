package proyecto_uoct.mensaje.controller;

import javax.servlet.http.*;

import org.apache.struts.action.*;
import proyecto_uoct.common.VO.*;
import proyecto_uoct.common.LocalizadorServicios;
import proyecto_uoct.mensaje.model.MensajeLocal;

public class mensajeAction extends Action {
    public ActionForward execute(ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest servletRequest,
                                 HttpServletResponse servletResponse) {

        HttpSession httpSession = servletRequest.getSession();
        DatosSesVO datoses;
        try {

            datoses = (DatosSesVO) httpSession.getAttribute("Ses_Usu");
            datoses.getId();
        } catch (Exception e) {
            servletRequest.setAttribute("mensaje", "La sesión ha caducado");
            return actionMapping.findForward("mensaje_fw");
        }

        String accion = " ";

        if (servletRequest.getParameter("accion") != null) {
            accion = servletRequest.getParameter("accion");
        }

        if ("aactualizarMens".equals(accion)) {
            return aActualizarMens(actionMapping, servletRequest,
                                   servletResponse);
        }

        if ("actualizarMens".equals(accion)) {
            return actualizarMens(actionMapping, servletRequest,
                                  servletResponse);
        }

        servletRequest.setAttribute("mensaje", "URL no válida");
        return actionMapping.findForward("mensaje_fw");
    }

    /**
     * actualizarMens
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param servletResponse HttpServletResponse
     * @return ActionForward
     */
    private ActionForward actualizarMens(ActionMapping actionMapping,
                                         HttpServletRequest request,
                                         HttpServletResponse response) {
        try {

            MensajeLocal mens = LocalizadorServicios.getInstance().
                                getMensajeLocal();
            mens.setMensaje(request.getParameter("mens"),1);
            request.setAttribute("mensaje","El mensaje fue actualizado exitosamente");
        }catch(Exception e){
            System.out.print(e.toString());
            request.setAttribute("mensaje","no se pudo actualizar el mensaje");
        }
        return actionMapping.findForward("mensaje_fw");
    }

    /**
     * aActualizarMens
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param servletResponse HttpServletResponse
     * @return ActionForward
     */
    private ActionForward aActualizarMens(ActionMapping actionMapping,
                                          HttpServletRequest servletRequest,
                                          HttpServletResponse servletResponse) {

        return actionMapping.findForward("actualizarMensaje");
    }
}

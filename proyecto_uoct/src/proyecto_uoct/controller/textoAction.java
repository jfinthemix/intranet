package proyecto_uoct.controller;

import javax.servlet.http.*;

import org.apache.struts.action.*;

public class textoAction extends Action {
    public ActionForward execute(ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest servletRequest,
                                 HttpServletResponse servletResponse) {

        try{
            String accion = servletRequest.getParameter("accion");

            if ("escribir".equals(accion)) {
                return escribe(actionMapping, servletRequest);
            }
            if ("leer".equals(accion)) {
                return leer(actionMapping, servletRequest);
            }
        }catch(Exception e){
            return leer(actionMapping, servletRequest);
        }
            return leer(actionMapping, servletRequest);
    }

    /**
     * leer
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward leer(ActionMapping actionMapping,
                               HttpServletRequest servletRequest) {
        mensajeTexto mt=new mensajeTexto();
        servletRequest.setAttribute("mensaje",mt.leer());
        return actionMapping.findForward("mensaje");
    }

    /**
     * escribe
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward escribe(ActionMapping actionMapping,
                                  HttpServletRequest servletRequest) {
        try{
            String ms = servletRequest.getParameter("mens");
            mensajeTexto mt = new mensajeTexto();
            mt.escribe(ms);

            return leer(actionMapping,servletRequest);
        }catch(Exception e){
            return leer(actionMapping,servletRequest);
        }
    }
}

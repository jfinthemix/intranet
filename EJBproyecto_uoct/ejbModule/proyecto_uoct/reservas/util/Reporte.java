package proyecto_uoct.reservas.util;

import org.apache.struts.action.ActionForward;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

public class Reporte {

    public static ActionForward mensaje(String mensaje,
                                        ActionMapping actionMapping,
                                        HttpServletRequest servletRequest,
                                        boolean error) {

        servletRequest.getSession().setAttribute("mensaje", mensaje);
        servletRequest.setAttribute("mensaje",mensaje);
        servletRequest.getSession().setAttribute("ES_ERROR", String.valueOf(error));
        return actionMapping.findForward("mensaje_fw");
    }

}

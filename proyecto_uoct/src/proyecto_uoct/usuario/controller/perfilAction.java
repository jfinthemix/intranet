package proyecto_uoct.usuario.controller;

import java.util.*;

import javax.servlet.http.*;

import org.apache.struts.action.*;
import proyecto_uoct.common.*;
import proyecto_uoct.usuario.model.*;
import proyecto_uoct.common.VO.DatosSesVO;

public class perfilAction extends Action {
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



        String hacia = servletRequest.getParameter("hacia");
        if (hacia.equals("aagregarPerfil")) {
            return aagregarPerfil(actionMapping, actionForm, servletRequest);
        }

        if (hacia.equals("ingresarPerfil")) {
            return ingresarPerfil(actionMapping, actionForm, servletRequest);
        }

        if (hacia.equals("alistaPerfiles")) {
            return listaPerfiles(actionMapping, actionForm, servletRequest);
        }
        if (hacia.equals("adetallePerfil")) {
            return aDetallePerfil(actionMapping, actionForm, servletRequest);
        }
        if (hacia.equals("aEditarPerfil")) {
            return aEditarPerfil(actionMapping, actionForm, servletRequest);
        }

        if (hacia.equals("actualizarPerfil")) {
            return actualizaPerfil(actionMapping, actionForm, servletRequest);
        }

        if (hacia.equals("eliminarPerfil")) {
            return eliminarPerfil(actionMapping, actionForm, servletRequest);
        }

        return actionMapping.getInputForward();
    }

    /**
     * eliminarPerfil
     *
     * @param actionMapping ActionMapping
     * @param actionForm ActionForm
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward eliminarPerfil(ActionMapping actionMapping,
                                         ActionForm actionForm,
                                         HttpServletRequest servletRequest) {

        PerfilLocal perfilLocal;
        try {
            Integer id_perfil = new Integer(servletRequest.getParameter(
                    "id_perfil"));

            perfilLocal = LocalizadorServicios.getInstance().getPerfilLocal();

            if (perfilLocal.eliminarPerfil(id_perfil)) {

                servletRequest.setAttribute("mensaje",
                                            "El perfil fue eliminado");
            } else {
                servletRequest.setAttribute("mensaje",
                                            "No se pudo eliminar el perfil ");

            }
            return actionMapping.findForward("mensaje_fw");

        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "No se pudo ingresar el Perfil. Revise que no existe otro perfil con el mismo nombre o que se ha asignado al menos una funcionalidad al nuevo perfil");
            return actionMapping.findForward("mensaje_fw");

        }

    }


    /**
     * actualizaPerfil
     *
     * @param actionMapping ActionMapping
     * @param actionForm ActionForm
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward actualizaPerfil(ActionMapping actionMapping,
                                          ActionForm actionForm,
                                          HttpServletRequest servletRequest) {
        PerfilLocal perfilLocal;
        try {
            Integer id_perfil = new Integer(servletRequest.getParameter(
                    "id_perfil"));
            String[] id_funcionalidades = servletRequest.getParameterValues(
                    "id_funcionalidad");
            perfilLocal = LocalizadorServicios.getInstance().getPerfilLocal();

            if (perfilLocal.actualizaPerfil(id_perfil, id_funcionalidades)) {

                servletRequest.setAttribute("mensaje",
                                            "El perfil fue Actualizado correctamente");
            } else {
                servletRequest.setAttribute("mensaje",
                                            "No se pudo actualizar el perfil");

            }
            return actionMapping.findForward("mensaje_fw");

        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "No se pudo ingresar el Perfil. Revise que no existe otro perfil con el mismo nombre o que se ha asignado al menos una funcionalidad al nuevo perfil");
            return actionMapping.findForward("mensaje_fw");

        }

    }


    /**
     * aEditarPerfil
     *
     * @param actionMapping ActionMapping
     * @param actionForm ActionForm
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward aEditarPerfil(ActionMapping actionMapping,
                                        ActionForm actionForm,
                                        HttpServletRequest servletRequest) {

        Integer idPer = Integer.valueOf(servletRequest.getParameter("idPer"));
        String nomPer = servletRequest.getParameter("nomPer");
        PerfilLocal perfilLocal;

        try {
            perfilLocal = LocalizadorServicios.getInstance().getPerfilLocal();

            List FdelP = perfilLocal.getFunsdePerfil(idPer);

            List modulos = perfilLocal.getModulos();

            List f = perfilLocal.getFuncionalidades();

            servletRequest.setAttribute("nomPer", nomPer);
            servletRequest.setAttribute("idPer", idPer);
            servletRequest.setAttribute("FdelP", FdelP);
            servletRequest.setAttribute("f", f);
            servletRequest.setAttribute("modulos", modulos);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return actionMapping.findForward("editarPerfil_fw");

    }

    /**
     * aDetallePerfil
     *
     * @param actionMapping ActionMapping
     * @param actionForm ActionForm
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward aDetallePerfil(ActionMapping actionMapping,
                                         ActionForm actionForm,
                                         HttpServletRequest servletRequest) {

        Integer id_perfil = Integer.valueOf(servletRequest.getParameter("idPer"));
        String nomPer = servletRequest.getParameter("nomPer");
        PerfilLocal perfilLocal;

        try {
            perfilLocal = LocalizadorServicios.getInstance().getPerfilLocal();
            List funcionalidades = perfilLocal.getFunsdePerfil(id_perfil);

            List modulos = perfilLocal.getModulos();

            servletRequest.setAttribute("nomPer", nomPer);
            servletRequest.setAttribute("funcionalidades", funcionalidades);
            servletRequest.setAttribute("modulos", modulos);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return actionMapping.findForward("detallePerfil_fw");
    }

    /**
     * listaPerfiles
     *
     * @param actionMapping ActionMapping
     * @param actionForm ActionForm
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward listaPerfiles(ActionMapping actionMapping,
                                        ActionForm actionForm,
                                        HttpServletRequest servletRequest) {

        PerfilLocal perfilLocal;
        try {

            perfilLocal = LocalizadorServicios.getInstance().getPerfilLocal();
            List listaPerfiles = new LinkedList();
            listaPerfiles = perfilLocal.getPerfiles();
            servletRequest.setAttribute("perfiles", listaPerfiles);
            return actionMapping.findForward("listaPerfiles_fw");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "No se pudo ingresar el Perfil. Revise que no existe otro perfil con el mismo nombre o que se ha asignado al menos una funcionalidad al nuevo perfil");
            return actionMapping.findForward("mensaje_fw");
        }

        // servletRequest.setAttribute("mensaje","No se pudo ingresar el Perfil. Revise que no existe otro perfil con el mismo nombre o que se ha asignado al menos una funcionalidad al nuevo perfil");
        //   return actionMapping.findForward("mensaje_fw");
    }

    /**
     * ingresarPerfil
     *
     * @param actionMapping ActionMapping
     * @param actionForm ActionForm
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward ingresarPerfil(ActionMapping actionMapping,
                                         ActionForm actionForm,
                                         HttpServletRequest servletRequest) {

        PerfilLocal perfilLocal;
        try {
            String nom_perfil = servletRequest.getParameter("nom_perfil");
            String[] id_funcionalidades = servletRequest.getParameterValues(
                    "id_funcionalidad");
            perfilLocal = LocalizadorServicios.getInstance().getPerfilLocal();

            if (perfilLocal.ingresarPerfil(nom_perfil, id_funcionalidades)) {
                servletRequest.setAttribute("mensaje",
                                            "El perfil fue ingresado exitosamente");
                return actionMapping.findForward("mensaje_fw");
            }

        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "No se pudo ingresar el Perfil. Revise que no existe otro perfil con el mismo nombre o que se ha asignado al menos una funcionalidad al nuevo perfil");
            return actionMapping.findForward("mensaje_fw");

        }
        servletRequest.setAttribute("mensaje", "No se pudo ingresar el Perfil. Revise que no existe otro perfil con el mismo nombre o que se ha asignado al menos una funcionalidad al nuevo perfil");
        return actionMapping.findForward("mensaje_fw");
    }

    /**
     * aagregarPerfil
     *
     * @param actionMapping ActionMapping
     * @param actionForm ActionForm
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward aagregarPerfil(ActionMapping actionMapping,
                                         ActionForm actionForm,
                                         HttpServletRequest servletRequest) {
        PerfilLocal perfilLocal;

        try {
            perfilLocal = LocalizadorServicios.getInstance().getPerfilLocal();
            List funcionalidades = perfilLocal.getFuncionalidades();
            List modulos = perfilLocal.getModulos();

            servletRequest.setAttribute("funcionalidades", funcionalidades);
            servletRequest.setAttribute("modulos", modulos);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return actionMapping.findForward("agregarPerfil_fw");
    }
}

package proyecto_uoct.inventario.controller;

import javax.servlet.http.*;

import org.apache.struts.action.*;
import proyecto_uoct.common.*;
import proyecto_uoct.common.VO.*;
import proyecto_uoct.inventario.VO.*;
import proyecto_uoct.inventario.model.*;
import proyecto_uoct.usuario.model.*;

public class InventarioAction extends Action {
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

        String accion = servletRequest.getParameter("accion");

        if (("aRegItem").equals(accion)) {
            return aRegItem(actionMapping, servletRequest);
        }

        if (("registrarItem").equals(accion)) {
            return registrarItem(actionMapping, servletRequest);
        }

        if (("buscarItem").equals(accion)) {
            return buscarItem(actionMapping, servletRequest);
        }

        if (("buscarParaEditarItem").equals(accion)) {
            return buscarParaEditarItem(actionMapping, servletRequest);
        }

        if (("editarItem").equals(accion)) {
            return editarItem(actionMapping, servletRequest);
        }

        if (("actualizarItem").equals(accion)) {
            return actualizarItem(actionMapping, servletRequest);
        }

        if (("eliminarItem").equals(accion)) {
            return eliminarItem(actionMapping, servletRequest);
        }

        servletRequest.setAttribute("mensaje", "URL no válida");
        return actionMapping.findForward("mensaje_fw");
    }

    /**
     * eliminarItem
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward eliminarItem(ActionMapping actionMapping,
                                       HttpServletRequest servletRequest) {
        try {
                   InventarioLocal inventarioLocal = LocalizadorServicios.getInstance().
                                                     getInventarioLocal();

                 Integer id=Integer.valueOf(servletRequest.getParameter("idItem"));
                   if (inventarioLocal.borrarItem(id)) {
                       servletRequest.setAttribute("mensaje",
                                                   "El item fue eliminado");

                   } else {
                       servletRequest.setAttribute("mensaje",
                                                   "No se pudo eliminar el Item");
                   }
                   return actionMapping.findForward("mensaje_fw");
               } catch (Exception e) {
                   e.printStackTrace();
                   servletRequest.setAttribute("mensaje",
                                               "No se pudo eliminar el Item");
            return actionMapping.findForward("mensaje_fw");
        }    }

    /**
     * actualizarItem
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward actualizarItem(ActionMapping actionMapping,
                                         HttpServletRequest servletRequest) {
        try {
            InventarioLocal inventarioLocal = LocalizadorServicios.getInstance().
                                              getInventarioLocal();
            ItemVO item = new ItemVO();
            item.setIdItem(Integer.valueOf(servletRequest.getParameter("idItem")));
            item.setNomItem(servletRequest.getParameter("nomItem"));
            item.setIdCategoria(Integer.valueOf(servletRequest.getParameter("idCategoria")));
            item.setIdUnidad(Integer.valueOf(servletRequest.getParameter("tUnidad")));
            item.setCantidadItem(Integer.valueOf(servletRequest.getParameter(
                    "cantidad")));
            if (inventarioLocal.actualizaItem(item)) {
                servletRequest.setAttribute("mensaje",
                                            "El item fue actualizado");

            } else {
                servletRequest.setAttribute("mensaje",
                                            "No se pudo actualizar el Item");
            }
            return actionMapping.findForward("mensaje_fw");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo actualizar el Item");
            return actionMapping.findForward("mensaje_fw");
        }
    }

    /**
     * editarItem
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward editarItem(ActionMapping actionMapping,
                                     HttpServletRequest servletRequest) {
        try {
            InventarioLocal inventarioLocal = LocalizadorServicios.getInstance().
                                              getInventarioLocal();
            Integer id = Integer.valueOf(servletRequest.getParameter("idItem"));
            servletRequest.setAttribute("item", inventarioLocal.detalleItem(id));
            return actionMapping.findForward("editarItem_fw");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la pag. de edición del inventario");
            return actionMapping.findForward("mensaje_fw");

        }
    }

    /**
     * buscarParaEditarItem
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward buscarParaEditarItem(ActionMapping actionMapping,
                                               HttpServletRequest
                                               servletRequest) {
        try {
            String buscar = servletRequest.getParameter("buscar");
            InventarioLocal inventarioLocal = LocalizadorServicios.getInstance().
                                              getInventarioLocal();
            UsuarioLocal usuarioLocal = LocalizadorServicios.getInstance().
                                        getUsuarioLocal();

            if (buscar != null) {
                BusItemVO bus = new BusItemVO();
                bus.setPalClave(servletRequest.getParameter("palClave"));
                bus.setIdCategoria(Integer.valueOf(servletRequest.getParameter(
                        "idCategoria")));
                servletRequest.setAttribute("items",
                                            inventarioLocal.buscarItem(bus));

                return actionMapping.findForward("busParaEditarItem_fw");

            } else {
                servletRequest.setAttribute("areas", usuarioLocal.getAreas());
                return actionMapping.findForward("busParaEditarItem_fw");
            }
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la pag. de búsqueda del inventario");
            return actionMapping.findForward("mensaje_fw");
        }
    }

    /**
     * buscarItem
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward buscarItem(ActionMapping actionMapping,
                                     HttpServletRequest servletRequest) {
        try {
            String buscar = servletRequest.getParameter("buscar");
            InventarioLocal inventarioLocal = LocalizadorServicios.getInstance().
                                              getInventarioLocal();
            UsuarioLocal usuarioLocal = LocalizadorServicios.getInstance().
                                        getUsuarioLocal();

            if (buscar != null) {
                BusItemVO bus = new BusItemVO();
                bus.setPalClave(servletRequest.getParameter("palClave"));
                bus.setIdCategoria(Integer.valueOf(servletRequest.getParameter(
                        "idCategoria")));
                servletRequest.setAttribute("items",
                                            inventarioLocal.buscarItem(bus));

                return actionMapping.findForward("busItem_fw");

            } else {
                servletRequest.setAttribute("areas", usuarioLocal.getAreas());
                return actionMapping.findForward("busItem_fw");
            }
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la pag. de búsqueda del inventario");
            return actionMapping.findForward("mensaje_fw");
        }
    }

    /**
     * registrarItem
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward registrarItem(ActionMapping actionMapping,
                                        HttpServletRequest servletRequest) {
        try {
            InventarioLocal inventarioLocal = LocalizadorServicios.getInstance().
                                              getInventarioLocal();
            ItemVO item = new ItemVO();
            item.setNomItem(servletRequest.getParameter("nomItem"));
            item.setIdUnidad(Integer.valueOf(servletRequest.getParameter("tUnidad")));
            item.setIdCategoria(Integer.valueOf(servletRequest.getParameter("idCategoria")));
            item.setCantidadItem(Integer.valueOf(servletRequest.getParameter("cantidad")));

            if (inventarioLocal.regItem(item)) {
                servletRequest.setAttribute("mensaje",
                                            "El item fue registrado en el inventario");

            } else {
                servletRequest.setAttribute("mensaje",
                                            "No se pudo registrar el Item en el Inventario");
            }
            return actionMapping.findForward("mensaje_fw");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo registrar el Item en el Inventario");
            return actionMapping.findForward("mensaje_fw");
        }
    }

    /**
     * aRegItem
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward aRegItem(ActionMapping actionMapping,
                                   HttpServletRequest servletRequest) {
        try {
            UsuarioLocal usuarioLocal = LocalizadorServicios.getInstance().
                                        getUsuarioLocal();
            servletRequest.setAttribute("areas", usuarioLocal.getAreas());
            return actionMapping.findForward("regItem_fw");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la pag. de registro de Items del Inventario");
            return actionMapping.findForward("mensaje_fw");
        }
    }
}

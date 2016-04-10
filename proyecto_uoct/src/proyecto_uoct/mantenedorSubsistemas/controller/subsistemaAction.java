package proyecto_uoct.mantenedorSubsistemas.controller;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import proyecto_uoct.common.*;
import proyecto_uoct.common.VO.*;
import proyecto_uoct.mantenedorSubsistemas.VO.*;
import proyecto_uoct.mantenedorSubsistemas.model.*;

public class subsistemaAction extends Action {
    public subsistemaAction() {
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
            //Session
            HttpSession httpSession;
            Integer ses_idusu;
            try {
                httpSession = servletRequest.getSession();
                DatosSesVO datosses = (DatosSesVO) httpSession.getAttribute("Ses_Usu");
                ses_idusu = (Integer) datosses.getId();
            } catch (Exception e) {
                e.printStackTrace();
                servletRequest.setAttribute("mensaje", "Su sesión ha caducado");
                return actionMapping.findForward("mensaje_fw");
            }
            //funcionalidad del "hacia"
            String hacia = null;
            if (servletRequest.getParameter("hacia") != null) {
                hacia = servletRequest.getParameter("hacia");
            }
            else {
                servletRequest.setAttribute("mensaje", "hacia = null xd");
                return actionMapping.findForward("mensaje_fw");
            }

            //System.out.println("hacia="+hacia);

            if (hacia.equals("buscar")) {
                return buscar(actionMapping,
                              servletRequest,
                              httpSession,
                              ses_idusu);
            }
            if (hacia.equals("ingresar")) {
                return ingresar(actionMapping,
                                servletRequest,
                                httpSession,
                                ses_idusu);
            }
            if (hacia.equals("Grabar")) {
                return resultado_ingresar(actionMapping,
                                          servletRequest,
                                          httpSession,
                                          ses_idusu);
            }
            if (hacia.equals("modificar")) {
                return modificar(actionMapping,
                                 servletRequest,
                                 httpSession,
                                 ses_idusu);
            }
            if (hacia.equals("eliminar")) {
                return eliminar(actionMapping,
                                servletRequest,
                                httpSession,
                                ses_idusu);
            }
            return actionMapping.getInputForward();
        }


        /* buscar */
        private ActionForward buscar(ActionMapping actionMapping,
                                     HttpServletRequest servletRequest,
                                     HttpSession httpSession,
                                     Integer ses_idusu) {
            try {
                MantenedorSubsistemasLocal subsistemaLocal = LocalizadorServicios.getInstance().getMantenedorSubsistemasLocal();
                SubsistemaVO subsistema           = new SubsistemaVO();
                int idPerfil            = 0;
                idPerfil = subsistemaLocal.idPerfil(ses_idusu);
                servletRequest.setAttribute("idPerfil", idPerfil);
                servletRequest.setAttribute("listaSubsistema", subsistemaLocal.buscarSubsistema());
                return actionMapping.findForward("buscarSubsistema");
            } catch (Exception e) {
                e.printStackTrace();
                servletRequest.setAttribute("mensaje", "No se pudo cargar la búsqueda.[buscar, subsistemaAction]");
                return actionMapping.findForward("mensaje_fw");
            }
        }
        /* ingresar */
        private ActionForward ingresar(ActionMapping actionMapping,
                                       HttpServletRequest servletRequest,
                                       HttpSession httpSession,
                                       Integer ses_idusu) {
            try {
                MantenedorSubsistemasLocal subsistemaLocal = LocalizadorServicios.getInstance().getMantenedorSubsistemasLocal();
                SubsistemaVO subsistema = new SubsistemaVO();
                int idPerfil            = 0;
                idPerfil = subsistemaLocal.idPerfil(ses_idusu);
                servletRequest.setAttribute("idPerfil", idPerfil);
                servletRequest.setAttribute("lista_sistema", subsistemaLocal.lista_sistema());

                return actionMapping.findForward("ingresarSubsistema");
            } catch (Exception e) {
                e.printStackTrace();
                servletRequest.setAttribute("mensaje", "No se pudo cargar los parámetros para el ingreso.[ingresar, subsistemaAction]");
                return actionMapping.findForward("mensaje_fw");
            }
        }
        /* resultado_ingresar */
        private ActionForward resultado_ingresar(ActionMapping actionMapping,
                                                 HttpServletRequest servletRequest,
                                                 HttpSession httpSession,
                                                 Integer ses_idusu) {
            Integer id         = new Integer(0);
            Integer id_subsistema = Integer.valueOf(servletRequest.getParameter("id_subsistema"));
            Integer id_sistema = Integer.valueOf(servletRequest.getParameter("id_sistema"));
            Integer hacer = Integer.valueOf(servletRequest.getParameter("hacer"));
            String nombre  = servletRequest.getParameter("nombre");
            try {
                MantenedorSubsistemasLocal subsistemaLocal = LocalizadorServicios.getInstance().getMantenedorSubsistemasLocal();
                SubsistemaVO sistema = new SubsistemaVO();
                if(hacer==-1){//Modificar
                    id = subsistemaLocal.modificarSubsistema(id_subsistema, nombre,id_sistema);
                    servletRequest.setAttribute("listaSubsistema", subsistemaLocal.buscarSubsistema());
                    servletRequest.setAttribute("id_subsistema", id);
                    servletRequest.setAttribute("mensaje","Se ha modificado la información del subsistema.");
                }
                else{//Ingresar nuevo
                    id = subsistemaLocal.ingresarSubsistema(nombre, id_sistema);
                    servletRequest.setAttribute("nombre", nombre);
                    servletRequest.setAttribute("id_sistema", id);
                    servletRequest.setAttribute("mensaje","Se ha ingresado el nuevo subsistema.");
                }
                int idPerfil            = 0;
                idPerfil = subsistemaLocal.idPerfil(ses_idusu);
                servletRequest.setAttribute("idPerfil", idPerfil);

                servletRequest.setAttribute("listaSubsistema", subsistemaLocal.buscarSubsistema());
                return actionMapping.findForward("buscarSubsistema");
            } catch (Exception e) {
                e.printStackTrace();
                servletRequest.setAttribute("mensaje", "No se pudo ingresar el subsistema.[resultado_ingresar, subsistemaAction]");
                return actionMapping.findForward("mensaje_fw");
            }

        }
        /* modificar_subsistema */
        private ActionForward modificar(ActionMapping actionMapping,
                                        HttpServletRequest servletRequest,
                                        HttpSession httpSession,
                                        Integer ses_idusu) {
            try {
                MantenedorSubsistemasLocal subsistemaLocal = LocalizadorServicios.getInstance().getMantenedorSubsistemasLocal();
                SubsistemaVO sistema       = new SubsistemaVO();
                Integer id_subsistema      = Integer.valueOf(servletRequest.getParameter("id_subsistema"));
                String nombre              = servletRequest.getParameter("nombre");
                Integer id_sistema      = Integer.valueOf(servletRequest.getParameter("id_sistema"));
                String nombre_sistema      = servletRequest.getParameter("nombre_sistema");
                servletRequest.setAttribute("id_subsistema", id_subsistema);
                servletRequest.setAttribute("nombre", nombre);
                servletRequest.setAttribute("id_sistema", id_sistema);
                servletRequest.setAttribute("nombre_sistema", nombre_sistema);

                servletRequest.setAttribute("listaSistema", subsistemaLocal.lista_sistema());
                return actionMapping.findForward("modificarSubsistema");
            } catch (Exception e) {
                e.printStackTrace();
                servletRequest.setAttribute("mensaje", "No se pudo llegar a la página para modificar el subsistema.[modificar, subsistemaAction]");
                return actionMapping.findForward("mensaje_fw");
            }
        }

        // eliminar_falla
        private ActionForward eliminar(ActionMapping actionMapping,
                                       HttpServletRequest servletRequest,
                                       HttpSession httpSession,
                                       Integer ses_idusu) {
            MantenedorSubsistemasLocal subsistemaLocal;
            String respuesta = "si";
            try {
                Integer id_subsistema = new Integer(servletRequest.getParameter("id_subsistema"));
                subsistemaLocal     = LocalizadorServicios.getInstance().getMantenedorSubsistemasLocal();
                respuesta = subsistemaLocal.sePuedeEliminar(id_subsistema);
                if(respuesta=="si"){
                    subsistemaLocal.eliminarSubsistema(id_subsistema);
                    servletRequest.setAttribute("mensaje", "El subsistema fue eliminado con éxito.");
                }
                else{
                    servletRequest.setAttribute("mensaje", "El subsistema no puede ser eliminado porque tiene dispositivos asociados.");
                }
               /* int idPerfil            = 0;
                idPerfil = subsistemaLocal.idPerfil(ses_idusu);
                servletRequest.setAttribute("idPerfil", idPerfil);
                servletRequest.setAttribute("listaSubsistema", subsistemaLocal.buscarSubsistema());
                return actionMapping.findForward("buscarSubsistema");*/
              return this.buscar(actionMapping, servletRequest, httpSession, ses_idusu);
            }
            catch (Exception e) {
                e.printStackTrace();
                servletRequest.setAttribute("mensaje", "No se pudo eliminar el subsistema.[eliminar, subsistemaAction]");
                return actionMapping.findForward("buscarSubsistema");
            }

        }

        private void jbInit() throws Exception {
        }
    }



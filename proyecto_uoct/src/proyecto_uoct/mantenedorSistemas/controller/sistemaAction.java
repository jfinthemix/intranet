package proyecto_uoct.mantenedorSistemas.controller;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import proyecto_uoct.common.*;
import proyecto_uoct.common.VO.*;
import proyecto_uoct.mantenedorSistemas.VO.*;
import proyecto_uoct.mantenedorSistemas.model.*;


public class sistemaAction extends Action {
    public sistemaAction() {
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
               MantenedorSistemasLocal sistemaLocal = LocalizadorServicios.getInstance().getMantenedorSistemasLocal();
               SistemaVO sistema           = new SistemaVO();
               int idPerfil            = 0;
               idPerfil = sistemaLocal.idPerfil(ses_idusu);
               servletRequest.setAttribute("idPerfil", idPerfil);
               servletRequest.setAttribute("listaSistema", sistemaLocal.buscarSistema());
               return actionMapping.findForward("buscarSistema");
           } catch (Exception e) {
               e.printStackTrace();
               servletRequest.setAttribute("mensaje", "No se pudo cargar la búsqueda.[buscar, sistemaAction]");
               return actionMapping.findForward("mensaje_fw");
           }
       }
       /* ingresar */
       private ActionForward ingresar(ActionMapping actionMapping,
                                      HttpServletRequest servletRequest,
                                      HttpSession httpSession,
                                      Integer ses_idusu) {
           try {
               MantenedorSistemasLocal sistemaLocal = LocalizadorServicios.getInstance().getMantenedorSistemasLocal();
               SistemaVO sistema = new SistemaVO();
               int idPerfil            = 0;
               idPerfil = sistemaLocal.idPerfil(ses_idusu);
               servletRequest.setAttribute("idPerfil", idPerfil);

               return actionMapping.findForward("ingresarSistema");
           } catch (Exception e) {
               e.printStackTrace();
               servletRequest.setAttribute("mensaje", "No se pudo cargar los parámetros para el ingreso.[ingresar, sistemaAction]");
               return actionMapping.findForward("mensaje_fw");
           }
        }
       /* resultado_ingresar */
       private ActionForward resultado_ingresar(ActionMapping actionMapping,
                                                HttpServletRequest servletRequest,
                                                HttpSession httpSession,
                                                Integer ses_idusu) {
           int id         = 0;
           int id_sistema = Integer.valueOf(servletRequest.getParameter("id_sistema"));
           String nombre  = servletRequest.getParameter("nombre");

           try {
               MantenedorSistemasLocal sistemaLocal = LocalizadorServicios.getInstance().getMantenedorSistemasLocal();
               SistemaVO sistema = new SistemaVO();


               if(id_sistema!=0){//Modificar
                   id = sistemaLocal.modificarSistema(id_sistema, nombre);
                   servletRequest.setAttribute("listaSistema", sistemaLocal.buscarSistema());
                   servletRequest.setAttribute("id_sistema", id);
                   servletRequest.setAttribute("mensaje","Se ha modificado el nombre del sistema.");
               }
               else{//Ingresar nuevo
                   id = sistemaLocal.ingresarSistema(nombre);
                   servletRequest.setAttribute("nombre", nombre);
                   servletRequest.setAttribute("id_sistema", id);
                   servletRequest.setAttribute("mensaje","Se ha ingresado el nuevo sistema.");
               }
               servletRequest.setAttribute("listaSistema", sistemaLocal.buscarSistema());
               //return actionMapping.findForward("buscarSistema");
               return this.buscar(actionMapping, servletRequest, httpSession, ses_idusu);
           } catch (Exception e) {
               e.printStackTrace();
               servletRequest.setAttribute("mensaje", "No se pudo ingresar el sistema.[resultado_ingresar, sistemaAction]");
               return actionMapping.findForward("mensaje_fw");
           }

       }
       /* modificar_sistema */
       private ActionForward modificar(ActionMapping actionMapping,
                                       HttpServletRequest servletRequest,
                                       HttpSession httpSession,
                                       Integer ses_idusu) {
           try {
               MantenedorSistemasLocal sistemaLocal = LocalizadorServicios.getInstance().getMantenedorSistemasLocal();
               SistemaVO sistema       = new SistemaVO();
               Integer id_sistema      = Integer.valueOf(servletRequest.getParameter("id_sistema"));
               String nombre           = servletRequest.getParameter("nombre");
               servletRequest.setAttribute("id_sistema", id_sistema);
               servletRequest.setAttribute("nombre", nombre);
               return actionMapping.findForward("modificarSistema");
           } catch (Exception e) {
               e.printStackTrace();
               servletRequest.setAttribute("mensaje", "No se pudo llegar a la página para modificar el sistema.[modificar, sistemaAction]");
               return actionMapping.findForward("mensaje_fw");
           }
       }

       // eliminar_falla
       private ActionForward eliminar(ActionMapping actionMapping,
                                      HttpServletRequest servletRequest,
                                      HttpSession httpSession,
                                      Integer ses_idusu) {
           MantenedorSistemasLocal sistemaLocal;
           String respuesta = "si";
           try {
               Integer id_sistema = new Integer(servletRequest.getParameter("id_sistema"));
               sistemaLocal     = LocalizadorServicios.getInstance().getMantenedorSistemasLocal();
               respuesta = sistemaLocal.sePuedeEliminar(id_sistema);
               if(respuesta=="si"){
                   sistemaLocal.eliminarSistema(id_sistema);
                   servletRequest.setAttribute("mensaje", "El sistema fue eliminado con éxito.");
               }
               else{
                   servletRequest.setAttribute("mensaje", "El sistema no puede ser eliminado porque tiene subsistemas asociados.");
               }

               servletRequest.setAttribute("listaSistema", sistemaLocal.buscarSistema());
               //return actionMapping.findForward("buscarSistema");
               return this.buscar(actionMapping, servletRequest, httpSession, ses_idusu);
           }
           catch (Exception e) {
               e.printStackTrace();
               servletRequest.setAttribute("mensaje", "No se pudo eliminar el sistema.[eliminar, sistemaAction]");
               return actionMapping.findForward("buscarSistema");
           }
       }



       private void jbInit() throws Exception {
       }
   }

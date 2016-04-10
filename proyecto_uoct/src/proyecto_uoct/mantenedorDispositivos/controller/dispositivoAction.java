package proyecto_uoct.mantenedorDispositivos.controller;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import proyecto_uoct.common.*;
import proyecto_uoct.common.VO.*;
import proyecto_uoct.mantenedorDispositivos.VO.*;
import proyecto_uoct.mantenedorDispositivos.model.*;
import proyecto_uoct.mantenedorSubsistemas.VO.*;
import proyecto_uoct.mantenedorSubsistemas.model.*;

public class dispositivoAction extends Action {
    public dispositivoAction() {
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
                servletRequest.setAttribute("mensaje", "Su sesi�n ha caducado");
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

            if (hacia.equals("Buscar")) {
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
                MantenedorDispositivosLocal dispositivoLocal = LocalizadorServicios.getInstance().getMantenedorDispositivosLocal();
                MantenedorSubsistemasLocal subsistemaLocal = LocalizadorServicios.getInstance().getMantenedorSubsistemasLocal();
                String subsist           = "";
                subsist                  = (servletRequest.getParameter("subsistema")== "")? "" : servletRequest.getParameter("subsistema");
                int idPerfil            = 0;
                idPerfil = dispositivoLocal.idPerfil(ses_idusu);
                servletRequest.setAttribute("idPerfil", idPerfil);
                servletRequest.setAttribute("subsistema", subsist);
                servletRequest.setAttribute("listaSubsistema", subsistemaLocal.buscarSubsistema());
                servletRequest.setAttribute("listaDispositivo", dispositivoLocal.buscarDispositivo(subsist));
                servletRequest.setAttribute("hacia", "buscar");
                servletRequest.setAttribute("nombreSubsistema", subsist);
                return actionMapping.findForward("buscarDispositivo");
            } catch (Exception e) {
                e.printStackTrace();
                servletRequest.setAttribute("mensaje", "No se pudo cargar la b�squeda.[buscar, dispositivoAction]");
                return actionMapping.findForward("mensaje_fw");
            }
        }
        /* ingresar */
        private ActionForward ingresar(ActionMapping actionMapping,
                                       HttpServletRequest servletRequest,
                                       HttpSession httpSession,
                                       Integer ses_idusu) {
            try {
                MantenedorDispositivosLocal dispositivoLocal = LocalizadorServicios.getInstance().getMantenedorDispositivosLocal();
                DispositivoVO dispositivo = new DispositivoVO();
                servletRequest.setAttribute("listaSubsistema", dispositivoLocal.lista_subsistema());
                servletRequest.setAttribute("listaEmpresa", dispositivoLocal.lista_empresa());
                servletRequest.setAttribute("listaTipoDispositivo", dispositivoLocal.lista_tipo_dispositivo());
                return actionMapping.findForward("ingresarDispositivo");
            } catch (Exception e) {
                e.printStackTrace();
                servletRequest.setAttribute("mensaje", "No se pudo cargar los par�metros para el ingreso.[ingresar, dispositivoAction]");
                return actionMapping.findForward("mensaje_fw");
            }
        }
        /* resultado_ingresar */
        private ActionForward resultado_ingresar(ActionMapping actionMapping,
                                                 HttpServletRequest servletRequest,
                                                 HttpSession httpSession,
                                                 Integer ses_idusu) {
        	try {
            String subsist = "";
            String nombreSubsistema="";
            Integer id                     = new Integer(0);
            Integer accion                 = Integer.valueOf(servletRequest.getParameter("accion"));
            Integer id_subsistema          = Integer.valueOf(servletRequest.getParameter("subsistema"));
            Integer id_empresa_mantenedora = Integer.valueOf(servletRequest.getParameter("empresaMantenedora"));
            Integer id_tipo_dispositivo    = Integer.valueOf(servletRequest.getParameter("tipoDispositivo"));
            String nombre                  = servletRequest.getParameter("nombre");
            String descripcion             = servletRequest.getParameter("descripcion");
            String msj = "";
            try {
                MantenedorDispositivosLocal dispositivoLocal = LocalizadorServicios.getInstance().getMantenedorDispositivosLocal();
                MantenedorSubsistemasLocal subsistemaLocal = LocalizadorServicios.getInstance().getMantenedorSubsistemasLocal();
                DispositivoVO dispositivo = new DispositivoVO();
                int idPerfil            = 0;
                idPerfil = dispositivoLocal.idPerfil(ses_idusu);
                servletRequest.setAttribute("idPerfil", idPerfil);

                if(accion==-1){//Modificar
                    Integer id_dispositivo         = Integer.valueOf(servletRequest.getParameter("id_dispositivo"));
                    id = dispositivoLocal.modificarDispositivo(id_dispositivo, nombre, descripcion, id_subsistema,  id_empresa_mantenedora, id_tipo_dispositivo);
                    servletRequest.setAttribute("listaDispositivo", dispositivoLocal.buscarDispositivo(subsist));
                    servletRequest.setAttribute("id_dispositivo", id);
                    //PROBANDO
                    //servletRequest.setAttribute("mensaje","Se ha modificado la informaci�n del dispositivo.");
                    msj = "Se ha modificado la informaci�n del dispositivo.";
                }
                else{//Ingresar nuevo
                    id = dispositivoLocal.ingresarDispositivo(id_subsistema, nombre, descripcion, id_empresa_mantenedora, id_tipo_dispositivo);

                    nombreSubsistema = dispositivoLocal.nombreSubsistema(id);
                    servletRequest.setAttribute("nombre", nombre);
                    servletRequest.setAttribute("id_dispositivo", id);
                    servletRequest.setAttribute("mensaje","Se ha ingresado el nuevo dispositivo.");
                    msj = "Se ha ingresado el nuevo dispositivo.";
                }
              servletRequest.setAttribute("subsistema", nombreSubsistema);
              servletRequest.setAttribute("nombreSubsistema", nombreSubsistema);
              servletRequest.setAttribute("mensaje", msj);
              servletRequest.setAttribute("listaSubsistema", subsistemaLocal.buscarSubsistema());
              servletRequest.setAttribute("listaDispositivo", dispositivoLocal.buscarDispositivo(nombreSubsistema));
              servletRequest.setAttribute("hacia", "buscar");
              return actionMapping.findForward("buscarDispositivo");
              //return this.buscar(actionMapping, servletRequest, httpSession, ses_idusu);

            } catch (Exception e) {
                e.printStackTrace();
                servletRequest.setAttribute("mensaje", "No se pudo ingresar el dispositivo.[resultado_ingresar, dispositivoAction]");
                return actionMapping.findForward("mensaje_fw");
            }
            } catch (Exception e) {
                e.printStackTrace();
                servletRequest.setAttribute("mensaje", "No se pudo ingresar el dispositivo.[resultado_ingresar, dispositivoAction]");
                return actionMapping.findForward("mensaje_fw");
            }
        	
            

        }

        /* modificar_subsistema */
        private ActionForward modificar(ActionMapping actionMapping,
                                        HttpServletRequest servletRequest,
                                        HttpSession httpSession,
                                        Integer ses_idusu) {
            try {
                MantenedorDispositivosLocal dispositivoLocal = LocalizadorServicios.getInstance().getMantenedorDispositivosLocal();
                DispositivoVO dispositivo   = new DispositivoVO();

                servletRequest.setAttribute("listaSubsistema", dispositivoLocal.lista_subsistema());
                servletRequest.setAttribute("listaEmpresa", dispositivoLocal.lista_empresa());
                servletRequest.setAttribute("listaTipoDispositivo", dispositivoLocal.lista_tipo_dispositivo());


                Integer id_dispositivo      = Integer.valueOf(servletRequest.getParameter("id_dispositivo"));
                String nombre               = servletRequest.getParameter("nombre");
                String descripcion          = servletRequest.getParameter("descripcion");
                Integer id_subsistema       = Integer.valueOf(servletRequest.getParameter("id_subsistema"));
                String nombre_subsistema    = servletRequest.getParameter("nombre_subsistema");
                Integer id_empresa          = Integer.valueOf(servletRequest.getParameter("id_empresa"));
                String empresa              = servletRequest.getParameter("empresa");
                Integer id_tipo_dispositivo = Integer.valueOf(servletRequest.getParameter("id_tipo_dispositivo"));
                String tipo_dispositivo     = servletRequest.getParameter("tipo_dispositivo");
                servletRequest.setAttribute("id_dispositivo", id_dispositivo);
                servletRequest.setAttribute("nombre", nombre);
                servletRequest.setAttribute("descripcion", descripcion);
                servletRequest.setAttribute("id_subsistema", id_subsistema);
                servletRequest.setAttribute("nombre_subsistema", nombre_subsistema);
                servletRequest.setAttribute("id_empresa", id_empresa);
                servletRequest.setAttribute("empresa", empresa);
                servletRequest.setAttribute("id_tipo_dispositivo", id_tipo_dispositivo);
                servletRequest.setAttribute("tipo_dispositivo", tipo_dispositivo);
                return actionMapping.findForward("modificarDispositivo");
            } catch (Exception e) {
                e.printStackTrace();
                servletRequest.setAttribute("mensaje", "No se pudo llegar a la p�gina para modificar el dispositivo.[modificar, dispositivoAction]");
                return actionMapping.findForward("mensaje_fw");
            }
        }

        // eliminar_falla
        private ActionForward eliminar(ActionMapping actionMapping,
                                       HttpServletRequest servletRequest,
                                       HttpSession httpSession,
                                       Integer ses_idusu) {

            String respuesta = "si";
            String nombreSubsistema= new String("");
            try {
                MantenedorDispositivosLocal dispositivoLocal = LocalizadorServicios.getInstance().getMantenedorDispositivosLocal();
                MantenedorSubsistemasLocal subsistemaLocal = LocalizadorServicios.getInstance().getMantenedorSubsistemasLocal();
                String subsist = "";
                Integer id_dispositivo = new Integer(servletRequest.getParameter("id_dispositivo"));
                nombreSubsistema = dispositivoLocal.nombreSubsistema(id_dispositivo);
                dispositivoLocal     = LocalizadorServicios.getInstance().getMantenedorDispositivosLocal();
                respuesta = dispositivoLocal.sePuedeEliminar(id_dispositivo);
                if(respuesta=="si"){
                    dispositivoLocal.eliminarDispositivo(id_dispositivo);
                    servletRequest.setAttribute("mensaje", "El dispositivo fue eliminado con �xito.");
                }
                else{
                    servletRequest.setAttribute("mensaje", "El dispositivo no puede ser eliminado porque tiene fallas asociadas.");
                }
                int idPerfil            = 0;
                idPerfil = dispositivoLocal.idPerfil(ses_idusu);
                servletRequest.setAttribute("idPerfil", idPerfil);
                servletRequest.setAttribute("listaDispositivo", dispositivoLocal.buscarDispositivo(subsist));

                servletRequest.setAttribute("listaSubsistema", subsistemaLocal.buscarSubsistema());
                servletRequest.setAttribute("listaDispositivo", dispositivoLocal.buscarDispositivo(nombreSubsistema));
                servletRequest.setAttribute("hacia", "buscar");

                return actionMapping.findForward("buscarDispositivo");
            }
            catch (Exception e) {
                e.printStackTrace();
                servletRequest.setAttribute("mensaje", "No se pudo eliminar el dispositivo.[eliminar, dispositivoAction]");
                return actionMapping.findForward("buscarDispositivo");
            }

        }

        private void jbInit() throws Exception {
        }
    }


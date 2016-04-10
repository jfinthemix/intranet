package proyecto_uoct.controller;

import javax.servlet.http.*;

import org.apache.struts.action.*;
import proyecto_uoct.common.*;
import proyecto_uoct.infoyrep.VO.*;
import proyecto_uoct.infoyrep.model.*;
import proyecto_uoct.reservas.model.RecursosLocal;
import java.util.List;
import java.util.Iterator;
import proyecto_uoct.reservas.VO.ReservaVO;
import proyecto_uoct.reservas.VO.RecursoVO;
import proyecto_uoct.reservas.util.Parse;
import proyecto_uoct.reservas.VO.FiltroRecursos;
import java.util.Date;

import proyecto_uoct.usuario.model.Usuario;
import proyecto_uoct.usuario.model.UsuarioLocal;
import proyecto_uoct.mensaje.model.MensajeLocal;

public class index2Action extends Action {
    public ActionForward execute(ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest request,
                                 HttpServletResponse servletResponse) {

        String accion = (String) request.getParameter("accion");

        if (accion == null) {
        	 try{ 
        	 UsuarioLocal usuLoc = LocalizadorServicios.getInstance().getUsuarioLocal();
             request.setAttribute("cumpleaneros", usuLoc.getCumpleaneros());
             this.frameDer(actionMapping, request);
        	 }catch(Exception e){
                 System.out.print(e.toString());
             }
            return actionMapping.findForward("index2");
        }

        if (accion.equals("portada")) {
            return actionMapping.findForward("portada");
        }
        if (accion.equals("arriba")) {
            try{
                UsuarioLocal usuLoc = LocalizadorServicios.getInstance().getUsuarioLocal();
                request.setAttribute("cumpleaneros", usuLoc.getCumpleaneros());
            	
            	
            }catch(Exception e){
                System.out.print(e.toString());
            }
            return actionMapping.findForward("arriba");
        }
        if (accion.equals("reportajes")) {
            return this.reportajes(actionMapping, request);
        }

       /* if (accion.equals("frameDer")) {
            return this.frameDer(actionMapping, request);
        }*/

        if (accion.equals("cumpleanos")) {
            return this.cuadroCumpleanos(actionMapping, request);
        }

        if (accion.equals("cuadroanexos")) {
            return this.cuadroAnexos(actionMapping, request);
        }

        if (accion.equals("infoinstit")) {
            return this.listarInfoInstit(actionMapping, request);
        }

        if (accion.equals("reportajesAnteriores")) {
            return this.reportajesAnteriores(actionMapping, request);
        }

        /*
         if (accion.equals("documento")) {
            return actionMapping.findForward("documento");
                 }

                 if (accion.equals("restriccion")) {
            return actionMapping.findForward("restriccion");
                 }
         */
        return actionMapping.findForward("index2");
    }

    /**
     * frameDer
     *
     * @param actionMapping ActionMapping
     * @param request HttpServletRequest
     * @return ActionForward
     */
    private void frameDer(ActionMapping actionMapping,
                                   HttpServletRequest request) {
        try {

            RecursosLocal recursosLocal = LocalizadorServicios.getInstance().getRecursosLocal();
            List recursos = recursosLocal.getRecursos();

            Iterator irec = recursos.iterator();
            while (irec.hasNext()) {
                RecursoVO recurso = (RecursoVO) irec.next();

                //Reservas a mostrar
                FiltroRecursos filtro = new FiltroRecursos();
                filtro.setIdRecurso(recurso.getIdRecurso());

                filtro.setFecha(new Date());
                //filtro.setFechaDesde(new Date());
                //filtro.setFechaHasta(new Date());

                recurso.setReservas(recursosLocal.getReservas(filtro));
            }

            request.setAttribute("RECURSOS", recursos);
            //return actionMapping.findForward("frameDer");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error al cargar la página");
            //return actionMapping.findForward("mensaje");
        }

    }


    /**
        * reportajesAnteriores
        *
        * @param actionMapping ActionMapping
        * @param request HttpServletRequest
        * @return ActionForward
        */
       private ActionForward reportajesAnteriores(ActionMapping actionMapping,
                                        HttpServletRequest request) {
           try {


               ReportajeLocal reportajeLocal = LocalizadorServicios.getInstance().
                                               getReportajeLocal();
               MensajeLocal mensajeLocal = LocalizadorServicios.getInstance().getMensajeLocal();

               request.setAttribute("mensaje",mensajeLocal.getMensaje(1));
               request.setAttribute("lreps",reportajeLocal.getTailReportajes());

               return actionMapping.findForward("reportajesAnteriores");
           } catch (LocalizadorServiciosException ex) {
               request.setAttribute("mensaje",
                                    "No se pudo cargar la lista de Reportajes");
               return actionMapping.findForward("mensaje");
           }

    }





    /**
     * reportajes
     *
     * @param actionMapping ActionMapping
     * @param request HttpServletRequest
     * @return ActionForward
     */
    private ActionForward reportajes(ActionMapping actionMapping,
                                     HttpServletRequest request) {
        try {
            ReportajeVO reportaje_izq = null;
            ReportajeVO reportaje_der = null;
            int izq = 1;
            int der = 2;

            ReportajeLocal reportajeLocal = LocalizadorServicios.getInstance().getReportajeLocal();
            MensajeLocal mensajeLocal = LocalizadorServicios.getInstance().getMensajeLocal();
            reportaje_izq = reportajeLocal.getReportajePorTipo(izq);
            reportaje_der = reportajeLocal.getReportajePorTipo(der);

            request.setAttribute("reportaje_izq", reportaje_izq);
            request.setAttribute("reportaje_der", reportaje_der);
            request.setAttribute("mensaje",mensajeLocal.getMensaje(1));

            return actionMapping.findForward("reportajes");
        } catch (LocalizadorServiciosException ex) {
            request.setAttribute("mensaje","No se pudo cargar la pagina inicial");
            return actionMapping.findForward("mensaje");
        }

    }


    /**
   * cuadroCumpleanos
   *
   * @param actionMapping ActionMapping
   * @param servletRequest HttpServletRequest
   * @param httpSession HttpSession
   * @return ActionForward
   */
  private ActionForward cuadroCumpleanos(ActionMapping actionMapping,
                                         HttpServletRequest servletRequest
                                         ) {

      try {

          UsuarioLocal usuarioLocal = LocalizadorServicios.getInstance().
                                      getUsuarioLocal();

          List usuarios = usuarioLocal.getListaUsuariosActivos();

          servletRequest.setAttribute("usuarios", usuarios);
          return actionMapping.findForward("cumpleanos");
      } catch (Exception ex) {
          servletRequest.setAttribute("mensaje",
                                      "No se pudo cargar la página de Cumpleaños");
          return actionMapping.findForward("mensaje");
      }

  }

  /**
  * acuadroAnexos
  *
  * @param actionMapping ActionMapping
  * @param servletRequest HttpServletRequest
  * @return ActionForward
  */
 private ActionForward cuadroAnexos(ActionMapping actionMapping,
                                     HttpServletRequest servletRequest) {

     UsuarioLocal usuarioLocal;
     try {
         usuarioLocal = LocalizadorServicios.getInstance().
                        getUsuarioLocal();

         List anexos = usuarioLocal.getAnexosIndep();
         List anexos_usu = usuarioLocal.getAnexosUsu();

         servletRequest.setAttribute("anexos", anexos);
         servletRequest.setAttribute("anexos_usu", anexos_usu);
         return actionMapping.findForward("cuadroanexos");
     } catch (Exception e) {
         e.printStackTrace();
         servletRequest.setAttribute("mensaje",
                                     "no se pudo cargar el cuadro de anexos");
         return actionMapping.findForward("mensaje");
     }

 }

 /**
   * listarInfoInstit
   *
   * @param actionMapping ActionMapping
   * @param actionForm ActionForm
   * @param servletRequest HttpServletRequest
   * @param servletResponse HttpServletResponse
   * @return ActionForward
   */
  private ActionForward listarInfoInstit(ActionMapping actionMapping,
                                         HttpServletRequest servletRequest) {

      try {
          InfoInstitLocal infoInstitLocal = LocalizadorServicios.getInstance().
                                            getInfoInstitLocal();

          List listaarchivos = infoInstitLocal.getListaArchivos();
          servletRequest.setAttribute("lista", listaarchivos);
          return actionMapping.findForward("listarInfo");
      } catch (LocalizadorServiciosException ex) {
          ex.printStackTrace();
          servletRequest.setAttribute("mensaje",
                                      "No se pudo consultar los archivos de información institucional");
          return actionMapping.findForward("mensaje");
      }
  }



}

package proyecto_uoct.controller;

import java.util.*;

import javax.servlet.http.*;

import org.apache.struts.action.*;
import proyecto_uoct.common.*;
import proyecto_uoct.common.VO.*;
import proyecto_uoct.reservas.VO.FiltroRecursos;
import proyecto_uoct.reservas.VO.RecursoVO;
import proyecto_uoct.reservas.model.RecursosLocal;
import proyecto_uoct.usuario.model.*;

public class indexAction extends Action {
    public ActionForward execute(ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest servletRequest,
                                 HttpServletResponse servletResponse) {

        HttpSession httpSession = servletRequest.getSession();
        Integer ses_idusu;
        /*   try {
               httpSession = servletRequest.getSession();
               DatosSesVO datosses = (DatosSesVO) httpSession.getAttribute(
                       "Ses_Usu");
               ses_idusu = (Integer) datosses.getId();
           } catch (Exception e) {
               e.printStackTrace();
               servletRequest.setAttribute("mensaje", "Su sesión ha caducado");
               return actionMapping.findForward("mensaje_fw");

           }*/

        if (servletRequest.getParameter("hacia").equals("inises")) {
            return iniciarSesion(actionMapping,servletRequest, httpSession);
        }

        if (servletRequest.getParameter("hacia").equals("cargarMenu")) {
            return this.cargarMenu(actionMapping, servletRequest, httpSession);
        }

        if (servletRequest.getParameter("hacia").equals("arriba")) {
            try{
                UsuarioLocal usuLoc = LocalizadorServicios.getInstance().getUsuarioLocal();
                httpSession = servletRequest.getSession();
                DatosSesVO datosses = (DatosSesVO) httpSession.getAttribute("Ses_Usu");
                servletRequest.setAttribute("usuario",datosses);
                servletRequest.setAttribute("cumpleaneros", usuLoc.getCumpleaneros());
            }catch(Exception e){
                System.out.print(e.toString());
            }
            return actionMapping.findForward("arriba");
        }


//-------------------------------------------------
        /* if (servletRequest.getParameter("hacia").compareTo("inises") == 0 &&
              servletRequest.getParameter("nom") != null &&
              servletRequest.getParameter("psw") != null) {
              String user = servletRequest.getParameter("nom");
              String psw = servletRequest.getParameter("psw");

              try {
                  usuarioLocal = LocalizadorServicios.getInstance().
                                 getUsuarioLocal();
              } catch (LocalizadorServiciosException ex) {
                  System.out.println("no se pudo llamar a EJB");
              }

              DatosSesVO usu = usuarioLocal.verifUsu(user, psw);
              if (usu != null) {

// INICIALIZA LA SESION poniendo el id del usuario en ses_idusu
                  httpSession = servletRequest.getSession(true);
                  httpSession.setAttribute("Ses_Usu", usu);

                  return actionMapping.findForward("marco_fw");
              } else {
                  servletRequest.setAttribute("mensaje",
         "No se pudo iniciar la sesion. Nombre de usuario o Password incorrecta");
                  return actionMapping.findForward("error");
              }

          }*/

//_-------------------___--------------------__----------__----------

        if (servletRequest.getParameter("hacia").equals("signout")) {

            return cerrarSession(actionMapping,
                                 servletRequest, httpSession);
        }

//_-------------------___--------------------__----------__----------
        servletRequest.setAttribute("mensaje", "URL incorrecta");
        return actionMapping.findForward("error");

    }

    /**
     * cerrarSession
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward cerrarSession(ActionMapping actionMapping,
                                        HttpServletRequest servletRequest,
                                        HttpSession httpSession) {
        httpSession = servletRequest.getSession();
        httpSession.setAttribute("Ses_Usu", null);
        servletRequest.getSession().invalidate();
        return actionMapping.findForward("signout");
    }

    /**
     * iniciarSesion
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward iniciarSesion(ActionMapping actionMapping,
                                        HttpServletRequest servletRequest,
                                        HttpSession httpSession) {
        try {
            String user = servletRequest.getParameter("nom");
            String psw = servletRequest.getParameter("psw");


         System.setProperty("file.encoding", "ISO8859_1");

         System.out.println("antes:"+System.getProperty("file.encoding"));

         System.out.println("OutputStreamWriter actual:"+
         new java.io.OutputStreamWriter(
        new java.io.ByteArrayOutputStream()).getEncoding());



            UsuarioLocal usuarioLocal = LocalizadorServicios.getInstance().getUsuarioLocal();
            servletRequest.setAttribute("cumpleaneros", usuarioLocal.getCumpleaneros());	
            DatosSesVO usu = usuarioLocal.verifUsu(user, psw);
            if (usu != null) {

// INICIALIZA LA SESION poniendo el id del usuario en ses_idusu
                httpSession = servletRequest.getSession(true);
                httpSession.setAttribute("Ses_Usu", usu);
                httpSession.setMaxInactiveInterval(-1);

                /**************/
                               
                //DatosSesVO datosses = (DatosSesVO) httpSession.getAttribute("Ses_Usu");
                Integer id_perfil = usu.getIdPerfil();

                List listaFuncionalidades = new LinkedList();
                List listaModulos = new LinkedList();
                PerfilLocal perfilLocal = null;

                perfilLocal = LocalizadorServicios.getInstance().getPerfilLocal();

                listaFuncionalidades = perfilLocal.getFunsdePerfil(id_perfil);
                listaModulos = perfilLocal.getModulos();

                List funConURL = new LinkedList();
                Iterator ifuncio = listaFuncionalidades.iterator();
                while (ifuncio.hasNext()) {
                    FuncionalidadVO fun = (FuncionalidadVO) ifuncio.next();
                    if (fun.getURLFun() != null) {
                        funConURL.add(fun);
                    }
                }

                servletRequest.setAttribute("funs", funConURL);
                servletRequest.setAttribute("mods", listaModulos);
                servletRequest.setAttribute("DatosUsu", usu);
                this.frameDer(actionMapping, servletRequest);
                /**************/
                return actionMapping.findForward("marco_fw");
            } else {
                servletRequest.setAttribute("mensaje",
                                            "No se pudo iniciar la sesion. Nombre de usuario o Password incorrecta");
                return actionMapping.findForward("error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "Error en la conexión con la Base de datos.");
            return actionMapping.findForward("error");

        }

    }


    public ActionForward cargarMenu(ActionMapping actionMapping,
                                    HttpServletRequest servletRequest,
                                    HttpSession httpSession) {
        try {
            httpSession = servletRequest.getSession();
            DatosSesVO datosses = (DatosSesVO) httpSession.getAttribute("Ses_Usu");
            Integer id_perfil = datosses.getIdPerfil();

            List listaFuncionalidades = new LinkedList();
            List listaModulos = new LinkedList();
            PerfilLocal perfilLocal = null;

            perfilLocal = LocalizadorServicios.getInstance().getPerfilLocal();

            listaFuncionalidades = perfilLocal.getFunsdePerfil(id_perfil);
            listaModulos = perfilLocal.getModulos();

            List funConURL = new LinkedList();
            Iterator ifuncio = listaFuncionalidades.iterator();
            while (ifuncio.hasNext()) {
                FuncionalidadVO fun = (FuncionalidadVO) ifuncio.next();
                if (fun.getURLFun() != null) {
                    funConURL.add(fun);
                }
            }

            servletRequest.setAttribute("funs", funConURL);
            servletRequest.setAttribute("mods", listaModulos);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return actionMapping.findForward("menu_fw");
    }

    private void frameDer(ActionMapping actionMapping,HttpServletRequest request) {
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
    
}

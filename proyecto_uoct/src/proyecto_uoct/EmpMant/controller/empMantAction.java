package proyecto_uoct.EmpMant.controller;

import java.text.*;
import java.util.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import proyecto_uoct.common.*;
import proyecto_uoct.common.VO.*;
import proyecto_uoct.EmpMant.VO.*;
import proyecto_uoct.EmpMant.model.*;

public class empMantAction extends Action {
    public empMantAction() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
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
        } else {
            servletRequest.setAttribute("mensaje", "hacia = null xd");
            return actionMapping.findForward("mensaje_fw");
        }
        //clic en menú
        if (hacia.equals("buscar_emp")) {
            return buscar_emp(actionMapping,
                              servletRequest,
                              httpSession,
                              ses_idusu);
        }
        if (hacia.equals("ingresar_emp")) {
            return ingresar_emp(actionMapping,
                                servletRequest,
                                httpSession,
                                ses_idusu);
        }
        if (hacia.equals("Buscar empresa")) {
            return resultado_buscar_emp(actionMapping,
                                        servletRequest,
                                        httpSession,
                                        ses_idusu);
        }
        if (hacia.equals("Grabar")) {
            return resultado_ingresar_emp(actionMapping,
                                          servletRequest,
                                          httpSession,
                                          ses_idusu);
        }
        if(hacia.equals("editar_emp")){
            return editar_emp(actionMapping,
                              servletRequest,
                              httpSession,
                              ses_idusu);
        }
        if(hacia.equals("ver_emp")){
            return ver_emp(actionMapping,
                           servletRequest,
                           httpSession,
                           ses_idusu);
        }
        if(hacia.equals("Grabar cambios")){
            return resultado_modificar_emp(actionMapping,
                           servletRequest,
                           httpSession,
                           ses_idusu);
        }
        if (hacia.equals("popUp")) {
            return popUp(actionMapping,
                              servletRequest,
                              httpSession,
                              ses_idusu);
        }
        return actionMapping.getInputForward();
    }
    /* boton buscar_emp */
    private ActionForward buscar_emp(ActionMapping actionMapping,
                                     HttpServletRequest servletRequest,
                                     HttpSession httpSession,
                                     Integer ses_idusu) {
        try {
            return actionMapping.findForward("buscar_emp");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "No se pudo cargar los parámetros para la búsqueda.[buscar_emp, empMantAction]");
            return actionMapping.findForward("mensaje_fw");
        }
    }
    /* boton popUp */
    private ActionForward popUp(ActionMapping actionMapping,
                                     HttpServletRequest servletRequest,
                                     HttpSession httpSession,
                                     Integer ses_idusu) {
        try {
            EmpMantLocal empmantLocal = LocalizadorServicios.getInstance().getEmpMantLocal();
            String nombre             = servletRequest.getParameter("nombre");
            String direccion          = servletRequest.getParameter("direccion");
            String telefono           = servletRequest.getParameter("telefono");
            Integer vigencia           = Integer.valueOf(servletRequest.getParameter("vigencia"));
            String tipo_dispositivo   = servletRequest.getParameter("tipo_dispositivo");
            String mail_1             = servletRequest.getParameter("mail_1");
            String tipo_dispositivo2  = servletRequest.getParameter("tipo_dispositivo2");
            String mail_2             = servletRequest.getParameter("mail_2");
            servletRequest.setAttribute("nombre", nombre);
            servletRequest.setAttribute("direccion", direccion);
            servletRequest.setAttribute("telefono", telefono);
            servletRequest.setAttribute("vigencia", vigencia);
            servletRequest.setAttribute("tipo_dispositivo", tipo_dispositivo);
            servletRequest.setAttribute("mail_1", mail_1);
            servletRequest.setAttribute("tipo_dispositivo2", tipo_dispositivo2);
            servletRequest.setAttribute("mail_2", mail_2);
            return actionMapping.findForward("PopUp");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "No se pudo cargar popUp con datos de la empresa.[popUp, empMantAction]");
            return actionMapping.findForward("mensaje_fw");
        }
    }
    /* boton ingresar_emp */
    private ActionForward ingresar_emp(ActionMapping actionMapping,
                                       HttpServletRequest servletRequest,
                                       HttpSession httpSession,
                                       Integer ses_idusu) {
        try {
            return actionMapping.findForward("ingresar_emp");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje","No se pudo cargar los parámetros para el ingreso.[ingresar_emp, empMantAction]");
            return actionMapping.findForward("mensaje_fw");
        }
    }
    // editar_emp
    private ActionForward editar_emp(ActionMapping actionMapping,
                                     HttpServletRequest
                                     servletRequest,
                                     HttpSession httpSession,
                                     Integer ses_idusu) {
        try {
            EmpMantLocal empmantLocal = LocalizadorServicios.getInstance().
                                        getEmpMantLocal();
            EmpMantVO empmant = new EmpMantVO();
            return actionMapping.findForward("editar_emp");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "No se pudo mostrar la empresa mantenedora.[resultado_editar_emp, empMantAction]");
            return actionMapping.findForward("mensaje_fw");
        }
    }
    /*resultado_buscar_emp*/
    private ActionForward resultado_buscar_emp(ActionMapping actionMapping,
                                               HttpServletRequest
                                               servletRequest,
                                               HttpSession httpSession,
                                               Integer ses_idusu) {
        try {
            EmpMantLocal empmantLocal = LocalizadorServicios.getInstance().getEmpMantLocal();
            Integer vigente           = Integer.valueOf(servletRequest.getParameter("vigente"));
            List listaempresas        = empmantLocal.buscarEmpMant(vigente);
            servletRequest.setAttribute("listaempresas", listaempresas);
            servletRequest.setAttribute("vigente", vigente);
            return actionMapping.findForward("buscar_emp");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "No se pudo cargar la búsqueda de empresa(s) mantenedora(s). [resultado_buscar_emp, empMantAction]");
            return actionMapping.findForward("mensaje_fw");
        }
    }
    // resultado_ingresar_emp
    private ActionForward resultado_ingresar_emp(ActionMapping actionMapping,
                                                 HttpServletRequest
                                                 servletRequest,
                                                 HttpSession httpSession,
                                                 Integer ses_idusu) {
        try {
            EmpMantLocal empmantLocal    = LocalizadorServicios.getInstance().getEmpMantLocal();
            EmpMantVO empmant            = new EmpMantVO();
            int auxIdEmpMant             = -1;
            String nombre                = servletRequest.getParameter("nombre");
            String direccion             = servletRequest.getParameter("direccion");
            String telefono              = servletRequest.getParameter("telefono");
            Integer vigente              = Integer.valueOf(servletRequest.getParameter("vigente"));
            Integer id_tipo_dispositivo  = new Integer(1);
            Integer id_tipo_dispositivo2 = new Integer(2);
            String mail_terreno          = servletRequest.getParameter("mail_terreno");
            String mail_sala             = servletRequest.getParameter("mail_sala");

            auxIdEmpMant                 = empmantLocal.ingresarEmpMant(nombre, telefono, direccion, vigente, id_tipo_dispositivo, mail_terreno, id_tipo_dispositivo2, mail_sala);
            List listaempresa            = empmantLocal.verEmpMant(auxIdEmpMant);
            List listadispositivosD      = empmantLocal.listaDispositivosD(auxIdEmpMant);
            List listadispositivosM      = empmantLocal.listaDispositivosM(auxIdEmpMant);
            servletRequest.setAttribute("listaempresa", listaempresa);
            servletRequest.setAttribute("id_emp", auxIdEmpMant);
            servletRequest.setAttribute("listadispositivosD", listadispositivosD);
            servletRequest.setAttribute("listadispositivosM", listadispositivosM);
            //System.out.println(auxIdEmpMant);
            if(auxIdEmpMant>0){
                servletRequest.setAttribute("mensaje", "La nueva empresa mantenedora fue ingresada con éxito.");
            }
            else{
                servletRequest.setAttribute("mensaje", "La nueva empresa mantenedora no fue ingresada.");
            }
            return actionMapping.findForward("editar_emp");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "No se pudo ingresar la nueva empresa mantenedora.[resultado_ingresar_emp, empMantAction]");
            return actionMapping.findForward("mensaje_fw");
        }
    }
    // ver_emp
    private ActionForward ver_emp(ActionMapping actionMapping,
                                  HttpServletRequest servletRequest,
                                  HttpSession httpSession,
                                  Integer ses_idusu) {
        try {
            EmpMantLocal empmantLocal  = LocalizadorServicios.getInstance().getEmpMantLocal();
            EmpMantVO empmant          = new EmpMantVO();
            Integer id_emp             = Integer.valueOf(servletRequest.getParameter("id_emp"));
            List listaempresa          = empmantLocal.verEmpMant(id_emp);
            List listadispositivosD    = empmantLocal.listaDispositivosD(id_emp);
            List listadispositivosM    = empmantLocal.listaDispositivosM(id_emp);
            servletRequest.setAttribute("listaempresa", listaempresa);
            servletRequest.setAttribute("id_emp", id_emp);
            servletRequest.setAttribute("listadispositivosD", listadispositivosD);
            servletRequest.setAttribute("listadispositivosM", listadispositivosM);
            return actionMapping.findForward("editar_emp");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "No se pudo cargar la empresa mantenedora.[resultado_ver_emp, empMantAction]");
            return actionMapping.findForward("mensaje_fw");
        }
    }
    // resultado_modificar_emp
    private ActionForward resultado_modificar_emp(ActionMapping actionMapping,
                                                  HttpServletRequest servletRequest,
                                                  HttpSession httpSession,
                                                  Integer ses_idusu) {
        try {
            EmpMantLocal empmantLocal = LocalizadorServicios.getInstance().getEmpMantLocal();
            EmpMantVO empmant         = new EmpMantVO();

            Integer id_emp            = Integer.valueOf(servletRequest.getParameter("id_emp"));
            String nombre             = servletRequest.getParameter("nombre");
            String direccion          = servletRequest.getParameter("direccion");
            String telefono           = servletRequest.getParameter("telefono");
            Integer vigente           = Integer.valueOf(servletRequest.getParameter("vigente"));
            String mail_1             = servletRequest.getParameter("mail_TERRENO");
            String mail_2             = servletRequest.getParameter("mail_SALA");
            String[] listaA           = servletRequest.getParameterValues("listaA");
            String[] listaB           = servletRequest.getParameterValues("listaB");
            //System.out.println(listaA.length);
            //System.out.println(listaB.length);
            int var_id_emp             = empmantLocal.modificarEmpMant(id_emp, nombre, direccion, telefono, vigente, mail_1, mail_2);//verEmpMant(id_emp);
            boolean ban                = empmantLocal.modificarDispositivo(listaA, listaB, id_emp); //modificar tabla dispositivo
            List listaempresa          = empmantLocal.verEmpMant(id_emp);
            List listadispositivosD    = empmantLocal.listaDispositivosD(id_emp);
            List listadispositivosM    = empmantLocal.listaDispositivosM(id_emp);
            servletRequest.setAttribute("listaempresa", listaempresa);
            servletRequest.setAttribute("id_emp", id_emp);
            servletRequest.setAttribute("listadispositivosD", listadispositivosD);
            servletRequest.setAttribute("listadispositivosM", listadispositivosM);
            //System.out.println(var_id_emp+ " y "+ban);
            if(var_id_emp>-1 && ban){
                servletRequest.setAttribute("mensaje", "Los cambios fueron realizados exitosamente");
            }
            else{
                servletRequest.setAttribute("mensaje", "Los cambios no fueron realizados en su totalidad");
            }
            return actionMapping.findForward("editar_emp");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "No se pudo cargar la empresa mantenedora.[resultado_ver_emp, empMantAction]");
            return actionMapping.findForward("mensaje_fw");
        }
    }

    private void jbInit() throws Exception {
    }
}

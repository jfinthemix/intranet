package proyecto_uoct.fallas.controller;

import java.text.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import proyecto_uoct.common.*;
import proyecto_uoct.common.VO.*;
import proyecto_uoct.fallas.VO.*;
import proyecto_uoct.fallas.model.*;
import proyecto_uoct.EIV.model.EIVLocal;



public class fallasAction extends Action {
    public fallasAction() {
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
        }
        else {
            servletRequest.setAttribute("mensaje", "hacia = null xd");
            return actionMapping.findForward("mensaje_fw");
        }

//System.out.println("hacia="+hacia);

        //clic en boton 'Buscar fallas' en buscar_fallas.jsp

        if (hacia.equals("Buscar")) {
            return resultado_buscar_falla(actionMapping,
                                          servletRequest,
                                          httpSession,
                                          ses_idusu);
        }
        //clic en menú
        if (hacia.equals("buscar_falla")) {
            return buscar_falla(actionMapping,
                                servletRequest,
                                httpSession,
                                ses_idusu);
        }
        //clic en boton 'Grabar' en ingresar_fallas.jsp
        if (hacia.equals("Grabar")) {
            return resultado_ingresar_falla(actionMapping,
                                            servletRequest,
                                            httpSession,
                                            ses_idusu);
        }
        if (hacia.equals("ingresar_falla")) {
            return ingresar_falla(actionMapping,
                                  servletRequest,
                                  httpSession,
                                  ses_idusu);
        }
        if (hacia.equals("Grabar detalle")) {
            return resultado_ingresar_detalle_falla(actionMapping,
                    servletRequest,
                    httpSession,
                    ses_idusu);
        }
        //clic en lupa para ver el detalle de la falla en buscar_fallas.jsp
        if (hacia.equals("ver_detalle_falla")) {
            return ver_detalle_falla(actionMapping,
                                     servletRequest,
                                     httpSession,
                                     ses_idusu);
        }
        //clic en X para eliminar falla en buscar_fallas.jsp
        if (hacia.equals("eliminar_falla")) {
            return eliminar_falla(actionMapping,
                                  servletRequest,
                                  httpSession,
                                  ses_idusu);
        }
        if (hacia.equals("editar_falla")) {
            return ver_detalle_falla(actionMapping,
                                     servletRequest,
                                     httpSession,
                                     ses_idusu);
        }
        /* NO SE OCUPA AHORA
             if (hacia.equals("fecha_hora_actual")) {
                 return fecha_hora_actual(actionMapping,
                           servletRequest,
                           httpSession,
                           ses_idusu);
             }
         */
        if (hacia.equals("fecha_actual")) {
            return fecha_actual(actionMapping,
                                servletRequest,
                                httpSession,
                                ses_idusu);
        }

        if (hacia.equals("lista_sistema")) {
            return lista_sistema(actionMapping,
                                 servletRequest,
                                 httpSession,
                                 ses_idusu);
        }
        if (hacia.equals("lista_subsistema")) {
            return lista_subsistema(actionMapping,
                                    servletRequest,
                                    httpSession,
                                    ses_idusu);
        }
        if (hacia.equals("lista_dispositivo")) {
            return lista_dispositivo(actionMapping,
                                     servletRequest,
                                     httpSession,
                                     ses_idusu);
        }
        if (hacia.equals("listaSistema")) {
            return listaSistema(actionMapping,
                                servletRequest,
                                httpSession,
                                ses_idusu);
        }
        if (hacia.equals("listaSubsistema")) {
            return listaSubsistema(actionMapping,
                                   servletRequest,
                                   httpSession,
                                   ses_idusu);
        }
        if (hacia.equals("listaDispositivo")) {
            return listaDispositivo(actionMapping,
                                    servletRequest,
                                    httpSession,
                                    ses_idusu);
        }
        if (hacia.equals("leerXml")) {
           return leerXml(actionMapping,
                          servletRequest,
                          httpSession,
                          ses_idusu);
       }
       if (hacia.equals("Reclamar")) {
          return reclamo(actionMapping,
                         servletRequest,
                         httpSession,
                         ses_idusu);
       }
       if (hacia.equals("reporte")) {
          return reporte(actionMapping,
                         servletRequest,
                         httpSession,
                         ses_idusu);
       }
       if (hacia.equals("aReporte")) {
          return aReporte(actionMapping,
                         servletRequest,
                         httpSession,
                         ses_idusu);
       }


       return actionMapping.getInputForward();
   }

    /* resultado_buscar_falla */
    private ActionForward resultado_buscar_falla(ActionMapping actionMapping,
                                                 HttpServletRequest servletRequest,
                                                 HttpSession httpSession,
                                                 Integer ses_idusu) {
        try {
            String estadoString     = "";
            String fechaActual      = "";
            String fechaInicial     = "";
            int idPerfil            = 0;
            FallasLocal fallasLocal = LocalizadorServicios.getInstance().getFallasLocal();
            FallaVO falla           = new FallaVO();
            //rescato las variables
            Integer sistema     = 0;
            Integer subsistema  = 0;
            Integer dispositivo = 0;
            if(servletRequest.getParameter("sistema")!=null){
                sistema     = Integer.valueOf(servletRequest.getParameter("sistema"));
            }
            if(servletRequest.getParameter("subsistema")!=null){
                subsistema  = Integer.valueOf(servletRequest.getParameter("subsistema"));
            }
            if(servletRequest.getParameter("dispositivo")!=null){
                dispositivo = Integer.valueOf(servletRequest.getParameter("dispositivo"));
            }
            String fecha_ini        = servletRequest.getParameter("fecha_ini");//"01-01-2009";
            String fecha_fin        = servletRequest.getParameter("fecha_fin");//"13-03-2009";

            if(fecha_ini==null){
                String aux2 = "1";
                String fechaInicial2 = fallasLocal.fecha_actual();
                aux2 = aux2 + "" + fechaInicial2;
                String fechaActual2 = fallasLocal.fecha_actual();
                String mes = "";
                String anio = "";
                String fecha_actual = fallasLocal.fecha_actual();
                mes = fecha_actual.substring(3, 5);
                anio = fecha_actual.substring(6);
                String fecha_inicial = "01-" + mes + "-" + anio;
                fecha_actual = fechaActual2;
                fecha_ini=fecha_inicial;
                fecha_fin=fecha_actual;
            }


            Integer estado          = Integer.valueOf(servletRequest.getParameter("estado"));
            //llamo a la funcion para que me traiga los datos
            List listafallas        = fallasLocal.buscar_falla(sistema, subsistema, dispositivo, fecha_ini, fecha_fin, estado);
            idPerfil = fallasLocal.idPerfil(ses_idusu);
            //PASO LAS VARIABLES A buscar_fallas y allá las recepciono
            servletRequest.setAttribute("listafallas", listafallas);
            servletRequest.setAttribute("lista_sistema",fallasLocal.lista_sistema());
            servletRequest.setAttribute("lista_subsistema",fallasLocal.lista_subsistema(1));
            servletRequest.setAttribute("lista_dispositivo",fallasLocal.lista_dispositivo(1, 1));
            servletRequest.setAttribute("fecha", fallasLocal.fecha_actual());
            servletRequest.setAttribute("sistema", sistema);
            servletRequest.setAttribute("subsistema", subsistema);
            servletRequest.setAttribute("dispositivo", dispositivo);
            servletRequest.setAttribute("SistemaSubsistema", fallasLocal.SistemaSubsistema());
            servletRequest.setAttribute("SistemaSubsistemaDispositivo", fallasLocal.SistemaSubsistemaDispositivo());
            if(estado.equals(0)){ estadoString= "Todas"; }
            if(estado.equals(1)){ estadoString= "Iniciada"; }
            if(estado.equals(2)){ estadoString= "Cerrada"; }
            if(estado.equals(3)){ estadoString= "Con solicitud de cierre"; }
            if(fecha_ini!=""){ fechaInicial=fecha_ini; }
            if(fecha_fin!=""){ fechaActual=fecha_fin; }
            servletRequest.setAttribute("estadoString", estadoString);
            servletRequest.setAttribute("fechaInicial", fechaInicial);
            servletRequest.setAttribute("fechaActual", fechaActual);
            servletRequest.setAttribute("idPerfil", idPerfil);
            return actionMapping.findForward("buscar_falla");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "No se pudo cargar la búsqueda de fallas. [resultado_buscar_falla, fallasAction]");
            return actionMapping.findForward("mensaje_fw");
        }
    }
    /* boton buscar_falla */
    private ActionForward buscar_falla(ActionMapping actionMapping,
                                       HttpServletRequest servletRequest,
                                       HttpSession httpSession,
                                       Integer ses_idusu) {
        try {
            FallasLocal fallasLocal = LocalizadorServicios.getInstance().getFallasLocal();
            FallaVO falla           = new FallaVO();
            servletRequest.setAttribute("lista_sistema", fallasLocal.lista_sistema());
            servletRequest.setAttribute("SistemaSubsistema", fallasLocal.SistemaSubsistema());
            servletRequest.setAttribute("SistemaSubsistemaDispositivo", fallasLocal.SistemaSubsistemaDispositivo());
            servletRequest.setAttribute("lista_sistema", fallasLocal.lista_sistema());
            servletRequest.setAttribute("lista_subsistema", fallasLocal.lista_subsistema(1));
            servletRequest.setAttribute("lista_dispositivo", fallasLocal.lista_dispositivo(1, 1));
            servletRequest.setAttribute("fecha", fallasLocal.fecha_actual());
            return actionMapping.findForward("buscar_falla");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "No se pudo cargar los parámetros para la búsqueda.[buscar_falla, fallasAction]");
            return actionMapping.findForward("mensaje_fw");
        }
    }
    // resultado_ingresar_falla
    private ActionForward resultado_ingresar_falla(ActionMapping actionMapping,
            HttpServletRequest servletRequest,
            HttpSession httpSession,
            Integer ses_idusu) {
        try {
            FallasLocal fallasLocal = LocalizadorServicios.getInstance().getFallasLocal();
            EIVLocal eivLocal       = LocalizadorServicios.getInstance().getEIVLocal();
            FallaVO falla           = new FallaVO();
            int auxIdFalla          = -1;
            int auxIdComentario     = -1;
            int idPerfil            = 0;
            String nombreUsuario    = "";
            Integer dispositivo     = Integer.valueOf(servletRequest.getParameter("dispositivo"));
            String nomDispositivo   = servletRequest.getParameter("nomDispositivo");
            String problema         = servletRequest.getParameter("problema");
            String titulo           = servletRequest.getParameter("titulo");
            String hora             = servletRequest.getParameter("hora");
            String fecha_actual     = servletRequest.getParameter("fecha_actual");
            String fecha_actual2    = fecha_actual;
            String evento           = servletRequest.getParameter("evento");
            String enviar_a         = servletRequest.getParameter("enviar_a");
            String enviaMail        = servletRequest.getParameter("enviaMail");
            //Jueves 27 agosto, para que al momento de la apertura del reclamo, si lo inica Auter
            //o Ingenieros, quede registrado que uno de los dos lo realizó, de lo contrario fue un integrante del grupo GF
            idPerfil = fallasLocal.idPerfil(ses_idusu); //idPerfil=1 (admin) y =43 (GF)
            String remitente        = "";
            String remitente2        = "";
            if(idPerfil==1 || idPerfil==43){
                remitente="Gestión de Fallas-"+fallasLocal.nombreUsuario(ses_idusu)+"\n gf@uoct.cl";
                remitente2="Gestión de Fallas-"+fallasLocal.nombreUsuario(ses_idusu);
            }
            else{
                remitente=fallasLocal.nombreUsuario(ses_idusu);
                remitente2=fallasLocal.nombreUsuario(ses_idusu);
            }
            //fin actualización, más abajo uso la variable "remitente"

            if(enviaMail==null){
                enviaMail="no";
            }
            fecha_actual            = fecha_actual + " " + hora;
            String comentario       = "";
            if(evento.equals("falla")){
                comentario       = "Se formaliza reclamo de " + nomDispositivo + " a las " + hora + " hr(s). Problema: " + problema + ". Se solicita atender.";
                auxIdFalla = fallasLocal.ingresar_falla(dispositivo, ses_idusu, ses_idusu,
                                           titulo, problema, fecha_actual, fecha_actual, 1,
                                           comentario);
                int empresaMant     = fallasLocal.empresaMantenedora(dispositivo);
                String nomEmpresaMant = fallasLocal.nomEmpresaMantenedora(empresaMant);
                int tipoDispo       = fallasLocal.tipoDispositivo(dispositivo);
                String futuroEnvia  = fallasLocal.mailDispositivo(tipoDispo, empresaMant);
                if(enviaMail.equals("si")){
                    //llamo a la funcion mail
                    String passw = "xxxxx";
                    String envia = "gf@uoct.cl";
                    String recibe = futuroEnvia+","+enviar_a;//"awevar@uoct.cl";
                    String cuerpo = "Sres. " + nomEmpresaMant + ":\n\n" +
                                    comentario +
                                    "\n\n Atte., \n\n--\n "+ remitente +" \n Unidad Operativa de Control de Tránsito\n Ministerio de Transportes y Telecomunicaciones\n www.uoct.cl\n Santa Beatriz 319, Providencia\n Santiago\n [t] 56-2-6760800";
                  String cuerpo2 = "ID UOCT: " + auxIdFalla + "\n" +
                                    "FECHA: " + fecha_actual2 + "\n" +
                                    "HORA: " + hora + "\n" +
                                    "ESTADO: iniciado" + "\n" +
                                    "RESPONSABLE: " + remitente2 + "\n" +
                                    "DESCRIPCION: " + problema;
                    //System.out.print("cuerpo : "+cuerpo);
                    //System.out.print("cuerpo nuevo: "+cuerpo2);
                    eivLocal.alertaEmail(envia, passw, recibe, servletRequest.getParameter("titulo"), cuerpo);
                    eivLocal.alertaEmail(envia, passw, recibe, servletRequest.getParameter("titulo"), cuerpo2);


                }
                List detallefalla   = fallasLocal.ver_detalle_falla(auxIdFalla, "falla");
                servletRequest.setAttribute("listadetallefallas", detallefalla);
                servletRequest.setAttribute("id_falla", auxIdFalla);
                servletRequest.setAttribute("evento", "falla");
                if(enviaMail.equals("si")){
                    servletRequest.setAttribute("mensaje","La falla fue ingresada con éxito. Se ha enviado un mail a " + nomEmpresaMant + ".");
                }
                else{
                    servletRequest.setAttribute("mensaje", "La falla fue ingresada con éxito. El mail no fue enviado por orden suya.");
                }
            }
            else {//if(evento.equals("comentario"))
                comentario       = hora + " hr(s): " + problema;
                auxIdComentario = fallasLocal.ingresar_comentario(dispositivo, ses_idusu, fecha_actual, comentario);
                List detallecomentario   = fallasLocal.ver_detalle_falla(auxIdComentario, "comentario");
                servletRequest.setAttribute("listadetallefallas", detallecomentario);
                servletRequest.setAttribute("id_falla", auxIdComentario);
                servletRequest.setAttribute("evento", "comentario");
                servletRequest.setAttribute("mensaje", "El comentario fue ingresado con éxito.");
            }
            idPerfil = fallasLocal.idPerfil(ses_idusu);
            servletRequest.setAttribute("idPerfil", idPerfil);
            return actionMapping.findForward("editar_falla");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "No se pudo ingresar el evento XXX.[resultado_ingresar_falla, fallasAction]");
            return actionMapping.findForward("mensaje_fw");
        }
    }
    /* boton ingresar_falla */
    private ActionForward ingresar_falla(ActionMapping actionMapping,
                                         HttpServletRequest servletRequest,
                                         HttpSession httpSession,
                                         Integer ses_idusu) {
        try {
            FallasLocal fallasLocal = LocalizadorServicios.getInstance().getFallasLocal();
            FallaVO falla           = new FallaVO();

            servletRequest.setAttribute("lista_sistema", fallasLocal.lista_sistema());
            servletRequest.setAttribute("lista_subsistema", fallasLocal.lista_subsistema(1));
            servletRequest.setAttribute("lista_dispositivo", fallasLocal.lista_dispositivo(1, 1));
            servletRequest.setAttribute("lista_dispositivo", fallasLocal.lista_dispositivo(1, 1));
            servletRequest.setAttribute("SistemaSubsistema", fallasLocal.SistemaSubsistema());
            servletRequest.setAttribute("SistemaSubsistemaDispositivo", fallasLocal.SistemaSubsistemaDispositivo());

            String deDondeVieneLaPeticion = (servletRequest.getParameter("vieneDe")==null ? "menu": servletRequest.getParameter("vieneDe"));

            if(deDondeVieneLaPeticion=="menu"){

            }
            else{
                Integer sistema        = Integer.valueOf(servletRequest.getParameter("sistema"));
                Integer subsistema     = Integer.valueOf(servletRequest.getParameter("subsistema"));
                Integer dispositivo    = Integer.valueOf(servletRequest.getParameter("dispositivo"));

                String nomSistema        = servletRequest.getParameter("nomSistema");
                String nomSubsistema     = servletRequest.getParameter("nomSubsistema");
                String nomDispositivo    = servletRequest.getParameter("nomDispositivo");

                servletRequest.setAttribute("sistema", sistema);
                servletRequest.setAttribute("subsistema", subsistema);
                servletRequest.setAttribute("dispositivo", dispositivo);
                servletRequest.setAttribute("nomSistema", nomSistema);
                servletRequest.setAttribute("nomSubsistema", nomSubsistema);
                servletRequest.setAttribute("nomDispositivo", nomDispositivo);

                //String dispoX = servletRequest.getParameter("dispositivoXml");
                //String problX = servletRequest.getParameter("problemaXml");
                String problema        = servletRequest.getParameter("problema");


                //servletRequest.setAttribute("dispositivoXml", dispositivo);//problema con String / Integer...
                servletRequest.setAttribute("problemaXml", problema);


            }
            //para hacer esto hay que reconocer el id del dispositivo y la empresa mantenedora,
            //PENDIENTE!!!
            //servletRequest.setAttribute("mailsBd", fallasLocal.mailDispositivo(1, 1));
            return actionMapping.findForward("ingresar_falla");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "No se pudo cargar los parámetros para el ingreso.[ingresar_falla, fallasAction]");
            return actionMapping.findForward("mensaje_fw");
        }
    }
    // ver_detalle_falla
    private ActionForward ver_detalle_falla(ActionMapping actionMapping,
                                            HttpServletRequest servletRequest,
                                            HttpSession httpSession,
                                            Integer ses_idusu) {
        try {
            FallasLocal fallasLocal = LocalizadorServicios.getInstance(). getFallasLocal();
            FallaVO falla           = new FallaVO();
            int idPerfil            = 0;
            Integer id_falla        = Integer.valueOf(servletRequest.getParameter("id_falla"));
            List listadetallefallas = fallasLocal.ver_detalle_falla(id_falla, "falla");
            idPerfil                = fallasLocal.idPerfil(ses_idusu);
            servletRequest.setAttribute("listadetallefallas", listadetallefallas);
            servletRequest.setAttribute("id_falla", id_falla);
            servletRequest.setAttribute("idPerfil", idPerfil);
            servletRequest.setAttribute("evento", "falla");
            return actionMapping.findForward("editar_falla");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "No se pudo cargar la búsqueda de la falla.[ver_detalle_falla, fallasAction]");
            return actionMapping.findForward("mensaje_fw");
        }
    }
    // resultado_ingresar_detalle_falla
    private ActionForward resultado_ingresar_detalle_falla(ActionMapping actionMapping,
            HttpServletRequest servletRequest,
            HttpSession httpSession,
            Integer ses_idusu) {
        try {
            FallasLocal fallasLocal = LocalizadorServicios.getInstance(). getFallasLocal();
            EIVLocal eivLocal       = LocalizadorServicios.getInstance().getEIVLocal();
            FallaVO falla           = new FallaVO();
            int idPerfil            = 0;
            String nombreUsuario    = "";
            Integer id_falla        = Integer.valueOf(servletRequest.getParameter("id_falla"));
            String titulo           = servletRequest.getParameter("titulo");
            String para_comentario  = servletRequest.getParameter("comentario");
            String comentario       = ""+para_comentario+"";
            String fecha_actual     = servletRequest.getParameter("fecha_actual");
            String fecha_cierre     = servletRequest.getParameter("fecha_cierre");
            String hora             = servletRequest.getParameter("hora");
            fecha_actual            = fecha_cierre + " " + hora;
            String cerrar           = servletRequest.getParameter("cerrar");
            String solicitarcerrar  = servletRequest.getParameter("solicitarcerrar");
            int idDispo             = fallasLocal.idDispositivo(id_falla);
            int empresaMant         = fallasLocal.empresaMantenedora(idDispo);
            int tipoDispo           = fallasLocal.tipoDispositivo(idDispo);
            String futuroEnvia      = fallasLocal.mailDispositivo(tipoDispo, empresaMant);
            if(cerrar==null){
                //SOLICITUD DE CIERRE, PERFIL AUTER-SSS y INGENIEROS-UOCT
                if (solicitarcerrar.equals("si")){
                    fallasLocal.ingresar_detalle_falla_y_solicitarcierre(id_falla, ses_idusu, comentario, fecha_actual);
                    String passw      = "xxxxx";
                    String envia      = "gf@uoct.cl";//"tecnicos-ingenieros@uoct.cl"
                    String recibe     = futuroEnvia;//"awevar@uoct.cl";
                    nombreUsuario     = fallasLocal.nombreUsuario(ses_idusu);
                    String cuerpo     = "Sres. Gestión de Fallas:\n\n" + comentario + "\n\n Atte., \n\n--\n " + nombreUsuario;
                    List detallefalla = fallasLocal.ver_detalle_falla(id_falla, "falla");
                    eivLocal.alertaEmail(envia, passw, recibe, servletRequest.getParameter("titulo"), cuerpo);
                    servletRequest.setAttribute("listadetallefallas", detallefalla);
                    servletRequest.setAttribute("id_falla", id_falla);
                    servletRequest.setAttribute("mensaje", "La solicitud de cierre de la falla fue ingresada con éxito. Se ha enviado un mail a Gestión de Fallas.");
                }
                else if (solicitarcerrar.equals("no")){
                    fallasLocal.ingresar_detalle_falla(id_falla, ses_idusu, comentario, fecha_actual);
                    List detallefalla2   = fallasLocal.ver_detalle_falla(id_falla, "falla");
                    servletRequest.setAttribute("listadetallefallas", detallefalla2);
                    servletRequest.setAttribute("id_falla", id_falla);
                    servletRequest.setAttribute("mensaje", "El nuevo comentario de la falla fue ingresado con éxito.");
                }
            }
            else{
                //CIERRE, PERFIL ADMINISTRADOR-UOCT
                if (cerrar.equals("si")){
                    fallasLocal.ingresar_detalle_falla_y_cierre(id_falla, ses_idusu, comentario, fecha_actual);
                    String nomEmpresaMant = fallasLocal.nomEmpresaMantenedora(empresaMant);
                    String passw          = "xxxxx";
                    String envia          = "gf@uoct.cl";
                    String recibe         = futuroEnvia;//"awevar@uoct.cl";//
                    String cuerpo         = "Sres. "+ nomEmpresaMant +":\n\n" + comentario +
                                            "\n\n Atte., \n\n--\n Gestión de Fallas-"+fallasLocal.nombreUsuario(ses_idusu)+"\ngf@uoct.cl\n Unidad Operativa de Control de Tránsito\n Ministerio de Transportes y Telecomunicaciones\n www.uoct.cl\n Santa Beatriz 319, Providencia\n Santiago\n [t] 56-2-6760800";
                    String cuerpo2 = "ID UOCT: " + id_falla + "\n" +
                                     "FECHA: " + fecha_cierre + "\n" +
                                     "HORA: " + hora + "\n" +
                                     "ESTADO: CERRADO" + "\n" +
                                     "RESPONSABLE: " + envia +
                                     "\n" + "DESCRIPCION: " + comentario;

                    List detallefalla     = fallasLocal.ver_detalle_falla(id_falla, "falla");
                    eivLocal.alertaEmail(envia, passw, recibe, titulo, cuerpo);
                    eivLocal.alertaEmail(envia, passw, recibe, servletRequest.getParameter("titulo"), cuerpo2);

                    servletRequest.setAttribute("listadetallefallas", detallefalla);
                    servletRequest.setAttribute("id_falla", id_falla);
                    servletRequest.setAttribute("mensaje", "El cierre de la falla fue ingresado con éxito. Se ha enviado un mail a " + nomEmpresaMant + ".");
                }
                else if (cerrar.equals("no")){
                    fallasLocal.ingresar_detalle_falla(id_falla, ses_idusu, comentario, fecha_actual);
                    List detallefalla2   = fallasLocal.ver_detalle_falla(id_falla, "falla");
                    servletRequest.setAttribute("listadetallefallas", detallefalla2);
                    servletRequest.setAttribute("id_falla", id_falla);
                    servletRequest.setAttribute("mensaje", "El nuevo comentario de la falla fue ingresado con éxito.");
                }
            }
            idPerfil = fallasLocal.idPerfil(ses_idusu);
            servletRequest.setAttribute("idPerfil", idPerfil);
            servletRequest.setAttribute("evento", "falla");
            return actionMapping.findForward("editar_falla");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "No se pudo cargar el cierre de la falla.[resultado_ingresar_detalle_falla, fallasAction]");
            return actionMapping.findForward("mensaje_fw");
        }
    }
    // eliminar_falla
    private ActionForward eliminar_falla(ActionMapping actionMapping,
                                         HttpServletRequest servletRequest,
                                         HttpSession httpSession,
                                         Integer ses_idusu) {
        FallasLocal fallasLocal;
        try {
            Integer id_falla = new Integer(servletRequest.getParameter("id_falla"));
            fallasLocal      = LocalizadorServicios.getInstance().getFallasLocal();
            fallasLocal.eliminar_falla(id_falla);
            servletRequest.setAttribute("mensaje", "La falla fue eliminada de la bitácora con éxito.");
            return actionMapping.findForward("mensaje_fw");
        }
        catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "No se pudo eliminar la falla.[eliminar_falla, fallasAction]");
            return actionMapping.findForward("mensaje_fw");
        }
    }
    /* hora_actual */
    private ActionForward fecha_actual(ActionMapping actionMapping,
                                      HttpServletRequest servletRequest,
                                      HttpSession httpSession,
                                      Integer ses_idusu) {
        FallasLocal fallasLocal;
        try {
            fallasLocal  = LocalizadorServicios.getInstance().getFallasLocal();
            String fecha = "";
            fecha        = fallasLocal.fecha_actual();
            servletRequest.setAttribute("fecha", fecha);
            return actionMapping.findForward("buscar_falla");
        }
        catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "No se pudo realizar la búsqueda de sistemas");
            return actionMapping.findForward("mensaje_fw");
        }
    }
    /* lista_sistema */
    private ActionForward lista_sistema(ActionMapping actionMapping,
                                        HttpServletRequest servletRequest,
                                        HttpSession httpSession,
                                        Integer ses_idusu) {
        FallasLocal fallasLocal;
        try {
            fallasLocal         = LocalizadorServicios.getInstance().getFallasLocal();
            String fecha_actual = "";
            fecha_actual        = fallasLocal.fecha_actual();
            servletRequest.setAttribute("fecha", fecha_actual);
            return actionMapping.findForward("buscar_falla");
        }
        catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "No se pudo traer la fecha actual");
            return actionMapping.findForward("mensaje_fw");
        }
    }
    /* lista_subsistema */
    private ActionForward lista_subsistema(ActionMapping actionMapping,
                                           HttpServletRequest servletRequest,
                                           HttpSession httpSession,
                                           Integer ses_idusu) {
        FallasLocal fallasLocal;
        try {
            fallasLocal             = LocalizadorServicios.getInstance().getFallasLocal();
            List lista_ss           = new LinkedList();
            String id_subsistema    = servletRequest.getParameter("id_subsistema");
            Integer id_subsistema_i = Integer.valueOf(id_subsistema); //convertir de string a int
            servletRequest.setAttribute("perfiles", lista_ss);
            return actionMapping.findForward("buscar_falla");
        }
        catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "No se pudo realizar la búsqueda de subsistemas");
            return actionMapping.findForward("mensaje_fw");
        }
    }
    /* lista_dispositivo */
    private ActionForward lista_dispositivo(ActionMapping actionMapping,
                                            HttpServletRequest servletRequest,
                                            HttpSession httpSession,
                                            Integer ses_idusu) {
        FallasLocal fallasLocal;
        try {
            fallasLocal              = LocalizadorServicios.getInstance().getFallasLocal();
            List lista_d             = new LinkedList();
            String id_dispositivo    = servletRequest.getParameter("id_dispositivo");
            Integer id_dispositivo_i = Integer.valueOf(id_dispositivo);
            servletRequest.setAttribute("perfiles", lista_d);
            return actionMapping.findForward("buscar_falla");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "No se pudo realizar la búsqueda de dispositivos");
            return actionMapping.findForward("mensaje_fw");
        }
    }
    /* listaSistema */
    private ActionForward listaSistema(ActionMapping actionMapping,
                                       HttpServletRequest servletRequest,
                                       HttpSession httpSession,
                                       Integer ses_idusu) {
        FallasLocal fallasLocal;
        try{
            fallasLocal          = LocalizadorServicios.getInstance().getFallasLocal();
            List lista_s         = new LinkedList();
            String id_sistema    = servletRequest.getParameter("sistema");
            Integer id_sistema_i = Integer.valueOf(id_sistema);
            lista_s              = fallasLocal.lista_subsistema(id_sistema_i);
            servletRequest.setAttribute("lista_sistema", lista_s);
            return actionMapping.findForward("ingresar_falla");
        }
        catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "No se pudo realizar la búsqueda de sistemas(Modulo: IF)");
            return actionMapping.findForward("mensaje_fw");
        }
    }
    /* listaSubsistema */
    private ActionForward listaSubsistema(ActionMapping actionMapping,
                                          HttpServletRequest servletRequest,
                                          HttpSession httpSession,
                                          Integer ses_idusu) {
        FallasLocal fallasLocal;
        try {
            fallasLocal             = LocalizadorServicios.getInstance().getFallasLocal();
            List lista_ss           = new LinkedList();
            String id_sistema       = servletRequest.getParameter("sistema");
            Integer id_sistema_i    = Integer.valueOf(id_sistema);
            String id_subsistema    = servletRequest.getParameter("subsistema");
            Integer id_subsistema_i = Integer.valueOf(id_subsistema);
            lista_ss                = fallasLocal.lista_dispositivo(id_sistema_i, id_subsistema_i);
            servletRequest.setAttribute("lista_subsistema", lista_ss);
            return actionMapping.findForward("ingresar_falla");
        }
        catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "No se pudo realizar la búsqueda de subsistemas (Modulo: IF)");
            return actionMapping.findForward("mensaje_fw");
        }
    }
    /* listDispositivo */
    private ActionForward listaDispositivo(ActionMapping actionMapping,
                                           HttpServletRequest servletRequest,
                                           HttpSession httpSession,
                                           Integer ses_idusu) {
        FallasLocal fallasLocal;
        try {
            fallasLocal              = LocalizadorServicios.getInstance().getFallasLocal();
            List lista_d             = new LinkedList();
            String id_dispositivo    = servletRequest.getParameter("dispositivo");
            Integer id_dispositivo_i = Integer.valueOf(id_dispositivo);
            servletRequest.setAttribute("lista_dispositivo", lista_d);
            return actionMapping.findForward("ingresar_falla");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "No se pudo realizar la búsqueda de dispositivos(Modulo: IF)");
            return actionMapping.findForward("mensaje_fw");
        }
    }

    /* leerXml */
   private ActionForward leerXml(ActionMapping actionMapping,
                                          HttpServletRequest servletRequest,
                                          HttpSession httpSession,
                                          Integer ses_idusu) {

        try {
            FallasLocal fallasLocal = LocalizadorServicios.getInstance().getFallasLocal();
            LectorSAX lector  = new LectorSAX();
            List nodo         = new ArrayList();
            lector.lee("C:/fallas_censo.xml");//"http://badopi.net/atom.xml"
            nodo = lector.getNodos();
            servletRequest.setAttribute("nodo", nodo);
            servletRequest.setAttribute("lista_dispositivo",fallasLocal.lista_dispositivo2(3, 5));
            return actionMapping.findForward("leerXml");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje", "No se pudo realizar la lectura del xml");
            return actionMapping.findForward("mensaje_fw");
        }
    }


    /* boton Reclamo */
       private ActionForward reclamo(ActionMapping actionMapping,
                                          HttpServletRequest servletRequest,
                                          HttpSession httpSession,
                                          Integer ses_idusu) {
           try {
               FallasLocal fallasLocal = LocalizadorServicios.getInstance().getFallasLocal();
               FallaVO falla           = new FallaVO();
               Integer dispositivo = Integer.valueOf(servletRequest.getParameter("dispositivo"));
               //String dispositivo = "URTD 0";
               String problema = servletRequest.getParameter("problema");
               //String problema = "Registros Totales = 287, registros esperados = 288";

               servletRequest.setAttribute("dispositivoXml", dispositivo);
               servletRequest.setAttribute("problemaXml", problema);
               return this.ingresar_falla(actionMapping, servletRequest, httpSession, ses_idusu);

               //return actionMapping.findForward("ingresar_falla");
           } catch (Exception e) {
               e.printStackTrace();
               servletRequest.setAttribute("mensaje", "No se pudo cargar los parámetros para la búsqueda.[buscar_falla, fallasAction]");
               return actionMapping.findForward("mensaje_fw");
           }
    }


    /* reporte */
        private ActionForward reporte(ActionMapping actionMapping,
                                                     HttpServletRequest servletRequest,
                                                     HttpSession httpSession,
                                                     Integer ses_idusu) {
            try {
                String estadoString     = "";
                String fechaActual      = "";
                String fechaInicial     = "";
                int idPerfil            = 0;
                FallasLocal fallasLocal = LocalizadorServicios.getInstance().getFallasLocal();
                FallaVO falla           = new FallaVO();
                //rescato las variables
                Integer sistema     = 0;
                Integer subsistema  = 0;
                Integer dispositivo = 0;
                if(servletRequest.getParameter("sistema")!=null){
                    sistema     = Integer.valueOf(servletRequest.getParameter("sistema"));
                }
                if(servletRequest.getParameter("subsistema")!=null){
                    subsistema  = Integer.valueOf(servletRequest.getParameter("subsistema"));
                }
                if(servletRequest.getParameter("dispositivo")!=null){
                    dispositivo = Integer.valueOf(servletRequest.getParameter("dispositivo"));
                }
                String fecha_ini        = servletRequest.getParameter("fecha_ini");//"01-01-2009";
                String fecha_fin        = servletRequest.getParameter("fecha_fin");//"13-03-2009";

                if(fecha_ini==null){
                    String aux2 = "1";
                    String fechaInicial2 = fallasLocal.fecha_actual();
                    aux2 = aux2 + "" + fechaInicial2;
                    String fechaActual2 = fallasLocal.fecha_actual();
                    String mes = "";
                    String anio = "";
                    String fecha_actual = fallasLocal.fecha_actual();
                    mes = fecha_actual.substring(3, 5);
                    anio = fecha_actual.substring(6);
                    String fecha_inicial = "01-" + mes + "-" + anio;
                    fecha_actual = fechaActual2;
                    fecha_ini=fecha_inicial;
                    fecha_fin=fecha_actual;
                }
                //llamo a la funcion para que me traiga los datos
                List listafallas        = fallasLocal.consultarReporte(sistema, subsistema, dispositivo, fecha_ini, fecha_fin);
                idPerfil = fallasLocal.idPerfil(ses_idusu);
                //PASO LAS VARIABLES A buscar_fallas y allá las recepciono
                servletRequest.setAttribute("listafallas", listafallas);
                servletRequest.setAttribute("lista_sistema",fallasLocal.lista_sistema());
                servletRequest.setAttribute("lista_subsistema",fallasLocal.lista_subsistema(1));
                servletRequest.setAttribute("lista_dispositivo",fallasLocal.lista_dispositivo(1, 1));
                servletRequest.setAttribute("fecha", fallasLocal.fecha_actual());
                servletRequest.setAttribute("sistema", sistema);
                servletRequest.setAttribute("subsistema", subsistema);
                servletRequest.setAttribute("dispositivo", dispositivo);
                servletRequest.setAttribute("SistemaSubsistema", fallasLocal.SistemaSubsistema());
                servletRequest.setAttribute("SistemaSubsistemaDispositivo", fallasLocal.SistemaSubsistemaDispositivo());
                if(fecha_ini!=""){ fechaInicial=fecha_ini; }
                if(fecha_fin!=""){ fechaActual=fecha_fin; }
                servletRequest.setAttribute("estadoString", estadoString);
                servletRequest.setAttribute("fechaInicial", fechaInicial);
                servletRequest.setAttribute("fechaActual", fechaActual);
                servletRequest.setAttribute("idPerfil", idPerfil);
                return actionMapping.findForward("aReporte");
            } catch (Exception e) {
                e.printStackTrace();
                servletRequest.setAttribute("mensaje", "No se pudo cargar la búsqueda de fallas. [resultado_buscar_falla, fallasAction]");
                return actionMapping.findForward("mensaje_fw");
            }
    }
    /* boton aReporte */
       private ActionForward aReporte(ActionMapping actionMapping,
                                          HttpServletRequest servletRequest,
                                          HttpSession httpSession,
                                          Integer ses_idusu) {
           try {
               FallasLocal fallasLocal = LocalizadorServicios.getInstance().getFallasLocal();
               FallaVO falla           = new FallaVO();
               servletRequest.setAttribute("lista_sistema", fallasLocal.lista_sistema());
               servletRequest.setAttribute("SistemaSubsistema", fallasLocal.SistemaSubsistema());
               servletRequest.setAttribute("SistemaSubsistemaDispositivo", fallasLocal.SistemaSubsistemaDispositivo());
               servletRequest.setAttribute("lista_sistema", fallasLocal.lista_sistema());
               servletRequest.setAttribute("lista_subsistema", fallasLocal.lista_subsistema(1));
               servletRequest.setAttribute("lista_dispositivo", fallasLocal.lista_dispositivo(1, 1));
               servletRequest.setAttribute("fecha", fallasLocal.fecha_actual());
               return actionMapping.findForward("aReporte");
           } catch (Exception e) {
               e.printStackTrace();
               servletRequest.setAttribute("mensaje", "No se pudo cargar los parámetros para la búsqueda.[buscar_falla, fallasAction]");
               return actionMapping.findForward("mensaje_fw");
           }
    }



    private void jbInit() throws Exception {
    }
}

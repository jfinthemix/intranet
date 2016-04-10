package proyecto_uoct.documentacion.controller;

import java.util.*;

import javax.servlet.http.*;

import org.apache.struts.action.*;
import proyecto_uoct.common.*;
import proyecto_uoct.common.VO.*;
import proyecto_uoct.documentacion.VO.*;
import proyecto_uoct.documentacion.model.*;

public class clienteAction extends Action {
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

        Integer id_usu = datoses.getId();

//-------------------------------------------------------
        if (servletRequest.getParameter("hacia").equals("busCli_pop")) {
            return busCli_pop(actionMapping, servletRequest,
                              httpSession);

        }
//----------------------------------------------
//-------------------------------------------------------
        if (servletRequest.getParameter("hacia").equals("exportarAgenda")) {
            return exportarAgenda(actionMapping, servletRequest,
                                  httpSession);

        }
        if (servletRequest.getParameter("hacia").equals("aregistrarCliente")) {
            return aregistrarCliente(actionMapping, servletRequest,
                                     httpSession);

        }

        if (servletRequest.getParameter("hacia").equals("ingresarCli")) {
            return ingresarCliente(actionMapping, servletRequest,
                                   httpSession);

        }
        if (servletRequest.getParameter("hacia").equals("buscarCli")) {
            return buscarCliente(actionMapping, servletRequest,
                                 httpSession);

        }
        if (servletRequest.getParameter("hacia").equals("abuscarCli")) {
            return abuscarCliente(actionMapping, servletRequest,
                                  httpSession);

        }

        if (servletRequest.getParameter("hacia").equals("abuscarParaEditarCli")) {
            return actionMapping.findForward("buscarParaEditarCli_fw");

        }
        if (servletRequest.getParameter("hacia").equals("buscarparaEditarCli")) {
            return buscarParaEditarCli(actionMapping, servletRequest,
                                       httpSession);

        }
        if (servletRequest.getParameter("hacia").equals("aeditarDatosCli")) {
            return aeditarDatosCliente(actionMapping, servletRequest,
                                       httpSession);

        }

        if (servletRequest.getParameter("hacia").equals("actualizarDatosCli")) {
            return actualizarDatosCliente(actionMapping, servletRequest,
                                          httpSession);

        }
        if (servletRequest.getParameter("hacia").equals("cambiarEstadoCli")) {
            return cambiarEstadoCliente(actionMapping, servletRequest,
                                        httpSession);

        }

//---------------------------------------------------------
//                      ENTIDADES EXTERNAS

        if (servletRequest.getParameter("hacia").compareTo("aregEntExt") ==
            0) {
            return aregEntExt(actionMapping, servletRequest);
        }

        if (servletRequest.getParameter("hacia").compareTo("registrarEntExt") ==
            0) {
            return registrarEntExt(actionMapping, servletRequest);
        }

        if (servletRequest.getParameter("hacia").compareTo("abuscarEntidadExt") ==
            0) {
            return actionMapping.findForward("buscarEntExt_fw");
        }

        if (servletRequest.getParameter("hacia").compareTo("buscarEntExt") ==
            0) {
            return buscarEntExt(actionMapping, servletRequest);
        }

        /*
         if (servletRequest.getParameter("hacia").equals("aeditarEntidadesExt")) {
            return aeditarEntidadesExternas(actionMapping, servletRequest,
                                            httpSession);

                 }
         */

        if (servletRequest.getParameter("hacia").equals("busParaEdEntExt")) {
            return aBusParaEdEntExt(actionMapping, servletRequest,
                                    httpSession);

        }

        if (servletRequest.getParameter("hacia").equals("abusParaEdEntExt")) {
            return actionMapping.findForward("busParaEdEntExt_fw");

        }

        if (servletRequest.getParameter("hacia").equals(
                "aeditarEntidadExterna")) {
            return aeditarEntidadExterna(actionMapping, servletRequest,
                                         httpSession);

        }
        if (servletRequest.getParameter("hacia").equals(
                "actualizarEntExt")) {
            return actualizarEntidadExterna(actionMapping, servletRequest,
                                            httpSession);

        }
        if (servletRequest.getParameter("hacia").equals(
                "cambiarEstadoEntExt")) {
            return cambiarEstadoEntExt(actionMapping, servletRequest,
                                       httpSession);

        }

//---------------------------------------------------------
        if (servletRequest.getParameter("hacia").compareTo("detalleCliente") ==
            0) {
            if (servletRequest.getParameter("id_cli") != null) {
                Integer id_cli = Integer.valueOf(servletRequest.getParameter(
                        "id_cli"));
                ClienteLocal clienteLocal = null;
                ClienteVO datoscli = null;

                try {
                    clienteLocal = LocalizadorServicios.getInstance().
                                   getClienteLocal();
                } catch (LocalizadorServiciosException ex) {
                    System.out.println("no se pudo llamar a EJB");
                }

                datoscli = clienteLocal.getDatosCli(id_cli);

                servletRequest.setAttribute("datoscli", datoscli);
                return actionMapping.findForward("detalleCli_fw");
            } else {
                return actionMapping.findForward("error_fw");
            }
        }
//---------------------------------------------------------

        if (servletRequest.getParameter("hacia").compareTo("detalleEntExt") ==
            0) {
            if (servletRequest.getParameter("id_ent") != null) {
                Integer id_ent = Integer.valueOf(servletRequest.getParameter(
                        "id_ent"));
                ClienteLocal clienteLocal = null;
                EntExtVO ent = null;

                try {
                    clienteLocal = LocalizadorServicios.getInstance().
                                   getClienteLocal();
                } catch (LocalizadorServiciosException ex) {
                    System.out.println("no se pudo llamar a EJB");
                }

                ent = clienteLocal.getDatosEnt(id_ent);

                servletRequest.setAttribute("datosEntidad", ent);
                return actionMapping.findForward("detalleEnt_fw");
            } else {
                return actionMapping.findForward("error_fw");
            }
        }

//---------------------------------------------------------
        /*        if (servletRequest.getParameter("hacia").compareTo("actualizarDatosCli") ==
                    0) {

         Integer id_cli = Integer.valueOf(servletRequest.getParameter(
                            "id_cli"));
         String nom_cli = (String) servletRequest.getParameter("nom_cli");
         String ape_cli = (String) servletRequest.getParameter("ape_cli");
         Integer tel_cli = Integer.valueOf(servletRequest.getParameter(
                            "tel_cli"));
         Integer cel_cli = Integer.valueOf(servletRequest.getParameter(
                            "cel_cli"));
         String email_cli = (String) servletRequest.getParameter("email_cli");
         Integer id_instit = Integer.valueOf(servletRequest.getParameter(
                            "id_instit"));
         String comentario = servletRequest.getParameter("comentario");

                    ClienteLocal clienteLocal = null;

                    ClienteVO datoscli = null;

                    try {
                        clienteLocal = LocalizadorServicios.getInstance().
                                       getClienteLocal();
                    } catch (LocalizadorServiciosException ex) {
                        System.out.println("no se pudo llamar a EJB");
                    }

         clienteLocal.updateDatosCli(id_cli, nom_cli, ape_cli, tel_cli,
                                                email_cli, cel_cli, id_instit);

                    datoscli = clienteLocal.getDatosCli(id_cli);

                    servletRequest.setAttribute("datoscli", datoscli);
                    return actionMapping.findForward("detalleCli_fw");

                }*/
//--------------------------------------------------------

        servletRequest.setAttribute("mensaje", "URL no válida");
        return actionMapping.findForward("error_fw");
    }

    /**
     * aBusParaEdEntExt
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward aBusParaEdEntExt(ActionMapping actionMapping,
                                           HttpServletRequest servletRequest,
                                           HttpSession httpSession) {
        try {
            ClienteLocal clienteLocal = LocalizadorServicios.getInstance().
                                        getClienteLocal();
            String nomEnt = servletRequest.getParameter("nomEntExt");

            servletRequest.setAttribute("entidades",
                                        clienteLocal.buscarEntExt(nomEnt));
            return actionMapping.findForward("busParaEdEntExt_fw");

        } catch (Exception ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la búsqueda de entidades externas");
            return actionMapping.findForward("mensaje_fw");
        }

    }

    /**
     * cambiarEstadoEntExt
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward cambiarEstadoEntExt(ActionMapping actionMapping,
                                              HttpServletRequest servletRequest,
                                              HttpSession httpSession) {
        try {
            Integer idEnt = Integer.valueOf(servletRequest.getParameter(
                    "idEntExt"));
            ClienteLocal clienteLocal = LocalizadorServicios.getInstance().
                                        getClienteLocal();

            if (clienteLocal.cambiarEstadoEntExt(idEnt)) {
                servletRequest.setAttribute("mensaje",
                                            "El estado de la Entidad fue actualizado");
                return actionMapping.findForward("mensaje_fw");

            }

            servletRequest.setAttribute("mensaje",
                                        "No se pudo Cambiar el estado de la Entidad");
            return actionMapping.findForward("mensaje_fw");

        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo Cambiar el estado de la Entidad");
            return actionMapping.findForward("mensaje_fw");

        }
    }

    /**
     * cambiarEstadoCliente
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward cambiarEstadoCliente(ActionMapping actionMapping,
                                               HttpServletRequest
                                               servletRequest,
                                               HttpSession httpSession) {
        try {
            Integer idCli = Integer.valueOf(servletRequest.getParameter("idCli"));
            ClienteLocal clienteLocal = LocalizadorServicios.getInstance().
                                        getClienteLocal();

            if (clienteLocal.cambiarEstadoCli(idCli)) {
                servletRequest.setAttribute("mensaje",
                                            "El estado del Cliente fue actualizado");
                return actionMapping.findForward("mensaje_fw");

            }

            servletRequest.setAttribute("mensaje",
                                        "No se pudo Cambiar el estado del Cliente");
            return actionMapping.findForward("mensaje_fw");

        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo Cambiar el estado del Cliente");
            return actionMapping.findForward("mensaje_fw");

        }

    }

    /**
     * actualizarDatosCliente
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward actualizarDatosCliente(ActionMapping actionMapping,
                                                 HttpServletRequest
                                                 servletRequest,
                                                 HttpSession httpSession) {

        try {
            Integer idCli = Integer.valueOf(servletRequest.getParameter(
                    "id_cli"));
            String nomCli = (String) servletRequest.getParameter("nomCli");
            String apeCli = (String) servletRequest.getParameter("apeCli");
            String telCli = servletRequest.getParameter("telCli");
            String celCli = servletRequest.getParameter("celCli");
            String emailCli = (String) servletRequest.getParameter("emailCli");
            Integer idEntExt = null;
            if (!servletRequest.getParameter("idEntExt").equals("")) {
                idEntExt = Integer.valueOf(servletRequest.getParameter(
                        "idEntExt"));
            }
            String comentarioCli = servletRequest.getParameter("comentarioCli");
            String cargoCli = servletRequest.getParameter("cargoCli");
            ClienteLocal clienteLocal = null;

            ClienteVO cli = new ClienteVO();
            cli.setIdCli(idCli);
            cli.setNomCli(nomCli);
            cli.setApeCli(apeCli);
            cli.setFonoCli(telCli);
            cli.setCelCli(celCli);
            cli.setEmailCli(emailCli);
            if (!servletRequest.getParameter("idEntExt").equals("")) {
                cli.setIdEnt(idEntExt);
            }

            cli.setComentCli(comentarioCli);
            cli.setCargo(cargoCli);

            clienteLocal = LocalizadorServicios.getInstance().
                           getClienteLocal();

            clienteLocal.updateDatosCli(cli);

            cli = clienteLocal.getDatosCli(idCli);

            servletRequest.setAttribute("datoscli", cli);
            servletRequest.setAttribute("mensaje",
                                        "Los Datos Fueron Actualizados");
            return actionMapping.findForward("detalleCli_fw");

        } catch (Exception ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo Actualizar el Cliente");
            return actionMapping.findForward("mensaje_fw");

        }

    }

    /**
     * aeditarDatosCliente
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward aeditarDatosCliente(ActionMapping actionMapping,
                                              HttpServletRequest servletRequest,
                                              HttpSession httpSession) {
        try {
            Integer idCli = Integer.valueOf(servletRequest.getParameter(
                    "idCli"));
            ClienteLocal clienteLocal = null;
            ClienteVO datoscli = null;

            clienteLocal = LocalizadorServicios.getInstance().
                           getClienteLocal();

            datoscli = clienteLocal.getDatosCli(idCli);
            servletRequest.setAttribute("datoscli", datoscli);
            return actionMapping.findForward("editarDatosCli_fw");
        } catch (Exception ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo Cargar la Página de Edición del Cliente");
            return actionMapping.findForward("mensaje_fw");

        }

    }


    /**
     * buscarParaEditarCli
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward buscarParaEditarCli(ActionMapping actionMapping,
                                              HttpServletRequest servletRequest,
                                              HttpSession httpSession) {
        ClienteLocal clienteLocal = null;
        try {
            clienteLocal = LocalizadorServicios.getInstance().
                           getClienteLocal();

            if (servletRequest.getParameter("PorPalabra") != null) {
                String palClave = servletRequest.getParameter("palClave");
                List listaCliEnt = clienteLocal.getCliEntporPal(palClave, false);
                servletRequest.setAttribute("listaCliEnt", listaCliEnt);
            }

            if (servletRequest.getParameter("Particulares") != null) {
                List listaCliEnt = clienteLocal.getCliPartic(false);
                servletRequest.setAttribute("listaCliEnt", listaCliEnt);

            }

            return actionMapping.findForward("buscarParaEditarCli_fw");
        } catch (LocalizadorServiciosException ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo buscar clientes");
            return actionMapping.findForward("mensaje_fw");

        }

    }


    /**
     * buscarEntExt
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward buscarEntExt(ActionMapping actionMapping,
                                       HttpServletRequest servletRequest) {
        try {
            ClienteLocal clienteLocal = LocalizadorServicios.getInstance().
                                        getClienteLocal();
            String nomEnt = servletRequest.getParameter("nomEntExt");

            servletRequest.setAttribute("entidades",
                                        clienteLocal.buscarEntExt(nomEnt));
            return actionMapping.findForward("buscarEntExt_fw");

        } catch (Exception ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "el cliente fue ingresado exitosamente");
            return actionMapping.findForward("mensaje_fw");
        }

    }

    /**
     * actualizarEntidadExterna
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward actualizarEntidadExterna(ActionMapping actionMapping,
            HttpServletRequest
            servletRequest,
            HttpSession httpSession) {

        try {
            ClienteLocal clienteLocal = LocalizadorServicios.getInstance().
                                        getClienteLocal();
            Integer idEnt = Integer.valueOf(servletRequest.getParameter("idEnt"));
            String fono = servletRequest.getParameter("fonoEnt");
            String dir = servletRequest.getParameter("dirEnt");
            String web = servletRequest.getParameter("webEnt");

            EntExtVO ent = new EntExtVO();

            ent.setDirEnt(dir);
            ent.setIdEnt(idEnt);
            ent.setFonoEnt(fono);
            ent.setWeb(web);

            if (clienteLocal.actualizarEntExt(ent)) {
                servletRequest.setAttribute("mensaje",
                                            "Los datos fueron actualizados");
            } else {
                servletRequest.setAttribute("mensaje",
                                            "Los datos no pudieron ser actualizados");
            }

            return actionMapping.findForward("mensaje_fw");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "Los datos no pudieron ser actualizados");
            return actionMapping.findForward("mensaje_fw");
        }
    }

    /**
     * aeditarEntidadExterna
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward aeditarEntidadExterna(ActionMapping actionMapping,
                                                HttpServletRequest
                                                servletRequest,
                                                HttpSession httpSession) {
        ClienteLocal clienteLocal = null;
        try {

            clienteLocal = LocalizadorServicios.getInstance().
                           getClienteLocal();
            Integer idEnt = Integer.valueOf(servletRequest.getParameter(
                    "id_ent"));

            servletRequest.setAttribute("entidad",
                                        clienteLocal.getDatosEnt(idEnt));
            return actionMapping.findForward("editarEntExt_fw");

        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "no se pudo cargar pagina de Lista de Entidades Externas");
            return actionMapping.findForward("mensaje_fw");
        }
    }

    /**
     * aeditarEntidadesExternas
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward aeditarEntidadesExternas(ActionMapping actionMapping,
            HttpServletRequest
            servletRequest,
            HttpSession httpSession) {
        ClienteLocal clienteLocal = null;
        try {

            clienteLocal = LocalizadorServicios.getInstance().
                           getClienteLocal();

            servletRequest.setAttribute("listaEnt",
                                        clienteLocal.getEntidadesExternas());
            return actionMapping.findForward("listaEntExt_fw");

        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "no se pudo cargar pagina de Lista de Entidades Externas");
            return actionMapping.findForward("mensaje_fw");
        }
    }

    /**
     * registrarInstit
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward registrarEntExt(ActionMapping actionMapping,
                                          HttpServletRequest servletRequest) {

        try {
            String nom_instit = servletRequest.getParameter("nom_instit");
            String dir_instit = servletRequest.getParameter("dir_instit");

            String fono = servletRequest.getParameter("fono_instit");

            String web = servletRequest.getParameter("web");

            EntExtVO ent = new EntExtVO();
            ent.setNomEnt(nom_instit);
            ent.setDirEnt(dir_instit);
            ent.setFonoEnt(fono);
            ent.setWeb(web);

            ClienteLocal clienteLocal = null;

            clienteLocal = LocalizadorServicios.getInstance().
                           getClienteLocal();

            boolean ingresada = clienteLocal.registrarEntExt(ent);

            if (ingresada == true) {
                servletRequest.setAttribute("mensaje",
                                            "La institución fue ingresada exitosamente");
                return actionMapping.findForward("mensaje_fw");
            } else {
                servletRequest.setAttribute("mensaje",
                                            "no se pudo ingresar la entidad externa");
            }
            return actionMapping.findForward("mensaje_fw");

        } catch (LocalizadorServiciosException ex) {
            System.out.println("no se pudo llamar a EJB");
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "no se pudo ingresar la entidad externa");

            return actionMapping.findForward("mensaje_fw");

        } catch (Exception ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "no se pudo ingresar la entidad externa");

            return actionMapping.findForward("mensaje_fw");

        }
    }

    /**
     * aregEntExt
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward aregEntExt(ActionMapping actionMapping,
                                     HttpServletRequest servletRequest) {

        return actionMapping.findForward("cargarRegEntExt_fw");
    }

    /**
     * abuscarCliente
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward abuscarCliente(ActionMapping actionMapping,
                                         HttpServletRequest servletRequest,
                                         HttpSession httpSession) {

        try {

            return actionMapping.findForward("cargarBuscarCli_fw");
        } catch (Exception ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la pagina de búsqueda de clientes");
            return actionMapping.findForward("mensaje_fw");
        }

    }

    /**
     * buscarCliente
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward buscarCliente(ActionMapping actionMapping,
                                        HttpServletRequest servletRequest,
                                        HttpSession httpSession) {
        ClienteLocal clienteLocal = null;
        try {
            clienteLocal = LocalizadorServicios.getInstance().
                           getClienteLocal();

            if (servletRequest.getParameter("PorPalabra") != null) {
                String palClave = servletRequest.getParameter("palClave");
                List listaCliEnt = clienteLocal.getCliEntporPal(palClave, true);
                servletRequest.setAttribute("listaCliEnt", listaCliEnt);
            }

            if (servletRequest.getParameter("Particulares") != null) {
                List listaCliEnt = clienteLocal.getCliPartic(true);
                servletRequest.setAttribute("listaCliEnt", listaCliEnt);

            }

            return actionMapping.findForward("cargarBuscarCli_fw");
        } catch (LocalizadorServiciosException ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo buscar clientes");
            return actionMapping.findForward("mensaje_fw");

        }

    }

    /**
     * ingresarCliente
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward ingresarCliente(ActionMapping actionMapping,
                                          HttpServletRequest servletRequest,
                                          HttpSession httpSession) {

        try {
            ClienteVO ncli = new ClienteVO();

            String nom_cli = servletRequest.getParameter("nom_cli");
            String ape_cli = servletRequest.getParameter("ape_cli");
            String fono_cli = servletRequest.getParameter("fono_cli");
            String email_cli = servletRequest.getParameter("email_cli");
            String cel_cli = servletRequest.getParameter("cel_cli");
            if (!servletRequest.getParameter("idEntExt").equals("")) {
                Integer id_instit = Integer.valueOf(servletRequest.getParameter(
                        "idEntExt"));
                ncli.setIdEnt(id_instit);
            }
            String comentario = servletRequest.getParameter("comentario");
            String cargo = servletRequest.getParameter("cargo");
            ClienteLocal clienteLocal = null;

            clienteLocal = LocalizadorServicios.getInstance().
                           getClienteLocal();

            ncli.setNomCli(nom_cli);
            ncli.setApeCli(ape_cli);
            ncli.setFonoCli(fono_cli);
            ncli.setEmailCli(email_cli);
            ncli.setCelCli(cel_cli);

            ncli.setComentCli(comentario);
            ncli.setCargo(cargo);
            boolean ingresado = clienteLocal.agregarCli(ncli);

            if (ingresado == true) {
                servletRequest.setAttribute("mensaje",
                                            "el cliente fue ingresado exitosamente");
                return actionMapping.findForward("mensaje_fw");

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo ingresar el cliente");
            return actionMapping.findForward("mensaje_fw");
        }

        servletRequest.setAttribute("mensaje",
                                    "No se pudo ingresar el cliente");
        return actionMapping.findForward("mensaje_fw");

    }

    /**
     * aregistrarCliente
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward aregistrarCliente(ActionMapping actionMapping,
                                            HttpServletRequest
                                            servletRequest,
                                            HttpSession httpSession) {

        List listatiposcli = null;
        List listainstit = null;
        ClienteLocal clienteLocal = null;

        try {
            clienteLocal = LocalizadorServicios.getInstance().
                           getClienteLocal();

            listainstit = clienteLocal.getEntidadesExternas();

            servletRequest.setAttribute("listainstit", listainstit);

            return actionMapping.findForward("cargarRegCli_fw");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la página de Registro de Clientes");
            return actionMapping.findForward("mensaje_fw");

        }
    }

    /**
     * exportarAgenda
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward exportarAgenda(ActionMapping actionMapping,
                                         HttpServletRequest servletRequest,
                                         HttpSession httpSession) {
        try {
            ClienteLocal clienteLocal = LocalizadorServicios.getInstance().
                                        getClienteLocal();
            servletRequest.setAttribute("clientes", clienteLocal.getAgendaCli());
            return actionMapping.findForward("exportarAgenda_fw");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la Exportar la agenda");
            return actionMapping.findForward("mensaje_fw");

        }
    }

    /**
     * busCli_pop
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param httpSession HttpSession
     * @return ActionForward
     */
    private ActionForward busCli_pop(ActionMapping actionMapping,
                                     HttpServletRequest servletRequest,
                                     HttpSession httpSession) {
        try {
            ClienteLocal clienteLocal = null;

            clienteLocal = LocalizadorServicios.getInstance().
                           getClienteLocal();

            List listainstitu = clienteLocal.getEntidadesExternas();

            if (servletRequest.getParameter("PorPalabra") != null) {
                String palClave = servletRequest.getParameter("palClave");
                List listaCliEnt = clienteLocal.getCliEntporPal(palClave, true);
                servletRequest.setAttribute("listaCliEnt", listaCliEnt);
            }
            if (servletRequest.getParameter("Particulares") != null) {
                List listaCliEnt = clienteLocal.getCliPartic(true);
                servletRequest.setAttribute("listaCliEnt", listaCliEnt);

            }

            return actionMapping.findForward("BusCli_pop_fw");
        } catch (LocalizadorServiciosException ex) {
            servletRequest.setAttribute("mensaje",
                                        "No se pudo Buscar Clientes");
            return actionMapping.findForward("mensaje_fw");
        } catch (Exception e) {
            servletRequest.setAttribute("mensaje",
                                        "No se pudo Buscar Clientes");
            return actionMapping.findForward("mensaje_fw");
        }

    }

}

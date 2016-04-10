package proyecto_uoct.reservas.controller;

import java.text.*;
import java.util.*;

import javax.servlet.http.*;

import org.apache.struts.action.*;
import proyecto_uoct.common.*;
import proyecto_uoct.common.VO.*;
import proyecto_uoct.common.util.*;
import proyecto_uoct.reservas.VO.*;
import proyecto_uoct.reservas.model.*;
import proyecto_uoct.reservas.util.*;

public class RecursosAction extends Action {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

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

        if (accion == null) {
            return listarRecursos(actionMapping, servletRequest);
        }

        if ("VER_AGENDA".equals(accion)) {
            return verAgenda(actionMapping, servletRequest);
        }

        if ("VER_SALA".equals(accion)) {
            return verAgendaSala(actionMapping, servletRequest);
        }

        if ("VER_SALAB".equals(accion)) {
            return verAgendaSalaB (actionMapping, servletRequest);
        }

        if ("VER_RESERVAS".equals(accion)) {
            return verReservas(actionMapping, servletRequest);
        }

        if ("BUSCAR_RESERVAS".equals(accion)) {
            return buscarReservas(actionMapping, servletRequest);
        }

        if ("ELIMINAR".equals(accion)) {
            return eliminarReserva(actionMapping, servletRequest);
        }

        if ("ELIMINARSALA".equals(accion)) {
            return eliminarReserva(actionMapping, servletRequest);
        }

        if ("RESERVAR".equals(accion)) {
            return reservar(actionMapping, servletRequest);
        }

        if ("RESERVARSALA".equals(accion)) {
            return reservar(actionMapping, servletRequest);
        }

        if ("ADMIN_RECURSOS".equals(accion)) {
            return adminRecursos(actionMapping, servletRequest);
        }

        if ("REGISTRAR_RECURSO".equals(accion)) {
            return registrarRecurso(actionMapping, servletRequest);
        }

        if ("EDITAR_RECURSO".equals(accion)) {
            return editarRecurso(actionMapping, servletRequest);
        }

        if ("ACTUALIZAR_RECURSO".equals(accion)) {
            return actualizarRecurso(actionMapping, servletRequest);
        }

        if ("GENERAR_REPORTE".equals(accion)) {
            return generarReporte(actionMapping, servletRequest);
        }

        return actionMapping.getInputForward();
    }


    /**
     * verAgendaSala
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward verAgendaSala(ActionMapping actionMapping,
                                        HttpServletRequest servletRequest) {
        try {

            RecursosLocal recursosLocal = LocalizadorServicios.getInstance().
                                          getRecursosLocal();

            HttpSession httpsession = servletRequest.getSession();
            DatosSesVO datoses = (DatosSesVO) httpsession.getAttribute(
                    "Ses_Usu");
            Integer a = datoses.getId();
            Long idusuario = new Long(a.longValue());
            Long sala = new Long ("1");
            //Parámetros y valores
            String _fecha = servletRequest.getParameter("fechaBusqueda");

            String accion = servletRequest.getParameter("accion");

            if (accion.equals("VER_SALA")){
            sala = new Long("61") ; // Esta lìnea obliga a que la Sala de reuniones esté identificada en la BD con el ID=61
            }

            if (accion.equals("VER_SALAB")){
            sala = new Long("101") ; // Esta lìnea obliga a que la Sala de reuniones 2 esté identificada en la BD con el ID=101
            }

            if (accion.equals("ELIMINARSALA")){
            sala = new Long(servletRequest.getParameter("idRecurso")) ;
            }
            if (accion.equals("RESERVARSALA")){
            sala = new Long(servletRequest.getParameter("idRecurso")) ;
            }





            if (_fecha == "" || _fecha == null) {

                Date hoy = new Date();
                _fecha = sdf.format(hoy);
            }

            String fecha_lun = null;
            String fecha_vie = null;
            //Reservas a mostrar

            Fecha fecha = Fecha.getInstance();
            fecha_lun = sdf.format(fecha.getDiadeSemana(sdf.parse(_fecha), 2));
            fecha_vie = sdf.format(fecha.getDiadeSemana(sdf.parse(_fecha), 6));

            FiltroRecursos filtro = new FiltroRecursos();
            filtro.setFechaDesde(Parse.newDate(fecha_lun));
            filtro.setFechaHasta(Parse.newDate(fecha_vie));

            //List reservas = recursosLocal.getReservas(filtro);

            filtro.setIdRecurso(sala);

            List reservas = new LinkedList();

            reservas.add(recursosLocal.getReservas(filtro));

            List recursos = new LinkedList();
            recursos.add(recursosLocal.getRecurso((sala)));

            servletRequest.setAttribute("Id_Usu", idusuario);
            servletRequest.setAttribute("RESERVAS", reservas);
            servletRequest.setAttribute("recursos", recursos);
            servletRequest.setAttribute("fechaBusqueda", _fecha);

        } catch (LocalizadorServiciosException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return actionMapping.findForward("verSala");

    }


    /**
     * verAgendaSala2
     *
     * Se usa para buscar las reservas de la sala de reuniones con televisores (sala2). Ojo que tiene acoplamiento con la base de datos, ya que se cita la sala que es el recurso 101 en la base de datos.
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward verAgendaSalaB (ActionMapping actionMapping,
                                        HttpServletRequest servletRequest) {
        try {

            RecursosLocal recursosLocal = LocalizadorServicios.getInstance().
                                          getRecursosLocal();

            HttpSession httpsession = servletRequest.getSession();
            DatosSesVO datoses = (DatosSesVO) httpsession.getAttribute(
                    "Ses_Usu");
            Integer a = datoses.getId();
            Long idusuario = new Long(a.longValue());

            //Parámetros y valores
            String _fecha = servletRequest.getParameter("fechaBusqueda");

            Long sala = new Long("101"); // Esta lìnea obliga a que la Sala de reuniones esté identificada en la BD con el ID=101

            if (_fecha == "" || _fecha == null) {

                Date hoy = new Date();
                _fecha = sdf.format(hoy);
            }

            String fecha_lun = null;
            String fecha_vie = null;

            //Reservas a mostrar

            Fecha fecha = Fecha.getInstance();
            fecha_lun = sdf.format(fecha.getDiadeSemana(sdf.parse(_fecha), 2));
            fecha_vie = sdf.format(fecha.getDiadeSemana(sdf.parse(_fecha), 6));

            FiltroRecursos filtro = new FiltroRecursos();
            filtro.setFechaDesde(Parse.newDate(fecha_lun));
            filtro.setFechaHasta(Parse.newDate(fecha_vie));

            //List reservas = recursosLocal.getReservas(filtro);

            filtro.setIdRecurso(sala);

            List reservas = new LinkedList();

            reservas.add(recursosLocal.getReservas(filtro));

            List recursos = new LinkedList();
            recursos.add(recursosLocal.getRecurso((long) sala));

            servletRequest.setAttribute("Id_Usu", idusuario);
            servletRequest.setAttribute("RESERVAS", reservas);
            servletRequest.setAttribute("recursos", recursos);
            servletRequest.setAttribute("fechaBusqueda", _fecha);

        } catch (LocalizadorServiciosException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return actionMapping.findForward("verSala2");

    }




    /**
     * verReservas
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward verReservas(ActionMapping actionMapping,
                                      HttpServletRequest servletRequest) {
        try {

            RecursosLocal recursosLocal = LocalizadorServicios.getInstance().
                                          getRecursosLocal();

            HttpSession httpsession = servletRequest.getSession();
            DatosSesVO datoses = (DatosSesVO) httpsession.getAttribute(
                    "Ses_Usu");
            Integer a = datoses.getId();
            Long idusuario = new Long(a.longValue());

            //Parámetros y valores
            String _fecha = servletRequest.getParameter("fechaBusqueda");

            List allRecursos = recursosLocal.getAllRecursos();

            if (_fecha == null) {

                Date hoy = new Date();
                _fecha = sdf.format(hoy);
            }

            //Reservas a mostrar
            FiltroRecursos filtro = new FiltroRecursos();
            filtro.setFechaDesde(Parse.newDate(_fecha));
            filtro.setFechaHasta(Parse.newDate(_fecha));

            //List reservas = recursosLocal.getReservas(filtro);
            List reservas = new LinkedList();

            Iterator i = allRecursos.iterator();
            while (i.hasNext()) {
                RecursoVO rec = (RecursoVO) i.next();
                filtro.setIdRecurso(rec.getIdRecurso());
                reservas.add(recursosLocal.getReservas(filtro));
            }
            servletRequest.setAttribute("Id_Usu", idusuario);
            servletRequest.setAttribute("RESERVAS", reservas);
            servletRequest.setAttribute("recursos", allRecursos);
            servletRequest.setAttribute("fechaBusqueda", _fecha);

        } catch (LocalizadorServiciosException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return actionMapping.findForward("verAgenda2");

    }

    /**
     * generarReporte
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @param servletRequest1 HttpServletRequest
     * @return ActionForward
     */
    private ActionForward generarReporte(ActionMapping actionMapping,
                                         HttpServletRequest servletRequest) {
        try {

            RecursosLocal recursosLocal = LocalizadorServicios.getInstance().
                                          getRecursosLocal();

            //Parámetros y valores
            String _fechaDesde = servletRequest.getParameter("fechaDesde");
            String _fechaHasta = servletRequest.getParameter("fechaHasta");
            List allRecursos = recursosLocal.getAllRecursos();
            if (_fechaDesde != null && _fechaHasta != null) {

                //Recurso a mostrar




                //Reservas a mostrar
                FiltroRecursos filtro = new FiltroRecursos();
                filtro.setFechaDesde(Parse.newDate(_fechaDesde));
                filtro.setFechaHasta(Parse.newDate(_fechaHasta));

                //List reservas = recursosLocal.getReservas(filtro);
                List reservas = new LinkedList();

                Iterator i = allRecursos.iterator();
                while (i.hasNext()) {
                    RecursoVO rec = (RecursoVO) i.next();
                    filtro.setIdRecurso(rec.getIdRecurso());
                    reservas.add(recursosLocal.getReservas(filtro));
                }
                servletRequest.setAttribute("RESERVAS", reservas);
            }
            servletRequest.setAttribute("recursos", allRecursos);

        } catch (LocalizadorServiciosException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return actionMapping.findForward("reporte");

    }

    /**
     * actualizarRecurso
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward actualizarRecurso(ActionMapping actionMapping,
                                            HttpServletRequest servletRequest) {
        try {
            RecursosLocal recursosLocal = LocalizadorServicios.
                                          getInstance().getRecursosLocal();
            RecursoVO recurso = new RecursoVO();
            recurso.setIdRecurso(new Long(servletRequest.getParameter("idRec")));
            recurso.setNombre(servletRequest.getParameter("nombre"));
            recurso.setDescripcion(servletRequest.getParameter("desc"));
            recurso.setIsActivo(new Integer(servletRequest.getParameter(
                    "idEstado")));

            if (recursosLocal.actualizarRecurso(recurso)) {
                servletRequest.setAttribute("mensaje",
                                            "El recurso fue actualizado exitosamente");
            } else {
                servletRequest.setAttribute("mensaje",
                                            "El recurso NO pudo ser actualizado");
            }

        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "El recurso NO pudo ser actualizado");
            return actionMapping.findForward("mensaje_fw");
        }
        return actionMapping.findForward("mensaje_fw");
    }

    /**
     * editarRecurso
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward editarRecurso(ActionMapping actionMapping,
                                        HttpServletRequest servletRequest) {
        try {
            RecursosLocal recursosLocal = LocalizadorServicios.
                                          getInstance().getRecursosLocal();
            Long idRec = new Long(servletRequest.getParameter("idRecurso"));
            servletRequest.setAttribute("RECURSO",
                                        recursosLocal.getRecurso(idRec));
            return actionMapping.findForward("editarRecurso");
        } catch (LocalizadorServiciosException e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la pag. de edicion del recurso");
            return listarRecursos(actionMapping, servletRequest);
        } catch (Exception ex) {
            ex.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la pag. de edicion del recurso");
            return listarRecursos(actionMapping, servletRequest);
        }
    }

    /**
     * registrarRecurso
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward registrarRecurso(ActionMapping actionMapping,
                                           HttpServletRequest servletRequest) {
        try {
            RecursosLocal recursosLocal = LocalizadorServicios.getInstance().
                                          getRecursosLocal();
            String nombre = servletRequest.getParameter("nombre");
            String desc = servletRequest.getParameter("desc");
            if (recursosLocal.insertaRecurso(nombre, desc)) {
                servletRequest.setAttribute("mensaje",
                                            "El recurso fue registrado");
            }
            return adminRecursos(actionMapping, servletRequest);
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la pag. de administración de recursos");
            return actionMapping.findForward("mensaje_fw");
        }

    }

    /**
     * adminRecursos
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward adminRecursos(ActionMapping actionMapping,
                                        HttpServletRequest servletRequest) {
        try {
            RecursosLocal recursosLocal = LocalizadorServicios.getInstance().
                                          getRecursosLocal();
            servletRequest.setAttribute("RECURSOS",
                                        recursosLocal.getAllRecursos());
            return actionMapping.findForward("adminRecursos");
        } catch (Exception e) {
            e.printStackTrace();
            servletRequest.setAttribute("mensaje",
                                        "No se pudo cargar la pag. de administración de recursos");
            return actionMapping.findForward("mensaje_fw");
        }
    }

    /**
     * Reservar
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward reservar(ActionMapping actionMapping,
                                   HttpServletRequest servletRequest) {
        String accion = servletRequest.getParameter("accion");

        try {
            RecursosLocal recursosLocal = LocalizadorServicios.getInstance().
                                          getRecursosLocal();
            HttpSession httpsession = servletRequest.getSession();
            DatosSesVO datoses = (DatosSesVO) httpsession.getAttribute(
                    "Ses_Usu");
            Integer a = datoses.getId();
            Long idusuario = new Long(a.longValue());
            servletRequest.setAttribute("Id_Usu", idusuario);

            //Parámetros y valores
            String _fecha = servletRequest.getParameter("fecha");
            String _horaInicio = servletRequest.getParameter("horaInicio");
            String _minutosInicio = servletRequest.getParameter("minutosInicio");
            String _horaFin = servletRequest.getParameter("horaFin");
            String _minutosFin = servletRequest.getParameter("minutosFin");
            String _motivo = servletRequest.getParameter("motivo");

            //Obtener los objetos para renderizar
            //Recurso a mostrar

            Long idRecurso = new Long(servletRequest.getParameter("idRecurso"));

            //Insertar la reserva
            ReservaVO reserva = new ReservaVO();
            reserva.setFechaReserva(Parse.newDate(_fecha));
            reserva.setHoraDeInicio(Parse.newTime(_horaInicio + ":" +
                                                  _minutosInicio));
            reserva.setHoraDeFin(Parse.newTime(_horaFin + ":" + _minutosFin));
            reserva.setIdRecurso(idRecurso);

            reserva.setIdUsuario(idusuario);
            reserva.setMotivo(_motivo);
            recursosLocal.insertarReserva(reserva);

        } catch (LocalizadorServiciosException e) {
            e.printStackTrace();
        } catch (RecursosException e) {
            e.printStackTrace();
            return Reporte.mensaje(e.getMessage(), actionMapping,
                                   servletRequest, true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if ("RESERVARSALA".equals(accion)) {
            return verAgendaSala(actionMapping, servletRequest);
        } else {
            return verReservas(actionMapping, servletRequest);
        }

    }

    /**
     * eliminarReserva
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward eliminarReserva(ActionMapping actionMapping,
                                          HttpServletRequest servletRequest) {

        try {
            RecursosLocal recursosLocal = LocalizadorServicios.getInstance().
                                          getRecursosLocal();

            // obtiene el identificador del usuario desde la sesion.
            HttpSession httpsession = servletRequest.getSession();
            DatosSesVO datoses = (DatosSesVO) httpsession.getAttribute(
                    "Ses_Usu");
            Integer a = datoses.getId();
            Long idusuario = new Long(a.longValue());
            servletRequest.setAttribute("Id_Usu", idusuario);

            String _fecha = servletRequest.getParameter("fecha");

            //Parámetros y valores
            String _idReserva = servletRequest.getParameter("idReserva");

            //Eliminar la reserva
            recursosLocal.eliminarReserva(new Long(_idReserva));

            Long idrecurso = new Long (servletRequest.getParameter("idRecurso"));

            if("ELIMINARSALA".equals(servletRequest.getParameter("accion")) && idrecurso.equals(new Long ("61"))){
               verAgendaSala(actionMapping, servletRequest);
               return actionMapping.findForward("verSala");
            }

            if("ELIMINARSALA".equals(servletRequest.getParameter("accion")) && idrecurso.equals(new Long ("101"))){
               verAgendaSalaB(actionMapping, servletRequest);
               return actionMapping.findForward("verSala2");
            }


            //Reservas a mostrar
            FiltroRecursos filtro = new FiltroRecursos();
            filtro.setFechaDesde(Parse.newDate(_fecha));
            filtro.setFechaHasta(Parse.newDate(_fecha));

            //List reservas = recursosLocal.getReservas(filtro);
            List reservas = new LinkedList();
            List allRecursos = recursosLocal.getAllRecursos();
            Iterator i = allRecursos.iterator();
            while (i.hasNext()) {
                RecursoVO rec = (RecursoVO) i.next();
                filtro.setIdRecurso(rec.getIdRecurso());
                reservas.add(recursosLocal.getReservas(filtro));
            }

            servletRequest.setAttribute("fechaBusqueda", _fecha);
            servletRequest.setAttribute("Id_Usu", idusuario);
            servletRequest.setAttribute("recursos", allRecursos);
            servletRequest.setAttribute("RESERVAS", reservas);

        } catch (LocalizadorServiciosException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return actionMapping.findForward("verAgenda2");
    }

    /**
     * listarReservas
     *
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward buscarReservas(ActionMapping actionMapping,
                                         HttpServletRequest servletRequest) {

        try {

            RecursosLocal recursosLocal = LocalizadorServicios.getInstance().
                                          getRecursosLocal();

            //Parámetros y valores
            String _fechaDesde = servletRequest.getParameter("fechaDesde");
            String _fechaHasta = servletRequest.getParameter("fechaHasta");

            //Recurso a mostrar
            RecursoVO recurso = getRecurso(servletRequest);

            //Reservas a mostrar
            FiltroRecursos filtro = new FiltroRecursos();
            filtro.setIdRecurso(recurso.getIdRecurso());
            filtro.setFechaDesde(Parse.newDate(_fechaDesde));
            filtro.setFechaHasta(Parse.newDate(_fechaHasta));

            List reservas = recursosLocal.getReservas(filtro);

            HttpSession httpsession = servletRequest.getSession();
            DatosSesVO datoses = (DatosSesVO) httpsession.getAttribute(
                    "Ses_Usu");
            Integer a = datoses.getId();
            Long idusuario = new Long(a.longValue());
            servletRequest.setAttribute("Id_Usu", idusuario);

            servletRequest.setAttribute("RECURSO", recurso);
            servletRequest.setAttribute("RESERVAS", reservas);

        } catch (LocalizadorServiciosException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return actionMapping.findForward("verAgenda");
    }

    /**
     * verAgenda
     * @param actionMapping ActionMapping
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward verAgenda(ActionMapping actionMapping,
                                    HttpServletRequest servletRequest) {

        HttpSession httpsession = servletRequest.getSession();
        DatosSesVO datoses = (DatosSesVO) httpsession.getAttribute(
                "Ses_Usu");
        Integer a = datoses.getId();
        Long idusuario = new Long(a.longValue());

        try {

            RecursosLocal recursosLocal = LocalizadorServicios.getInstance().
                                          getRecursosLocal();

            String _idRecurso = servletRequest.getParameter("idRecurso");
            Long idRecurso = new Long(_idRecurso);

            RecursoVO recurso = recursosLocal.getRecurso(idRecurso);
            List reservas = recurso.getReservas();
            servletRequest.setAttribute("Id_Usu", idusuario);
            servletRequest.setAttribute("RECURSO", recurso);
            servletRequest.setAttribute("RESERVAS", reservas);

        } catch (LocalizadorServiciosException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return actionMapping.findForward("verAgenda");
    }


    /**
     * listarRecursos
     * @param actionForm ActionForm
     * @param servletRequest HttpServletRequest
     * @return ActionForward
     */
    private ActionForward listarRecursos(ActionMapping actionMapping,
                                         HttpServletRequest servletRequest) {

        try {
            RecursosLocal recursosLocal = LocalizadorServicios.
                                          getInstance().getRecursosLocal();

            servletRequest.setAttribute("RECURSOS", recursosLocal.getRecursos());

        } catch (LocalizadorServiciosException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return actionMapping.findForward("listarRecursos");
    }

    /**
     * Recupera el VO desde el cliente
     * @param servletRequest HttpServletRequest
     * @return RecursoVO
     */
    private RecursoVO getRecurso(HttpServletRequest servletRequest) {
        String _idRecurso = servletRequest.getParameter("idRecurso");
        String _nombreRecurso = servletRequest.getParameter("nombreRecurso");

        RecursoVO recurso = new RecursoVO();
        recurso.setNombre(_nombreRecurso);
        recurso.setIdRecurso(new Long(_idRecurso));
        return recurso;
    }
}

package proyecto_uoct.EIV.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import proyecto_uoct.EIV.VO.BuscadorEIVVO;
import proyecto_uoct.EIV.VO.EIVVO;
import proyecto_uoct.EIV.VO.EventoVO;
import proyecto_uoct.EIV.VO.FlujoVO;
import proyecto_uoct.EIV.model.EIVLocal;
import proyecto_uoct.common.LocalizadorServicios;
import proyecto_uoct.common.LocalizadorServiciosException;
import proyecto_uoct.common.VO.DatosSesVO;
import proyecto_uoct.documentacion.VO.BusDocsVO;
import proyecto_uoct.documentacion.model.ClienteLocal;
import proyecto_uoct.documentacion.model.DocumentoLocal;
import proyecto_uoct.usuario.VO.UsuarioVO;
import proyecto_uoct.usuario.model.UsuarioLocal;

public class eivAction extends Action {
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest servletRequest,
	    HttpServletResponse servletResponse) {

	String hacia = servletRequest.getParameter("hacia");
	HttpSession httpsession = servletRequest.getSession();
	DatosSesVO datoses;
	try {

	    datoses = (DatosSesVO) httpsession.getAttribute("Ses_Usu");
	    datoses.getId();
	} catch (Exception e) {
	    servletRequest.setAttribute("mensaje", "La sesión ha caducado");
	    return actionMapping.findForward("mensaje_fw");
	}
	if (hacia != null) {

	    // -------- TIPOS DE EIV ----------
	    if (hacia.equals("cargarTiposEIV")) {
		return cargaTiposEIV(actionMapping, servletRequest, httpsession);
	    }
	    if (hacia.equals("eliminarTipoEIV")) {
		return eliminarTipoEIV(actionMapping, servletRequest, httpsession);
	    }
	    if (hacia.equals("agregarTipoEIV")) {
		return agregarTipoEIV(actionMapping, servletRequest, httpsession);
	    }

	    // ----------------------------------
	    if (hacia.equals("cargarRegistrarEIV")) {
		return cargarRegistrarEIV(actionMapping, servletRequest, httpsession);
	    }

	    if (hacia.equals("cargarBusDoc")) {
		return cargarBusDocOficio(actionMapping, servletRequest, httpsession);
	    }

	    if (hacia.equals("buscarDocOficio")) {
		return buscarDocOficio(actionMapping, servletRequest, httpsession);
	    }

	    if (hacia.equals("detEIVydetEventos")) {
		return detEIVyDetEventos(actionMapping, servletRequest, httpsession);
	    }
	    if (hacia.equals("detEIVyle")) {
		return detEIVyle(actionMapping, servletRequest, httpsession);
	    }
	    if (hacia.equals("detEIVydetEventyflujos")) {
		return detEIVydetEventyflujos(actionMapping, servletRequest, httpsession);
	    }

	    /*
	     * if (hacia.equals("aCorregirEvento")) { return
	     * aCorregirEvento(actionMapping, servletRequest, httpsession); }
	     */
	    if (hacia.equals("detEvento")) {
		return detEvento(actionMapping, servletRequest, httpsession);
	    }

	    if (hacia.equals("aEnvioEmail")) {
		return aEnvioEmail(actionMapping, servletRequest, httpsession);
	    }

	    if (hacia.equals("enviarEmail")) {
		return enviarEmail(actionMapping, servletRequest, httpsession);
	    }

	    if (hacia.equals("aEnviarEmailIngreso")) {
		return aEnviarEmailIngreso(actionMapping, servletRequest, httpsession);
	    }

	    if (hacia.equals("enviarEmailIngreso")) {
		return enviarEmail(actionMapping, servletRequest, httpsession);
	    }

	    if (hacia.equals("registrarEIV")) {
		return registrarEIV(actionMapping, servletRequest, httpsession);
	    }

	    // ---------- COMUNAS ---------------

	    if (hacia.equals("adminComunas")) {
		return adminComunas(actionMapping, servletRequest, httpsession);
	    }
	    if (hacia.equals("eliminarComuna")) {
		return eliminarComuna(actionMapping, servletRequest, httpsession);
	    }
	    if (hacia.equals("agregarComuna")) {
		return agregarComuna(actionMapping, servletRequest, httpsession);
	    }

	    // ----------------------------------------------------

	    if (hacia.equals("cargarBuscarEIV")) {
		return cargarBuscarEIV(actionMapping, servletRequest, httpsession);
	    }

	    if (hacia.equals("buscarEIV")) {
		return buscarEIV(actionMapping, servletRequest, httpsession);
	    }
	    if (hacia.equals("detalleEIV")) {
		return detalleEIV(actionMapping, servletRequest, httpsession);
	    }
	    if (hacia.equals("abuscarParaEditarEIV")) {
		return abusParaEditarEIV(actionMapping, servletRequest, httpsession);
	    }
	    if (hacia.equals("busParaEditarEIV")) {
		return busParaEditarEIV(actionMapping, servletRequest, httpsession);
	    }

	    if (hacia.equals("editarEIV")) {
		return editarEIV(actionMapping, servletRequest, httpsession);
	    }

	    if (hacia.equals("actualizarEIV")) {
		return actualizarEIV(actionMapping, servletRequest, httpsession);
	    }

	    // ----BITACORA----------------------

	    if (hacia.equals("editarBitacora")) {
		return editarBitacora(actionMapping, servletRequest, httpsession);
	    }

	    if (hacia.equals("aAgregarBit")) {
		return aAgregarEvento(actionMapping, servletRequest, httpsession);
	    }

	    if (hacia.equals("primeraRev")) {
		return regPrimeraRev(actionMapping, servletRequest, httpsession);
	    }

	    if (hacia.equals("reingresarEIV")) {
		return reingresarEIV(actionMapping, servletRequest, httpsession);
	    }

	    if (hacia.equals("aprobar_UOCT")) {
		return aprobarUOCT(actionMapping, servletRequest, httpsession);
	    }

	    if (hacia.equals("rechazar_UOCT")) {
		return rechazarUOCT(actionMapping, servletRequest, httpsession);
	    }

	    if (hacia.equals("aprobar_SEREMITT")) {
		return aprobarSEREMITT(actionMapping, servletRequest, httpsession);
	    }

	    if (hacia.equals("rechazar_SEREMITT")) {
		return rechazarSEREMITT(actionMapping, servletRequest, httpsession);
	    }

	    if (hacia.equals("agregarEvento")) {
		return agregarEvento(actionMapping, servletRequest, httpsession);
	    }

	    if (hacia.equals("editarEvento")) {
		return editarEvento(actionMapping, servletRequest, httpsession);
	    }

	    if (hacia.equals("actualizarEvento")) {
		return actualizarEvento(actionMapping, servletRequest, httpsession);
	    }

	    if (hacia.equals("despacharEIV")) {
		return despacharEIV(actionMapping, servletRequest, httpsession);
	    }
	    if (hacia.equals("aDespachar")) {
		return aDespachar(actionMapping, servletRequest, httpsession);
	    }

	    /*
	     * if (hacia.equals("aReingresar")) { return
	     * aReingresar(actionMapping, servletRequest, httpsession); }
	     * 
	     * 
	     * 
	     * if (hacia.equals("aAprobRech")) { return
	     * aAprobRech(actionMapping, servletRequest, httpsession); }
	     * 
	     * if (hacia.equals("aprobar_rechazar")) { return
	     * aprob_rech(actionMapping, servletRequest, httpsession); }
	     */
	    if (hacia.equals("eliminarEvento")) {
		return eliminarEvento(actionMapping, servletRequest, httpsession);
	    }

	    if (hacia.equals("editarFlujos")) {
		return editarFlujos(actionMapping, servletRequest, httpsession);
	    }

	    if (hacia.equals("ingresarFlujo")) {
		return ingresarFlujo(actionMapping, servletRequest, httpsession);
	    }

	    // ----------------------------------------------------------

	    servletRequest.setAttribute("mensaje", "URL no v�lida");
	    return actionMapping.findForward("mensaje_fw");

	}
	servletRequest.setAttribute("mensaje", "URL no v�lida");
	return actionMapping.findForward("mensaje_fw");
    }

    /**
     * actualizarEvento
     *
     * Recibe y procesa la petici�n de actualizar los datos de un evento de una
     * bit�cora.
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward actualizarEvento(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    Integer id_eiv = Integer.valueOf(servletRequest.getParameter("id_eiv"));
	    Integer idEvento = Integer.valueOf(servletRequest.getParameter("idEvento"));
	    String nomEIV = servletRequest.getParameter("nomEIV");

	    String fechaEvento = servletRequest.getParameter("fechaEvento");

	    String desc_ev = servletRequest.getParameter("desc_ev");

	    Integer idDoc = Integer.valueOf(servletRequest.getParameter("idDoc"));

	    String tipoDoc = servletRequest.getParameter("tipoDoc");

	    EventoVO evento = new EventoVO();
	    evento.setIdEvento(idEvento);
	    evento.setFechaEv(sdf.parse(fechaEvento));
	    evento.setDescEv(desc_ev);
	    evento.setIdDocRel(idDoc);
	    evento.setTipoDoc(tipoDoc);

	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();

	    eivLocal.actualizarEvento(evento);

	    servletRequest.setAttribute("nomEIV", nomEIV);
	    servletRequest.setAttribute("id_eiv", id_eiv);

	    return detalleEIV(actionMapping, servletRequest, httpsession);
	} catch (Exception e) {
	    System.out.print(e.toString());
	    servletRequest.setAttribute("mensaje", "Error en el procesamiento de la actualizaci�n");
	    return actionMapping.findForward("mensaje_fw");
	}

    }

    /**
     * editarEvento
     *
     * Obtiene los datos de un evento de bit�cora de un EIV y los env�a a la
     * p�gina correspondiente para la edici�n de los datos.
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward editarEvento(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {

	try {
	    Integer id_eiv = Integer.valueOf(servletRequest.getParameter("id_eiv"));
	    Integer idEvento = Integer.valueOf(servletRequest.getParameter("idEvento"));
	    String nomEIV = servletRequest.getParameter("nomEIV");

	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();

	    servletRequest.setAttribute("detEvento", eivLocal.getDetalleEvento(idEvento));
	    servletRequest.setAttribute("nomEIV", nomEIV);
	    servletRequest.setAttribute("id_eiv", id_eiv);

	    return actionMapping.findForward("editarEvento_fw");
	} catch (Exception e) {
	    System.out.print(e.toString());
	    servletRequest.setAttribute("mensaje", "Error en el procesamiento de la petici�n");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * rechazarSEREMITT
     *
     *
     * Los tipos de eventos son: (1) Primera Revisi�n: Implica el cambio de
     * estado del EIV a 'En Observaci�n{2}' (2) Reingreso de documentaci�n. (3)
     * Despacho de documentacion: (4) Aprobacion por parte de UOCT:Implica el
     * cambio de estado del EIV a 'Aprobado por UOCT{3}' (5) Rechazo por parte
     * de UOCT: Implica el cambio de estado del EIV a 'Rechazado por UOCT{4}'
     * (6) Aprobacion por parte de SEREMITT (7) Rechazo por parte de SEREMITT
     * (8) Otro tipo de bit�cora.
     * 
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward rechazarSEREMITT(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    Integer id_eiv = Integer.valueOf(servletRequest.getParameter("id_eiv"));
	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();
	    String fechaEvento = servletRequest.getParameter("fechaEvento");
	    String desc_ev = servletRequest.getParameter("desc_ev");
	    String titulo = servletRequest.getParameter("tituloEvento");
	    EventoVO nuevoev = new EventoVO();
	    Integer id_doc = null;
	    if (!servletRequest.getParameter("idDoc").equals("")) {
		id_doc = Integer.valueOf(servletRequest.getParameter("idDoc"));
		nuevoev.setIdDocRel(id_doc);
	    }

	    nuevoev.setFechaEv(sdf.parse(fechaEvento));
	    nuevoev.setDescEv(desc_ev);
	    nuevoev.setTitulo(titulo);

	    boolean isInsertEvento = eivLocal.insertEventoEIV(nuevoev, id_eiv, new Integer(7)); // Para
												// el
												// �ltimo
												// parametro
												// ver
												// comentario
												// del
												// metodo

	    if (isInsertEvento) {
		servletRequest.setAttribute("mensaje",
			"La bit�cora ha sido ingresada. El EIV ha sido registrado como rechazado por seremitt"
				+ "<br><br><a href='eivAction.do?hacia=detalleEIV&id_eiv=" + id_eiv
				+ "'>Ver Detalle del EIV</a>");

	    } else {
		servletRequest.setAttribute("mensaje", "La bit�cora no pudo ser ingresada");
	    }
	    return actionMapping.findForward("mensaje_fw");
	    // return detalleEIV(actionMapping, servletRequest, httpsession);
	    // return editarBitacora(actionMapping, servletRequest,
	    // httpsession);
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo agregar bit�cora");
	    return actionMapping.findForward("mensaje_fw");
	}

    }

    /**
     * aprobarSEREMITT
     *
     *
     * Los tipos de eventos son: (1) Primera Revisi�n: Implica el cambio de
     * estado del EIV a 'En Observaci�n{2}' (2) Reingreso de documentaci�n. (3)
     * Despacho de documentacion: (4) Aprobacion por parte de UOCT:Implica el
     * cambio de estado del EIV a 'Aprobado por UOCT{3}' (5) Rechazo por parte
     * de UOCT: Implica el cambio de estado del EIV a 'Rechazado por UOCT{4}'
     * (6) Aprobacion por parte de SEREMITT (7) Rechazo por parte de SEREMITT
     * (8) Otro tipo de bit�cora.
     * 
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward aprobarSEREMITT(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    Integer id_eiv = Integer.valueOf(servletRequest.getParameter("id_eiv"));
	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();
	    String fechaEvento = servletRequest.getParameter("fechaEvento");
	    String desc_ev = servletRequest.getParameter("desc_ev");
	    String titulo = servletRequest.getParameter("tituloEvento");
	    EventoVO nuevoev = new EventoVO();
	    Integer id_doc = null;
	    if (!servletRequest.getParameter("idDoc").equals("")) {
		id_doc = Integer.valueOf(servletRequest.getParameter("idDoc"));
		nuevoev.setIdDocRel(id_doc);
	    }

	    nuevoev.setFechaEv(sdf.parse(fechaEvento));
	    nuevoev.setDescEv(desc_ev);
	    nuevoev.setTitulo(titulo);

	    boolean isInsertEvento = eivLocal.insertEventoEIV(nuevoev, id_eiv, new Integer(6)); // Para
												// el
												// �ltimo
												// parametro
												// ver
												// comentario
												// del
												// metodo

	    if (isInsertEvento) {
		servletRequest.setAttribute("mensaje",
			"La bit�cora ha sido ingresada.El EIV ha sido registrado como aprobado por seremitt"
				+ "<br><br><a href='eivAction.do?hacia=detalleEIV&id_eiv=" + id_eiv
				+ "'>Ver Detalle del EIV</a>");

	    } else {
		servletRequest.setAttribute("mensaje", "La bit�cora no pudo ser ingresada");
	    }
	    return actionMapping.findForward("mensaje_fw");
	    // return detalleEIV(actionMapping, servletRequest, httpsession);
	    // return editarBitacora(actionMapping, servletRequest,
	    // httpsession);
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo agregar bit�cora");
	    return actionMapping.findForward("mensaje_fw");
	}

    }

    /**
     * rechazarUOCT
     *
     *
     * Los tipos de eventos son: (1) Primera Revisi�n: Implica el cambio de
     * estado del EIV a 'En Observaci�n{2}' (2) Reingreso de documentaci�n. (3)
     * Despacho de documentacion: (4) Aprobacion por parte de UOCT:Implica el
     * cambio de estado del EIV a 'Aprobado por UOCT{3}' (5) Rechazo por parte
     * de UOCT: Implica el cambio de estado del EIV a 'Rechazado por UOCT{4}'
     * (6) Aprobacion por parte de SEREMITT (7) Rechazo por parte de SEREMITT
     * (8) Otro tipo de bit�cora.
     * 
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward rechazarUOCT(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    Integer id_eiv = Integer.valueOf(servletRequest.getParameter("id_eiv"));
	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();
	    String fechaEvento = servletRequest.getParameter("fechaEvento");
	    String desc_ev = servletRequest.getParameter("desc_ev");
	    String titulo = servletRequest.getParameter("tituloEvento");
	    EventoVO nuevoev = new EventoVO();
	    Integer id_doc = null;
	    if (!servletRequest.getParameter("idDoc").equals("")) {
		id_doc = Integer.valueOf(servletRequest.getParameter("idDoc"));
		nuevoev.setIdDocRel(id_doc);
	    }

	    nuevoev.setFechaEv(sdf.parse(fechaEvento));
	    nuevoev.setDescEv(desc_ev);
	    nuevoev.setTitulo(titulo);

	    boolean isInsertEvento = eivLocal.insertEventoEIV(nuevoev, id_eiv, new Integer(5)); // Para
												// el
												// �ltimo
												// parametro
												// ver
												// comentario
												// del
												// metodo

	    if (isInsertEvento) {
		servletRequest.setAttribute("mensaje",
			"La bit�cora ha sido ingresada. El EIV ha sido registrado como rechazado por UOCT"
				+ "<br><br><a href='eivAction.do?hacia=detalleEIV&id_eiv=" + id_eiv
				+ "'>Ver Detalle del EIV</a>");

	    } else {
		servletRequest.setAttribute("mensaje", "La bit�cora no pudo ser ingresada");
	    }

	    return actionMapping.findForward("mensaje_fw");
	    // return detalleEIV(actionMapping, servletRequest, httpsession);
	    // return editarBitacora(actionMapping, servletRequest,
	    // httpsession);
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo agregar bit�cora");
	    return actionMapping.findForward("mensaje_fw");
	}

    }

    /**
     * aprobarUOCT Los tipos de eventos son: (1) Primera Revisi�n: Implica el
     * cambio de estado del EIV a 'En Observaci�n{2}' (2) Reingreso de
     * documentaci�n. (3) Despacho de documentacion: (4) Aprobacion por parte de
     * UOCT:Implica el cambio de estado del EIV a 'Aprobado por UOCT{3}' (5)
     * Rechazo por parte de UOCT: Implica el cambio de estado del EIV a
     * 'Rechazado por UOCT{4}' (6) Aprobacion por parte de SEREMITT (7) Rechazo
     * por parte de SEREMITT (8) Otro tipo de bit�cora.
     * 
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward aprobarUOCT(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    Integer id_eiv = Integer.valueOf(servletRequest.getParameter("id_eiv"));
	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();
	    String fechaEvento = servletRequest.getParameter("fechaEvento");
	    String desc_ev = servletRequest.getParameter("desc_ev");
	    String titulo = servletRequest.getParameter("tituloEvento");
	    EventoVO nuevoev = new EventoVO();
	    Integer id_doc = null;
	    if (!servletRequest.getParameter("idDoc").equals("")) {
		id_doc = Integer.valueOf(servletRequest.getParameter("idDoc"));
		nuevoev.setIdDocRel(id_doc);
	    }

	    nuevoev.setFechaEv(sdf.parse(fechaEvento));
	    nuevoev.setDescEv(desc_ev);
	    nuevoev.setTitulo(titulo);

	    boolean isInsertEvento = eivLocal.insertEventoEIV(nuevoev, id_eiv, new Integer(4)); // Para
												// el
												// �ltimo
												// parametro
												// ver
												// comentario
												// del
												// metodo

	    if (isInsertEvento) {
		servletRequest.setAttribute("mensaje",
			"La bit�cora ha sido ingresada .El EIV ha sido registrado como aprobado por UOCT"
				+ "<br><br><a href='eivAction.do?hacia=detalleEIV&id_eiv=" + id_eiv
				+ "'>Ver Detalle del EIV</a>");

	    } else {
		servletRequest.setAttribute("mensaje", "La bit�cora no pudo ser ingresada");
	    }

	    return actionMapping.findForward("mensaje_fw");
	    // return detalleEIV(actionMapping, servletRequest, httpsession);
	    // return editarBitacora(actionMapping, servletRequest,
	    // httpsession);
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo agregar bit�cora");
	    return actionMapping.findForward("mensaje_fw");
	}

    }

    /**
     * regPrimeraRev
     *
     *
     * Los tipos de eventos son: (1) Primera Revisi�n: Implica el cambio de
     * estado del EIV a 'En Observaci�n{2}' (2) Reingreso de documentaci�n. (3)
     * Despacho de documentacion: (4) Aprobacion por parte de UOCT:Implica el
     * cambio de estado del EIV a 'Aprobado por UOCT{3}' (5) Rechazo por parte
     * de UOCT: Implica el cambio de estado del EIV a 'Rechazado por UOCT{4}'
     * (6) Aprobacion por parte de SEREMITT (7) Rechazo por parte de SEREMITT
     * (8) Otro tipo de bit�cora.
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward regPrimeraRev(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    Integer id_eiv = Integer.valueOf(servletRequest.getParameter("id_eiv"));
	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();
	    String fechaEvento = servletRequest.getParameter("fechaEvento");
	    String desc_ev = servletRequest.getParameter("desc_ev");
	    String titulo = servletRequest.getParameter("tituloEvento");
	    EventoVO nuevoev = new EventoVO();
	    Integer id_doc = null;
	    if (!servletRequest.getParameter("idDoc").equals("")) {
		id_doc = Integer.valueOf(servletRequest.getParameter("idDoc"));
		nuevoev.setIdDocRel(id_doc);
	    }

	    nuevoev.setFechaEv(sdf.parse(fechaEvento));
	    nuevoev.setDescEv(desc_ev);
	    nuevoev.setTitulo(titulo);

	    boolean isInsertEvento = eivLocal.insertEventoEIV(nuevoev, id_eiv, new Integer(1)); // para
												// el
												// ultimo
												// parametro
												// ver
												// el
												// comentario
												// del
												// metodo

	    if (isInsertEvento) {
		servletRequest.setAttribute("mensaje",
			"La bit�cora ha sido ingresada como Primera Revisi�n, "
				+ "por lo que el EISTU esta en estado 'En Observacion'"
				+ "<br><br><a href='eivAction.do?hacia=detalleEIV&id_eiv=" + id_eiv
				+ "'>Ver Detalle del EIV</a>");

	    } else {
		servletRequest.setAttribute("mensaje", "La bit�cora no pudo ser ingresada");
	    }
	    return actionMapping.findForward("mensaje_fw");
	    // return detalleEIV(actionMapping, servletRequest, httpsession);
	    // return editarBitacora(actionMapping, servletRequest,
	    // httpsession);
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo agregar bit�cora");
	    return actionMapping.findForward("mensaje_fw");
	}

    }

    /**
     * aAgregarEvento
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward aAgregarEvento(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();
	    String nomEIV = servletRequest.getParameter("nomEIV");
	    Integer id_eiv = Integer.valueOf(servletRequest.getParameter("id_eiv"));

	    List eventos = eivLocal.getEventosEIV(id_eiv);

	    UsuarioLocal usuarioLoc = LocalizadorServicios.getInstance().getUsuarioLocal();
	    DatosSesVO ds = (DatosSesVO) httpsession.getAttribute("Ses_Usu");

	    if (usuarioLoc.tienePermiso(ds.getIdPerfil(), "Registrar Primera Revision")) {
		servletRequest.setAttribute("primera", new Integer(1));
	    } else {
		servletRequest.setAttribute("primera", new Integer(0));
	    }

	    if (usuarioLoc.tienePermiso(ds.getIdPerfil(), "Registrar Reingreso de EISTU")) {
		servletRequest.setAttribute("reingreso", new Integer(1));
	    } else {
		servletRequest.setAttribute("reingreso", new Integer(0));
	    }

	    if (usuarioLoc.tienePermiso(ds.getIdPerfil(), "Despachar documento EIV")) {
		servletRequest.setAttribute("despachar", new Integer(1));
	    } else {
		servletRequest.setAttribute("despachar", new Integer(0));
	    }

	    if (usuarioLoc.tienePermiso(ds.getIdPerfil(), "Aprobar por UOCT")) {
		servletRequest.setAttribute("aprobarUOCT", new Integer(1));
	    } else {
		servletRequest.setAttribute("aprobarUOCT", new Integer(0));
	    }

	    if (usuarioLoc.tienePermiso(ds.getIdPerfil(), "Rechazar por UOCT")) {
		servletRequest.setAttribute("rechazarUOCT", new Integer(1));
	    } else {
		servletRequest.setAttribute("rechazarUOCT", new Integer(0));
	    }

	    if (usuarioLoc.tienePermiso(ds.getIdPerfil(), "Aprobar por SEREMITT")) {
		servletRequest.setAttribute("aprobarSeremitt", new Integer(1));
	    } else {
		servletRequest.setAttribute("aprobarSeremitt", new Integer(0));
	    }

	    if (usuarioLoc.tienePermiso(ds.getIdPerfil(), "Rechazar por SEREMITT")) {
		servletRequest.setAttribute("rechazarSeremitt", new Integer(1));
	    } else {
		servletRequest.setAttribute("rechazarSeremitt", new Integer(0));
	    }

	    if (usuarioLoc.tienePermiso(ds.getIdPerfil(), "Registrar otros eventos")) {
		servletRequest.setAttribute("otrosEventos", new Integer(1));
	    } else {
		servletRequest.setAttribute("otrosEventos", new Integer(0));
	    }

	    servletRequest.setAttribute("id_eiv", id_eiv);
	    servletRequest.setAttribute("nomEIV", nomEIV);
	    servletRequest.setAttribute("eventos", eventos);

	    return actionMapping.findForward("registrarBit_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar la p�gina de registro de bit�cora");
	    return actionMapping.findForward("mensaje_fw");
	}

    }

    /**
     * ingresarFlujo
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward ingresarFlujo(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {

	Integer id_eiv = Integer.valueOf(servletRequest.getParameter("id_eiv"));

	FlujoVO flujo = new FlujoVO();
	Date fechaflujo = new Date();
	try {
	    fechaflujo = sdf.parse(servletRequest.getParameter("fechaflujo"));
	} catch (ParseException ex3) {
	}

	Integer tipodia = Integer.valueOf(servletRequest.getParameter("tipodia"));
	String calle1 = servletRequest.getParameter("calle1");
	String calle2 = servletRequest.getParameter("calle2");
	String horasMed = servletRequest.getParameter("horasMed");

	flujo.setIdEIV(id_eiv);
	flujo.setFecha(fechaflujo);
	flujo.setCalle1(calle1);
	flujo.setCalle2(calle2);
	flujo.setIdTipoDia(tipodia);
	flujo.setHorasMed(horasMed);

	EIVLocal eivLocal = null;
	try {
	    eivLocal = LocalizadorServicios.getInstance().getEIVLocal();
	} catch (LocalizadorServiciosException ex) {
	    System.out.println("no se pudo llamar a EJB");
	}

	eivLocal.insertFlujo(flujo);
	servletRequest.setAttribute("id_eiv", id_eiv);
	return actionMapping.findForward("registrarFlujo_fw");
    }

    /**
     * editarFlujos
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward editarFlujos(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    servletRequest.setAttribute("id_eiv", Integer.valueOf(servletRequest.getParameter("id_eiv")));
	    return actionMapping.findForward("registrarFlujo_fw");
	} catch (Exception e) {
	    servletRequest.setAttribute("mensaje", "No se pudo cargar la p�gina de flujos vehiculares");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * aEnviarEmailIngreso
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward aEnviarEmailIngreso(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {

	    UsuarioLocal usuarioLocal = LocalizadorServicios.getInstance().getUsuarioLocal();
	    EIVLocal eiv = LocalizadorServicios.getInstance().getEIVLocal();
	    List listaUsuarios = usuarioLocal.getListaUsuarios();

	    String nomEIV = null;
	    Integer id_eiv = null;

	    if (servletRequest.getParameter("nomEIV") != null && servletRequest.getParameter("id_eiv") != null) {
		nomEIV = servletRequest.getParameter("nomEIV");
		id_eiv = Integer.valueOf(servletRequest.getParameter("id_eiv"));
	    } else {
		nomEIV = (String) servletRequest.getAttribute("nomEIV");
		id_eiv = (Integer) servletRequest.getAttribute("id_eiv");
	    }

	    DatosSesVO datosses = (DatosSesVO) httpsession.getAttribute("Ses_Usu");
	    UsuarioVO u = usuarioLocal.getDatosUsu(datosses.getId());
	    String rmt = u.getEmail();
	    if (rmt == null) {
		servletRequest.setAttribute("mensaje",
			"La direcci�n de Email de su cuenta de Intranet no ha sido configurada. Registrela y vuelva a intentar el envio de email<br/> Configurela en 'Editar Datos personales' y vuelva a intentarlo'");
		return actionMapping.findForward("mensaje_fw");
	    }

	    servletRequest.setAttribute("rmt", rmt);
	    servletRequest.setAttribute("detEIV", eiv.getDetalleEIV(id_eiv));
	    servletRequest.setAttribute("id_eiv", id_eiv);
	    servletRequest.setAttribute("nomEIV", nomEIV);
	    servletRequest.setAttribute("listaUsuarios", listaUsuarios);
	    return actionMapping.findForward("envioEmailIngreso_fw");

	} catch (Exception ex) {
	    ex.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar la p�gina de env�o de email");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * enviarEmail
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward enviarEmail(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {

	    EIVLocal eivlocal = LocalizadorServicios.getInstance().getEIVLocal();
	    DatosSesVO datosses = (DatosSesVO) httpsession.getAttribute("Ses_Usu");

	    String passw = servletRequest.getParameter("passw");
	    String rmt = servletRequest.getParameter("rmt");
	    if (rmt == null) {
		servletRequest.setAttribute("mensaje",
			"No se recibi� correctamente la direcci�n de Email del remitente");
		return actionMapping.findForward("mensaje_fw");
	    } else {
		eivlocal.alertaEmail(rmt, passw, servletRequest.getParameter("emails") + "," + rmt,
			servletRequest.getParameter("subject"), servletRequest.getParameter("content"));
		servletRequest.setAttribute("mensaje", "El email ha sido enviado exitosamente");

		if (servletRequest.getParameter("ingresoEIV") != null) {
		    servletRequest.setAttribute("id_eiv", Integer.valueOf(servletRequest.getParameter("id_eiv")));
		    servletRequest.setAttribute("nomEIV", servletRequest.getParameter("nomEIV"));
		    return actionMapping.findForward("registrarFlujo_fw");
		}
		return actionMapping.findForward("mensaje_fw");

	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("id_eiv", Integer.valueOf(servletRequest.getParameter("id_eiv")));
	    servletRequest.setAttribute("nomEIV", servletRequest.getParameter("nomEIV"));
	    servletRequest.setAttribute("mensaje", "No se pudo enviar el mensaje");
	    return actionMapping.findForward("registrarFlujo_fw");
	}
    }

    /**
     * eliminarEvento
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward eliminarEvento(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    Integer idEvento = Integer.valueOf(servletRequest.getParameter("idEvento"));
	    EIVLocal eivlocal = LocalizadorServicios.getInstance().getEIVLocal();
	    if (eivlocal.eliminarEvento(idEvento)) {
		servletRequest.setAttribute("mensaje", "El evento fue eliminado");
	    } else {
		servletRequest.setAttribute("mensaje", "No se pudo eliminar el evento");
	    }
	    return editarBitacora(actionMapping, servletRequest, httpsession);
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo eliminar el evento");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * aprob_rech
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    /*
     * private ActionForward aprob_rech(ActionMapping actionMapping,
     * HttpServletRequest servletRequest, HttpSession httpsession) { try {
     * Integer id_eiv = Integer.valueOf(servletRequest.getParameter( "id_eiv"));
     * EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();
     * String fechaDespacho = servletRequest.getParameter("fecha"); String
     * desc_ev = servletRequest.getParameter("desc_ev"); Integer accion =
     * Integer.valueOf(servletRequest.getParameter( "accion")); String titulo =
     * null; if (accion.intValue() == 1) { titulo = "Aprobaci�n de EIV"; } if
     * (accion.intValue() == 2) { titulo = "Rechazo de EIV"; } EventoVO nuevoev
     * = new EventoVO(); Integer id_doc = null; id_doc =
     * Integer.valueOf(servletRequest. getParameter("idDoc"));
     * nuevoev.setIdDocRel(id_doc);
     * 
     * nuevoev.setFechaEv(sdf.parse(fechaDespacho)); nuevoev.setDescEv(desc_ev);
     * nuevoev.setTitulo(titulo); boolean isInsertEvento =
     * eivLocal.aprobar_rechazar(nuevoev, id_eiv, accion); if (isInsertEvento) {
     * servletRequest.setAttribute("mensaje",
     * "El operaci�n efectuada exitosamente"); } else {
     * servletRequest.setAttribute("mensaje",
     * "La operacion no pudo ser realizada"); } return detalleEIV(actionMapping,
     * servletRequest, httpsession); //return aAprobRech(actionMapping,
     * servletRequest, httpsession); } catch (Exception e) {
     * e.printStackTrace(); servletRequest.setAttribute("mensaje",
     * "No se pudo agregar bit�cora"); return
     * actionMapping.findForward("mensaje_fw"); } }
     */
    /**
     * reingresarEIV
     *
     *
     * * Los tipos de eventos son: (1) Primera Revisi�n: Implica el cambio de
     * estado del EIV a 'En Observaci�n{2}' (2) Reingreso de documentaci�n. (3)
     * Despacho de documentacion: (4) Aprobacion por parte de UOCT:Implica el
     * cambio de estado del EIV a 'Aprobado por UOCT{3}' (5) Rechazo por parte
     * de UOCT: Implica el cambio de estado del EIV a 'Rechazado por UOCT{4}'
     * (6) Aprobacion por parte de SEREMITT (7) Rechazo por parte de SEREMITT
     * (8) Otro tipo de bit�cora.
     *
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward reingresarEIV(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    Integer id_eiv = Integer.valueOf(servletRequest.getParameter("id_eiv"));
	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();
	    String fechaDespacho = servletRequest.getParameter("fechaEvento");
	    String desc_ev = servletRequest.getParameter("desc_ev");
	    String titulo = "Reingreso de Documentaci�n";
	    EventoVO nuevoev = new EventoVO();
	    Integer id_doc = null;
	    id_doc = Integer.valueOf(servletRequest.getParameter("idDoc"));
	    nuevoev.setIdDocRel(id_doc);

	    nuevoev.setFechaEv(sdf.parse(fechaDespacho));
	    nuevoev.setDescEv(desc_ev);
	    nuevoev.setTitulo(titulo);
	    boolean isInsertEvento = eivLocal.insertEventoEIV(nuevoev, id_eiv, new Integer(2)); // ver
												// comentario
												// del
												// metodo
	    if (isInsertEvento) {
		servletRequest.setAttribute("mensaje",
			"El reingreso ha sido registrado" + "<BR><BR><a href='eivAction.do?hacia=detalleEIV&id_eiv="
				+ id_eiv + "'>Ver Detalle del EIV</a>");

	    } else {
		servletRequest.setAttribute("mensaje", "El reingreso no pudo ser registrado");
	    }
	    return actionMapping.findForward("mensaje_fw");

	    // return detalleEIV(actionMapping, servletRequest, httpsession);
	    // return aReingresar(actionMapping, servletRequest, httpsession);
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo agregar bit�cora");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * despacharEIV * Los tipos de eventos son: (1) Primera Revisi�n: Implica el
     * cambio de estado del EIV a 'En Observaci�n{2}' (2) Reingreso de
     * documentaci�n. (3) Despacho de documentacion: (4) Aprobacion por parte de
     * UOCT:Implica el cambio de estado del EIV a 'Aprobado por UOCT{3}' (5)
     * Rechazo por parte de UOCT: Implica el cambio de estado del EIV a
     * 'Rechazado por UOCT{4}' (6) Aprobacion por parte de SEREMITT (7) Rechazo
     * por parte de SEREMITT (8) Otro tipo de bit�cora.
     *
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward despacharEIV(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    Integer id_eiv = Integer.valueOf(servletRequest.getParameter("id_eiv"));
	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();
	    String fechaDespacho = servletRequest.getParameter("fechaEvento");
	    String desc_ev = servletRequest.getParameter("desc_ev");
	    String titulo = "Despacho de documentacion";
	    EventoVO nuevoev = new EventoVO();
	    Integer id_doc = null;
	    id_doc = Integer.valueOf(servletRequest.getParameter("idDoc"));
	    nuevoev.setIdDocRel(id_doc);

	    nuevoev.setFechaEv(sdf.parse(fechaDespacho));
	    nuevoev.setDescEv(desc_ev);
	    nuevoev.setTitulo(titulo);

	    boolean isInsertEvento = eivLocal.insertEventoEIV(nuevoev, id_eiv, new Integer(3)); // ver
												// comentario
												// del
												// metodo
	    if (isInsertEvento) {
		servletRequest.setAttribute("mensaje",
			"El despacho de documentaci�n se ha registrado exitosamente"
				+ "<BR><BR><a href='eivAction.do?hacia=detalleEIV&id_eiv=" + id_eiv
				+ "'>Ver Detalle del EIV</a>");
	    } else {
		servletRequest.setAttribute("mensaje", "No se pudo registrar el despacho de documentaci�n");
	    }

	    return actionMapping.findForward("mensaje_fw");
	    // return detalleEIV(actionMapping, servletRequest, httpsession);
	    // return aDespachar(actionMapping, servletRequest, httpsession);
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo agregar bit�cora");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * agregarEvento
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward agregarEvento(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    Integer id_eiv = Integer.valueOf(servletRequest.getParameter("id_eiv"));
	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();
	    String fechaEvento = servletRequest.getParameter("fechaEvento");
	    String desc_ev = servletRequest.getParameter("desc_ev");
	    String titulo = servletRequest.getParameter("tituloEvento");
	    EventoVO nuevoev = new EventoVO();
	    Integer id_doc = null;
	    if (!servletRequest.getParameter("idDoc").equals("")) {
		id_doc = Integer.valueOf(servletRequest.getParameter("idDoc"));
		nuevoev.setIdDocRel(id_doc);
	    }

	    nuevoev.setFechaEv(sdf.parse(fechaEvento));
	    nuevoev.setDescEv(desc_ev);
	    nuevoev.setTitulo(titulo);

	    boolean isInsertEvento = eivLocal.insertEventoEIV(nuevoev, id_eiv, new Integer(8)); // ver
												// comentario
												// del
												// metodo
	    if (isInsertEvento) {
		servletRequest.setAttribute("mensaje",
			"La bit�cora ha sido ingresada" + "<BR><BR><a href='eivAction.do?hacia=detalleEIV&id_eiv="
				+ id_eiv + "'>Ver Detalle del EIV</a>");

	    } else {
		servletRequest.setAttribute("mensaje", "La bit�cora no pudo ser ingresada");
	    }

	    return actionMapping.findForward("mensaje_fw");
	    // return detalleEIV(actionMapping, servletRequest, httpsession);
	    // return editarBitacora(actionMapping, servletRequest,
	    // httpsession);
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo agregar bit�cora");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * editarBitacora
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward editarBitacora(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {

	try {
	    Integer id_eiv = Integer.valueOf(servletRequest.getParameter("id_eiv"));
	    String nomEIV = servletRequest.getParameter("nomEIV");
	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();

	    List eventos = eivLocal.getEventosEIV(id_eiv);

	    servletRequest.setAttribute("id_eiv", id_eiv);
	    servletRequest.setAttribute("nomEIV", nomEIV);
	    servletRequest.setAttribute("eventos", eventos);
	    return actionMapping.findForward("bitacoraEIV_fw");
	} catch (Exception ex4) {
	    ex4.printStackTrace();
	    servletRequest.setAttribute("mensaje", "no se pudo cargar la p�gina de Bitacora del EIV");
	    return actionMapping.findForward("bitacoraEIV_fw");
	}

    }

    /**
     * actualizarEIV
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward actualizarEIV(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    EIVVO eiv = new EIVVO();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	    eiv.setIdEIV(Integer.valueOf(servletRequest.getParameter("id_eiv")));
	    eiv.setIdEstado(Integer.valueOf(servletRequest.getParameter("id_estado")));
	    eiv.setIdTipoEstudio(Integer.valueOf(servletRequest.getParameter("id_tipoestudio")));

	    eiv.setFechaPresent(sdf.parse(servletRequest.getParameter("fechaPresentacion")));

	    eiv.setFechaEnvioSeremitt(sdf.parse(servletRequest.getParameter("fechaEnvioSeremitt")));

	    eiv.setFechaIng(sdf.parse(servletRequest.getParameter("fechaIngreso")));

	    eiv.setFechaVenc(sdf.parse(servletRequest.getParameter("fechaVencimiento")));

	    eiv.setIdComuna(Integer.valueOf(servletRequest.getParameter("idComuna")));
	    eiv.setDir(servletRequest.getParameter("direccion"));

	    String[] redes = servletRequest.getParameterValues("listaRel");
	    int ir = 0;
	    if (redes != null) {
		ir = redes.length;
	    }

	    List l_redes = new LinkedList();
	    Integer unared = null;
	    for (int i = 0; i < ir; i++) {
		unared = Integer.valueOf(redes[i]);
		l_redes.add(unared);
	    }

	    eiv.setRedes(l_redes);
	    eiv.setEstacionamientos(Integer.valueOf(servletRequest.getParameter("estacionamientos")));
	    eiv.setNomCons(servletRequest.getParameter("consultor"));

	    if (!servletRequest.getParameter("empconsultora").equals("")) {
		eiv.setEmpCons(servletRequest.getParameter("empconsultora"));
	    }
	    eiv.setIdEncargado(Integer.valueOf(servletRequest.getParameter("id_ingeniero")));

	    EIVLocal eivLocal = null;
	    eivLocal = LocalizadorServicios.getInstance().getEIVLocal();

	    if (eivLocal.actualizarEIV(eiv)) {
		servletRequest.setAttribute("mensaje", "El Eiv fue actualizado correctamente");
		return detalleEIV(actionMapping, servletRequest, httpsession);
		// return actionMapping.findForward("mensaje_fw");
	    }
	    return actionMapping.findForward("registrarFlujo_fw");
	} catch (LocalizadorServiciosException ex) {
	    ex.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo actualizar el EIV.");
	    return actionMapping.findForward("mensaje_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo actualizar el EIV");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * editarEIV
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward editarEIV(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {

	    Integer id_eiv = Integer.valueOf(servletRequest.getParameter("id_eiv"));

	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();

	    UsuarioLocal usuarioLocal = LocalizadorServicios.getInstance().getUsuarioLocal();

	    ClienteLocal clienteLocal = LocalizadorServicios.getInstance().getClienteLocal();

	    List listaIngenieros = usuarioLocal.getListaIngenieros();
	    EIVVO eiv = eivLocal.getDetalleEIV(id_eiv);
	    List comunas = eivLocal.getListaComunas();
	    List redes = eivLocal.getTodaslasRedes();
	    servletRequest.setAttribute("estados", eivLocal.getEstadosEIV());
	    servletRequest.setAttribute("tiposEIV", eivLocal.getTiposEIV());
	    servletRequest.setAttribute("redes", redes);
	    servletRequest.setAttribute("comunas", comunas);
	    servletRequest.setAttribute("listaIngenieros", listaIngenieros);
	    servletRequest.setAttribute("detalleeiv", eiv);
	    return actionMapping.findForward("editarEIV_fw");

	} catch (Exception ex) {
	    ex.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar la p�gina de Edici�n de EIV");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * busParaEditarEIV
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward busParaEditarEIV(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    BuscadorEIVVO bus = new BuscadorEIVVO();
	    String e = servletRequest.getParameter("ideiv");
	    if (!servletRequest.getParameter("ideiv").equals("")) {
		bus.setIdEIV(Integer.valueOf(e));
	    }
	    if (!servletRequest.getParameter("palClave").equals("")) {
		bus.setPalClave(servletRequest.getParameter("palClave"));
	    }
	    if (!servletRequest.getParameter("fechaVencimiento").equals("")) {
		bus.setFechaVenc(servletRequest.getParameter("fechaVencimiento"));
	    }

	    if (!servletRequest.getParameter("fechaVencimiento_b").equals("")) {
		bus.setFechaVenc_b(servletRequest.getParameter("fechaVencimiento_b"));
	    }

	    if (!servletRequest.getParameter("estado").equals("")) {
		bus.setIdEstado(Integer.valueOf(servletRequest.getParameter("estado")));
	    }
	    if (!servletRequest.getParameter("idIngeniero").equals("")) {
		bus.setIdIngeniero(Integer.valueOf(servletRequest.getParameter("idIngeniero")));
	    }
	    if (!servletRequest.getParameter("idComuna").equals("")) {
		bus.setIdComuna(Integer.valueOf(servletRequest.getParameter("idComuna")));
	    }

	    if (!servletRequest.getParameter("red").equals("")) {
		bus.setRed(Integer.valueOf(servletRequest.getParameter("red")));
	    }

	    if (!servletRequest.getParameter("consultor").equals("")) {
		bus.setConsultor(servletRequest.getParameter("consultor"));
	    }

	    UsuarioLocal usuarioLocal = LocalizadorServicios.getInstance().getUsuarioLocal();

	    DatosSesVO ds = (DatosSesVO) httpsession.getAttribute("Ses_Usu");

	    // Si tiene permisos para registrar flujos

	    boolean regFlujos = usuarioLocal.tienePermiso(ds.getIdPerfil(), "registrar flujos");
	    Integer regF = null;
	    if (regFlujos) {
		regF = new Integer(1);
	    } else {
		regF = new Integer(0);
	    }

	    // Si tiene permisos para editar la bit�cora

	    boolean edBit = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Editar bit�cora");
	    Integer edB = null;
	    if (edBit) {
		edB = new Integer(1);
	    } else {
		edB = new Integer(0);
	    }
	    // Si tiene permisos para enviar Email de Vencimiento
	    boolean email = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Enviar Email de Vencimiento");
	    Integer em = null;
	    if (email) {
		em = new Integer(1);
	    } else {
		em = new Integer(0);
	    }

	    // Si tiene permisos para enviar Editar los datos del EIV
	    boolean editar = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Editar EISTU");
	    Integer ed = null;
	    if (editar) {
		ed = new Integer(1);
	    } else {
		ed = new Integer(0);
	    }

	    // CONSULTA SI EL USUARIO TIENE PERMISOS PARA REGISTRAR DISTINTOS
	    // TIPOS DE BITACORAS

	    Integer regBit = null;

	    boolean pri = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Registrar Primera Revision");

	    boolean desp = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Despachar documento EIV");

	    boolean ap_uoct = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Aprobar por UOCT");

	    boolean rech_uoct = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Rechazar por UOCT");

	    boolean ap_sere = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Aprobar por SEREMITT");

	    boolean rech_sere = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Rechazar por SEREMITT");

	    boolean reing = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Registrar Reingreso de EISTU");

	    boolean otros = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Registrar otros eventos");

	    if (pri || desp || ap_uoct || rech_uoct || ap_sere || rech_sere || reing || otros) {
		regBit = new Integer(1);
	    } else {
		regBit = new Integer(0);
	    }

	    /***************************/

	    servletRequest.setAttribute("editarEIV", ed);
	    servletRequest.setAttribute("regBit", regBit);
	    servletRequest.setAttribute("email", em);
	    servletRequest.setAttribute("editarBitacora", edB);
	    servletRequest.setAttribute("regFlujos", regF);

	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();
	    List eivs = eivLocal.buscarEIV(bus);
	    servletRequest.setAttribute("listaEIV", eivs);
	    return abusParaEditarEIV(actionMapping, servletRequest, httpsession);
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar la p�gina de busqueda de EIV");
	    return actionMapping.findForward("mensaje_fw");
	}

    }

    /**
     * abusParaEditarEIV Carga la pagina de b�squeda de EIV para editar.
     *
     * NOTAR QUE SE EXIGE QUE LOS NOMBRES DE LAS FUNCIONALIDADES ASOCIADAS A LOS
     * PERFILES SEAN EXACTAMENTE COMO EST�N EN LA BASE DE DATOS!!
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward abusParaEditarEIV(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    List losIngenieros = null;
	    List estadosEIV = null;
	    UsuarioLocal usuarioLocal = null;
	    EIVLocal eivLocal = null;

	    usuarioLocal = LocalizadorServicios.getInstance().getUsuarioLocal();

	    eivLocal = LocalizadorServicios.getInstance().getEIVLocal();

	    DatosSesVO ds = (DatosSesVO) httpsession.getAttribute("Ses_Usu");

	    // Si tiene permisos para registrar flujos

	    boolean regFlujos = usuarioLocal.tienePermiso(ds.getIdPerfil(), "registrar flujos");
	    Integer regF = null;
	    if (regFlujos) {
		regF = new Integer(1);
	    } else {
		regF = new Integer(0);
	    }

	    // Si tiene permisos para editar la bit�cora

	    boolean edBit = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Editar bit�cora");
	    Integer edB = null;
	    if (edBit) {
		edB = new Integer(1);
	    } else {
		edB = new Integer(0);
	    }
	    // Si tiene permisos para enviar Email de Vencimiento
	    boolean email = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Enviar Email de Vencimiento");
	    Integer em = null;
	    if (email) {
		em = new Integer(1);
	    } else {
		em = new Integer(0);
	    }

	    // Si tiene permisos para enviar Editar los datos del EIV
	    boolean editar = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Editar EISTU");
	    Integer ed = null;
	    if (editar) {
		ed = new Integer(1);
	    } else {
		ed = new Integer(0);
	    }

	    // CONSULTA SI EL USUARIO TIENE PERMISOS PARA REGISTRAR DISTINTOS
	    // TIPOS DE BITACORAS

	    Integer regBit = null;

	    boolean pri = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Registrar Primera Revision");

	    boolean desp = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Despachar documento EIV");

	    boolean ap_uoct = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Aprobar por UOCT");

	    boolean rech_uoct = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Rechazar por UOCT");

	    boolean ap_sere = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Aprobar por SEREMITT");

	    boolean rech_sere = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Rechazar por SEREMITT");

	    boolean reing = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Registrar Reingreso de EISTU");

	    boolean otros = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Registrar otros eventos");

	    if (pri || desp || ap_uoct || rech_uoct || ap_sere || rech_sere || reing || otros) {
		regBit = new Integer(1);
	    } else {
		regBit = new Integer(0);
	    }

	    /***************************/

	    servletRequest.setAttribute("editarEIV", ed);
	    servletRequest.setAttribute("regBit", regBit);
	    servletRequest.setAttribute("email", em);
	    servletRequest.setAttribute("editarBitacora", edB);
	    servletRequest.setAttribute("regFlujos", regF);
	    /*
	     * servletRequest.setAttribute("editarEIV", ed);
	     * servletRequest.setAttribute("despachar", desp);
	     * servletRequest.setAttribute("reingresar", rei);
	     * servletRequest.setAttribute("apro_rech", a_r);
	     * servletRequest.setAttribute("email", em);
	     * servletRequest.setAttribute("editarBitacora", edB);
	     * servletRequest.setAttribute("regFlujos", regF);
	     */
	    losIngenieros = usuarioLocal.getListaIngenieros();
	    estadosEIV = eivLocal.getEstadosEIV();
	    servletRequest.setAttribute("listaIngenieros", losIngenieros);
	    servletRequest.setAttribute("listaEstados", estadosEIV);
	    servletRequest.setAttribute("comunas", eivLocal.getListaComunas());
	    return actionMapping.findForward("busParaEditarEIV_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar la p�gina de busqueda de EIV");
	    return actionMapping.findForward("mensaje_fw");
	}

    }

    /**
     * detalleEIV
     *
     * Muestra el detalle de un EIV
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward detalleEIV(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {

	try {
	    Integer id_eiv = Integer.valueOf(servletRequest.getParameter("id_eiv"));

	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();

	    UsuarioLocal usuarioLocal = LocalizadorServicios.getInstance().getUsuarioLocal();
	    DatosSesVO ds = (DatosSesVO) httpsession.getAttribute("Ses_Usu");

	    boolean regFlujos = usuarioLocal.tienePermiso(ds.getIdPerfil(), "registrar flujos");
	    Integer regF = null;
	    if (regFlujos) {
		regF = new Integer(1);
	    } else {
		regF = new Integer(0);
	    }

	    boolean edBit = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Editar bit�cora");
	    Integer edB = null;
	    if (edBit) {
		edB = new Integer(1);
	    } else {
		edB = new Integer(0);
	    }

	    boolean email = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Enviar Email de Vencimiento");
	    Integer em = null;
	    if (email) {
		em = new Integer(1);
	    } else {
		em = new Integer(0);
	    }

	    boolean editar = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Editar EIV");
	    Integer ed = null;
	    if (editar) {
		ed = new Integer(1);
	    } else {
		ed = new Integer(0);
	    }

	    Integer regBit = null;

	    boolean pri = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Registrar Primera Revision");

	    boolean desp = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Despachar documento EIV");

	    boolean ap_uoct = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Aprobar por UOCT");

	    boolean rech_uoct = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Rechazar por UOCT");

	    boolean ap_sere = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Aprobar por SEREMITT");

	    boolean rech_sere = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Rechazar por SEREMITT");

	    boolean reing = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Registrar Reingreso de EISTU");

	    boolean otros = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Registrar otros eventos");

	    if (pri || desp || ap_uoct || rech_uoct || ap_sere || rech_sere || reing || otros) {
		regBit = new Integer(1);
	    } else {
		regBit = new Integer(0);
	    }

	    servletRequest.setAttribute("editarEIV", ed);
	    servletRequest.setAttribute("regBit", regBit);
	    servletRequest.setAttribute("email", em);
	    servletRequest.setAttribute("editarBitacora", edB);
	    servletRequest.setAttribute("regFlujos", regF);

	    servletRequest.setAttribute("detalleeiv", eivLocal.getDetalleEIV(id_eiv));
	    servletRequest.setAttribute("eventos", eivLocal.getEventosEIV(id_eiv));
	    return actionMapping.findForward("detEIV_fw");
	} catch (LocalizadorServiciosException ex) {
	    ex.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar el detalle del EIV");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * buscarEIV
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward buscarEIV(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    BuscadorEIVVO bus = new BuscadorEIVVO();
	    String e = servletRequest.getParameter("ideiv");
	    if (!servletRequest.getParameter("ideiv").equals("")) {
		bus.setIdEIV(Integer.valueOf(e));
	    }
	    if (!servletRequest.getParameter("palClave").equals("")) {
		bus.setPalClave(servletRequest.getParameter("palClave"));
	    }
	    if (!servletRequest.getParameter("fechaVencimiento").equals("")) {
		bus.setFechaVenc(servletRequest.getParameter("fechaVencimiento"));
	    }

	    if (!servletRequest.getParameter("estadoSeremitt").equals("0")) {
		bus.setEstadoSeremitt(Integer.valueOf(servletRequest.getParameter("estadoSeremitt")));
	    }

	    if (!servletRequest.getParameter("fechaVencimiento_b").equals("")) {
		bus.setFechaVenc_b(servletRequest.getParameter("fechaVencimiento_b"));
	    }

	    if (!servletRequest.getParameter("estado").equals("")) {
		bus.setIdEstado(Integer.valueOf(servletRequest.getParameter("estado")));
	    }
	    if (!servletRequest.getParameter("idIngeniero").equals("")) {
		bus.setIdIngeniero(Integer.valueOf(servletRequest.getParameter("idIngeniero")));
	    }
	    if (!servletRequest.getParameter("idComuna").equals("")) {
		bus.setIdComuna(Integer.valueOf(servletRequest.getParameter("idComuna")));
	    }

	    if (!servletRequest.getParameter("red").equals("")) {
		bus.setRed(Integer.valueOf(servletRequest.getParameter("red")));
	    }

	    if (!servletRequest.getParameter("consultor").equals("")) {
		bus.setConsultor(servletRequest.getParameter("consultor"));
	    }

	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();
	    List eivs = eivLocal.buscarEIV(bus);
	    servletRequest.setAttribute("listaEIV", eivs);
	    return cargarBuscarEIV(actionMapping, servletRequest, httpsession);
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar la p�gina de busqueda de EIV");
	    return actionMapping.findForward("mensaje_fw");
	}

    }

    /**
     * cargarBuscarEIV
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward cargarBuscarEIV(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {

	try {
	    List losIngenieros = null;
	    List estadosEIV = null;
	    UsuarioLocal usuarioLocal = null;
	    EIVLocal eivLocal = null;

	    usuarioLocal = LocalizadorServicios.getInstance().getUsuarioLocal();

	    eivLocal = LocalizadorServicios.getInstance().getEIVLocal();

	    losIngenieros = usuarioLocal.getListaIngenieros();
	    estadosEIV = eivLocal.getEstadosEIV();
	    servletRequest.setAttribute("listaIngenieros", losIngenieros);
	    servletRequest.setAttribute("listaEstados", estadosEIV);
	    servletRequest.setAttribute("comunas", eivLocal.getListaComunas());
	    return actionMapping.findForward("cargarBusEIV_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar la p�gina de busqueda de EIV");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * registrarEIV
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward registrarEIV(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {

	try {
	    EIVVO nuevoeiv = new EIVVO();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    nuevoeiv.setNomEiv(servletRequest.getParameter("titulo_eiv"));

	    nuevoeiv.setIdOficio(Integer.valueOf(servletRequest.getParameter("idDocOficio")));

	    nuevoeiv.setIdTipoEstudio(Integer.valueOf(servletRequest.getParameter("id_tipoestudio")));

	    nuevoeiv.setFechaPresent(sdf.parse(servletRequest.getParameter("fechaPresentacion")));

	    nuevoeiv.setFechaEnvioSeremitt(sdf.parse(servletRequest.getParameter("fechaEnvioSeremitt")));

	    nuevoeiv.setFechaIng(sdf.parse(servletRequest.getParameter("fechaIngreso")));

	    nuevoeiv.setFechaVenc(sdf.parse(servletRequest.getParameter("fechaVencimiento")));

	    nuevoeiv.setIdComuna(Integer.valueOf(servletRequest.getParameter("id_comuna")));
	    nuevoeiv.setDir(servletRequest.getParameter("direccion"));

	    String[] redes = servletRequest.getParameterValues("listaRel");
	    int ir = 0;
	    if (redes != null) {
		ir = redes.length;
	    }

	    List l_redes = new LinkedList();
	    Integer unared = null;
	    for (int i = 0; i < ir; i++) {
		unared = Integer.valueOf(redes[i]);
		l_redes.add(unared);
	    }

	    nuevoeiv.setRedes(l_redes);
	    nuevoeiv.setEstacionamientos(Integer.valueOf(servletRequest.getParameter("estacionamientos")));
	    nuevoeiv.setNomCons(servletRequest.getParameter("consultor"));

	    if (!servletRequest.getParameter("empconsultora").equals("")) {
		nuevoeiv.setEmpCons(servletRequest.getParameter("empconsultora"));
	    }
	    nuevoeiv.setIdEncargado(Integer.valueOf(servletRequest.getParameter("id_ingeniero")));

	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();

	    Integer idnuevo = eivLocal.insertEIV(nuevoeiv);
	    if (idnuevo == null) {
		servletRequest.setAttribute("mensaje", "No se pudo registrar el EIV");
		return actionMapping.findForward("mensaje_fw");
	    } else {
		servletRequest.setAttribute("mensaje", "El EIV fue registrado exitosamente");
	    }
	    servletRequest.setAttribute("id_eiv", idnuevo);
	    servletRequest.setAttribute("nomEIV", nuevoeiv.getNomEiv());

	} catch (LocalizadorServiciosException ex) {
	    ex.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo registrar el EIV.");
	    return actionMapping.findForward("mensaje_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo registrar el EIV");
	    return actionMapping.findForward("mensaje_fw");
	}

	if (servletRequest.getParameter("noEmail") == null) {
	    return aEnviarEmailIngreso(actionMapping, servletRequest, httpsession);
	} else {
	    return actionMapping.findForward("registrarFlujo_fw");
	}

    }

    /**
     * cargarRegistrarEIV
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward cargarRegistrarEIV(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    EIVLocal eivLocal = null;
	    UsuarioLocal usuarioLocal = null;
	    ClienteLocal clienteLocal = null;
	    DocumentoLocal documentoLocal = null;
	    eivLocal = LocalizadorServicios.getInstance().getEIVLocal();
	    usuarioLocal = LocalizadorServicios.getInstance().getUsuarioLocal();

	    clienteLocal = LocalizadorServicios.getInstance().getClienteLocal();
	    documentoLocal = LocalizadorServicios.getInstance().getDocumentoLocal();

	    List eivsnuevos = documentoLocal.getEIVnuevos();

	    List listaIngenieros = null;
	    listaIngenieros = usuarioLocal.getListaIngenieros();
	    List comunas = eivLocal.getListaComunas();
	    List redes = eivLocal.getTodaslasRedes();
	    List tiposeiv = eivLocal.getTiposEIV();

	    servletRequest.setAttribute("tiposeiv", tiposeiv);
	    servletRequest.setAttribute("redes", redes);
	    servletRequest.setAttribute("comunas", comunas);
	    servletRequest.setAttribute("listaIngenieros", listaIngenieros);
	    return actionMapping.findForward("registrarEIV_fw");

	} catch (LocalizadorServiciosException ex) {
	    ex.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar la p�gina de registro de EIV");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * agregarComuna
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward agregarComuna(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {

	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();
	    String nomComuna = servletRequest.getParameter("nuevaComuna");
	    if (eivLocal.agregarComuna(nomComuna)) {
		servletRequest.setAttribute("mensaje", "La comuna fue ingresada exitosamente");
	    } else {
		servletRequest.setAttribute("mensaje", "La comuna no pudo ser ingresada");
	    }
	    return adminComunas(actionMapping, servletRequest, httpsession);
	} catch (Exception ex) {
	    ex.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo registrar la comuna");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * eliminarComuna
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward eliminarComuna(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();
	    Integer idComuna = Integer.valueOf(servletRequest.getParameter("idComuna"));
	    if (eivLocal.eliminarComuna(idComuna)) {
		servletRequest.setAttribute("mensaje", "La comuna fue eliminada exitosamente");
	    } else {
		servletRequest.setAttribute("mensaje",
			"La comuna no pudo ser eliminada.Verifique que no existe EIV en esta comuna");
	    }
	    return adminComunas(actionMapping, servletRequest, httpsession);
	} catch (Exception ex) {
	    ex.printStackTrace();
	    servletRequest.setAttribute("mensaje", "La comuna no pudo ser eliminada");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * adminComunas
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward adminComunas(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();
	    servletRequest.setAttribute("comunas", eivLocal.getListaComunas());
	    return actionMapping.findForward("adminComunas_fw");
	} catch (Exception ex) {
	    ex.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar la p�gina de b�squeda de documentos");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * buscarDocOficio
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward buscarDocOficio(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {

	try {

	    DocumentoLocal dl = LocalizadorServicios.getInstance().getDocumentoLocal();
	    BusDocsVO bus = new BusDocsVO();
	    // indica q no se debe buscar en los documentos relacionados con las
	    // Iniciativas de Inversi�n
	    bus.setEnIniciativas(false);
	    if (servletRequest.getParameter("tipoBus").equals("1")) {
		bus.setIdTipoDoc(Integer.valueOf(servletRequest.getParameter("idTipoDoc")));
		bus.setCodigoDoc(servletRequest.getParameter("codigoDoc"));
	    }
	    bus.setPalClave(servletRequest.getParameter("palClave"));
	    if (servletRequest.getParameter("tipoBus").equals("2")) {
		bus.setIdSentido(new Integer(1)); // Indica q solamente debe
						  // buscar entre los documentos
						  // entrantes<1>

	    }
	    bus.setMateria(servletRequest.getParameter("materia"));
	    bus.setFechaIni(servletRequest.getParameter("fecha_ini"));
	    bus.setFechaFin(servletRequest.getParameter("fecha_fin"));
	    bus.setEncargado(Integer.valueOf(servletRequest.getParameter("id_usuario")));

	    servletRequest.setAttribute("listadocs", dl.buscarDocumentos(bus));
	    return cargarBusDocOficio(actionMapping, servletRequest, httpsession);
	} catch (LocalizadorServiciosException ex) {
	    ex.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar la p�gina de b�squeda de documentos");
	    return actionMapping.findForward("mensaje_fw");
	} catch (Exception ex) {
	    ex.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar la p�gina de b�squeda de documentos");
	    return actionMapping.findForward("mensaje_fw");
	}

    }

    /**
     * cargarBusDocOficio
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward cargarBusDocOficio(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {

	try {
	    UsuarioLocal usuarioLocal = LocalizadorServicios.getInstance().getUsuarioLocal();
	    DocumentoLocal documentoLocal = LocalizadorServicios.getInstance().getDocumentoLocal();

	    List tiposentrantes = documentoLocal.getTiposDoc(new Integer(1));
	    List tipossalientes = documentoLocal.getTiposDoc(new Integer(2));
	    List listausu = usuarioLocal.getListaNomUsu();

	    servletRequest.setAttribute("tiposentrantes", tiposentrantes);
	    servletRequest.setAttribute("tipossalientes", tipossalientes);
	    servletRequest.setAttribute("listausu", listausu);
	    return actionMapping.findForward("buscarDocOficio_fw");
	} catch (LocalizadorServiciosException ex) {
	    ex.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar la p�gina de b�squeda de oficios");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * agregarTipoEIV
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward agregarTipoEIV(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();
	    if (eivLocal.insertTipoEIV(servletRequest.getParameter("nuevotipo"))) {
		servletRequest.setAttribute("mensaje", "El nuevo tipo de EIV fue ingresado exitosamente");
		return cargaTiposEIV(actionMapping, servletRequest, httpsession);
	    } else {
		servletRequest.setAttribute("mensaje", "No se pudo ingresar el tipo de EIV");
		return cargaTiposEIV(actionMapping, servletRequest, httpsession);

	    }

	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo ingresar el tipo de EIV.");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * eliminarTipoEIV
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward eliminarTipoEIV(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    Integer idTipo = Integer.valueOf(servletRequest.getParameter("id_tipo"));
	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();
	    if (eivLocal.eliminarTipoEIV(idTipo)) {
		servletRequest.setAttribute("mensaje", "El tipo de EIV fue eliminado exitosamente");
		return cargaTiposEIV(actionMapping, servletRequest, httpsession);
	    } else {
		servletRequest.setAttribute("mensaje",
			"No se pudo eliminar el tipo de EIV.Verifique que no existe ning�n EIV de este tipo");
		return actionMapping.findForward("mensaje_fw");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje",
		    "No se pudo eliminar el tipo de EIV.Verifique que no existe ning�n EIV de este tipo");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * aEnvioEmail
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward aEnvioEmail(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {

	try {

	    UsuarioLocal usuarioLocal = LocalizadorServicios.getInstance().getUsuarioLocal();
	    EIVLocal eiv = LocalizadorServicios.getInstance().getEIVLocal();
	    List listaIngenieros = usuarioLocal.getListaIngenieros();

	    String nomEIV = servletRequest.getParameter("nomEIV");
	    Integer id_eiv = Integer.valueOf(servletRequest.getParameter("id_eiv"));

	    DatosSesVO datosses = (DatosSesVO) httpsession.getAttribute("Ses_Usu");
	    UsuarioVO u = usuarioLocal.getDatosUsu(datosses.getId());
	    String rmt = u.getEmail();
	    if (rmt == null) {
		servletRequest.setAttribute("mensaje",
			"La direcci�n de Email de su cuenta de Intranet no ha sido configurada. Registrela y vuelva a intentar el envio de email<br/> Configurela en 'Editar Datos personales' y vuelva a intentarlo'");
		return actionMapping.findForward("mensaje_fw");
	    }

	    servletRequest.setAttribute("rmt", rmt);
	    servletRequest.setAttribute("detEIV", eiv.getDetalleEIV(id_eiv));
	    servletRequest.setAttribute("id_eiv", id_eiv);
	    servletRequest.setAttribute("nomEIV", nomEIV);
	    servletRequest.setAttribute("listaIngenieros", listaIngenieros);
	    return actionMapping.findForward("aEnvioEmail_fw");

	} catch (Exception ex) {
	    ex.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar la p�gina de env�o de email");
	    return actionMapping.findForward("mensaje_fw");
	}

    }

    /**
     * aDespachar
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward aDespachar(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {

	try {
	    String nomEIV = servletRequest.getParameter("nomEIV");
	    Integer id_eiv = Integer.valueOf(servletRequest.getParameter("id_eiv"));

	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();

	    List eventos = eivLocal.getEventosEIV(id_eiv);

	    servletRequest.setAttribute("eventos", eventos);
	    servletRequest.setAttribute("id_eiv", id_eiv);
	    servletRequest.setAttribute("nomEIV", nomEIV);
	    return actionMapping.findForward("aDespacharEIV_fw");
	} catch (Exception e) {
	    servletRequest.setAttribute("mensaje", "No se pudo cargar la p�gina de despachos");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * detEvento
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward detEvento(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    Integer idEvento = Integer.valueOf(servletRequest.getParameter("id_evento"));

	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();
	    EventoVO evento = eivLocal.getDetalleEvento(idEvento);
	    servletRequest.setAttribute("nomEIV", servletRequest.getParameter("nomEIV"));
	    servletRequest.setAttribute("id_eiv", servletRequest.getParameter("id_eiv"));
	    servletRequest.setAttribute("evento", evento);
	    return actionMapping.findForward("detEvento");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "no se pudo cargar el detalle de la bit�cora");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * aCorregirBitacora
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward aCorregirEvento(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	return actionMapping.findForward("aCorregirEvento_fw");
    }

    // -------------------------------------------------------------
    private ActionForward cargaTiposEIV(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {

	try {
	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();
	    List tiposEIV = eivLocal.getTiposEIV();
	    servletRequest.setAttribute("tiposEIV", tiposEIV);

	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudieron cargar los tipos de EIV");
	    return actionMapping.findForward("mensaje_fw");

	}
	return actionMapping.findForward("a_tiposEIV");

    }

    // -----------------------------------------------------

    // -------------------------------------------------------------
    private ActionForward detEIVyDetEventos(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    EIVLocal eivloc = LocalizadorServicios.getInstance().getEIVLocal();
	    Integer ideiv = Integer.valueOf(servletRequest.getParameter("id_eiv"));
	    servletRequest.setAttribute("detalleeiv", eivloc.getDetalleEIV(ideiv));
	    servletRequest.setAttribute("eventos", eivloc.getEventosEIV(ideiv));
	    return actionMapping.findForward("detEIVydetEventos");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar el detalle del EIV");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    // -----------------------------------------------------
    // -------------------------------------------------------------
    private ActionForward detEIVyle(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    EIVLocal eivloc = LocalizadorServicios.getInstance().getEIVLocal();
	    Integer ideiv = Integer.valueOf(servletRequest.getParameter("id_eiv"));
	    servletRequest.setAttribute("detalleeiv", eivloc.getDetalleEIV(ideiv));
	    servletRequest.setAttribute("eventos", eivloc.getEventosEIV(ideiv));
	    return actionMapping.findForward("detEIVyle");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar el detalle del EIV");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    // -----------------------------------------------------
    // -------------------------------------------------------------
    private ActionForward detEIVydetEventyflujos(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    EIVLocal eivloc = LocalizadorServicios.getInstance().getEIVLocal();
	    Integer ideiv = Integer.valueOf(servletRequest.getParameter("id_eiv"));
	    servletRequest.setAttribute("detalleeiv", eivloc.getDetalleEIV(ideiv));
	    servletRequest.setAttribute("eventos", eivloc.getEventosEIV(ideiv));
	    return actionMapping.findForward("detEIVydetEventyflujos");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar el detalle del EIV");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    // -----------------------------------------------------
    // -------------------------------------------------------------
    private ActionForward aReingresar(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();
	    String nomEIV = servletRequest.getParameter("nomEIV");
	    Integer id_eiv = Integer.valueOf(servletRequest.getParameter("id_eiv"));
	    servletRequest.setAttribute("eventos", eivLocal.getEventosEIV(id_eiv));
	    servletRequest.setAttribute("id_eiv", id_eiv);
	    servletRequest.setAttribute("nomEIV", nomEIV);
	    return actionMapping.findForward("aReingresar_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar la pagina de reingreso de documentaci�n");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    // -----------------------------------------------------
    // -------------------------------------------------------------
    private ActionForward aAprobRech(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {

	try {
	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();
	    String nomEIV = servletRequest.getParameter("nomEIV");
	    Integer id_eiv = Integer.valueOf(servletRequest.getParameter("id_eiv"));
	    servletRequest.setAttribute("eventos", eivLocal.getEventosEIV(id_eiv));
	    servletRequest.setAttribute("id_eiv", id_eiv);
	    servletRequest.setAttribute("nomEIV", nomEIV);
	    return actionMapping.findForward("aAprobRech_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar la pagina de aprobaci�n o rechazo de EIV");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    // -----------------------------------------------------
    // -----------------------------------------------------

    // ----------------------------------------------------------

}

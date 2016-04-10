package proyecto_uoct.proyecto.controller;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import proyecto_uoct.common.LocalizadorServicios;
import proyecto_uoct.common.LocalizadorServiciosException;
import proyecto_uoct.common.VO.DatosSesVO;
import proyecto_uoct.documentacion.VO.ClienteVO;
import proyecto_uoct.documentacion.VO.DocumentoInVO;
import proyecto_uoct.documentacion.VO.TipoDocVO;
import proyecto_uoct.documentacion.model.DocumentoLocal;
import proyecto_uoct.proyecto.VO.BusNLOVO;
import proyecto_uoct.proyecto.VO.BusOTVO;
import proyecto_uoct.proyecto.VO.DetalleOTVO;
import proyecto_uoct.proyecto.VO.DetalleProyectoVO;
import proyecto_uoct.proyecto.VO.DocumentodeListaProyVO;
import proyecto_uoct.proyecto.VO.NLOVO;
import proyecto_uoct.proyecto.VO.NLOdeListaVO;
import proyecto_uoct.proyecto.model.ProyectoLocal;
import proyecto_uoct.usuario.model.UsuarioLocal;

public class ProyectoAction extends Action {
    @Override
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) {

	HttpSession httpSession = request.getSession();
	DatosSesVO datoses;
	try {

	    datoses = (DatosSesVO) httpSession.getAttribute("Ses_Usu");
	    datoses.getId();

	} catch (Exception e) {
	    request.setAttribute("mensaje", "La sesión ha caducado");
	    return actionMapping.findForward("mensaje_fw");
	}

	String hacia = null;
	String hacia2 = request.getParameter("hacia");

	if (request.getParameter("hacia") != null) {
	    hacia = request.getParameter("hacia");
	} else {
	    request.setAttribute("mensaje",
		    "Error al cargar la página. Si está subiendo un archivo, verifique que su tamaño no supera los 10 MB ");
	    return actionMapping.findForward("mensaje_fw");
	}

	Integer ses_idusu = datoses.getId();

	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	// -------------------------------
	if (hacia.compareTo("aBusIni") == 0) {
	    return aBusIni(actionMapping, request, httpSession);

	}

	if (hacia.compareTo("buscarIni") == 0) {
	    return buscarIniciativas(actionMapping, request, httpSession);

	}

	if (hacia.compareTo("aEditarProy") == 0) {
	    return aEditarProy(actionMapping, request, httpSession);

	}
	/*
	 * if (hacia.compareTo("aRegDocEntrante") == 0) { return
	 * aRegDocEntrante(actionMapping, request, httpSession);
	 * 
	 * } if (hacia.compareTo("aRegDocSaliente") == 0) { return
	 * aRegDocSaliente(actionMapping, request, httpSession); }
	 */

	if (hacia.compareTo("aRegistrarIniciativa") == 0) {
	    return aRegistrarIniciativa(actionMapping, request);

	}

	if (hacia.compareTo("registrarIniciativa") == 0) {
	    return registrarIniciativa(actionMapping, request);

	}

	if (hacia.compareTo("detalleIni") == 0) {
	    return detalleIniciativa(actionMapping, request);

	}

	if (hacia.compareTo("aBuscarparaEditarIni") == 0) {
	    return aBuscarparaEditarIni(actionMapping, request);
	}

	if (hacia.compareTo("buscarParaEditarIni") == 0) {
	    return buscarParaEditarIni(actionMapping, request);
	}

	if (hacia.compareTo("editarIni") == 0) {
	    return editarIni(actionMapping, request);

	}

	if (hacia.compareTo("actualizarIni") == 0) {
	    return actualizarIni(actionMapping, request);

	}

	if (hacia.compareTo("aFinalizarIni") == 0) {
	    return aFinalizarIni(actionMapping, request);

	}

	if (hacia.compareTo("finalizarIni") == 0) {
	    return finalizarIni(actionMapping, request);

	}

	// ------------------DOCUMENTOS DEL PROYECTO---------------
	if (hacia.compareTo("adminDocsIni") == 0) {
	    return adminDocsIni(actionMapping, request);
	}

	if (hacia.equals("regDocIni")) {
	    return regDocIni(actionMapping, request, actionForm);
	}

	if (hacia.equals("eliminarDoc")) {
	    return eliminarDoc(actionMapping, request);
	}

	if (hacia.equals("descargarDocIni")) {
	    return descargarDocIni(actionMapping, request, response);
	}

	// ------------ O T --------------------
	if (hacia.equals("adminIni")) {
	    return adminIni(actionMapping, request);
	}

	if (hacia.compareTo("aBuscarIniToOT") == 0) {
	    return aBuscarIniToOT(actionMapping, request, httpSession);

	}

	if (hacia.compareTo("buscarIniToOT") == 0) {
	    return buscarIniToOT(actionMapping, request, httpSession);

	}

	if (hacia.equals("aRegOT")) {
	    return aRegOT(actionMapping, request);
	}

	if (hacia.equals("registrarOT")) {
	    return registrarOT(actionMapping, request, actionForm);
	}

	if (hacia.equals("detalleOT")) {
	    return detalleOT(actionMapping, request);
	}

	if (hacia.equals("buscarOT")) {
	    return buscarOT(actionMapping, request);
	}

	if (hacia.equals("actualizarFechas")) {
	    return actualizarFechas(actionMapping, request);
	}

	if (hacia.equals("adminOTs")) {
	    return adminOTs(actionMapping, request);
	}

	if (hacia.equals("editarFechasOT")) {
	    return editarFechasOT(actionMapping, request);
	}

	if (hacia.equals("editarOT")) {
	    return editarOT(actionMapping, request);
	}

	if (hacia.equals("actualizarOT")) {
	    return actualizarOT(actionMapping, request);
	}

	if (hacia.equals("borrarOT")) {
	    return borrarOT(actionMapping, request);
	}

	// -----------NLO ------------------

	if (hacia.equals("abusInitoNLO")) {
	    return abusInitoNLO(actionMapping, request);
	}

	if (hacia.equals("busInitoNLO")) {
	    return busInitoNLO(actionMapping, request);
	}

	if (hacia.equals("aRegNLOIni")) {
	    return aRegNLOdeIni(actionMapping, request);
	}

	if (hacia.equals("aRegNLOOT")) {
	    return aRegNLOOT(actionMapping, request);
	}

	if (hacia.equals("OTsdelProyToRegNLO")) {
	    return otsdelProyToRegNLO(actionMapping, request);
	}

	if (hacia.equals("registrarNLO")) {
	    return registrarNLO(actionMapping, request, actionForm);
	}

	if (hacia.equals("buscarNLO")) {
	    return buscarNLO(actionMapping, request, actionForm);
	}

	if (hacia.equals("editarNLO")) {
	    return editarNLO(actionMapping, request);
	}

	if (hacia.equals("adminNLOdelaIni")) {
	    return adminNLOdelaIni(actionMapping, request);
	}

	if (hacia.equals("adminNLOdelaOT")) {
	    return adminNLOdelaOT(actionMapping, request);
	}

	if (hacia.equals("actualizarNLO")) {
	    return actualizarNLO(actionMapping, request);
	}

	if (hacia.equals("eliminarNLO")) {
	    return eliminarNLO(actionMapping, request);
	}

	request.setAttribute("mensaje", "URL err�nea");
	return actionMapping.findForward("mensaje_fw");

    }

    /**
     * eliminarNLO
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward eliminarNLO(ActionMapping actionMapping, HttpServletRequest request) {
	try {

	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    // ----RECIBE LOS DATOS DE LA NLO
	    Integer idNLO = Integer.valueOf(request.getParameter("idNLO"));

	    ProyectoLocal proyectoLocal = LocalizadorServicios.getInstance().getProyectoLocal();

	    proyectoLocal.eliminarNLO(idNLO);
	    request.setAttribute("mensaje", "La NLO fue eliminada exitosamente");
	    return actionMapping.findForward("mensaje_fw");
	} catch (Exception ex) {
	    ex.printStackTrace();
	    request.setAttribute("mensaje", "No se pudo eliminar la NLO");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * actualizarNLO
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward actualizarNLO(ActionMapping actionMapping, HttpServletRequest request) {
	try {

	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    // ----RECIBE LOS DATOS DE LA NLO

	    Integer idNLO = Integer.valueOf(request.getParameter("idNLO"));
	    String nota = request.getParameter("nota");

	    NLOVO nlo = new NLOVO();
	    nlo.setId(idNLO);
	    nlo.setStr(nota);
	    nlo.setFechaNLO(sdf.parse(request.getParameter("fechaNLO")));

	    ProyectoLocal proyectoLocal = LocalizadorServicios.getInstance().getProyectoLocal();

	    proyectoLocal.actualizarNLO(nlo);
	    request.setAttribute("mensaje", "La NLO fue actualizada exitosamente");
	    return actionMapping.findForward("mensaje_fw");
	} catch (Exception ex) {
	    ex.printStackTrace();
	    request.setAttribute("mensaje", "No se pudo actualizar la NLO");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * adminNLOdelaOT
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward adminNLOdelaOT(ActionMapping actionMapping, HttpServletRequest request) {
	try {
	    ProyectoLocal proyLoc = LocalizadorServicios.getInstance().getProyectoLocal();
	    DetalleOTVO detot = proyLoc.getDetalleOT(Integer.valueOf(request.getParameter("idOT")));

	    request.setAttribute("detOT", detot);
	    return actionMapping.findForward("adminNLOsOT_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("mensaje", "no se pudo cargar la pag. de administraci�n de NLO");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * adminNLOdelaIni
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward adminNLOdelaIni(ActionMapping actionMapping, HttpServletRequest request) {
	try {
	    ProyectoLocal proyLoc = LocalizadorServicios.getInstance().getProyectoLocal();
	    DetalleProyectoVO det = proyLoc.getDetalleIniciativa(Integer.valueOf(request.getParameter("idProy")));

	    request.setAttribute("detalleProy", det);
	    return actionMapping.findForward("adminNLOs_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("mensaje", "no se pudo cargar el administraci�n de NLO");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * editarNLO
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward editarNLO(ActionMapping actionMapping, HttpServletRequest request) {
	try {
	    ProyectoLocal p = LocalizadorServicios.getInstance().getProyectoLocal();

	    Integer idNLO = Integer.valueOf(request.getParameter("idNLO"));
	    DetalleProyectoVO detProy = null;

	    if (request.getParameter("idProy") != null) {
		Integer idProy = Integer.valueOf(request.getParameter("idProy"));
		detProy = p.getDetalleIniciativa(idProy);
		Iterator i = detProy.getNLOs().iterator();
		while (i.hasNext()) {
		    NLOdeListaVO n = (NLOdeListaVO) i.next();
		    if (n.getId().equals(idNLO)) {
			request.setAttribute("detnlo", n);
			continue;
		    }
		}
	    } else {
		Integer idOT = Integer.valueOf(request.getParameter("idOT"));
		detProy = p.getDetalleIniDesdeOT(idOT);
		DetalleOTVO ot = p.getDetalleOT(idOT);

		Iterator i = ot.getNLOs().iterator();
		while (i.hasNext()) {
		    NLOVO nvo = (NLOVO) i.next();
		    if (nvo.getId().equals(idNLO)) {
			NLOdeListaVO n = new NLOdeListaVO();
			n.setId(nvo.getId());
			n.setStr(nvo.getStr());
			n.setFechaNLO(nvo.getFechaNLO());
			request.setAttribute("detnlo", n);
			continue;
		    }
		}

		request.setAttribute("ot", ot);
	    }

	    request.setAttribute("detalleProy", detProy);

	    return actionMapping.findForward("editarNLO_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("mensaje", "No se pudo cargar la pag. de edici�n de la NLO");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * borrarOT
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward borrarOT(ActionMapping actionMapping, HttpServletRequest request) {
	try {
	    ProyectoLocal p = LocalizadorServicios.getInstance().getProyectoLocal();
	    p.borrarOT(Integer.valueOf(request.getParameter("idOT")));
	    request.setAttribute("mensaje", "La OT fue eliminada exitosamente");
	    request.setAttribute("idProy", request.getAttribute("idProy"));
	    return detalleIniciativa(actionMapping, request);

	} catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("mensaje", "La OT no pudo ser eliminada");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * actualizarOT
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward actualizarOT(ActionMapping actionMapping, HttpServletRequest request) {
	try {

	    // ----RECIBE LOS DATOS DE LA OT

	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	    DetalleOTVO ot = new DetalleOTVO();
	    ot.setIdProy(Integer.valueOf(request.getParameter("idProy")));
	    ot.setIdOT(Integer.valueOf(request.getParameter("idOT")));
	    ot.setIdEstado(Integer.valueOf(request.getParameter("idEstado")));

	    ot.setFechaOT(sdf.parse(request.getParameter("fechaOT")));
	    ot.setIdTipoOT(new Integer(1));
	    ClienteVO c = new ClienteVO();
	    c.setIdCli(Integer.valueOf(request.getParameter("idCli")));
	    ot.setCli(c);

	    String[] respons = request.getParameterValues("list1");
	    List responsables = new LinkedList();
	    for (int i = 0; i < respons.length; i++) {
		responsables.add(Integer.valueOf(respons[i]));
	    }
	    ot.setEncargados(responsables);

	    ot.setVencimiento(sdf.parse(request.getParameter("fecha_pejec")));
	    ot.setPlazo(Integer.valueOf(request.getParameter("plazo")));

	    if (!request.getParameter("ep").equals("")) {
		ot.setEP(request.getParameter("ep"));
	    }

	    ProyectoLocal proyectoLocal = LocalizadorServicios.getInstance().getProyectoLocal();

	    proyectoLocal.actualizarOT(ot);
	    request.setAttribute("idOT", ot.getIdOT());
	    request.setAttribute("mensaje", "La OT fue actualizada exitosamente");
	    return detalleOT(actionMapping, request);
	} catch (Exception ex) {
	    ex.printStackTrace();
	    request.setAttribute("mensaje", "No se pudo actualizar la OT");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * finalizarIni
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward finalizarIni(ActionMapping actionMapping, HttpServletRequest request) {
	try {
	    ProyectoLocal p = LocalizadorServicios.getInstance().getProyectoLocal();
	    DetalleProyectoVO det = p.getDetalleIniciativa(Integer.valueOf(request.getParameter("idProy")));
	    det.setFechaFin(request.getParameter("fechaFin"));

	    p.finalizarIni(det);

	    request.setAttribute("mensaje",
		    "La iniciativa ha sido cerrada con fecha:" + request.getParameter("fechaFin"));
	    return actionMapping.findForward("mensaje_fw");
	} catch (Exception e) {
	    request.setAttribute("mensaje", "No se pudo cargar la pag. de finalizaci�n de la Iniciativa ");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * aFinalizarIni
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward aFinalizarIni(ActionMapping actionMapping, HttpServletRequest request) {
	try {
	    ProyectoLocal p = LocalizadorServicios.getInstance().getProyectoLocal();
	    DetalleProyectoVO det = p.getDetalleIniciativa(Integer.valueOf(request.getParameter("idProy")));
	    request.setAttribute("nomProy", det.getNomProy());
	    request.setAttribute("idProy", Integer.valueOf(request.getParameter("idProy")));
	    return actionMapping.findForward("finalizarIni_fw");
	} catch (Exception e) {
	    request.setAttribute("mensaje", "No se pudo cargar la pag. de finalizaci�n de la Iniciativa ");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * editarOT
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward editarOT(ActionMapping actionMapping, HttpServletRequest request) {

	try {
	    Integer id_OT = Integer.valueOf(request.getParameter("idOT"));
	    ProyectoLocal proyectoLocal = null;

	    proyectoLocal = LocalizadorServicios.getInstance().getProyectoLocal();

	    DetalleProyectoVO det = proyectoLocal.getDetalleIniciativa(Integer.valueOf(request.getParameter("idProy")));

	    DocumentoLocal docLoc = LocalizadorServicios.getInstance().getDocumentoLocal();

	    request.setAttribute("estados", proyectoLocal.getEstadosOT());
	    request.setAttribute("detalleProy", det);

	    request.setAttribute("detalleOT", proyectoLocal.getDetalleOT(id_OT));
	    return actionMapping.findForward("editarOT_fw");
	} catch (Exception ex) {
	    request.setAttribute("mensaje", "No se pudo cargar la pag. de edici�n de la OT");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * adminOTs
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward adminOTs(ActionMapping actionMapping, HttpServletRequest request) {
	try {
	    ProyectoLocal p = LocalizadorServicios.getInstance().getProyectoLocal();

	    request.setAttribute("detalleProy",
		    p.getDetalleIniciativa(Integer.valueOf(request.getParameter("idProy"))));
	    return actionMapping.findForward("adminOTs_fw");
	} catch (Exception e) {
	    request.setAttribute("mensaje", "No se pudo cargar la pag. de administraci�n de OTs ");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * actualizarFechas
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward actualizarFechas(ActionMapping actionMapping, HttpServletRequest request) {
	try {
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    ProyectoLocal proyLoc = LocalizadorServicios.getInstance().getProyectoLocal();
	    DetalleOTVO det = new DetalleOTVO();
	    det.setIdOT(Integer.valueOf(request.getParameter("idOT")));
	    det.setFechaOT(sdf.parse(request.getParameter("fecha_ot")));
	    det.setVencimiento(sdf.parse(request.getParameter("fecha_pejec")));
	    if (!request.getParameter("fecha_susc").equals("")) {
		det.setSuscrip(sdf.parse(request.getParameter("fecha_susc")));
	    }
	    if (!request.getParameter("fecha_tconc").equals("")) {
		det.setFinTareas(sdf.parse(request.getParameter("fecha_tconc")));
	    }

	    if (!request.getParameter("fecha_apro").equals("")) {
		det.setAprobacion(sdf.parse(request.getParameter("fecha_apro")));
	    }

	    proyLoc.actualizarFechasOT(det);
	    request.setAttribute("mensaje", "las fechas de la OT fueron actualizadas exitosamente");
	    return actionMapping.findForward("mensaje_fw");
	} catch (Exception e) {
	    request.setAttribute("mensaje", "No se pudieron actualizar las fechas de la Ot ");
	    return actionMapping.findForward("mensaje_fw");
	}

    }

    /**
     * buscarNLO
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @param actionForm
     *            ActionForm
     * @return ActionForward
     */
    private ActionForward buscarNLO(ActionMapping actionMapping, HttpServletRequest request, ActionForm actionForm) {
	try {
	    ProyectoLocal proyectoLocal = LocalizadorServicios.getInstance().getProyectoLocal();

	    if (request.getParameter("buscar") != null) {
		BusNLOVO bus = new BusNLOVO();

		if (!request.getParameter("idnlo").equals("")) {

		    bus.setIdNLO(Integer.valueOf(request.getParameter("idnlo")));
		}

		bus.setPalClave(request.getParameter("palClave"));
		bus.setFechaNLO(request.getParameter("fechaNLO"));

		request.setAttribute("nlos", proyectoLocal.buscarNLO(bus));
	    }

	    return actionMapping.findForward("buscarNLO_fw");
	} catch (Exception ex) {
	    ex.printStackTrace();
	    request.setAttribute("mensaje", "No se pudo cargar la busqueda de nlo");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * buscarOT
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward buscarOT(ActionMapping actionMapping, HttpServletRequest request) {
	try {
	    ProyectoLocal proyectoLocal = LocalizadorServicios.getInstance().getProyectoLocal();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    if (request.getParameter("buscar") != null) {
		BusOTVO busOT = new BusOTVO();

		/*
		 * if (!request.getParameter("idOT").equals("")) {
		 * busOT.setIdOT(Integer.valueOf(request.getParameter(
		 * "idOT"))); }
		 */

		busOT.setPalClave(request.getParameter("palClave"));
		if (!request.getParameter("idEstado").equals("")) {
		    busOT.setIdEstado(Integer.valueOf(request.getParameter("idEstado")));
		}

		String lafecha = request.getParameter("fechaVenc");
		// lafecha = (sdf.format(sdf.parse(lafecha)));
		busOT.setFechaVenc(lafecha);

		request.setAttribute("ot", proyectoLocal.buscarOT(busOT));
	    }
	    request.setAttribute("est", proyectoLocal.getEstadosOT());
	    return actionMapping.findForward("buscarOT_fw");
	} catch (Exception ex) {
	    request.setAttribute("mensaje", "No se pudo cargar la busqueda de OT");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * detalleOT
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward detalleOT(ActionMapping actionMapping, HttpServletRequest request) {
	try {
	    Integer id_OT = Integer.valueOf(request.getParameter("idOT"));
	    ProyectoLocal proyectoLocal = LocalizadorServicios.getInstance().getProyectoLocal();

	    UsuarioLocal usuLoc = LocalizadorServicios.getInstance().getUsuarioLocal();

	    DetalleOTVO ot = proyectoLocal.getDetalleOT(id_OT);
	    request.setAttribute("detalleOT", ot);

	    HttpSession httpSession = request.getSession();
	    DatosSesVO datoses;
	    datoses = (DatosSesVO) httpSession.getAttribute("Ses_Usu");

	    request.setAttribute("idSes", datoses.getId());

	    Integer regnlo = null;
	    if (usuLoc.tienePermiso(datoses.getIdPerfil(), "registrar NLO")) {
		regnlo = new Integer(1);
	    } else {
		regnlo = new Integer(0);
	    }

	    Integer editarFechasOT = null;
	    if (usuLoc.tienePermiso(datoses.getIdPerfil(), "Editar Fechas OT")) {
		editarFechasOT = new Integer(1);
	    } else {
		editarFechasOT = new Integer(0);
	    }

	    request.setAttribute("editarFechasOT", editarFechasOT);
	    request.setAttribute("regnlo", regnlo);
	    request.setAttribute("detproy", proyectoLocal.getDetalleIniciativa(ot.getIdProy()));

	    return actionMapping.findForward("detalleOT_fw");
	} catch (Exception ex) {
	    request.setAttribute("mensaje", "No se pudo cargar el detalle de la OT");
	    return actionMapping.findForward("mensaje_fw");
	}

    }

    /**
     * otsdelProyToRegNLO
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward otsdelProyToRegNLO(ActionMapping actionMapping, HttpServletRequest request) {
	try {
	    ProyectoLocal proyLoc = LocalizadorServicios.getInstance().getProyectoLocal();
	    DetalleProyectoVO det = proyLoc.getDetalleIniciativa(Integer.valueOf(request.getParameter("idProy")));
	    request.setAttribute("detalleProy", det);
	    return actionMapping.findForward("otsDelProy_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("mensaje", "no se pudo cargar la pag. de lista de OT");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * aRegNLOOT
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward aRegNLOOT(ActionMapping actionMapping, HttpServletRequest request) {
	try {
	    ProyectoLocal proyLoc = LocalizadorServicios.getInstance().getProyectoLocal();

	    Integer idOT = Integer.valueOf(request.getParameter("idOT"));
	    DocumentoLocal docLoc = LocalizadorServicios.getInstance().getDocumentoLocal();

	    // lista los tipos de docs que tienen la palabra LO,
	    // salientes,activos
	    List tiposDoc = docLoc.getTiposDocxPal("LO", new Integer(2), new Integer(1));

	    Iterator i = tiposDoc.iterator();
	    while (i.hasNext()) {
		TipoDocVO t = (TipoDocVO) i.next();
		t.setTipo(t.getTipo() + "(saliente)");
	    }

	    // lista los tipos que tienen la palabra LO, entrantes, activos
	    List tiposDocIn = docLoc.getTiposDocxPal("LO", new Integer(1), new Integer(1));

	    Iterator ien = tiposDocIn.iterator();
	    while (ien.hasNext()) {
		TipoDocVO t = (TipoDocVO) ien.next();
		t.setTipo(t.getTipo() + "(entrante)");
	    }

	    Iterator ii = tiposDocIn.iterator();
	    while (ii.hasNext()) {
		tiposDoc.add(ii.next());
	    }
	    request.setAttribute("detOT", proyLoc.getDetalleOT(idOT));
	    request.setAttribute("tiposDoc", tiposDoc);
	    request.setAttribute("detalleProy", proyLoc.getDetalleIniDesdeOT(idOT));
	    return actionMapping.findForward("regNLOOT_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("mensaje", "no se pudo cargar la pag. de registro de OT");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * registrarNLOIni
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @param actionForm
     *            ActionForm
     * @return ActionForward
     */
    private ActionForward registrarNLO(ActionMapping actionMapping, HttpServletRequest request, ActionForm actionForm) {
	try {

	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    // ----RECIBE LOS DATOS DE LA NLO

	    Integer idIni = Integer.valueOf(request.getParameter("idProy"));

	    String nota = request.getParameter("nota");

	    NLOVO nlo = new NLOVO();
	    nlo.setIdProy(idIni);
	    nlo.setStr(nota);
	    nlo.setFechaNLO(sdf.parse(request.getParameter("fechaDoc")));

	    // ------RECIBE LOS DATOS DEL DOCUMENTO---------

	    Integer idTipoDoc = Integer.valueOf(request.getParameter("idTipoDoc"));
	    String codDoc = request.getParameter("codDoc");
	    Integer idCli = Integer.valueOf(request.getParameter("idCli"));
	    String materia = request.getParameter("materia");
	    String fechaIn = request.getParameter("fecha_IO");
	    String fechaDoc = request.getParameter("fechaDoc");

	    String[] respons = request.getParameterValues("list1");
	    List responsables = new LinkedList();
	    for (int i = 0; i < respons.length; i++) {
		responsables.add(Integer.valueOf(respons[i]));
	    }

	    // ++
	    Integer[] idDocAnt = null;
	    if (request.getParameterValues("listaRel") != null) {
		String[] idAnt = request.getParameterValues("listaRel");
		idDocAnt = new Integer[idAnt.length];
		for (int i = 0; i < idAnt.length; i++) {
		    idDocAnt[i] = Integer.valueOf(idAnt[i]);
		}
	    }

	    String resumen = request.getParameter("descrip");

	    AgregarDocProy_fb docAF = (AgregarDocProy_fb) actionForm; // Se
								      // llama
								      // al bean
								      // que
								      // recibe
								      // los
								      // Documentos
	    FormFile eldoc = docAF.getEldoc();
	    FormFile eldoc1 = docAF.getEldoc1();
	    FormFile eldoc2 = docAF.getEldoc2();

	    docAF.reset();
	    DocumentoInVO newDoc = new DocumentoInVO();

	    newDoc.setIdTipoDoc(idTipoDoc);
	    newDoc.setCodDoc(codDoc);
	    newDoc.setIdRemitente(idCli);
	    newDoc.setMat(materia);
	    newDoc.setFechaIn(fechaIn);
	    newDoc.setFechaDoc(fechaDoc);
	    newDoc.setAnt(idDocAnt);

	    Integer[] resps = new Integer[respons.length];
	    for (int i = 0; i < respons.length; i++) {
		resps[i] = Integer.valueOf(respons[i]);
	    }
	    newDoc.setResponsable(resps);
	    newDoc.setResumen(resumen);

	    newDoc.setFile(eldoc);
	    newDoc.setFile1(eldoc1);
	    newDoc.setFile2(eldoc2);

	    ProyectoLocal proyectoLocal = LocalizadorServicios.getInstance().getProyectoLocal();

	    nlo.setDocumento(newDoc);

	    if (request.getParameter("idOT") != null) {
		DetalleOTVO ot = new DetalleOTVO();
		ot.setIdOT(Integer.valueOf(request.getParameter("idOT")));
		nlo.setOT(ot);
	    }

	    proyectoLocal.regNLO(nlo);
	    request.setAttribute("mensaje", "La NLO fue registrada exitosamente");
	    if (request.getParameter("regNLOOT_submit") != null) {
		return editarFechasOT(actionMapping, request);
	    }
	    return actionMapping.findForward("mensaje_fw");
	} catch (Exception ex) {
	    ex.printStackTrace();
	    request.setAttribute("mensaje", "No se pudo registrar la NLO");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * editarFechasOT
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward editarFechasOT(ActionMapping actionMapping, HttpServletRequest request) {

	try {
	    Integer id_OT = Integer.valueOf(request.getParameter("idOT"));
	    ProyectoLocal proyectoLocal = LocalizadorServicios.getInstance().getProyectoLocal();

	    DetalleOTVO ot = proyectoLocal.getDetalleOT(id_OT);
	    request.setAttribute("detalleOT", ot);
	    return actionMapping.findForward("edFechasOT_fw");
	} catch (Exception ex) {
	    request.setAttribute("mensaje", "No se pudo cargar el detalle de la OT");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * aRegNLOdeIni
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward aRegNLOdeIni(ActionMapping actionMapping, HttpServletRequest request) {
	try {
	    ProyectoLocal proyLoc = LocalizadorServicios.getInstance().getProyectoLocal();
	    DetalleProyectoVO det = proyLoc.getDetalleIniciativa(Integer.valueOf(request.getParameter("idProy")));

	    DocumentoLocal docLoc = LocalizadorServicios.getInstance().getDocumentoLocal();

	    // lista los tipos de docs que tienen la palabra LO,
	    // salientes,activos
	    List tiposDoc = docLoc.getTiposDocxPal("LO", new Integer(2), new Integer(1));

	    Iterator i = tiposDoc.iterator();
	    while (i.hasNext()) {
		TipoDocVO t = (TipoDocVO) i.next();
		t.setTipo(t.getTipo() + "(saliente)");
	    }

	    // lista los tipos que tienen la palabra LO, entrantes, activos
	    List tiposDocIn = docLoc.getTiposDocxPal("LO", new Integer(1), new Integer(1));

	    Iterator ien = tiposDocIn.iterator();
	    while (ien.hasNext()) {
		TipoDocVO t = (TipoDocVO) ien.next();
		t.setTipo(t.getTipo() + "(entrante)");
	    }

	    Iterator ii = tiposDocIn.iterator();
	    while (ii.hasNext()) {
		tiposDoc.add(ii.next());
	    }

	    request.setAttribute("tiposDoc", tiposDoc);
	    request.setAttribute("detalleProy", det);
	    return actionMapping.findForward("regNLOIni_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("mensaje", "no se pudo cargar la pag. de registro de NLO");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * busInitoNLO
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward busInitoNLO(ActionMapping actionMapping, HttpServletRequest request) {
	try {
	    ProyectoLocal proyLoc = LocalizadorServicios.getInstance().getProyectoLocal();
	    HttpSession httpSession = request.getSession();
	    DatosSesVO datoses = (DatosSesVO) httpSession.getAttribute("Ses_Usu");
	    Date hoy = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
	    Integer ano = Integer.valueOf(request.getParameter("ano"));
	    request.setAttribute("proyectos", proyLoc.getProyectosxAno(ano, true, datoses.getId()));
	    return actionMapping.findForward("busInitoNLO_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("mensaje", "no se pudo cargar la pag. de b�squeda de Iniciativas");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * abusInitoNLO
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward abusInitoNLO(ActionMapping actionMapping, HttpServletRequest request) {
	try {
	    ProyectoLocal proyLoc = LocalizadorServicios.getInstance().getProyectoLocal();
	    HttpSession httpSession = request.getSession();
	    DatosSesVO datoses = (DatosSesVO) httpSession.getAttribute("Ses_Usu");

	    Date hoy = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
	    Integer ano = Integer.valueOf(sdf.format(hoy));
	    request.setAttribute("proyectos", proyLoc.getProyectosxAno(ano, true, datoses.getId()));
	    return actionMapping.findForward("busInitoNLO_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("mensaje", "no se pudo cargar la pag. de b�squeda de Iniciativas");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * registrarOT
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @param actionForm
     *            ActionForm
     * @return ActionForward
     */
    private ActionForward registrarOT(ActionMapping actionMapping, HttpServletRequest request, ActionForm actionForm) {
	try {

	    // ----RECIBE LOS DATOS DE LA OT
	    Integer idIni = Integer.valueOf(request.getParameter("idProy"));

	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	    DetalleOTVO ot = new DetalleOTVO();
	    ot.setIdProy(idIni);
	    ot.setIdEstado(new Integer(1));
	    ot.setNomOT(request.getParameter("codDoc"));
	    ot.setFechaOT(sdf.parse(request.getParameter("fechaOT")));
	    ot.setIdTipoOT(new Integer(1));
	    ClienteVO c = new ClienteVO();
	    c.setIdCli(Integer.valueOf(request.getParameter("idCli")));
	    ot.setCli(c);

	    String[] respons = request.getParameterValues("list1");
	    List responsables = new LinkedList();
	    for (int i = 0; i < respons.length; i++) {
		responsables.add(Integer.valueOf(respons[i]));
	    }
	    ot.setEncargados(responsables);

	    if (!request.getParameter("fecha_pejec").equals("")) {
		ot.setVencimiento(sdf.parse(request.getParameter("fecha_pejec")));
	    }
	    ot.setPlazo(Integer.valueOf(request.getParameter("plazo")));

	    if (!request.getParameter("ep").equals("")) {
		ot.setEP(request.getParameter("ep"));
	    }

	    // ------RECIBE LOS DATOS DEL DOCUMENTO---------

	    Integer idTipoDoc = Integer.valueOf(request.getParameter("idTipoDoc"));
	    String codDoc = request.getParameter("codDoc");
	    Integer idCli = Integer.valueOf(request.getParameter("idCli"));
	    String materia = request.getParameter("materia");
	    String fechaIn = request.getParameter("fecha_IO");
	    String fechaDoc = request.getParameter("fechaOT");

	    // ++
	    Integer[] idDocAnt = null;
	    if (request.getParameterValues("listaRel") != null) {
		String[] idAnt = request.getParameterValues("listaRel");
		idDocAnt = new Integer[idAnt.length];
		for (int i = 0; i < idAnt.length; i++) {
		    idDocAnt[i] = Integer.valueOf(idAnt[i]);
		}
	    }

	    String resumen = request.getParameter("descrip");

	    AgregarDocProy_fb docAF = (AgregarDocProy_fb) actionForm; // Se
								      // llama
								      // al bean
								      // que
								      // recibe
								      // los
								      // Documentos
	    FormFile eldoc = docAF.getEldoc();
	    FormFile eldoc1 = docAF.getEldoc1();
	    FormFile eldoc2 = docAF.getEldoc2();

	    docAF.reset();
	    DocumentoInVO newDoc = new DocumentoInVO();

	    newDoc.setIdTipoDoc(idTipoDoc);
	    newDoc.setCodDoc(codDoc);
	    newDoc.setIdRemitente(idCli);
	    newDoc.setMat(materia);
	    newDoc.setFechaIn(fechaIn);
	    newDoc.setFechaDoc(fechaDoc);
	    newDoc.setAnt(idDocAnt);

	    Integer[] resps = new Integer[respons.length];
	    for (int i = 0; i < respons.length; i++) {
		resps[i] = Integer.valueOf(respons[i]);
	    }
	    newDoc.setResponsable(resps);
	    newDoc.setResumen(resumen);

	    newDoc.setFile(eldoc);
	    newDoc.setFile1(eldoc1);
	    newDoc.setFile2(eldoc2);

	    ot.setDocumento(newDoc);

	    ProyectoLocal proyectoLocal = LocalizadorServicios.getInstance().getProyectoLocal();

	    proyectoLocal.regOT(ot);
	    request.setAttribute("mensaje", "La OT fue registrada exitosamente");
	    return detalleIniciativa(actionMapping, request);
	} catch (Exception ex) {
	    ex.printStackTrace();
	    request.setAttribute("mensaje", "No se pudo registrar la OT");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * buscarIniciativas_pop
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @param httpSession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward buscarIniToOT(ActionMapping actionMapping, HttpServletRequest request,
	    HttpSession httpSession) {
	try {
	    Integer ano = Integer.valueOf(request.getParameter("ano"));

	    httpSession = request.getSession();
	    DatosSesVO datoses = (DatosSesVO) httpSession.getAttribute("Ses_Usu");

	    ProyectoLocal proyectoLocal = LocalizadorServicios.getInstance().getProyectoLocal();

	    List proyectos = proyectoLocal.getProyectosxAno(ano, true, datoses.getId());
	    request.setAttribute("proyectos", proyectos);
	    return actionMapping.findForward("busIniToOT_fw");

	} catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("mensaje", "no se pudo realizar la b�squeda");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * aBusIni_pop
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @param httpSession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward aBuscarIniToOT(ActionMapping actionMapping, HttpServletRequest request,
	    HttpSession httpSession) {
	try {
	    ProyectoLocal proyLoc = LocalizadorServicios.getInstance().getProyectoLocal();
	    httpSession = request.getSession();
	    DatosSesVO datoses = (DatosSesVO) httpSession.getAttribute("Ses_Usu");

	    Date hoy = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
	    Integer ano = Integer.valueOf(sdf.format(hoy));
	    request.setAttribute("proyectos", proyLoc.getProyectosxAno(ano, true, datoses.getId()));
	    return actionMapping.findForward("busIniToOT_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("mensaje", "no se pudo cargar la pag. de b�squeda de Iniciativas");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * aRegOT
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward aRegOT(ActionMapping actionMapping, HttpServletRequest request) {
	try {
	    ProyectoLocal proyLoc = LocalizadorServicios.getInstance().getProyectoLocal();
	    DetalleProyectoVO det = proyLoc.getDetalleIniciativa(Integer.valueOf(request.getParameter("idProy")));

	    DocumentoLocal docLoc = LocalizadorServicios.getInstance().getDocumentoLocal();

	    request.setAttribute("tiposDocOut", docLoc.getTiposDocxPal("OT", new Integer(2), new Integer(1)));
	    request.setAttribute("tiposDocIn", docLoc.getTiposDocxPal("OT", new Integer(1), new Integer(1)));

	    request.setAttribute("detalleProy", det);
	    return actionMapping.findForward("regOT_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("mensaje", "no se pudo cargar la pag. de registro de OT");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * adminIni
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward adminIni(ActionMapping actionMapping, HttpServletRequest request) {
	try {
	    ProyectoLocal proyLoc = LocalizadorServicios.getInstance().getProyectoLocal();
	    DetalleProyectoVO det = proyLoc.getDetalleIniciativa(Integer.valueOf(request.getParameter("idProy")));

	    String fa = det.getFondosAnuales();
	    if (fa != null) {
		fa = fa.replaceAll("\r\n", "</br>");
		det.setFondosAnuales(fa);
	    }

	    request.setAttribute("detalleProy", det);
	    return actionMapping.findForward("adminIni_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("mensaje", "no se pudo cargar la pag. de administraci�n de la iniciativa");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * actualizarIni
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward actualizarIni(ActionMapping actionMapping, HttpServletRequest request) {
	try {
	    ProyectoLocal proyLoc = LocalizadorServicios.getInstance().getProyectoLocal();
	    DetalleProyectoVO detProy = new DetalleProyectoVO();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    detProy.setIdProy(Integer.valueOf(request.getParameter("idIni")));
	    detProy.setNomProy(request.getParameter("nom_proy"));
	    detProy.setFechaProy(sdf.parse(request.getParameter("fechaini")));
	    detProy.setIdCliente(new Integer(request.getParameter("idCli")));
	    detProy.setDescripcion(request.getParameter("descrip"));
	    detProy.setIdEncargado(new Integer(request.getParameter("id_encargado")));
	    detProy.setFondosAnuales(request.getParameter("fanual"));
	    String[] encs = request.getParameterValues("list1");

	    List id_ens = new LinkedList();

	    for (int i = 0; i < encs.length; i++) {
		Integer id = new Integer(encs[i]);
		id_ens.add(id);
	    }
	    detProy.setEquipo(id_ens);
	    detProy.setFondosAnuales(request.getParameter("fanual"));
	    if (!request.getParameter("ftotales").equals("")) {
		detProy.setFondosTotales(new Integer(request.getParameter("ftotales")));
	    }

	    detProy.setIsActivo(Integer.valueOf("1"));

	    proyLoc.actualizarDatosIni(detProy);
	    request.setAttribute("mensaje", "La Iniciativa de Inversi�n fue actualizada exitosamente");

	    request.setAttribute("idProy", Integer.valueOf(request.getParameter("idIni")));
	    return detalleIniciativa(actionMapping, request);
	} catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("mensaje", "No se pudo actualizar la Iniciativa de Inversi�n");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * editarIni
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward editarIni(ActionMapping actionMapping, HttpServletRequest request) {
	try {
	    ProyectoLocal proyLoc = LocalizadorServicios.getInstance().getProyectoLocal();
	    DetalleProyectoVO det = proyLoc.getDetalleIniciativa(Integer.valueOf(request.getParameter("idProy")));
	    UsuarioLocal usuarioLocal = LocalizadorServicios.getInstance().getUsuarioLocal();

	    request.setAttribute("listausu", usuarioLocal.getUsuariosActivos());
	    request.setAttribute("detalleProy", det);
	    return actionMapping.findForward("edtiarDatosIni_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("mensaje", "no se pudo cargar la pag de edici�n de la iniciativa");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * descargarDocIni
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return ActionForward
     */
    private ActionForward descargarDocIni(ActionMapping actionMapping, HttpServletRequest request,
	    HttpServletResponse response) {
	try {

	    ProyectoLocal proyLoc = LocalizadorServicios.getInstance().getProyectoLocal();
	    Integer idArchivo = Integer.valueOf(request.getParameter("idDoc"));

	    DocumentodeListaProyVO a = proyLoc.descargarDocumento(idArchivo);
	    byte[] b = a.getArchivo();

	    ByteArrayOutputStream output = new ByteArrayOutputStream();
	    output.write(b, 0, b.length);

	    response.setContentType("application/download");
	    response.setHeader("content-disposition", "attachment; fileName=\"" + a.getStr() + "\"");
	    response.setContentLength(output.size());

	    // Escribir el archivo en el response
	    OutputStream out = response.getOutputStream();
	    output.writeTo(out);
	    out.flush();
	    out.close();

	} catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("mensaje", "No se pudo descargar el archivo");
	    return actionMapping.findForward("mensaje_fw");

	}
	return null;
    }

    /**
     * eliminarDoc
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward eliminarDoc(ActionMapping actionMapping, HttpServletRequest request) {
	try {
	    Integer id_proy = Integer.valueOf(request.getParameter("idProy"));

	    ProyectoLocal proyectoLocal = LocalizadorServicios.getInstance().getProyectoLocal();

	    Integer idDoc = Integer.valueOf(request.getParameter("idDoc"));

	    if (proyectoLocal.eliminaDocIni(idDoc)) {
		request.setAttribute("mensaje", "El documento fue eliminado exitosamente");
	    } else {
		request.setAttribute("mensaje", "No se pudo eliminar el documento");
	    }
	    request.setAttribute("nomProy", request.getParameter("nomProy"));

	    return adminDocsIni(actionMapping, request);
	} catch (Exception ex) {
	    ex.printStackTrace();
	    request.setAttribute("mensaje", "No se pudo eliminar el documento");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * regDocIni
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @param actionForm
     *            ActionForm
     * @return ActionForward
     */
    private ActionForward regDocIni(ActionMapping actionMapping, HttpServletRequest request, ActionForm actionForm) {

	try {
	    Integer id_proy = Integer.valueOf(request.getParameter("idProy"));
	    AgregarDocProy_fb af = (AgregarDocProy_fb) actionForm;

	    FormFile doc = af.getDocProy();
	    DocumentodeListaProyVO nuevodoc = new DocumentodeListaProyVO();

	    nuevodoc.setStr(doc.getFileName());
	    nuevodoc.setIdProy(id_proy);

	    nuevodoc.setElDoc(doc);
	    ProyectoLocal proyectoLocal = LocalizadorServicios.getInstance().getProyectoLocal();

	    if (proyectoLocal.regDocIni(nuevodoc)) {
		request.setAttribute("mensaje", "El documento fue Ingresado");
	    } else {
		request.setAttribute("mensaje", "No se pudo registrar el documento");
	    }
	    request.setAttribute("nomProy", request.getParameter("nomProy"));

	    return adminDocsIni(actionMapping, request);
	} catch (Exception ex) {
	    ex.printStackTrace();
	    request.setAttribute("mensaje", "No se pudo registrar el documento");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * adminDocsIni
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward adminDocsIni(ActionMapping actionMapping, HttpServletRequest request) {
	try {
	    Integer idIni = Integer.valueOf(request.getParameter("idProy"));

	    ProyectoLocal proyectoLocal = LocalizadorServicios.getInstance().getProyectoLocal();

	    List docs = proyectoLocal.getDocsIniciativa(idIni);

	    request.setAttribute("docs", docs);
	    request.setAttribute("idProy", request.getParameter("idProy"));
	    request.setAttribute("nomProy", request.getParameter("nomProy"));
	    return actionMapping.findForward("adminDocsIni_fw");

	} catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("mensaje",
		    "no se pudo cargar la pag. de administraci�n de documentos de la iniciativa");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * buscarParaEditarIni
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward buscarParaEditarIni(ActionMapping actionMapping, HttpServletRequest request) {
	try {
	    Integer ano = Integer.valueOf(request.getParameter("ano"));

	    ProyectoLocal proyectoLocal = LocalizadorServicios.getInstance().getProyectoLocal();

	    // List proyectos = proyectoLocal.getProyectosxAno(ano, false);

	    HttpSession httpSession = request.getSession();
	    DatosSesVO datoses;
	    datoses = (DatosSesVO) httpSession.getAttribute("Ses_Usu");

	    UsuarioLocal usuLoc = LocalizadorServicios.getInstance().getUsuarioLocal();
	    Integer regnlo = null;
	    if (usuLoc.tienePermiso(datoses.getIdPerfil(), "registrar NLO")) {
		regnlo = new Integer(1);
	    } else {
		regnlo = new Integer(0);
	    }

	    Integer regot = null;

	    if (usuLoc.tienePermiso(datoses.getIdPerfil(), "registrar ot")) {
		regot = new Integer(1);
	    } else {
		regot = new Integer(0);
	    }

	    request.setAttribute("proyectos", proyectoLocal.getProyectosxAno(ano, false, datoses.getId()));

	    request.setAttribute("regnlo", regnlo);
	    request.setAttribute("regot", regot);
	    request.setAttribute("idSes", datoses.getId());
	    return actionMapping.findForward("busParaEditarIni_fw");

	} catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("mensaje", "no se pudo realizar la b�squeda");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * buscarIniciativas
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @param httpSession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward buscarIniciativas(ActionMapping actionMapping, HttpServletRequest request,
	    HttpSession httpSession) {

	try {
	    Integer ano = Integer.valueOf(request.getParameter("ano"));
	    if (ano.equals(new Integer(0))) {
		ano = new Integer(0);
	    }
	    String s_soloActivos = request.getParameter("soloActivos");
	    if (s_soloActivos != null) {
		boolean soloActivos = true;
	    } else {
		boolean soloActivos = false;
	    }
	    ProyectoLocal proyectoLocal = null;

	    proyectoLocal = LocalizadorServicios.getInstance().getProyectoLocal();

	    httpSession = request.getSession();
	    DatosSesVO datoses;
	    datoses = (DatosSesVO) httpSession.getAttribute("Ses_Usu");

	    request.setAttribute("proyectosaCargo", proyectoLocal.getProyectos(ano, datoses.getId(), "aCargo", true));
	    request.setAttribute("proyectosEnEquipo",
		    proyectoLocal.getProyectos(ano, datoses.getId(), "enEquipo", true));
	    request.setAttribute("otrosProyectos", proyectoLocal.getProyectos(ano, datoses.getId(), "otros", true));

	    return actionMapping.findForward("busIniciativas_fw");

	} catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("mensaje", "no se pudo realizar la b�squeda");
	    return actionMapping.findForward("mensaje_fw");
	}

    }

    /**
     * aBuscarparaEditarIni
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward aBuscarparaEditarIni(ActionMapping actionMapping, HttpServletRequest request) {
	try {
	    ProyectoLocal proyLoc = LocalizadorServicios.getInstance().getProyectoLocal();
	    Date hoy = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
	    Integer ano = Integer.valueOf(sdf.format(hoy));

	    HttpSession httpSession = request.getSession();
	    DatosSesVO datoses;
	    datoses = (DatosSesVO) httpSession.getAttribute("Ses_Usu");
	    UsuarioLocal usuLoc = LocalizadorServicios.getInstance().getUsuarioLocal();
	    Integer regnlo = null;
	    if (usuLoc.tienePermiso(datoses.getIdPerfil(), "registrar NLO")) {
		regnlo = new Integer(1);
	    } else {
		regnlo = new Integer(0);
	    }

	    Integer regot = null;

	    if (usuLoc.tienePermiso(datoses.getIdPerfil(), "registrar ot")) {
		regot = new Integer(1);
	    } else {
		regot = new Integer(0);
	    }

	    request.setAttribute("regnlo", regnlo);
	    request.setAttribute("regot", regot);
	    request.setAttribute("idSes", datoses.getId());
	    request.setAttribute("proyectos", proyLoc.getProyectosxAno(ano, false, datoses.getId()));
	    return actionMapping.findForward("busParaEditarIni_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("mensaje", "no se pudo cargar la pag. de b�squeda de Iniciativas");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * detalleIniciativa
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward detalleIniciativa(ActionMapping actionMapping, HttpServletRequest request) {
	try {
	    ProyectoLocal proyLoc = LocalizadorServicios.getInstance().getProyectoLocal();

	    DetalleProyectoVO det = null;

	    try {
		det = proyLoc.getDetalleIniciativa(Integer.valueOf(request.getParameter("idProy")));

	    } catch (Exception e) {
		det = proyLoc.getDetalleIniciativa((Integer) request.getAttribute("idProy"));
	    }

	    String fa = det.getFondosAnuales();
	    if (fa != null) {
		fa = fa.replaceAll("\r\n", "</br>");
		det.setFondosAnuales(fa);
	    }

	    request.setAttribute("detalleProy", det);

	    HttpSession httpSession = request.getSession();
	    DatosSesVO datoses;
	    datoses = (DatosSesVO) httpSession.getAttribute("Ses_Usu");

	    UsuarioLocal usuLoc = LocalizadorServicios.getInstance().getUsuarioLocal();
	    Integer edini = null;

	    if (usuLoc.tienePermiso(datoses.getIdPerfil(), "Editar Iniciativa")) {
		edini = new Integer(1);
	    } else {
		edini = new Integer(0);
	    }

	    Integer regnlo = null;
	    if (usuLoc.tienePermiso(datoses.getIdPerfil(), "registrar NLO")) {
		regnlo = new Integer(1);
	    } else {
		regnlo = new Integer(0);
	    }

	    Integer regot = null;
	    if (usuLoc.tienePermiso(datoses.getIdPerfil(), "registrar ot")) {
		regot = new Integer(1);
	    } else {
		regot = new Integer(0);
	    }

	    request.setAttribute("regnlo", regnlo);
	    request.setAttribute("regot", regot);
	    request.setAttribute("edini", edini);
	    request.setAttribute("idSes", datoses.getId());

	    return actionMapping.findForward("detalleIni_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("mensaje", "no se pudo cargar el detalle de la iniciativa");
	    return actionMapping.findForward("mensaje_fw");
	}

    }

    /**
     * aBusProy
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @param httpSession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward aBusIni(ActionMapping actionMapping, HttpServletRequest request, HttpSession httpSession) {

	try {
	    ProyectoLocal proyLoc = LocalizadorServicios.getInstance().getProyectoLocal();
	    Date hoy = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
	    Integer ano = new Integer(0);
	    DatosSesVO ds = (DatosSesVO) httpSession.getAttribute("Ses_Usu");
	    request.setAttribute("proyectosaCargo", proyLoc.getProyectos(ano, ds.getId(), "aCargo", true));
	    request.setAttribute("proyectosEnEquipo", proyLoc.getProyectos(ano, ds.getId(), "enEquipo", true));
	    request.setAttribute("otrosProyectos", proyLoc.getProyectos(ano, ds.getId(), "otros", true));
	    return actionMapping.findForward("busIniciativas_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("mensaje", "no se pudo cargar la pag. de b�squeda de Iniciativas");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * registrarIniciativa
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward registrarIniciativa(ActionMapping actionMapping, HttpServletRequest request) {
	try {
	    ProyectoLocal proyLoc = LocalizadorServicios.getInstance().getProyectoLocal();
	    DetalleProyectoVO detProy = new DetalleProyectoVO();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    detProy.setNomProy(request.getParameter("nom_proy"));

	    String fechadeinicio = request.getParameter("fechaini");

	    detProy.setFechaProy(sdf.parse(request.getParameter("fechaini")));
	    detProy.setIdCliente(new Integer(request.getParameter("idCli")));
	    detProy.setDescripcion(request.getParameter("descrip"));
	    detProy.setIdEncargado(new Integer(request.getParameter("id_encargado")));
	    String[] encs = request.getParameterValues("list1");

	    List id_ens = new LinkedList();

	    for (int i = 0; i < encs.length; i++) {
		Integer id = new Integer(encs[i]);
		id_ens.add(id);
	    }
	    detProy.setEquipo(id_ens);
	    detProy.setFondosAnuales(request.getParameter("fanual"));
	    if (!request.getParameter("ftotales").equals("")) {
		detProy.setFondosTotales(new Integer(request.getParameter("ftotales")));
	    }

	    proyLoc.insertProyecto(detProy);
	    request.setAttribute("mensaje", "La Iniciativa de Inversi�n fue registrada exitosamente");
	    return actionMapping.findForward("mensaje_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("mensaje", "No se pudo registrar la Iniciativa de Inversi�n");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * aRegistrarProyecto
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @param ses_idusu
     *            Integer
     * @return ActionForward
     */
    private ActionForward aRegistrarIniciativa(ActionMapping actionMapping, HttpServletRequest request) {
	try {
	    UsuarioLocal usuarioLocal = LocalizadorServicios.getInstance().getUsuarioLocal();
	    request.setAttribute("listausu", usuarioLocal.getUsuariosActivos());
	    return actionMapping.findForward("regIniciativa_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("mensaje", "No se pudo cargar la pag de registro de Iniciativa de Inversi�n");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * aRegDocSaliente
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @param ses_idusu
     *            Integer
     * @return ActionForward
     */
    /*
     * private ActionForward aRegDocSaliente(ActionMapping actionMapping,
     * HttpServletRequest request, HttpSession httpsession) {
     * 
     * DocumentoLocal documentoLocal = null; UsuarioLocal usuarioLocal = null;
     * ClienteLocal clienteLocal = null; try { documentoLocal =
     * LocalizadorServicios.getInstance(). getDocumentoLocal();
     * 
     * usuarioLocal = LocalizadorServicios.getInstance(). getUsuarioLocal();
     * 
     * clienteLocal = LocalizadorServicios.getInstance(). getClienteLocal();
     * 
     * } catch (LocalizadorServiciosException ex) { System.out.println(
     * "no se pudo llamar a EJB"); }
     * 
     * List tiposentrantes = documentoLocal.getTiposDoc(new Integer(1)); List
     * tipossalientes = documentoLocal.getTiposDoc(new Integer(2));
     * 
     * List listausu = usuarioLocal.getListaNomUsu();
     * 
     * List clientes = null; request.setAttribute("mensaje",
     * request.getAttribute("mensaje")); request.setAttribute("listausu",
     * listausu); request.setAttribute("tiposentrantes", tiposentrantes);
     * request.setAttribute("tipossalientes", tipossalientes);
     * request.setAttribute("clientes", clientes);
     * 
     * return actionMapping.findForward("aRegDocSaliente_fw"); }
     */
    /**
     * aRegDocEntrante
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @param ses_idusu
     *            Integer
     * @return ActionForward
     */

    /*
     * private ActionForward aRegDocEntrante(ActionMapping actionMapping,
     * HttpServletRequest request, HttpSession httpsession) { DocumentoLocal
     * documentoLocal = null; UsuarioLocal usuarioLocal = null; ClienteLocal
     * clienteLocal = null; try { documentoLocal =
     * LocalizadorServicios.getInstance(). getDocumentoLocal();
     * 
     * usuarioLocal = LocalizadorServicios.getInstance(). getUsuarioLocal();
     * 
     * clienteLocal = LocalizadorServicios.getInstance(). getClienteLocal();
     * 
     * } catch (LocalizadorServiciosException ex) { System.out.println(
     * "no se pudo llamar a EJB"); }
     * 
     * List tiposentrantes = documentoLocal.getTiposDoc(new Integer(1)); List
     * tipossalientes = documentoLocal.getTiposDoc(new Integer(2));
     * 
     * List listausu = usuarioLocal.getListaNomUsu();
     * 
     * List clientes = null; request.setAttribute("mensaje",
     * request.getAttribute("mensaje")); request.setAttribute("listausu",
     * listausu); request.setAttribute("tiposentrantes", tiposentrantes);
     * request.setAttribute("tipossalientes", tipossalientes);
     * request.setAttribute("clientes", clientes);
     * 
     * return actionMapping.findForward("aRegDocEntrante_fw"); }
     */
    /**
     * aEditarProy
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    private ActionForward aEditarProy(ActionMapping actionMapping, HttpServletRequest request,
	    HttpSession httpsession) {
	Integer id_proy = Integer.valueOf(request.getParameter("id_proy"));

	ProyectoLocal proyectoLocal = null;

	try {
	    proyectoLocal = LocalizadorServicios.getInstance().getProyectoLocal();
	} catch (LocalizadorServiciosException ex) {
	    System.out.println("no se pudo llamar a EJB");
	}

	// DetalleProyectoVO proy = proyectoLocal.getDetalleProy(id_proy);

	// request.setAttribute("detalleProy", proy);
	return actionMapping.findForward("edtiarDatosIni_fw");

    }

    /**
     * aregnlocOT
     *
     * @param actionMapping
     *            ActionMapping
     * @param request
     *            HttpServletRequest
     * @param httpsession
     *            HttpSession
     * @return ActionForward
     */
    /*
     * private ActionForward aregnlocOT(ActionMapping actionMapping,
     * HttpServletRequest request, HttpSession httpsession) {
     * 
     * Integer id_proyecto = Integer.valueOf(request.getParameter(
     * "id_proyecto")); Integer id_ot = null; if (request.getParameter("id_ot")
     * != null) { id_ot = Integer.valueOf(request.getParameter("id_ot")); }
     * 
     * ProyectoLocal proyectoLocal = null;
     * 
     * try { proyectoLocal = LocalizadorServicios.getInstance().
     * getProyectoLocal();
     * 
     * } catch (LocalizadorServiciosException ex) { System.out.println(
     * "no se pudo llamar a EJB"); }
     * 
     * // DetalleProyectoVO detproy = proyectoLocal.getDetalleProy( //
     * id_proyecto);
     * 
     * List otdelproy = proyectoLocal.getOTsProy(id_proyecto);
     * 
     * if (id_ot != null) { DetalleOTVO detot =
     * proyectoLocal.getDetalleOT(id_ot); request.setAttribute("detot", detot);
     * } request.setAttribute("otdelproy", otdelproy); //
     * request.setAttribute("detproy", detproy);
     * 
     * return actionMapping.findForward("regNLOOT_fw"); }
     */
}

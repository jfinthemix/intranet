package proyecto_uoct.ventas.controller;

import java.text.SimpleDateFormat;
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

import proyecto_uoct.EIV.model.EIVLocal;
import proyecto_uoct.common.LocalizadorServicios;
import proyecto_uoct.common.VO.DatosSesVO;
import proyecto_uoct.usuario.model.UsuarioLocal;
import proyecto_uoct.ventas.VO.BusVtaVO;
import proyecto_uoct.ventas.VO.CliVtaVO;
import proyecto_uoct.ventas.VO.InfoVtaVO;
import proyecto_uoct.ventas.VO.VtaVO;
import proyecto_uoct.ventas.model.VentasLocal;

public class VentasAction extends Action {
    public VentasAction() {
	try {
	    jbInit();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    @Override
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest servletRequest,
	    HttpServletResponse servletResponse) {

	HttpSession httpSession = servletRequest.getSession();
	DatosSesVO datoses;
	try {

	    datoses = (DatosSesVO) httpSession.getAttribute("Ses_Usu");
	    datoses.getId();
	} catch (Exception e) {
	    servletRequest.setAttribute("mensaje", "La sesi�n ha caducado");
	    return actionMapping.findForward("mensaje_fw");
	}

	String accion = servletRequest.getParameter("accion");

	if (("AdminTiposVTA").equals(accion)) {
	    return adminTiposVta(actionMapping, servletRequest);
	}
	if (("agregarInfoVta").equals(accion)) {
	    return agregarInfoVta(actionMapping, servletRequest);
	}

	if (("editarInfoVta").equals(accion)) {
	    return editarInfoVta(actionMapping, servletRequest);
	}

	if (("actualizarInfoVta").equals(accion)) {
	    return actualizarInfoVta(actionMapping, servletRequest);
	}

	// ---------CLIENTE --------------

	if (("aRegistrarCli").equals(accion)) {
	    return actionMapping.findForward("regCliente_fw");
	}

	if (("RegClienteVta").equals(accion)) {
	    return RegistrarCli(actionMapping, servletRequest);
	}

	if (("buscarCli").equals(accion)) {
	    return buscarCliVta(actionMapping, servletRequest);
	}

	if (("detalleCli").equals(accion)) {
	    return detalleCli(actionMapping, servletRequest);
	}

	if (("exportarAgendaCli").equals(accion)) {
	    return exportarAgendaCli(actionMapping, servletRequest);
	}

	if (("exportarAgendaCli").equals(accion)) {
	    return exportarAgendaCli(actionMapping, servletRequest);
	}

	if (("buscarparaEditarCli").equals(accion)) {
	    return buscarparaEditarCli(actionMapping, servletRequest);
	}

	if (("editarCli").equals(accion)) {
	    return editarCli(actionMapping, servletRequest);
	}

	if (("actualizarCliente").equals(accion)) {
	    return actualizarCliente(actionMapping, servletRequest);
	}

	if (("busCliVta_pop").equals(accion)) {
	    return busCliVta_pop(actionMapping, servletRequest);
	}

	// ----------Ventas-------------
	if (("aRegProcesoVta").equals(accion)) {
	    return aRegProcesoVta(actionMapping, servletRequest);
	}

	if (("regProcesoVta").equals(accion)) {
	    return regProcesoVta(actionMapping, servletRequest);
	}

	if (("buscarVta").equals(accion)) {
	    return buscarVta(actionMapping, servletRequest);
	}

	if (("detalleVenta").equals(accion)) {
	    return detalleVenta(actionMapping, servletRequest, httpSession);
	}
	if (("buscarParaEditarVta").equals(accion)) {
	    return buscarParaEditarVta(actionMapping, servletRequest);
	}

	if (("editarVenta").equals(accion)) {
	    return editarVenta(actionMapping, servletRequest);
	}

	if (("actualizarVta").equals(accion)) {
	    return actualizarVta(actionMapping, servletRequest);
	}

	if (("regFechaCotizacion").equals(accion)) {
	    return regFechaCotizacion(actionMapping, servletRequest, httpSession);
	}

	if (("regFechaPago").equals(accion)) {
	    return regFechaPago(actionMapping, servletRequest, httpSession);
	}
	if (("regFechaEntregaInfo").equals(accion)) {
	    return regFechaEntregaInfo(actionMapping, servletRequest, httpSession);
	}

	if (("regFechaFinVenta").equals(accion)) {
	    return regFechaFinVenta(actionMapping, servletRequest, httpSession);
	}

	if (("anularVenta").equals(accion)) {
	    return anularVenta(actionMapping, servletRequest, httpSession);
	}

	if (("aactualizarUF").equals(accion)) {
	    return aactualizarUF(actionMapping, servletRequest);
	}

	if (("actualizarUF").equals(accion)) {
	    return actualizarUF(actionMapping, servletRequest);
	}

	if (("refrescaRegPago").equals(accion)) {
	    return refrescaRegPago(actionMapping, servletRequest);
	}

	return null;
    }

    /**
     * refrescaRegPago
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward refrescaRegPago(ActionMapping actionMapping, HttpServletRequest servletRequest) {
	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();
	    Integer idVta = Integer.valueOf(servletRequest.getParameter("idVenta"));

	    Float uf = Float.valueOf(servletRequest.getParameter("nuevaUF").replace(',', '.'));
	    servletRequest.setAttribute("uf", uf);
	    servletRequest.setAttribute("venta", ventasLocal.detalleVta(idVta));
	    return actionMapping.findForward("regFechaPago_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "Error al refrescar la informaci�n de la Venta");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * actualizarVta
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward actualizarVta(ActionMapping actionMapping, HttpServletRequest servletRequest) {
	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();
	    VtaVO v = new VtaVO();
	    CliVtaVO cli = new CliVtaVO();
	    v.setIdVenta(Integer.valueOf(servletRequest.getParameter("idVenta")));
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    String f = servletRequest.getParameter("fecha");
	    v.setFechaRecepcion(sdf.parse(f));

	    cli.setIdCliente(Integer.valueOf(servletRequest.getParameter("idCli")));
	    v.setCliente(cli);

	    String[] d = servletRequest.getParameterValues("detVenta");
	    List detalle = new LinkedList();
	    for (int i = 0; i < d.length; i++) {
		InfoVtaVO inf = new InfoVtaVO();
		String cant = d[i].substring(0, d[i].indexOf("--"));
		inf.setCantidad(Integer.valueOf(cant));

		String idInf = d[i].substring(d[i].indexOf("--") + 2, d[i].indexOf("!!"));
		inf.setIdInfo(Integer.valueOf(idInf));
		String desc = d[i].substring(d[i].lastIndexOf("!!") + 2);
		inf.setDescripcion(desc);

		detalle.add(inf);
	    }
	    v.setDetVenta(detalle);
	    v.setComentario(servletRequest.getParameter("comentario"));
	    ventasLocal.actualizarVenta(v);

	    servletRequest.setAttribute("mensaje", "El proceso de venta fue actualizado exitosamente");
	    return actionMapping.findForward("mensaje_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "El proceso de venta no pudo ser actualizado");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * aactualizarUF
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward aactualizarUF(ActionMapping actionMapping, HttpServletRequest servletRequest) {
	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();
	    Float f = new Float(ventasLocal.getUF());
	    servletRequest.setAttribute("uf", f);
	    return actionMapping.findForward("actualizarUF_fw");

	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo actualizar el valor de la UF");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * actualizarUF
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward actualizarUF(ActionMapping actionMapping, HttpServletRequest servletRequest) {
	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();
	    String ufS = servletRequest.getParameter("uf");

	    Float uf = Float.valueOf(ufS.replaceAll(",", "."));
	    String ufS2 = uf.toString();

	    if (ventasLocal.actualizaUF(ufS2)) {
		servletRequest.setAttribute("mensaje", "El valor de la UF ha sido actualizado");
	    } else {
		servletRequest.setAttribute("mensaje", "Error: No se pudo actualizar el valor de la UF");
	    }
	    return actionMapping.findForward("mensaje_fw");
	}

	catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo actualizar el valor de la UF");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * anularVenta
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward anularVenta(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpSession) {
	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();
	    Integer idVta = Integer.valueOf(servletRequest.getParameter("idVenta"));
	    if (ventasLocal.anularVenta(idVta)) {
		servletRequest.setAttribute("mensaje", "La venta ha sido anulada");
	    } else {
		servletRequest.setAttribute("mensaje", "Error: No se pudo anular la venta");
	    }
	    return detalleVenta(actionMapping, servletRequest, httpSession);
	}

	catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo anular la venta");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * regFechaFinVenta
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward regFechaFinVenta(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpSession) {

	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();
	    Integer idVta = Integer.valueOf(servletRequest.getParameter("idVenta"));
	    String codFact = servletRequest.getParameter("codFactura");

	    String s = servletRequest.getParameter("Submit");
	    if (s != null) {

		String fecha = servletRequest.getParameter("fecha");
		if (ventasLocal.regFechaFin(fecha, codFact, idVta)) {
		    servletRequest.setAttribute("mensaje", "La venta ha sido actualizada");
		} else {
		    servletRequest.setAttribute("mensaje", "Error: La venta no fue actualizada");
		}
		return detalleVenta(actionMapping, servletRequest, httpSession);
	    } else {
		servletRequest.setAttribute("venta", ventasLocal.detalleVta(idVta));
		return actionMapping.findForward("regFechaFin_fw");
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo registrar la fecha de entrega informaci�n vendida");
	    return actionMapping.findForward("mensaje_fw");

	}

    }

    /**
     * regFechaEntregaInfo
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward regFechaEntregaInfo(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpSession) {
	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();
	    Integer idVta = Integer.valueOf(servletRequest.getParameter("idVenta"));

	    String s = servletRequest.getParameter("Submit");
	    if (s != null) {

		String fecha = servletRequest.getParameter("fecha");
		if (ventasLocal.regFechaEntrega(fecha, idVta)) {
		    servletRequest.setAttribute("mensaje", "La venta ha sido actualizada");
		} else {
		    servletRequest.setAttribute("mensaje", "Error: La venta no fue actualizada");
		}
		return detalleVenta(actionMapping, servletRequest, httpSession);
	    } else {
		servletRequest.setAttribute("venta", ventasLocal.detalleVta(idVta));
		return actionMapping.findForward("regFechaEntregaInfo_fw");
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo registrar la fecha de entrega informaci�n vendida");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * regFechaPago
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward regFechaPago(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpSession) {
	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();
	    Integer idVta = Integer.valueOf(servletRequest.getParameter("idVenta"));

	    String s = servletRequest.getParameter("Submit");
	    if (s != null) {
		String ufS = servletRequest.getParameter("valorUF");
		Float ufF = new Float(ufS.replace(',', '.'));
		String fecha = servletRequest.getParameter("fecha");
		if (ventasLocal.regFechaPago(fecha, idVta, ufF)) {
		    servletRequest.setAttribute("mensaje", "La venta ha sido actualizada");
		} else {
		    servletRequest.setAttribute("mensaje", "Error: La venta no fue actualizada");
		}
		return detalleVenta(actionMapping, servletRequest, httpSession);
	    } else {
		Float uf = new Float(ventasLocal.getUF());
		servletRequest.setAttribute("uf", uf);
		servletRequest.setAttribute("venta", ventasLocal.detalleVta(idVta));
		return actionMapping.findForward("regFechaPago_fw");
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo registrar la fecha de pago");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * regFechaCotizacion
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward regFechaCotizacion(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpSession) {
	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();
	    Integer idVta = Integer.valueOf(servletRequest.getParameter("idVenta"));

	    String s = servletRequest.getParameter("Submit");
	    if (s != null) {

		String fecha = servletRequest.getParameter("fecha");
		if (ventasLocal.regFechaCotizacion(fecha, idVta)) {
		    servletRequest.setAttribute("mensaje", "La venta ha sido actualizada");
		} else {
		    servletRequest.setAttribute("mensaje", "Error: La venta no fue actualizada");
		}
		return detalleVenta(actionMapping, servletRequest, httpSession);
	    } else {
		servletRequest.setAttribute("venta", ventasLocal.detalleVta(idVta));
		return actionMapping.findForward("regEntregaCotiz_fw");
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo registrar la fecha de entrega de la cotizaci�n");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * editarVenta
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward editarVenta(ActionMapping actionMapping, HttpServletRequest servletRequest) {
	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();
	    Integer idVta = Integer.valueOf(servletRequest.getParameter("idVenta"));
	    servletRequest.setAttribute("venta", ventasLocal.detalleVta(idVta));
	    servletRequest.setAttribute("tiposInf", ventasLocal.getTiposInfo(true));
	    return actionMapping.findForward("editarVenta_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar la pag. de edici�n de ventas");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * buscarParaEditarVta
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward buscarParaEditarVta(ActionMapping actionMapping, HttpServletRequest servletRequest) {
	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	    if (servletRequest.getParameter("Submit") != null) {
		BusVtaVO bus = new BusVtaVO();
		if (!("").equals(servletRequest.getParameter("fechaPresentacion"))
			&& !("").equals(servletRequest.getParameter("fechaPresentacion2"))) {
		    bus.setFechaIni(sdf.parse(servletRequest.getParameter("fechaPresentacion")));
		    bus.setFechaIni2(sdf.parse(servletRequest.getParameter("fechaPresentacion2")));
		}
		if (!("").equals(servletRequest.getParameter("idCli"))) {
		    bus.setIdCli(Integer.valueOf(servletRequest.getParameter("idCli")));
		}

		if (!("0").equals(servletRequest.getParameter("idEstado"))) {
		    bus.setIdEstado(Integer.valueOf(servletRequest.getParameter("idEstado")));
		}

		servletRequest.setAttribute("ventas", ventasLocal.buscarVentas(bus));
	    }
	    servletRequest.setAttribute("estados", ventasLocal.getEstadosVta());
	    return actionMapping.findForward("busParaEditarVenta_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar la pag. de busqueda de ventas");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * detalleVenta
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward detalleVenta(ActionMapping actionMapping, HttpServletRequest servletRequest,
	    HttpSession httpsession) {
	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();
	    UsuarioLocal usuarioLocal = LocalizadorServicios.getInstance().getUsuarioLocal();

	    DatosSesVO ds = (DatosSesVO) httpsession.getAttribute("Ses_Usu");

	    Integer idVta = Integer.valueOf(servletRequest.getParameter("idVenta"));
	    VtaVO venta = ventasLocal.detalleVta(idVta);
	    Float f = null;
	    if (venta.getUfPago() != null) {
		f = venta.getUfPago();
		Iterator i = venta.getDetVenta().iterator();
		LinkedList detVenta_n = new LinkedList();
		while (i.hasNext()) {
		    InfoVtaVO info = (InfoVtaVO) i.next();
		    info.setPrecio(info.getPrecioPago());
		    detVenta_n.add(info);
		}
		venta.setDetVenta(detVenta_n);

	    } else {
		f = new Float(ventasLocal.getUF());
	    }
	    servletRequest.setAttribute("venta", ventasLocal.detalleVta(idVta));
	    servletRequest.setAttribute("uf", f);

	    boolean puedeEditar = usuarioLocal.tienePermiso(ds.getIdPerfil(), "Editar Ventas");
	    if (puedeEditar) {
		servletRequest.setAttribute("editable", new Integer(1));
	    } else {
		servletRequest.setAttribute("editable", new Integer(0));
	    }

	    return actionMapping.findForward("detalleVenta_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar la pag. de detalle de la venta");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * buscarVta
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward buscarVta(ActionMapping actionMapping, HttpServletRequest servletRequest) {
	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();

	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	    if (servletRequest.getParameter("Submit") != null) {
		BusVtaVO bus = new BusVtaVO();
		if (!("").equals(servletRequest.getParameter("fechaPresentacion"))
			&& !("").equals(servletRequest.getParameter("fechaPresentacion2"))) {
		    bus.setFechaIni(sdf.parse(servletRequest.getParameter("fechaPresentacion")));
		    bus.setFechaIni2(sdf.parse(servletRequest.getParameter("fechaPresentacion2")));
		}
		if (!("").equals(servletRequest.getParameter("idCli"))) {
		    bus.setIdCli(Integer.valueOf(servletRequest.getParameter("idCli")));
		}

		if (!("0").equals(servletRequest.getParameter("idEstado"))) {
		    bus.setIdEstado(Integer.valueOf(servletRequest.getParameter("idEstado")));
		}
		if (!("").equals(servletRequest.getParameter("palClave"))) {
		    bus.setPalClave(String.valueOf(servletRequest.getParameter("palClave")));
		}

		servletRequest.setAttribute("ventas", ventasLocal.buscarVentas(bus));
	    }
	    List estadosVta = ventasLocal.getEstadosVta();
	    servletRequest.setAttribute("estados", estadosVta);
	    return actionMapping.findForward("buscarVtas_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar la pag. de busqueda de ventas");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * regProcesoVta
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward regProcesoVta(ActionMapping actionMapping, HttpServletRequest servletRequest) {
	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();
	    VtaVO v = new VtaVO();
	    CliVtaVO cli = new CliVtaVO();

	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    String f = servletRequest.getParameter("fechaPresentacion");
	    v.setFechaRecepcion(sdf.parse(f));

	    cli.setIdCliente(Integer.valueOf(servletRequest.getParameter("idCli")));
	    v.setCliente(cli);

	    String[] d = servletRequest.getParameterValues("detVenta");
	    List detalle = new LinkedList();
	    for (int i = 0; i < d.length; i++) {
		InfoVtaVO inf = new InfoVtaVO();
		String cant = d[i].substring(0, d[i].indexOf("--"));
		inf.setCantidad(Integer.valueOf(cant));
		String idInf = d[i].substring(d[i].indexOf("--") + 2, d[i].indexOf("!!"));
		inf.setIdInfo(Integer.valueOf(idInf));
		String desc = d[i].substring(d[i].lastIndexOf("!!") + 2);
		inf.setDescripcion(desc);
		detalle.add(inf);
	    }
	    v.setDetVenta(detalle);
	    v.setComentario(servletRequest.getParameter("comentario"));
	    v.setCodVenta(servletRequest.getParameter("codVta"));
	    if (ventasLocal.regProcesoVenta(v)) {
		String mensaje = "El proceso de venta fue registrado exitosamente";

		EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();

		String cuerpoCorreo = "Identificador del proceso de venta:";

		eivLocal.alertaEmail("ventas@uoct.cl", "x", "jfanasco@uoct.cl", "Cotizaci�n de Venta", cuerpoCorreo);

		servletRequest.setAttribute("mensaje", mensaje);

	    } else {
		servletRequest.setAttribute("mensaje", "El proceso de venta no pudo ser registrado");
	    }
	    return actionMapping.findForward("mensaje_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "El proceso de venta no pudo ser registrado");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * busCliVta_pop
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward busCliVta_pop(ActionMapping actionMapping, HttpServletRequest servletRequest) {
	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();
	    if (servletRequest.getParameter("PorPalabra") != null) {
		servletRequest.setAttribute("clientes",
			ventasLocal.buscarClienteVta(servletRequest.getParameter("palClave"), true));
	    }
	    return actionMapping.findForward("busCliVta_pop_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar la pag. de busqueda de clientes");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * aRegProcesoVta
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward aRegProcesoVta(ActionMapping actionMapping, HttpServletRequest servletRequest) {
	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();
	    servletRequest.setAttribute("tiposInfoVta", ventasLocal.getTiposInfo(true));
	    return actionMapping.findForward("RegProcesoVta_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar la pag. de registro de ventas");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * actualizarCliente
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward actualizarCliente(ActionMapping actionMapping, HttpServletRequest servletRequest) {
	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();
	    CliVtaVO cli = new CliVtaVO();
	    cli.setIdCliente(Integer.valueOf(servletRequest.getParameter("idCliente")));
	    if ((!("").equals(servletRequest.getParameter("rut")))
		    && (!("").equals(servletRequest.getParameter("codRut")))) {
		cli.setRut(new Integer(servletRequest.getParameter("rut")));
		cli.setCodRutCli(servletRequest.getParameter("codRut").charAt(0));
	    }

	    cli.setTelefono(servletRequest.getParameter("fono_cli"));

	    cli.setEmail(servletRequest.getParameter("email_cli"));
	    cli.setGiro(servletRequest.getParameter("giro"));
	    cli.setDireccion(servletRequest.getParameter("dir_cli"));
	    cli.setContactos(servletRequest.getParameter("contactos"));
	    cli.setComentario(servletRequest.getParameter("comentario"));
	    cli.setIsActivo(Integer.valueOf(servletRequest.getParameter("idEstado")));

	    if (ventasLocal.actualizarCliente(cli)) {
		servletRequest.setAttribute("mensaje", "El cliente fue actualizado exitosamente");
	    } else {
		servletRequest.setAttribute("mensaje", "No se pudo actualizar el cliente");
	    }
	    return actionMapping.findForward("mensaje_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo actualizar el cliente");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * editarCli
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward editarCli(ActionMapping actionMapping, HttpServletRequest servletRequest) {
	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();
	    servletRequest.setAttribute("cliente",
		    ventasLocal.getDetalleCliente(Integer.valueOf(servletRequest.getParameter("idCli"))));
	    return actionMapping.findForward("editarCliente_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar el detalle del cliente");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * buscarparaEditarCli
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward buscarparaEditarCli(ActionMapping actionMapping, HttpServletRequest servletRequest) {
	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();
	    if (servletRequest.getParameter("PorPalabra") != null) {
		servletRequest.setAttribute("clientes",
			ventasLocal.buscarClienteVta(servletRequest.getParameter("palClave"), false));
	    }
	    return actionMapping.findForward("buscarParaEditarCli_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar la pag. de busqueda de clientes");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * exportarAgendaCli
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward exportarAgendaCli(ActionMapping actionMapping, HttpServletRequest servletRequest) {
	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();
	    servletRequest.setAttribute("clientes", ventasLocal.exportarAgenda());

	    return actionMapping.findForward("exportarCliVta_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo exportar la agenda");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * detalleCli
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward detalleCli(ActionMapping actionMapping, HttpServletRequest servletRequest) {
	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();
	    servletRequest.setAttribute("cliente",
		    ventasLocal.getDetalleCliente(Integer.valueOf(servletRequest.getParameter("idCli"))));
	    return actionMapping.findForward("detalleCliente_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar el detalle del cliente");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * buscarCliVta
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward buscarCliVta(ActionMapping actionMapping, HttpServletRequest servletRequest) {
	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();
	    if (servletRequest.getParameter("PorPalabra") != null) {
		servletRequest.setAttribute("clientes",
			ventasLocal.buscarClienteVta(servletRequest.getParameter("palClave"), true));
	    }
	    return actionMapping.findForward("buscarCliVta_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo cargar la pag. de busqueda de clientes");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * RegistrarCli
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward RegistrarCli(ActionMapping actionMapping, HttpServletRequest servletRequest) {

	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();
	    CliVtaVO cli = new CliVtaVO();
	    cli.setNomCli(servletRequest.getParameter("nom_cli"));
	    if ((!("").equals(servletRequest.getParameter("rut")))
		    && (!("").equals(servletRequest.getParameter("codRut")))) {
		cli.setRut(new Integer(servletRequest.getParameter("rut")));
		cli.setCodRutCli(servletRequest.getParameter("codRut").charAt(0));
	    }

	    cli.setTelefono(servletRequest.getParameter("fono_cli"));

	    cli.setEmail(servletRequest.getParameter("email_cli"));
	    cli.setGiro(servletRequest.getParameter("giro"));
	    cli.setDireccion(servletRequest.getParameter("dir_cli"));
	    cli.setComentario(servletRequest.getParameter("comentario"));
	    cli.setContactos(servletRequest.getParameter("contactos"));

	    if (ventasLocal.regClienteVta(cli)) {
		servletRequest.setAttribute("mensaje", "El cliente fue registrado exitosamente");
	    } else {
		servletRequest.setAttribute("mensaje", "No se pudo registrar cliente");
	    }
	    return actionMapping.findForward("mensaje_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo registrar el cliente");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * actualizarInfoVta
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward actualizarInfoVta(ActionMapping actionMapping, HttpServletRequest servletRequest) {
	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();
	    Integer id = Integer.valueOf(servletRequest.getParameter("idInfoVta"));
	    String nom = servletRequest.getParameter("nom");
	    String unidad = servletRequest.getParameter("unidad");
	    Float precio = new Float(
		    servletRequest.getParameter("precio1") + "." + servletRequest.getParameter("precio2"));

	    InfoVtaVO inf = new InfoVtaVO();
	    inf.setIdInfo(id);
	    inf.setTipoInfo(nom);
	    inf.setUnidad(unidad);
	    inf.setPrecio(precio);

	    if ("1".equals(servletRequest.getParameter("idEstado"))) {
		inf.setIsActivo(new Integer(1));
	    } else {
		inf.setIsActivo(new Integer(0));
	    }

	    if (ventasLocal.actualizarInfoVta(inf)) {
		servletRequest.setAttribute("mensaje", "La informaci�n para la venta fue actualizada");

	    } else {
		servletRequest.setAttribute("mensaje", "No se pudo actualizar la informaci�n para la venta");
	    }
	    return actionMapping.findForward("mensaje_fw");

	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo actualizar la informaci�n para la venta");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * editarInfoVta
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward editarInfoVta(ActionMapping actionMapping, HttpServletRequest servletRequest) {

	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();
	    Integer id = Integer.valueOf(servletRequest.getParameter("idInfoVta"));

	    servletRequest.setAttribute("detInfo", ventasLocal.getDetalleInfoVta(id));

	    return actionMapping.findForward("editarTipoInfoVta_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje",
		    "No se pudo cargar la pag. de edici�n de tipos de informaci�n para la venta");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * agregarInfoVta
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward agregarInfoVta(ActionMapping actionMapping, HttpServletRequest servletRequest) {
	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();
	    InfoVtaVO inf = new InfoVtaVO();
	    inf.setTipoInfo(servletRequest.getParameter("nom"));
	    inf.setUnidad(servletRequest.getParameter("unidad"));
	    inf.setPrecio(
		    new Float(servletRequest.getParameter("precio") + "." + servletRequest.getParameter("precio2")));
	    if (ventasLocal.regInfoVta(inf)) {
		servletRequest.setAttribute("mensaje", "El tipo de de informaci�n fue registrada exitosamente");
	    } else {
		servletRequest.setAttribute("mensaje", "No se pudo registrar el tipo de informaci�n para la venta");
	    }
	    return adminTiposVta(actionMapping, servletRequest);
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo registrar el tipo de informaci�n para la venta");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * adminTiposVta
     *
     * @param actionMapping
     *            ActionMapping
     * @param servletRequest
     *            HttpServletRequest
     * @return ActionForward
     */
    private ActionForward adminTiposVta(ActionMapping actionMapping, HttpServletRequest servletRequest) {
	try {
	    VentasLocal ventasLocal = LocalizadorServicios.getInstance().getVentasLocal();

	    servletRequest.setAttribute("tiposInfo", ventasLocal.getTiposInfo(false));
	    return actionMapping.findForward("adminTiposVta_fw");
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje",
		    "No se pudo cargar la pag. de administraci�n de tipos de informaci�n para la venta");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    private void jbInit() throws Exception {
    }
}

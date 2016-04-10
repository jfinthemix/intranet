package proyecto_uoct.infoyrep.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
import proyecto_uoct.documentacion.VO.ArchivoDocVO;
import proyecto_uoct.infoyrep.VO.ArchivoInfoVO;
import proyecto_uoct.infoyrep.model.InfoInstitLocal;

public class InfoinstitAction extends Action {
    @Override
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest servletRequest,
	    HttpServletResponse servletResponse) {

	HttpSession httpsession = servletRequest.getSession();

	String hacia = servletRequest.getParameter("hacia");
	DatosSesVO datoses;

	if (hacia == null) {
	    fileup1ActionForm myForm = (fileup1ActionForm) actionForm;
	    servletRequest.setAttribute("mensaje",
		    "URL Incorrecta.Si está subiendo un archivo, verifique que su tamaño no supera los 10 MB");
	    return actionMapping.findForward("mensaje_fw");

	}

	// -------------------------------------------

	if (("listarInfo").equals(servletRequest.getParameter("hacia"))) {
	    return listarInfoInstit(actionMapping, actionForm, servletRequest, servletResponse);

	}

	if (("descargarArchivo").equals(servletRequest.getParameter("hacia"))) {
	    return descargarArchivo(actionMapping, actionForm, servletRequest, servletResponse);

	}

	try {

	    datoses = (DatosSesVO) httpsession.getAttribute("Ses_Usu");
	    datoses.getId();
	} catch (Exception e) {
	    servletRequest.setAttribute("mensaje", "La sesión ha caducado");
	    return actionMapping.findForward("mensaje_fw");
	}

	if (servletRequest.getParameter("hacia").compareTo("adminArchivos") == 0) {
	    return adminArchivos(actionMapping, actionForm, servletRequest, servletResponse);

	}

	if (("agregarArchivo").equals(servletRequest.getParameter("hacia"))) {

	    return agregarArchivo(actionMapping, actionForm, servletRequest, servletResponse);

	}

	if (("eliminarArchivo").equals(servletRequest.getParameter("hacia"))) {
	    return eliminarArchivo(actionMapping, actionForm, servletRequest, servletResponse);

	}

	servletRequest.setAttribute("mensaje", "URL errónea");
	return actionMapping.findForward("mensaje_fw");

    }

    /**
     * eliminarArchivo
     *
     * @param actionMapping
     *            ActionMapping
     * @param actionForm
     *            ActionForm
     * @param servletRequest
     *            HttpServletRequest
     * @param servletResponse
     *            HttpServletResponse
     * @return ActionForward
     */
    private ActionForward eliminarArchivo(ActionMapping actionMapping, ActionForm actionForm,
	    HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
	try {

	    InfoInstitLocal infoInstitLocal = LocalizadorServicios.getInstance().getInfoInstitLocal();
	    Integer idArchivo = Integer.valueOf(servletRequest.getParameter("idFile"));
	    infoInstitLocal.eliminarArchivo(idArchivo);
	    servletRequest.setAttribute("mensaje", "El archivo fue eliminado exitosamente");
	    return adminArchivos(actionMapping, actionForm, servletRequest, servletResponse);
	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo descargar el archivo");
	    return actionMapping.findForward("mensaje_fw");

	}
    }

    /**
     * descargarArchivo
     *
     * @param actionMapping
     *            ActionMapping
     * @param actionForm
     *            ActionForm
     * @param servletRequest
     *            HttpServletRequest
     * @param servletResponse
     *            HttpServletResponse
     * @return ActionForward
     */
    private ActionForward descargarArchivo(ActionMapping actionMapping, ActionForm actionForm,
	    HttpServletRequest servletRequest, HttpServletResponse response) {
	try {

	    InfoInstitLocal infoInstitLocal = LocalizadorServicios.getInstance().getInfoInstitLocal();
	    Integer idArchivo = Integer.valueOf(servletRequest.getParameter("idFile"));

	    ArchivoDocVO a = infoInstitLocal.getArchivo(idArchivo);
	    byte[] b = a.getArchivo();

	    ByteArrayOutputStream output = new ByteArrayOutputStream();
	    output.write(b, 0, b.length);

	    response.setContentType("application/download");
	    response.setHeader("content-disposition", "attachment; fileName=\"" + a.getNomArchivo() + "\"");
	    response.setContentLength(output.size());

	    // Escribir el archivo en el response
	    OutputStream out = response.getOutputStream();
	    output.writeTo(out);
	    out.flush();
	    out.close();

	} catch (Exception e) {
	    e.printStackTrace();
	    servletRequest.setAttribute("mensaje", "No se pudo descargar el archivo");
	    return actionMapping.findForward("mensaje_fw");

	}
	return null;
    }

    /**
     * adminArchivos
     *
     * @param actionMapping
     *            ActionMapping
     * @param actionForm
     *            ActionForm
     * @param servletRequest
     *            HttpServletRequest
     * @param servletResponse
     *            HttpServletResponse
     * @return ActionForward
     */
    private ActionForward adminArchivos(ActionMapping actionMapping, ActionForm actionForm,
	    HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
	try {
	    InfoInstitLocal infoInstitLocal = LocalizadorServicios.getInstance().getInfoInstitLocal();

	    List listaarchivos = infoInstitLocal.getListaArchivos();
	    servletRequest.setAttribute("lista", listaarchivos);
	    return actionMapping.findForward("agregarInfo_fw");
	} catch (Exception ex) {
	    ex.printStackTrace();
	    servletRequest.setAttribute("mensaje",
		    "No se pudo cargar la pag. de Administraci�n de informaci�n institucional");
	    return actionMapping.findForward("mensaje_fw");
	}

    }

    /**
     * listarInfoInstit
     *
     * @param actionMapping
     *            ActionMapping
     * @param actionForm
     *            ActionForm
     * @param servletRequest
     *            HttpServletRequest
     * @param servletResponse
     *            HttpServletResponse
     * @return ActionForward
     */
    private ActionForward listarInfoInstit(ActionMapping actionMapping, ActionForm actionForm,
	    HttpServletRequest servletRequest, HttpServletResponse servletResponse) {

	try {
	    InfoInstitLocal infoInstitLocal = LocalizadorServicios.getInstance().getInfoInstitLocal();

	    List listaarchivos = infoInstitLocal.getListaArchivos();
	    servletRequest.setAttribute("lista", listaarchivos);
	    return actionMapping.findForward("listarInfo_fw");
	} catch (LocalizadorServiciosException ex) {
	    ex.printStackTrace();
	    servletRequest.setAttribute("mensaje", "El archivo no pudo ser ingresado");
	    return actionMapping.findForward("mensaje_fw");
	}
    }

    /**
     * agregarArchivo
     *
     * @param actionMapping
     *            ActionMapping
     * @param actionForm
     *            ActionForm
     * @param servletRequest
     *            HttpServletRequest
     * @param servletResponse
     *            HttpServletResponse
     * @return ActionForward
     */
    private ActionForward agregarArchivo(ActionMapping actionMapping, ActionForm actionForm,
	    HttpServletRequest servletRequest, HttpServletResponse servletResponse) {

	try {
	    fileup1ActionForm myForm = (fileup1ActionForm) actionForm;
	    FormFile unarchivo = myForm.getUnArchivo();
	    // String contentType = unarchivo.getContentType();
	    // String fileName = unarchivo.getFileName();
	    // int fileSize = unarchivo.getFileSize();

	    InfoInstitLocal infoInstitLocal = null;
	    ArchivoInfoVO archivo = new ArchivoInfoVO();
	    archivo.setDescripcion(myForm.getDescripcion());
	    // archivo.setLaData(unarchivo);

	    // Transformar el formfile a file
	    File f = new File(unarchivo.getFileName());
	    DataOutputStream dostr = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
	    dostr.write(unarchivo.getFileData());
	    dostr.close();

	    byte[] archivoInfo = unarchivo.getFileData();
	    archivo.setArchivo(archivoInfo);
	    archivo.setArchivoFile(f);

	    infoInstitLocal = LocalizadorServicios.getInstance().getInfoInstitLocal();
	    infoInstitLocal.upArchivo(archivo);
	    servletRequest.setAttribute("mensaje", "El archivo fue ingresado");
	    return adminArchivos(actionMapping, actionForm, servletRequest, servletResponse);
	} catch (Exception ex) {
	    ex.printStackTrace();
	    servletRequest.setAttribute("mensaje", "El archivo no pudo ser ingresado");
	}

	return actionMapping.findForward("mensaje_fw");
    }

}

/*
 * String hacia= servletRequest.getParameter("hacia"); HttpSession httpsession=
 * servletRequest.getSession(); DatosSesVO datosses=
 * (DatosSesVO)httpsession.getAttribute("Ses_Usu");
 * 
 * 
 * //---------------------------------------------------------------
 * if(hacia.compareTo("listarInfo")==0){ return
 * actionMapping.findForward("listarInfo_fw"); }
 * 
 * else { servletRequest.setAttribute("mensaje", "URL incorrecta"); return
 * actionMapping.findForward("mensaje_fw"); } }
 */

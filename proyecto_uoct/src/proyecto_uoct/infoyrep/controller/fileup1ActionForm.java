package proyecto_uoct.infoyrep.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class fileup1ActionForm extends ActionForm {

    @Override
    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {

	return null;
    }

    @Override
    public void reset(ActionMapping actionMapping, HttpServletRequest servletRequest) {
    }

    private FormFile unArchivo;
    private String descripcion;

    public void setUnArchivo(FormFile unArchivo) {
	this.unArchivo = unArchivo;
    }

    public FormFile getUnArchivo() {
	return unArchivo;

    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public String getDescripcion() {
	return this.descripcion;
    }

}

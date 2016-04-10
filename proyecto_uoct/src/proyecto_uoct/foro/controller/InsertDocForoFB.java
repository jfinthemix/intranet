package proyecto_uoct.foro.controller;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;

public class InsertDocForoFB extends ActionForm {
    private FormFile undoc;
    public FormFile getUndoc() {
        return undoc;
    }

    public void setUndoc(FormFile undoc) {
        this.undoc = undoc;
    }

    public ActionErrors validate(ActionMapping actionMapping,
                                 HttpServletRequest httpServletRequest) {

        return null;
    }

    public void reset(ActionMapping actionMapping,
                      HttpServletRequest servletRequest) {
    }
}

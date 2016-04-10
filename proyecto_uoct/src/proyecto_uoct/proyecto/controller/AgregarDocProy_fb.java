package proyecto_uoct.proyecto.controller;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.upload.FormFile;

public class AgregarDocProy_fb extends ActionForm {
    private FormFile docProy;

    private FormFile eldoc = null;
    private FormFile eldoc1 = null;
    private FormFile eldoc2 = null;


    public void setEldoc(FormFile eldoc) {
        this.eldoc = eldoc;
    }

    public void setEldoc1(FormFile eldoc1) {
        this.eldoc1 = eldoc1;
    }

    public void setEldoc2(FormFile eldoc2) {
        this.eldoc2 = eldoc2;
    }


    public FormFile getEldoc() {
        return eldoc;
    }

    public FormFile getEldoc1() {
        return eldoc1;
    }

    public FormFile getEldoc2() {
        return eldoc2;
    }


    public FormFile getDocProy() {
        return docProy;
    }

    public void setDocProy(FormFile docProy) {
        this.docProy = docProy;
    }

    public ActionErrors validate(ActionMapping actionMapping,
                                 HttpServletRequest httpServletRequest) {
        return null;
    }

    public void reset(ActionMapping actionMapping,
                      HttpServletRequest servletRequest) {
    }

    public void reset() {
        docProy = null;
        eldoc = null;
        eldoc1 = null;
        eldoc2 = null;

    }

}

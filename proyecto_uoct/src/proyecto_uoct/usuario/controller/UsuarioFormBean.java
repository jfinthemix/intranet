package proyecto_uoct.usuario.controller;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.upload.FormFile;

public class UsuarioFormBean extends ActionForm {
    private FormFile curric;

    public FormFile getCurric() {
        return curric;
    }

    public void setCurric(FormFile curric) {
        this.curric = curric;
    }

    private FormFile informe;

    public FormFile getInforme() {
        return informe;
    }

    public void setInforme(FormFile informe) {
        this.informe = informe;
    }



            private FormFile foto;

            public FormFile getFoto() {
                return foto;
            }

            public void setFoto(FormFile foto) {
                this.foto = foto;
    }


    public ActionErrors validate(ActionMapping actionMapping,
                                 HttpServletRequest httpServletRequest) {

        return null;
    }

    public void reset(ActionMapping actionMapping,
                      HttpServletRequest servletRequest) {
    }
}

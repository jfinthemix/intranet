package proyecto_uoct.infoyrep.controller;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.upload.FormFile;

public class IngReportajeActionForm extends ActionForm {
    private String desc_rep;
    private String hacia;
    private String tipo_rep;
    private String tit_rep;
    private FormFile foto;
    private String idRep;

    public String getDesc_rep() {
        return desc_rep;
    }

    public void setDesc_rep(String desc_rep) {
        this.desc_rep = desc_rep;
    }

    public void setTit_rep(String tit_rep) {
        this.tit_rep = tit_rep;
    }

    public void setTipo_rep(String tipo_rep) {
        this.tipo_rep = tipo_rep;
    }

    public void setHacia(String hacia) {
        this.hacia = hacia;
    }


    public void setFoto(FormFile foto){
    this.foto=foto;
    }



    public void setIdRep(String idRep){
        this.idRep=idRep;
    }





    public String getHacia() {
        return hacia;
    }

    public String getTipo_rep() {
        return tipo_rep;
    }

    public String getTit_rep() {
        return tit_rep;
    }

    public FormFile getFoto(){
        return this.foto;
    }


    public String getIdRep(){
        return  this.idRep;
}


    public ActionErrors validate(ActionMapping actionMapping,
                                 HttpServletRequest httpServletRequest) {
        return null;
    }

    public void reset(ActionMapping actionMapping,
                      HttpServletRequest servletRequest) {
    }
}

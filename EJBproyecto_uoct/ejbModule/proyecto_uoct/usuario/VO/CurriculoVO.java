package proyecto_uoct.usuario.VO;

import org.apache.struts.upload.FormFile;

public class CurriculoVO {
    public CurriculoVO() {
    }

    private FormFile curr = null;
    private String nomCurr = null;
    private Integer id_usu = null;

    public void setCurr(FormFile c) {
        this.curr = c;
    }

    public void setNomCurr(String nom) {
        this.nomCurr = nom;
    }


    public void setIdUsu(Integer id) {
        this.id_usu = id;
    }


    public FormFile getCurr() {
        return this.curr;
    }

    public String getNomCurr() {
        return this.nomCurr;
    }


    public Integer getIdUsu() {
        return this.id_usu;
    }


}

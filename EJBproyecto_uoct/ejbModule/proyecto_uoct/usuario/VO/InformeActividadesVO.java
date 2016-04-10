package proyecto_uoct.usuario.VO;

import java.io.File;
import java.util.Date;
import org.apache.struts.upload.FormFile;

public class InformeActividadesVO {
    public InformeActividadesVO() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Integer idInforme = null;
    private Integer idUsu = null;
    private Date fechaInforme = null;
    private FormFile ffInforme = null;
    private String nomInforme = null;
    private byte[] informeB = null;
    private File informeFile = null;

    public void setIdInfor(Integer id) {
        this.idInforme = id;
    }

    public void setIdUsu(Integer id) {
        this.idUsu = id;
    }

    public void setFechaInfor(Date fecha) {
        this.fechaInforme = fecha;
    }

    public void setFormFile(FormFile ff) {
        this.ffInforme = ff;
    }

    public void setNomInfor(String nom) {
        this.nomInforme = nom;
    }

    public void setInformeB(byte[] inf) {
        this.informeB = inf;
    }

    public void setInformeFile(File inf) {
        this.informeFile = inf;
    }

    
    
    
    public Integer getIdUsu() {
        return this.idUsu;
    }

    public Date getFechaInfor() {
        return this.fechaInforme;
    }

    public FormFile getFormFile() {
        return this.ffInforme;
    }


    public Integer getIdInfor() {
        return this.idInforme;
    }

    private void jbInit() throws Exception {
    }

    public String getNomInfor() {
        return this.nomInforme;
    }

    public byte[] getInformeB() {
        return this.informeB;
    }

    public File getInformeFile() {
        return this.informeFile;
    }
    
}

package proyecto_uoct.documentacion.VO;

import java.util.Date;
import java.util.List;


public class DocumentoVO extends DocumentodeListaVO {
    public DocumentoVO() {
    }

    private Date fechaDoc = null;
    private List responsableDoc = null;
    private List docsRel = null;
    private List docsPosteriores = null;
    private String resumenDoc = null;
    private String sentidoDoc = null;
    private Integer id_cli = null;
    private String nom_cli = null;
    private String ape_cli = null;
    private String entidad = null;
    private List archivos = null;
    private boolean activo = false;
    private Integer idEntExt = null;
    public void setFechaDoc(Date fe) {
        this.fechaDoc = fe;
    }

    public void setResponsableDoc(List r) {
        this.responsableDoc = r;
    }

    public void setDocsRelacionados(List d) {
        this.docsRel = d;
    }

    public void setDocsPosteriores(List d) {
        this.docsPosteriores = d;
    }

    public void setResumen(String res) {
        this.resumenDoc = res;
    }

    public void setSentidoDoc(String sen) {
        this.sentidoDoc = sen;
    }


    public void setIdCli(Integer id) {
        this.id_cli = id;
    }

    public void setNomCli(String nom) {
        this.nom_cli = nom;
    }

    public void setApeCli(String ap) {
        this.ape_cli = ap;
    }

    public void setEntidad(String ent) {
        this.entidad = ent;
    }

    public void setArchivos(List archs) {
        this.archivos = archs;
    }

    public void setIsActivo(Integer i) {
        if (i.intValue() == 0) {
            this.activo = false;
        } else {
            this.activo = true;
        }
    }

    public void setIdEntidad(Integer id) {
        this.idEntExt = id;
    }

    //-------- GETTERS

    public Date getFechaDoc() {
        return this.fechaDoc;
    }

    public List getResponsableDoc() {
        return this.responsableDoc;
    }

    public List getDocsRelacionados() {
        return this.docsRel;
    }

    public List getDocsPosteriores() {
    return this.docsPosteriores;
}


    public String getResumen() {
        return this.resumenDoc;
    }

    public String getSentidoDoc() {
        return this.sentidoDoc;
    }

    public Integer getIdCli() {
        return this.id_cli;
    }


    public String getNomCli() {
        return this.nom_cli;
    }

    public String getApeCli() {
        return this.ape_cli;
    }

    public String getEntidad() {
        return this.entidad;
    }

    public List getArchivos() {
        return this.archivos;
    }

    public boolean getIsActivo() {
        return this.activo;
    }

    public Integer getIdEntidad() {
        return this.idEntExt;
    }

}

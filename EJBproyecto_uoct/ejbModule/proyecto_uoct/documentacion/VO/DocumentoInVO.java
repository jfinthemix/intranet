package proyecto_uoct.documentacion.VO;

import org.apache.struts.upload.FormFile;

public class DocumentoInVO {
    private Integer idDoc = null;
    private Integer idTipoDoc = null;
    private String tipoDoc = null;
    private String codDoc = null;
    private Integer idRemitente = null;
    private String mat = null;
    private String fechaIN = null;
    private String fechaDoc = null;
    private Integer[] ant = null;
    private Integer[] responsable = null;
    private String resumen = null;
    private FormFile file = null;
    private FormFile file1 = null;
    private FormFile file2 = null;


    public void setIdDoc(Integer idDoc) {
        this.idDoc = idDoc;
    }

    public void setIdTipoDoc(Integer idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

    public void setTipoDoc(String tipo) {
        this.tipoDoc = tipo;
    }

    public void setCodDoc(String codigo) {
        this.codDoc = codigo;
    }

    public void setIdRemitente(Integer idRemit) {
        this.idRemitente = idRemit;
    }

    public void setMat(String mat) {
        this.mat = mat;
    }

    public void setFechaIn(String fechaIn) {
        this.fechaIN = fechaIn;
    }

    public void setFechaDoc(String fechaDoc) {
        this.fechaDoc = fechaDoc;
    }

    public void setAnt(Integer[] ant) {
        this.ant = ant;
    }

    public void setResponsable(Integer[] responsables) {
        this.responsable = responsables;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public void setFile(FormFile archiv) {
        this.file = archiv;
    }

    public void setFile1(FormFile file) {
        this.file1 = file;
    }

    public void setFile2(FormFile file) {
        this.file2 = file;
    }


    //--------- getters

    public Integer getIdDoc() {
        return this.idDoc;
    }

    public Integer getIdTipoDoc() {
        return this.idTipoDoc;
    }

    public String getTipoDoc() {
        return this.tipoDoc;
    }

    public String getCodDoc() {
        return this.codDoc;
    }

    public Integer getIdRemitente() {
        return this.idRemitente;
    }

    public String getMat() {
        return this.mat;
    }

    public String getFechaIn() {
        return this.fechaIN;
    }

    public String getFechaDoc() {
        return this.fechaDoc;
    }

    public Integer[] getAnt() {
        return this.ant;
    }

    public Integer[] getResponsable() {
        return this.responsable;
    }

    public String getResumen() {
        return this.resumen;
    }

    public FormFile getFile() {
        return this.file;
    }

    public FormFile getFile1() {
        return this.file1;
    }

    public FormFile getFile2() {
        return this.file2;
    }


}

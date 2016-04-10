package proyecto_uoct.documentacion.VO;

import java.util.Date;


public class DocumentodeListaVO {
    public DocumentodeListaVO() {
    }

    private Integer idDoc = null;
    private String codDoc = null;
    private Integer idTipoDoc = null;
    private String tipoDoc = null;
    private boolean idEstado = false;
    private String estadoDoc = null;
    private String materiaDoc = null;
    private Date fechaio = null;
    private String rutaDoc = null;
    private boolean activo = false;

    public void setIdDoc(Integer id) {
        this.idDoc = id;
    }

    public void setCodDoc(String cod) {
        this.codDoc = cod;
    }

    public void setIdTipoDoc(Integer idt) {
        this.idTipoDoc = idt;
    }

    public void setTipoDoc(String tipo) {
        this.tipoDoc = tipo;
    }

    public void setIdEstado(boolean estado) {
        this.idEstado = estado;
    }

    public void setEstado(String est) {
        this.estadoDoc = est;
    }

    public void setMateriaDoc(String materia) {
        this.materiaDoc = materia;
    }

    public void setFechaio(Date fecha) {
        this.fechaio = fecha;
    }

    public void setRutaDoc(String r) {
        this.rutaDoc = r;
    }

    public void setIsActivo(Integer i) {
        if (i.intValue() == 1) {
            this.activo = true;
        } else {
            this.activo = false;
        }
    }

    // GETTERS!!--------------------------------

    public Integer getIdDoc() {
        return this.idDoc;
    }

    public String getCodDoc() {
        return this.codDoc;
    }

    public Integer getIdTipoDoc() {
        return this.idTipoDoc;
    }

    public String getTipoDoc() {
        return this.tipoDoc;
    }

    public boolean getIdEstado() {
        return this.idEstado;
    }

    public String getEstado() {
        return this.estadoDoc;
    }

    public String getMateriaDoc() {
        return this.materiaDoc;
    }

    public Date getFechaio() {
        return this.fechaio;
    }

    public String getRutaDoc() {
        return this.rutaDoc;
    }

    public boolean getIsActivo() {
        return this.activo;
    }

}

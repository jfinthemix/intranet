package proyecto_uoct.EIV.VO;

import java.util.Date;

public class EventoVO {
    public EventoVO() {
    }

    private Date fechaEv = null;
    private String desc = null;
    private String titulo = null;
    private Integer id_docRel = null;
    private String codDoc = null;
    private String tipoDoc = null;
    private Integer id_evento = null;
    private Integer idTipoEvento = null;
    private String tipoEvento = null;


    public void setFechaEv(Date fe) {
        this.fechaEv = fe;

    }

    public void setDescEv(String d) {
        this.desc = d;
    }

    public void setIdDocRel(Integer i) {
        this.id_docRel = i;
    }

    public void setIdEvento(Integer idev) {
        this.id_evento = idev;
    }

    public void setTitulo(String tit) {
        this.titulo = tit;
    }

    public void setCodDoc(String cod) {
        this.codDoc = cod;
    }

    public void setTipoDoc(String tipodoc) {
        this.tipoDoc = tipodoc;
    }

    public void setIdTipoEvento(Integer idTipoEvento) {
        this.idTipoEvento = idTipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

//------------------------------Getters

    public Date getFechaEv() {
        return this.fechaEv;
    }

    public String getDescEv() {
        return this.desc;
    }

    public Integer getIdDocRel() {
        return this.id_docRel;
    }

    public Integer getIdEvento() {
        return this.id_evento;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getCodDoc() {
        return this.codDoc;
    }

    public String getTipoDoc() {
        return this.tipoDoc;
    }

    public Integer getIdTipoEvento() {
        return this.idTipoEvento;
    }

    public String getTipoEvento() {
        return this.tipoEvento;
    }

}

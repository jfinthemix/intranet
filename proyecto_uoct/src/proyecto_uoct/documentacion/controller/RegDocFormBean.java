package proyecto_uoct.documentacion.controller;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.upload.FormFile;
import java.lang.System;

public class RegDocFormBean extends ActionForm {
    private String asunto;
    private String descrip;
    private FormFile eldoc = null;
    private FormFile eldoc1 = null;
    private FormFile eldoc2 = null;
    private String fecha_IO;
    private String fecha_doc;
    private String hacia;
    private String id_cliente;
    private String id_docEntrante;
    private String id_docSaliente;
    private String id_tipoEntrante;
    private String id_tipoSaliente;
    private String list1;
    private String listaRel;
    private String sentido;
    private String id_tipoRel;


    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public void setSentido(String sentido) {
        this.sentido = sentido;
    }

    public void setListaRel(String listaRel) {
        this.listaRel = listaRel;
    }

    public void setList1(String list1) {
        this.list1 = list1;
    }

    public void setId_tipoSaliente(String id_tipoSaliente) {
        this.id_tipoSaliente = id_tipoSaliente;
    }

    public void setId_tipoEntrante(String id_tipoEntrante) {
        this.id_tipoEntrante = id_tipoEntrante;
    }

    public void setId_docSaliente(String id_docSaliente) {
        this.id_docSaliente = id_docSaliente;
    }

    public void setId_docEntrante(String id_docEntrante) {
        this.id_docEntrante = id_docEntrante;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setHacia(String hacia) {
        this.hacia = hacia;
    }

    public void setFecha_doc(String fecha_doc) {
        this.fecha_doc = fecha_doc;
    }

    public void setFecha_IO(String fecha_IO) {
        this.fecha_IO = fecha_IO;
    }

    public void setEldoc(FormFile eldoc) {
        this.eldoc = eldoc;
    }

    public void setEldoc1(FormFile eldoc1) {
        this.eldoc1 = eldoc1;
    }

    public void setEldoc2(FormFile eldoc2) {
        this.eldoc2 = eldoc2;
    }


    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public void set_IdtipoRel(String id_tipoRel) {
        this.id_tipoRel = id_tipoRel;
    }


    public String getDescrip() {
        return descrip;
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


    public String getFecha_IO() {
        return fecha_IO;
    }

    public String getFecha_doc() {
        return fecha_doc;
    }

    public String getHacia() {
        return hacia;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public String getId_docEntrante() {
        return id_docEntrante;
    }

    public String getId_docSaliente() {
        return id_docSaliente;
    }

    public String getId_tipoEntrante() {
        return id_tipoEntrante;
    }

    public String getId_tipoSaliente() {
        return id_tipoSaliente;
    }

    public String getList1() {
        return list1;
    }

    public String getListaRel() {
        return listaRel;
    }

    public String getSentido() {
        return sentido;
    }

    public String get_IdtipoRel() {
        return this.id_tipoRel;
    }


    public ActionErrors validate(ActionMapping actionMapping,
                                 HttpServletRequest httpServletRequest) {
        return null;
    }

    public void reset(ActionMapping actionMapping,
                      HttpServletRequest servletRequest) {
    }

    public void reset() {

        asunto = null;
        descrip = null;
        eldoc = null;
        eldoc1 = null;
        eldoc2 = null;
        fecha_IO = null;
        fecha_doc = null;
        hacia = null;
        id_cliente = null;
        id_docEntrante = null;
        id_docSaliente = null;
        id_tipoEntrante = null;
        id_tipoSaliente = null;
        list1 = null;
        listaRel = null;
        sentido = null;
        id_tipoRel = null;

    }
}

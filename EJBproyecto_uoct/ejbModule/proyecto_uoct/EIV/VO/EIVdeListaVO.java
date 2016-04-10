package proyecto_uoct.EIV.VO;

import java.util.Date;

public class EIVdeListaVO {
    public EIVdeListaVO() {
    }

    private Integer idEIV = null;
//    private Integer id_tipo_doc=null;
    private String nomEiv = null;
    private String nom_encargado = null;
    private String nom2_encargado = null;
    private String ape_encargado = null;
    private String nom_cons = null;
    private String empCons = null;
    private Date fecha_venci = null;
    private String estado = null;
    private Integer idEstado = null;

    public void setIdEIV(Integer id) {
        this.idEIV = id;
    }

    public void setNomEiv(String nom) {
        this.nomEiv = nom;
    }

    public void setNomEncarg(String nom) {
        this.nom_encargado = nom;
    }

    public void setNom2Encarg(String nom) {
        this.nom2_encargado = nom;
    }

    public void setApeEncarg(String ape) {
        this.ape_encargado = ape;
    }


    public void setNomCons(String nom) {
        this.nom_cons = nom;
    }


    public void setEmpCons(String ent) {
        this.empCons = ent;
    }

    public void setFechaVenc(Date fe) {
        this.fecha_venci = fe;
    }

    public void setEstado(String es) {
        this.estado = es;
    }


    public void setIdEstado(Integer id) {
        this.idEstado = id;
    }

//-------------GETTERS--------------

    public Integer getIdEIV() {
        return this.idEIV;
    }

    public String getNomEiv() {
        return this.nomEiv;
    }

    public String getNomEncarg() {
        return this.nom_encargado;
    }

    public String getNom2Encarg() {
        return this.nom2_encargado;
    }

    public String getApeEncarg() {
        return this.ape_encargado;
    }


    public String getNomCons() {
        return this.nom_cons;
    }


    public String getEmpCons() {
        return this.empCons;
    }

    public Date getFechaVenc() {
        return this.fecha_venci;
    }

    public String getEstado() {
        return this.estado;
    }


    public Integer getIdEstado() {
        return this.idEstado;
    }
}

package proyecto_uoct.EIV.VO;

import java.util.Date;

public class FlujoVO {
    public FlujoVO() {
    }


    private Date fecha = null;
    private Integer id_tipodia = null;
    private String tipoDia = null;
    private String interseccion = null;
    private Integer idEIV = null;
    private String calle1 = null;
    private String calle2 = null;
    private String horasMed = null;
    private Integer idFlujo=null;


    public void setFecha(Date f) {
        this.fecha = f;
    }

    public void setTipoDia(String tdia) {
        this.tipoDia = tdia;
    }

    public void setInterseccion(String i) {
        this.interseccion = i;
    }


    public void setIdEIV(Integer id) {
        this.idEIV = id;
    }

    public void setIdTipoDia(Integer td) {
        this.id_tipodia = td;
    }

    public void setCalle1(String calle1) {
        this.calle1 = calle1;
    }

    public void setCalle2(String calle2) {
        this.calle2 = calle2;
    }

    public void setHorasMed(String horasmed) {
        this.horasMed = horasmed;

    }

    public void setIdFlujo(Integer idFlu){
        this.idFlujo=idFlu;
    }

    //-------------GETTERS

    public Date getFecha() {
        return this.fecha;
    }

    public String getTipoDia() {
        return this.tipoDia;
    }

    public String getInterseccion() {
        return this.interseccion;
    }


    public Integer getIdEIV() {
        return this.idEIV;
    }

    public Integer getIdTipoDia() {
        return this.id_tipodia;
    }

    public String getCalle1() {
        return this.calle1;
    }

    public String getCalle2() {
        return this.calle2;
    }

    public String getHorasMed() {
        return this.horasMed;

    }

    public Integer getIdFlujo(){
        return  this.idFlujo;
    }

}

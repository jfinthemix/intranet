package proyecto_uoct.ventas.VO;

import java.util.Date;

public class BusVtaVO {
    public BusVtaVO() {
    }

    private Integer idCli = null;
    private Date fechaIni = null;
    private Date fechaIni2 = null;
    private Integer idEstado = null;
    private String palClave = null;

    public void setIdCli(Integer id) {
        this.idCli = id;
    }

    public void setFechaIni(Date fecha) {
        this.fechaIni = fecha;
    }

    public void setFechaIni2(Date fecha) {
        this.fechaIni2 = fecha;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public void setPalClave(String palClave) {
        this.palClave = palClave;
    }

//-------GETTERS-------

    public Integer getIdCli() {
        return this.idCli;
    }

    public Date getFechaIni() {
        return this.fechaIni;
    }

    public Date getFechaIni2() {
        return this.fechaIni2;
    }

    public Integer getIdEstado() {
        return this.idEstado;
    }

    public String getPalClave() {
        return this.palClave;
    }

}

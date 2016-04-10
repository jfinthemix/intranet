package proyecto_uoct.proyecto.VO;

import java.util.Date;

public class OTdeListaVO extends proyecto_uoct.common.VO.IdStrVO {
    public OTdeListaVO() {
    }

    private String estadoOT = null;
    private Date fechaInicio = null;
    private Date fechaVenc = null;

    public void setEstado(String es) {
        this.estadoOT = es;
    }

    public void setFechaInicio(Date f) {
        this.fechaInicio = f;
    }

    public void setFechaVencimiento(Date v) {
        this.fechaVenc= v;
    }

//----------------------------------------------

    public String getEstadoOT() {
        return this.estadoOT;
    }

    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public Date getFechaVencimiento() {
        return this.fechaVenc;
    }

}

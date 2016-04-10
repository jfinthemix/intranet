package proyecto_uoct.proyecto.VO;

public class BusOTVO {
    public BusOTVO() {
    }

    private Integer idOT = null;
    private String palClave = null;
    private Integer idEstado = null;
    private String fechaVenc = null;

    public void setIdOT(Integer id) {
        this.idOT = id;
    }

    public void setPalClave(String palClave) {
        this.palClave = palClave;
    }

    public void setIdEstado(Integer id) {
        this.idEstado = id;
    }

    public void setFechaVenc(String fechaVen) {
        this.fechaVenc = fechaVen;
    }


// Getters

    public Integer getIdOT() {
        return this.idOT;
    }

    public String getPalClave() {
        return this.palClave;
    }

    public Integer getIdEstado() {
        return this.idEstado;
    }

    public String getFechaVenc() {
        return this.fechaVenc;
    }

}

package proyecto_uoct.EIV.VO;

public class BusFlujosVO {

    private Integer idEiv = null;
    private String fecha = null;
    private String calles = null;

    public void setIdEIV(Integer ideiv) {
        this.idEiv = ideiv;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setCalles(String calles) {
        this.calles = calles;
    }


    //--------------GETTERS


    public Integer getIdEIV() {
        return this.idEiv;
    }

    public String getFecha() {
        return this.fecha;
    }

    public String getCalles() {
        return this.calles;
    }
}

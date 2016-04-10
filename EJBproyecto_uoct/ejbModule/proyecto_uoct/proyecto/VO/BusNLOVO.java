package proyecto_uoct.proyecto.VO;


public class BusNLOVO {
    public BusNLOVO() {
    }

    private Integer idNLO = null;
    private String palClave = null;
    private String fechaNLO = null;


    public void setIdNLO(Integer id) {
        this.idNLO = id;
    }

    public void setPalClave(String pal) {
        this.palClave = pal;
    }

    public void setFechaNLO(String fecha) {
        this.fechaNLO = fecha;
    }


    public Integer getIdNLO() {
        return this.idNLO;
    }

    public String getPalClave() {
        return this.palClave;
    }

    public String getFechaNLO() {
        return this.fechaNLO;
    }


}

package proyecto_uoct.EIV.VO;

public class BuscadorEIVVO {
    private String palClave = null;
    private Integer idEIV = null;
    private String fechaVenc = null;
    private String fechaVenc_b = null;
    private Integer idEstado = null;
    private Integer idIngeniero = null;
    private Integer idComuna = null;
    private Integer red = null;
    private String consultor = null;
    private Integer estadoSeremitt = null;

    public void setPalClave(String pal) {
        this.palClave = pal;
    }

    public void setIdEIV(Integer id) {
        this.idEIV = id;
    }

    public void setFechaVenc(String fechaVenc) {
        this.fechaVenc = fechaVenc;
    }

    public void setFechaVenc_b(String fechaVenc) {
        this.fechaVenc_b = fechaVenc;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public void setIdIngeniero(Integer idIng) {
        this.idIngeniero = idIng;
    }

    public void setIdComuna(Integer idComuna) {
        this.idComuna = idComuna;
    }

    public void setRed(Integer red) {
        this.red = red;
    }

    public void setConsultor(String cons) {
        this.consultor = cons;
    }

    public void setEstadoSeremitt(Integer estado) {
        this.estadoSeremitt = estado;
    }

//-------------------------GETTERS------------
    public String getPalClave() {
        return this.palClave;
    }

    public Integer getIdEIV() {
        return this.idEIV;
    }

    public String getFechaVenc() {
        return this.fechaVenc;
    }

    public String getFechaVenc_b() {
        return this.fechaVenc_b;
    }

    public Integer getIdEstado() {
        return this.idEstado;
    }

    public Integer getIdIngeniero() {
        return this.idIngeniero;
    }

    public Integer getIdComuna() {
        return this.idComuna;
    }

    public Integer getRed() {
        return this.red;
    }

    public String getConsultor() {
        return this.consultor;
    }

    public Integer getEstadoSeremitt() {
        return this.estadoSeremitt;
    }

}

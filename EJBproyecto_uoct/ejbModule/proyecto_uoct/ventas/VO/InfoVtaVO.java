package proyecto_uoct.ventas.VO;

public class InfoVtaVO {
    public InfoVtaVO() {
    }

    private Integer idVta = null;
    private Integer idInfo = null;
    private Integer cantidad = null;
    private String tipoInfo = null;
    private String unidad = null;
    private Float precio = null;
    private boolean isActivo = false;
    private String descripcion = null;
    private Float precioPago=null;
    private Integer idDetalle=null;


    public void setIdVta(Integer id) {
        this.idVta = id;
    }

    public void setIdInfo(Integer id) {
        this.idInfo = id;
    }

    public void setCantidad(Integer cant) {
        this.cantidad = cant;
    }

    public void setTipoInfo(String tipo) {
        this.tipoInfo = tipo;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public void setIsActivo(Integer activ) {
        if (activ.intValue() == 1) {
            this.isActivo = true;
        }
    }

    public void setDescripcion(String desc) {
        this.descripcion = desc;
    }

    public void setPrecioPago(Float precio){
        this.precioPago=precio;
    }

    public void setIdDetalle(Integer idDet){
        this.idDetalle=idDet;
    }

    //----------GETTERS--------
    public Integer getIdVta() {
        return this.idVta;
    }

    public Integer getIdInfo() {
        return this.idInfo;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }

    public String getTipoInfo() {
        return this.tipoInfo;
    }

    public String getUnidad() {
        return this.unidad;
    }

    public Float getPrecio() {
        return this.precio;
    }

    public boolean getIsActivo() {
        return this.isActivo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }
    public Float getPrecioPago(){
        return this.precioPago;
    }

    public Integer getIdDetalle(){
        return  this.idDetalle;
    }

}

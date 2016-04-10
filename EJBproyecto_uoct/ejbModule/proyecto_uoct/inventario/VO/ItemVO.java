package proyecto_uoct.inventario.VO;

public class ItemVO {


    private Integer idItem = null;
    private String nomItem = null;
    private Integer idCategoria = null;
    private String nomCategoria = null;

    private Integer idTUnidad = null;
    private String unidad = null;

    private Integer cantidadItem = null;


    public void setIdItem(Integer id) {
        this.idItem = id;
    }

    public void setNomItem(String nom) {
        this.nomItem = nom;
    }

    public void setIdCategoria(Integer idCat) {
        this.idCategoria = idCat;
    }

    public void setNomCategoria(String nomCat) {
        this.nomCategoria = nomCat;
    }

    public void setIdUnidad(Integer idUni) {
        this.idTUnidad = idUni;
    }

    public void setUnidad(String tuni) {
        this.unidad = tuni;
    }

    public void setCantidadItem(Integer cant) {
        this.cantidadItem = cant;
    }

//GETTERS

    public Integer getIdItem() {
        return this.idItem;
    }

    public String getNomItem() {
        return this.nomItem;
    }

    public Integer getIdCategoria() {
        return this.idCategoria;
    }

    public String getNomCategoria() {
        return this.nomCategoria;
    }

    public Integer getIdUnidad() {
        return this.idTUnidad;
    }

    public String getUnidad() {
        return this.unidad;
    }

    public Integer getCantidadItem() {
        return this.cantidadItem;
    }


}

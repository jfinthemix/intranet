package proyecto_uoct.inventario.VO;

public class BusItemVO {
    private String palClave = null;
    private Integer idCategoria = null;

    public void setPalClave(String pal) {
        this.palClave = pal;
    }

    public void setIdCategoria(Integer idCat) {
        this.idCategoria = idCat;
    }


    public String getPalClave() {
        return this.palClave;
    }

    public Integer getIdCategoria() {
        return this.idCategoria;
    }


}

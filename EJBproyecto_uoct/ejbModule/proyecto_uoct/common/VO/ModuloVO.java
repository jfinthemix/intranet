package proyecto_uoct.common.VO;

public class ModuloVO {
    public ModuloVO() {
    }

    private Integer id_modulo = null;
    private String nombre_modulo = null;

    public void setIdModulo(Integer id) {
        this.id_modulo = id;
    }

    public void setNombreModulo(String nombre) {
        this.nombre_modulo = nombre;
    }


    public Integer getIdModulo() {
        return this.id_modulo;
    }

    public String getNombreModulo() {
        return this.nombre_modulo;
    }


}

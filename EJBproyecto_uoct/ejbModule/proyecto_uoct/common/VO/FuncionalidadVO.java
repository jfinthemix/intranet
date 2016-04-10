package proyecto_uoct.common.VO;

public class FuncionalidadVO {
    public FuncionalidadVO() {
    }

    private Integer id_funcionalidad = null;
    private String nombre_fun = null;
    private String url_fun = null;
    private Integer id_modulo = null;
    private Integer popup = null;
    private String params_popup = null;

    public void setIdFuncionalidad(Integer idfun) {
        this.id_funcionalidad = idfun;
    }

    public void setNombreFun(String nombre) {
        this.nombre_fun = nombre;
    }

    public void setURLFun(String url) {
        this.url_fun = url;
    }

    public void setIdModulo(Integer idmod) {
        this.id_modulo = idmod;
    }

    public void setPopup(Integer pop) {
        this.popup = pop;
    }

    public void setParams_popup(String params) {
        this.params_popup = params;
    }


    public Integer getIdFuncionalidad() {
        return this.id_funcionalidad;
    }

    public String getNombreFun() {
        return this.nombre_fun;
    }

    public String getURLFun() {
        return this.url_fun;
    }

    public Integer getIdModulo() {
        return this.id_modulo;
    }

    public Integer getPopup() {
        return this.popup;
    }

    public String getParams_popup() {
        return this.params_popup;
    }


}

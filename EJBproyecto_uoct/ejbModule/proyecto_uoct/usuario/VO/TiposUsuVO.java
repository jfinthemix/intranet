package proyecto_uoct.usuario.VO;

public class TiposUsuVO {
    public TiposUsuVO() {
    }

    private Integer id_tipo_usu=null;
    private String tipo_usu=null;

    public void setIdTipoUsu(Integer tipo){
        this.id_tipo_usu=tipo;
    }

    public void setTipoUsu(String tipo){
        this.tipo_usu=tipo;
    }

    public Integer getIdTipoUsu(){
        return this.id_tipo_usu;
    }
    public String getTipoUsu(){
        return this.tipo_usu;
    }
}

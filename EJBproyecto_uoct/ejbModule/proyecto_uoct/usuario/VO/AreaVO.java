package proyecto_uoct.usuario.VO;

public class AreaVO {
    public AreaVO() {
    }

    Integer id_area=null;
    String area=null;

    public void setIdArea(Integer id_area){
        this.id_area=id_area;
    }

    public void setArea(String area){
        this.area=area;
    }

    public Integer getIdArea(){
        return this.id_area;
    }

    public String getArea(){
        return this.area;
    }
}

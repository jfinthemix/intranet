package proyecto_uoct.foro.VO;

public class TemasVO {

    Integer id_tema;
    String tema;

    public TemasVO() {
    }
    public void setIdTema(Integer id_del_tema){
        this.id_tema=id_del_tema;
    }

    public Integer getIdTema(){
        return this.id_tema;
    }
    public void setTema(String el_tema){
        this.tema=el_tema;
    }

    public String getTema(){
       return this.tema;
    }

}

package proyecto_uoct.common.VO;

public class IdStrVO {
    public IdStrVO() {
    }

    private Integer id=null;
    private String str = null;

    public void setId(Integer idd){
        this.id=idd;
    }
    public void setStr(String strr){
        this.str=strr;
    }

    public Integer getId(){
        return this.id;
    }

    public String getStr(){
        return this.str;
    }

}

package proyecto_uoct.documentacion.VO;

public class EntExtVO {
    public EntExtVO() {
    }

    private Integer idEnt = null;
    private String nomEnt = null;
    private String fono_ent = null;
    private String dir_ent = null;
    private String web = null;
    private Integer activo = null;

    public void setIdEnt(Integer id) {
        this.idEnt = id;

    }

    public void setNomEnt(String no) {
        this.nomEnt = no;
    }

    public void setFonoEnt(String fono) {
        this.fono_ent = fono;
    }

    public void setDirEnt(String dir) {
        this.dir_ent = dir;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public void setIsActivo(Integer act) {
        this.activo = act;
    }

//--------- GETTERS!!

    public Integer getIdEnt() {
        return this.idEnt;

    }

    public String getNomEnt() {
        return this.nomEnt;
    }

    public String getFonoEnt() {
        return this.fono_ent;
    }

    public String getDirEnt() {
        return this.dir_ent;
    }

    public String getWeb() {
        return this.web;
    }

    public Integer getIsActivo() {
        return this.activo;
    }

}

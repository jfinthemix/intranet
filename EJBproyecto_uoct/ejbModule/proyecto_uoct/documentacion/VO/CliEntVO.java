package proyecto_uoct.documentacion.VO;

public class CliEntVO {
    public CliEntVO() {
    }

    private Integer idCli = null;
    private String nomCli = null;
    private String ape_cli = null;
    private Integer idEnt = null;
    private String nom_ent = null;
    private Integer isActivo = null;

    public void setIdCli(Integer id_u) {
        this.idCli = id_u;
    }

    public void setNomCli(String n) {
        this.nomCli = n;
    }

    public void setApeCli(String o) {
        this.ape_cli = o;
    }

    public void setIdEnt(Integer d) {
        this.idEnt = d;
    }

    public void setNomEnt(String nom_ent) {
        this.nom_ent = nom_ent;
    }

    public void setIsActivo(Integer activo) {
        this.isActivo = activo;
    }

//-------------------------------------------------------

    public Integer getIdCli() {
        return this.idCli;
    }

    public String getNomCli() {
        return this.nomCli;
    }

    public String getApeCli() {
        return this.ape_cli;
    }

    public Integer getIdEnt() {
        return this.idEnt;
    }

    public String getNomEnt() {
        return this.nom_ent;
    }

    public Integer getIsActivo() {
        return this.isActivo;
    }

}

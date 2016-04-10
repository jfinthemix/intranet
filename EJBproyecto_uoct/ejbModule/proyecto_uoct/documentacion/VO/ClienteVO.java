package proyecto_uoct.documentacion.VO;

public class ClienteVO {
    public ClienteVO() {
    }

    private Integer id_cli = null;
    private String nom_cli = null;
    private String ape_cli = null;
    private String fono_cli = null;
    private String email_cli = null;
    private String cel_cli = null;
    private String comentario_cli = null;
    private String nom_ent = null;
    private String cargo = null;
    private EntExtVO entidad = new EntExtVO();
    private Integer activo = null;

    public void setIdCli(Integer id_u) {
        this.id_cli = id_u;
    }

    public void setNomCli(String n) {
        this.nom_cli = n;
    }

    public void setApeCli(String o) {
        this.ape_cli = o;
    }

    public void setFonoCli(String fono) {
        this.fono_cli = fono;
    }

    public void setEmailCli(String email) {
        this.email_cli = email;
    }

    public void setCelCli(String cel) {
        this.cel_cli = cel;
    }

    public void setComentCli(String com) {
        this.comentario_cli = com;
    }

    public void setIdEnt(Integer i) {
        this.entidad.setIdEnt(i);
    }

    public void setNomEnt(String nom) {
        this.entidad.setNomEnt(nom);
    }

    public void setTelefonoEnt(String fono) {
        this.entidad.setFonoEnt(fono);
    }

    public void setDirEnt(String dirEnt) {
        this.entidad.setDirEnt(dirEnt);
    }

    public void setWebEnt(String web) {
        this.entidad.setWeb(web);
    }


    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setIsActivo(Integer activo) {
        this.activo = activo;
    }


    // GETTERS
    public Integer getIdCli() {
        return this.id_cli;
    }

    public String getNomCli() {
        return this.nom_cli;
    }

    public String getApeCli() {
        return this.ape_cli;
    }


    public String getFonoCli() {
        return this.fono_cli;
    }

    public String getEmailCli() {
        return this.email_cli;
    }

    public String getCelCli() {
        return this.cel_cli;
    }

    public String getComentCli() {
        return this.comentario_cli;
    }


    public String getCargo() {
        return this.cargo;
    }


    public Integer getIdEnt() {
        return this.entidad.getIdEnt();
    }

    public String getNomEnt() {
        return this.entidad.getNomEnt();
    }

    public String getTelefonoEnt() {
        return this.entidad.getFonoEnt();
    }

    public String getDirEnt() {
        return this.entidad.getDirEnt();
    }

    public String getWebEnt() {
        return this.entidad.getWeb();
    }

    public Integer getIsActivo() {
        return this.activo;
    }


}

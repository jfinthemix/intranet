package proyecto_uoct.common.VO;

public class DatosSesVO {
    public DatosSesVO() {
    }

    private Integer id_usu = null;
    private String nom_usu = null;
    private String nom2_usu = null;
    private String ape_usu = null;
    private Integer id_perfil = null;


    public void setId(Integer id) {
        this.id_usu = id;
    }

    public void setNom(String nom) {
        this.nom_usu = nom;
    }

    public void setNom2(String nom2) {
        this.nom2_usu = nom2;
    }

    public void setAp(String ap) {
        this.ape_usu = ap;
    }

    public void setIdPerfil(Integer id) {
        this.id_perfil = id;
    }



    //getters

    public Integer getId() {
        return this.id_usu;
    }

    public String getNom() {
        return this.nom_usu;
    }

    public String getNom2() {
        return this.nom2_usu;
    }

    public String getAp() {
        return this.ape_usu;
    }

    public Integer getIdPerfil() {
        return this.id_perfil;
    }

}

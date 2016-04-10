package proyecto_uoct.usuario.VO;

public class AnexoVO {
    public AnexoVO() {
    }

    private Integer idAnexo = null;
    private String nom_anexo = null;
    private String anexo = null;

    public void setIdAnexo(Integer id) {
        this.idAnexo = id;
    }

    public void setNomAnexo(String nom) {
        this.nom_anexo = nom;
    }

    public void setAnexo(String anx) {
        this.anexo = anx;
    }


    public Integer getIdAnexo() {
        return this.idAnexo;
    }

    public String getNomAnexo() {
        return this.nom_anexo;
    }

    public String getAnexo() {
        return this.anexo;
    }


}

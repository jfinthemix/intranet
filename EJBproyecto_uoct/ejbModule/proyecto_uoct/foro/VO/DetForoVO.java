package proyecto_uoct.foro.VO;

import java.sql.Date;

public class DetForoVO {

    private Integer idForo = null;
    private String titForo = null;
    private String temaForo = null;
    private Date fechaIni = null;
    private String descForo = null;
    private String adminForo = null;
    private Integer idTema = null;
    private Integer id_usu = null;
    private String post = null;
    private String fechaIni_s = null;


    public DetForoVO() {
    }


    // SETTERS !!!  DAN UN VALOR AL ATRIBUTO DEL OBJETO

    public void setIdForo(Integer id_foro) {
        this.idForo = id_foro;
    }

    public void setTitForo(String titforo) {
        this.titForo = titforo;
    }

    public void setTemaForo(String temaforo) {
        this.temaForo = temaforo;
    }

    public void setFechaIniForo(Date fechaini) {
        this.fechaIni = fechaini;
    }

    public void setDescForo(String descforo) {
        this.descForo = descforo;
    }

    public void setAdminForo(String admin) {
        this.adminForo = admin;
    }

    public void setFechaIni_s(String fecha) {
        this.fechaIni_s = fecha;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setIdUsu(Integer idusu) {
        this.id_usu = idusu;
    }

    public void setIdTema(Integer idtema) {
        this.idTema = idtema;
    }


    //GETTERS!!!! DEVUELVEN EL VALOR DEL ATRIBUTO DEL OBJETO

    public Integer getIdForo() {
        return this.idForo;
    }

    public String getTitForo() {
        return this.titForo;
    }

    public String getTemaForo() {
        return this.temaForo;
    }

    public Date getFechaIniForo() {
        return this.fechaIni;
    }

    public String getDescForo() {
        return this.descForo;
    }

    public String getAdminForo() {
        return this.adminForo;
    }

    public String getFechaIni_s() {
        return this.fechaIni_s;
    }

    public String getPost() {
        return this.post;
    }

    public Integer getIdUsu() {
        return this.id_usu;
    }

    public Integer getIdTema() {
        return this.idTema;
    }


}

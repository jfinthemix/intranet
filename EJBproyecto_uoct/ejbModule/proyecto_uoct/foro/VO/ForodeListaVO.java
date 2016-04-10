package proyecto_uoct.foro.VO;

import java.sql.Date;

public class ForodeListaVO {

    private Integer idForo=null;
    private String tituloForo=null;
    private String temaForo=null;
    private Date fechaForo=null;

    public ForodeListaVO() {
    }

    public void setIdForo(Integer idforo) {
        this.idForo = idforo;
    }

    public Integer getIdForo() {
       return this.idForo;
    }


    public void setTituloForo(String titulo) {
        this.tituloForo = titulo;
    }

    public String getTituloForo() {
       return this.tituloForo;
    }


    public void setFechaForo(Date fechainicio) {
        this.fechaForo = fechainicio;
    }

    public Date getFechaForo() {
       return this.fechaForo;
    }

    public void setTemaForo(String temaforo) {
        this.temaForo = temaforo;
    }

    public String getTemaForo() {
    return this.temaForo;
    }

}

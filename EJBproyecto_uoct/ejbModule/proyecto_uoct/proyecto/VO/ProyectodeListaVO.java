package proyecto_uoct.proyecto.VO;

import java.util.Date;
import java.util.List;
import proyecto_uoct.documentacion.VO.ClienteVO;

public class ProyectodeListaVO {
    public ProyectodeListaVO() {
    }

    private Integer idProy = null;
    private String nomProy = null;
    private Date fechaProy = null;
    private Integer idEncargado = null;
    private String encargado = null;
    private boolean isActivo = false;
    private ClienteVO cli = null;
    private boolean esDelEquipo = false;


    public void setIdProy(Integer id) {
        this.idProy = id;
    }

    public void setNomProy(String n) {
        this.nomProy = n;
    }

    public void setFechaProy(Date fe) {
        this.fechaProy = fe;
    }

    public void setIdEncargado(Integer id) {
        this.idEncargado = id;
    }

    public void setEncargado(String enc) {
        this.encargado = enc;
    }

    public void setIsActivo(Integer i) {
        if (i.intValue() == 1) {
            this.isActivo = true;
        }
    }

    public void setCli(ClienteVO cli) {
        this.cli = cli;
    }

    public void setEsDelEquipo(boolean pertenece) {
        this.esDelEquipo = pertenece;
    }

    //------------GETTERS

    public Integer getIdProy() {
        return this.idProy;
    }

    public String getNomProy() {
        return this.nomProy;
    }

    public Date getFechaProy() {
        return this.fechaProy;
    }

    public Integer getIdEncargado() {
        return this.idEncargado;
    }

    public String getEncargado() {
        return this.encargado;
    }

    public boolean getIsActivo() {
        return this.isActivo;
    }

    public ClienteVO getCli() {
        return this.cli;
    }

    public boolean getEsDelEquipo() {
        return this.esDelEquipo;
    }

}

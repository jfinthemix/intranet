package proyecto_uoct.EIV.VO;

import java.util.Date;
import java.util.List;

public class EIVVO extends EIVdeListaVO {
    public EIVVO() {
    }

    private Integer id_tipoest = null;
    private String tipo_estudio = null;
    private Date fecha_ingreso = null;
    private Date fecha_presentacion = null;
    private Date fecha_envioSeremitt = null;
    private Integer id_comuna = null;
    private String comuna = null;
    private String dir = null;
    private List redes = null;
    private Integer estacionamientos = null;
    private List flujos = null;
    private Integer id_encargado = null;
    private Integer idOficio = null;
    private String nomDocumento = null;
    private Integer estadoSeremitt = null;


    public void setIdTipoEstudio(Integer id) {
        this.id_tipoest = id;
    }

    public void setTipoEstudio(String tipo) {
        this.tipo_estudio = tipo;
    }

    public void setFechaIng(Date fe) {
        this.fecha_ingreso = fe;
    }

    public void setIdComuna(Integer idcom) {
        this.id_comuna = idcom;
    }

    public void setComuna(String co) {
        this.comuna = co;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public void setRedes(List red) {
        this.redes = red;
    }

    public void setEstacionamientos(Integer es) {
        this.estacionamientos = es;
    }

    public void setFlujos(List f) {
        this.flujos = f;
    }

    public void setIdEncargado(Integer i) {
        this.id_encargado = i;
    }

    public void setIdOficio(Integer id) {
        this.idOficio = id;
    }


    public void setFechaPresent(Date present) {
        this.fecha_presentacion = present;
    }

    public void setFechaEnvioSeremitt(Date envioSere) {
        this.fecha_envioSeremitt = envioSere;
    }

    public void setNomDocumento(String nomDoc) {
        this.nomDocumento = nomDoc;
    }

    public void setEstadoSeremitt(Integer idestado) {
        this.estadoSeremitt = idestado;
    }


    //--------GETTERS------------------------

    public String getTipoEstudio() {
        return this.tipo_estudio;
    }

    public Date getFechaIng() {
        return this.fecha_ingreso;
    }

    public Integer getIdComuna() {
        return this.id_comuna;
    }

    public String getComuna() {
        return this.comuna;
    }

    public String getDir() {
        return this.dir;
    }

    public List getRedes() {
        return this.redes;
    }

    public Integer getEstacionamientos() {
        return this.estacionamientos;
    }

    public List getFlujos() {
        return this.flujos;
    }

    public Integer getIdTipoEstudio() {
        return this.id_tipoest;
    }

    public Integer getIdEncargado() {
        return this.id_encargado;
    }

    public Integer getIdOficio() {
        return this.idOficio;
    }

    public Date getFechaPresent() {
        return this.fecha_presentacion;
    }

    public Date getFechaEnvioSeremitt() {
        return this.fecha_envioSeremitt;
    }

    public String getNomDocumento() {
        return this.nomDocumento;
    }

    public Integer getEstadoSeremitt() {
        return this.estadoSeremitt;
    }

}

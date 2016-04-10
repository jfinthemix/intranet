package proyecto_uoct.proyecto.VO;

import java.util.*;

import proyecto_uoct.documentacion.VO.*;

public class DetalleOTVO {
    public DetalleOTVO() {
    }

    private Integer idOT = null;
    private String nomOT = null;
    private Integer idEstado = null;
    private String estadoOT = null;
    private Date fecha_ot = null;
    private Date fechaSus = null;
    private Date vencimiento = null;
    private Date tareasConc = null;
    private Date aprobacion = null;
    private Integer plazo = null;
    private List nlos = null;
    private List encargados = null;
    private Integer idProy = null;
    private String ep = null;
    private Integer idTipoOT = null;
    private String tipoOT = null;
    private ClienteVO cli = null;
    private DocumentoInVO documento = null;
    private DetalleProyectoVO detProy = null;

    //---------setters




    public void setIdOT(Integer id) {
        this.idOT = id;
    }

    public void setNomOT(String nom) {
        this.nomOT = nom;
    }

    public void setIdEstado(Integer id) {
        this.idEstado = id;
    }

    public void setEstadoOT(String es) {
        this.estadoOT = es;
    }

    public void setEncargados(List encar) {
        this.encargados = encar;
    }

    public void setFechaOT(Date fe) {
        this.fecha_ot = fe;
    }

    public void setVencimiento(Date venc) {
        this.vencimiento = venc;
    }

    public void setSuscrip(Date sus) {
        this.fechaSus = sus;
    }

    public void setFinTareas(Date fin) {
        this.tareasConc = fin;
    }

    public void setAprobacion(Date ap) {
        this.aprobacion = ap;
    }

    public void setNLOs(List nlos) {
        this.nlos = nlos;
    }

    public void setPlazo(Integer plazo) {
        this.plazo = plazo;
    }

    public void setIdProy(Integer id_proy) {
        this.idProy = id_proy;
    }

    public void setEP(String ep) {
        this.ep = ep;
    }


    public void setIdTipoOT(Integer idTipo) {
        this.idTipoOT = idTipo;
    }

    public void setTipoOT(String tipo) {
        this.tipoOT = tipo;
    }

    public void setCli(ClienteVO cliente) {
        this.cli = cliente;
    }

    public void setDocumento(DocumentoInVO doc) {
        this.documento = doc;
    }

    public void setDetProy(DetalleProyectoVO proy) {
        this.detProy = proy;
    }



    //---------Getters

    public Integer getIdOT() {
        return this.idOT;
    }

    public String getNomOT() {
        return this.nomOT;
    }

    public Integer getIdEstado() {
        return this.idEstado;
    }

    public String getEstadoOT() {
        return this.estadoOT;
    }

    public List getEncargados() {
        return this.encargados;
    }

    public Date getFechaOT() {
        return this.fecha_ot;
    }

    public Date getVencimiento() {
        return this.vencimiento;
    }

    public Date getSuscrip() {
        return this.fechaSus;
    }

    public Date getFinTareas() {
        return this.tareasConc;
    }

    public Date getAprobacion() {
        return this.aprobacion;
    }

    public List getNLOs() {
        return this.nlos;
    }

    public Integer getPlazo() {
        return this.plazo;
    }

    public Integer getIdProy() {
        return this.idProy;
    }

    public String getEP() {
        return this.ep;
    }

    public Integer getIdTipoOT() {
        return this.idTipoOT;
    }

    public String getTipoOT() {
        return this.tipoOT;
    }

    public ClienteVO getCli() {
        return this.cli;
    }

    public DocumentoInVO getDocumento() {
        return this.documento;
    }

    public DetalleProyectoVO getDetProy() {
        return this.detProy;
    }


}

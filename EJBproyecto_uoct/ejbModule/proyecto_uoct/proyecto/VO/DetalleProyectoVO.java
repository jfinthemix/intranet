package proyecto_uoct.proyecto.VO;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

public class DetalleProyectoVO extends ProyectodeListaVO {
    public DetalleProyectoVO() {
    }

    private List documentos_proy = null;
    private List ots_proy = null;
    private List nlo_s_ot = null;
    private List equipo = null;
    private String descripcion = null;
    private Integer idEncargado = null;
    private Integer idCliente = null;
    private String nomCliente = null;
    private String fondosAnuales = null;
    private Integer fondosTotales = null;
    private Date fechaFin = null;
    private boolean esDelEquipo = false;

    public void setDocumentos(List docs) {
        this.documentos_proy = docs;
    }

    public void setOTs(List ots) {
        this.ots_proy = ots;
    }

    public void setNLOs(List n) {
        this.nlo_s_ot = n;
    }

    public void setEquipo(List i) {
        this.equipo = i;
    }

    public void setDescripcion(String desc) {
        this.descripcion = desc;
    }

    public void setIdEncargado(Integer id) {
        this.idEncargado = id;
    }

    public void setIdCliente(Integer id) {
        this.idCliente = id;
    }

    public void setNomCliente(String cli) {
        this.nomCliente = cli;
    }

    public void setFondosAnuales(String fa) {
        this.fondosAnuales = fa;
    }

    public void setFondosTotales(Integer ft) {
        this.fondosTotales = ft;
    }

    public void setFechaFin(String fecha) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        this.fechaFin = sdf.parse(fecha);
    }

    public void setEsDelEquipo(boolean esdelequipo) {
        this.esDelEquipo = esdelequipo;
    }


    //-----getters

    public List getDocumentos() {
        return this.documentos_proy;
    }

    public List getOTs() {
        return this.ots_proy;
    }

    public List getNLOs() {
        return this.nlo_s_ot;
    }

    public List getEquipo() {
        return this.equipo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public Integer getIdEncargado() {
        return this.idEncargado;
    }

    public Integer getIdCliente() {
        return this.idCliente;
    }

    public String getNomCliente() {
        return this.nomCliente;
    }

    public String getFondosAnuales() {
        return this.fondosAnuales;
    }

    public Integer getFondosTotales() {
        return this.fondosTotales;
    }

    public Date getFechaFin() {
        return this.fechaFin;
    }

    public boolean getEsDelEquipo() {
        return this.esDelEquipo;
    }

}

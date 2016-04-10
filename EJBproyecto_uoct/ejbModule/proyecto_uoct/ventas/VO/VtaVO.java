package proyecto_uoct.ventas.VO;

import java.util.*;

public class VtaVO {

    public VtaVO() {
    }

    private Integer idVenta = null;
    private String codVenta = null;
    private Integer idTIpoVenta = null;
    private Integer idestado = null;
    private String estado = null;
    private CliVtaVO cliente = null;
    private Date fechaRecepcion = null;
    private Date fechaCotizacion = null;
    private Date fechaPago = null;
    private Date fechaEntrega = null;
    private Date fechaFin = null;
    private String comentario = null;
    private List detVenta = null;
    private Float ufPago = null;
    private String codFact = null;


    public void setIdVenta(Integer id) {
        this.idVenta = id;
    }

    public void setCodVenta(String cod) {
        this.codVenta = cod;
    }

    public void setIdTipoVenta(Integer id) {
        this.idTIpoVenta = id;
    }

    public void setIdEstado(Integer id) {
        this.idestado = id;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCliente(CliVtaVO cli) {
        this.cliente = cli;
    }

    public void setFechaRecepcion(Date fecha) {
        this.fechaRecepcion = fecha;
    }

    public void setFechaCotizacion(Date fecha) {
        this.fechaCotizacion = fecha;
    }

    public void setFechaPago(Date fecha) {
        this.fechaPago = fecha;
    }

    public void setFechaEntrega(Date fecha) {
        this.fechaEntrega = fecha;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setDetVenta(List detalle) {
        this.detVenta = detalle;
    }

    public void setUfPago(float uf) {
        this.ufPago = new Float(uf);
    }

    public void setFechaFin(Date fecha) {
        this.fechaFin = fecha;
    }

    public void setCodFact(String cod) {
        this.codFact = cod;
    }

//------------GETTERS----------
    public Integer getIdVenta() {
        return this.idVenta;
    }

    public String getCodVenta() {
        return this.codVenta;
    }


    public Integer getIdTipoVenta() {
        return this.idTIpoVenta;
    }


    public Integer getIdEstado() {
        return this.idestado;
    }

    public String getEstado() {
        return this.estado;
    }


    public CliVtaVO getCliente() {
        return this.cliente;
    }

    public Date getFechaRecepcion() {
        return this.fechaRecepcion;
    }

    public Date getFechaCotizacion() {
        return this.fechaCotizacion;
    }

    public Date getFechaPago() {
        return this.fechaPago;
    }

    public Date getFechaEntrega() {
        return this.fechaEntrega;
    }

    public String getComentario() {
        return this.comentario;
    }

    public List getDetVenta() {
        return this.detVenta;
    }

    public Float getUfPago() {
        return this.ufPago;
    }

    public Date getFechaFin() {
        return this.fechaFin;
    }

    public String getCodFact() {
        return this.codFact;
    }


}

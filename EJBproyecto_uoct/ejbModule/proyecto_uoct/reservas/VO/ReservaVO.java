package proyecto_uoct.reservas.VO;

import java.util.Date;

public class ReservaVO {

    private Long idReserva;
    private Date fechaReserva;
    private Date horaDeInicio;
    private Date horaDeFin;
    private Long idUsuario;
    private Long idRecurso;
    private String nombreUsuario;
    private String motivo;

    public ReservaVO() {
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public Long getIdReserva() {
        return idReserva;
    }

    public Long getIdRecurso() {
        return idRecurso;
    }

    public Date getHoraDeInicio() {
        return horaDeInicio;
    }

    public Date getHoraDeFin() {
        return horaDeFin;
    }

    public String getMotivo(){
        return motivo;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    public void setIdRecurso(Long idRecurso) {
        this.idRecurso = idRecurso;
    }

    public void setHoraDeInicio(Date horaDeInicio) {
        this.horaDeInicio = horaDeInicio;
    }

    public void setHoraDeFin(Date horaDeFin) {
        this.horaDeFin = horaDeFin;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setMotivo(String motivo){
        this.motivo = motivo;
    }

}

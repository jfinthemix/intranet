package proyecto_uoct.reservas.VO;

import java.util.*;
import proyecto_uoct.reservas.util.Parse;
import java.text.SimpleDateFormat;

public class FiltroRecursos {
    private Date fechaDesde;
    private Date fechaHasta;
    private Long idRecurso;
    private Date validacionHoraInicio;
    private Date validacionHoraFin;
    private Date fecha;
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm");

    public FiltroRecursos() {
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public void setIdRecurso(Long idRecurso) {
        this.idRecurso = idRecurso;
    }

    public void setValidacionHoraInicio(Date validacionHoraInicio) {

        this.validacionHoraInicio = validacionHoraInicio;
    }

    public void setValidacionHoraFin(Date validacionHoraFin) {

        this.validacionHoraFin = validacionHoraFin;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public Long getIdRecurso() {
        return idRecurso;
    }

    public Date getValidacionHoraInicio() {

        return validacionHoraInicio;
    }

    public Date getValidacionHoraFin() {
        return validacionHoraFin;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getHoraInicioSinPuntos(){
        String sinP =Parse.quitaDosPuntos(hora.format(validacionHoraInicio));
        return sinP;
    }

    public String getHoraFinSinPuntos(){
        String sinP=Parse.quitaDosPuntos(hora.format(validacionHoraFin));
        return sinP;
}

}

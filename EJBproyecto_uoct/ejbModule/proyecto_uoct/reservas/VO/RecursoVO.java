package proyecto_uoct.reservas.VO;

import java.util.List;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class RecursoVO {

    private Long idRecurso;
    private Long idTipoRecurso;
    private String nombre;
    private String descripcion;
    private List reservas;
    private boolean activo;
    public RecursoVO() {
    }

    public void setIdRecurso(Long idRecurso) {
        this.idRecurso = idRecurso;
    }

    public Long getIdRecurso() {
        return this.idRecurso;
    }


    public void setIdTipoRecurso(Long idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
    }

    public Long getIdTipoRecurso() {
        return this.idTipoRecurso;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void setIsActivo(Integer activo){
        if(activo.intValue()==0){
            this.activo=false;
        }else{
            this.activo=true;
        }
    }

//-----------GETTERS-------------
    public String getNombre() {
        return this.nombre;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setReservas(List reservas) {
        this.reservas = reservas;
    }

    public List getReservas() {
        return reservas;
    }

    public boolean getIsActivo(){
        return activo;
    }
}

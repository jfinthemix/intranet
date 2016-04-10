package proyecto_uoct.mantenedorSubsistemas.VO;
import java.sql.*;

public class SubsistemaVO {
    private Integer id_sistema       = null;
    private Integer id_subsistema     = null;
    private String  nombre_subsistema = null;
    private String  nombre_sistema    = null;

    public SubsistemaVO() {
    }

    public void setIdSubsistema(Integer id_subsistema) {
        this.id_subsistema = id_subsistema;
    }
    public Integer getIdSubsistema() {
        return this.id_subsistema;
    }
    public void setIdSistema(Integer id_sistema) {
        this.id_sistema = id_sistema;
    }
    public Integer getIdSistema() {
        return this.id_sistema;
    }
    public void setNombreSubsistema(String nombre_subsistema) {
        this.nombre_subsistema = nombre_subsistema;
    }
    public String getNombreSubsistema() {
        return this.nombre_subsistema;
    }
    public void setNombreSistema(String nombre_sistema) {
        this.nombre_sistema = nombre_sistema;
    }
    public String getNombreSistema() {
        return this.nombre_sistema;
    }
}



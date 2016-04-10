package proyecto_uoct.mantenedorDispositivos.VO;
import java.sql.*;

public class DispositivoVO {

//dispositivo
    private Integer id_dispositivo      = null;
    private String  nombre              = null;
    private String  descripcion         = null;

//tipo_dispositivo
    private Integer id_tipo_dispositivo = null;
    private String  tipo_dispositivo    = null;

//subsistema
    private Integer id_subsistema       = null;
    private String  nombre_subsistema   = null;

//sistema
    private Integer id_sistema          = null;
    private String  nombre_sistema      = null;

//empresa
    private Integer id_empresa          = null;
    private String  nombre_empresa      = null;




    public DispositivoVO() {
    }
//dispositivo
    public void setIdDispositivo(Integer id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }
    public Integer getIdDispositivo() {
        return this.id_dispositivo;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return this.nombre;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
//tipo_dispositivo
    public void setIdTipoDispositivo(Integer id_tipo_dispositivo) {
        this.id_tipo_dispositivo = id_tipo_dispositivo;
    }
    public Integer getIdTipoDispositivo() {
        return this.id_tipo_dispositivo;
    }
    public void setTipoDispositivo(String tipo_dispositivo) {
        this.tipo_dispositivo = tipo_dispositivo;
    }
    public String getTipoDispositivo() {
        return this.tipo_dispositivo;
    }
//subsistema
    public void setIdSubsistema(Integer id_subsistema) {
        this.id_subsistema = id_subsistema;
    }
    public Integer getIdSubsistema() {
        return this.id_subsistema;
    }
    public void setNombreSubsistema(String nombre_subsistema) {
        this.nombre_subsistema = nombre_subsistema;
    }
    public String getNombreSubsistema() {
        return this.nombre_subsistema;
    }

//sistema
    public void setIdSistema(Integer id_sistema) {
        this.id_sistema = id_sistema;
    }
    public Integer getIdSistema() {
        return this.id_sistema;
    }
    public void setNombreSistema(String nombre_sistema) {
        this.nombre_sistema = nombre_sistema;
    }
    public String getNombreSistema() {
        return this.nombre_sistema;
    }

//empresa
    public void setIdEmpresa(Integer id_empresa) {
        this.id_empresa = id_empresa;
    }
    public Integer getIdEmpresa() {
        return this.id_empresa;
    }
    public void setNombreEmpresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }
    public String getNombreEmpresa() {
        return this.nombre_empresa;
    }

}

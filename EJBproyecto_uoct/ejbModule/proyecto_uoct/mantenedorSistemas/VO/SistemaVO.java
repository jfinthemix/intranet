package proyecto_uoct.mantenedorSistemas.VO;
import java.sql.*;

public class SistemaVO {
    private Integer id_sistema = null;
    private String  nombre     = null;


    public SistemaVO() {
    }

    public void setIdSistema(Integer id_sistema) {
        this.id_sistema = id_sistema;
    }
    public Integer getIdSistema() {
        return this.id_sistema;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return this.nombre;
    }
}

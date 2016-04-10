package proyecto_uoct.EmpMant.VO;
import java.sql.*;
import java.util.Date;
import java.util.List;

public class EmpMantVO {

    //empresa_mantenedora
    private Integer id_empresa          = null;
    private String  nombre              = null;
    private String  telefono            = null;
    private String  direccion           = null;
    private Integer vigente             = null;
    private String  tipo_dispositivo    = null;
    private String  tipo_dispositivo2   = null;
    private String  mail_1              = null;
    private String  mail_2              = null;
    //dispositivo
    private Integer id_dispositivo      = null;
    private String  nombre_dispositivo  = null;
    private String  descripcion         = null;
    private Integer id_tipo_dispositivo = null;
    private List    l_listaA            = null;
    private List    l_listaB            = null;

    public EmpMantVO() {
    }

    public void set_id_empresa(Integer id_empresa) {
        this.id_empresa = id_empresa;
    }
    public Integer get_id_empresa() {
        return this.id_empresa;
    }
    public void set_nombre(String nombre) {
           this.nombre = nombre;
       }
    public String get_nombre() {
           return this.nombre;
    }
    public void set_telefono(String telefono) {
           this.telefono = telefono;
       }
    public String get_telefono() {
           return this.telefono;
    }
    public void set_direccion(String direccion) {
           this.direccion = direccion;
       }
    public String get_direccion() {
           return this.direccion;
    }
    public void set_vigente(Integer vigente) {
        this.vigente = vigente;
    }
    public Integer get_vigente() {
        return this.vigente;
    }
    public void set_tipo_dispositivo(String tipo_dispositivo) {
        this.tipo_dispositivo = tipo_dispositivo;
    }
    public String get_tipo_dispositivo() {
        return this.tipo_dispositivo;
    }
    public void set_tipo_dispositivo2(String tipo_dispositivo2) {
        this.tipo_dispositivo2 = tipo_dispositivo2;
    }
    public String get_tipo_dispositivo2() {
        return this.tipo_dispositivo2;
    }
    public void set_mail_1(String mail_1) {
           this.mail_1 = mail_1;
       }
    public String get_mail_1() {
           return this.mail_1;
    }
    public void set_mail_2(String mail_2) {
           this.mail_2 = mail_2;
       }
    public String get_mail_2() {
           return this.mail_2;
    }
    public void set_id_dispositivo(Integer id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }
    public Integer get_id_dispositivo() {
        return this.id_dispositivo;
    }
    public void set_nombre_dispositivo(String nombre_dispositivo) {
        this.nombre_dispositivo = nombre_dispositivo;
    }
    public String get_nombre_dispositivo() {
        return this.nombre_dispositivo;
    }
    public void set_descripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String get_descripcion() {
        return this.descripcion;
    }
    public void set_id_tipo_dispositivo(Integer id_tipo_dispositivo) {
        this.id_tipo_dispositivo = id_tipo_dispositivo;
    }
    public Integer get_id_tipo_dispositivo() {
        return this.id_tipo_dispositivo;
    }

    public void set_l_listaA(List listaA) {
        this.l_listaA = listaA;
    }
    public List get_l_listaA() {
        return this.l_listaA;
    }
    public void set_l_listaB(List listaB) {
         this.l_listaB = listaB;
    }
    public List get_l_listaB() {
        return this.l_listaB;
    }


}

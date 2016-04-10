package proyecto_uoct.usuario.VO;

import java.util.Date;
import java.sql.Blob;

public class UsuarioVO {
    public UsuarioVO() {
    }

    Integer idUsu = null;
    String username = null;
    String nombreUsu = null;
    String nombre2_usu = null;
    String apellido_usu = null;
    String apellido2_usu = null;

    Integer telefono = null;
    String direccion = null;
    String email = null;
    Date cumpleanos = null;
    String cumpleanos_str = null;
    String area = null;
    Integer id_area = null;
    Integer celular = null;
    String anexo = null;
    boolean isActivo;
    byte[] foto = null;
    Integer idPerfil = null;

    boolean isCurric = false;


    //Blob curriculum=null;
    public void setIdUsu(Integer idusu) {
        this.idUsu = idusu;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    public void setNombreUsu(String name) {
        this.nombreUsu = name;
    }

    public void setNombreUsu2(String name2) {
        this.nombre2_usu = name2;
    }

    public void setApellUsu(String apell) {
        this.apellido_usu = apell;
    }

    public void setApellUsu2(String apell2) {
        this.apellido2_usu = apell2;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public void setDir(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCumpleanos(Date cumple) {
        this.cumpleanos = cumple;
    }

    public void setCumpleanosStr(String cum) {
        this.cumpleanos_str = cum;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setIdArea(Integer idarea) {
        this.id_area = idarea;
    }

    public void setCelular(Integer cel) {
        this.celular = cel;
    }

    public void setAnexo(String anexo) {
        this.anexo = anexo;
    }

    public void setIsActivo(int isAct) {
        if (isAct != 0) {
            this.isActivo = true;
        } else {
            this.isActivo = false;
        }
    }


    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public void setIdPerfil(Integer idP) {
        this.idPerfil = idP;
    }


    public void setIsCurric(boolean iscurr) {
        this.isCurric = iscurr;
    }


    /*    public void setCurr(Blob curr){
            this.curriculum= curr;
        }
     */

    /*----------------------GETTERS-------------*/

    public Integer getIdUsu() {
        return this.idUsu;
    }

    public String getUsername() {
        return this.username;
    }

    public String getNombreUsu() {
        return this.nombreUsu;
    }

    public String getNombreUsu2() {
        return this.nombre2_usu;
    }

    public String getApellUsu() {
        return this.apellido_usu;
    }

    public String getApellUsu2() {
        return this.apellido2_usu;
    }

    public Integer getTelefono() {
        return this.telefono;
    }

    public String getDir() {
        return this.direccion;
    }

    public String getEmail() {
        return this.email;
    }

    public Date getCumpleanos() {
        return this.cumpleanos;
    }

    public String getCumpleanosStr() {
        return this.cumpleanos_str;
    }

    public String getArea() {
        return this.area;
    }

    public Integer getIdArea() {
        return this.id_area;
    }

    public Integer getCelular() {
        return this.celular;
    }

    public String getAnexo() {
        return this.anexo;
    }

    public boolean getIsActivo() {
        return this.isActivo;
    }

    public byte[] getFoto() {
        return this.foto;
    }

    public Integer getIdPerfil() {
        return this.idPerfil;
    }

    public boolean getIsCurric() {
        return this.isCurric;
    }

}

package proyecto_uoct.ventas.VO;

public class CliVtaVO {
    public CliVtaVO() {
    }

    private Integer idCliente = null;
    private String nomCli = null;
    private Integer rutCli = null;
    private char codRutCli = '\0';
    private String direccion = null;
    private String telefono = null;
    private String email = null;
    private String giro = null;
    private String comentario = null;
    private boolean isActivo = false;
    private String contactos = null;


    public void setIdCliente(Integer idCli) {
        this.idCliente = idCli;
    }

    public void setNomCli(String nom) {
        this.nomCli = nom;
    }

    public void setRut(Integer rut) {
        this.rutCli = rut;
    }

    public void setCodRutCli(char cod) {
        this.codRutCli = cod;
    }

    public void setDireccion(String dir) {
        this.direccion = dir;
    }

    public void setTelefono(String tel) {
        this.telefono = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGiro(String giro) {
        this.giro = giro;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setIsActivo(Integer activ) {
        if (activ.intValue() == 1) {
            this.isActivo = true;
        }
    }

    public void setContactos(String cont) {
        this.contactos = cont;
    }


//----------GETTERS-------

    public Integer getIdCliente() {
        return this.idCliente;
    }

    public String getNomCli() {
        return this.nomCli;
    }

    public Integer getRut() {
        return this.rutCli;
    }

    public char getCodRutCli() {
        return this.codRutCli;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public String getEmail() {
        return this.email;
    }

    public String getGiro() {
        return this.giro;
    }

    public String getComentario() {
        return this.comentario;
    }

    public boolean getIsActivo() {
        return isActivo;
    }

    public String getContactos() {
        return this.contactos;
    }

}

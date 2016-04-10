package proyecto_uoct.documentacion.VO;

public class BusDocsVO {
    public BusDocsVO() {
    }

    private Integer idTipoDoc = null;
    private String codigoDoc = null;
    private Integer id_sentido = null;
    private String materia = null;
    private String fecha_ini = null;
    private String fecha_fin = null;
    private Integer encargado = null;
    private String palClave = null;
    private boolean enIniciativas = true;


    public void setIdTipoDoc(Integer idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

    public void setCodigoDoc(String codigo) {
        this.codigoDoc = codigo;
    }

    public void setIdSentido(Integer id) {
        this.id_sentido = id;
    }

    public void setMateria(String as) {
        this.materia = as;
    }

    public void setFechaIni(String in) {
        this.fecha_ini = in;
    }

    public void setFechaFin(String fin) {
        this.fecha_fin = fin;
    }

    public void setEncargado(Integer id_enc) {
        this.encargado = id_enc;
    }

    public void setPalClave(String pal) {
        this.palClave = pal;
    }

    public void setEnIniciativas(boolean enIni) {
        this.enIniciativas = enIni;
    }

    //---getters

    public Integer getIdTipoDoc() {
        return this.idTipoDoc;
    }

    public String getCodigoDoc() {
        return this.codigoDoc;
    }

    public Integer getIdSentido() {
        return this.id_sentido;
    }

    public String getMateria() {
        return this.materia;
    }

    public String getFechaIni() {
        return this.fecha_ini;
    }

    public String getFechaFin() {
        return this.fecha_fin;
    }

    public Integer getEncargado() {
        return this.encargado;
    }

    public String getPalClave() {
        return this.palClave;
    }

    public boolean getEnIniciativas() {
        return this.enIniciativas;
    }

}

package proyecto_uoct.documentacion.VO;

public class TipoDocVO {

    private Integer idTipo = null;
    private String tipo = null;
    private boolean isActivo = false;
    private boolean enIniciativas = false;
    private Integer idsentido = null;


    public void setIdTipo(Integer idT) {
        this.idTipo = idT;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setIsActivo(int isAct) {
        if (isAct == 0) {
            this.isActivo = false;
        } else {
            this.isActivo = true;
        }
    }

    public void setEnIniciativas(int enIni) {
        if (enIni == 0) {
            this.enIniciativas = false;
        } else {
            this.enIniciativas = true;
        }
    }

    public void setIdSentido(Integer idSen) {
        this.idsentido = idSen;
    }

    //GETTERS


    public Integer getIdTipo() {
        return this.idTipo;
    }

    public String getTipo() {
        return this.tipo;
    }

    public boolean getIsActivo() {
        return this.isActivo;
    }

    public boolean getEnIniciativas() {
        return this.enIniciativas;
    }

    public Integer getIdSentido() {
        return this.idsentido;
    }

}

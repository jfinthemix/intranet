package proyecto_uoct.infoyrep.VO;

public class ReportajeVO {
    public ReportajeVO() {
    }

    private Integer id_rep = null;
    private String tit_rep = null;
    private String desc_rep = null;
    private byte[] foto_rep = null;
    private Integer tipo = null;


    public void setIdRep(Integer id_rep) {
        this.id_rep = id_rep;
    }

    public void setTitRep(String tit_rep) {
        this.tit_rep = tit_rep;
    }

    public void setDescRep(String des_rep) {
        this.desc_rep = des_rep;
    }

    //setter de la foto

    public void setFotoRep(byte[] foto_rep) {
        this.foto_rep = foto_rep;
    }

    public void setTipo(int tipo) {
        this.tipo = new Integer(tipo);
    }


    public Integer getIdRep() {
        return this.id_rep;
    }

    public String getTitRep() {
        return this.tit_rep;
    }

    public String getDescRep() {
        return this.desc_rep;
    }

    // getter de la foto

    public byte[] getFotoRep() {
        return this.foto_rep;
    }

    public Integer getTipo() {
        return this.tipo;
    }

}

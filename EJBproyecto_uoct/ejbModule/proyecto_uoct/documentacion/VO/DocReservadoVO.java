package proyecto_uoct.documentacion.VO;

import proyecto_uoct.usuario.VO.UsuarioVO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.*;

public class DocReservadoVO {
    public DocReservadoVO() {
    }

    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private UsuarioVO usu = null;
    private Integer idTipoDoc = null;
    private String tipoDoc = null;
    private String codigo = null;
    private String fechaReg = null;
    private Date fechaRegDate=null;

    private Integer idDoc = null;

    public void setUsu(UsuarioVO usu) {
        this.usu = usu;
    }

    public void setIdTipoDoc(Integer idTipo) {
        this.idTipoDoc = idTipo;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public void setCodigo(String cod) {
        this.codigo = cod;
    }

    public void setFechaReg(String fecha) {
        this.fechaReg = fecha;
        try {
            this.fechaRegDate = sdf.parse(fecha);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    public void setIdDoc(Integer idDoc) {
        this.idDoc = idDoc;
    }


//--------getters

    public UsuarioVO getUsu() {
        return this.usu;
    }

    public Integer getIdTipoDoc() {
        return this.idTipoDoc;
    }

    public String getTipoDoc() {
        return this.tipoDoc;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public String getFechaReg() {
        return this.fechaReg;
    }

    public Integer getIdDoc() {
        return this.idDoc;
    }

    public Date getFechaRegDate(){
        return this.fechaRegDate;
    }


}

package proyecto_uoct.proyecto.VO;

import proyecto_uoct.documentacion.VO.DocumentoInVO;
import java.util.Date;

public class NLOVO extends proyecto_uoct.common.VO.IdStrVO {
    public NLOVO() {
    }

    private Integer idProy = null;
    private DocumentoInVO documento = null;
    private DetalleOTVO ot = null;
    private String nomProy = null;
    private Date fechaNLO = null;
    private String detalleNLO = null;

    public void setIdProy(Integer idproy) {
        this.idProy = idproy;
    }

    public void setDocumento(DocumentoInVO doc) {
        this.documento = doc;
    }

    public void setOT(DetalleOTVO ot) {
        this.ot = ot;
    }

    public void setNomProy(String nomproy) {
        this.nomProy = nomproy;
    }

    public void setFechaNLO(Date fecha) {
        this.fechaNLO = fecha;
    }

    public void setDetalleNLO(String detalle){
        this.detalleNLO=detalle;
    }

    public DocumentoInVO getDocumento() {
        return this.documento;
    }

    public Integer getIdProy() {
        return this.idProy;
    }

    public DetalleOTVO getOT() {
        return this.ot;
    }

    public String getNomProy() {
        return this.nomProy;
    }

    public Date getFechaNLO() {
        return this.fechaNLO;
    }

    public String getDetalleNLO(){
       return this.detalleNLO;
    }


}

package proyecto_uoct.proyecto.VO;

import proyecto_uoct.documentacion.VO.DocumentoInVO;
import java.util.Date;

public class NLOdeListaVO extends proyecto_uoct.common.VO.IdStrVO {
    public NLOdeListaVO() {
    }

    private DocumentoInVO doc = null;
    private Date fechaNLO = null;
    public void setDoc(DocumentoInVO doc) {
        this.doc = doc;
    }

    public void setFechaNLO(Date fecha) {
        this.fechaNLO = fecha;
    }

    public DocumentoInVO getDoc() {
        return this.doc;
    }

    public Date getFechaNLO() {
        return this.fechaNLO;
    }

}

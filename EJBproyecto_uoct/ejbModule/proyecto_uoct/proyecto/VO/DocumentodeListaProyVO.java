package proyecto_uoct.proyecto.VO;

import org.apache.struts.upload.FormFile;

public class DocumentodeListaProyVO extends proyecto_uoct.common.VO.IdStrVO {
    public DocumentodeListaProyVO() {
    }

    private FormFile eldoc = null;
    private Integer id_proy = null;
    private byte[] archivo = null;



    public void setElDoc(FormFile doc) {
        this.eldoc = doc;
    }

    public void setIdProy(Integer id) {
        this.id_proy = id;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }




    public FormFile getElDoc() {
        return this.eldoc;
    }

    public Integer getIdProy() {
        return this.id_proy;
    }


    public byte[] getArchivo() {
        return this.archivo;
    }

}

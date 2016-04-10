package proyecto_uoct.foro.VO;

import org.apache.struts.upload.FormFile;

public class DocForoVO {
    public DocForoVO() {
    }

    private Integer idDocForo = null;
    private String tituloDoc = null;
    private Integer idForo = null;
    private FormFile documento = null;
    private byte[] documentoByte = null;

    public void setIdForo(Integer id) {
        this.idForo = id;
    }


    public void setIdDocForo(Integer id_foro) {
        this.idDocForo = id_foro;
    }

    public void setTitulodoc(String titdoc) {
        this.tituloDoc = titdoc;
    }


    public void setDocumento(FormFile doc) {
        this.documento = doc;
    }

    public void setDocumentoByte(byte[] doc) {
        this.documentoByte = doc;

    }


    public Integer getIdDocForo() {
        return this.idDocForo;
    }

    public String getTituloDoc() {
        return this.tituloDoc;
    }


    public Integer getIdForo() {
        return this.idForo;
    }

    public FormFile getDocumento() {
        return this.documento;
    }

    public byte[] getDocumentoByte() {
        return this.documentoByte;

    }

}

package proyecto_uoct.documentacion.VO;

import org.apache.struts.upload.FormFile;

public class ArchivoDocVO {
    private Integer idArchivo = null;
    private String nomArchivo = null;
    private byte[] archivo = null;
    private FormFile archivoFile = null;

    public void setIdArchivo(Integer idArch) {
        this.idArchivo = idArch;
    }

    public void setNomArchivo(String nom) {
        this.nomArchivo = nom;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public void setArchivoFile(FormFile archivo) {
        this.archivoFile = archivo;
    }

//------GETTERS

    public Integer getIdArchivo() {
        return this.idArchivo;
    }

    public String getNomArchivo() {
        return this.nomArchivo;
    }

    public byte[] getArchivo() {
        return this.archivo;
    }

    public FormFile getArchivoFile() {
        return this.archivoFile;
    }

}

package proyecto_uoct.infoyrep.VO;

import java.io.File;

import org.apache.struts.upload.FormFile;

public class ArchivoInfoVO {
    public ArchivoInfoVO() {
    }

    private FormFile ladata = null;
    private String descripcion = null;
    private Integer idFile = null;
    private byte[] archivo = null;
    private String nomArchivo = null;
    private File archivoFile = null;

    public void setLaData(FormFile da) {
	this.ladata = da;
    }

    public void setDescripcion(String desc) {
	this.descripcion = desc;
    }

    public void setIdFile(Integer id) {
	this.idFile = id;
    }

    public void setArchivo(byte[] arch) {
	this.archivo = arch;
    }

    public void setNomArchivo(String nomArchivo) {
	this.nomArchivo = nomArchivo;
    }

    public void setArchivoFile(File Archivo) {
	this.archivoFile = Archivo;
    }

    // ----------------getters

    public FormFile getLaData() {
	return this.ladata;
    }

    public String getDescripcion() {
	return this.descripcion;
    }

    public Integer getIdFile() {
	return this.idFile;
    }

    public byte[] getArchivo() {
	return this.archivo;
    }

    public String getNomArchivo() {
	return this.nomArchivo;
    }

    public File getArchivoFile() {
	return this.archivoFile;
    }

}

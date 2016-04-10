package proyecto_uoct.infoyrep.model;

import javax.ejb.EJBLocalObject;
import proyecto_uoct.infoyrep.VO.ArchivoInfoVO;
import java.util.List;
import proyecto_uoct.documentacion.VO.ArchivoDocVO;

public interface InfoInstitLocal extends EJBLocalObject {


    public List getListaArchivos();

    public boolean upArchivo(ArchivoInfoVO arch);

    public ArchivoDocVO getArchivo(Integer idArchivo);

    public boolean eliminarArchivo(Integer idArchivo);
}

package proyecto_uoct.foro.model;

import javax.ejb.EJBLocalObject;
import java.util.List;
import proyecto_uoct.foro.VO.DetForoVO;
import proyecto_uoct.foro.VO.DocForoVO;

public interface ForosLocal extends EJBLocalObject {


    public List getListaForos(Integer id_tema);

    public List getForosxfecha(String fecha_s);

    public List getForosxpal(String pal);

    public DetForoVO getDetForo(Integer id_foro);

    public List getDocsForo(Integer id_foro);

    public List getPost(Integer id_foro);

    public void ingresarPost(Integer id_foro, Integer id_usu, String comentario);

    public boolean actDescForo(Integer id_foro, String desc);

    public boolean borrarDocForo(Integer id_doc);

    public int agregarForo(DetForoVO nuevof);

    public Integer getAdminForo(Integer id_foro);

    public List buscarForos(String palClave, String fecha, Integer idTema);

    public boolean registrarDocumentoForo(DocForoVO docForo);

    public DocForoVO descargaDocForo(Integer idDocForo);
}

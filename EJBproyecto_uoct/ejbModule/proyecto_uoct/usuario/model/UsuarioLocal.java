package proyecto_uoct.usuario.model;

import javax.ejb.EJBLocalObject;
import java.util.List;
import proyecto_uoct.usuario.VO.UsuarioVO;
import proyecto_uoct.common.VO.DatosSesVO;
import proyecto_uoct.usuario.VO.AnexoVO;
import org.apache.struts.upload.FormFile;
import proyecto_uoct.usuario.VO.CurriculoVO;
import oracle.sql.BLOB;
import java.io.InputStream;
import java.io.File;
import proyecto_uoct.usuario.VO.InformeActividadesVO;

public interface UsuarioLocal extends EJBLocalObject {


    public List getAreas();

    public boolean ingresarUsuario(String nombre_usu, String nombre2_usu,
                                   String apellido_usu, String apellido2_usu,
                                   String username, String password,
                                   Integer id_area, Integer id_perfil);

    public List getListaUsuarios();

    public List getListaUsuariosActivos();

    public UsuarioVO getDatosUsu(Integer id_usu);

    public UsuarioVO getDatosUsuEdit(Integer id_usu);

    public void eliminarUsuario(Integer id_usu);

    public boolean cambiarEstadoUsu(Integer id_usu);

    public boolean cambiarContrasena(Integer id_usu, String nueva_c,
                                     String vieja_c);

    public boolean reasignarContrasena(Integer id_usu, String nueva_c);

    public DatosSesVO verifUsu(String nom, String psw);

    public List getTiposdelUsu(Integer id_usu);

    public List getListaNomUsu();

    public List getListaIngenieros();

    public void actDatosUsu(UsuarioVO datosdelusu) throws Exception;

    public boolean actDatosUsuSimple(UsuarioVO datosdelusu);

    public List getAnexosIndep();

    public List getAnexosUsu();

    public AnexoVO getAnexo(Integer idAnx);

    public boolean actualizaAnexo(Integer idAnx, String anx);

    public boolean registraAnexo(String nomAnx, String anx);

    public boolean borrarAnexo(Integer idAnx);

    public List getUsuariosActivos();

    public boolean actualizaCurriculo(CurriculoVO curriculo);

    public byte[] getCurriculo(Integer idUsu);

    public List getInformes(Integer idUsu);

    public boolean registraInforme(InformeActividadesVO informe);

    public boolean actualizarFoto(byte[] foto, Integer idUsu);

    public boolean borrarInforme(Integer idInf);

    public InformeActividadesVO getInforme(Integer idInf);

    public boolean tienePermiso(Integer idPerfil, Integer idFuncionalidad);

    public boolean tienePermiso(Integer idPerfil, String funcionalidad);

    public byte[] getFotoUsu(Integer idUsu);

    public boolean agregarArea(String nomArea);

    public boolean eliminarArea(Integer idArea);

    public List getCumpleaneros();
}

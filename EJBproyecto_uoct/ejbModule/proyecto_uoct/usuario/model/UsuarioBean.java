package proyecto_uoct.usuario.model;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.CreateException;
import java.util.List;
import proyecto_uoct.dao.UsuarioDAO;
import java.sql.*;
import proyecto_uoct.usuario.VO.UsuarioVO;
import proyecto_uoct.common.VO.DatosSesVO;
import proyecto_uoct.usuario.VO.AnexoVO;
import proyecto_uoct.usuario.VO.CurriculoVO;
import oracle.sql.BLOB;
import java.io.InputStream;
import java.io.File;
import proyecto_uoct.usuario.VO.InformeActividadesVO;

public class UsuarioBean implements SessionBean {
    SessionContext sessionContext;
    public void ejbCreate() throws CreateException {
    }

    public void ejbRemove() {
    }

    public void ejbActivate() {
    }

    public void ejbPassivate() {
    }


    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    public List getAreas() {
        List areas = null;
        UsuarioDAO area = UsuarioDAO.getInstance();

        try {
            areas = area.getAreas();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return areas;
    }

    public boolean ingresarUsuario(String nombre_usu, String nombre2_usu,
                                String apellido_usu,
                                String apellido2_usu, String username,
                                String password, Integer id_area,
                                Integer id_perfil){
        UsuarioDAO nuevousu = UsuarioDAO.getInstance();

        try {
            nuevousu.registrarUsuario(nombre_usu, nombre2_usu,
                                      apellido_usu,
                                      apellido2_usu, username,
                                      password, id_area, id_perfil);
            return true;
        } catch (SQLException ex) {
            System.out.println("El usuario no pudo ser registrado");
            ex.printStackTrace();
            sessionContext.setRollbackOnly();
            return false;
        }

    }

//---------------------------------------------------
    public List getListaUsuarios() {
        List listausu = null;
        UsuarioDAO usu = UsuarioDAO.getInstance();
        try {
            listausu = usu.getListaUsuarios();
        } catch (Exception ex) {
            System.out.println("no se pudo buscar la lista");
            ex.printStackTrace();
        }

        return listausu;

    }

    public List getListaUsuariosActivos() {
    List listausu = null;
    UsuarioDAO usu = UsuarioDAO.getInstance();
    try {
        listausu = usu.getListaUsuariosActivos();
    } catch (Exception ex) {
        System.out.println("no se pudo buscar la lista");
        ex.printStackTrace();
    }
    return listausu;
}




    public UsuarioVO getDatosUsu(Integer id_usu) {
        UsuarioVO usuario = null;
        UsuarioDAO u = UsuarioDAO.getInstance();
        usuario = u.getDatosUsu(id_usu);
        return usuario;
    }

    public UsuarioVO getDatosUsuEdit(Integer id_usu) {
        UsuarioVO usu = null;
        UsuarioDAO usudao = UsuarioDAO.getInstance();
        usu = usudao.getDatosUsuEdit(id_usu);
        return usu;

    }

    public void eliminarUsuario(Integer id_usu) {

        UsuarioDAO usudao = UsuarioDAO.getInstance();
        usudao.eliminarUsuario(id_usu);

    }

    public boolean cambiarEstadoUsu(Integer id_usu) {

        UsuarioDAO usudao = UsuarioDAO.getInstance();
        Integer isactivo = usudao.getEstadoUsu(id_usu);
        if (isactivo.intValue() == 1) {
            usudao.desactivarUsu(id_usu);
            return true;
        }
        if (isactivo.intValue() != 1) {
            usudao.activarUsu(id_usu);
            return true;
        } else {
            return false;
        }

    }

    public boolean cambiarContrasena(Integer id_usu, String nueva_c,
                                  String vieja_c) {
        UsuarioDAO usudao = UsuarioDAO.getInstance();
        try {
            return usudao.cambiarContrasena(id_usu, nueva_c, vieja_c);
        } catch (Exception ex) {
            return false;
        }

    }


    public boolean reasignarContrasena(Integer id_usu, String nueva_c) {
        UsuarioDAO usudao = UsuarioDAO.getInstance();
        try {
            usudao.reasignarContrasena(id_usu, nueva_c);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            sessionContext.setRollbackOnly();
            return false;
        }

    }


    public DatosSesVO verifUsu(String nom, String psw) {
        DatosSesVO usu = null;
        UsuarioDAO usudao = UsuarioDAO.getInstance();
        usu = usudao.verifUsu(nom, psw);

        return usu;
    }

    public List getTiposdelUsu(Integer id_usu) {
        List listadetipos = null;
        UsuarioDAO usudao = UsuarioDAO.getInstance();
        listadetipos = usudao.getTiposdelUsu(id_usu);
        return listadetipos;
    }

    public List getListaNomUsu() {

        List listaUsuarios = null;
        UsuarioDAO usu = UsuarioDAO.getInstance();
        try {
            listaUsuarios = usu.getListaNomUsu();
        } catch (Exception ex) {
            System.out.println("no se consultaron los nombres de usuarios");
            ex.printStackTrace();
        }
        return listaUsuarios;

    }

    public List getListaIngenieros() {
        List listaing = null;
        UsuarioDAO usu = UsuarioDAO.getInstance();
        listaing = usu.getListaIngenieros();
        return listaing;

    }

    public void actDatosUsu(UsuarioVO datosdelusu) throws Exception {
        try{
        UsuarioDAO usu = UsuarioDAO.getInstance();
        usu.actDatosUsu(datosdelusu);}
    catch(Exception e){
        e.printStackTrace();
        throw e;
    }

    }

    public boolean actDatosUsuSimple(UsuarioVO datosdelusu) {
        boolean isactualiz = false;
        UsuarioDAO usu = UsuarioDAO.getInstance();
        isactualiz = usu.actDatosUsuSimple(datosdelusu);
        return isactualiz;
    }


    public List getAnexosIndep() {
        UsuarioDAO usu = UsuarioDAO.getInstance();
        return usu.getAnexosIndep();
    }

    public List getAnexosUsu() {
        UsuarioDAO usu = UsuarioDAO.getInstance();
        return usu.getAnexosUsu();
    }

    public AnexoVO getAnexo(Integer idAnx) {
        AnexoVO anx = null;
        UsuarioDAO usu = UsuarioDAO.getInstance();

        anx = usu.getAnexoIndep(idAnx);

        return anx;
    }

    public boolean actualizaAnexo(Integer idAnx, String anx) {
        UsuarioDAO usu = UsuarioDAO.getInstance();

        if (usu.actualizaAnexo(idAnx, anx)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean registraAnexo(String nomAnx, String anx) {
        UsuarioDAO usu = UsuarioDAO.getInstance();
        if (usu.registraAnexo(nomAnx, anx)) {
            return true;
        } else {
            return false;
        }
    }


    public boolean borrarAnexo(Integer idAnx) {
        UsuarioDAO usu = UsuarioDAO.getInstance();
        if (usu.borrarAnexo(idAnx)) {
            return true;
        } else {
            return false;
        }
    }

    public List getUsuariosActivos() {

        UsuarioDAO usu = UsuarioDAO.getInstance();

        return usu.getUsuariosActivos();

    }

    public boolean actualizaCurriculo(CurriculoVO curriculo) {

        UsuarioDAO usu = UsuarioDAO.getInstance();
        try {
            if (usu.actualizaCurriculo(curriculo)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            sessionContext.setRollbackOnly();
            return false;
        }
        return false;
    }

    public byte[] getCurriculo(Integer idUsu) {

        UsuarioDAO usu = UsuarioDAO.getInstance();
        return usu.getCurriculo(idUsu);
    }

    public List getInformes(Integer idUsu) {

        UsuarioDAO usu = UsuarioDAO.getInstance();
        return usu.getInformes(idUsu);

    }

    public boolean  registraInforme(InformeActividadesVO informe) {

        UsuarioDAO usu = UsuarioDAO.getInstance();
        if(usu.registraInforme(informe)){
            return true;
        }else{
            sessionContext.setRollbackOnly();
            return false;
        }

    }

    public boolean  actualizarFoto(byte[] foto, Integer idUsu) {
        UsuarioDAO usu = UsuarioDAO.getInstance();
        return usu.actualizarFoto(foto,idUsu);
    }

    public boolean  borrarInforme(Integer idInf) {
        UsuarioDAO usu = UsuarioDAO.getInstance();
        if( usu.borrarInforme(idInf)){
            return true;
        }else{
            sessionContext.setRollbackOnly();
            return false;
        }


    }

    public InformeActividadesVO  getInforme(Integer idInf) {
            UsuarioDAO usu = UsuarioDAO.getInstance();

        return usu.getInforme(idInf);
    }

    public boolean  tienePermiso(Integer idPerfil, Integer idFuncionalidad) {
            UsuarioDAO usu = UsuarioDAO.getInstance();
            return usu.tienePermiso(idPerfil,idFuncionalidad);
    }

    public boolean  tienePermiso(Integer idPerfil, String funcionalidad) {
            UsuarioDAO usu = UsuarioDAO.getInstance();
            return usu.tienePermiso(idPerfil,funcionalidad);
    }


    public byte[]  getFotoUsu(Integer idUsu) {
        UsuarioDAO usu = UsuarioDAO.getInstance();
        return usu.getFotoUsu(idUsu);
    }

    public boolean  agregarArea(String nomArea) {
        UsuarioDAO usu = UsuarioDAO.getInstance();
        return usu.agregarArea(nomArea);
    }

    public boolean  eliminarArea(Integer idArea) {
        UsuarioDAO usu = UsuarioDAO.getInstance();
        return usu.eliminarArea(idArea);
    }

    public List  getCumpleaneros() {
        UsuarioDAO usu = UsuarioDAO.getInstance();
        return usu.getCumpleanerosMes();
    }
}

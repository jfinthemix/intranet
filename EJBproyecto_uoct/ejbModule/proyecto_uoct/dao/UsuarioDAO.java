package proyecto_uoct.dao;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import oracle.sql.*;
import org.apache.struts.upload.*;
import proyecto_uoct.common.*;
import proyecto_uoct.common.VO.*;
import proyecto_uoct.common.util.*;
import proyecto_uoct.usuario.VO.*;


public class UsuarioDAO {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public UsuarioDAO() {
    }

    private static UsuarioDAO dao = null;

    public static UsuarioDAO getInstance() {
        if (dao == null) {
            dao = new UsuarioDAO();
        }
        return dao;
    }


    public List getAreas() throws Exception {

        Connection conn = null;
        List areas = new LinkedList();

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "SELECT id_area,area FROM area");
            ResultSet rs = ps.executeQuery();

            AreaVO area = null;

            while (rs.next()) {
                area = new AreaVO();
                area.setIdArea(new Integer(rs.getInt(1)));
                area.setArea(rs.getString(2));

                areas.add(area);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return areas;
    }

//------------------------------------------------


    public List getListaUsuarios() throws Exception {
        Connection conn = null;
        List listaUsuarios = new LinkedList();

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "SELECT username,nombre_usu,nombre2_usu,apellido_usu," +
                    "area,id_usu,activo,cumpleanos,email FROM usuario,area " +
                    "WHERE usuario.id_area=area.id_area");
            ResultSet rs = ps.executeQuery();

            UsuarioVO usuario = null;

            while (rs.next()) {
                usuario = new UsuarioVO();
                usuario.setUsername(rs.getString(1));
                usuario.setNombreUsu(rs.getString(2));
                usuario.setNombreUsu2(rs.getString(3));
                usuario.setApellUsu(rs.getString(4));
                usuario.setArea(rs.getString(5));
                usuario.setIdUsu(new Integer(rs.getInt(6)));
                usuario.setIsActivo(rs.getInt(7));
                usuario.setCumpleanos(rs.getDate(8));
                usuario.setEmail(rs.getString(9));
                listaUsuarios.add(usuario);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaUsuarios;
    }





    public List getListaUsuariosActivos() throws Exception {
        Connection conn = null;
        List listaUsuarios = new LinkedList();

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "SELECT username,nombre_usu,nombre2_usu,apellido_usu," +
                    "area,id_usu,activo,cumpleanos,email FROM usuario,area " +
                    "WHERE usuario.id_area=area.id_area and activo=1");
            ResultSet rs = ps.executeQuery();

            UsuarioVO usuario = null;

            while (rs.next()) {
                usuario = new UsuarioVO();
                usuario.setUsername(rs.getString(1));
                usuario.setNombreUsu(rs.getString(2));
                usuario.setNombreUsu2(rs.getString(3));
                usuario.setApellUsu(rs.getString(4));
                usuario.setArea(rs.getString(5));
                usuario.setIdUsu(new Integer(rs.getInt(6)));
                usuario.setIsActivo(rs.getInt(7));
                usuario.setCumpleanos(rs.getDate(8));
                usuario.setEmail(rs.getString(9));
                listaUsuarios.add(usuario);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaUsuarios;
    }




    //------------------------------------------------

    /**
     * getListaNomUsu
     * entrega la lista completa de usuarios del sistema(id,nombre, 2º nombre,
     * apellido)
     *
     * @return List
     * @throws Exception
     */
    public List getListaNomUsu() throws Exception {
        Connection conn = null;
        List listaUsuarios = new LinkedList();

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "select id_usu, nombre_usu, nombre2_usu,apellido_usu,activo from usuario");
            ResultSet rs = ps.executeQuery();

            UsuarioVO usuario = null;

            while (rs.next()) {
                usuario = new UsuarioVO();
                usuario.setIdUsu(new Integer(rs.getInt(1)));
                usuario.setNombreUsu(rs.getString(2));
                usuario.setNombreUsu2(rs.getString(3));
                usuario.setApellUsu(rs.getString(4));
                usuario.setIsActivo(rs.getInt(5));
                listaUsuarios.add(usuario);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaUsuarios;
    }


//-----------------------
    public boolean registrarUsuario(String nombre_usu, String nombre2_usu,
                                    String apellido_usu,
                                    String apellido2_usu, String username,
                                    String password, Integer id_area,
                                    Integer id_perfil) throws
            SQLException {
        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "INSERT INTO USUARIO (USUARIO.id_usu,USUARIO.username," +
                    "USUARIO.nombre_usu,USUARIO.nombre2_usu," +
                    "USUARIO.apellido_usu,USUARIO.apellido2_usu," +
                    "USUARIO.contrasena,USUARIO.id_area,USUARIO.id_perfil," +
                    "activo)values(s_usuario.nextval,?,?,?,?,?,?,?,?,1)");
            ps.setString(1, username);
            ps.setString(2, nombre_usu);
            ps.setString(3, nombre2_usu);
            ps.setString(4, apellido_usu);
            ps.setString(5, apellido2_usu);
            ps.setString(6, password);
            ps.setInt(7, id_area.intValue());
            ps.setInt(8, id_perfil.intValue());
            ps.executeUpdate();

            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;

        }

    }


//---------------------------------------------------------
    public UsuarioVO getDatosUsu(Integer id_usu) {
        Connection conn = null;
        UsuarioVO usuario = null;
        String q =
                "SELECT id_usu, nombre_usu,nombre2_usu,apellido_usu,username," +
                "area,area.id_area, activo,apellido2_usu,id_perfil,email FROM usuario , area " +
                "WHERE usuario.id_area=area.id_area AND usuario.id_usu=?";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_usu.intValue());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new UsuarioVO();
                usuario.setIdUsu(new Integer(rs.getInt(1)));
                usuario.setNombreUsu(rs.getString(2));
                usuario.setNombreUsu2(rs.getString(3));
                usuario.setApellUsu(rs.getString(4));

                usuario.setUsername(rs.getString(5));
                usuario.setArea(rs.getString(6));
                usuario.setIdArea(new Integer(rs.getInt(7)));
                usuario.setIsActivo(rs.getInt(8));
                usuario.setApellUsu2(rs.getString(9));
                usuario.setIdPerfil(new Integer(rs.getInt(10)));
                usuario.setEmail(rs.getString(11));
            }
            conn.close();
            return usuario;

        } catch (SQLException ex) {
            System.out.println(
                    "no se pudo consultar datos del usuario");
            ex.printStackTrace();
        }

        return usuario;
    }


//-------------------------------------------
    public UsuarioVO getDatosUsuEdit(Integer id_usu) {
        Connection conn = null;
        UsuarioVO usuario = null;
        String q = "SELECT id_usu, nombre_usu,nombre2_usu," +
                   " apellido_usu,apellido2_usu,username," +
                   " telefono,celular,direccion,cumpleanos," +
                   "email,area,anexo,curriculum  FROM usuario,area" +
                   " WHERE usuario.id_area=area.id_area " +
                   "AND usuario.id_usu=?";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_usu.intValue());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new UsuarioVO();
                usuario.setIdUsu(new Integer(rs.getInt(1)));
                usuario.setNombreUsu(rs.getString(2));
                usuario.setNombreUsu2(rs.getString(3));
                usuario.setApellUsu(rs.getString(4));
                usuario.setApellUsu2(rs.getString(5));
                usuario.setUsername(rs.getString(6));
                usuario.setTelefono(new Integer(rs.getInt(7)));
                usuario.setCelular(new Integer(rs.getInt(8)));
                usuario.setDir(rs.getString(9));
                usuario.setCumpleanos(rs.getDate(10));
                usuario.setEmail(rs.getString(11));
                usuario.setArea(rs.getString(12));
                usuario.setAnexo(rs.getString(13));
                if (rs.getBlob(14) != null) {
                    usuario.setIsCurric(true);
                }
                conn.close();
                return usuario;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return usuario;
    }

//-------------------------------------------------------
    public void eliminarUsuario(Integer id_usu) {

        Connection conn = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "DELETE usuario WHERE id_usu=?");
            ps.setInt(1, id_usu.intValue());

            PreparedStatement ps2 = conn.prepareCall(
                    "DELETE usuario_tipo_usu WHERE id_usu=?");
            ps2.setInt(1, id_usu.intValue());

            ps.executeUpdate();
            ps2.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("no se realizo eliminacion");
            ex.printStackTrace();
        }

    }

//---------------------------------------------------------

    public boolean cambiarContrasena(Integer id_usu, String nueva_c,
                                     String vieja_c) throws Exception {

        Connection conn = null;
        String contrasena = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "SELECT usuario.contrasena FROM usuario WHERE usuario.id_usu=?");
            ps.setInt(1, id_usu.intValue());
            ResultSet rs = ps.executeQuery();
            rs.next();
            contrasena = rs.getString(1);
            //ps.close();
            if (contrasena.equals(vieja_c)) {
                PreparedStatement ps2 = conn.prepareCall(
                        "UPDATE usuario SET usuario.contrasena= ? where id_usu=?");
                ps2.setString(1, nueva_c);
                ps2.setInt(2, id_usu.intValue());
                ps2.executeUpdate();
                conn.close();
                return true;
            } else {
                conn.close();
                return false;
            }

        } catch (Exception e2) {
            conn.close();
            e2.printStackTrace();
            throw e2;
        }
    }

//-----------------------------------------



    public void reasignarContrasena(Integer id_usu, String nueva_c) throws
            Exception {

        Connection conn = null;
        String contrasena = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps2 = conn.prepareCall(
                    "UPDATE usuario SET usuario.contrasena= ? where id_usu=?");
            ps2.setString(1, nueva_c);
            ps2.setInt(2, id_usu.intValue());
            ps2.executeUpdate();
            conn.close();
        } catch (Exception e2) {
            conn.close();
            e2.printStackTrace();
            throw e2;
        }
    }

    //--------------------------------------


    public DatosSesVO verifUsu(String nom, String psw) {

        Connection conn = null;
        DatosSesVO usu = null;
        nom = nom.toLowerCase();
        psw = psw.toLowerCase();

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "SELECT usuario.id_usu,usuario.nombre_usu,usuario.nombre2_usu," +
                    "usuario.apellido_usu, usuario.id_perfil " +
                    "FROM usuario  WHERE LOWER(usuario.username)=? " +
                    "and LOWER(usuario.contrasena)=? and activo=1");

            ps.setString(1, nom);
            ps.setString(2, psw);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usu = new DatosSesVO();
                usu.setId(new Integer(rs.getInt(1)));
                usu.setNom(rs.getString(2));
                usu.setNom2(rs.getString(3));
                usu.setAp(rs.getString(4));
                usu.setIdPerfil(new Integer(rs.getInt(5)));

            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("error en la consuta a la bd");
            ex.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException ex1) {
        }
        return usu;
    }

//--------------------------------------------------------
    public List getTiposdelUsu(Integer id_usu) {
        List listatipos = new LinkedList();

        Connection conn = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "SELECT tipousuario.id_tipo_usu, tipousuario.tipo_usuario" +
                    " from usuario_tipo_usu ,tipoUsuario " +
                    "WHERE usuario_tipo_usu.id_tipo_usu= tipousuario.id_tipo_usu " +
                    "AND id_usu=?");
            ps.setInt(1, id_usu.intValue());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TiposUsuVO tu = new TiposUsuVO();
                tu.setIdTipoUsu(new Integer(rs.getInt(1)));
                tu.setTipoUsu(rs.getString(2));
                listatipos.add(tu);
            }

            conn.close();

        } catch (SQLException ex) {
            System.out.println("error en la consuta a la bd");
            ex.printStackTrace();
        }
        return listatipos;
    }

    //----------------------------------------------
    //Obtiene el id, nombres y apellido de los usuarios del area Ingenieria

    public List getListaIngenieros() {

        List listaing = new LinkedList();

        Connection conn = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "SELECT id_usu, nombre_usu, nombre2_usu," +
                    " apellido_usu,email " +
                    "FROM usuario ,area " +
                    "WHERE usuario.id_area= area.id_area " +
                    "AND area.area like 'Ingenieria'");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UsuarioVO usu = new UsuarioVO();
                usu.setIdUsu(new Integer(rs.getInt(1)));
                usu.setNombreUsu(rs.getString(2));
                usu.setNombreUsu2(rs.getString(3));
                usu.setApellUsu(rs.getString(4));
                usu.setEmail(rs.getString(5));
                listaing.add(usu);
            }

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaing;

    }

    /**
     * actDatosUsu
     *
     * @param datosdelusu UsuarioVO
     * @return boolean
     */
    public void actDatosUsu(UsuarioVO datosdelusu) throws Exception {
        Connection conn = null;

        try {

            String q = "UPDATE usuario SET nombre_usu='" +
                       datosdelusu.getNombreUsu() +
                       "', nombre2_usu='" + datosdelusu.getNombreUsu2() + "'," +
                       " apellido_usu='" + datosdelusu.getApellUsu() +
                       "', apellido2_usu='" + datosdelusu.getApellUsu2() + "'," +
                       "telefono=" + datosdelusu.getTelefono() + ",celular=" +
                       datosdelusu.getCelular() + "," +
                       " direccion='" + datosdelusu.getDir() + "', email='" +
                       datosdelusu.getEmail() + "'";

            if (!datosdelusu.getCumpleanosStr().equals("")) {
                q += ",cumpleanos=TO_DATE('" +
                        datosdelusu.getCumpleanosStr().substring(0, 5) +
                        "-2000','DD-MM-YYYY')";
            } else {
                q += ",cumpleanos=null";
            }
            q += ",anexo='" + datosdelusu.getAnexo()
                    + "' WHERE usuario.id_usu=" + datosdelusu.getIdUsu();
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            System.out.println("error en la Actualizacion de los datos");
            ex.printStackTrace();
            throw ex;
        }

    }


    /**
     * actDatosUsu
     *
     * @param datosdelusu UsuarioVO
     * @return boolean
     */
    public boolean actDatosUsuSimple(UsuarioVO datosdelusu) {
        Connection conn = null;
        String q = "UPDATE usuario SET nombre_usu=?, nombre2_usu=?," +
                   " apellido_usu=?, apellido2_usu=?," +
                   "username=?,id_area=?,id_perfil=?" +
                   " WHERE usuario.id_usu=?";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setString(1, datosdelusu.getNombreUsu());
            ps.setString(2, datosdelusu.getNombreUsu2());
            ps.setString(3, datosdelusu.getApellUsu());
            ps.setString(4, datosdelusu.getApellUsu2());
            ps.setString(5, datosdelusu.getUsername());
            ps.setInt(6, datosdelusu.getIdArea().intValue());
            ps.setInt(7, datosdelusu.getIdPerfil().intValue());
            ps.setInt(8, datosdelusu.getIdUsu().intValue());
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("error en la Actualizacion de los datos");
            ex.printStackTrace();
        }

        return false;
    }


    /**
     * getAnexosIndep
     *
     * @return List
     */
    public List getAnexosIndep() {

        List anxs = new LinkedList();

        Connection conn = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "SELECT id_anexo,nom_anexo,anexo FROM anexo ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AnexoVO a = new AnexoVO();
                a.setIdAnexo(new Integer(rs.getInt(1)));
                a.setNomAnexo(rs.getString(2));
                a.setAnexo(rs.getString(3));
                anxs.add(a);
            }

            conn.close();

        } catch (SQLException ex) {
            System.out.println("error en la consuta a la bd");
            ex.printStackTrace();
        }
        return anxs;
    }

    /**
     * getAnexosUsu
     *
     * @return List
     */
    public List getAnexosUsu() {
        List anxs = new LinkedList();

        Connection conn = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "SELECT nombre_usu,nombre2_usu,apellido_usu,anexo FROM usuario where activo=1 ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AnexoVO a = new AnexoVO();
                if (rs.getString(2) != null) {
                    a.setNomAnexo(rs.getString(1) + " " + rs.getString(2) + " " +
                                  rs.getString(3));
                } else {
                    a.setNomAnexo(rs.getString(1) + " " +
                                  rs.getString(3));
                }
                a.setAnexo(rs.getString(4));
                anxs.add(a);
            }

            conn.close();

        } catch (SQLException ex) {
            System.out.println("error en la consuta a la bd");
            ex.printStackTrace();
        }

        return anxs;
    }

    /**
     * getAnexoIndep
     *
     * @param idAnx Integer
     * @return AnexoVO
     */
    public AnexoVO getAnexoIndep(Integer idAnx) {

        Connection conn = null;
        AnexoVO a = new AnexoVO();
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "SELECT id_anexo,nom_anexo,anexo FROM anexo where id_anexo=? ");
            ps.setInt(1, idAnx.intValue());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a.setIdAnexo(new Integer(rs.getInt(1)));
                a.setNomAnexo(rs.getString(2));
                a.setAnexo(rs.getString(3));
            }

            conn.close();

        } catch (SQLException ex) {
            System.out.println("error en la consuta a la bd");
            ex.printStackTrace();
        }

        return a;
    }

    public boolean actualizaAnexo(Integer idAnx, String anx) {

        Connection conn;
        String q = "UPDATE anexo SET anexo=? WHERE id_anexo=?";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setString(1, anx);
            ps.setInt(2, idAnx.intValue());
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * registraAnexo
     *
     * @param nomAnx String
     * @param anx String
     * @return boolean
     */
    public boolean registraAnexo(String nomAnx, String anx) {

        Connection conn;
        String q = "INSERT INTO anexo (id_anexo,nom_anexo,anexo) values"
                   + "(s_anexo.NEXTVAL,?,?)";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setString(1, nomAnx);
            ps.setString(2, anx);
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * borrarAnexo
     *
     * @param idAnx Integer
     * @return boolean
     */
    public boolean borrarAnexo(Integer idAnx) {

        Connection conn;
        String q = "DELETE anexo WHERE id_anexo=?";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idAnx.intValue());
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * getEstadoUsu
     *
     * @param id_usu Integer
     * @return boolean
     */
    public Integer getEstadoUsu(Integer id_usu) {

        Connection conn;
        String q = "SELECT activo FROM usuario WHERE id_usu=? ";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_usu.intValue());
            ResultSet rs = ps.executeQuery();
            rs.next();
            Integer i = new Integer(rs.getInt(1));
            conn.close();
            return (i);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    /**
     * desactivarUsu
     *
     * @param id_usu Integer
     */
    public boolean desactivarUsu(Integer id_usu) {

        Connection conn;
        String q = "UPDATE usuario SET activo =0 WHERE id_usu=? ";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_usu.intValue());

            if (ps.executeUpdate() != 0) {
                conn.close();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    /**
     * activarUsu
     *
     * @param id_usu Integer
     */
    public boolean activarUsu(Integer id_usu) {

        Connection conn;
        String q = "UPDATE usuario SET activo =1 WHERE id_usu=? ";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_usu.intValue());

            if (ps.executeUpdate() != 0) {
                conn.close();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;

    }

    /**
     * getUsuariosActivos
     *
     * @return List
     */
    public List getUsuariosActivos() {
        Connection conn = null;
        List listaUsuarios = new LinkedList();

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "SELECT username,nombre_usu,nombre2_usu,apellido_usu,area,id_usu,activo " +
                    "FROM usuario ,area " +
                    "WHERE  usuario.id_area=area.id_area AND activo=1");
            ResultSet rs = ps.executeQuery();

            UsuarioVO usuario = null;

            while (rs.next()) {
                usuario = new UsuarioVO();
                usuario.setUsername(rs.getString(1));
                usuario.setNombreUsu(rs.getString(2));
                usuario.setNombreUsu2(rs.getString(3));
                usuario.setApellUsu(rs.getString(4));
                usuario.setArea(rs.getString(5));
                usuario.setIdUsu(new Integer(rs.getInt(6)));
                usuario.setIsActivo(rs.getInt(7));
                listaUsuarios.add(usuario);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaUsuarios;
    }

    /**
     * actualizaCurriculo
     *
     * @param curriculo CurriculoVO
     */
    public boolean actualizaCurriculo(CurriculoVO curriculo) throws Exception {
        Connection conn;
        Integer idUsu = curriculo.getIdUsu();
        FormFile curr = curriculo.getCurr();
        Archivo a = Archivo.getInstance();
        File fileCurr = a.formFileToFile(curr);

        try {

            conn = ConnectionPool.getInstance().getConnection();
            //conn.setAutoCommit(false);
            String q = "UPDATE usuario SET curriculum=empty_blob()" +
                       " WHERE id_usu=?";
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idUsu.intValue());
            ps.executeUpdate();

            q = "SELECT curriculum FROM  usuario WHERE id_usu=?  ";
            ps = conn.prepareCall(q);
            ps.setInt(1, idUsu.intValue());
            ResultSet rs = ps.executeQuery();
            rs.next();
            BLOB blob = (BLOB) rs.getBlob(1);
            FileInputStream instream = new FileInputStream(fileCurr);
            OutputStream outstream = blob.setBinaryStream(0);
            int size = blob.getBufferSize();
            byte[] buffer = new byte[size];
            int length = -1;
            while ((length = instream.read(buffer)) != -1) {
                outstream.write(buffer, 0, length);
            }
            instream.close();
            outstream.close();

            //conn.commit();
            conn.close();
            return true;
        } catch (Exception e) {

            e.printStackTrace();
            throw e;

        }
    }

    /**
     * getCurriculo
     *
     * @param idUsu Integer
     */
    public byte[] getCurriculo(Integer idUsu) {
        Connection conn;
        String q = "SELECT curriculum FROM  usuario WHERE id_usu=?  ";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idUsu.intValue());
            ResultSet rs = ps.executeQuery();
            rs.next();
            BLOB blob = (BLOB) rs.getBlob(1);
            int len = (int) blob.length();
            byte[] b = blob.getBytes(1, len);
            conn.close();
            //          Archivo a = Archivo.getInstance();
//            File file= a.byteToFile(b);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * getInformes
     *
     * @param idUsu Integer
     * @return List
     */
    public List getInformes(Integer idUsu) {

        Connection conn;
        List infs = new LinkedList();
        String q = "SELECT id_informe,nombre_informe,fecha" +
                   " FROM informeactividades , usuario " +
                   "WHERE informeactividades.id_usu=usuario.id_usu" +
                   " AND usuario.id_usu=? ORDER BY fecha DESC";

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idUsu.intValue());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                InformeActividadesVO inf = new InformeActividadesVO();
                inf.setIdInfor(new Integer(rs.getInt(1)));
                inf.setNomInfor(rs.getString(2));
                inf.setFechaInfor(rs.getDate(3));
                infs.add(inf);
            }
            conn.close();
            return infs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * registraInforme
     *
     * @param informe InformeActividadesVO
     * @return boolean
     */
    public boolean registraInforme(InformeActividadesVO informe) {

        Connection conn;
       // Archivo a = Archivo.getInstance();
        
        /*try {
            ffInforme = a.formFileToFile(informe.getFormFile());
        } catch (Exception ex) {
            ex.printStackTrace();
        	return false;
          
        }*/

        String q = "INSERT INTO informeactividades" +
                   " (id_informe, nombre_informe, id_usu, fecha,documento) values" +
                   " (s_informeactividades.nextval, ?,?,TO_DATE(?,'DD-MM-YYYY'),empty_blob())  ";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setString(1, informe.getNomInfor());
            ps.setInt(2, informe.getIdUsu().intValue());
            ps.setString(3, sdf.format(informe.getFechaInfor()));
            ps.executeUpdate();

            q = "SELECT s_informeactividades.currval FROM dual";
            ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int nuevo = rs.getInt(1);

            q = "SELECT documento FROM informeactividades WHERE id_informe=? ";
            ps = conn.prepareCall(q);
            ps.setInt(1, nuevo);
            rs = ps.executeQuery();
            rs.next();

           // BLOB blob = (BLOB) rs.getBlob(1);
            FileInputStream instream = new FileInputStream(informe.getInformeFile());
            OutputStream outstream = rs.getBlob(1).setBinaryStream(0);
            //int size = (int) rs.getBlob(1).length();
            int size = (int) informe.getInformeB().length;
            byte[] buffer = new byte[size];
            int length = -1;	
            while ((length = instream.read(buffer)) != -1) {
                outstream.write(buffer, 0, length);
            }
            instream.close();
            outstream.close();

            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * actualizarFoto
     *
     * @param foto FormFile
     * @param idUsu Integer
     * @return boolean
     */
    public boolean actualizarFoto(byte[] foto, Integer idUsu) {
        Connection conn;
        Archivo a = Archivo.getInstance();
        try {
            File fileFoto = a.byteToFile(foto);
            conn = ConnectionPool.getInstance().getConnection();
            //conn.setAutoCommit(false);
            String q = "UPDATE usuario SET foto=empty_blob()" +
                       " WHERE id_usu=?";
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idUsu.intValue());
            ps.executeUpdate();

            q = "SELECT foto FROM  usuario WHERE id_usu=?  ";
            ps = conn.prepareCall(q);
            ps.setInt(1, idUsu.intValue());
            ResultSet rs = ps.executeQuery();
            rs.next();
            BLOB blob = (BLOB) rs.getBlob(1);
            FileInputStream instream = new FileInputStream(fileFoto);
            OutputStream outstream = blob.setBinaryStream(0);
            int size = blob.getBufferSize();
            byte[] buffer = new byte[size];
            int length = -1;
            while ((length = instream.read(buffer)) != -1) {
                outstream.write(buffer, 0, length);
            }
            instream.close();
            outstream.close();

            //conn.commit();
            conn.close();
            return true;
        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }

    /**
     * borrarInforme
     *
     * @param idInf Integer
     * @return boolean
     */
    public boolean borrarInforme(Integer idInf) {

        Connection conn;
        String q = "DELETE informeactividades WHERE id_informe=?";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idInf.intValue());
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * getInforme
     *
     * @param idInf Integer
     * @return InformeActividadesVO
     */
    public InformeActividadesVO getInforme(Integer idInf) {
        Connection conn;
        String q =
                "SELECT documento,nombre_informe FROM informeactividades  WHERE id_informe=?";
        InformeActividadesVO informeVO = new InformeActividadesVO();
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idInf.intValue());
            ResultSet rs = ps.executeQuery();
            rs.next();
            //BLOB blob = (BLOB) rs.getBlob(1);
            int len = (int) rs.getBlob(1).length();
            byte[] b = rs.getBlob(1).getBytes(1, len);
            informeVO.setInformeB(b);
            informeVO.setNomInfor(rs.getString(2));

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return informeVO;

    }

    /**
     * tienePermiso
     *
     * @param idPerfil Integer
     * @param idFuncionalidad Integer
     * @return boolean
     */
    public boolean tienePermiso(Integer idPerfil, Integer idFuncionalidad) {
        Connection conn;
        String q = "select id_funcionalidad from perfil_funcionalidad where id_funcionalidad=? and id_perfil=?";
        InformeActividadesVO informeVO = new InformeActividadesVO();
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idFuncionalidad.intValue());
            ps.setInt(2, idPerfil.intValue());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                conn.close();
                return true;
            }
            conn.close();
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    /**
     * getFotoUsu
     *
     * @param idUsu Integer
     * @return byte[]
     */
    public byte[] getFotoUsu(Integer idUsu) {
        Connection conn;
        String q = "SELECT foto FROM  usuario WHERE id_usu=?  ";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idUsu.intValue());
            ResultSet rs = ps.executeQuery();
            rs.next();
            BLOB blob = (BLOB) rs.getBlob(1);
            if (blob != null) {
                int len = (int) blob.length();
                byte[] b = blob.getBytes(1, len);
                conn.close();
                return b;
            } else {
                conn.close();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * agregarArea
     *
     * @param nomArea String
     * @return boolean
     */
    public boolean agregarArea(String nomArea) {
        Connection conn;
        String q =
                "INSERT INTO area (id_area, area) VALUES (s_area.nextval, ?)";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setString(1, nomArea);
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * eliminarArea
     *
     * @param idArea Integer
     * @return boolean
     */
    public boolean eliminarArea(Integer idArea) {
        Connection conn;
        String q = "DELETE  area WHERE  id_area=?";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idArea.intValue());
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * tienePermiso
     *
     * @return boolean
     */
    public boolean tienePermiso(Integer idPerfil, String funcionalidad) {
        Connection conn;
        String q = "SELECT perfil_funcionalidad.id_funcionalidad FROM" +
                   " perfil_funcionalidad , funcionalidad" +
                   " WHERE perfil_funcionalidad.ID_FUNCIONALIDAD=funcionalidad.ID_FUNCIONALIDAD " +
                   " AND id_perfil=" + idPerfil +
                   " AND UPPER(nombre_funcionalidad)" +
                   " LIKE UPPER('%" + funcionalidad + "%') ";
        InformeActividadesVO informeVO = new InformeActividadesVO();
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                conn.close();
                return true;
            }
            conn.close();
        } catch (Exception e) {
            return false;
        }

        return false;

    }


    public List getCumpleanerosMes() {

        List cumpleaneros = new LinkedList();
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
        java.util.Date hoy = new java.util.Date();
        Calendar hoytb = Calendar.getInstance();
        hoytb.add(hoytb.MONTH, 1);
        String masunmes = sdf.format(hoytb.getTime());

        Connection conn;
        try {
            java.util.Date masunmes_d = sdf.parse(masunmes);
            String q = null;
            if (sdf2.format(hoy).equals("12")) {
                q =
                        "select nombre_usu,nombre2_usu,apellido_usu,cumpleanos  from usuario where " +
                        "cumpleanos >=TO_DATE('01-" + sdf2.format(hoy) +
                        "-2000','DD-MM-YYYY')" +
                        " and cumpleanos <TO_DATE('01-" + sdf2.format(masunmes_d) +
                        "-2001','DD-MM-YYYY') and usuario.ACTIVO=1 order by cumpleanos asc";

            } else {
                q =
                        "select nombre_usu,nombre2_usu,apellido_usu,cumpleanos  from usuario where " +
                        "cumpleanos >=TO_DATE('01-" + sdf2.format(hoy) +
                        "-2000','DD-MM-YYYY')" +
                        " and cumpleanos <TO_DATE('01-" + sdf2.format(masunmes_d) +
                        "-2000','DD-MM-YYYY') and usuario.ACTIVO=1 order by cumpleanos asc";
            }
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UsuarioVO usu = new UsuarioVO();
                usu.setNombreUsu(rs.getString(1));
                usu.setNombreUsu2(rs.getString(2));
                usu.setApellUsu(rs.getString(3));
                usu.setCumpleanos(rs.getDate(4));
                cumpleaneros.add(usu);
            }
            conn.close();
        } catch (Exception e) {
            System.out.print(e.toString());
        }
        return cumpleaneros;
    }


}
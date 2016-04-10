 package proyecto_uoct.dao;

import java.sql.*;
import java.util.*;

import proyecto_uoct.common.*;
import proyecto_uoct.common.VO.*;

public class PerfilDAO {
    public PerfilDAO() {
    }

    private static PerfilDAO dao = null;

    public static PerfilDAO getInstance() {
        if (dao == null) {
            dao = new PerfilDAO();
        }
        return dao;
    }

    /**
     * getFunsdePerfil
     *
     * @param id_perfil Integer
     * @return List
     */
    public List getFunsdePerfil(Integer id_perfil) {
        Connection conn = null;
        String q =
                "SELECT FUNCIONALIDAD.id_funcionalidad,url,nombre_funcionalidad,id_modulo,popup,params_popup" +
                "  FROM FUNCIONALIDAD,PERFIL_FUNCIONALIDAD WHERE" +
                " FUNCIONALIDAD.id_funcionalidad=PERFIL_FUNCIONALIDAD.ID_FUNCIONALIDAD" +
                " AND PERFIL_FUNCIONALIDAD.id_perfil=? "+
                " ORDER BY prioridad";
        List funs = new LinkedList();

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_perfil.intValue());
            ResultSet rs = ps.executeQuery();

            FuncionalidadVO fun = null;

            while (rs.next()) {
                fun = new FuncionalidadVO();
                fun.setIdFuncionalidad(new Integer(rs.getInt(1)));
                fun.setURLFun(rs.getString(2));
                fun.setNombreFun(rs.getString(3));
                fun.setIdModulo(new Integer(rs.getInt(4)));
                fun.setPopup(new Integer(rs.getInt(5)));
                fun.setParams_popup(rs.getString(6));
                funs.add(fun);

            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return funs;

    }

    /**
     * getModulos
     *
     * @return List
     */
    public List getModulos() {

        Connection conn = null;
        String q = "  SELECT id_modulo, nombre_modulo FROM modulo ORDER BY PRIORIDAD";
        List mods = new LinkedList();
        Integer Activos=0;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            //ps.setInt(1,Activos.intValue() );
            ResultSet rs = ps.executeQuery();
            
            ModuloVO m = null;

            while (rs.next()) {
                m = new ModuloVO();
                m.setIdModulo(new Integer(rs.getInt(1)));
                m.setNombreModulo(rs.getString(2));

                mods.add(m);

            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mods;

    }

    /**
     * getFuncionalidades
     *
     * @return List
     */
    public List getFuncionalidades() {
        Connection conn = null;
        String q =
                " SELECT id_funcionalidad, nombre_funcionalidad,id_modulo FROM funcionalidad";
        List funcs = new LinkedList();

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();

            FuncionalidadVO f = null;

            while (rs.next()) {
                f = new FuncionalidadVO();
                f.setIdFuncionalidad(new Integer(rs.getInt(1)));
                f.setNombreFun(rs.getString(2));
                f.setIdModulo(new Integer(rs.getInt(3)));
                funcs.add(f);

            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return funcs;

    }

    /**
     * ingresarPerfil
     *
     * @param nom_perfil String
     */
    public Integer ingresarPerfil(String nom_perfil) throws Exception {
        Connection conn = null;
        Integer idNuevo = null;
        String q = "INSERT INTO perfil (id_perfil,nombre_perfil) values (s_perfil.NextVal, UPPER(?))";

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setString(1, nom_perfil);

            if (ps.executeUpdate() == 1) {
                q = "SELECT s_perfil.CURRVAL from dual";
                ps = conn.prepareCall(q);
                ResultSet rs = ps.executeQuery();
                rs.next();
                idNuevo = new Integer(rs.getInt(1));
            }
            conn.close();
            return idNuevo;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    /**
     * ingresarPerfil_Func
     *
     * @param idNuevo Integer
     * @param i int
     */
    public void ingresarPerfil_Func(Integer idPerfil, int i) throws Exception {
        Connection conn = null;

        String q = "INSERT INTO perfil_funcionalidad" +
                   " (id_perfil,id_funcionalidad) values (?,?)";

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idPerfil.intValue());
            ps.setInt(2, i);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    /**
     * getPerfiles
     *
     * @return List
     */
    public List getPerfiles() {
        Connection conn = null;
        String q =
                " SELECT id_perfil, nombre_perfil FROM perfil";
        List perfiles = new LinkedList();

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();

            PerfilVO p = null;

            while (rs.next()) {
                p = new PerfilVO();
                p.setId(new Integer(rs.getInt(1)));
                p.setStr(rs.getString(2));
                perfiles.add(p);

            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return perfiles;
    }

    /**
     * borraFuncionalidades
     *
     * @param id_perfil Integer
     */
    public boolean borraFuncionalidades(Integer id_perfil) throws Exception {
        Connection conn;
        String q = "DELETE perfil_funcionalidad WHERE id_perfil=?";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_perfil.intValue());
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * eliminarPerfil
     *
     * @param id_perfil Integer
     */
    public boolean eliminarPerfil(Integer id_perfil) throws Exception {
        Connection conn;
        String q = "DELETE perfil WHERE id_perfil=?";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_perfil.intValue());
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        //return false;

    }

    /**
     * esEliminable
     *
     * @param id_perfil Integer
     * @return boolean
     */
    public boolean esEliminable(Integer id_perfil) throws Exception {
        Connection conn;
        String q = "SELECT id_usu FROM USUARIO WHERE id_perfil=?";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_perfil.intValue());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                conn.close();
                return false;
            } else {
                conn.close();
                return true;

            }

        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }


}

package proyecto_uoct.dao;
import java.sql.*;
import java.text.*;
import java.util.*;
import proyecto_uoct.common.*;
import proyecto_uoct.mantenedorSistemas.VO.*;

public class MantSistemaDAO {
    public MantSistemaDAO() {
    }

    private static MantSistemaDAO dao = null;
    public static MantSistemaDAO getInstance() {
        if (dao == null) {
            dao = new MantSistemaDAO();
        }
        return dao;
    }

    /**********************************/
    /*     insert, select, delete     */
    /**********************************/
//buscar
    public List buscarSistema() throws Exception {
        List listaSistema = new LinkedList();
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            String select_1 =
                    " select id_sistema, nombre" +
                    " from sistema " +
                    " order by 1";
            PreparedStatement ps = conn.prepareCall(select_1);
            ResultSet rs = ps.executeQuery();
            SistemaVO sistema = null;
            while (rs.next()) {
                sistema = new SistemaVO();
                sistema.setIdSistema(new Integer(rs.getInt("id_sistema")));
                sistema.setNombre(rs.getString("nombre"));
                listaSistema.add(sistema);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaSistema;
    }

//ingresar
    public int ingresarSistema(String nombre) throws Exception {
        int valor_id_sistema =0;
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    " insert into sistema (id_sistema, nombre) " +
                    " values (s_sistema.nextval, ?)");
            ps.setString(1, nombre);
            ps.executeUpdate();
            ps.close();
            PreparedStatement ps2 = conn.prepareCall(
                    "select max(id_sistema) from sistema");
            ResultSet rs2 = ps2.executeQuery();
            if (rs2.next()) {
                valor_id_sistema = rs2.getInt(1);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valor_id_sistema;
    }

//modificar
    public int modificarSistema(int id_sistema, String nombre) throws Exception {
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    " update sistema " +
                    " set nombre = ? " +
                    " where id_sistema=?");
            ps.setString(1, nombre);
            ps.setInt(2, id_sistema);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id_sistema;
    }

//eliminar
    public int eliminarSistema(int id_sistema) throws Exception {
        try {
            Connection conn           = ConnectionPool.getInstance().getConnection();
            PreparedStatement delete  = null;
            delete                    = conn.prepareCall(
              " delete from sistema where id_sistema=?");
              delete.setInt(1, id_sistema);
              delete.executeUpdate();
              delete.close();
              conn.close();
          }  catch(Exception e){
              e.printStackTrace();
          }
          return id_sistema;
    }



    //sePuedeEliminar
        public String sePuedeEliminar(int id_sistema) throws Exception {
            List listaSistema = new LinkedList();
            String respuesta = "no";
            int valor=0;
            try {
                Connection conn           = ConnectionPool.getInstance().getConnection();
                String select_1 =
                        " select count(*) as id_sistema" +
                        " from subsistema " +
                        " where id_sistema="+ id_sistema;
                PreparedStatement ps = conn.prepareCall(select_1);
                ResultSet rs = ps.executeQuery();
                SistemaVO sistema = null;
                while (rs.next()) {
                    sistema = new SistemaVO();
                    sistema.setIdSistema(new Integer(rs.getInt("id_sistema")));
                    listaSistema.add(sistema);
                }
                Iterator i = listaSistema.iterator();
                while (i.hasNext()) {
                    SistemaVO sistema2 = (SistemaVO) i.next();
                    valor= sistema2.getIdSistema();
                }
                if(valor==0){
                    respuesta = "si";
                }
                conn.close();
            }  catch(Exception e){
                e.printStackTrace();
            }
            return respuesta;
        }

        public int idPerfil(Integer ses_idusu) throws Exception{
                int idPerfil = 0;
                try{
                    Connection conn = ConnectionPool.getInstance().getConnection();
                    PreparedStatement ps = conn.prepareCall(
                            " select distinct p.id_perfil " +
                            " from perfil_funcionalidad p " +
                            " where p.id_perfil in (select id_perfil " +
                            "                       from usuario " +
                            "                      where id_usu=" + ses_idusu + ")" +
                            " and p.id_funcionalidad in (107, 106, 105, 104)") ;
                    //el SELECT: si id_perfil del usuario tiene la funcionalidad 107, 106, 105 y 104
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()) {
                        idPerfil = rs.getInt("id_perfil");
                    }
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return idPerfil;
    }

}













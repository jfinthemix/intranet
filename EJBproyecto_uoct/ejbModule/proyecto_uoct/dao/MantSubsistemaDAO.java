package proyecto_uoct.dao;
import java.sql.*;
import java.text.*;
import java.util.*;
import proyecto_uoct.common.*;
import proyecto_uoct.mantenedorSubsistemas.VO.*;

public class MantSubsistemaDAO {
    public MantSubsistemaDAO() {
    }
    private static MantSubsistemaDAO dao = null;
    public static MantSubsistemaDAO getInstance() {
        if (dao == null) {
            dao = new MantSubsistemaDAO();
        }
        return dao;
    }

    /**********************************/
    /*     insert, select, delete     */
    /**********************************/

    //buscar
       public List buscarSubsistema() throws Exception {
           List listaSubsistema = new LinkedList();
           try {
               Connection conn = ConnectionPool.getInstance().getConnection();
               String select_1 =
                       " SELECT ss.id_subsistema, ss.nombre as nombre_subsistema, s.id_sistema, s.nombre as nombre_sistema " +
                       " FROM SUBSISTEMA SS, SISTEMA S " +
                       " WHERE s.id_sistema=ss.id_sistema " +
                       "order by 3";
               PreparedStatement ps = conn.prepareCall(select_1);
               ResultSet rs = ps.executeQuery();
               SubsistemaVO subsistema = null;
               while (rs.next()) {
                   subsistema = new SubsistemaVO();
                   subsistema.setIdSubsistema(new Integer(rs.getInt("id_subsistema")));
                   subsistema.setNombreSubsistema(rs.getString("nombre_subsistema"));
                   subsistema.setIdSistema(new Integer(rs.getInt("id_sistema")));
                   subsistema.setNombreSistema(rs.getString("nombre_sistema"));
                   listaSubsistema.add(subsistema);
               }
               conn.close();
           } catch (Exception e) {
               e.printStackTrace();
           }
           return listaSubsistema;
       }

//ingresar
       public int ingresarSubsistema(String nombre, int id_sistema) throws Exception {
           int valor_id_subsistema =0;
           try {
               Connection conn = ConnectionPool.getInstance().getConnection();
               PreparedStatement ps = conn.prepareCall(
                       " insert into subsistema (id_subsistema, nombre, id_sistema) " +
                       " values (s_subsistema.nextval, ?, ?)");
               ps.setString(1, nombre);
               ps.setInt(2, id_sistema);
               ps.executeUpdate();
               ps.close();

               PreparedStatement ps2 = conn.prepareCall(
                       "select max(id_subsistema) from subsistema");
               ResultSet rs2 = ps2.executeQuery();
               if (rs2.next()) {
                   valor_id_subsistema = rs2.getInt(1);
               }

               conn.close();
           } catch (Exception e) {
               e.printStackTrace();
           }
           return valor_id_subsistema;
       }

//modificar
       public int modificarSubsistema(int id_subsistema, String nombre, int id_sistema) throws Exception {
           try {
               Connection conn = ConnectionPool.getInstance().getConnection();
               PreparedStatement ps = conn.prepareCall(
                       " update subsistema " +
                       " set nombre = ? , id_sistema= ? " +
                       " where id_subsistema=?");
               ps.setString(1, nombre);
               ps.setInt(2, id_sistema);
               ps.setInt(3, id_subsistema);
               ps.executeUpdate();
               ps.close();
               conn.close();
           } catch (Exception e) {
               e.printStackTrace();
           }
           return id_subsistema;
       }

//eliminar
       public int eliminarSubsistema(int id_subsistema) throws Exception {
           try {
               Connection conn           = ConnectionPool.getInstance().getConnection();
               PreparedStatement delete  = null;
               delete                    = conn.prepareCall(
                 " delete from subsistema where id_subsistema=?");
                 delete.setInt(1, id_subsistema);
                 delete.executeUpdate();
                 delete.close();
                 conn.close();
             }  catch(Exception e){
                 e.printStackTrace();
             }
             return id_subsistema;
       }

       //para lista_sistema
       public List lista_sistema() throws Exception {
           List lista_sistema       = new LinkedList();
           try {
               Connection conn      = ConnectionPool.getInstance().getConnection();
               PreparedStatement ps = conn.prepareCall(
                       " select id_sistema, nombre " +
                       " from sistema " +
                       " order by id_sistema");
               ResultSet rs  = ps.executeQuery();
               SubsistemaVO subsistema = null;
               while (rs.next()) {
                   subsistema = new SubsistemaVO();
                   subsistema.setIdSistema(new Integer(rs.getInt("id_sistema")));
                   subsistema.setNombreSistema(rs.getString("nombre"));
                   lista_sistema.add(subsistema);
               }
               conn.close();
           }
           catch (Exception e) {
               e.printStackTrace();
           }
           return lista_sistema;
       }

       //sePuedeEliminar
           public String sePuedeEliminar(int id_subsistema) throws Exception {
               List listaSubsistema = new LinkedList();
               String respuesta = "no";

               int valor=0;
               try {
                   Connection conn           = ConnectionPool.getInstance().getConnection();
                   String select_1 =
                           " select count(*) as id_subsistema" +
                           " from dispositivo " +
                           " where id_subsistema="+ id_subsistema;
                   PreparedStatement ps = conn.prepareCall(select_1);
                   ResultSet rs = ps.executeQuery();
                   SubsistemaVO subsistema = null;
                   while (rs.next()) {
                       subsistema = new SubsistemaVO();
                       subsistema.setIdSubsistema(new Integer(rs.getInt("id_subsistema")));
                       listaSubsistema.add(subsistema);
                   }
                   Iterator i = listaSubsistema.iterator();
                   while (i.hasNext()) {
                       SubsistemaVO subsistema2 = (SubsistemaVO) i.next();
                       valor= subsistema2.getIdSubsistema();
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



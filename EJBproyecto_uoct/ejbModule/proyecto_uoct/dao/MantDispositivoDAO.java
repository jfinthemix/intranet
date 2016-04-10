package proyecto_uoct.dao;
import java.sql.*;
import java.text.*;
import java.util.*;
import proyecto_uoct.common.*;
import proyecto_uoct.mantenedorDispositivos.VO.*;

public class MantDispositivoDAO {
    public MantDispositivoDAO() {
    }
    private static MantDispositivoDAO dao = null;
    public static MantDispositivoDAO getInstance() {
        if (dao == null) {
            dao = new MantDispositivoDAO();
        }
        return dao;
    }

    /**********************************/
    /*     insert, select, delete     */
    /**********************************/
    public List buscarDispositivo(String subsistema) throws Exception {
        List listaDispositivo = new LinkedList();
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            String vacio="";
            String select_1 =
                    " select s.id_sistema, s.nombre as nom_sistema, d.id_subsistema, ss.nombre as nom_subsistema, d.id_dispositivo, d.nombre as nom_dispositivo, " +
                    " d.descripcion as des_dispositivo, d.id_empresa_mantenedora as id_empresa, e.nombre as nom_empresa, d.id_tipo_dispositivo as id_tipo, td.descripcion as des_tipo " +
                    " from dispositivo d, subsistema ss, sistema s, empresa_mantenedora e, tipo_dispositivo td  " +
                    " where d.id_subsistema        = ss.id_subsistema " +
                    " and ss.id_sistema            = s.id_sistema " +
                    " and d.id_empresa_mantenedora = e.id_empresa_mantenedora " +
                    " and d.id_tipo_dispositivo    = td.id_tipo_dispositivo ";
            if(subsistema.equals(vacio)){

            }
            else{
             select_1 = select_1 + "and ss.nombre = '" + subsistema + "'";
            }
            select_1 = select_1 + " order by 1,3,5";
            PreparedStatement ps      = conn.prepareCall(select_1);
            ResultSet rs              = ps.executeQuery();
            DispositivoVO dispositivo = null;
            while (rs.next()) {
                dispositivo = new DispositivoVO();
                dispositivo.setIdSistema(new Integer(rs.getInt("id_sistema")));
                dispositivo.setNombreSistema(rs.getString("nom_sistema"));
                dispositivo.setIdSubsistema(new Integer(rs.getInt("id_subsistema")));
                dispositivo.setNombreSubsistema(rs.getString("nom_subsistema"));
                dispositivo.setIdDispositivo(new Integer(rs.getInt("id_dispositivo")));
                dispositivo.setNombre(rs.getString("nom_dispositivo"));
                dispositivo.setDescripcion(rs.getString("des_dispositivo"));
                dispositivo.setIdEmpresa(new Integer(rs.getInt("id_empresa")));
                dispositivo.setNombreEmpresa(rs.getString("nom_empresa"));
                dispositivo.setIdTipoDispositivo(new Integer(rs.getInt("id_tipo")));
                dispositivo.setTipoDispositivo(rs.getString("des_tipo"));
                listaDispositivo.add(dispositivo);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDispositivo;
    }


    //ingresar
    public int ingresarDispositivo(int id_subsistema, String nombre, String descripcion, int id_empresa_mantenedora, int id_tipo_dispositivo) throws Exception {
        int valor_id_dispositivo =0;
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();


            PreparedStatement ps1 = conn.prepareCall(
                    "select max(id_dispositivo) as id from dispositivo");
            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next()) {
                valor_id_dispositivo = rs1.getInt("id") + 1;
            }



            PreparedStatement ps = conn.prepareCall(
                    " insert into dispositivo (id_dispositivo, id_subsistema, nombre, descripcion, id_empresa_mantenedora, id_tipo_dispositivo) " +
                    " values (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, valor_id_dispositivo);
            ps.setInt(2, id_subsistema);
            ps.setString(3, nombre);
            ps.setString(4, descripcion);
            ps.setInt(5, id_empresa_mantenedora);
            ps.setInt(6, id_tipo_dispositivo);
            ps.executeUpdate();
            ps.close();



            PreparedStatement ps2 = conn.prepareCall(
                    "select id_dispositivo as id from dispositivo where id_subsistema="+ id_subsistema +" and nombre = '"+ nombre +"' and descripcion = '"+ descripcion +"' and id_empresa_mantenedora = "+ id_empresa_mantenedora+" and id_tipo_dispositivo = "+ id_tipo_dispositivo);
            ResultSet rs2 = ps2.executeQuery();
            if (rs2.next()) {
                valor_id_dispositivo = rs2.getInt("id");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valor_id_dispositivo;
    }

    //modificar
    public int modificarDispositivo(int id_dispositivo, String nombre, String descripcion, int id_subsistema,  int id_empresa_mantenedora, int id_tipo_dispositivo) throws Exception {
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    " update dispositivo " +
                    " set nombre = ? , descripcion = ?, id_subsistema= ?, id_empresa_mantenedora = ?, id_tipo_dispositivo = ? " +
                    " where id_dispositivo=?");
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setInt(3, id_subsistema);
            ps.setInt(4, id_empresa_mantenedora);
            ps.setInt(5, id_tipo_dispositivo);
            ps.setInt(6, id_dispositivo);
            ps.executeUpdate();
            ps.close();
            conn.close();
            return 1; //ok
        } catch (Exception e) {
            e.printStackTrace();
            return -1;//no se pudo realizar la eliminacion
        }

    }

    //eliminar
    public int eliminarDispositivo(int id_dispositivo) throws Exception {
        try {
            Connection conn           = ConnectionPool.getInstance().getConnection();
            PreparedStatement delete  = null;
            delete                    = conn.prepareCall(
                    " delete from dispositivo where id_dispositivo=?");
            delete.setInt(1, id_dispositivo);
            delete.executeUpdate();
            delete.close();
            conn.close();
            return id_dispositivo;
        }  catch(Exception e){
            e.printStackTrace();
            return -1;//throw(e);
        }

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
            DispositivoVO dispositivo = null;
            while (rs.next()) {
                dispositivo = new DispositivoVO();
                dispositivo.setIdSistema(new Integer(rs.getInt("id_sistema")));
                dispositivo.setNombreSistema(rs.getString("nombre"));
                lista_sistema.add(dispositivo);
            }
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return lista_sistema;
    }

    //para lista_subsistema
    public List lista_subsistema() throws Exception {
        List lista_subsistema       = new LinkedList();
        try {
            Connection conn      = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    " select id_subsistema, nombre " +
                    " from subsistema " +
                    " order by id_subsistema");
            ResultSet rs  = ps.executeQuery();
            DispositivoVO dispositivo = null;
            while (rs.next()) {
                dispositivo = new DispositivoVO();
                dispositivo.setIdSubsistema(new Integer(rs.getInt("id_subsistema")));
                dispositivo.setNombreSubsistema(rs.getString("nombre"));
                lista_subsistema.add(dispositivo);
            }
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return lista_subsistema;
    }

    //empresas
    public List lista_empresa() throws Exception {
           List lista_empresa       = new LinkedList();
           try {
               Connection conn      = ConnectionPool.getInstance().getConnection();
               PreparedStatement ps = conn.prepareCall(
                       " select id_empresa_mantenedora, nombre" +
                       " from empresa_mantenedora " +
                       " order by id_empresa_mantenedora");
               ResultSet rs  = ps.executeQuery();
               DispositivoVO dispositivo = null;
               while (rs.next()) {
                   dispositivo = new DispositivoVO();
                   dispositivo.setIdEmpresa(new Integer(rs.getInt("id_empresa_mantenedora")));
                   dispositivo.setNombreEmpresa(rs.getString("nombre"));
                   lista_empresa.add(dispositivo);
               }
               conn.close();
           }
           catch (Exception e) {
               e.printStackTrace();
           }
           return lista_empresa;
    }

    //tipos de dispositivos
    public List lista_tipo_dispositivo() throws Exception {
           List tipo_dispositivo       = new LinkedList();
           try {
               Connection conn      = ConnectionPool.getInstance().getConnection();
               PreparedStatement ps = conn.prepareCall(
                       " select id_tipo_dispositivo, descripcion  " +
                       " from tipo_dispositivo " +
                       " order by 1");
               ResultSet rs  = ps.executeQuery();
               DispositivoVO dispositivo = null;
               while (rs.next()) {
                   dispositivo = new DispositivoVO();
                   dispositivo.setIdTipoDispositivo(new Integer(rs.getInt("id_tipo_dispositivo")));
                   dispositivo.setTipoDispositivo(rs.getString("descripcion"));
                   tipo_dispositivo.add(dispositivo);
               }
               conn.close();
           }
           catch (Exception e) {
               e.printStackTrace();
           }
           return tipo_dispositivo;
    }

    //sePuedeEliminar
        public String sePuedeEliminar(int id_dispositivo) throws Exception {
            List listaDispositivo = new LinkedList();
            String respuesta = "no";
            int valor=0;
            try {
                Connection conn           = ConnectionPool.getInstance().getConnection();
                String select_1 =
                        " select count(*) as id_dispositivo" +
                        " from falla " +
                        " where id_dispositivo="+ id_dispositivo;
                PreparedStatement ps = conn.prepareCall(select_1);
                ResultSet rs = ps.executeQuery();
                DispositivoVO dispositivo = null;
                while (rs.next()) {
                    dispositivo = new DispositivoVO();
                    dispositivo.setIdDispositivo(new Integer(rs.getInt("id_dispositivo")));
                    listaDispositivo.add(dispositivo);
                }
                Iterator i = listaDispositivo.iterator();
                while (i.hasNext()) {
                    DispositivoVO dispositivo2 = (DispositivoVO) i.next();
                    valor= dispositivo2.getIdDispositivo();
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



    public String nombreSubsistema(int id) throws Exception{
      String nombre = "";
      try{
      nombre=    " select ss.nombre " +
                  " from subsistema ss, dispositivo d " +
                  " where d.id_dispositivo = " + id +
                  " and d.id_subsistema =ss.id_subsistema" ;
          Connection conn = ConnectionPool.getInstance().getConnection();
          PreparedStatement ps = conn.prepareCall(
                  " select ss.nombre " +
                  " from subsistema ss, dispositivo d " +
                  " where d.id_dispositivo = " + id +
                  " and d.id_subsistema =ss.id_subsistema") ;
          ResultSet rs = ps.executeQuery();
          if(rs.next()) {
              nombre = rs.getString("nombre");
          }
          conn.close();
      } catch (Exception e) {
          e.printStackTrace();
      }
      return nombre;
  }



}


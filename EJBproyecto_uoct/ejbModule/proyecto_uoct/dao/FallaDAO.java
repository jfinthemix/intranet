package proyecto_uoct.dao;

import java.sql.*;
import java.text.*;
import java.util.*;

import proyecto_uoct.common.*;
import proyecto_uoct.fallas.VO.*;

public class FallaDAO {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    public FallaDAO() {
    }
    private static FallaDAO dao = null;
    public static FallaDAO getInstance() {
        if (dao == null) {
            dao = new FallaDAO();
        }
        return dao;
    }

    /**********************************/
    /*     insert, select, delete     */
    /**********************************/
public String fecha_hora_actual(){
        String fecha_hora = "";
        try {
            Connection conn      = ConnectionPool.getInstance().getConnection();
            String select        = "SELECT TO_CHAR(SYSDATE, 'DD-MM-YYYY HH24:MI') FROM dual" ;
            PreparedStatement ps = conn.prepareCall(select);
            ResultSet rs         = ps.executeQuery();

            if (rs.next()) {
                fecha_hora       = rs.getString(1);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fecha_hora;
    }
    public String fecha_actual(){
            String fecha = "";
            try {
                Connection conn      = ConnectionPool.getInstance().getConnection();
                String select        = "  SELECT TO_CHAR(SYSDATE, 'DD-MM-YYYY') FROM dual" ;
                PreparedStatement ps = conn.prepareCall(select);
                ResultSet rs         = ps.executeQuery();

                if (rs.next()) {
                    fecha            = rs.getString(1);
                }
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return fecha;
    }



    //para buscar_falla_periodo
    public List buscar_falla(Integer lista_sistema, Integer lista_subsistema, Integer lista_dispositivo, String fecha_ingreso1, String fecha_ingreso2, Integer estado) throws Exception {
        List lista_falla = new LinkedList();
        //System.out.println(estado);
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            String select_1 =
            " select f.id_falla, TO_CHAR(f.fecha_ingreso, 'DD-MM-YYYY HH24:MI') as fecha_ingreso, f.titulo, TO_CHAR(f.fecha_cierre, 'DD-MM-YYYY HH24:MI') as fecha_cierre, TO_CHAR(f.fecha_plazo, 'DD-MM-YYYY HH24:MI') as fecha_plazo, f.estado " +
            " from falla f, dispositivo d, subsistema ss, sistema s " +
            " where f.id_dispositivo               = d.id_dispositivo " +
            " and d.id_subsistema                  = ss.id_subsistema " +
            " and ss.id_sistema                    = s.id_sistema " +
            " and f.fecha_ingreso                 >= TO_DATE('"+fecha_ingreso1+"','DD-MM-YYYY HH24:MI') " +
            " and f.fecha_ingreso                 <= TO_DATE('"+fecha_ingreso2+" 23:59','DD-MM-YYYY HH24:MI') " ;
            if ( estado != 0) {
                if( estado.equals(1) ) {
                  select_1 += " and f.estado         in (1,3)";
                  }
                else
                  select_1 += " and f.estado         =" + estado;
                }
            if ( lista_sistema > 0) {
                select_1 += " and ss.id_sistema    =" + lista_sistema;
                }
            if ( lista_subsistema > 0) {
                select_1 += " and d.id_subsistema  =" + lista_subsistema;
                }
            if ( lista_dispositivo > 0) {
                select_1 += " and f.id_dispositivo =" + lista_dispositivo;
                }
            select_1 += " union";
            select_1 += " select c2.id_comentario, TO_CHAR(c2.fecha_actualiza, 'DD-MM-YYYY HH24:MI') as fecha_ingreso, " +
                    " 'Comentario'  || ' ' || d2.nombre  || ' ' || d2.descripcion || ' ' ||  c2.comentario \"titulo\", " +
                    " TO_CHAR(c2.fecha_actualiza, 'DD-MM-YYYY HH24:MI') as fecha_plazo, TO_CHAR(c2.fecha_actualiza, 'DD-MM-YYYY HH24:MI') as fecha_cierre,0 " +
                    " from comentario c2, sistema s2, subsistema ss2, dispositivo d2 " +
                    " where c2.id_dispositivo       = d2.id_dispositivo " +
                    " and d2.id_subsistema          = ss2.id_subsistema " +
                    " and ss2.id_sistema            = s2.id_sistema " +
                    " and c2.fecha_actualiza       >= TO_DATE('"+fecha_ingreso1+"','DD-MM-YYYY HH24:MI') " +
                    " and c2.fecha_actualiza       <= TO_DATE('"+fecha_ingreso2+" 23:59','DD-MM-YYYY HH24:MI') " ;
            if ( lista_sistema > 0) {
                select_1 += " and ss2.id_sistema    =" + lista_sistema;
            }
            if ( lista_subsistema > 0) {
                select_1 += " and d2.id_subsistema  =" + lista_subsistema;
            }
           if ( lista_dispositivo > 0) {
                select_1 += " and c2.id_dispositivo =" + lista_dispositivo;
            }
            select_1 += " order by 2, 1";
//System.out.print(select_1);
            PreparedStatement ps = conn.prepareCall(select_1);
            ResultSet rs         = ps.executeQuery();
            FallaVO falla        = null;
            while (rs.next()) {
                falla = new FallaVO();
                falla.set_id_falla(new Integer(rs.getInt("id_falla")));
                falla.set_fecha_ingreso2(rs.getString("fecha_ingreso"));
                falla.set_titulo(rs.getString("titulo"));
                falla.set_fecha_plazo2(rs.getString("fecha_plazo"));
                falla.set_fecha_cierre2(rs.getString("fecha_cierre"));
                falla.set_estado(new Integer(rs.getInt("estado")));
                lista_falla.add(falla);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista_falla;
    }

    //para ver_detalle_falla
    public List ver_detalle_falla(Integer id_falla, String evento) throws Exception {
        List lista_detalle_falla = new LinkedList();
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            if(evento.equals("falla")){
                String select_2 =
                        " select  d.id_dispositivo, f.id_falla, s.nombre as sistema, ss.nombre as subsistema, d.nombre as dispositivo, f.titulo, f.estado, TO_CHAR(f.fecha_ingreso, 'DD-MM-YYYY HH24:MI') as fecha_ingreso, u.username as usu_inicia, TO_CHAR(f.fecha_cierre, 'DD-MM-YYYY HH24:MI') as fecha_cierre, u2.username as usu_cierra, TO_CHAR(f.fecha_plazo, 'DD-MM-YYYY HH24:MI') as fecha_plazo, " +
                        " df.id_detalle_falla, df.comentario, TO_CHAR(df.fecha_actualiza, 'DD-MM-YYYY HH24:MI') as fecha_actualiza, u3.username as usu_actualiza " +
                        " from detalle_falla df, falla f, usuario u, usuario u2, usuario u3, dispositivo d, subsistema ss, sistema s " +
                        " where f.id_usu_inicia   = u.id_usu " +
                        " and f.id_usu_cierra     = u2.id_usu " +
                        " and df.id_usu_actualiza = u3.id_usu " +
                        " and df.id_falla         = f.id_falla " +
                        " and f.id_dispositivo    = d.id_dispositivo " +
                        " and d.id_subsistema     = ss.id_subsistema " +
                        " and ss.id_sistema       = s.id_sistema " +
                        " and f.id_falla          = " + id_falla +
                        " order by df.id_detalle_falla";
                PreparedStatement ps = conn.prepareCall(select_2);
                ResultSet rs = ps.executeQuery();

                FallaVO falla = null;
                while (rs.next()) {
                    falla = new FallaVO();
                    falla.set_id_dispositivo(new Integer(rs.getString(
                            "id_dispositivo")));
                    falla.set_id_falla(new Integer(rs.getString("id_falla")));
                    falla.set_nombre_sistema(rs.getString("sistema"));
                    falla.set_nombre_subsistema(rs.getString("subsistema"));
                    falla.set_nombre_dispositivo(rs.getString("dispositivo"));
                    falla.set_titulo(rs.getString("titulo"));
                    falla.set_estado(new Integer(rs.getInt("estado")));
                    falla.set_fecha_ingreso2(rs.getString("fecha_ingreso"));
                    falla.set_usu_inicia(rs.getString("usu_inicia"));
                    falla.set_fecha_cierre2(rs.getString("fecha_cierre"));
                    falla.set_usu_cierra(rs.getString("usu_cierra"));
                    falla.set_id_detalle_falla(new Integer(rs.getInt(
                            "id_detalle_falla")));
                    falla.set_comentario(rs.getString("comentario"));
                    falla.set_fecha_actualiza2(rs.getString("fecha_actualiza"));
                    falla.set_username(rs.getString("usu_actualiza"));
                    falla.set_fecha_plazo2(rs.getString("fecha_plazo"));
                    lista_detalle_falla.add(falla);
                }
            }
            if(evento.equals("comentario"))    {
                String select_2 =
                        " select d.id_dispositivo, c.id_comentario, s.nombre as sistema, ss.nombre as subsistema, d.nombre as dispositivo, '-' as titulo, 0 as estado, TO_CHAR(c.fecha_actualiza, 'DD-MM-YYYY HH24:MI') as fecha_ingreso, u.username as usu_inicia, TO_CHAR(c.fecha_actualiza, 'DD-MM-YYYY HH24:MI') as fecha_cierre, " +
                        " u.username as usu_cierra, TO_CHAR(c.fecha_actualiza, 'DD-MM-YYYY HH24:MI') as fecha_plazo, c.id_comentario as id_detalle_falla, c.comentario, TO_CHAR(c.fecha_actualiza, 'DD-MM-YYYY HH24:MI') as fecha_actualiza, u.username as usu_actualiza " +
                        " from comentario c, usuario u, dispositivo d, subsistema ss, sistema s " +
                        " where c.id_usu_actualiza   = u.id_usu " +
                        " and c.id_dispositivo       = d.id_dispositivo " +
                        " and d.id_subsistema        = ss.id_subsistema " +
                        " and ss.id_sistema          = s.id_sistema " +
                        " and c.id_comentario        = " + id_falla +
                        " order by c.id_comentario";
                PreparedStatement ps = conn.prepareCall(select_2);
                ResultSet rs = ps.executeQuery();

                FallaVO falla = null;
                while (rs.next()) {
                    falla = new FallaVO();
                    falla.set_id_dispositivo(new Integer(rs.getString("id_dispositivo")));
                    falla.set_id_falla(new Integer(rs.getString("id_comentario")));
                    falla.set_nombre_sistema(rs.getString("sistema"));
                    falla.set_nombre_subsistema(rs.getString("subsistema"));
                    falla.set_nombre_dispositivo(rs.getString("dispositivo"));
                    falla.set_titulo(rs.getString("titulo"));
                    falla.set_estado(new Integer(rs.getInt("estado")));
                    falla.set_fecha_ingreso2(rs.getString("fecha_ingreso"));
                    falla.set_usu_inicia(rs.getString("usu_inicia"));
                    falla.set_fecha_cierre2(rs.getString("fecha_cierre"));
                    falla.set_usu_cierra(rs.getString("usu_cierra"));
                    falla.set_id_detalle_falla(new Integer(rs.getInt(
                            "id_detalle_falla")));
                    falla.set_comentario(rs.getString("comentario"));
                    falla.set_fecha_actualiza2(rs.getString("fecha_actualiza"));
                    falla.set_username(rs.getString("usu_actualiza"));
                    falla.set_fecha_plazo2(rs.getString("fecha_plazo"));
                    lista_detalle_falla.add(falla);
                }
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista_detalle_falla;
    }

    //para ingresar_falla
    public int ingresar_falla(Integer n_id_dispositivo, Integer n_id_usu_inicia,Integer n_id_usu_cierra, String titulo, String problema, String n_fecha_ingreso, String n_fecha_cierre, Integer n_estado, String comentario) throws Exception {
        int valor_id_falla         = 0;
        int valor_id_detalle_falla = 0;
        int variable_aux           = 0;
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps   = null;
            PreparedStatement ps22 = null;
            PreparedStatement ps23 = null;
            PreparedStatement ps3  = null;
            PreparedStatement ps4  = null;

            ps = conn.prepareCall(
                    " insert into falla (id_falla, id_dispositivo, id_usu_inicia, id_usu_cierra, titulo, fecha_ingreso, fecha_cierre, estado, fecha_plazo, problema) " +
                    " values (s_falla.nextval, ?, ?, ?, ?, TO_DATE(?,'DD-MM-YYYY HH24:MI'), '', ?, TO_DATE(?,'DD-MM-YYYY HH24:MI'), ?)"); //id_falla.nextval
            ps.setInt(1, n_id_dispositivo);
            ps.setInt(2, n_id_usu_inicia);
            ps.setInt(3, n_id_usu_cierra);
            ps.setString(4, titulo);
            ps.setString(5, n_fecha_ingreso);
            ps.setInt(6, n_estado);
            ps.setString(7, n_fecha_ingreso);
            ps.setString(8, problema);
            ps.executeUpdate();

            ps22 = conn.prepareCall("select s_falla.currval from dual");
            ResultSet rs22 = ps22.executeQuery();
            if (rs22.next()) {
                valor_id_falla = rs22.getInt(1);
            }

            ps23 = conn.prepareCall("select max(id_detalle_falla)+1 from detalle_falla");
            ResultSet rs23 = ps23.executeQuery();
            if (rs23.next()) {
                valor_id_detalle_falla = rs23.getInt(1);
                //System.out.print("id_detalle_falla: "+valor_id_detalle_falla);
            }

            ps3 = conn.prepareCall(
                    " insert into detalle_falla (id_detalle_falla, id_usu_actualiza, id_falla, comentario, fecha_actualiza) " +
                    " values (?, ?, ?, ?, TO_DATE(?,'DD-MM-YYYY HH24:MI'))"); //TO_DATE(?,'DD-MM-YYYY HH:MM'))
            ps3.setInt(1, valor_id_detalle_falla);
            ps3.setInt(2, n_id_usu_inicia);
            ps3.setInt(3, valor_id_falla);
            ps3.setString(4, comentario);
            ps3.setString(5, n_fecha_ingreso);
            variable_aux=ps3.executeUpdate();

            if(variable_aux==1){
                ps4 = conn.prepareCall(
                    " UPDATE FALLA SET FECHA_PLAZO=to_date((SELECT to_char(fecha_ingreso + 8/24, 'DD-MM-YYYY HH24:MI') " +
                    " FROM FALLA " +
                    " WHERE id_falla=?),'DD-MM-YYYY HH24:MI') WHERE id_falla=?");
                ps4.setInt(1, valor_id_falla);
                ps4.setInt(2, valor_id_falla);
                ps4.executeUpdate();
            }
            else{
                ps = conn.prepareCall(
                        " delete from falla " +
                        " where id_falla=?"); //id_falla.nextval
                ps.setInt(1, valor_id_falla);
                ps.executeUpdate();
            }
            conn.close();
        } catch (Exception ex3) {
            ex3.printStackTrace();
            throw ex3;
        }
        return valor_id_falla;
    }
    //para ingresar_detalle_falla
    public void ingresar_detalle_falla(Integer id_falla, Integer n_id_usu_actualiza, String comentario, String fecha_actualiza) throws  Exception {
        int valor_id_detalle_falla=0;
        try {
            Connection conn      = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = null;
            ps                   = conn.prepareCall("select max(id_detalle_falla)+1 from detalle_falla");
            ResultSet rs         = ps.executeQuery();
            if (rs.next()) {
                valor_id_detalle_falla = rs.getInt(1);
            }
            PreparedStatement ps3 = conn.prepareCall(
            " insert into detalle_falla (id_detalle_falla, id_usu_actualiza, id_falla, comentario, fecha_actualiza) " +
            " values (?, ?, ?, ?, TO_DATE(?,'DD-MM-YYYY HH24:MI'))");
            ps3.setInt(1, valor_id_detalle_falla);
            ps3.setInt(2, n_id_usu_actualiza);
            ps3.setInt(3, id_falla);
            ps3.setString(4, comentario);
            ps3.setString(5, fecha_actualiza);
            ps3.executeUpdate();
            ps3.close();
            conn.close();
        }
        catch (Exception e) {
           e.printStackTrace();
       }
   }
   //para ingresar_detalle_falla_y_cierre
    public void ingresar_detalle_falla_y_cierre(Integer id_falla, Integer n_id_usu_actualiza, String comentario, String fecha_actualiza) throws  Exception {
        int valor_id_detalle_falla=0;
        try {
            Connection conn      = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = null;
            ps                   = conn.prepareCall(" select max(id_detalle_falla)+1 from detalle_falla ");
            ResultSet rs         = ps.executeQuery();
            if (rs.next()) {
                valor_id_detalle_falla = rs.getInt(1);
            }
            PreparedStatement ps3 = conn.prepareCall(
            " insert into detalle_falla (id_detalle_falla, id_usu_actualiza, id_falla, comentario, fecha_actualiza) " +
            " values (?, ?, ?, ?, TO_DATE(?,'DD-MM-YYYY HH24:MI'))");
            ps3.setInt(1, valor_id_detalle_falla);
            ps3.setInt(2, n_id_usu_actualiza);
            ps3.setInt(3, id_falla);
            ps3.setString(4, comentario);
            ps3.setString(5, fecha_actualiza);
            ps3.executeUpdate();
            ps3.close();
            conn.close();
        } catch (Exception e) {
           e.printStackTrace();
       }
       try{
           Connection conn       = ConnectionPool.getInstance().getConnection();
           PreparedStatement ps4 = conn.prepareCall(
           " update falla " +
           " set id_usu_cierra = ? , fecha_cierre=TO_DATE(?,'DD-MM-YYYY HH24:MI'), estado =2 "+
           " where id_falla=?");
           ps4.setInt(1, n_id_usu_actualiza);
           ps4.setString(2, fecha_actualiza);
           ps4.setInt(3, id_falla);
           ps4.executeUpdate();
           ps4.close();
           conn.close();
       }
       catch (Exception e) {
           e.printStackTrace();
       }
   }
   //para ingresar_detalle_falla_y_solicitarcierre
      public void ingresar_detalle_falla_y_solicitarcierre(Integer id_falla, Integer n_id_usu_actualiza, String comentario, String fecha_actualiza) throws  Exception {
          int valor_id_detalle_falla=0;
          try {
              Connection conn      = ConnectionPool.getInstance().getConnection();
              PreparedStatement ps = null;
              ps                   = conn.prepareCall(" select max(id_detalle_falla)+1 from detalle_falla ");
              ResultSet rs         = ps.executeQuery();
              if (rs.next()) {
                  valor_id_detalle_falla = rs.getInt(1);
              }
              PreparedStatement ps3 = conn.prepareCall(
              " insert into detalle_falla (id_detalle_falla, id_usu_actualiza, id_falla, comentario, fecha_actualiza) " +
              " values (?, ?, ?, ?, TO_DATE(?,'DD-MM-YYYY HH24:MI'))");
              ps3.setInt(1, valor_id_detalle_falla);
              ps3.setInt(2, n_id_usu_actualiza);
              ps3.setInt(3, id_falla);
              ps3.setString(4, comentario);
              ps3.setString(5, fecha_actualiza);
              ps3.executeUpdate();
              ps3.close();
              conn.close();
          } catch (Exception e) {
             e.printStackTrace();
         }
         try{
             Connection conn       = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps4 = conn.prepareCall(
             " update falla " +
             " set id_usu_cierra = ? , fecha_cierre=TO_DATE(?,'DD-MM-YYYY HH24:MI'), estado =3 "+
             " where id_falla=?");
             ps4.setInt(1, n_id_usu_actualiza);
             ps4.setString(2, fecha_actualiza);
             ps4.setInt(3, id_falla);
             ps4.executeUpdate();
             ps4.close();
             conn.close();
         }
         catch (Exception e) {
             e.printStackTrace();
         }
   }

   //para ingresar_comentario
   public int ingresar_comentario(Integer n_id_dispositivo,
                                     Integer n_id_usu_actualiza,
                                     String n_fecha_actualiza, String comentario)throws Exception {
         int valor_id_comentario=0;
         try {
             Connection conn       = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps  = null;
             PreparedStatement ps1 = null;
             PreparedStatement ps2 = null;
             ps                    = conn.prepareCall(" select max(id_comentario)+1 from comentario ");
             ResultSet rs          = ps.executeQuery();
             if (rs.next()) {
                 valor_id_comentario = rs.getInt(1);
                 //System.out.print("id_detalle_falla: "+valor_id_detalle_falla);
             }

             ps1 = conn.prepareCall(
                     " insert into comentario (id_comentario, id_dispositivo, id_usu_actualiza, comentario, fecha_actualiza) " +
                     " values (?, ?, ?, ?, TO_DATE(?,'DD-MM-YYYY HH24:MI'))");
             ps1.setInt(1, valor_id_comentario);
             ps1.setInt(2, n_id_dispositivo);
             ps1.setInt(3, n_id_usu_actualiza);
             ps1.setString(4, comentario);
             ps1.setString(5, n_fecha_actualiza);
             ps1.executeUpdate();

             ps2 = conn.prepareCall("select max(id_comentario) as id_comentario from comentario");
             ResultSet rs2 = ps2.executeQuery();
             if (rs2.next()) {
                 valor_id_comentario = rs2.getInt(1);
             }
             conn.close();
         } catch (Exception ex3) {
             ex3.printStackTrace();
             throw ex3;
         }
         return valor_id_comentario;
     }
  //para eliminar_falla
  public void eliminar_falla(Integer id_falla) throws Exception {
      try {
          Connection conn           = ConnectionPool.getInstance().getConnection();
          PreparedStatement delete  = null;
          PreparedStatement delete2 = null;
          delete                    = conn.prepareCall(
            " delete from detalle_falla where id_falla=?");
            delete.setInt(1, id_falla);
            delete.executeUpdate();
            delete.close();
            delete2 = conn.prepareCall(
            " delete from falla where id_falla=?");
            delete2.setInt(1, id_falla);
            delete2.executeUpdate();
            delete2.close();
            conn.close();
        }  catch(Exception e){
            e.printStackTrace();
        }
    }

    /**********************************/
    /*                                */
    /*    lista/menu para busqueda    */
    /*                                */
    /**********************************/

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
            FallaVO falla = null;
            while (rs.next()) {
                falla = new FallaVO();
                falla.set_id_sistema(new Integer(rs.getInt("id_sistema")));
                falla.set_nombre_sistema(rs.getString("nombre"));
                lista_sistema.add(falla);
            }
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return lista_sistema;
    }

    //para lista_subsistema
    public List lista_subsistema(Integer id_sistema) throws Exception {
        List lista_subsistema = new LinkedList();
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    " select ss.id_subsistema, ss.nombre " +
                    " from subsistema ss, sistema s " +
                    " where ss.id_sistema = s.id_sistema " +
                    " order by ss.id_subsistema");
            //" and ss.id_sistema = " + id_sistema +
            ResultSet rs = ps.executeQuery();
            FallaVO falla = null;
            while (rs.next()) {
                falla = new FallaVO();
                falla.set_id_subsistema(new Integer(rs.getInt("id_subsistema")));
                falla.set_nombre_subsistema(rs.getString("nombre"));
                lista_subsistema.add(falla);
            }
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return lista_subsistema;
    }

    //para lista_dispositivo
    public List lista_dispositivo(Integer id_sistema, Integer id_subsistema) throws Exception {
        List lista_dispositivo   = new LinkedList();
        try {
            Connection conn      = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    " select d.id_dispositivo, d.nombre || '  ' || d.descripcion \"nombre\"  " +
                    " from dispositivo d, subsistema ss, sistema s " +
                    " where d.id_subsistema = ss.id_subsistema " +
                    " and ss.id_sistema     = s.id_sistema " +
                    " and d.nombre         <> 'no disponible' " +
                    " order by d.id_dispositivo"  );
            //"  and ss.id_sistema = " + id_sistema +
            //" and d.id_subsistema = " + id_subsistema +
            ResultSet rs = ps.executeQuery();
            FallaVO falla = null;
            while (rs.next()) {
                falla = new FallaVO();
                falla.set_id_dispositivo(new Integer(rs.getInt("id_dispositivo")));
                falla.set_nombre_dispositivo(rs.getString("nombre"));
                lista_dispositivo.add(falla);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista_dispositivo;
    }
    //para lista_dispositivo
    public List lista_dispositivo2(Integer id_sistema, Integer id_subsistema) throws Exception {
        List lista_dispositivo   = new LinkedList();
        try {
            Connection conn      = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    " select d.id_dispositivo, d.nombre \"nom\", d.nombre || '  ' || d.descripcion \"nombre\"  " +
                    " from dispositivo d, subsistema ss, sistema s " +
                    " where d.id_subsistema = ss.id_subsistema " +
                    " and ss.id_sistema     = s.id_sistema " +
                    " and d.nombre         <> 'no disponible' " +
                    " order by d.id_dispositivo"  );
            //"  and ss.id_sistema = " + id_sistema +
            //" and d.id_subsistema = " + id_subsistema +
            ResultSet rs = ps.executeQuery();
            FallaVO falla = null;
            while (rs.next()) {
                falla = new FallaVO();
                falla.set_id_dispositivo(new Integer(rs.getInt("id_dispositivo")));
                falla.set_nom_dispositivo(rs.getString("nom"));
                falla.set_nombre_dispositivo(rs.getString("nombre"));
                lista_dispositivo.add(falla);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista_dispositivo;
    }


    //para SistemaSubsistema
    public List SistemaSubsistema() throws Exception {
        List lista = new LinkedList();
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    " select s.id_sistema, s.nombre as nombre_sistema, ss.id_subsistema, ss.nombre as nombre_subsistema" +
                    " from sistema s, subsistema ss " +
                    " where s.id_sistema = ss.id_sistema " +
                    "order by s.id_sistema, ss.id_subsistema");
            ResultSet rs = ps.executeQuery();
            FallaVO falla = null;
            while (rs.next()) {
                falla = new FallaVO();
                falla.set_id_sistema(new Integer(rs.getInt("id_sistema")));
                falla.set_nombre_sistema(rs.getString("nombre_sistema"));
                falla.set_id_subsistema(new Integer(rs.getInt("id_subsistema")));
                falla.set_nombre_subsistema(rs.getString("nombre_subsistema"));
                lista.add(falla);
            }
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    //SisteaSubsistemaDispositivo
    public List SistemaSubsistemaDispositivo() throws Exception {
        List lista = new LinkedList();
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    " select s.id_sistema, s.nombre as nombre_sistema, ss.id_subsistema, ss.nombre as nombre_subsistema, " +
                    " d.id_dispositivo, d.nombre || '  ' || d.descripcion \"nombre_dispositivo\"  " +
                    " from sistema s, subsistema ss, dispositivo d " +
                    " where s.id_sistema  = ss.id_sistema " +
                    " and d.id_subsistema = ss.id_subsistema " +
                    " order by s.id_sistema, ss.id_subsistema, d.id_dispositivo ");
            ResultSet rs = ps.executeQuery();
            FallaVO falla = null;
            while (rs.next()) {
                falla = new FallaVO();
                falla.set_id_sistema(new Integer(rs.getInt("id_sistema")));
                falla.set_nombre_sistema(rs.getString("nombre_sistema"));
                falla.set_id_subsistema(new Integer(rs.getInt("id_subsistema")));
                falla.set_nombre_subsistema(rs.getString("nombre_subsistema"));
                falla.set_id_dispositivo(new Integer(rs.getInt("id_dispositivo")));
                falla.set_nombre_dispositivo(rs.getString("nombre_dispositivo"));
                lista.add(falla);
            }
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int tipoDispositivo(Integer id_dispositivo) throws Exception{
        int tipoDispositivo = 0;
        try{
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    " select id_tipo_dispositivo " +
                    " from dispositivo " +
                    " where id_dispositivo = " + id_dispositivo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                tipoDispositivo= rs.getInt("id_tipo_dispositivo");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tipoDispositivo;
    }

    public int empresaMantenedora(Integer id_dispositivo) throws Exception{
        Integer empresaMantenedora = 0;
        try{
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    " select id_empresa_mantenedora " +
                    " from dispositivo " +
                    " where id_dispositivo = " + id_dispositivo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                empresaMantenedora = rs.getInt("id_empresa_mantenedora");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empresaMantenedora;
    }
    public int idDispositivo(Integer id_falla) throws Exception{
           Integer idDispositivo = 0;
           try{
               Connection conn = ConnectionPool.getInstance().getConnection();
               PreparedStatement ps = conn.prepareCall(
                       " select id_dispositivo " +
                       " from falla " +
                       " where id_falla = " + id_falla);
               ResultSet rs = ps.executeQuery();
               if(rs.next()) {
                   idDispositivo = rs.getInt("id_dispositivo");
               }
               conn.close();
           } catch (Exception e) {
               e.printStackTrace();
           }
           return idDispositivo;
    }

    public String mailDispositivo(Integer id_tipo_dispositivo, int id_empresa_mantenedora) throws Exception{
        String mailDispositivo = "";
        try{
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    " select mail " +
                    " from emp_mant_tipo_dispo " +
                    " where id_tipo_dispositivo    = " + id_tipo_dispositivo +
                    " and id_empresa_mantenedora   = " + id_empresa_mantenedora);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                mailDispositivo= rs.getString("mail");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mailDispositivo;
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
                     " and p.id_funcionalidad in (107)") ;
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


    public String nombreUsuario(Integer ses_idusu) throws Exception{
        String nombreUsuario = "";
        try{
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    " select nombre_usu ||' '|| apellido_usu ||' '|| apellido2_usu \"nombre_usuario\" " +
                    " from usuario " +
                    " where id_usu= " + ses_idusu);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                nombreUsuario= rs.getString("nombre_usuario");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nombreUsuario;
    }

    public String nomEmpresaMantenedora(Integer id_empresa_mantenedora) throws Exception{
        String nomEmpresaMantenedora = "";
        try{
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    " select nombre as nombre_empresa" +
                    " from empresa_mantenedora " +
                    " where id_empresa_mantenedora = " + id_empresa_mantenedora);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                nomEmpresaMantenedora= rs.getString("nombre_empresa");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nomEmpresaMantenedora;
    }


    //    consultarReporte
        public List consultarReporte(Integer lista_sistema, Integer lista_subsistema, Integer lista_dispositivo, String fecha_ingreso1, String fecha_ingreso2) throws Exception {
            List lista_falla = new LinkedList();
            Integer contador = new Integer(0);
            try {
                Connection conn = ConnectionPool.getInstance().getConnection();
                String select_1 = "select f.id_dispositivo, d.nombre, d.descripcion, count (f.id_dispositivo) as cantidad " +
                                  "from falla f, dispositivo d, subsistema ss, sistema s  " +
                                  "where f.id_dispositivo = d.id_dispositivo " +
                                  "and f.fecha_ingreso >= TO_DATE('"+fecha_ingreso1+"','DD-MM-YYYY HH24:MI') " +
                                  "and f.fecha_cierre <= TO_DATE('"+fecha_ingreso2+" 23:59','DD-MM-YYYY HH24:MI') " +
                                  "and d.id_subsistema = ss.id_subsistema " +
                                  "and ss.id_sistema = s.id_sistema " ;
                if ( lista_dispositivo > 0) {
                    select_1 += " and d.id_dispositivo =" + lista_dispositivo;
                }
                if ( lista_subsistema > 0) {
                    select_1 += " and ss.id_subsistema =" + lista_subsistema;
                }
                if ( lista_sistema > 0) {
                    select_1 += " and s.id_sistema =" + lista_sistema;
                }
                select_1 += " group by f.id_dispositivo, d.nombre, d.descripcion  having count (f.id_dispositivo) >1 ";
                //System.out.print(select_1);
                PreparedStatement ps = conn.prepareCall(select_1);
                ResultSet rs         = ps.executeQuery();
                FallaVO falla        = null;
                while (rs.next()) {
                    contador++;
                    falla = new FallaVO();
                    falla.set_id_falla(contador);
              /*      falla.set_titulo(rs.getString("titulo"));
                    falla.set_fecha_ingreso2(rs.getString("fecha_ingreso"));
                    falla.set_fecha_cierre2(rs.getString("fecha_cierre"));
                    falla.set_problema(rs.getString("problema"));
                    falla.set_estado(new Integer(rs.getInt("estado")));*/
                    falla.set_id_dispositivo(new Integer(rs.getInt("id_dispositivo")));
                    falla.set_nombre_dispositivo(rs.getString("nombre"));
                    falla.set_comentario(rs.getString("descripcion"));
                    falla.set_id_detalle_falla(new Integer(rs.getInt("cantidad")));
                    lista_falla.add(falla);
                }
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return lista_falla;
    }





}

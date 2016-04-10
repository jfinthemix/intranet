package proyecto_uoct.dao;

import java.sql.*;
import java.util.*;

import proyecto_uoct.common.*;
import proyecto_uoct.documentacion.VO.*;


public class ClienteDAO {
    Connection conn = null;

//--------------------------------------------
    public ClienteDAO() {
    }

    // Singleton---------

    private static ClienteDAO dao = null;

    public static ClienteDAO getInstance() {
        if (dao == null) {
            dao = new ClienteDAO();
        }
        return dao;
    }


//-----------------------------------------------

    public boolean agregarCli(ClienteVO cliente) {

        boolean ingresado = false;
        String q =
                "INSERT INTO cliente (id_cliente,nombre_cliente,apellido_cliente,activo ";
        String q2 = "values (s_cliente.nextval,'" + cliente.getNomCli() + "','" +
                    cliente.getApeCli() + "',1";

        if (!cliente.getFonoCli().equals("")) {
            q = q + ",telefono_cliente";
            q2 = q2 + ",'" + cliente.getFonoCli() + "'";
        }
        if (!cliente.getEmailCli().equals("")) {
            q = q + ",email_cliente";
            q2 = q2 + ",'" + cliente.getEmailCli() + "'";
        }
        if (!cliente.getCelCli().equals("")) {
            q = q + ",celular_cliente";
            q2 = q2 + ",'" + cliente.getCelCli() + "'";
        }
        if (cliente.getIdEnt()!=null) {
            q = q + ",id_ent_ext";
            q2 = q2 + "," + cliente.getIdEnt();
        }
        if (!cliente.getCargo().equals("")) {
            q = q + ",cargo";
            q2 = q2 + ",'" + cliente.getCargo() + "'";
        }
        if (!cliente.getComentCli().equals("")) {
            q = q + ",comentario_cliente";
            q2 = q2 + ",'" + cliente.getComentCli() + "'";
        }

        q = q + ")";
        q2 = q2 + ")";
        q = q + q2;
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            ingresado = true;
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ingresado;
    }

    //-------------------------------------------------

    public boolean registrarEntExt(EntExtVO entidad) {
        boolean ingresado = false;

        String q1 = "INSERT INTO entidadexterna (id_ent_ext,nombre_entidad,activo";
        String q2 = " (s_entidadexterna.nextval,'" + entidad.getNomEnt() + "',1";
        if (!entidad.getDirEnt().equals("")) {
            q1 = q1 + ",direccion_entidad";
            q2 = q2 + ",'" + entidad.getDirEnt() + "'";
        }
        if (!entidad.getFonoEnt().equals("")) {
            q1 = q1 + ",telefono_entidad";
            q2 = q2 + ",'" + entidad.getFonoEnt().toString() + "'";
        }
        if (!entidad.getWeb().equals("")) {
            q1 = q1 + " ,sitio_web";
            q2 = q2 + ",'" + entidad.getWeb() + "'";
        }
        q1 = q1 + ")values";
        q2 = q2 + ")";

        q1 = q1 + q2;

        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q1);
            ps.executeUpdate();
            ingresado = true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ingresado;
    }

//--------------------------------------------------------
    public List getCliEntporPal(String palClave,boolean activos) {
        List listacli = new LinkedList();
        String q = "SELECT cliente.id_cliente, cliente.nombre_cliente, cliente.apellido_cliente"+
                   " , entidadexterna.id_ent_ext, entidadexterna.nombre_entidad,cliente.activo "+
                   " FROM cliente,entidadexterna "+
                   " WHERE cliente.id_ent_ext=entidadexterna.ID_ENT_EXT(+) "+
                   " AND  (UPPER(cliente.nombre_cliente) "+
                   " LIKE UPPER('%"+palClave+"%') OR UPPER (cliente.apellido_cliente)"+
                   " LIKE UPPER('%"+palClave+"%') OR UPPER(entidadexterna.nombre_entidad) LIKE UPPER('%"+palClave+"%')"+
                   " OR UPPER(cliente.comentario_cliente) LIKE UPPER('%"+palClave+"%') OR"+
                   " UPPER(cliente.email_cliente) LIKE UPPER('%"+palClave+"%') OR"+
                   " UPPER(entidadexterna.nombre_entidad) LIKE UPPER('%"+palClave+"%') OR"+
                   " UPPER(entidadexterna.direccion_entidad) LIKE ('%"+palClave+"%'))";

           if(activos){
               q += " AND CLIENTE.ACTIVO=1 AND ENTIDADEXTERNA.ACTIVO(+)=1";
           }
           q+=" order by cliente.nombre_cliente asc";
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CliEntVO cli = new CliEntVO();
                cli.setIdCli(new Integer(rs.getInt(1)));
                cli.setNomCli(rs.getString(2));
                cli.setApeCli(rs.getString(3));

                cli.setIdEnt(new Integer(rs.getInt(4)));
                cli.setNomEnt(rs.getString(5));
                cli.setIsActivo(new Integer(rs.getInt(6)));
                listacli.add(cli);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listacli;
    }

//-------------------------------------------------------

    public List getAgendaCli() {
        List listacli = new LinkedList();
        String q =
                "SELECT id_cliente, nombre_cliente, apellido_cliente" +
                " ,telefono_cliente,email_cliente,celular_cliente" +
                " ,comentario_cliente,cargo" +
                " ,entidadexterna.id_ent_ext,entidadexterna.nombre_entidad," +
                " entidadexterna.telefono_entidad,entidadexterna.direccion_entidad," +
                " entidadexterna.sitio_web " +
                " FROM cliente,entidadexterna WHERE cliente.id_ent_ext=" +
                " entidadexterna.ID_ENT_EXT(+) order by cliente.nombre_cliente,cliente.apellido_cliente asc";
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ClienteVO cli = new ClienteVO();
                cli.setIdCli(new Integer(rs.getInt(1)));
                cli.setNomCli(rs.getString(2));
                cli.setApeCli(rs.getString(3));
                cli.setFonoCli(rs.getString(4));
                cli.setEmailCli(rs.getString(5));
                cli.setCelCli(rs.getString(6));
                cli.setComentCli(rs.getString(7));
                cli.setCargo(rs.getString(8));

                cli.setIdEnt(new Integer(rs.getInt(9)));
                cli.setNomEnt(rs.getString(10));
                cli.setTelefonoEnt(rs.getString(11));
                cli.setDirEnt(rs.getString(12));
                cli.setWebEnt(rs.getString(13));
                listacli.add(cli);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listacli;
    }


//-------------------------------------------------------

    public ClienteVO getDatosCli(Integer id_cli) {
        ClienteVO cliente = new ClienteVO();
        Connection conn = null;
        String q = "SELECT id_cliente, nombre_cliente, apellido_cliente" +
                   " ,telefono_cliente,email_cliente,celular_cliente" +
                   " ,comentario_cliente,cargo" +
                   " ,entidadexterna.id_ent_ext,entidadexterna.nombre_entidad,cliente.activo" +
                   " FROM cliente, entidadexterna WHERE cliente.id_ent_ext=" +
                   " entidadexterna.ID_ENT_EXT(+) AND id_cliente=?";

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_cli.intValue());
            ResultSet rs = ps.executeQuery();
            rs.next();

            cliente.setIdCli(new Integer(rs.getInt(1)));
            cliente.setNomCli(rs.getString(2));
            cliente.setApeCli(rs.getString(3));
            cliente.setFonoCli(rs.getString(4));
            cliente.setEmailCli(rs.getString(5));
            cliente.setCelCli(rs.getString(6));
            cliente.setComentCli(rs.getString(7));
            cliente.setCargo(rs.getString(8));
            if(rs.getInt(9)!=0){
            cliente.setIdEnt(new Integer(rs.getInt(9)));
            cliente.setNomEnt(rs.getString(10));}
            cliente.setIsActivo(new Integer(rs.getInt(11)));
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cliente;

    }

//---------------------------------------------------------
    public ClienteVO getDatosCliPartic(Integer id_cli) {
        ClienteVO cliente = new ClienteVO();
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "select cliente.id_cliente, cliente.nombre_cliente, cliente.apellido_cliente, cliente.telefono_cliente, cliente.email_cliente, cliente.celular_cliente, cliente.comentario_cliente from cliente where id_cliente=?");
            ps.setInt(1, id_cli.intValue());
            ResultSet rs = ps.executeQuery();

            rs.next();

            cliente.setIdCli(new Integer(rs.getInt(1)));
            cliente.setNomCli(rs.getString(2));
            cliente.setApeCli(rs.getString(3));
            cliente.setFonoCli(rs.getString(4));
            cliente.setEmailCli(rs.getString(5));
            cliente.setCelCli(rs.getString(6));
            cliente.setComentCli(rs.getString(7));
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cliente;

    }


//----------------------------------------------------------

    /**
     * getDatosEnt
     *
     * @param id_ent Integer
     * @return EntExtVO
     */
    public EntExtVO getDatosEnt(Integer id_ent) {

        EntExtVO entidad = new EntExtVO();
        String q = "select ID_ENT_EXT,NOMBRE_ENTIDAD,TELEFONO_ENTIDAD," +
                   "DIRECCION_ENTIDAD,SITIO_WEB,ACTIVO from " +
                   "entidadexterna where entidadexterna.id_ent_ext=?";
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_ent.intValue());
            ResultSet rs = ps.executeQuery();

            rs.next();

            entidad.setIdEnt(new Integer(rs.getInt(1)));
            entidad.setNomEnt(rs.getString(2));
            entidad.setFonoEnt(rs.getString(3));
            entidad.setDirEnt(rs.getString(4));
            entidad.setWeb(rs.getString(5));
            entidad.setIsActivo(new Integer(rs.getInt(6)));

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return entidad;

    }

    /**
     * getCliEntporEnt
     *
     * @param id_ent_ext Integer
     * @return List
     */
    /*  public List getCliEntporEnt(Integer id_ent_ext) {
          List listacli = new LinkedList();

          try {
              Connection conn = ConnectionPool.getInstance().getConnection();
              PreparedStatement ps = conn.prepareCall(
                      "SELECT cliente.id_cliente, cliente.nombre_cliente, cliente.apellido_cliente,entidadexterna.id_ent_ext, entidadexterna.nombre_entidad FROM cliente join entidadexterna ON cliente.id_ent_ext= entidadexterna.ID_ENT_EXT WHERE cliente.id_ent_ext=? ");
              ps.setInt(1, id_ent_ext.intValue());
              ResultSet rs = ps.executeQuery();

              while (rs.next()) {
                  CliEntVO cli = new CliEntVO();
                  cli.setIdCli(new Integer(rs.getInt(1)));
                  cli.setNomCli(rs.getString(2));
                  cli.setApeCli(rs.getString(3));
                  cli.setIdEnt(new Integer(rs.getInt(4)));
                  cli.setNomEnt(rs.getString(5));

                  listacli.add(cli);
              }

              conn.close();

          } catch (Exception e) {
              e.printStackTrace();
          }

          return listacli;

      }
     */
    /**
     * getCliPartic
     *
     * @return List
     */
    public List getCliPartic(boolean activo) {
        List listacli = new LinkedList();
        String q="SELECT cliente.id_cliente, cliente.nombre_cliente, "+
                 "cliente.apellido_cliente,activo "+
                 "FROM cliente WHERE cliente.id_ent_ext is null ";

        if(activo){
            q+=" AND activo=1";
        }

        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CliEntVO cli = new CliEntVO();
                cli.setIdCli(new Integer(rs.getInt(1)));
                cli.setNomCli(rs.getString(2));
                cli.setApeCli(rs.getString(3));
                cli.setIsActivo(new Integer(rs.getInt(4)));
                listacli.add(cli);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listacli;
    }

    /**
     * updateDatosCli
     *
     * @param id_cli Integer
     * @param nom_cli String
     * @param ape_cli String
     * @param tel_cli Integer
     * @param email_cli String
     * @param id_instit Integer
     */
    public void updateDatosCli(ClienteVO cli) {

        Connection conn = null;
        String str;
        if (cli.getIdEnt() != null) {
            str = "UPDATE cliente SET nombre_cliente=?, apellido_cliente=?," +
                  "telefono_cliente=?, email_cliente=?,celular_cliente=?," +
                  "comentario_cliente=?,cargo=?,id_ent_ext=" + cli.getIdEnt() +
                  " where id_cliente=?";

        } else {
            str = "UPDATE cliente SET nombre_cliente=?, apellido_cliente=?," +
                  "telefono_cliente=?, email_cliente=?,celular_cliente=?," +
                  "comentario_cliente=?,cargo=?,id_ent_ext=? where id_cliente=?";

        }
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(str);
            ps.setString(1, cli.getNomCli());
            ps.setString(2, cli.getApeCli());
            ps.setString(3, cli.getFonoCli());
            ps.setString(4, cli.getEmailCli());
            ps.setString(5, cli.getCelCli());
            ps.setString(6, cli.getComentCli());
            ps.setString(7, cli.getCargo());

            if (cli.getIdEnt() == null) {
                ps.setNull(8, 4);
                ps.setInt(9, cli.getIdCli().intValue());
            }
            if (cli.getIdEnt() != null) {
                ps.setInt(8, cli.getIdCli().intValue());
            }

            ps.executeUpdate();

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public List getEntidadesExternas() {
        List lista = new LinkedList();
        String q = "SELECT id_ent_ext,nombre_entidad,activo " +
                   " FROM entidadexterna order by nombre_entidad ";

        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EntExtVO ent = new EntExtVO();
                ent.setIdEnt(new Integer(rs.getInt(1)));
                ent.setNomEnt(rs.getString(2));
                ent.setIsActivo(new Integer (rs.getInt(3)));
                lista.add(ent);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;

    }

    /**
     * actualizarEntExt
     *
     * @param entidad EntExtVO
     * @return boolean
     */
    public boolean actualizarEntExt(EntExtVO entidad) {
        Connection conn;
        String q = "UPDATE entidadexterna SET direccion_entidad=?," +
                   " telefono_entidad=?,sitio_web=? WHERE id_ent_ext=?";

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setString(1, entidad.getDirEnt());
            ps.setString(2, entidad.getFonoEnt());
            ps.setString(3, entidad.getWeb());
            ps.setInt(4, entidad.getIdEnt().intValue());
            ps.executeUpdate();

            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * buscarEntExt
     *
     * @param nomEnt String
     * @return List
     */
    public List buscarEntExt(String nomEnt) {
        List lista = new LinkedList();
        String q = "SELECT id_ent_ext,nombre_entidad" +
                   " FROM entidadexterna WHERE" +
                   " UPPER(nombre_entidad) LIKE UPPER('%" + nomEnt + "%') AND activo=1" +
                   " order by nombre_entidad";
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EntExtVO ent = new EntExtVO();
                ent.setIdEnt(new Integer(rs.getInt(1)));
                ent.setNomEnt(rs.getString(2));
                lista.add(ent);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * getEstadoUsu
     *
     * @param idCli Integer
     * @return Integer
     */
    public Integer getEstadoUsu(Integer idCli) throws Exception {
        List lista = new LinkedList();
        String q = "SELECT activo" +
                   " FROM cliente WHERE" +
                   " id_cliente=?";
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idCli.intValue());
            ResultSet rs = ps.executeQuery();
            rs.next();
            Integer i=new Integer(rs.getInt(1));
            conn.close();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * desactivarCli
     *
     * @param idCli Integer
     */
    public boolean desactivarCli(Integer idCli) throws Exception {
        Connection conn;
        String q = "UPDATE cliente SET activo=0 WHERE id_cliente=? ";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idCli.intValue());

            if (ps.executeUpdate() != 0) {
                conn.close();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;

        }
        return false;

    }

    /**
     * activarCli
     *
     * @param idCli Integer
     */
    public boolean activarCli(Integer idCli) throws Exception {
        Connection conn;
        String q = "UPDATE cliente SET activo =1 WHERE id_cliente=? ";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idCli.intValue());

            if (ps.executeUpdate() != 0) {
                conn.close();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return false;

    }

    /**
     * getEstadoEntExt
     *
     * @param idEntExt Integer
     * @return Integer
     */
    public Integer getEstadoEntExt(Integer idEntExt) throws Exception {
        List lista = new LinkedList();
        String q = "SELECT activo" +
                   " FROM entidadexterna WHERE" +
                   " id_ent_ext=?";
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idEntExt.intValue());
            ResultSet rs = ps.executeQuery();
            rs.next();
            Integer i=new Integer(rs.getInt(1));
            conn.close();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * desactivarEntExt
     *
     * @param idEntExt Integer
     */
    public boolean desactivarEntExt(Integer idEntExt) throws Exception {
        Connection conn;
        String q = "UPDATE entidadexterna SET activo=0 WHERE id_ent_ext=? ";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idEntExt.intValue());

            if (ps.executeUpdate() != 0) {
                conn.close();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;

        }
        return false;

    }

    /**
     * activarEntExt
     *
     * @param idEntExt Integer
     */
    public boolean activarEntExt(Integer idEntExt) throws Exception {
        Connection conn;
        String q = "UPDATE entidadexterna SET activo =1 WHERE id_ent_ext=? ";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idEntExt.intValue());

            if (ps.executeUpdate() != 0) {
                conn.close();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return false;

    }

}

package proyecto_uoct.dao;

import java.sql.*;
import java.text.*;
import java.util.*;

import proyecto_uoct.common.*;
import proyecto_uoct.common.VO.*;
import proyecto_uoct.ventas.VO.*;

public class VentasDAO {
    private VentasDAO() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    private static VentasDAO dao = null;

    public static VentasDAO getInstance() {
        if (dao == null) {
            dao = new VentasDAO();
        }
        return dao;
    }


    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");


    public void regTipoInfo(InfoVtaVO info) throws Exception {
        Connection conn = null;
        String q =
                "INSERT INTO tipoinfovta (idtipoinfo,tipoinfo,unidad,precio,activo)" +
                "values (s_tipoinfovta.nextval,'" + info.getTipoInfo() +
                "','" + info.getUnidad() + "'," + info.getPrecio() + " ,1)";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            conn.close();
            throw e;
        }

    }


    public void actuTipoInfo(InfoVtaVO info) throws Exception {
        Connection conn = null;
        int activo = 0;
        if (info.getIsActivo()) {
            activo = 1;
        }

        String q = "UPDATE tipoinfovta SET unidad='" + info.getUnidad() +
                   "',precio=" + info.getPrecio() +
                   ",activo=" + activo + " WHERE idtipoinfo=" + info.getIdInfo();
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            conn.close();
            throw e;
        }

    }


    public List getTiposInfo(boolean activos) throws Exception {
        Connection conn = null;
        List tipos = new LinkedList();

        String q =
                "select idtipoinfo,tipoinfo,unidad,precio,activo from tipoinfovta ";

        if (activos) {
            q += "where activo=1";
        }

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                InfoVtaVO inf = new InfoVtaVO();
                inf.setIdInfo(new Integer(rs.getInt(1)));
                inf.setTipoInfo(rs.getString(2));
                inf.setUnidad(rs.getString(3));
                inf.setPrecio(new Float(rs.getFloat(4)));
                inf.setIsActivo(new Integer(rs.getInt(5)));
                tipos.add(inf);
            }
            conn.close();
            return tipos;
        } catch (Exception e) {
            e.printStackTrace();
            conn.close();
            throw e;
        }

    }


    public InfoVtaVO detalleTipoInfo(Integer idtipo) {
        Connection conn = null;
        InfoVtaVO inf = new InfoVtaVO();
        String q = "select idtipoinfo,tipoinfo,unidad,precio,activo from tipoinfovta where idtipoinfo=" +
                   idtipo;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                inf.setIdInfo(new Integer(rs.getInt(1)));
                inf.setTipoInfo(rs.getString(2));
                inf.setUnidad(rs.getString(3));
                inf.setPrecio(new Float(rs.getFloat(4)));
                inf.setIsActivo(new Integer(rs.getInt(5)));

            }
            conn.close();
            return inf;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException ex) {
                return null;
            }
            return null;
        }

    }

//-------- CLIENTE----------------


    public void regCliVta(CliVtaVO cli) throws Exception {
        Connection conn = null;
        String q = "INSERT INTO clientevta (idclientevta,nombre,activo";
        String q2 = " values (s_clientevta.nextval,'" +
                    cli.getNomCli() + "',1";

        if (!("").equals(cli.getDireccion())) {
            q += ",direccion";
            q2 += ",'" + cli.getDireccion() + "'";
        }

        if (cli.getRut() != null && (cli.getCodRutCli() != '\0')) {
            q += ",rut,codigorut";
            q2 += "," + cli.getRut() + ",'" + cli.getCodRutCli() + "'";
        }

        if (!("").equals(cli.getTelefono())) {
            q += ",telefono";
            q2 += ",'" + cli.getTelefono() + "'";
        }

        if (!("").equals(cli.getEmail())) {
            q += ",email";
            q2 += ",'" + cli.getEmail() + "'";
        }

        if (!("").equals(cli.getGiro())) {
            q += ",giro";
            q2 += ",'" + cli.getGiro() + "'";
        }

        if (!("").equals(cli.getComentario())) {
            q += ",comentario";
            q2 += ",'" + cli.getComentario() + "'";
        }

        if (!("").equals(cli.getContactos())) {
            q += ",contactos";
            q2 += ",'" + cli.getContactos() + "'";
        }

        q = q + ") " + q2 + ")";

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            conn.close();
            throw e;
        }

    }


    public List busCliVta(String pal, boolean activo) {
        Connection conn = null;
        List clis = new LinkedList();
        String q = "select idclientevta,nombre,activo from clientevta";

        if (!("").equals(pal) && activo) {
            q += " where (UPPER(nombre) LIKE UPPER ('%" + pal + "%')" +
                    " OR UPPER(direccion) LIKE UPPER ('%" + pal + "%')" +
                    " OR UPPER(giro) LIKE UPPER ('%" + pal + "%')" +
                    " OR UPPER(comentario) LIKE UPPER ('%" + pal + "%')" +
                    " OR UPPER(contactos) LIKE UPPER ('%" + pal + "%'))" +
                    " AND activo=1";
        }

        if (!("").equals(pal) && !activo) {
            q += " where (UPPER(nombre) LIKE UPPER ('%" + pal + "%')" +
                    " OR UPPER(direccion) LIKE UPPER ('%" + pal + "%')" +
                    " OR UPPER(giro) LIKE UPPER ('%" + pal + "%')" +
                    " OR UPPER(contactos) LIKE UPPER ('%" + pal + "%')" +
                    " OR UPPER(comentario) LIKE UPPER ('%" + pal + "%'))";
        }

        if (("").equals(pal) && activo) {
            q += " where activo=1";
        }

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CliVtaVO c = new CliVtaVO();
                c.setIdCliente(new Integer(rs.getInt(1)));
                c.setNomCli(rs.getString(2));
                c.setIsActivo(new Integer(rs.getInt(3)));
                clis.add(c);
            }
            conn.close();
            return clis;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException ex) {
                return null;
            }
            return null;
        }

    }


    public CliVtaVO detalleCliVta(Integer id) {
        Connection conn = null;
        CliVtaVO cli = new CliVtaVO();
        String q = "select idclientevta,nombre," +
                   "direccion,rut,codigorut," +
                   "telefono,giro,comentario," +
                   "activo,email,contactos from clientevta" +
                   " where idclientevta=" + id;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cli.setIdCliente(new Integer(rs.getInt(1)));
                cli.setNomCli(rs.getString(2));
                cli.setDireccion(rs.getString(3));
                cli.setRut(new Integer(rs.getInt(4)));
                if (rs.getString(5) != null) {
                    cli.setCodRutCli(rs.getString(5).charAt(0));
                }
                cli.setTelefono(rs.getString(6));
                cli.setGiro(rs.getString(7));
                cli.setComentario(rs.getString(8));
                cli.setIsActivo(new Integer(rs.getInt(9)));
                cli.setEmail(rs.getString(10));
                cli.setContactos(rs.getString(11));
            }
            conn.close();
            return cli;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException ex) {
                return null;
            }
            return null;
        }

    }


    public List agendaCliVta() {
        Connection conn = null;
        List clis = new LinkedList();
        String q = "select idclientevta,nombre," +
                   "direccion,rut,codigorut," +
                   "telefono,giro,comentario," +
                   "activo,email,contactos from clientevta";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CliVtaVO cli = new CliVtaVO();
                cli.setIdCliente(new Integer(rs.getInt(1)));
                cli.setNomCli(rs.getString(2));
                cli.setDireccion(rs.getString(3));
                cli.setRut(new Integer(rs.getInt(4)));
                if (rs.getString(5) != null) {
                    cli.setCodRutCli(rs.getString(5).charAt(0));
                }
                cli.setTelefono(rs.getString(6));
                cli.setGiro(rs.getString(7));
                cli.setComentario(rs.getString(8));
                cli.setIsActivo(new Integer(rs.getInt(9)));
                cli.setEmail(rs.getString(10));
                cli.setContactos(rs.getString(11));
                clis.add(cli);
            }
            conn.close();
            return clis;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException ex) {
                return null;
            }
            return null;
        }

    }


    public void actuCliVta(CliVtaVO cli) throws Exception {
        Connection conn = null;
        try {
            int activo = 0;
            if (cli.getIsActivo()) {
                activo = 1;
            }

            String q = "UPDATE clientevta SET " +
                       "activo=" + activo;
            if (!("").equals(cli.getDireccion())) {
                q += ",direccion='" + cli.getDireccion() + "'";
            }

            if (cli.getRut() != null && (cli.getCodRutCli() != '\0')) {
                q += ",rut=" + cli.getRut() + ",codigorut='" + cli.getCodRutCli() +
                        "'"; ;
            }

            if (!("").equals(cli.getTelefono())) {
                q += ",telefono=" + cli.getTelefono();
            }

            if (!("").equals(cli.getGiro())) {
                q += ",giro='" + cli.getGiro() + "'";
            }

            if (!("").equals(cli.getEmail())) {
                q += ",email='" + cli.getEmail() + "'";
            }

            if (!("").equals(cli.getComentario())) {
                q += ",comentario='" + cli.getComentario() + "'";
            }

            if (!("").equals(cli.getContactos())) {
                q += ",contactos='" + cli.getContactos() + "'";
            }

            q += " WHERE idclientevta=" + cli.getIdCliente();

            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            conn.close();
            throw e;
        }

    }


//----------------------VENTAS---------------------

    public Integer regVenta(VtaVO v) throws Exception {
        Connection conn = null;

        try {
            String q =
                    "INSERT INTO VENTA (idvta,codigovta,idestadovta,idclientevta,fecha_recepcion";
            String q2 = " values(s_venta.nextval,UPPER('" + v.getCodVenta() +
                        "'),1," +
                        v.getCliente().getIdCliente() +
                        ",TO_DATE('" + sdf.format(v.getFechaRecepcion()) +
                        "','DD-MM-YYYY')";
            if (v.getComentario() != null) {
                q += ",comentario";
                q2 += ",'" + v.getComentario() + "'";
            }

            q += ")" + q2 + ")";
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();

            q = "select s_venta.currval from dual";
            ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            Integer nv = null;
            if (rs.next()) {
                nv = new Integer(rs.getInt(1));
            }
            conn.close();
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
            conn.close();
            throw e;
        }

    }


    public void regDetalleVta(List det, Integer idvta) throws Exception {
        Connection conn = null;
        try {
            if (det != null) {
                Iterator idet = det.iterator();
                while (idet.hasNext()) {
                    InfoVtaVO inf = (InfoVtaVO) idet.next();
                    String q =
                            "INSERT INTO detallevta(iddetalle,idtipoinfo,idvta,cantidad,descripcion)" +
                            "values (s_detallevta.nextval," + inf.getIdInfo() +
                            "," + idvta +
                            "," + inf.getCantidad() + ",'" + inf.getDescripcion() +
                            "')";
                    conn = ConnectionPool.getInstance().getConnection();
                    PreparedStatement ps = conn.prepareCall(q);
                    ps.executeUpdate();
                    conn.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            conn.close();
            throw e;
        }

    }


    public List busVentas(BusVtaVO bus) {
        Connection conn = null;
        List lista = new LinkedList();
        List lista2 = new LinkedList();

        try {


            String q ="SELECT distinct venta.idvta,venta.idestadovta,estadovta,fecha_recepcion,idClientevta,codigoVta " +
                    " FROM VENTA , ESTADOVTA , detallevta WHERE" +
                    " VENTA.IDESTADOVTA=ESTADOVTA.IDESTADOVTA and venta.idvta = detallevta.idvta";
            boolean p = false;
            if (bus.getFechaIni() != null && bus.getFechaIni2() != null) {
                if (!p) {
                    q += " AND fecha_recepcion>=TO_DATE('" +
                            sdf.format(bus.getFechaIni()) +
                            "','DD-MM-YYYY') AND fecha_recepcion<=TO_DATE('" +
                            sdf.format(bus.getFechaIni2()) +
                            "','DD-MM-YYYY')";
                    p = true;
                } else {
                    q += " AND (fecha_recepcion>=TO_DATE('" +
                            sdf.format(bus.getFechaIni()) +
                            "','DD-MM-YYYY') AND fecha_recepcion<=TO_DATE('" +
                            sdf.format(bus.getFechaIni2()) +
                            "','DD-MM-YYYY'))";
                }
            }

            if (bus.getIdCli().intValue() != 0) {
                if (!p) {
                    q += " AND venta.idclientevta=" + bus.getIdCli();
                    p = true;
                } else {
                    q += " AND venta.idclientevta=" + bus.getIdCli();
                }
            }

            if (bus.getIdEstado() != null) {
                if (!p) {
                    q += " AND venta.idestadovta=" + bus.getIdEstado();
                    p = true;
                } else {
                    q += " AND venta.idestadovta=" + bus.getIdEstado();
                }
            }

            if (bus.getPalClave() != null) {
                if(!p) {
                    q += " AND UPPER(DETALLEVTA.DESCRIPCION) LIKE UPPER('%" + bus.getPalClave() + "%')";
                    p = true;
                } else {
                    q += " AND UPPER(DETALLEVTA.DESCRIPCION) LIKE UPPER('%" + bus.getPalClave() + "%')";
                }



            }

            q += " order by fecha_recepcion desc";
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                VtaVO vta = new VtaVO();
                vta.setIdVenta(new Integer(rs.getInt(1)));
                vta.setIdEstado(new Integer(rs.getInt(2)));
                vta.setEstado(rs.getString(3));
                vta.setFechaRecepcion(rs.getDate(4));
                CliVtaVO cli = new CliVtaVO();
                cli.setIdCliente(Integer.valueOf(rs.getString(5)));
                vta.setCodVenta(rs.getString(6));
                vta.setCliente(cli);
                lista.add(vta);
            }
            conn.close();
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    public VtaVO getVenta(Integer idVta) {
        Connection conn = null;
        VtaVO vta = new VtaVO();
        try {
            String q =
                    "SELECT idvta,venta.idestadovta,estadovta,fecha_recepcion," +
                    "fecha_cotizacion,fecha_pago,fecha_entrega,comentario,idclientevta,codigovta,ufpago,fecha_fin,codFactura" +
                    " FROM VENTA ,ESTADOVTA WHERE" +
                    " VENTA.IDESTADOVTA=ESTADOVTA.IDESTADOVTA AND idvta=" +
                    idVta;
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vta.setIdVenta(new Integer(rs.getInt(1)));
                vta.setIdEstado(new Integer(rs.getInt(2)));
                vta.setEstado(rs.getString(3));
                vta.setFechaRecepcion(rs.getDate(4));
                vta.setFechaCotizacion(rs.getDate(5));
                vta.setFechaPago(rs.getDate(6));
                vta.setFechaEntrega(rs.getDate(7));
                vta.setComentario(rs.getString(8));
                CliVtaVO cli = new CliVtaVO();
                cli.setIdCliente(Integer.valueOf(rs.getString(9)));
                vta.setCodVenta(rs.getString(10));
                if (rs.getFloat(11) != 0.0) {
                    vta.setUfPago(rs.getFloat(11));
                }
                vta.setFechaFin(rs.getDate(12));
                vta.setCodFact(rs.getString(13));
                vta.setCliente(cli);
            }
            conn.close();
            return vta;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    public List getDetalleVta(Integer idvta) {
        Connection conn = null;
        List det = new LinkedList();
        String q = "SELECT detallevta.idtipoinfo,idvta,cantidad,tipoinfo," +
                   "unidad,precio,descripcion,preciopago,iddetalle" +
                   " FROM detallevta, tipoinfovta " +
                   "WHERE detallevta.idtipoinfo=tipoinfovta.idtipoinfo" +
                   " AND idvta=" + idvta;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                InfoVtaVO i = new InfoVtaVO();
                i.setIdInfo(new Integer(rs.getInt(1)));
                i.setIdVta(new Integer(rs.getInt(2)));
                i.setCantidad(new Integer(rs.getInt(3)));
                i.setTipoInfo(rs.getString(4));
                i.setUnidad(rs.getString(5));
                i.setPrecio(new Float(rs.getFloat(6)));
                i.setDescripcion(rs.getString(7));
                i.setPrecioPago(new Float(rs.getFloat(8)));
                i.setIdDetalle(new Integer(rs.getInt(9)));
                det.add(i);
            }
            conn.close();
            return det;
        }

        catch (Exception e) {
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException ex) {
            }
            return null;
        }

    }

    public void regFechaCot(Integer idvta, String fechacot) throws Exception {

        Connection conn = null;

        try {
            String q = "UPDATE VENTA SET " +
                       "fecha_cotizacion=TO_DATE('" + fechacot +
                       "','DD-MM-YYYY')" +
                       " WHERE idvta=" + idvta;
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            conn.close();
            throw e;
        }

    }


    public void regFechaPago_UF(Integer idvta, String fecha, Float uf) throws
            Exception {

        Connection conn = null;

        try {
            String q = "UPDATE VENTA SET " +
                       "fecha_pago=TO_DATE('" + fecha +
                       "','DD-MM-YYYY'),UFPAGO=?" +
                       " WHERE idvta=" + idvta;
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setFloat(1, uf.floatValue());
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            conn.close();
            throw e;
        }

    }


    public void regFechaEntrega(Integer idvta, String fecha) throws Exception {

        Connection conn = null;

        try {
            String q = "UPDATE VENTA SET " +
                       "fecha_entrega=TO_DATE('" + fecha +
                       "','DD-MM-YYYY')" +
                       " WHERE idvta=" + idvta;
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            conn.close();
            throw e;
        }

    }


    public void cambiaEstado(Integer idvta, Integer idEstado) throws Exception {
        Connection conn = null;

        try {
            String q = "UPDATE VENTA SET " +
                       "idestadovta=" + idEstado +
                       " WHERE idvta=" + idvta;
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            conn.close();
            throw e;
        }

    }


    //-------Venta--------

    /**
     * actualizarVenta
     *
     * @param venta VtaVO
     */
    public void actualizarVenta(VtaVO venta) throws Exception {
        Connection conn = null;
        try {
            String q = "UPDATE VENTA SET" +
                       " idclientevta=" + venta.getCliente().getIdCliente() +
                       " ,fecha_recepcion=TO_DATE('" +
                       sdf.format(venta.getFechaRecepcion()) +
                       "','DD-MM-YYYY')" +
                       ",comentario='" + venta.getComentario() + "'";
            q += " WHERE idvta=" + venta.getIdVenta();

            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    /**
     * eliminarDetalleVenta
     *
     * @param integer Integer
     */
    public void eliminarDetalleVenta(Integer idVenta) throws Exception {

        Connection conn = null;
        try {
            String q = "DELETE detallevta WHERE idvta=" + idVenta;
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


//-----------ESTADO VENTA------
    public List getEstadosVtas() {
        Connection conn = null;
        List es = new LinkedList();
        try {
        String q =
                    "SELECT idestadovta,estadovta FROM estadovta ORDER BY idestadovta";

            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                IdStrVO estado = new IdStrVO();
                estado.setId(new Integer(rs.getInt(1)));
                estado.setStr(rs.getString(2));
                es.add(estado);
            }
            conn.close();
            return es;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException ex) {
            }
            return null;
        }

    }


//-----------UF------
    public float getUF() {

        Connection conn = null;

        float uf = (float) 0;
        try {
        String q =
                "SELECT uf FROM uf";

            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                uf = (float) rs.getFloat(1);
            }

            conn.close();
            return uf;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException ex) {
            }
            return uf;
        }
    }

    public void actualizaUF(Float uf) throws Exception {
        Connection conn = null;
        String q =
                "update uf set uf=?";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setFloat(1, uf.floatValue());
            ps.executeUpdate();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * setPrecioPago
     *
     * @param integer Integer
     * @param floatIdent Float
     */
    public void setPrecioPago(Integer idInfo, Float precio) throws Exception {

        Connection conn = null;
        try {
        String q =
                "update detallevta set preciopago=? where iddetalle=?";

            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setFloat(1, precio.floatValue());
            ps.setInt(2, idInfo.intValue());
            ps.executeUpdate();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    /**
     * regFechaFin
     *
     * Registra la fecha de finalización del proceso de venta, e ingresa el
     * codigo de la factura con la que se pagó la venta.
     *
     * @param fecha String
     * @param codFact String
     * @param idVta Integer
     */
    public void regFechaFin(String fecha, String codFact, Integer idVta) throws
            Exception {

        Connection conn = null;
         try {
        String q =
            "update venta set fecha_fin=TO_DATE('"+fecha+"','DD-MM-YYYY')"+
            ", codfactura='"+codFact+"' where idvta="+idVta.toString();

            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    private void jbInit() throws Exception {
    }


}

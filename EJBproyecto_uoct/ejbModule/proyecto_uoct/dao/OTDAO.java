package proyecto_uoct.dao;

import java.sql.*;
import java.text.*;
import java.util.*;

import proyecto_uoct.common.*;
import proyecto_uoct.common.VO.*;
import proyecto_uoct.documentacion.VO.*;
import proyecto_uoct.proyecto.VO.*;
import proyecto_uoct.usuario.VO.*;

public class OTDAO {
    public OTDAO() {
    }

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private static OTDAO dao = null;

    public static OTDAO getInstance() {
        if (dao == null) {
            dao = new OTDAO();
        }
        return dao;
    }


    public DetalleOTVO getDetalleOT(Integer id_ot) {
        DetalleOTVO detot = new DetalleOTVO();
        Connection conn = null;
        String q = "select ordendetrabajo.id_ot,ordendetrabajo.nombre_ot," +
                   " ordendetrabajo.fecha_ot,ordendetrabajo.fecha_vencimiento," +
                   " ordendetrabajo.fecha_suscripcion,ordendetrabajo.tareas_concluidas," +
                   " ordendetrabajo.aprobacion,plazo_ejecucion,cobro_ep," +
                   " ordendetrabajo.idiniciativa," +
                   " iniciativa.NOMBRE_INI," +
                   " estadoot.idestado,estadoot.ESTADO,tipo_ot.TIPOOT, " +
                   " cliente.id_cliente," +
                   " cliente.NOMBRE_CLIENTE,cliente.apellido_cliente," +
                   " datosdocumento.id_documento,datosdocumento.TIPO_DOCUMENTO,datosdocumento.codigo" +
                   " from ordendetrabajo ,iniciativa,estadoot,cliente,datosdocumento,tipo_ot" +
                   " WHERE ordendetrabajo.idiniciativa=iniciativa.idiniciativa" +
                   " AND ordendetrabajo.IDESTADO=estadoot.idestado" +
                   " AND ordendetrabajo.ID_CLIENTE=cliente.id_cliente" +
                   " AND datosdocumento.ID_DOCUMENTO=ordendetrabajo.ID_DOCUMENTO" +
                   " AND tipo_ot.ID_TIPOOT=ordendetrabajo.ID_TIPOOT" +
                   " AND ordendetrabajo.id_ot =?";

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_ot.intValue());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                detot.setIdOT(new Integer(rs.getInt(1)));
                detot.setNomOT(rs.getString(2));
                detot.setFechaOT(rs.getDate(3));
                detot.setVencimiento(rs.getDate(4));
                detot.setSuscrip(rs.getDate(5));
                detot.setFinTareas(rs.getDate(6));
                detot.setAprobacion(rs.getDate(7));
                detot.setPlazo(new Integer(rs.getInt(8)));
                detot.setEP(rs.getString(9));
                DetalleProyectoVO proy = new DetalleProyectoVO();
                proy.setIdProy(new Integer(rs.getInt(10)));
                proy.setNomProy(rs.getString(11));
                detot.setDetProy(proy);
                detot.setIdProy(new Integer(rs.getInt(10)));

                detot.setIdEstado(new Integer(rs.getInt(12)));
                detot.setEstadoOT(rs.getString(13));

                detot.setTipoOT(rs.getString(14));

                ClienteVO cli = new ClienteVO();
                cli.setIdCli(new Integer(rs.getInt(15)));
                cli.setNomCli(rs.getString(16));
                cli.setApeCli(rs.getString(17));

                detot.setCli(cli);
                DocumentoInVO doc = new DocumentoInVO();
                doc.setIdDoc(new Integer(rs.getInt(18)));
                doc.setTipoDoc(rs.getString(19));
                doc.setCodDoc(rs.getString(20));
                detot.setDocumento(doc);

                detot.setEncargados(this.getEncargadosOTxOT(id_ot));
                detot.setNLOs(this.getNLOsxOT(id_ot));
                detot.setEstadoOT(this.getEstadoOT(detot.getIdOT()));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return detot;
    }

    /**
     * getNLOsxOT
     *
     * @param id_ot Integer
     * @return List
     */
    public List getNLOsxOT(Integer id_ot) throws Exception {
        Connection conn = null;
        List nlos = new LinkedList();
        try {
            String q = "SELECT nlo.id_nlo, descripcion_nlo," +
                       "datosDocumento.ID_DOCUMENTO," +
                       "datosdocumento.TIPO_DOCUMENTO," +
                       "datosdocumento.CODIGO,nlo.fecha_nlo" +
                       " from nlo,ot_nlo,datosdocumento " +
                       " WHERE nlo.id_nlo= ot_nlo.id_nlo" +
                       " AND nlo.ID_DOCUMENTO=datosdocumento.id_documento " +
                       " AND id_ot=? order by nlo.id_nlo asc";

            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_ot.intValue());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NLOVO n = new NLOVO();
                n.setId(new Integer(rs.getInt(1)));
                n.setStr(rs.getString(2));
                DocumentoInVO doc = new DocumentoInVO(); ;
                doc.setIdDoc(new Integer(rs.getInt(3)));
                doc.setTipoDoc(rs.getString(4));
                doc.setCodDoc(rs.getString(5));
                n.setDocumento(doc);
                n.setFechaNLO(rs.getDate(6));
                nlos.add(n);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return nlos;
    }

    /**
     * getEncargadosOTxOT
     *
     * @param id_ot Integer
     * @return List
     */
    public List getEncargadosOTxOT(Integer id_ot) {
        Connection conn = null;
        List encargados = new LinkedList();

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "select usuario.id_usu,usuario.nombre_usu," +
                    "usuario.nombre2_usu,usuario.apellido_usu,usuario.email " +
                    "from usuario ,responsable_ot " +
                    "WHERE usuario.id_usu=responsable_ot.id_usu " +
                    "AND responsable_ot.id_ot=?");
            ps.setInt(1, id_ot.intValue());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UsuarioVO usu = new UsuarioVO();
                usu.setIdUsu(new Integer(rs.getInt(1)));
                usu.setNombreUsu(rs.getString(2));
                usu.setNombreUsu2(rs.getString(3));
                usu.setApellUsu(rs.getString(4));
                usu.setEmail(rs.getString(5));
                encargados.add(usu);
            }

            conn.close();
        } catch (Exception e) {
            System.out.println("no se consultaron los encargados de la ot");
            e.printStackTrace();
        }

        return encargados;

    }

    public Integer regNLO(NLOVO nlo) throws Exception {
        Integer id_nlo = null;
        String q =
                "INSERT INTO NLO (id_nlo,idiniciativa,descripcion_nlo,id_documento,fecha_nlo) values" +
                "(s_nlo.nextval," + nlo.getIdProy() + ",'" + nlo.getStr() +
                "'," + nlo.getDocumento().getIdDoc() + ",TO_DATE('" +
                sdf.format(nlo.getFechaNLO()) + "','DD-MM-YYYY'))";
        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);

            if (ps.executeUpdate() != 0) {
                ps = conn.prepareCall("select s_nlo.currval from dual");
                ResultSet rs = ps.executeQuery();

                rs.next();

                id_nlo = new Integer(rs.getInt(1));
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return id_nlo;
    }


    public boolean insertOT_NLO(Integer id_ot, Integer id_nlo) throws Exception {

        String q = "INSERT INTO ot_nlo (id_ot,id_nlo) VALUES (?,?)";

        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_ot.intValue());
            ps.setInt(2, id_nlo.intValue());

            if (ps.executeUpdate() != 0) {
                conn.close();
                return true;
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return false;

    }


    public boolean insertNLO_doc(String id_documento, Integer id_tipo_doc,
                                 Integer id_nlo) {

        String q = "INSERT INTO NLO_DOC (id_documento,id_tipo_doc, id_nlo)" +
                   " VALUES (?,?,?)";

        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setString(1, id_documento);
            ps.setInt(2, id_tipo_doc.intValue());
            ps.setInt(3, id_nlo.intValue());

            if (ps.executeUpdate() != 0) {
                conn.close();
                return true;
            }

            conn.close();
        } catch (Exception e) {
            System.out.println("no se consultaron los tipos de cliente");
            e.printStackTrace();
        }

        return false;
    }

    public String getEstadoOT(Integer id_ot) {
        String estado = "Iniciada";
        String q = "select fecha_ot,fecha_suscripcion,tareas_concluidas," +
                   "aprobacion,fecha_vencimiento FROM" +
                   " ordendetrabajo WHERE id_ot=?";
        Connection conn = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_ot.intValue());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getDate(1) != null) {
                    estado = "Iniciada";
                }
                if (rs.getDate(2) != null) {
                    estado = "Suscrita";
                }
                if (rs.getDate(3) != null) {
                    estado = "Tareas Concluidas";
                }
                if (rs.getDate(4) != null) {
                    estado = "Aprobada";
                }
            }
            conn.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return estado;
    }

    /**
     * actualizarFechasOT
     *
     * @param f_ot DetalleOTVO
     * @return boolean
     */
    public boolean actualizarFechasOT(DetalleOTVO f_ot) {

        Connection conn = null;
        boolean isact = false;
        try {

            String q = "UPDATE ordendetrabajo SET  ";
            if (f_ot.getFechaOT() != null) {
                q += "fecha_ot = TO_DATE('" + sdf.format(f_ot.getFechaOT()) +
                        "','DD-MM-YYYY') ";
            }

            if (f_ot.getVencimiento() != null) {
                q += " , fecha_vencimiento = TO_DATE('" +
                        sdf.format(f_ot.getVencimiento()) + "','DD-MM-YYYY') ";
            }

            if (f_ot.getSuscrip() != null) {
                q += ", fecha_suscripcion =TO_DATE('" +
                        sdf.format(f_ot.getSuscrip()) +
                        "' ,'DD-MM-YYYY') ";
            }

            if (f_ot.getFinTareas() != null) {
                q += ", tareas_concluidas =TO_DATE('" +
                        sdf.format(f_ot.getFinTareas()) + "' ,'DD-MM-YYYY')";
            }

            if (f_ot.getAprobacion() != null) {
                q += ", aprobacion =TO_DATE('" + sdf.format(f_ot.getAprobacion()) +
                        "' ,'DD-MM-YYYY')";
            }

            q += " where id_ot=?";

            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, f_ot.getIdOT().intValue());
            ps.executeUpdate();

            isact = true;
            conn.close();
        } catch (Exception e) {

            e.printStackTrace();
        }

        return isact;
    }


    /**
     * buscarOT
     *
     * @param bus BusOTVO
     * @return List
     */
    public List buscarOT(BusOTVO bus) throws Exception {

        if(bus.getPalClave()== null){
            bus.setPalClave("");
        }

        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();

            String q =
                    "select ordendetrabajo.ID_OT,ordendetrabajo.NOMBRE_OT,estadoot.ESTADO," +
                    "ordendetrabajo.FECHA_VENCIMIENTO,iniciativa.IDINICIATIVA, iniciativa.NOMBRE_INI" +
                    " from ordendetrabajo,estadoot,iniciativa WHERE ordendetrabajo.IDESTADO= estadoot.IDESTADO" +
                    "  AND ordendetrabajo.IDINICIATIVA= iniciativa.IDINICIATIVA";

            boolean pri = true;
            if (bus.getIdOT() != null) {
                q += " and ordendetrabajo.id_ot=" + bus.getIdOT();

            }

            if (bus.getIdEstado() != null) {
                q += " and ordendetrabajo.idestado=" + bus.getIdEstado();
            }

            if (!bus.getFechaVenc().equals("")) {
                q += " and ordendetrabajo.FECHA_VENCIMIENTO=TO_DATE('" +
                        bus.getFechaVenc() + "','DD-MM-YYYY')";
            }
            if (!bus.getPalClave().equals("")) {

                q += " AND (UPPER(nombre_ot) LIKE UPPER('%" +
                        bus.getPalClave() + "%')" +
                        " or UPPER(cobro_ep) LIKE UPPER('%" +
                        bus.getPalClave() +
                        "%')or UPPER(iniciativa.NOMBRE_INI) LIKE UPPER('%" +
                        bus.getPalClave() + "%'))";

            }
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            List ots = new LinkedList();
            while (rs.next()) {
                DetalleOTVO ot = new DetalleOTVO();
                ot.setIdOT(new Integer(rs.getInt(1)));
                ot.setNomOT(rs.getString(2));
                ot.setEstadoOT(rs.getString(3));
                ot.setVencimiento(rs.getDate(4));
                DetalleProyectoVO proy = new DetalleProyectoVO();
                proy.setIdProy(new Integer(rs.getInt(5)));
                proy.setNomProy(rs.getString(6));
                ot.setDetProy(proy);
                ots.add(ot);

            }

            conn.close();
            return ots;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * getEstadosOT
     *
     * @return List
     */
    public List getEstadosOT() throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();

            String q =
                    "select IDESTADO, estado from estadoot order by idestado asc";

            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            List estados = new LinkedList();
            while (rs.next()) {
                IdStrVO id = new IdStrVO();
                id.setId(new Integer(rs.getInt(1)));
                id.setStr(rs.getString(2));
                estados.add(id);
            }

            conn.close();
            return estados;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * actualizarEstadoOT
     *
     * @param integer Integer
     * @param idEstado int
     */
    public void actualizarEstadoOT(Integer idOT, int idEstado) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            String q = "UPDATE ordendetrabajo SET idestado=" + idEstado +
                       " WHERE id_ot=" + idOT;

            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * actualizarOT
     *
     * @param detot DetalleOTVO
     */
    public void actualizarOT(DetalleOTVO detot) throws Exception {

        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            String q = "UPDATE ordendetrabajo SET " +
                       " id_cliente=" + detot.getCli().getIdCli() +
                       ", fecha_ot=TO_DATE('" + sdf.format(detot.getFechaOT()) +
                       "','DD-MM-YYYY')" +
                       ", plazo_ejecucion=" + detot.getPlazo() +
                       ", fecha_vencimiento=TO_DATE('" +
                       sdf.format(detot.getVencimiento()) + "','DD-MM-YYYY')";

            if (detot.getEP() != null) {
                q += ",cobro_ep='" + detot.getEP() + "'";
            }

            q += ", idestado=" + detot.getIdEstado() + " WHERE id_ot=" +
                    detot.getIdOT();

            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    /**
     * borrarEncargadosOT
     *
     * @param integer Integer
     */
    public void borrarEncargadosOT(Integer idot) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            String q = "DELETE responsable_ot WHERE id_ot=" + idot;
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * borrarOT
     * Elimina un registro en la tabla ordendetrabajo con la llave primaria
     * entregada como referencia
     *
     * @param idot Integer
     * @throws Exception
     */
    public void borrarOT(Integer idot) throws Exception {

        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            String q = "DELETE ordendetrabajo WHERE id_ot=" + idot;
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }


    /**
     * borrarNLO
     *
     * Borra un registro de la tabla de NLO con el id entregado como parametro
     *
     * @param idnlo Integer
     * @throws Exception
     */
    public void borrarNLO(Integer idnlo) throws Exception {

        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            String q = "DELETE nlo WHERE id_nlo=" + idnlo;
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }


    /**
     * borrarOT_NLO
     * Borra los registros de relación OT-NLO
     *
     * @param idot Integer
     * @throws Exception
     */
    public void borrarOT_NLO(Integer idot) throws Exception {

        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            String q = "DELETE ot_nlo WHERE id_ot=" + idot;
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    /**
     * actualizarNLO
     *
     * @param nlo NLOVO
     */
    public void actualizarNLO(NLOVO nlo) throws Exception {

        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            String q = "UPDATE nlo SET DESCRIPCION_NLO='" + nlo.getStr() + "'" +
                       ", fecha_nlo=TO_DATE('" + sdf.format(nlo.getFechaNLO()) +
                       "','DD-MM-YYYY')" +
                       " WHERE id_nlo=" + nlo.getId();
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }


}

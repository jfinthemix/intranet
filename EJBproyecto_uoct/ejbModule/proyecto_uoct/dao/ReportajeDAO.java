package proyecto_uoct.dao;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import oracle.sql.*;
import oracle.sql.BLOB;
import proyecto_uoct.common.*;
import proyecto_uoct.common.util.*;
import proyecto_uoct.infoyrep.VO.*;

public class ReportajeDAO {
    public ReportajeDAO() {
    }


    private static ReportajeDAO dao = null;

    public static ReportajeDAO getInstance() {
        if (dao == null) {
            dao = new ReportajeDAO();
        }
        return dao;
    }


    public int agregarReportaje(ReportajeVO nuevoRepor) throws Exception {

        Connection conn = null;
        java.util.Date hoy = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        int id_nuevo = 0;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = null;
            ps = conn.prepareCall("INSERT INTO reportaje (id_reportaje,titulo_reportaje,descripcion_reportaje,fecha,tipo)" +
                                  "VALUES (s_reportaje.nextval,?,?,TO_DATE('" +
                                  sdf.format(hoy) + "','DD-MM-YYYY HH24:MI')," +
                                  nuevoRepor.getTipo() + ")");

            ps.setString(1, nuevoRepor.getTitRep());
            ps.setString(2, nuevoRepor.getDescRep());
            ps.executeUpdate();
            ps = conn.prepareCall("select s_reportaje.currval from dual ");
            ResultSet rs = ps.executeQuery();
            rs.next();
            id_nuevo = rs.getInt(1);
            conn.close();
        } catch (Exception ex) {
            System.out.print(ex.toString());

            throw ex;
        }
        return id_nuevo;
    }


    /*
         INGRESA UNA FOTO DE UN REPORTAJE A LA BD
     */


    public void agregarFoto(byte[] foto, Integer idRep) throws Exception {

        Connection conn = null;
        Archivo a = Archivo.getInstance();
        try {
            File fileFoto = a.byteToFile(foto);
            conn = ConnectionPool.getInstance().getConnection();
            String q = "UPDATE reportaje SET foto_reportaje=empty_blob()" +
                       " WHERE id_reportaje=?";
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idRep.intValue());
            ps.executeUpdate();

            q = "SELECT foto_reportaje FROM reportaje WHERE id_reportaje=?";
            ps = conn.prepareCall(q);
            ps.setInt(1, idRep.intValue());
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
            conn.close();
        } catch (Exception e) {

            e.printStackTrace();
            throw e;

        }

    }

    /*
     Retorna el detalle de un reportaje por medio del identificador
     */
    public ReportajeVO getReportaje(int id) {

        Connection conn = null;
        ReportajeVO reportaje = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = null;

            ps = conn.prepareCall("SELECT reportaje.id_reportaje," +
                                  " reportaje.titulo_reportaje, reportaje.descripcion_reportaje," +
                                  " fecha,tipo FROM reportaje" +
                                  " where id_reportaje=" + id +
                                  " order by fecha asc");

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                reportaje = new ReportajeVO();
                reportaje.setIdRep(new Integer(rs.getInt(1)));
                reportaje.setTitRep(rs.getString(2));
                reportaje.setDescRep(rs.getString(3));
                // agregar la fecha aqui!!!
                reportaje.setTipo(rs.getInt(5));
                //reportaje.setFotoRep(rs.getBlob(4));
            }

            conn.close();

        } catch (SQLException ex3) {
            System.out.print("no se pudo consultar el reportaje");
        }
        return reportaje;

    }

    /**
     * getFoto
     *
     * @param idRep Integer
     * @return byte[]
     */
    public byte[] getFoto(int idRep) {
        Connection conn;
        String q =
                "SELECT foto_reportaje FROM reportaje WHERE id_reportaje=?  ";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idRep);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int len = (int)rs.getBlob(1).length();
            byte[] b = rs.getBlob(1).getBytes(1, len);
            //BLOB blob = (BLOB) rs.getBlob(1);
            //int len = (int) blob.length();
            ///byte[] b = blob.getBytes(1, len);
            
            conn.close();
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * getTailReportajes
     *selecciona los últimos 10 reportajes ingresados a la Base de datos sin contar los ultimos 2
     * @return List
     */
    public List getTailReportajes() {
        List reps = new LinkedList();
        Connection conn;
        String q = "SELECT * FROM (SELECT ID_REPORTAJE," +
                   "TITULO_REPORTAJE FROM REPORTAJE " +
                   "ORDER BY ID_REPORTAJE DESC) WHERE ROWNUM < 11";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ReportajeVO rep = new ReportajeVO();
                rep.setIdRep(new Integer(rs.getInt(1)));
                rep.setTitRep(rs.getString(2));
                reps.add(rep);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reps;
    }

    /**
     * Obtiene el detalle del ultimo reportaje del tipo indicado como parámetro
     *
     * @param tipo int
     * @return ReportajeVO
     */
    public ReportajeVO getReportajePorTipo(int tipo) {

        Connection conn = null;
        ReportajeVO reportaje = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = null;

            ps = conn.prepareCall(
                    "select * FROM (SELECT reportaje.id_reportaje," +
                    " reportaje.titulo_reportaje, reportaje.descripcion_reportaje, " +
                    " fecha from reportaje where tipo=" + tipo +
                    " order by id_reportaje DESC) where rownum =1");

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                reportaje = new ReportajeVO();
                reportaje.setIdRep(new Integer(rs.getInt(1)));
                reportaje.setTitRep(rs.getString(2));
                reportaje.setDescRep(rs.getString(3));
                //reportaje.setFotoRep(rs.getBlob(4));
            }

            conn.close();

        } catch (SQLException ex3) {
            System.out.print("no se pudo consultar el reportaje");
        }
        return reportaje;

    }

    /**
     * actualizaReportaje
     *
     * @param reportaje ReportajeVO
     */
    public void actualizaReportaje(ReportajeVO reportaje) throws Exception {
        Connection conn = null;
        try {
            String q = "UPDATE reportaje SET titulo_reportaje='" +
                       reportaje.getTitRep() + "', descripcion_reportaje='" +
                       reportaje.getDescRep() + "' WHERE id_reportaje=" +
                       reportaje.getIdRep();

            conn= ConnectionPool.getInstance().getConnection();
            PreparedStatement ps= conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.print(e.toString());
            throw e;
        }
    }
}



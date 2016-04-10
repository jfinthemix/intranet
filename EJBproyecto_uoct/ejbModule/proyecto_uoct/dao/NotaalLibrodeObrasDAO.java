package proyecto_uoct.dao;

import proyecto_uoct.proyecto.VO.*;
import proyecto_uoct.documentacion.VO.DocumentoInVO;
import proyecto_uoct.proyecto.VO.DetalleOTVO;
import proyecto_uoct.common.ConnectionPool;
import proyecto_uoct.dao.OTDAO;
import java.sql.*;
import java.text.*;
import java.util.*;


public class NotaalLibrodeObrasDAO {
    public NotaalLibrodeObrasDAO() {
    }


    private static NotaalLibrodeObrasDAO dao = null;

    public static NotaalLibrodeObrasDAO getInstance() {
        if (dao == null) {
            dao = new NotaalLibrodeObrasDAO();
        }
        return dao;
    }

    public NLOVO getDetalleNLO(Integer idNLO) throws Exception {

        NLOVO nlo = new NLOVO();

        Connection conn = null;
        String q = "select nlo.id_nlo, nlo.idiniciativa, nlo.id_documento," +
                   " nlo.descripcion_nlo, nlo.fecha_nlo,ot_nlo.id_ot from NLO,"
                   +
                   "ot_nlo where nlo.id_nlo= ot_nlo.id_nlo(+) and nlo.id_nlo=?";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idNLO.intValue());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nlo.setId(new Integer(rs.getInt(1)));
                nlo.setIdProy(new Integer(rs.getInt(2)));
                DocumentoInVO doc = new DocumentoInVO();
                doc.setIdDoc(new Integer(rs.getInt(3)));
                nlo.setDocumento(doc);
                nlo.setDetalleNLO(rs.getString(4));
                nlo.setFechaNLO(rs.getDate(5));
                DetalleOTVO detot = new DetalleOTVO();
                detot.setIdOT(new Integer(rs.getInt(6)));
                nlo.setOT(detot);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return nlo;
    }


    public void borrarOT_NLO(Integer idnlo) throws Exception {

        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            String q = "DELETE ot_nlo WHERE id_nlo=" + idnlo;
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }



}

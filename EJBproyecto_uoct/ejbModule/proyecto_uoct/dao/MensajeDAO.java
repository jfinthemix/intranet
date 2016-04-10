package proyecto_uoct.dao;

import java.sql.Connection;
import proyecto_uoct.common.ConnectionPool;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Clase para la interacción con la tabla mensaje en la BD
 *
 */
public class MensajeDAO {

    //---- singleton
    private MensajeDAO() {
    }

    private static MensajeDAO dao = null;

    public static MensajeDAO getInstance() {
        if (dao == null) {
            dao = new MensajeDAO();
        }
        return dao;
    }

    //---------

    public String getMensaje(int id) throws Exception {
        Connection conn = null;
        String q = "SELECT mensaje FROM mensaje WHERE idmensaje=?";
        String mens = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                mens = rs.getString(1);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return mens;
    }


    public void setMensaje(String mensaje, int id) throws Exception {
        Connection conn = null;
        String q = "UPDATE mensaje SET mensaje='"+mensaje+"' WHERE idmensaje=?";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.print(e.toString());
            throw e;
        }
    }


}

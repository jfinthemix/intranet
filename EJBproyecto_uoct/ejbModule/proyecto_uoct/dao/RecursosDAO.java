package proyecto_uoct.dao;

import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

import proyecto_uoct.common.*;
import proyecto_uoct.reservas.VO.*;
import proyecto_uoct.reservas.util.Parse;

public class RecursosDAO {

    private static RecursosDAO dao = null;

    public static RecursosDAO getInstance() {
        if (dao == null) {
            dao = new RecursosDAO();
        }
        return dao;
    }

    private SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
    private SimpleDateFormat hora = new SimpleDateFormat("HH:mm");
    public List getRecursos() throws Exception {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List recursos = new LinkedList();

        try {

            String sql = "SELECT " +
                         "NOMBRE_RECURSO, " +
                         "ID_RECURSO, " +
                         "ID_TIPO_REC, " +
                         "DESCRIPCION_RECURSO " +
                         "FROM RECURSO WHERE ACTIVO=1";

            conn = ConnectionPool.getInstance().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                RecursoVO recurso = new RecursoVO();
                recurso.setNombre(rs.getString(1));
                recurso.setIdRecurso(new Long(rs.getLong(2)));
                recurso.setIdTipoRecurso(new Long(rs.getLong(3)));
                recurso.setDescripcion(rs.getString(4));
                recursos.add(recurso);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error en el módulo de recursos: " +
                                e.getMessage());
        } finally {
            conn.close();
        }

        return recursos;
    }


    public RecursoVO getRecurso(Long idRecurso) throws Exception {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        RecursoVO recurso = null;

        try {

            String sql = "SELECT " +
                         "NOMBRE_RECURSO, " +
                         "ID_RECURSO, " +
                         "ID_TIPO_REC, " +
                         "DESCRIPCION_RECURSO,ACTIVO " +
                         "FROM RECURSO " +
                         "WHERE ID_RECURSO = ?";

            conn = ConnectionPool.getInstance().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, idRecurso.longValue());
            rs = ps.executeQuery();

            if (rs.next()) {
                recurso = new RecursoVO();
                recurso.setNombre(rs.getString(1));
                recurso.setIdRecurso(new Long(rs.getLong(2)));
                recurso.setIdTipoRecurso(new Long(rs.getLong(3)));
                recurso.setDescripcion(rs.getString(4));
                recurso.setIsActivo(new Integer(5));

                FiltroRecursos filtro = new FiltroRecursos();
                filtro.setIdRecurso(recurso.getIdRecurso());
                filtro.setFechaDesde(new Date());

                recurso.setReservas(getReservas(filtro));
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error en el módulo de recursos: " +
                                e.getMessage());
        } finally {
            conn.close();
        }

        return recurso;
    }


    public List getReservas(FiltroRecursos filtro) throws Exception {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List reservas = new LinkedList();

        try {

            String sql = "SELECT " +
                         "RESERVA.FECHA_RESERVA, " +
                         "RESERVA.HORA_INICIO, " +
                         "RESERVA.HORA_FIN, " +
                         "RESERVA.ID_RESERVA, " +
                         "RESERVA.ID_USU, " +
                         "USUARIO.NOMBRE_USU, " +
                         "USUARIO.NOMBRE2_USU, " +
                         "USUARIO.APELLIDO_USU, " +
                         "USUARIO.APELLIDO2_USU, RESERVA.MOTIVO " +
                         "FROM RESERVA , USUARIO WHERE RESERVA.ID_USU = USUARIO.ID_USU " +
                         "AND ID_RECURSO = ? ";

            if (filtro.getFecha() != null) {
                sql += "AND RESERVA.FECHA_RESERVA = TO_DATE('" +
                        fecha.format(filtro.getFecha()) + "', 'DD-MM-YYYY') ";
            }

            if (filtro.getFechaDesde() != null) {
                sql += "AND RESERVA.FECHA_RESERVA >= TO_DATE('" +
                        fecha.format(filtro.getFechaDesde()) +
                        "', 'DD-MM-YYYY') ";
            }

            if (filtro.getFechaHasta() != null) {
                sql += "AND RESERVA.FECHA_RESERVA <= TO_DATE('" +
                        fecha.format(filtro.getFechaHasta()) +
                        "', 'DD-MM-YYYY') ";
            }

            if (filtro.getValidacionHoraInicio() != null) {
                sql += "AND ((RESERVA.HORA_INICIO <= " +
                        filtro.getHoraInicioSinPuntos() + " ";

                sql += "AND RESERVA.HORA_FIN > " +
                        filtro.getHoraInicioSinPuntos() + ") ";

                sql += "OR (RESERVA.HORA_INICIO <" +
                        filtro.getHoraFinSinPuntos() + " ";

                sql += "AND RESERVA.HORA_FIN >= " +
                        filtro.getHoraFinSinPuntos() + ")";

                sql += "OR (RESERVA.HORA_INICIO >= " +
                        filtro.getHoraInicioSinPuntos() + " ";

                sql += "AND RESERVA.HORA_FIN <= " +
                        filtro.getHoraFinSinPuntos() + "))";

            }

            sql += "ORDER BY RESERVA.FECHA_RESERVA, RESERVA.HORA_INICIO";

            conn = ConnectionPool.getInstance().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, filtro.getIdRecurso().longValue());
            rs = ps.executeQuery();

            while (rs.next()) {
                ReservaVO reserva = new ReservaVO();
                reserva.setFechaReserva(rs.getDate(1));
                String _hora = String.valueOf(rs.getInt(2));
                if (_hora.equals("0")) {
                    _hora = "0000";
                }

                _hora = _hora.substring(0, _hora.length() - 2) + ":" +
                        _hora.substring(_hora.length() - 2);

                reserva.setHoraDeInicio(Parse.newTime(_hora));

                _hora = String.valueOf(rs.getInt(3));

                if (_hora.equals("0")) {
                    _hora = "0000";
                }

                _hora = _hora.substring(0, _hora.length() - 2) + ":" +
                        _hora.substring(_hora.length() - 2);

                reserva.setHoraDeFin(Parse.newTime(_hora));
                reserva.setIdReserva(new Long(rs.getLong(4)));
                reserva.setIdUsuario(new Long(rs.getLong(5)));
                if (rs.getString(7) != null &&
                    rs.getString(9) != null) {
                    reserva.setNombreUsuario(rs.getString(6) + " " +
                                             rs.getString(7) + " " +
                                             rs.getString(8) + " " +
                                             rs.getString(9));
                }
                if (rs.getString(7) == null &&
                    rs.getString(9) != null) {
                    reserva.setNombreUsuario(rs.getString(6) + " " +
                                             rs.getString(8) + " " +
                                             rs.getString(9));
                }
                if (rs.getString(7) != null &&
                    rs.getString(9) == null) {
                    reserva.setNombreUsuario(rs.getString(6) + " " +
                                             rs.getString(7) + " " +
                                             rs.getString(8));
                }
                if (rs.getString(7) == null &&
                    rs.getString(9) == null) {
                    reserva.setNombreUsuario(rs.getString(6) + " " +
                                             rs.getString(8));
                }
                reserva.setMotivo(rs.getString(10));
                if(reserva.getMotivo()==null){
                    reserva.setMotivo("-");
                }
                reserva.setIdRecurso(filtro.getIdRecurso());
                reservas.add(reserva);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error en el módulo de recursos: " +
                                e.getMessage());
        } finally {
            conn.close();
        }

        return reservas;
    }

    public void insertarReserva(ReservaVO reserva) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;

        try {

            String sql = "INSERT INTO RESERVA(" +
                         "FECHA_RESERVA, " +
                         "HORA_INICIO, " +
                         "HORA_FIN, " +
                         "ID_RESERVA, " +
                         "ID_RECURSO, " +
                         "ID_USU,MOTIVO) " +
                         "VALUES (TO_DATE(?, 'DD-MM-YYYY'), ?, ?, S_RESERVA.NEXTVAL, ?, ?,?)";

            conn = ConnectionPool.getInstance().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, fecha.format(reserva.getFechaReserva()));
            ps.setLong(2,
                       Long.parseLong(Parse.quitaDosPuntos(hora.format(reserva.
                    getHoraDeInicio()))));
            ps.setLong(3,
                       Long.parseLong(Parse.quitaDosPuntos((hora.format(reserva.
                    getHoraDeFin())))));
            ps.setLong(4, reserva.getIdRecurso().longValue());
            ps.setLong(5, reserva.getIdUsuario().longValue());
            ps.setString(6,reserva.getMotivo());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error en el módulo de recursos: " +
                                e.getMessage());
        } finally {
            conn.close();
        }
    }

    public void eliminarReserva(Long idReserva) throws Exception {

        Connection conn = null;
        PreparedStatement ps = null;

        try {

            String sql = "DELETE FROM RESERVA " +
                         "WHERE ID_RESERVA = ? ";

            conn = ConnectionPool.getInstance().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, idReserva.longValue());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error en el módulo de recursos: " +
                                e.getMessage());
        } finally {
            conn.close();
        }
    }

    /**
     * getAllRecursos
     *
     * @return List
     */
    public List getAllRecursos() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List recursos = new LinkedList();

        try {

            String sql = "SELECT " +
                         "NOMBRE_RECURSO, " +
                         "ID_RECURSO, " +
                         "ID_TIPO_REC, " +
                         "DESCRIPCION_RECURSO,ACTIVO " +
                         "FROM RECURSO";

            conn = ConnectionPool.getInstance().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                RecursoVO recurso = new RecursoVO();
                recurso.setNombre(rs.getString(1));
                recurso.setIdRecurso(new Long(rs.getLong(2)));
                recurso.setIdTipoRecurso(new Long(rs.getLong(3)));
                recurso.setDescripcion(rs.getString(4));
                recurso.setIsActivo(new Integer(rs.getInt(5)));
                recursos.add(recurso);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error en el módulo de recursos: " +
                                e.getMessage());
        } finally {
            conn.close();
        }

        return recursos;
    }

    /**
     * insertaRecurso
     *
     * @param nom String
     * @param desc String
     * @return boolean
     */
    public boolean insertaRecurso(String nom, String desc) {
        try {
            String q = "INSERT INTO recurso (id_recurso,nombre_recurso," +
                       "descripcion_recurso,id_tipo_rec,activo)" +
                       "values(s_recurso.nextval,'" + nom + "','" + desc +
                       "',3,1)";
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(q);
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * actualizarRecurso
     *
     * @param recurso RecursoVO
     * @return boolean
     */
    public boolean actualizarRecurso(RecursoVO recurso) throws Exception {
        try {
            int act = 1;
            if (!recurso.getIsActivo()) {
                act = 0;
            }

            String q = "Update recurso SET nombre_recurso='" +
                       recurso.getNombre() + "'," +
                       "descripcion_recurso='" + recurso.getDescripcion() +
                       "'," +
                       " activo=" + act +
                       " WHERE id_recurso=" + recurso.getIdRecurso();
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /*public boolean rangoValido(FiltroRecursos filtro) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;

        try {

            String sql = "SELECT "+
                         "RESERVA.FECHA_RESERVA, "+
                         "RESERVA.HORA_INICIO, "+
                         "RESERVA.HORA_FIN, "+
                         "RESERVA.ID_RESERVA, "+
                         "RESERVA.ID_USU, "+
                         "USUARIO.NOMBRE_USU, "+
                         "USUARIO.NOMBRE2_USU, "+
                         "USUARIO.APELLIDO_USU, "+
                         "USUARIO.APELLIDO2_USU "+
     "FROM RESERVA JOIN USUARIO ON RESERVA.ID_USU = USUARIO.ID_USU "+
                         "WHERE ID_RECURSO = ? ";

            conn = ConnectionPool.getInstance().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, filtro.getIdRecurso().longValue());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
     throw new Exception("Error en el módulo de recursos: " + e.getMessage());
        } finally {
            conn.close();
        }
         }*/
}

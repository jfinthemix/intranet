package proyecto_uoct.dao;

import java.sql.*;
import java.text.*;
import java.util.*;

import proyecto_uoct.EIV.VO.*;
import proyecto_uoct.common.*;
import proyecto_uoct.common.VO.*;

public class EIVDAO {

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public EIVDAO() {
    }


    private static EIVDAO dao = null;

    public static EIVDAO getInstance() {
        if (dao == null) {
            dao = new EIVDAO();
        }
        return dao;
    }


    public List getEstadosEIV() {
        List listaes = new LinkedList();
        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "SELECT id_estado_eiv, descrip_estado FROM ESTADOEIV");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                IdStrVO estado = new IdStrVO();
                estado.setId(new Integer(rs.getInt(1)));
                estado.setStr(rs.getString(2));
                listaes.add(estado);
            }

            conn.close();

        } catch (Exception e) {
            System.out.println("no se consultaron los tipos de cliente");
            e.printStackTrace();
        }
        return listaes;
    }





    /**
     * getDetalleEIV
     *
     * @param id_eiv Integer
     * @return EIVVO
     */
    public EIVVO getDetalleEIV(Integer id_eiv) {
        EIVVO eiv = new EIVVO();
        Connection conn = null;

        String q =
                "SELECT id_eiv, titulo_EIV,fecha_vencimiento, cons, empcons," +
                "USUARIO.nombre_usu,USUARIO.nombre2_usu,USUARIO.apellido_usu," +
                "fecha_ingreso,id_estado_eiv, USUARIO.id_usu,descrip_estado," +
                "fecha_presentacion,fecha_envio_seremitt," +
                "datoseiv.ID_DOCUMENTO,datosdocumento.TIPO_DOCUMENTO,datosdocumento.CODIGO,ESTADOSEREMITT" +
                " FROM datosEIV,USUARIO,datosdocumento " +
                "WHERE datosEIV.id_usu=USUARIO.id_usu(+) " +
                "AND datoseiv.ID_DOCUMENTO=datosdocumento.ID_DOCUMENTO(+) " +
                "AND datosEIV.id_eiv=?";

        /*" SELECT id_eiv, titulo_EIV,fecha_vencimiento, cons, empcons," +
         " usuario.nombre_usu,usuario.nombre2_usu,usuario.apellido_usu," +
         " fecha_ingreso,id_estado_eiv, usuario.id_usu,descrip_estado," +
         " fecha_presentacion,fecha_envio_seremitt FROM datosEIV JOIN usuario " +
         "on datosEIV.id_usu=usuario.id_usu(+) WHERE datosEIV.id_eiv=?";*/
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_eiv.intValue());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                eiv.setIdEIV(new Integer(rs.getInt(1)));
                eiv.setNomEiv(rs.getString(2));
                eiv.setFechaVenc(rs.getDate(3));
                eiv.setNomCons(rs.getString(4));
                eiv.setEmpCons(rs.getString(5));
                eiv.setNomEncarg(rs.getString(6));
                eiv.setNom2Encarg(rs.getString(7));
                eiv.setApeEncarg(rs.getString(8));
                eiv.setFechaIng(rs.getDate(9));
                eiv.setIdEstado(new Integer(rs.getInt(10)));
                eiv.setIdEncargado(new Integer(rs.getInt(11)));
                eiv.setEstado(rs.getString(12));
                eiv.setFechaPresent(rs.getDate(13));
                eiv.setFechaEnvioSeremitt(rs.getDate(14));
                eiv.setIdOficio(new Integer(rs.getInt(15)));
                eiv.setNomDocumento(rs.getString(16) + " " + rs.getString(17));
                eiv.setEstadoSeremitt(new Integer(rs.getInt(18)));
                eiv.setFlujos(this.getFlujosEIV(id_eiv));
                eiv.setRedes(this.getRedesEIV(id_eiv));

            }

            ps = conn.prepareCall(
                    "SELECT tipo_eiv, eiv.id_tipo_eiv from eiv, tipo_eiv " +
                    " WHERE tipo_eiv.id_tipo_eiv(+)= eiv.id_tipo_eiv  " +
                    " AND eiv.id_eiv=?");
            ps.setInt(1, id_eiv.intValue());
            rs = ps.executeQuery();
            if (rs.next()) {
                eiv.setTipoEstudio(rs.getString(1));
                eiv.setIdTipoEstudio(new Integer(rs.getInt(2)));
            }

            ps = conn.prepareCall(
                    "SELECT comuna,eiv.direccion,eiv.estacionamientos,eiv.id_comuna" +
                    " FROM eiv ,comuna WHERE eiv.id_comuna= comuna.id_comuna(+) AND eiv.id_eiv=?");
            ps.setInt(1, id_eiv.intValue());
            rs = ps.executeQuery();
            if (rs.next()) {
                eiv.setComuna(rs.getString(1));
                eiv.setDir(rs.getString(2));
                eiv.setEstacionamientos(new Integer(rs.getInt(3)));
                eiv.setIdComuna(new Integer(rs.getInt(4)));
            }

            conn.close();

        } catch (Exception e) {
            System.out.println("no se consulto detalle de eiv");
            e.printStackTrace();
        }

        return eiv;
    }

//-------------------------------------------------------------------------
    /**
     * getRedesEIV
     *
     * @param id_eiv Integer
     * @return List Retorna una lista con los identificadores(INTEGER)
     *   de las redes relacionadas con el EIV entregado como parametro
     */
    public List getRedesEIV(Integer id_eiv) {
        Connection conn = null;
        List redes = new LinkedList();
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "select id_red from eiv_red where id_eiv=?");
            ps.setInt(1, id_eiv.intValue());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer r = new Integer(rs.getInt(1));
                redes.add(r);
            }
            conn.close();

        } catch (Exception e) {
            System.out.println("no se consultaron las redes del eiv");
            e.printStackTrace();
        }
        return redes;

    }

    //-------------------------------------------------------------------------
    public List getFlujosEIV(Integer id_eiv) {
        Connection conn = null;
        List flujos = new LinkedList();
        String q =
                "SELECT tipo_dia,fecha,horasMed,calle1,calle2,id_eiv," +
                "flujovehicular.id_tipo_dia,id_flujo_vehicular" +
                " FROM flujovehicular , tipo_de_dia  " +
                " WHERE flujovehicular.ID_TIPO_DIA= tipo_de_dia.id_TIPO_DIA " +
                " AND flujovehicular.id_eiv=? ORDER BY id_flujo_vehicular";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_eiv.intValue());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FlujoVO elflu = new FlujoVO();
                elflu.setTipoDia(rs.getString(1));
                elflu.setFecha(rs.getDate(2));
                elflu.setHorasMed(rs.getString(3));
                elflu.setCalle1(rs.getString(4));
                elflu.setCalle2(rs.getString(5));
                elflu.setIdTipoDia(new Integer(rs.getInt(7)));
                if (rs.getString(5) != null) {
                    elflu.setInterseccion(rs.getString(4) + " / " +
                                          rs.getString(5));
                } else {
                    elflu.setInterseccion(rs.getString(4) + " / --");
                }
                elflu.setIdEIV(new Integer(rs.getInt(6)));
                elflu.setIdFlujo(new Integer(rs.getInt(8)));
                flujos.add(elflu);
            }
            conn.close();

        } catch (Exception e) {
            try {
                conn.close();
            } catch (SQLException ex) {
            }
            System.out.println("no se consultaron flujos del EIV");
            e.printStackTrace();
        }
        return flujos;

    }


    //------------------------------------------------------

    public List getFlujosxCalle(String calle) {
        Connection conn = null;
        List flujos = new LinkedList();
        calle = calle.toUpperCase();

        String q = "SELECT tipo_dia,fecha,horasmed,calle1,calle2,id_eiv FROM " +
                   "flujovehicular , tipo_de_dia WHERE flujovehicular.ID_TIPO_DIA= " +
                   "tipo_de_dia.id_TIPO_DIA AND UPPER(flujovehicular.calle1) LIKE ? OR " +
                   "UPPER(flujovehicular.calle2) LIKE ?";

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setString(1, "%" + calle + "%");
            ps.setString(2, "%" + calle + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FlujoVO elflu = new FlujoVO();
                elflu.setTipoDia(rs.getString(1));
                elflu.setFecha(rs.getDate(2));
                elflu.setHorasMed(rs.getString(3));
                elflu.setCalle1(rs.getString(4));
                elflu.setCalle2(rs.getString(5));
                elflu.setInterseccion(rs.getString(4) + "/" + rs.getString(5));
                elflu.setIdEIV(new Integer(rs.getInt(6)));
                flujos.add(elflu);
            }
            conn.close();

        } catch (Exception e) {
            System.out.println("no se consultaron flujos");
            e.printStackTrace();
        }
        return flujos;

    }

//---------------------------------------------------

    public List getFlujosxFecha(java.util.Date fecha) {
        Connection conn = null;
        List flujos = new LinkedList();
        String FECHA = null;

        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
        FECHA = sdf.format(fecha);

        String q = "SELECT tipo_dia,fecha,hora_inicio,hora_fin,calle1,calle2," +
                   " id_eiv FROM flujovehicular , tipo_de_dia WHERE " +
                   " flujovehicular.ID_TIPO_DIA= tipo_de_dia.id_TIPO_DIA " +
                   " AND flujovehicular.fecha= TO_DATE( ? ,'DD-MM-YYYY')";

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setString(1, FECHA);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FlujoVO elflu = new FlujoVO();
                elflu.setTipoDia(rs.getString(1));
                elflu.setFecha(rs.getDate(2));
                elflu.setHorasMed(rs.getString(3));
                elflu.setCalle1(rs.getString(4));
                elflu.setCalle2(rs.getString(5));
                elflu.setInterseccion(rs.getString(4) + " " + rs.getString(5));
                elflu.setIdEIV(new Integer(rs.getInt(6)));
                flujos.add(elflu);
            }
            conn.close();

        } catch (Exception e) {
            System.out.println("no se consultaron flujos");
            e.printStackTrace();
        }
        return flujos;

    }

    //---------------------------------------------------

    /**
     * getListaComunas
     *
     * @return List
     */
    public List getListaComunas() {
        List comunas = new LinkedList();

        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "SELECT id_comuna,comuna FROM comuna order by comuna asc ");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                IdStrVO com = new IdStrVO();
                com.setId(new Integer(rs.getInt(1)));
                com.setStr(rs.getString(2));

                comunas.add(com);
            }

            conn.close();
            return comunas;
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return comunas;
    }


    /**
     * getTodaslasRedes
     *
     * @return List Retorna una lista con los identificadores (INTEGER) de todas las redes
     */
    public List getTodaslasRedes() {
        List redes = new LinkedList();

        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "SELECT id_red FROM red ");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer r = new Integer(rs.getInt(1));

                redes.add(r);
            }

            conn.close();

        }

        catch (Exception e) {
            System.out.println("no se consultaron la comunas");
            e.printStackTrace();
        }

        return redes;

    }


    public List getEventos(Integer id_eiv) {
        List eventos = new LinkedList();

        String q = "select id_evento_eiv, fecha_evento, descripcion," +
                   " eventoeiv.id_documento,datosdocumento.Tipo_documento," +
                   " datosdocumento.codigo,titulo,eventoeiv.ID_TIPO_EVENTO from eventoeiv,datosdocumento " +
                   " WHERE eventoEIV.id_documento=" +
                   " datosdocumento.ID_DOCUMENTO(+) AND " +
                   "id_eiv=? order by fecha_evento desc";

        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_eiv.intValue());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EventoVO evento = new EventoVO();
                evento.setIdEvento(new Integer(rs.getInt(1)));
                evento.setFechaEv(rs.getDate(2));
                evento.setDescEv(rs.getString(3));
                evento.setIdDocRel(new Integer(rs.getInt(4)));
                evento.setTipoDoc(rs.getString(5));
                evento.setCodDoc(rs.getString(6));
                evento.setTitulo(rs.getString(7));
                evento.setIdTipoEvento(new Integer(rs.getInt(8)));
                eventos.add(evento);
            }

            conn.close();

        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return eventos;

    }


    public EventoVO getDetalleEvento(Integer idEvento) {

        String q = "select id_evento_eiv, fecha_evento, descripcion," +
                   "eventoeiv.id_documento,datosdocumento.Tipo_documento," +
                   "datosdocumento.codigo,titulo from eventoeiv , datosdocumento" +
                   " WHERE eventoEIV.id_documento=" +
                   "datosdocumento.ID_DOCUMENTO(+) AND " +
                   "id_evento_eiv=?";

        EventoVO evento = new EventoVO();
        try {

            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idEvento.intValue());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                evento.setIdEvento(new Integer(rs.getInt(1)));
                evento.setFechaEv(rs.getDate(2));
                evento.setDescEv(rs.getString(3));
                evento.setIdDocRel(new Integer(rs.getInt(4)));
                evento.setTipoDoc(rs.getString(5));
                evento.setCodDoc(rs.getString(6));
                evento.setTitulo(rs.getString(7));
            }
            conn.close();
            return evento;
        } catch (Exception e) {
            e.printStackTrace();
            return evento;
        }

    }


    public Integer[] getDoc_EIV(Integer id_eiv) {
        Connection conn = null;
        Integer[] doc = null;
        String q =
                "SELECT id_documento, id_tipo_doc from eiv_doc where id_eiv=?";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_eiv.intValue());
            ResultSet rs = ps.executeQuery();
            rs.next();
            doc[1] = new Integer(rs.getInt(1));
            doc[2] = new Integer(rs.getInt(2));
            conn.close();
            return doc;
        } catch (Exception e) {
            System.out.println("no se consultaron los oficios");
            e.printStackTrace();
        }

        return doc;

    }

    /**
     * insertEIV
     *
     * @param nuevoeiv EIVVO
     * @return boolean
     */
    public Integer insertEIV(EIVVO eiv) throws Exception {
        try {
            Integer id_nuevo = null;
            String q =
                    "INSERT INTO eiv (ID_EIV,TITULO_EIV,ID_DOCUMENTO,ID_TIPO_EIV," +
                    "FECHA_PRESENTACION,FECHA_ENVIO_SEREMITT,FECHA_INGRESO,FECHA_VENCIMIENTO," +
                    "ID_COMUNA,DIRECCION,ESTACIONAMIENTOS,CONS,ID_USU,ID_ESTADO_EIV,estadoseremitt";
            if (eiv.getEmpCons() != null) {
                q += ",EMPCONS";
            }
            String q2 = " VALUES (S_EIV.NEXTVAL,'" + eiv.getNomEiv() + "'," +
                        eiv.getIdOficio() +
                        "," + eiv.getIdTipoEstudio() + ",TO_DATE('" +
                        sdf.format(eiv.getFechaPresent()) + "','DD-MM-YYYY')," +
                        "TO_DATE('" + sdf.format(eiv.getFechaEnvioSeremitt()) +
                        "','DD-MM-YYYY')," +
                        "TO_DATE('" + sdf.format(eiv.getFechaIng()) +
                        "','DD-MM-YYYY')," +
                        "TO_DATE('" + sdf.format(eiv.getFechaVenc()) +
                        "','DD-MM-YYYY')," + eiv.getIdComuna() +
                        ",'" + eiv.getDir() + "'," + eiv.getEstacionamientos() +
                        ",'" + eiv.getNomCons() +
                        "'," + eiv.getIdEncargado() + ",1,0";

            if (eiv.getEmpCons() != null) {
                q2 += ",'" + eiv.getEmpCons() + "'";
            }

            q = q + ") " + q2 + ")";

            Connection conn = null;

            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);

            ps.executeUpdate();

            ps = conn.prepareCall(" Select s_eiv.currval FROM DUAL");
            ResultSet rs = ps.executeQuery();
            rs.next();
            Integer id_nuevoeiv = new Integer(rs.getInt(1));
            id_nuevo = id_nuevoeiv;
            conn.close();
            return id_nuevo;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }


    /**
     * insertEIV_red
     * inserta relaciones entre eiv y redes
     *
     * @param id_eiv Integer
     * @param redes List
     * @return boolean
     */
    public boolean insertEIV_red(Integer id_eiv, List redes) throws Exception {

        try {
            String q = "INSERT INTO eiv_red (id_eiv, id_red) values ( ?,?)";

            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            if (redes != null) {
                Iterator ir = redes.iterator();
                while (ir.hasNext()) {
                    Integer r = (Integer) ir.next();
                    ps.setInt(1, id_eiv.intValue());
                    ps.setInt(2, r.intValue());
                    ps.executeUpdate();
                }

            }

            conn.close();
            return true;
        }

        catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


    public void insertFlujo(FlujoVO flujo) {

        String q = "  insert into flujovehicular" +
                   "(id_flujo_vehicular,  id_tipo_dia,   id_Eiv,  fecha," +
                   "calle1,calle2,horasmed) values" +
                   " (s_flujovehicular.nextval,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?)";

        Connection conn = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);

            ps.setInt(1, flujo.getIdTipoDia().intValue());
            ps.setInt(2, flujo.getIdEIV().intValue());

            String fechaflu = sdf.format(flujo.getFecha());
            ps.setString(3, fechaflu);

            ps.setString(4, flujo.getCalle1());
            ps.setString(5, flujo.getCalle2());
            ps.setString(6, flujo.getHorasMed());

            ps.executeUpdate();

            conn.close();

        }

        catch (SQLException ex) {

            System.out.println("ERROR EN EL INSERT DE flujo");
            ex.printStackTrace();
        }

    }

    public Integer getReestudio(Integer id_eiv) {
        Integer id_rees = null;

        Connection conn = null;
        String q = "select id_eiv_reestudio from reestudio where id_eiv =?";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_eiv.intValue());

            ResultSet rs = ps.executeQuery();
            rs.next();

            id_rees = new Integer(rs.getInt(1));

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return id_rees;
    }


    /**
     * insertEventoEIV
     * idTipo evento puede ser
     * 1<bitacora>,2<despacho>,3<aprobacion>,4<rechazo>
     *
     * @param evento EventoVO
     * @param id_eiv Integer
     * @param idTipoEvento Integer
     * @return boolean
     * @throws Exception
     */
    public boolean insertEventoEIV(EventoVO evento, Integer id_eiv,
                                   Integer idTipoEvento) throws Exception {

        try {
            java.util.Date hora=new java.util.Date();
            SimpleDateFormat horaFormat=new SimpleDateFormat("HH:mm:ss");
            Connection conn = null;
            String q = "INSERT INTO eventoeiv (id_evento_eiv, fecha_evento," +
                       "id_eiv, descripcion,titulo,id_tipo_evento";

            String q2 = " (s_eventoeiv.NEXTVAL, TO_DATE('" +sdf.format(evento.getFechaEv())+
                        ":"+horaFormat.format(hora)+
                        "','DD-MM-YYYY:hh24:mi:ss')," + id_eiv + ",'" + evento.getDescEv() +
                        "','" + evento.getTitulo() + "'," + idTipoEvento;

            if (evento.getIdDocRel() != null) {
                q += ",id_documento";
                q2 += "," + evento.getIdDocRel();
            }

            q = q + ") values";
            q2 = q2 + ")";
            q = q + q2;

            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


    /**
     * actuEstadoEIV
     * id_estado debe ser 1 para el estado Ingresado,
     * 2 para En observación, 3 para Aprobado, 4 para Rechazado.
     *
     * @param id_estado Integer
     * @param id_eiv Integer
     * @return boolean
     * @throws Exception
     */
    public boolean actuEstadoEIV(Integer id_estado, Integer id_eiv) throws
            Exception {
        Connection conn;
        String q = "UPDATE EIV SET id_estado_eiv = ? WHERE id_eiv = ?";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_estado.intValue());
            ps.setInt(2, id_eiv.intValue());
            ps.executeUpdate();

            conn.close();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }

    /**
     * getTiposEIV
     *
     * @return List
     */
    public List getTiposEIV() throws Exception {
        List ltipos = new LinkedList();
        String q =
                "select id_tipo_eiv,tipo_eiv from tipo_EIV order by tipo_eiv asc";
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                IdStrVO tipo = new IdStrVO();
                tipo.setId(new Integer(rs.getInt(1)));
                tipo.setStr(rs.getString(2));
                ltipos.add(tipo);
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

        return ltipos;

    }


    public boolean insertTipoEIV(String tipo) throws Exception {

        String q = "INSERT INTO tipo_eiv  (id_tipo_eiv,tipo_eiv) " +
                   "values(s_tipo_eiv.nextval,upper('" + tipo + "')) ";
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }

    public boolean eliminarTipoEIV(Integer tipo) throws Exception {

        String q = "DELETE tipo_eiv WHERE id_tipo_eiv=" + tipo.toString();
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }

    /**
     * insertComuna
     *
     * @param nomComuna String
     * @return boolean
     */
    public boolean insertComuna(String nomComuna) throws Exception {
        String q = "INSERT INTO comuna  (id_comuna,comuna) " +
                   "values(s_comuna.nextval,upper('" + nomComuna + "')) ";
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * eliminarComuna
     *
     * @param idComuna Integer
     * @return boolean
     */
    public boolean eliminarComuna(Integer idComuna) throws Exception {
        String q = "DELETE comuna WHERE id_comuna=" + idComuna.toString();
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * buscarEIV
     *
     * @param bus BuscadorEIVVO
     * @return List
     */
    public List buscarEIV(BuscadorEIVVO bus) {
        String q =
                "SELECT DATOSEIV.id_eiv, DATOSEIV.titulo_EIV,DATOSEIV.fecha_vencimiento," +
                " DATOSEIV.cons,DATOSEIV.empcons, " +
                " usuario.nombre_usu,usuario.nombre2_usu,usuario.apellido_usu," +
                " DATOSEIV.descrip_estado,documento.CODIGO,datosEIV.id_estado_eiv,datoseiv.ESTADOSEREMITT "+
                " FROM datosEIV,usuario,documento,eiv_red  " +
                " where datosEIV.id_usu=usuario.id_usu(+) " +
                " and datosEIV.id_documento=documento.id_documento" +
                " and datosEIV.id_eiv=eiv_red.id_eiv " ;
                /*" Intersect " +
                " (SELECT DATOSEIV.id_eiv, DATOSEIV.titulo_EIV,DATOSEIV.fecha_vencimiento, " +
                " DATOSEIV.cons,DATOSEIV.empcons," +
                " usuario.nombre_usu,usuario.nombre2_usu,usuario.apellido_usu," +
                " DATOSEIV.descrip_estado,documento.CODIGO,datosEIV.id_estado_eiv " +
                " FROM datosEIV,usuario,documento,eiv_red " +
                " WHERE datosEIV.id_usu=usuario.id_usu(+) " +
                " AND datosEIV.id_documento=documento.id_documento" +
                " AND datosEIV.id_eiv=eiv_red.id_eiv";
*/

        if (bus.getIdEIV() != null) {
            q += " AND datosEIV.id_eiv='" + bus.getIdEIV() + "'";
        }

        if (bus.getConsultor() != null) {

            q += " AND UPPER(datosEIV.cons) LIKE UPPER('%" + bus.getConsultor() +
                    "%')";
        }

        if (bus.getFechaVenc() != null && bus.getFechaVenc_b() != null) {
            q += " AND TO_DATE('" + bus.getFechaVenc() +
                    "','DD-MM-YYYY')< datosEIV.fecha_vencimiento AND TO_DATE('" +
                    bus.getFechaVenc_b() +
                    "','DD-MM-YYYY')> datosEIV.fecha_vencimiento ";

        }

        if (bus.getFechaVenc() != null && bus.getFechaVenc_b() == null) {
            q += " AND TO_DATE('" + bus.getFechaVenc() +
                    "','DD-MM-YYYY')< datosEIV.fecha_vencimiento";

        }

        if (bus.getFechaVenc() == null && bus.getFechaVenc_b() != null) {
            q += " AND TO_DATE('" + bus.getFechaVenc_b() +
                    "','DD-MM-YYYY')> datosEIV.fecha_vencimiento";

        }

        if (bus.getIdComuna() != null) {
            q += " AND id_comuna=" + bus.getIdComuna();

        }

        if (bus.getIdEstado() != null) {

            q += " AND datosEIV.id_estado_eiv=" + bus.getIdEstado();
        }

        if (bus.getIdIngeniero() != null) {
            q += " AND datoseiv.id_usu=" + bus.getIdIngeniero();

        }

        if (bus.getRed() != null) {
            q += " AND id_red=" + bus.getRed();
        }

        if (bus.getEstadoSeremitt() != null) {
            q += " AND estadoseremitt=" + bus.getEstadoSeremitt();
        }


        if (bus.getPalClave() != null) {
            q += " AND (UPPER(datosEIV.titulo_eiv) LIKE UPPER('%" +
                    bus.getPalClave() +
                    "%') OR " +
                    " UPPER(datosEIV.cons) LIKE UPPER('%" + bus.getPalClave() +
                    "%') OR" +
                    " UPPER(datosEIV.empcons) LIKE UPPER('%" + bus.getPalClave() +
                    "%') OR" +
                    " UPPER(usuario.nombre_usu) LIKE UPPER('%" +
                    bus.getPalClave() + "%') OR" +
                    " UPPER(usuario.nombre2_usu) LIKE UPPER('%" +
                    bus.getPalClave() + "%') OR" +
                    " UPPER(usuario.apellido_usu) LIKE UPPER('%" +
                    bus.getPalClave() + "%') OR" +
                    " UPPER(descrip_estado) LIKE UPPER('%" +
                    bus.getPalClave() + "%') OR" +
                    " UPPER(documento.CODIGO) LIKE UPPER('%" +
                    bus.getPalClave() + "%') OR" +
                    " UPPER(datoseiv.direccion) LIKE UPPER('%" +
                    bus.getPalClave() + "%') OR" +
                    " UPPER(datoseiv.comuna) LIKE UPPER('%" +
                    bus.getPalClave() + "%'))";

        }
        q = " ( "+q+" ) ";
        q= q+" INTERSECT "+ q;

        List listaeiv = new LinkedList();
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EIVdeListaVO eiv = new EIVdeListaVO();
                eiv.setIdEIV(new Integer(rs.getInt(1)));
                eiv.setNomEiv(rs.getString(2));
                eiv.setFechaVenc(rs.getDate(3));
                eiv.setNomCons(rs.getString(4));
                eiv.setEmpCons(rs.getString(5));
                eiv.setNomEncarg(rs.getString(6));
                eiv.setNom2Encarg(rs.getString(7));
                eiv.setApeEncarg(rs.getString(8));
                eiv.setEstado(rs.getString(9));
                eiv.setIdEstado(new Integer(rs.getInt(11)));
                listaeiv.add(eiv);
            }

            conn.close();
            return listaeiv;
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return listaeiv;
    }

    /**
     * actualizarEIV
     *
     * @param eiv EIVDAO
     * @return boolean
     */
    public boolean actualizarEIV(EIVVO eiv) throws Exception {
        try {
            String q =
                    " UPDATE eiv SET ID_TIPO_EIV=" + eiv.getIdTipoEstudio() +
                    ", FECHA_PRESENTACION=TO_DATE('" +
                    sdf.format(eiv.getFechaPresent()) +
                    "','DD-MM-YYYY')," +
                    " FECHA_ENVIO_SEREMITT=TO_DATE('" +
                    sdf.format(eiv.getFechaEnvioSeremitt()) +
                    "','DD-MM-YYYY')," +
                    " FECHA_INGRESO=TO_DATE('" + sdf.format(eiv.getFechaIng()) +
                    "','DD-MM-YYYY')," +
                    " FECHA_VENCIMIENTO=TO_DATE('" +
                    sdf.format(eiv.getFechaVenc()) +
                    "','DD-MM-YYYY')," +
                    " ID_COMUNA=" + eiv.getIdComuna() + "," +
                    " DIRECCION='" + eiv.getDir() + "',ESTACIONAMIENTOS=" +
                    eiv.getEstacionamientos() + "," +
                    " CONS='" + eiv.getNomCons() + "',ID_USU=" +
                    eiv.getIdEncargado() +
                    ",ID_ESTADO_EIV=" + eiv.getIdEstado();
            if (eiv.getEmpCons() != null) {
                q += ",EMPCONS='" + eiv.getEmpCons() + "'";
            }

            q += " WHERE ID_EIV=" + eiv.getIdEIV();

            Connection conn = null;

            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);

            ps.executeUpdate();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    /**
     * eliminarRedesEIV
     *
     * @param integer Integer
     */
    public boolean eliminarRedesEIV(Integer id_eiv) throws Exception {

        String q = "DELETE eiv_red WHERE id_eiv=" + id_eiv;
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }

    /**
     * eliminarFlujo
     *
     * @param idFlujo Integer
     * @return boolean
     */
    public boolean eliminarFlujo(Integer idFlujo) throws Exception {
        String q = "DELETE flujovehicular WHERE id_flujo_vehicular=" + idFlujo;
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }

    /**
     * eliminarEvento
     *
     * @param idEvento Integer
     */
    public boolean eliminarEvento(Integer idEvento) throws Exception {
        String q = "DELETE eventoeiv WHERE id_evento_eiv=" + idEvento;
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }

    /**
     * buscarFlujos
     *
     * @param busflujos BusFlujosVO
     * @return List
     */
    public List buscarFlujos(BusFlujosVO busflujos) {
        try {
            Connection conn = null;
            List flujos = new LinkedList();
            String q =
                    "SELECT tipo_dia,fecha,horasmed,calle1,calle2," +
                    "id_eiv FROM flujovehicular , tipo_de_dia where " +
                    "flujovehicular.ID_TIPO_DIA= tipo_de_dia.id_TIPO_DIA";

            String q2 = "";

            if (busflujos.getIdEIV() != null) {
                q2 += " AND ID_EIV=" + busflujos.getIdEIV();
            }

            if (busflujos.getCalles() != null) {
                q2 += " AND (UPPER(calle1) LIKE UPPER( '%" +
                        busflujos.getCalles() + "%')" +
                        " OR UPPER(calle2) LIKE UPPER( '%" +
                        busflujos.getCalles() + "%'))";

            }
            if (busflujos.getFecha() != null) {
                q2 += " AND flujovehicular.fecha= TO_DATE( '" +
                        busflujos.getFecha() + "' ,'DD-MM-YYYY')";
            }

            q = q + q2;
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q); ;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FlujoVO elflu = new FlujoVO();
                elflu.setTipoDia(rs.getString(1));
                elflu.setFecha(rs.getDate(2));
                elflu.setHorasMed(rs.getString(3));
                elflu.setCalle1(rs.getString(4));
                elflu.setCalle2(rs.getString(5));
                if (rs.getString(5) != null) {
                    elflu.setInterseccion(rs.getString(4) + " / " +
                                          rs.getString(5));
                } else {
                    elflu.setInterseccion(rs.getString(4) + " / --");
                }
                elflu.setIdEIV(new Integer(rs.getInt(6)));
                flujos.add(elflu);
            }
            conn.close();
            return flujos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    public List buscarEIVxFechaVenc(java.util.Date fechaVenc) throws Exception {
        String q =
                "(SELECT DATOSEIV.id_eiv, DATOSEIV.titulo_EIV,DATOSEIV.fecha_vencimiento," +
                " DATOSEIV.cons,DATOSEIV.empcons, " +
                " usuario.nombre_usu,usuario.nombre2_usu,usuario.apellido_usu," +
                " DATOSEIV.descrip_estado,documento.CODIGO,datosEIV.id_estado_eiv FROM datosEIV,usuario,documento,eiv_red " +
                " where datosEIV.id_usu=usuario.id_usu(+) " +
                " and datosEIV.id_documento=documento.id_documento" +
                " and datosEIV.id_eiv=eiv_red.id_eiv) " +
                " Intersect " +
                " (SELECT DATOSEIV.id_eiv, DATOSEIV.titulo_EIV,DATOSEIV.fecha_vencimiento, " +
                " DATOSEIV.cons,DATOSEIV.empcons," +
                " usuario.nombre_usu,usuario.nombre2_usu,usuario.apellido_usu," +
                " DATOSEIV.descrip_estado,documento.CODIGO,datosEIV.id_estado_eiv " +
                " FROM datosEIV,usuario,documento,eiv_red " +
                " WHERE datosEIV.id_usu=usuario.id_usu(+) " +
                " AND datosEIV.id_documento=documento.id_documento" +
                " AND datosEIV.id_eiv=eiv_red.id_eiv";

        q += " AND TO_DATE('" + sdf.format(fechaVenc) +
                "','DD-MM-YYYY')= fecha_vencimiento )";

        List listaeiv = new LinkedList();
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EIVdeListaVO eiv = new EIVdeListaVO();
                eiv.setIdEIV(new Integer(rs.getInt(1)));
                eiv.setNomEiv(rs.getString(2));
                eiv.setFechaVenc(rs.getDate(3));
                eiv.setNomCons(rs.getString(4));
                eiv.setEmpCons(rs.getString(5));
                eiv.setNomEncarg(rs.getString(6));
                eiv.setNom2Encarg(rs.getString(7));
                eiv.setApeEncarg(rs.getString(8));
                eiv.setEstado(rs.getString(9));
                eiv.setIdEstado(new Integer(rs.getInt(11)));

                listaeiv.add(eiv);

            }

            conn.close();
            return listaeiv;
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * cambiaEstadoSeremitt
     * Actualiza el estado de un EIV con respecto a seremitt
     * El estadoSeremitt debe ser interpretado como:
     *    1: aprobado por seremitt
     *    2: rechazado por seremitt
     *
     * @param id_eiv Integer
     * @param i int
     */
    public void cambiaEstadoSeremitt(Integer id_eiv, int i) throws Exception {
        String q = "UPDATE EIV set estadoseremitt="+i+" where id_eiv="+id_eiv;
              try {
                  Connection conn = ConnectionPool.getInstance().getConnection();
                  PreparedStatement ps = conn.prepareCall(q);
                  ps.executeUpdate();
                  conn.close();

              } catch (Exception ex) {
                  System.out.print(ex.toString());
                  throw ex;
              }

    }

    /**
     * actualizarEvento
     *
     * Actualiza los datos de un evento de bitácora.
     *
     * @param idEIV Integer
     * @param evento EventoVO
     */
    public void actualizarEvento(EventoVO evento) throws Exception {

        String q=null;

        if(evento.getIdDocRel().intValue()!=0){
        q = "UPDATE eventoeiv SET descripcion='"+evento.getDescEv()+"',"+
                   "fecha_evento=TO_DATE('"+sdf.format(evento.getFechaEv())+"','DD-MM-YYYY'),"+
                   "id_documento="+evento.getIdDocRel()+" WHERE id_evento_eiv="+evento.getIdEvento();
        }else{
         q = "UPDATE eventoeiv SET descripcion='"+evento.getDescEv()+"',"+
                       "fecha_evento=TO_DATE('"+sdf.format(evento.getFechaEv())+"','DD-MM-YYYY') "+
                      "WHERE id_evento_eiv="+evento.getIdEvento();
        }


              try {
                  Connection conn = ConnectionPool.getInstance().getConnection();
                  PreparedStatement ps = conn.prepareCall(q);
                  ps.executeUpdate();
                  conn.close();

              } catch (Exception ex) {
                  System.out.print(ex.toString());
                  throw ex;
              }

    }


}

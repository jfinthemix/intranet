package proyecto_uoct.dao;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import oracle.sql.*;
import proyecto_uoct.common.*;
import proyecto_uoct.common.VO.*;
import proyecto_uoct.common.util.*;
import proyecto_uoct.documentacion.VO.*;
import proyecto_uoct.proyecto.VO.*;
import proyecto_uoct.usuario.VO.*;


public class ProyectoDAO {
    public ProyectoDAO() {
    }


    private static ProyectoDAO dao = null;

    public static ProyectoDAO getInstance() {
        if (dao == null) {
            dao = new ProyectoDAO();
        }
        return dao;
    }


    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");


//----------------------------------------
    public List getAllProyectos() {
        List proys = new LinkedList();
        Connection conn = null;
        String q =
                "SELECT id_proyecto, nombre_proyecto FROM proyecto order by fecha_inicio desc";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                IdStrVO unp = new IdStrVO();
                unp.setId(new Integer(rs.getInt(1)));
                unp.setStr(rs.getString(2));
                proys.add(unp);

            }

            conn.close();
        }

        catch (Exception e) {
            System.out.println("no se consultaron los proyectos");
            e.printStackTrace();
        }
        return proys;
    }


//----------------------------------------

    /**
     * getProyectosxAno
     * recibe un Integer indicando el año para realizar una búsqueda de
     * proyectos por ese parámetro. Retorna un List con los datos de los
     * proyectos coincidentes
     *
     * @param ano Integer
     * @return List
     */
    public List getProyectosxAno(Integer ano, boolean isActivo) {
        List proyectos = new LinkedList();
        Connection conn = null;
        String ano1 = "01-" + "01" + "-" + ano.toString();

        ano = new Integer(ano.intValue() + 1);
        String ano2 = "01-" + "01" + "-" + ano.toString();

        String q = "SELECT iniciativa.idIniciativa, nombre_ini," +
                   " fecha_inicio,nombre_usu,apellido_usu,iniciativa.activo, " +
                   " cliente.NOMBRE_CLIENTE,cliente.APELLIDO_CLIENTE,cliente.id_cliente,encargadoIni.id_usu" +
                   " FROM iniciativa ,encargadoini,usuario,cliente   " +
                   " WHERE iniciativa.idiniciativa=encargadoini.IDINICIATIVA" +
                   "  AND encargadoini.id_usu=usuario.id_usu" +
                   "  AND iniciativa.ID_CLIENTE=cliente.ID_CLIENTE" +
                   " AND iniciativa.fecha_inicio >= TO_DATE(?, 'DD-MM-YYYY')" +
                   " AND iniciativa.fecha_inicio < TO_DATE(?, 'DD-MM-YYYY')";

        if (isActivo) {
            q += " and iniciativa.activo=1";
        }

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setString(1, ano1);
            ps.setString(2, ano2);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProyectodeListaVO pro = new ProyectodeListaVO();
                pro.setIdProy(new Integer(rs.getInt(1)));
                pro.setNomProy(rs.getString(2));
                pro.setFechaProy(rs.getDate(3));
                pro.setEncargado(rs.getString(4) + " " + rs.getString(5));
                pro.setIsActivo(new Integer(rs.getInt(6)));
                ClienteVO c = new ClienteVO();
                c.setNomCli(rs.getString(7));
                c.setApeCli(rs.getString(8));
                c.setIdCli(new Integer(rs.getInt(9)));
                pro.setCli(c);
                pro.setIdEncargado(new Integer(rs.getInt(10)));
                proyectos.add(pro);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return proyectos;
    }


    public List BuscadorProyEnEquipo(Integer ano, Integer idUsu,
                                     boolean isActivo) {
        List proyectos = new LinkedList();
        Connection conn = null;

        String q = "SELECT INICIATIVAENCARGADO.IDINICIATIVA, NOMBRE_INI, " +
                   "INICIATIVAENCARGADO.fecha_inicio,INICIATIVAENCARGADO.nombre_usu,INICIATIVAENCARGADO.nombre2_usu, " +
                   "INICIATIVAENCARGADO.apellido_usu,INICIATIVAENCARGADO.activo," +
                   " INICIATIVAENCARGADO.id_usu FROM INICIATIVAENCARGADO," +
                   " EQUIPOINI, USUARIO " +
                   " WHERE INICIATIVAENCARGADO.IDINICIATIVA=EQUIPOINI.IDINICIATIVA " +
                   " AND EQUIPOINI.ID_USU=USUARIO.ID_USU AND USUARIO.ID_USU=" +
                   idUsu.toString() +
                   " and iniciativaencargado.ID_USU <> " + idUsu.toString();

        if (ano.intValue() != 0) {

            String ano1 = "01-" + "01" + "-" + ano.toString();

            ano = new Integer(ano.intValue() + 1);
            String ano2 = "01-" + "01" + "-" + ano.toString();
            q += " AND iniciativaencargado.fecha_inicio >= TO_DATE('" + ano1 +
                    "', 'DD-MM-YYYY')" +
                    " AND iniciativaencargado.fecha_inicio < TO_DATE('" + ano2 +
                    "', 'DD-MM-YYYY')";

        }

        if (isActivo) {
            q += " and iniciativaencargado.activo=1";
        }

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProyectodeListaVO pro = new ProyectodeListaVO();
                pro.setIdProy(new Integer(rs.getInt(1)));
                pro.setNomProy(rs.getString(2));
                pro.setFechaProy(rs.getDate(3));
                if (rs.getString(5) != null) {
                    pro.setEncargado(rs.getString(4) + " " + rs.getString(5) +
                                     " " + rs.getString(6));
                } else {
                    pro.setEncargado(rs.getString(4) + " " + rs.getString(6));
                }
                pro.setIsActivo(new Integer(rs.getInt(7)));
                pro.setIdEncargado(new Integer(rs.getInt(8)));
                proyectos.add(pro);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return proyectos;
    }


    public List BuscadorProyaCargo(Integer ano, Integer idUsu, boolean isActivo) {
        List proyectos = new LinkedList();
        Connection conn = null;

        String q = "SELECT INICIATIVAENCARGADO.IDINICIATIVA, NOMBRE_INI," +
                   " INICIATIVAENCARGADO.fecha_inicio,INICIATIVAENCARGADO.nombre_usu," +
                   " INICIATIVAENCARGADO.nombre2_usu,INICIATIVAENCARGADO.apellido_usu," +
                   " INICIATIVAENCARGADO.activo,INICIATIVAENCARGADO.id_usu FROM " +
                   " INICIATIVAENCARGADO where id_usu=" + idUsu.toString();

        if (ano.intValue() != 0) {

            String ano1 = "01-" + "01" + "-" + ano.toString();

            ano = new Integer(ano.intValue() + 1);
            String ano2 = "01-" + "01" + "-" + ano.toString();
            q += " AND iniciativaencargado.fecha_inicio >= TO_DATE('" + ano1 +
                    "', 'DD-MM-YYYY')" +
                    " AND iniciativaencargado.fecha_inicio < TO_DATE('" + ano2 +
                    "', 'DD-MM-YYYY')";

        }

        if (isActivo) {
            q += " and iniciativaencargado.activo=1";
        }

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProyectodeListaVO pro = new ProyectodeListaVO();
                pro.setIdProy(new Integer(rs.getInt(1)));
                pro.setNomProy(rs.getString(2));
                pro.setFechaProy(rs.getDate(3));
                if (rs.getString(5) != null) {
                    pro.setEncargado(rs.getString(4) + " " + rs.getString(5) +
                                     " " + rs.getString(6));
                } else {
                    pro.setEncargado(rs.getString(4) + " " + rs.getString(6));
                }

                pro.setIsActivo(new Integer(rs.getInt(7)));
                pro.setIdEncargado(new Integer(rs.getInt(8)));
                proyectos.add(pro);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return proyectos;
    }


    public List BuscadorOtrasIniciativas(Integer ano, Integer idUsu,
                                         boolean isActivo) {
        List proyectos = new LinkedList();
        Connection conn = null;

        String q = "select distinct i.idiniciativa, i.nombre_ini, " +
                   " i.fecha_inicio, i.nombre_usu," +
                   " i.nombre2_usu, i.apellido_usu, i.activo, i.id_usu " +
                   " from iniciativaencargado i, equipoini e " +
                   " where i.idiniciativa=e.idiniciativa " +
                   "and i.idiniciativa not in (select i.idiniciativa" +
                   " from iniciativa i, equipoini e " +
                   " where i.idiniciativa=e.idiniciativa" +
                   " and e.id_usu=" + idUsu.toString()+" )";


        if (ano.intValue() != 0) {

            String ano1 = "01-" + "01" + "-" + ano.toString();

            ano = new Integer(ano.intValue() + 1);
            String ano2 = "01-" + "01" + "-" + ano.toString();
            q += " AND i.fecha_inicio >= TO_DATE('" + ano1 +
                    "', 'DD-MM-YYYY')" +
                    " AND i.fecha_inicio < TO_DATE('" + ano2 +
                    "', 'DD-MM-YYYY')";

        }

        if (isActivo) {
            q += " and i.activo=1";
        }


        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProyectodeListaVO pro = new ProyectodeListaVO();
                pro.setIdProy(new Integer(rs.getInt(1)));
                pro.setNomProy(rs.getString(2));
                pro.setFechaProy(rs.getDate(3));


                if (rs.getString(5) != null) {
                    pro.setEncargado(rs.getString(4) + " " + rs.getString(5) +
                                     " " + rs.getString(6));
                } else {
                    pro.setEncargado(rs.getString(4) + " " + rs.getString(6));
                }



                pro.setIsActivo(new Integer(rs.getInt(7)));
                pro.setIdEncargado(new Integer(rs.getInt(8)));
                proyectos.add(pro);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return proyectos;
    }


    //-----------------------------------

    public List getEquipoIni(Integer idIni) {
        Connection conn = null;
        String q =
                "SELECT equipoIni.ID_USU, nombre_usu,nombre2_usu, apellido_usu " +
                "FROM equipoini ,usuario " +
                "WHERE usuario.id_usu=equipoini.id_usu AND idiniciativa =?";
        List encargados = new LinkedList();
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idIni.intValue());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UsuarioVO u = new UsuarioVO();
                u.setIdUsu(new Integer(rs.getInt(1)));
                u.setNombreUsu(rs.getString(2));
                u.setNombreUsu2(rs.getString(3));
                u.setApellUsu(rs.getString(4));
                encargados.add(u);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return encargados;
    }


    //-----------------------------------
    public DetalleProyectoVO getDetIni(Integer idIni) {

        Connection conn = null;
        DetalleProyectoVO pro = new DetalleProyectoVO();

        String q = "SELECT iniciativa.idiniciativa, nombre_Ini," +
                   " fecha_inicio,descripcion_ini,fondos_totales," +
                   "fondos_anuales,iniciativa.activo,cliente.id_cliente," +
                   " cliente.nombre_cliente,cliente.apellido_cliente" +
                   " FROM iniciativa , cliente " +
                   "WHERE cliente.id_cliente=iniciativa.id_cliente" +
                   "  AND iniciativa.idiniciativa=?";

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idIni.intValue());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                pro.setIdProy(new Integer(rs.getInt(1)));
                pro.setNomProy(rs.getString(2));
                pro.setFechaProy(rs.getDate(3));
                pro.setDescripcion(rs.getString(4));
                pro.setFondosTotales(new Integer(rs.getInt(5)));
                pro.setFondosAnuales(rs.getString(6));
                pro.setIsActivo(new Integer(rs.getInt(7)));
                pro.setIdCliente(new Integer(rs.getInt(8)));
                pro.setNomCliente(rs.getString(9) + " " + rs.getString(10));
            }

            conn.close();

        } catch (Exception e) {
            System.out.println("no se consultaron los tipos de cliente");
            e.printStackTrace();
        }
        return pro;
    }

//-----------------------------------------




    /**
     * getNLOssinOT
     *
     * @param id_proy Integer
     * @return List
     */
    public List getNLOsinOT(Integer id_proy) {
        List nlos = new LinkedList();

        Connection conn = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "select nlo.id_nlo,descripcion_nlo," +
                    "datosDocumento.ID_DOCUMENTO," +
                    "datosdocumento.TIPO_DOCUMENTO," +
                    "datosdocumento.CODIGO,nlo.FECHA_NLO " +
                    "FROM nlo , nlo_sin_ot,datosdocumento WHERE nlo.ID_NLO=nlo_sin_ot.ID_NLO  " +
                    "AND nlo.ID_DOCUMENTO=datosdocumento.id_documento " +
                    "AND nlo.IDINICIATIVA=? order by nlo.id_nlo asc");
            ps.setInt(1, id_proy.intValue());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                NLOdeListaVO n = new NLOdeListaVO();
                n.setId(new Integer(rs.getInt(1)));
                n.setStr(rs.getString(2));
                DocumentoInVO doc = new DocumentoInVO();
                doc.setIdDoc(new Integer(rs.getInt(3)));
                doc.setTipoDoc(rs.getString(4));
                doc.setCodDoc(rs.getString(5));
                n.setFechaNLO(rs.getDate(6));
                n.setDoc(doc);
                nlos.add(n);

            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nlos;
    }


//-----------------------------------------
    /**
     * getOTsProy
     *
     * @param id_proy Integer
     * @return List
     */
    public List getOTsProy(Integer id_proy) {
        List ots = new LinkedList();

        Connection conn = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "SELECT id_ot,nombre_ot,fecha_ot,estadoot.estado,fecha_vencimiento" +
                    " FROM ordendetrabajo ,estadoOT" +
                    " WHERE ordendetrabajo.idestado=estadoot.idestado" +
                    " AND ordendetrabajo.idiniciativa=?" +
                    " order by id_ot asc");
            ps.setInt(1, id_proy.intValue());
            ResultSet rs = ps.executeQuery();
            OTDAO otdao = OTDAO.getInstance();
            while (rs.next()) {
                OTdeListaVO unaot = new OTdeListaVO();
                unaot.setId(new Integer(rs.getInt(1)));
                unaot.setStr(rs.getString(2));
                unaot.setFechaInicio(rs.getDate(3));
                unaot.setEstado(rs.getString(4));
                unaot.setFechaVencimiento(rs.getDate(5));
                ots.add(unaot);
            }

            conn.close();

        } catch (Exception e) {
            System.out.println("no se consultaron los tipos de cliente");
            e.printStackTrace();
        }

        return ots;

    }


    //-----------------------------------------
    /**
     * getDocumentosProy
     *
     * @param id_proy Integer
     * @return List
     */
    public List getDocumentosIni(Integer idIni) {
        List documentos = new LinkedList();
        Connection conn = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "SELECT id_docini,nombre_doc from documentoiniciativa where idiniciativa=?");
            ps.setInt(1, idIni.intValue());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DocumentodeListaProyVO doc = new DocumentodeListaProyVO();
                doc.setId(new Integer(rs.getInt(1)));
                doc.setStr(rs.getString(2));
                documentos.add(doc);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return documentos;

    }


    public Integer insertProyecto(DetalleProyectoVO nuevoproy) throws Exception {

        Integer np = null;
        Connection conn = null;

        try {

            String q =
                    " INSERT INTO iniciativa (idiniciativa,nombre_ini,id_cliente," +
                    "fecha_inicio,activo ";
            String q2 = " VALUES" +
                        "(s_iniciativa.nextval,'" + nuevoproy.getNomProy() +
                        "'," + nuevoproy.getIdCliente() + ",TO_DATE('" +
                        sdf.format(nuevoproy.getFechaProy()) +
                        "','DD-MM-YYYY'),1 ";

            if (!nuevoproy.getDescripcion().equals("")) {
                q += " , descripcion_ini";
                q2 += " , '" + nuevoproy.getDescripcion() + "'";
            }

            if (!nuevoproy.getFondosAnuales().equals("")) {
                q += " ,fondos_anuales";
                q2 += " ,'" + nuevoproy.getFondosAnuales() + "'";
            }

            if (nuevoproy.getFondosTotales() != null) {
                q += " ,fondos_totales";
                q2 += " ,'" + nuevoproy.getFondosTotales() + "'";
            }

            q += ") " + q2 + ") ";

            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            if (ps.executeUpdate() != 0) {
                ps = conn.prepareCall("select s_iniciativa.currval from dual");
                ResultSet rs = ps.executeQuery();
                rs.next();
                np = new Integer(rs.getInt(1));
            }

            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        return np;
    }


    public boolean insertaEquipo(Integer id_usu, Integer id_ini) throws
            Exception {

        String q = "INSERT INTO equipoini (idiniciativa, id_usu) VALUES(?,?)";

        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_ini.intValue());
            ps.setInt(2, id_usu.intValue());
            ps.executeUpdate();
            conn.close();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public Integer insertOT(DetalleOTVO nuevaot) {
        Integer nOT = null;
        String q2 = "INSERT INTO ORDENDETRABAJO (id_ot,id_proyecto,nombre_ot," +
                    "fecha_ot";

        String q3 = " VALUES (s_ordendetrabajo.nextval,?,?,?";


        if(nuevaot.getPlazo()!=null ){
            q2+=",plazo_ejecucion";
            q3+= ", "+ nuevaot.getPlazo().toString();

        }


        if(nuevaot.getVencimiento()!=null ){
            q2+=",fecha_vencimiento";
            q3+= ", TO_DATE ('" + sdf.format(nuevaot.getVencimiento()) +
                    "','DD-MM-YYYY') ";
        }


        if(nuevaot.getEP()!=null && !nuevaot.getEP().equals("") ){
            q2+=",cobro_ep";
            q3+=", "+nuevaot.getEP();
        }

            q2+=",id_tipoot";
            q3+= ", "+nuevaot.getIdTipoOT().toString();


        if (nuevaot.getSuscrip() != null) {
            q2 += ",fecha_suscripcion";
            q3 += ", TO_DATE ('" + sdf.format(nuevaot.getSuscrip()) +
                    "','DD-MM-YYYY') ";
        }

        if (nuevaot.getFinTareas() != null) {
            q2 += ",tareas_concluidas";
            q3 += ", TO_DATE ('" + sdf.format(nuevaot.getFinTareas()) +
                    "','DD-MM-YYYY') ";
        }

        if (nuevaot.getAprobacion() != null) {
            q2 += ",aprobacion";
            q3 += ", TO_DATE ('" + sdf.format(nuevaot.getAprobacion()) +
                    "','DD-MM-YYYY') ";
        }

        String q = q2 + " ) " + q3 + " )";

        Connection conn = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, nuevaot.getIdProy().intValue());
            ps.setString(2, nuevaot.getNomOT());
            ps.setString(3, sdf.format(nuevaot.getFechaOT()));
            ps.setInt(4, nuevaot.getPlazo().intValue());
            ps.setString(5, sdf.format(nuevaot.getVencimiento()));
            ps.setString(6, nuevaot.getEP());

            if (ps.executeUpdate() != 0) {
                ps = conn.prepareCall(
                        "SELECT s_ordendetrabajo.currval FROM DUAL");
                ResultSet rs = ps.executeQuery();
                rs.next();
                nOT = new Integer(rs.getInt(1));
            }

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return nOT;
    }


    /**
     * insertaDocProy
     *
     * @param nuevodoc DocumentodeListaProyVO
     * @return boolean
     */
    public boolean regDocIni(DocumentodeListaProyVO nuevodoc) throws Exception {
        Connection conn = null;

        try {
            Archivo a = Archivo.getInstance();
            File ffInforme = null;
            try {
                ffInforme = a.formFileToFile(nuevodoc.getElDoc());
            } catch (Exception ex) {
                throw ex;
            }

            String q = "insert into documentoiniciativa " +
                       "(id_docini,idiniciativa,nombre_doc,archivo)" +
                       "  values (s_dociniciativa.nextval," +
                       nuevodoc.getIdProy() +
                       ",'" + nuevodoc.getElDoc().getFileName() +
                       "',empty_blob())";

            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(q);
            ps.executeUpdate();

            q = "SELECT s_dociniciativa.currval FROM dual";
            ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int nuevo = rs.getInt(1);

            q =
                    "SELECT archivo FROM documentoiniciativa WHERE id_docini=" +
                    nuevo;
            ps = conn.prepareCall(q);
            rs = ps.executeQuery();
            rs.next();

            BLOB blob = (BLOB) rs.getBlob(1);
            FileInputStream instream = new FileInputStream(ffInforme);
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
            return true;

        }

        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    /**
     * insertaEncargado
     *
     * @param integer Integer
     */
    public void insertaEncargado(Integer idUsu, Integer idIniciativa) throws
            Exception {
        Connection conn = null;
        String q = "INSERT INTO encargadoini (idiniciativa,id_usu)VALUES(?,?)";

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idIniciativa.intValue());
            ps.setInt(2, idUsu.intValue());
            ps.executeUpdate();

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * getEncargadoIni
     *
     * @param idIni Integer
     * @return String
     */
    public UsuarioVO getEncargadoIni(Integer idIni) {
        Connection conn = null;
        UsuarioVO u = new UsuarioVO();
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "select encargadoini.id_usu,nombre_usu,apellido_usu " +
                    "from encargadoini,usuario WHERE encargadoini.ID_USU=usuario.ID_USU " +
                    "AND encargadoini.IDINICIATIVA=?");
            ps.setInt(1, idIni.intValue());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                u.setIdUsu(new Integer(rs.getInt(1)));
                u.setNombreUsu(rs.getString(2) + " " + rs.getString(3));
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            return u;

        }

        return u;

    }

    /**
     * eliminaDocIni
     *
     * @param idDoc Integer
     * @return boolean
     */
    public boolean eliminaDocIni(Integer idDoc) throws Exception {
        Connection conn = null;
        String q = "DELETE DOCUMENTOINICIATIVA WHERE ID_DOCINI=?";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idDoc.intValue());
            ps.executeUpdate();

            conn.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * descargarDocumento
     *
     * @param idDoc Integer
     * @return DocumentodeListaProyVO
     */
    public DocumentodeListaProyVO descargarDocumento(Integer idDoc) {
        Connection conn;
        String q =
                "SELECT archivo,nombre_doc FROM DOCUMENTOINICIATIVA  WHERE id_docini=?";
        DocumentodeListaProyVO a = new DocumentodeListaProyVO();
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idDoc.intValue());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                BLOB blob = (BLOB) rs.getBlob(1);
                int len = (int) blob.length();
                byte[] b = blob.getBytes(1, len);
                a.setArchivo(b);
                a.setStr(rs.getString(2));
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;

        }
        return a;
    }

    /**
     * actualizarDatosIni
     *
     * @param proy DetalleProyectoVO
     */
    public void actualizarDatosIni(DetalleProyectoVO proy) throws Exception {

        int isA = 0;
        if (proy.getIsActivo()) {
            isA = 1;
        }

        Integer np = null;
        Connection conn = null;

        try {

            String q = " UPDATE iniciativa set nombre_ini='" + proy.getNomProy() +
                       "'" +
                       ",id_cliente=" + proy.getIdCliente() +
                       ",fecha_inicio=TO_DATE('" + sdf.format(proy.getFechaProy()) +
                       "','DD-MM-YYYY')"
                       + ",activo=" + isA;

            if (!proy.getDescripcion().equals("")) {
                q += " , descripcion_ini='" + proy.getDescripcion() + "'";
            }

            if (!proy.getFondosAnuales().equals("")) {
                q += " ,fondos_anuales='" + proy.getFondosAnuales() + "'";
            }

            if (proy.getFondosTotales() != null) {
                q += " ,fondos_totales=" + proy.getFondosTotales();
            }

            q += " WHERE idiniciativa=" + proy.getIdProy();

            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }

    /**
     * eliminarEquipo
     *  elimina el equipo de personas encargadas de una iniciativa de inversión
     *
     * @param integer Integer
     */
    public void eliminarEquipo(Integer idIni) throws Exception {

        Connection conn = null;
        String q = "DELETE equipoini WHERE IDINICIATIVA=?";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idIni.intValue());
            ps.executeUpdate();

            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }

    /**
     * eliminaEncargado
     *
     * @param integer Integer
     */
    public void eliminaEncargado(Integer idIni) throws Exception {
        Connection conn = null;
        String q = "DELETE encargadoIni WHERE IDINICIATIVA=?";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idIni.intValue());
            ps.executeUpdate();

            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }

    /**
     * regOT
     *
     * @param nuevaOT DetalleOTVO
     * @return Integer
     */
    public Integer regOT(DetalleOTVO nuevaOT) throws Exception {
        Integer nOT = null;
        String q =
                "INSERT INTO ORDENDETRABAJO (id_ot,idiniciativa,idestado,nombre_ot," +
                "fecha_ot,plazo_ejecucion,fecha_vencimiento,id_cliente,id_documento,id_tipoot";
        String q2 = " VALUES (s_ordendetrabajo.nextval," + nuevaOT.getIdProy() +
                    "," + nuevaOT.getIdEstado() + ",'" + nuevaOT.getNomOT() +
                    "'," +
                    "TO_DATE('" + sdf.format(nuevaOT.getFechaOT()) +
                    "','DD-MM-YYYY')," +
                    nuevaOT.getPlazo() + ",TO_DATE('" +
                    sdf.format(nuevaOT.getVencimiento()) + "','DD-MM-YYYY')" +
                    "," + nuevaOT.getCli().getIdCli() + "," +
                    nuevaOT.getDocumento().getIdDoc() + ",1";

        if (nuevaOT.getEP() != null) {
            q += " ,cobro_ep";
            q2 += " ,'" + nuevaOT.getEP() + "'";
        }

        q = q + " ) " + q2 + " )";

        Connection conn = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            if (ps.executeUpdate() != 0) {
                ps = conn.prepareCall(
                        "SELECT s_ordendetrabajo.currval FROM DUAL");
                ResultSet rs = ps.executeQuery();
                rs.next();
                nOT = new Integer(rs.getInt(1));
            }

            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

        return nOT;
    }

    /**
     * regEncOT
     *
     * @param nOT Integer
     * @param e Integer
     */
    public void regResponsableOT(Integer idOT, Integer idUsu) throws Exception {
        Connection conn = null;
        String q = "INSERT INTO responsable_ot (id_ot, id_usu) VALUES" +
                   "(?,?)";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idOT.intValue());
            ps.setInt(2, idUsu.intValue());

            ps.executeUpdate();

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }


    public DetalleProyectoVO getDetIniDesdeOT(Integer idOT) {

        Connection conn = null;
        DetalleProyectoVO pro = new DetalleProyectoVO();

        try {
            conn = ConnectionPool.getInstance().getConnection();

            String q = "SELECT idiniciativa FROM ordendetrabajo where id_ot=" +
                       idOT;
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int idini = rs.getInt(1);

            q = "SELECT iniciativa.idiniciativa, nombre_Ini," +
                " fecha_inicio,descripcion_ini,fondos_totales," +
                "fondos_anuales,iniciativa.activo,cliente.id_cliente," +
                " cliente.nombre_cliente,cliente.apellido_cliente" +
                " FROM iniciativa ,cliente " +
                "WHERE cliente.id_cliente=iniciativa.id_cliente" +
                "  AND iniciativa.idiniciativa=?";

            ps = conn.prepareCall(q);
            ps.setInt(1, idini);

            rs = ps.executeQuery();

            while (rs.next()) {
                pro.setIdProy(new Integer(rs.getInt(1)));
                pro.setNomProy(rs.getString(2));
                pro.setFechaProy(rs.getDate(3));
                pro.setDescripcion(rs.getString(4));
                pro.setFondosTotales(new Integer(rs.getInt(5)));
                pro.setFondosAnuales(rs.getString(6));
                pro.setIsActivo(new Integer(rs.getInt(7)));
                pro.setIdCliente(new Integer(rs.getInt(8)));
                pro.setNomCliente(rs.getString(9) + " " + rs.getString(10));
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pro;
    }

    /**
     * buscarNLO
     *
     * @param bus BusNLOVO
     * @return List
     */
    public List buscarNLO(BusNLOVO bus) throws Exception {
        Connection conn = null;
        List nlos = new LinkedList();
        try {
            conn = ConnectionPool.getInstance().getConnection();

            String q = "SELECT NLO.ID_NLO,NLO.DESCRIPCION_NLO,NLO.FECHA_NLO," +
                       " INICIATIVA.IDINICIATIVA,INICIATIVA.NOMBRE_INI" +
                       ",ORDENDETRABAJO.ID_OT,ORDENDETRABAJO.NOMBRE_OT,NLO.ID_DOCUMENTO," +
                       " datosdocumento.TIPO_DOCUMENTO,datosdocumento.CODIGO" +
                       " FROM NLO,INICIATIVA,OT_NLO,ORDENDETRABAJO,datosdocumento" +
                       " WHERE NLO.IDINICIATIVA=INICIATIVA.IDINICIATIVA" +
                       " AND NLO.ID_NLO=OT_NLO.ID_NLO(+)" +
                       " AND OT_NLO.ID_OT=ORDENDETRABAJO.ID_OT(+)" +
                       " AND DATOSDOCUMENTO.ID_DOCUMENTO=NLO.ID_DOCUMENTO";

            if (bus.getIdNLO() != null) {
                q += " and nlo.id_nlo=" + bus.getIdNLO();
            }

            if (!bus.getFechaNLO().equals("")) {
                q += " and nlo.fecha_nlo= TO_DATE('" + bus.getFechaNLO() +
                        "','DD-MM-YYYY')";
            }

            if (!bus.getPalClave().equals("")) {
                q += " and( UPPER(nlo.descripcion_nlo) LIKE UPPER('%" +
                        bus.getPalClave() + "%')";
                q += " or UPPER(iniciativa.nombre_ini) LIKE UPPER('%" +
                        bus.getPalClave() + "%')";
                q += " or UPPER(ORDENDETRABAJO.NOMBRE_OT) LIKE UPPER('%" +
                        bus.getPalClave() + "%'))";

            }

            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NLOVO n = new NLOVO();
                n.setId(new Integer(rs.getInt(1)));
                n.setStr(rs.getString(2));
                n.setFechaNLO(rs.getDate(3));
                n.setIdProy(new Integer(rs.getInt(4)));
                n.setNomProy(rs.getString(5));
                DetalleOTVO o = new DetalleOTVO();
                o.setIdOT(new Integer(rs.getInt(6)));
                o.setNomOT(rs.getString(7));
                n.setOT(o);
                DocumentoInVO d = new DocumentoInVO();
                d.setIdDoc(new Integer(rs.getInt(8)));
                d.setTipoDoc(rs.getString(9));
                d.setCodDoc(rs.getString(10));
                n.setDocumento(d);
                nlos.add(n);
            }

            conn.close();
            return nlos;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * regFechaFin
     *
     * @param integer Integer
     * @param date Date
     */
    public void regFechaFin(Integer idIni, java.util.Date fechaFin) throws
            Exception {
        Connection conn = null;
        try {
            String q = "UPDATE iniciativa SET fecha_fin= TO_DATE('" +
                       sdf.format(fechaFin) +
                       "','DD-MM-YYYY') WHERE idiniciativa=" + idIni;
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
     * cerrarIni
     *
     * @param integer Integer
     */
    public void cerrarIni(Integer idIni) throws Exception {
        Connection conn = null;
        try {
            String q = "UPDATE INICIATIVA SET activo=0 WHERE IDINICIATIVA=" +
                       idIni;
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
     * esDelEquipo
     *
     * @param idUsu Integer
     * @return boolean
     */
    public boolean esDelEquipo(Integer idUsu, Integer idIni) throws Exception {
        Connection conn = null;
        try {
            String q = "select * from equipoini where id_usu=" + idUsu
                       + "and idiniciativa=" + idIni;
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                conn.close();
                return true;
            }
            conn.close();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }


}

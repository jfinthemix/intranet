package proyecto_uoct.dao;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import oracle.sql.*;
import org.apache.struts.upload.*;
import proyecto_uoct.common.*;
import proyecto_uoct.common.util.*;
import proyecto_uoct.documentacion.VO.*;
import proyecto_uoct.reservas.util.*;
import proyecto_uoct.usuario.VO.*;

public class DocumentoDAO {
    public DocumentoDAO() {
    }

    private static DocumentoDAO dao = null;

    public static DocumentoDAO getInstance() {
        if (dao == null) {
            dao = new DocumentoDAO();
        }
        return dao;
    }


    Connection conn = null;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    /*
     obtiene una lista de los tipos de documentos existentes( entrantes o salientes).
     recibe el identificador del sentido que puede ser entrante(1) o saliente(2).
        cada componente de la lista es del tipo IdStrVO, o sea que esta compuesto por un entero y un string
     */

    public List getTiposDoc(Integer sentido) {
        List tiposdoc = new LinkedList();

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "SELECT id_tipo_doc, tipo_documento,eniniciativas,activo,id_sentido FROM tipodocumento WHERE id_sentido=? order by id_tipo_doc asc");
            ps.setInt(1, sentido.intValue());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TipoDocVO td = new TipoDocVO();
                td.setIdTipo(new Integer(rs.getInt(1)));
                td.setTipo(rs.getString(2));
                td.setEnIniciativas(rs.getInt(3));
                td.setIsActivo(rs.getInt(4));
                td.setIdSentido(new Integer(rs.getInt(5)));
                tiposdoc.add(td);
            }
            conn.close();

        } catch (Exception e) {
            System.out.println("no se consultaron los tipos de documento");
            e.printStackTrace();
        }

        return tiposdoc;

    }


    public List getTiposDocxPal(String palClave, Integer sentido,
                                Integer isActivo) {
        List tiposdoc = new LinkedList();

        String q = "SELECT id_tipo_doc, tipo_documento,eniniciativas," +
                   " activo,id_sentido " +
                   "FROM tipodocumento WHERE " +
                   "UPPER(tipo_documento) LIKE UPPER(?) " +
                   "AND id_sentido=? ";

        if (isActivo.intValue() == 1) {
            q += " AND activo=1";
        }

        q += " order by id_tipo_doc desc";

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setString(1, "%" + palClave + "%");
            ps.setInt(2, sentido.intValue());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TipoDocVO td = new TipoDocVO();
                td.setIdTipo(new Integer(rs.getInt(1)));
                td.setTipo(rs.getString(2));
                td.setEnIniciativas(rs.getInt(3));
                td.setIsActivo(rs.getInt(4));
                td.setIdSentido(new Integer(rs.getInt(5)));
                tiposdoc.add(td);
            }
            conn.close();

        } catch (Exception e) {
            System.out.println("no se consultaron los tipos de documento");
            e.printStackTrace();
        }

        return tiposdoc;

    }


    /* recibe el id de un documento para buscar todos los datos relacionados con
        éste y almacenarlos en un DocumentoVO. */

    public DocumentoVO getDetalleDoc(Integer idDoc) throws Exception {
        DocumentoVO eldoc = new DocumentoVO();
        String q =
                "SELECT id_documento,id_tipo_doc,codigo,fecha_ingreso_egreso," +
                "fecha_asignacion_respuesta,materia," +
                "tipo_documento,sentido_documento,nombre_cliente," +
                "apellido_cliente, nombre_entidad,id_cliente,DATOSDOCUMENTO.activo,DATOSDOCUMENTO.id_ent_ext " +
                " FROM ENTIDADEXTERNA,DATOSDOCUMENTO WHERE " +
                "DATOSDOCUMENTO.ID_ENT_EXT=ENTIDADEXTERNA.ID_ENT_EXT(+)" +
                " AND id_documento=?";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idDoc.intValue());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                eldoc.setIdDoc(new Integer(rs.getInt(1)));
                eldoc.setIdTipoDoc(new Integer(rs.getInt(2)));
                eldoc.setCodDoc(rs.getString(3));
                eldoc.setFechaio(rs.getDate(4));
                eldoc.setFechaDoc(rs.getDate(5));
                eldoc.setMateriaDoc(rs.getString(6));
                //eldoc.setResumen(rs.getString(7));
                eldoc.setTipoDoc(rs.getString(7));
                eldoc.setSentidoDoc(rs.getString(8));
                eldoc.setNomCli(rs.getString(9));
                eldoc.setApeCli(rs.getString(10));
                eldoc.setEntidad(rs.getString(11));
                eldoc.setIdCli(new Integer(rs.getInt(12)));
                eldoc.setIsActivo(new Integer(rs.getInt(13)));
                eldoc.setIdEntidad(new Integer(rs.getInt(14)));
                eldoc.setIdEstado(getEstadoDoc(eldoc.getIdDoc()));
                conn.close();
                eldoc.setDocsRelacionados(this.getDocsAnteriores(idDoc));
                eldoc.setDocsPosteriores(this.getDocsPosteriores(idDoc));
                eldoc.setResponsableDoc(this.getResponsables(idDoc));
                eldoc.setArchivos(this.getArchivosDeDoc(idDoc));
            }
            if (conn.isClosed() == false) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return eldoc;

    }

    /*
     * retorna una lista de nombres de los usuarios asociados como responsables
     * con el documento que recibe(id_doc,id_tipo_doc)
     */

    public List getResponsables(Integer idDoc) throws Exception {
        List resp = new LinkedList();

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "SELECT nombre_usu, nombre2_usu, apellido_usu, usuario.id_usu FROM usu_doc,usuario WHERE usuario.id_usu= usu_doc.id_usu AND id_documento=?");
            ps.setInt(1, idDoc.intValue());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UsuarioVO usu = new UsuarioVO();
                usu.setNombreUsu(rs.getString(1));
                usu.setNombreUsu2(rs.getString(2));
                usu.setApellUsu(rs.getString(3));
                usu.setIdUsu(new Integer(rs.getInt(4)));
                resp.add(usu);
            }
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return resp;
    }


    /**
     * getIdArchivosDeDoc
     * Entrega una lista del Identificador y nombre de los archivos relacionados
     * con el documento ingresado como parámetro.
     *
     * @param idDoc Integer
     * @return List
     * @throws Exception
     */
    public List getArchivosDeDoc(Integer idDoc) throws Exception {
        List archivos = new LinkedList();

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "SELECT idarchivo,nomarchivo  FROM ARCHIVODOC WHERE id_documento=?");
            ps.setInt(1, idDoc.intValue());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ArchivoDocVO arch = new ArchivoDocVO();
                arch.setIdArchivo(new Integer(rs.getInt(1)));
                arch.setNomArchivo(rs.getString(2));
                archivos.add(arch);
            }
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return archivos;

    }


    /**
     * getDocsRelacionados
     * Obtiene una lista de los documentos relacionados con el documento
     * ingresado como parametro.
     * Un documento esta relacionado con otro si estan registrados como par en
     * la tabla Doc_Doc de la BD, es decir, se devolverá una lista de documentos
     * que responden al ingresado como parametro, mas los documentos a los que
     * responde el documento ingresado como parametro
     *
     * @param idDoc Integer
     * @return List
     * @throws Exception
     */
    public List getDocsAnteriores(Integer idDoc) throws Exception {

        List docsrel = new LinkedList();

        String q2 =
                "SELECT DOC_DOC.id_documento_relacionado,codigo,tipo_documento" +
                " FROM DOC_DOC,datoslistadocs " +
                " WHERE DOC_DOC.id_documento_relacionado=datoslistadocs.ID_DOCUMENTO" +
                " AND doc_doc.id_documento=?";

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q2);
            ps.setInt(1, idDoc.intValue());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DocumentodeListaVO doc = new DocumentodeListaVO();
                doc.setIdDoc(new Integer(rs.getInt(1)));
                doc.setCodDoc(rs.getString(2));
                doc.setTipoDoc(rs.getString(3));
                docsrel.add(doc);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return docsrel;

    }

    public List getDocsPosteriores(Integer idDoc) throws Exception {

        List docsrel = new LinkedList();
        String q = "SELECT DOC_DOC.id_documento,codigo,tipo_documento " +
                   " FROM DOC_DOC ,datoslistadocs " +
                   " WHERE DOC_DOC.id_documento=datoslistadocs.ID_DOCUMENTO" +
                   " AND doc_doc.id_documento_relacionado=?";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idDoc.intValue());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DocumentodeListaVO doc = new DocumentodeListaVO();
                doc.setIdDoc(new Integer(rs.getInt(1)));
                doc.setCodDoc(rs.getString(2));
                doc.setTipoDoc(rs.getString(3));
                docsrel.add(doc);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return docsrel;

    }


    /**
     * buscarDocumentos
     *
     * @param busdocs BusDocsVO
     * @return List
     */
    public List buscarDocumentos(BusDocsVO busdocs) {
        List docs = new LinkedList();

        System.out.println(System.getProperty("file.encoding"));

        try {

            String q =
                    "SELECT  datoslistadocs.id_documento,datoslistadocs.id_tipo_doc," +
                    "datoslistadocs.tipo_documento,datoslistadocs.codigo, materia," +
                    "fecha_ingreso_egreso FROM  datoslistadocs ";

            if (busdocs.getEncargado().intValue() != 0) {
                q +=
                        ",usu_doc WHERE usu_doc.id_documento(+)= datoslistadocs.id_documento";
            }
            if (busdocs.getIdSentido() != null) {
                if (busdocs.getEncargado().intValue() == 0) {
                    q += " WHERE id_sentido=" +
                            busdocs.getIdSentido().toString();
                } else {
                    q += " AND id_sentido=" +
                            busdocs.getIdSentido().toString();
                }
            } else {
                if (busdocs.getEncargado().intValue() == 0) {
                    q += " WHERE datoslistadocs.id_tipo_doc=" +
                            busdocs.getIdTipoDoc() +
                            " AND UPPER(datoslistadocs.codigo) LIKE'%" +
                            busdocs.getCodigoDoc().toUpperCase() + "%'";
                } else {
                    q += " AND datoslistadocs.id_tipo_doc=" +
                            busdocs.getIdTipoDoc() +
                            " AND UPPER(datoslistadocs.codigo) LIKE'%" +
                            busdocs.getCodigoDoc().toUpperCase() + "%'";

                }
            }
            if (!busdocs.getMateria().equals("")) {
                q += " AND UPPER(materia) LIKE '%" +
                        busdocs.getMateria().toUpperCase() +
                        "%'";
            }

            if (busdocs.getFechaIni().compareTo("") != 0 &&
                busdocs.getFechaFin().compareTo("") != 0) {
                java.util.Date fecha_ini = Parse.newDate(busdocs.
                        getFechaIni());
                java.util.Date fecha_fin = Parse.newDate(busdocs.
                        getFechaFin());
                q += " AND fecha_ingreso_egreso >= TO_DATE('" +
                        sdf.format(fecha_ini) + "','DD-MM-YYYY')";
                q += " AND fecha_ingreso_egreso <= TO_DATE('" +
                        sdf.format(fecha_fin) + "','DD-MM-YYYY')";
            }

            if (busdocs.getEncargado().intValue() != 0) {
                q += " AND id_usu=" + busdocs.getEncargado();
            }

            if (!busdocs.getPalClave().equals("")) {
                q += " AND (UPPER(materia) LIKE UPPER('%" + busdocs.getPalClave() +
                        "%')" +
                        " or UPPER(NOMBRE_CLIENTE) LIKE UPPER('%" +
                        busdocs.getPalClave() + "%')" +
                        " OR UPPER (APELLIDO_CLIENTE) LIKE UPPER('%" +
                        busdocs.getPalClave() + "%')" +
                        " OR UPPER (MATERIA) LIKE UPPER('%" +
                        busdocs.getPalClave() + "%'))";
                //" OR UPPER (RESUMEN) LIKE UPPER('%"+busdocs.getPalClave()+"%'))";

            }

            if (!busdocs.getEnIniciativas()) {
                q += " AND eniniciativas=0";
            }

            if (busdocs.getEncargado().intValue() != 0) {
                q += " INTERSECT " + q;
            }

            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DocumentodeListaVO doc = new DocumentodeListaVO();
                doc.setIdDoc(new Integer(rs.getInt(1)));
                doc.setIdTipoDoc(new Integer(rs.getInt(2)));
                doc.setTipoDoc(rs.getString(3));
                doc.setCodDoc(rs.getString(4));
                doc.setMateriaDoc(rs.getString(5));
                doc.setFechaio(rs.getDate(6));
                doc.setIdEstado(getEstadoDoc(new Integer(rs.getInt(1))));

                docs.add(doc);

            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return docs;
    }

    //--------------------------------------------

    /**
     * insertDoc
     * Inserta en la tabla Documento de la bd un nuevo registro
     *
     * @param nuevodoc DocumentoInVO
     * @return Integer
     * @throws Exception
     */
    public Integer insertDoc(DocumentoInVO nuevodoc) throws Exception {
        String q = null;
        q = "INSERT  INTO documento (id_documento, id_tipo_doc,codigo," +
            " id_Cliente,fecha_ingreso_egreso, fecha_asignacion_respuesta," +
            "materia,activo) VALUES (s_documento.nextval,?,?,?,TO_DATE(?,'DD-MM-YYYY')," +
            "TO_DATE(?,'DD-MM-YYYY'),?,1)";
        Connection conn = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, nuevodoc.getIdTipoDoc().intValue());
            ps.setString(2, nuevodoc.getCodDoc());
            ps.setInt(3, nuevodoc.getIdRemitente().intValue());
            ps.setString(4, nuevodoc.getFechaIn());
            ps.setString(5, nuevodoc.getFechaDoc());
            ps.setString(6, nuevodoc.getMat());
            //ps.setString(7, nuevodoc.getResumen());

            ps.executeUpdate();

            q = "SELECT s_documento.currval FROM DUAL";
            ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int a = rs.getInt(1);

            conn.close();
            return new Integer(a);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }

    /**
     * insertDoc_Doc
     * Inserta en la tabla "doc_Doc" una relacion entre 2 documentos.
     * Esta relacion implica que un documento es Ant. de otro, es decir que
     * idDoc es Respuesta al documento idDocAnt.
     *
     * @param idDoc Integer
     * @param idDocResp Integer
     * @return boolean
     * @throws Exception
     */
    public boolean insertDoc_Doc(Integer idDoc, Integer idDocAnt) throws
            Exception {
        String q =
                "INSERT into doc_doc (id_documento,id_documento_relacionado)" +
                " VALUES (?,?)";

        Connection conn = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idDoc.intValue());
            ps.setInt(2, idDocAnt.intValue());
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * getNomTipoDoc
     * Consigue el nombre del id de tipo de documento entregado como parámetro
     *
     * @param id_tipo Integer
     * @return String
     */
    public String getNomTipoDoc(Integer id_tipo) {

        String q =
                "SELECT tipo_documento FROM tipodocumento WHERE id_tipo_doc=?";

        String eltipo = null;

        Connection conn = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_tipo.intValue());

            ResultSet rs = ps.executeQuery();
            rs.next();

            eltipo = rs.getString(1);
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return eltipo;

    }

    public boolean getEstadoDoc(Integer idDoc) {

        String q =
                "SELECT id_documento FROM doc_doc WHERE id_documento_relacionado=?";

        Connection conn = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idDoc.intValue());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                conn.close();
                return true;
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    /**
     * borrarDoc_Doc
     *
     * @param id_tipo_doc Integer
     * @param id_doc String
     * @return boolean
     */
    public boolean borrarDoc_Doc(Integer id_tipo_doc, String id_doc) {
        String q = "DELETE doc_doc WHERE id_tipo_doc=" + id_tipo_doc +
                   " and id_documento='" + id_doc +
                   "' or id_tipo_doc_resp=" +
                   id_tipo_doc +
                   " and id_documento_resp='" + id_doc + "'";
        Connection conn = null;
        boolean isBorrado = false;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            if (ps.executeUpdate() != 0) {
                isBorrado = true;
            }

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return isBorrado;

    }


    public boolean borrarDoc(Integer id_doc) throws Exception {
        String q = "DELETE documento WHERE  id_documento=" + id_doc;
        Connection conn = null;
        boolean isBorrado = false;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            if (ps.executeUpdate() != 0) {
                isBorrado = true;
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

        return isBorrado;
    }

    /**
     * borrarEIV_Doc
     * Elimina la relacion de un documento con un EIV
     *
     * @param id_doc Integer
     * @return boolean
     * @throws Exception
     */
    public boolean borrarEIV_Doc(Integer id_doc) throws
            Exception {

        String q = "DELETE eiv_doc WHERE id_documento=" + id_doc;
        Connection conn = null;
        boolean isBorrado = false;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            if (ps.executeUpdate() != 0) {
                isBorrado = true;
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

        return isBorrado;
    }

    /**
     * borrarEventoEIV
     *
     * @param id_tipo_doc Integer
     * @param id_doc String
     * @return boolean
     */
    public boolean borrarEventoEIV(Integer id_doc) throws Exception {
        String q = "DELETE EventoEIV WHERE id_documento=" + id_doc;
        Connection conn = null;
        boolean isBorrado = false;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            if (ps.executeUpdate() != 0) {
                isBorrado = true;
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

        return isBorrado;
    }


    /**
     * borrarNLO_Doc
     *
     * @param id_doc Integer
     * @return boolean
     * @throws Exception
     */
    public boolean borrarNLO_Doc(Integer id_doc) throws Exception {
        String q = "DELETE nlo WHERE id_documento=" + id_doc;
        Connection conn = null;
        boolean isBorrado = false;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            if (ps.executeUpdate() != 0) {
                isBorrado = true;
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

        return isBorrado;
    }

    /**
     * insertUsu_Doc
     */
    public void insertUsu_Doc(Integer idDoc, Integer idUsu) throws
            Exception {
        Connection conn = null;
        String q = "INSERT INTO usu_doc (id_documento,id_usu) VALUES (?,?)";
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idDoc.intValue());
            ps.setInt(2, idUsu.intValue());
            ps.executeUpdate();
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }

    /**
     * insertaDocFile
     *
     * @param idDoc Integer
     * @param formFile FormFile
     */
    public boolean insertaDocFile(Integer idDoc, FormFile archivo) throws
            Exception {

        Connection conn;
        Archivo a = Archivo.getInstance();
        File ffDoc = null;
        try {
            ffDoc = a.formFileToFile(archivo);

            String q = "INSERT INTO archivodoc" +
                       " (idarchivo,id_documento,nomArchivo,archivo) values" +
                       " (s_archivodoc.nextval,?,?, empty_blob())  ";
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idDoc.intValue());
            ps.setString(2, archivo.getFileName());
            ps.executeUpdate();

            q = "SELECT s_archivodoc.currval from dual";
            ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int idnew = rs.getInt(1);

            q = "SELECT archivo FROM archivodoc WHERE idarchivo=? ";
            ps = conn.prepareCall(q);
            ps.setInt(1, idnew);
            rs = ps.executeQuery();
            rs.next();
            BLOB blob = (BLOB) rs.getBlob("archivo");
            FileInputStream instream = new FileInputStream(ffDoc);
            OutputStream outstream = blob.setBinaryStream(0); //blob.getBinaryOutputStream();
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
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * getArchivoDoc
     *
     * @param idArchivo Integer
     * @return ArchivoDocVO
     */
    public ArchivoDocVO getArchivoDoc(Integer idArchivo) {
        Connection conn;
        String q =
                "SELECT archivo,nomArchivo FROM archivodoc  WHERE idArchivo=?";
        ArchivoDocVO a = new ArchivoDocVO();
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idArchivo.intValue());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                BLOB blob = (BLOB) rs.getBlob(1);
                int len = (int) blob.length();
                byte[] b = blob.getBytes(1, len);
                a.setArchivo(b);
                a.setNomArchivo(rs.getString(2));
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;

        }
        return a;
    }


    /**
     * borrarArchivoDoc
     *
     * @param idDoc Integer
     * @return boolean
     * @throws Exception
     */
    public boolean borrarArchivoDoc(Integer idDoc) throws Exception {
        Connection conn;
        String q =
                "DELETE archivodoc  WHERE id_documento=?";
        ArchivoDocVO a = new ArchivoDocVO();
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
     * borrarArchivoDocConIdArchivo
     *
     * @param idArchivo Integer
     * @return boolean
     * @throws Exception
     */
    public boolean borrarArchivoDocConIdArchivo(Integer idArchivo) throws
            Exception {
        Connection conn;
        String q =
                "DELETE archivodoc  WHERE idArchivo=?";
        ArchivoDocVO a = new ArchivoDocVO();
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, idArchivo.intValue());
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;

        }
    }

    /**
     * borrarUsu_Doc
     *
     * @param integer Integer
     */
    public void borrarUsu_Doc(Integer idDoc) throws Exception {
        try {
            String q = "DELETE usu_doc WHERE id_documento=" + idDoc;
            Connection conn = null;
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
     * borrarDoc_Doc
     * Elimina los registros de DOC_DOC donde el documento recibido como
     * parametro es el principal(no donde es el documento Anterior).
     *
     * @param idDoc Integer
     */
    public void borrarDoc_Doc(Integer idDoc) throws Exception {
        try {
            String q = "DELETE doc_doc WHERE id_documento=" + idDoc;
            Connection conn = null;
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
     * borrarDoc_Doc_Ant
     * Elimina los registros de DOC_DOC donde el documento recibido como
     * parametro es el documento anterior(ANT.).
     *
     * @param idDoc Integer
     */
    public void borrarDoc_Doc_Ant(Integer idDoc) throws Exception {
        try {
            String q = "DELETE doc_doc WHERE id_documento_relacionado=" + idDoc;
            Connection conn = null;
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
     * actualizarDoc
     *
     * @param doc DocumentoInVO
     */
    public void actualizarDoc(DocumentoInVO doc) throws Exception {

        try {
            String q = "UPDATE documento SET " +
                       " id_cliente=" + doc.getIdRemitente() +
                       ",fecha_ingreso_egreso=TO_DATE('" + doc.getFechaIn() +
                       "','DD-MM-YYYY')" +
                       ",fecha_asignacion_respuesta=TO_DATE('" +
                       doc.getFechaDoc() + "','DD-MM-YYYY')" +
                       ",materia='" + doc.getMat() + "'" +
                       //",resumen='" + doc.getResumen() + "'" +
                       " WHERE id_documento=" + doc.getIdDoc();
            Connection conn = null;
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
     * insertaTipoDoc
     *inserta en la BD un nuevo tipo de documento, indicando su codigo, el sentido(1->entrante,2->saliente)
     *y si es usado en el modulo de iniciativas de inversión.
     * Ademas genera una secuencia en la BD llamada S_TD_<codigovalidado>.
     *
     * @param codigo String
     * @param idSentido Integer
     * @param isEnIniciativas boolean
     */
    public void insertaTipoDoc(String codigo, Integer idSentido,
                               Integer isEnIniciativas) throws Exception {
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            String q = "INSERT INTO tipodocumento" +
                       " (id_tipo_doc,tipo_documento,id_sentido," +
                       "eniniciativas,activo)" +
                       " values(s_tipodocumento.nextval,'" + codigo + "'," +
                       idSentido.toString() + "," + isEnIniciativas.toString() +
                       ",1)";
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            conn.close();
            e.printStackTrace();
            throw e;
        }
    }


    public void insertaSecuenciaTipoDoc(String codigo) throws Exception {
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();

            UtilString us = new UtilString();
            codigo = us.validaStringParaBD(codigo);

            String q = "CREATE SEQUENCE S_TD_" + codigo +
                       " START WITH 1" +
                       " MAXVALUE 999999999999999999999999999" +
                       " MINVALUE 1" +
                       " NOCYCLE" +
                       " nocache" +
                       " NOORDER";
            PreparedStatement ps = conn.prepareCall(q);
            ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            conn.close();
            e.printStackTrace();

            throw e;

        }
    }


    /**
     * desactivarTipoDoc
     *
     * @param idTipoDoc Integer
     */
    public void desactivarTipoDoc(Integer idTipoDoc) throws Exception {
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            String q = "UPDATE tipodocumento SET activo=0 WHERE id_tipo_doc=" +
                       idTipoDoc.toString();
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }


    /**
     * activarTipoDoc
     *
     * @param idTipoDoc Integer
     * @throws Exception
     */
    public void activarTipoDoc(Integer idTipoDoc) throws Exception {
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            String q = "UPDATE tipodocumento SET activo=1 WHERE id_tipo_doc=" +
                       idTipoDoc.toString();
            PreparedStatement ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    /**
     * reservarDoc
     *
     * @param tipoDoc String
     * @return Integer
     */
    public Integer reservarDoc(String tipoDoc, Integer idUsu, Integer idTipoDoc) throws
            Exception {
        try {
            UtilString u = new UtilString();
            tipoDoc = u.validaStringParaBD(tipoDoc);
            String q = "SELECT s_td_" + tipoDoc + ".nextval FROM DUAL";
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Integer i = new Integer(rs.getInt(1));

            String hoy = sdf.format(new java.util.Date());
            q =
                    "INSERT INTO reservacod_doc (id_usu,id_tipo_Doc,codigo,fecha_reg)" +
                    "values (" + idUsu + "," + idTipoDoc + ",'" + i.toString() +
                    "',TO_DATE('" + hoy + "','DD-MM-YYYY'))";
            ps = conn.prepareCall(q);
            ps.executeUpdate();
            conn.close();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    public boolean estaEnBitacora(Integer idDoc) {
        String q = "SELECT id_evento_eiv from EventoEIV WHERE id_documento=" + idDoc.toString();
        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                conn.close();
                return true;
            } else {
                conn.close();
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * buscarReservasDoc
     *
     * @param bus BusDocsVO
     * @return List
     */
    public List buscarReservasDoc(BusDocsVO bus) throws Exception {
        List docs = new LinkedList();
        try {

            String q = "SELECT DOCSRESERVADOSVIEW.ID_USU," +
                       "usuario.NOMBRE_USU,usuario.NOMBRE2_USU,usuario.APELLIDO_USU," +
                       "DOCSRESERVADOSVIEW.ID_TIPO_DOC,tipodocumento.tipo_documento," +
                       "DOCSRESERVADOSVIEW.CODIGO,DOCSRESERVADOSVIEW.FECHA_REG,DOCSRESERVADOSVIEW.ID_DOCUMENTO" +
                       " FROM DOCSRESERVADOSVIEW,USUARIO,TIPODOCUMENTO" +
                       " WHERE DOCSRESERVADOSVIEW.id_usu=USUARIO.ID_USU" +
                       " AND DOCSRESERVADOSVIEW.ID_TIPO_DOC" +
                       " = TIPODOCUMENTO.id_tipo_doc";

            boolean flag = false;
            if (bus.getEncargado() != null) {
                q += " AND DOCSRESERVADOSVIEW.ID_USU=" + bus.getEncargado();
                flag = true;
            }

            if (bus.getFechaIni() != null &&
                bus.getFechaFin() != null) {
                if (flag) {
                    q += " AND fecha_reg >= TO_DATE('" +
                            bus.getFechaIni() + "','DD-MM-YYYY')";
                    q += " AND fecha_reg <= TO_DATE('" +
                            bus.getFechaFin() + "','DD-MM-YYYY')";
                } else {
                    q += " AND fecha_reg >= TO_DATE('" +
                            bus.getFechaIni() + "','DD-MM-YYYY')";
                    q += " AND fecha_reg <= TO_DATE('" +
                            bus.getFechaFin() + "','DD-MM-YYYY')";
                    flag = true;
                }
            }

            if (bus.getIdTipoDoc() != null) {
                if (flag) {
                    q += " AND DOCSRESERVADOSVIEW.ID_TIPO_DOC=" +
                            bus.getIdTipoDoc();
                } else {
                    q += " AND DOCSRESERVADOSVIEW.ID_TIPO_DOC=" +
                            bus.getIdTipoDoc();
                    flag = true;
                }
            }

            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DocReservadoVO doc = new DocReservadoVO();
                UsuarioVO u = new UsuarioVO();
                u.setIdUsu(new Integer(rs.getInt(1)));
                u.setNombreUsu(rs.getString(2));
                u.setNombreUsu2(rs.getString(3));
                u.setApellUsu(rs.getString(4));
                doc.setUsu(u);
                doc.setIdTipoDoc(new Integer(rs.getInt(5)));
                doc.setTipoDoc(rs.getString(6));
                doc.setCodigo(rs.getString(7));
                doc.setFechaReg(sdf.format(rs.getDate(8)));
                if (rs.getInt(9) != 0) {
                    doc.setIdDoc(new Integer(rs.getInt(9)));
                }
                docs.add(doc);

            }
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return docs;
    }

}

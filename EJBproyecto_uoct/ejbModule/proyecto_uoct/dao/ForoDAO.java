package proyecto_uoct.dao;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import oracle.sql.*;
import proyecto_uoct.common.*;
import proyecto_uoct.common.util.*;
import proyecto_uoct.foro.VO.*;


public class ForoDAO {

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public ForoDAO() {
    }


    private static ForoDAO dao = null;

    public static ForoDAO getInstance() {
        if (dao == null) {
            dao = new ForoDAO();
        }
        return dao;
    }


    public List getListaTemas() throws Exception {

        List listaTemas = new LinkedList();

        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(
                    "SELECT id_tema,tema FROM TEMAFORO");
            ResultSet rs = ps.executeQuery();

            TemasVO tema = null;

            while (rs.next()) {
                tema = new TemasVO();
                tema.setIdTema(new Integer(rs.getInt(1)));
                tema.setTema(rs.getString(2));

                listaTemas.add(tema);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaTemas;
    }

    public void agregaTema(String nuevoTema) throws Exception {

        Connection conn = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = conn.prepareCall(
                "insert into temaforo (id_tema,tema) values (s_temaforo.nextval, ?)");
        ps.setString(1, nuevoTema);
        ps.executeUpdate();
        ps.close();
        conn.close();

    }

    public void borrarTema(int id_tema) throws Exception {

        Connection conn = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = conn.prepareCall(
                "delete from temaforo where id_tema=?");
        ps.setInt(1, id_tema);
        ps.executeUpdate();
        ps.close();
        conn.close();

    }


    public List listaForosTema(Integer id_tema) {

        List lista = new LinkedList();
        String s;
        int id_tem = id_tema.intValue();

        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = null;

            ps = conn.prepareCall("select foro.ID_FORO,foro.TITULO,foro.FECHA_INI,temaforo.TEMA from foro,temaforo"+
                                  " WHERE foro.ID_TEMA=temaforo.ID_TEMA AND temaforo.ID_TEMA=?");
            ps.setInt(1, id_tem);
            ResultSet rs = ps.executeQuery();

            ForodeListaVO forodelista = null;

            while (rs.next()) {
                forodelista = new ForodeListaVO();
                forodelista.setIdForo(new Integer(rs.getInt(1)));
                forodelista.setTituloForo(rs.getString(2));
                forodelista.setFechaForo(rs.getDate(3));
                forodelista.setTemaForo(rs.getString(4));
                lista.add(forodelista);
            }

            conn.close();

        } catch (SQLException ex2) {
            System.out.println("no se realizó consulta de foros por tema");
        }

        return lista;

    }

    public List getForosxFecha(String fecha_s) {
        List listaForos = new LinkedList();

        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = null;

            ps = conn.prepareCall("select foro.ID_FORO,foro.TITULO,foro.FECHA_INI,temaforo.TEMA from foro,temaforo "+
                                  "WHERE foro.ID_TEMA=temaforo.ID_TEMA AND foro.FECHA_INI=TO_DATE(?,'DD/MM/YYYY') order by foro.FECHA_INI desc");
            ps.setString(1, fecha_s);
            ResultSet rs = ps.executeQuery();

            ForodeListaVO forodelista = null;

            while (rs.next()) {
                forodelista = new ForodeListaVO();
                forodelista.setIdForo(new Integer(rs.getInt(1)));
                forodelista.setTituloForo(rs.getString(2));
                forodelista.setFechaForo(rs.getDate(3));
                forodelista.setTemaForo(rs.getString(4));
                listaForos.add(forodelista);
            }

            conn.close();

        } catch (SQLException ex2) {
            System.out.println("no se realizó consulta de foros por fecha");
        }

        return listaForos;
    }

    public List getForosxpal(String pal) {

        List listaForos = new LinkedList();
        String pal2 = "%" + pal + "%";

        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = null;

            ps = conn.prepareStatement("select foro.ID_FORO,foro.TITULO,foro.FECHA_INI,temaforo.TEMA from foro,temaforo "+
                                       "WHERE foro.ID_TEMA=temaforo.ID_TEMA AND foro.descripcionforo "+
                                       "LIKE ? or foro.titulo like ? order by foro.FECHA_INI desc");
            ps.setString(1, pal2);
            ps.setString(2, pal2);
            ResultSet rs = ps.executeQuery();

            ForodeListaVO forodelista = null;

            while (rs.next()) {
                forodelista = new ForodeListaVO();
                forodelista.setIdForo(new Integer(rs.getInt(1)));
                forodelista.setTituloForo(rs.getString(2));
                forodelista.setFechaForo(rs.getDate(3));
                forodelista.setTemaForo(rs.getString(4));
                listaForos.add(forodelista);
            }

            conn.close();

        } catch (SQLException ex2) {
            ex2.printStackTrace();
            //System.out.println(
            //      "no se realizó consulta de foros por palabra clave");
        }

        return listaForos;

    }

    public DetForoVO getDetForo(Integer id_foro) {
        DetForoVO detForo = new DetForoVO();

        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = null;

            ps = conn.prepareCall("select foro.ID_FORO,foro.TITULO,foro.FECHA_INI,foro.DESCRIPCIONFORO,temaforo.TEMA from"+
                                  " foro,temaforo WHERE foro.ID_TEMA=temaforo.ID_TEMA AND foro.id_foro=?");
            ps.setInt(1, id_foro.intValue());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                detForo.setIdForo(new Integer(rs.getInt(1)));
                detForo.setTitForo(rs.getString(2));
                detForo.setFechaIniForo(rs.getDate(3));
                detForo.setDescForo(rs.getString(4));
                detForo.setTemaForo(rs.getString(5));
                rs.close();
            }

            ps = conn.prepareCall("select usuario.id_usu,usuario.nombre_usu,usuario.nombre2_usu, usuario.apellido_usu "+
                                  "from foro, usuario WHERE foro.id_usu=usuario.id_usu AND foro.id_foro=?");
            ps.setInt(1, id_foro.intValue());
            ResultSet rs1 = ps.executeQuery();
            if (rs1.next()) {
                String nom1 = rs1.getString(2);
                String nom2 = rs1.getString(3);
                String ap1 = rs1.getString(4);
                if (nom2 == null) {
                    nom1 = nom1 + " " + ap1;
                } else {
                    nom1 = nom1 + " " + nom2 + " " + ap1;
                }
                detForo.setAdminForo(nom1);
                conn.close();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return detForo;

    }

    public List getDocsForo(Integer id_foro) {
        List listadocs = new LinkedList();

        Connection conn = null;
        DocForoVO docsVO = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = null;

            ps = conn.prepareCall("select documentoforo.TITULO_DOC, documentoforo.id_doc_foro from documentoforo where id_foro=?");
            ps.setInt(1, id_foro.intValue());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                docsVO = new DocForoVO();
                docsVO.setIdDocForo(new Integer(rs.getInt(2)));
                docsVO.setTitulodoc(rs.getString(1));
                listadocs.add(docsVO);
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("no se consultaron los documentos");
            ex.printStackTrace();
        }

        return listadocs;
    }

    public List getPost(Integer id_foro) {
        List listapost = new LinkedList();
        PostUsuVO postVO = null;

        Connection conn = null;
        String q = "select post.descripcion, usuario.nombre_usu," +
                   "usuario.nombre2_usu,usuario.apellido_usu,fecha_post,id_post "+
                   "from usuario,post WHERE usuario.ID_USU=post.id_usu "+
                   "AND post.id_foro=? order by fecha_post asc,id_post asc";

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_foro.intValue());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                postVO = new PostUsuVO();
                postVO.setPost(rs.getString(1));
                if (rs.getString(3) != null) {
                    postVO.setUsu(rs.getString(2) + " " + rs.getString(3) + " " +
                                  rs.getString(4));
                } else {
                    postVO.setUsu(rs.getString(2) + " " + rs.getString(4));
                }

                postVO.setFechaPost(rs.getDate(5));
                postVO.setIdPost(new Integer(rs.getInt(6)));
                listapost.add(postVO);

            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("no se consultaron los post");
            ex.printStackTrace();
        }

        return listapost;

    }

// funcion para ingresar un post a un foro existente
    public void ingresarPost(Integer id_foro, Integer id_usu, String comentario) {

        Connection conn = null;
        java.util.Date hoy = new java.util.Date();
        String s_hoy = sdf.format(hoy);

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = null;

            ps = conn.prepareCall("insert into post (post.id_post,post.descripcion,post.ID_FORO,post.ID_USU,fecha_post) values (s_post.nextval, ?,?,?,TO_DATE(?,'DD-MM-YYYY'))");
            ps.setString(1, comentario);
            ps.setInt(2, id_foro.intValue());
            ps.setInt(3, id_usu.intValue());
            ps.setString(4, s_hoy);

            ps.executeUpdate();

            conn.close();

        } catch (SQLException ex3) {
            System.out.print("no se realizo insert de post");
            ex3.printStackTrace();

        }

    }


    public boolean actDescForo(Integer id_foro, String desc) throws Exception {

        Connection conn = null;
        boolean resp = false;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = null;

            ps = conn.prepareCall(
                    "update FORO set foro.descripcionforo=? where id_foro=?");
            ps.setString(1, desc);
            ps.setInt(2, id_foro.intValue());

            ps.executeUpdate();
            resp = true;
            conn.close();

        } catch (SQLException ex3) {
            ex3.printStackTrace();
            throw ex3;

        }
        return resp;

    }


    public boolean borrarDocForo(Integer id_doc) throws Exception {

        Connection conn = null;
        boolean resp = false;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = null;

            ps = conn.prepareCall(
                    "delete documentoforo where id_doc_foro=?");
            ps.setInt(1, id_doc.intValue());

            if (ps.executeUpdate() != 0) {
                resp = true;
            }
            conn.close();

        } catch (Exception ex3) {
            ex3.printStackTrace();
            throw ex3;

        }
        return resp;

    }



    public boolean borrarAllDocsForo(Integer idForo) throws Exception {

            Connection conn = null;
            boolean resp = false;

            try {
                conn = ConnectionPool.getInstance().getConnection();
                PreparedStatement ps = null;

                ps = conn.prepareCall(
                        "delete documentoforo where id_Foro=?");
                ps.setInt(1, idForo.intValue());

                if (ps.executeUpdate() != 0) {
                    resp = true;
                }
                conn.close();

            } catch (Exception ex3) {
                ex3.printStackTrace();
                throw ex3;

            }
            return resp;

    }




    public int agregarForo(DetForoVO nuevof) throws Exception {

        Connection conn = null;

        int id_newforo = 0;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = null;
            PreparedStatement ps2 = null;
            PreparedStatement ps3 = null;

            ps = conn.prepareCall("insert into foro (foro.ID_FORO,foro.ID_TEMA ,foro.TITULO,foro.DESCRIPCIONFORO,foro.FECHA_INI,foro.id_usu) values( s_foro.nextval,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?)");
            ps.setInt(1, nuevof.getIdTema().intValue());
            ps.setString(2, nuevof.getTitForo());
            ps.setString(3, nuevof.getDescForo());
            ps.setString(4, nuevof.getFechaIni_s());
            ps.setInt(5, nuevof.getIdUsu().intValue());
            ps.executeUpdate();

            ps2 = conn.prepareCall("select s_foro.currval from dual");
            ResultSet rs2 = ps2.executeQuery();

            if (rs2.next()) {
                id_newforo = rs2.getInt(1);
            }

            ps3 = conn.prepareCall("insert into post (post.ID_POST, post.descripcion, post.id_foro,post.id_usu,fecha_post) values (s_post.nextval, ?,?,?,TO_DATE(?,'DD-MM-YYYY'))");
            ps3.setString(1, nuevof.getPost());
            ps3.setInt(2, id_newforo);
            ps3.setInt(3, nuevof.getIdUsu().intValue());
            ps3.setString(4, nuevof.getFechaIni_s());
            ps3.executeUpdate();

            conn.close();

        } catch (Exception ex3) {

            ex3.printStackTrace();
            throw ex3;
        }
        return id_newforo;
    }

    /**
     * getAdminForo
     *
     * @param id_foro Integer
     * @return Integer
     */
    public Integer getAdminForo(Integer id_foro) {
        Connection conn = null;
        Integer id_ad = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = null;

            ps = conn.prepareCall(
                    "Select id_usu FROM foro where foro.id_foro=?");
            ps.setInt(1, id_foro.intValue());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id_ad = new Integer(rs.getInt(1));
            }
            conn.close();

        } catch (SQLException ex3) {
            System.out.print("no se realizo insert del foro");
            ex3.printStackTrace();

        }
        return id_ad;
    }

    /**
     * borrarForos
     *
     * @param id_tema int
     */
    public void borrarForos(int id_tema) throws Exception {

        String q = "select id_foro from foro where id_tema =?";
        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_tema);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id_foro = rs.getInt(1);
                this.borrarPost(id_foro);
                this.borrarunforo(id_foro);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    public void borrarPost(int id_foro) throws Exception {

        String q = "DELETE post where id_foro =?";
        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_foro);
            ps.executeUpdate();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public void borrarunforo(int id_foro) throws Exception {

        String q = "DELETE foro where id_foro =?";
        Connection conn = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, id_foro);
            ps.executeUpdate();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    /**
     * buscarForos
     *
     * @param palClave String
     * @param fecha String
     * @param idTema Integer
     * @return List
     */
    public List buscarForos(String palClave, String fecha, Integer idTema) {
        try {

            List foros = new LinkedList();
            String q = "select foro.ID_FORO,foro.TITULO,foro.FECHA_INI,temaforo.TEMA "+
                       "from foro,temaforo WHERE foro.ID_TEMA=temaforo.ID_TEMA";
            boolean f = false;
            if (!"".equals(palClave)) {
                if (!f) {
                    q += " AND UPPER(foro.descripcionforo) like UPPER('%" +
                            palClave + "%')" +
                            " or UPPER(foro.titulo) like UPPER('%" + palClave +
                            "%') " +
                            " OR UPPER(temaforo.TEMA) like UPPER('%" +
                            palClave + "%')";
                    f = true;
                } else {
                    q += " AND UPPER(foro.descripcionforo) like UPPER('%" +
                            palClave + "%')" +
                            " or UPPER(foro.titulo) like UPPER('%" + palClave +
                            "%') " +
                            " OR UPPER(foro.descripcionforo) like UPPER('%" +
                            palClave + "%')";
                }
            }

            if (!"".equals(fecha)) {
                if (!f) {
                    q += " AND fecha_ini= TO_DATE('" + fecha +
                            "','DD-MM-YYYY')";
                    f = true;
                } else {
                    q += " AND fecha_ini= TO_DATE('" + fecha +
                            "','DD-MM-YYYY')";
                }
            }

            if (idTema != null) {
                if (!f) {
                    q += " AND temaforo.ID_TEMA=" + idTema;
                    f = true;
                } else {
                    q += " and temaforo.ID_TEMA=" + idTema;
                }
            }

            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ForodeListaVO forodelista = new ForodeListaVO();
                forodelista.setIdForo(new Integer(rs.getInt(1)));
                forodelista.setTituloForo(rs.getString(2));
                forodelista.setFechaForo(rs.getDate(3));
                forodelista.setTemaForo(rs.getString(4));
                foros.add(forodelista);
            }

            conn.close();
            return foros;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * registrarDocForo
     *
     * @param docForo DocForoVO
     * @return boolean
     */
    public boolean registrarDocForo(DocForoVO docForo) {
        Connection conn;
         Archivo a = Archivo.getInstance();
         File ffInforme = null;
         try {
             ffInforme = a.formFileToFile(docForo.getDocumento());
         } catch (Exception ex) {
             return false;
         }

         String q = "INSERT INTO documentoforo (id_doc_foro,id_foro,titulo_doc,documento_foro)"+
                    "values (s_documentoforo.nextval,"+docForo.getIdForo()+" ,'"+
                    docForo.getDocumento().getFileName()+"' ,empty_blob())";
         try {
             conn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = conn.prepareCall(q);
             ps.executeUpdate();

             q = "SELECT s_documentoforo.currval FROM dual";
             ps = conn.prepareCall(q);
             ResultSet rs = ps.executeQuery();
             rs.next();
             int nuevo = rs.getInt(1);

             q = "SELECT documento_foro FROM documentoforo WHERE id_doc_foro=? ";
             ps = conn.prepareCall(q);
             ps.setInt(1, nuevo);
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
         } catch (Exception e) {
             e.printStackTrace();
             return false;
        }
    }

    /**
     * getDocForo
     *
     * @param idDocForo Integer
     * @return DocForoVO
     */
    public DocForoVO getDocForo(Integer idDocForo) {
        Connection conn;
         String q =
                 "SELECT documento_foro,titulo_doc FROM documentoforo  WHERE id_doc_foro=?";
         DocForoVO a = new DocForoVO();
         try {
             conn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = conn.prepareCall(q);
             ps.setInt(1, idDocForo.intValue());
             ResultSet rs = ps.executeQuery();
             if (rs.next()) {
                 BLOB blob = (BLOB) rs.getBlob(1);
                 int len = (int) blob.length();
                 byte[] b = blob.getBytes(1, len);
                 a.setDocumentoByte(b);
                 a.setTitulodoc(rs.getString(2));
             }
             conn.close();
         } catch (Exception ex) {
             ex.printStackTrace();
             return null;

        }
        return a;    }


}

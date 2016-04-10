package proyecto_uoct.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import oracle.sql.BLOB;
import proyecto_uoct.common.ConnectionPool;
import proyecto_uoct.common.util.Archivo;
import proyecto_uoct.documentacion.VO.ArchivoDocVO;
import proyecto_uoct.infoyrep.VO.ArchivoInfoVO;

public class InfoInstitDAO {
    public InfoInstitDAO() {
    }

    private static InfoInstitDAO dao = null;

    public static InfoInstitDAO getInstance() {
	if (dao == null) {
	    dao = new InfoInstitDAO();
	}
	return dao;
    }

    /**
     * upArchivo
     *
     * @param arch
     *            ArchivoInfoVO
     * @return boolean
     */
    public boolean upArchivo(ArchivoInfoVO arch) throws Exception {

	Connection conn;
	Archivo a = Archivo.getInstance();
	File ffInforme = null;
	try {
	    ffInforme = a.formFileToFile(arch.getLaData());
	} catch (Exception ex) {
	    throw ex;
	}

	String q = "INSERT INTO informacioninstitucional (id_info_instit,descripcion,nombre_archivo,archivo)"
		+ " values (s_informacioninsitucional.nextval,'" + arch.getDescripcion() + "','"
		+ arch.getLaData().getFileName() + "',empty_blob())";
	try {
	    conn = ConnectionPool.getInstance().getConnection();
	    PreparedStatement ps = conn.prepareStatement(q);
	    ps.executeUpdate();

	    q = "SELECT s_informacioninsitucional.currval FROM dual";
	    ps = conn.prepareCall(q);
	    ResultSet rs = ps.executeQuery();
	    rs.next();
	    int nuevo = rs.getInt(1);

	    q = "SELECT archivo FROM informacioninstitucional WHERE id_info_instit=" + nuevo;
	    ps = conn.prepareCall(q);
	    rs = ps.executeQuery();
	    rs.next();

	    // BLOB blob = (BLOB) rs.getBlob(1);

	    /*
	     * FileInputStream instream = new FileInputStream(ffInforme);
	     * OutputStream outstream = blob.setBinaryStream(0); int size =
	     * blob.getBufferSize(); byte[] buffer = new byte[size]; int length
	     * = -1; while ((length = instream.read(buffer)) != -1) {
	     * outstream.write(buffer, 0, length); } instream.close();
	     * outstream.close();
	     */

	    FileInputStream instream = new FileInputStream(arch.getArchivoFile());
	    OutputStream outstream = rs.getBlob(1).setBinaryStream(0);
	    // int size = (int) rs.getBlob(1).length();
	    int size = (int) arch.getArchivo().length;
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
     * listaArchivos entrega una lista de los archivos disponibles
     *
     * @return List
     */
    public List listaArchivos() {

	List lista = new LinkedList();
	Connection conn = null;
	String que = "SELECT id_info_instit,nombre_archivo,descripcion FROM informacioninstitucional";
	try {
	    conn = ConnectionPool.getInstance().getConnection();
	    PreparedStatement ps = conn.prepareStatement(que);

	    ResultSet rs = ps.executeQuery();

	    while (rs.next()) {
		ArchivoInfoVO ar = new ArchivoInfoVO();
		ar.setIdFile(new Integer(rs.getInt(1)));
		ar.setNomArchivo(rs.getString(2));
		ar.setDescripcion(rs.getString(3));
		lista.add(ar);
	    }
	    conn.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return lista;
    }

    public ArchivoDocVO getArchivoDoc(Integer idArchivo) {
	Connection conn;
	String q = "SELECT archivo,nombre_archivo FROM informacioninstitucional  WHERE id_info_instit=?";
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

    public boolean eliminarArchivo(Integer idArchivo) throws Exception {
	try {
	    Connection conn;
	    String q = "Delete informacioninstitucional  WHERE id_info_instit=?";
	    ArchivoDocVO a = new ArchivoDocVO();
	    conn = ConnectionPool.getInstance().getConnection();
	    PreparedStatement ps = conn.prepareCall(q);
	    ps.setInt(1, idArchivo.intValue());
	    ps.executeUpdate();
	    conn.close();
	    return true;
	} catch (Exception e) {
	    e.printStackTrace();
	    throw e;
	}
    }

}

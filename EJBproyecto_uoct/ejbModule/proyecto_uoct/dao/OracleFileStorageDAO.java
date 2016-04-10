package proyecto_uoct.dao;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.OutputStream;

import java.sql.Blob;
import java.util.List;
import java.sql.PreparedStatement;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;

public class OracleFileStorageDAO {
   public OracleFileStorageDAO() {
  }

/*
public class OracleFileStorageDAO implements FileStorageDAO {
    public FileInfo getFileInfo(long id) {
        FileInfo result = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement("SELECT * FROM "
                                       + Constants.TABLE_NAME
                                       + " WHERE "
                                       + Constants.COLUMN_FILE_ID
                                       + " = ?");
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                result = buildFileInfo(rs);
            } else {
                result = null;
            }
        } catch (SQLException ex) {
            throw new DataAccessException(
                    "id = " + id,
                    ex);
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        return result;
    }

    public FileInfo getFileInfo(String filename) {
        FileInfo result = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement("SELECT * FROM "
                                       + Constants.TABLE_NAME
                                       + " WHERE "
                                       + Constants.COLUMN_FILENAME
                                       + " = ?");
            ps.setString(1, filename);
            rs = ps.executeQuery();

            if (rs.next()) {
                result = buildFileInfo(rs);
            } else {
                result = null;
            }
        } catch (SQLException ex) {
            throw new DataAccessException(
                    "filename = " + filename,
                    ex);
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        return result;
    }

    public byte[] getFileData(long id) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        getFileData(id, baos);
        return baos.toByteArray();
    }

    public void getFileData(long id, OutputStream out) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement("SELECT * FROM "
                                       + Constants.TABLE_NAME
                                       + " WHERE "
                                       + Constants.COLUMN_FILE_ID
                                       + " = ?");
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Blob b = rs.getBlob(Constants.COLUMN_FILEDATA);
                InputStream in = b.getBinaryStream();
                byte[] buffer = new byte[32768];
                int n = 0;
                while ((n = in.read(buffer)) != -1) {
                    out.write(buffer, 0, n);
                }
                in.close();
            } else {
                throw new DataAccessException("not found: id=" + id);
            }
        } catch (java.io.IOException ex) {
            throw new DataAccessException(
                    "id = " + id,
                    ex);
        } catch (SQLException ex) {
            throw new DataAccessException(
                    "id = " + id,
                    ex);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }

    private static FileInfo buildFileInfo(ResultSet rs) throws SQLException {
        FileInfo info = new FileInfo();
        info.setFilename(rs.getString(Constants.COLUMN_FILENAME));
        info.setFileSize(rs.getLong(Constants.COLUMN_FILESIZE));
        info.setId(rs.getLong(Constants.COLUMN_FILE_ID));
        return info;
    }

    public List getAllFiles() {
        List result = new ArrayList();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement("SELECT * FROM "
                                       + Constants.TABLE_NAME
                                       + " ORDER BY "
                                       + Constants.COLUMN_FILENAME);
            rs = ps.executeQuery();

            while (rs.next()) {
                FileInfo info = buildFileInfo(rs);
                result.add(info);
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        return result;
    }



 public long saveFile(String filename,
  				InputStream input,
  				int filesize)
  	{
  		long id = -1;

  		FileInfo info = getFileInfo(filename);

  		if (info == null)
  		{
  			id = insertNewFile(filename, input, filesize);
  		}
  		else
  		{
  			overwriteFile(info.getId(), input, filesize);
  			id = info.getId();
  		}
  		return id;
  	}

  	protected long insertNewFile(String filename,
  				InputStream input,
 				int filesize)
  	{
  		Connection conn = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		OutputStream out = null;

  		long id = -1;

  		try
  		{
  			conn = DBUtil.getConnection();

  			//
  			//
  			// we need to explicitly manage this transaction
  			//
  			//
  			conn.setAutoCommit(false);

  			id = getNextFileId();

 			ps = conn.prepareStatement("INSERT INTO "
  								+ Constants.TABLE_NAME
  								+ " ( "
  								+ Constants.COLUMN_FILENAME
 								+ ", "
 							+ Constants.COLUMN_FILESIZE
 								+ ", "
  								+ Constants.COLUMN_FILEDATA
  								+ ", "
 								+ Constants.COLUMN_FILE_ID
  								+ " ) VALUES ( ?, ?, empty_blob(), ? ) ");
  			ps.setString(1, filename);
  			ps.setLong(2, filesize);
 			ps.setLong(3, id);
 			ps.execute();

  			ps.close();

  			ps = conn.prepareStatement("SELECT * FROM "
  						+ Constants.TABLE_NAME
  						+ " WHERE "
  						+ Constants.COLUMN_FILE_ID
  						+ " = ? FOR UPDATE ");
  			ps.setLong(1, id);
  			rs = ps.executeQuery();

  			rs.next();
  			Blob b = rs.getBlob(Constants.COLUMN_FILEDATA);
  			b.truncate(0);
  			out = b.setBinaryStream(0L);
  			byte[] buffer = new byte[32768];
  			int n = 0;
  			while ( ( n = input.read(buffer) ) != -1)
  			{
  				out.write(buffer, 0, n);
  			}
  			out.flush();
  			conn.commit();
  		}
  		catch (SQLException ex)
  		{
 			throw new DataAccessException(ex);
  		}
  		catch (java.io.IOException ex)
  		{
  			throw new DataAccessException(ex);
 		}
  		finally
  		{
  			try
  			{
  				DBUtil.close(conn, ps, rs);
  			}
  			finally
  			{
  				if (out != null)
  				{
  					try
 					{
  						out.close();
 					}
  					catch (java.io.IOException ignored)
  					{
  						// ignored
  					}
  				}
  			}
  		}

  		return id;
  	}

  	public boolean fileExists(final String filename)
  	{
  		FileInfo info = getFileInfo(filename);

  		if (info == null)
  		{
  			return false;
  		}
  		else
  		{
  			return true;
  		}
 	}

  	public void overwriteFile(
 			final long id,
  			final InputStream input,
  			final int filesize)
  {
  	Connection conn = null;
  	PreparedStatement ps = null;
  	ResultSet rs = null;
  	OutputStream out = null;

 	try
  	{
  		conn = DBUtil.getConnection();

  		//
 		//
  		// we need to explicitly manage this transaction
  		//
  		//
  		conn.setAutoCommit(false);

  		ps = conn.prepareStatement("SELECT "
  					+ Constants.COLUMN_FILESIZE
  					+ ", "
  					+ Constants.COLUMN_FILEDATA
  					+ " FROM "
  					+ Constants.TABLE_NAME
 		+ " WHERE "
  					+ Constants.COLUMN_FILE_ID
  					+ " = ? FOR UPDATE ",
  					ResultSet.TYPE_FORWARD_ONLY,
  					ResultSet.CONCUR_UPDATABLE);
  		ps.setLong(1, id);
  		rs = ps.executeQuery();

  		if (rs.next())
  		{
  			rs.updateLong(Constants.COLUMN_FILESIZE, filesize);
  			Blob b = rs.getBlob(Constants.COLUMN_FILEDATA);
  			b.truncate(0);
  			out = b.setBinaryStream(0L);
  			byte[] buffer = new byte[4096];
  			int n = 0;
  			while ( ( n = input.read(buffer) ) != -1)
  			{
  				out.write(buffer, 0, n);
 			}
  			out.flush();
  			rs.updateRow();
  			conn.commit();
  		}
  		else
  		{
  			throw new DataAccessException("id "
  									+ id
  									+ " does not exist");
  		}
  	}
  	catch (SQLException ex)
 	{
  		throw new DataAccessException(ex);
  	}
  	catch (java.io.IOException ex)
  	{
  		throw new DataAccessException(ex);
  	}
  	finally
  	{
  		try
  		{
 			DBUtil.close(conn, ps, rs);
  		}
  		finally
  		{
  			if (out != null)
  			{
  				try
  				{
  					out.close();
  				}
  				catch (java.io.IOException ignored)
  				{
  					// ignored
  				}
  			}
  		}
  	}

  }

 	public boolean deleteFile(long id)
  	{
  		boolean result = false;

  		Connection conn = null;
  		PreparedStatement ps = null;
 		ResultSet rs = null;

  		try
  		{
  			conn = DBUtil.getConnection();
  			ps = conn.prepareStatement("DELETE FROM "
 							+ Constants.TABLE_NAME
  								+ " WHERE "
  								+ Constants.COLUMN_FILE_ID
  								+ " = ?");
 			ps.setLong(1, id);
  			result = ps.execute();
  		}
  		catch (SQLException ex)
  		{
  			throw new DataAccessException(
  							"error while deleting id = " + id,
  							ex);
  		}
  		finally
  		{
  			DBUtil.close(conn, ps, rs);
  		}

  		return result;
  	}

  	protected long getNextFileId()
  	{
  		Connection conn = null;
  		Statement st = null;
  		ResultSet rs = null;

  		long id = -1;

  		try
  		{
  			conn = DBUtil.getConnection();
  			st = conn.createStatement();

  			String sql = "SELECT "
  						+ Constants.SEQUENCE_NAME_FILE_ID
  						+ "."
  						+ Constants.NEXTVAL
  						+ " FROM dual";

  			rs = st.executeQuery(sql);
  			rs.next();
  			id = rs.getLong(Constants.NEXTVAL);
  		}
  		catch (SQLException ex)
  		{
  			throw new DataAccessException(ex);
  		}
  		finally
  		{
  			DBUtil.close(conn, st, rs);
  		}

  		return id;
 	}


















*/
}



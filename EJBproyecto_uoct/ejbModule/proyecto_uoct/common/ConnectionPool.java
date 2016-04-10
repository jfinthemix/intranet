package proyecto_uoct.common;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.sql.SQLException;
import oracle.jdbc.*;

public final class ConnectionPool {

  private static ConnectionPool s_connectionPool;
  private DataSource m_dataSource;

  private ConnectionPool() throws Exception {
    try {

   InitialContext ctx = new InitialContext();
      //m_dataSource = (DataSource) ctx.lookup("java:/OracleDS");
     m_dataSource = (DataSource) ctx.lookup("java:/ItraData");
    } catch(NamingException e){
      e.printStackTrace(System.out);
      throw new Exception("No se pudo obtener DataSource");
    }
  }

  /**
   * Inicia o retorna instancia de Singleton
   * @return Connection Pool Instancia de ConnectionPool
   */
  synchronized public static ConnectionPool getInstance() {
    if (s_connectionPool == null) {
      // Este objeto se encarga ahora de
      // obtener el objeto DataSource.
      try {
        s_connectionPool = new ConnectionPool();
      } catch (Exception e) {
        throw new IllegalStateException("Pool no inicializado.");


 }
    }
    return s_connectionPool;
  }

  /**
   * Retorna una Conexi&oacute;n a la base de datos definida.
   * @return Connection Conexi&oacute;n a la base de datos definida.
   * @throws SQLException Cuando falla la obtención de la conexi&oacute;n
   java:/ItraData
   */
  public Connection getConnection() throws SQLException {
	  /*Connection con = null;
	  Driver myDriver = new oracle.jdbc.driver.OracleDriver();
	  String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	  String USER = "intra_sh";
	  String PASS = "uoct319";*/
	  try{
		  /*DriverManager.registerDriver( myDriver );
		  Connection conn = DriverManager.getConnection(URL, USER, PASS);*/
		   return m_dataSource.getConnection();
		  
       //return conn;
   }catch(SQLException e){
       throw e;
   }
	  
  }

}

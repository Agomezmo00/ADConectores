package dbEmbebidaSqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConectorDB {
	
	String url = ".//ficheros//chinook.db";
	Connection conector;
	
	
	public void establecerConexion(){
		 try {
			 conector = DriverManager.getConnection("jdbc:sqlite:"+url);
		     if (conector!=null) {
		         System.out.println("Conectado");
		     }
		 }catch (SQLException ex) {
		     System.err.println("No se ha podido conectar a la base de datos\n"+ex.getMessage());
		 }
		}
		 public void close(){
		        try {
		        	conector.close();
		        } catch (SQLException ex) {
		            Logger.getLogger(ConectorDB.class.getName()).log(Level.SEVERE, null, ex);
		        }
		 }

}

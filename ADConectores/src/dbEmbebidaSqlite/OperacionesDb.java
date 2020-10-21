package dbEmbebidaSqlite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OperacionesDb {

	
	public void insertaArtist(Artista artista) {
		
		ConectorDB con = new ConectorDB();
		
		con.establecerConexion(); //Si se conecta bien, veremos un mensaje que diga que se ha conectado, o nos avise en caso contrario
		
		try {
			PreparedStatement st = con.conector.prepareStatement("insert into artists(name) values (?)");
				st.setString(1, artista.getName());
				st.execute();
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		con.close();
	}
	
	
	public void listaArtistas() {
		
		ConectorDB con = new ConectorDB();
		
		con.establecerConexion(); //Si se conecta bien, veremos un mensaje que diga que se ha conectado, o nos avise en caso contrario
		
		try {
			PreparedStatement st = con.conector.prepareStatement("select * from artists");
			
			ResultSet resultado = null;
			resultado = st.executeQuery();
            while (resultado.next()) {
            	System.out.println("Id: "+resultado.getInt("ArtistId"));
            	System.out.println("Nombre: "+resultado.getString("Name"));
            	System.out.println("---------------------------");
            }
            } catch (SQLException ex) {
            	ex.printStackTrace();
            }
		
	}
	
	
}

package dbEmbebidaSqlite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	public void listaCancionesDisco(String nombreDiscoIntroducido) {
		ConectorDB con = new ConectorDB();
		con.establecerConexion(); //Si se conecta bien, veremos un mensaje que diga que se ha conectado, o nos avise en caso contrario
		
		try {
			int albumId = recuperaPrimerAlbum(con, nombreDiscoIntroducido);
			//Consulta que devuelve las canciones y duraciones cuyo álbum encaje con el titulo introducido
			String consulta = "select t.name, t.milliseconds from tracks t where t.albumid = ?";
			PreparedStatement st = con.conector.prepareStatement(consulta);
			st.setInt(1, albumId);
			
			ResultSet resultado = null;
			resultado = st.executeQuery();
			
			if (resultado == null) {
				System.out.println("La consulta no ha devuelto resultados");
			}
			
			int i=0;
            while (resultado.next()) {
            	
            	i++;
            	System.out.println(i+". "+resultado.getString("Name"));
            	
            	
            	int duracionMilisegundos = resultado.getInt(2);
            	
            	if(duracionMilisegundos > 0) {
            		int duracionSegundos = duracionMilisegundos / 1000;
            		int minutos = duracionSegundos/60;
            		int segundos = duracionSegundos % 60;
            		System.out.println("Duración: "+minutos+":"+String.format("%02d", segundos));
            	} else {
            		System.out.println("Duración: No hay datos");
            	}
            	
            	
            	System.out.println("---------------------------");
            }
            } catch (SQLException ex) {
            	ex.printStackTrace();
            }
	}
	
	public int recuperaPrimerAlbum (ConectorDB con, String nombre) {
		
		int idAlbum = -1;
		try {
			
			String consulta = "select a.albumid from albums a where a.title like ?";
			
			PreparedStatement st = con.conector.prepareStatement(consulta);
			
			st.setString(1, "%"+nombre+"%");
			
			ResultSet resultado = null;
			
			resultado = st.executeQuery();
			Statement state = resultado.getStatement();
			
			System.out.println(state.getFetchDirection());
			
			if(resultado != null) {
				while(resultado.next()) {
					return resultado.getInt(1);
				}
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return idAlbum;
	}
	
	
}

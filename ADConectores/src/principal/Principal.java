package principal;

import java.util.Scanner;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbEmbebidaSqlite.Artista;
import dbEmbebidaSqlite.OperacionesDb;
import ejemploMySQL.Conexion;
import java.sql.Connection;


public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		Artista a = new Artista();
//		a.setName("Leonard Cohen");
//		a.guardar();
//		
//		a.verTodosArtistas();
		
		//verCancionesAlbum();
		verCiudadesPais();
		
		
	}
	
	public static void verCancionesAlbum() {
		
		Scanner inputTeclado = new Scanner(System.in);
		System.out.println("Introduce el título del álbum");
		
		String titulo = inputTeclado.nextLine();
		OperacionesDb odb = new OperacionesDb();
		odb.listaCancionesDisco(titulo);
		
		
	}
	
	
	public static void verCiudadesPais() {
		
		try {
		Conexion con = new Conexion();
		Connection cx = con.conectar();
		
		PreparedStatement st = cx.prepareStatement("select * from city where countrycode like 'ESP'");
		
		ResultSet results = st.executeQuery();
		while(results.next()) {
			System.out.println("Ciudad: "+results.getString("Name"));
			System.out.println("Región: "+results.getString("District"));
		}
		
		
		
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
				
	}

}

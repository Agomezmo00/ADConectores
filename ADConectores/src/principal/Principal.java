package principal;

import java.util.Scanner;

import dbEmbebidaSqlite.Artista;
import dbEmbebidaSqlite.OperacionesDb;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		Artista a = new Artista();
//		a.setName("Leonard Cohen");
//		a.guardar();
//		
//		a.verTodosArtistas();
		
		verCancionesAlbum();
	}
	
	public static void verCancionesAlbum() {
		
		Scanner inputTeclado = new Scanner(System.in);
		System.out.println("Introduce el título del álbum");
		
		String titulo = inputTeclado.nextLine();
		OperacionesDb odb = new OperacionesDb();
		odb.listaCancionesDisco(titulo);
		
		
	}

}

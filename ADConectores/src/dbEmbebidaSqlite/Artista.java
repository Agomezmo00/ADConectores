package dbEmbebidaSqlite;

public class Artista {
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public void guardar() {
		OperacionesDb op = new OperacionesDb();
		op.insertaArtist(this);
	}
	
	public void verTodosArtistas() {
		OperacionesDb op = new OperacionesDb();
		op.listaArtistas();
	}
	
}

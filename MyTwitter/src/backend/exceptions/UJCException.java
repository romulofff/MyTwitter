package backend.exceptions;

public class UJCException extends Exception {
	
	private static final long serialVersionUID = 1L;

	String usuario;
	
	public UJCException() {
		super("Usuário já cadastrado.");
		this.usuario = usuario;
	}
	
	public String getMesssage(){
		return "Este usuário : " + this.usuario + " já foi cadastrado. "
				+ "Tente novamente.";
	}
	
	public String getUsuario(){
		return this.usuario;
	}
}
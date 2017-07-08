package backend.twitter;

public class Tweet {

	// Atributos

	private String usuario;
	private String mensagem;

	// Métodos

	public Tweet(String usuario, String mensagem) {
		setUsuario(usuario);
		setMensagem(mensagem);
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return this.mensagem;
	}
}

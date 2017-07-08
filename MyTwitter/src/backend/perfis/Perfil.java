package backend.perfis;

import java.util.Vector;

import backend.twitter.Tweet;

public abstract class Perfil {

	// Atributos

	private String usuario;
	private Vector<Perfil> seguidos;
	private Vector<Perfil> seguidores;
	private Vector<Tweet> timeline;
	private boolean ativo;

	// Métodos

	public Perfil(String usuario) {
		this.setUsuario(usuario);
		this.ativo = true;
		this.timeline = new Vector<Tweet>();
		this.seguidos = new Vector<Perfil>();
		this.seguidores = new Vector<Perfil>();
	}

	public void addSeguido(Perfil usuario) {
		this.seguidos.addElement(usuario);
	}

	public void addSeguidor(Perfil usuario) {
		this.seguidores.addElement(usuario);
	}

	public void addTweet(Tweet tweet) {
		this.timeline.addElement(tweet);
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public Vector<Perfil> getSeguidos() {
		return this.seguidos;
	}

	public Vector<Perfil> getSeguidores() {
		return this.seguidores;
	}

	public Vector<Tweet> getTimeline() {
		return this.timeline;
	}

	public void setAtivo(boolean valor) {
		this.ativo = valor;
	}

	public boolean isAtivo() {
		return this.ativo;
	}
}

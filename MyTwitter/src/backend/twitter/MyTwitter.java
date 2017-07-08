package backend.twitter;

import java.util.Vector;

import backend.exceptions.MFPException;
import backend.exceptions.PDException;
import backend.exceptions.PEException;
import backend.exceptions.PIException;
import backend.exceptions.SIException;
import backend.exceptions.UJCException;
import backend.perfis.Perfil;
import backend.repositorio.IRepositorioUsuario;

public class MyTwitter implements ITwitter {

	// Atributos

	private IRepositorioUsuario repo;

	// Métodos

	public MyTwitter(IRepositorioUsuario repo) {
		this.repo = repo;
	}

	private Perfil buscarPerfil(String usuario) throws PIException, PDException {
		Perfil perfil = this.repo.buscar(usuario);
		if (perfil == null) {
			throw new PIException();
		} else if (!perfil.isAtivo()) {
			throw new PDException();
		}
		return perfil;
	}

	public void criarPerfil(Perfil usuario) throws PEException {
		try {
			this.repo.cadastrar(usuario);
			System.out.println(" Usuário cadastrado com sucesso.");
		} catch (UJCException ujce) {
			throw new PEException();
		}
	}

	public void cancelarPerfil(String usuario) throws PIException, PDException {
		Perfil perfil = buscarPerfil(usuario);
		perfil.setAtivo(false);
		System.out.println("Perfil desativado com sucesso.");
	}

	public void tweetar(String usuario, String mensagem) throws PIException, MFPException {
		try {
			if (mensagem.length() <= 140 && mensagem.length() >= 1) {
				Perfil perfil = buscarPerfil(usuario);
				Tweet tweet = new Tweet(usuario, mensagem);
				perfil.addTweet(tweet);
				for (Perfil seguidor : perfil.getSeguidores()) {
					this.repo.buscar(seguidor.getUsuario()).addTweet(tweet);
				}

			} else {
				throw new MFPException();
			}
		} catch (PDException e) {
			System.out.println("Perfil desativado. Tweet não postado.");
		}

	}

	public Vector<Tweet> timeline(String usuario) throws PIException, PDException {
		Perfil perfil = buscarPerfil(usuario);
		return perfil.getTimeline();
	}

	public Vector<Tweet> tweets(String usuario) throws PIException, PDException {
		Perfil perfil = buscarPerfil(usuario);
		Vector<Tweet> tweetsDoUsuario = new Vector<Tweet>();
		for (Tweet tweet : perfil.getTimeline()) {
			if (tweet.getUsuario() == perfil.getUsuario()) {
				tweetsDoUsuario.addElement(tweet);
			}
		}
		return tweetsDoUsuario;
	}

	public void seguir(String seguidor, String seguido) throws PIException, PDException, SIException {
		Perfil novoSeguido = buscarPerfil(seguido);
		Perfil novoSeguidor = buscarPerfil(seguidor);
		if (novoSeguido.getUsuario().equals(novoSeguidor.getUsuario())) {
			throw new SIException();
		} else {
			novoSeguido.addSeguidor(novoSeguidor);
			novoSeguidor.addSeguido(novoSeguido);
		}
	}

	public int numeroSeguidores(String usuario) throws PIException, PDException {
		Perfil perfil = buscarPerfil(usuario);
		return perfil.getSeguidores().size();
	}

	public Vector<Perfil> seguidores(String usuario) throws PIException, PDException {
		Perfil perfil = buscarPerfil(usuario);
		return perfil.getSeguidores();
	}

	public Vector<Perfil> seguidos(String usuario) throws PIException, PDException {
		Perfil perfil = buscarPerfil(usuario);
		return perfil.getSeguidos();
	}

}

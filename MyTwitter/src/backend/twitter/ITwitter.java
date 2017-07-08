package backend.twitter;

import java.util.Vector;

import backend.exceptions.MFPException;
import backend.exceptions.PDException;
import backend.exceptions.PEException;
import backend.exceptions.PIException;
import backend.exceptions.SIException;
import backend.perfis.Perfil;

public interface ITwitter {

	public void criarPerfil(Perfil usuario) throws PEException;

	public void cancelarPerfil(String usuario) throws PIException, PDException;

	public void tweetar(String usuario, String mensagem) throws PIException, MFPException;

	public Vector<Tweet> timeline(String usuario) throws PIException, PDException;

	public Vector<Tweet> tweets(String usuario) throws PIException, PDException;

	public void seguir(String seguidor, String seguido) throws PIException, PDException, SIException;

	public int numeroSeguidores(String usuario) throws PIException, PDException;

	public Vector<Perfil> seguidores(String usuario) throws PIException, PDException;

	public Vector<Perfil> seguidos(String usuario) throws PIException, PDException;
}

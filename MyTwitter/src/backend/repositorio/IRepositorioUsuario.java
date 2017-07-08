package backend.repositorio;

import backend.exceptions.UJCException;
import backend.exceptions.UNCException;
import backend.perfis.Perfil;

public interface IRepositorioUsuario {

	public void cadastrar(Perfil usuario) throws UJCException;

	public Perfil buscar(String usuario);

	public void atualizar(Perfil usuario) throws UNCException;
}

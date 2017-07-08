package backend.repositorio;

import java.util.Vector;

import backend.exceptions.UJCException;
import backend.exceptions.UNCException;
import backend.perfis.Perfil;

public class RepositorioVector implements IRepositorioUsuario {

	private Vector<Perfil> usersRepo;

	public RepositorioVector() {
		usersRepo = new Vector<Perfil>();
	}

	public void cadastrar(Perfil usuario) throws UJCException {
		if (buscar(usuario.getUsuario()) == null) {
			this.usersRepo.addElement(usuario);
		} else {
			throw new UJCException();
		}
	}

	public Perfil buscar(String usuario) {
		for (Perfil perfil : usersRepo) {
			if (perfil.getUsuario().equals(usuario)) {
				return perfil;
			}
		}
		return null;
	}

	public void atualizar(Perfil usuario) throws UNCException {
		Perfil usuarioAntigo = buscar(usuario.getUsuario());
		if (usuarioAntigo == null){
			throw new UNCException();
		} else {
			usuarioAntigo = usuario;
		}
	}

}

package backend.perfis;

public class PessoaJuridica extends Perfil {

	// Atributos
	
	private long cnpj;
	
	// Métodos 
	
	public PessoaJuridica(String usuario) {
		super(usuario);
		this.setCnpj(cnpj);
	}

	public void setCnpj(long cnpj){
		this.cnpj = cnpj;
	}
	
	public long getCnpj(){
		return this.cnpj;
	}
}

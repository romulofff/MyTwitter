package backend.perfis;

public class PessoaFisica extends Perfil {

	// Atributos
	
	private long cpf;
	
	// Métodos
	
	public PessoaFisica(String usuario) {
		super(usuario);	
		this.setCpf(cpf);
	}
	
	public void setCpf(long cpf){
		this.cpf = cpf;
	}
	
	public long getCpf(){
		return this.cpf;
	}
	
}

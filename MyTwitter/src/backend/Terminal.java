package backend;

import java.util.Scanner;
import java.util.Vector;

import backend.exceptions.MFPException;
import backend.exceptions.PDException;
import backend.exceptions.PEException;
import backend.exceptions.PIException;
import backend.exceptions.SIException;
import backend.perfis.Perfil;
import backend.perfis.PessoaFisica;
import backend.perfis.PessoaJuridica;
import backend.repositorio.RepositorioVector;
import backend.twitter.MyTwitter;
import backend.twitter.Tweet;

public class Terminal {

	private static Scanner scanf = new Scanner(System.in);

	public static void main(String[] args) {
		RepositorioVector repo = new RepositorioVector();
		MyTwitter twitter = new MyTwitter(repo);
		boolean executando = true;
		Perfil usuario = null;

		while (executando) {
			if (usuario == null) {
				switch (menuEntrada()) {
				case 1:
					String user;
					long cpf;
					long cnpj;
					Perfil perfil;
					System.out.println("Escolhe o tipo de conta que deseja criar:");
					System.out.println("1. Conta Pessoa fisica");
					System.out.println("2. Conta Pessoa juridica");
					int opcao = scanf.nextInt();
					if (opcao == 1) {
						System.out.println("Digite seu nome de usuario:");
						user = scanf.next();
						System.out.println("Digite seu cpf:");
						cpf = scanf.nextLong();
						perfil = new PessoaFisica(user, cpf);
					} else if (opcao == 2) {
						System.out.println("Digite seu nome de usuario");
						user = scanf.next();
						System.out.println("Digite seu cnpj:");
						cnpj = scanf.nextLong();
						perfil = new PessoaJuridica(user);
						((PessoaJuridica) perfil).setCnpj(cnpj);
					} else {
						System.out.println("Opção invalida!");
						break;
					}
					try {
						twitter.criarPerfil(perfil);
						perfil.setAtivo(true);
					} catch (PEException e) {
						System.out.println("Erro: " + e.getMessage());
					}

					break;

				case 2:
					System.out.println("Digite o nome de usuario:");
					String nomeUsuario = scanf.next();

					if (repo.buscar(nomeUsuario) != null) {
						usuario = repo.buscar(nomeUsuario);
					} else {
						System.out.println("Usuário nao cadastrado!");
					}
					break;

				case 3:
					System.out.println("Encerrando execucao");
					executando = false;
					usuario = null;
					break;

				default:
					break;

				}

			} else {
				switch (menuPrincipal(usuario.getUsuario())) {

				case 1:
					System.out.println("Digite o nome de usuario do perfil que voce deseja seguir:");
					scanf.nextLine();
					String usuarioSeguido = scanf.next();
					try {
						twitter.seguir(usuario.getUsuario(), usuarioSeguido);
						System.out.println("Você esta agora seguindo @" + usuarioSeguido);
					} catch (PIException | PDException | SIException e) {
						System.out.println("Erro: " + e.getMessage());
					}
					break;

				case 2:
					System.out.println("Digite seu tweet contendo até 140 caracteres:");
					scanf.nextLine();
					String tweet = scanf.nextLine();
					try {
						twitter.tweetar(usuario.getUsuario(), tweet);
						System.out.println("Operaçao realizada com sucesso");
					} catch (PIException | MFPException e) {
						System.out.println("Erro: " + e.getMessage());
					}
					break;

				case 3:
					System.out.println("Timeline de @" + usuario.getUsuario());
					try {
						Vector<Tweet> timeline = twitter.timeline(usuario.getUsuario());
						for (int i = 0; i < timeline.size(); i++) {
							printTweet(timeline.get(i));
						}
					} catch (PIException | PDException e) {
						System.out.println("Erro: " + e.getMessage());
					}
					break;

				case 4:
					System.out.println("Tweets do perfil de @" + usuario.getUsuario() + " :");
					try {
						Vector<Tweet> tweets = twitter.tweets(usuario.getUsuario());
						for (int i = 0; i < tweets.size(); i++) {
							printTweet(tweets.get(i));
						}
					} catch (PIException | PDException e) {
						System.out.println("Erro: " + e.getMessage());
					}

					break;

				case 5:
					try {
						System.out.println("Numero de seguidores: " + twitter.numeroSeguidores(usuario.getUsuario()));
						System.out.println("Seus seguidores:");
						Vector<Perfil> seguidores = twitter.seguidores(usuario.getUsuario());
						for (int i = 0; i < seguidores.size(); i++) {
							System.out.println("@" + seguidores.get(i).getUsuario());
						}
					} catch (PIException | PDException e) {
						System.out.println("Erro: " + e.getMessage());
					}

					break;

				case 6:
					if (usuario.isAtivo() == true) {
						System.out.println("O usuário ja esta ativado!");
					} else {
						usuario.setAtivo(true);
						System.out.println("Usuário ativado com sucesso!");
					}

					break;

				case 7:
					if (usuario.isAtivo() == false) {
						System.out.println("O usuário ja esta desativado!");
					}
					try {
						twitter.cancelarPerfil(usuario.getUsuario());
						System.out.println("A conta foi desativada!");
					} catch (PIException | PDException e) {
						System.out.println("Erro: " + e.getMessage());
					}

					break;

				case 8:
					System.out.println("Informacoes da conta:");
					System.out.println("Nome de usuário: " + usuario.getUsuario());
					if (usuario instanceof PessoaFisica) {
						System.out.println("CPF: " + ((PessoaFisica) usuario).getCpf());
					} else {
						System.out.println("CNPJ: " + ((PessoaJuridica) usuario).getCnpj());
					}

					break;

				case 9:
					System.out.println("Fazendo logoff!");
					usuario = null;
					break;

				case 10:
					System.out.println("Encerrando execução!");
					usuario = null;
					executando = false;

					break;

				default:
					break;
				}

			}
		}

	}

	public static int menuEntrada() {
		System.out.println("Ola, seja bem vindo ao MyTwitter!");
		System.out.println("Por favor, escolha uma das opçoes:");
		System.out.println("1. Criar uma nova conta");
		System.out.println("2. Entrar em uma conta");
		System.out.println("3. Sair");
		int opcao = scanf.nextInt();
		return opcao;
	}

	public static int menuPrincipal(String usuario) {
		System.out.println("Ola @" + usuario + ", seja bem vindo ao seu twitter!");
		System.out.println("Escolha uma ação:");
		System.out.println("1. Seguir um usuário");
		System.out.println("2. Tweetar");
		System.out.println("3. Ver Timeline");
		System.out.println("4. Ver seus Tweets");
		System.out.println("5. Verificar seus seguidores");
		System.out.println("6. Ativar conta");
		System.out.println("7. Desativar conta");
		System.out.println("8. Dados da conta");
		System.out.println("9. Sair");
		System.out.println("10. Fechar");
		int opcao = scanf.nextInt();
		return opcao;
	}

	private static void printTweet(Tweet tweet) {
		System.out.println("Usuario: @" + tweet.getUsuario());
		System.out.println("Tweet: " + tweet.getMensagem());
		System.out.println(" ");
	}

}
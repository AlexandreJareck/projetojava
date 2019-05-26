package br.edu.ctup.view;

import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.edu.ctup.controller.ControllerCliente;
import br.edu.ctup.controller.ControllerPedido;
import br.edu.ctup.model.Cliente;
import br.edu.ctup.model.Pedido;
import br.edu.ctup.model.Produto;

public class Principal {

	static ControllerCliente controllerCliente = new ControllerCliente();
	static ControllerPedido controllerPedidos = new ControllerPedido();
	static SimpleDateFormat br = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	static Cliente cliente;
	static Scanner sc = new Scanner(System.in);
	static String flush;
	private static Cliente clienteLogado;
	private static int id;
	static int opcao;
	static int opcaoPedido;
	static boolean continueLoop = true;
	static int indice;
	private static Scanner sc2;
	private static Scanner sc3;

	public static void main(String[] args) {
		controllerPedidos.cadastarMenuBebidas();
		controllerPedidos.cadastarMenuLanches();
		do {
			try {
				do {
					menuPrincipal();
					opcao = sc.nextInt();
					switch (opcao) {
					case 1:
						logarCliente();
						break;
					case 2:
						cadastrarCliente();
						break;
					case 3:
						modoAdm();
						break;
					case 4:
						cozinha();
						break;
					default:
						System.out.println("Finalizando programa... ");
						System.exit(0);
						break;
					}

				} while (opcao > 0 && opcao < 3);

			} catch (InputMismatchException inputMismatchException) {
				excepetion();
			}
		} while (continueLoop);
	}

	public static void menuPrincipal() {
		System.out.println(" ####################################### ");
		System.out.println("#                                        #");
		System.out.println("####          MENU PRINCIPAL          ####");
		System.out.println("#                                        #");
		System.out.println("#     [1] - Logar como Cliente           #");
		System.out.println("#     [2] - Cadastrar Cliente            #");
		System.out.println("#     [3] - Visualizar pedidos Aberto    #");
		System.out.println("#     [4] - Visualizar pedidos Fechado   #");
		System.out.println("#                                        #");
		System.out.println("#              [5] - Sair                #");
		System.out.println("#                                        #");
		System.out.println(" ######################################## \n");
		System.out.printf("Digite sua opção: ");
	}

	static public void menuCliente() {
		System.out.println(" ####################################### ");
		System.out.println("#                                       #");
		System.out.println("#    [1] - Restaurante                  #");
		System.out.println("#    [2] - Alterar dados                #");
		System.out.println("#    [3] - Pagar Pedidos Em Aberto      #");
		System.out.println("#                                       #");
		System.out.println("#           [4] - Sair                  #");
		System.out.println("#                                       #");
		System.out.println(" ####################################### \n");
		System.out.printf("Digite sua opção: ");
	}

	public static void logarCliente() {
		System.out.printf("Digite seu login: ");
		String login = sc.next();
		System.out.printf("Digite sua senha: ");
		String senha = sc.next();
		System.out.println();
		indice = controllerCliente.autenticarCliente(login, senha);
		clienteLogado = controllerCliente.clienteLogado();
		if (indice > -1) {
			System.out.println("Login efetuado com sucesso!!");
			System.out.println();
			menuCliente();
			opcao = sc.nextInt();
			System.out.println();
			switch (opcao) {
			case 1:

				for (Pedido pedido : controllerPedidos.listarPedido()) {
					if (pedido.getStatus() == 2 && pedido.getCliente().getId() == indice) {
						System.out.println("Você ja tem pedido pendente!!");
						System.out.println("Finalize seu pedido, para que possa pedir novamente!!");
						System.out.println("Você está sendo redirecionado para pagamento...");
						System.out.println();
						pagamentoAberto();

					}
				}
				controllerPedidos.listarNewCarrinho();
				menuCardapio();
				break;
			case 2:
				alterarDados(indice);
				break;

			case 3:

				pagamentoAberto();
				break;

			default:
				System.out.println();
				System.out.println("Você digitou um opção invalida!");
				System.out.println("Voltando para o menu principal !");
				break;
			}

		} else {
			System.out.println("Login ou senha invalida!");
			System.out.println("Voltando para o menu principal..");
		}
	}

	public static void cadastrarCliente() {
		sc2 = new Scanner(System.in);
		cliente = new Cliente();
		System.out.printf("Digite seu Nome: ");
		String nome = sc2.nextLine();
		cliente.setNome(nome);
		System.out.printf("Digite seu CPF: ");
		String cpf = sc2.next();
		boolean confereCpf = controllerCliente.autenticarCpf(cpf);
		if (confereCpf) {
			System.out.printf("Digite seu RG: ");
			cliente.setRg(sc2.next());
			cliente.setCpf(cpf);
			System.out.printf("Digite seu Login: ");
			String login = sc2.next();
			boolean confereLogin = controllerCliente.autenticarLogin(login);
			if (confereLogin) {
				System.out.printf("Digite sua Senha: ");
				cliente.setSenha(sc2.next());
				cliente.setLogin(login);
				cliente.setId(id);
				flush = sc2.nextLine();
				System.out.printf("Digite seu Endereço(Rua,nº e bairro): ");
				String endereco = sc2.nextLine();

				cliente.setEndereco(endereco);

				System.out.println(cliente.getEndereco());
				controllerCliente.cadastrarCliente(cliente);
				System.out.println("\n # Cliente CADASTRADO COM SUCESSO !! # \n");
			} else {
				System.out.println(" # Login JA CADASTRADO !! #\n");
				System.out.println(" # Tente novamente, com outro Login! #\n");
				cadastrarCliente();
			}
		} else {
			System.out.println("CPF JA CADASTRADO !!");
			System.out.println("Tente novamente, com outro CPF!");
			System.out.println();
			cadastrarCliente();
		}
	}

	public static void alterarDados(int indice) {
		sc3 = new Scanner(System.in);
		System.out.println("Você não pode alterar RG E CPF!!\n");
		System.out.printf("Digite seu Nome: ");
		cliente.setNome(sc3.nextLine());
		System.out.printf("Digite seu Login: ");
		String login = sc3.next();
		boolean confereLogin = controllerCliente.autenticarLogin(login);
		if (confereLogin) {
			cliente.setLogin(login);
			System.out.printf("Digite sua Senha: ");
			cliente.setSenha(sc3.next());
			flush = sc3.nextLine();
			System.out.printf("Digite seu Endereço(Rua,nº,bairro,cidade,UF: ");
			cliente.setEndereco(sc3.nextLine());
			controllerCliente.alterarDadosCliente(indice, cliente);
			System.out.println();
			System.out.println("Cadastro alterado com sucesso!!\n");
			System.out.println("Nome: " + cliente.getNome() + " .");
			System.out.println("CPF: " + cliente.getCpf());
			System.out.println("RG: " + cliente.getRg());
			System.out.println("Login: " + cliente.getLogin());
			System.out.println("Senha: " + cliente.getSenha());
			System.out.println("Endereço: " + cliente.getEndereco() + " .");
			System.out.println();			
			menuCliente();
			opcao = sc.nextInt();
			System.out.println();
			switch (opcao) {
			case 1:

				for (Pedido pedido : controllerPedidos.listarPedido()) {
					if (pedido.getStatus() == 2 && pedido.getCliente().getId() == indice) {
						System.out.println("Você ja tem pedido pendente!!");
						System.out.println("Finalize seu pedido, para que possa pedir novamente!!");
						System.out.println("Você está sendo redirecionado para pagamento...");
						System.out.println();
						pagamentoAberto();

					}
				}
				controllerPedidos.listarNewCarrinho();
				menuCardapio();
				break;
			case 2:
				alterarDados(indice);
				break;

			case 3:

				pagamentoAberto();
				break;

			default:
				System.out.println();
				System.out.println("Você digitou um opção invalida!");
				System.out.println("Voltando para o menu principal !");
				break;
			}

		} else {
			System.out.println(" # Login JA CADASTRADO !! #\n");
			System.out.println(" # Tente novamente, com outro Login! #\n");
			alterarDados(indice);
		}

	}

	static public void pagamentoAberto() {

		for (Pedido pedido : controllerPedidos.listarPedido()) {
			if (pedido.getStatus() == 2 && pedido.getCliente().getId() == indice) {
				System.out.println("Nome do Cliente: " + pedido.getCliente().getNome());
				for (Produto produto : pedido.getListaProdPedido()) {
					System.out.println(produto.getDescricao() + " - " + produto.getPreco() + "R$");

				}
				System.out.println("Valor Total: " + pedido.getValorTotal());
				formaDePagamento();
			}

		}

	}

	public static void menuCardapio() {
		System.out.println(" ####################################### ");
		System.out.println("#                                       #");
		System.out.println("#############     CARDAPIO    ###########");
		System.out.println("#             [1] - PRATOS              #");
		System.out.println("#             [2] - BEBIDAS             #");
		System.out.println("#                                       #");
		System.out.println("#             [3] - SAIR                #");
		System.out.println("#                                       #");
		System.out.println(" ####################################### \n");
		System.out.printf("Digite sua opção: ");
		opcao = sc.nextInt();
		switch (opcao) {
		case 1:
			for (int i = 0; i < controllerPedidos.listarMenuComida().size(); i++) {

				System.out.println(
						"[" + (i + 1) + "]" + " - " + controllerPedidos.listarMenuComida().get(i).getDescricao()
								+ " - R$ " + controllerPedidos.listarMenuComida().get(i).getPreco());

			}
			System.out.printf("Escolha seu Prato: ");
			int op = sc.nextInt();
			controllerPedidos.cadastrarCarrinhoCompra(controllerPedidos.listarMenuComida().get(op - 1));
			System.out.println();
			menuEscolha();
			break;
		case 2:
			for (int i = 0; i < controllerPedidos.listarMenuBebidas().size(); i++) {

				System.out.println(
						"[" + (i + 1) + "]" + " - " + controllerPedidos.listarMenuBebidas().get(i).getDescricao()
								+ " - R$ " + controllerPedidos.listarMenuBebidas().get(i).getPreco());

			}
			System.out.printf("Escolha sua bebida: ");
			op = sc.nextInt();
			controllerPedidos.cadastrarCarrinhoCompra(controllerPedidos.listarMenuBebidas().get(op - 1));
			System.out.println();
			menuEscolha();
			break;

		case 3:
			System.out.println("Voltando para o menu principal..");

			break;
		default:
			System.out.println("Opção invalida...");
			System.out.println("Você está sendo redirecionado para o menu principal...");
			System.out.println("Voltando para o Menu Cardapio..");
			menuCardapio();
			break;
		}
	}

	public static void menuEscolha() {
		System.out.println(" ######################################## ");
		System.out.println("#                                        #");
		System.out.println("#         [1] - Fazer Outro Pedido       #");
		System.out.println("#         [2] - Finalizar Pedidos        #");
		System.out.println("#                                        #");
		System.out.println("#         [3] - Pagar Depois(Sair)       # ");
		System.out.println("#                                        #");
		System.out.println(" ####################################### \n");
		System.out.printf("Escolha sua opção: ");
		opcaoPedido = sc.nextInt();
		System.out.println();
		switch (opcaoPedido) {
		case 1:
			System.out.println();
			menuCardapio();
			break;
		case 2:
			System.out.println();
			pagamentoPedidos();
			break;
		case 3:
			System.out.println();
			controllerPedidos.pedidoEmAberto(clienteLogado);
			controllerPedidos.valorTotal();
			break;
		default:
			System.out.println();
			System.out.println("Você digitou uma opção invalida !");
			System.out.println("Informe a forma de pagamento !");
			menuEscolha();
			break;
		}
	}

	static public void pagamentoPedidos() {
		controllerPedidos.finalizarPedido(clienteLogado);
		controllerPedidos.valorTotal();
		System.out.println("Cliente: " + controllerPedidos.listarPedido()
				.get(controllerPedidos.listarPedido().size() - 1).getCliente().getNome());
		for (int i = 0; i < controllerPedidos.retornarPedido().getListaProdPedido().size(); i++) {
			System.out.println(controllerPedidos.retornarPedido().getListaProdPedido().get(i).getDescricao() + ": "
					+ controllerPedidos.retornarPedido().getListaProdPedido().get(i).getPreco() + "R$");
		}
		System.out.println("Data do pedido: " + (br.format(controllerPedidos.retornarPedido().getDataPedido())));
		System.out.println();
		System.out.println("Preco Total: " + controllerPedidos.retornarPedido().getValorTotal());
		formaDePagamento();
	}

	static public void formaDePagamento() {
		System.out.println("Informe a forma de pagamento");
		System.out.println("[1] - Cartão");
		System.out.println("[2] - Dinheiro");
		System.out.printf("Forma de pagamento: ");
		int formaPag = sc.nextInt();
		switch (formaPag) {
		case 1:
			System.out.printf("Insira a senha do cartão: ");
			int senhaCartao = sc.nextInt();
			System.out.println("\nPagamento Efetuado com sucesso!!");
			System.out.println();
			controllerPedidos.retornarPedido().setStatus(1);
			
			if (senhaCartao < 0) {
				System.out.println("Não pode digitar numero negativo..");
				formaDePagamento();
			}
			break;
		case 2:
			System.out.println();
			System.out.println("Preco Total: " + controllerPedidos.retornarPedido().getValorTotal());
			System.out.printf("Informe o valor do pagamento: ");
			double pague = sc.nextDouble();
			if (pague < 0) {
				System.out.println("Não pode digitar numero negativo..");
				formaDePagamento();
			}
			double faltaPague = controllerPedidos.confirmarPagamento(pague,
					controllerPedidos.retornarPedido().getValorTotal());

			if (pague == controllerPedidos.retornarPedido().getValorTotal()) {
				System.out.println("Pagamento Efetuado!!");
				controllerPedidos.retornarPedido().setStatus(1);
				System.out.println("Obrigado pela preferência!");
			} else if (pague > controllerPedidos.retornarPedido().getValorTotal()) {
				System.out.println("Pagamento Efetuado!!");
				System.out.println("Seu troco é : " + faltaPague);
				controllerPedidos.retornarPedido().setStatus(1);
				System.out.println("Obrigado pela preferência!\n");
			}

			while (pague < controllerPedidos.retornarPedido().getValorTotal()) {
				System.out.println("Pagamento incompleto");
				System.out.println("Falta: " + faltaPague);
				controllerPedidos.retornarPedido().setValorTotal(faltaPague);
				System.out.printf("Insira o restante: ");
				pague = sc.nextDouble();
				faltaPague = controllerPedidos.confirmarPagamento(pague,
						controllerPedidos.retornarPedido().getValorTotal());
				if (pague > faltaPague) {
					System.out.println("Pagamento Efetuado!!");
					System.out.println("Seu troco é : " + faltaPague);
					controllerPedidos.retornarPedido().setStatus(1);
					System.out.println("Obrigado pela preferência!\n");
				}
			}
			break;
		default:
			System.out.println();
			System.out.println("Você digitou uma opção errada...");
			System.out.println("Seu pedido foi registrado !!");
			System.out.println("Faça seu login e efetue o pagamento !");
			System.out.println("Você está sendo redirecionado para o menu principal...");
			break;
		}
	}

	public static void cozinha() {
		System.out.printf("Digite seu Login: ");
		String login = sc.next();
		System.out.printf("Digite sua senha: ");
		String senha = sc.next();
		if (login.equals("coz") && senha.equals("coz")) {
			System.out.println();
			System.out.println("##   MENU COZINHA ##");
			System.out.println();
			if (controllerPedidos.retornarPedido().getStatus() < 1) {
				System.out.println("Nenhum pedido finalizado");
			}
			for (Pedido pedido : controllerPedidos.listarPedido()) {
				if (pedido.getStatus() == 1) {
					System.out.println("Nome do Cliente: " + pedido.getCliente().getNome());
					System.out.println("Login: " + pedido.getCliente().getLogin());
					for (Produto produto : pedido.getListaProdPedido()) {
						System.out.println("Seu pedido foi:" + produto.getDescricao());
						System.out.println("Pedido feito às: " + (br.format(pedido.getDataPedido())));
					}
				}
			}
			System.out.println();
			System.out.println();
		} else {
			System.out.println("Login ou senha invalida, tente novamente\n");
		}
	}

	public static void modoAdm() {
		System.out.println(" Administrador ");
		System.out.printf("Digite seu Login: ");
		String login = sc.next();
		System.out.printf("Digite sua senha: ");
		String senha = sc.next();
		if (login.equals("adm") && senha.equals("adm")) {
			System.out.println();
			if (controllerPedidos.retornarPedido().getStatus() < 2) {
				System.out.println(" Nenhum pedido em aberto !!!");
			}
			for (Pedido pedido : controllerPedidos.listarPedido()) {
				
				if (pedido.getStatus() == 2) {
					System.out.println("Nome do Cliente: " + pedido.getCliente().getNome());
					System.out.println("Login: " + pedido.getCliente().getLogin());
					System.out.println("CPF: " + pedido.getCliente().getCpf());
					System.out.println("RG: " + pedido.getCliente().getRg());
					for (Produto produto : pedido.getListaProdPedido()) {						
						
						System.out.println(
								"Seu pedido foi:" + produto.getDescricao() + " - " + " R$ " + produto.getPreco());
						System.out.println("Pedido feito às: " + (br.format(pedido.getDataPedido())));
					}
					System.out.println("Valor Total: " + pedido.getValorTotal());
				}
			}
			System.out.println();
			System.out.println();
		} else {
			System.out.println("Login ou senha invalida, tente novamente!");
		}
	}

	public static void excepetion() {
		System.out.println();
		sc.nextLine();
		System.out.printf("Essa opção só aceita numeros inteiros!\n por favor, digite uma opção valida !");
		System.out.println(" !!! Você está sendo redirecionado para o Menu Principal  !!!");
		System.out.println();
	}

}

package br.edu.ctup.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.ctup.model.Cliente;
import br.edu.ctup.model.Pedido;
import br.edu.ctup.model.Produto;

public class ControllerPedido {

	private List<Produto> carrinhoCompra;

	List<Pedido> listaDePedido = new ArrayList<>();
	static Pedido pedido = new Pedido();
	List<Produto> listaMenuComidas = new ArrayList<>();
	List<Produto> listaMenuBebidas = new ArrayList<>();
	static int id;
	public void cadastarMenuLanches() {
		
		Produto arrozFeijao = new Produto("Arroz, Feijão, bife e batatta frita", 15.00);
		Produto peixe = new Produto("Peixe Assado", 19.00);
		Produto carne = new Produto("Carne-de-Sol Assada", 21.00);
		Produto camarao = new Produto("Camarão ao creme de Abóbora", 23.00);
		Produto mukeka = new Produto("Moqueca de peixe", 28.00);
		listaMenuComidas.add(arrozFeijao);
		listaMenuComidas.add(peixe);
		listaMenuComidas.add(carne);
		listaMenuComidas.add(camarao);
		listaMenuComidas.add(mukeka);
	}

	public List<Produto> listarMenuComida() {
		return listaMenuComidas;
	}

	public void cadastarMenuBebidas() {
		
		Produto agua = new Produto("Água Mineral 500ml", 4.00);
		Produto cerveja = new Produto("Cerveja Orginal 600ml", 8.00);
		Produto sucoNatural = new Produto("Suco Natural 500ml", 7.00);
		Produto refri = new Produto("Refrigerante 500ml", 6.00);
		Produto vinho = new Produto("Taça de Vinho 300ml", 21.00);

		listaMenuBebidas.add(cerveja);
		listaMenuBebidas.add(sucoNatural);
		listaMenuBebidas.add(refri);
		listaMenuBebidas.add(agua);
		listaMenuBebidas.add(vinho);

	}

	public List<Produto> listarMenuBebidas() {
		return listaMenuBebidas;
	}

	public void cadastrarCarrinhoCompra(Produto pedidos) {
		if (carrinhoCompra == null) {
			carrinhoCompra = new ArrayList<>();
			carrinhoCompra.add(pedidos);

		} else {
			carrinhoCompra.add(pedidos);
		}

	}

	public void finalizarPedido(Cliente clienteLogado) {		
		
		pedido = new Pedido();
		pedido.setCliente(clienteLogado);
		
		pedido.setListaProdPedido(carrinhoCompra);
		pedido.setStatus(2);
		listaDePedido.add(pedido);
		pedido.setDataPedido(new Date(System.currentTimeMillis()));
		
	}

	public void valorTotal() {
		double total = 0;

		for (int i = 0; i < retornarPedido().getListaProdPedido().size(); i++) {

			total = total + retornarPedido().getListaProdPedido().get(i).getPreco();

			retornarPedido().setValorTotal(total);

		}

	}

	public List<Produto> listarNewCarrinho() {
		return carrinhoCompra = new ArrayList<Produto>();
	}

	public List<Produto> listarCarrinho() {
		return carrinhoCompra;
	}

	public Pedido retornarPedido() {
		return pedido;
	}

	public List<Pedido> listarPedido() {
		return listaDePedido;
	}

	public void pedidoEmAberto(Cliente clienteLogado) {
		 
		pedido = new Pedido();
		//clienteLogado.setId(id);
		pedido.setCliente(clienteLogado);
		
		pedido.setListaProdPedido(carrinhoCompra);
		pedido.setStatus(2);
		listaDePedido.add(pedido);
		pedido.setDataPedido(new Date(System.currentTimeMillis()));
		//id = id +1;
	}
	public double confirmarPagamento(double pagamento, double precoTotal) {
		double resultado = 0;
		
		if (pagamento > precoTotal) {
			resultado = pagamento - precoTotal;
		}
		else if (pagamento < precoTotal) {
			resultado = precoTotal - pagamento;
		} else if (pagamento > precoTotal) {
			resultado = pagamento - precoTotal;
		} else {
			resultado = 0;
		}

		return resultado;

	}
	
	
	public void valorParaPagar() {
		double total = 0;

		for (int i = 0; i < retornarPedido().getListaProdPedido().size(); i++) {

			total = total + retornarPedido().getListaProdPedido().get(i).getPreco();

			pedido.setValorTotal(total);

		}

	}
	
	

}

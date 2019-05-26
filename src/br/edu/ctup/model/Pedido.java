package br.edu.ctup.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {

	private Cliente cliente ;
	private List<Produto> listaProdPedido  = new ArrayList<>();
	private double valorTotal;
	private int nrPedido;
	private Date dataPedido;
	private int Status;

	
	

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Produto> getListaProdPedido() {
		return listaProdPedido;
	}
	public void setListaProdPedido(List<Produto> carrinhoCompra) {
		this.listaProdPedido = carrinhoCompra;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public int getNrPedido() {
		return nrPedido;
	}
	public void setNrPedido(int nrPedido) {
		this.nrPedido = nrPedido;
	}
	
	public Date getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
}

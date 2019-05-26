package br.edu.ctup.model;

public class Produto {

	String descricao;
	double preco;
	
	
	public Produto() {}
	
	public Produto(String descricao, double valor) {
		this.descricao = descricao;
		this.preco = valor;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
}

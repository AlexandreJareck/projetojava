package br.edu.ctup.controller;

import java.util.ArrayList;
import java.util.List;

import br.edu.ctup.model.Cliente;

public class ControllerCliente {
	List<Cliente> listCliente = new ArrayList<Cliente>();
	Cliente cliente = new Cliente();
	static int index;

	public void cadastrarCliente(Cliente cliente) {
		cliente.setId(index);
		listCliente.add(cliente);
		index = index+1;
	}

	public List<Cliente> ListarCliente() {
		return listCliente;
	}

	public void alterarDadosCliente(int indice, Cliente cliente) {

		listCliente.get(indice).setNome(cliente.getNome());
		listCliente.get(indice).setSenha(cliente.getSenha());
		listCliente.get(indice).setLogin(cliente.getLogin());
		listCliente.get(indice).setEndereco(cliente.getEndereco());
		listCliente.get(indice).setTelefone(cliente.getTelefone());

	}

	public int autenticarCliente(String login, String senha) {
		int indice = -1;

		for (int i = 0; i < listCliente.size(); i++) {

			if (listCliente.get(i).getLogin().equals(login) && listCliente.get(i).getSenha().equals(senha)) {
				cliente = listCliente.get(i);
				indice = i;
				break;
			}
		}
		return indice;	}

	public boolean autenticarCpf(String cpf) {
		boolean confereCpf = true;
		for (int i = 0; i < listCliente.size(); i++) {
			if (listCliente.get(i).getCpf().equals(cpf)) {
				confereCpf = false;
				break;
			}
		}
		return confereCpf;
	}

	public boolean autenticarLogin(String login) {
		boolean confereLogin = true;
		for (int i = 0; i < listCliente.size(); i++) {
			if (listCliente.get(i).getLogin().equals(login)) {
				confereLogin = false;
				break;
			}
		}
		return confereLogin;
	}

	public Cliente clienteLogado() {
		return cliente;
	}

}

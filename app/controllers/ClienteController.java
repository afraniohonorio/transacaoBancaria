package controllers;

import models.Cliente;
import models.Conta;
import play.mvc.Controller;
import java.util.*;

public class ClienteController extends Controller {
	
	public static void inserirCliente(String nome, String cpf){
		
		try{
			
			Cliente cliente = new Cliente();
			cliente.inserir(nome, cpf);
			renderJSON("Cliente cadastrado com sucesso!! Seu id é: " + cliente.id);
			
		} catch(Exception e){
			
			renderJSON("Houve um problema ao cadastrar o cliente, excecao: " + e.getMessage());
			
		}
	}
	
	public static void excluirCliente(Long idCliente){
		
		try{
			
			
			Cliente.excluir(idCliente);
			renderJSON("Cliente excluido com sucesso!!");
			
		} catch(Exception e){
			
			renderJSON("Houve um problema ao excluir o cliente, excecao: " + e.getMessage());
		}
	}
	
	public static void atualizarCliente(Long idCliente, String nomeCliente, String cpfCliente){
		
		try{
			
			Cliente cliente = Cliente.findById(idCliente);
			cliente.atualizar(cliente, nomeCliente, cpfCliente);
			renderJSON("Cliente com identificador " + cliente.id + " atualizado com sucesso!!");
			
		} catch (Exception e){
			
			renderJSON("Houve um problema ao atualizar o cliente, excecao: " + e.getMessage());
		}		
	}
}

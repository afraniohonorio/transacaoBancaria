package controllers;

import java.util.List;
import models.Agencia;
import models.Cliente;
import models.Conta;
import play.mvc.Controller;

public class ContaController extends Controller{
	
	public static void inserirConta(Long numero, Long idCliente, Long idAgencia){
		
		try{
			
			Conta conta = new Conta();
			conta.inserir(numero, idCliente, idAgencia);
			renderJSON(conta);
			
		} catch (Exception e) {
			
			renderJSON("Houve um problema ao cadastrar a conta, exceção: " + e.getMessage());
			
		}
	}
	
	public static void excluirConta(Long idConta){
		
		try{
			
			Conta.excluir(idConta);
			
		} catch(Exception e){
			
			renderJSON("Houve um problema ao excluir a conta, exceção: " + e.getMessage());
		}
		
	}
	
	public static void verificarSaldo(Long idConta){
		
		try{
			
			Conta conta = new Conta();
			conta.saldo(idConta);
			renderJSON(conta.saldo);
			//System.out.println(conta.saldo);
			
		} catch (Exception e) {
			
			renderJSON("Houve um problema ao verificar o saldo da conta, exceção: " + e.getMessage());
		}
	}
	
	public static void sacarValor(Long idConta, Double valor){
		
		try{
			
			Conta contaCliente = Conta.findById(idConta);
			contaCliente.sacar(contaCliente, valor);
			renderJSON(contaCliente);
			
		} catch (Exception e) {
			
			renderJSON("Houve um problema ao sacar o valor da conta, exceção: " + e.getMessage());
		}
	}
	
	public static void depositarValor(Long idConta, Double valor){
		
		try{
			
			Conta contaCliente = Conta.findById(idConta);
			contaCliente.depositar(contaCliente, valor);
			renderJSON(contaCliente);
			
		} catch (Exception e) {
			
			renderJSON("Houve um problema ao depositar o valor na conta, exceção: " + e.getMessage());
		}
	}
	
	public static void transferirValor(Long idEnvia, Long idRecebe, Double valor){
		
		try{
			
			Conta.transferir(idEnvia, idRecebe, valor);
			
			renderJSON(valor + " transferido para conta com sucesso!");
			
		} catch (Exception e) {
			
			renderJSON("Houve um problema ao transferir o valor para a conta, exceção: " + e.getMessage());
		}
	}
	
	public static void allContas (){
		
		try{
			
			List<Conta> listaDeContas = Conta.retornaContas();
			for (Conta conta : listaDeContas)
				renderJSON(conta);
			
		} catch (Exception e) {
			
			renderJSON("Houve um problema ao retornar as contas, exceção: " + e.getMessage());
		}
	}
}

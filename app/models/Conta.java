package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.GenericModel;

@Entity
@Table(name = "conta")
public class Conta extends GenericModel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "numero", nullable = false, unique = true)
	public Long numero;
	
	@Column(name = "saldo", nullable = false)
	public Double saldo;
		
	public void inserir (Long numero, Long idCliente, Long idAgencia){
		
		if(numero == null || idCliente == null || idAgencia == null)
			throw new IllegalArgumentException("Os campos são obrigatórios");
			
		Cliente cliente = Cliente.findById(idCliente);
		Agencia agencia = Agencia.findById(idAgencia);
		
		
		for(Conta contaCli : cliente.contas) {
			
			for(Conta contaAgen : agencia.contas) {
				
				if(contaCli.numero == contaAgen.numero) {
					
					throw new IllegalArgumentException("Cliente já possui uma conta nessa agência");
				}
			}
		}			
		
		this.numero = numero;
		this.saldo = 0.0;
		cliente.contas.add(this);
		agencia.contas.add(this);
		this.save();
		cliente.save();
		agencia.save();
	}
	
	public static void excluir (Long id){
		
		if(id == null)
			throw new IllegalArgumentException("Os parametros não podem ser nulos.");
		
		Conta conta = Conta.findById(id);
		conta.delete();
		
	}
	
	public void saldo (Long id){
		
		if(id == null)
			throw new IllegalArgumentException("O campo id é obrigatório");
		
		this.findById(id);
	}
	
	public void sacar (Conta conta, Double valor){

		this.saldo = this.saldo - valor;
		this.save();
	}
	
	public void depositar (Conta conta, Double valor){

		this.saldo = this.saldo + valor;
		this.save();
	}
	
	public static void transferir (Long idEnvia, Long idRecebe, Double valor){

		Conta contaEnvia = Conta.findById(idEnvia);
		Conta contaRecebe = Conta.findById(idRecebe);
		
		if(valor > 1000){
			contaRecebe.saldo = contaRecebe.saldo + ted(contaEnvia, valor);
			contaEnvia.save();
			contaRecebe.save();
		}
		else {
			contaRecebe.saldo = contaRecebe.saldo + doc(contaEnvia, valor);
			contaEnvia.save();
			contaRecebe.save();
		}
	}
	
	private static Double ted(Conta conta, Double valor){
		if(conta.saldo >= (valor + 25.45))
			conta.saldo = conta.saldo - (valor + 25.45);
		else
			throw new IllegalArgumentException("Saldo insuficiente");
		
		return valor;
	}
	
	private static Double doc(Conta conta, Double valor){
		if(conta.saldo >= (valor + 12.96))
			conta.saldo = conta.saldo - (valor + 12.96);			
		else
			throw new IllegalArgumentException("Saldo insuficiente");
		
		return valor;
	}
	
	public static List<Conta> retornaContas (){
		
		List<Conta> listaContas = Conta.findAll();
		return listaContas;
	}
	
}

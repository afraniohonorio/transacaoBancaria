package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.GenericModel;
import play.db.jpa.JPABase;

@Entity
@Table(name = "cliente")
public class Cliente extends GenericModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public Long id;
	
	@Column(name = "nome", nullable = false)
	public String nome;
	
	@Column(name = "cpf", nullable = false, unique = true)
	public String cpf;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "id_cliente")
	public List<Conta> contas;
	
	public void inserir(String nome, String cpf){
		
		if(nome == null || cpf == null)
			throw new IllegalArgumentException("Os campos são obrigatórios");
		
		this.nome = nome;
		this.cpf = cpf;
		this.save();
		
	}
	
	public static void excluir(Long id){
		
		if(id == null)
			throw new IllegalArgumentException("Os parametros não podem ser nulos.");
		
		Cliente cliente = Cliente.findById(id);
		cliente.delete();
		
	}
	
	public void atualizar(Cliente cliente, String nome, String cpf){
		
		this.nome = nome;
		this.cpf = cpf;
		this.save();
		
	}	
}


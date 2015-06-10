package models;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import net.sf.oval.constraint.Email;
import play.db.jpa.GenericModel;

@Entity
@Table(name = "agencia")
public class Agencia extends GenericModel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public Long id;
	
	@Column(name = "codigo", nullable = false, unique = true)
	@NotNull(message = "codigo não pode ser nulo")
	public Long codigo;
	
	@Column(name = "localizacao", nullable = false)
	public String localizacao;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "id_agencia")
	public List<Conta> contas;
	
	public void inserir(Long codigo, String localizacao) { 
		
		if(codigo == null || localizacao == null)
			throw new IllegalArgumentException("Os parametros não podem ser nulos.");
		
		this.codigo = codigo;
		this.localizacao = localizacao;
		this.save();
		
	}
	
	public void excluir(Long idAgencia) { 
		
		if(idAgencia == null)
			throw new IllegalArgumentException("Os parametros não podem ser nulos.");
		
		Agencia agencia = Agencia.findById(idAgencia);
		agencia.delete();
	}
	
	public void atualizar(Long id, Long codigo, String localizacao) { 
		
		if(id == null || codigo == null || localizacao == null)
			throw new IllegalArgumentException("Os parametros não podem ser nulos.");
		
		Agencia agencia = Agencia.findById(id);
		agencia.codigo = codigo;
		agencia.localizacao = localizacao;
		agencia.save();
		
	}
}

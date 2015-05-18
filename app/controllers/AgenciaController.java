package controllers;

import play.mvc.Controller;
import java.util.*;
import models.*;

public class AgenciaController extends Controller{
	
	public static void inserirAgencia(Long codigo, String localizacao){
		
		try {
			
			Agencia agencia = new Agencia();
			agencia.inserir(codigo, localizacao);
			renderJSON("Agencia inserida com sucesso, id: " + agencia.id);
			
			
		} catch(Exception e) {
			
			renderJSON("Houve um problema ao inserir a agencia, excecao: " + e.getMessage());
			
		}	
	}
	
	public static void excluirAgencia (Long idAgencia){
		
		try{
			
			Agencia agencia = new Agencia();
			agencia.excluir(idAgencia);
			renderJSON("Agência " + agencia.localizacao + " excluído com sucesso!!");
			
		} catch (Exception e) {
			
			renderJSON("Houve um problema ao excluir a agência, excecao: " + e.getMessage());
			
		}		
	}
	
	public static void atualizarAgencia(Long idAgencia, Long codigoAgencia, String localizacaoAgencia){
		
		try{
			
			Agencia agencia = new Agencia();
			agencia.atualizar(idAgencia, codigoAgencia, localizacaoAgencia);
			renderJSON("Agencia com identificador " + idAgencia + " atualizada com sucesso!!");
			
		} catch (Exception e){
			
			renderJSON("Houve um problema ao atualizar a agência, excecao: " + e.getMessage());
		}		
	}
}

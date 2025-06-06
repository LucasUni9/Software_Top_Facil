package model;

import java.util.ArrayList;
import java.util.List;

public class ControleTarefa {
	
	public ControleTarefa() {
	}
	
	private List<Tarefa> tarefas = new ArrayList<>();
	
	public void adicionarTarefa(String nome, String descricao, String status) {
		tarefas.add(new Tarefa(nome, descricao, status));
	}
	
	public void listarTarefas( ) {
		//System.out.println("Lista De Tarefas: ");
		if (tarefas.isEmpty()) {
		//	System.out.println("Nenhuma tarefa cadastrada");
		} else {
			for(Tarefa tarefa : tarefas) {
				tarefa.consultarTarefa();
			}
		}
	}
	
	public void removerTarefa(int posicao) {
		tarefas.remove(posicao - 1);
	}
	
	public void alterarTarefa(int alteracao, String nome, String descricao) {
		tarefas.get(alteracao - 1).setNome(nome);
		tarefas.get(alteracao - 1).setDescricao(descricao);
	}

//Funca escolher status da tarefa PENDENTE,EXECUTANDO,CONCLUIDA
	public StatusTarefa escolherStatusTarefa(String status) {
		switch(status) {
			case "pendente" :
				return StatusTarefa.PENDENTE;
		case "executando" :
				return StatusTarefa.EXECUTANDO;
			case "concluida" :
				return StatusTarefa.CONCLUIDA;			
		}
		return null;
	}
}

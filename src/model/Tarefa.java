package model;

public class Tarefa {
	
	private String nome;
	private String descricao;
	private StatusTarefa status;
	
	public Tarefa() {
	}
	
	public Tarefa(String nome, String descricao, StatusTarefa status) {
		this.nome = nome;
		this.descricao = descricao;
		this.setStatus(status);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void consultarTarefa() {
		System.out.println("Nome: " + nome + " | Descrição: " + descricao + " | Status: " + status);
	}

	public StatusTarefa getStatus() {
		return status;
	}

	public void setStatus(StatusTarefa status) {
		this.status = status;
	}

}

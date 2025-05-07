package model;

public class Tarefa {
	
	public int idTarefa;
	private String nome;
	private String descricao;
	private String status;
	
	public Tarefa() {
	}
	
	public Tarefa(String nome, String descricao, String status) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getIdTarefa() {
		return nome;
	}

	public void setIdTarefa(String nome) {
		this.nome = nome;
	}


}

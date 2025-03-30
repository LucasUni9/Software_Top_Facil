package model;

public class Usuario {
	
	public String nome;
	public String email;
	public String senha;
	
	public int id;

	public Usuario(String nome, String email, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void consultarUsuario() {
		System.out.println("Nome: " + nome + " | Email: " + email + " | Senha: " + senha + "| ID: " + id);
	}
}
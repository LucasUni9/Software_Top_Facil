package connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Tarefa;
import model.Usuario;

public class ControleConexao {

	//Criar Banco
	public static void criarBanco() throws SQLException, IOException {
		String sql = """
				CREATE DATABASE IF NOT EXISTS topFacil
				""";

		Connection conexao = Conexao.conectarBanco();
		Statement stmt = conexao.createStatement();
		stmt.execute(sql);
		System.out.println("Banco de dados criado com sucesso!");
		
		conexao.close();
	}
	
	//Criar tabela usuario
	public static void criarTabelaUsuario() throws SQLException, IOException {
		String selecionarDatabase = "USE topfacil";
		String sql = """
				CREATE TABLE IF NOT EXISTS usuarios (
				id INT AUTO_INCREMENT PRIMARY KEY, 
				nome VARCHAR(100) NOT NULL,
				email VARCHAR(100) UNIQUE NOT NULL, 
				senha VARCHAR(255) NOT NULL );
				""";
		Connection conexao = Conexao.conectarBanco();		
		Statement stmt = conexao.createStatement();
		stmt.executeUpdate(selecionarDatabase); 
		stmt.executeUpdate(sql);
		System.out.println("Tabela usuarios criado com sucesso!");
		
		conexao.close();
	}

	//Criar Tabela tarefa
	public static void criarTabelaTarefa() throws SQLException, IOException {
		String selecionarDatabase = "USE topfacil";
		String sql = """
				CREATE TABLE IF NOT EXISTS tarefas (
				id INT AUTO_INCREMENT PRIMARY KEY,
				nome VARCHAR(100) NOT NULL,
				descricao TEXT,
				status ENUM('pendente', 'em andamento', 'concluida') DEFAULT 
				'pendente',
				usuario_id INT,
				FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
				);
				""";

		Connection conexao = Conexao.conectarBanco();
		Statement stmt = conexao.createStatement();
		stmt.executeUpdate(selecionarDatabase); 
		stmt.executeUpdate(sql);
		System.out.println("Tabela Tarefa criado com sucesso!");
		
		conexao.close();
	}

	//adicionar usuario
	public static void adicionarUsuario(Usuario usuario) throws SQLException, IOException {
		String selecionarDatabase = "USE topfacil";
		String sql = "INSERT IGNORE INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";

		Connection conexao = Conexao.conectarBanco();
	    PreparedStatement pstmt = conexao.prepareStatement(sql);
	    pstmt.setString(1, usuario.nome);
	    pstmt.setString(2, usuario.email);
	    pstmt.setString(3, usuario.senha);
	    
	    pstmt.executeUpdate(selecionarDatabase);
	    pstmt.executeUpdate();
		
		System.out.println("Usuario adicionado com sucesso!");
		
		pegarIdUsuario(usuario);
		conexao.close();
	}

	//adicionar tarefa
	 public static void adicionarTarefa(String nome, String descricao, String status, int idUsuario) throws SQLException, IOException {
		String selecionarDatabase = "USE topfacil";
		String sql = "INSERT INTO tarefas (nome, descricao, status, usuario_id) VALUES (?,?,?,?)";

		Connection conexao = Conexao.conectarBanco();
	    PreparedStatement pstmt = conexao.prepareStatement(sql);
	    pstmt.setString(1, nome);
	    pstmt.setString(2, descricao);
	    pstmt.setString(3, status);
	    pstmt.setInt(4, idUsuario);
	    
	    pstmt.executeUpdate(selecionarDatabase);
	    pstmt.executeUpdate();
	    
		System.out.println("Tarefa adicionada com sucesso!");
		
		conexao.close();
	}
	
	// pega o id do usuario no banco
	public static void pegarIdUsuario(Usuario usuario) throws SQLException, IOException {
		String selecionarDatabase = "USE topfacil";
		String sql = "SELECT id FROM usuarios WHERE nome = ? AND email = ? AND senha = ?";
	    
		Connection conexao = Conexao.conectarBanco();
	    PreparedStatement pstmt = conexao.prepareStatement(sql);
	    pstmt.setString(1, usuario.nome);
	    pstmt.setString(2, usuario.email);
	    pstmt.setString(3, usuario.senha);
	    
	    pstmt.executeUpdate(selecionarDatabase);
	    ResultSet rs = pstmt.executeQuery();
	    rs.next();
	    usuario.id = rs.getInt("id"); // Retorna o ID do usuÃ¡rio encontrado
	    conexao.close();
	}
	
	/* Atualizando um usuário
	public static void aualizandoUsuario(Usuario usuario) throws SQLException, IOException {
		String selecionarDatabase = "USE topfacil";
		String sql = "UPDATE usuarios SET nome = : ? WHERE id = ?; VALUES (?, ?)";

		Connection conexao = Conexao.conectarBanco();
	    PreparedStatement pstmt = conexao.prepareStatement(sql);
	    pstmt.setString(1, usuario.nome);
	    pstmt.setString(2, usuario.email);
	    
	    pstmt.executeUpdate(selecionarDatabase);
	    pstmt.executeUpdate();
		
		System.out.println("Usuario adicionado com sucesso!");
		
		pegarIdUsuario(usuario);
		conexao.close();
	} 
	
	Atualizando o status de uma tarefa
	UPDATE tarefas SET status = 'concluída' WHERE id = 1;
	public static void adicionarUsuario(Usuario usuario) throws SQLException, IOException {
		String selecionarDatabase = "USE topfacil";
		String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";

		Connection conexao = Conexao.conectarBanco();
	    PreparedStatement pstmt = conexao.prepareStatement(sql);
	    pstmt.setString(1, usuario.nome);
	    pstmt.setString(2, usuario.email);
	    pstmt.setString(3, usuario.senha);
	    
	    pstmt.executeUpdate(selecionarDatabase);
	    pstmt.executeUpdate();
		
		System.out.println("Usuario adicionado com sucesso!");
		
		pegarIdUsuario(usuario);
		conexao.close();
	} */
	
	//Deletando um usuário
	public static void deletarUsuario(Usuario usuario) throws SQLException, IOException {
		String selecionarDatabase = "USE topfacil";
		String sql = "DELETE FROM usuarios WHERE id = ? VALUES (?)";

		Connection conexao = Conexao.conectarBanco();
	    PreparedStatement pstmt = conexao.prepareStatement(sql);
	    pstmt.setInt(1, usuario.id);
	    
	    pstmt.executeUpdate(selecionarDatabase);
	    pstmt.executeUpdate();
		
		System.out.println("Usuario deletado com sucesso!");
		conexao.close();
	}

	//Deletando uma tarefa
	public static void deletarTarefa(Tarefa tarefa) throws SQLException, IOException {
		String selecionarDatabase = "USE topfacil";
		String sql = "DELETE FROM tarefas WHERE nome = ? VALUES (?)";

		Connection conexao = Conexao.conectarBanco();
	    PreparedStatement pstmt = conexao.prepareStatement(sql);
	    pstmt.setString(1, tarefa.getNome());
	    
	    pstmt.executeUpdate(selecionarDatabase);
	    pstmt.executeUpdate();
		
		System.out.println("Tarefa deletado com sucesso!");
		conexao.close();
	}
	/*
	Alterando a tabela para adicionar um novo campo
	ALTER TABLE usuarios ADD COLUMN telefone VARCHAR(20);
	public static void adicionarUsuario(Usuario usuario) throws SQLException, IOException {
		String selecionarDatabase = "USE topfacil";
		String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";

		Connection conexao = Conexao.conectarBanco();
	    PreparedStatement pstmt = conexao.prepareStatement(sql);
	    pstmt.setString(1, usuario.nome);
	    pstmt.setString(2, usuario.email);
	    pstmt.setString(3, usuario.senha);
	    
	    pstmt.executeUpdate(selecionarDatabase);
	    pstmt.executeUpdate();
		
		System.out.println("Usuario adicionado com sucesso!");
		
		pegarIdUsuario(usuario);
		conexao.close();
	}

*/
	
	
}

package connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
				status ENUM('pendente', 'em andamento', 'concluída') DEFAULT 
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

	//adicionar tarefa
	 public static void adicionarTarefa(String nome, String descricao) throws SQLException, IOException {
		String selecionarDatabase = "USE topfacil";
		 String sql = "INSERT INTO tarefas (nome, descricao, status, usuario_id) VALUES (?,?,?,?)";

		Connection conexao = Conexao.conectarBanco();
	    PreparedStatement pstmt = conexao.prepareStatement(sql);
	    pstmt.setString(1, nome);
	    pstmt.setString(2, descricao);
	    
	    pstmt.executeUpdate(selecionarDatabase);
	    pstmt.executeUpdate();
	    
		System.out.println("Tarefa adicionada com sucesso!");
		
		conexao.close();
	}
	
	public static void pegarIdUsuario(Usuario usuario) throws SQLException, IOException {
		String sql = "SELECT id FROM usuarios WHERE nome = ? AND email = ? AND senha = ?";
	    
		Connection conexao = Conexao.conectarBanco();
	    PreparedStatement pstmt = conexao.prepareStatement(sql);
	    pstmt.setString(1, usuario.nome);
	    pstmt.setString(2, usuario.email);
	    pstmt.setString(3, usuario.senha);

	    ResultSet rs = pstmt.executeQuery();
	    usuario.id = rs.getInt("id"); // Retorna o ID do usuário encontrado
	    conexao.close();
	}
}

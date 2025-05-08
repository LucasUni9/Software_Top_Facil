package connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.Alert;
import model.Usuario;

public class ControleConexao {

	//Criar Banco
	public static void criarBanco() throws IOException {
		try {	
			String sql = """
						CREATE DATABASE IF NOT EXISTS topFacil
						""";
		
				Connection conexao = Conexao.conectarBanco();
				Statement stmt = conexao.createStatement();
				stmt.execute(sql);
			//	System.out.println("Banco de dados criado com sucesso!");
				
				conexao.close();
		} catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Erro");
	        alert.setHeaderText("Ocorreu um erro");
	        alert.setContentText("Erro ao criar o banco de dados");
	        alert.showAndWait();
		}
	}
	
	//Criar tabela usuario
	public static void criarTabelaUsuario() throws IOException {
		try {
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
		//	System.out.println("Tabela usuarios criado com sucesso!");
			
			conexao.close();
		} catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Erro");
	        alert.setHeaderText("Ocorreu um erro");
	        alert.setContentText("Erro ao criar a tabela de usuários");
	        alert.showAndWait();
		}
	}

	//Criar Tabela tarefa
	public static void criarTabelaTarefa() throws IOException {
		try {
			String selecionarDatabase = "USE topfacil";
			String sql = """
					CREATE TABLE IF NOT EXISTS tarefas (
					id INT AUTO_INCREMENT PRIMARY KEY,
					nome VARCHAR(100) NOT NULL,
					descricao TEXT,
					status VARCHAR(20),
					usuario_id INT,
					FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
					);
					""";
	
			Connection conexao = Conexao.conectarBanco();
			Statement stmt = conexao.createStatement();
			stmt.executeUpdate(selecionarDatabase); 
			stmt.executeUpdate(sql);
			//System.out.println("Tabela Tarefa criado com sucesso!");
			
			conexao.close();
		} catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Erro");
	        alert.setHeaderText("Ocorreu um erro");
	        alert.setContentText("Erro ao criar a tabela de tarefas");
	        alert.showAndWait();
		}
	}

	//adicionar usuario
	public static void adicionarUsuario(Usuario usuario) throws SQLException, IOException {
		try {
			String selecionarDatabase = "USE topfacil";
			String sql = "INSERT IGNORE INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";
	
			Connection conexao = Conexao.conectarBanco();
		    PreparedStatement pstmt = conexao.prepareStatement(sql);
		    pstmt.setString(1, usuario.nome);
		    pstmt.setString(2, usuario.email);
		    pstmt.setString(3, usuario.senha);
		    
		    pstmt.executeUpdate(selecionarDatabase);
		    pstmt.executeUpdate();
			
			//System.out.println("Usuario adicionado com sucesso!");
			
			pegarIdUsuario(usuario.nome, usuario.email);
			conexao.close();
		} catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Erro");
	        alert.setHeaderText("Ocorreu um erro");
	        alert.setContentText("Erro ao adicionar usuário");
	        alert.showAndWait();
		}
	}

	//adicionar tarefa
	 public static void adicionarTarefa(String nome, String descricao, String status, int idUsuario) throws IOException {
		try {
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
		    
		    
			//System.out.println("Tarefa adicionada com sucesso!");
			
			conexao.close();
		} catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Erro");
	        alert.setHeaderText("Ocorreu um erro");
	        alert.setContentText("Erro ao adicionar a tarefa");
	        alert.showAndWait();
		}
	}
	 
	 public static int pegarIdTarefa(String nome, String descricao) throws SQLException, IOException {
		   
		 	String selecionarDatabase = "USE topfacil";
		    String sql = "SELECT id FROM tarefas WHERE nome = ? AND descricao = ? ";
		    
		    Connection conexao = Conexao.conectarBanco();

		    // Seleciona o banco de dados
		    Statement stmt = conexao.createStatement();
		    stmt.execute(selecionarDatabase);

		    // Prepara a consulta para buscar o ID
		    PreparedStatement pstmt = conexao.prepareStatement(sql);
		    pstmt.setString(1, nome);
		    pstmt.setString(2, descricao);
		    
		    ResultSet rs = pstmt.executeQuery();
		    
		    int id = -1; // Valor padrão para caso nenhum usuário seja encontrado
		    if (rs.next()) {
		        id = rs.getInt("id");
		    }

		    conexao.close();
		    return id;
		}
	
	 public static int pegarIdUsuario(String email, String senha) throws SQLException, IOException {
		    String selecionarDatabase = "USE topfacil";
		    String sql = "SELECT id FROM usuarios WHERE email = ? AND senha = ?";
		    
		    Connection conexao = Conexao.conectarBanco();

		    // Seleciona o banco de dados
		    Statement stmt = conexao.createStatement();
		    stmt.execute(selecionarDatabase);

		    // Prepara a consulta para buscar o ID
		    PreparedStatement pstmt = conexao.prepareStatement(sql);
		    pstmt.setString(1, email);
		    pstmt.setString(2, senha);
		    
		    ResultSet rs = pstmt.executeQuery();
		    
		    int id = -1; // Valor padrão para caso nenhum usuário seja encontrado
		    if (rs.next()) {
		        id = rs.getInt("id");
		    }

		    conexao.close();
		    return id;
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
	public static void deletarUsuario(Usuario usuario) throws IOException {
		try {
			String selecionarDatabase = "USE topfacil";
			String sql = "DELETE FROM usuarios WHERE id = ?";
	
			Connection conexao = Conexao.conectarBanco();
		    PreparedStatement pstmt = conexao.prepareStatement(sql);
		    pstmt.setInt(1, usuario.id);
		    
		    pstmt.executeUpdate(selecionarDatabase);
		    pstmt.executeUpdate();
			
			//System.out.println("Usuario deletado com sucesso!");
			conexao.close();
		} catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Erro");
	        alert.setHeaderText("Ocorreu um erro");
	        alert.setContentText("Erro ao deletar usuário");
	        alert.showAndWait();
		}
	}

	//Deletando uma tarefa
	public static void deletarTarefa(int id) throws IOException {
		try {
			String selecionarDatabase = "USE topfacil";
			String sql = "DELETE FROM tarefas WHERE id = ?";
	
			System.out.println("Deletando tarefa" + id);
			Connection conexao = Conexao.conectarBanco();
		    PreparedStatement pstmt = conexao.prepareStatement(sql);
		    pstmt.setLong(1, id);
		    
		    pstmt.executeUpdate(selecionarDatabase);
		    pstmt.executeUpdate();
			
			//System.out.println("Tarefa deletado com sucesso!");
			conexao.close();
		} catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Erro");
	        alert.setHeaderText("Ocorreu um erro");
	        alert.setContentText("Erro ao deletar a tarefa");
	        alert.showAndWait();
		}
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

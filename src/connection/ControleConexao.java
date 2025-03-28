package connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
		String sql = """
				CREATE TABLE IF NOT EXISTS usuarios (
				id INT AUTO_INCREMENT PRIMARY KEY, 
				nome VARCHAR(100) NOT NULL,
				email VARCHAR(100) UNIQUE NOT NULL, 
				senha VARCHAR(255) NOT NULL );
				""";

		Connection conexao = Conexao.conectarBanco();
		Statement stmt = conexao.createStatement();
		stmt.execute(sql);
		System.out.println("Tabela usuarios criado com sucesso!");
		
		conexao.close();
	}

	//Criar Tabela tarefa
 public static void criarTabelaTarefa throws SQLException, IOException {
		String sql = """
				CREATE TABLE IF NOT EXISTS tarefas (
				id INT AUTO_INCREMENT PRIMARY KEY,
				nome VARCHAR(100) NOT NULL,
				descricao TEXT,
				status ENUM('pendente', 'em andamento', 'concluída') DEFAULT 'pendente',
				usuario_id INT,
				FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
				);
				""";

		Connection conexao = Conexao.conectarBanco();
		Statement stmt = conexao.createStatement();
		stmt.execute(sql);
		System.out.println("Tabela Tarefa criado com sucesso!");
		
		conexao.close();
	}

	//adicionar usuario
	/*public static void criarBanco() throws SQLException, IOException {
		String sql = """
				CREATE DATABASE IF NOT EXISTS topFacil
				""";

		Connection conexao = Conexao.conectarBanco();
		Statement stmt = conexao.createStatement();
		stmt.execute(sql);
		System.out.println("Banco de dados criado com sucesso!");
		
		conexao.close();
	}*/

	//adicionar tarefa
	/* public static void criarBanco() throws SQLException, IOException {
		String sql = """
				CREATE DATABASE IF NOT EXISTS topFacil
				""";

		Connection conexao = Conexao.conectarBanco();
		Statement stmt = conexao.createStatement();
		stmt.execute(sql);
		System.out.println("Banco de dados criado com sucesso!");
		
		conexao.close();
	} */
	
	
	

}

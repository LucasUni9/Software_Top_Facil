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
	
	//Criar tabela tarefa
	//Criar Tabela usuario
	//adicionar usuario
	//adicionar tarefa
	
	
	
	

}

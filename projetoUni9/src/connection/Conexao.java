package connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexao {
		
	
	
	public static Connection conectarBanco() throws SQLException, IOException {
		Properties prop = getProperties();
		final String url = prop.getProperty("urlBanco");
		final String usuario = prop.getProperty("usuarioBanco");
		final String senha = prop.getProperty("senhaBanco");
		
		System.out.println("Conexão feita com sucesso!");
		return DriverManager.getConnection(url, usuario, senha);
	}
	
	public static Properties getProperties() throws IOException {
		Properties prop = new Properties();
		String caminho = "conexaoBanco.properties";
		prop.load(Conexao.class.getResourceAsStream(caminho));
		return prop;
	}
	
}

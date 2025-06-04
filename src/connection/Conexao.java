package connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class Conexao {
	
	public static Connection conectarBanco() throws Exception {
		try {
				Properties prop = getProperties();
				final String url = prop.getProperty("urlBanco");
				final String usuario = prop.getProperty("usuarioBanco");
				final String senha = prop.getProperty("senhaBanco");
				
				return DriverManager.getConnection(url, usuario, senha);
			
		} catch (Exception e) {
			throw new Exception("Erro ao conectar com o banco: " + e.getMessage());
		}
	}

	
	public static Properties getProperties() throws IOException {
		Properties prop = new Properties();
		String caminho = "conexaoBanco.properties";
		prop.load(Conexao.class.getResourceAsStream(caminho));
		return prop;
	}
	
}

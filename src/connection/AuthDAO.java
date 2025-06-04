package connection;

import java.io.IOException;
import java.sql.*;

public class AuthDAO {

    public static boolean login(String email, String senha) throws Exception {
    	String selecionarDatabase = "USE topfacil";
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";

        try (Connection conexao = Conexao.conectarBanco();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            stmt.setString(2, senha);
            
            stmt.executeQuery(selecionarDatabase);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Se existir um resultado, o login é válido
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Retorna falso caso ocorra um erro ou não encontre o usuário
    }

    
}

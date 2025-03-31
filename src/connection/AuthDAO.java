import java.sql.*;

public class AuthDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/seu_banco";
    private static final String USER = "seu_usuario";
    private static final String PASSWORD = "sua_senha";

    public static boolean login(String email, String senha) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Se existir um resultado, o login é válido
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Retorna falso caso ocorra um erro ou não encontre o usuário
    }

    public static void main(String[] args) {
        // Teste do login
        if (login("teste@email.com", "123456")) {
            System.out.println("Login bem-sucedido!");
        } else {
            System.out.println("Email ou senha incorretos.");
        }
    }
}

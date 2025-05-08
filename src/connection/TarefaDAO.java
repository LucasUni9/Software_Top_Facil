package connection;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {

    public List<TarefasBanco> buscarTarefasPorUsuario(int usuarioId) throws SQLException, IOException {
    	Connection conexao = Conexao.conectarBanco();
        List<TarefasBanco> listaTarefas = new ArrayList<>();
        String selecionarDatabase = "USE topfacil";
        String sql = "SELECT * FROM tarefas WHERE usuario_id = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.executeQuery(selecionarDatabase);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TarefasBanco tarefa = new TarefasBanco(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getString("status"),
                    rs.getInt("usuario_id")
                );
                listaTarefas.add(tarefa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTarefas;
    }
}

package connection;

public class Sessao {
	
    private static int idUsuario;

    public static void setIdUsuario(int id) {
        idUsuario = id;
    }

    public static int getIdUsuario() {
        return idUsuario;
    }
}

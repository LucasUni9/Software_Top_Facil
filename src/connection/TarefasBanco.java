public class TarefasBanco {
    private int id;
    private String titulo;
    private String descricao;
    private String status;
    private int usuarioId;

    // Construtor
    public TarefasBanco(int id, String titulo, String descricao, String status, int usuarioId) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.usuarioId = usuarioId;
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public String getStatus() { return status; }
    public int getUsuarioId() { return usuarioId; }
}

package view;

import javafx.scene.control.TextInputDialog;
import model.Tarefa;

import java.util.Optional;

public class EditarTarefaDialog {
	
    public static void mostrar(Tarefa tarefa, Card controller) {
        TextInputDialog dialogTitulo = new TextInputDialog(tarefa.getNome());
        dialogTitulo.setTitle("Editar Título");
        dialogTitulo.setHeaderText("Editar título da tarefa");
        Optional<String> novoTitulo = dialogTitulo.showAndWait();

        novoTitulo.ifPresent(t -> {
            tarefa.setNome(t);
            // Você pode fazer update no banco aqui
        });

        TextInputDialog dialogDescricao = new TextInputDialog(tarefa.getDescricao());
        dialogDescricao.setTitle("Editar Descrição");
        dialogDescricao.setHeaderText("Editar descrição da tarefa");
        Optional<String> novaDescricao = dialogDescricao.showAndWait();

        novaDescricao.ifPresent(d -> {
            tarefa.setDescricao(d);
            // Também atualizar no banco aqui se quiser
        });

        // Atualiza a visualização do card
        controller.atualizarVisual();
    }
}

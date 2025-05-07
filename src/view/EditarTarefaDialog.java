package view;

import javafx.scene.control.TextInputDialog;
import model.Tarefa;

import java.util.Optional;

public class EditarTarefaDialog {
	
    public static void mostrar(Tarefa tarefa, Card controller) {
        TextInputDialog dialogTitulo = new TextInputDialog(tarefa.getNome());
        dialogTitulo.setTitle("Editar T�tulo");
        dialogTitulo.setHeaderText("Editar t�tulo da tarefa");
        Optional<String> novoTitulo = dialogTitulo.showAndWait();

        novoTitulo.ifPresent(t -> {
            tarefa.setNome(t);
            // Voc� pode fazer update no banco aqui
        });

        TextInputDialog dialogDescricao = new TextInputDialog(tarefa.getDescricao());
        dialogDescricao.setTitle("Editar Descri��o");
        dialogDescricao.setHeaderText("Editar descri��o da tarefa");
        Optional<String> novaDescricao = dialogDescricao.showAndWait();

        novaDescricao.ifPresent(d -> {
            tarefa.setDescricao(d);
            // Tamb�m atualizar no banco aqui se quiser
        });

        // Atualiza a visualiza��o do card
        controller.atualizarVisual();
    }
}

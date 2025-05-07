package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Tarefa;

public class Card {
	
	   @FXML
	    public Button btnEditarCard;

	    @FXML
	    public Label lblDescricao;

	    @FXML
	    public Label lblTitulo;
	    
	    public String urlCard = "/javaFXML/CardTarefa.fxml";
	    
	    private Tarefa tarefa;
	    
	    ControleCena controleCena = new ControleCena();

	    @FXML
	    void btnEditarCard(ActionEvent event) {
	 
	    }
	    
	     // A tarefa associada a este card

	    public void setDados(Tarefa tarefa) {
	        this.tarefa = tarefa;
	        lblTitulo.setText(tarefa.getNome());
	        lblDescricao.setText(tarefa.getDescricao());

	        btnEditarCard.setOnAction(e -> {
				try {
					abrirTelaEdicao();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
	    }

	    private void abrirTelaEdicao() throws IOException {
	        // Exemplo: abrir uma nova tela/modal para editar
	       // EditarTarefaDialog.mostrar(tarefa, this); // Passa a tarefa e o próprio controller
	    	//System.out.println("Botão editar apertado" + tarefa.getNome());
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/javaFXML/TelaEditarTarefa.fxml"));
	        Parent root = loader.load();

	        // Obter o controlador da tela de criação
	       ControleEditarTarefa controle = loader.getController();
	       controle.setControleCard(this);
	       controle.setCardEditar(tarefa);

	        // Passar o controlador da tela principal para o controlador da tela de criação
	//        controle.setControleCard(this); // "this" se refere ao controlador da tela principal

	        // Exibir a tela de criação (abre em uma nova janela, por exemplo)
	        Stage stage = new Stage();
	        stage.setScene(new Scene(root));
	        stage.show();
	    }

	    // Permite atualizar os dados após edição
	    public void atualizarVisual() {
	        lblTitulo.setText(tarefa.getNome());
	        lblDescricao.setText(tarefa.getDescricao());
	    }
}

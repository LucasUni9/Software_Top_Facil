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
	    
	    private ControleTelaPrincipal controlePrincipal;

	    public void setControlePrincipal(ControleTelaPrincipal controlePrincipal) {
	        this.controlePrincipal = controlePrincipal;
	    }

	    @FXML
	    void btnEditarCard(ActionEvent event) {
	 
	    }

	    public void setDados(Tarefa tarefa) {
	        this.tarefa = tarefa;
	        lblTitulo.setText(tarefa.getNome());
	        lblDescricao.setText(tarefa.getDescricao());

	        btnEditarCard.setOnAction(e -> {
				try {
					abrirTelaEdicao();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
	    }

	    private void abrirTelaEdicao() throws IOException {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/javaFXML/TelaEditarTarefa.fxml"));
	        Parent root = loader.load();
	        
	        // Pega o controller da nova tela
	        ControleEditarTarefa controllerEditar = loader.getController();

	        // Passa a instância da tela principal
	        controllerEditar.setControlePrincipal(controlePrincipal);
	        

	        // Obter o controlador da tela de criação
	       ControleEditarTarefa controle = loader.getController();
	       controle.setControleCard(this);
	       controle.setCardEditar(tarefa);

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

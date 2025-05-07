package view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import connection.ControleConexao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Tarefa;

public class ControleEditarTarefa implements Initializable{

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnSalvar;

    @FXML
    private ComboBox<String> escolhaStatus;

    @FXML
    private TextArea txtDescricaoTarefa;

    @FXML
    private TextField txtNomeTarefa;
    
    public Card controleCard; 
    
    public Tarefa tarefa;
    

    @FXML
    public void btnCancelar(ActionEvent event) {
    	// Fechar a janela de criação e voltar para a tela principal
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void btnSalvar(ActionEvent event) throws IOException {
    	String nome = txtNomeTarefa.getText();
    	String descricao = txtDescricaoTarefa.getText();
    	String status = escolhaStatus.getValue();
    	
    	tarefa = new Tarefa(nome, descricao, status);
        // Passar os dados para a tela principal
        if (controleCard != null) {
           // controleTelaPrincipal.atualizarTelaPrincipal(titulo, descricao, status);
        	controleCard.setDados(tarefa);
        }

        // Fechar a janela de criação (caso você queira fechar após salvar)
        Stage stage = (Stage) btnSalvar.getScene().getWindow();
        stage.close();
    }
    
    private ControleTelaPrincipal controlePrincipal;

    public void setControlePrincipal(ControleTelaPrincipal controle) {
        this.controlePrincipal = controle;
    }
    
    @FXML
    public void btnExcluir(ActionEvent event) throws SQLException, IOException {
        
        int idTarefa = ControleConexao.pegarIdTarefa(tarefa.getNome(), tarefa.getDescricao());
        ControleConexao.deletarTarefa(idTarefa);

        // Carrega o FXML e força a atualização dos cards
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/javaFXML/TelaPrincipalFinal.fxml"));
        Parent root = loader.load();

        ControleTelaPrincipal controle = loader.getController();
        controle.carregarCards();
        System.out.println("Recarregando cards");
        // Fecha a janela atual
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
    public void setControleCard(Card controle) {
        this.controleCard = controle;
    }
    
    public void setCardEditar(Tarefa tarefa) {
    	setTarefa(tarefa);
    	txtNomeTarefa.setText(tarefa.getNome());
    	txtDescricaoTarefa.setText(tarefa.getDescricao());
    	switch (tarefa.getStatus()) {
    		case "A Fazer": {
    			escolhaStatus.setPromptText("A Fazer");
    			break;
    		
    		}
    		case "Exexutando": {
    			escolhaStatus.setPromptText("Executando");
    			break;
    		}
    		case "Concluido": {
    			escolhaStatus.setPromptText("Concluido");
    			break;
    		}
    		default: {
    			escolhaStatus.setPromptText("Indefinido");
    		}
    	}
    	
    }
    	
    public void setTarefa(Tarefa tarefa) {
    	this.tarefa = tarefa;
    }
    
    	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 escolhaStatus.getItems().addAll("A Fazer", "Executando", "Concluido");
		
	}

}

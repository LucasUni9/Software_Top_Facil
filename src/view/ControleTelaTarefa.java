package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControleTelaTarefa {
	
	@FXML
	public Button btnSalvar;
	
	@FXML
	public TextField txtNomeTarefaCriacao;
	
	@FXML
	public TextArea txtDescricaoTarefaCriacao;
	
	@FXML
	public Button btnCancelar;
	
	@FXML
	public ComboBox escolhaStatus;
	
	@FXML
	public Label lblTitulo;
	
	@FXML
	public Button btnEditarCard;
	
	@FXML
	public Label lblDescricao;
	
	
	public String urlCriacao = "/javaFXML/TelaCriacao.fxml";
	public String urlCard = "/javaFXML/CardTarefa.fxml";
	
	public String getUrl() {
		return urlCriacao;
	}
	
	public String getUrlCard() {
		return urlCard;
	}
	
    public void setDados(String titulo, String descricao) {
        lblTitulo.setText(titulo);
        lblDescricao.setText(descricao);
    }
    
    public void btnSalvar(ActionEvent event) throws IOException {
    	String titulo = txtNomeTarefaCriacao.getText();
    	String descricao = txtDescricaoTarefaCriacao.getText();
    }
    
    
}


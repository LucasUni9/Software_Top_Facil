package view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import connection.ControleConexao;
import connection.Sessao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControleTelaTarefa implements Initializable {

	@FXML
	public Button btnSalvar;
	
	@FXML
	public TextField txtNomeTarefaCriacao;
	
	@FXML
	public TextArea txtDescricaoTarefaCriacao;
	
	@FXML
	public Button btnCancelar;
	
	@FXML
	public ComboBox<String> escolhaStatus;
	
	private ControleTelaPrincipal controleTelaPrincipal;
	
	ControleCena controleCena = new ControleCena();	
	
	public String urlCriacao = "/javaFXML/TelaCriacao.fxml";
	
	public String getUrl() {
		return urlCriacao;
	}
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        escolhaStatus.getItems().addAll("A Fazer", "Executando", "Concluído");
    }
    
    public void setControleTelaPrincipal(ControleTelaPrincipal controle) {
        this.controleTelaPrincipal = controle;
    }
    
    @FXML
    public void btnSalvar(ActionEvent event) throws Exception {
        String titulo = txtNomeTarefaCriacao.getText();
        String descricao = txtDescricaoTarefaCriacao.getText();
        String status = escolhaStatus.getValue();
        
        // Passar os dados para a tela principal
        if (controleTelaPrincipal != null) {
        	controleTelaPrincipal.atualizarTelaPrincipal(titulo, descricao, status);
        }
        
        ControleConexao.adicionarTarefa(titulo, descricao, status, Sessao.getIdUsuario());
        // Fechar a janela de criação (caso você queira fechar após salvar)
        Stage stage = (Stage) btnSalvar.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void btnCancelar(ActionEvent event) throws IOException {
        // Fechar a janela de criação e voltar para a tela principal
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
    
    

}


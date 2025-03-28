package view;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControleTelaLogin extends Application {

	@FXML
	public Button btnLogin;
	
	@FXML
	public TextField txtEmail;
	
	@FXML
	public PasswordField txtSenha;
	
	
	public void pressionarBtnLogin (ActionEvent event) throws IOException { 
		String email = txtEmail.getText();
		String senha = txtSenha.getText();
		
		trocarPagina(event);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/javaFXML/Login.fxml"));
        primaryStage.setTitle("Simulador de Kanban");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
	}
	
    public void trocarPagina(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/javaFXML/TelaPrincipal.fxml"));
        Parent root = loader.load();
        
        // Obtém a janela (Stage) atual e troca a cena
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}

package view;

import java.io.IOException;
import java.sql.SQLException;

import connection.AuthDAO;
import connection.ControleConexao;
import connection.Sessao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Usuario;

public class ControleTelaLogin {
	
	@FXML
	public Button btnLogin;
	
	@FXML
	public TextField txtEmail;
	
	@FXML
	public PasswordField txtSenha;
	
	@FXML
	public Button btnCadastrese;
	
	@FXML
	public Button btnCadastrar;
	
	@FXML
	public TextField txtNomeCadastro;
	
	@FXML
	public TextField txtEmailCadastro;
	@FXML
	public PasswordField txtSenhaCadastro;
	
	ControleCena controleCena = new ControleCena();
	ControleConexao controleConexao = new ControleConexao();
	
	
	public String urlLogin = "/javaFXML/TelaLoginFinal.fxml";
	public String urlTelaCadastro = "/javaFXML/TelaCadastroFinal.fxml";
	
	public void btnLogin (ActionEvent event) throws IOException, SQLException { 
		String email = txtEmail.getText();
		String senha = txtSenha.getText();
		
		if (AuthDAO.login(email, senha)) {
			int idUsuario = ControleConexao.pegarIdUsuario(email, senha);
			Sessao.setIdUsuario(idUsuario);
			
	        controleCena.trocarPagina(event, controleCena.getUrl());
	    } else {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Erro");
	        alert.setHeaderText("Ocorreu um erro");
	        alert.setContentText("Login inválido");
	        alert.showAndWait();
	    }
	}
	
	public void btnCadastrese (ActionEvent event) throws IOException {
		controleCena.trocarPagina(event, urlTelaCadastro);
	}
	
	public void btnCadastrar (ActionEvent event) throws IOException, SQLException {
		String nome = txtNomeCadastro.getText();
		String email = txtEmailCadastro.getText();
		String senha = txtSenhaCadastro.getText();
		Usuario usuario = new Usuario(nome, email, senha);
		ControleConexao.adicionarUsuario(usuario);
		controleCena.trocarPagina(event, controleCena.getUrl());
	}
	

}

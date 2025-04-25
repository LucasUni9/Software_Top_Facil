package view;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControleCena extends Application{

	public String urlTelaPrincipal = "/javaFXML/TelaPrincipalFinal.fxml";
	
	
	public String getUrl() {
		return urlTelaPrincipal;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/javaFXML/TelaLoginFinal.fxml"));
        primaryStage.setTitle("Simulador de Kanban");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
	}
	
	
    public void trocarPagina(ActionEvent event, String url) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
    	Parent root = loader.load();
        
    	// Obtém a janela (Stage) atual e troca a cena
    	Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
    	stage.setScene(new Scene(root));
    	stage.show();

    }
}
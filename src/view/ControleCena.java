package view;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

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
	
	
	public FXMLLoader trocarPagina(ActionEvent event, String url) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
    	Parent root = loader.load();
        
        // Troca a cena
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

        return loader;
    }
}
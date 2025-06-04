package view;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.Node;

public class ControleCena extends Application{

	public static String erroConexao = null;
	
	public String urlTelaPrincipal = "/javaFXML/TelaPrincipalFinal.fxml";
	
	
	public String getUrl() {
		return urlTelaPrincipal;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		if (erroConexao != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Conexão");
            alert.setHeaderText("Não foi possível conectar ao banco de dados.");
            alert.setContentText(erroConexao);
            alert.showAndWait();
            // Aqui você pode encerrar o programa ou seguir sem banco
           // Platform.exit();
           // return;
		}
		
		Parent root = FXMLLoader.load(getClass().getResource("/javaFXML/TelaLoginFinal.fxml"));
        primaryStage.setTitle("Simulador de Kanban");
        primaryStage.setScene(new Scene(root));
       // primaryStage.setMaximized(false);
       // primaryStage.setMaximized(true);
        primaryStage.show();
	}
	
	
	public FXMLLoader trocarPagina(ActionEvent event, String url) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
    	Parent root = loader.load();
        
        // Troca a cena
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
       // stage.setMaximized(false); // reset
       // stage.setMaximized(true);
        //stage.setFullScreen(true);
        stage.show();

        return loader;
    }
}
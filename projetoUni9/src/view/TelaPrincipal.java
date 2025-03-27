package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaPrincipal extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
/*		Label nomeTarefa = new Label("Nome da tarefa");
		Label tituloTarefa = new Label("Descricao da tarefa");
		VBox boxTarefa = new VBox(nomeTarefa,tituloTarefa);
		boxTarefa.setAlignment(Pos.CENTER);
		boxTarefa.setStyle("-fx-background-color: Gainsboro; -fx-padding: 5px;");
		
		Label nomeTarefa2 = new Label("Nome da tarefa 2");
		Label tituloTarefa2 = new Label("Descrição da tarefa 2");
		VBox boxTarefa2 = new VBox(nomeTarefa2,tituloTarefa2);
		boxTarefa2.setAlignment(Pos.CENTER);
		boxTarefa2.setStyle("-fx-padding: 5px");
		
		Label nomeTarefa3 = new Label("Nome da tarefa 3");
		Label tituloTarefa3 = new Label("Descrição da tarefa 3");
		VBox boxTarefa3 = new VBox(nomeTarefa3,tituloTarefa3);
		boxTarefa3.setAlignment(Pos.CENTER);
		boxTarefa3.setStyle("-fx-background-color: Gainsboro; -fx-padding: 5px;"); */
		

        Label tituloTarefa = new Label("Titulo da tarefa ");
        Label descricaoTarefa = new Label("Descrição da tarefa ");
        VBox card = new VBox(tituloTarefa, descricaoTarefa);
        card.setPrefSize(400, 170);
        card.setStyle("-fx-background-color: white; -fx-border-color: #ccc; -fx-padding: 5; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 3);");
        
        Label tituloTarefa2 = new Label("Titulo da tarefa ");
        Label descricaoTarefa2 = new Label("Descrição da tarefa ");
        VBox card2 = new VBox(tituloTarefa2, descricaoTarefa2);
        card2.setPrefSize(400, 170);
        card2.setStyle("-fx-background-color: white; -fx-border-color: #ccc; -fx-padding: 5; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 3);");
  
        Label tituloTarefa3 = new Label("Titulo da tarefa ");
        Label descricaoTarefa3 = new Label("Descrição da tarefa ");
        VBox card3 = new VBox(tituloTarefa3, descricaoTarefa3);
        card3.setPrefSize(400, 170);
        card3.setStyle("-fx-background-color: white; -fx-border-color: #ccc; -fx-padding: 5; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 3);");
  
        Label tituloTarefa4 = new Label("Titulo da tarefa ");
        Label descricaoTarefa4 = new Label("Descrição da tarefa ");
        VBox card4 = new VBox(tituloTarefa4, descricaoTarefa4);
        card4.setPrefSize(400, 170);
        card4.setStyle("-fx-background-color: white; -fx-border-color: #ccc;  -fx-padding: 5; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 3);");
        
        Label tituloTarefa5 = new Label("Titulo da tarefa ");
        Label descricaoTarefa5 = new Label("Descrição da tarefa ");
        VBox card5 = new VBox(tituloTarefa5, descricaoTarefa5);
        card5.setPrefSize(400, 170);
        card5.setStyle("-fx-background-color: white; -fx-border-color: #ccc;  -fx-padding: 5; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 3);");
  
        Label tituloTarefa6 = new Label("Titulo da tarefa ");
        Label descricaoTarefa6 = new Label("Descrição da tarefa ");
        VBox card6 = new VBox(tituloTarefa6, descricaoTarefa6);
        card6.setPrefSize(400, 170);
        card6.setStyle("-fx-background-color: white; -fx-border-color: #ccc;  -fx-padding: 5; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 3);");
  
        VBox coluna1 = new VBox(5); 
        coluna1.setStyle("-fx-padding: 5; -fx-background-color: f4f4f4;");
        coluna1.getChildren().addAll(card, card4);
        
        VBox coluna2 = new VBox(5); 
        coluna2.setStyle("-fx-padding: 5; -fx-background-color: #f4f4f4;");
        coluna2.getChildren().addAll(card2, card5);
        
        VBox coluna3 = new VBox(5);
        coluna3.setStyle("-fx-padding: 5; -fx-background-color: f4f4f4;");
        coluna3.getChildren().addAll(card3, card6);
        
		HBox boxPrincipal = new HBox(coluna1, coluna2, coluna3);
		boxPrincipal.setAlignment(Pos.CENTER);
		
		Scene cenaPrincipal = new Scene(boxPrincipal, 1100, 800);
		
		primaryStage.setScene(cenaPrincipal);
		primaryStage.setTitle("Simulador de Kanban");
		primaryStage.show(); 	
	}

}

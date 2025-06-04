package view;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import connection.Sessao;
import connection.TarefaDAO;
import connection.TarefasBanco;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Tarefa;

public class ControleTelaPrincipal implements Initializable { 
    
	public String urlTelaPrincipal = "/javaFXML/TelaPrincipalFinal.fxml";
	
	@FXML
	public Button btnAdicionar;
	
	@FXML
	public VBox colunaAFazer;
	
	@FXML
	public VBox colunaExecutando;
	
	@FXML
	public VBox colunaConcluidos;

	
	TarefaDAO tarefaDAO = new TarefaDAO();
	ControleCena controleCena = new ControleCena();
	ControleTelaLogin telaLogin = new ControleTelaLogin();
	ControleEditarTarefa editarTarefa = new ControleEditarTarefa();
	
	private List<TarefasBanco> tarefasDoUsuario = new ArrayList<>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
			try {
				carregarCards();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void adicionarCard(String titulo, String descricao, String status) throws IOException {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/javaFXML/CardTarefa.fxml"));
			Parent cardNode = loader.load();
			
			Tarefa tarefa = new Tarefa(titulo, descricao, status);
			
			Card controler = loader.getController();
			controler.setDados(tarefa);
			controler.setControlePrincipal(this);
			VBox.setMargin(cardNode, new Insets(5, 10, 5, 10)); 
            
            switch (status) { 
			case "A Fazer": {
				colunaAFazer.getChildren().add(cardNode);
				break;
			}
			case "Executando": {
				colunaExecutando.getChildren().add(cardNode);
				break;
			}
			case "Concluido": {
				colunaConcluidos.getChildren().add(cardNode);
				break;
			}
			default:
				colunaAFazer.getChildren().add(cardNode);
			}  
    }

	// Método público para atualizar a tela principal com dados da tela de criação
    public void atualizarTelaPrincipal(String titulo, String descricao, String status) throws IOException {
       
        adicionarCard(titulo, descricao, status);
    }
    
    @FXML
    public void btnAdicionar(ActionEvent event) throws IOException {
        // Carregar o FXML da tela de criação
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/javaFXML/TelaCriacao.fxml"));
        Parent root = loader.load();

        // Obter o controlador da tela de criação
        ControleTelaTarefa controleTelatarefa = loader.getController();

        // Passar o controlador da tela principal para o controlador da tela de criação
        controleTelatarefa.setControleTelaPrincipal(this); // "this" se refere ao controlador da tela principal

        // Exibir a tela de criação (abre em uma nova janela, por exemplo)
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public void carregarCards() throws Exception { 
    	try { 
	    	tarefasDoUsuario.clear();
	    	if (colunaAFazer.getChildren().size() > 1) {
	    		colunaAFazer.getChildren().remove(1, colunaAFazer.getChildren().size());
	        }
	    	if (colunaExecutando.getChildren().size() > 1) {
	    		colunaExecutando.getChildren().remove(1, colunaExecutando.getChildren().size());
	        }
	    	if (colunaConcluidos.getChildren().size() > 1) {
	    		colunaConcluidos.getChildren().remove(1, colunaConcluidos.getChildren().size());
	        }
	        List<TarefasBanco> tarefasDoUsuario = tarefaDAO.buscarTarefasPorUsuario(Sessao.getIdUsuario());
	        for (TarefasBanco tarefa : tarefasDoUsuario) {
	        	//System.out.println("Carregando tarefa: " + tarefa.getTitulo());
	        	adicionarCard(tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getStatus());
        	} 
        } catch (Exception e) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	    	alert.setTitle("Erro");
	    	alert.setHeaderText("Ocorreu um erro");
	    	alert.setContentText("Erro ao carregar card da tarefa");
	    	alert.showAndWait();
        }
    	
      }
    
}


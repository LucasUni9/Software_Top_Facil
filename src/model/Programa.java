package model;

import java.io.IOException;
import java.sql.SQLException;
import connection.ControleConexao;
import javafx.application.Application;
import view.ControleCena;

public class Programa {
	
	public static void main(String[] args) throws SQLException, IOException {		
		try {
			ControleConexao.criarBanco();
			ControleConexao.criarTabelaUsuario();
			ControleConexao.criarTabelaTarefa();
        } catch (Exception e) {
        	ControleCena.erroConexao = e.getMessage(); // guarda a mensagem de erro
        }

		Application.launch(ControleCena.class, args); // inicia JavaFX
    }
}
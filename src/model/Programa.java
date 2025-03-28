package model;

import java.io.IOException;
import java.sql.SQLException;

import connection.Conexao;
import connection.ControleConexao;
import javafx.application.Application;
import view.ControleTelaLogin;

public class Programa {
	
	public static void main(String[] args) throws SQLException, IOException {
		
	//	Scanner entrada = new Scanner(System.in);
	//	String continuar = "s";
	//	ControleTarefa controleTarefa = new ControleTarefa();
		Conexao.conectarBanco();
		Application.launch(ControleTelaLogin.class, args);
		ControleConexao.criarBanco();
	/*	while(continuar.equalsIgnoreCase("s")) { 
			
			System.out.println("Oque deseja fazer: \n1-Adicionar Tarefa, 2-Consultar Tarefas, 3-Alterar Tarefa, 4-Remover Tarefa? ");
			int decisao = entrada.nextInt();
			
			switch(decisao) {
				case 1:
					System.out.print("Nome da tarefa: ");
					entrada.nextLine();
					String nome = entrada.nextLine();
					System.out.print("Descrição da tarefa: ");
					String descricao = entrada.nextLine();
					
					controleTarefa.adicionarTarefa(nome, descricao, StatusTarefa.PENDENTE);
					break;
					
				case 2:
					System.out.println("Consultar Tarefas");
					controleTarefa.listarTarefas();
					break;
					
				case 3:
					System.out.print("Qual tarefa quer alterar? ");
					int alteracao = entrada.nextInt();
					System.out.print("Nome novo da tarefa: ");
					entrada.nextLine();
					String nomeNovo = entrada.nextLine();
					System.out.print("Descrição nova da tarefa: ");
					String descricaoNova = entrada.nextLine();
					
					controleTarefa.alterarTarefa(alteracao, nomeNovo, descricaoNova);
					break;	
					
				case 4:
					System.out.print("Qual tarefa quer remover? ");
					int posicao = entrada.nextInt();
					controleTarefa.removerTarefa(posicao);
					break;
			}
			
			System.out.print("Quer continuar? ");
			continuar = entrada.next();
			
		}		*/
		
	//	entrada.close();
	}

}

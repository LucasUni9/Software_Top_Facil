package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import connection.AuthDAO;
import connection.Conexao;
import connection.ControleConexao;
import connection.TarefaDAO;
import connection.TarefasBanco;
import javafx.application.Application;
import view.ControleCena;

public class Programa {
	
	public static void main(String[] args) throws SQLException, IOException {
		
		Scanner entrada = new Scanner(System.in);
		String continuar = "s";
		ControleTarefa controleTarefa = new ControleTarefa();
		Connection conexao = Conexao.conectarBanco();
		Application.launch(ControleCena.class, args);
		ControleConexao.criarBanco();
		ControleConexao.criarTabelaUsuario();
		ControleConexao.criarTabelaTarefa();

		System.out.print("Login do usuario \nNome: ");
		String nomeUsuario = entrada.nextLine();
		System.out.print("Email: ");
		String email = entrada.nextLine();
		System.out.print("Senha: ");
		String senha = entrada.nextLine();
		
		Usuario usuario = new Usuario(nomeUsuario, email, senha);
        if (AuthDAO.login(email,senha)) {
            System.out.println("Login bem-sucedido!");
        } else {
            System.out.println("Email ou senha incorretos.");
        }
		ControleConexao.adicionarUsuario(usuario);
		ControleConexao.pegarIdUsuario(usuario);
		usuario.consultarUsuario();
		TarefaDAO tarefaDAO = new TarefaDAO(conexao);

		while(continuar.equalsIgnoreCase("s")) { 
			
			System.out.println("Oque deseja fazer: \n1-Adicionar Tarefa, 2-Consultar Tarefas, 3-Alterar Tarefa, 4-Remover Tarefa?");
			int decisao = entrada.nextInt();
			
			switch(decisao) {
				case 1:
					System.out.print("Nome da tarefa: ");
					entrada.nextLine();
					String nome = entrada.nextLine();
					System.out.print("Descrição da tarefa: ");
					String descricao = entrada.nextLine();
					System.out.println("Status da tarefa: pendente, em andamento ou conluida?");
					String status = entrada.nextLine();
	//				StatusTarefa statusDaTarefa = controleTarefa.escolherStatusTarefa(status);
					
	//				controleTarefa.adicionarTarefa(nome, descricao, statusDaTarefa);
					ControleConexao.adicionarTarefa(nome,descricao, status, usuario.id);
					break;
					
				case 2:
					System.out.println("Consultar Tarefas");
		//			controleTarefa.listarTarefas();
					// Agora você pode manipular as tarefas na sua aplicação
					List<TarefasBanco> tarefasDoUsuario = tarefaDAO.buscarTarefasPorUsuario(usuario.id);
					for (TarefasBanco tarefa : tarefasDoUsuario) {
						System.out.println("Tarefas: " + tarefa.getTitulo() + "| Descrição: " + tarefa.getDescricao());
					}
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
			
		}		
		
		entrada.close();
	}

}

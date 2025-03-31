package model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import connection.Conexao;
import connection.ControleConexao;

public class Programa {
	
	public static void main(String[] args) throws SQLException, IOException {
		
		Scanner entrada = new Scanner(System.in);
		String continuar = "s";
		ControleTarefa controleTarefa = new ControleTarefa();
		Conexao.conectarBanco();
	//	Application.launch(ControleTelaLogin.class, args);
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
		ControleConexao.adicionarUsuario(usuario);
		ControleConexao.pegarIdUsuario(usuario);
		usuario.consultarUsuario();
		

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
					StatusTarefa statusDaTarefa = controleTarefa.escolherStatusTarefa(status);
					
					controleTarefa.adicionarTarefa(nome, descricao, statusDaTarefa);
					ControleConexao.adicionarTarefa(nome,descricao, status, usuario.id);
					break;
					
				case 2:
					System.out.println("Consultar Tarefas");
					controleTarefa.listarTarefas();
					// Agora você pode manipular as tarefas na sua aplicação
					for (TarefasBanco tarefa : tarefasDoUsuario) {
			    System.out.println("Título: " + tarefa.getTitulo());
					}
					break;
					
				case 3:
					System.out.print("Qual tarefa quer alterar? ");
					int alteracao = entrada.nextInt();
					System.out.print("Nome novo da tarefa: ");
					entrada.nextLine();
					String nomeNovo = entrada.nextLine();
					System.out.print("DescriÃ§Ã£o nova da tarefa: ");
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

package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {

	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final FuncionarioRepository funcionarioRepository;
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner in) {
		while(system) {
			System.out.println("Qual acao de cargo deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Busca funcionario nome");
			System.out.println("2 - Busca funcionario nome, data contratação e salário maior");
			System.out.println("3 - Busca funcionario data contratação maior");
			System.out.println("4 - Pesquisa funcionario salário");
			int action = in.nextInt();
			
			switch (action) {
			case 1:
				buscaFuncionarioNome(in);
				break;
			case 2:
				buscaFuncionarioNomeSalarioMaiorData(in);
				break;
			case 3:
				buscaFuncionarioDataContratacao(in);
				break;
			case 4:
				pesquisaFuncionarioSalario();
				break;
			default:
				system = false;
				break;
			}
		}
	}
	
	private void buscaFuncionarioNome(Scanner in) {
		System.out.println("Qual nome deseja pesquisar? ");
		String nome = in.next();
		List<Funcionario> listFuncionarios = funcionarioRepository.findByNome(nome);
		listFuncionarios.forEach(System.out::println);
	}
	
	private void buscaFuncionarioNomeSalarioMaiorData(Scanner in) {
		System.out.println("Qual o nome deseja pesquisar? ");
		String nome = in.next();
		
		System.out.println("Qual data de contratação deseja pesquisar? ");
		String data = in.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		System.out.println("Qual o salário deseja pesquisar? ");
		Double salario = in.nextDouble();
		
		List<Funcionario> listFuncionarios = funcionarioRepository
				.findNomeDataContratacaoSalarioMaior(nome, salario, localDate);
		listFuncionarios.forEach(System.out::println);
	}
	
	private void buscaFuncionarioDataContratacao(Scanner in) {
		System.out.println("Qual data de contratação deseja pesquisar? ");
		String data = in.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		List<Funcionario> listFuncionarios = funcionarioRepository.findDataContratacaoMaior(localDate);
		listFuncionarios.forEach(System.out::println);
	}
	
	private void pesquisaFuncionarioSalario() {
		List<FuncionarioProjecao> listFuncionarios = funcionarioRepository.findFuncionarioSalario();
		listFuncionarios.forEach(f -> System.out.println("Funcionario> id: " + f.getId() 
			+ " | nome: " + f.getNome() + " | salário: " + f.getSalario()));
	}
}

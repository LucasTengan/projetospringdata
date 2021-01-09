package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService {

	private Boolean system = true;
	private final CargoRepository cargoRepository;

	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}

	public void inicial(Scanner in) {

		while (system) {
			System.out.println("Qual ação de cargo deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");

			int acao = in.nextInt();
			switch (acao) {
			case 1:
				salvar(in);
				break;
			case 2:
				atualizar(in);
				break;
			case 3:
				visualizar();
				break;
			case 4:
				deletar(in);
				break;
			default:
				system = false;
				break;
			}
		}
	}

	private void salvar(Scanner in) {
		System.out.println("Descrição do cargo");

		String descricao = in.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);

		cargoRepository.save(cargo);
		System.out.println("Cargo salvo");
	}

	private void atualizar(Scanner in) {
		System.out.println("Id do cargo que quer atualizar");
		int id = in.nextInt();

		System.out.println("Descrição do Cargo: ");
		String descricao = in.next();

		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);

		cargoRepository.save(cargo);
		System.out.println("Cargo atualizado");
	}
	
	private void visualizar() {
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(System.out::println);
	}
	
	private void deletar(Scanner in) {
		System.out.println("Id do cargo que quer deletar");
		int id = in.nextInt();
		
		cargoRepository.deleteById(id);
		System.out.println("Cargo deletado");
	}
}

package br.com.alura.spring.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Funcionario;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {

	// Uso de derivated queries: Query Creation
	List<Funcionario> findByNome(String nome);

	// Métodos parecidos, um com derivated query e outro com jpql
//	List<Funcionario> findByNomeAndSalarioGreaterThanAndDataContratacao(String nome, Double salario, LocalDate data);
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.salario >= :salario AND f.data = :data")
	List<Funcionario> findNomeDataContratacaoSalarioMaior(String nome, Double salario, LocalDate data);
	
	// Native queries
	@Query(value = "SELECT * FROM funcionarios f where f.data >= :data", nativeQuery = true)
	List<Funcionario> findDataContratacaoMaior(LocalDate data);
}

package br.com.reclameaqui.GestorDeReclamacoes.respository;

import br.com.reclameaqui.GestorDeReclamacoes.model.Empresa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EmpresaRepository extends MongoRepository<Empresa, String> {

  @Query("{'fantasia': ?0}")
  List<Empresa> findByFantasia(String nome);

}

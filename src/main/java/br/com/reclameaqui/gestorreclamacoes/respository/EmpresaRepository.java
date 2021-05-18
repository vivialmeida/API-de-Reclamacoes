package br.com.reclameaqui.gestorreclamacoes.respository;

import br.com.reclameaqui.gestorreclamacoes.model.Empresa;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmpresaRepository extends MongoRepository<Empresa, String> {

  List<Empresa> findEmpresaByFantasia(String nome);

}

package br.com.reclameaqui.GestorDeReclamacoes.respository;

import br.com.reclameaqui.GestorDeReclamacoes.model.Empresa;
import br.com.reclameaqui.GestorDeReclamacoes.model.Localidade;
import br.com.reclameaqui.GestorDeReclamacoes.model.Reclamacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReclamacaoRepository extends MongoRepository<Reclamacao, String> {


      @Query(value = "{'data':{ $gte: ?0, $lte : ?1}}")
      List<Reclamacao> getReclamacaoByDatahoraIsBetween(LocalDate dataInicio, LocalDate dataFim);


      @Query(value = "{'localidade.pais': ?0.pais}")
      List<Reclamacao> recuperarRecalmacoesPorLocalidade(Localidade localidade);


      @Query(value = "{'empresa': ?0}")
      List<Reclamacao> recuperarReclamacoesPorEmpresa(Empresa empresa);



//      @Query(value = "{'employees.name': ?0}", fields = "{'employees' : 0}")


}

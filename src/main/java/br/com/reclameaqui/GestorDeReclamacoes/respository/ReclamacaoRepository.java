package br.com.reclameaqui.GestorDeReclamacoes.respository;

import br.com.reclameaqui.GestorDeReclamacoes.model.Reclamacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReclamacaoRepository extends MongoRepository<Reclamacao, String> {


      List<Reclamacao> findReclamacaoByDataBetween(LocalDate dataInicio, LocalDate dataFim);

      List<Reclamacao> findReclamacaosByLocalidadeIsIn(List<String> idsLocalidade);

      List<Reclamacao> findReclamacaoByEmpresaIsIn(List<String> idsEmpresa);

}

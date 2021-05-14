package br.com.reclameaqui.GestorDeReclamacoes.respository;

import br.com.reclameaqui.GestorDeReclamacoes.model.Reclamacao;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReclamacaoRepository extends MongoRepository<Reclamacao, String> {

      List<Reclamacao> getReclamacaoByDatahoraIsBetween(LocalDate dataInicio, LocalDate dataFim);


}

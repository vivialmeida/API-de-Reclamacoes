package br.com.reclameaqui.GestorDeReclamacoes.respository;

import br.com.reclameaqui.GestorDeReclamacoes.model.Reclamacao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReclamacaoRepository extends MongoRepository<Reclamacao, String> {
}

package br.com.reclameaqui.GestorDeReclamacoes.respository;

import br.com.reclameaqui.GestorDeReclamacoes.model.Localidade;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocalidadeRepository extends MongoRepository<Localidade, String> {
}

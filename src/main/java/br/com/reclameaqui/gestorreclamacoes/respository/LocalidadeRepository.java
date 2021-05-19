package br.com.reclameaqui.gestorreclamacoes.respository;

import br.com.reclameaqui.gestorreclamacoes.model.Localidade;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocalidadeRepository extends MongoRepository<Localidade, String> {

      Localidade findLocalidadeByPaisAndCidadeAndEstado(String pais, String cidade, String estado);
}

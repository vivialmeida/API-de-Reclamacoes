package br.com.reclameaqui.gestorreclamacoes.respository.templates;

import br.com.reclameaqui.gestorreclamacoes.model.Localidade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class LocalidadeRepositoryTemplateImpl implements LocalidadeRepositoryTemplate {

      private final MongoTemplate mongoTemplate;


      public List<String> recuperarIdsLocalidade(Localidade localidade) {
            Query query = new Query();
            if (Objects.nonNull(localidade.getPais()) && !localidade.getPais().isEmpty()) {
                  query.addCriteria(Criteria.where("pais").is(localidade.getPais()));
            }
            if (Objects.nonNull(localidade.getEstado()) &&  !localidade.getEstado().isEmpty()) {
                  query.addCriteria(Criteria.where("estado").is(localidade.getEstado()));
            }
            if (Objects.nonNull(localidade.getCidade()) && !localidade.getCidade().isEmpty()) {
                  query.addCriteria(Criteria.where("cidade").is(localidade.getCidade()));
            }
            query.fields().include("id");
            List<Localidade> localidades = mongoTemplate.find(query, Localidade.class);

            return localidades.stream().map(Localidade::getId)
                    .collect(Collectors.toList());

      }
}

package br.com.reclameaqui.GestorDeReclamacoes.respository.templates;

import br.com.reclameaqui.GestorDeReclamacoes.model.Empresa;
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
public class EmpresaRepositoryTemplateImpl implements EmpresaRepositoryTemplate {

      private final MongoTemplate mongoTemplate;

      public List<String> recuperarReclamacoesPorEmpresa(Empresa empresa) {
            Query query = new Query();
            if (Objects.nonNull(empresa.getFantasia())) {
                  query.addCriteria(Criteria.where("fantasia").is(empresa.getFantasia()));
            }
            if (Objects.nonNull(empresa.getCnpj())) {
                  query.addCriteria(Criteria.where("cnpj").is(empresa.getCnpj()));
            }
            query.fields().include("id");
            List<Empresa> empresas = mongoTemplate.find(query, Empresa.class);

            return empresas.stream().map(Empresa::getId)
                    .collect(Collectors.toList());


      }



}

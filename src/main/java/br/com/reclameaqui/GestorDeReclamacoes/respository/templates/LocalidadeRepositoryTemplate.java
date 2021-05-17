package br.com.reclameaqui.GestorDeReclamacoes.respository.templates;

import br.com.reclameaqui.GestorDeReclamacoes.model.Localidade;

import java.util.List;

public interface LocalidadeRepositoryTemplate {

      List<String> recuperarIdsLocalidade(Localidade localidade);

}

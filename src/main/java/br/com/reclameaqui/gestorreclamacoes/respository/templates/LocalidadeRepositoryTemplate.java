package br.com.reclameaqui.gestorreclamacoes.respository.templates;

import br.com.reclameaqui.gestorreclamacoes.model.Localidade;

import java.util.List;

public interface LocalidadeRepositoryTemplate {

      List<String> recuperarIdsLocalidade(Localidade localidade);


}

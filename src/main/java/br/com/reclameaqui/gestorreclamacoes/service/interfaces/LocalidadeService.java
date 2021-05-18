package br.com.reclameaqui.gestorreclamacoes.service.interfaces;

import br.com.reclameaqui.gestorreclamacoes.model.Localidade;

import java.util.List;

public interface LocalidadeService {
   Localidade adicionarOuAtualizarLocalidade(Localidade localidade);

   List<String> recuperarIdsLocalidade(Localidade localidade);
}

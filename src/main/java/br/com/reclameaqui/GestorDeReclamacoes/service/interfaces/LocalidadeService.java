package br.com.reclameaqui.GestorDeReclamacoes.service.interfaces;

import br.com.reclameaqui.GestorDeReclamacoes.model.Localidade;

import java.util.List;

public interface LocalidadeService {
   Localidade adicionarOuAtualizarLocalidade(Localidade localidade);

   List<String> recuperarIdsLocalidade(Localidade localidade);
}

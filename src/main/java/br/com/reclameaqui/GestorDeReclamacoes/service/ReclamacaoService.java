package br.com.reclameaqui.GestorDeReclamacoes.service;

import br.com.reclameaqui.GestorDeReclamacoes.model.Reclamacao;

import java.util.List;

public interface ReclamacaoService {

      List<Reclamacao> recuperarReclamacoes();

      Reclamacao recuperarReclamacao(Long idReclamcao);

      void inserirReclamacao(Reclamacao reclamacao);

      Reclamacao atualizarReclamacao(Long idReclamacao, Reclamacao reclamacao);

      void excluirReclamacao(Long idReclamacao);







}

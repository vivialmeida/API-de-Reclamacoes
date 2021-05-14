package br.com.reclameaqui.GestorDeReclamacoes.service;

import br.com.reclameaqui.GestorDeReclamacoes.model.Reclamacao;
import br.com.reclameaqui.GestorDeReclamacoes.respository.ReclamacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReclamacaoServiceImpl implements ReclamacaoService {

      private final ReclamacaoRepository reclamacaoRepository;

      @Override
      public List<Reclamacao> recuperarReclamacoes() {
            List<Reclamacao> reclamacaos = reclamacaoRepository.findAll();
            System.out.println(reclamacaos);
            return reclamacaos;

      }

      @Override
      public Reclamacao recuperarReclamacao(Long idReclamcao) {
            return null;
      }

      @Override
      public void inserirReclamacao(Reclamacao reclamacao) {

      }

      @Override
      public Reclamacao atualizarReclamacao(Long idReclamacao, Reclamacao reclamacao) {
            return null;
      }

      @Override
      public void excluirReclamacao(Long idReclamacao) {

      }
}

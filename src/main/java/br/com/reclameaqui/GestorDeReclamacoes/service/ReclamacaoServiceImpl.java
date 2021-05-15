package br.com.reclameaqui.GestorDeReclamacoes.service;

import br.com.reclameaqui.GestorDeReclamacoes.model.Reclamacao;
import br.com.reclameaqui.GestorDeReclamacoes.respository.ReclamacaoRepository;
import br.com.reclameaqui.GestorDeReclamacoes.service.exception.ReclamacaoValidationException;
import br.com.reclameaqui.GestorDeReclamacoes.service.interfaces.ReclamacaoService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReclamacaoServiceImpl implements ReclamacaoService {

      private final ReclamacaoRepository reclamacaoRepository;

      @Override
      public List<Reclamacao> recuperarReclamacoes() {
            List<Reclamacao> reclamacaos = reclamacaoRepository.findAll();
             return reclamacaos;

      }

      @Override
      public Iterable<Reclamacao> recuperarReclamacaoPorId(String idReclamacao) {
            return  reclamacaoRepository.findAllById(Collections.singleton(idReclamacao));
      }

      @Override
      public List<Reclamacao> recuperarReclamacaoPorData(LocalDate dataInicio, LocalDate dataFim) {
            if (Objects.nonNull(dataFim)){
                  validarData(dataInicio, dataFim);
            }

           return reclamacaoRepository.getReclamacaoByDatahoraIsBetween(dataInicio,dataFim);

      }

      private void validarData(LocalDate dataInicio, LocalDate dataFim){
            if(dataFim.isBefore(dataInicio)){
                  throw new ReclamacaoValidationException("Data fim é maior que a data de inicio");
            }
      }

      @Override
      public List<Reclamacao> recuperarReclamacaoPorEmpresa(String nomeEmpresa, String cnpjEmpresa) {
            return null;
      }

      @Override
      public List<Reclamacao> recuperarReclamacaoPorLocalidade(String pais, String estado, String cidade) {
            return null;
      }

      @Override
      public void inserirReclamacao(Reclamacao reclamacao) {
            reclamacaoRepository.insert(reclamacao);

      }

      @Override
      public Reclamacao atualizarReclamacao(Reclamacao reclamacao) {
          return reclamacaoRepository.save(reclamacao);
      }

      @Override
      public void excluirReclamacao(String idReclamacao) {
            reclamacaoRepository.deleteById(idReclamacao);
      }
}

package br.com.reclameaqui.GestorDeReclamacoes.service;

import br.com.reclameaqui.GestorDeReclamacoes.model.Empresa;
import br.com.reclameaqui.GestorDeReclamacoes.model.Localidade;
import br.com.reclameaqui.GestorDeReclamacoes.model.Reclamacao;
import br.com.reclameaqui.GestorDeReclamacoes.respository.ReclamacaoRepository;
import br.com.reclameaqui.GestorDeReclamacoes.respository.templates.EmpresaRepositoryTemplate;
import br.com.reclameaqui.GestorDeReclamacoes.service.exception.ReclamacaoValidationException;
import br.com.reclameaqui.GestorDeReclamacoes.service.interfaces.EmpresaService;
import br.com.reclameaqui.GestorDeReclamacoes.service.interfaces.LocalidadeService;
import br.com.reclameaqui.GestorDeReclamacoes.service.interfaces.ReclamacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReclamacaoServiceImpl implements ReclamacaoService {

      private final ReclamacaoRepository reclamacaoRepository;
      private final EmpresaRepositoryTemplate reclamacaoRepositoryTemplate;
      private final LocalidadeService localidadeService;
      private final EmpresaService empresaService;

      @Override
      public Page<Reclamacao> recuperarReclamacoes(Integer page, Integer size) {
            Pageable pageable = PageRequest.of(page, size);
            Page<Reclamacao> reclamacaos = reclamacaoRepository.findAll(pageable);
            return reclamacaos;

      }

      @Override
      public Reclamacao recuperarReclamacaoPorId(String idReclamacao) {
            Optional<Reclamacao> reclamacoes = reclamacaoRepository.findById(idReclamacao);
            if (!reclamacoes.isPresent()) {
                  throw new ReclamacaoValidationException("Nenhuma reclamação encontrada para o id informado!");
            }
            return reclamacoes.get();
      }

      @Override
      public List<Reclamacao> recuperarReclamacaoPorData(LocalDate dataInicio, LocalDate dataFim) {
            if (Objects.nonNull(dataFim)) {
                  validarData(dataInicio, dataFim);
            }

            List<Reclamacao> reclamacoes = reclamacaoRepository.findReclamacaoByDataBetween(dataInicio, dataFim);
            if (reclamacoes.isEmpty()) {
                  throw new ReclamacaoValidationException("Nenhuma reclamação encontrada para a data informada! ");
            }

            return reclamacoes;
      }

      @Override
      public List<Reclamacao> recuperarReclamacaoPorEmpresa(String nomeEmpresa, String cnpjEmpresa) {
            Empresa empresa = Empresa.builder()
                    .fantasia(nomeEmpresa)
                    .cnpj(cnpjEmpresa)
                    .build();

            validarEmpresa(empresa);

            List<String> idsEmpresa = reclamacaoRepositoryTemplate.recuperarReclamacoesPorEmpresa(empresa);

            List<Reclamacao> reclamacoes = reclamacaoRepository.findReclamacaoByEmpresaIsIn(idsEmpresa);
            if (reclamacoes.isEmpty()) {
                  throw new ReclamacaoValidationException("Nenhuma reclamação encontrada para a empresa informada!");
            }

            return reclamacoes;

      }

      @Override
      public List<Reclamacao> recuperarReclamacaoPorLocalidade(String pais, String estado, String cidade) {
            Localidade localidade = Localidade.builder()
                    .pais(pais)
                    .estado(estado)
                    .cidade(cidade)
                    .build();

            validarLocalidade(localidade);

            List<String> idsLocalidades = localidadeService.recuperarIdsLocalidade(localidade);

            List<Reclamacao> reclamacoes = reclamacaoRepository.findReclamacaosByLocalidadeIsIn(idsLocalidades);
            if (reclamacoes.isEmpty()) {
                  throw new ReclamacaoValidationException("Nenhuma reclamação encontrada para a localidade informada!");
            }

            return reclamacoes;

      }

      @Override
      public Reclamacao inserirReclamacao(Reclamacao reclamacao) {
            Localidade localidade = localidadeService.adicionarOuAtualizarLocalidade(reclamacao.getLocalidade());
            Empresa empresa = empresaService.adicionarOuAtualizarEmpresa(reclamacao.getEmpresa());
            reclamacao.setLocalidade(localidade);
            reclamacao.setEmpresa(empresa);
            return reclamacaoRepository.insert(reclamacao);

      }

      @Override
      public Reclamacao atualizarReclamacao(String id, Reclamacao reclamacao) {
            reclamacao.setId(id);
            empresaService.adicionarOuAtualizarEmpresa(reclamacao.getEmpresa());
            localidadeService.adicionarOuAtualizarLocalidade(reclamacao.getLocalidade());
            return reclamacaoRepository.save(reclamacao);
      }

      @Override
      public void excluirReclamacao(String idReclamacao) {
            reclamacaoRepository.deleteById(idReclamacao);
      }

      private void validarData(LocalDate dataInicio, LocalDate dataFim) {
            if (dataFim.isBefore(dataInicio)) {
                  throw new ReclamacaoValidationException("Data fim é menor que a data de inicio");
            }
      }

      private void validarLocalidade(Localidade localidade) {
            if (Objects.isNull(localidade.getPais()) && Objects.isNull(localidade.getEstado())
                    && Objects.isNull(localidade.getCidade())) {
                  throw new ReclamacaoValidationException("Filtro de localidade nao informado");
            }
      }

      private void validarEmpresa(Empresa empresa) {
            if (Objects.isNull(empresa.getCnpj()) && Objects.isNull(empresa.getFantasia())) {
                  throw new ReclamacaoValidationException("Filtro de empresa nao informado");
            }
      }
}

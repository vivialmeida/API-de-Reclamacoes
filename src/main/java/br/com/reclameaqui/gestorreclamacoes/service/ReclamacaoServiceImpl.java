package br.com.reclameaqui.gestorreclamacoes.service;

import br.com.reclameaqui.gestorreclamacoes.model.Empresa;
import br.com.reclameaqui.gestorreclamacoes.model.Localidade;
import br.com.reclameaqui.gestorreclamacoes.model.Reclamacao;
import br.com.reclameaqui.gestorreclamacoes.model.dto.ReclamacaoDTO;
import br.com.reclameaqui.gestorreclamacoes.respository.ReclamacaoRepository;
import br.com.reclameaqui.gestorreclamacoes.respository.templates.EmpresaRepositoryTemplate;
import br.com.reclameaqui.gestorreclamacoes.service.exception.NaoEncontradoException;
import br.com.reclameaqui.gestorreclamacoes.service.exception.ReclamacaoValidationException;
import br.com.reclameaqui.gestorreclamacoes.service.interfaces.EmpresaService;
import br.com.reclameaqui.gestorreclamacoes.service.interfaces.LocalidadeService;
import br.com.reclameaqui.gestorreclamacoes.service.interfaces.ReclamacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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
      public PageImpl<ReclamacaoDTO> recuperarReclamacoes(Integer page, Integer size) {
            Pageable pageable = PageRequest.of(page, size);
            Page<Reclamacao> reclamacaos = reclamacaoRepository.findAll(pageable);
            if (!reclamacaos.hasContent()){
                  throw new NaoEncontradoException("Não ha registro de reclamacoes");
            }

            return new PageImpl<ReclamacaoDTO>(convertListReclamacaoEmReclamacaoDTO(reclamacaos.getContent()), pageable, reclamacaos.getTotalElements());

      }

      @Override
      public ReclamacaoDTO recuperarReclamacaoPorId(String idReclamacao) {
            Optional<Reclamacao> reclamacoes = reclamacaoRepository.findById(idReclamacao);
            if (!reclamacoes.isPresent()) {
                  throw new NaoEncontradoException("Nenhuma reclamação encontrada para o id informado!");
            }
            return ReclamacaoDTO.convertDTOReclamacao(reclamacoes.get());
      }

      @Override
      public List<ReclamacaoDTO> recuperarReclamacaoPorData(LocalDate dataInicio, LocalDate dataFim) {
            if (Objects.nonNull(dataFim)) {
                  validarData(dataInicio, dataFim);
            }

            List<Reclamacao> reclamacoes = reclamacaoRepository.findReclamacaoByDataBetween(dataInicio, dataFim);
            if (reclamacoes.isEmpty()) {
                  throw new NaoEncontradoException("Nenhuma reclamação encontrada para a data informada! ");
            }

            return convertListReclamacaoEmReclamacaoDTO(reclamacoes);
      }

      @Override
      public List<ReclamacaoDTO> recuperarReclamacaoPorEmpresa(String nomeEmpresa, String cnpjEmpresa) {
            Empresa empresa = Empresa.builder()
                    .fantasia(nomeEmpresa)
                    .cnpj(cnpjEmpresa)
                    .build();

            validarEmpresa(empresa);

            List<String> idsEmpresa = reclamacaoRepositoryTemplate.recuperarReclamacoesPorEmpresa(empresa);

            List<Reclamacao> reclamacoes = reclamacaoRepository.findReclamacaoByEmpresaIsIn(idsEmpresa);
            if (reclamacoes.isEmpty()) {
                  throw new NaoEncontradoException("Nenhuma reclamação encontrada para a empresa informada!");
            }

            return convertListReclamacaoEmReclamacaoDTO(reclamacoes);

      }

      @Override
      public List<ReclamacaoDTO> recuperarReclamacaoPorLocalidade(String pais, String estado, String cidade) {
            Localidade localidade = Localidade.builder()
                    .pais(pais)
                    .estado(estado)
                    .cidade(cidade)
                    .build();

            validarLocalidade(localidade);

            List<String> idsLocalidades = localidadeService.recuperarIdsLocalidade(localidade);

            List<Reclamacao> reclamacoes = reclamacaoRepository.findReclamacaosByLocalidadeIsIn(idsLocalidades);
            if (reclamacoes.isEmpty()) {
                  throw new NaoEncontradoException("Nenhuma reclamação encontrada para a localidade informada!");
            }

            return convertListReclamacaoEmReclamacaoDTO(reclamacoes);

      }

      @Override
      public ReclamacaoDTO inserirReclamacao(Reclamacao reclamacao) {
            Localidade localidade = localidadeService.adicionarOuAtualizarLocalidade(reclamacao.getLocalidade());
            Empresa empresa = empresaService.adicionarOuAtualizarEmpresa(reclamacao.getEmpresa());
            reclamacao.setLocalidade(localidade);
            reclamacao.setEmpresa(empresa);
            return ReclamacaoDTO.convertDTOReclamacao(reclamacaoRepository.insert(reclamacao));

      }

      @Override
      public ReclamacaoDTO atualizarReclamacao(String id, Reclamacao reclamacao) {
            reclamacao.setId(id);
            empresaService.adicionarOuAtualizarEmpresa(reclamacao.getEmpresa());
            localidadeService.adicionarOuAtualizarLocalidade(reclamacao.getLocalidade());
            return ReclamacaoDTO.convertDTOReclamacao(reclamacaoRepository.save(reclamacao));
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

      private List<ReclamacaoDTO> convertListReclamacaoEmReclamacaoDTO(List<Reclamacao> reclamacoes) {
            List<ReclamacaoDTO> reclamacoesDTO = new ArrayList<>();
            reclamacoes.forEach(reclamacao -> reclamacoesDTO.add(ReclamacaoDTO.convertDTOReclamacao(reclamacao)));
            return reclamacoesDTO;
      }


}

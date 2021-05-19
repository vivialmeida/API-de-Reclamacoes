package br.com.reclameaqui.gestorreclamacoes.service;

import br.com.reclameaqui.gestorreclamacoes.model.Empresa;
import br.com.reclameaqui.gestorreclamacoes.model.dto.EmpresaDTO;
import br.com.reclameaqui.gestorreclamacoes.respository.EmpresaRepository;
import br.com.reclameaqui.gestorreclamacoes.service.exception.NaoEncontradoException;
import br.com.reclameaqui.gestorreclamacoes.service.interfaces.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {

      private final EmpresaRepository empresaRepository;

      @Override
      public Empresa adicionarOuAtualizarEmpresa(Empresa empresa) {

            Empresa empresaJaExistente = recuperarEmpresaCasoJaExista(empresa);
            if (Objects.nonNull(empresaJaExistente)){
                  return empresaJaExistente;
            }
            return empresaRepository.save(empresa);
      }

      @Override
      public HashSet<EmpresaDTO> recuperarEmpresas() {
            List<Empresa> empresas = empresaRepository.findAll();
            if (Objects.isNull(empresas) || empresas.isEmpty()) {
                  throw new NaoEncontradoException("Não ha registro de empresas");
            }
            return new HashSet<>(convertListEmpresaEmEmpresaDTO(empresas));

      }

      @Override
      public HashSet<EmpresaDTO> recuperarEmpresaPor(String nomeEmpresa) {
            List<Empresa> empresas = empresaRepository.findEmpresaByFantasia(nomeEmpresa);
            if (Objects.isNull(empresas) || empresas.isEmpty()) {
                  throw new NaoEncontradoException("Não registo de empresa com o nome informado! ");
            }
            return new HashSet<>(convertListEmpresaEmEmpresaDTO(empresas));

      }

      private List<EmpresaDTO> convertListEmpresaEmEmpresaDTO(List<Empresa> empresas) {
            List<EmpresaDTO> empresasDTO = new ArrayList<>();
            empresas.forEach(empresa -> empresasDTO.add(EmpresaDTO.convertDTOEmpresa(empresa)));
            return empresasDTO;
      }

      private Empresa recuperarEmpresaCasoJaExista(Empresa empresa){
            ExampleMatcher modelMatcher = ExampleMatcher.matching()
                    .withIgnorePaths("id")
                    .withMatcher("fantasia", ignoreCase())
                    .withMatcher("nome", ignoreCase());

            Example<Empresa> example = Example.of(empresa, modelMatcher);
            empresaRepository.exists(example);
            return empresaRepository.findEmpresaByFantasiaAndCnpj(empresa.getFantasia(), empresa.getCnpj());

      }
}
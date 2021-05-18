package br.com.reclameaqui.gestorreclamacoes.service;

import br.com.reclameaqui.gestorreclamacoes.model.Empresa;
import br.com.reclameaqui.gestorreclamacoes.respository.EmpresaRepository;
import br.com.reclameaqui.gestorreclamacoes.service.exception.NaoEncontradoException;
import br.com.reclameaqui.gestorreclamacoes.service.interfaces.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {

      private final EmpresaRepository empresaRepository;

      @Override
      public Empresa adicionarOuAtualizarEmpresa(Empresa empresa) {
            return empresaRepository.save(empresa);
      }

      @Override
      public HashSet<Empresa> recuperarEmpresas() {
            List<Empresa> empresas = empresaRepository.findAll();
            if (Objects.isNull(empresas) || empresas.isEmpty()){
                 throw new  NaoEncontradoException("Não ha registro de empresas");
            }
            return new HashSet<>(empresas);

      }

      @Override
      public HashSet<Empresa> recuperarEmpresaPor(String nomeEmpresa) {
            List<Empresa> empresas= empresaRepository.findEmpresaByFantasia(nomeEmpresa);
            if (Objects.isNull(empresas) || empresas.isEmpty()){
                  throw new  NaoEncontradoException("Não registo de empresa com o nome informado! ");
            }
             return new HashSet<>(empresas);

      }
}

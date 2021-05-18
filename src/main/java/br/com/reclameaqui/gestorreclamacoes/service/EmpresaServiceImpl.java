package br.com.reclameaqui.gestorreclamacoes.service;

import br.com.reclameaqui.gestorreclamacoes.model.Empresa;
import br.com.reclameaqui.gestorreclamacoes.respository.EmpresaRepository;
import br.com.reclameaqui.gestorreclamacoes.service.interfaces.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashSet;

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
            return new HashSet<>(empresaRepository.findAll());
      }

      @Override
      public HashSet<Empresa> recuperarEmpresaPor(String nomeEmpresa) {
             return new HashSet<>(empresaRepository.findEmpresaByFantasia(nomeEmpresa));

      }
}

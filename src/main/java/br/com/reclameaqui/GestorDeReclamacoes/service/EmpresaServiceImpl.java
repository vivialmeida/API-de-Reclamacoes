package br.com.reclameaqui.GestorDeReclamacoes.service;

import br.com.reclameaqui.GestorDeReclamacoes.model.Empresa;
import br.com.reclameaqui.GestorDeReclamacoes.service.interfaces.EmpresaService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmpresaServiceImpl implements EmpresaService {
      @Override
      public List<Empresa> recuperarEmpresaPor(String nomeEmpresa, String cnpjEmpresa) {
            return null;
      }
}

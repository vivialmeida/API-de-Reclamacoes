package br.com.reclameaqui.GestorDeReclamacoes.respository.templates;
import br.com.reclameaqui.GestorDeReclamacoes.model.Empresa;

import java.util.List;

public interface EmpresaRepositoryTemplate {
      List<String> recuperarReclamacoesPorEmpresa(Empresa empresa);


}

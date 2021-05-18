package br.com.reclameaqui.gestorreclamacoes.respository.templates;
import br.com.reclameaqui.gestorreclamacoes.model.Empresa;

import java.util.List;

public interface EmpresaRepositoryTemplate {
      List<String> recuperarReclamacoesPorEmpresa(Empresa empresa);


}
